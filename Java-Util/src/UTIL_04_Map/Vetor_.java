package UTIL_04_Map;

import util.Linhas;
import util.VoutarRun;

import java.util.*;

public class Vetor_ {
	public static void Vetor_Run() {

		Linhas linhas = new Linhas();
		String collec = "\n\t Map<K, V>: Representa um mapeamento de chave-valor. \n";
		System.err.println(collec);

		linhas.run_Caracteres();
		Import_1V.run_Import_1V();

		//linhas.run_Caracteres();
		//Import_2V.run_Import_2V();

		//linhas.run_Caracteres();
		//Import_3V.run_Import_3V();
	}
}

class Import_1V {

	// Enum para os tipos de usuários
	enum TipoUsuario {
		ADMIN("admin"), USUARIO("usuario"), GUEST("guest");

		private final String tipo;

		TipoUsuario(String tipo) {
			this.tipo = tipo;
		}

		public String getTipo() {
			return this.tipo;
		}
	}

	public static void run_Import_1V() {
		System.err.println("\t\t1. Aqui está um exemplo de código Java que implementa um sistema de login "
				+ "utilizando vetores, matrizes, do-while, switch-case, if-else, for, Map, e Enum com o tema de login:\n");

		System.out.println("admin, admin123");
		System.out.println("usuario, user123");
		System.out.println("guest,guest123");
		// Map para armazenar as credenciais (usuário: senha)
		Map<String, String> usuarios = new HashMap<>();
		usuarios.put("admin", "admin123");
		usuarios.put("usuario", "user123");
		usuarios.put("guest", "guest123");

		// Vetor com o nome de usuários permitidos
		String[] nomesUsuarios = { "admin", "usuario", "guest" };

		Scanner scanner = new Scanner(System.in);
		boolean loginBemSucedido = false;
		String usuarioDigitado, senhaDigitada;

		// Loop de login
		do {
			System.out.println("Digite o nome de usuário:");
			usuarioDigitado = scanner.nextLine();

			// Verifica se o nome de usuário é válido
			if (Arrays.asList(nomesUsuarios).contains(usuarioDigitado)) {
				System.out.println("Digite a senha:");
				senhaDigitada = scanner.nextLine();

				// Verifica se a senha corresponde
				if (usuarios.containsKey(usuarioDigitado) && usuarios.get(usuarioDigitado).equals(senhaDigitada)) {
					System.out.println("Login bem-sucedido!");
					loginBemSucedido = true;
				} else {
					System.out.println("Senha incorreta. Tente novamente.");
				}
			} else {
				System.out.println("Usuário não encontrado. Tente novamente.");
			}
		} while (!loginBemSucedido);

		// Após o login, o menu de escolha de tipo de usuário aparece
		if (loginBemSucedido) {
			System.out.println("Escolha o tipo de usuário:");
			System.out.println("1 - Admin");
			System.out.println("2 - Usuario");
			System.out.println("3 - Guest");

			int escolha = scanner.nextInt();
			scanner.nextLine(); // Consumir a quebra de linha

			switch (escolha) {
			case 1:
				System.out.println("Bem-vindo, Admin!");
				// Ações específicas para admin
				break;
			case 2:
				System.out.println("Bem-vindo, Usuario!");
				// Ações específicas para usuário
				break;
			case 3:
				System.out.println("Bem-vindo, Guest!");
				// Ações específicas para guest
				break;
			default:
				System.out.println("Opção inválida.");
				break;
			}
		}

		// Exemplo de uso do loop for
		System.out.println("\nLista de usuários cadastrados:");
		for (String nome : usuarios.keySet()) {
			System.out.println("- " + nome);
		}


		VoutarRun voutarRun = new VoutarRun();
		voutarRun.run_CaracteresMenu_Red();

	}
}

enum StatusEmail {
	ENVIADO("Enviado"), PENDENTE("Pendente"), FALHOU("Falhou");

	private String descricao;

	StatusEmail(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}

class Import_2V {
	public static void run_Import_2V() {
		System.err.println("\t\t2. Aqui está um exemplo de código em Java utilizando os conceitos que você mencionou, "
				+ "com o tema de e-mail. O código usa um vetor, uma matriz, do-while, switch-case, if, else if, for, Map, e Enum.\n");
		// Criando o Map de emails
		Map<String, StatusEmail> emails = new HashMap<>();

		// Adicionando e-mails ao Map
		emails.put("usuario1@dominio.com", StatusEmail.PENDENTE);
		emails.put("usuario2@dominio.com", StatusEmail.ENVIADO);
		emails.put("usuario3@dominio.com", StatusEmail.FALHOU);

		// Vetor para armazenar os emails que foram enviados
		String[] enviados = new String[emails.size()];
		int contadorEnviados = 0;

		// Matrix para simular várias tentativas de envio de e-mails
		String[][] tentativas = new String[3][2]; // 3 tentativas, cada uma com status de envio

		// Populando a matriz de tentativas
		for (int i = 0; i < tentativas.length; i++) {
			for (int j = 0; j < tentativas[i].length; j++) {
				tentativas[i][j] = "Tentativa " + (i + 1) + " status: " + (j + 1);
			}
		}

		// Loop de interação
		boolean continuar = true;
		do {
			System.out.println("Selecione uma opção:");
			System.out.println("1 - Ver status de emails");
			System.out.println("2 - Enviar email");
			System.out.println("3 - Ver tentativas de envio");
			System.out.println("4 - Sair");

			int opcao = 1; // Suponha que a entrada seja 1 para fins de demonstração

			switch (opcao) {
			case 1:
				// Verificar status de todos os e-mails
				for (Map.Entry<String, StatusEmail> entry : emails.entrySet()) {
					String email = entry.getKey();
					StatusEmail status = entry.getValue();
					System.out.println("Email: " + email + " | Status: " + status.getDescricao());
				}
				break;
			case 2:
				// Enviar e-mail
				String emailParaEnviar = "usuario4@dominio.com";
				if (emails.containsKey(emailParaEnviar)) {
					System.out.println("Email já existe: " + emailParaEnviar);
				} else {
					emails.put(emailParaEnviar, StatusEmail.PENDENTE);
					System.out.println("E-mail " + emailParaEnviar + " agora está na fila de envio.");
				}
				break;
			case 3:
				// Mostrar tentativas de envio
				for (int i = 0; i < tentativas.length; i++) {
					for (int j = 0; j < tentativas[i].length; j++) {
						System.out.println(tentativas[i][j]);
					}
				}
				break;
			case 4:
				continuar = false;
				break;
			default:
				System.out.println("Opção inválida.");
			}
		} while (continuar);

		// Verificar emails enviados
		for (Map.Entry<String, StatusEmail> entry : emails.entrySet()) {
			if (entry.getValue() == StatusEmail.ENVIADO) {
				enviados[contadorEnviados] = entry.getKey();
				contadorEnviados++;
			}
		}

		// Mostrar os e-mails enviados
		if (contadorEnviados > 0) {
			System.out.println("Emails enviados:");
			for (int i = 0; i < contadorEnviados; i++) {
				System.out.println(enviados[i]);
			}
		} else {
			System.out.println("Nenhum e-mail enviado.");
		}

	}
}

class Import_3V {
	public static void run_Import_3V() {
		System.err.println("\t\t3. Aqui está um exemplo de código em Java que utiliza vetores, matrizes, "
				+ "\nestruturas de controle como do-while, switch-case, if-else-if, laços for, Map, e Enum para "
				+ "\nvalidar e armazenar informações de CPF e CNPJ. O código tem como foco o uso de Map para "
				+ "\narmazenar dados e enumerações para a distinção de tipos de documentos.\n");

		Scanner scanner = new Scanner(System.in);
		String input;
		TipoDocumento tipo;

		do {
			System.out.println("Digite um CPF ou CNPJ para validar (ou 'sair' para encerrar):");
			input = scanner.nextLine();

			// Verifica se o usuário deseja sair
			if (input.equalsIgnoreCase("sair")) {
				break;
			}

			// Switch para determinar se o input é CPF ou CNPJ
			switch (input.length()) {
			case 11:
				tipo = TipoDocumento.CPF;
				if (validarCPF(input)) {
					System.out.println("CPF " + input + " é válido.");
					documentos.put(input, tipo); // Armazena no mapa
				} else {
					System.out.println("CPF " + input + " é inválido.");
				}
				break;
			case 14:
				tipo = TipoDocumento.CNPJ;
				if (validarCNPJ(input)) {
					System.out.println("CNPJ " + input + " é válido.");
					documentos.put(input, tipo); // Armazena no mapa
				} else {
					System.out.println("CNPJ " + input + " é inválido.");
				}
				break;
			default:
				System.out.println("Número inválido! Digite um CPF ou CNPJ com o tamanho correto.");
				break;
			}

			// Mostra todos os documentos armazenados
			System.out.println("\nDocumentos armazenados:");
			for (Map.Entry<String, TipoDocumento> entry : documentos.entrySet()) {
				System.out.println("Documento: " + entry.getKey() + " - Tipo: " + entry.getValue());
			}

			System.out.println("\nDeseja continuar? (s/n):");
			String continuar = scanner.nextLine();
			if (continuar.equalsIgnoreCase("n")) {
				break;
			}

		} while (true);


		VoutarRun voutarRun = new VoutarRun();
		voutarRun.run_CaracteresMenu_Red();
		System.out.println("Programa encerrado.");

	}

	// Enum para diferenciar tipos de documentos
	public enum TipoDocumento {
		CPF, CNPJ
	}

	// Mapa para armazenar documentos válidos
	static Map<String, TipoDocumento> documentos = new HashMap<>();

	// Função de validação de CPF (simples exemplo de validação)
	public static boolean validarCPF(String cpf) {
		if (cpf.length() != 11)
			return false;
		// Exemplo básico: verificar se todos os dígitos são iguais
		for (int i = 1; i < cpf.length(); i++) {
			if (cpf.charAt(i) != cpf.charAt(0)) {
				return true;
			}
		}
		return false;
	}

	// Função de validação de CNPJ (simples exemplo de validação)
	public static boolean validarCNPJ(String cnpj) {
		if (cnpj.length() != 14)
			return false;
		// Exemplo básico: verificar se todos os dígitos são iguais
		for (int i = 1; i < cnpj.length(); i++) {
			if (cnpj.charAt(i) != cnpj.charAt(0)) {
				return true;
			}
		}
		return false;
	}
}