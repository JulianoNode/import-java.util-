package UTIL_11_ArrayList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import util.Linhas;

public class For_Matrix {
	public static void For_Run() {

		Linhas linhas = new Linhas();
		String collec = "\n\t Nome \n";
		System.err.println(collec);

		linhas.run_Caracteres();
		Import_1F.run_Import_1F();

		linhas.run_Caracteres();
		Import_1Fs.run_Import_1Fs();

		linhas.run_Caracteres();
		Import_1Ff.run_Import_1Ff();

		linhas.run_Caracteres();
		Import_2F.run_Import_2F();

		linhas.run_Caracteres();
		Import_3F.run_Import_3F();

	}
}
//Corpo da Classe _______________________________

//Enum representando os comandos SQL principais
enum SQLOperation {
	SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, ALTER, JOIN, WHERE, GROUP_BY, ORDER_BY;
}

//Classe abstrata representando uma entidade SQL
abstract class SQLEntity {
	String tableName;

	abstract void execute();
}

//Implementação concreta da tabela
class SQLTable extends SQLEntity {
	private List<String[]> rows = new ArrayList<>();

	public SQLTable(String tableName) {
		this.tableName = tableName;
	}

	synchronized void insert(String[] data) {
		rows.add(data);
		System.out.println("INSERT INTO " + tableName + " VALUES (" + String.join(", ", data) + ")");
	}

	synchronized void select() {
		System.out.println("SELECT * FROM " + tableName);
		for (String[] row : rows) {
			System.out.println(String.join(", ", row));
		}
	}

	synchronized void update(int index, String[] newData) {
		if (index >= 0 && index < rows.size()) {
			rows.set(index, newData);
			System.out.println(
					"UPDATE " + tableName + " SET VALUE = " + String.join(", ", newData) + " WHERE ID = " + index);
		} else {
			System.out.println("Registro não encontrado!");
		}
	}

	synchronized void delete(int index) {
		if (index >= 0 && index < rows.size()) {
			rows.remove(index);
			System.out.println("DELETE FROM " + tableName + " WHERE ID = " + index);
		} else {
			System.out.println("Registro não encontrado!");
		}
	}

	synchronized void innerJoin(SQLTable otherTable, int thisJoinIndex, int otherJoinIndex) {
		System.out.println("INNER JOIN entre " + this.tableName + " e " + otherTable.tableName);
		for (String[] row1 : rows) {
			for (String[] row2 : otherTable.rows) {
				if (row1[thisJoinIndex].equals(row2[otherJoinIndex])) {
					System.out.println(String.join(", ", row1) + " | " + String.join(", ", row2));
				}
			}
		}
	}

	@Override
	void execute() {
		System.out.println("Executando operações em " + tableName);
	}
}

class SQLDatabaseS {
	public static void mainS() {
		SQLTable usersTable = new SQLTable("Users");
		SQLTable ordersTable = new SQLTable("Orders");

		usersTable.insert(new String[] { "1", "John", "Doe", "30" });
		usersTable.insert(new String[] { "2", "Jane", "Doe", "25" });
		usersTable.insert(new String[] { "3", "Mike", "Smith", "40" });

		ordersTable.insert(new String[] { "101", "1", "Laptop", "1200" });
		ordersTable.insert(new String[] { "102", "2", "Phone", "800" });
		ordersTable.insert(new String[] { "103", "3", "Tablet", "600" });

		usersTable.select();
		ordersTable.select();

		usersTable.innerJoin(ordersTable, 0, 1);
	}
}

class Import_1Fs {
	public static void run_Import_1Fs() {
		System.err.println("\t\t1. \n");
		SQLDatabaseS.mainS();
	}
}

//Corpo da Classe _____________________________
//Enum representando os comandos SQL principais
enum SQLOperationf {
	SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, ALTER, JOIN, WHERE, GROUP_BY, ORDER_BY;
}

//Classe abstrata representando uma entidade SQL
abstract class SQLEntityf {
	String tableName;

	abstract void execute();
}

//Implementação concreta da tabela
class SQLTablef extends SQLEntityf {
	private List<String> rows = new ArrayList<>();

	public SQLTablef(String tableName) {
		this.tableName = tableName;
	}

	synchronized void insert(String data) {
		rows.add(data);
		System.out.println("INSERT INTO " + tableName + " VALUES (" + data + ")");
	}

	synchronized void select() {
		System.out.println("SELECT * FROM " + tableName);
		for (String row : rows) {
			System.out.println(row);
		}
	}

	synchronized void update(int index, String newData) {
		if (index >= 0 && index < rows.size()) {
			rows.set(index, newData);
			System.out.println("UPDATE " + tableName + " SET VALUE = " + newData + " WHERE ID = " + index);
		} else {
			System.out.println("Registro não encontrado!");
		}
	}

	synchronized void delete(int index) {
		if (index >= 0 && index < rows.size()) {
			rows.remove(index);
			System.out.println("DELETE FROM " + tableName + " WHERE ID = " + index);
		} else {
			System.out.println("Registro não encontrado!");
		}
	}

	@Override
	void execute() {
		System.out.println("Executando operações em " + tableName);
	}
}

class SQLDatabasef {
	public static void mainf() {
		SQLTablef usersTable = new SQLTablef("Users");

		usersTable.insert("('John', 'Doe', 30)");
		usersTable.insert("('Jane', 'Doe', 25)");
		usersTable.insert("('Mike', 'Smith', 40)");

		usersTable.select();

		usersTable.update(1, "('Jane', 'Doe', 26)");

		usersTable.delete(2);

		usersTable.select();

		SQLOperation operation = SQLOperation.SELECT;

		if (operation == SQLOperation.SELECT) {
			System.out.println("Executando um SELECT");
		} else if (operation == SQLOperation.INSERT) {
			System.out.println("Executando um INSERT");
		} else if (operation == SQLOperation.UPDATE) {
			System.out.println("Executando um UPDATE");
		} else {
			System.out.println("Operação SQL não suportada");
		}

		// Testando todas as operações SQL
		for (SQLOperation op : SQLOperation.values()) {
			System.out.println("Operação SQL: " + op);
		}
	}
}

class Import_1Ff {
	public static void run_Import_1Ff() {
		System.err.println("\t\t1. \n");
		SQLDatabasef.mainf();
	}
}

//Corpo da Classe _______________________________
// Enum representando os tipos de comandos SQL
enum SqlCommand {
	SELECT, INSERT, UPDATE, DELETE, CREATE_TABLE
}

// Enum representando os estados de uma consulta
enum QueryState {
	PENDING, EXECUTING, SUCCESS, FAILED, CANCELLED
}

// Enum representando os tipos de dados suportados
enum DataType {
	STRING, INTEGER, FLOAT, BOOLEAN, DATE
}

// Enum representando os níveis de acesso
enum AccessLevel {
	ADMIN, USER, GUEST, SUPER_ADMIN, READ_ONLY
}

// Enum representando os status da conexão ao banco
enum ConnectionStatus {
	CONNECTED, DISCONNECTED, ERROR, TIMEOUT, RECONNECTING
}

// Classe abstrata para consultas SQL
abstract class SqlQuery {
	protected String query;
	protected QueryState state;

	public SqlQuery(String query) {
		this.query = query;
		this.state = QueryState.PENDING;
	}

	public abstract void execute();
}

// Classe abstrata para operações de sincronização
abstract class SyncOperation {
	public synchronized void logOperation(String message) {
		System.err.println("[SYNC LOG]: " + message);
	}
}

// Classe abstrata para representar uma conexão com banco de dados
abstract class DatabaseConnection {
	protected ConnectionStatus status;

	public abstract void connect();

	public abstract void disconnect();
}

// Classe abstrata para representar manipulação de tabelas
abstract class TableManager {
	protected String tableName;
	protected ArrayList<String> columns;

	public TableManager(String tableName) {
		this.tableName = tableName;
		this.columns = new ArrayList<>();
	}

	public abstract void createTable();

	public abstract void dropTable();
}

// Classe abstrata para gerenciamento de transações
abstract class TransactionManager {
	public abstract void beginTransaction();

	public abstract void commitTransaction();

	public abstract void rollbackTransaction();
}

// Implementação concreta de uma consulta SQL
class SqlExecution extends SqlQuery {
	public SqlExecution(String query) {
		super(query);

	}

	@Override
	public synchronized void execute() {
		System.out.println("Executando: " + query);
		this.state = QueryState.EXECUTING;
		String message = "Ok";
		try {
			Thread.sleep(1000); // Simula tempo de execução
			this.state = QueryState.SUCCESS;
			System.out.println("Consulta executada com sucesso.");
			System.err.println("[SYNC LOG]: " + message);
		} catch (InterruptedException e) {
			this.state = QueryState.FAILED;
			System.out.println("Erro na execução da consulta.");
		}
	}
}

// Implementação concreta de uma conexão
class SqlConnection extends DatabaseConnection {
	@Override
	public synchronized void connect() {
		System.out.println("Conectando ao banco de dados...");
		this.status = ConnectionStatus.CONNECTED;
	}

	@Override
	public synchronized void disconnect() {
		System.out.println("Desconectando do banco de dados...");
		this.status = ConnectionStatus.DISCONNECTED;
	}
}

class MainSQL {
	public static void main_SQL() {
		ArrayList<SqlQuery> queries = new ArrayList<>();
		queries.add(new SqlExecution("SELECT * FROM users"));
		queries.add(new SqlExecution("INSERT INTO users (name, age) VALUES ('Alice', 25)"));
		queries.add(new SqlExecution("UPDATE users SET age = 26 WHERE name = 'Alice'"));
		queries.add(new SqlExecution("DELETE FROM users WHERE name = 'Alice'"));
		queries.add(new SqlExecution("CREATE TABLE products (id INT, name VARCHAR(50))"));

		for (SqlQuery query : queries) {
			query.execute();
		}
	}
}

class Import_1F {
	public static void run_Import_1F() {
		System.err.println("\t\t1. \n");
		MainSQL.main_SQL();
	}
}

//Corpo da Classe _______________________________
// Enums para diferentes aspectos do jogo
enum TipoTigrinho {
	NORMAL, RARO, LENDARIO, MITICO, DIVINO;
}

enum TipoRodada {
	GRATIS, PAGA, BONUS, JACKPOT, MEGA_JACKPOT;
}

enum TipoPremio {
	MOEDAS, GIROS, ITENS_RAROS, XP, NENHUM;
}

enum EstadoJogo {
	ATIVO, PAUSADO, FINALIZADO, EM_MANUTENCAO, INDISPONIVEL;
}

enum NivelDificuldade {
	FACIL, MEDIO, DIFICIL, EXPERIENTE, IMPOSSIVEL;
}

// Classe abstrata para Tigrinhos
abstract class Tigrinho {
	protected String nome;
	protected TipoTigrinho tipo;

	public Tigrinho(String nome, TipoTigrinho tipo) {
		this.nome = nome;
		this.tipo = tipo;
	}

	public abstract void exibirInfo();
}

// Classe abstrata para Rodadas
abstract class Rodada {
	protected TipoRodada tipoRodada;
	protected int numeroRodadas;

	public Rodada(TipoRodada tipoRodada, int numeroRodadas) {
		this.tipoRodada = tipoRodada;
		this.numeroRodadas = numeroRodadas;
	}

	public abstract void iniciarRodada();
}

// Classe abstrata para Premios
abstract class Premio {
	protected TipoPremio tipoPremio;
	protected int valor;

	public Premio(TipoPremio tipoPremio, int valor) {
		this.tipoPremio = tipoPremio;
		this.valor = valor;
	}

	public abstract void exibirPremio();
}

// Classe abstrata para Estado do Jogo
abstract class Estad {
	protected EstadoJogo estado;

	public Estad(EstadoJogo estado) {
		this.estado = estado;
	}

	public abstract void alterarEstado(EstadoJogo novoEstado);
}

// Classe abstrata para Dificuldade do Jogo
abstract class Dificuldade {
	protected NivelDificuldade nivel;

	public Dificuldade(NivelDificuldade nivel) {
		this.nivel = nivel;
	}

	public abstract void ajustarDificuldade();
}

// Implementação do CRUD do Jogo do Tigrinho
class JogoTigrinho {
	private final ArrayList<Tigrinho> listaTigrinhos = new ArrayList<>();

	public synchronized void adicionarTigrinho(Tigrinho tigrinho) {
		listaTigrinhos.add(tigrinho);
		System.out.println("Tigrinho adicionado: " + tigrinho.nome);
	}

	public synchronized void listarTigrinhos() {
		if (listaTigrinhos.isEmpty()) {
			System.out.println("Nenhum Tigrinho cadastrado.");
			return;
		}
		System.out.println("Lista de Tigrinhos:");
		for (Tigrinho t : listaTigrinhos) {
			t.exibirInfo();
		}
	}

	public synchronized void removerTigrinho(String nome) {
		Iterator<Tigrinho> iterator = listaTigrinhos.iterator();
		while (iterator.hasNext()) {
			Tigrinho t = iterator.next();
			if (t.nome.equalsIgnoreCase(nome)) {
				iterator.remove();
				System.out.println("Tigrinho removido: " + nome);
				return;
			}
		}
		System.out.println("Tigrinho não encontrado.");
	}
}

// Implementação de um Tigrinho específico
class TigrinhoNormal extends Tigrinho {
	public TigrinhoNormal(String nome) {
		super(nome, TipoTigrinho.NORMAL);
	}

	@Override
	public void exibirInfo() {
		System.out.println("Tigrinho: " + nome + " | Tipo: " + tipo);
	}
}

// Teste do CRUD
class MainTi {
	public static void main_Ti() {
		JogoTigrinho jogo = new JogoTigrinho();

		Tigrinho t1 = new TigrinhoNormal("Tigrinho Feliz");
		Tigrinho t2 = new TigrinhoNormal("Tigrinho Sortudo");

		jogo.adicionarTigrinho(t1);
		jogo.adicionarTigrinho(t2);

		jogo.listarTigrinhos();

		jogo.removerTigrinho("Tigrinho Feliz");

		jogo.listarTigrinhos();
	}
}

class Import_2F {
	public static void run_Import_2F() {
		System.err.println("\t\t2. \n");
		MainTi.main_Ti();
	}
}

//Corpo da Classe _______________________________

enum TipoCartao {
	BASIC, GOLD, PLATINUM;
}

enum StatusCartao {
	ATIVO, BLOQUEADO, CANCELADO;
}

enum TipoOperacao {
	CREDITO, DEBITO;
}

abstract class CartaoCredito {
	protected String numero;
	protected double limite;
	protected StatusCartao status;
	protected TipoCartao tipo;

	public CartaoCredito(String numero, double limite, TipoCartao tipo) {
		this.numero = numero;
		this.limite = limite;
		this.tipo = tipo;
		this.status = StatusCartao.ATIVO;
	}

	public abstract void realizarOperacao(double valor, TipoOperacao operacao);
}

class GerenciadorCartao {
	private final List<CartaoCredito> cartoes = new ArrayList<>();

	public synchronized void adicionarCartao(CartaoCredito cartao) {
		cartoes.add(cartao);
	}

	public synchronized void listarCartoes() {
		for (CartaoCredito cartao : cartoes) {
			System.out.println("Número: " + cartao.numero + ", Tipo: " + cartao.tipo + ", Status: " + cartao.status);
		}
	}

	public synchronized void atualizarStatus(String numero, StatusCartao novoStatus) {
		for (CartaoCredito cartao : cartoes) {
			if (cartao.numero.equals(numero)) {
				cartao.status = novoStatus;
				System.out.println("Status do cartão " + numero + " atualizado para " + novoStatus);
				return;
			}
		}
		System.out.println("Cartão não encontrado!");
	}

	public synchronized void removerCartao(String numero) {
		Iterator<CartaoCredito> iterator = cartoes.iterator();
		while (iterator.hasNext()) {
			CartaoCredito cartao = iterator.next();
			if (cartao.numero.equals(numero)) {
				iterator.remove();
				System.out.println("Cartão " + numero + " removido.");
				return;
			}
		}
		System.out.println("Cartão não encontrado!");
	}
}

class MainCar {
	public static void mainCar() {
		GerenciadorCartao gerenciador = new GerenciadorCartao();

		CartaoCredito cartao1 = new CartaoCredito("1234-5678-9012-3456", 5000, TipoCartao.GOLD) {
			@Override
			public void realizarOperacao(double valor, TipoOperacao operacao) {
				if (operacao == TipoOperacao.CREDITO) {
					limite += valor;
				} else if (operacao == TipoOperacao.DEBITO && valor <= limite) {
					limite -= valor;
				} else {
					System.out.println("Operação inválida.");
				}
			}
		};

		CartaoCredito cartao2 = new CartaoCredito("9876-5432-1098-7654", 10000, TipoCartao.PLATINUM) {
			@Override
			public void realizarOperacao(double valor, TipoOperacao operacao) {
				if (operacao == TipoOperacao.CREDITO) {
					limite += valor;
				} else if (operacao == TipoOperacao.DEBITO && valor <= limite) {
					limite -= valor;
				} else {
					System.out.println("Operação inválida.");
				}
			}
		};

		gerenciador.adicionarCartao(cartao1);
		gerenciador.adicionarCartao(cartao2);
		gerenciador.listarCartoes();
		gerenciador.atualizarStatus("1234-5678-9012-3456", StatusCartao.BLOQUEADO);
		gerenciador.removerCartao("9876-5432-1098-7654");
	}
}

class Import_3F {
	public static void run_Import_3F() {
		System.err.println("\t\t3. Numero de cartao de credito\n");

		MainCar.mainCar();

	}
}
