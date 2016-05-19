package geral;

import entidades.Balanca;

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
		nro_Entidades_Sistema=Config.nroEntidades;
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
	
	public double[] getTamanhoDaFila(){
		double[] report = new double[3];
		if(this.tamanho_filaB_min<tamanho_filaC_min)
			report[0] = this.tamanho_filaB_min;
		else
			report[0] = this.tamanho_filaC_min;
		
		for(int i=0; i<Config.nroEntidades+1;i++){
			
		}
		
		report[1] = (tmp_filaB_med+tmp_filaC_med)/(nro_entidades_filaB+nro_entidades_filaC);
		
		
		
		if(tamanho_filaB_max>tamanho_filaC_max)
			report[2] = this.tamanho_filaB_max;
		else
			report[2] = this.tamanho_filaC_max;
		return report;
	}
	
	public double getTaxaOcupacaoBalanca(){
		double t = Sistema.balancas.getRecurso(0).get_TaxaOcupacao();
		return t;
	}
	
	public double getTaxaOcupacaoCarregador(int i){
		double t = Sistema.carregadores.getRecurso(i).get_TaxaOcupacao();
		return t;
	}
	
	public double[] getTempoNaFila(){
		double[] report = new double[3];
		if(tmp_filaB_min<tmp_filaC_min)
			report[0] = this.tmp_filaB_min;
		else
			report[0] = this.tmp_filaC_min;
		
		report[1] = (tmp_filaB_med+tmp_filaC_med)/(nro_entidades_filaB+nro_entidades_filaC);
		
		if(tmp_filaB_max>tmp_filaC_max)
			report[2] = this.tmp_filaB_max;
		else
			report[2] = this.tmp_filaC_max;
		
		return report;
	}
	
	public double[] getTempoCiclo(){
		double[] report = new double[3];
		report[0] = tmp_ciclo_min;
		report[1] = tmp_ciclo_med/nro_transportes;
		report[2] = tmp_ciclo_max;
		return report;
	}
	
	public int getNumeroTransportes(){
		return this.nro_transportes;
	}
	
	public int getNumeroEntidadesNoSistema(){
		return this.nro_Entidades_Sistema;
	}
	
	public String getReport(){
		String report="";
		report+="==========Número de entidades das filas==========\n";
		report+="-Mínimo: "+1+";\n";
		report+="-Médio: "+1+";\n";
		report+="-Máximo: "+1+";\n\n";
		
		report+="==========Taxa Média de Ocupação==========\n";
		report+="-Carregador 1: "+this.getTaxaOcupacaoCarregador(0)*100+"%;\n";
		report+="-Carregador 2: "+this.getTaxaOcupacaoCarregador(1)*100+"%;\n";
		report+="-Balança: "+this.getTaxaOcupacaoBalanca()*100+"%;\n\n";
		
		report+="==========Tempo de uma Entidade na Fila==========\n";
		double[] tempo_na_fila = this.getTempoNaFila();
		report+="-Mínimo: "+tempo_na_fila[0]+";\n";
		report+="-Médio: "+tempo_na_fila[1]+";\n";
		report+="-Máximo: "+tempo_na_fila[2]+";\n\n";
		
		report+="==========Tempo de Ciclo==========\n";
		if(this.getNumeroTransportes()==0){
			report+="Nenhum ciclo completado; \n\n";
		}
		else{
			double[] tempo_de_ciclo = this.getTempoCiclo();
			report+="-Mínimo: "+tempo_de_ciclo[0]+";\n";
			report+="-Médio: "+tempo_de_ciclo[1]+";\n";
			report+="-Máximo: "+tempo_de_ciclo[2]+";\n\n";
		}
		
		report+="==========Outros==========\n";
		report+="-Número de transportes: "+this.getNumeroTransportes()+";\n";
		report+="-Entidades no Sistema: "+this.nro_Entidades_Sistema+";\n";
		report+="-Tempo de simulação: "+Sistema.tempo_atual+";\n\n";
		return report;
	}
}
