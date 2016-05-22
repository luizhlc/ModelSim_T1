package geral;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Vector;

import Estruturas.ListaRecurso;
import entidades.Balanca;
import entidades.Carregador;
import entidades.Entidade;
import entidades.Recurso;
import evento.Carregamento;
import evento.Evento;


public class Sistema {
	//CLASSES DA PUTARIA: tudo public static, pensar melhor como tratar os eventos.
	public static Queue<Entidade> fila_carregamento;
	public static Queue<Entidade> fila_balanca;
	public static List<Entidade> entidades;
	public static List<Entidade> viajando;
	public static List<Evento> lista_eventos;
	
	public static double tempo_atual;
	
	public static ListaRecurso carregadores;
	public static ListaRecurso balancas;
	
	public Sistema(){
	}
	
	
	public void initialize(){
		//inicializacao estruturas
		carregadores = new ListaRecurso();
		balancas = new ListaRecurso();
		fila_carregamento = new LinkedList<Entidade>();
		fila_balanca = new LinkedList<Entidade>();
		entidades = new ArrayList<>();
		viajando = new ArrayList<>();
		
		lista_eventos = new ArrayList<Evento>();
		tempo_atual = 0;
		
		carregadores.adicionaRecurso(new Carregador("Carregador_1", fila_carregamento, fila_balanca));
		carregadores.adicionaRecurso(new Carregador("Carregador_2", fila_carregamento, fila_balanca));
		balancas.adicionaRecurso(new Balanca("Balanca", fila_balanca, viajando));
		
		//inicializacao entidades, disparo de eventos iniciais;
		for(int i = 0;i<Config.nroEntidades;i++){
			String nome = "caminhao_"+(i+1);
			entidades.add(new Entidade(nome));
			
			fila_carregamento.add(entidades.get(i));
		}
		
		for(int i=0;carregadores.pegaLivre()!=null;i++){
			Recurso r = carregadores.pegaLivre();
			r.ocupa();
			double tempo = Config.dist_carregador.getVal();
			lista_eventos.add(new Carregamento(tempo, tempo_atual, entidades.get(i), r));
		}
		Collections.sort(lista_eventos);
	}

	
	public void avancaTempo(){
		Evento e = lista_eventos.remove(0);
		tempo_atual= e.getTempo();
		update();
		e.tratamento();
		Collections.sort(lista_eventos);
		
	}
	
	private void update(){
		Relatorio relatorio = Relatorio.get_relatorio();
		relatorio.update_tamanho_filaC(fila_carregamento.size());
		relatorio.update_tamanho_filaB(fila_balanca.size());
		relatorio.set_timestamp();
	}
	
	//PARA IMPRIMIR E COLORIR
	
	
	public List<String>getFilaCarregador_toPrint(){
		List<String> fila = new ArrayList<String>();
		int size = fila_carregamento.size();
		for(int i=0; i<size; i++){
			Entidade r = fila_carregamento.remove();
			fila.add(i,r.getNome());
			fila_carregamento.add(r);
		}
		return fila;
	}
	
	public List<String>getFilaBalanca_toPrint(){
		List<String> fila = new ArrayList<String>();
		int size = fila_balanca.size();
		for(int i=0; i<size; i++){
			Entidade r = fila_balanca.remove();
			fila.add(i,r.getNome());
			fila_balanca.add(r);
		}
		return fila;
	}
	
	public String getEntidadeNaBalanca(){
		Balanca b = (Balanca)balancas.getRecurso(0);
		if(b.estaLivre())
			return "";
		return b.get_cliente().getNome();
	}
	
	public String getEntidadeNoCarregador(int i){
		Carregador b = (Carregador)carregadores.getRecurso(i);
		if(b.estaLivre()==true)
			return "";
		return b.get_cliente().getNome();
	}
	public Vector<String> getEntidadesViajando(){
		Vector<String> fila = new Vector<String>();
		for(int i=0;i<viajando.size();i++){
			fila.add(viajando.get(i).getNome());
		}
		return fila;
	}
	
	public Vector<String> getEventos(){
		Vector<String> fila = new Vector<String>();
		Collections.sort(lista_eventos);
		for(int i =0; i<lista_eventos.size();i++){
			fila.add(i, lista_eventos.get(i).toPrint());
		}
		return fila;
	}

}
