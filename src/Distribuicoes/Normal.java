package Distribuicoes;

public class Normal implements Distribuicao{
	 
	private double med, dp;
	public Normal(double media, double desvio_padrao){
		med = media;
		dp = desvio_padrao;
	}
	@Override
	public double getVal(double[] rand) {
		double z1 = Math.sqrt(-2*Math.log(rand[0]))*Math.cos(2*Math.PI*rand[1]);
//		double z2 = Math.sqrt(-2*Math.log(rand[1]))*Math.cos(2*Math.PI*rand[0]);
		return med+dp*z1;
	}

}
