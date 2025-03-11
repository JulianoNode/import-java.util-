package UTIL_08_Arrays;

import util.Linhas;
import util.VoutarRun;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class While_Matrix {
	public static void While_Run() {
		Linhas linhas = new Linhas();
		String collec = "\n\t Arrays: Métodos utilitários para trabalhar com arrays (ex.: ordenação, busca \n";
		System.err.println(collec);

		linhas.run_Caracteres();
		Import_1WM.run_Import_1WM();

		linhas.run_Caracteres();
		Import_2WM.run_Import_2WM();

		linhas.run_Caracteres();
		Import_3WM.run_Import_3WM();

	}
}

//Corpo da Classe _______________________________
//Enum representando diferentes tipos de operações SQL
enum SQLOperation {
	INSERT, SELECT, UPDATE, DELETE, DROP;
}

//Enum para tipos de usuários
enum UserType {
	ADMIN, EDITOR, VIEWER, GUEST, SUPER_ADMIN;
}

//Enum para status de transação
enum TransactionStatus {
	PENDING, SUCCESS, FAILED, ROLLBACK, CANCELLED;
}

//Enum para tipo de ordenação
enum SortType {
	ASC, DESC, NONE, CUSTOM, RANDOM;
}

//Enum para estratégias de busca
enum SearchStrategy {
	BINARY_SEARCH, LINEAR_SEARCH, HASH_SEARCH, TREE_SEARCH, SQL_INDEX;
}

//Classe abstrata para definição do CRUD abstrato
abstract class AbstractDatabase {
	abstract void insert(String query);

	abstract void update(String query);

	abstract void delete(String query);

	abstract List<String> selectAll();
}

//Implementação concreta de um Banco de Dados Simples
class SQLDatabase extends AbstractDatabase {
	private final List<String> data = new ArrayList<>();
	private final ReentrantLock lock = new ReentrantLock();

	@Override
	void insert(String query) {
		data.add(query);
		System.out.println("Inserido: " + query);
	}

	@Override
	void update(String query) {
		if (!data.isEmpty()) {
			data.set(0, query);
			System.out.println("Atualizado: " + query);
		}
	}

	@Override
	void delete(String query) {
		data.remove(query);
		System.out.println("Deletado: " + query);
	}

	@Override
	List<String> selectAll() {
		return data;
	}

	// Método para ordenar os dados
	void sortData(SortType sortType) {
		switch (sortType) {
		case ASC -> Collections.sort(data);
		case DESC -> data.sort(Collections.reverseOrder());
		case RANDOM -> Collections.shuffle(data);
		default -> System.out.println("Nenhuma ordenação aplicada");
		}
	}

	// Método de busca com diferentes estratégias
	boolean search(String item, SearchStrategy strategy) {
		boolean found = false;
		switch (strategy) {
		case LINEAR_SEARCH -> {
			for (var entry : data) {
				if (entry.equals(item)) {
					found = true;
					break;
				}
			}
		}
		case BINARY_SEARCH -> {
			List<String> sorted = new ArrayList<>(data);
			Collections.sort(sorted);
			found = Collections.binarySearch(sorted, item) >= 0;
		}
		default -> System.out.println("Estratégia não suportada");
		}
		return found;
	}

	// Método sincronizado para inserção segura
	void synchronizedInsert(String query) {
		lock.lock();
		try {
			insert(query);
		} finally {
			lock.unlock();
		}
	}
}

//Classe principal

class Import_1WM {
	public static void run_Import_1WM() {
		System.err.println("\t\t1. SQL \n");

		SQLDatabase db = new SQLDatabase();
		db.insert("INSERT INTO users VALUES ('John', 'Doe')");
		db.insert("INSERT INTO users VALUES ('Jane', 'Dae')");
		db.insert("INSERT INTO users VALUES ('Aon', 'Dee')");
		db.insert("INSERT INTO users VALUES ('Sene', 'Due')");

		System.out.println("Antes da ordenação: " + db.selectAll());
		db.sortData(SortType.ASC);
		System.out.println("Após ordenação ASC: " + db.selectAll());

		boolean found = db.search("INSERT INTO users VALUES ('John', 'Doe')", SearchStrategy.LINEAR_SEARCH);
		System.out.println("Busca realizada. Encontrado? " + found);

		boolean fou = db.search("INSERT INTO users VALUES ('John', 'Doe')", SearchStrategy.HASH_SEARCH);
		System.out.println("Busca realizada. Encontrado? " + fou);

	}
}

//Corpo da Classe _______________________________
enum Moeda {
	REAL("BRL"), DOLAR("USD"), EURO("EUR"), LIBRA("GBP"), IENE("JPY");

	private String codigo;

	Moeda(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}
}

abstract class MoedaAbstract {
	private String nome;
	private double valor;

	public MoedaAbstract(String nome, double valor) {
		this.nome = nome;
		this.valor = valor;
	}

	public String getNome() {
		return nome;
	}

	public double getValor() {
		return valor;
	}

	public abstract void exibirDetalhes();
}

class MoedaConcreta extends MoedaAbstract {

	public MoedaConcreta(String nome, double valor) {
		super(nome, valor);
	}

	@Override
	public void exibirDetalhes() {
		System.out.println("Moeda: " + getNome() + " | Valor: " + getValor());
	}
}

class Import_2WM {
	private static MoedaConcreta[] moedas = new MoedaConcreta[5];
	private static int contador = 0;
	private static boolean sincronizado = true;

	public static synchronized void adicionarMoeda(MoedaConcreta moeda) {
		if (contador < moedas.length) {
			moedas[contador] = moeda;
			contador++;
		} else {
			System.out.println("Não é possível adicionar mais moedas. Limite atingido.");
		}
	}

	public static void exibirMoedas() {
		for (int i = 0; i < contador; i++) {
			moedas[i].exibirDetalhes();
		}
	}

	public static MoedaConcreta buscarMoeda(String nome) {
		for (int i = 0; i < contador; i++) {
			if (moedas[i].getNome().equalsIgnoreCase(nome)) {
				return moedas[i];
			}
		}
		return null;
	}

	public static void ordenarMoedasPorValor() {
		Arrays.sort(moedas, 0, contador, (moeda1, moeda2) -> Double.compare(moeda1.getValor(), moeda2.getValor()));
	}

	public static void removerMoeda(String nome) {
		for (int i = 0; i < contador; i++) {
			if (moedas[i].getNome().equalsIgnoreCase(nome)) {
				for (int j = i; j < contador - 1; j++) {
					moedas[j] = moedas[j + 1];
				}
				moedas[contador - 1] = null;
				contador--;
				System.out.println("Moeda " + nome + " removida com sucesso.");
				return;
			}
		}
		System.out.println("Moeda não encontrada.");
	}

	public static void run_Import_2WM() {
		System.err.println("\t\t2.\n");

		Scanner scanner = new Scanner(System.in);
		boolean rodando = true;

		while (rodando) {
			System.out.println("\nEscolha uma operação:");
			System.out.println("1 - Adicionar Moeda");
			System.out.println("2 - Exibir Moedas");
			System.out.println("3 - Buscar Moeda");
			System.out.println("4 - Ordenar Moedas por Valor");
			System.out.println("5 - Remover Moeda");
			System.out.println("6 - Sair");
			int opcao = scanner.nextInt();
			scanner.nextLine(); // Limpar o buffer

			switch (opcao) {
			case 1:
				System.out.print("Digite o nome da moeda: ");
				String nome = scanner.nextLine();
				System.out.print("Digite o valor da moeda: ");
				double valor = scanner.nextDouble();
				MoedaConcreta moeda = new MoedaConcreta(nome, valor);
				adicionarMoeda(moeda);
				break;

			case 2:
				exibirMoedas();
				break;

			case 3:
				System.out.print("Digite o nome da moeda para buscar: ");
				nome = scanner.nextLine();
				MoedaConcreta moedaBuscada = buscarMoeda(nome);
				if (moedaBuscada != null) {
					moedaBuscada.exibirDetalhes();
				} else {
					System.out.println("Moeda não encontrada.");
				}
				break;

			case 4:
				ordenarMoedasPorValor();
				System.out.println("Moedas ordenadas por valor.");
				break;

			case 5:
				System.out.print("Digite o nome da moeda para remover: ");
				nome = scanner.nextLine();
				removerMoeda(nome);
				break;

			case 6:
				rodando = false;
				break;

			default:
				System.out.println("Opção inválida.");
				break;
			}
		}
		VoutarRun voutarRun = new VoutarRun();
		voutarRun.run_CaracteresMenu_Red();

	}

}

//Corpo da Classe _______________________________
//Enum para operações CRUD
enum Operacao7 {
	INSERIR, ATUALIZAR, DELETAR, BUSCAR, LISTAR
}

//Classe abstrata para definição básica do CRUD
abstract class AbstractCRUD {
	protected List<String> registros = new ArrayList<>();
	protected final ReentrantLock lock = new ReentrantLock();

	abstract void inserir(String registro);

	abstract void atualizar(int index, String novoValor);

	abstract void deletar(int index);

	abstract String buscar(String termo);

	abstract void listarOrdenado();
}

//Implementação do CRUD
class SQLCrud extends AbstractCRUD {

	@Override
	void inserir(String registro) {
		lock.lock();
		try {
			registros.add(registro);
			System.out.println("Registro inserido: " + registro);
		} finally {
			lock.unlock();
		}
	}

	@Override
	void atualizar(int index, String novoValor) {
		if (index >= 0 && index < registros.size()) {
			lock.lock();
			try {
				registros.set(index, novoValor);
				System.out.println("Registro atualizado: " + novoValor);
			} finally {
				lock.unlock();
			}
		} else {
			System.out.println("Índice inválido!");
		}
	}

	@Override
	void deletar(int index) {
		if (index >= 0 && index < registros.size()) {
			lock.lock();
			try {
				System.out.println("Registro deletado: " + registros.remove(index));
			} finally {
				lock.unlock();
			}
		} else {
			System.out.println("Índice inválido!");
		}
	}

	@Override
	String buscar(String termo) {
		for (var registro : registros) {
			if (registro.contains(termo)) {
				return "Registro encontrado: " + registro;
			}
		}
		return "Registro não encontrado.";
	}

	@Override
	void listarOrdenado() {
		lock.lock();
		try {
			registros.sort(Comparator.naturalOrder());
			System.out.println("Registros ordenados: " + registros);
		} finally {
			lock.unlock();
		}
	}
}

//Classe principal para executar as operações
class Import_3WM {
	public static void run_Import_3WM() {
		System.err.println("\t\t3. \n");
		SQLCrud crud = new SQLCrud();
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("Escolha uma operação: INSERIR, ATUALIZAR, DELETAR, BUSCAR, LISTAR, SAIR");
			String input = scanner.next().toUpperCase();
			Operacao7 operacao7;

			try {
				operacao7 = Operacao7.valueOf(input);
			} catch (IllegalArgumentException e) {
				if (input.equals("SAIR")) {
					break;
				}
				System.out.println("Operação inválida!");
				continue;
			}

			switch (operacao7) {
			case INSERIR:
				System.out.println("Digite o registro:");
				scanner.nextLine(); // Consumir a quebra de linha
				crud.inserir(scanner.nextLine());
				break;
			case ATUALIZAR:
				System.out.println("Digite o índice e o novo valor:");
				int indexUpdate = scanner.nextInt();
				scanner.nextLine();
				crud.atualizar(indexUpdate, scanner.nextLine());
				break;
			case DELETAR:
				System.out.println("Digite o índice a deletar:");
				int indexDelete = scanner.nextInt();
				crud.deletar(indexDelete);
				break;
			case BUSCAR:
				System.out.println("Digite o termo de busca:");
				scanner.nextLine();
				System.out.println(crud.buscar(scanner.nextLine()));
				break;
			case LISTAR:
				crud.listarOrdenado();
				break;
			default:
				System.out.println("Operação não reconhecida.");
			}
		}
		VoutarRun voutarRun = new VoutarRun();
		voutarRun.run_CaracteresMenu_Red();
	}
}
