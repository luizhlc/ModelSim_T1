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
    private JTextArea caminhoesViajando;
    private JTextArea caminhaoCarregador1;
    private JTextArea caminhaoCarregador2;
    private JLabel numViajando;
    private JTextArea caminhoesBalanca;
    private JButton resetarButton;
    private JSlider slider;
    long passoSimulacao = 500;

    Sistema sistema = new Sistema();

    boolean iniciado = false;

    volatile boolean isInterrupted = false;

    public TelaSimulacao() {
        super("Simulação");

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
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

        resetarButton.addActionListener(a -> {
            reiniciar();
        });

        slider.setMinimum(0);
        slider.setMaximum(3000);
        slider.addChangeListener(l -> {
            passoSimulacao = 3000 - slider.getValue();
            System.out.println("Passo: " + passoSimulacao);

        });
    }

    public void reiniciar() {
        new TelaEntradaParametros();
        this.setVisible(false);
        isInterrupted = true;
    }

    public void init(){
        sistema.initialize();
        updateInfo();
    }

    public void iniciar() {
        iniciado = true;
    }

    public void updateInfo() {

        String filaCarregadorToPrint = getResultToPrint(sistema.getFilaCarregador_toPrint());
        String filaBalancaToPrint = getResultToPrint(sistema.getFilaBalanca_toPrint());
        this.filaCarregamento.setText(filaCarregadorToPrint);
        this.filaPesagem.setText(filaBalancaToPrint);

        this.caminhaoCarregador1.setText(sistema.getEntidadeNoCarregador(0));
        this.caminhaoCarregador2.setText(sistema.getEntidadeNoCarregador(1));
        this.caminhoesBalanca.setText(sistema.getEntidadeNaBalanca());

        this.caminhoesViajando.setText(getResultToPrint(sistema.getEntidadesViajando()));

        this.fillNum("Fila carregamento", numFilaCarregamento, Sistema.fila_carregamento.size());
        this.fillNum("Fila pesagem", numFilaPesagem, Sistema.fila_balanca.size());
        this.fillNum("Caminhções viajando", numViajando, Sistema.viajando.size());

        carregador1Estado.setText(updateEstado(Sistema.carregadores.getRecurso(0)));
        carregador2Estado.setText(updateEstado(Sistema.carregadores.getRecurso(1)));
        balancaEstado.setText(updateEstado(Sistema.balancas.getRecurso(0)));

        tempoSimulacao.setText(Sistema.tempo_atual+"");

    }

    private void fillNum(String label, JLabel jLabelToChange, int size) {
        jLabelToChange.setText(label + " (" + size + ")");
    }

    private String getResultToPrint(List<String> lista){
        return lista.stream().reduce((recurso, resultado) -> recurso + "\n" + resultado).orElse("");
    }

    public String updateEstado(Recurso recurso) {
        return recurso.estaLivre() ? "Livre" : "Ocupado";
    }

    public void parar() {
        iniciado = false;
    }

    public void avancar() {
        sistema.avancaTempo();
        updateInfo();
        System.out.println("avançou");
    }

    @Override
    public void run() {
        long currentTime = 0;
        while(!isInterrupted){
            long elapsedTime = System.currentTimeMillis();
            if (elapsedTime - currentTime >= passoSimulacao) {
                if (iniciado) {
                    avancar();
                }
                if(Config.tmpSimulacao < Sistema.tempo_atual){
                    JOptionPane.showMessageDialog(null, "A simulação terminou!");
                    break;
                }
                currentTime = elapsedTime;
            }
        }
    }
}
