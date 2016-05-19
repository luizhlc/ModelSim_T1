package geral;

import view.TelaSimulacao;

public class Main {

	public static void main(String[] args) {
        new Thread(new TelaSimulacao()).run();
	}

}
