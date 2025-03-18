package UTIL_09_Iterator;

import util.Linhas;
import java.util.*;
import java.net.*;

public class For_Matrix2 {
	public static void For_Run() {

		Linhas linhas = new Linhas();
		String collec = "\n\t Iterator<E>: Interface para iterar sobre elementos de uma coleção. \n";
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

abstract class SistemaOperacional_ {
	String nome;

	public SistemaOperacional_(String nome) {
		this.nome = nome;
	}

	abstract void exibirInfo();
}

class Linux_ extends SistemaOperacional_ {
	public Linux_(String nome) {
		super(nome);
	}

	@Override
	void exibirInfo() {
		System.out.println("Sistema Operacional Linux: " + nome);
	}
}

class Windows_ extends SistemaOperacional_ {
	public Windows_(String nome) {
		super(nome);
	}

	@Override
	void exibirInfo() {
		System.out.println("Sistema Operacional Windows: " + nome);
	}
}

class MacOS_ extends SistemaOperacional_ {
	public MacOS_(String nome) {
		super(nome);
	}

	@Override
	void exibirInfo() {
		System.out.println("Sistema Operacional MacOS: " + nome);
	}
}

// Enum representando os tipos de IP
enum TipoIP_ {
	IPV4("IPv4"), IPV6("IPv6");

	private final String tipo;

	TipoIP_(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}
}

// Enum representando DNS
enum DNS {
	GOOGLE("8.8.8.8"), CLOUDFLARE("1.1.1.1"), OPENDNS("208.67.222.222");

	private final String endereco;

	DNS(String endereco) {
		this.endereco = endereco;
	}

	public String getEndereco() {
		return endereco;
	}
}

// Interface para manipulação de operações com IP
interface OperacoesIP {
	void verificarConexao(String endereco);
}

class ConexaoIPv4 implements OperacoesIP {
	@Override
	public void verificarConexao(String endereco) {
		try {
			InetAddress ip = InetAddress.getByName(endereco);
			if (ip.isReachable(5000)) {
				System.out.println("Conexão estabelecida com IPv4: " + endereco);
			} else {
				System.out.println("Falha na conexão com IPv4: " + endereco);
			}
		} catch (Exception e) {
			System.out.println("Erro ao verificar conexão IPv4.");
		}
	}
}

class ConexaoIPv6 implements OperacoesIP {
	@Override
	public void verificarConexao(String endereco) {
		try {
			InetAddress ip = InetAddress.getByName(endereco);
			if (ip.isReachable(5000)) {
				System.out.println("Conexão estabelecida com IPv6: " + endereco);
			} else {
				System.out.println("Falha na conexão com IPv6: " + endereco);
			}
		} catch (Exception e) {
			System.out.println("Erro ao verificar conexão IPv6.");
		}
	}
}

// Classe abstrata para manipulação de DNS
abstract class ManipulacaoDNS {
	public abstract void obterEnderecoDNS();
}

class DNSGoogle extends ManipulacaoDNS {
	@Override
	public void obterEnderecoDNS() {
		System.out.println("Endereço DNS Google: " + DNS.GOOGLE.getEndereco());
	}
}

class DNSCloudflare extends ManipulacaoDNS {
	@Override
	public void obterEnderecoDNS() {
		System.out.println("Endereço DNS Cloudflare: " + DNS.CLOUDFLARE.getEndereco());
	}
}

class DNSOpenDNS extends ManipulacaoDNS {
	@Override
	public void obterEnderecoDNS() {
		System.out.println("Endereço DNS OpenDNS: " + DNS.OPENDNS.getEndereco());
	}
}

class Import_1F {
	public static void run_Import_1F() {
		System.err.println("\t\t1. \n");

		List<SistemaOperacional_> sistemas = new ArrayList<>();
		sistemas.add(new Linux_("Ubuntu"));
		sistemas.add(new Windows_("Windows 10"));
		sistemas.add(new MacOS_("MacOS Monterey"));

		// Utilizando Iterator
		Iterator<SistemaOperacional_> iterator = sistemas.iterator();
		while (iterator.hasNext()) {
			SistemaOperacional_ so = iterator.next();
			so.exibirInfo();
		}

		// Operações com IP
		OperacoesIP conexaoIP = new ConexaoIPv4();
		conexaoIP.verificarConexao("8.8.8.8");

		conexaoIP = new ConexaoIPv6();
		conexaoIP.verificarConexao("2001:4860:4860::8888");

		// Manipulação de DNS
		ManipulacaoDNS dns = new DNSGoogle();
		dns.obterEnderecoDNS();

		dns = new DNSCloudflare();
		dns.obterEnderecoDNS();

		// Ordenação de sistemas operacionais
		Collections.sort(sistemas, new Comparator<SistemaOperacional_>() {
			@Override
			public int compare(SistemaOperacional_ o1, SistemaOperacional_ o2) {
				return o1.nome.compareTo(o2.nome);
			}
		});

		// Exibindo os sistemas operacionais ordenados
		System.out.println("\nSistemas Operacionais Ordenados:");
		for (SistemaOperacional_ so : sistemas) {
			so.exibirInfo();
		}

		// Demonstração de busca usando índice
		String nomeProcurado = "Windows 10";
		for (SistemaOperacional_ so : sistemas) {
			if (so.nome.equals(nomeProcurado)) {
				System.out.println("\nSistema Operacional encontrado: " + nomeProcurado);
				break;
			}
		}

		// Sincronização de acesso a lista de sistemas
		synchronized (sistemas) {
			sistemas.add(new Linux_("Debian"));
			sistemas.add(new MacOS_("Big Sur"));
			sistemas.add(new Linux_("Debian Linux"));
			sistemas.add(new MacOS_("Big Linux"));
		}

		// Exibindo todos os sistemas operacionais após a sincronização
		System.out.println("\nSistemas Operacionais após sincronização:");
		for (SistemaOperacional_ so : sistemas) {
			so.exibirInfo();
		}

	}
}

//Corpo da Classe _______________________________
enum TipoWifi {
	RÁPIDO, LENTO, MEDIO, FIBRA, NET_4G, NET_5G, WIFI6, WIFI7, MESH, LTE;
}

abstract class Wifi {
	protected String nome;
	protected TipoWifi tipo;

	public Wifi(String nome, TipoWifi tipo) {
		this.nome = nome;
		this.tipo = tipo;
	}

	public abstract void conectar();
}

abstract class RedeNet {
	protected String enderecoIp;

	public RedeNet(String enderecoIp) {
		this.enderecoIp = enderecoIp;
	}

	public abstract void conectar();
}

abstract class Conexao {
	public abstract void conectarWifi(Wifi wifi);

	public abstract void verificarRede(RedeNet rede);
}

class WifiConcreto extends Wifi {
	public WifiConcreto(String nome, TipoWifi tipo) {
		super(nome, tipo);
	}

	@Override
	public void conectar() {
		System.out.println("Conectando ao Wi-Fi: " + nome + " do tipo " + tipo);
	}
}

class RedeConcreto extends RedeNet {
	public RedeConcreto(String enderecoIp) {
		super(enderecoIp);
	}

	@Override
	public void conectar() {
		System.out.println("Conectando à rede com o IP: " + enderecoIp);
	}
}

class ConexaoWifi extends Conexao {
	@Override
	public void conectarWifi(Wifi wifi) {
		System.out.println("Conectando ao Wi-Fi: " + wifi.nome);
	}

	@Override
	public void verificarRede(RedeNet rede) {
		System.out.println("Verificando a rede com IP: " + rede.enderecoIp);
	}
}

class WifiManager {
	private List<Wifi> wifiList = new ArrayList<>();

	public void adicionarWifi(Wifi wifi) {
		wifiList.add(wifi);
	}

	public void buscarWifi(String nome) {
		Iterator<Wifi> iterator = wifiList.iterator();
		while (iterator.hasNext()) {
			Wifi wifi = iterator.next();
			if (wifi.nome.equals(nome)) {
				System.out.println("Wi-Fi encontrado: " + wifi.nome + " do tipo " + wifi.tipo);
				return;
			}
		}
		System.out.println("Wi-Fi não encontrado!");
	}

	public void ordenarWifi() {
		Collections.sort(wifiList, new Comparator<Wifi>() {
			@Override
			public int compare(Wifi o1, Wifi o2) {
				return o1.nome.compareTo(o2.nome); // Ordena pelo nome
			}
		});
		System.out.println("Wi-Fi ordenados por nome:");
		for (Wifi wifi : wifiList) {
			System.out.println(wifi.nome + " (" + wifi.tipo + ")");
		}
	}

	public synchronized void sincronizarWifi() {
		System.out.println("Sincronizando Wi-Fi...");
		// Exemplo de sincronização, pode ser estendido conforme a lógica
	}
}

//Corpo da Classe _______________________________
class Import_2F {
	public static void run_Import_2F() {
		System.err.println("\t\t2. \n");
		WifiManager wifiManager = new WifiManager();

		// Criando Wi-Fi's
		Wifi wifi1 = new WifiConcreto("Wi-Fi_1", TipoWifi.FIBRA);
		Wifi wifi2 = new WifiConcreto("Wi-Fi_2", TipoWifi.LTE);
		Wifi wifi3 = new WifiConcreto("Wi-Fi_3", TipoWifi.NET_5G);

		// Adicionando Wi-Fi's à lista
		wifiManager.adicionarWifi(wifi1);
		wifiManager.adicionarWifi(wifi2);
		wifiManager.adicionarWifi(wifi3);

		// Buscar Wi-Fi
		wifiManager.buscarWifi("Wi-Fi_2");

		// Ordenar Wi-Fi's
		wifiManager.ordenarWifi();

		// Sincronizar Wi-Fi
		wifiManager.sincronizarWifi();

		// Usando InetAddress para exibir IPV4, IPV6 e DNS
		try {
			InetAddress ipV4 = InetAddress.getByName("google.com");
			InetAddress ipV6 = InetAddress.getByName("ipv6.google.com");

			System.out.println("IPv4: " + ipV4.getHostAddress());
			System.out.println("IPv6: " + ipV6.getHostAddress());
		} catch (UnknownHostException e) {
			System.out.println("Erro ao obter os endereços IP");
		}
	}
}

//Corpo da Classe _______________________________
//10 ENUMS
enum WifiType {
	PUBLIC, PRIVATE, ENTERPRISE, GUEST, MESH, HOTSPOT, MOBILE, SATELLITE, FIBER, DSL;
}

enum SecurityProtocol {
	WPA2, WPA3, WEP, OPEN, ENTERPRISE;
}

enum FrequencyBand {
	TWO_GHZ, FIVE_GHZ, SIX_GHZ;
}

enum ConnectionStatus {
	CONNECTED, DISCONNECTED, LIMITED, UNKNOWN;
}

enum EncryptionTypeN {
	AES, TKIP, NONE;
}

enum ChannelWidth {
	MHZ_20, MHZ_40, MHZ_80, MHZ_160;
}

enum SpeedCategory {
	LOW, MEDIUM, HIGH, ULTRA_HIGH;
}

enum DeviceType {
	ROUTER, MODEM, REPEATER, BRIDGE, SWITCH;
}

enum AddressType {
	IPV4, IPV6, DNS;
}

enum DataRate {
	SLOW, AVERAGE, FAST, GIGABIT;
}

//10 CLASSES ABSTRATAS
abstract class WifiNetwork {
	String ssid;
	WifiType type;
	SecurityProtocol security;

	abstract void connect();

	abstract void disconnect();
}

abstract class Connection {
	ConnectionStatus status;

	abstract void checkStatus();
}

abstract class Device {
	DeviceType type;

	abstract void configure();
}

abstract class Address {
	AddressType type;

	abstract void resolveAddress();
}

abstract class Speed {
	SpeedCategory category;

	abstract void measureSpeed();
}

abstract class Encryption {
	EncryptionTypeN encryptionType;

	abstract void encryptData();
}

abstract class Bandwidth {
	ChannelWidth channelWidth;

	abstract void adjustBandwidth();
}

abstract class Frequency {
	FrequencyBand frequencyBand;

	abstract void setFrequency();
}

abstract class DataLimit {
	DataRate dataRate;

	abstract void enforceDataLimit();
}

abstract class Authentication {
	boolean authenticated;

	abstract void performAuthentication();
}

//IMPLEMENTAÇÕES CONCRETAS
//Exemplo de rede Wi‑Fi
class HomeWifi extends WifiNetwork {
	HomeWifi(String ssid, WifiType type, SecurityProtocol security) {
		this.ssid = ssid;
		this.type = type;
		this.security = security;
	}

	@Override
	void connect() {
		System.out.println("Conectando à rede: " + ssid);
	}

	@Override
	void disconnect() {
		System.out.println("Desconectando da rede: " + ssid);
	}
}

//Classe para resolução de endereços usando InetAddress
class WifiAddress extends Address {
	String addressValue;

	WifiAddress(String addressValue, AddressType type) {
		this.addressValue = addressValue;
		this.type = type;
	}

	@Override
	void resolveAddress() {
		try {
			InetAddress inetAddr = InetAddress.getByName(addressValue);
			System.out.println("[" + type + "] Resolução: " + inetAddr.getHostAddress());
		} catch (UnknownHostException e) {
			System.out.println("Erro ao resolver: " + addressValue);
		}
	}
}

//Gerenciador de redes com métodos sincronizados, ordenação e busca
class WifiManagerR {
	// Lista sincronizada para acesso thread-safe
	private List<WifiNetwork> networks = Collections.synchronizedList(new ArrayList<>());

	// Método sincronizado para adicionar rede
	synchronized void addNetwork(WifiNetwork network) {
		networks.add(network);
	}

	// Método sincronizado para remover rede
	synchronized void removeNetwork(WifiNetwork network) {
		networks.remove(network);
	}

	// Listagem das redes utilizando Iterator
	void listNetworks() {
		Iterator<WifiNetwork> iterator = networks.iterator();
		while (iterator.hasNext()) {
			WifiNetwork network = iterator.next();
			System.out.println(
					"SSID: " + network.ssid + " | Tipo: " + network.type + " | Segurança: " + network.security);
		}
	}

	// Ordena as redes por SSID
	synchronized void sortNetworks() {
		Collections.sort(networks, new Comparator<WifiNetwork>() {
			@Override
			public int compare(WifiNetwork o1, WifiNetwork o2) {
				return o1.ssid.compareTo(o2.ssid);
			}
		});
		System.out.println("Redes ordenadas por SSID.");
	}

	// Busca rede pelo SSID utilizando if, else if, else if, else e break
	WifiNetwork searchNetwork(String ssid) {
		WifiNetwork found = null;
		for (WifiNetwork network : networks) {
			if (network.ssid.equals(ssid)) {
				found = network;
				break; // Rede encontrada: condição exata
			} else if (network.ssid.startsWith(ssid)) {
				found = network;
				break; // Rede cujo SSID inicia com o termo buscado
			} else if (network.ssid.contains(ssid)) {
				found = network;
				break; // Rede que contém o termo buscado
			} else {
				// Continuação se nenhuma condição for satisfeita
				continue;
			}
		}
		return found;
	}
}

//================== CLASSE PRINCIPAL ==================
class Import_3F {
	public static void run_Import_3F() {
		System.err.println("\t\t3.\n");
		// Cria o gerenciador e adiciona redes
		WifiManagerR manager = new WifiManagerR();

		WifiNetwork net1 = new HomeWifi("HomeNetwork", WifiType.PRIVATE, SecurityProtocol.WPA2);
		WifiNetwork net2 = new HomeWifi("GuestNetwork", WifiType.GUEST, SecurityProtocol.OPEN);
		WifiNetwork net3 = new HomeWifi("OfficeNetwork", WifiType.ENTERPRISE, SecurityProtocol.WPA3);
		WifiNetwork net4 = new HomeWifi("CafeNetwork", WifiType.PUBLIC, SecurityProtocol.WEP);
		WifiNetwork net5 = new HomeWifi("MeshNetwork", WifiType.MESH, SecurityProtocol.ENTERPRISE);

		manager.addNetwork(net1);
		manager.addNetwork(net2);
		manager.addNetwork(net3);
		manager.addNetwork(net4);
		manager.addNetwork(net5);

		// Exibe redes disponíveis utilizando Iterator
		System.out.println("Redes disponíveis:");
		manager.listNetworks();

		// Ordenação das redes pelo SSID
		manager.sortNetworks();
		System.out.println("\nApós ordenação:");
		manager.listNetworks();

		// Busca por rede utilizando condições e break
		String busca = "OfficeNetwork";
		WifiNetwork result = manager.searchNetwork(busca);
		if (result != null) {
			System.out.println("\nRede encontrada: " + result.ssid);
		} else {
			System.out.println("\nRede não encontrada para: " + busca);
		}

		// Demonstração de iteração com matriz (2D) de redes
		WifiNetwork[][] matrix = new WifiNetwork[2][3];
		matrix[0][0] = net1;
		matrix[0][1] = net2;
		matrix[0][2] = net3;
		matrix[1][0] = net4;
		matrix[1][1] = net5;
		matrix[1][2] = net1; // Apenas para exemplo

		System.out.println("\nIterando sobre a matriz de redes:");
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j].ssid + "\t");
				// Estrutura condicional com break no loop interno
				if (j == 0) {
					System.out.print("[Primeira Coluna] ");
				} else if (j == 1) {
					System.out.print("[Segunda Coluna] ");
				} else if (j == 2) {
					System.out.print("[Terceira Coluna] ");
					break; // Interrompe o loop interno após a terceira coluna
				} else {
					System.out.print("[Outra Coluna] ");
				}
			}
			System.out.println();
		}

		// Demonstração de resolução de endereços com java.net.InetAddress
		System.out.println("\nResolução de Endereços:");
		WifiAddress ipv4 = new WifiAddress("8.8.8.8", AddressType.IPV4);
		WifiAddress ipv6 = new WifiAddress("2001:4860:4860::8888", AddressType.IPV6);
		WifiAddress dns = new WifiAddress("www.google.com", AddressType.DNS);
		ipv4.resolveAddress();
		ipv6.resolveAddress();
		dns.resolveAddress();
	}
}