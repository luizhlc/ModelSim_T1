package Distribuicoes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Triangular implements Distribuicao{

	private double a, b, c, val_comparacao;

	public Triangular(){

	}

	public Triangular(double minimo, double medio, double maximo){
		if(minimo>medio || medio>maximo){
			System.out.println("USUARIO BURRO: parâmetros não condizem com a distribuição triangular");
		}
		a = minimo;
		b = medio;
		c = maximo;
		val_comparacao =  (b-a)/(c-a);
	}
	
	@Override
	public double getVal() {
		Double r = new Random().nextDouble();
		if(r<=val_comparacao){
			return a + Math.sqrt(r*(b-a)*(c-a));
		}
		return c-Math.sqrt((1-r)*(c-b)*(c-a));
	}

	@Override
	public void setParams(Double... params) {
		a = params[0];
		b = params[1];
		c = params[2];
	}

	@Override
	public List<String> getParams() {
		String[] params = {"a", "b", "c"};
		return new ArrayList<String>(Arrays.asList(params));
	}
}
