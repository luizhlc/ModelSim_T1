package Distribuicoes;

public class Constante implements Distribuicao{

	private double a;
	public Constante(double val){
		a = val;
	}

	@Override
	public double getVal() {
		return a;
	}

}
