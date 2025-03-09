package UTIL_07_Collections;

import util.Linhas;
import util.VoutarRun;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class Vetor_ {
	public static void Vetor_Run() {

		Linhas linhas = new Linhas();
		String collec = "\n\t Collections: Métodos utilitários para trabalhar com coleções (ex.: ordenação, busca, sincronização). \n";
		System.err.println(collec);

		linhas.run_Caracteres();
		Import_1VV.run_Import_1VV();

		linhas.run_Caracteres();
		Import_1V.run_Import_1V();

		linhas.run_Caracteres();
		Import_2V.run_Import_2V();

		linhas.run_Caracteres();
		Import_3V.run_Import_3V();
	}
}

//Corpo da Classe _______________________________
class Import_1V {
	public static void run_Import_1V() {
		System.err.println("\t\t1. \n");

		Scanner scanner = new Scanner(System.in);
		GerenciadorProdutos gerenciador = new GerenciadorProdutos();
		int opcao;

		do {
			System.out.println(
					"\n1. Adicionar Produto\n2. Remover Produto\n3. Buscar Produto\n4. Listar Produtos\n5. Ordenar por Preço\n6. Sair");
			System.out.print("Escolha uma opção: ");
			opcao = scanner.nextInt();
			scanner.nextLine();

			switch (opcao) {
			case 1:
				System.out.print("ID: ");
				int id = scanner.nextInt();
				scanner.nextLine(); // Consumir quebra de linha
				System.out.print("Nome: ");
				String nome = scanner.nextLine();
				System.out.print("Preço: ");
				double preco = scanner.nextDouble();
				System.out.println("Categoria (0:ELETRONICO, 1:ALIMENTO, 2:ROUPA, 3:LIMPEZA): ");
				int catIndex = scanner.nextInt();
				Categoria categoria = Categoria.values()[catIndex];

				gerenciador.adicionarProduto(new ProdutoConcreto(id, nome, preco, categoria));
				break;

			case 2:
				System.out.print("ID do produto a remover: ");
				int removeId = scanner.nextInt();
				gerenciador.removerProduto(removeId);
				break;

			case 3:
				System.out.print("Nome do produto a buscar: ");
				String buscaNome = scanner.nextLine();
				Produto produto = gerenciador.buscarProduto(buscaNome);
				if (produto != null) {
					produto.exibirDetalhes();
				} else {
					System.out.println("Produto não encontrado!");
				}
				break;

			case 4:
				gerenciador.listarProdutos();
				break;

			case 5:
				gerenciador.ordenarPorPreco();
				System.out.println("Produtos ordenados por preço!");
				break;

			case 6:
				System.out.println("Saindo...");
				break;

			default:
				System.out.println("Opção inválida!");
			}
		} while (opcao != 6);
		VoutarRun voutarRun = new VoutarRun();
		voutarRun.run_CaracteresMenu_Red();

	}
}

//Corpo da Classe _______________________________
class Import_2V {
	private static List<Usuario> usuarios = Collections.synchronizedList(new ArrayList<>());

	public static void run_Import_2V() {
		System.err.println("\t\t2. \n");

		Scanner scanner = new Scanner(System.in);
		int opcao;

		do {
			System.out.println("\n### CRUD de Perfis ###");
			System.out.println("1. Adicionar Perfil");
			System.out.println("2. Exibir Perfis");
			System.out.println("3. Ordenar Perfis");
			System.out.println("4. Buscar Perfil");
			System.out.println("5. Sair");
			System.out.print("Escolha uma opção: ");
			opcao = scanner.nextInt();
			scanner.nextLine();

			switch (opcao) {
			case 1:
				adicionarPerfil(scanner);
				break;
			case 2:
				exibirPerfis();
				break;
			case 3:
				ordenarPerfis();
				break;
			case 4:
				buscarPerfil(scanner);
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

	private static void adicionarPerfil(Scanner scanner) {
		System.out.print("Nome: ");
		String nome = scanner.nextLine();

		System.out.print("Idade: ");
		int idade = scanner.nextInt();
		scanner.nextLine();

		System.out.println("Escolha o tipo de perfil: ");
		for (TipoPerfil tipo : TipoPerfil.values()) {
			System.out.println(tipo.ordinal() + ". " + tipo);
		}
		int tipoEscolhido = scanner.nextInt();
		scanner.nextLine();

		if (tipoEscolhido >= 0 && tipoEscolhido < TipoPerfil.values().length) {
			TipoPerfil tipoPerfil = TipoPerfil.values()[tipoEscolhido];
			usuarios.add(new Usuario(nome, idade, tipoPerfil));
			System.out.println("Perfil adicionado com sucesso!");
		} else {
			System.out.println("Tipo de perfil inválido!");
		}
	}

	private static void exibirPerfis() {
		if (usuarios.isEmpty()) {
			System.out.println("Nenhum perfil cadastrado.");
		} else {
			for (Usuario usuario : usuarios) {
				usuario.exibirPerfil();
			}
		}
	}

	private static void ordenarPerfis() {
		if (usuarios.size() > 1) {
			Collections.sort(usuarios, Comparator.comparing(Usuario::getNome));
			System.out.println("Perfis ordenados por nome.");
		} else {
			System.out.println("Não há perfis suficientes para ordenar.");
		}
	}

	private static void buscarPerfil(Scanner scanner) {
		System.out.print("Digite o nome para buscar: ");
		String nomeBusca = scanner.nextLine();

		boolean encontrado = false;
		for (Usuario usuario : usuarios) {
			if (usuario.getNome().equalsIgnoreCase(nomeBusca)) {
				usuario.exibirPerfil();
				encontrado = true;
			}
		}

		if (!encontrado) {
			System.out.println("Perfil não encontrado.");
		}

	}
}

//Corpo da Classe _______________________________
class Import_3V {
	private static final List<CartaoCredito> cartoes = Collections.synchronizedList(new ArrayList<>());
	private static final Scanner scanner = new Scanner(System.in);

	public static void run_Import_3V() {
		System.err.println("\t\t3. \n");

		int opcao;
		do {
			System.out.println("\n--- Menu CRUD Cartão de Crédito ---");
			System.out.println("1. Adicionar Cartão");
			System.out.println("2. Listar Cartões");
			System.out.println("3. Buscar Cartão");
			System.out.println("4. Remover Cartão");
			System.out.println("5. Ordenar Cartões");
			System.out.println("6. Sair");
			System.out.print("Escolha uma opção: ");
			opcao = scanner.nextInt();
			scanner.nextLine();

			switch (opcao) {
			case 1 -> adicionarCartao();
			case 2 -> listarCartoes();
			case 3 -> buscarCartao();
			case 4 -> removerCartao();
			case 5 -> ordenarCartoes();
			case 6 -> System.out.println("Encerrando o sistema...");
			default -> System.out.println("Opção inválida!");
			}
		} while (opcao != 6);

		VoutarRun voutarRun = new VoutarRun();
		voutarRun.run_CaracteresMenu_Red();
	}

	private static void adicionarCartao() {
		System.out.print("Número do Cartão: ");
		String numero = scanner.nextLine();
		System.out.print("Titular: ");
		String titular = scanner.nextLine();
		System.out.print("Bandeira (VISA, MASTERCARD, etc.): ");
		Bandeira bandeira = Bandeira.valueOf(scanner.nextLine().toUpperCase());
		System.out.print("Tipo (CREDITO, DEBITO, MULTIFUNCIONAL): ");
		TipoCartao tipo = TipoCartao.valueOf(scanner.nextLine().toUpperCase());
		System.out.print("Limite: ");
		double limite = scanner.nextDouble();
		scanner.nextLine();
		System.out.print("Categoria (GOLD, PLATINUM, etc.): ");
		Categoria categoria = Categoria.valueOf(scanner.nextLine().toUpperCase());
		System.out.print("Banco Emissor (ITAU, BRADESCO, etc.): ");
		BancoEmissor banco = BancoEmissor.valueOf(scanner.nextLine().toUpperCase());

		cartoes.add(new CartaoCredito(numero, titular, bandeira, tipo, limite, categoria, banco));
		System.out.println("Cartão adicionado com sucesso!");
	}

	private static void listarCartoes() {
		if (cartoes.isEmpty()) {
			System.out.println("Nenhum cartão cadastrado.");
		} else {
			for (CartaoCredito c : cartoes) {
				c.exibirDetalhes();
			}
		}
	}

	private static void buscarCartao() {
		System.out.print("Digite o número do cartão para buscar: ");
		String numero = scanner.nextLine();
		boolean encontrado = false;
		for (CartaoCredito c : cartoes) {
			if (c.numero.equals(numero)) {
				c.exibirDetalhes();
				encontrado = true;
				break;
			}
		}
		if (!encontrado) {
			System.out.println("Cartão não encontrado.");
		}
	}

	private static void removerCartao() {
		System.out.print("Digite o número do cartão para remover: ");
		String numero = scanner.nextLine();
		boolean removido = cartoes.removeIf(c -> c.numero.equals(numero));
		System.out.println(removido ? "Cartão removido." : "Cartão não encontrado.");
	}

	private static void ordenarCartoes() {
		Collections.sort(cartoes, Comparator.comparing(c -> c.numero));
		System.out.println("Cartões ordenados pelo número!");
	}
}

//Corpo da Classe _______________________________
class Import_1VV {
	private static List<Double> historico = Collections.synchronizedList(new ArrayList<>());
	private static ConversorMoeda conversor = new ConversorMoeda();

	public static void run_Import_1VV() {
		System.err.println("\t\t1. \n");

		Scanner scanner = new Scanner(System.in);
		int opcao;

		do {
			System.out.println("\n=== CRUD de Conversão de Moedas ===");
			System.out.println("1. Converter moeda");
			System.out.println("2. Mostrar histórico");
			System.out.println("3. Ordenar histórico");
			System.out.println("4. Buscar valor no histórico");
			System.out.println("5. Sair");
			System.out.print("Escolha uma opção: ");
			opcao = scanner.nextInt();

			switch (opcao) {
			case 1:
				System.out.print("Digite o valor: ");
				double valor = scanner.nextDouble();
				System.out.print("Digite a moeda de origem (USD, EUR, JPY, CNY, CHF, BRL, GBP): ");
				Moeda de = Moeda.valueOf(scanner.next().toUpperCase());
				System.out.print("Digite a moeda de destino: ");
				Moeda para = Moeda.valueOf(scanner.next().toUpperCase());

				double resultado = conversor.converter(valor, de, para);
				historico.add(resultado);
				System.out.println("Valor convertido: " + resultado);
				break;

			case 2:
				System.out.println("Histórico de conversões: " + historico);
				break;

			case 3:
				Collections.sort(historico);
				System.out.println("Histórico ordenado: " + historico);
				break;

			case 4:
				System.out.print("Digite o valor para buscar: ");
				double busca = scanner.nextDouble();
				if (historico.contains(busca)) {
					System.out.println("Valor encontrado no histórico!");
				} else {
					System.out.println("Valor não encontrado.");
				}
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

//1 Corpo da Classe Abstract e Enum _______________________________

//Enum para categorias de produtos
enum Categoria {
	ELETRONICO, ALIMENTO, ROUPA, LIMPEZA;
}

//Classe abstrata Produto
abstract class Produto {
	protected int id;
	protected String nome;
	protected double preco;
	protected Categoria categoria;

	public Produto(int id, String nome, double preco, Categoria categoria) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.categoria = categoria;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public double getPreco() {
		return preco;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public abstract void exibirDetalhes();
}

//Classe concreta ProdutoConcreto
class ProdutoConcreto extends Produto {
	public ProdutoConcreto(int id, String nome, double preco, Categoria categoria) {
		super(id, nome, preco, categoria);
	}

	@Override
	public void exibirDetalhes() {
		System.out.println("ID: " + id + " | Nome: " + nome + " | Preço: R$" + preco + " | Categoria: " + categoria);
	}
}

//Classe para CRUD
class GerenciadorProdutos {
	private List<Produto> produtos = Collections.synchronizedList(new ArrayList<>());
	@SuppressWarnings("unused")
	private int[][] matrizProdutos = new int[5][5]; // Exemplo de uso de matriz

	// Adicionar produto
	public void adicionarProduto(Produto produto) {
		produtos.add(produto);
	}

	// Remover produto por ID
	public void removerProduto(int id) {
		produtos.removeIf(produto -> produto.getId() == id);
	}

	// Buscar produto por nome
	public Produto buscarProduto(String nome) {
		for (Produto p : produtos) {
			if (p.getNome().equalsIgnoreCase(nome)) {
				return p;
			}
		}
		return null;
	}

	// Ordenação de produtos por preço
	public void ordenarPorPreco() {
		Collections.sort(produtos, Comparator.comparingDouble(Produto::getPreco));
	}

	// Exibir todos os produtos
	public void listarProdutos() {
		for (Produto p : produtos) {
			p.exibirDetalhes();
		}
	}
}

//2 Corpo da Classe Abstract e Enum _______________________________

//Enum para tipos de perfil
enum TipoPerfil {
	ADMIN, USER, MODERATOR, GUEST, DEVELOPER, ANALYST;
}

//Classe abstrata para Perfil
abstract class Perfil {
	protected String nome;
	protected TipoPerfil tipo;

	public Perfil(String nome, TipoPerfil tipo) {
		this.nome = nome;
		this.tipo = tipo;
	}

	public abstract void exibirPerfil();
}

//Classe concreta de usuário
class Usuario extends Perfil {
	private int idade;

	public Usuario(String nome, int idade, TipoPerfil tipo) {
		super(nome, tipo);
		this.idade = idade;
	}

	@Override
	public void exibirPerfil() {
		System.out.println("Nome: " + nome + ", Idade: " + idade + ", Tipo: " + tipo);
	}

	public String getNome() {
		return nome;
	}

	public TipoPerfil getTipo() {
		return tipo;
	}
}

//3 Corpo da Classe Abstract e Enum _______________________________

//Definição dos 6 enums
enum Bandeira {
	VISA, MASTERCARD, AMEX, ELO, HIPERCARD, DISCOVER;
}

enum StatusCartao {
	ATIVO, BLOQUEADO, CANCELADO;
}

enum TipoCartao {
	CREDITO, DEBITO, MULTIFUNCIONAL;
}

enum Categoria2 {
	GOLD, PLATINUM, BLACK, STANDARD;
}

enum BancoEmissor {
	ITAU, BRADESCO, SANTANDER, CAIXA, BB;
}

enum Moeda {
	USD, EUR, JPY, CNY, CHF, BRL, GBP;
}

//Classe abstrata
abstract class Cartao {
	protected String numero;
	protected String titular;
	protected Bandeira bandeira;
	protected StatusCartao status;
	protected TipoCartao tipo;

	public Cartao(String numero, String titular, Bandeira bandeira, TipoCartao tipo) {
		this.numero = numero;
		this.titular = titular;
		this.bandeira = bandeira;
		this.tipo = tipo;
		this.status = StatusCartao.ATIVO;
	}

	public abstract void exibirDetalhes();
}

//Classe concreta
class CartaoCredito extends Cartao {
	private double limite;
	private Categoria categoria;
	private BancoEmissor banco;

	public CartaoCredito(String numero, String titular, Bandeira bandeira, TipoCartao tipo, double limite,
			Categoria categoria, BancoEmissor banco) {
		super(numero, titular, bandeira, tipo);
		this.limite = limite;
		this.categoria = categoria;
		this.banco = banco;
	}

	@Override
	public void exibirDetalhes() {
		System.out.println(
				"Cartão: " + numero + " | Titular: " + titular + " | Banco: " + banco + " | Limite: R$" + limite);
	}
}

//3V Corpo da Classe Abstract e Enum _______________________________

//Classe abstrata para operações de conversão
abstract class Conversor {
	protected Map<Moeda, Double> taxas;

	public Conversor() {
		taxas = new HashMap<>();
		taxas.put(Moeda.USD, 1.0);
		taxas.put(Moeda.EUR, 0.92);
		taxas.put(Moeda.JPY, 150.5);
		taxas.put(Moeda.CNY, 7.26);
		taxas.put(Moeda.CHF, 0.88);
		taxas.put(Moeda.BRL, 5.12);
		taxas.put(Moeda.GBP, 0.78);
	}

	public abstract double converter(double valor, Moeda de, Moeda para);
}

//Implementação da conversão
class ConversorMoeda extends Conversor {
	@Override
	public double converter(double valor, Moeda de, Moeda para) {
		if (!taxas.containsKey(de) || !taxas.containsKey(para)) {
			throw new IllegalArgumentException("Moeda inválida!");
		}
		return valor * (taxas.get(para) / taxas.get(de));
	}
}

