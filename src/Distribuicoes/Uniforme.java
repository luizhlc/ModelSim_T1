package Distribuicoes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Uniforme implements Distribuicao{

	private double a, b;

	public Uniforme(){

	}

	public Uniforme(double minimo, double maximo){
		if(minimo>maximo){
			System.out.println("USUARIO BURRO: parâmetros não condizem com a distribuição uniforme");
		}
		a=minimo;
		b=maximo;
	}
	
	@Override
	public double getVal() {
		double r = new Random().nextDouble();
		return a+(b-a)*r;
	}

	@Override
	public void setParams(Double... params) {
		a = params[0];
		b = params[1];
	}

	@Override
	public List<String> getParams() {
		String[] params = {"a", "b"};
		return new ArrayList<String>(Arrays.asList(params));
	}
}
