package evento;

import entidades.Entidade;

public abstract class Evento implements Comparable{

	protected double tempo_duração, tempo_disparo;
	protected Entidade entidade;
	public Evento(double duracao, double tempo_atual, Entidade ent){
		tempo_duração = duracao;
		tempo_disparo = tempo_atual+duracao;
		entidade = ent;
	}
	
	public double getTempo(){
		return tempo_disparo;
	}
	abstract public void tratamento();
	
	@Override
	public int compareTo(Object o) {
		if (!(o instanceof Evento))
            return 0;
		Evento n = (Evento)o;
		if(this.tempo_disparo<n.tempo_disparo)
			return -1;
		return 1;
	}
	
//	@Override
//	public boolean equals(Object o) {
//		if (!(o instanceof Evento))
//            return false;
//		Evento n = (Evento)o;
//
//        return this.tempo_disparo==n.tempo_disparo;
//	}
	
	public String estatisticas(){
		return "";
	}
	

}
