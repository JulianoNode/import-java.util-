package UTIL_02_List;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import util.Linhas;

public class Vetor_ {
	public static void Vetor_Run() {

		Linhas linhas = new Linhas();
		String collec = "\n\t Nome \n";
		System.err.println(collec);

		linhas.run_Caracteres();
		Import_1V.run_Import_1V();

		linhas.run_Caracteres();
		Import_2V.run_Import_2V();

		linhas.run_Caracteres();
		Import_3V.run_Import_3V();
	}
}

//Corpo da Classe
@interface Matrix3D {
	int depth();

	int rows();

	int columns();
}

@Matrix3D(depth = 3, rows = 3, columns = 3)
class Import_1V {
	public static void run_Import_1V() {
		System.out.println("\t\t1. Preenchendo a matriz 3D com valores e Exibindo a matriz 3D \n");

		int depth = 3, rows = 3, columns = 3;
		List<List<List<Integer>>> matrix3D = new ArrayList<>();

		// Preenchendo a matriz 3D com valores
		for (int i = 0; i < depth; i++) {
			List<List<Integer>> matrix2D = new ArrayList<>();
			for (int j = 0; j < rows; j++) {
				List<Integer> row = new ArrayList<>();
				for (int k = 0; k < columns; k++) {
					row.add(i * rows * columns + j * columns + k);
				}
				matrix2D.add(row);
			}
			matrix3D.add(matrix2D);
		}

		// Exibindo a matriz 3D
		for (List<List<Integer>> matrix2D : matrix3D) {
			for (List<Integer> row : matrix2D) {
				System.out.println(row);
			}
		}
	}
}

//Corpo da Classe
@interface DataInfo {
	String version();
}

@DataInfo(version = "1.0")
class Import_2V {
	public static void run_Import_2V() {
		System.out.println("\t\t2. Inicializando a matriz com valores baseados em cálculos\n");

		int size = 4;
		List<List<Integer>> matrix = new ArrayList<>();

		// Inicializando a matriz com valores baseados em cálculos
		for (int i = 0; i < size; i++) {
			List<Integer> row = new ArrayList<>();
			for (int j = 0; j < size; j++) {
				row.add(i * size + j);
			}
			matrix.add(row);
		}

		// Manipulação de dados na matriz (exemplo: transpor a matriz)
		List<List<Integer>> transposedMatrix = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			List<Integer> row = new ArrayList<>();
			for (int j = 0; j < size; j++) {
				row.add(matrix.get(j).get(i));
			}
			transposedMatrix.add(row);
		}

		// Exibindo a matriz transposta
		System.out.println("Transposed Matrix:");
		for (List<Integer> row : transposedMatrix) {
			System.out.println(row);
		}
	}
}

class Import_3V {
	public static void run_Import_3V() {
		System.out.println("\t\t3. O programa permite ao usuário escolher entre preencher e exibir "
				+ "uma lista de nomes, um vetor de números e uma matriz de notas.\n");

		Scanner scanner = new Scanner(System.in);
		List<String> nomes = new ArrayList<>(); // Lista de Strings
		int[] numeros = new int[5]; // Vetor de inteiros
		int[][] matriz = new int[3][3]; // Matriz 3x3 de inteiros

		int opcao;
		do {
			System.out.println("\n=== MENU ===");
			System.out.println("1 - Adicionar nomes à lista");
			System.out.println("2 - Mostrar lista de nomes");
			System.out.println("3 - Preencher vetor");
			System.out.println("4 - Mostrar vetor");
			System.out.println("5 - Preencher matriz");
			System.out.println("6 - Mostrar matriz");
			System.out.println("7 - Sair");
			System.out.print("Escolha uma opção: ");
			opcao = scanner.nextInt();

			switch (opcao) {
			case 1:
				scanner.nextLine(); // Consumir a quebra de linha
				System.out.print("Digite um nome: ");
				String nome = scanner.nextLine();
				nomes.add(nome);
				System.out.println("Nome adicionado!");
				break;

			case 2:
				if (nomes.isEmpty()) {
					System.out.println("A lista de nomes está vazia.");
				} else {
					System.out.println("Lista de nomes:");
					for (String n : nomes) {
						System.out.println("- " + n);
					}
				}
				break;

			case 3:
				System.out.println("Digite 5 números:");
				for (int i = 0; i < numeros.length; i++) {
					System.out.print("Número " + (i + 1) + ": ");
					numeros[i] = scanner.nextInt();
				}
				System.out.println("Vetor preenchido!");
				break;

			case 4:
				System.out.println("Vetor de números:");
				for (int num : numeros) {
					System.out.print(num + " ");
				}
				System.out.println();
				break;

			case 5:
				System.out.println("Preenchendo a matriz 3x3:");
				for (int i = 0; i < matriz.length; i++) {
					for (int j = 0; j < matriz[i].length; j++) {
						System.out.print("Digite um valor para [" + i + "][" + j + "]: ");
						matriz[i][j] = scanner.nextInt();
					}
				}
				System.out.println("Matriz preenchida!");
				break;

			case 6:
				System.out.println("Matriz 3x3:");
				for (int i = 0; i < matriz.length; i++) {
					for (int j = 0; j < matriz[i].length; j++) {
						System.out.print(matriz[i][j] + "\t");
					}
					System.out.println();
				}
				break;

			case 7:
				System.out.println("Saindo do programa...");
				System.exit(0);
				break;

			default:
				System.out.println("Opção inválida! Tente novamente.");
				break;
			}
		} while (opcao != 7);

		scanner.close();

	}
}
