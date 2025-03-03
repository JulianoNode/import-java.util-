package UTIL_03_Set;

import java.util.*;
import util.Linhas;

public class For_Matrix {
	public static void For_Run() {

		Linhas linhas = new Linhas();
		String collec = "\n\t Set: Representa um conjunto (não permite elementos duplicados)..\n";
		System.err.println(collec);

		linhas.run_Caracteres();
		Import_1F.run_Import_1F();

		linhas.run_Caracteres();
		Import_2F.run_Import_2F();

		linhas.run_Caracteres();
		Import_3F.run_Import_3F();

	}
}

@interface MyAnnotation {
	String value();
}

@MyAnnotation(value = "Testando Set com anotação")
class Import_1F {
	public static void run_Import_1F() {
		System.err.println("\t\t1. Usando Set e @interface com if e for\n");

		Set<Integer> numeros = new HashSet<>();
		numeros.add(5);
		numeros.add(10);
		numeros.add(15);

		for (Integer numero : numeros) {
			if (numero % 2 == 0) {
				System.out.println(numero + " é par.");
			} else {
				System.out.println(numero + " é ímpar.");
			}
		}

	}
}

//Definindo uma anotação personalizada
@interface AnnotationSet {
	String description() default "This is a custom annotation";
}

class Import_2F {

	// Método para processar um Set e utilizar if/else dentro de um loop for
	@AnnotationSet(description = "Método que processa um Set")
	public static void processSet(Set<Integer> numbers) {
		for (Integer number : numbers) {
			if (number % 2 == 0) {
				System.out.println(number + " é par.");
			} else if (number % 2 != 0) {
				System.out.println(number + " é ímpar.");
			} else {
				System.out.println("Valor desconhecido");
			}
		}
	}

	public static void run_Import_2F() {
		System.err.println("\t\t2. Codigo para saber se é par ou impar \n");

		// Criando um Set de números
		Set<Integer> numbers = new HashSet<>();
		numbers.add(10);
		numbers.add(15);
		numbers.add(22);
		numbers.add(37);
		numbers.add(210);
		numbers.add(125);
		numbers.add(222);
		numbers.add(237);
		numbers.add(130);
		numbers.add(135);
		numbers.add(223);
		numbers.add(337);

		// Chamando o método processSet com o Set
		processSet(numbers);

	}
}

//Definindo a Annotation
@interface ValidateSetSize {
	int minSize()

	default 1;

	int maxSize() default 10;
}

class Import_3F {

	// Método para validar o tamanho do Set usando a Annotation
	public static boolean validateSetSize(Set<String> set) {
		try {
			ValidateSetSize annotation = Import_3F.class.getMethod("main", String[].class)
					.getAnnotation(ValidateSetSize.class);
			int minSize = annotation.minSize();
			int maxSize = annotation.maxSize();
			return set.size() >= minSize && set.size() <= maxSize;
		} catch (NoSuchMethodException e) {			
		}
		return false;
	}

	@ValidateSetSize(minSize = 2, maxSize = 6)
	public static void run_Import_3F() {
		System.err.println("\t\t3. Aqui está um exemplo de um código em Java "
				+ "usando o comando java.util.Set, @interface, estruturas if, if-else, e for:\n");

		// Criando um Set com elementos
		Set<String> set = new HashSet<>();
		set.add("Java");
		set.add("Python");
		set.add("JS");
		set.add("C#");
		set.add("CSS");
		set.add("HTML");
		set.add("JAvaScript");
		set.add("C++");
		set.add("Gol");
		set.add("Dilphi");

		// Verificando se o Set tem um tamanho adequado
		if (validateSetSize(set)) {
			System.out.println("O tamanho do Set está dentro do intervalo permitido.");
		} else {
			System.out.println("O tamanho do Set não está dentro do intervalo permitido.");
		}

		// Usando um loop for para iterar sobre o Set
		for (String element : set) {
			if (element.length() == 4) {
				System.out.println(element + " tem mais de 4 caracteres.");
			} else if (element.length() == 3) {
				System.out.println(element + " tem mais de 3 caracteres.");
			}  else if (element.length() == 2) {
				System.out.println(element + " tem mais de 2 caracteres.");
			}else {
				System.out.println(element + " tem 4 ou menos caracteres.");
			}
		}

	}
}
