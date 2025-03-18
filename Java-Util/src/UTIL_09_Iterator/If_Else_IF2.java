package UTIL_09_Iterator;

import util.Linhas;
import util.VoutarRun;

import java.io.*;
import java.util.*;

public class If_Else_IF2 {
	public static void If_Else_IF_Run() {

		Linhas linhas = new Linhas();
		String collec = "\n\t Iterator<E>: Interface para iterar sobre elementos de uma coleção. \n";
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

//Enums para tipos de senhas
enum TipoSenha {
	NUMERICA, ALFANUMERICA, SIMBOLOS, LONGA, CURTA
}

enum ForcaSenha {
	FRACA, MEDIA, FORTE, MUITO_FORTE, EXTREMA
}

enum CategoriaSenha {
	PESSOAL, CORPORATIVA, BANCARIA, REDES_SOCIAIS, OUTROS
}

enum StatusSenha {
	ATIVA, EXPIRADA, TEMPORARIA, BLOQUEADA, INVALIDA
}

enum ComplexidadeSenha {
	BAIXA, MODERADA, ALTA, ULTRA_ALTA, MILITAR
}

//Classe abstrata base
abstract class Senha {
	protected String nome;
	protected TipoSenha tipo;
	protected StatusSenha status;

	public Senha(String nome, TipoSenha tipo, StatusSenha status) {
		this.nome = nome;
		this.tipo = tipo;
		this.status = status;
	}

	public abstract void exibirDetalhes();
}

//Classes abstratas específicas
abstract class SenhaForte extends Senha {
	public SenhaForte(String nome) {
		super(nome, TipoSenha.LONGA, StatusSenha.ATIVA);
	}
}

abstract class SenhaMedia extends Senha {
	public SenhaMedia(String nome) {
		super(nome, TipoSenha.ALFANUMERICA, StatusSenha.ATIVA);
	}
}

abstract class SenhaFraca extends Senha {
	public SenhaFraca(String nome) {
		super(nome, TipoSenha.CURTA, StatusSenha.INVALIDA);
	}
}

abstract class SenhaEspecial extends Senha {
	public SenhaEspecial(String nome) {
		super(nome, TipoSenha.SIMBOLOS, StatusSenha.TEMPORARIA);
	}
}

abstract class SenhaCorporativa extends Senha {
	public SenhaCorporativa(String nome) {
		super(nome, TipoSenha.ALFANUMERICA, StatusSenha.BLOQUEADA);
	}
}

//Classe record para armazenar dados
record RegistroSenha(String nome, TipoSenha tipo, StatusSenha status) {
}

//Implementação de CRUD com ordenação, busca e sincronização
class GerenciadorSenhas {
	private final List<RegistroSenha> senhas = Collections.synchronizedList(new ArrayList<>());

	public synchronized void adicionarSenha(RegistroSenha senha) {
		senhas.add(senha);
	}

	public synchronized void removerSenha(String nome) {
		senhas.removeIf(s -> s.nome().equalsIgnoreCase(nome));
	}

	public synchronized void ordenarSenhas() {
		senhas.sort(Comparator.comparing(RegistroSenha::nome));
	}

	public synchronized RegistroSenha buscarSenha(String nome) {
		for (RegistroSenha s : senhas) {
			if (s.nome().equalsIgnoreCase(nome)) {
				return s;
			}
		}
		return null;
	}

	public synchronized void exibirSenhas() {
		Iterator<RegistroSenha> it = senhas.iterator();
		while (it.hasNext()) {
			RegistroSenha s = it.next();
			System.out.println(s);
		}
	}
}

//Execução principal
class Import_1IF {
	public static void run_Import_1IF() {
		System.err.println("\t\t1. \n");
		GerenciadorSenhas gerenciador = new GerenciadorSenhas();
		gerenciador.adicionarSenha(new RegistroSenha("Admin", TipoSenha.ALFANUMERICA, StatusSenha.ATIVA));
		gerenciador.adicionarSenha(new RegistroSenha("Guest", TipoSenha.NUMERICA, StatusSenha.EXPIRADA));
		gerenciador.adicionarSenha(new RegistroSenha("Root", TipoSenha.LONGA, StatusSenha.BLOQUEADA));

		gerenciador.ordenarSenhas();
		gerenciador.exibirSenhas();

		// Uso de if, else if, else, switch case e break
		Scanner scanner = new Scanner(System.in);
		System.out.println("Digite o nome da senha para buscar:");
		String nomeBusca = scanner.nextLine();
		RegistroSenha senhaEncontrada = gerenciador.buscarSenha(nomeBusca);

		if (senhaEncontrada != null) {
			System.out.println("Senha encontrada: " + senhaEncontrada);
			switch (senhaEncontrada.tipo()) {
			case NUMERICA:
				System.out.println("Esta senha é numérica.");
				break;
			case ALFANUMERICA:
				System.out.println("Esta senha contém letras e números.");
				break;
			case SIMBOLOS:
				System.out.println("Esta senha contém símbolos especiais.");
				break;
			case LONGA:
				System.out.println("Esta é uma senha longa e segura.");
				break;
			case CURTA:
				System.out.println("Esta senha é curta e pode ser fraca.");
				break;
			default:
				System.out.println("Tipo de senha desconhecido.");
			}
		} else {
			System.out.println("Senha não encontrada.");
		}
		VoutarRun voutarRun = new VoutarRun();
		voutarRun.run_CaracteresMenu_Red();
	}
}

//Corpo da Classe _______________________________
//Enums
enum PerfilNivel {
	BASICO, INTERMEDIARIO, AVANCADO, ADMIN, SUPERUSER;
}

enum StatusPerfil {
	ATIVO, INATIVO, SUSPENSO, EXCLUIDO, AGUARDANDO;
}

enum TipoAcesso {
	LEITURA, ESCRITA, TOTAL, RESTRITO, NEGADO;
}

enum RegiaoPerfil {
	NORTE, SUL, LESTE, OESTE, CENTRO;
}

enum CategoriaPerfil {
	NORMAL, VIP, PREMIUM, ENTERPRISE, FREE;
}

//Classe Record
record Perfil(String nome, PerfilNivel nivel, StatusPerfil status, TipoAcesso acesso, RegiaoPerfil regiao,
		CategoriaPerfil categoria) {
}

//Classe Abstrata
abstract class GerenciadorPerfil {
	protected List<Perfil> perfis = Collections.synchronizedList(new ArrayList<>());

	abstract void adicionarPerfil(Perfil perfil);

	abstract void listarPerfis();

	abstract Perfil buscarPerfil(String nome);

	abstract void ordenarPerfis();

	abstract void salvarPerfis(String arquivo) throws IOException;

	abstract void carregarPerfis(String arquivo) throws IOException;
}

class PerfilManager extends GerenciadorPerfil {
	@Override
	synchronized void adicionarPerfil(Perfil perfil) {
		perfis.add(perfil);
	}

	@Override
	synchronized void listarPerfis() {
		Iterator<Perfil> it = perfis.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

	@Override
	synchronized Perfil buscarPerfil(String nome) {
		for (Perfil p : perfis) {
			if (p.nome().equalsIgnoreCase(nome)) {
				return p;
			}
		}
		return null;
	}

	@Override
	synchronized void ordenarPerfis() {
		perfis.sort(Comparator.comparing(Perfil::nome));
	}

	@Override
	synchronized void salvarPerfis(String arquivo) throws IOException {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(arquivo))) {
			out.writeObject(new ArrayList<>(perfis));
		}
	}

	@Override
	synchronized void carregarPerfis(String arquivo) throws IOException {
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivo))) {
			perfis = Collections.synchronizedList((List<Perfil>) in.readObject());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}

class Import_2IF {
	public static void run_Import_2IF() {
		System.err.println("\t\t2.\n");
		PerfilManager manager = new PerfilManager();
		manager.adicionarPerfil(new Perfil("Alice", PerfilNivel.BASICO, StatusPerfil.ATIVO, TipoAcesso.LEITURA,
				RegiaoPerfil.NORTE, CategoriaPerfil.FREE));
		manager.adicionarPerfil(new Perfil("Bob", PerfilNivel.ADMIN, StatusPerfil.INATIVO, TipoAcesso.TOTAL,
				RegiaoPerfil.SUL, CategoriaPerfil.VIP));

		System.out.println("Lista de Perfis Antes da Ordenação:");
		manager.listarPerfis();

		manager.ordenarPerfis();
		System.out.println("Lista de Perfis Após Ordenação:");
		manager.listarPerfis();

		System.out.println("Buscando perfil Bob: " + manager.buscarPerfil("Bob"));
	}
}

//Corpo da Classe _______________________________

//Enums
enum BitcoinStatus {
	ATIVO, INATIVO, PENDENTE, CANCELADO, CONFIRMADO, FALHOU, PROCESSANDO;
}

enum TransacaoTipo {
	COMPRA, VENDA, TRANSFERENCIA, MINERACAO, STAKING, RECOMPENSA, TAXA;
}

enum MoedaTipo {
	BTC, ETH, USDT, ADA, XRP, LTC, BCH;
}

enum CarteiraTipo {
	HOT, COLD, HARDWARE, PAPER, MOBILE, DESKTOP, WEB;
}

enum UsuarioTipo {
	ADMIN, TRADER, INVESTIDOR, MINERADOR, DESENVOLVEDOR, ANALISTA, ENTUSIASTA;
}

enum ExchangeTipo {
	BINANCE, COINBASE, KRAKEN, BITTREX, HUOBI, OKX, BITFINEX;
}

enum SegurancaNivel {
	BAIXO, MEDIO, ALTO, MUITO_ALTO, EXTREMO, MILITAR, ULTRA;
}

//Classe Abstrata
abstract class GerenciadorBitcoin {
	protected List<Bitcoin> bitcoins = Collections.synchronizedList(new ArrayList<>());

	abstract void adicionarBitcoin(Bitcoin bitcoin);

	abstract void listarBitcoins();

	abstract Bitcoin buscarBitcoin(String id);

	abstract void ordenarBitcoins();
}

abstract class GerenciadorCarteira {
	protected List<CarteiraTipo> carteiras = Collections.synchronizedList(new ArrayList<>());

	abstract void adicionarCarteira(CarteiraTipo carteira);

	abstract void listarCarteiras();

	abstract CarteiraTipo buscarCarteira(String id);

	abstract void ordenarCarteiras();
}

abstract class GerenciadorUsuario {
	protected List<UsuarioTipo> usuarios = Collections.synchronizedList(new ArrayList<>());

	abstract void adicionarUsuario(UsuarioTipo usuario);

	abstract void listarUsuarios();

	abstract UsuarioTipo buscarUsuario(String id);

	abstract void ordenarUsuarios();
}

abstract class GerenciadorTransacao {
	protected List<TransacaoTipo> transacoes = Collections.synchronizedList(new ArrayList<>());

	abstract void adicionarTransacao(TransacaoTipo transacao);

	abstract void listarTransacoes();

	abstract TransacaoTipo buscarTransacao(String id);

	abstract void ordenarTransacoes();
}

abstract class GerenciadorExchange {
	protected List<ExchangeTipo> exchanges = Collections.synchronizedList(new ArrayList<>());

	abstract void adicionarExchange(ExchangeTipo exchange);

	abstract void listarExchanges();

	abstract ExchangeTipo buscarExchange(String id);

	abstract void ordenarExchanges();
}

abstract class GerenciadorSeguranca {
	protected List<SegurancaNivel> segurancas = Collections.synchronizedList(new ArrayList<>());

	abstract void adicionarSeguranca(SegurancaNivel seguranca);

	abstract void listarSegurancas();

	abstract SegurancaNivel buscarSeguranca(String id);

	abstract void ordenarSegurancas();
}

abstract class GerenciadorHistorico<Historico> {
	protected List<Historico> historicos = Collections.synchronizedList(new ArrayList<>());

	abstract void adicionarHistorico(Historico historico);

	abstract void listarHistoricos();

	abstract Historico buscarHistorico(String id);

	abstract void ordenarHistoricos();
}

//Classe Bitcoin
class Bitcoin {
	private final String id;
	private final MoedaTipo tipo;
	private final double valor;
	private final BitcoinStatus status;

	public Bitcoin(String id, MoedaTipo tipo, double valor, BitcoinStatus status) {
		this.id = id;
		this.tipo = tipo;
		this.valor = valor;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public MoedaTipo getTipo() {
		return tipo;
	}

	public double getValor() {
		return valor;
	}

	public BitcoinStatus getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "Bitcoin{" + "id='" + id + '\'' + ", tipo=" + tipo + ", valor=" + valor + ", status=" + status + '}';
	}
}

//Implementação concreta de GerenciadorBitcoin
class BitcoinManager extends GerenciadorBitcoin {
	@Override
	synchronized void adicionarBitcoin(Bitcoin bitcoin) {
		bitcoins.add(bitcoin);
	}

	@Override
	synchronized void listarBitcoins() {
		Iterator<Bitcoin> it = bitcoins.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

	@Override
	synchronized Bitcoin buscarBitcoin(String id) {
		for (Bitcoin b : bitcoins) {
			if (b.getId().equalsIgnoreCase(id)) {
				return b;
			}
		}
		return null;
	}

	@Override
	synchronized void ordenarBitcoins() {
		bitcoins.sort(Comparator.comparing(Bitcoin::getId));
	}
}

class Import_3IF {
	
    public static void main_R() {
        BitcoinManager manager = new BitcoinManager();
        manager.adicionarBitcoin(new Bitcoin("BTC-001", MoedaTipo.BTC, 45000.50, BitcoinStatus.ATIVO));
        manager.adicionarBitcoin(new Bitcoin("BTC-002", MoedaTipo.BTC, 47000.75, BitcoinStatus.CONFIRMADO));
        manager.adicionarBitcoin(new Bitcoin("BTC-005", MoedaTipo.BTC, 0.00, BitcoinStatus.CANCELADO));
        manager.adicionarBitcoin(new Bitcoin("BTC-003", MoedaTipo.BTC, 97000.75, BitcoinStatus.PENDENTE));
        manager.adicionarBitcoin(new Bitcoin("BTC-008", MoedaTipo.BTC, 75000.50, BitcoinStatus.PROCESSANDO));
        manager.adicionarBitcoin(new Bitcoin("BTC-004", MoedaTipo.BTC, 37000.75, BitcoinStatus.PENDENTE));
        
        System.out.println("Lista de Bitcoins Antes da Ordenação:");
        manager.listarBitcoins();
        
        manager.ordenarBitcoins();
        System.out.println("Lista de Bitcoins Após Ordenação:\n");
        manager.listarBitcoins();
        
        System.out.println("Buscando Bitcoin BTC-002: " + manager.buscarBitcoin("BTC-002"));
    }
	public static void run_Import_3IF() {
		System.err.println("\t\t3. \n");
		
		Import_3IF.main_R();
	}
}
