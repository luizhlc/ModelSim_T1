package view;

import entidades.Recurso;
import geral.Config;
import geral.Sistema;

import javax.swing.*;
import java.util.List;

/**
 * Created by decio on 17/05/16.
 */
public class TelaSimulacao extends JFrame implements Runnable{
    private JPanel panel1;
    private JButton avançarPassoButton;
    private JButton iniciarButton;
    private JButton pararButton;
    private JLabel carregador1Estado;
    private JLabel carregador2Estado;
    private JLabel balancaEstado;
    private JTextArea filaCarregamento;
    private JTextArea filaPesagem;
    private JLabel numFilaCarregamento;
    private JLabel numFilaPesagem;
    private JLabel tempoSimulacao;
    private JTextArea caminhoesBalanca;
    private JTextArea caminhoesViajando;
    private JTextArea caminhaoCarregador1;
    private JTextArea caminhaoCarregador2;

    Sistema sistema = new Sistema();

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
        sistema.initialize();
        updateInfo();
        iniciado = true;
    }

    public void updateInfo() {

        String filaCarregadorToPrint = getResultToPrint(sistema.getFilaCarregador_toPrint());
        String filaBalancaToPrint = getResultToPrint(sistema.getFilaBalanca_toPrint());
        this.filaCarregamento.setText(filaCarregadorToPrint);
        this.filaPesagem.setText(filaBalancaToPrint);

        this.caminhaoCarregador1.setText(sistema.getEntidadeNoCarregador(0));
        this.caminhaoCarregador2.setText(sistema.getEntidadeNoCarregador(1));

//        this.caminhoesBalanca.setText(sistema.getEntidadeNaBalanca());

        this.caminhoesViajando.setText(getResultToPrint(sistema.getEntidadesViajando()));

        String numFilaCarregamentoText = Sistema.fila_carregamento.size() + "";
        numFilaCarregamento.setText("Fila carregamento (" + numFilaCarregamentoText + ")");
        String numFilaBalancaText = Sistema.fila_balanca.size() + "";
        numFilaPesagem.setText("Fila pesagem (" + numFilaBalancaText + ")");

        carregador1Estado.setText(updateEstado(Sistema.carregadores.getRecurso(0)));
        carregador2Estado.setText(updateEstado(Sistema.carregadores.getRecurso(1)));
        balancaEstado.setText(updateEstado(Sistema.balancas.getRecurso(0)));

        tempoSimulacao.setText(Sistema.tempo_atual+"");

    }

    private String getResultToPrint(List<String> lista){
        return lista.stream().reduce((recurso, resultado) -> recurso + "\n" + resultado).orElse("");
    }

    public String updateEstado(Recurso recurso) {
        if(recurso.estaLivre())
            return "Livre";
        else
            return "Ocupado";
    }

    public void parar() {
        iniciado = false;
    }

    public void avancar() {
        sistema.avancaTempo();
        updateInfo();
        System.out.println("avançou");
    }

    long passo = 500;

    @Override
    public void run() {
        long currentTime = 0;
//        while () {
        while(true){
            long elapsedTime = System.currentTimeMillis();
            if (elapsedTime - currentTime >= passo) {
                currentTime = elapsedTime;
                if(Config.tmpSimulacao < Sistema.tempo_atual){
                    System.out.println("tomar no cu");
                    JOptionPane.showMessageDialog(null, "A simulação terminou!");
                    break;
                }
                System.out.println("tic tac");
                if (iniciado) {
                    avancar();
                }
            }
        }
    }
}
