package evento;

import entidades.Entidade;
import entidades.Recurso;
import geral.Config;
import geral.Sistema;

public class Pesagem extends Evento{
	Recurso recurso;
	public Pesagem(double duracao, double tempo_atual, Entidade ent, Recurso rec){
		super(duracao,tempo_atual, ent);
		recurso=rec;
	}

	
	@Override
	public void tratamento() {
		//passa adiante
		recurso.libera(tempo_duração);
		
		//Verifica fila.
		if(!Sistema.fila_balanca.isEmpty()){
			recurso.ocupa();
			double duracao = Config.dist_balanca.getVal();
			Sistema.lista_eventos.add(new Pesagem(duracao, Sistema.tempo_atual, entidade, recurso));
		}
		

	}
}