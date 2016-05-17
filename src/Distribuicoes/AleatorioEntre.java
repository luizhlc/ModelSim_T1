package Distribuicoes;

import java.util.Random;

public class AleatorioEntre implements Distribuicao{

	private double min, max;
	public AleatorioEntre(double minimo, double maximo){
		if(minimo>maximo){
			System.out.println("USUARIO BURRO: parâmetros não condizem com aleatorio");
		}
		min=minimo;
		max=maximo;
	}
	
	@Override
	public double getVal() {
		double r = new Random().nextDouble();
		return min + (max-min)*r;
	}

}
