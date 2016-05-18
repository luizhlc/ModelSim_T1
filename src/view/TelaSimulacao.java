package view;

import Distribuicoes.Constante;
import Distribuicoes.Distribuicao;
import Estruturas.ListaRecurso;
import entidades.Recurso;
import geral.Config;
import geral.Hellport;
import geral.OptionalConsumer;
import geral.Sistema;

import javax.swing.*;
import java.util.Optional;

/**
 * Created by decio on 17/05/16.
 */
public class TelaSimulacao extends JFrame implements Runnable {
    private JPanel panel1;
    private JButton avançarPassoButton;
    private JButton iniciarButton;
    private JButton pararButton;
    private JTextArea textArea1;
    private JLabel carregadorEstado;
    private JLabel carregadorFila;
    private JLabel balancaEstado;
    private JLabel balancaFila;

    Sistema sistema = new Sistema();
    Distribuicao d = new Constante(2);

    boolean iniciado = false;

    public TelaSimulacao() {
        super("Simulação");

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 500);
        this.setContentPane(panel1);
        setVisible(true);

        iniciarButton.addActionListener(a -> {
            iniciar();
        });

        pararButton.addActionListener(a -> {
            parar();
        });

        avançarPassoButton.addActionListener(a -> {
            avancar();
        });
    }

    public void iniciar() {
        Distribuicao d = new Constante(2);
        Config.dist_balanca = d;
        Config.dist_carregador = d;
        Config.dist_transporte = d;
        Config.nroEntidades = 10;
        Config.tmpSimulacao = 50;
        sistema.initialize();

        Hellport r = Hellport.get_relatorio();
        for (int i = 0; i < 4; i++) {
            sistema.avancaTempo();
        }
        updateInfo();

        iniciado = true;
    }

    public void updateInfo() {
        this.balancaFila.setText(Sistema.fila_balanca.size() + "");
        this.carregadorFila.setText(Sistema.fila_carregamento.size() + "");

        updateEstado(Sistema.carregadores, carregadorEstado);
        updateEstado(Sistema.balancas, balancaEstado);

    }

    public void updateEstado(ListaRecurso listaRecurso, JLabel label) {
        Optional<Recurso> optRecurso = listaRecurso.stream().filter(b -> b.estaLivre()).findAny();
        OptionalConsumer.of(optRecurso).ifPresent(a -> {
            label.setText("Livre");
        }).ifNotPresent(() -> {
            label.setText("Ocupado");
        });
    }

    public void parar() {
        iniciado = false;
    }

    public void avancar() {
        Hellport r = Hellport.get_relatorio();
        sistema.avancaTempo();
        updateInfo();
        System.out.println("avançou");
    }

    long passo = 500;

    @Override
    public void run() {
        long currentTime = 0;
        while (true) {
            long elapsedTime = System.currentTimeMillis();
            if (elapsedTime - currentTime >= passo) {
                currentTime = elapsedTime;
                System.out.println("tic tac");
                if (iniciado) {
                    avancar();
                }
            }
        }
    }
}
