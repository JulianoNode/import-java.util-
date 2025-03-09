package UTIL_07_Collections;

import util.Linhas;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class For_Matrix {
	public static void For_Run() {

		Linhas linhas = new Linhas();
		String collec = "\n\t Collections: Métodos utilitários para trabalhar com coleções (ex.: ordenação, busca, sincronização). \n";
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
//Enum para tipos de usuários
enum UserType {
	ADMIN, MODERATOR, REGULAR
}

//Classe Abstrata para definir a estrutura de um Usuário
abstract class User {
	protected String name;
	protected String email;
	protected UserType type;

	public User(String name, String email, UserType type) {
		this.name = name;
		this.email = email;
		this.type = type;
	}

	// Método abstrato para exibir informações
	public abstract void displayUserInfo();

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public UserType getType() {
		return type;
	}
}

//Classe concreta que estende User
class NetworkUser extends User {
	public NetworkUser(String name, String email, UserType type) {
		super(name, email, type);
	}

	@Override
	public void displayUserInfo() {
		System.out.println("Name: " + name + ", Email: " + email + ", Type: " + type);
	}
}

//Classe de gerenciamento de usuários
class UserManager {
	private List<User> users;
	private final ReentrantLock lock;

	public UserManager() {
		this.users = new ArrayList<>();
		this.lock = new ReentrantLock();
	}

	// Método para adicionar um usuário
	public void addUser(User user) {
		lock.lock();
		try {
			users.add(user);
		} finally {
			lock.unlock();
		}
	}

	// Método para listar todos os usuários
	public void listUsers() {
		lock.lock();
		try {
			for (User user : users) {
				user.displayUserInfo();
			}
		} finally {
			lock.unlock();
		}
	}

	// Método para buscar um usuário por e-mail
	public User getUserByEmail(String email) {
		lock.lock();
		try {
			for (User user : users) {
				if (user.getEmail().equals(email)) {
					return user;
				}
			}
		} finally {
			lock.unlock();
		}
		return null;
	}

	// Método para atualizar um usuário
	public void updateUser(String email, String newName, String newEmail, UserType newType) {
		lock.lock();
		try {
			User user = getUserByEmail(email);
			if (user != null) {
				users.remove(user);
				users.add(new NetworkUser(newName, newEmail, newType));
			}
		} finally {
			lock.unlock();
		}
	}

	// Método para remover um usuário
	public void removeUser(String email) {
		lock.lock();
		try {
			User user = getUserByEmail(email);
			if (user != null) {
				users.remove(user);
			}
		} finally {
			lock.unlock();
		}
	}

	// Método para ordenar usuários por nome
	public void sortUsers() {
		lock.lock();
		try {
			Collections.sort(users, Comparator.comparing(User::getName));
		} finally {
			lock.unlock();
		}
	}
}

class Import_1F {
	public static void run_Import_1F() {
		System.err.println("\t\t1. Usuario de Rede\n");

		UserManager manager = new UserManager();

		// Criando usuários
		User user1 = new NetworkUser("John Doe", "john@example.com", UserType.ADMIN);
		User user2 = new NetworkUser("Jane Smith", "jane@example.com", UserType.MODERATOR);
		User user3 = new NetworkUser("Alice Brown", "alice@example.com", UserType.REGULAR);

		// Adicionando usuários
		manager.addUser(user1);
		manager.addUser(user2);
		manager.addUser(user3);

		// Listando usuários
		System.out.println("Lista de usuários:");
		manager.listUsers();

		// Ordenando usuários por nome
		System.out.println("\nLista de usuários ordenada por nome:");
		manager.sortUsers();
		manager.listUsers();

		// Atualizando um usuário
		System.out.println("\nAtualizando usuário:");
		manager.updateUser("alice@example.com", "Alice Cooper", "alice.cooper@example.com", UserType.MODERATOR);
		manager.listUsers();

		// Removendo um usuário
		System.out.println("\nRemovendo usuário:");
		manager.removeUser("jane@example.com");
		manager.listUsers();

	}
}

//Corpo da Classe _______________________________

enum TipoConexao {
	WIFI, QUATRO_G, CINCO_G
}

abstract class Dispositivo {
	protected String nome;
	protected TipoConexao tipoConexao;
	protected String enderecoMac;

	public Dispositivo(String nome, TipoConexao tipoConexao, String enderecoMac) {
		this.nome = nome;
		this.tipoConexao = tipoConexao;
		this.enderecoMac = enderecoMac;
	}

	public abstract void conectar();

	@Override
	public String toString() {
		return "Dispositivo [nome=" + nome + ", tipoConexao=" + tipoConexao + ", enderecoMac=" + enderecoMac + "]";
	}
}

class Wifi extends Dispositivo {
	public Wifi(String nome, String enderecoMac) {
		super(nome, TipoConexao.WIFI, enderecoMac);
	}

	@Override
	public void conectar() {
		System.out.println(nome + " está conectado via Wi-Fi.");
	}
}

class CrudWifi {
	private List<Dispositivo> dispositivos = Collections.synchronizedList(new ArrayList<>());

	// Método para adicionar dispositivos
	public void adicionar(Dispositivo dispositivo) {
		dispositivos.add(dispositivo);
	}

	// Método para buscar dispositivos por nome
	public Dispositivo buscarPorNome(String nome) {
		for (Dispositivo dispositivo : dispositivos) {
			if (dispositivo.nome.equals(nome)) {
				return dispositivo;
			}
		}
		return null;
	}

	// Método para ordenar os dispositivos por nome
	public void ordenarPorNome() {
		dispositivos.sort(Comparator.comparing(d -> d.nome));
	}

	// Método para exibir todos os dispositivos
	public void listarTodos() {
		dispositivos.forEach(System.out::println);
	}
}

class Import_2F {
	public static void run_Import_2F() {
		System.err.println("\t\t2. Usuario de WIFI \n");

		CrudWifi crud = new CrudWifi();

		// Adicionando dispositivos
		Dispositivo wifi1 = new Wifi("WiFi-Home", "00:1A:2B:3C:4D:5E");
		Dispositivo wifi2 = new Wifi("WiFi-Office", "00:1A:2B:3C:4D:6F");

		crud.adicionar(wifi1);
		crud.adicionar(wifi2);

		// Ordenando e listando dispositivos
		crud.ordenarPorNome();
		crud.listarTodos();

		// Buscando um dispositivo específico
		Dispositivo dispositivoBuscado = crud.buscarPorNome("WiFi-Home");
		if (dispositivoBuscado != null) {
			System.out.println("\nDispositivo encontrado: " + dispositivoBuscado);
		}

		// Conectando o dispositivo
		if (dispositivoBuscado != null) {
			dispositivoBuscado.conectar();
		}
	}
}

//Corpo da Classe _______________________________

//Enum para representar as operações CRUD
enum WifiOperation {
	CREATE, READ, UPDATE, DELETE
}

//Classe abstrata para métodos de sincronização, ordenação e busca
abstract class WifiCrudOperations {
	protected Lock lock = new ReentrantLock();

	// Método abstrato para ordenar os IPs
	public abstract void sortIPs(List<String> wifiList);

	// Método abstrato para buscar um IP
	public abstract boolean searchIP(List<String> wifiList, String ip);

	// Método abstrato para sincronizar as operações
	public abstract void synchronizeCrudOperation(WifiOperation operation, List<String> wifiList, String ip);
}

//Classe concreta que implementa os métodos abstratos
class WifiCrud extends WifiCrudOperations {

	// Implementação do método de ordenação
	@Override
	public void sortIPs(List<String> wifiList) {
		Collections.sort(wifiList);
		System.out.println("Wi-Fi list sorted: " + wifiList);
	}

	// Implementação do método de busca
	@Override
	public boolean searchIP(List<String> wifiList, String ip) {
		return wifiList.contains(ip);
	}

	// Implementação do método de sincronização
	@Override
	public void synchronizeCrudOperation(WifiOperation operation, List<String> wifiList, String ip) {
		lock.lock();
		try {
			switch (operation) {
			case CREATE:
				wifiList.add(ip);
				System.out.println("IP added: " + ip);
				break;
			case READ:
				System.out.println("Wi-Fi list: " + wifiList);
				break;
			case UPDATE:
				if (wifiList.contains(ip)) {
					int index = wifiList.indexOf(ip);
					wifiList.set(index, ip + "_updated");
					System.out.println("IP updated: " + ip);
				} else {
					System.out.println("IP not found to update");
				}
				break;
			case DELETE:
				wifiList.remove(ip);
				System.out.println("IP removed: " + ip);
				break;
			default:
				System.out.println("Invalid operation");
				break;
			}
		} finally {
			lock.unlock();
		}
	}
}

class Import_3F {
	public static void run_Import_3F() {
		System.err.println("\t\t3.Wifi Com IP\n");

		// Inicializando a lista de IPs de Wi-Fi
		List<String> wifiList = new ArrayList<>();

		// Criando a instância do WifiCrud
		WifiCrud wifiCrud = new WifiCrud();

		// Simulando operações CRUD com sincronização
		wifiCrud.synchronizeCrudOperation(WifiOperation.CREATE, wifiList, "192.168.0.1");
		wifiCrud.synchronizeCrudOperation(WifiOperation.CREATE, wifiList, "192.168.0.2");
		wifiCrud.synchronizeCrudOperation(WifiOperation.CREATE, wifiList, "192.168.0.3");

		wifiCrud.synchronizeCrudOperation(WifiOperation.READ, wifiList, null);

		wifiCrud.synchronizeCrudOperation(WifiOperation.UPDATE, wifiList, "192.168.0.2");

		wifiCrud.synchronizeCrudOperation(WifiOperation.READ, wifiList, null);

		wifiCrud.synchronizeCrudOperation(WifiOperation.DELETE, wifiList, "192.168.0.1");

		wifiCrud.synchronizeCrudOperation(WifiOperation.READ, wifiList, null);

		// Ordenando a lista
		wifiCrud.sortIPs(wifiList);

		// Buscando um IP
		boolean found = wifiCrud.searchIP(wifiList, "192.168.0.3");
		System.out.println("IP found: " + found);

	}
}
