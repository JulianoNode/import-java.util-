package UTIL_11_ArrayList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import util.Linhas;

public class Simples {
	public static void Simples_Run() {

		Linhas linhas = new Linhas();
		String collec = "\n\t Nome\n";
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

// Enum para categorias de produtos
enum CategoriaProduto {
	ELETRONICO, ALIMENTO, VESTUARIO;
}

// Enum para status do carrinho
enum StatusCarrinho {
	ABERTO, FECHADO;
}

// Enum para métodos de pagamento
enum MetodoPagamento {
	CARTAO, BOLETO, PIX;
}

// Classe abstrata Produto
abstract class Produto {
	protected String nome;
	protected double preco;
	protected CategoriaProduto categoria;

	public Produto(String nome, double preco, CategoriaProduto categoria) {
		this.nome = nome;
		this.preco = preco;
		this.categoria = categoria;
	}

	public abstract double calcularDesconto();

	public String getNome() {
		return nome;
	}

	public double getPreco() {
		return preco;
	}
}

// Classe abstrata para controle de estoque
abstract class ControleEstoque {
	protected int quantidade;

	public ControleEstoque(int quantidade) {
		this.quantidade = quantidade;
	}

	public abstract void atualizarEstoque(int quantidadeComprada);
}

// Classe abstrata para cálculo de valor total
abstract class CalculoValor {
	public abstract double calcularValorTotal();
}

// Classe concreta que estende Produto
class Eletronico extends Produto {
	public Eletronico(String nome, double preco) {
		super(nome, preco, CategoriaProduto.ELETRONICO);
	}

	@Override
	public double calcularDesconto() {
		return preco * 0.90; // 10% de desconto
	}
}

// Classe Carrinho
class Carrinho extends CalculoValor {
	private ArrayList<Produto> produtos = new ArrayList<>();
	private StatusCarrinho status;

	public Carrinho() {
		this.status = StatusCarrinho.ABERTO;
	}

	public synchronized void adicionarProduto(Produto produto) {
		if (status == StatusCarrinho.ABERTO) {
			produtos.add(produto);
			System.out.println(produto.getNome() + " adicionado ao carrinho.");
		} else {
			System.out.println("Carrinho fechado. Não é possível adicionar produtos.");
		}
	}

	public synchronized void removerProduto(String nome) {
		Iterator<Produto> it = produtos.iterator();
		while (it.hasNext()) {
			Produto produto = it.next();
			if (produto.getNome().equals(nome)) {
				it.remove();
				System.out.println(nome + " removido do carrinho.");
				return;
			}
		}
		System.out.println("Produto não encontrado.");
	}

	@Override
	public synchronized double calcularValorTotal() {
		double total = 0;
		for (Produto produto : produtos) {
			total += produto.calcularDesconto();
		}
		return total;
	}

	public synchronized void fecharCarrinho() {
		this.status = StatusCarrinho.FECHADO;
		System.out.println("Carrinho fechado. Total: R$ " + calcularValorTotal());
	}
}

// Classe principal para teste
class MainProduto {
	public static void main_P() {
		Carrinho carrinho = new Carrinho();
		Produto p1 = new Eletronico("Laptop", 4500.0);
		Produto p2 = new Eletronico("Smartphone", 2500.0);

		carrinho.adicionarProduto(p1);
		carrinho.adicionarProduto(p2);
		System.out.println("Total no carrinho: R$ " + carrinho.calcularValorTotal());

		carrinho.fecharCarrinho();
	}
}

class Import_1S {
	public static void run_Import_1S() {
		System.err.println("\t\t1. \n");
		MainProduto.main_P();
	}
}

//Corpo da Classe _______________________________

// Enum representando status de nota
enum NotaStatus {
	APROVADO, RECUPERACAO, REPROVADO;
}

// Enum representando status de frequência
enum FrequenciaStatus {
	REGULAR, IRREGULAR, REPROVADO;
}

// Enum representando tipos de disciplinas
enum TipoDisciplina {
	OBRIGATORIA, ELETIVA;
}

// Classe abstrata Pessoa
abstract class Pessoa {
	protected String nome;
	protected int idade;

	public Pessoa(String nome, int idade) {
		this.nome = nome;
		this.idade = idade;
	}

	public abstract void exibirInformacoes();
}

// Classe abstrata Disciplina
abstract class Disciplina {
	protected String nome;
	protected TipoDisciplina tipo;
	protected double nota;
	protected int frequencia;

	public Disciplina(String nome, TipoDisciplina tipo) {
		this.nome = nome;
		this.tipo = tipo;
	}

	public abstract NotaStatus calcularNotaStatus();

	public abstract FrequenciaStatus calcularFrequenciaStatus();

	public void setNota(double nota) {
		this.nota = nota;
	}

	public void setFrequencia(int frequencia) {
		this.frequencia = frequencia;
	}
}

// Classe abstrata Carrinho de Disciplinas
abstract class Carrinho1 {
	protected List<Disciplina> disciplinas = new ArrayList<>();

	public synchronized void adicionarDisciplina(Disciplina disciplina) {
		disciplinas.add(disciplina);
	}

	public synchronized void removerDisciplina(Disciplina disciplina) {
		disciplinas.remove(disciplina);
	}

	public abstract void exibirCarrinho();
}

// Implementação concreta de Aluno
class Aluno extends Pessoa {
	private List<Disciplina> disciplinas = new ArrayList<>();

	public Aluno(String nome, int idade) {
		super(nome, idade);
	}

	public void adicionarDisciplina(Disciplina disciplina) {
		disciplinas.add(disciplina);
	}

	public void exibirInformacoes() {
		System.out.println("Aluno: " + nome + ", Idade: " + idade);
	}
}

// Implementação concreta de DisciplinaMatematica
class DisciplinaMatematica extends Disciplina {
	public DisciplinaMatematica() {
		super("Matemática", TipoDisciplina.OBRIGATORIA);
	}

	@Override
	public NotaStatus calcularNotaStatus() {
		if (nota >= 7.0)
			return NotaStatus.APROVADO;
		else if (nota >= 5.0)
			return NotaStatus.RECUPERACAO;
		else
			return NotaStatus.REPROVADO;
	}

	@Override
	public FrequenciaStatus calcularFrequenciaStatus() {
		if (frequencia >= 75)
			return FrequenciaStatus.REGULAR;
		else if (frequencia >= 50)
			return FrequenciaStatus.IRREGULAR;
		else
			return FrequenciaStatus.REPROVADO;
	}
}

// Implementação de Carrinho de Disciplinas
class CarrinhoDisciplinas extends Carrinho1 {
	@Override
	public void exibirCarrinho() {
		System.out.println("Disciplinas no Carrinho:");
		for (Disciplina d : disciplinas) {
			System.out.println("- " + d.nome);
		}
	}
}

// Teste principal
class EscolaSistema {
	public static void main_E() {
		Aluno aluno = new Aluno("Carlos", 16);
		DisciplinaMatematica matematica = new DisciplinaMatematica();
		matematica.setNota(8.5);
		matematica.setFrequencia(80);

		CarrinhoDisciplinas carrinho = new CarrinhoDisciplinas();
		carrinho.adicionarDisciplina(matematica);

		aluno.adicionarDisciplina(matematica);
		aluno.exibirInformacoes();
		carrinho.exibirCarrinho();

		System.out.println("Status Nota: " + matematica.calcularNotaStatus());
		System.out.println("Status Frequência: " + matematica.calcularFrequenciaStatus());
	}
}

class Import_2S {
	public static void run_Import_2S() {
		System.err.println("\t\t2. \n");
		EscolaSistema.main_E();
	}
}

//Corpo da Classe _______________________________

//ENUM para Estados com valores diferentes de imposto
enum Estado {
	SP(0.18), RJ(0.20), MG(0.15), RS(0.17), PR(0.16);

	private final double imposto;

	Estado(double imposto) {
		this.imposto = imposto;
	}

	public double getImposto() {
		return this.imposto;
	}
}

//ENUM para Tipo de Documento
enum TipoDocumento {
	CPF, CNPJ;
}

//ENUM para Status de Validação
enum StatusValidacao {
	VALIDO, INVALIDO;
}

//Classe abstrata para Documentos
abstract class Documento {
	protected String numero;
	protected Estado estado;
	protected TipoDocumento tipo;

	public Documento(String numero, Estado estado, TipoDocumento tipo) {
		this.numero = numero;
		this.estado = estado;
		this.tipo = tipo;
	}

	public abstract StatusValidacao validar();

	public abstract double calcularValorComImposto(double valorBase);
}

//Classe abstrata para CPF
abstract class CPF extends Documento {
	public CPF(String numero, Estado estado) {
		super(numero, estado, TipoDocumento.CPF);
	}
}

//Classe abstrata para CNPJ
abstract class CNPJ extends Documento {
	public CNPJ(String numero, Estado estado) {
		super(numero, estado, TipoDocumento.CNPJ);
	}
}

//Implementação concreta de CPF
class CPFConcreto extends CPF {
	public CPFConcreto(String numero, Estado estado) {
		super(numero, estado);
	}

	@Override
	public StatusValidacao validar() {
		return numero.length() == 11 ? StatusValidacao.VALIDO : StatusValidacao.INVALIDO;
	}

	@Override
	public double calcularValorComImposto(double valorBase) {
		return valorBase + (valorBase * estado.getImposto());
	}
}

//Implementação concreta de CNPJ
class CNPJConcreto extends CNPJ {
	public CNPJConcreto(String numero, Estado estado) {
		super(numero, estado);
	}

	@Override
	public StatusValidacao validar() {
		return numero.length() == 14 ? StatusValidacao.VALIDO : StatusValidacao.INVALIDO;
	}

	@Override
	public double calcularValorComImposto(double valorBase) {
		return valorBase + (valorBase * estado.getImposto());
	}
}

//Classe Principal
class SistemaCPF_CNPJ {
	public static void main_CC() {
		List<Documento> documentos = new ArrayList<>();

		documentos.add(new CPFConcreto("12345678901", Estado.SP));
		documentos.add(new CNPJConcreto("12345678000199", Estado.RJ));

		double valorBase = 1000.0;

		for (Documento doc : documentos) {
			System.out.println("Documento: " + doc.numero);
			System.out.println("Tipo: " + doc.tipo);
			System.out.println("Estado: " + doc.estado);
			System.out.println("Validação: " + doc.validar());
			System.out.println("Valor com Imposto: R$ " + doc.calcularValorComImposto(valorBase));
			System.out.println("---------------------------");
		}
	}
}

class Import_3S {
	public static void run_Import_3S() {
		System.err.println("\t\t3. \n");
		SistemaCPF_CNPJ.main_CC();
	}
}
