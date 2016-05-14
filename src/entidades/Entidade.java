package entidades;

import geral.Sistema;

public class Entidade {
	protected String nome;
	protected double tempo_fila=0;
	private double timestamp_ciclo, timestamp_fila ;
	
	public Entidade (String n){
		nome =n;
		timestamp_ciclo=0;
		timestamp_fila=0;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void set_timestamp_ciclo(){
		timestamp_ciclo = Sistema.tempo_atual;
	}
	
	public double get_timestamp_ciclo(){
		return timestamp_ciclo;
	}
	
	public void set_timestamp_fila(){
		timestamp_fila = Sistema.tempo_atual;
	}
	
	public double get_timestamp_fila(){
		return timestamp_fila;
	}
}
