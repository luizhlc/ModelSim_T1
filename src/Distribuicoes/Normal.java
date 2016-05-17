package Distribuicoes;

import java.util.Random;

public class Normal implements Distribuicao{
	 
	private double med, dp;
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

}
