package UTIL_03_Set;

import util.Linhas;
import java.util.HashSet;
import java.util.Set;

public class While_Matrix {
	public static void While_Run() {
		Linhas linhas = new Linhas();
		String collec = "\n\t Set: Representa um conjunto (não permite elementos duplicados). \n";
		System.err.println(collec);

		linhas.run_Caracteres();
		Import_1WM.run_Import_1WM();

		linhas.run_Caracteres();
		Import_2WM.run_Import_2WM();

		linhas.run_Caracteres();
		Import_3WM.run_Import_3WM();

	}
}

@interface IterationChecker {
}

class Import_1WM {
	@IterationChecker
	public static void run_Import_1WM() {
		System.err.println("\t\t1. Usando Set com do while\n");
		Set<Integer> set = new HashSet<>();
		set.add(1);
		set.add(2);
		set.add(3);
		set.add(4);

		Integer[] setArray = set.toArray(new Integer[0]);
		int index = 0;

		do {
			if (setArray[index] % 2 == 0) {
				System.out.println(setArray[index] + " is even.");
			}
			index++;
		} while (index < setArray.length);

	}
}

//Definindo uma anotação personalizada
@interface Verificador {
	String valor() default "valor default";
}

class Import_2WM {

	// Método com a anotação personalizada Verificador
	@Verificador(valor = "Testando anotação")
	public static void verificarSet(Set<String> set) {
		// Usando for para iterar sobre o Set
		for (String item : set) {
			if (item.equals("teste")) {
				System.out.println("Item encontrado: " + item);
			} else if (item.equals("exemplo")) {
				System.out.println("Exemplo encontrado: " + item);
			} else if (item.equals("Animal")) {
				System.out.println("Animal encontrado: " + item);
			} else {
				System.out.println("Outro item: " + item);
			}
		}

		// Usando do-while
		int counter = 0;
		do {
			System.out.println("Contando: " + counter);
			counter++;
		} while (counter < 3);
	}

	public static void run_Import_2WM() {
		System.err.println(
				"\t\t2. Aqui está um exemplo de código Java que utiliza o @interface para criar uma anotação personalizada\n");
		// Criando um Set
		Set<String> meuSet = new HashSet<>();
		meuSet.add("teste");
		meuSet.add("exemplo");
		meuSet.add("outro");
		meuSet.add("Comida");
		meuSet.add("Animal");
		meuSet.add("Produtos");

		// Chamando o método anotado
		verificarSet(meuSet);
	}
}

class Import_3WM {
	// Definindo a anotação personalizada
	@interface MyAnnotation {
		String description() default "Esta é uma anotação personalizada";
	}

	public static void run_Import_3WM() {
		System.err.println("\t\t3. Aqui está um exemplo de código Java que usa o Set do pacote java.util\n");

		// Criando um Set de Strings
		Set<String> mySet = new HashSet<>();
		mySet.add("Apple");
		mySet.add("Banana");
		mySet.add("Cherry");
		mySet.add("Date");

		// Usando a anotação na classe
		@MyAnnotation(description = "Manipulação de Set com Loops e Switch Case")
		class InnerClass {
			// Método para demonstrar os loops e switch
			public void processSet() {
				// Usando um laço for para percorrer o Set
				for (String item : mySet) {
					System.out.println("For Loop: " + item);

					// Switch case dentro do for
					switch (item) {
					case "Apple":
						System.out.println("Fruit is Apple");
						break;
					case "Banana":
						System.out.println("Fruit is Banana");
						break;
					case "Cherry":
						System.out.println("Fruit is Cherry");
						break;
					default:
						System.out.println("Unknown Fruit");
						break;
					}
				}

				// Usando do-while para percorrer o Set de forma alternativa
				System.out.println("\nUsing do-while:");
				var iterator = mySet.iterator();
				do {
					if (iterator.hasNext()) {
						String item = iterator.next();
						System.out.println("Do-while Loop: " + item);
					}
				} while (iterator.hasNext());
			}
		}

		// Criando e chamando a classe interna
		InnerClass innerClass = new InnerClass();
		innerClass.processSet();

	}
}