package geral;

import Distribuicoes.Constante;
import Distribuicoes.Distribuicao;

public class Main {

	public static void main(String[] args) {
		Sistema a = new Sistema();
		Distribuicao d = new Constante(2);
		Config.dist_balanca=d;
		Config.dist_carregador=d;
		Config.dist_transporte=d;
		Config.nroEntidades=3;
		Config.tmpSimulacao=50;
		a.initialize();
		while(true){
			a.avancaTempo();
		}
	}

}
