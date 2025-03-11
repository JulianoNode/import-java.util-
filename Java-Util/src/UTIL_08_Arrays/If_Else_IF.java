package UTIL_08_Arrays;

import util.Linhas;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class If_Else_IF {
	public static void If_Else_IF_Run() {

		Linhas linhas = new Linhas();
		String collec = "\n\t Arrays: Métodos utilitários para trabalhar com arrays (ex.: ordenação, busca \n";
		System.err.println(collec);

		linhas.run_Caracteres();
		Import_1IF.run_Import_1IF();

		linhas.run_Caracteres();
		Import_2IF.run_Import_2IF();

		linhas.run_Caracteres();
		Import_3IF.run_Import_3IF();
	}
}

//Corpo da Classe _______________________________
//Enum para status da mensagem
enum MessageStatus {
	SENT, DELIVERED, READ, FAILED, PENDING;
}

//Enum para tipo de mensagem
enum MessageType {
	TEXT, IMAGE, VIDEO, AUDIO, DOCUMENT;
}

//Enum para prioridade da mensagem
enum MessagePriority {
	LOW, MEDIUM, HIGH, URGENT, IMMEDIATE;
}

//Enum para ações CRUD
enum CRUDAction {
	CREATE, READ, UPDATE, DELETE, SEARCH;
}

//Enum para métodos de ordenação
enum SortMethod {
	BY_DATE, BY_STATUS, BY_PRIORITY;
}

//Classe abstrata para mensagem
abstract class AbstractMessage {
	protected String content;
	protected Date timestamp;
	protected MessageStatus status;
	protected MessageType type;
	protected MessagePriority priority;

	public AbstractMessage(String content, MessageType type, MessagePriority priority) {
		this.content = content;
		this.timestamp = new Date();
		this.status = MessageStatus.PENDING;
		this.type = type;
		this.priority = priority;
	}

	public abstract void displayMessage();
}

//Classe concreta de mensagem
class Message extends AbstractMessage {
	public Message(String content, MessageType type, MessagePriority priority) {
		super(content, type, priority);
	}

	@Override
	public void displayMessage() {
		System.out.println("[" + timestamp + "] " + type + " - " + priority + ": " + content + " (" + status + ")");
	}
}

//Gerenciador de mensagens
class MessageManager {
	private List<Message> messages = new ArrayList<>();
	private ReentrantLock lock = new ReentrantLock();

	public void performAction(CRUDAction action, Message message) {
		lock.lock();
		try {
			if (action == CRUDAction.CREATE) {
				messages.add(message);
				System.out.println("Mensagem adicionada.");
			} else if (action == CRUDAction.READ) {
				messages.forEach(Message::displayMessage);
			} else if (action == CRUDAction.UPDATE) {
				for (Message msg : messages) {
					if (msg.equals(message)) {
						msg.status = MessageStatus.READ;
						System.out.println("Mensagem atualizada.");
						break;
					}
				}
			} else if (action == CRUDAction.DELETE) {
				messages.remove(message);
				System.out.println("Mensagem removida.");
			} else if (action == CRUDAction.SEARCH) {
				for (Message msg : messages) {
					if (msg.content.contains(message.content)) {
						msg.displayMessage();
						break;
					}
				}
			} else {
				System.out.println("Ação inválida.");
			}
		} finally {
			lock.unlock();
		}
	}

	public void sortMessages(SortMethod method) {
		lock.lock();
		try {
			if (method == SortMethod.BY_DATE) {
				messages.sort(Comparator.comparing(m -> m.timestamp));
			} else if (method == SortMethod.BY_STATUS) {
				messages.sort(Comparator.comparing(m -> m.status));
			} else if (method == SortMethod.BY_PRIORITY) {
				messages.sort(Comparator.comparing(m -> m.priority));
			} else {
				System.out.println("Método de ordenação inválido.");
			}
		} finally {
			lock.unlock();
		}
	}
}

class Import_1IF {
	public static void run_Import_1IF() {
		System.err.println("\t\t1. Mesagem Whats App \n");

		MessageManager manager = new MessageManager();
		Message msg1 = new Message("Oi, tudo bem?", MessageType.TEXT, MessagePriority.HIGH);
		Message msg2 = new Message("Veja essa imagem", MessageType.IMAGE, MessagePriority.MEDIUM);

		manager.performAction(CRUDAction.CREATE, msg1);
		manager.performAction(CRUDAction.CREATE, msg2);

		System.out.println("Mensagens antes da ordenação:");
		manager.performAction(CRUDAction.READ, null);

		manager.sortMessages(SortMethod.BY_PRIORITY);
		System.out.println("Mensagens após ordenação por prioridade:");
		manager.performAction(CRUDAction.READ, null);
	}

}

//Corpo da Classe _______________________________
//Enum para representar o status do dispositivo Bluetooth
enum BluetoothStatus {
	PAREADO, CONECTADO, DESCONECTADO, ATIVO, INATIVO;
}

//Classe abstrata para definir estrutura do CRUD
abstract class BluetoothDeviceManager {
	abstract void adicionarDispositivo(BluetoothDevice device);

	abstract void removerDispositivo(String nome);

	abstract BluetoothDevice buscarDispositivo(String nome);

	abstract void ordenarDispositivos();

	abstract void sincronizar();
}

//Classe concreta que gerencia os dispositivos Bluetooth
class BluetoothManager extends BluetoothDeviceManager {
	private BluetoothDevice[] dispositivos = new BluetoothDevice[0];

	@Override
	public void adicionarDispositivo(BluetoothDevice device) {
		dispositivos = Arrays.copyOf(dispositivos, dispositivos.length + 1);
		dispositivos[dispositivos.length - 1] = device;
	}

	@Override
	public void removerDispositivo(String nome) {
		for (int i = 0; i < dispositivos.length; i++) {
			if (dispositivos[i].getNome().equalsIgnoreCase(nome)) {
				BluetoothDevice[] novoArray = new BluetoothDevice[dispositivos.length - 1];
				System.arraycopy(dispositivos, 0, novoArray, 0, i);
				System.arraycopy(dispositivos, i + 1, novoArray, i, dispositivos.length - i - 1);
				dispositivos = novoArray;
				break;
			}
		}
	}

	@Override
	public BluetoothDevice buscarDispositivo(String nome) {
		for (BluetoothDevice device : dispositivos) {
			if (device.getNome().equalsIgnoreCase(nome)) {
				return device;
			}
		}
		return null;
	}

	@Override
	public void ordenarDispositivos() {
		Arrays.sort(dispositivos, Comparator.comparing(BluetoothDevice::getNome));
	}

	@Override
	public void sincronizar() {
		for (BluetoothDevice device : dispositivos) {
			if (device.getStatus() == BluetoothStatus.PAREADO) {
				device.setStatus(BluetoothStatus.CONECTADO);
			}
		}
	}

	public void listarDispositivos() {
		for (BluetoothDevice device : dispositivos) {
			System.out.println(device);
		}
	}
}

//Classe BluetoothDevice
class BluetoothDevice {
	private String nome;
	private BluetoothStatus status;

	public BluetoothDevice(String nome, BluetoothStatus status) {
		this.nome = nome;
		this.status = status;
	}

	public String getNome() {
		return nome;
	}

	public BluetoothStatus getStatus() {
		return status;
	}

	public void setStatus(BluetoothStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Dispositivo: " + nome + ", Status: " + status;
	}
}

//Classe principal para testar o CRUD
class Import_2IF {
	public static void run_Import_2IF() {
		System.err.println("\t\t2.Codigo de Bluetooth \n");
		BluetoothManager manager = new BluetoothManager();

		// Adicionando dispositivos
		manager.adicionarDispositivo(new BluetoothDevice("Fone", BluetoothStatus.PAREADO));
		manager.adicionarDispositivo(new BluetoothDevice("Teclado", BluetoothStatus.DESCONECTADO));
		manager.adicionarDispositivo(new BluetoothDevice("Mouse", BluetoothStatus.ATIVO));

		// Listando dispositivos
		System.out.println("Dispositivos antes da ordenação:");
		manager.listarDispositivos();

		// Ordenação
		manager.ordenarDispositivos();
		System.out.println("\nDispositivos após ordenação:");
		manager.listarDispositivos();

		// Busca
		BluetoothDevice encontrado = manager.buscarDispositivo("Mouse");
		System.out.println("\nDispositivo encontrado: " + (encontrado != null ? encontrado : "Nenhum encontrado"));

		// Sincronização
		manager.sincronizar();
		System.out.println("\nDispositivos após sincronização:");
		manager.listarDispositivos();

		// Remoção
		manager.removerDispositivo("Teclado");
		System.out.println("\nDispositivos após remoção:");
		manager.listarDispositivos();
	}
}

//Corpo da Classe _______________________________
//Enum para Status do Host
enum HostStatus {
	ACTIVE, INACTIVE, MAINTENANCE, DECOMMISSIONED, UNKNOWN;
}

//Enum para Tipo de Host
enum HostType {
	WEB_SERVER, DATABASE, APPLICATION, FILE_SERVER, CACHE;
}

//Enum para Prioridade
enum HostPriority {
	HIGH, MEDIUM, LOW, CRITICAL, NORMAL;
}

//Enum para Sistema Operacional
enum HostOS {
	LINUX, WINDOWS, MACOS, UNIX, OTHER;
}

//Enum para Localização
enum HostLocation {
	USA, EUROPE, ASIA, SOUTH_AMERICA, AFRICA;
}

//Classe abstrata Host
abstract class AbstractHost {
	protected String name;
	protected HostStatus status;
	protected HostType type;
	protected HostPriority priority;
	protected HostOS os;
	protected HostLocation location;

	public AbstractHost(String name, HostStatus status, HostType type, HostPriority priority, HostOS os,
			HostLocation location) {
		this.name = name;
		this.status = status;
		this.type = type;
		this.priority = priority;
		this.os = os;
		this.location = location;
	}

	public abstract void display();
}

//Classe concreta Host
class Host extends AbstractHost {
	public Host(String name, HostStatus status, HostType type, HostPriority priority, HostOS os,
			HostLocation location) {
		super(name, status, type, priority, os, location);
	}

	@Override
	public void display() {
		System.out.println("Host: " + name + " | Status: " + status + " | Type: " + type + " | Priority: " + priority
				+ " | OS: " + os + " | Location: " + location);
	}
}

//CRUD de Hosts
class HostManager {
	private List<Host> hosts = Collections.synchronizedList(new ArrayList<>());
	private Lock lock = new ReentrantLock();

	public void addHost(Host host) {
		lock.lock();
		try {
			hosts.add(host);
		} finally {
			lock.unlock();
		}
	}

	public void removeHost(String name) {
		lock.lock();
		try {
			hosts.removeIf(host -> host.name.equalsIgnoreCase(name));
		} finally {
			lock.unlock();
		}
	}

	public Host searchHost(String name) {
		lock.lock();
		try {
			for (Host host : hosts) {
				if (host.name.equalsIgnoreCase(name)) {
					return host;
				}
			}
			return null;
		} finally {
			lock.unlock();
		}
	}

	public void sortHosts() {
		lock.lock();
		try {
			hosts.sort(Comparator.comparing(h -> h.name));
		} finally {
			lock.unlock();
		}
	}

	public void displayHosts() {
		lock.lock();
		try {
			for (Host host : hosts) {
				host.display();
			}
		} finally {
			lock.unlock();
		}
	}
}

class Import_3IF {
	public static void run_Import_3IF() {
		System.err.println("\t\t3. HostName \n");
		HostManager manager = new HostManager();

        manager.addHost(new Host("Server1", HostStatus.ACTIVE, HostType.WEB_SERVER, HostPriority.HIGH, HostOS.LINUX, HostLocation.USA));
        manager.addHost(new Host("Server2", HostStatus.INACTIVE, HostType.DATABASE, HostPriority.MEDIUM, HostOS.WINDOWS, HostLocation.EUROPE));
        manager.addHost(new Host("Server3", HostStatus.MAINTENANCE, HostType.APPLICATION, HostPriority.CRITICAL, HostOS.UNIX, HostLocation.ASIA));
        
        System.out.println("Antes da ordenação:");
        manager.displayHosts();

        manager.sortHosts();
        System.out.println("\nApós a ordenação:");
        manager.displayHosts();

        System.out.println("\nBuscando Server2:");
        Host found = manager.searchHost("Server2");
        if (found != null) {
            found.display();
        } else {
            System.out.println("Host não encontrado.");
        }
    }
}
