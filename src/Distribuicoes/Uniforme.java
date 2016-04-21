package Distribuicoes;

public class Uniforme implements Distribuicao{

	private double a, b, c, val_comparacao;
	public Uniforme(double minimo, double medio, double maximo){
		if(minimo>medio || medio>maximo){
			System.out.println("USUARIO BURRO: parâmetros não condizem com a distribuição triangular");
		}
		a = minimo;
		b = medio;
		c = maximo;
		val_comparacao =  (b-a)/(c-a);
	}
	
	@Override
	public double getVal(double rand) {
		if(rand<=val_comparacao){
			return a + Math.sqrt(rand*(b-a)*(c-a));
		}
		return c-Math.sqrt((1-rand)*(c-b)*(c-a));
	}

}
