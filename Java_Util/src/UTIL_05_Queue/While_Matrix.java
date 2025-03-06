package UTIL_05_Queue;

import util.Linhas;
import util.VoutarRun;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class While_Matrix {
	public static void While_Run() {
		Linhas linhas = new Linhas();
		String collec = "\n\t Queue<E>: Representa uma fila (FIFO). \n";
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
//Classe abstrata representando uma Pessoa
abstract class Pessoa4 {
	String nome;
	int idade;

	public Pessoa4(String nome, int idade) {
		this.nome = nome;
		this.idade = idade;
	}

	abstract void exibirInformacoes();
}

//Classe Paciente herdando de Pessoa
class Paciente extends Pessoa4 {
	String doenca;

	public Paciente(String nome, int idade, String doenca) {
		super(nome, idade);
		this.doenca = doenca;
	}

	@Override
	void exibirInformacoes() {
		System.out.println("Paciente: " + nome + ", Idade: " + idade + ", Doença: " + doenca);
	}
}

class Import_1WM {
	static Queue<Paciente> filaDeEspera = new LinkedList<>();
	static String[][] leitos = new String[3][3]; // Matriz 3x3 representando os leitos

	public static void run_Import_1WM() {
		System.err.println("\t\t1.\n");

		Scanner scanner = new Scanner(System.in);
		int opcao;

		// Inicializa os leitos como vazios
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				leitos[i][j] = "Vazio";
			}
		}

		while (true) {
			System.out.println("\n=== MENU HOSPITAL ===");
			System.out.println("1. Adicionar Paciente à Fila");
			System.out.println("2. Internar Paciente");
			System.out.println("3. Exibir Leitos");
			System.out.println("4. Sair");
			System.out.print("Escolha uma opção: ");
			opcao = scanner.nextInt();
			scanner.nextLine();

			if (opcao == 1) {
				System.out.print("Nome do paciente: ");
				String nome = scanner.nextLine();
				System.out.print("Idade: ");
				int idade = scanner.nextInt();
				scanner.nextLine();
				System.out.print("Doença: ");
				String doenca = scanner.nextLine();

				Paciente paciente = new Paciente(nome, idade, doenca);
				filaDeEspera.add(paciente);
				System.out.println("Paciente adicionado à fila de espera!");
			} else if (opcao == 2) {
				if (filaDeEspera.isEmpty()) {
					System.out.println("Não há pacientes na fila de espera.");
				} else {
					boolean internado = false;
					for (int i = 0; i < 3; i++) {
						for (int j = 0; j < 3; j++) {
							if (leitos[i][j].equals("Vazio")) {
								Paciente p = filaDeEspera.poll();
								leitos[i][j] = p.nome;
								System.out.println(
										"Paciente " + p.nome + " foi internado no leito [" + i + "][" + j + "]");
								internado = true;
								break;
							}
						}
						if (internado)
							break;
					}
					if (!internado) {
						System.out.println("Todos os leitos estão ocupados!");
					}
				}
			} else if (opcao == 3) {
				System.out.println("\nEstado dos Leitos:");
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						System.out.print("[" + leitos[i][j] + "] ");
					}
					System.out.println();
				}
			} else if (opcao == 4) {
				System.out.println("Saindo do sistema...");
				break;
			} else {
				System.out.println("Opção inválida, tente novamente.");
			}
		}

		VoutarRun voutarRun = new VoutarRun();
		voutarRun.run_CaracteresMenu_Red();

	}
}

//Corpo da Classe _______________________________

//Classe abstrata Representando um Paciente
abstract class Paciente2 {
	String nome;
	String problema;
	int prioridade;

	public Paciente2(String nome, String problema, int prioridade) {
		this.nome = nome;
		this.problema = problema;
		this.prioridade = prioridade;
	}

	abstract void atenderPaciente();

	// Definir a prioridade de atendimento
	public int getPrioridade() {
		return prioridade;
	}
}

//Classe concreta PacienteDental que herda Paciente
class PacienteDental extends Paciente2 {

	public PacienteDental(String nome, String problema, int prioridade) {
		super(nome, problema, prioridade);
	}

	@Override
	void atenderPaciente() {
		System.out.println("Atendendo paciente " + nome + " com problema de " + problema);
	}
}

class Import_2WM {
	public static void run_Import_2WM() {
		System.err.println("\t\t2. Sistema de Controle de Consultas Dentárias com Prioridade\r\n"
				+ "\nEste exemplo utiliza a estrutura Queue para controlar uma fila de pacientes com prioridades diferentes, com base no tipo de problema dental. "
				+ "A prioridade é definida por um código numérico.\n");
		// Fila de pacientes com prioridade
		PriorityQueue<Paciente2> fila = new PriorityQueue<>((p1, p2) -> p2.getPrioridade() - p1.getPrioridade());

		// Adicionando pacientes à fila com prioridade
		fila.add(new PacienteDental("João", "Cárie", 2));
		fila.add(new PacienteDental("Maria", "Dor de dente", 1));
		fila.add(new PacienteDental("Pedro", "Limpeza", 3));

		// Atendendo pacientes com base na prioridade
		while (!fila.isEmpty()) {
			Paciente2 paciente = fila.poll();
			paciente.atenderPaciente();
		}
	}
}

//Corpo da Classe _______________________________

//Classe abstrata Representando um Paciente
abstract class Paciente3 {
	String nome;
	String problema;

	public Paciente3(String nome, String problema) {
		this.nome = nome;
		this.problema = problema;
	}

	abstract void atenderPaciente3();
}

//Classe concreta PacienteDental que herda Paciente
class PacienteDental3 extends Paciente3 {

	public PacienteDental3(String nome, String problema) {
		super(nome, problema);
	}

	@Override
	void atenderPaciente3() {
		if (problema.equalsIgnoreCase("Cárie")) {
			System.out.println("Atendendo paciente " + nome + " com cárie. Consulta simples.");
		} else if (problema.equalsIgnoreCase("Dor de dente")) {
			System.out.println("Atendendo paciente " + nome + " com dor de dente. Emergencial.");
		} else {
			System.out.println("Atendendo paciente " + nome + " para tratamento de longo prazo.");
		}
	}
}

class Import_3WM {
	public static void run_Import_3WM() {
		System.err.println("\t\t3. Simulando o Atendimento com Várias Condições\n");

		// Fila de pacientes
		Queue<Paciente3> fila = new LinkedList<>();

		// Adicionando pacientes à fila
		fila.add(new PacienteDental3("João", "Cárie"));
		fila.add(new PacienteDental3("Maria", "Dor de dente"));
		fila.add(new PacienteDental3("Pedro", "Limpeza"));

		// Atendendo pacientes
		while (!fila.isEmpty()) {
			Paciente3 paciente = fila.poll();
			paciente.atenderPaciente3();
		}

	}
}