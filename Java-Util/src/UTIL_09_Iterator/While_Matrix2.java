package UTIL_09_Iterator;

import util.Linhas;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

public class While_Matrix2 {
	public static void While_Run() {
		Linhas linhas = new Linhas();
		String collec = "\n\t Iterator<E>: Interface para iterar sobre elementos de uma coleção. \n";
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
enum TipoVPN7 {
	OPENVPN, PPTP, L2TP, SSTP, IKEV2, IPSEC, SSLVPN, MPLS, GRE, DMVPN
}

enum TipoEndereco {
	IPV4, IPV6, DNS
}

enum StatusVPN {
	ATIVA, INATIVA, CONECTANDO, DESCONEXAO
}

enum ProtocoloVPN {
	UDP, TCP, SCTP
}

enum TipoRede {
	PRIVATE, PUBLICA, LOCAL
}

enum TipoAutenticacao {
	USERNAME_PASSWORD, CERTIFICADO, TOKENS, AUTENTICACAO_MULTIFATOR
}

enum TipoEnderecoDNS {
	PRIMARY, SECONDARY, TERTIARY
}

enum TipoConexao {
	WIFI, ETHERNET, MOBILE, FIBRA
}

enum PrioridadeVPN {
	ALTA, MEDIA, BAIXA
}

enum TipoServidor {
	WINDOWS, LINUX, MACOS, BSD
}

abstract class EnderecoRede {
	protected String endereco;
	protected TipoEndereco tipo;

	public abstract void conectar();

	public String getEndereco() {
		return endereco;
	}

	public TipoEndereco getTipo() {
		return tipo;
	}
}

abstract class VPN7 {
	protected TipoVPN7 tipo;
	protected StatusVPN status;

	public abstract void conectarVPN();

	public TipoVPN7 getTipo() {
		return tipo;
	}

	public StatusVPN getStatus() {
		return status;
	}
}

class EnderecoIPv4 extends EnderecoRede {
	public EnderecoIPv4(String endereco) {
		this.endereco = endereco;
		this.tipo = TipoEndereco.IPV4;
	}

	@Override
	public void conectar() {
		System.out.println("Conectando via IPv4: " + endereco);
	}
}

class EnderecoIPv6 extends EnderecoRede {
	public EnderecoIPv6(String endereco) {
		this.endereco = endereco;
		this.tipo = TipoEndereco.IPV6;
	}

	@Override
	public void conectar() {
		System.out.println("Conectando via IPv6: " + endereco);
	}
}

class EnderecoDNS extends EnderecoRede {
	public EnderecoDNS(String endereco) {
		this.endereco = endereco;
		this.tipo = TipoEndereco.DNS;
	}

	@Override
	public void conectar() {
		System.out.println("Conectando via DNS: " + endereco);
	}
}

class OpenVPN extends VPN7 {
	public OpenVPN() {
		this.tipo = TipoVPN7.OPENVPN;
		this.status = StatusVPN.INATIVA;
	}

	@Override
	public void conectarVPN() {
		System.out.println("Conectando com OpenVPN...");
	}
}

class PPTPVPN extends VPN7 {
	public PPTPVPN() {
		this.tipo = TipoVPN7.PPTP;
		this.status = StatusVPN.INATIVA;
	}

	@Override
	public void conectarVPN() {
		System.out.println("Conectando com PPTP VPN...");
	}
}

class L2TPVPN extends VPN7 {
	public L2TPVPN() {
		this.tipo = TipoVPN7.L2TP;
		this.status = StatusVPN.INATIVA;
	}

	@Override
	public void conectarVPN() {
		System.out.println("Conectando com L2TP VPN...");
	}
}

class MPLSVPN extends VPN7 {
	public MPLSVPN() {
		this.tipo = TipoVPN7.MPLS;
		this.status = StatusVPN.INATIVA;
	}

	@Override
	public void conectarVPN() {
		System.out.println("Conectando com MPLS VPN...");
	}
}

class SistemaConexao {
	private List<VPN7> vpns;
	private List<EnderecoRede> enderecos;

	public SistemaConexao() {
		this.vpns = new ArrayList<>();
		this.enderecos = new ArrayList<>();
	}

	public void adicionarVPN(VPN7 vpn) {
		vpns.add(vpn);
	}

	public void adicionarEndereco(EnderecoRede endereco) {
		enderecos.add(endereco);
	}

	public void ordenarVPNs() {
		vpns.sort(Comparator.comparing(VPN7::getTipo));
	}

	public void buscarVPN(TipoVPN7 tipo) {
		for (VPN7 vpn : vpns) {
			if (vpn.getTipo() == tipo) {
				System.out.println("VPN encontrada: " + vpn.getTipo());
			}
		}
	}

	public void sincronizarConexoes() {
		synchronized (this) {
			for (EnderecoRede endereco : enderecos) {
				endereco.conectar();
			}
			for (VPN7 vpn : vpns) {
				vpn.conectarVPN();
			}
		}
	}
}

class Import_1WM {
	public static void mainWM() {
		SistemaConexao sistema = new SistemaConexao();

		// Criando instâncias de VPN e Endereços
		VPN7 openVPN = new OpenVPN();
		VPN7 pptpVPN = new PPTPVPN();
		VPN7 l2tpVPN = new L2TPVPN();
		VPN7 mplsVPN = new MPLSVPN();

		EnderecoRede enderecoIPv4 = new EnderecoIPv4("192.168.0.1");
		EnderecoRede enderecoIPv6 = new EnderecoIPv6("2001:0db8:85a3:0000:0000:8a2e:0370:7334");
		EnderecoRede enderecoDNS = new EnderecoDNS("8.8.8.8");

		sistema.adicionarVPN(openVPN);
		sistema.adicionarVPN(pptpVPN);
		sistema.adicionarVPN(l2tpVPN);
		sistema.adicionarVPN(mplsVPN);

		sistema.adicionarEndereco(enderecoIPv4);
		sistema.adicionarEndereco(enderecoIPv6);
		sistema.adicionarEndereco(enderecoDNS);

		// Ordenando VPNs por tipo
		sistema.ordenarVPNs();

		// Buscando por uma VPN específica
		sistema.buscarVPN(TipoVPN7.L2TP);

		// Sincronizando conexões
		sistema.sincronizarConexoes();
	}

	public static void run_Import_1WM() {
		System.err.println("\t\t1.VPN\n");
		Import_1WM.mainWM();
	}
}

//Corpo da Classe _______________________________
class Import_2WM {

	// Enums para tipos de servidores e protocolos
	public enum TipoServidor {
		WEB_SERVER, DATABASE_SERVER, FILE_SERVER, DNS_SERVER, MAIL_SERVER, FTP_SERVER, APP_SERVER, PROXY_SERVER,
		GAME_SERVER, MEDIA_SERVER
	}

	public enum Protocolo {
		IPV4, IPV6, DNS, FTP, HTTP, HTTPS, SMTP, POP3, IMAP, TCP
	}

	// Classe abstrata Servidor
	public abstract class Servidor {
		protected String nome;
		protected String ip;
		protected TipoServidor tipo;
		protected Protocolo protocolo;

		public Servidor(String nome, String ip, TipoServidor tipo, Protocolo protocolo) {
			this.nome = nome;
			this.ip = ip;
			this.tipo = tipo;
			this.protocolo = protocolo;
		}

		public abstract void exibirInfo();

		public String getNome() {
			return nome;
		}

		public String getIp() {
			return ip;
		}

		public TipoServidor getTipo() {
			return tipo;
		}

		public Protocolo getProtocolo() {
			return protocolo;
		}
	}

	// Implementação do servidor Web
	public class WebServer extends Servidor {
		public WebServer(String nome, String ip) {
			super(nome, ip, TipoServidor.WEB_SERVER, Protocolo.HTTP);
		}

		@Override
		public void exibirInfo() {
			System.out.println("Servidor Web: " + nome + " | IP: " + ip + " | Protocolo: " + protocolo);
		}
	}

	// Implementação do servidor DNS
	public class DNSServer extends Servidor {
		public DNSServer(String nome, String ip) {
			super(nome, ip, TipoServidor.DNS_SERVER, Protocolo.DNS);
		}

		@Override
		public void exibirInfo() {
			System.out.println("Servidor DNS: " + nome + " | IP: " + ip + " | Protocolo: " + protocolo);
		}
	}

	// Classe para gerenciar servidores
	public class ServidorController {
		private List<Servidor> servidores = Collections.synchronizedList(new ArrayList<Servidor>());

		// Método de adição de servidor
		public void adicionarServidor(Servidor servidor) {
			servidores.add(servidor);
		}

		// Método de busca por nome
		public Servidor buscarPorNome(String nome) {
			for (Servidor servidor : servidores) {
				if (servidor.getNome().equals(nome)) {
					return servidor;
				}
			}
			return null;
		}

		// Método para ordenar servidores por nome
		public void ordenarServidoresPorNome() {
			servidores.sort(Comparator.comparing(Servidor::getNome));
		}

		// Exibição de todos os servidores
		public void exibirServidores() {
			for (Servidor servidor : servidores) {
				servidor.exibirInfo();
			}
		}
	}

	// Função principal para rodar exemplos
	public static void main9() {
		Import_2WM manager = new Import_2WM();
		ServidorController controller = manager.new ServidorController();

		// Adicionando servidores
		controller.adicionarServidor(manager.new WebServer("ServidorWeb1", "192.168.1.1"));
		controller.adicionarServidor(manager.new DNSServer("ServidorDNS1", "192.168.2.1"));

		// Ordenando servidores por nome
		controller.ordenarServidoresPorNome();

		// Exibindo servidores
		controller.exibirServidores();

		// Buscando um servidor pelo nome
		Servidor encontrado = controller.buscarPorNome("ServidorDNS1");
		if (encontrado != null) {
			System.out.println("Servidor encontrado: " + encontrado.getNome());
		} else {
			System.out.println("Servidor não encontrado");
		}
	}

	public static void run_Import_2WM() {
		System.err.println("\t\t2.\n");
		Import_2WM.main9();
	}
}

//Corpo da Classe _______________________________
abstract class OS {
	String nome;

	public OS(String nome) {
		this.nome = nome;
	}

	public abstract void detalhes();
}

class Windows extends OS {
	public Windows() {
		super("Windows");
	}

	@Override
	public void detalhes() {
		System.out.println("Sistema Operacional: " + nome);
		System.out.println("Versão: Windows 10");
	}
}

class Linux extends OS {
	public Linux() {
		super("Linux");
	}

	@Override
	public void detalhes() {
		System.out.println("Sistema Operacional: " + nome);
		System.out.println("Versão: Ubuntu 20.04");
	}
}

class MacOS extends OS {
	public MacOS() {
		super("MacOS");
	}

	@Override
	public void detalhes() {
		System.out.println("Sistema Operacional: " + nome);
		System.out.println("Versão: macOS Big Sur");
	}
}

enum TipoIP {
	IPV4_, IPV6_, DNS_
}

enum Categoria {
	DESKTOP, SERVIDOR, EMBARCADO
}

enum Estado {
	ATIVO, INATIVO, EM_MANUTENCAO
}

enum Licenca {
	FREE, PAID, TRIAL
}

enum Arquitetura {
	X86, X64, ARM
}

enum Rede3 {
	WIFI, CABLE, FIBRA
}

enum Suporte {
	HARDWARE, SOFTWARE, AMBOS
}

enum Interface {
	GRAFICA, COMANDOS, NENHUMA
}

enum Versao {
	ALFA, BETA, ESTAVEL
}

class SistemaOperacional implements Iterator<OS> {
	private List<OS> osList;
	private int position = 0;

	public SistemaOperacional() {
		osList = new ArrayList<>();
		osList.add(new Windows());
		osList.add(new Linux());
		osList.add(new MacOS());
	}

	@Override
	public boolean hasNext() {
		return position < osList.size();
	}

	@Override
	public OS next() {
		if (hasNext()) {
			return osList.get(position++);
		}
		return null;
	}

	public void listarOS() {
		while (hasNext()) {
			OS os = next();
			os.detalhes();
		}
	}
}

class IPValidator2 {
	public static boolean validarIP(String ip, TipoIP  tipo) {
		try {
			switch (tipo) {
			case IPV4_:
				return InetAddress.getByName(ip) instanceof Inet4Address;
			case IPV6_:
				return InetAddress.getByName(ip) instanceof Inet6Address;
			case DNS_:
				return InetAddress.getByName(ip) instanceof InetAddress;
			default:
				return false;
			}
		} catch (UnknownHostException e) {
			return false;
		}
	}
}

class Import_3WM {

	public static void run_Import_3WM() {
		System.err.println("\t\t3. \n");

		// Exemplo de uso de Enums, Iterator e Classes Abstratas

		SistemaOperacional sistemaOperacional = new SistemaOperacional();
		sistemaOperacional.listarOS();

		// Exemplo de uso de validação de IP
		String ipTest = "192.168.0.1";
		if (IPValidator2.validarIP(ipTest, TipoIP.IPV4_)) {
			System.out.println(ipTest + " é um IP válido IPV4");
		} else {
			System.out.println(ipTest + " não é um IP válido IPV4");
		}

		// Exemplo de uso de estrutura de controle (switch case, if-else)
		TipoIP tipoIP = TipoIP.IPV6_;
		switch (tipoIP) {
		case IPV4_:
			System.out.println("Verificando IPV4");
			break;
		case IPV6_:
			System.out.println("Verificando IPV6");
			break;
		case DNS_:
			System.out.println("Verificando DNS");
			break;
		default:
			System.out.println("Tipo de IP desconhecido");
		}

		// Exemplo de uso de for e do-while
		List<Integer> numeros = Arrays.asList(5, 2, 8, 1, 6);
		// Ordenando com for
		System.out.println("Ordenação usando for:");
		for (int i = 0; i < numeros.size(); i++) {
			System.out.println(numeros.get(i));
		}

		// Ordenação usando do-while
		System.out.println("Ordenação usando do-while:");
		int i = 0;
		do {
			System.out.println(numeros.get(i));
			i++;
		} while (i < numeros.size());

		// Exemplo de busca e sincronização
		synchronized (numeros) {
			if (numeros.contains(8)) {
				System.out.println("O número 8 foi encontrado.");
			}
		}
	}
}
