package UTIL_10_ListInterator;

import util.Linhas;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class For_Matrix {
	public static void For_Run() {

		Linhas linhas = new Linhas();
		String collec = "\n\t ListIterator<E>: Iterador para listas que permite percorrer em ambas as direções. \n";
		System.err.println(collec);

		linhas.run_Caracteres();
		Import_1F.run_Import_1F();

		linhas.run_Caracteres();
		Import_2F.run_Import_2F();

		linhas.run_Caracteres();
		Import_3F.run_Import_3F();

	}
}

//Corpo da Classe _______________________________
//Enum para tipos de conta
enum TipoConta1 {
	POUPANCA, CORRENTE, SALARIO;
}

//Enum para status de transação
enum StatusTransacao {
	SUCESSO, FALHA, PENDENTE;
}

//Enum para níveis de permissão
enum NivelPermissao {
	ADMIN, USUARIO, VISITANTE;
}

//Classe abstrata para Conta Bancária
abstract class ContaBancaria1 {
	protected String titular;
	protected double saldo;
	protected TipoConta1 tipo;
	protected final ReentrantLock lock = new ReentrantLock();

	public ContaBancaria1(String titular, double saldo, TipoConta1 tipo) {
		this.titular = titular;
		this.saldo = saldo;
		this.tipo = tipo;
	}

	public abstract void depositar(double valor);

	public abstract boolean sacar(double valor);

	public abstract double calcularPorcentagem(double taxa);
}

//Classe abstrata para manipulação de usuários
abstract class UsuarioRede {
	protected String enderecoIPV6;
	protected NivelPermissao permissao;

	public UsuarioRede(String enderecoIPV6, NivelPermissao permissao) {
		this.enderecoIPV6 = enderecoIPV6;
		this.permissao = permissao;
	}

	public abstract boolean autenticar();
}

//Classe abstrata para sincronização
abstract class Sincronizacao1 {
	protected abstract void sincronizarDados();
}

//Classe concreta de conta bancária
class Conta1 extends ContaBancaria1 {
	public Conta1(String titular, double saldo, TipoConta1 tipo) {
		super(titular, saldo, tipo);
	}

	@Override
	public void depositar(double valor) {
		lock.lock();
		try {
			saldo += valor;
			System.out.println("Depósito de R$" + valor + " realizado com sucesso.");
		} finally {
			lock.unlock();
		}
	}

	@Override
	public boolean sacar(double valor) {
		lock.lock();
		try {
			if (saldo >= valor) {
				saldo -= valor;
				System.out.println("Saque de R$" + valor + " realizado com sucesso.");
				return true;
			} else {
				System.out.println("Saldo insuficiente!");
				return false;
			}
		} finally {
			lock.unlock();
		}
	}

	@Override
	public double calcularPorcentagem(double taxa) {
		return saldo * (taxa / 100);
	}
}

//Classe concreta de usuário de rede
class Usuario1 extends UsuarioRede {
	public Usuario1(String enderecoIPV6, NivelPermissao permissao) {
		super(enderecoIPV6, permissao);
	}

	@Override
	public boolean autenticar() {
		return permissao != NivelPermissao.VISITANTE;
	}
}

//Classe concreta de sincronização
class ServicoSincronizacao extends Sincronizacao1 {
	private List<Conta1> contas;

	public ServicoSincronizacao(List<Conta1> contas) {
		this.contas = contas;
	}

	@Override
	protected void sincronizarDados() {
		System.out.println("Sincronizando contas...");
		for (Conta1 conta : contas) {
			System.out.println("Conta de " + conta.titular + " sincronizada.");
		}
	}
}

//Classe principal para testar
class Banco {
	public static void main_Banco() {
		List<Conta1> contas = new ArrayList<>();
		contas.add(new Conta1("Alice", 5000, TipoConta1.CORRENTE));
		contas.add(new Conta1("Bob", 3000, TipoConta1.POUPANCA));

		ServicoSincronizacao sincronizacao = new ServicoSincronizacao(contas);
		sincronizacao.sincronizarDados();

		ListIterator<Conta1> iterator = contas.listIterator();
		while (iterator.hasNext()) {
			Conta1 conta = iterator.next();
			double juros = conta.calcularPorcentagem(5);
			System.out.println("Juros de 5% para " + conta.titular + " = R$" + juros);
		}
	}
}

class Import_1F {
	public static void run_Import_1F() {
		System.err.println("\t\t1. Porcentagem com taxa de juros\n");
		Banco.main_Banco();
	}
}

//Corpo da Classe _______________________________
//Enum complexo para diferentes categorias de produtos
enum CategoriaProduto1 {
	ELETRONICO(0.15), ALIMENTO(0.05), ROUPA(0.10);

	private final double taxa;

	CategoriaProduto1(double taxa) {
		this.taxa = taxa;
	}

	public double getTaxa() {
		return taxa;
	}
}

//Enum complexo para status do produto
enum StatusProduto {
	DISPONIVEL, ESGOTADO, SOB_ENCOMENDA;
}

//Enum para tipos de desconto
enum TipoDesconto {
	NENHUM(0), PROMOCAO(0.10), CUPOM(0.20);

	private final double desconto;

	TipoDesconto(double desconto) {
		this.desconto = desconto;
	}

	public double getDesconto() {
		return desconto;
	}
}

//Classe abstrata base para produtos
abstract class Produto1 {
	protected String nome;
	protected double preco;
	protected CategoriaProduto1 categoria;
	protected StatusProduto status;

	public Produto1(String nome, double preco, CategoriaProduto1 categoria, StatusProduto status) {
		this.nome = nome;
		this.preco = preco;
		this.categoria = categoria;
		this.status = status;
	}

	public abstract double calcularPrecoFinal(TipoDesconto desconto);
}

//Classe abstrata com funcionalidade de sincronização de rede IPv6
abstract class RedeSincronizacao {
	public synchronized void sincronizarUsuario() {
		try {
			Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
			while (interfaces.hasMoreElements()) {
				NetworkInterface ni = interfaces.nextElement();
				Enumeration<InetAddress> addresses = ni.getInetAddresses();
				while (addresses.hasMoreElements()) {
					InetAddress addr = addresses.nextElement();
					System.out.println("Sincronizando com usuário IP: " + addr.getHostAddress());
				}
			}
		} catch (SocketException e) {
			System.out.println("Erro ao sincronizar: " + e.getMessage());
		}
	}
}

//Implementação de um produto físico
class ProdutoFisico extends Produto1 {
	public ProdutoFisico(String nome, double preco, CategoriaProduto1 categoria, StatusProduto status) {
		super(nome, preco, categoria, status);
	}

	@Override
	public double calcularPrecoFinal(TipoDesconto desconto) {
		return preco - (preco * desconto.getDesconto()) + (preco * categoria.getTaxa());
	}
}

class GerenciadorProdutos extends RedeSincronizacao {
	private List<Produto1> produtos = new ArrayList<>();

	public void adicionarProduto(Produto1 produto) {
		produtos.add(produto);
	}

	public void listarProdutos() {
		ListIterator<Produto1> it = produtos.listIterator();
		while (it.hasNext()) {
			Produto1 p = it.next();
			System.out.println("Produto: " + p.nome + ", Preço Final: " + p.calcularPrecoFinal(TipoDesconto.PROMOCAO));
		}
	}

	public static void main_Gerador() {
		GerenciadorProdutos gerenciador = new GerenciadorProdutos();

		// Matriz de produtos
		Produto1[][] estoque = new Produto1[2][2];
		estoque[0][0] = new ProdutoFisico("Laptop", 3000, CategoriaProduto1.ELETRONICO, StatusProduto.DISPONIVEL);
		estoque[0][1] = new ProdutoFisico("Camisa", 100, CategoriaProduto1.ROUPA, StatusProduto.DISPONIVEL);
		estoque[1][0] = new ProdutoFisico("Chocolate", 20, CategoriaProduto1.ALIMENTO, StatusProduto.ESGOTADO);
		estoque[1][1] = new ProdutoFisico("Smartphone", 2000, CategoriaProduto1.ELETRONICO,
				StatusProduto.SOB_ENCOMENDA);

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				gerenciador.adicionarProduto(estoque[i][j]);
			}
		}
		gerenciador.listarProdutos();
		// gerenciador.sincronizarUsuario();
	}
}

class Import_2F {
	public static void run_Import_2F() {
		System.err.println("\t\t2. \n");
		GerenciadorProdutos.main_Gerador();
	}
}

//Corpo da Classe _______________________________

//Enums complexos representando diferentes aspectos do Funcionário de Obra
enum Funcao {
	ENGENHEIRO, MESTRE_DE_OBRAS, PEDREIRO, SERVENTE, ELETRICISTA;
}

enum NivelAcesso {
	ADMINISTRADOR, GERENTE, OPERACIONAL, VISITANTE;
}

enum StatusFuncionario {
	ATIVO, FERIAS, DEMITIDO, LICENCA;
}

enum Turno {
	MATUTINO, VESPERTINO, NOTURNO, PLANTAO;
}

enum Permissao {
	ACESSO_TOTAL, RESTRITO, CONSULTA, BLOQUEADO;
}

//Classe abstrata de base
abstract class Pessoa {
	String nome;
	int idade;
	String cpf;

	public Pessoa(String nome, int idade, String cpf) {
		this.nome = nome;
		this.idade = idade;
		this.cpf = cpf;
	}

	abstract void exibirDetalhes();
}

//Classe abstrata para Funcionários
abstract class Funcionario extends Pessoa {
	Funcao funcao;
	StatusFuncionario status;
	Turno turno;

	public Funcionario(String nome, int idade, String cpf, Funcao funcao, StatusFuncionario status, Turno turno) {
		super(nome, idade, cpf);
		this.funcao = funcao;
		this.status = status;
		this.turno = turno;
	}

	abstract void alterarStatus(StatusFuncionario novoStatus);
}

//Classe abstrata para Controle de Acesso
abstract class ControleAcesso {
	synchronized void autenticarUsuario(String cpf, NivelAcesso nivel) {
		System.out.println("Autenticando usuário com CPF: " + cpf + " e nível: " + nivel);
	}
}

//Classe concreta FuncionárioObra
class FuncionarioObra extends Funcionario {
	private Permissao permissao;

	public FuncionarioObra(String nome, int idade, String cpf, Funcao funcao, StatusFuncionario status, Turno turno,
			Permissao permissao) {
		super(nome, idade, cpf, funcao, status, turno);
		this.permissao = permissao;
	}

	@Override
	void exibirDetalhes() {
		System.out
				.println("Funcionário: " + nome + ", Função: " + funcao + ", Status: " + status + ", Turno: " + turno);
	}

	@Override
	void alterarStatus(StatusFuncionario novoStatus) {
		synchronized (this) {
			System.out.println("Alterando status de " + nome + " para " + novoStatus);
			this.status = novoStatus;
		}
	}
}

class Funcionarios_Obra {
	public static void main_Obra() {
		List<FuncionarioObra> funcionarios = new ArrayList<>();
		funcionarios.add(new FuncionarioObra("Carlos", 45, "12345678900", Funcao.ENGENHEIRO, StatusFuncionario.ATIVO,
				Turno.MATUTINO, Permissao.ACESSO_TOTAL));
		funcionarios.add(new FuncionarioObra("João ", 39, "98765432100", Funcao.PEDREIRO, StatusFuncionario.ATIVO,
				Turno.VESPERTINO, Permissao.RESTRITO));
		funcionarios.add(new FuncionarioObra("Ana  ", 34, "56789123400", Funcao.ELETRICISTA, StatusFuncionario.FERIAS,
				Turno.NOTURNO, Permissao.CONSULTA));
		funcionarios.add(new FuncionarioObra("Lucas", 24, "95782823400", Funcao.SERVENTE, StatusFuncionario.LICENCA,
				Turno.PLANTAO, Permissao.RESTRITO));

		System.out.println("Lista de Funcionários:");
		for (FuncionarioObra f : funcionarios) {
			f.exibirDetalhes();
		}

		System.out.println("\nUtilizando ListIterator para navegação:");
		ListIterator<FuncionarioObra> iterator = funcionarios.listIterator();
		while (iterator.hasNext()) {
			FuncionarioObra f = iterator.next();
			System.out.println("Navegando: " + f.nome +"\t  | Status:"+f.status);
		}
	}
}

class Import_3F {
	public static void run_Import_3F() {
		System.err.println("\t\t3. Obra \n");
		Funcionarios_Obra.main_Obra();
	}
}

//1 Corpo da Classe Abstract e Enum _______________________________

//2 Corpo da Classe Abstract e Enum _______________________________

//3 Corpo da Classe Abstract e Enum _______________________________
