package geral;

public class Hellport {
	private static Hellport relatorio;

	private double timestamp_fila;
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

	private int tamanho_filaC_min;
	private int tamanho_filaC_max;

	private int tamanho_filaB_min;
	private int tamanho_filaB_max;

	private double[] tamanho_tempo_filaC;
	private double[] tamanho_tempo_filaB;

	private Hellport(){}

	public void reset(){
		timestamp_fila =0;

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

		tamanho_filaC_min=Integer.MAX_VALUE;
		tamanho_filaC_max=-1;

		tamanho_filaB_min=Integer.MAX_VALUE;
		tamanho_filaB_max=-1;

		tamanho_tempo_filaC = new double[Config.nroEntidades+1];
		tamanho_tempo_filaB = new double[Config.nroEntidades+1];
		for(int i =0; i<Config.nroEntidades;i++){
			tamanho_tempo_filaC[i]=0;
			tamanho_tempo_filaB[i]=0;
		}
	}

	public static Hellport get_relatorio(){
		if(relatorio==null){
			relatorio = new Hellport();
			relatorio.reset();
		}
		return relatorio;
	}


	public void update_tmp_ciclo(double tempo){
		nro_transportes++;
		if(tempo>tmp_ciclo_max){
			tmp_ciclo_max=tempo;
		}
		if(tempo<tmp_ciclo_min){
			tmp_ciclo_min=tempo;
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
		if(tempo<tmp_filaB_min){
			tmp_filaB_min=tempo;
		}
		tmp_filaB_med+=tempo;
	}

	public void update_tamanho_filaC(int size){
		if(Double.compare(Sistema.tempo_atual, timestamp_fila)!=0){
			tamanho_tempo_filaC[size]+=Sistema.tempo_atual-timestamp_fila;
			if(size>tamanho_filaC_max){
				tamanho_filaC_max=size;
			}
			if(size<tamanho_filaC_min){
				tamanho_filaC_min=size;
			}
		}

	}

	public void update_tamanho_filaB(int size){
		if(Double.compare(Sistema.tempo_atual, timestamp_fila)!=0){
			tamanho_tempo_filaB[size]+=Sistema.tempo_atual-timestamp_fila;
			if(size>tamanho_filaB_max){
				tamanho_filaB_max=size;
			}
			if(size<tamanho_filaB_min){
				tamanho_filaB_min=size;
			}
		}
	}

	public void set_timestamp(){
		timestamp_fila=Sistema.tempo_atual;
	}
	public void update_nro_entidades_sistema(int d){
		nro_Entidades_Sistema+=d;
	}
}
