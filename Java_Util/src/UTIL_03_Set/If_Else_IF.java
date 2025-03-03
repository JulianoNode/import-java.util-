package UTIL_03_Set;

import java.util.HashSet;
import java.util.Set;

import util.Linhas;

public class If_Else_IF {
	public static void If_Else_IF_Run() {

		Linhas linhas = new Linhas();
		String collec = "\n\t Set: Representa um conjunto (não permite elementos duplicados). \n";
		System.err.println(collec);

		linhas.run_Caracteres();
		Import_1IF.run_Import_1IF();

		linhas.run_Caracteres();
		Import_2IF.run_Import_2IF();

		linhas.run_Caracteres();
		Import_3IF.run_Import_3IF();
	}
}

//Corpo da Classe
@interface Status {
	String value();
}

class Import_1IF {
	@Status(value = "Ativo")
	public static void run_Import_1IF() {
		System.err.println("\t\t1.Comando @interface com valores condicionais \n");
		Set<String> statusSet = new HashSet<>();
		statusSet.add("Ativo");
		statusSet.add("Inativo");

		String statusVerificado = "Ativo";

		if (statusSet.contains(statusVerificado)) {
			System.out.println("Status " + statusVerificado + " encontrado.");
		} else if (statusVerificado.equals("Suspenso")) {
			System.out.println("Status Suspenso não encontrado.");
		} else {
			System.out.println("Status não encontrado.");
		}
	}
}
//Corpo da Classe

//Definindo uma anotação personalizada
@interface Tarefa {
	String descricao();

	String data();
}

class Import_2IF {
	@Tarefa(descricao = "Comprar leite", data = "2025-03-04")
	public static void run_Import_2IF() {
		System.err.println("\t\t2. Uso de java.util.Set com múltiplos if e else\n");
		
		Set<String> tarefas = new HashSet<>();
		tarefas.add("Comprar leite");
		tarefas.add("Estudar Java");

		System.out.println("Lista de tarefas: " + tarefas);
	}
}
//Corpo da Classe

class Import_3IF {
	@interface MyAnnotation {
		String value();
	}

	@MyAnnotation(value = "Conjunto de Frutas")
	public static void run_Import_3IF() {
		System.err.println("\t\t3. Uso de @interface com uma String\n");
		Set<String> frutas = new HashSet<>();
		frutas.add("Maçã");
		frutas.add("Banana");
		frutas.add("Laranja");

		// Exibe todas as frutas no conjunto
		System.out.println("Frutas no conjunto:");
		for (String fruta : frutas) {
			System.out.println(fruta);
		}

	}

}
