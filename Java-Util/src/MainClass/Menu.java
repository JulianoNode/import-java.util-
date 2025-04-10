package MainClass;

import java.util.Scanner;

import UTIL_01_Collection.Run_Collection;
import UTIL_02_List.Run_List;
import UTIL_03_Set.Run_Set;
import UTIL_04_Map.Run_Map;
import UTIL_05_Queue.Run_Queue;
import UTIL_06_Deque.Run_Deque;
import UTIL_07_Collections.Run_Collections;
import UTIL_08_Arrays.Run_Arrays;
import UTIL_09_Iterator.Run_Iterator;
import UTIL_10_ListInterator.Run_ListIterator;
import UTIL_11_ArrayList.Run_ArrayList;
import UTIL_12_LinkedList.Run_LinkedList;
import util.LimparConsole;
import util.Pausar;


public class Menu {
	public static void  Meun_Run() throws Exception {

		Pausar pausar = new Pausar();
		LimparConsole clear = new LimparConsole();
		Scanner scanner = new Scanner(System.in);

		// Vetor de opções do menu
		String[] menuOptions = {"\t☕✔️📌 Interfaces de Coleções: \n\n" + 
						"1. 👉 UTIL =  Collection<E>: A interface raiz para a hierarquia de coleções.",
						"👉 UTIL =  List<E>: Representa uma lista ordenada.",
						"👉 UTIL =  Set<E>: Representa um conjunto (não permite elementos duplicados).",
						"👉 UTIL =  Map<K, V>: Representa um mapeamento de chave-valor.",
						"👉 UTIL =  Queue<E>: Representa uma fila (FIFO).",
						"👉 UTIL =  Deque<E>: Representa uma fila dupla (pode adicionar/remover de ambas as extremidades).\n\n"+
						"2.\t☕✔️📌 Classes Auxiliares: \n\n", 
						"👉 UTIL =  Collections: Métodos utilitários para trabalhar com coleções (ex.: ordenação, busca, sincronização).",
						"👉 UTIL =  Arrays: Métodos utilitários para trabalhar com arrays (ex.: ordenação, busca",
						"👉 UTIL =  Iterator<E>: Interface para iterar sobre elementos de uma coleção.",
						"👉 UTIL =  ListIterator<E>: Iterador para listas que permite percorrer em ambas as direções.\n\n"+
						"3.\t☕✔️📌 Implementações das Interfaces: \n\n", 
						"👉 UTIL =  ArrayList<E>: Uma lista baseada em array dinâmico.",
						"👉 UTIL =  LinkedList<E>: Lista duplamente encadeada.",
						"👉 UTIL =  HashSet<E>: Implementação de Set baseada em tabela de hash.",
						"👉 UTIL =  LinkedHashSet<E>: Variante de HashSet que mantém a ordem de inserção.",
						"👉 UTIL =  TreeSet<E>: Implementação de Set ordenada (baseada em árvore).",
						"👉 UTIL =  HashMap<K, V>: Implementação de Map baseada em tabela de hash.",
						"👉 UTIL =  LinkedHashMap<K, V>: Variante de HashMap que mantém a ordem de inserção.",
						"👉 UTIL =  TreeMap<K, V>: Implementação de Map ordenada (baseada em árvore).",
						"👉 UTIL =  PriorityQueue<E>: Fila de prioridade.\n\n"+
						"4.\t☕✔️📌 Datas e Tempo: \n\n", 
						"👉 UTIL =  Date: Representa um ponto no tempo (obsoleto em favor de java.time).",
						"👉 UTIL =  Calendar: Classe abstrata para manipular datas e horas.",
						"👉 UTIL =  TimeZone: Representa fusos horários.",
						"👉 UTIL =  GregorianCalendar: Implementação concreta de Calendar.\n\n"+
						"5.\t☕✔️📌 Geradores de Números Aleatórios: \n\n", 
						"👉 UTIL =  Random: Classe para gerar números aleatórios.",
						"👉 UTIL =  SplittableRandom: Gerador de números aleatórios para paralelismo eficiente.\n\n"+
						"6.\t☕✔️📌 Outras Classes Úteis: \n\n",
						"👉 UTIL =  Scanner: Para entrada de dados (ex.: ler do console, arquivos).",
						"👉 UTIL =  Properties: Armazena pares chave-valor em arquivos de propriedades.",
						"👉 UTIL =  Optional<T>: Uma classe que representa um valor que pode ou não estar presente (para evitar null).\n\n"+
						"7.\t☕✔️📌 Outras Estruturas de Dados \n\n", 
						"👉 UTIL =  BitSet: Uma matriz de bits eficiente.",
						"👉 UTIL =  Stack<E>: Implementação de pilha baseada em Vector.",
						"👉 UTIL =  Vector<E>: Lista dinâmica sincronizada (menos usada que ArrayList).\n\n",
						"👋 Sair 👋 \n" };

		int choice;
		do {
			// Exibindo o menu
			System.out.println("\n\t=== 📌 Lista Completa das Classes do IMPORT JAVA.NET 📌===\n");
			for (int i = 0; i < menuOptions.length; i++) {
				System.out.println((i + 1) + ". " + menuOptions[i]);
			}

			System.out.print("Escolha uma opção do MENU: ");
			choice = scanner.nextInt();
			scanner.nextLine(); // Consumir o '\n'

			switch (choice) {
			case 1:
				Run_Collection.Start_Collection();	
				pausar.run_Pause();
				clear.CleanConsole_Run();
				break;

			case 2:
				Run_List.Start_List();
				pausar.run_Pause();
				clear.CleanConsole_Run();
				break;

			case 3:
				Run_Set.Start_Set();
				pausar.run_Pause();
				clear.CleanConsole_Run();
				break;

			case 4:
				Run_Map.Start_Map();
				pausar.run_Pause();
				clear.CleanConsole_Run();
				break;

			case 5:
				Run_Queue.Start_Queue();
				pausar.run_Pause();
				clear.CleanConsole_Run();
				break;

			case 6:
				Run_Deque.Start_Deque();
				pausar.run_Pause();
				clear.CleanConsole_Run();
				break;
			case 7:
				Run_Collections.Start_Collections();
				pausar.run_Pause();
				clear.CleanConsole_Run();
				break;
			case 8:
				Run_Arrays.Start_Arrays();
				pausar.run_Pause();
				clear.CleanConsole_Run();
				break;
			case 9:
				Run_Iterator.Start_Iterator();
				pausar.run_Pause();
				clear.CleanConsole_Run();
				break;
			case 10:
				Run_ListIterator.Start_ListIterator();
				pausar.run_Pause();
				clear.CleanConsole_Run();
				break;
			case 11:
				Run_ArrayList.Start_ArrayList();
				pausar.run_Pause();
				clear.CleanConsole_Run();
				break;
			case 12:
				Run_LinkedList.Start_LinkedList();
				pausar.run_Pause();
				clear.CleanConsole_Run();
				break;
			case 13:
				pausar.run_Pause();
				clear.CleanConsole_Run();
				break;
			case 14:
				pausar.run_Pause();
				clear.CleanConsole_Run();
				break;
			case 15:
				pausar.run_Pause();
				clear.CleanConsole_Run();
				break;
			case 16:
				pausar.run_Pause();
				clear.CleanConsole_Run();
				break;
			case 17:
				pausar.run_Pause();
				clear.CleanConsole_Run();
				break;
			case 18:
				pausar.run_Pause();
				clear.CleanConsole_Run();
				break;
			case 19:
				pausar.run_Pause();
				clear.CleanConsole_Run();
				break;
			case 20:
				pausar.run_Pause();
				clear.CleanConsole_Run();
				break;
			case 21:
				pausar.run_Pause();
				clear.CleanConsole_Run();
				break;
			case 22:
				pausar.run_Pause();
				clear.CleanConsole_Run();
				break;

			case 23:
				pausar.run_Pause();
				clear.CleanConsole_Run();
				break;

			case 24:
				pausar.run_Pause();
				clear.CleanConsole_Run();
				break;

			case 25:
				pausar.run_Pause();
				clear.CleanConsole_Run();
				break;

			case 26:
				pausar.run_Pause();
				clear.CleanConsole_Run();
				break;

			case 27:
				pausar.run_Pause();
				clear.CleanConsole_Run();
				break;

			case 28:
				pausar.run_Pause();
				clear.CleanConsole_Run();
				break;

			case 29:
				pausar.run_Pause();
				clear.CleanConsole_Run();
				break;

			case 30:
				pausar.run_Pause();
				clear.CleanConsole_Run();
				break;
			
			case 31: // Sair
				System.out.println("Encerrando o programa...");
				System.exit(0);
				break;
			
			default:
				System.out.println("Opção inválida. Tente novamente.");
				break;
			}
		} while (choice != 31);
		scanner.close();
	}

}
