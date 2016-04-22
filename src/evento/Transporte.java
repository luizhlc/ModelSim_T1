package evento;

import entidades.Entidade;
import entidades.Recurso;
import geral.Config;
import geral.Sistema;

public class Transporte extends Evento{
	public Transporte(double duracao, double tempo_atual, Entidade ent){
		super(duracao, tempo_atual, ent);
	}

	@Override
	public void tratamento() {
		//verifica se recurso livre
		boolean livre = false;
		Recurso r = null;
		if(Sistema.carregador1.estaLivre()){
			r = Sistema.carregador1;
			livre=true;
		}
		else{
			if(Sistema.carregador2.estaLivre()){
				r = Sistema.carregador2;
				livre=true;
			}
		}
		
		if(livre){
			double duracao = Config.dist_carregador.getVal();
			Sistema.lista_eventos.add(new Carregamento(duracao, Sistema.tempo_atual, Sistema.fila_carregamento.remove(),r));
			return;
		}
		
		Sistema.fila_carregamento.add(e);	
	}
}
