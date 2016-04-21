package Distribuicoes;

public class Exponencial implements Distribuicao{
	
	private double lambda;
	public Exponencial(double lambda){
		this.lambda = lambda;
	}
	
	@Override
	public double getVal(double rand) {
		return (-1/lambda)*Math.log(1-rand);
	}

}
