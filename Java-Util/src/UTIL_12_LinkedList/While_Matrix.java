package UTIL_12_LinkedList;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

import util.Linhas;
import util.VoutarRun;

public class While_Matrix {
	public static void While_Run() {
		Linhas linhas = new Linhas();
		String collec = "\n\t Nome \n";
		System.err.println(collec);

		linhas.run_Caracteres();
		Import_1WM.run_Import_1WM();

		linhas.run_Caracteres();
		Import_2WM.run_Import_2WM();

		linhas.run_Caracteres();
		Import_3WM.run_Import_3WM();

	}
}

//Corpo da Classe _______________________________

// ENUM 1: País
enum VPNCountry {
	EUA("198.51.100.1"), BRASIL("203.0.113.2"), ALEMANHA("192.0.2.3"), JAPAO("203.0.113.4");

	private String ip;

	VPNCountry(String ip) {
		this.ip = ip;
	}

	public String getIP() {
		return ip;
	}
}

// ENUM 2: Protocolo
enum VPNProtocol {
	  OPENVPN("OpenVPN"),
	    L2TP("L2TP/IPSec"),
	    PPTP("PPTP"),
	    IKEV2("IKEv2");

	    private final String displayName;

	    // Construtor do enum para armazenar um nome mais amigável
	    VPNProtocol(String displayName) {
	        this.displayName = displayName;
	    }

	    // Retorna o nome amigável do protocolo
	    public String getDisplayName() {
	        return displayName;
	    }

	    // Retorna o nome completo do protocolo com sufixo "_SECURE"
	    public String getProtocolName() {
	        return name() + "_SECURE";
	    }

	    @Override
	    public String toString() {
	        return displayName;
	    }
}

// ENUM 3: Status
enum VPNStatus {
	CONECTADO, DESCONECTADO, ERRO;

	public boolean isActive() {
		return this == CONECTADO;
	}
}

// ENUM 4: Log
enum VPNLog {
	LOGIN, LOGOUT, TIMEOUT, FALHA;

	public void printLog() {
		System.out.println("LOG => Ação: " + this.name());
	}
}

// ENUM 5: Segurança
enum VPNSecurity {
	ALTA, MEDIA, BAIXA, CRITICA;

	public String nivel() {
		return switch (this) {
		case ALTA -> "TLS 1.3";
		case MEDIA -> "TLS 1.2";
		case BAIXA -> "SSL 3.0";
		case CRITICA -> "Inseguro";
		};
	}
}

// Classe abstrata
abstract class VPNConnection {
	protected VPNCountry pais;
	protected VPNProtocol protocolo;
	protected VPNStatus status;

	public VPNConnection(VPNCountry pais, VPNProtocol protocolo) {
		this.pais = pais;
		this.protocolo = protocolo;
		this.status = VPNStatus.DESCONECTADO;
	}

	public abstract void conectar();

	public abstract void desconectar();
}

// Classe concreta
class SessaoVPN extends VPNConnection {
	public SessaoVPN(VPNCountry pais, VPNProtocol protocolo) {
		super(pais, protocolo);
	}

	@Override
	public synchronized void conectar() {
		System.out.println("Conectando a " + pais + " via " + protocolo.getProtocolName() + "...");
		status = VPNStatus.CONECTADO;
		VPNLog.LOGIN.printLog();
		System.out.println("IP HTTPS: " + pais.getIP() + " [" + VPNSecurity.ALTA.nivel() + "]");
	}

	@Override
	public synchronized void desconectar() {
		System.out.println("Desconectando de " + pais + "...");
		status = VPNStatus.DESCONECTADO;
		VPNLog.LOGOUT.printLog();
	}
}

class VPNHard {
	public static void mainVPN() {
		Scanner sc = new Scanner(System.in);
		LinkedList<SessaoVPN> filaVPN = new LinkedList<>();

		// Matriz de IPs [País][2 regiões]
		String[][] vpnIPs = { { "198.51.100.1", "198.51.100.2" }, // EUA
				{ "203.0.113.2", "203.0.113.5" }, // Brasil
				{ "192.0.2.3", "192.0.2.8" }, // Alemanha
				{ "203.0.113.4", "203.0.113.9" } // Japão
		};

		int opcao, tentativas = 0;
		while (tentativas < 5) {
			System.out.println("\nEscolha o país para conexão VPN:");
			System.out.println("1 - EUA\n2 - BRASIL\n3 - ALEMANHA\n4 - JAPÃO\n0 - Sair");
			opcao = sc.nextInt();

			if (opcao == 0)
				break;

			VPNCountry paisEscolhido = null;
			switch (opcao) {
			case 1 -> paisEscolhido = VPNCountry.EUA;
			case 2 -> paisEscolhido = VPNCountry.BRASIL;
			case 3 -> paisEscolhido = VPNCountry.ALEMANHA;
			case 4 -> paisEscolhido = VPNCountry.JAPAO;
			default -> {
				System.out.println("Opção inválida!");
				continue;
			}
			}

			SessaoVPN sessao = new SessaoVPN(paisEscolhido, VPNProtocol.OPENVPN);
			sessao.conectar();
			filaVPN.add(sessao);

			System.out.println("IPs disponíveis:");
			for (int i = 0; i < vpnIPs[opcao - 1].length; i++) {
				System.out.println("Região " + (i + 1) + ": " + vpnIPs[opcao - 1][i]);
			}

			tentativas++;
		}

		System.out.println("\nDesconectando todas as sessões...");
		for (SessaoVPN sessao : filaVPN) {
			sessao.desconectar();
		}

		System.out.println("\nFinalizado.");
	}
}

class Import_1WM {
	public static void run_Import_1WM() {
		System.err.println("\t\t1.\n");
		VPNHard.mainVPN(); 
	}
}

//Corpo da Classe _______________________________

// Enums com métodos
enum VPNCountrys {
	USA("us.vpn.server.com"), BRAZIL("br.vpn.server.com"), GERMANY("de.vpn.server.com"), JAPAN("jp.vpn.server.com");

	private final String server;

	VPNCountrys(String server) {
		this.server = server;
	}

	public String getServer() {
		return server;
	}

	public void connectMessage() {
		System.out.println("Conectando-se ao servidor: " + server);
	}
}

enum ConnectionStatus {
	CONNECTED, DISCONNECTED, ERROR, PENDING, TIMEOUT;

	public String statusInfo() {
		return switch (this) {
		case CONNECTED -> "Conexão estabelecida com sucesso!";
		case DISCONNECTED -> "VPN desconectada.";
		case ERROR -> "Erro ao conectar.";
		case PENDING -> "Conexão pendente...";
		case TIMEOUT -> "Tempo de conexão esgotado!";
		};
	}
}

enum EncryptionLevel {
	LOW(128), MEDIUM(192), HIGH(256), ULTRA(512), MAX(1024);

	private final int bits;

	EncryptionLevel(int bits) {
		this.bits = bits;
	}

	public int getBits() {
		return bits;
	}

	public void showLevel() {
		System.out.println("Nível de Criptografia: " + bits + " bits");
	}
}

enum Protocol {
	PPTP, L2TP, OPENVPN, IKEv2, WIREGUARD;

	public void showProtocol() {
		System.out.println("Usando protocolo: " + this.name());
	}
}

enum LogLevel {
	INFO, WARNING, DEBUG, ERROR, CRITICAL;

	public void log(String message) {
		System.out.println("[" + this.name() + "]: " + message);
	}
}

// Classe abstrata
abstract class VPNConnections {
	protected VPNCountrys country;
	protected ConnectionStatus status;

	public VPNConnections(VPNCountrys country) {
		this.country = country;
		this.status = ConnectionStatus.PENDING;
	}

	public abstract void establish();

	public abstract void disconnect();
}

// Implementação da classe abstrata
class VPNService extends VPNConnections {
	private final EncryptionLevel encryption;
	private final Protocol protocol;

	public VPNService(VPNCountrys country, EncryptionLevel encryption, Protocol protocol) {
		super(country);
		this.encryption = encryption;
		this.protocol = protocol;
	}

	@Override
	public synchronized void establish() {
		LogLevel.INFO.log("Estabelecendo conexão com " + country.name());
		country.connectMessage();
		encryption.showLevel();
		protocol.showProtocol();
		status = ConnectionStatus.CONNECTED;
		System.out.println(status.statusInfo());
	}

	@Override
	public synchronized void disconnect() {
		LogLevel.WARNING.log("Desconectando da VPN " + country.name());
		status = ConnectionStatus.DISCONNECTED;
		System.out.println(status.statusInfo());
	}
}

class VPNMain {
	public static void mainVPN() {
		Scanner sc = new Scanner(System.in);
		LinkedList<VPNService> vpnList = new LinkedList<>();

		String[][] vpnMatrix = { { "USA", "BRAZIL" }, { "GERMANY", "JAPAN" } };

		int option;
		do {
			System.out.println("\n==== MENU VPN ====");
			System.out.println("1 - Listar Países");
			System.out.println("2 - Conectar");
			System.out.println("3 - Desconectar");
			System.out.println("4 - Sair");
			System.out.print("Escolha: ");
			option = sc.nextInt();

			switch (option) {
			case 1 -> {
				System.out.println("=== Países Disponíveis ===");
				for (int i = 0; i < vpnMatrix.length; i++) {
					for (int j = 0; j < vpnMatrix[i].length; j++) {
						System.out.print(vpnMatrix[i][j] + "\t");
					}
					System.out.println();
				}
			}

			case 2 -> {
				System.out.println("Escolha um país para conectar: [0-USA, 1-BRAZIL, 2-GERMANY, 3-JAPAN]");
				int pais = sc.nextInt();
				VPNCountrys country = switch (pais) {
				case 0 -> VPNCountrys.USA;
				case 1 -> VPNCountrys.BRAZIL;
				case 2 -> VPNCountrys.GERMANY;
				case 3 -> VPNCountrys.JAPAN;
				default -> null;
				};

				if (country != null) {
					VPNService vpn = new VPNService(country, EncryptionLevel.HIGH, Protocol.OPENVPN);
					vpn.establish();
					vpnList.add(vpn);
				} else {
					LogLevel.ERROR.log("País inválido!");
				}
			}

			case 3 -> {
				if (vpnList.isEmpty()) {
					LogLevel.WARNING.log("Nenhuma conexão ativa.");
				} else {
					VPNService vpn = vpnList.removeFirst();
					vpn.disconnect();
				}
			}

			case 4 -> System.out.println("Encerrando o sistema...");
			default -> LogLevel.CRITICAL.log("Opção inválida!");
			}

		} while (option != 4);
		VoutarRun voutarRun = new VoutarRun();
		voutarRun.run_CaracteresMenu_Red();
	}
}

class Import_2WM {
	public static void run_Import_2WM() {
		System.err.println("\t\t2.\n");
		VPNMain.mainVPN();
	}
}
//Corpo da Classe _______________________________

// Enum com métodos para cada moeda
enum Moedak {
	EURO(0.91), LIBRA(0.78), IENE(110.57), REAL(5.10), DOLAR_CANADENSE(1.35), PESO_MEXICANO(17.12), FRANCO_SUIÇO(0.89);

	private final double taxaConversao;

	Moedak(double taxa) {
		this.taxaConversao = taxa;
	}

	public double converter(double valorUSD) {
		return valorUSD * taxaConversao;
	}

	public static void listarMoedas() {
		System.out.println("Moedas disponíveis:");
		for (Moedak m : Moedak.values()) {
			System.out.println("- " + m.name());
		}
	}
}

// Classe abstrata
abstract class Conversork {
	public abstract double converter(Moedak moeda, double valor);

	public abstract void sincronizar();
}

// Implementação concreta
class ConversorUSD extends Conversork {
	private final ReentrantLock lock = new ReentrantLock();
	private final LinkedList<String> historico = new LinkedList<>();

	@Override
	public double converter(Moedak moeda, double valor) {
		return moeda.converter(valor);
	}

	@Override
	public synchronized void sincronizar() {
		System.out.println("Sincronizando com o servidor de câmbio...");
		try {
			Thread.sleep(500);
			System.out.println("Sincronizado com sucesso.");
		} catch (InterruptedException e) {
			System.out.println("Erro na sincronização.");
		}
	}

	public void registrarHistorico(String registro) {
		lock.lock();
		try {
			historico.add(registro);
		} finally {
			lock.unlock();
		}
	}

	public void exibirHistorico() {
		System.out.println("Histórico de conversões:");
		for (String h : historico) {
			System.out.println(h);
		}
	}
}

class ConversorMoedaHard {
	public static void mainMH() {
		Scanner scanner = new Scanner(System.in);
		ConversorUSD conversor = new ConversorUSD();
		double[][] matrizValores = new double[5][7];
		String[] nomes = { "EURO", "LIBRA", "IENE", "REAL", "DOLAR_CANADENSE", "PESO_MEXICANO", "FRANCO_SUIÇO" };

		conversor.sincronizar();

		System.out.print("Digite a quantidade de valores em USD que deseja converter: ");
		int qtd = scanner.nextInt();
		double[] valoresUSD = new double[qtd];

		for (int i = 0; i < qtd; i++) {
			System.out.print("Valor USD #" + (i + 1) + ": ");
			valoresUSD[i] = scanner.nextDouble();
		}

		int linha = 0;
		while (linha < 1 && linha < qtd) {
			System.out.println("Conversão #" + (linha + 1) + ":");

			for (int i = 0; i < nomes.length; i++) {
				Moedak moeda = Moedak.valueOf(nomes[i]);
				matrizValores[linha][i] = conversor.converter(moeda, valoresUSD[linha]);
				String registro = String.format("%.2f USD = %.2f %s", valoresUSD[linha], matrizValores[linha][i],
						moeda.name());
				conversor.registrarHistorico(registro);
			}
			linha++;
		}

		// Mostrar menu de escolha com switch-case
		while (true) {
			System.out.println("\n--- Menu ---");
			System.out.println("1. Listar moedas");
			System.out.println("2. Exibir histórico");
			System.out.println("3. Converter novo valor");
			System.out.println("0. Sair");
			System.out.print("Escolha uma opção: ");
			int opcao = scanner.nextInt();

			switch (opcao) {
			case 1:
				Moedak.listarMoedas();
				break;
			case 2:
				conversor.exibirHistorico();
				break;
			case 3:
				System.out.print("Digite o valor em USD: ");
				double novoValor = scanner.nextDouble();
				System.out.print("Digite a moeda de destino (ex: EURO): ");
				String moedaEscolhida = scanner.next().toUpperCase();

				boolean encontrada = false;
				for (Moedak m : Moedak.values()) {
					if (m.name().equals(moedaEscolhida)) {
						double convertido = conversor.converter(m, novoValor);
						String registro = String.format("%.2f USD = %.2f %s", novoValor, convertido, m.name());
						conversor.registrarHistorico(registro);
						System.out.println(registro);
						encontrada = true;
						break;
					}
				}

				if (!encontrada) {
					System.out.println("Moeda inválida.");
				}
				break;
			case 0:
				System.out.println("Encerrando...");
				return;
			default:
				System.out.println("Opção inválida.");
			}
		}
	}
}

class Import_3WM {
	public static void run_Import_3WM() {
		System.err.println("\t\t3. \n");
		ConversorMoedaHard.mainMH();
	}
}