package Distribuicoes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Normal implements Distribuicao{
	 
	private double med, dp;

	public Normal(){

	}

	public Normal(double media, double desvio_padrao){
		med = media;
		dp = desvio_padrao;
	}
	@Override
	public double getVal() {
		double r1 = new Random().nextDouble();
		double r2 = new Random().nextDouble();
		double z1 = Math.sqrt(-2*Math.log(r1))*Math.cos(2*Math.PI*r2);
		return med+dp*z1;
	}

	@Override
	public void setParams(Double... params) {
		med = params[0];
		dp = params[1];
	}

	@Override
	public List<String> getParams() {
		String[] params = {"med", "dp"};
		return new ArrayList<String>(Arrays.asList(params));
	}
}
