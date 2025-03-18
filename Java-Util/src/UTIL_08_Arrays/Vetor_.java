package UTIL_08_Arrays;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

import util.Linhas;
import util.VoutarRun;

import java.util.concurrent.CopyOnWriteArrayList;

public class Vetor_ {
	public static void Vetor_Run() {

		Linhas linhas = new Linhas();
		String collec = "\n\t Arrays: Métodos utilitários para trabalhar com arrays (ex.: ordenação, busca \n";
		System.err.println(collec);

		linhas.run_Caracteres();
		Import_5V.run_Import_5V();
		
		linhas.run_Caracteres();
		Import_1V.run_Import_1V();

		linhas.run_Caracteres();
		Import_2V.run_Import_2V();

		linhas.run_Caracteres();
		Import_3V.run_Import_3V();

		linhas.run_Caracteres();
		Import_4V.run_Import_4V();
	}
}

//Corpo da Classe _______________________________
//Enum com operações CRUD
enum CrudOperation {
	CREATE, READ, UPDATE, DELETE, EXIT
}

//Classe abstrata para definir operações
abstract class AbstractCrud {
	protected int[] ids = new int[10]; // Vetor de IDs
	protected int count = 0;
	protected final ReentrantLock lock = new ReentrantLock();

	abstract void create(int id);

	abstract void read();

	abstract void update(int oldId, int newId);

	abstract void delete(int id);

	// Método de ordenação
	public void sort() {
		Arrays.sort(ids, 0, count);
		System.out.println("IDs ordenados: " + Arrays.toString(Arrays.copyOf(ids, count)));
	}

	// Método de busca
	public int search(int id) {
		int index = Arrays.binarySearch(ids, 0, count, id);
		return (index >= 0) ? index : -1;
	}
}

//Implementação do CRUD
class Crud extends AbstractCrud {
	@Override
	public void create(int id) {
		lock.lock();
		try {
			if (count < ids.length) {
				ids[count++] = id;
				System.out.println("ID " + id + " adicionado com sucesso.");
			} else {
				System.out.println("Limite de IDs atingido.");
			}
		} finally {
			lock.unlock();
		}
	}

	@Override
	public void read() {
		System.out.println("Lista de IDs: " + Arrays.toString(Arrays.copyOf(ids, count)));
	}

	@Override
	public void update(int oldId, int newId) {
		lock.lock();
		try {
			int index = search(oldId);
			if (index != -1) {
				ids[index] = newId;
				System.out.println("ID " + oldId + " atualizado para " + newId);
			} else {
				System.out.println("ID não encontrado.");
			}
		} finally {
			lock.unlock();
		}
	}

	@Override
	public void delete(int id) {
		lock.lock();
		try {
			int index = search(id);
			if (index != -1) {
				for (int i = index; i < count - 1; i++) {
					ids[i] = ids[i + 1];
				}
				count--;
				System.out.println("ID " + id + " removido.");
			} else {
				System.out.println("ID não encontrado.");
			}
		} finally {
			lock.unlock();
		}
	}
}

//Classe principal
class Import_1V {
	public static void run_Import_1V() {
		System.err.println("\t\t1. ID \n");
		Scanner scanner = new Scanner(System.in);
		Crud crud = new Crud();

		while (true) {
			System.out.println("\nEscolha uma operação: CREATE, READ, UPDATE, DELETE, EXIT");
			String input = scanner.next().toUpperCase();

			if (!Arrays.stream(CrudOperation.values()).anyMatch(op -> op.name().equals(input))) {
				System.out.println("Operação inválida. Tente novamente.");
				continue;
			}

			CrudOperation operation = CrudOperation.valueOf(input);

			if (operation == CrudOperation.EXIT) {
				System.out.println("Saindo...");
				break;
			}

			switch (operation) {
			case CREATE:
				System.out.print("Digite o ID a ser adicionado: ");
				int idCreate = scanner.nextInt();
				crud.create(idCreate);
				break;
			case READ:
				crud.read();
				break;
			case UPDATE:
				System.out.print("Digite o ID antigo: ");
				int oldId = scanner.nextInt();
				System.out.print("Digite o novo ID: ");
				int newId = scanner.nextInt();
				crud.update(oldId, newId);
				break;
			case DELETE:
				System.out.print("Digite o ID a ser removido: ");
				int idDelete = scanner.nextInt();
				crud.delete(idDelete);
				break;
			}
		}
		VoutarRun voutarRun = new VoutarRun();
		voutarRun.run_CaracteresMenu_Red();
	}
}

//Corpo da Classe _______________________________
//Enum para categorias de alunos
enum Categoria2 {
	BASICO, INTERMEDIARIO, AVANCADO, PROFISSIONAL, AMADOR, COMPETITIVO, CASUAL, PREMIUM, VIP, EXCLUSIVO;
}

//Enum para perfil dos alunos
enum Perfil {
	ADMIN, PROFESSOR, ALUNO, COORDENADOR, SECRETARIO, TUTOR, ORIENTADOR, DIRETOR, PESQUISADOR, ASSISTENTE;
}

//Classe abstrata
abstract class Pessoa {
	protected String nome;
	protected int idade;

	public Pessoa(String nome, int idade) {
		this.nome = nome;
		this.idade = idade;
	}

	public abstract void exibirInformacoes();
}

//Classe Aluno que herda de Pessoa
class Aluno extends Pessoa {
	private Categoria2 categoria;
	private Perfil perfil;

	public Aluno(String nome, int idade, Categoria2 categoria, Perfil perfil) {
		super(nome, idade);
		this.categoria = categoria;
		this.perfil = perfil;
	}

	public Categoria2 getCategoria() {
		return categoria;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	@Override
	public void exibirInformacoes() {
		System.out.println("Nome: " + nome + ", Idade: " + idade + ", Categoria: " + categoria + ", Perfil: " + perfil);
	}
}

//Classe de gerenciamento do CRUD
class GerenciadorAlunos {
	private final List<Aluno> alunos = Collections.synchronizedList(new CopyOnWriteArrayList<>());

	public void adicionarAluno(Aluno aluno) {
		alunos.add(aluno);
	}

	public void listarAlunos() {
		for (Aluno aluno : alunos) {
			aluno.exibirInformacoes();
		}
	}

	public void ordenarAlunos() {
		alunos.sort(Comparator.comparing(a -> a.nome));
	}

	public Aluno buscarAluno(String nome) {
		for (Aluno aluno : alunos) {
			if (aluno.nome.equalsIgnoreCase(nome)) {
				return aluno;
			}
		}
		return null;
	}

	public void removerAluno(String nome) {
		Iterator<Aluno> iterator = alunos.iterator();
		while (iterator.hasNext()) {
			Aluno aluno = iterator.next();
			if (aluno.nome.equalsIgnoreCase(nome)) {
				iterator.remove();
				break;
			}
		}
	}
}

class Import_2V {
	public static void run_Import_2V() {
		System.err.println("\t\t2. \n");
		Scanner scanner = new Scanner(System.in);
		GerenciadorAlunos gerenciador = new GerenciadorAlunos();
		int opcao;

		do {
			System.out.println("\n1. Adicionar Aluno");
			System.out.println("2. Listar Alunos");
			System.out.println("3. Ordenar Alunos");
			System.out.println("4. Buscar Aluno");
			System.out.println("5. Remover Aluno");
			System.out.println("6. Sair");
			System.out.print("Escolha uma opção: ");
			opcao = scanner.nextInt();
			scanner.nextLine();

			switch (opcao) {
			case 1:
				System.out.print("Nome: ");
				String nome = scanner.nextLine();
				System.out.print("Idade: ");
				int idade = scanner.nextInt();
				scanner.nextLine();

				System.out.println("Escolha uma categoria (0-9): ");
				for (Categoria2 c : Categoria2.values()) {
					System.out.println(c.ordinal() + " - " + c);
				}
				Categoria2 categoria = Categoria2.values()[scanner.nextInt()];
				scanner.nextLine();

				System.out.println("Escolha um perfil (0-9): ");
				for (Perfil p : Perfil.values()) {
					System.out.println(p.ordinal() + " - " + p);
				}
				Perfil perfil = Perfil.values()[scanner.nextInt()];
				scanner.nextLine();

				gerenciador.adicionarAluno(new Aluno(nome, idade, categoria, perfil));
				break;

			case 2:
				gerenciador.listarAlunos();
				break;

			case 3:
				gerenciador.ordenarAlunos();
				System.out.println("Alunos ordenados!");
				break;

			case 4:
				System.out.print("Nome do aluno para buscar: ");
				String nomeBusca = scanner.nextLine();
				Aluno alunoEncontrado = gerenciador.buscarAluno(nomeBusca);
				if (alunoEncontrado != null) {
					alunoEncontrado.exibirInformacoes();
				} else {
					System.out.println("Aluno não encontrado.");
				}
				break;

			case 5:
				System.out.print("Nome do aluno para remover: ");
				String nomeRemover = scanner.nextLine();
				gerenciador.removerAluno(nomeRemover);
				System.out.println("Aluno removido!");
				break;

			case 6:
				System.out.println("Saindo...");
				break;

			default:
				System.out.println("Opção inválida!");
				break;
			}
		} while (opcao != 6);
		VoutarRun voutarRun = new VoutarRun();
		voutarRun.run_CaracteresMenu_Red();
	}
}

//Corpo da Classe _______________________________
//Enum para Categorias
enum Categoria5 {
	ADMIN, USER, GUEST, MANAGER, DEVELOPER, TESTER, HR, SALES, SUPPORT, FINANCE;
}

//Classe Abstrata para Entidade
abstract class Entidade {
	protected int id;
	protected String nome;

	public Entidade(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public abstract void exibirInformacoes();
}

//Classe Perfil herdando Entidade
class Perfil2 extends Entidade {
	private Categoria5 categoria;

	public Perfil2(int id, String nome, Categoria5 categoria) {
		super(id, nome);
		this.categoria = categoria;
	}

	public void exibirInformacoes() {
		System.out.println("ID: " + id + ", Nome: " + nome + ", Categoria: " + categoria);
	}
}

//Classe CRUD
class Crud3 {
	private Perfil2[] perfis = new Perfil2[0];

	public synchronized void adicionarPerfil(int id, String nome, Categoria5 categoria) {
		perfis = Arrays.copyOf(perfis, perfis.length + 1);
		perfis[perfis.length - 1] = new Perfil2(id, nome, categoria);
	}

	public void listarPerfis() {
		Arrays.sort(perfis, (a, b) -> Integer.compare(a.getId(), b.getId()));
		for (Perfil2 p : perfis) {
			p.exibirInformacoes();
		}
	}

	public Perfil2 buscarPerfil(int id) {
		for (Perfil2 p : perfis) {
			if (p.getId() == id)
				return p;
		}
		return null;
	}

	public synchronized void removerPerfil(int id) {
		int index = -1;
		for (int i = 0; i < perfis.length; i++) {
			if (perfis[i].getId() == id) {
				index = i;
				break;
			}
		}
		if (index != -1) {
			Perfil2[] novoArray = new Perfil2[perfis.length - 1];
			System.arraycopy(perfis, 0, novoArray, 0, index);
			System.arraycopy(perfis, index + 1, novoArray, index, perfis.length - index - 1);
			perfis = novoArray;
		}
	}
}

//Classe Principal

class Import_3V {
	public static void run_Import_3V() {
		System.err.println("\t\t3. \n");
		Scanner scanner = new Scanner(System.in);
		Crud3 crud = new Crud3();
		int opcao;

		while (true) {
			System.out.println("1. Adicionar Perfil\n2. Listar Perfis\n3. Buscar Perfil\n4. Remover Perfil\n5. Sair");
			opcao = scanner.nextInt();
			scanner.nextLine();

			if (opcao == 1) {
				System.out.print("ID: ");
				int id = scanner.nextInt();
				scanner.nextLine();
				System.out.print("Nome: ");
				String nome = scanner.nextLine();
				System.out.println("Escolha uma categoria:");
				for (Categoria5 c : Categoria5.values()) {
					System.out.println(c.ordinal() + ". " + c);
				}
				int catIndex = scanner.nextInt();
				crud.adicionarPerfil(id, nome, Categoria5.values()[catIndex]);
			} else if (opcao == 2) {
				crud.listarPerfis();
			} else if (opcao == 3) {
				System.out.print("Digite o ID: ");
				int id = scanner.nextInt();
				Perfil2 p = crud.buscarPerfil(id);
				if (p != null)
					p.exibirInformacoes();
				else
					System.out.println("Perfil não encontrado.");
			} else if (opcao == 4) {
				System.out.print("Digite o ID: ");
				int id = scanner.nextInt();
				crud.removerPerfil(id);
			} else if (opcao == 5) {
				break;
			}
		}

		VoutarRun voutarRun = new VoutarRun();
		voutarRun.run_CaracteresMenu_Red();
	}
}

//Classe Principal

//Enums
enum Categoria6 {
	FUNDAMENTAL, MEDIO, SUPERIOR
}

enum Perfil6 {
	ADMIN, PROFESSOR, ALUNO6
}

enum Disciplina {
	MATEMATICA, HISTORIA, FISICA, QUIMICA, PORTUGUES, INGLES, BIOLOGIA, ARTES, GEOGRAFIA, FILOSOFIA
}

enum Frequencia {
	PRESENTE, AUSENTE, JUSTIFICADO
}

//Classe Abstrata
abstract class Pessoa6 {
	protected int id6;
	protected String nome;
	protected Perfil6 perfil;

	public Pessoa6(int id6, String nome, Perfil6 perfil) {
		this.id6 = id6;
		this.nome = nome;
		this.perfil = perfil;
	}

	public abstract void exibirInformacoes();
}

//Classe Aluno
class Aluno6 extends Pessoa6 {
	private Categoria6 categoria;
	private Map<Disciplina, Double> notas;
	private Map<Disciplina, Frequencia> frequencias;

	public Aluno6(int id6, String nome, Categoria6 categoria) {
		super(id6, nome, Perfil6.ALUNO6);
		this.categoria = categoria;
		this.notas = new HashMap<>();
		this.frequencias = new HashMap<>();
	}

	public void adicionarNota(Disciplina disciplina, double nota) {
		notas.put(disciplina, nota);
	}

	public void registrarFrequencia(Disciplina disciplina, Frequencia status) {
		frequencias.put(disciplina, status);
	}

	public double getNota(Disciplina disciplina) {
		return notas.getOrDefault(disciplina, 0.0);
	}

	public Frequencia getFrequencia(Disciplina disciplina) {
		return frequencias.getOrDefault(disciplina, Frequencia.AUSENTE);
	}

	@Override
	public void exibirInformacoes() {
		System.out.println("ID: " + id6 + ", Nome: " + nome + ", Categoria: " + categoria);
	}
}

//Classe Gerenciador de Alunos
class GerenciadorAlunos6 {
	List<Aluno6> alunos6 = new ArrayList<>();
	private ReentrantLock lock = new ReentrantLock();

	public void adicionarAluno(Aluno6 aluno) {
		lock.lock();
		try {
			alunos6.add(aluno);
		} finally {
			lock.unlock();
		}
	}

	public void listarAlunos() {
		alunos6.forEach(Aluno6::exibirInformacoes);
	}

	public void ordenarPorNome() {
		alunos6.sort(Comparator.comparing(a -> a.nome));
	}

	public Aluno6 buscarPorId(int id) {
		return alunos6.stream().filter(a -> a.id6 == id).findFirst().orElse(null);
	}

	public void removerAluno(int id6) {
		alunos6.removeIf(a -> a.id6 == id6);
	}
}

//Classe Principal com Menu

class Import_4V {
	public static void run_Import_4V() {
		Scanner scanner = new Scanner(System.in);
		GerenciadorAlunos6 gerenciador = new GerenciadorAlunos6();
		int opcao;

		do {
			System.out.println(
					"1. Adicionar Aluno\n2. Listar Alunos\n3. Buscar Aluno\n4. Ordenar Alunos\n5. Remover Aluno\n0. Sair");
			opcao = scanner.nextInt();

			switch (opcao) {
			case 1:
				System.out.print("Nome: ");
				String nome = scanner.next();
				System.out.print("Categoria (0-FUNDAMENTAL, 1-MEDIO, 2-SUPERIOR): ");
				Categoria6 categoria = Categoria6.values()[scanner.nextInt()];
				Aluno6 aluno = new Aluno6(gerenciador.alunos6.size() + 1, nome, categoria);
				gerenciador.adicionarAluno(aluno);
				break;
			case 2:
				gerenciador.listarAlunos();
				break;
			case 3:
				System.out.print("ID do aluno: ");
				Aluno6 encontrado = gerenciador.buscarPorId(scanner.nextInt());
				if (encontrado != null)
					encontrado.exibirInformacoes();
				else
					System.out.println("Aluno não encontrado.");
				break;
			case 4:
				gerenciador.ordenarPorNome();
				System.out.println("Alunos ordenados!");
				break;
			case 5:
				System.out.print("ID do aluno a remover: ");
				gerenciador.removerAluno(scanner.nextInt());
				System.out.println("Aluno removido!");
				break;
			case 0:
				System.out.println("Saindo...");
				break;
			default:
				System.out.println("Opção inválida!");
			}
		} while (opcao != 0);

		VoutarRun voutarRun = new VoutarRun();
		voutarRun.run_CaracteresMenu_Red();
	}
}

// Classes de codigos

//Enum para tipos de hosts
enum TipoHosts {
	VPS, DEDICADO, COMPARTILHADO, CLOUD, EDGE, COLOCATION, BARE_METAL, CONTAINER, SERVERLESS, LAMBDA;
}

//Classe abstrata base para todos os hosts
abstract class AbstractHosts {
	String nome;
	TipoHosts tipo;

	AbstractHosts(String nome, TipoHosts tipo) {
		this.nome = nome;
		this.tipo = tipo;
	}

	abstract void detalhes();
}

//10 classes abstratas extendendo a base
abstract class Host1 extends AbstractHosts {
	Host1(String n) {
		super(n, TipoHosts.VPS);
	}

	void detalhes() {
	}
}

abstract class Host22 extends AbstractHosts {
	Host22(String n) {
		super(n, TipoHosts.DEDICADO);
	}

	void detalhes() {
	}
}

abstract class Host3 extends AbstractHosts {
	Host3(String n) {
		super(n, TipoHosts.COMPARTILHADO);
	}

	void detalhes() {
	}
}

abstract class Host4 extends AbstractHosts {
	Host4(String n) {
		super(n, TipoHosts.CLOUD);
	}

	void detalhes() {
	}
}

abstract class Host5 extends AbstractHosts {
	Host5(String n) {
		super(n, TipoHosts.EDGE);
	}

	void detalhes() {
	}
}

abstract class Host6 extends AbstractHosts {
	Host6(String n) {
		super(n, TipoHosts.COLOCATION);
	}

	void detalhes() {
	}
}

abstract class Host7 extends AbstractHosts {
	Host7(String n) {
		super(n, TipoHosts.BARE_METAL);
	}

	void detalhes() {
	}
}

abstract class Host8 extends AbstractHosts {
	Host8(String n) {
		super(n, TipoHosts.CONTAINER);
	}

	void detalhes() {
	}
}

abstract class Host9 extends AbstractHosts {
	Host9(String n) {
		super(n, TipoHosts.SERVERLESS);
	}

	void detalhes() {
	}
}

abstract class Host10 extends AbstractHosts {
	Host10(String n) {
		super(n, TipoHosts.LAMBDA);
	}

	void detalhes() {
	}
}

class Import_5V {
	private static String[][] hosts = new String[10][2];
	private static int count = 0;
	private static ReentrantLock lock = new ReentrantLock();

	// Método de ordenação
	public static void ordenar() {
		Arrays.sort(hosts, (a, b) -> a[0] != null && b[0] != null ? a[0].compareTo(b[0]) : 0);
	}

	// Método de busca
	public static int buscar(String nome) {
		for (int i = 0; i < count; i++) {
			if (hosts[i][0].equalsIgnoreCase(nome)) {
				return i;
			}
		}
		return -1;
	}

	public static void run_Import_5V() {

		Scanner scanner = new Scanner(System.in);
		int opcao;

		do {
			System.out.println("1. Adicionar Host");
			System.out.println("2. Listar Hosts");
			System.out.println("3. Buscar Host");
			System.out.println("4. Ordenar Hosts");
			System.out.println("5. Sair");
			System.out.print("Escolha uma opção: ");
			opcao = scanner.nextInt();
			scanner.nextLine();

			switch (opcao) {
			case 1:
				if (count >= 10) {
					System.out.println("Lista cheia!");
					break;
				}
				System.out.print("Nome do Host: ");
				String nome = scanner.nextLine();
				System.out.print("Tipo (0-9): ");
				int tipoIndex = scanner.nextInt();
				scanner.nextLine();

				if (tipoIndex < 0 || tipoIndex >= TipoHost.values().length) {
					System.out.println("Tipo inválido!");
					break;
				}

				lock.lock();
				try {
					hosts[count][0] = nome;
					hosts[count][1] = TipoHost.values()[tipoIndex].name();
					count++;
				} finally {
					lock.unlock();
				}
				System.out.println("Host adicionado!");
				break;

			case 2:
				for (int i = 0; i < count; i++) {
					System.out.println("Nome: " + hosts[i][0] + " | Tipo: " + hosts[i][1]);
				}
				break;

			case 3:
				System.out.print("Digite o nome do Host para buscar: ");
				String nomeBusca = scanner.nextLine();
				int index = buscar(nomeBusca);
				if (index != -1) {
					System.out.println("Encontrado: " + hosts[index][0] + " | Tipo: " + hosts[index][1]);
				} else {
					System.out.println("Host não encontrado.");
				}
				break;

			case 4:
				ordenar();
				System.out.println("Hosts ordenados!");
				break;

			case 5:
				System.out.println("Saindo...");
				break;

			default:
				System.out.println("Opção inválida!");
			}
		} while (opcao != 5);
		VoutarRun voutarRun = new VoutarRun();
		voutarRun.run_CaracteresMenu_Red();

	}
}
