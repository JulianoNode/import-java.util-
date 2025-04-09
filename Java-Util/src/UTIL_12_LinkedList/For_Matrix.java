package UTIL_12_LinkedList;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import util.Linhas;
import util.VoutarMenu;

public class For_Matrix {
	public static void For_Run() {

		Linhas linhas = new Linhas();
		String collec = "\n\t Nome \n";
		System.err.println(collec);

		linhas.run_Caracteres();
		Import_1F.run_Import_1F();

		linhas.run_Caracteres();
		Import_2F.run_Import_2F();

		linhas.run_Caracteres();
		Import_3F.run_Import_3F();

	}
}

//Corpo da Classe _______________________________

enum TipoSom {
	SUBWOOFER(1200), WOOFER(800), MID(400), TWEETER(200), CORNETA(300);

	private final int watts;

	TipoSom(int watts) {
		this.watts = watts;
	}

	public int getWatts() {
		return watts;
	}

	public String faixa() {
		if (watts >= 1000)
			return "Graves extremos";
		else if (watts >= 600)
			return "Graves e médios";
		else
			return "Agudos";
	}
}

enum NivelPotencia {
	LEVE, MÉDIO, PESADO, EXTREMO, INSANO;

	public String descricao() {
		return switch (this) {
		case LEVE -> "Som interno de carro";
		case MÉDIO -> "Som de garagem";
		case PESADO -> "Trio elétrico local";
		case EXTREMO -> "Abala quarteirão";
		case INSANO -> "Campeonato SPL!";
		};
	}
}

abstract class SistemaSom {
	protected String nome;

	public SistemaSom(String nome) {
		this.nome = nome;
	}

	public abstract void mostrar();
}

class PotenciaSistema extends SistemaSom {
	private final LinkedList<TipoSom> componentes = new LinkedList<>();
	private final int[][] matrizSom = new int[2][3]; // Ex: posições para gravação

	public PotenciaSistema(String nome) {
		super(nome);
	}

	public synchronized void adicionarComponente(TipoSom tipo, int linha, int coluna) {
		componentes.add(tipo);
		matrizSom[linha][coluna] = tipo.getWatts();
	}

	public synchronized int calcularTotal() {
		int total = 0;
		for (TipoSom t : componentes) {
			total += t.getWatts();
		}
		return total;
	}

	public NivelPotencia avaliarNivel() {
		int total = calcularTotal();
		if (total < 1000)
			return NivelPotencia.LEVE;
		else if (total < 2000)
			return NivelPotencia.MÉDIO;
		else if (total < 3000)
			return NivelPotencia.PESADO;
		else if (total < 4000)
			return NivelPotencia.EXTREMO;
		else
			return NivelPotencia.INSANO;
	}

	@Override
	public void mostrar() {
		System.out.println("Sistema: " + nome);
		for (TipoSom t : componentes) {
			System.out.println(" - " + t + " (" + t.getWatts() + "W) | Faixa: " + t.faixa());
		}
		System.out.println("Potência Total: " + calcularTotal() + "W");
		NivelPotencia nivel = avaliarNivel();
		System.out.println("Nível de Potência: " + nivel + " → " + nivel.descricao());
	}
}

class MainSom {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PotenciaSistema sistema = new PotenciaSistema("Alto Motivo Master");

		System.out.println("🎶 Configuração de Potência de SOM 🎶");
		TipoSom[] opcoes = TipoSom.values();

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3 && i * 3 + j < opcoes.length; j++) {
				TipoSom tipo = opcoes[i * 3 + j];
				System.out.println("Adicionar " + tipo + " (" + tipo.getWatts() + "W)? (s/n)");
				String resposta = sc.next();
				if (resposta.equalsIgnoreCase("s")) {
					sistema.adicionarComponente(tipo, i, j);
				}
			}
		}

		System.out.println("\n🔊 Resultado Final 🔊");
		sistema.mostrar();
	}
}

class Import_1F {
	public static void run_Import_1F() {
		System.err.println("\t\t1. \n");
		MainSom.main(null);
	}
}

//Corpo da Classe _______________________________

// Enum de países com métodos
enum PaisVPN {
	BRASIL("🇧🇷", 1), EUA("🇺🇸", 2), JAPAO("🇯🇵", 3), ALEMANHA("🇩🇪", 4);

	private String emoji;
	private int codigo;

	PaisVPN(String emoji, int codigo) {
		this.emoji = emoji;
		this.codigo = codigo;
	}

	public String getEmoji() {
		return emoji;
	}

	public int getCodigo() {
		return codigo;
	}
}

// Enum de perfis
enum PerfilVPN {
	BÁSICO(10, 2), INTERMEDIÁRIO(50, 4), AVANÇADO(100, 6), ULTRA(200, 8);

	private int velocidade;
	private int seguranca;

	PerfilVPN(int velocidade, int seguranca) {
		this.velocidade = velocidade;
		this.seguranca = seguranca;
	}

	public int getVelocidade() {
		return velocidade;
	}

	public int getSeguranca() {
		return seguranca;
	}
}

// Enum de status da conexão
enum StatusConexao {
	CONECTANDO, CONECTADO, ERRO, DESCONECTADO;

	public void mostrarStatus() {
		switch (this) {
		case CONECTANDO -> System.out.println("🔄 Conectando...");
		case CONECTADO -> System.out.println("✅ Conectado com sucesso!");
		case ERRO -> System.out.println("❌ Erro na conexão!");
		case DESCONECTADO -> System.out.println("🔌 VPN desconectada.");
		}
	}
}

// Enum de tipo de usuário
enum TipoUsuario {
	FREE, PREMIUM, ADMIN, DESENVOLVEDOR;

	public boolean temPrioridade() {
		return this == PREMIUM || this == ADMIN || this == DESENVOLVEDOR;
	}
}

// Enum de protocolos
enum ProtocoloVPN {
	OPENVPN, WIREGUARD, IKEV2, L2TP;

	public String descricao() {
		return switch (this) {
		case OPENVPN -> "OpenVPN é seguro e estável.";
		case WIREGUARD -> "WireGuard é moderno e rápido.";
		case IKEV2 -> "IKEv2 é bom para dispositivos móveis.";
		case L2TP -> "L2TP tem criptografia dupla.";
		};
	}
}

// Classe abstrata de conexão
abstract class ConexaoVPN {
	protected PaisVPN pais;
	protected PerfilVPN perfil;
	protected StatusConexao status;

	public ConexaoVPN(PaisVPN pais, PerfilVPN perfil) {
		this.pais = pais;
		this.perfil = perfil;
		this.status = StatusConexao.DESCONECTADO;
	}

	public abstract void conectar();

	public abstract void desconectar();

	public synchronized void mostrarDetalhes() {
		System.out.println("🌐 País: " + pais + " " + pais.getEmoji());
		System.out.println("⚙️ Perfil: " + perfil);
		System.out.println("🚀 Velocidade: " + perfil.getVelocidade() + " Mbps");
		System.out.println("🔐 Segurança: Nível " + perfil.getSeguranca());
		System.out.println("🔁 Status atual: " + status);
	}
}

// Classe concreta
class ClienteVPN extends ConexaoVPN {

	public ClienteVPN(PaisVPN pais, PerfilVPN perfil) {
		super(pais, perfil);
	}

	@Override
	public synchronized void conectar() {
		status = StatusConexao.CONECTANDO;
		status.mostrarStatus();
		try {
			Thread.sleep(1000); // simula conexão
			status = StatusConexao.CONECTADO;
		} catch (Exception e) {
			status = StatusConexao.ERRO;
		}
		status.mostrarStatus();
	}

	@Override
	public synchronized void desconectar() {
		status = StatusConexao.DESCONECTADO;
		status.mostrarStatus();
	}
}

// Classe principal
class VPNHardCode {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		LinkedList<ClienteVPN> conexoes = new LinkedList<>();

		// Matriz com VPNs x Perfis
		String[][] matrixVPN = { { "Brasil Básico", "Brasil Intermediário", "Brasil Avançado", "Brasil Ultra" },
				{ "EUA Básico", "EUA Intermediário", "EUA Avançado", "EUA Ultra" },
				{ "Japão Básico", "Japão Intermediário", "Japão Avançado", "Japão Ultra" },
				{ "Alemanha Básico", "Alemanha Intermediário", "Alemanha Avançado", "Alemanha Ultra" } };

		System.out.println("=== VPN - Escolha seu país ===");
		int i = 0;
		for (PaisVPN p : PaisVPN.values()) {
			System.out.println(i + " - " + p + " " + p.getEmoji());
			i++;
		}
		int paisEscolhido = sc.nextInt();

		System.out.println("=== Escolha seu perfil ===");
		int j = 0;
		for (PerfilVPN pf : PerfilVPN.values()) {
			System.out.println(j + " - " + pf + " | Velocidade: " + pf.getVelocidade() + " Mbps | Segurança: "
					+ pf.getSeguranca());
			j++;
		}
		int perfilEscolhido = sc.nextInt();

		// switch case com matriz
		if (paisEscolhido >= 0 && paisEscolhido < 4 && perfilEscolhido >= 0 && perfilEscolhido < 4) {
			switch (paisEscolhido) {
			case 0, 1, 2, 3 -> {
				System.out.println("🔍 VPN Selecionada: " + matrixVPN[paisEscolhido][perfilEscolhido]);
				ClienteVPN cliente = new ClienteVPN(PaisVPN.values()[paisEscolhido],
						PerfilVPN.values()[perfilEscolhido]);
				cliente.conectar();
				cliente.mostrarDetalhes();
				conexoes.add(cliente);
			}
			default -> System.out.println("❌ VPN inválida.");
			}
		} else {
			System.out.println("❌ Opção inválida.");
		}

		// Iterador
		Iterator<ClienteVPN> it = conexoes.iterator();
		while (it.hasNext()) {
			ClienteVPN cli = it.next();
			System.out.println("\n📦 Histórico da conexão:");
			cli.mostrarDetalhes();
		}

		// Voutar para o MEUNU
		VoutarMenu voutarMenu = new VoutarMenu();
		voutarMenu.run_CaracteresMenu_Red();
	}
}

class Import_2F {
	public static void run_Import_2F() {
		System.err.println("\t\t2. \n");
		VPNHardCode.main(null);
	}
}

//Corpo da Classe _______________________________

// 5 Enums com métodos
enum VPNCountryc {
	BRAZIL("vpn.br.com"), USA("vpn.us.com"), GERMANY("vpn.de.com"), JAPAN("vpn.jp.com");

	private final String domain;

	VPNCountryc(String domain) {
		this.domain = domain;
	}

	public String getDomain() {
		return "https://" + domain;
	}
}

enum VPNProtocolc {
	HTTPS, HTTP;

	public boolean isSecure() {
		return this == HTTPS;
	}
}

enum ConnectionStatusc {
	CONNECTED, DISCONNECTED, FAILED;

	public String getStatusMessage() {
		switch (this) {
		case CONNECTED:
			return "Conectado com sucesso.";
		case DISCONNECTED:
			return "Desconectado da rede.";
		case FAILED:
			return "Falha na conexão.";
		default:
			return "Status desconhecido.";
		}
	}
}

enum UserAccessLevel {
	ADMIN, USER, GUEST;

	public boolean hasFullAccess() {
		return this == ADMIN;
	}
}

enum ConnectionType {
	DEDICATED, SHARED, MOBILE, PUBLIC;

	public String typeInfo() {
		return switch (this) {
		case DEDICATED -> "Alta velocidade e segurança.";
		case SHARED -> "Velocidade moderada.";
		case MOBILE -> "Instável, mas disponível em trânsito.";
		case PUBLIC -> "Não recomendado para dados sensíveis.";
		};
	}
}

// Classe abstrata
abstract class VPNConnectionc {
	protected VPNCountryc country;
	protected VPNProtocolc protocol;

	public VPNConnectionc(VPNCountryc country, VPNProtocolc protocol) {
		this.country = country;
		this.protocol = protocol;
	}

	public abstract void connect();

	public abstract void disconnect();

	public synchronized void syncLog(String msg) {
		System.out.println("[LOG] " + msg);
	}
}

// Classe concreta
class SecureVPN extends VPNConnectionc {
	private LinkedList<String> connectionLogs;
	private ConnectionStatusc status;

	public SecureVPN(VPNCountryc country, VPNProtocolc protocol) {
		super(country, protocol);
		this.connectionLogs = new LinkedList<>();
		this.status = ConnectionStatusc.DISCONNECTED;
	}

	@Override
	public synchronized void connect() {
		if (!protocol.isSecure()) {
			status = ConnectionStatusc.FAILED;
			syncLog("Protocolo inseguro detectado.");
		} else {
			status = ConnectionStatusc.CONNECTED;
			syncLog("Conectando a " + country.getDomain());
		}
		connectionLogs.add(status.getStatusMessage());
	}

	@Override
	public synchronized void disconnect() {
		status = ConnectionStatusc.DISCONNECTED;
		syncLog("Desconectando de " + country.name());
		connectionLogs.add(status.getStatusMessage());
	}

	public void showLogs() {
		System.out.println("=== LOGS DE CONEXÃO ===");
		for (String log : connectionLogs) {
			System.out.println(log);
		}
	}
}

// Classe principal
class VPNManager {
	public static void mainV() {
		Scanner scanner = new Scanner(System.in);
		SecureVPN[][] vpnMatrix = new SecureVPN[2][2]; // 4 países em matriz

		// Preenchendo a matriz
		VPNCountryc[] countries = VPNCountryc.values();
		int index = 0;
		for (int i = 0; i < vpnMatrix.length; i++) {
			for (int j = 0; j < vpnMatrix[i].length; j++) {
				vpnMatrix[i][j] = new SecureVPN(countries[index], VPNProtocolc.HTTPS);
				index++;
			}
		}

		System.out.println("Escolha um país para conectar:");
		System.out.println("1 - Brasil\n2 - EUA\n3 - Alemanha\n4 - Japão");
		int option = scanner.nextInt();

		int row = 0, col = 0;

		// switch com matriz
		switch (option) {
		case 1 -> {
			row = 0;
			col = 0;
		}
		case 2 -> {
			row = 0;
			col = 1;
		}
		case 3 -> {
			row = 1;
			col = 0;
		}
		case 4 -> {
			row = 1;
			col = 1;
		}
		default -> {
			System.out.println("Opção inválida.");
			return;
		}
		}

		SecureVPN vpn = vpnMatrix[row][col];

		System.out.println("Iniciando conexão segura...");
		vpn.connect();

		System.out.println("Deseja desconectar? (s/n)");
		char resp = scanner.next().charAt(0);
		if (resp == 's' || resp == 'S') {
			vpn.disconnect();
		}

		vpn.showLogs();
	}
}

class Import_3F {
	public static void run_Import_3F() {
		System.err.println("\t\t3.\n");
		VPNManager.mainV();
	}
}
