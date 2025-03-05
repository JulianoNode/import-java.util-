package UTIL_05_Queue;

import util.Linhas;

import java.util.LinkedList;
import java.util.Queue;

public class For_Matrix {
	public static void For_Run() {

		Linhas linhas = new Linhas();
		String collec = "\n\t Queue<E>: Representa uma fila (FIFO)..\n";
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
class Import_1F {
	public static void run_Import_1F() {
		System.err.println("\t\t1.  Manipulação de Animais na Fila (Queue)\n");

		Queue<Animal> fila = new LinkedList<>();

		// Adicionando animais à fila
		fila.add(new Cachorro("Rex"));
		fila.add(new Gato("Mimi"));
		fila.add(new Cachorro("Thor"));
		fila.add(new Gato("Luna"));

		// Processando a fila de animais
		while (!fila.isEmpty()) {
			Animal animal = fila.poll(); // Remove o primeiro da fila
			animal.emitirSom();
		}
	}
}

//Corpo da Classe _______________________________
class Import_2F {
	public static void run_Import_2F() {
		System.err.println("\t\t2. Filtragem de Animais usando for e if-else if\n");

		Queue<Animal> fila = new LinkedList<>();
		fila.add(new Cachorro("Rex"));
		fila.add(new Gato("Mimi"));
		fila.add(new Cachorro("Thor"));
		fila.add(new Gato("Luna"));
		fila.add(new Gato("Dandan"));

		int qtdCachorros = 0, qtdGatos = 0;

		for (Animal animal : fila) {
			if (animal instanceof Cachorro) {
				qtdCachorros++;
			} else if (animal instanceof Gato) {
				qtdGatos++;
			}
		}

		System.out.println("Total de cachorros: " + qtdCachorros);
		System.out.println("Total de gatos: " + qtdGatos);
	}
}

//Corpo da Classe _______________________________
class Import_3F {
	public static void run_Import_3F() {
		System.err.println("\t\t3. Removendo Animais da Fila com while e if-else\n");

		Queue<Animal> fila = new LinkedList<>();
		fila.add(new Cachorro("Rex"));
		fila.add(new Gato("Mimi"));
		fila.add(new Cachorro("Thor"));
		fila.add(new Gato("Luna"));
		fila.add(new Gato("Dandan"));

		while (!fila.isEmpty()) {
			Animal animal = fila.poll(); // Remove da fila

			if (animal instanceof Cachorro) {
				System.out.println(animal.nome + " foi adotado por um fazendeiro.");
			} else if (animal instanceof Gato) {
				System.out.println(animal.nome + " encontrou um novo lar.");
			}
		}

	}
}

//Corpo da Classe _______________________________
//Classe abstrata Animal
abstract class Animal {
	protected String nome;

	public Animal(String nome) {
		this.nome = nome;
	}

	abstract void emitirSom();
}

//Classe Cachorro que herda de Animal
class Cachorro extends Animal {
	public Cachorro(String nome) {
		super(nome);
	}

	@Override
	void emitirSom() {
		System.out.println(nome + " late: Au Au!");
	}
}

//Classe Gato que herda de Animal
class Gato extends Animal {
	public Gato(String nome) {
		super(nome);
	}

	@Override
	void emitirSom() {
		System.out.println(nome + " mia: Miau!");
	}
}
