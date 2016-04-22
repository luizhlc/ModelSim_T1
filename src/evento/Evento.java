package evento;

import entidades.Entidade;

public abstract class Evento{

	protected double tempo_duração, tempo_disparo;
	protected Entidade e;
	public Evento(double duracao, double tempo_atual, Entidade ent){
		tempo_duração = duracao;
		tempo_disparo = tempo_atual+duracao;
		e = ent;
	}
	
	public double getTempo(){
		return tempo_disparo;
	}
	abstract public void tratamento();

	public int compareTo(Evento o) {
		if(this.tempo_disparo<o.tempo_disparo)
			return 1;
		return -1;
	}

}
