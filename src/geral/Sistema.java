package geral;
import java.util.LinkedList;
import java.util.Queue;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Vector;

import entidades.Entidade;
import entidades.Recurso;
import evento.Carregamento;
import evento.Evento;


public class Sistema {
	//CLASSES DA PUTARIA: tudo public static, pensar melhor como tratar os eventos.
	public static Queue<Entidade> fila_carregamento;
	public static Queue<Entidade> fila_balanca;
	public static Vector<Entidade> entidades;
	public static SortedSet<Evento> lista_eventos;
	
	public static double tempo_atual;
	
	public static Recurso carregador1;
	public static Recurso carregador2;
	public static Recurso balanca;
	
	public void initialize(){
		//inicializacao estruturas
		fila_carregamento = new LinkedList<Entidade>();
		fila_balanca = new LinkedList<Entidade>();
		entidades = new Vector<Entidade>();
		lista_eventos = new TreeSet<Evento>();
		
		tempo_atual = 0;
		
		carregador1 = new Recurso();
		carregador2 = new Recurso();
		balanca = new Recurso();
		
		//inicializacao entidades, disparo de eventos iniciais;
		
		Entidade caminhao1 = new Entidade("caminhao_1");
		entidades.add(caminhao1);
		double tempo_Caminhao1 = Config.dist_carregador.getVal();
		Evento eA = new Carregamento(tempo_Caminhao1, tempo_atual, caminhao1, carregador1);
		lista_eventos.add(eA);
		
		Entidade caminhao2 = new Entidade("caminhao_2");
		entidades.add(caminhao2);
		double tempo_Caminhao2 = Config.dist_carregador.getVal();
		Evento eB = new Carregamento(tempo_Caminhao2, tempo_atual, caminhao2, carregador2);
		lista_eventos.add(eB);
		
		
		for(int i = 3;i<Config.nroEntidades;i++){
			String nome = "caminhao_"+i;
			entidades.add(new Entidade(nome));
			fila_carregamento.add(entidades.get(i-1)); //ESPERO QUE NAO DE MERDA
		}
	}

	
	public void avancaTempo(){
		Evento e = lista_eventos.first();
		tempo_atual= e.getTempo();
		e.tratamento();
	}
	
	public String getReport(){
		return "";
	}

}
