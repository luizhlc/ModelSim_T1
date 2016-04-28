package Estruturas;

import java.util.Vector;
import entidades.Recurso;

public class ListaRecurso {
	
	private Vector<Recurso> recursos;
	
	
	public Recurso recursoPos(int i){
		return recursos.elementAt(i);
	}

	public ListaRecurso(){
		recursos = new Vector<Recurso>();
	}
	
	public void adicionaRecurso(Recurso r){
		recursos.add(r);
	}
	
	public Recurso pegaLivre(){
		for(int i=0; i<recursos.size();i++){
			if(recursos.elementAt(i).estaLivre()){
				return recursos.elementAt(i);
			}
		}
		return null;
	}
}
