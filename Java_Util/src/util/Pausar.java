package util;

import java.io.IOException;

public class Pausar {

	public String run_Pause() {
		System.out.println("O programa está pausado. Pressione Enter para continuar...");

		try {
			System.in.read(); // Espera o usuário pressionar Enter

		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Programa continuado.");
		return "";
	}
	
}