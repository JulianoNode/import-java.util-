package UTIL_00_;

import util.Linhas;

public class Simples {
	public static void Simples_Run() {

		Linhas linhas = new Linhas();
		String collec = "\n\t Nome\n";
		System.err.println(collec);

		linhas.run_Caracteres();
		Import_1S.run_Import_1S();

		linhas.run_Caracteres();
		Import_2S.run_Import_2S();

		linhas.run_Caracteres();
		Import_3S.run_Import_3S();


	}
}

class Import_1S {
	public static void run_Import_1S() {
		System.err.println("\t\t1. \n");

	}
}

class Import_2S {
	public static void run_Import_2S() {
		System.err.println("\t\t2. \n");

	}
}

class Import_3S {
	public static void run_Import_3S() {
		System.err.println("\t\t3. \n");

	}
}

