package entidades;

import java.util.Collection;
import java.util.Queue;

import geral.Sistema;

public abstract class Recurso {
	private double tempo_servico;
	int nro_atendidos;
	protected Collection<Entidade> saida;
	protected Queue<Entidade> entrada;
	protected Entidade cliente;
	protected boolean livre;
	String nome;
	
	public Recurso(String n, Queue<Entidade> in, Collection<Entidade> out){
		nome =n;
		tempo_servico=0;
		nro_atendidos=0;
		livre=true;
		entrada =in;
		saida = out;	
	}
	
	public void ocupa(){
		cliente = entrada.remove();
		update();
		livre=false;
	}
	
	public Entidade get_cliente(){
		return cliente;
	}
	
	public void libera(double tempo){
		livre =true;
		nro_atendidos++;
		tempo_servico+=tempo;
		cliente.set_timestamp_fila();
		saida.add(cliente);
	}
	
	public boolean estaLivre(){
		return livre;
	}

	double get_TaxaOcupacao(){
		return tempo_servico/Sistema.tempo_atual;
	}
	
	int getNroAtendidos(){
		return nro_atendidos;
	}
	
	abstract public void update();
	
	
}
