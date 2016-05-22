package evento;

import entidades.Entidade;
import entidades.Recurso;
import geral.Config;
import geral.Relatorio;
import geral.Sistema;

public class Pesagem extends Evento{
	Recurso recurso;
	public Pesagem(double duracao, double tempo_atual, Entidade ent, Recurso rec){
		super(duracao,tempo_atual, ent);
		recurso=rec;
	}

	@Override
	public void tratamento() {
		update();
		
		//passa adiante
		recurso.libera(tempo_duração);
		double duracao_transporte = Config.dist_transporte.getVal();
		Sistema.lista_eventos.add(new Transporte(duracao_transporte, Sistema.tempo_atual, entidade));
		
		//Verifica fila.
		if(!Sistema.fila_balanca.isEmpty()){
			recurso.ocupa();
			double duracao = Config.dist_balanca.getVal();
			Sistema.lista_eventos.add(new Pesagem(duracao, Sistema.tempo_atual, recurso.get_cliente(), recurso));
		}
		

	}
	
	@Override
	protected void update(){
		Relatorio relatorio = Relatorio.get_relatorio();
		relatorio.update_nro_entidades_sistema(-1);
	}
	
	@Override
	public String toPrint() {
		String retorno=""+tempo_disparo +" | Pesagem | "+entidade.getNome()+";\n";
		return retorno;
	}
}
