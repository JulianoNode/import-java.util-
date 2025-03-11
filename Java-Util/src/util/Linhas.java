package util;

public class Linhas {

	public String run_LinhaRed() {
		System.err.println(
				"\n______________________________________________________________________________________________\n");
		return "";
	}

	public void run_LinhaWirte() {
		System.out.println(
				"\n______________________________________________________________________________________________\n");
	}

	public void run_LinhaRedNew() {
		System.err.println(
				"\n______________________________________ ** New Programa ** ______________________________________\n");
	}

	public void run_Caracteres() {
		char[] caracteres = { '_', '_', '_', '_', '_', '*', '*', '*', '*', '*', '*', 'N', 'e', 'w', ' ', 'P', 'r', 'o',
				'g', 'r', 'a', 'm', 'a', '*', '*', '*', '*', '*', '*', '_', '_', '_', '_', '_', };

		for (char c : caracteres) {
			System.out.print(c);
			try {
				Thread.sleep(10); // Pausa de 1 segundo (1000 milissegundos)
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("\n");

	}
	
	public void run_Caracteres_Red() {
		char[] caracteres = { '_', '_', '_', '_', '_', '*', '*', '*', '*', '*', '*', 'N', 'e', 'w', ' ', 'P', 'r', 'o',
				'g', 'r', 'a', 'm', 'a', '*', '*', '*', '*', '*', '*', '_', '_', '_', '_', '_', };

		for (char c : caracteres) {
			System.err.print(c);
			try {
				Thread.sleep(10); // Pausa de 1 segundo (1000 milissegundos)
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
