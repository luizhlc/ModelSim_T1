package entidades;

public abstract class Recurso {
	protected double tempo_ocioso=0;
	protected double nro_atendidos=0;
	protected boolean ocupado = false;
	
	public void ocupa(){
		ocupado=true;
	}
	
	public void libera(){
		ocupado =false;
		nro_atendidos++;
	}
	public void atualiza(double tmp){
		if(ocupado ==false){
			tempo_ocioso+=tmp;
		}
	}
	
}
