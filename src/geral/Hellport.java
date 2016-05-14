package geral;

public class Hellport {
	private static Hellport relatorio = new Hellport();
	private int nro_transportes;
	private int nro_Entidades_Sistema;
	
	private double tmp_ciclo_max;
	private double tmp_ciclo_min;
	private double tmp_ciclo_med;
	
	private int nro_entidades_filaC;
	private double tmp_filaC_max;
	private double tmp_filaC_min;
	private double tmp_filaC_med;
	
	private int nro_entidades_filaB;
	private double tmp_filaB_max;
	private double tmp_filaB_min;
	private double tmp_filaB_med;
	
	
	private Hellport(){
		reset();
	}
	
	public void reset(){
		nro_transportes=0;
		nro_Entidades_Sistema=0;
		tmp_ciclo_max=-1;
		tmp_ciclo_min=Double.MAX_VALUE;
		tmp_ciclo_med=0;
		
		nro_entidades_filaC =0;
		tmp_filaC_max=-1;
		tmp_filaC_min=Double.MAX_VALUE;
		tmp_filaC_med=0;
		
		nro_entidades_filaB=0;
		tmp_filaB_max=-1;
		tmp_filaB_min=Double.MAX_VALUE;
		tmp_filaB_med=0;
	}
	
	public static Hellport get_relatorio(){
		return relatorio;
	}
	
	
	public void update_tmp_ciclo(double tempo){
		nro_transportes++;
		if(tempo>tmp_ciclo_max){
			tmp_ciclo_max=tempo;
		}
		else{
			if(tempo<tmp_ciclo_min){
				tmp_ciclo_min=tempo;
			}
		}
		tmp_ciclo_med+=tempo;
	}
	
//	Se o Melga pega.....
	public void update_tmp_filaC(double tempo){
		nro_entidades_filaC++;
		if(tempo>tmp_filaC_max){
			tmp_filaC_max=tempo;
		}
		else{
			if(tempo<tmp_filaC_min){
				tmp_filaC_min=tempo;
			}
		}
		tmp_filaC_med+=tempo;
	}
	
	public void update_tmp_filaB(double tempo){
		nro_entidades_filaB++;
		if(tempo>tmp_filaB_max){
			tmp_filaB_max=tempo;
		}
		else{
			if(tempo<tmp_filaB_min){
				tmp_filaB_min=tempo;
			}
		}
		tmp_filaB_med+=tempo;
	}
	
	public void update_nro_entidades_sistema(int d){
		nro_Entidades_Sistema+=d;
	}
}
