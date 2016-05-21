package entidades;

import java.util.Collection;
import java.util.Queue;

import geral.Relatorio;
import geral.Sistema;

public class Balanca extends Recurso{

	public Balanca(String n, Queue<Entidade> in, Collection<Entidade> out) {
		super(n, in, out);
	}
	
	@Override
	protected void update(){
		Relatorio relatorio = Relatorio.get_relatorio();
		relatorio.update_tmp_filaB(Sistema.tempo_atual-cliente.get_timestamp_fila());
	}
	
}
