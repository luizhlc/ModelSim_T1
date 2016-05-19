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
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by decio on 17/05/16.
 */
public class TelaSimulacao extends JFrame implements Runnable {
    private JPanel panel1;
    private JButton avançarPassoButton;
    private JButton iniciarButton;
    private JButton pararButton;
    private JLabel carregador1Estado;
    private JLabel carregador2Estado;
    private JLabel balanca1Estado;
    private JTextArea filaCarregamento;
    private JTextArea filaPesagem;
    private JLabel numFilaCarregamento;
    private JLabel numFilaPesagem;

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

        String filaCarregadorToPrint = getResultToPrint(sistema.getFilaBalanca_toPrint());
        String filaBalancaToPrint = getResultToPrint(sistema.getFilaCarregador_toPrint());

        this.filaCarregamento.setText(filaCarregadorToPrint);
        this.filaPesagem.setText(filaBalancaToPrint);

        String numFilaCarregamentoText = Sistema.fila_carregamento.size() + "";
        numFilaCarregamento.setText("Fila carregamento (" + numFilaCarregamentoText + ")");
        String numFilaBalancaText = Sistema.fila_balanca.size() + "";
        numFilaPesagem.setText("Fila pesagem (" + numFilaBalancaText + ")");

        updateEstado(Sistema.carregadores, carregador1Estado);
        updateEstado(Sistema.balancas, carregador2Estado);

    }

    private String getResultToPrint(List<String> lista){
        return lista.stream().reduce((recurso, resultado) -> recurso + resultado + "\n").orElse("");
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
