package UTIL_02_List;

import java.util.ArrayList;
import java.util.List;
import util.Linhas;

public class For_Matrix {
	public static void For_Run() {

		Linhas linhas = new Linhas();
		String collec = "\n\t List: Representa uma lista ordenada.\n";
		System.err.println(collec);

		linhas.run_Caracteres();
		Import_1F.run_Import_1F();

		linhas.run_Caracteres();
		Import_2F.run_Import_2F();

		linhas.run_Caracteres();
		Import_3F.run_Import_3F();

	}
}

//Corpo da Classe
class Import_1F {

	@interface MatrixProcessorInfo {
		String author()

		default "Unknown";

		String version() default "1.0";
	}

	@MatrixProcessorInfo(author = "Juliano", version = "1.1")
	public static void run_Import_1F() {
		System.err.println("\t\t1. Preenchendo a matriz com listas de inteiros \n");
		
		List<List<Integer>> matrix = new ArrayList<>();

		// Preenchendo a matriz com listas de inteiros
		for (int i = 0; i < 5; i++) {
			List<Integer> row = new ArrayList<>();
			for (int j = 0; j < 4; j++) {
				row.add(i * j);
			}
			matrix.add(row);
		}

		// Processando a matriz
		for (List<Integer> row : matrix) {
			for (Integer value : row) {
				System.out.print(value + " ");
			}
			System.out.println();
		}

		// Exibindo a anotação
		if (Import_1F.class.isAnnotationPresent(MatrixProcessorInfo.class)) {
			MatrixProcessorInfo info = Import_1F.class.getAnnotation(MatrixProcessorInfo.class);
			System.err.println("Author: " + info.author());
			System.out.println("Version: " + info.version());
		}

	}
}

//Corpo da Classe
@interface MatrixInfo {
	String author() default "Unknown";
}

class Import_2F {

	// Método que popula uma matriz 2D e utiliza List<E> para armazenar os valores
	public static List<Integer> populateMatrix(int rows, int cols) {
		List<Integer> matrixList = new ArrayList<>();

		// Preenchendo a lista com valores de uma matriz 2D
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				matrixList.add(i * j); // Exemplo simples, multiplicando os índices
			}
		}
		return matrixList;
	}

	// Método que imprime a matriz em formato de matriz
	public static void printMatrix(List<Integer> matrixList, int rows, int cols) {
		int index = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				System.out.print(matrixList.get(index++) + "\t");
			}
			System.out.println();
		}
	}

	@SuppressWarnings("static-access")
	public static void run_Import_2F() {
		System.err.println("\t\t2. Criação de uma instância da classe MatrixOperations \n");
		int rows = 11;
		int cols = 5;

		// Criação de uma instância da classe MatrixOperations
		Import_2F matrixOps = new Import_2F();

		// Popula a matriz
		List<Integer> matrixList = matrixOps.populateMatrix(rows, cols);

		// Imprime a matriz
		matrixOps.printMatrix(matrixList, rows, cols);

		// Obtendo e exibindo a annotation
		MatrixInfo matrixInfo = Import_2F.class.getAnnotation(MatrixInfo.class);
		if (matrixInfo != null) {
			System.out.println("\nMatrixInfo Annotation:");
			System.out.println("Author: " + matrixInfo.author());

		}
	}
}

//Corpo da Classe
@interface DataType {
	String value() default "Integer";
}

@DataType(value = "String")
class Import_3F {

	public static void run_Import_3F() {
		System.err.println("\t\t3. Exibindo a anotação de uma Matrix \n");
		List<List<String>> matrix = new ArrayList<>();

		// Criando uma matriz de listas de Strings
		for (int i = 0; i < 5; i++) {
			List<String> row = new ArrayList<>();
			for (int j = 0; j < 3; j++) {
				row.add("Item " + (i * 3 + j));
			}
			matrix.add(row);
		}

		// Processando a matriz
		for (List<String> row : matrix) {
			for (String value : row) {
				System.out.print(value + " ");
			}
			System.out.println();
		}

		// Exibindo a anotação
		if (Import_3F.class.isAnnotationPresent(DataType.class)) {
			DataType dataType = Import_3F.class.getAnnotation(DataType.class);
			System.out.println("Data Type: " + dataType.value());
		}

	}
}
