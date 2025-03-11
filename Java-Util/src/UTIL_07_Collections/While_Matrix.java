package UTIL_07_Collections;

import util.Linhas;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class While_Matrix {
	public static void While_Run() {
		Linhas linhas = new Linhas();
		String collec = "\n\t Collections: Métodos utilitários para trabalhar com coleções (ex.: ordenação, busca, sincronização). \n";
		System.err.println(collec);

		linhas.run_Caracteres();
		Import_1WM.run_Import_1WM();

		linhas.run_Caracteres();
		Import_3WM.run_Import_3WM();

		linhas.run_Caracteres();
		Import_2WM.run_Import_2WM();
	}
}

//Corpo da Classe _______________________________

abstract class WifiDevice {
	String name;
	String ipAddress;

	WifiDevice(String name, String ipAddress) {
		this.name = name;
		this.ipAddress = ipAddress;
	}

	abstract void connect();

	abstract void disconnect();
}

enum WifiStatus {
	CONNECTED, DISCONNECTED, PENDING;
}

class WifiManager {
	private List<WifiDevice> devices;
	private static final Object lock = new Object();

	WifiManager() {
		devices = new ArrayList<>();
	}

	// CRUD Operations
	public void addDevice(WifiDevice device) {
		synchronized (lock) {
			devices.add(device);
			Collections.sort(devices, Comparator.comparing(d -> d.ipAddress));
		}
	}

	public void removeDevice(WifiDevice device) {
		synchronized (lock) {
			devices.remove(device);
		}
	}

	public WifiDevice getDevice(String ipAddress) {
		synchronized (lock) {
			for (WifiDevice device : devices) {
				if (device.ipAddress.equals(ipAddress)) {
					return device;
				}
			}
		}
		return null;
	}

	public void listDevices() {
		synchronized (lock) {
			for (WifiDevice device : devices) {
				System.out.println("Device: " + device.name + ", IP: " + device.ipAddress);
			}
		}
	}

	// Sorting and Searching
	public void sortDevices() {
		synchronized (lock) {
			Collections.sort(devices, Comparator.comparing(d -> d.ipAddress));
		}
	}

	public WifiDevice searchByIP(String ip) {
		synchronized (lock) {
			for (WifiDevice device : devices) {
				if (device.ipAddress.equals(ip)) {
					return device;
				}
			}
		}
		return null;
	}
}

class Router extends WifiDevice {
	WifiStatus status;

	Router(String name, String ipAddress) {
		super(name, ipAddress);
		this.status = WifiStatus.PENDING;
	}

	@Override
	void connect() {
		status = WifiStatus.CONNECTED;
		System.out.println(name + " connected.");
	}

	@Override
	void disconnect() {
		status = WifiStatus.DISCONNECTED;
		System.out.println(name + " disconnected.");
	}

	public void printStatus() {
		System.out.println(name + " Status: " + status);
	}
}

class Import_1WM {
	public static void run_Import_1WM() {
		System.err.println("\t\t1.\n");

		WifiManager manager = new WifiManager();

		Router router1 = new Router("Router1", "192.168.1.1");
		Router router2 = new Router("Router2", "192.168.1.2");
		Router router3 = new Router("Router3", "192.168.1.3");

		manager.addDevice(router1);
		manager.addDevice(router2);
		manager.addDevice(router3);

		// Listing all devices
		System.out.println("All devices:");
		manager.listDevices();

		// Sorting devices by IP
		manager.sortDevices();
		System.out.println("\nDevices sorted by IP:");
		manager.listDevices();

		// Searching for a device by IP
		WifiDevice searchedDevice = manager.searchByIP("192.168.1.2");
		if (searchedDevice != null) {
			System.out.println("\nDevice found: " + searchedDevice.name + " IP: " + searchedDevice.ipAddress);
		} else {
			System.out.println("\nDevice not found.");
		}

		// Connecting and disconnecting
		router1.connect();
		router2.disconnect();
		router1.printStatus();
		router2.printStatus();

	}
}

//Corpo da Classe _______________________________

enum StatusWiFi {
	CONECTADO, DESCONECTADO, PENDENTE;
}

enum TipoConexaoW {
	FIBRA, WIFI_4G, WIFI_5G;
}

abstract class WiFi {
	private String ip;
	private String nome;
	private StatusWiFi status;
	private TipoConexaoW tipoConexao;

	public WiFi(String ip, String nome, StatusWiFi status, TipoConexaoW tipoConexao) {
		this.ip = ip;
		this.nome = nome;
		this.status = status;
		this.tipoConexao = tipoConexao;
	}

	public abstract void exibirInfo();

	// Getters e Setters
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public StatusWiFi getStatus() {
		return status;
	}

	public void setStatus(StatusWiFi status) {
		this.status = status;
	}

	public TipoConexaoW getTipoConexao() {
		return tipoConexao;
	}

	public void setTipoConexao(TipoConexaoW tipoConexao) {
		this.tipoConexao = tipoConexao;
	}
}

class WiFiImpl extends WiFi {
	public WiFiImpl(String ip, String nome, StatusWiFi status, TipoConexaoW tipoConexao) {
		super(ip, nome, status, tipoConexao);
	}

	@Override
	public void exibirInfo() {
		System.out.println("Nome da rede: " + getNome());
		System.out.println("IP: " + getIp());
		System.out.println("Status: " + getStatus());
		System.out.println("Tipo de Conexão: " + getTipoConexao());
	}
}

class Import_2WM {

	private static List<WiFi> redesWiFi = new ArrayList<>();

	public static void adicionarRedeWiFi(Scanner scanner) {
		System.out.print("Digite o nome da rede: ");
		String nome = scanner.nextLine();
		System.out.print("Digite o IP da rede: (Ex de IP: 192.168.1.1)");
		String ip = scanner.nextLine();
		System.out.print("Digite o tipo de conexão (FIBRA, WIFI_4G, WIFI_5G): ");
		String tipo = scanner.nextLine();
		TipoConexaoW tipoConexao = TipoConexaoW.valueOf(tipo.toUpperCase());
		System.out.print("Digite o status (CONECTADO, DESCONECTADO, PENDENTE): ");
		String status = scanner.nextLine();
		StatusWiFi statusWiFi = StatusWiFi.valueOf(status.toUpperCase());

		WiFi rede = new WiFiImpl(ip, nome, statusWiFi, tipoConexao);
		redesWiFi.add(rede);
		System.out.println("Rede Wi-Fi adicionada com sucesso!");
	}

	public static void listarRedesWiFi() {
		if (redesWiFi.isEmpty()) {
			System.out.println("Não há redes Wi-Fi cadastradas.");
		} else {
			for (WiFi rede : redesWiFi) {
				rede.exibirInfo();
				System.out.println("-------------------------------");
			}
		}
	}

	public static void atualizarRedeWiFi(Scanner scanner) {
		System.out.print("Digite o IP da rede que deseja atualizar: ");
		String ip = scanner.nextLine();
		WiFi rede = buscarRedePorIp(ip);
		if (rede != null) {
			System.out.print("Digite o novo nome da rede: ");
			rede.setNome(scanner.nextLine());
			System.out.print("Digite o novo status (CONECTADO, DESCONECTADO, PENDENTE): ");
			rede.setStatus(StatusWiFi.valueOf(scanner.nextLine().toUpperCase()));
			System.out.print("Digite o novo tipo de conexão (FIBRA, WIFI_4G, WIFI_5G): ");
			rede.setTipoConexao(TipoConexaoW.valueOf(scanner.nextLine().toUpperCase()));
			System.out.println("Rede Wi-Fi atualizada com sucesso!");
		} else {
			System.out.println("Rede Wi-Fi não encontrada.");
		}
	}

	public static void removerRedeWiFi(Scanner scanner) {
		System.out.print("Digite o IP da rede que deseja remover: ");
		String ip = scanner.nextLine();
		WiFi rede = buscarRedePorIp(ip);
		if (rede != null) {
			redesWiFi.remove(rede);
			System.out.println("Rede Wi-Fi removida com sucesso!");
		} else {
			System.out.println("Rede Wi-Fi não encontrada.");
		}
	}

	public static void buscarPorIp(Scanner scanner) {
		System.out.print("Digite o IP da rede que deseja buscar: ");
		String ip = scanner.nextLine();
		WiFi rede = buscarRedePorIp(ip);
		if (rede != null) {
			rede.exibirInfo();
		} else {
			System.out.println("Rede Wi-Fi não encontrada.");
		}
	}

	public static WiFi buscarRedePorIp(String ip) {
		for (WiFi rede : redesWiFi) {
			if (rede.getIp().equals(ip)) {
				return rede;
			}
		}
		return null;
	}

	public static void ordenarRedesWiFi() {
		Collections.sort(redesWiFi, new Comparator<WiFi>() {
			@Override
			public int compare(WiFi rede1, WiFi rede2) {
				return rede1.getNome().compareTo(rede2.getNome());
			}
		});
		System.out.println("Redes Wi-Fi ordenadas com sucesso.");
	}

	public static void run_Import_2WM() {

		System.err.println("\t\t2.\n");
		Scanner scanner = new Scanner(System.in);

		// Vetor de opções do menu
		String[] menuOptions = { "📌 Adicionar rede Wi-Fi", "📌 Listar redes Wi-Fi", "📌 Atualizar rede Wi-Fi",
				"📌 Remover rede Wi-Fi", "📌 Buscar por IP", "📌 Ordenar redes Wi-Fi por nome", "⏪ Voutar para o menu",
				"👋 Sair 👋 \n" };

		int choice = 0;
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
				adicionarRedeWiFi(scanner);
				break;
			case 2:
				listarRedesWiFi();
				break;
			case 3:
				atualizarRedeWiFi(scanner);
				break;
			case 4:
				removerRedeWiFi(scanner);
				break;
			case 5:
				buscarPorIp(scanner);
				break;
			case 6:
				ordenarRedesWiFi();
				break;
			case 7:
				System.out.println("Saindo...");
				break;
			default:
				System.out.println("Opção inválida!");
			}
		} while (choice != 7);

		scanner.close();

	}
}

//Corpo da Classe _______________________________

//Enum para indicar o tipo de IP
enum IpType {
	IPV4, IPV6
}

//Enum para os tipos de operações CRUD
enum CrudOperation {
	CREATE, READ, UPDATE, DELETE
}

//Classe abstrata para operações de Wi-Fi
abstract class WifiOperation7 {
	abstract void execute(WifiNetwork network);
}

//Classe concreta para cada operação de Wi-Fi
class CreateWifiOperation extends WifiOperation7 {
	@Override
	void execute(WifiNetwork network) {
		System.out.println("Criando rede Wi-Fi: " + network);
	}
}

class ReadWifiOperation extends WifiOperation7 {
	@Override
	void execute(WifiNetwork network) {
		System.out.println("Lendo dados da rede Wi-Fi: " + network);
	}
}

class UpdateWifiOperation extends WifiOperation7 {
	@Override
	void execute(WifiNetwork network) {
		System.out.println("Atualizando rede Wi-Fi: " + network);
	}
}

class DeleteWifiOperation extends WifiOperation7 {
	@Override
	void execute(WifiNetwork network) {
		System.out.println("Deletando rede Wi-Fi: " + network);
	}
}

//Classe WifiNetwork para armazenar informações sobre as redes
class WifiNetwork implements Comparable<WifiNetwork> {
	String name;
	IpType ipType;
	String ipAddress;

	WifiNetwork(String name, IpType ipType, String ipAddress) {
		this.name = name;
		this.ipType = ipType;
		this.ipAddress = ipAddress;
	}

	@Override
	public String toString() {
		return "Wi-Fi Nome: " + name + ", IP: " + ipAddress + ", Tipo de IP: " + ipType;
	}

	@Override
	public int compareTo(WifiNetwork o) {
		return this.name.compareTo(o.name); // Ordenação pelo nome
	}
}

class Import_3WM {
	private static List<WifiNetwork> wifiList = new ArrayList<>();
	private static Lock lock = new ReentrantLock();

	public static void run_Import_3WM() {
		System.err.println("\t\t3. Rede IPV4 E IPV6 \n");

		// Adicionando redes Wi-Fi
		wifiList.add(new WifiNetwork("WiFi_A", IpType.IPV4, "192.168.0.1"));
		wifiList.add(new WifiNetwork("WiFi_B", IpType.IPV6, "2001:0db8:85a3:0000:0000:8a2e:0370:7334"));
		wifiList.add(new WifiNetwork("WiFi_C", IpType.IPV4, "192.168.0.2"));
		System.out.println("___________________________");

		// Executando operações CRUD com uso de enum
		performCrudOperation(CrudOperation.CREATE,
				new WifiNetwork("WiFi_D", IpType.IPV6, "2001:0db8:85a3:0000:0000:8a2e:0370:7335"));
		performCrudOperation(CrudOperation.READ, wifiList.get(0));
		performCrudOperation(CrudOperation.UPDATE, new WifiNetwork("WiFi_A", IpType.IPV4, "192.168.0.10"));
		System.out.println("___________________________");

		performCrudOperation(CrudOperation.DELETE, wifiList.get(2));
		System.out.println("___________________________");

		// Ordenação
		Collections.sort(wifiList);
		System.out.println("Listagem de redes Wi-Fi ordenadas:");
		for (WifiNetwork network : wifiList) {
			System.out.println(network);
		}

		// Busca
		WifiNetwork foundNetwork = searchNetwork("WiFi_B");
		if (foundNetwork != null) {
			System.out.println("Rede encontrada: " + foundNetwork);
		} else {
			System.out.println("Rede não encontrada.");
		}

		// Sincronização com Lock
		lock.lock();
		try {
			// Exemplo de operação sincronizada (modificação da lista)
			wifiList.add(new WifiNetwork("WiFi_E", IpType.IPV4, "192.168.0.3"));
			System.out.println("Lista de redes Wi-Fi após modificação:");
			for (WifiNetwork network : wifiList) {
				System.out.println(network);
			}
		} finally {
			lock.unlock();
		}
	}

	// Método para realizar a operação CRUD
	private static void performCrudOperation(CrudOperation operation, WifiNetwork network) {
		WifiOperation7 wifiOperation = null;

		switch (operation) {
		case CREATE:
			wifiOperation = new CreateWifiOperation();
			System.out.println("___________________________");
			break;
		case READ:
			wifiOperation = new ReadWifiOperation();
			System.out.println("___________________________");
			break;
		case UPDATE:
			wifiOperation = new UpdateWifiOperation();
			System.out.println("___________________________");
			break;
		case DELETE:
			wifiOperation = new DeleteWifiOperation();
			System.out.println("___________________________");
			break;
		}

		if (wifiOperation != null) {
			wifiOperation.execute(network);
		}
	}

	// Método para realizar busca de rede Wi-Fi por nome
	private static WifiNetwork searchNetwork(String name) {
		for (WifiNetwork network : wifiList) {
			if (network.name.equals(name)) {
				return network;
			}
		}
		return null; // Retorna null se não encontrar

	}
}