package view;

import entidades.Recurso;
import geral.Config;
import geral.FileWriter;
import geral.Relatorio;
import geral.Sistema;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.DecimalFormat;
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
    private JTextArea relatorio;
    private JTextArea eventosFuturos;
    private JButton relatorioButton;
    long passoSimulacao = 500;

    Sistema sistema = new Sistema();

    volatile boolean iniciado = false;

    volatile boolean isInterrupted = false;

    public TelaSimulacao() {
        super("Simulação");

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
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

        relatorioButton.addActionListener(a -> {
            gerarRelatorio();
        });

        slider.setMinimum(1);
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

        this.eventosFuturos.setText(getResultToPrint(sistema.getEventos()));

        updateRelatorio();
    }

    private void updateRelatorio() {
        DecimalFormat format = new DecimalFormat("###.###");

        String numEntidades = format.format(Relatorio.get_relatorio().getNumeroEntidadesNoSistema())+"";
        String numTransportes = Relatorio.get_relatorio().getNumeroTransportes()+"";
        String mediaOcupacaoCarregador1 = format.format(Relatorio.get_relatorio().getTaxaOcupacaoCarregador(0))+"";
        String mediaOcupacaoCarregador2 = format.format(Relatorio.get_relatorio().getTaxaOcupacaoCarregador(1))+"";
        String mediaOcupacaoBalanca = format.format(Relatorio.get_relatorio().getTaxaOcupacaoBalanca())+"";
//        String tempoEntidadeFila = format.format(Relatorio.get_relatorio().getTempoNaFila()[1])+"";
        String tempoEntidadeFilaPesagem = format.format(Relatorio.get_relatorio().getTempoNaFilaB()[1])+"";
        String tempoEntidadeFilaCarregamento = format.format(Relatorio.get_relatorio().getTempoNaFilaC()[1])+"";
        String tempoCiclo = format.format(Relatorio.get_relatorio().getTempoCiclo()[1])+"";

        relatorio.setText(
            "Número de entidades no sistema: " + numEntidades + "\n" +
            "Número de transportes: " + numTransportes + "\n" +
            "Taxa média de ocupação do carregador 1: " + mediaOcupacaoCarregador1 + "\n" +
            "Taxa média de ocupação do carregador 2: " + mediaOcupacaoCarregador2 + "\n" +
            "Taxa média de ocupação da balança: " + mediaOcupacaoBalanca + "\n" +
            "Tempo médio de uma entidade na fila de pesagem: " + tempoEntidadeFilaPesagem + "\n" +
            "Tempo médio de uma entidade na fila de carregamento: " + tempoEntidadeFilaCarregamento + "\n" +
            "Taxa médio de ciclo: " + tempoCiclo
        );
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
                if(Config.tmpSimulacao <= Sistema.tempo_atual){
                    int input = JOptionPane.showOptionDialog(null, "A simulação terminou!\nGerar relatório?", "", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

                    if(input == JOptionPane.OK_OPTION) {
                        gerarRelatorio();
                    }

                    input = JOptionPane.showOptionDialog(null, "Deseja simular novamente?", "", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
                    if(input == JOptionPane.OK_OPTION) {
                        reiniciar();
                    }
                    break;
                }
                currentTime = elapsedTime;
            }
        }
    }

    private void gerarRelatorio() {
        FileWriter.generateTxt(Relatorio.get_relatorio().getReport());

        JOptionPane.showMessageDialog(null, "O relatório foi gerado no diretório raiz desde projeto com o nome Relatório.txt!");

        int input = JOptionPane.showOptionDialog(null, "Deseja visualizá-lo?", "", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
        if(input == JOptionPane.OK_OPTION) {
            File file = new File(Paths.get(".").toAbsolutePath().normalize().toString()+"/Relatório.txt");
            try {
                Desktop.getDesktop().open(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
