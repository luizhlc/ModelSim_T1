package Estruturas;

import entidades.Recurso;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ListaRecurso {
	
	private List<Recurso> recursos;

	public Recurso recursoPos(int i){
		return recursos.get(i);
	}

	public ListaRecurso(){
		recursos = new ArrayList<Recurso>();
	}

	public Stream<Recurso> stream(){
		return recursos.stream();
	}
	
	public void adicionaRecurso(Recurso r){
		recursos.add(r);
	}
	
	public Recurso getRecurso(int i){
		return recursos.get(i);
	}
	
	public Recurso pegaLivre(){
		for(int i=0; i<recursos.size();i++){
			if(recursos.get(i).estaLivre()){
				return recursos.get(i);
			}
		}
		return null;
	}
}
