package UTIL_11_ArrayList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import util.Linhas;
import util.VoutarMenu;

public class While_Matrix {
	public static void While_Run() {
		Linhas linhas = new Linhas();
		String collec = "\n\t Nome \n";
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
//Enum para diferentes tipos de agências bancárias
enum TipoAgencia {
	CENTRAL(1001), REGIONAL(2002), LOCAL(3003), INTERNACIONAL(4004), DIGITAL(5005), PRIVADA(6006);

	private final int codigo;

	TipoAgencia(int codigo) {
		this.codigo = codigo;
	}

	public int getCodigo() {
		return codigo;
	}
}

//Classe abstrata para contas bancárias
abstract class Conta {
	protected String usuario;
	protected String senha;
	protected TipoAgencia agencia;

	public Conta(String usuario, String senha, TipoAgencia agencia) {
		this.usuario = usuario;
		this.senha = senha;
		this.agencia = agencia;
	}

	public abstract boolean autenticar(String usuario, String senha);

	public TipoAgencia getAgencia() {
		return agencia;
	}
}

//Classe concreta que implementa a conta de usuário
class ContaUsuario extends Conta {
	public ContaUsuario(String usuario, String senha, TipoAgencia agencia) {
		super(usuario, senha, agencia);
	}

	@Override
	public boolean autenticar(String usuario, String senha) {
		return this.usuario.equals(usuario) && this.senha.equals(senha);
	}
}

//Classe para gerenciar o sistema de login
class SistemaBanco {
	private ArrayList<Conta> contas = new ArrayList<>();

	public synchronized void adicionarConta(Conta conta) {
		contas.add(conta);
	}

	public synchronized boolean autenticarUsuario(String usuario, String senha, int codigoAgencia) {
		Iterator<Conta> it = contas.iterator();
		while (it.hasNext()) {
			Conta conta = it.next();
			if (conta.autenticar(usuario, senha) && conta.getAgencia().getCodigo() == codigoAgencia) {
				return true;
			}
		}
		return false;
	}
}

//Classe principal para execução
class LoginAgenciaBanco {
	public static void mainLAB() {
		Scanner scanner = new Scanner(System.in);
		SistemaBanco sistema = new SistemaBanco();

		// Criando algumas contas: CENTRAL(1001), REGIONAL(2002), LOCAL(3003), 
		//						  INTERNACIONAL(4004), DIGITAL(5005), PRIVADA(6006);
		sistema.adicionarConta(new ContaUsuario("user1", "pass1", TipoAgencia.CENTRAL));
		sistema.adicionarConta(new ContaUsuario("user2", "pass2", TipoAgencia.REGIONAL));
		sistema.adicionarConta(new ContaUsuario("user3", "pass3", TipoAgencia.LOCAL));

		while (true) {
			System.out.println("Digite o usuário: ");
			String usuario = scanner.nextLine();
			System.out.println("Digite a senha: ");
			String senha = scanner.nextLine();
			System.out.println("Digite o código da agência: ");
			int codigoAgencia = scanner.nextInt();
			scanner.nextLine(); // Consumir a quebra de linha

			if (sistema.autenticarUsuario(usuario, senha, codigoAgencia)) {
				System.out.println("Login bem-sucedido na agência " + codigoAgencia);
				break;
			} else {
				System.out.println("Falha no login. Tente novamente.");
			}
		}
		// Voutar para o MEUNU
		VoutarMenu voutarMenu = new VoutarMenu();
		voutarMenu.run_CaracteresMenu_Red();
	}
}

class Import_1WM {
	public static void run_Import_1WM() {
		System.err.println("\t\t1.\n");
		LoginAgenciaBanco.mainLAB();
	}
}

//Corpo da Classe _______________________________

//Enum de Perfis com comandos dentro
enum Perfilg {
	ADMIN {
		@Override
		public void comando() {
			System.out.println("Acesso total ao sistema.");
		}
	},
	MODERADOR {
		@Override
		public void comando() {
			System.out.println("Acesso para gerenciar usuários.");
		}
	},
	USUARIO {
		@Override
		public void comando() {
			System.out.println("Acesso limitado ao sistema.");
		}
	},
	VISITANTE {
		@Override
		public void comando() {
			System.out.println("Acesso apenas para visualização.");
		}
	};

	public abstract void comando();
}

//Classe abstrata para conexão e sincronização
abstract class BancoDeDados {
	protected synchronized void conectar() {
		System.out.println("Conectando ao banco de dados...");
	}

	protected synchronized void desconectar() {
		System.out.println("Desconectando do banco de dados...");
	}
}

//Classe Login
class Login extends BancoDeDados {
	private ArrayList<Usuario> usuarios = new ArrayList<>();

	public void adicionarUsuario(String nome, String senha, Perfilg perfil) {
		usuarios.add(new Usuario(nome, senha, perfil));
	}

	public boolean autenticar(String nome, String senha) {
		conectar();
		boolean autenticado = false;
		Iterator<Usuario> iterator = usuarios.iterator();

		while (iterator.hasNext()) {
			Usuario u = iterator.next();
			if (u.getNome().equals(nome) && u.getSenha().equals(senha)) {
				autenticado = true;
				System.out.println("Login bem-sucedido. Perfil: " + u.getPerfil());
				u.getPerfil().comando();
				break;
			}
		}

		if (!autenticado) {
			System.out.println("Usuário ou senha inválidos.");
		}

		desconectar();
		return autenticado;
	}
}

//Classe Usuario
class Usuario {
	private String nome;
	private String senha;
	private Perfilg perfil;

	public Usuario(String nome, String senha, Perfilg perfil) {
		this.nome = nome;
		this.senha = senha;
		this.perfil = perfil;
	}

	public String getNome() {
		return nome;
	}

	public String getSenha() {
		return senha;
	}

	public Perfilg getPerfil() {
		return perfil;
	}
}

//Classe principal
class SistemaLogin {
	public static void mainSL() {
		Scanner scanner = new Scanner(System.in);
		Login login = new Login();

		// Adicionando usuários
		login.adicionarUsuario("admin", "1234", Perfilg.ADMIN);
		login.adicionarUsuario("mod", "5678", Perfilg.MODERADOR);
		login.adicionarUsuario("user", "abcd", Perfilg.USUARIO);
		login.adicionarUsuario("guest", "0000", Perfilg.VISITANTE);

		// Loop de login
		while (true) {
			System.out.print("Digite o nome de usuário: ");
			String nome = scanner.next();
			System.out.print("Digite a senha: ");
			String senha = scanner.next();

			if (login.autenticar(nome, senha)) {
				break;
			}
		}
		// Voutar para o MEUNU
		VoutarMenu voutarMenu = new VoutarMenu();
		voutarMenu.run_CaracteresMenu_Red();
	}
}

class Import_2WM {
	public static void run_Import_2WM() {
		System.err.println("\t\t2.\n");
		SistemaLogin.mainSL();
	}
}

//Corpo da Classe _______________________________

class SqlHardJava {

	// 10 Enums com comandos SQL
	enum SQLCommand {
		SELECT("SELECT"), INSERT("INSERT INTO"), UPDATE("UPDATE"), DELETE("DELETE FROM"), CREATE_TABLE("CREATE TABLE"),
		ALTER_TABLE("ALTER TABLE"), DROP_TABLE("DROP TABLE"), JOIN("INNER JOIN"), WHERE("WHERE"), ORDER_BY("ORDER BY"),
		FROM("FROM");

		private String command;

		SQLCommand(String command) {
			this.command = command;
		}

		public String getCommand() {
			return command;
		}
	}

	// Classe abstrata para simulação de operações de SQL
	abstract static class SQLOperation {
		public abstract void execute();
	}

	// Implementação de operação SELECT
	static class SelectOperation extends SQLOperation {
		private String tableName;
		private List<String> columns;

		public SelectOperation(String tableName, List<String> columns) {
			this.tableName = tableName;
			this.columns = columns;
		}

		@Override
		public void execute() {
			StringBuilder sql = new StringBuilder(SQLCommand.SELECT.getCommand());
			if (columns.isEmpty()) {
				sql.append(" * ");
			} else {
				sql.append(String.join(", ", columns));
			}
			sql.append(" " + SQLCommand.FROM.getCommand() + " " + tableName);
			System.out.println(sql);
		}
	}

	// Implementação de operação INSERT
	static class InsertOperation extends SQLOperation {
		private String tableName;
		private List<String> values;

		public InsertOperation(String tableName, List<String> values) {
			this.tableName = tableName;
			this.values = values;
		}

		@Override
		public void execute() {
			StringBuilder sql = new StringBuilder(SQLCommand.INSERT.getCommand());
			sql.append(" " + tableName + " VALUES (" + String.join(", ", values) + ")");
			System.out.println(sql);
		}
	}

	// Classe que simula um sistema SQL com operações e sincronização
	static class SQLSystem {
		private List<SQLOperation> operations;

		public SQLSystem() {
			this.operations = new ArrayList<>();
		}

		// Método sincronizado para adicionar operações
		public synchronized void addOperation(SQLOperation operation) {
			operations.add(operation);
		}

		// Método para executar todas as operações em sequência
		public void executeOperations() {
			Iterator<SQLOperation> iterator = operations.iterator();
			while (iterator.hasNext()) {
				SQLOperation operation = iterator.next();
				operation.execute();
			}
		}

		// Método para realizar consulta com base em condições (usando if, else if,
		// else)
		public void conditionalQuery(String condition) {
			if ("SELECT".equalsIgnoreCase(condition)) {
				System.out.println("Executing SELECT query...");
				// Exemplo de SELECT
				List<String> columns = new ArrayList<>();
				columns.add("id");
				columns.add("name");
				SelectOperation select = new SelectOperation("users", columns);
				addOperation(select);
			} else if ("INSERT".equalsIgnoreCase(condition)) {
				System.out.println("Executing INSERT query...");
				// Exemplo de INSERT
				List<String> values = new ArrayList<>();
				values.add("1");
				values.add("'John Doe'");
				InsertOperation insert = new InsertOperation("users", values);
				addOperation(insert);
			} else {
				System.out.println("Unknown condition, no SQL operation executed.");
			}
		}

		// Método que simula execução de operações com laços e condições complexas
		public void executeComplexSQL() {
			List<String> tables = new ArrayList<>();
			tables.add("users");
			tables.add("orders");
			tables.add("products");

			// Uso de for e while
			for (String table : tables) {
				// Criação de diferentes operações com base na tabela
				if ("users".equals(table)) {
					List<String> columns = new ArrayList<>();
					columns.add("id");
					columns.add("name");
					SelectOperation select = new SelectOperation(table, columns);
					addOperation(select);
				} else {
					List<String> values = new ArrayList<>();
					values.add("2");
					values.add("'Jane Doe'");
					InsertOperation insert = new InsertOperation(table, values);
					addOperation(insert);
				}
			}

			// Simulação de execução com while
			int i = 0;
			while (i < operations.size()) {
				SQLOperation op = operations.get(i);
				op.execute();
				i++;
			}
		}
	}

	public static void mainW() {
		SQLSystem sqlSystem = new SQLSystem();

		// Executando consultas com condições
		sqlSystem.conditionalQuery("SELECT");
		sqlSystem.conditionalQuery("INSERT");

		// Adicionando operações manualmente
		List<String> values = new ArrayList<>();
		values.add("1");
		values.add("'New User'");
		InsertOperation insert = new InsertOperation("users", values);
		sqlSystem.addOperation(insert);

		// Executando todas as operações
		sqlSystem.executeOperations();

		// Executando operações complexas com loops e condicionais
		sqlSystem.executeComplexSQL();
	}
}

class Import_3WM {
	public static void run_Import_3WM() {
		System.err.println("\t\t3. \n");
		SqlHardJava.mainW();
	}
}