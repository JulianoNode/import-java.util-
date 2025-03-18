package UTIL_09_Iterator;

import util.Linhas;
import util.VoutarRun;

import java.util.*;

public class Vetor_ {
	public static void Vetor_Run() {

		Linhas linhas = new Linhas();
		String collec = "\n\t Iterator<E>: Interface para iterar sobre elementos de uma coleção. \n";
		System.err.println(collec);

		linhas.run_Caracteres();
		Import_1V.run_Import_1V();

		linhas.run_Caracteres();
		Import_2V.run_Import_2V();

		linhas.run_Caracteres();
		Import_3V.run_Import_3V();
	}
}

//Corpo da Classe _______________________________
//1 Corpo da Classe Abstract e Enum _______________________________
//1️⃣ ENUMs - Representam diferentes tipos de Host
enum TipoHost {
	DEDICADO, VPS, COMPARTILHADO, CLOUD, EDGE, REVENDA, BARE_METAL, GERENCIADO, SERVIDOR_FISICO, CONTAINER;
}

//2️⃣ Classes abstratas
abstract class Host {
	String nome;
	TipoHost tipo;
	int capacidade;

	abstract void detalhes();
}

abstract class Servidor extends Host {
}

abstract class Virtualizacao extends Host {
}

abstract class Armazenamento extends Host {
}

abstract class Balanceamento extends Host {
}

abstract class Monitoramento extends Host {
}

abstract class Segurança extends Host {
}

abstract class Rede extends Host {
}

abstract class Backup extends Host {
}

abstract class Escalabilidade extends Host {
}

abstract class Gerenciamento extends Host {
}

//3️⃣ Implementação CRUD e Métodos
class Import_1V {
	private final List<Host> hosts = Collections.synchronizedList(new ArrayList<>());
	private final Scanner scanner = new Scanner(System.in);

	// Adiciona um novo host
	public void adicionarHost(Host host) {
		hosts.add(host);
		System.out.println("Host adicionado com sucesso!");
	}

	// Remove um host
	public void removerHost(String nome) {
		Iterator<Host> iterator = hosts.iterator();
		while (iterator.hasNext()) {
			if (iterator.next().nome.equalsIgnoreCase(nome)) {
				iterator.remove();
				System.out.println("Host removido!");
				return;
			}
		}
		System.out.println("Host não encontrado.");
	}

	// Busca um host
	public void buscarHost(String nome) {
		for (Host host : hosts) {
			if (host.nome.equalsIgnoreCase(nome)) {
				System.out.println("Encontrado: " + host.nome + " - Tipo: " + host.tipo);
				return;
			}
		}
		System.out.println("Host não encontrado.");
	}

	// Ordenação (Por nome)
	public void ordenarHosts() {
		hosts.sort(Comparator.comparing(h -> h.nome));
		System.out.println("Hosts ordenados por nome!");
	}

	// Exibir todos os hosts
	public void listarHosts() {
		synchronized (hosts) {
			for (Host host : hosts) {
				System.out.println(host.nome + " - " + host.tipo);
			}
		}
	}

	// Menu interativo
	public void menu() {
		int opcao;
		do {
			System.out.println(
					"1. Adicionar Host\n2. Remover Host\n3. Buscar Host\n4. Listar Hosts\n5. Ordenar Hosts\n0. Sair");
			opcao = scanner.nextInt();
			scanner.nextLine();

			switch (opcao) {
			case 1:
				System.out.print("Nome do Host: ");
				String nome = scanner.nextLine();
				adicionarHost(new Virtualizacao() {
					{
						nome = nome;
						tipo = TipoHost.VPS;
					}

					@Override
					void detalhes() {
					}
				});
				break;
			case 2:
				System.out.print("Nome do Host a remover: ");
				removerHost(scanner.nextLine());
				break;
			case 3:
				System.out.print("Nome do Host a buscar: ");
				buscarHost(scanner.nextLine());
				break;
			case 4:
				listarHosts();
				break;
			case 5:
				ordenarHosts();
				break;
			case 0:
				System.out.println("Saindo...");
				break;
			default:
				System.out.println("Opção inválida!");
			}
		} while (opcao != 0);
		VoutarRun voutarRun = new VoutarRun();
		voutarRun.run_CaracteresMenu_Red();
	}

	public static void run_Import_1V() {
		System.err.println("\t\t1. tipos Host \n");
		Import_1V host = new Import_1V();
		host.menu();
	}
}

//Corpo da Classe _______________________________

//Enum representando tipos de criptografia suportadas por VPNs
enum EncryptionType {
	AES, DES, RSA, ECC, CHACHA20, BLOWFISH, TWO_FISH, CAMELLIA, SERPENT, IDEA;
}

//Classe abstrata base para diferentes categorias de VPNs
abstract class VPNCategory {
	protected String name;
	protected EncryptionType encryption;

	public VPNCategory(String name, EncryptionType encryption) {
		this.name = name;
		this.encryption = encryption;
	}

	public abstract void displayInfo();
}

//Classe abstrata 1
abstract class SecureVPN extends VPNCategory {
	public SecureVPN(String name, EncryptionType encryption) {
		super(name, encryption);
	}
}

//Classe concreta
class CommercialVPN extends SecureVPN {
	public CommercialVPN(String name, EncryptionType encryption) {
		super(name, encryption);
	}

	@Override
	public void displayInfo() {
		System.out.println("Commercial VPN: " + name + " with " + encryption + " encryption.");
	}
}

class VPNManager {
	private List<VPNCategory> vpns = new ArrayList<>();

	public synchronized void addVPN(VPNCategory vpn) {
		vpns.add(vpn);
	}

	public synchronized void removeVPN(String name) {
		Iterator<VPNCategory> iterator = vpns.iterator();
		while (iterator.hasNext()) {
			VPNCategory vpn = iterator.next();
			if (vpn.name.equals(name)) {
				iterator.remove();
				break;
			}
		}
	}

	public void searchVPN(String name) {
		for (VPNCategory vpn : vpns) {
			if (vpn.name.equalsIgnoreCase(name)) {
				vpn.displayInfo();
				return;
			}
		}
		System.out.println("VPN not found.");
	}

	public void sortVPNs() {
		vpns.sort(Comparator.comparing(v -> v.name));
	}

	public void listVPNs() {
		for (VPNCategory vpn : vpns) {
			vpn.displayInfo();
		}
	}
}

class Import_2V {

	public static void menu() {
		VPNManager manager = new VPNManager();
		Scanner scanner = new Scanner(System.in);

		int choice;
		do {
			System.out.println("1. Add VPN\n2. Remove VPN\n3. Search VPN\n4. Sort VPNs\n5. List VPNs\n6. Exit");
			choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				System.out.print("Enter VPN name: ");
				String name = scanner.nextLine();
				System.out.print("Choose encryption (0-9): ");
				int encIdx = scanner.nextInt();
				if (encIdx < 0 || encIdx >= EncryptionType.values().length) {
					System.out.println("Invalid choice.");
					break;
				}
				EncryptionType encType = EncryptionType.values()[encIdx];
				manager.addVPN(new CommercialVPN(name, encType));
				break;
			case 2:
				System.out.print("Enter VPN name to remove: ");
				manager.removeVPN(scanner.nextLine());
				break;
			case 3:
				System.out.print("Enter VPN name to search: ");
				manager.searchVPN(scanner.nextLine());
				break;
			case 4:
				manager.sortVPNs();
				System.out.println("VPNs sorted.");
				break;
			case 5:
				manager.listVPNs();
				break;
			case 6:
				System.out.println("Exiting...");
				break;
			default:
				System.out.println("Invalid option.");
			}
		} while (choice != 6);
		VoutarRun voutarRun = new VoutarRun();
		voutarRun.run_CaracteresMenu_Red();
	}

	public static void run_Import_2V() {
		System.err.println("\t\t2. Tipos de VPN\n");
		Import_2V.menu();
	}
}

//Corpo da Classe _______________________________
//10 Enums para diferentes categorias de VPN e Endereços na Net
enum TipoVPN {
	PPTP, L2TP, IPSec, OpenVPN, WireGuard, SSTP, IKEv2, SoftEther, ZeroTier, Shadowsocks;
}

enum Protocolo {
	TCP, UDP, HTTP, HTTPS, SOCKS5, ICMP, GRE, ESP, AH, L2TP;
}

//10 Classes Abstratas
abstract class Rede2 {
	String endereco;

	abstract void conectar();
}

abstract class VPN extends Rede2 {
	TipoVPN tipo;

	abstract void autenticar();
}

abstract class EnderecoNet extends Rede2 {
	String dominio;

	abstract void resolverDNS();
}

abstract class Servidor2 extends VPN {
	String localizacao;

	abstract void configurar();
}

abstract class Cliente extends VPN {
	String usuario;

	abstract void conectarCliente();
}

abstract class Proxy extends EnderecoNet {
	Protocolo protocolo;

	abstract void redirecionar();
}

abstract class Firewall extends EnderecoNet {
	boolean ativo;

	abstract void bloquear();
}

abstract class Roteador extends Rede2 {
	int portas;

	abstract void encaminharPacotes();
}

abstract class IDS extends Rede2 {
	boolean monitoramento;

	abstract void detectarAmeacas();
}

abstract class CDN extends Rede2 {
	int cache;

	abstract void distribuirConteudo();
}

//Implementação de classe concreta
class ServidorVPN extends Servidor2 {
	ServidorVPN(String endereco, TipoVPN tipo, String localizacao) {
		this.endereco = endereco;
		this.tipo = tipo;
		this.localizacao = localizacao;
	}

	@Override
	void conectar() {
		System.out.println("Conectando ao servidor VPN em: " + endereco);
	}

	@Override
	void autenticar() {
		System.out.println("Autenticando na VPN tipo: " + tipo);
	}

	@Override
	void configurar() {
		System.out.println("Configurando servidor em " + localizacao);
	}
}

class Import_3V {
	public static void V3_Run() {
		Scanner scanner = new Scanner(System.in);
		List<ServidorVPN> servidores = new ArrayList<>();
		servidores.add(new ServidorVPN("192.168.1.1", TipoVPN.OpenVPN, "EUA"));
		servidores.add(new ServidorVPN("192.168.1.2", TipoVPN.WireGuard, "Brasil"));
		servidores.add(new ServidorVPN("192.168.1.3", TipoVPN.IKEv2, "França"));
		servidores.add(new ServidorVPN("192.168.1.4", TipoVPN.PPTP, "Berlin"));
		servidores.add(new ServidorVPN("192.168.1.5", TipoVPN.SSTP, "Alemanha"));
		servidores.add(new ServidorVPN("192.168.1.6", TipoVPN.Shadowsocks, "Canada"));
		servidores.add(new ServidorVPN("192.168.1.7", TipoVPN.SoftEther, "China"));

		int opcao;
		do {
			System.out.println("1 - Listar Servidores VPN");
			System.out.println("2 - Adicionar Servidor");
			System.out.println("3 - Buscar Servidor");
			System.out.println("4 - Ordenar Servidores");
			System.out.println("5 - Sair");
			opcao = scanner.nextInt();
			scanner.nextLine();

			switch (opcao) {
			case 1:
				System.out.println("Lista de Servidores:");
				for (ServidorVPN s : servidores) {
					System.out.println(s.endereco + " - " + s.tipo + " - " + s.localizacao);
				}
				break;
			case 2:
				System.out.println("Digite o endereço:");
				String endereco = scanner.nextLine();
				System.out.println("Escolha o tipo de VPN (0 a 9):");
				for (TipoVPN t : TipoVPN.values()) {
					System.out.println(t.ordinal() + " - " + t);
				}
				TipoVPN tipo = TipoVPN.values()[scanner.nextInt()];
				scanner.nextLine();
				System.out.println("Digite a localização:");
				String localizacao = scanner.nextLine();
				servidores.add(new ServidorVPN(endereco, tipo, localizacao));
				System.out.println("Servidor adicionado!");
				break;
			case 3:
				System.out.println("Digite o endereço para buscar:");
				String busca = scanner.nextLine();
				boolean encontrado = false;
				Iterator<ServidorVPN> iterator = servidores.iterator();
				while (iterator.hasNext()) {
					ServidorVPN s = iterator.next();
					if (s.endereco.equals(busca)) {
						System.out.println("Encontrado: " + s.endereco + " - " + s.tipo + " - " + s.localizacao);
						encontrado = true;
						break;
					}
				}
				if (!encontrado)
					System.out.println("Servidor não encontrado.");
				break;
			case 4:
				servidores.sort(Comparator.comparing(s -> s.endereco));
				System.out.println("Servidores ordenados pelo endereço IP!");
				break;
			case 5:
				System.out.println("Saindo...");
				break;
			default:
				System.out.println("Opção inválida!");
			}
		} while (opcao != 5);
		VoutarRun voutarRun = new VoutarRun();
		voutarRun.run_CaracteresMenu_Red();
	}

	public static void run_Import_3V() {
		System.err.println("\t\t3. Tipos de VPN \n");
		Import_3V.V3_Run();

	}
}
