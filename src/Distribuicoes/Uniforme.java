package Distribuicoes;

public class Uniforme implements Distribuicao{

	private double a, b;
	public Uniforme(double minimo, double maximo){
		if(minimo>maximo){
			System.out.println("USUARIO BURRO: parâmetros não condizem com a distribuição uniforme");
		}
		a=minimo;
		b=maximo;
	}
	
	@Override
	public double getVal(double rand) {
		return a+(b-a)*rand;
	}

}
