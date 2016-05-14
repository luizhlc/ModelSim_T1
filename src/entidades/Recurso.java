package entidades;

import java.util.Collection;
import java.util.Queue;


public class Recurso {
	private double tempo_servico;
	int nro_atendidos;
	private Collection<Entidade> saida;
	private Queue<Entidade> entrada;
	private Entidade cliente;
	private boolean livre;
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
		livre=false;
	}
	
	public Entidade get_cliente(){
		return cliente;
	}
	
	public void libera(double tempo){
		livre =true;
		nro_atendidos++;
		tempo_servico+=tempo;
		saida.add(cliente);
	}
	
	public boolean estaLivre(){
		return livre;
	}

	double getTempoServico(){
		return tempo_servico;
	}
	
	int getNroAtendidos(){
		return nro_atendidos;
	}
	
	
}
