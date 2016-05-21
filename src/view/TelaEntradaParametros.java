package view;

import Distribuicoes.*;
import geral.Config;

import javax.swing.*;
import java.util.DoubleSummaryStatistics;

/**
 * Created by decio on 19/05/16.
 */
public class TelaEntradaParametros extends JFrame {
    private JComboBox comboDistribuicaoCarregador2;
    private JComboBox comboDistribuicaoBalanca;
    private JComboBox comboDistribuicaoTransporte;
    private JButton iniciarButton;
    private JPanel rootPanel;
    private JTextField param1Carregador2;
    private JTextField param2Carregador2;
    private JTextField param3Carregador2;
    private JTextField param1Balanca;
    private JTextField param3Balanca;
    private JTextField param1Transporte;
    private JTextField param2Transporte;
    private JTextField param3Transporte;
    private JLabel param1LabelCarregador2;
    private JLabel param2LabelCarregador2;
    private JLabel param3LabelCarregador2;
    private JLabel param2LabelBalanca;
    private JLabel param3LabelBalanca;
    private JLabel param1LabelTransporte;
    private JLabel param2LabelTransporte;
    private JLabel param3LabelTransporte;
    private JTextField param2Balanca;
    private JLabel param1LabelBalanca;
    private JTextField tempoTotalSimuladoTextField;
    private JPanel tempoTotalSimulado;
    private JTextField numCaminhoes;
    private final JTextField[][] inputsArray;
    private final JLabel[][] labelsArray;

    public TelaEntradaParametros(){
        super("Simulação");

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 500);
        this.setContentPane(rootPanel);
        setVisible(true);

        comboDistribuicaoCarregador2.setModel(new DefaultComboBoxModel<>(Distribuicoes.values()));
        comboDistribuicaoBalanca.setModel(new DefaultComboBoxModel<>(Distribuicoes.values()));
        comboDistribuicaoTransporte.setModel(new DefaultComboBoxModel<>(Distribuicoes.values()));

        labelsArray = new JLabel[][]{
                {param1LabelCarregador2, param2LabelCarregador2, param3LabelCarregador2},
                {param1LabelBalanca, param2LabelBalanca, param3LabelBalanca},
                {param1LabelTransporte, param2LabelTransporte, param3LabelTransporte}
        };

        inputsArray = new JTextField[][]{
                {param1Carregador2, param2Carregador2, param3Carregador2},
                {param1Balanca, param2Balanca, param3Balanca},
                {param1Transporte, param2Transporte, param3Transporte}
        };

        clearAllLabels();

        comboDistribuicaoCarregador2.addActionListener(a -> {
            handleDistribuicao(comboDistribuicaoCarregador2, 0);
        });

        comboDistribuicaoBalanca.addActionListener(a -> {
            handleDistribuicao(comboDistribuicaoBalanca, 1);
        });

        comboDistribuicaoTransporte.addActionListener(a -> {
            handleDistribuicao(comboDistribuicaoTransporte, 2);
        });

        iniciarButton.addActionListener(a -> {
            TelaSimulacao telaSimulacao = new TelaSimulacao();
            Config.dist_carregador = handleDistribuicao(comboDistribuicaoCarregador2, 0);
            Config.dist_balanca = handleDistribuicao(comboDistribuicaoBalanca, 1);
            Config.dist_transporte= handleDistribuicao(comboDistribuicaoCarregador2, 2);
            Config.tmpSimulacao = Integer.parseInt(this.tempoTotalSimuladoTextField.getText());
            Config.nroEntidades = Integer.parseInt(this.numCaminhoes.getText());
            telaSimulacao.init();
            new Thread(telaSimulacao).start();
            this.setVisible(false);
        });
    }

    private Distribuicao handleDistribuicao(JComboBox combo, int j) {
        Distribuicao d = Distribuicoes.getDistribuicaoByDescription(combo.getSelectedItem().toString());
        clear(j);

        for (int i = 0; i < d.getParams().size();i++){
            inputsArray[j][i].setVisible(true);
            labelsArray[j][i].setVisible(true);
            labelsArray[j][i].setText(d.getParams().get(i));
        }
        Double param1 = getParam(inputsArray[j][0]);
        Double param2 = getParam(inputsArray[j][1]);
        Double param3 = getParam(inputsArray[j][2]);
        d.setParams(param1,param2, param3);
        return d;
    }

    private double getParam(JTextField jTextField) {
        return jTextField.getText().isEmpty() ? 0 : Double.parseDouble(jTextField.getText());
    }

    private void clear(int j){
        for (int i=0;i<3;i++){
            labelsArray[j][i].setVisible(false);
        }

        for (int i=0;i<3;i++){
            inputsArray[j][i].setVisible(false);
        }
    }

    private void clearAllLabels() {
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                labelsArray[j][i].setVisible(false);
            }
        }

        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                inputsArray[j][i].setVisible(false);
            }
        }
    }

}
