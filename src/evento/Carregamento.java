package evento;

import entidades.Entidade;
import entidades.Recurso;
import geral.Config;
import geral.Sistema;

public class Carregamento extends Evento{
	Recurso recurso;
	public Carregamento(double duracao, double tempo_atual, Entidade ent, Recurso rec){
		super(duracao, tempo_atual, ent);
		recurso=rec;
	}

	@Override
	public void tratamento() {
		recurso.libera(tempo_duração);
		
		//passa pra frente
		Recurso proximo = Sistema.balancas.pegaLivre();
		if(proximo!=null){
			proximo.ocupa();
			double duracao = Config.dist_balanca.getVal();
			Sistema.lista_eventos.add(new Pesagem(duracao, Sistema.tempo_atual, entidade, proximo));			
		}
		
		disparaCarregamento();
		
		//dispara proximo
	}

	private void disparaCarregamento(){
		if(!Sistema.fila_carregamento.isEmpty()){
			recurso.ocupa();
			double duracao = Config.dist_carregador.getVal();
			Sistema.lista_eventos.add(new Carregamento(duracao, Sistema.tempo_atual, recurso.get_cliente(),recurso));
		}
	}
	@Override
	protected void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toPrint() {
		String retorno=""+tempo_disparo +" | Carregamento | "+entidade.getNome()+";\n";
		return retorno;
	}
}
