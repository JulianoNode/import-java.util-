package UTIL_01_Collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import util.Linhas;

public class While_Matrix {
	public static void While_Run() {
		Linhas linhas = new Linhas();
		String collec = "\n\t Collection<E>: A interface raiz para a hierarquia de coleções. \n";
		System.err.println(collec);
		
		linhas.run_Caracteres();
		Import_1WM.run_Import_1WM();
		
		linhas.run_Caracteres();
		Import_2WM.run_Import_2WM();
		
		linhas.run_Caracteres();
		Import_3WM.run_Import_3WM();
		
	}
}

class Import_1WM {	 
	public static void run_Import_1WM() {		
		System.err.println("\t\t1. Criando uma coleção de List<Integer> que representam as linhas da matriz\n");

        // Criando uma coleção de List<Integer> que representam as linhas da matriz
        Collection<List<Integer>> matrix = new ArrayList<>();
        
        // Criando a matriz 3x3
        List<Integer> row1 = Arrays.asList(1, 2, 3);
        List<Integer> row2 = Arrays.asList(4, 5, 6);
        List<Integer> row3 = Arrays.asList(7, 8, 9);
        
        matrix.add(row1);
        matrix.add(row2);
        matrix.add(row3);
        
        // Usando um laço while para percorrer a coleção de listas (matriz)
        Iterator<List<Integer>> iterator = matrix.iterator();
        
        while (iterator.hasNext()) {
            List<Integer> row = iterator.next();
            System.out.println(row);
        }
	}
}
class Import_2WM {	 
	public static void run_Import_2WM() {		
		System.err.println("\t\t2. Criando uma coleção para armazenar a matriz\n");

		  // Criando uma coleção para armazenar a matriz
        Collection<Integer> collection = new ArrayList<>();
        
        // Preenchendo a coleção com valores da matriz
        for (int i = 1; i <= 9; i++) {
            collection.add(i);
        }
        System.out.println(collection.size());
        // Usando o laço while para iterar sobre a coleção
        Iterator<Integer> iterator = collection.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next() + " ");
        }
	
	}
}

class Import_3WM {	 
	public static void run_Import_3WM() {		
		System.err.println("\t\t3. Criando uma matriz 2D\n");

		// Criando uma matriz 2D
		int[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };

		// Criando uma coleção para armazenar as linhas da matriz
		Collection<List<Integer>> matrixCollection = new ArrayList<>();

		// Convertendo as linhas da matriz para List e adicionando à coleção
		for (int i = 0; i < matrix.length; i++) {
			List<Integer> row = new ArrayList<>();
			for (int j = 0; j < matrix[i].length; j++) {
				row.add(matrix[i][j]);
			}
			matrixCollection.add(row);
		}

		// Iterando sobre a coleção e exibindo as linhas da matriz
		Iterator<List<Integer>> iterator = matrixCollection.iterator();
		while (iterator.hasNext()) {
			System.out.print(iterator.next());
		}
		System.out.println("\n\n");

	}
}