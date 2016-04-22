package evento;

import entidades.Entidade;
import entidades.Recurso;
import geral.Config;
import geral.Sistema;

public class Carregamento extends Evento{
	Recurso r;
	public Carregamento(double duracao, double tempo_atual, Entidade ent, Recurso rec){
		super(duracao, tempo_atual, ent);
		r=rec;
	}

	@Override
	public void tratamento() {
		r.libera(tempo_duração);
		
		//passa pra frente
		if(Sistema.balanca.estaLivre()){
			Sistema.balanca.ocupa();
			double duracao = Config.dist_balanca.getVal();
			Sistema.lista_eventos.add(new Pesagem(duracao, Sistema.tempo_atual, e, Sistema.balanca));			
		}
		else{
			Sistema.fila_balanca.add(e);
		}
		
		//dispara proximo
		if(!Sistema.fila_carregamento.isEmpty()){
			r.ocupa();
			double duracao = Config.dist_carregador.getVal();
			Sistema.lista_eventos.add(new Carregamento(duracao, Sistema.tempo_atual, Sistema.fila_carregamento.remove(),r));
		}
		
	}
}
