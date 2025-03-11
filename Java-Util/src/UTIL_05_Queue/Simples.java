package UTIL_05_Queue;

import util.Linhas;
import java.util.Queue;
import java.util.LinkedList;

public class Simples {
	public static void Simples_Run() {

		Linhas linhas = new Linhas();
		String collec = "\n\t Queue<E>: Representa uma fila (FIFO).\n";
		System.err.println(collec);

		linhas.run_Caracteres();
		Import_1S.run_Import_1S();

		linhas.run_Caracteres();
		Import_2S.run_Import_2S();

		linhas.run_Caracteres();
		Import_3S.run_Import_3S();

	}
}

//Corpo da classe Abstract
abstract class Cliente {
	protected String nome;
	protected int idade;

	public Cliente(String nome, int idade) {
		this.nome = nome;
		this.idade = idade;
	}

	// Método abstrato que deve ser implementado pelas subclasses
	public abstract void exibirInfo();
}

class ClienteVIP extends Cliente {
	public ClienteVIP(String nome, int idade) {
		super(nome, idade);
	}

	@Override
	public void exibirInfo() {
		System.out.println("Cliente VIP: " + nome + ", Idade: " + idade);
	}
}

class ClienteComum extends Cliente {
	public ClienteComum(String nome, int idade) {
		super(nome, idade);
	}

	@Override
	public void exibirInfo() {
		System.out.println("Cliente Comum: " + nome + ", Idade: " + idade);
	}
}

//Corpo da Classe _______________________________
class Import_1S {
	public static void run_Import_1S() {
		System.err.println("\t\t1.  Adicionando Clientes a uma Fila (Queue)\r\n"
				+ "Este código usa uma fila (Queue) para gerenciar clientes.\n");

		Queue<Cliente> filaClientes = new LinkedList<>();

		filaClientes.add(new ClienteVIP("Alice", 30));
		filaClientes.add(new ClienteComum("Bob", 25));
		filaClientes.add(new ClienteVIP("Carlos", 40));

		System.out.println("Clientes na fila:");
		for (Cliente cliente : filaClientes) {
			cliente.exibirInfo();
		}

	}
}

//Corpo da Classe _______________________________
class Import_2S {
	public static void run_Import_2S() {
		System.err.println("\t\t2. Processamento da Fila com switch-case\r\n"
				+ "Este código processa os clientes na fila e trata VIPs de forma diferenciada. \n");

		Queue<Cliente> filaClientes = new LinkedList<>();

		filaClientes.add(new ClienteVIP("Alice", 30));
		filaClientes.add(new ClienteComum("Bob", 25));
		filaClientes.add(new ClienteVIP("Carlos", 40));

		while (!filaClientes.isEmpty()) {
			Cliente cliente = filaClientes.poll();
			System.err.print("Atendendo: ");
			cliente.exibirInfo();

			// Simulando prioridade de atendimento usando switch-case
			switch (cliente.getClass().getSimpleName()) {
			case "ClienteVIP":
				System.out.println("Prioridade alta para cliente VIP.\n");
				break;
			case "ClienteComum":
				System.out.println("Atendimento padrão para cliente comum.\n");
				break;
			default:
				System.out.println("Tipo de cliente desconhecido.\n");
			}
		}

	}
}

//Corpo da Classe _______________________________
class Import_3S {
	public static void run_Import_3S() {
		System.err.println("\t\t3. Verificação de Cliente com if-else\r\n"
				+ "Aqui, o código verifica se há um cliente específico na fila.\n");

		Queue<Cliente> filaClientes = new LinkedList<>();

		Cliente alice = new ClienteVIP("Alice", 30);
		Cliente bob = new ClienteComum("Bob", 25);

		filaClientes.add(alice);
		filaClientes.add(bob);

		// Verificar se um cliente específico está na fila
		if (filaClientes.contains(alice)) {
			System.out.println(alice.nome + " está na fila.");
		} else {
			System.out.println(alice.nome + " não está na fila.");
		}

	}
}
