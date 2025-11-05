package UTIL_01_Collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import util.Linhas;

public class Vetor_ {
	public static void Vetor_Run() {

		Linhas linhas = new Linhas();
		String collec = "\n\t Collection<E>: A interface raiz para a hierarquia de coleções. \n";
		System.err.println(collec);


		linhas.run_Caracteres();
		Import_1V.run_Import_1V();

		linhas.run_Caracteres();
		Import_2V.run_Import_2V();

		linhas.run_Caracteres();
		Import_3V.run_Import_3V();


	}
}

class Import_1V {

	public static void run_Import_1V() {
		System.err.println("\t\t1. \n");

		// Criando um vetor de Strings
		String[] vetor = { "João", "Maria", "Carlos", "Ana" };

		// Convertendo o vetor para uma Collection (List)
		Collection<String> colecao = Arrays.asList(vetor);

		// Exibindo os elementos da Collection
		System.out.println("Elementos da Collection:");
		for (String nome : colecao) {
			System.out.println(nome);
		}

		// Adicionando um novo elemento na Collection (não será permitido)
		// colecao.add("Lucas"); // Isso geraria uma UnsupportedOperationException

		// Criando uma nova lista para demonstrar a adição
		List<String> lista = new ArrayList<>(colecao);
		lista.add("Lucas");
		lista.add("Juca");
		lista.add("Lila");

		// Exibindo a lista atualizada
		System.out.println("\nLista atualizada:");
		for (String nome : lista) {
			System.err.println(nome);

		}

	}
}

class Import_2V {

	public static void run_Import_2V() {
		System.err.println("\t\t2. Gerenciador de Números (Adiciona números a uma lista e exibe)\n");

		Collection<Integer> numeros = new ArrayList<>();
		Scanner scanner = new Scanner(System.in);
		int opcao;

		do {
			System.out.println("\nMenu:");
			System.out.println("1 - Adicionar número");
			System.out.println("2 - Exibir números");
			System.out.println("3 - Sair");
			System.out.print("Escolha uma opção: ");
			opcao = scanner.nextInt();

			switch (opcao) {
			case 1:
				System.out.print("Digite um número: ");
				int numero = scanner.nextInt();
				numeros.add(numero);
				System.out.println("Número adicionado com sucesso!");
				break;
			case 2:
				if (numeros.isEmpty()) {
					System.out.println("A lista está vazia.");
				} else {
					System.out.println("Números armazenados: " + numeros);
				}
				break;
			case 3:
				System.out.println("Saindo...");
				break;
			default:
				System.out.println("Opção inválida, tente novamente.");
			}
		} while (opcao != 3);

		// 🚫 NÃO use sc.close() se for continuar lendo System.in depois!
		if (scanner != null) {
			scanner = null; // libera o scanner sem fechar System.in
		}

	}
}

class Import_3V {

	public static void run_Import_3V() {
		System.err.println("\t\t3. Verificador de Notas (Usa vetor para armazenar notas e calcula média)\n");

		Collection<Double> notas = new ArrayList<>();
		Scanner scanner = new Scanner(System.in);
		int opcao;

		do {
			System.out.println("\nMenu:");
			System.out.println("1 - Adicionar nota");
			System.out.println("2 - Calcular média");
			System.out.println("3 - Sair");
			System.out.print("Escolha uma opção: ");
			opcao = scanner.nextInt();

			switch (opcao) {
			case 1:
				System.out.print("Digite a nota: ");
				double nota = scanner.nextDouble();
				notas.add(nota);
				System.out.println("Nota adicionada.");
				break;
			case 2:
				if (notas.isEmpty()) {
					System.out.println("Nenhuma nota foi adicionada.");
				} else {
					double soma = 0;
					for (double n : notas) {
						soma += n;
					}
					double media = soma / notas.size();
					System.out.println("Média das notas: " + media);

					if (media >= 7) {
						System.out.println("Aluno aprovado!");
					} else {
						System.out.println("Aluno reprovado.");
					}
				}
				break;
			case 3:
				System.out.println("Saindo...");
				break;
			default:
				System.out.println("Opção inválida, tente novamente.");
			}
		} while (opcao != 3);
		// 🚫 NÃO use sc.close() se for continuar lendo System.in depois!
		if (scanner != null) {
			scanner = null; // libera o scanner sem fechar System.in
		}

	}
}