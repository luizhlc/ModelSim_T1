package evento;

import entidades.Entidade;
import entidades.Recurso;
import geral.Config;
import geral.Sistema;

public class Pesagem extends Evento{
	Recurso r;
	public Pesagem(double duracao, double tempo_atual, Entidade ent, Recurso rec){
		super(duracao,tempo_atual, ent);
		r=rec;
	}

	
	@Override
	public void tratamento() {
		//passa adiante
		r.libera(tempo_duração);
		if(r.estaLivre()){
			r.ocupa();
			double duracao = Config.dist_transporte.getVal();
			Sistema.lista_eventos.add(new Transporte(duracao, Sistema.tempo_atual, e));
		}
		else{
			Sistema.fila_balanca.add(e);
		}
		
		//Verifica fila.
		if(!Sistema.fila_balanca.isEmpty()){
			r.ocupa();
			double duracao = Config.dist_balanca.getVal();
			Sistema.lista_eventos.add(new Pesagem(duracao, Sistema.tempo_atual, e, r));
		}
		

	}
}
