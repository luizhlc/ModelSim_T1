package evento;

import entidades.Entidade;
import entidades.Recurso;

public class Pesagem extends Evento{
	Recurso r;
	public Pesagem(double tmp, Entidade ent, Recurso rec){
		super(tmp, ent);
		r=rec;
	}


	@Override
	public void tratamento() {
		// TODO Auto-generated method stub
		
	}
}
