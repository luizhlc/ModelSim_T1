package geral;

import java.util.Vector;

import javax.swing.plaf.synth.SynthSeparatorUI;

import Distribuicoes.Constante;
import Distribuicoes.Distribuicao;

public class Main {

	public static void main(String[] args) {
		Sistema a = new Sistema();
		Distribuicao d = new Constante(2);
		Config.dist_balanca=d;
		Config.dist_carregador=d;
		Config.dist_transporte=d;
		Config.nroEntidades=10;
		Config.tmpSimulacao=50;
		a.initialize();
		Relatorio r = Relatorio.get_relatorio();
		for(int i=0; i<50;i++){
			a.avancaTempo();
		
		}
		Vector<String> v = a.getEventos();
		for(int i =0; i<v.size();i++){
			System.out.println(v.elementAt(i));
		}
		
		System.out.println(r.getReport());
	}

}
