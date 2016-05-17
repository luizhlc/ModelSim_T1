package entidades;

import java.util.Collection;
import java.util.Queue;

import geral.Hellport;
import geral.Sistema;

public class Balanca extends Recurso{

	public Balanca(String n, Queue<Entidade> in, Collection<Entidade> out) {
		super(n, in, out);
	}
	
	@Override
	protected void update(){
		Hellport relatorio = Hellport.get_relatorio();
		relatorio.update_tmp_filaB(Sistema.tempo_atual-cliente.tempo_fila);
	}
	
}
