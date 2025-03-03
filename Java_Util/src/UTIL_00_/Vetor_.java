package UTIL_00_;

import util.Linhas;

public class Vetor_ {
	public static void  Vetor_Run() {

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

class Import_1V {
	public static void run_Import_1V() {
		System.err.println("\t\t1. \n");

	}
}

class Import_2V {
	public static void run_Import_2V() {
		System.err.println("\t\t2. \n");

	}
}

class Import_3V {
	public static void run_Import_3V() {
		System.err.println("\t\t3. \n");

	}
}