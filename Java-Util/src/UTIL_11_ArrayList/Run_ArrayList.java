package UTIL_11_ArrayList;

import java.util.Scanner;

import MainClass.Menu;
import util.LimparConsole;
import util.Pausar;
import util.VoutarMenu;
import util.VoutarRun;

public class Run_ArrayList {

	@SuppressWarnings({ "unused", "resource" })
	public static void Start_ArrayList() throws Exception {

		Pausar pausar = new Pausar();
		LimparConsole clear = new LimparConsole();
		Scanner scanner = new Scanner(System.in);

		// Vetor de opções do menu
		String[] menuOptions = { "📌 ArrayList = Simple", "📌 ArrayList = If e else if",
				"📌 ArrayList = For com Matrix", "📌 ArrayList = do while com Matrix", "📌 ArrayList = Vetor",
				"📌 TransacaoDeConta", "⏪ Voutar para o menu", "👋 Sair 👋 \n" };

		int choice;
		do {
			// Exibindo o menu
			System.out.println("\n\t=== Import java.util.* ===\n");
			for (int i = 0; i < menuOptions.length; i++) {
				System.out.println((i + 1) + ". " + menuOptions[i]);
			}

			System.out.print("Escolha uma opção: ");
			choice = scanner.nextInt();
			scanner.nextLine(); // Consumir o '\n'

			switch (choice) {

			case 1:
				Simples.Simples_Run();
				break;

			case 2:
				If_Else_IF.If_Else_IF_Run();
				break;

			case 3:
				For_Matrix.For_Run();
				break;

			case 4:
				While_Matrix.While_Run();
				break;

			case 5:
				Vetor_.Vetor_Run();
				break;

			case 6:
				TransacaoDeConta.TransacaoDeConta_Run();
				break;

			case 7: // Voutar para o MEUNU
				VoutarMenu voutarMenu = new VoutarMenu();
				voutarMenu.run_CaracteresMenu_Red();
				System.out.println("\n\n");
				Menu.Meun_Run();

				break;
			case 8:
				VoutarRun voutarRun = new VoutarRun();
				voutarRun.run_CaracteresMenu_Red();
				break;

			default:
				System.out.println("Opção inválida. Tente novamente.");
				break;
			}
		} while (choice != 7);

		VoutarRun voutarRun = new VoutarRun();
		voutarRun.run_CaracteresMenu_Red();
	}
}
