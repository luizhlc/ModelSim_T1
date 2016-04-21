package evento;

import entidades.Entidade;

public abstract class Evento {
	
	double tempo;
	Entidade e;
	public Evento(double tmp, Entidade ent){
		tempo = tmp;
		e = ent;
	}
	abstract public void tratamento();
}
