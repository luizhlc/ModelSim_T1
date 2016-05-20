package Distribuicoes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class AleatorioEntre implements Distribuicao{

	private double min, max;

	public AleatorioEntre() {

	}

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

	@Override
	public void setParams(Double... params) {
		min = params[0];
		max = params[1];
	}

	@Override
	public List<String> getParams() {
		String[] params = {"min", "max"};
		return new ArrayList<String>(Arrays.asList(params));
	}

}
