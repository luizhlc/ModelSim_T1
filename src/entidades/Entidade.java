package entidades;

public class Entidade {
	protected String nome;
	protected double tempo_fila=0;
	public Entidade (String n){
		nome =n;
	}
	public String getNome() {
		return nome;
	}
}
