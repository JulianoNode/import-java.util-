package UTIL_06_Deque;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

import util.Linhas;
import util.VoutarRun;

public class Simples {
	public static void Simples_Run() {

		Linhas linhas = new Linhas();
		String collec = "\n\t Deque<E>: Representa uma fila dupla (pode adicionar/remover de ambas as extremidades).\n";
		System.err.println(collec);

		linhas.run_Caracteres();
		Import_1S.run_Import_1S();

		linhas.run_Caracteres();
		Import_2S.run_Import_2S();

		//linhas.run_Caracteres();
		//Import_2SS.run_Import_2SS();

		linhas.run_Caracteres();
		Import_3S.run_Import_3S();
	}
}

//Corpo da Classe _______________________________
class Import_1S {
	public static void run_Import_1S() {
		System.err.println("\t\t1. \n");

		// Criando a fila de fotos
		Deque<String> photos = new ArrayDeque<>();
		photos.add("Foto1.jpg");
		photos.add("Foto2.jpg");
		photos.add("Foto3.jpg");
		photos.add("Foto4.jpg");

		// Criando o navegador
		PhotoNavigator navigator = new PhotoNavigator(photos);

		// Testando navegação
		navigator.navigate(Action.NEXT); // Vai para Foto2.jpg
		navigator.navigate(Action.NEXT); // Vai para Foto3.jpg
		navigator.navigate(Action.PREV); // Volta para Foto2.jpg
		navigator.navigate(Action.FIRST); // Vai para Foto1.jpg
		navigator.navigate(Action.LAST); // Vai para Foto4.jpg
		navigator.navigate(Action.EXIT); // Sai

	}
}

//Corpo da Classe _______________________________
class Import_2S {
	public static void run_Import_2S() {
		System.err.println("\t\t2. \n");

		LivroPaginacao livro = new LivroPaginacao();

		livro.navegar(PaginacaoAcao.EXIBIR);
		livro.navegar(PaginacaoAcao.AVANCAR);
		livro.navegar(PaginacaoAcao.EXIBIR);
		livro.navegar(PaginacaoAcao.RETROCEDER);
		livro.navegar(PaginacaoAcao.EXIBIR);
		livro.navegar(PaginacaoAcao.ULTIMA);
		livro.navegar(PaginacaoAcao.EXIBIR);

	}
}

//Corpo da Classe _______________________________
class Import_2SS {
	public static void run_Import_2SS() {
		System.err.println("\t\t2. \n");

		LivroPaginacao livro = new LivroPaginacao();
		Scanner scanner = new Scanner(System.in);
		boolean continuar = true;

		while (continuar) {
			System.out.println("\nEscolha uma opção:");
			System.out.println("1 - Avançar Página");
			System.out.println("2 - Retroceder Página");
			System.out.println("3 - Ir para Primeira Página");
			System.out.println("4 - Ir para Última Página");
			System.out.println("5 - Exibir Página Atual");
			System.out.println("6 - Sair");

			int opcao = scanner.nextInt();

			switch (opcao) {
			case 1:
				livro.navegar(PaginacaoAcao.AVANCAR);
				break;
			case 2:
				livro.navegar(PaginacaoAcao.RETROCEDER);
				break;
			case 3:
				livro.navegar(PaginacaoAcao.PRIMEIRA);
				break;
			case 4:
				livro.navegar(PaginacaoAcao.ULTIMA);
				break;
			case 5:
				livro.navegar(PaginacaoAcao.EXIBIR);
				break;
			case 6:
				continuar = false;
				break;
			default:
				System.out.println("Opção inválida!");
			}
		}

		VoutarRun voutarRun = new VoutarRun();
		voutarRun.run_CaracteresMenu_Red();
	}
}

//Corpo da Classe _______________________________
class Import_3S {
	public static void run_Import_3S() {
		System.err.println("\t\t3. \n");

		Scanner scanner = new Scanner(System.in);
		Deque<Aluno> filaAlunos = new ArrayDeque<>();

		int opcao;
		do {
			System.out.println("\n=== MENU ===");
			System.out.println("1 - Adicionar Aluno");
			System.out.println("2 - Remover Aluno");
			System.out.println("3 - Listar Alunos");
			System.out.println("4 - Sair");
			System.out.print("Escolha uma opção: ");
			opcao = scanner.nextInt();
			scanner.nextLine(); // Consumir quebra de linha

			switch (opcao) {
			case 1:
				System.out.print("Nome do aluno: ");
				String nome = scanner.nextLine();
				System.out.print("Idade do aluno: ");
				int idade = scanner.nextInt();
				scanner.nextLine(); // Consumir quebra de linha

				System.out.println("Escolha o status do aluno (1-ATIVO, 2-INATIVO, 3-TRANCADO): ");
				int statusEscolha = scanner.nextInt();
				scanner.nextLine(); // Consumir quebra de linha

				StatusAluno status = switch (statusEscolha) {
				case 1 -> StatusAluno.ATIVO;
				case 2 -> StatusAluno.INATIVO;
				case 3 -> StatusAluno.TRANCADO;
				default -> {
					System.out.println("Opção inválida! Definindo como ATIVO.");
					yield StatusAluno.ATIVO;
				}
				};

				Aluno novoAluno = new Aluno(nome, idade, status);
				filaAlunos.addLast(novoAluno);
				System.out.println("Aluno adicionado com sucesso!");
				break;

			case 2:
				if (!filaAlunos.isEmpty()) {
					Aluno removido = filaAlunos.removeFirst();
					System.out.println("Aluno removido: " + removido.nome);
				} else {
					System.out.println("Nenhum aluno na fila.");
				}
				break;

			case 3:
				if (filaAlunos.isEmpty()) {
					System.out.println("A fila está vazia.");
				} else {
					System.out.println("\nLista de Alunos:");
					for (Aluno aluno : filaAlunos) {
						aluno.exibirInformacoes();
					}
				}
				break;

			case 4:
				System.out.println("Saindo...");
				break;

			default:
				System.out.println("Opção inválida! Tente novamente.");
			}
		} while (opcao != 4);

		System.exit(0);

	}
}

//1 Corpo de classe abstract e enum ______________

//Enum para ações de navegação
enum Action {
	NEXT, PREV, FIRST, LAST, EXIT
}

//Classe abstrata que define o comportamento da paginação
abstract class PhotoPagination {
	protected Deque<String> photos;
	protected String currentPhoto;

	public PhotoPagination(Deque<String> photos) {
		this.photos = photos;
		this.currentPhoto = photos.peekFirst(); // Começa na primeira foto
	}

	abstract void navigate(Action action);
}

//Implementação concreta da navegação de fotos
class PhotoNavigator extends PhotoPagination {

	public PhotoNavigator(Deque<String> photos) {
		super(photos);
	}

	@Override
	void navigate(Action action) {
		switch (action) {
		case NEXT:
			if (!photos.isEmpty()) {
				photos.addLast(photos.pollFirst()); // Move a foto atual para o final
				currentPhoto = photos.peekFirst();
			}
			break;
		case PREV:
			if (!photos.isEmpty()) {
				photos.addFirst(photos.pollLast()); // Move a última foto para o início
				currentPhoto = photos.peekFirst();
			}
			break;
		case FIRST:
			currentPhoto = photos.peekFirst(); // Volta para a primeira foto
			break;
		case LAST:
			currentPhoto = photos.peekLast(); // Vai para a última foto
			break;
		case EXIT:
			System.out.println("Saindo da navegação.");
			return;
		default:
			System.out.println("Ação inválida.");
			return;
		}
		System.out.println("Foto atual: " + currentPhoto);
	}
}

//2 Corpo de classe abstract e enum ______________

enum PaginacaoAcao {
	AVANCAR, RETROCEDER, PRIMEIRA, ULTIMA, EXIBIR;
}

abstract class Paginacao {
	protected Deque<String> paginas = new LinkedList<>();
	protected String paginaAtual;

	public Paginacao() {
		carregarPaginas();
		paginaAtual = paginas.peekFirst();
	}

	protected abstract void carregarPaginas();

	public abstract void navegar(PaginacaoAcao acao);
}

class LivroPaginacao extends Paginacao {

	@Override
	protected void carregarPaginas() {
		paginas.add("Página 1: Introdução");
		paginas.add("Página 2: Índice");
		paginas.add("Página 3: Capítulo 1");
		paginas.add("Página 4: Capítulo 2");
		paginas.add("Página 5: Conclusão");
	}

	@Override
	public void navegar(PaginacaoAcao acao) {
		switch (acao) {
		case AVANCAR:
			if (!paginas.isEmpty() && paginas.contains(paginaAtual)) {
				paginaAtual = paginas.pollFirst();
				paginas.addLast(paginaAtual);
			}
			break;
		case RETROCEDER:
			if (!paginas.isEmpty() && paginas.contains(paginaAtual)) {
				paginaAtual = paginas.pollLast();
				paginas.addFirst(paginaAtual);
			}
			break;
		case PRIMEIRA:
			paginaAtual = paginas.peekFirst();
			break;
		case ULTIMA:
			paginaAtual = paginas.peekLast();
			break;
		case EXIBIR:
			System.out.println("Página Atual: " + paginaAtual);
			break;
		}
	}
}

//3 Corpo de classe abstract e enum ______________

//Enum para representar o status do aluno
enum StatusAluno {
	ATIVO, INATIVO, TRANCADO;
}

//Classe abstrata para definir a estrutura de um aluno
abstract class Pessoa {
	protected String nome;
	protected int idade;

	public Pessoa(String nome, int idade) {
		this.nome = nome;
		this.idade = idade;
	}

	public abstract void exibirInformacoes();
}

//Classe concreta que estende Pessoa
class Aluno extends Pessoa {
	private StatusAluno status;

	public Aluno(String nome, int idade, StatusAluno status) {
		super(nome, idade);
		this.status = status;
	}

	@Override
	public void exibirInformacoes() {
		System.out.println("Nome: " + nome + ", Idade: " + idade + ", Status: " + status);
	}
}
