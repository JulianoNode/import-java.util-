package util;

public class VoutarMenu {

	public void run_CaracteresMenu_Red() {
		char[] caracteres = { '☕', '☕', '☕', '☕', '☕', ' ', '*', '*', '*', '*', '*', '*',' ', 
				'V', 'O', 'U', 'T', 'A', 'R',' ','P', 'A', 'R', 'A', ' ','O',' ','M', 'E', 'N', 'U', ' ', 
				'*', '*', '*', '*', '*', '*', ' ', '☕', '☕', '☕', '☕', '☕', };
		System.out.println("\n\n\n\n\n\n");
		for (char c : caracteres) {
			System.err.print(c);
			try {
				Thread.sleep(50); // Pausa de 1 segundo (1000 milissegundos)
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
