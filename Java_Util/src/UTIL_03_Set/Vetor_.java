package UTIL_03_Set;

import java.util.Set;
import java.util.HashSet;
import java.util.Scanner;

import util.Linhas;

public class Vetor_ {
	public static void Vetor_Run() {

		Linhas linhas = new Linhas();
		String collec = "\n\t Set: Representa um conjunto (não permite elementos duplicados). \n";
		System.err.println(collec);

		linhas.run_Caracteres();
		Import_1V.run_Import_1V();

		linhas.run_Caracteres();
		Import_2V.run_Import_2V();

		linhas.run_Caracteres();
		Import_3V.run_Import_3V();
	}
}

//Definição do Enum com Status de Tarefa
enum Status1 {
	PENDENTE, EM_PROGRESSO, CONCLUIDO
}

//Definição de uma Anotação Customizada
@interface Informacao {
	String autor();

	String versao();
}

//Classe que usa a anotação e implementa a lógica
@Informacao(autor = "SeuNome", versao = "1.0")
class Import_1V {
	public static void run_Import_1V() {
		System.err.println(
				"\t\t1. Ele gerencia um conjunto de tarefas com um Enum de status, usa Set para armazenar os status únicos\n");

		// Vetor de Tarefas
		String[] tarefas = { "Estudar Java", "Fazer exercícios", "Revisar código" };

		// Usando Set para armazenar status únicos
		Set<Status1> statusSet = new HashSet<>();
		statusSet.add(Status1.PENDENTE);
		statusSet.add(Status1.EM_PROGRESSO);
		statusSet.add(Status1.CONCLUIDO);

		// Percorrendo o vetor com for
		for (int i = 0; i < tarefas.length; i++) {
			System.out.println("Tarefa: " + tarefas[i]);

			// Escolhendo um status aleatório para a tarefa
			Status1 status1 = (i % 2 == 0) ? Status1.PENDENTE : Status1.CONCLUIDO;

			// Switch para verificar o status
			switch (status1) {
			case PENDENTE:
				System.out.println("Status: PENDENTE - Precisa iniciar!");
				break;
			case EM_PROGRESSO:
				System.out.println("Status: EM PROGRESSO - Continue trabalhando!");
				break;
			case CONCLUIDO:
				System.out.println("Status: CONCLUÍDO - Parabéns!");
				break;
			}
		}

		System.out.println("\n=== Verificando Status com Do-While ===");
		int index = 0;
		do {
			System.out.println("Verificando tarefa: " + tarefas[index]);
			index++;
		} while (index < tarefas.length);
	}
}

//Definição da anotação com um valor float
@interface NivelInfo {
	float prioridade();
}

//Enum com Annotation
enum NivelAcesso {
	@NivelInfo(prioridade = 1.0f)
	ADMIN(Set.of("LER", "ESCREVER", "DELETAR")),

	@NivelInfo(prioridade = 0.7f)
	MODERADOR(Set.of("LER", "ESCREVER")),

	@NivelInfo(prioridade = 0.4f)
	USUARIO(Set.of("LER"));

	private final Set<String> permissoes;

	NivelAcesso(Set<String> permissoes) {
		this.permissoes = new HashSet<>(permissoes);
	}

	public Set<String> getPermissoes() {
		return permissoes;
	}
}

class Import_2V {
	public static void run_Import_2V() {
		System.err.println(
				"\t\t2. @interface NivelInfo → Define uma anotação personalizada para armazenar a prioridade de cada nível.\n");

		// Criando um vetor com os níveis de acesso
		NivelAcesso[] niveis = NivelAcesso.values();

		int i = 0;
		do {
			NivelAcesso nivel = niveis[i];
			System.out.println("\nNível: " + nivel);

			// Usando switch-case
			switch (nivel) {
			case ADMIN:
				System.out.println("Prioridade Alta");
				break;
			case MODERADOR:
				System.out.println("Prioridade Média");
				break;
			case USUARIO:
				System.out.println("Prioridade Baixa");
				break;
			default:
				System.out.println("Desconhecido");
			}

			// Exibir permissões usando for
			System.out.print("Permissões: ");
			for (String permissao : nivel.getPermissoes()) {
				System.out.print(permissao + " ");
			}
			System.out.println();

			i++;
		} while (i < niveis.length);

	}
}

//Enum para definir os tipos de usuário
enum UserRole {
	ADMIN, USER, GUEST;
}

//Annotation personalizada para Login
@interface LoginInfo {
	String message() default "Sistema de Login";
}

//Classe de Usuário
class User {
	String username;
	String password;
	UserRole role;

	public User(String username, String password, UserRole role) {
		this.username = username;
		this.password = password;
		this.role = role;
	}
}

//Classe principal
@LoginInfo
class Import_3V {
	public static void run_Import_3V() {
		System.err.println("\t\t3. \n");

		Scanner scanner = new Scanner(System.in);
		Set<User> userSet = new HashSet<>();

		// Vetor de usuários pré-definidos
		User[] users = { 
				new User("admin", "1234", UserRole.ADMIN), 
				new User("user", "abcd", UserRole.USER),
				new User("guest", "convidada", UserRole.GUEST) };

		// Adiciona os usuários no conjunto Set
		for (User user : users) {
			userSet.add(user);
		}

		boolean authenticated = false;
		do {
			System.out.print("Usuário: ");
			String inputUser = scanner.nextLine();
			System.out.print("Senha: ");
			String inputPass = scanner.nextLine();

			for (User user : userSet) {
				if (user.username.equals(inputUser) && user.password.equals(inputPass)) {
					authenticated = true;
					System.out.println("Login bem-sucedido!");
					handleUserRole(user.role);
					break;
				}
			}

			if (!authenticated) {
				System.out.println("Credenciais inválidas. Tente novamente.");
			}
		} while (!authenticated);

		scanner.close();
	}

	// Método para processar o tipo de usuário
	public static void handleUserRole(UserRole role) {
		switch (role) {
		case ADMIN:
			System.out.println("Bem-vindo, Admin! Acesso total concedido.");
			break;
		case USER:
			System.out.println("Bem-vindo, Usuário! Acesso limitado.");
			break;
		case GUEST:
			System.out.println("Bem-vindo, Convidado! Acesso somente leitura.");
			break;
		default:
			System.out.println("Papel de usuário desconhecido.");
		}

	}
}