package view;

import Distribuicoes.Constante;
import Distribuicoes.Distribuicao;
import geral.Config;
import geral.Hellport;
import geral.Sistema;

import javax.swing.*;

/**
 * Created by decio on 17/05/16.
 */
public class TelaSimulacao extends JFrame {
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
		Config.dist_balanca=d;
		Config.dist_carregador=d;
		Config.dist_transporte=d;
		Config.nroEntidades=10;
		Config.tmpSimulacao=50;
        sistema.initialize();

        Hellport r = Hellport.get_relatorio();
		for(int i=0; i<4;i++){
			sistema.avancaTempo();
		}
        updateInfo();
    }

    public void updateInfo(){
        this.balancaFila.setText(Sistema.fila_balanca.size()+"");
        this.carregadorFila.setText(Sistema.fila_carregamento.size()+"");

        if(Sistema.balancas.stream().filter(b -> b.estaLivre()).findAny().isPresent()) {
            this.balancaEstado.setText("Livre");
        }else{
            this.balancaEstado.setText("Ocupado");
        }

        if(Sistema.carregadores.stream().filter(c -> c.estaLivre()).findAny().isPresent()) {
            this.carregadorEstado.setText("Livre");
        }else{
            this.carregadorEstado.setText("Ocupado");
        }
    }

    public void parar() {

    }

    public void avancar() {
        Hellport r = Hellport.get_relatorio();
            sistema.avancaTempo();
        updateInfo();
        System.out.println("avançou");
    }
}
