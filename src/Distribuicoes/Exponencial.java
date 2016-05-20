package Distribuicoes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Exponencial implements Distribuicao{
	
	private double lambda;

	public Exponencial(){

	}

	public Exponencial(double lambda){
		this.lambda = lambda;
	}
	
	@Override
	public double getVal() {
		double rand = new Random().nextDouble();
		return (-1/lambda)*Math.log(1-rand);
	}

	@Override
	public void setParams(Double... params) {
		lambda = params[0];
	}

	@Override
	public List<String> getParams() {
		String[] params = {"lambda",};
		return new ArrayList<String>(Arrays.asList(params));
	}
}
