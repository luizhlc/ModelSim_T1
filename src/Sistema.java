import java.util.LinkedList;
import java.util.Queue;

import entidades.Balanca;
import entidades.Carregador;
import entidades.Entidade;
import entidades.Recurso;
import evento.Carregamento;
import evento.Evento;


public class Sistema {
	private Queue<Entidade> fila_carregamento = new LinkedList<Entidade>();
	private Queue<Entidade> fila_balanca = new LinkedList<Entidade>();
	private double tempo_atual =0;
	
	private Recurso carregador1 = new Carregador();
	private Recurso carregador2 = new Carregador();
	private Recurso balanca = new Balanca();
	
	public void initialize(){
		
		Entidade a = new Entidade("caminhao_1");
		double tempoA = Config.dist_carregador.getVal();
		Evento eA = new Carregamento(tempoA, a, carregador1);
		//adicionar na lista ordenada de eventos
		Entidade b = new Entidade("caminhao_2");
		double tempoB = Config.dist_carregador.getVal();
		Evento eB = new Carregamento(tempoA, a, carregador2);
		//adicionar na lista ordenada de eventos
		for(int i = 2;i<Config.nroEntidades;i++){
			String nome = "caminhao_"+i;
			fila_carregamento.add(new Entidade(nome));
		}
	}

}
