package UTIL_10_ListInterator;

import util.Linhas;

import java.net.InetAddress;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class If_Else_IF {
	public static void If_Else_IF_Run() {

		Linhas linhas = new Linhas();
		String collec = "\n\t ListIterator<E>: Iterador para listas que permite percorrer em ambas as direções. \n";
		System.err.println(collec);

		linhas.run_Caracteres();
		Import_1IF.run_Import_1IF();

		linhas.run_Caracteres();
		Import_2IF.run_Import_2IF();

		linhas.run_Caracteres();
		Import_3IF.run_Import_3IF();
	}
}

//Corpo da Classe _______________________________

//Enums representando diferentes categorias de produto, status de carrinho e métodos de pagamento
enum CategoriaProduto {
	ELETRONICO, ROUPA, ALIMENTO;
}

enum StatusCarrinho {
	VAZIO, COM_ITENS, FINALIZADO;
}

enum MetodoPagamento {
	CREDITO, DEBITO, BOLETO;
}

//Interface para sincronização
@interface Sincronizacao {
	String descricao();
}

//Interface para validação
@interface Validacao {
	String tipo();
}

//Interface para segurança
@interface Seguranca {
	String nivel();
}

//Classe abstrata representando um produto
abstract class Produto {
	protected String nome;
	protected double preco;
	protected int quantidade;
	protected CategoriaProduto categoria;

	public Produto(String nome, double preco, int quantidade, CategoriaProduto categoria) {
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
		this.categoria = categoria;
	}

	public abstract double calcularValorTotal();
}

//Classe concreta representando um produto específico
class Eletronico extends Produto {
	public Eletronico(String nome, double preco, int quantidade) {
		super(nome, preco, quantidade, CategoriaProduto.ELETRONICO);
	}

	@Override
	public double calcularValorTotal() {
		return this.preco * this.quantidade;
	}

	@Override
	public String toString() {
		return "Eletronico [Nome=" + nome + ", Preco=" + preco + ", Quantidade=" + quantidade + ", Categoria="
				+ categoria + ", Total()=" + calcularValorTotal() + "]\n";
	}

}

//Classe abstrata representando um carrinho de compras
abstract class Carrinho {
	protected List<Produto> produtos = new ArrayList<>();
	protected StatusCarrinho status = StatusCarrinho.VAZIO;
	protected final ReentrantLock lock = new ReentrantLock();

	public abstract void adicionarProduto(Produto produto);

	public abstract void removerProduto(String nome);

	public abstract double calcularTotal();
}

//Classe concreta de carrinho
class CarrinhoCompras extends Carrinho {

	@Sincronizacao(descricao = "Sincronização de adição de produto no carrinho")
	@Override
	public synchronized void adicionarProduto(Produto produto) {
		lock.lock();
		try {
			produtos.add(produto);
			status = StatusCarrinho.COM_ITENS;
		} finally {
			lock.unlock();
		}
	}

	@Override
	public void removerProduto(String nome) {
		ListIterator<Produto> iterator = produtos.listIterator();
		while (iterator.hasNext()) {
			Produto produto = iterator.next();
			if (produto.nome.equals(nome)) {
				iterator.remove();
				break;
			}
		}
		if (produtos.isEmpty()) {
			status = StatusCarrinho.VAZIO;
		}
	}

	@Override
	public double calcularTotal() {
		double total = 0;
		for (Produto produto : produtos) {
			total += produto.calcularValorTotal();
		}
		return total;
	}
}

//Classe abstrata de pagamento
abstract class Pagamento {
	protected MetodoPagamento metodo;

	public Pagamento(MetodoPagamento metodo) {
		this.metodo = metodo;
	}

	public abstract boolean processarPagamento(double valor);
}

//Classe concreta de pagamento por cartão
class PagamentoCartao extends Pagamento {
	public PagamentoCartao(MetodoPagamento metodo) {
		super(metodo);
	}

	@Override
	public boolean processarPagamento(double valor) {
		if (valor > 0) {
			System.out.println("Pagamento de R$" + valor + " realizado via " + metodo);
			return true;
		}
		return false;
	}
}

class LojaOnline {
	public static void main_Loja() {
		CarrinhoCompras carrinho = new CarrinhoCompras();
		Produto celular = new Eletronico("Smartphone", 70.0, 7);
		Produto notebook = new Eletronico("Notebook", 30.0, 3);
		Produto fone = new Eletronico("Smartphone", 40.0, 4);
		Produto livro = new Eletronico("Notebook", 20.0, 2);
		Produto tv = new Eletronico("Smartphone", 10.0, 3);
		Produto teclado = new Eletronico("Notebook", 30.0, 3);

		System.out.println("Total do carrinho: R$" + celular);
		System.out.println("Total do carrinho: R$" + notebook);
		System.out.println("Total do carrinho: R$" + fone);
		System.out.println("Total do carrinho: R$" + livro);
		System.out.println("Total do carrinho: R$" + tv);
		System.out.println("Total do carrinho: R$" + teclado);

		carrinho.adicionarProduto(celular);
		carrinho.adicionarProduto(notebook);
		carrinho.adicionarProduto(fone);
		carrinho.adicionarProduto(livro);
		carrinho.adicionarProduto(tv);
		carrinho.adicionarProduto(teclado);

		System.out.println("Total do carrinho: R$" + carrinho.calcularTotal());

		PagamentoCartao pagamento = new PagamentoCartao(MetodoPagamento.CREDITO);
		pagamento.processarPagamento(carrinho.calcularTotal());
	}
}

class Import_1IF {
	public static void run_Import_1IF() {
		System.err.println("\t\t1. \n");
		LojaOnline.main_Loja();
	}
}

//Corpo da Classe _______________________________

//Enums para representar estados quânticos, tipos de contas e status do carrinho
enum EstadoQuantico {
	SUPERPOSICAO, ENTRELAÇAMENTO, COLAPSO
}

enum TipoConta {
	CORRENTE, POUPANCA, INVESTIMENTO
}

enum StatusCarrinho2 {
	VAZIO, PREENCHIDO, FINALIZADO
}

//Interfaces para definições complexas
@interface Sincronizacao2 {
}

@interface Transacao {
}

@interface Quantica {
}

//Classe abstrata para contas bancárias abstratas
abstract class ContaBancaria {
	protected String titular;
	protected double saldo;
	protected TipoConta tipo;

	public ContaBancaria(String titular, double saldo, TipoConta tipo) {
		this.titular = titular;
		this.saldo = saldo;
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "ContaBancaria Titular da Conta = " + titular + ", Saldo = " + saldo + ", Tipo = " + tipo + "\n";
	}

	public abstract void sincronizar();

	public abstract void transacionar(double valor);
}

//Classe abstrata para mecânica quântica aplicada
abstract class MecanicaQuantica {
	protected EstadoQuantico estado;

	public MecanicaQuantica(EstadoQuantico estado) {
		this.estado = estado;
	}

	public abstract void colapsar();
}

//Classe abstrata para um carrinho de compras
abstract class Car {
	protected List<String> produtos = new ArrayList<>();
	protected StatusCarrinho2 status;

	public Car() {
		this.status = StatusCarrinho2.VAZIO;
	}

	public abstract void adicionarProduto(String produto);

	public abstract void finalizarCompra();
}

//Implementação concreta de uma Conta Bancária
class ContaUsuario extends ContaBancaria {
	public ContaUsuario(String titular, double saldo, TipoConta tipo) {
		super(titular, saldo, tipo);
	}

	@Override
	@Sincronizacao2
	public synchronized void sincronizar() {
		try {
			InetAddress ip = InetAddress.getLocalHost();
			System.out.println("Sincronizando conta de " + titular + " no IP: " + ip.getHostAddress());
		} catch (Exception e) {
			System.out.println("Erro na sincronização!");
		}
	}

	@Override
	@Transacao
	public synchronized void transacionar(double valor) {
		if (valor > 0) {
			saldo += valor;
			System.out.println("Depósito de " + valor + " realizado. Novo saldo: " + saldo);
		} else if (valor < 0 && saldo >= Math.abs(valor)) {
			saldo += valor;
			System.out.println("Saque de " + Math.abs(valor) + " realizado. Novo saldo: " + saldo);
		} else {
			System.err.println("Saldo insuficiente!");
		}
	}
}

//Implementação concreta de um sistema quântico
class ExperimentoQuantico extends MecanicaQuantica {
	public ExperimentoQuantico(EstadoQuantico estado) {
		super(estado);
	}

	@Override
	@Quantica
	public void colapsar() {
		System.out.println("Colapsando estado quântico: " + estado);
		estado = EstadoQuantico.COLAPSO;
	}
}

//Implementação concreta de um carrinho de compras
class CarrinhoCompra extends Car {

	@Override
	public void adicionarProduto(String produto) {
		produtos.add(produto);
		status = StatusCarrinho2.PREENCHIDO;
		System.out.println("Produto " + produto + " adicionado ao carrinho.");
	}

	@Override
	public void finalizarCompra() {
		if (!produtos.isEmpty()) {
			status = StatusCarrinho2.FINALIZADO;
			System.out.println("Compra finalizada com produtos: " + produtos);
		} else {
			System.out.println("Carrinho vazio. Adicione produtos antes de finalizar.");
		}
	}
}

class SimulacaoQuantica {
	public static void main_Simulacao() {
		ContaUsuario conta = new ContaUsuario("Alice", 5000, TipoConta.CORRENTE);
		System.err.println(conta.toString());
		conta.sincronizar();
		conta.transacionar(1500);
		conta.transacionar(-2000);
		conta.transacionar(-5000);
		conta.transacionar(-200);
		conta.transacionar(-500);

		System.out.println("Novo saldo: " + conta.saldo + " de " + conta.titular);

		ContaUsuario cont = new ContaUsuario("Lucia", 100, TipoConta.INVESTIMENTO);
		System.err.println(cont.toString());
		cont.sincronizar();
		cont.transacionar(590);
		cont.transacionar(200);
		cont.transacionar(377);
		cont.transacionar(501);
		cont.transacionar(256);
		cont.transacionar(305);
		System.out.println("Novo saldo: " + cont.saldo + " de " + cont.titular);

		ContaUsuario con = new ContaUsuario("João", 2100, TipoConta.POUPANCA);
		System.err.println(con.toString());
		con.sincronizar();
		con.transacionar(500);
		con.transacionar(200);
		con.transacionar(300);
		con.transacionar(200);
		con.transacionar(600);
		con.transacionar(700);
		System.out.println("Novo saldo: " + con.saldo + " de " + con.titular);

		ExperimentoQuantico experimento = new ExperimentoQuantico(EstadoQuantico.SUPERPOSICAO);
		experimento.colapsar();

		CarrinhoCompra car = new CarrinhoCompra();
		car.adicionarProduto("\nComputador Quântico");
		car.adicionarProduto("Livro de Mecânica Quântica");
		car.finalizarCompra();
	}
}

class Import_2IF {
	public static void run_Import_2IF() {
		System.err.println("\t\t2. Tranzação de dinheiro\n");
		SimulacaoQuantica.main_Simulacao();
	}
}

//Corpo da Classe _______________________________
//Definição de Enum para Operações Bancárias
enum TipoOperacao {
	DEPOSITO, SAQUE, TRANSFERENCIA;
}

enum StatusOperacao {
	SUCESSO, FALHA, PENDENTE;
}

enum TiposConta {
	CORRENTE, POUPANCA;
}

//Interfaces Personalizadas
@interface ValidarTransacao {
}

@interface AutenticarUsuario {
}

@interface SincronizarRede {
}

//Classe abstrata representando uma conta bancária
abstract class Conta {
	protected String titular;
	protected double saldo;
	protected TiposConta tipo;
	protected List<String> historicoTransacoes = new ArrayList<>();

	public Conta(String titular, double saldoInicial, TiposConta tipo) {
		this.titular = titular;
		this.saldo = saldoInicial;
		this.tipo = tipo;
	}

	public abstract void realizarOperacao(TipoOperacao operacao, double valor);

	public synchronized void exibirSaldo() {
		System.err.println("\nTitular: " + titular + " | Saldo Atual: " + saldo + " | Canta " + tipo +"\n");
	}
}

//Classe abstrata para Operação Bancária
abstract class OperacaoBancaria {
	protected StatusOperacao status;
	protected double valor;
	protected TipoOperacao tipo;

	public OperacaoBancaria(TipoOperacao tipo, double valor) {
		this.tipo = tipo;
		this.valor = valor;
		this.status = StatusOperacao.PENDENTE;
	}

	public abstract void processarOperacao(Conta conta);
}

//Classe abstrata para Usuário
abstract class Usuarios {
	protected String nome;
	protected String ip;

	public Usuarios(String nome, String ip) {
		this.nome = nome;
		this.ip = ip;
	}

	public abstract void autenticar();
}

//Implementação da Conta Corrente com Métodos Sincronizados
class ContaCorrente extends Conta {
	public ContaCorrente(String titular, double saldoInicial, TipoConta corrente) {
		super(titular, saldoInicial, TiposConta.CORRENTE);
	}

	@Override
	@ValidarTransacao
	@SincronizarRede
	public synchronized void realizarOperacao(TipoOperacao operacao, double valor) {
		if (operacao == TipoOperacao.DEPOSITO) {
			saldo += valor;
			historicoTransacoes.add("Depósito de R$ " + valor);
		} else if (operacao == TipoOperacao.SAQUE) {
			if (saldo >= valor) {
				saldo -= valor;
				historicoTransacoes.add("Saque de R$ " + valor);
			} else {
				System.out.println("Saldo insuficiente!");
			}
		}
	}
}

//Implementação da Conta Corrente com Métodos Sincronizados
class ContaPoupanca extends Conta {
	public ContaPoupanca(String titular, double saldoInicial, TipoConta poupanca) {
		super(titular, saldoInicial, TiposConta.POUPANCA);
	}

	@Override
	@ValidarTransacao
	@SincronizarRede
	public synchronized void realizarOperacao(TipoOperacao operacao, double valor) {
		if (operacao == TipoOperacao.DEPOSITO) {
			saldo += valor;
			historicoTransacoes.add("Depósito de R$ " + valor);
		} else if (operacao == TipoOperacao.SAQUE) {
			if (saldo >= valor) {
				saldo -= valor;
				historicoTransacoes.add("Saque de R$ " + valor);
			} else {
				System.out.println("Saldo insuficiente!");
			}
		}
	}
}


//Implementação do Usuário
class Cliente extends Usuarios {
	private Conta conta;

	public Cliente(String nome, String ip, Conta conta) {
		super(nome, ip);
		this.conta = conta;
	}

	@Override
	@AutenticarUsuario
	public void autenticar() {
		System.out.println("Usuário " + nome + " autenticado no IP " + ip);
	}

	public void exibirExtrato() {
		ListIterator<String> iterator = conta.historicoTransacoes.listIterator();
		System.out.println("Extrato para " + nome + ":");
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
}

//Teste da implementação
class BancoSimulacao {
	public static void main_Banco() {
		Conta minhaConta = new ContaCorrente("Carlos", 1000.00, TipoConta.CORRENTE);
		Cliente cliente = new Cliente("Carlos", "192.168.1.79", minhaConta);

		minhaConta.exibirSaldo();
		
		cliente.autenticar();
		minhaConta.realizarOperacao(TipoOperacao.DEPOSITO, 500);
		minhaConta.realizarOperacao(TipoOperacao.SAQUE, 300);

		minhaConta.exibirSaldo();
		cliente.exibirExtrato();
		
		Conta minhaContaP = new ContaPoupanca("Augusto", 900.00, TipoConta.POUPANCA);
		Cliente clienteP = new Cliente("Augusto", "192.168.1.10", minhaContaP);

		minhaContaP.exibirSaldo();
		
		clienteP.autenticar();
		minhaContaP.realizarOperacao(TipoOperacao.DEPOSITO, 600);
		minhaContaP.realizarOperacao(TipoOperacao.SAQUE, 500);
		minhaContaP.realizarOperacao(TipoOperacao.SAQUE, 1000);

		minhaContaP.exibirSaldo();
		clienteP.exibirExtrato();
		minhaContaP.realizarOperacao(TipoOperacao.SAQUE, 100);
		minhaContaP.exibirSaldo();
		clienteP.exibirExtrato();
		minhaContaP.realizarOperacao(TipoOperacao.DEPOSITO, 653);
		minhaContaP.exibirSaldo();
		clienteP.exibirExtrato();
	}
}

class Import_3IF {
	public static void run_Import_3IF() {
		System.err.println("\t\t3. Banco Simulacao de tranferencia\n");
		BancoSimulacao.main_Banco();
	}
}
