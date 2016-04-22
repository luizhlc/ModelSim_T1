package entidades;

import geral.Sistema;

public class Recurso {
	protected double tempo_servico;
	protected double nro_atendidos;
	protected boolean livre;
	
	public Recurso(){
		tempo_servico=0;
		nro_atendidos=0;
		livre=true;
		
	}
	public void ocupa(){
		livre=false;
	}
	
	public void libera(double tempo){
		livre =true;
		nro_atendidos++;
		tempo_servico+=tempo;
	}
	
	public boolean estaLivre(){
		return livre;
	}
	
}
