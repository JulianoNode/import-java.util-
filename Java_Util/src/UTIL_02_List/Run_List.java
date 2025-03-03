package UTIL_02_List;

import java.util.Scanner;

import MainClass.Menu;
import util.LimparConsole;
import util.Pausar;
import util.VoutarMenu;

public class Run_List {
	@SuppressWarnings("unused")
	public static void Start_List() throws Exception {

		Pausar pausar = new Pausar();
		LimparConsole clear = new LimparConsole();
		Scanner scanner = new Scanner(System.in);

		// Vetor de opções do menu
		String[] menuOptions = { "📌 List = Simple", "📌 List = If e else if",
				"📌 List = For com Matrix", "📌 List = do while com Matrix", "📌 List = Vetor",
				"⏪ Voutar para o menu" };

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

			case 6: // Voutar para o MEUNU
				VoutarMenu voutarMenu = new VoutarMenu();
				voutarMenu.run_CaracteresMenu_Red();
				System.out.println("\n\n");
				Menu.Meun_Run();

				break;

			default:
				System.out.println("Opção inválida. Tente novamente.");
				break;
			}
		} while (choice != 7);
		scanner.close();

	}
}
