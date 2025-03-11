package UTIL_08_Arrays;

import util.Linhas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;

public class For_Matrix {
	public static void For_Run() {

		Linhas linhas = new Linhas();
		String collec = "\n\t Arrays: Métodos utilitários para trabalhar com arrays (ex.: ordenação, busca \n";
		System.err.println(collec);

		linhas.run_Caracteres();
		Import_1F.run_Import_1F();

		linhas.run_Caracteres();
		Import_2F.run_Import_2F();

		linhas.run_Caracteres();
		Import_3F.run_Import_3F();

		linhas.run_Caracteres();
		Import_4F.run_Import_4F();

	}
}

//Corpo da Classe _______________________________
//Enum para Status do Host
enum Status2 {
	ATIVO, INATIVO, MANUTENCAO, DESATIVADO, PENDENTE;
}

//Enum para Tipo de Host
enum TipoHost {
	SERVIDOR, ROUTER, SWITCH, FIREWALL, COMPUTADOR;
}

//Classe Abstrata Host
abstract class Host2 {
	protected int id;
	protected String nome;
	protected Status2 status;
	protected TipoHost tipo;

	public Host2(int id, String nome, Status2 status, TipoHost tipo) {
		this.id = id;
		this.nome = nome;
		this.status = status;
		this.tipo = tipo;
	}

	public abstract void exibirDetalhes();
}

//Classe concreta
class HostImpl extends Host2 {
	public HostImpl(int id, String nome, Status2 status, TipoHost tipo) {
		super(id, nome, status, tipo);
	}

	@Override
	public void exibirDetalhes() {
		System.out.println("ID: " + id + ", Nome: " + nome + ", Status: " + status + ", Tipo: " + tipo);
	}
}

//CRUD de Hosts
class HostManager2 {
	private Host2[] hosts = new Host2[0];
	private final ReentrantLock lock = new ReentrantLock();

	public synchronized void adicionarHost(Host2 host) {
		hosts = Arrays.copyOf(hosts, hosts.length + 1);
		hosts[hosts.length - 1] = host;
	}

	public synchronized void removerHost(int id) {
		var novoArray = Arrays.stream(hosts).filter(h -> h.id != id).toArray(Host2[]::new);
		hosts = novoArray;
	}

	public void listarHosts() {
		for (var host : hosts) {
			host.exibirDetalhes();
		}
	}

	public void ordenarHosts() {
		Arrays.sort(hosts, Comparator.comparing(h -> h.nome));
	}

	public void buscarHost(int id) {
		ordenarHosts();
		int index = Arrays.binarySearch(hosts, new HostImpl(id, "", Status2.ATIVO, TipoHost.COMPUTADOR),
				Comparator.comparingInt(h -> h.id));

		if (index >= 0) {
			System.out.println("Host encontrado: ");
			hosts[index].exibirDetalhes();
		} else {
			System.out.println("Host não encontrado.");
		}
	}
}

//Teste do CRUD
class Import_1F {
	public static void run_Import_1F() {
		System.err.println("\t\t1. HostName \n");
		HostManager2 manager2 = new HostManager2();

		manager2.adicionarHost(new HostImpl(1, "Servidor Alpha", Status2.ATIVO, TipoHost.SERVIDOR));
		manager2.adicionarHost(new HostImpl(2, "Roteador X", Status2.MANUTENCAO, TipoHost.ROUTER));
		manager2.adicionarHost(new HostImpl(3, "Firewall Y", Status2.INATIVO, TipoHost.FIREWALL));

		System.out.println("--- Lista de Hosts ---");
		manager2.listarHosts();

		System.out.println("\n--- Ordenando Hosts ---");
		manager2.ordenarHosts();
		manager2.listarHosts();

		System.out.println("\n--- Buscando Host com ID 2 ---");
		manager2.buscarHost(2);

		System.out.println("\n--- Removendo Host com ID 2 ---");
		manager2.removerHost(2);
		manager2.listarHosts();
	}
}

//Corpo da Classe _______________________________
//Enum para definir os tipos de ordenação
enum OrderType {
	ASCENDING, DESCENDING, ALPHABETICAL, DATE, CUSTOM
}

//Classe abstrata base
abstract class AbstractPagination<T> {
	protected List<T> items;
	protected int pageSize;
	protected final ReentrantLock lock = new ReentrantLock();

	public AbstractPagination(List<T> items, int pageSize) {
		this.items = items;
		this.pageSize = pageSize;
	}

	public abstract void sort(OrderType orderType);

	public abstract List<T> search(String query);
}

//Classe concreta que implementa a paginação
class PaginatedCrud extends AbstractPagination<String> {
	public PaginatedCrud(List<String> items, int pageSize) {
		super(items, pageSize);
	}

	public void addItem(String item) {
		lock.lock();
		try {
			items.add(item);
		} finally {
			lock.unlock();
		}
	}

	public void removeItem(String item) {
		lock.lock();
		try {
			items.remove(item);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public void sort(OrderType orderType) {
		lock.lock();
		try {
			switch (orderType) {
			case ASCENDING:
				items.sort(Comparator.naturalOrder());
				break;
			case DESCENDING:
				items.sort(Comparator.reverseOrder());
				break;
			case ALPHABETICAL:
				items.sort(String::compareToIgnoreCase);
				break;
			case DATE:
				// Exemplo de ordenação por data se houvesse datas
				break;
			case CUSTOM:
				Collections.shuffle(items);
				break;
			default:
				break;
			}
		} finally {
			lock.unlock();
		}
	}

	@Override
	public List<String> search(String query) {
		List<String> results = new ArrayList<>();
		for (String item : items) {
			if (item.toLowerCase().contains(query.toLowerCase())) {
				results.add(item);
			}
		}
		return results;
	}

	public List<String> getPage(int page) {
		int start = page * pageSize;
		int end = Math.min(start + pageSize, items.size());
		return items.subList(start, end);
	}
}

//Classe principal para testar

class Import_2F {
	public static void run_Import_2F() {
		System.err.println("\t\t2. Paginação \n");

		List<String> data = new ArrayList<>(Arrays.asList("Apple", "Banana", "Cherry", "Date", "Elderberry", "1Apple",
				"1Banana", "1Cherry", "1Date", "1Elderberry", "2Apple", "2Banana", "2Cherry", "2Date", "2Elderberry"));
		PaginatedCrud crud = new PaginatedCrud(data, 3);

		crud.addItem("Fig");
		crud.sort(OrderType.ALPHABETICAL);
		System.out.println("Ordenado: " + crud.items);

		List<String> results = crud.search("a");
		System.out.println("Busca por 'a': " + results);

		System.out.println("\nPágina 1: " + crud.getPage(0));
		System.out.println("Página 2: " + crud.getPage(1));
		System.out.println("Página 3: " + crud.getPage(2));
		System.out.println("Página 4: " + crud.getPage(3));
		System.out.println("Página 5: " + crud.getPage(4));
		System.out.println("Página 6: " + crud.getPage(5));
		System.out.println("\n");

	}
}

//Corpo da Classe _______________________________
//Definição de Enums
enum Status5 {
	ATIVO, INATIVO, PENDENTE, EXCLUIDO, ARQUIVADO
}

enum Prioridade {
	BAIXA, MEDIA, ALTA, URGENTE, CRITICA
}

enum Tipo {
	ADMIN, USUARIO, GUEST, MODERADOR, DESENVOLVEDOR
}

enum Categoria {
	TECNOLOGIA, SAUDE, EDUCACAO, FINANCAS, ENTRETENIMENTO
}

enum Acao {
	INSERIR, ATUALIZAR, DELETAR, LISTAR, BUSCAR
}

//Classe abstrata
abstract class Registro {
	int id;
	String nome;
	Status5 status;
	Prioridade prioridade;
	Tipo tipo;

	public Registro(int id, String nome, Status5 status, Prioridade prioridade, Tipo tipo) {
		this.id = id;
		this.nome = nome;
		this.status = status;
		this.prioridade = prioridade;
		this.tipo = tipo;
	}

	abstract void exibirInformacoes();
}

//Classe concreta
class Tabela extends Registro {
	static ReentrantLock lock = new ReentrantLock();

	public Tabela(int id, String nome, Status5 status, Prioridade prioridade, Tipo tipo) {
		super(id, nome, status, prioridade, tipo);
	}

	@Override
	void exibirInformacoes() {
		System.out.println("ID: " + id + ", Nome: " + nome + ", Status: " + status + ", Prioridade: " + prioridade
				+ ", Tipo: " + tipo);
	}
}

//Classe para gerenciar o CRUD
class GerenciadorTabela {
	private Tabela[] registros = new Tabela[0];

	public synchronized void adicionarRegistro(Tabela registro) {
		registros = Arrays.copyOf(registros, registros.length + 1);
		registros[registros.length - 1] = registro;
	}

	public synchronized void removerRegistro(int id) {
		registros = Arrays.stream(registros).filter(r -> r.id != id).toArray(Tabela[]::new);
	}

	public synchronized Tabela buscarRegistro(int id) {
		for (var registro : registros) {
			if (registro.id == id) {
				return registro;
			}
		}
		return null;
	}

	public synchronized void listarRegistros() {
		for (var registro : registros) {
			registro.exibirInformacoes();
		}
	}

	public synchronized void ordenarPorNome() {
		Arrays.sort(registros, Comparator.comparing(r -> r.nome));
	}
}

//Classe principal
class Import_3F {
	public static void run_Import_3F() {
		System.err.println("\t\t3. Tabelas \n");
		GerenciadorTabela gerenciador = new GerenciadorTabela();

		// Criando registros
		Tabela reg1 = new Tabela(1, "Carlos", Status5.ATIVO, Prioridade.ALTA, Tipo.ADMIN);
		Tabela reg2 = new Tabela(2, "Ana", Status5.INATIVO, Prioridade.MEDIA, Tipo.USUARIO);
		Tabela reg3 = new Tabela(3, "Bruno", Status5.PENDENTE, Prioridade.URGENTE, Tipo.MODERADOR);

		// Adicionando registros
		gerenciador.adicionarRegistro(reg1);
		gerenciador.adicionarRegistro(reg2);
		gerenciador.adicionarRegistro(reg3);

		// Listando registros
		System.out.println("Registros antes da ordenação:");
		gerenciador.listarRegistros();

		// Ordenando e listando novamente
		gerenciador.ordenarPorNome();
		System.out.println("\nRegistros após ordenação por nome:");
		gerenciador.listarRegistros();

		// Buscando um registro
		int buscaId = 2;
		Tabela encontrado = gerenciador.buscarRegistro(buscaId);
		if (encontrado != null) {
			System.out.println("\nRegistro encontrado:");
			encontrado.exibirInformacoes();
		} else {
			System.out.println("\nRegistro com ID " + buscaId + " não encontrado.");
		}

		// Removendo um registro
		gerenciador.removerRegistro(1);
		System.out.println("\nRegistros após remoção do ID 1:");
		gerenciador.listarRegistros();
	}
}

// Classe principal

//Definição de enums para colunas da tabela
enum Coluna {
	ID, NOME, IDADE, CIDADE, SALARIO;
}

enum Operacao {
	INSERIR, ATUALIZAR, DELETAR, BUSCAR, LISTAR;
}

enum Ordenacao {
	ASC, DESC;
}

enum TipoBusca {
	EXATA, PARCIAL;
}

enum EstadoRegistro {
	ATIVO, INATIVO;
}

//Classe abstrata para o CRUD
abstract class CrudTabela {
	abstract void inserir(String[] dados);

	abstract void atualizar(int id, String[] novosDados);

	abstract void deletar(int id);

	abstract String[] buscar(int id);

	abstract void listar();

	abstract void ordenar(Coluna coluna, Ordenacao ordem);
}

//Implementação concreta da tabela
class Tabela2 extends CrudTabela {
	private String[][] dados;
	private int count;
	private final Lock lock = new ReentrantLock();

	public Tabela2(int tamanho) {
		this.dados = new String[tamanho][Coluna.values().length];
		this.count = 0;
	}

	@Override
	public void inserir(String[] dadosEntrada) {
		lock.lock();
		try {
			if (count >= dados.length) {
				System.out.println("Tabela cheia!");
				return;
			}
			dados[count++] = dadosEntrada;
			System.out.println("Registro inserido com sucesso!");
		} finally {
			lock.unlock();
		}
	}

	@Override
	public void atualizar(int id, String[] novosDados) {
		lock.lock();
		try {
			for (int i = 0; i < count; i++) {
				if (dados[i][0].equals(String.valueOf(id))) {
					dados[i] = novosDados;
					System.out.println("Registro atualizado!");
					return;
				}
			}
			System.out.println("ID não encontrado!");
		} finally {
			lock.unlock();
		}
	}

	@Override
	public void deletar(int id) {
		lock.lock();
		try {
			for (int i = 0; i < count; i++) {
				if (dados[i][0].equals(String.valueOf(id))) {
					for (int j = i; j < count - 1; j++) {
						dados[j] = dados[j + 1];
					}
					count--;
					System.out.println("Registro deletado!");
					return;
				}
			}
			System.out.println("ID não encontrado!");
		} finally {
			lock.unlock();
		}
	}

	@Override
	public String[] buscar(int id) {
		for (int i = 0; i < count; i++) {
			if (dados[i][0].equals(String.valueOf(id))) {
				return dados[i];
			}
		}
		return null;
	}

	@Override
	public void listar() {
		for (int i = 0; i < count; i++) {
			System.out.println(Arrays.toString(dados[i]));
		}
	}

	@Override
	public void ordenar(Coluna coluna, Ordenacao ordem) {
		int index = coluna.ordinal();

		System.out.println("Tabela ordenada por " + coluna);
	}
}

//Teste da Tabela
class Import_4F {
	public static void run_Import_4F() {

		Tabela2 tabela = new Tabela2(10);

		tabela.inserir(new String[] { "1", "Carlos", "30", "São Paulo", "5000" });
		tabela.inserir(new String[] { "2", "Ana", "25", "Rio de Janeiro", "4000" });
		tabela.inserir(new String[] { "3", "Marcos", "35", "Belo Horizonte", "6000" });

		tabela.listar();

		System.out.println("\nOrdenando por nome ASC:");
		tabela.ordenar(Coluna.NOME, Ordenacao.ASC);
		tabela.listar();

		System.out.println("\nBuscando ID 2:");
		System.out.println(Arrays.toString(tabela.buscar(2)));

		System.out.println("\nAtualizando ID 3:");
		tabela.atualizar(3, new String[] { "3", "Marcos Silva", "36", "Curitiba", "6500" });
		tabela.listar();

		System.out.println("\nDeletando ID 1:");
		tabela.deletar(1);
		tabela.listar();
	}
}
