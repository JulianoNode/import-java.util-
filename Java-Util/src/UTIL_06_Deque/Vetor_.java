package UTIL_06_Deque;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

import util.Linhas;
import util.VoutarMenu;

public class Vetor_ {
	public static void Vetor_Run() {

		Linhas linhas = new Linhas();
		String collec = "\n\t Deque<E>: Representa uma fila dupla (pode adicionar/remover de ambas as extremidades). \n";
		System.err.println(collec);

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

		Deque<ChuteFutebol> chuteQueue = new LinkedList<>();

		// Adicionando chutes com diferentes velocidades e ângulos na fila
		chuteQueue.add(new ChuteFutebol(25.0, 30.0));
		chuteQueue.add(new ChuteFutebol(30.0, 45.0));
		chuteQueue.add(new ChuteFutebol(22.0, 60.0));

		// Laço for para percorrer a fila de chutes
		for (ChuteFutebol chute : chuteQueue) {
			double tempo = chute.calcularTempoDeVoo();
			System.out.println("Tempo de voo do chute (velocidade: " + chute.getVelocidadeInicial() + " m/s, ângulo: "
					+ chute.getAngulo() + " graus): " + tempo + " segundos.");
		}

		// Utilizando do-while para simular uma sequência de cálculos enquanto a fila
		// não estiver vazia
		do {
			ChuteFutebol chute = chuteQueue.poll();
			if (chute != null) {
				double tempo = chute.calcularTempoDeVoo();
				System.out.println("Tempo de voo do chute (velocidade: " + chute.getVelocidadeInicial()
						+ " m/s, ângulo: " + chute.getAngulo() + " graus): " + tempo + " segundos.");
			}
		} while (!chuteQueue.isEmpty());

		// Usando switch-case para categorizar o tipo de chute baseado na velocidade
		double velocidadeChute = 30.0;
		switch ((int) velocidadeChute) {
		case 20:
			System.out.println("Chute classificado como LENTO.");
			break;
		case 30:
			System.out.println("Chute classificado como MEDIO.");
			break;
		case 40:
			System.out.println("Chute classificado como RAPIDO.");
			break;
		default:
			System.out.println("Velocidade não categorizada.");
			break;
		}

		// Usando if-else-if para analisar o tipo de chute com base no ângulo
		double anguloChute = 45.0;
		if (anguloChute < 30) {
			System.out.println("Chute baixo");
		} else if (anguloChute >= 30 && anguloChute <= 45) {
			System.out.println("Chute médio");
		} else {
			System.out.println("Chute alto");
		}

		// Definindo o tipo do chute
		TipoChute tipo = TipoChute.CURVO;

		switch (tipo) {
		case ALTO:
			System.out.println("O chute é alto");
			break;
		case LATERAL:
			System.out.println("O chute é lateral");
			break;
		case CURVO:
			System.out.println("O chute é curvo");
			break;
		default:
			System.out.println("Tipo de chute desconhecido");
		}

	}
}

//Corpo da Classe _______________________________
class Import_2V {

	private static Deque<Item> itens = new LinkedList<>();
	private static Scanner scanner = new Scanner(System.in);

	public static void run_Import_2V() {
		System.err.println("\t\t2. \n");

		boolean rodando = true;

		while (rodando) {
			mostrarMenu();
			int opcao = scanner.nextInt();
			scanner.nextLine(); // Limpa o buffer

			switch (opcao) {
			case 1:
				criarItem();
				break;
			case 2:
				lerItens();
				break;
			case 3:
				atualizarItem();
				break;
			case 4:
				deletarItem();
				break;
			case 5:
				rodando = false;
				System.out.println("Saindo do sistema...");
				break;
			default:
				System.out.println("Opção inválida! Tente novamente.");
				break;
			}
		}
	}

	public static void mostrarMenu() {
		System.out.println("\n---- MENU ----");
		System.out.println("1. Criar Item");
		System.out.println("2. Ler Itens");
		System.out.println("3. Atualizar Item");
		System.out.println("4. Deletar Item");
		System.out.println("5. Sair");
		System.out.print("Escolha uma opção: ");
	}

	public static void criarItem() {
		System.out.println("\nDigite o nome do item:");
		String nome = scanner.nextLine();
		System.out.println("Digite o autor do livro:");
		String autor = scanner.nextLine();

		Livro livro = new Livro(nome, autor);
		itens.add(livro);
		System.out.println("Item criado com sucesso!");
	}

	public static void lerItens() {
		if (itens.isEmpty()) {
			System.out.println("Nenhum item encontrado!");
		} else {
			System.out.println("\nLista de Itens:");
			for (Item item : itens) {
				item.exibirInfo();
			}
		}
	}

	public static void atualizarItem() {
		if (itens.isEmpty()) {
			System.out.println("Nenhum item para atualizar!");
			return;
		}

		System.out.println("\nDigite o nome do item a ser atualizado:");
		String nome = scanner.nextLine();

		boolean encontrado = false;
		for (Item item : itens) {
			if (item.nome.equals(nome)) {
				System.out.println("Item encontrado. Digite o novo nome:");
				String novoNome = scanner.nextLine();
				System.out.println("Digite o novo autor:");
				String novoAutor = scanner.nextLine();
				((Livro) item).nome = novoNome;
				((Livro) item).autor = novoAutor;
				System.out.println("Item atualizado com sucesso!");
				encontrado = true;
				break;
			}
		}

		if (!encontrado) {
			System.out.println("Item não encontrado!");
		}
	}

	public static void deletarItem() {
		if (itens.isEmpty()) {
			System.out.println("Nenhum item para deletar!");
			return;
		}

		System.out.println("\nDigite o nome do item a ser deletado:");
		String nome = scanner.nextLine();

		boolean removido = false;
		for (Item item : itens) {
			if (item.nome.equals(nome)) {
				itens.remove(item);
				System.out.println("Item deletado com sucesso!");
				removido = true;
				break;
			}
		}

		if (!removido) {
			System.out.println("Item não encontrado para deletar!");
		}

	}
}

//1 Corpo da Classe Abstract e enum _______________________________
class Import_3V {
	public static void run_Import_3V() {
		System.err.println("\t\t3. int id, String nome, double preco, TipoProduto tipoProduto \n");

		Scanner scanner = new Scanner(System.in);
		CRUDProduto crudProduto = new CRUDProdutoImpl();

		int opcao;
		do {
			System.out.println("\nCRUD de Produtos:");
			System.out.println("1. Adicionar Produto");
			System.out.println("2. Atualizar Produto");
			System.out.println("3. Remover Produto");
			System.out.println("4. Buscar Produto");
			System.out.println("5. Listar Produtos");
			System.out.println("6. Sair");
			System.out.print("Escolha uma opção: ");
			opcao = scanner.nextInt();
			scanner.nextLine(); // Limpar o buffer

			switch (opcao) {
			case 1:
				System.out.print("Digite o ID do produto: ");
				int id = scanner.nextInt();
				scanner.nextLine();
				System.out.print("Digite o nome do produto: ");
				String nome = scanner.nextLine();
				System.out.print("Digite o preço do produto: ");
				double preco = scanner.nextDouble();
				scanner.nextLine();
				System.out.print("Digite o tipo de produto (ELETRONICO, ALIMENTO, ROUPA): ");
				TipoProduto tipo = TipoProduto.valueOf(scanner.nextLine().toUpperCase());
				Produto produto = new Produto(id, nome, preco, tipo);
				crudProduto.adicionarProduto(produto);
				break;

			case 2:
				System.out.print("Digite o ID do produto a ser atualizado: ");
				int idAtualizar = scanner.nextInt();
				scanner.nextLine();
				System.out.print("Digite o novo nome do produto: ");
				String nomeAtualizado = scanner.nextLine();
				System.out.print("Digite o novo preço do produto: ");
				double precoAtualizado = scanner.nextDouble();
				scanner.nextLine();
				System.out.print("Digite o novo tipo de produto: ");
				TipoProduto tipoAtualizado = TipoProduto.valueOf(scanner.nextLine().toUpperCase());
				Produto produtoAtualizado = new Produto(idAtualizar, nomeAtualizado, precoAtualizado, tipoAtualizado);
				crudProduto.atualizarProduto(idAtualizar, produtoAtualizado);
				break;

			case 3:
				System.out.print("Digite o ID do produto a ser removido: ");
				int idRemover = scanner.nextInt();
				crudProduto.removerProduto(idRemover);
				break;

			case 4:
				System.out.print("Digite o ID do produto a ser buscado: ");
				int idBuscar = scanner.nextInt();
				Produto produtoBuscado = crudProduto.buscarProduto(idBuscar);
				if (produtoBuscado != null) {
					System.out.println(produtoBuscado);
				}
				break;

			case 5:
				crudProduto.listarProdutos();
				break;

			case 6:
				System.out.println("Saindo...");
				break;

			default:
				System.out.println("Opção inválida! Tente novamente.");
				break;
			}

		} while (opcao != 6);

		VoutarMenu voutarMenu = new VoutarMenu();
		voutarMenu.run_CaracteresMenu_Red();

	}
}

enum TipoChute {
	ALTO, LATERAL, CURVO
}

abstract class Chute {
	protected double velocidadeInicial;
	protected double angulo;

	// Método abstrato para calcular o tempo de voo
	public abstract double calcularTempoDeVoo();

	// Getter e Setter
	public double getVelocidadeInicial() {
		return velocidadeInicial;
	}

	public void setVelocidadeInicial(double velocidadeInicial) {
		this.velocidadeInicial = velocidadeInicial;
	}

	public double getAngulo() {
		return angulo;
	}

	public void setAngulo(double angulo) {
		this.angulo = angulo;
	}
}

class ChuteFutebol extends Chute {

	public ChuteFutebol(double velocidadeInicial, double angulo) {
		this.velocidadeInicial = velocidadeInicial;
		this.angulo = angulo;
	}

	@Override
	public double calcularTempoDeVoo() {
		double gravidade = 9.8; // aceleração gravitacional
		double anguloEmRadianos = Math.toRadians(this.angulo);
		double tempo = (2 * this.velocidadeInicial * Math.sin(anguloEmRadianos)) / gravidade;
		return tempo;
	}
}

//2 Corpo da Classe Abstract e enum _______________________________

enum Operacao {
	CRIAR, LER, ATUALIZAR, DELETAR, SAIR
}

abstract class Item {
	String nome;

	public Item(String nome) {
		this.nome = nome;
	}

	public abstract void exibirInfo();
}

class Livro extends Item {
	String autor;

	public Livro(String nome, String autor) {
		super(nome);
		this.autor = autor;
	}

	@Override
	public void exibirInfo() {
		System.out.println("Nome: " + nome + ", Autor: " + autor);
	}
}

//3 Corpo da Classe Abstract e enum _______________________________
enum TipoProduto {
	ELETRONICO, ALIMENTO, ROUPA;
}

class Produto {
	private int id;
	private String nome;
	private double preco;
	private TipoProduto tipoProduto;

	public Produto(int id, String nome, double preco, TipoProduto tipoProduto) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.tipoProduto = tipoProduto;
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

	public TipoProduto getTipoProduto() {
		return tipoProduto;
	}

	@Override
	public String toString() {
		return "Produto [ID=" + id + ", Nome=" + nome + ", Preço=" + preco + ", Tipo=" + tipoProduto + "]";
	}
}

abstract class CRUDProduto {
	protected Deque<Produto> produtos = new ArrayDeque<>();

	public abstract void adicionarProduto(Produto produto);

	public abstract void atualizarProduto(int id, Produto produto);

	public abstract void removerProduto(int id);

	public abstract Produto buscarProduto(int id);

	public abstract void listarProdutos();
}

class CRUDProdutoImpl extends CRUDProduto {

	@Override
	public void adicionarProduto(Produto produto) {
		produtos.add(produto);
		System.out.println("Produto adicionado: " + produto);
	}

	@Override
	public void atualizarProduto(int id, Produto produto) {
		for (Produto p : produtos) {
			if (p.getId() == id) {
				produtos.remove(p);
				produtos.add(produto);
				System.out.println("Produto atualizado: " + produto);
				return;
			}
		}
		System.out.println("Produto com ID " + id + " não encontrado.");
	}

	@Override
	public void removerProduto(int id) {
		for (Produto p : produtos) {
			if (p.getId() == id) {
				produtos.remove(p);
				System.out.println("Produto removido: " + p);
				return;
			}
		}
		System.out.println("Produto com ID " + id + " não encontrado.");
	}

	@Override
	public Produto buscarProduto(int id) {
		for (Produto p : produtos) {
			if (p.getId() == id) {
				return p;
			}
		}
		System.out.println("Produto com ID " + id + " não encontrado.");
		return null;
	}

	@Override
	public void listarProdutos() {
		if (produtos.isEmpty()) {
			System.out.println("Não há produtos cadastrados.");
		} else {
			System.out.println("Lista de Produtos:");
			for (Produto p : produtos) {
				System.out.println(p);
			}
		}
	}
}