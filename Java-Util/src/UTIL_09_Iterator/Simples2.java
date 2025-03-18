package UTIL_09_Iterator;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

import util.Linhas;

public class Simples2 {
	public static void Simples_Run() {

		Linhas linhas = new Linhas();
		String collec = "\n\t Iterator<E>: Interface para iterar sobre elementos de uma coleção.\n";
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

//Enums
enum TipoTransacao {
	COMPRA, VENDA, MINERACAO, TRANSFERENCIA, DOACAO, PAGAMENTO, TROCA;
}

enum MoedaCripto {
	BITCOIN, ETHEREUM, LITECOIN, DOGECOIN, RIPPLE, CARDANO, POLKADOT;
}

enum StatusTransacao {
	PENDENTE, CONCLUIDA, CANCELADA, FALHOU, PROCESSANDO, REEMBOLSADA, AGUARDANDO_CONFIRMACAO;
}

enum TipoCarteira {
	HARDWARE, SOFTWARE, PAPEL, MOBILE, WEB, DESKTOP, BANCO;
}

enum TipoSeguranca {
	SENHA, S2FA, BIOMETRIA, CHAVE_PRIVADA, FRASE_SECRETA, AUTENTICACAO_HARDWARE, REDE_MULTISIG;
}

enum RegiaoMercado {
	AMERICA, EUROPA, ASIA, AFRICA, OCEANIA, GLOBAL, LOCAL;
}

enum CategoriaInvestidor {
	INICIANTE, INTERMEDIARIO, AVANCADO, PROFISSIONAL, INSTITUCIONAL, TRADER, HOLD;
}

//Classe Abstrata
abstract class GerenciadorBitcoin5 {
	protected List<TransacaoBitcoin> transacoes = Collections.synchronizedList(new ArrayList<>());

	abstract void adicionarTransacao(TransacaoBitcoin transacao);

	abstract void listarTransacoes();

	abstract TransacaoBitcoin buscarTransacao(String id);

	abstract void ordenarTransacoes();

	abstract void calcularSomaMoedas();
}

//Classe de Transação
abstract class TransacaoBitcoin {
	protected String id;
	protected MoedaCripto moeda;
	protected double quantidade;
	protected TipoTransacao tipo;
	protected StatusTransacao status;

	public TransacaoBitcoin(String id, MoedaCripto moeda, double quantidade, TipoTransacao tipo,
			StatusTransacao status) {
		this.id = id;
		this.moeda = moeda;
		this.quantidade = quantidade;
		this.tipo = tipo;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public MoedaCripto getMoeda() {
		return moeda;
	}

	public double getQuantidade() {
		return quantidade;
	}

	public TipoTransacao getTipo() {
		return tipo;
	}

	public StatusTransacao getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "[ID: " + id + ", Moeda: " + moeda + ", Quantidade: " + quantidade + ", Tipo: " + tipo + ", Status: "
				+ status + "]";
	}
}

//Implementação do Gerenciador
class BitcoinManager5 extends GerenciadorBitcoin5 {
	@Override
	synchronized void adicionarTransacao(TransacaoBitcoin transacao) {
		transacoes.add(transacao);
	}

	@Override
	synchronized void listarTransacoes() {
		Iterator<TransacaoBitcoin> it = transacoes.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

	@Override
	synchronized TransacaoBitcoin buscarTransacao(String id) {
		for (TransacaoBitcoin t : transacoes) {
			if (t.getId().equalsIgnoreCase(id)) {
				return t;
			}
		}
		return null;
	}

	@Override
	synchronized void ordenarTransacoes() {
		transacoes.sort(Comparator.comparing(TransacaoBitcoin::getId));
	}

	@Override
	synchronized void calcularSomaMoedas() {
		Map<MoedaCripto, Double> somaMoedas = new HashMap<>();
		for (TransacaoBitcoin t : transacoes) {
			somaMoedas.put(t.getMoeda(), somaMoedas.getOrDefault(t.getMoeda(), 0.0) + t.getQuantidade());
		}
		System.out.println("Soma total por moeda: " + somaMoedas);
	}
}

//Classe Principal

class Import_1S {
	public static void run_Import_1S() {
		System.err.println("\t\t1. \n");
		BitcoinManager5 manager = new BitcoinManager5();
		manager.adicionarTransacao(new TransacaoBitcoin("TXN001", MoedaCripto.BITCOIN, 0.5, TipoTransacao.COMPRA,
				StatusTransacao.CONCLUIDA) {
		});
		manager.adicionarTransacao(new TransacaoBitcoin("TXN002", MoedaCripto.ETHEREUM, 2.0, TipoTransacao.VENDA,
				StatusTransacao.PENDENTE) {
		});

		System.out.println("Lista de Transações:");
		manager.listarTransacoes();

		manager.ordenarTransacoes();
		System.out.println("Transações Ordenadas:");
		manager.listarTransacoes();

		manager.calcularSomaMoedas();
	}
}

//Corpo da Classe _______________________________

//Enums representando diferentes aspectos do Bitcoin
enum TipoTransacao2 {
	COMPRA, VENDA, TRANSFERENCIA
}

enum StatusTransacao2 {
	PENDENTE, CONCLUIDA, CANCELADA
}

enum Moeda {
	BTC, ETH, LTC, USDT, XRP, ADA, DOT
}

enum RedeBlockchain {
	BITCOIN, ETHEREUM, LITECOIN
}

enum TipoCarteira2 {
	HOT, COLD, HARDWARE
}

enum NivelUsuario {
	BASICO, AVANCADO, ADMIN
}

enum TipoOrdem {
	MERCADO, LIMITADA, STOP_LOSS
}

//Classe abstrata para representar um elemento do sistema
abstract class Entidade {
	protected UUID id;
	protected Date dataCriacao;

	public Entidade() {
		this.id = UUID.randomUUID();
		this.dataCriacao = new Date();
	}

	public UUID getId() {
		return id;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}
}

//Classe abstrata para transações
abstract class Transacao extends Entidade {
	protected double valor;
	protected Moeda moeda;
	protected StatusTransacao2 status;

	public Transacao(double valor, Moeda moeda) {
		super();
		this.valor = valor;
		this.moeda = moeda;
		this.status = StatusTransacao2.PENDENTE;
	}

	public abstract void processar();
}

//Classe abstrata para carteiras
abstract class Carteira extends Entidade {
	protected TipoCarteira2 tipo;
	protected Map<Moeda, Double> saldo;

	public Carteira(TipoCarteira2 tipo) {
		super();
		this.tipo = tipo;
		this.saldo = new HashMap<>();
		for (Moeda m : Moeda.values()) {
			saldo.put(m, 0.0);
		}
	}

	public synchronized void adicionarSaldo(Moeda moeda, double quantidade) {
		saldo.put(moeda, saldo.get(moeda) + quantidade);
	}

	public synchronized boolean retirarSaldo(Moeda moeda, double quantidade) {
		if (saldo.get(moeda) >= quantidade) {
			saldo.put(moeda, saldo.get(moeda) - quantidade);
			return true;
		}
		return false;
	}

	public abstract void exibirSaldo();
}

//Classe abstrata para usuários
abstract class Usuario extends Entidade {
	protected String nome;
	protected NivelUsuario nivel;

	public Usuario(String nome, NivelUsuario nivel) {
		super();
		this.nome = nome;
		this.nivel = nivel;
	}
}

//Implementação de CRUD para transações de Bitcoin
class GerenciadorTransacoes {
	private final List<Transacao> transacoes = new ArrayList<>();
	private final ReentrantLock lock = new ReentrantLock();

	public void adicionarTransacao(Transacao transacao) {
		lock.lock();
		try {
			transacoes.add(transacao);
		} finally {
			lock.unlock();
		}
	}

	public void listarTransacoes() {
		lock.lock();
		try {
			Iterator<Transacao> it = transacoes.iterator();
			while (it.hasNext()) {
				Transacao t = it.next();
				System.out.println("ID: " + t.getId() + ", Valor: " + t.valor + " " + t.moeda + " Status: " + t.status);
			}
		} finally {
			lock.unlock();
		}
	}

	public synchronized boolean removerTransacao(UUID id) {
		Iterator<Transacao> it = transacoes.iterator();
		while (it.hasNext()) {
			Transacao t = it.next();
			if (t.getId().equals(id)) {
				it.remove();
				return true;
			}
		}
		return false;
	}
}

//Exemplo de transação concreta
class TransacaoBitcoin_ extends Transacao {
	public TransacaoBitcoin_(double valor) {
		super(valor, Moeda.BTC);
	}

	@Override
	public void processar() {
		this.status = StatusTransacao2.CONCLUIDA;
		System.out.println("Transação de " + valor + " BTC processada!");
	}
}

//Teste do sistema
class Import_2S {
	public static void run_Import_2S() {
		System.err.println("\t\t2. \n");

		GerenciadorTransacoes gerenciador = new GerenciadorTransacoes();

		Transacao t1 = new TransacaoBitcoin_(0.05);
		Transacao t2 = new TransacaoBitcoin_(0.1);

		gerenciador.adicionarTransacao(t1);
		gerenciador.adicionarTransacao(t2);

		gerenciador.listarTransacoes();

		t1.processar();

		System.out.println("Removendo transação: " + gerenciador.removerTransacao(t1.getId()));
		gerenciador.listarTransacoes();
	}
}

//Corpo da Classe _______________________________
//Enums representando diferentes aspectos da TV
enum MarcaTV {
	SAMSUNG, LG, SONY, PHILIPS, PANASONIC, TCL, PHILCO
}

enum TamanhoTV {
	P32, P40, P50, P55, P65, P75, P85
}

enum ResolucaoTV {
	HD, FULL_HD, UHD_4K, UHD_8K
}

enum TipoTela {
	LED, OLED, QLED, PLASMA, LCD, MICROLED, MINI_LED
}

enum ModoExibicao {
	CINEMA, JOGO, PADRAO, VIVIDO, ECO, ESPORTE, HDR
}

enum TipoSom {
	MONO, ESTEREO, SURROUND, DOLBY_ATMOS, DTS, HI_RES
}

enum TipoConexao6 {
	HDMI, USB, BLUETOOTH, WIFI, RCA, VGA, DISPLAYPORT
}

//Classe abstrata para representar um elemento do sistema
abstract class Entidade2 {
	protected UUID id;
	protected Date dataCriacao;

	public Entidade2() {
		this.id = UUID.randomUUID();
		this.dataCriacao = new Date();
	}

	public UUID getId() {
		return id;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}
}

//Classe abstrata para TV
abstract class TV extends Entidade {
	protected MarcaTV marca;
	protected TamanhoTV tamanho;
	protected ResolucaoTV resolucao;
	protected TipoTela tipoTela;

	public TV(MarcaTV marca, TamanhoTV tamanho, ResolucaoTV resolucao, TipoTela tipoTela) {
		super();
		this.marca = marca;
		this.tamanho = tamanho;
		this.resolucao = resolucao;
		this.tipoTela = tipoTela;
	}

	public abstract void exibirInformacoes();
}

//Classe abstrata para Configurações da TV
abstract class ConfiguracaoTV extends Entidade {
	protected ModoExibicao modoExibicao;
	protected TipoSom tipoSom;

	public ConfiguracaoTV(ModoExibicao modoExibicao, TipoSom tipoSom) {
		super();
		this.modoExibicao = modoExibicao;
		this.tipoSom = tipoSom;
	}

	public abstract void aplicarConfiguracao();
}

//Classe abstrata para usuários da TV
abstract class Usuario2 extends Entidade2 {
	protected String nome;
	protected int idade;

	public Usuario2(String nome, int idade) {
		super();
		this.nome = nome;
		this.idade = idade;
	}
}

//Implementação de CRUD para TVs
class GerenciadorTV {
	private final List<TV> televisores = new ArrayList<>();
	private final ReentrantLock lock = new ReentrantLock();

	public void adicionarTV(TV tv) {
		lock.lock();
		try {
			televisores.add(tv);
		} finally {
			lock.unlock();
		}
	}

	public void listarTVs() {
		lock.lock();
		try {
			Iterator<TV> it = televisores.iterator();
			while (it.hasNext()) {
				TV tv = it.next();
				tv.exibirInformacoes();
			}
		} finally {
			lock.unlock();
		}
	}

	public synchronized boolean removerTV(UUID id) {
		Iterator<TV> it = televisores.iterator();
		while (it.hasNext()) {
			TV tv = it.next();
			if (tv.getId().equals(id)) {
				it.remove();
				return true;
			}
		}
		return false;
	}
}

//Exemplo de uma TV concreta
class SmartTV extends TV {
	public SmartTV(MarcaTV marca, TamanhoTV tamanho, ResolucaoTV resolucao, TipoTela tipoTela) {
		super(marca, tamanho, resolucao, tipoTela);
	}

	@Override
	public void exibirInformacoes() {
		System.out.println("TV " + marca + " " + tamanho + " " + resolucao + " " + tipoTela);
	}
}

class Import_3S {
	public static void run_Import_3S() {
		System.err.println("\t\t3. \n");

		GerenciadorTV gerenciador = new GerenciadorTV();

		TV tv1 = new SmartTV(MarcaTV.SAMSUNG, TamanhoTV.P55, ResolucaoTV.UHD_4K, TipoTela.QLED);
		TV tv2 = new SmartTV(MarcaTV.LG, TamanhoTV.P65, ResolucaoTV.UHD_8K, TipoTela.OLED);

		gerenciador.adicionarTV(tv1);
		gerenciador.adicionarTV(tv2);

		gerenciador.listarTVs();

		System.out.println("Removendo TV: " + gerenciador.removerTV(tv1.getId()));
		gerenciador.listarTVs();

	}
}
