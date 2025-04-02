package UTIL_07_Collections;

import util.Linhas;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Simples {
	public static void Simples_Run() {

		Linhas linhas = new Linhas();
		String collec = "\n\t Collections: Métodos utilitários para trabalhar com coleções (ex.: ordenação, busca, sincronização). \n";
		System.err.println(collec);

		linhas.run_Caracteres();
		Import_1S.run_Import_1S();

		linhas.run_Caracteres();
		Import_2S.run_Import_2S();

		linhas.run_Caracteres();
		Import_3S.run_Import_3S();

	}
}

//Corpo da Classe _______________________________
//Enum para categorias de livros
enum Categoria3 {
	FICCAO, NAO_FICCAO, INFANTIL, TECNOLOGIA;
}

//Enum para status do livro
enum Status3 {
	DISPONIVEL, EMPRESTADO, RESERVADO;
}

//Enum para as ações possíveis no CRUD
enum Acao3 {
	CRIAR, LER, ATUALIZAR, DELETAR;
}

//Classe abstrata para definir o comportamento comum dos livros
abstract class Livro {
	private String nome;
	private String autor;
	private Categoria3 categoria;
	private Status3 status;

	public Livro(String nome, String autor, Categoria3 categoria, Status3 status) {
		this.nome = nome;
		this.autor = autor;
		this.categoria = categoria;
		this.status = status;
	}

	public String getnome() {
		return nome;
	}

	public String getAutor() {
		return autor;
	}

	public Categoria3 getCategoria() {
		return categoria;
	}

	public Status3 getStatus() {
		return status;
	}

	public void setStatus(Status3 status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Livro{" + "nome='" + nome + '\'' + ", autor='" + autor + '\'' + ", categoria=" + categoria
				+ ", status=" + status + '}';
	}
}

//Classe concreta para representar um livro específico
class LivroConcreto extends Livro {
	public LivroConcreto(String nome, String autor, Categoria3 categoria, Status3 status) {
		super(nome, autor, categoria, status);
	}
}

//Classe que gerencia a lista de livros (CRUD)
class Biblioteca {
	private List<Livro> livros;
	private Lock lock;

	public Biblioteca() {
		this.livros = new ArrayList<>();
		this.lock = new ReentrantLock();
	}

	// Criar
	public void criarLivro(Livro livro) {
		lock.lock();
		try {
			livros.add(livro);
			System.out.println("Livro criado: " + livro);
		} finally {
			lock.unlock();
		}
	}

	// Ler
	public Livro lerLivro(String nome) {
		lock.lock();
		try {
			for (Livro livro : livros) {
				if (livro.getnome().equalsIgnoreCase(nome)) {
					System.out.println("Livro encontrado: " + livro);
					return livro;
				}
			}
			System.out.println("Livro não encontrado.");
			return null;
		} finally {
			lock.unlock();
		}
	}

	// Atualizar
	public void atualizarStatus(String nome, Status3 novoStatus) {
		lock.lock();
		try {
			Livro livro = lerLivro(nome);
			if (livro != null) {
				livro.setStatus(novoStatus);
				System.out.println("Status atualizado para: " + livro);
			}
		} finally {
			lock.unlock();
		}
	}

	// Deletar
	public void deletarLivro(String nome) {
		lock.lock();
		try {
			Livro livro = lerLivro(nome);
			if (livro != null) {
				livros.remove(livro);
				System.out.println("Livro deletado: " + livro);
			}
		} finally {
			lock.unlock();
		}
	}

	// Ordenação por título
	public void ordenarLivrosPornome() {
		lock.lock();
		try {
			livros.sort(Comparator.comparing(Livro::getnome));
			System.out.println("Livros ordenados por título:");
			livros.forEach(System.out::println);
		} finally {
			lock.unlock();
		}
	}

	// Busca por categoria
	public void buscarPorCategoria(Categoria3 categoria) {
		lock.lock();
		try {
			System.out.println("Livros encontrados na categoria " + categoria + ":");
			livros.stream().filter(livro -> livro.getCategoria() == categoria).forEach(System.out::println);
		} finally {
			lock.unlock();
		}
	}

	// Sincronização de operações
	public void sincronizarOperacoes() {
		lock.lock();
		try {
			System.out.println("Operações sincronizadas com sucesso.");
		} finally {
			lock.unlock();
		}
	}
}

class Import_1S {

	public static void run_Import_1S() {
		System.err.println("\t\t1. \n");

		Biblioteca biblioteca = new Biblioteca();

		// Criando livros
		Livro livro1 = new LivroConcreto("Java para Iniciantes", "Autor A", Categoria3.TECNOLOGIA, Status3.DISPONIVEL);
		Livro livro2 = new LivroConcreto("O Senhor dos Anéis", "J.R.R. Tolkien", Categoria3.FICCAO, Status3.DISPONIVEL);
		Livro livro3 = new LivroConcreto("Harry Potter e a Pedra Filosofal", "J.K. Rowling", Categoria3.INFANTIL,
				Status3.DISPONIVEL);

		// Realizando operações CRUD
		biblioteca.criarLivro(livro1);
		biblioteca.criarLivro(livro2);
		biblioteca.criarLivro(livro3);

		// Ordenação por título
		biblioteca.ordenarLivrosPornome();

		// Buscar por categoria
		biblioteca.buscarPorCategoria(Categoria3.FICCAO);

		// Atualizar status de um livro
		biblioteca.atualizarStatus("Java para Iniciantes", Status3.EMPRESTADO);

		// Deletar livro
		biblioteca.deletarLivro("Harry Potter e a Pedra Filosofal");

		// Sincronizar operações
		biblioteca.sincronizarOperacoes();

	}
}

//Corpo da Classe _______________________________

//Enum para Estado do Vídeo
enum EstadoVideo {
	REPRODUZINDO, PAUSADO, PARADO;
}

//Enum para Tipo de Vídeo
enum TipoVideo {
	TUTORIAL, ENTRETENIMENTO, EDUCATIVO;
}

//Enum para Prioridade de Tempo
enum PrioridadeTempo {
	ALTA, MEDIA, BAIXA;
}

//Classe Abstrata para Video
abstract class Video {
	protected String nome;
	protected EstadoVideo estado;
	protected TipoVideo tipo;
	protected PrioridadeTempo prioridade;

	public Video(String nome, EstadoVideo estado, TipoVideo tipo, PrioridadeTempo prioridade) {
		this.nome = nome;
		this.estado = estado;
		this.tipo = tipo;
		this.prioridade = prioridade;
	}

	public abstract void exibirInfo();

	// Getters e Setters
	public String getnome() {
		return nome;
	}

	public EstadoVideo getEstado() {
		return estado;
	}

	public TipoVideo getTipo() {
		return tipo;
	}

	public PrioridadeTempo getPrioridade() {
		return prioridade;
	}

	public void setEstado(EstadoVideo estado) {
		this.estado = estado;
	}
}

//Classe VideoTemporizado que estende a classe Video
class VideoTemporizado extends Video {
	private int duracao;

	public VideoTemporizado(String nome, EstadoVideo estado, TipoVideo tipo, PrioridadeTempo prioridade,
			int duracao) {
		super(nome, estado, tipo, prioridade);
		this.duracao = duracao;
	}

	@Override
	public void exibirInfo() {
		System.out.println("Título: " + nome);
		System.out.println("Estado: " + estado);
		System.out.println("Tipo: " + tipo);
		System.out.println("Prioridade: " + prioridade);
		System.out.println("Duração: " + duracao + " minutos");
		System.err.println("______________________________________");
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}
}

//Classe que realiza o CRUD
class CRUDVideos {
	private List<VideoTemporizado> videos;
	private final ReentrantLock lock = new ReentrantLock();

	public CRUDVideos() {
		videos = new ArrayList<>();
	}

	// Método para adicionar vídeo
	public synchronized void adicionar(VideoTemporizado video) {
		lock.lock();
		try {
			videos.add(video);
		} finally {
			lock.unlock();
		}
	}

	// Método para listar todos os vídeos
	public synchronized void listar() {
		lock.lock();
		try {
			for (VideoTemporizado video : videos) {
				video.exibirInfo();
				System.out.println("---------------------------");
			}
		} finally {
			lock.unlock();
		}
	}

	// Método para atualizar vídeo
	public synchronized void atualizar(int index, VideoTemporizado video) {
		lock.lock();
		try {
			if (index >= 0 && index < videos.size()) {
				videos.set(index, video);
			}
		} finally {
			lock.unlock();
		}
	}

	// Método para remover vídeo
	public synchronized void remover(int index) {
		lock.lock();
		try {
			if (index >= 0 && index < videos.size()) {
				videos.remove(index);
			}
		} finally {
			lock.unlock();
		}
	}

	// Método para buscar vídeo por título
	public synchronized VideoTemporizado buscarPornome(String nome) {
		lock.lock();
		try {
			for (VideoTemporizado video : videos) {
				if (video.getnome().equalsIgnoreCase(nome)) {
					return video;
				}
			}
		} finally {
			lock.unlock();
		}
		return null;
	}

	// Método para ordenar vídeos por duração
	public synchronized void ordenarPorDuracao() {
		lock.lock();
		try {
			Collections.sort(videos, Comparator.comparingInt(VideoTemporizado::getDuracao));
		} finally {
			lock.unlock();
		}
	}

	// Método para ordenar vídeos por prioridade
	public synchronized void ordenarPorPrioridade() {
		lock.lock();
		try {
			Collections.sort(videos, Comparator.comparing(VideoTemporizado::getPrioridade));
		} finally {
			lock.unlock();
		}
	}
}

class Import_2S {
	public static void run_Import_2S() {
		System.err.println("\t\t2. Videos\n");

		CRUDVideos crudVideos = new CRUDVideos();

		// Adicionando vídeos
		crudVideos.adicionar(new VideoTemporizado("Tutorial de Java", EstadoVideo.REPRODUZINDO, TipoVideo.TUTORIAL,
				PrioridadeTempo.ALTA, 30));
		crudVideos.adicionar(new VideoTemporizado("Filme Educativo", EstadoVideo.PAUSADO, TipoVideo.EDUCATIVO,
				PrioridadeTempo.BAIXA, 120));
		crudVideos.adicionar(new VideoTemporizado("Comédia no Youtube", EstadoVideo.PARADO, TipoVideo.ENTRETENIMENTO,
				PrioridadeTempo.MEDIA, 45));

		// Listando vídeos
		System.out.println("Lista de vídeos:");
		crudVideos.listar();

		// Ordenando por duração
		System.out.println("Vídeos ordenados por duração:");
		crudVideos.ordenarPorDuracao();
		crudVideos.listar();

		// Buscando por título
		System.out.println("Buscando por título 'Filme Educativo':");
		VideoTemporizado video = crudVideos.buscarPornome("Filme Educativo");
		if (video != null) {
			video.exibirInfo();
		} else {
			System.out.println("Vídeo não encontrado.");
		}

		// Atualizando um vídeo
		System.out.println("Atualizando o vídeo 'Comédia no Youtube':");
		VideoTemporizado videoAtualizado = new VideoTemporizado("Comédia no Youtube", EstadoVideo.REPRODUZINDO,
				TipoVideo.ENTRETENIMENTO, PrioridadeTempo.MEDIA, 50);
		crudVideos.atualizar(2, videoAtualizado);
		crudVideos.listar();

		// Removendo um vídeo
		System.out.println("Removendo o vídeo 'Tutorial de Java':");
		crudVideos.remover(0);
		crudVideos.listar();

	}
}

//Corpo da Classe _______________________________

//Enum para os tipos de tempo
enum TipoDeTempo {
	SOL, CHUVA, NUVEM, FOG, NEVE
}

//Classe abstrata para CRUD
abstract class CRUD<T> {
	protected List<T> lista;
	protected Lock lock = new ReentrantLock(); // Sincronização para operações seguras

	public CRUD() {
		lista = new ArrayList<>();
	}

	public abstract void add(T item);

	public abstract void remove(T item);

	public abstract void update(int index, T item);

	public abstract T get(int index);

	public void ordenar() {
		lock.lock();
		try {
			Collections.sort(lista, null);
		} finally {
			lock.unlock();
		}
	}

	public int buscar(T item) {
		lock.lock();
		try {
			return lista.indexOf(item);
		} finally {
			lock.unlock();
		}
	}

	public void listar() {
		lock.lock();
		try {
			lista.forEach(System.out::println);
		} finally {
			lock.unlock();
		}
	}
}

//Classe Tempo
class Tempo8 implements Comparable<Tempo8> {
	private TipoDeTempo tipo;
	private int hora;
	private int minuto;

	public Tempo8(TipoDeTempo tipo, int hora, int minuto) {
		this.tipo = tipo;
		this.hora = hora;
		this.minuto = minuto;
	}

	public TipoDeTempo getTipo() {
		return tipo;
	}

	public int getHora() {
		return hora;
	}

	public int getMinuto() {
		return minuto;
	}

	@Override
	public int compareTo(Tempo8 other) {
		return Integer.compare(this.hora, other.hora); // Ordenação por hora
	}

	@Override
	public String toString() {
		return "Tempo [Tipo: " + tipo + ", Hora: " + hora + ", Minuto: " + minuto + "]";
	}
}

//Classe EstacaoRadio (cada estação pode ter um horário de transmissão)
class EstacaoRadio extends CRUD<Tempo8> {
	private String nomeEstacao;

	public EstacaoRadio(String nomeEstacao) {
		super();
		this.nomeEstacao = nomeEstacao;
	}

	@Override
	public void add(Tempo8 item) {
		lock.lock();
		try {
			lista.add(item);
			System.out.println("Tempo adicionado: " + item);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public void remove(Tempo8 item) {
		lock.lock();
		try {
			if (lista.remove(item)) {
				System.out.println("Tempo removido: " + item);
			} else {
				System.out.println("Tempo não encontrado: " + item);
			}
		} finally {
			lock.unlock();
		}
	}

	@Override
	public void update(int index, Tempo8 item) {
		lock.lock();
		try {
			if (index >= 0 && index < lista.size()) {
				lista.set(index, item);
				System.out.println("Tempo atualizado: " + item);
			} else {
				System.out.println("Índice inválido.");
			}
		} finally {
			lock.unlock();
		}
	}

	@Override
	public Tempo8 get(int index) {
		lock.lock();
		try {
			if (index >= 0 && index < lista.size()) {
				return lista.get(index);
			} else {
				return null;
			}
		} finally {
			lock.unlock();
		}
	}

	public String getNomeEstacao() {
		return nomeEstacao;
	}
}

class Import_3S {
	public static void run_Import_3S() {
		System.err.println("\t\t3. \n");
		
		 EstacaoRadio estacao1 = new EstacaoRadio("Estacao X");

	        // Adicionando tempos
	        estacao1.add(new Tempo8(TipoDeTempo.SOL, 14, 30));
	        estacao1.add(new Tempo8(TipoDeTempo.CHUVA, 10, 15));
	        estacao1.add(new Tempo8(TipoDeTempo.FOG, 7, 45));

	        // Listando os tempos
	        System.out.println("Tempos na " + estacao1.getNomeEstacao() + ":");
	        estacao1.listar();

	        // Ordenando os tempos
	        System.out.println("\nOrdenando os tempos...");
	        estacao1.ordenar();
	        estacao1.listar();

	        // Buscando um tempo
	        Tempo8 tempoBuscado = new Tempo8(TipoDeTempo.CHUVA, 10, 15);
	        int indice = estacao1.buscar(tempoBuscado);
	        System.out.println("\nÍndice do tempo " + tempoBuscado + ": " + indice);

	        // Atualizando um tempo
	        estacao1.update(indice, new Tempo8(TipoDeTempo.NEVE, 8, 00));
	        estacao1.listar();

	        // Removendo um tempo
	        estacao1.remove(new Tempo8(TipoDeTempo.SOL, 14, 30));
	        estacao1.listar();

	}
}
