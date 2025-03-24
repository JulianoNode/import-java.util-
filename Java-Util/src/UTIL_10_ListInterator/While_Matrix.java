package UTIL_10_ListInterator;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;
import util.Linhas;

public class While_Matrix {
	public static void While_Run() {
		Linhas linhas = new Linhas();
		String collec = "\n\t ListIterator<E>: Iterador para listas que permite percorrer em ambas as direções. \n";
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
//Enum para os cargos na obra
enum Cargo {
	ENGENHEIRO, MESTRE_DE_OBRAS, PEDREIRO, SERVENTE, ELETRICISTA;

	public String descricao() {
		switch (this) {
		case ENGENHEIRO:
			return "Responsável pelo planejamento e execução.";
		case MESTRE_DE_OBRAS:
			return "Coordena os trabalhadores na obra.";
		case PEDREIRO:
			return "Executa tarefas de alvenaria e acabamento.";
		case SERVENTE:
			return "Auxilia os pedreiros e organiza materiais.";
		case ELETRICISTA:
			return "Instala e mantém sistemas elétricos.";
		default:
			return "Cargo não definido.";
		}
	}
}

//Enum para nível de acesso
enum NivelAcessos {
	ADMINISTRADOR, GERENTE, OPERACIONAL, VISITANTE;

	public boolean temPermissao(String operacao) {
		if (this == ADMINISTRADOR)
			return true;
		if (this == GERENTE && !operacao.equals("Excluir"))
			return true;
		if (this == OPERACIONAL && operacao.equals("Visualizar"))
			return true;
		return this == VISITANTE && operacao.equals("Visualizar Básico");
	}
}

//Classe abstrata representando um funcionário de obra
abstract class Funcionarios {
	protected String nome;
	protected Cargo cargo;
	protected NivelAcessos acesso;

	public Funcionarios(String nome, Cargo cargo, NivelAcessos acesso) {
		this.nome = nome;
		this.cargo = cargo;
		this.acesso = acesso;
	}

	public abstract void executarTarefa();

	public synchronized void acessarSistema(String operacao) {
		if (acesso.temPermissao(operacao)) {
			System.out.println(nome + " tem permissão para " + operacao);
		} else {
			System.out.println(nome + " NÃO tem permissão para " + operacao);
		}
	}
}

//Classe abstrata para gerenciar sincronização de funcionários
abstract class GerenciadorFuncionarios {
	protected List<Funcionarios> funcionarios = Collections.synchronizedList(new ArrayList<>());
	protected ReentrantLock lock = new ReentrantLock();

	public void adicionarFuncionario(Funcionarios f) {
		lock.lock();
		try {
			funcionarios.add(f);
		} finally {
			lock.unlock();
		}
	}

	public abstract void listarFuncionarios();
}

//Implementação concreta
class GerenteObra extends GerenciadorFuncionarios {
	@Override
	public void listarFuncionarios() {
		synchronized (funcionarios) {
			ListIterator<Funcionarios> it = funcionarios.listIterator();
			while (it.hasNext()) {
				Funcionarios f = it.next();
				System.out.println(f.nome + " - " + f.cargo + " - " + f.acesso);
			}
		}
	}
}

//Classe concreta de funcionário
class Engenheiros extends Funcionarios {
	public Engenheiros(String nome, NivelAcessos acesso) {
		super(nome, Cargo.ENGENHEIRO, acesso);
	}

	@Override
	public void executarTarefa() {
		System.out.println(nome + " está planejando a construção.");
	}
}

class Main_Obra {
	public static void main_Run() {
		GerenteObra gerente = new GerenteObra();

		Funcionarios f1 = new Engenheiros("Carlos", NivelAcessos.ADMINISTRADOR);
		Funcionarios f2 = new Engenheiros("Ana", NivelAcessos.GERENTE);
		Funcionarios f3 = new Engenheiros("Mia", NivelAcessos.VISITANTE);

		gerente.adicionarFuncionario(f1);
		gerente.adicionarFuncionario(f2);
		gerente.adicionarFuncionario(f3);

		gerente.listarFuncionarios();
		System.err.println("\n");
		f1.acessarSistema("Editar");
		f2.acessarSistema("Excluir");
		f3.acessarSistema("Visualizar Básico");
		System.err.println("\n");
		f3.acessarSistema("Editar");
		f1.acessarSistema("Excluir");
		f2.acessarSistema("Visualizar Básico");
	}
}

class Import_1WM {
	public static void run_Import_1WM() {
		System.err.println("\t\t1.\n");
		Main_Obra.main_Run();
	}
}

//Corpo da Classe _______________________________

//Enum complexo com 10 funções e vetor
enum Cargo1 {
	ENGENHEIRO(new String[] { "Projetos", "Supervisão" }), PEDREIRO(new String[] { "Construção", "Acabamento" }),

	ELETRICISTA(new String[] { "Fiação", "Manutenção" }), ENCANADOR(new String[] { "Tubulação", "Hidráulica" });

	private String[] tarefas;

	Cargo1(String[] tarefas) {
		this.tarefas = tarefas;
	}

	public String[] getTarefas() {
		return tarefas;
	}
}

//Enum NivelAcesso com laço for
enum NivelAcesso1 {
	ADMIN, SUPERVISOR, OPERARIO;

	public static void listarNiveis() {
		for (NivelAcesso1 nivel : NivelAcesso1.values()) {
			System.out.println("Acesso: " + nivel);
		}
	}
}

//Classe abstrata base
abstract class Pessoa1 {
	String nome;
	int idade;

	public Pessoa1(String nome, int idade) {
		this.nome = nome;
		this.idade = idade;
	}

	abstract void exibirDetalhes();
}

//Classe abstrata intermediária
abstract class Funcionario1 extends Pessoa1 {
	Cargo1 cargo;
	NivelAcesso1 acesso;

	public Funcionario1(String nome, int idade, Cargo1 cargo, NivelAcesso1 acesso) {
		super(nome, idade);
		this.cargo = cargo;
		this.acesso = acesso;
	}

	synchronized void acessarSistema() {
		System.out.println(nome + " está acessando o sistema com nível " + acesso);
	}

	abstract void executarTarefa();

	synchronized void registrarPonto() {
		System.out.println(nome + " registrou o ponto com sucesso.");
	}
}

//Classe abstrata avançada
abstract class TrabalhadorObra extends Funcionario1 {
	public TrabalhadorObra(String nome, int idade, Cargo1 cargo, NivelAcesso1 acesso) {
		super(nome, idade, cargo, acesso);
	}
}

//Implementação final
class Pedreiro extends TrabalhadorObra {
	public Pedreiro(String nome, int idade) {
		super(nome, idade, Cargo1.PEDREIRO, NivelAcesso1.OPERARIO);
	}

	@Override
	void exibirDetalhes() {
		System.out.println("Pedreiro: " + nome + ", Idade: " + idade);
	}

	@Override
	void executarTarefa() {
		System.out.println(nome + " está executando tarefas: " + Arrays.toString(cargo.getTarefas()));
	}
}

class Main_Fun {
	public static void main_Fun() {
		List<Funcionario1> funcionarios = new ArrayList<>();
		funcionarios.add(new Pedreiro("Carlos", 35));
		funcionarios.add(new Pedreiro("João", 40));

		// Usando ListIterator
		ListIterator<Funcionario1> iterador = funcionarios.listIterator();
		while (iterador.hasNext()) {
			Funcionario1 f = iterador.next();
			f.exibirDetalhes();
			f.executarTarefa();
			f.acessarSistema();
			f.registrarPonto();
		}
	}
}

class Import_2WM {
	public static void run_Import_2WM() {
		System.err.println("\t\t2.\n");
		Main_Fun.main_Fun();
	}
}

//Corpo da Classe _______________________________
class ObraLogin {
	// Definindo Enums para Nível de Acesso
	enum NivelAcesso {
		ADMIN(1), SUPERVISOR(2), OPERARIO(3), GESTOR(4);

		private final int nivel;

		NivelAcesso(int nivel) {
			this.nivel = nivel;
		}

		public int getNivel() {
			return nivel;
		}

		public static NivelAcesso getNivelByValue(int value) {
			for (NivelAcesso nivel : values()) {
				if (nivel.getNivel() == value) {
					return nivel;
				}
			}
			return null;
		}
	}

	// Enum de Status do Login
	enum StatusLogin {
		SUCESSO, FALHA, BLOQUEADO, INATIVO;

		public static StatusLogin getStatusByValue(String status) {
			switch (status.toUpperCase()) {
			case "SUCESSO":
				return SUCESSO;
			case "FALHA":
				return FALHA;
			case "BLOQUEADO":
				return BLOQUEADO;
			case "INATIVO":
				return INATIVO;
			default:
				return FALHA;
			}
		}
	}

	// Classe Abstrata de Funcionário
	abstract static class Funcionario {
		protected String nome;
		protected String usuario;
		protected String senha;
		protected NivelAcesso acesso;

		public Funcionario(String nome, String usuario, String senha, NivelAcesso acesso) {
			this.nome = nome;
			this.usuario = usuario;
			this.senha = senha;
			this.acesso = acesso;
		}

		public abstract StatusLogin logar(String usuario, String senha);
	}

	// Implementação de um Operário
	static class Operario extends Funcionario {

		public Operario(String nome, String usuario, String senha, NivelAcesso acesso) {
			super(nome, usuario, senha, acesso);
		}

		@Override
		public StatusLogin logar(String usuario, String senha) {
			if (this.usuario.equals(usuario) && this.senha.equals(senha)) {
				return StatusLogin.SUCESSO;
			}
			return StatusLogin.FALHA;
		}
	}

	// Implementação de um Supervisor
	static class Supervisor extends Funcionario {

		public Supervisor(String nome, String usuario, String senha, NivelAcesso acesso) {
			super(nome, usuario, senha, acesso);
		}

		@Override
		public StatusLogin logar(String usuario, String senha) {
			if (this.usuario.equals(usuario) && this.senha.equals(senha)) {
				return StatusLogin.SUCESSO;
			}
			return StatusLogin.FALHA;
		}
	}

	// Classe com funções de login e validação
	public static class SistemaLogin {
		private static List<Funcionario> funcionarios = new ArrayList<>();
		private static ListIterator<Funcionario> iterador;

		// Adicionando funcionários
		public static void cadastrarFuncionario(Funcionario funcionario) {
			funcionarios.add(funcionario);
		}

		// Realizando login
		public static StatusLogin realizarLogin(String usuario, String senha) {
			iterador = funcionarios.listIterator();
			while (iterador.hasNext()) {
				Funcionario funcionario = iterador.next();
				StatusLogin status = funcionario.logar(usuario, senha);
				if (status == StatusLogin.SUCESSO) {
					return StatusLogin.SUCESSO;
				}
			}
			return StatusLogin.FALHA;
		}

		// Função de sincronização para login
		public static synchronized boolean loginSincronizado(String usuario, String senha) {
			return realizarLogin(usuario, senha) == StatusLogin.SUCESSO;
		}
	}

	public static void main_Login() {
		// Cadastro de funcionários
		SistemaLogin.cadastrarFuncionario(new Operario("João", "joao123", "senha123", NivelAcesso.OPERARIO));
		SistemaLogin.cadastrarFuncionario(new Supervisor("Maria", "maria456", "senha456", NivelAcesso.SUPERVISOR));

		// Realizando login de forma sincronizada
		String usuario = "joao123";
		String senha = "senha123";

		// Verificando o status de login
		boolean resultadoLogin = SistemaLogin.loginSincronizado(usuario, senha);
		if (resultadoLogin) {
			System.out.println("Login realizado com sucesso: " + usuario);
		} else {
			System.out.println("Falha no login: " + usuario);
		}

		// Acesso ao nível de segurança
		for (NivelAcesso nivel : NivelAcesso.values()) {
			System.out.println("Nível de Acesso: " + nivel + " com valor " + nivel.getNivel());
		}
		
		SistemaLogin.cadastrarFuncionario(new Supervisor("Maria", "maria456", "senha456", NivelAcesso.SUPERVISOR));
		// Realizando login de forma sincronizada
		String usuario2 = "Maria";
		String senha2 = "senha123";

		// Verificando o status de login
		boolean resultadoLogin1 = SistemaLogin.loginSincronizado(usuario2, senha2);
		if (resultadoLogin1) {
			System.out.println("Login realizado com sucesso: " + usuario2);
		} else {
			System.out.println("Falha no login: " + usuario2);
		}

		// Acesso ao nível de segurança
		for (NivelAcesso nivel : NivelAcesso.values()) {
			System.out.println("Nível de Acesso: " + nivel + " com valor " + nivel.getNivel());
		}
	}
}

class Import_3WM {
	public static void run_Import_3WM() {
		System.err.println("\t\t3. \n");
		 ObraLogin.main_Login();
	}
}