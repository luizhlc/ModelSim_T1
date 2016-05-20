package Distribuicoes;

import java.util.Arrays;

/**
 * Created by decio on 19/05/16.
 */
public enum Distribuicoes implements  IsDistribuicao{
    Selecione(""){
        @Override
        public Distribuicao getDistribuicao() {
            return null;
        }
    },
    AleatorioEntre("AleatorioEntre"){
        @Override
        public Distribuicao getDistribuicao() {
            return new AleatorioEntre();
        }
    },
    Constante("Constante"){
        @Override
        public Distribuicao getDistribuicao() {
            return new Constante();
        }
    },
    Exponencial("Exponencial"){
        @Override
        public Distribuicao getDistribuicao() {
            return new Exponencial();
        }
    },
    Normal("Normal"){
        @Override
        public Distribuicao getDistribuicao() {
            return new Normal();
        }
    },
    Triangular("Triangular"){
        @Override
        public Distribuicao getDistribuicao() {
            return new Triangular();
        }
    },
    Uniforme("Uniforme"){
        @Override
        public Distribuicao getDistribuicao() {
            return new Uniforme();
        }
    };

    String description;

    Distribuicoes(String description){
        this.description = description;
    }

    public static Distribuicao getDistribuicaoByDescription(String description) {
        return Arrays.asList(values()).stream().filter(d -> d.description.equals(description)).findAny().orElse(Selecione).getDistribuicao();
    }
}
