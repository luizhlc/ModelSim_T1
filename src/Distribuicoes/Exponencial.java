package Distribuicoes;

import java.util.Random;

public class Exponencial implements Distribuicao{
	
	private double lambda;
	public Exponencial(double lambda){
		this.lambda = lambda;
	}
	
	@Override
	public double getVal() {
		double rand = new Random().nextDouble();
		return (-1/lambda)*Math.log(1-rand);
	}

}
