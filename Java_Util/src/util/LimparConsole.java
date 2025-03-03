package util;

import java.io.IOException;

public class LimparConsole {

	public String CleanConsole_Run() {
		// Detectar o sistema operacional
		String sistemaOperacional = System.getProperty("os.name").toLowerCase();

		try {
			if (sistemaOperacional.contains("win")) {
				// Comando para Windows
				new ProcessBuilder("cmd", "/c", "cls", "clear").inheritIO().start().waitFor();
				System.out.println(" **** Erro ao tentar limpar o console: ****");
			} else {
				// Comando para sistemas Unix/Linux/MacOS
				System.out.print("\033[H\033[2J");
				System.out.flush();
			}
		} catch (IOException | InterruptedException e) {
			System.out.println("Erro ao tentar limpar o console: " + e.getMessage());
		}
		return "***** Console limpo *****";
	}

}
