package UTIL_08_Arrays;

import util.Linhas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Simples {
	public static void Simples_Run() {

		Linhas linhas = new Linhas();
		String collec = "\n\t Arrays: Métodos utilitários para trabalhar com arrays (ex.: ordenação, busca\n";
		System.err.println(collec);

		linhas.run_Caracteres();
		Import_1S.run_Import_1S();

		linhas.run_Caracteres();
		Import_2S.run_Import_2S();

		linhas.run_Caracteres();
		Import_3S.run_Import_3S();

	}
}

//Corpo da Classe _______________________________

// Enum para tipos de segurança
enum SecurityType {
	OPEN, WEP, WPA, WPA2, WPA3
}

// Enum para tipo de rede
enum NetworkType {
	PUBLIC, PRIVATE, ENTERPRISE, GUEST, IOT
}

// Enum para frequência da rede
enum Frequency {
	GHZ_2_4, GHZ_5, GHZ_6, DUAL_BAND, TRI_BAND
}

// Enum para status da rede
enum Status {
	ACTIVE, INACTIVE, PENDING, BLOCKED, UNKNOWN
}

// Enum para protocolo de IP
enum IPProtocol {
	IPV4, IPV6, DUAL_STACK, NONE, AUTO
}

// Classe abstrata para definir uma rede WiFi
abstract class AbstractWiFi {
	protected String ssid;
	protected SecurityType security;
	protected NetworkType type;
	protected Frequency frequency;
	protected Status status;
	protected IPProtocol ipProtocol;

	public AbstractWiFi(String ssid, SecurityType security, NetworkType type, Frequency frequency, Status status,
			IPProtocol ipProtocol) {
		this.ssid = ssid;
		this.security = security;
		this.type = type;
		this.frequency = frequency;
		this.status = status;
		this.ipProtocol = ipProtocol;
	}

	public abstract void connect();

	public abstract void disconnect();
}

// Classe concreta de WiFi
class WiFi extends AbstractWiFi {
	public WiFi(String ssid, SecurityType security, NetworkType type, Frequency frequency, Status status,
			IPProtocol ipProtocol) {
		super(ssid, security, type, frequency, status, ipProtocol);
	}

	@Override
	public void connect() {
		System.out.println("Conectando a: " + ssid);
	}

	@Override
	public void disconnect() {
		System.out.println("Desconectando de: " + ssid);
	}

	@Override
	public String toString() {
		return "WiFi{SSID='" + ssid + "', Security=" + security + ", Type=" + type + ", Frequency=" + frequency
				+ ", Status=" + status + ", IPProtocol=" + ipProtocol + "} ";
	}
}

// CRUD de WiFi
class WiFiManager {
	private WiFi[] networks = new WiFi[0];
	private final Lock lock = new ReentrantLock();

	public void addNetwork(WiFi wifi) {
		lock.lock();
		try {
			networks = Arrays.copyOf(networks, networks.length + 1);
			networks[networks.length - 1] = wifi;
		} finally {
			lock.unlock();
		}
	}

	public void removeNetwork(String ssid) {
		lock.lock();
		try {
			networks = Arrays.stream(networks).filter(wifi -> !wifi.ssid.equals(ssid)).toArray(WiFi[]::new);
		} finally {
			lock.unlock();
		}
	}

	public WiFi searchNetwork(String ssid) {
		for (WiFi wifi : networks) {
			if (wifi.ssid.equals(ssid)) {
				return wifi;
			}
		}
		return null;
	}

	public void sortNetworks() {
		Arrays.sort(networks, Comparator.comparing(w -> w.ssid));
	}

	public void listNetworks() {
		Arrays.stream(networks).forEach(System.out::println);
	}

}

class Import_1S {
	public static void run_Import_1S() {
		System.err.println("\t\t1. Wifi Com IPV4 e IPV6 \n");
		WiFiManager manager = new WiFiManager();

		WiFi wifi1 = new WiFi("HomeWiFi", SecurityType.WPA2, NetworkType.PRIVATE, Frequency.GHZ_5, Status.ACTIVE,
				IPProtocol.IPV4);
		WiFi wifi2 = new WiFi("OfficeNet", SecurityType.WPA3, NetworkType.ENTERPRISE, Frequency.DUAL_BAND,
				Status.ACTIVE, IPProtocol.DUAL_STACK);
		WiFi wifi3 = new WiFi("CafeHotspot", SecurityType.OPEN, NetworkType.PUBLIC, Frequency.GHZ_2_4, Status.INACTIVE,
				IPProtocol.NONE);

		manager.addNetwork(wifi1);
		manager.addNetwork(wifi2);
		manager.addNetwork(wifi3);

		System.out.println("\nRedes Disponíveis:");
		manager.listNetworks();

		System.out.println("\nBuscando 'OfficeNet':");
		System.out.println(manager.searchNetwork("OfficeNet"));

		System.out.println("\nOrdenando redes por SSID:");
		manager.sortNetworks();
		manager.listNetworks();

		System.out.println("\nRemovendo 'CafeHotspot':");
		manager.removeNetwork("CafeHotspot");
		manager.listNetworks();
	}
}

//Corpo da Classe _______________________________
//Enum para tipos de telefone
enum TipoTelefone {
	CELULAR, FIXO, TRABALHO, RESIDENCIAL, OUTRO;
}

//Classe abstrata base para um telefone
abstract class TelefoneBase {
	protected String numero;
	protected TipoTelefone tipo;

	public TelefoneBase(String numero, TipoTelefone tipo) {
		this.numero = numero;
		this.tipo = tipo;
	}

	public abstract void exibirInfo();
}

//Classe concreta que estende a base
class Telefone extends TelefoneBase {
	public Telefone(String numero, TipoTelefone tipo) {
		super(numero, tipo);
	}

	@Override
	public void exibirInfo() {
		System.out.println("Tipo: " + tipo + ", Número: " + numero);
	}
}

//CRUD de telefones
class TelefoneCRUD {
	private Telefone[] telefones = new Telefone[0];
	private final ReentrantLock lock = new ReentrantLock();

	public void adicionarTelefone(String numero, TipoTelefone tipo) {
		lock.lock();
		try {
			telefones = Arrays.copyOf(telefones, telefones.length + 1);
			telefones[telefones.length - 1] = new Telefone(numero, tipo);
		} finally {
			lock.unlock();
		}
	}

	public void listarTelefones() {
		Arrays.stream(telefones).forEach(Telefone::exibirInfo);
	}

	public void buscarTelefone(String numero) {
		for (Telefone telefone : telefones) {
			if (telefone.numero.equals(numero)) {
				telefone.exibirInfo();
				return;
			}
		}
		System.out.println("Número não encontrado.");
	}

	public void removerTelefone(String numero) {
		lock.lock();
		try {
			for (int i = 0; i < telefones.length; i++) {
				if (telefones[i].numero.equals(numero)) {
					Telefone[] novoArray = new Telefone[telefones.length - 1];
					System.arraycopy(telefones, 0, novoArray, 0, i);
					System.arraycopy(telefones, i + 1, novoArray, i, telefones.length - i - 1);
					telefones = novoArray;
					System.out.println("Número removido.");
					return;
				}
			}
			System.out.println("Número não encontrado.");
		} finally {
			lock.unlock();
		}
	}

	public void ordenarTelefones() {
		Arrays.sort(telefones, (a, b) -> a.numero.compareTo(b.numero));
		System.out.println("Telefones ordenados.");
	}
}

//Testando o CRUD
class Import_2S {
	public static void run_Import_2S() {
		System.err.println("\t\t2. Numeros de telefones \n");
		TelefoneCRUD crud = new TelefoneCRUD();

		crud.adicionarTelefone("11987654321", TipoTelefone.CELULAR);
		crud.adicionarTelefone("1134567890", TipoTelefone.FIXO);
		crud.adicionarTelefone("11911223344", TipoTelefone.TRABALHO);

		System.out.println("Lista inicial:");
		crud.listarTelefones();

		System.out.println("\nOrdenando telefones:");
		crud.ordenarTelefones();
		crud.listarTelefones();

		System.out.println("\nBuscando telefone:");
		crud.buscarTelefone("11911223344");

		System.out.println("\nRemovendo telefone:");
		crud.removerTelefone("1134567890");
		crud.listarTelefones();
	}
}

//Corpo da Classe _______________________________

//Enum para status da mensagem
enum Status1 {
	ENVIADA, RECEBIDA, LIDA, RESPONDIDA, APAGADA;
}

//Classe abstrata representando uma mensagem abstrata
abstract class MensagemBase {
	protected String conteudo;
	protected String remetente;
	protected Status1 status;

	public MensagemBase(String conteudo, String remetente) {
		this.conteudo = conteudo;
		this.remetente = remetente;
		this.status = Status1.ENVIADA;
	}

	public abstract void exibirMensagem();
}

//Classe concreta de mensagem
class Mensagem extends MensagemBase {
	public Mensagem(String conteudo, String remetente) {
		super(conteudo, remetente);
	}

	@Override
	public void exibirMensagem() {
		System.out.println(remetente + ": " + conteudo + " (" + status + ")");
	}
}

//Classe para gerenciar as mensagens
class GerenciadorMensagens {
	private List<Mensagem> mensagens = new ArrayList<>();
	private ReentrantLock lock = new ReentrantLock();

	// Adiciona uma nova mensagem
	public void adicionarMensagem(String conteudo, String remetente) {
		lock.lock();
		try {
			mensagens.add(new Mensagem(conteudo, remetente));
		} finally {
			lock.unlock();
		}
	}

	// Lista todas as mensagens ordenadas pelo remetente
	public void listarMensagens() {
		mensagens.sort(Comparator.comparing(m -> m.remetente));
		for (Mensagem msg : mensagens) {
			msg.exibirMensagem();
		}
	}

	// Busca uma mensagem pelo remetente
	public void buscarMensagem(String remetente) {
		for (Mensagem msg : mensagens) {
			if (msg.remetente.equalsIgnoreCase(remetente)) {
				msg.exibirMensagem();
				break;
			}
		}
	}
}

class Import_3S {
	public static void run_Import_3S() {
		System.err.println("\t\t3. Mesagem do Whats App \n");

		GerenciadorMensagens gm = new GerenciadorMensagens();

		gm.adicionarMensagem("Olá!", "Alice");
		gm.adicionarMensagem("Oi, tudo bem?", "Bob");
		gm.adicionarMensagem("Sim, e você?", "Alice");

		System.out.println("--- Mensagens Ordenadas ---");
		gm.listarMensagens();

		System.out.println("\n--- Busca por mensagens de Alice ---");
		gm.buscarMensagem("Alice");
	}
}