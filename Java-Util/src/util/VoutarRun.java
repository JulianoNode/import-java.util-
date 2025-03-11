package util;

public class VoutarRun {

	public void run_CaracteresMenu_Red() {
		char[] caracteres = { '☕', '☕', '☕', '☕', '☕', ' ', '*', '*', '*', '*', '*', '*',' ', 
				'V', 'O', 'U', 'T', 'A', 'R',' ','P', 'A', 'R', 'A', ' ','O',' ','M', 'E', 'N', 'U', ' ', 
				'*', '*', '*', '*', '*', '*', ' ', '☕', '☕', '☕', '☕', '☕', };
		System.out.println("\n");
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
