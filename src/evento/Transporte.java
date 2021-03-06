package evento;

import entidades.Entidade;
import entidades.Recurso;
import geral.Config;
import geral.Hellport;
import geral.Sistema;

public class Transporte extends Evento{
	public Transporte(double duracao, double tempo_atual, Entidade ent){
		super(duracao, tempo_atual, ent);
	}

	@Override
	public void tratamento() {
		update();
		
		//fim do transporte
		
		Sistema.fila_carregamento.add(entidade);
		
		//verifica se recurso livre
		Recurso rec = Sistema.carregadores.pegaLivre();
		if(rec!=null){
			rec.ocupa();
			double duracao = Config.dist_carregador.getVal();
			Sistema.lista_eventos.add(new Carregamento(duracao, Sistema.tempo_atual, rec.get_cliente(),rec));
			return;
		}
	}
	
	@Override
	protected void update(){
		Hellport relatorio = Hellport.get_relatorio();
		relatorio.update_tmp_ciclo(Sistema.tempo_atual-entidade.get_timestamp_ciclo());
		relatorio.update_nro_entidades_sistema(1);
		
		entidade.set_timestamp_ciclo();
	}
}
