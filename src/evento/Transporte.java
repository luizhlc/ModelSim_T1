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
		Sistema.fila_carregamento.add(entidade);
		
		Recurso rec = Sistema.carregadores.pegaLivre();
		if(rec!=null){
			rec.ocupa();
			double duracao = Config.dist_carregador.getVal();
			Sistema.lista_eventos.add(new Carregamento(duracao, Sistema.tempo_atual, Sistema.fila_carregamento.remove(),rec));
			return;
		}
	}
}
