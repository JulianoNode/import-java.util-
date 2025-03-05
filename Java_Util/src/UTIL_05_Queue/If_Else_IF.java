package UTIL_05_Queue;

import util.Linhas;

import java.util.LinkedList;
import java.util.Queue;

public class If_Else_IF {
	public static void If_Else_IF_Run() {

		Linhas linhas = new Linhas();
		String collec = "\n\t Queue<E>: Representa uma fila (FIFO). \n";
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

//Classe abstrata Pessoa
abstract class Pessoa {
	protected String nome;
	protected int idade;

	public Pessoa(String nome, int idade) {
		this.nome = nome;
		this.idade = idade;
	}

	public abstract void exibirInfo();
}

//Classe Cliente
class Cliente1 extends Pessoa {
	private String idCliente;

	public Cliente1(String nome, int idade, String idCliente) {
		super(nome, idade);
		this.idCliente = idCliente;
	}

	@Override
	public void exibirInfo() {
		System.out.println("Cliente: " + nome + ", Idade: " + idade + ", ID: " + idCliente);
	}
}

//Classe Usuário
class Usuario extends Pessoa {
	private String email;

	public Usuario(String nome, int idade, String email) {
		super(nome, idade);
		this.email = email;
	}

	@Override
	public void exibirInfo() {
		System.out.println("Usuário: " + nome + ", Idade: " + idade + ", Email: " + email);
	}
}

class Import_1IF {
	public static void run_Import_1IF() {
		System.err.println("\t1. Aqui está um código em Java que usa if, else if, while e Queue para gerenciar "
				+ "\nPessoa, Cliente e Usuário. Ele usa uma classe abstrata Pessoa e implementa "
				+ "\nfilas (Queue) para armazenar clientes e usuários.\n");

		Queue<Pessoa> fila = new LinkedList<>();

		// Adicionando Clientes e Usuários na fila
		fila.add(new Cliente1("João", 30, "C123"));
		fila.add(new Usuario("Maria", 25, "maria@email.com"));
		fila.add(new Cliente1("Carlos", 40, "C456"));
		fila.add(new Usuario("Ana", 22, "ana@email.com"));

		while (!fila.isEmpty()) {
			Pessoa p = fila.poll(); // Remove e retorna o primeiro da fila

			if (p instanceof Cliente1) {
				System.out.println("Atendendo um Cliente...");
			} else if (p instanceof Usuario) {
				System.out.println("Atendendo um Usuário...");
			}

			p.exibirInfo();
			System.out.println("---------------------------");
		}
	}
}

//Corpo da Classe _______________________________
//Classe abstrata Pessoa
abstract class Pessoa2 {
	protected String nome;

	public Pessoa2(String nome) {
		this.nome = nome;
	}

	public abstract void realizarAcao();
}

//Subclasse Cliente
class Cliente2 extends Pessoa2 {
	public Cliente2(String nome) {
		super(nome);
	}

	@Override
	public void realizarAcao() {
		System.out.println(nome + " está comprando um produto.");
	}
}

//Subclasse Vendedor
class Vendedor2 extends Pessoa2 {
	public Vendedor2(String nome) {
		super(nome);
	}

	@Override
	public void realizarAcao() {
		System.out.println(nome + " está vendendo um produto.");
	}
}

//Subclasse Usuario
class Usuario2 extends Pessoa2 {
	public Usuario2(String nome) {
		super(nome);
	}

	@Override
	public void realizarAcao() {
		System.out.println(nome + " está acessando o sistema.");
	}
}

class Import_2IF {
	public static void run_Import_2IF() {
		System.err.println("\t2. Aqui está um código em Java que implementa uma estrutura com uma classe abstrata "
				+ "\nPessoa, e as classes Cliente, Vendedor e Usuario que herdam dela. Ele usa Queue para "
				+ "\ngerenciar uma fila de atendimento e utiliza if, else if e while para processar os elementos da fila.\n");

		Queue<Pessoa2> fila = new LinkedList<>();

		// Adicionando pessoas à fila
		fila.add(new Cliente2("Carlos"));
		fila.add(new Vendedor2("Ana"));
		fila.add(new Usuario2("Bruno"));
		fila.add(new Cliente2("Fernanda"));
		fila.add(new Vendedor2("Lucas"));

		// Processando a fila
		while (!fila.isEmpty()) {
			Pessoa2 pessoa = fila.poll();
			if (pessoa instanceof Cliente2) {
				System.out.println("Atendendo um cliente...");
			} else if (pessoa instanceof Vendedor2) {
				System.out.println("Atendendo um vendedor...");
			} else if (pessoa instanceof Usuario2) {
				System.out.println("Atendendo um usuário...");
			}
			pessoa.realizarAcao();
			System.out.println("---------------------------");
		}
	}
}

//Corpo da Classe _______________________________

//Classe abstrata Usuario
abstract class Usuario3 {
	protected String nome;
	protected int id;

	public Usuario3(String nome, int id) {
		this.nome = nome;
		this.id = id;
	}

	public abstract void mostrarInfo();
}

//Classe Cliente que herda de Usuario
class Cliente3 extends Usuario3 {
	public Cliente3(String nome, int id) {
		super(nome, id);
	}

	@Override
	public void mostrarInfo() {
		System.out.println("Cliente: " + nome + " | ID: " + id);
	}
}

//Classe Vendedor que herda de Usuario
class Vendedor3 extends Usuario3 {
	public Vendedor3(String nome, int id) {
		super(nome, id);
	}

	@Override
	public void mostrarInfo() {
		System.out.println("Vendedor: " + nome + " | ID: " + id);
	}
}

//Classe Produto
class Produto {
	private String nome;
	private double preco;

	public Produto(String nome, double preco) {
		this.nome = nome;
		this.preco = preco;
	}

	public void mostrarProduto() {
		System.out.println("Produto: " + nome + " | Preço: R$" + preco);
	}
}

class Import_3IF {
	public static void run_Import_3IF() {
		System.err.println("\t3. Aqui está um código em Java que implementa uma estrutura básica para "
				+ "\ngerenciar produtos, clientes, vendedores e usuários usando uma classe abstrata e a "
				+ "\ninterface Queue da biblioteca java.util. O código inclui uma fila de clientes e produtos, "
				+ "\nalém de estruturas de controle if, else if, e while.\n");

		Queue<Cliente3> filaClientes = new LinkedList<>();
		Queue<Produto> filaProdutos = new LinkedList<>();

		// Adicionando clientes à fila
		filaClientes.add(new Cliente3("Carlos", 1));
		filaClientes.add(new Cliente3("Ana", 2));
		filaClientes.add(new Cliente3("Pedro", 3));

		// Adicionando produtos à fila
		filaProdutos.add(new Produto("Notebook", 3500.00));
		filaProdutos.add(new Produto("Smartphone", 2000.00));
		filaProdutos.add(new Produto("Fone de Ouvido", 150.00));

		// Processando a fila de clientes
		while (!filaClientes.isEmpty()) {
			Cliente3 cliente = filaClientes.poll();
			cliente.mostrarInfo();
		}

		System.out.println("---");

		// Processando a fila de produtos
		while (!filaProdutos.isEmpty()) {
			Produto produto = filaProdutos.poll();
			produto.mostrarProduto();
		}

		// Verificação de existência de um produto
		String produtoBuscado = "Smartphone";
		boolean encontrado = false;
		if (produtoBuscado.equals("Notebook")) {
			encontrado = true;
		} else if (produtoBuscado.equals("Smartphone")) {
			encontrado = true;
		} else if (produtoBuscado.equals("Fone de Ouvido")) {
			encontrado = true;
		} else {
			encontrado = false;
		}

		System.out.println("Produto " + produtoBuscado + " encontrado: " + encontrado);
	}
}
