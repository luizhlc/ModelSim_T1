package Distribuicoes;

public class Triangular implements Distribuicao{

	private double a, b, c, val_comparacao;
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
	public double getVal(double[] rand) {
		if(rand[0]<=val_comparacao){
			return a + Math.sqrt(rand[0]*(b-a)*(c-a));
		}
		return c-Math.sqrt((1-rand[0])*(c-b)*(c-a));
	}

}
