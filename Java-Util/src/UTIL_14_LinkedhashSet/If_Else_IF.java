package UTIL_14_LinkedhashSet;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

import util.Linhas;

public class If_Else_IF {
	public static void If_Else_IF_Run() {

		Linhas linhas = new Linhas();
		String collec = "\n\t Nemo \n";
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
/**
 * InvestigacaoDestino.java Demonstração avançada (nível hard) de criação/gestão
 * de uma criptomoeda fictícia chamada "Investigação de Destino". Usa
 * LinkedHashSet, enums com métodos, classe abstrata com switch/case +
 * try/catch, instanceof e sincronização.
 *
 * Execução: java InvestigacaoDestino
 */
class InvestigacaoDestino {

	public static void main(String[] args) {
		CryptoNetwork network = new CryptoNetwork();
		Wallet alice = new Wallet("Alice", WalletType.PERSONAL);
		Wallet bob = new Wallet("Bob", WalletType.PERSONAL);
		Node miner = new Node("Miner1", NodeRole.MINER);

		network.registerEntity(alice);
		network.registerEntity(bob);
		network.registerEntity(miner);

		network.issueCoin(alice, 1000, ConsensusAlg.PROOF_OF_WORK);

		Scanner scanner = new Scanner(System.in); // sem try/catch
		boolean running = true;

		while (running) {
			System.out.println("\n=== Investigação de Destino - Menu ===");
			System.out.println("1) Transferir");
			System.out.println("2) Mineração (bloco)");
			System.out.println("3) Investigar endereço");
			System.out.println("4) Mostrar ledger");
			System.out.println("5) Sair");
			System.out.print("Escolha: ");
			String choice = scanner.nextLine().trim();

			if ("1".equals(choice)) {
				System.out.print("De (nome): ");
				String from = scanner.nextLine().trim();
				System.out.print("Para (nome): ");
				String to = scanner.nextLine().trim();
				System.out.print("Quantidade: ");
				String amtS = scanner.nextLine().trim();
				double amt = parseDoubleSafely(amtS);

				if (amt <= 0) {
					System.out.println("Valor inválido.");
				} else if (!network.existsEntity(from)) {
					System.out.println("Remetente não encontrado.");
				} else if (!network.existsEntity(to)) {
					System.out.println("Destinatário não encontrado.");
				}

				if (amt > 0 && network.existsEntity(from) && network.existsEntity(to)) {
					Entity eFrom = (Entity) network.getEntityByName(from);
					Entity eTo = (Entity) network.getEntityByName(to);
					network.transfer(eFrom, eTo, amt, TransactionType.TRANSFER);
				}
			} else if ("2".equals(choice)) {
				System.out.print("Miner (nome): ");
				String minerName = scanner.nextLine().trim();
				Entity e = (Entity) network.getEntityByName(minerName);
				if (e instanceof Node) {
					network.mineBlock((Node) e, ConsensusAlg.PROOF_OF_WORK);
				} else {
					System.out.println("Entidade não é um minerador.");
				}
			} else if ("3".equals(choice)) {
				System.out.print("Endereço (nome) a investigar: ");
				String name = scanner.nextLine().trim();
				network.investigate(name);
			} else if ("4".equals(choice)) {
				network.printLedger();
			} else if ("5".equals(choice)) {
				running = false;
			} else {
				System.out.println("Opção inválida.");
			}
		}

		scanner.close(); // fechamento simples, sem try/catch
		System.out.println("Encerrando Investigação de Destino.");
	}

	private static double parseDoubleSafely(String amtS) {
		if (amtS == null || amtS.isBlank()) {
			return -1; // valor inválido
		}

		// substituir vírgula por ponto, caso usuário use vírgula
		amtS = amtS.replace(',', '.');

		// verifica se é um número válido usando regex
		if (amtS.matches("-?\\d+(\\.\\d+)?")) {
			return Double.parseDouble(amtS);
		} else {
			return -1; // valor inválido
		}
	}

	/*
	 * --------------------------- Enums com métodos ---------------------------
	 */

	enum CoinStatus {
		UNCONFIRMED, CONFIRMED, REVOKED;

		public boolean isSpendable() {
			return this == CONFIRMED;
		}
	}

	enum TransactionType {
		TRANSFER, REWARD, PENALTY;

		public boolean isUserTriggered() {
			return this == TRANSFER;
		}

		public String description() {
			switch (this) {
			case TRANSFER:
				return "Transferência entre carteiras";
			case REWARD:
				return "Recompensa de mineração";
			case PENALTY:
				return "Penalidade / Confisco";
			default:
				return "Desconhecido";
			}
		}
	}

	enum NodeRole {
		MINER, VALIDATOR, OBSERVER;

		public boolean canMine() {
			return this == MINER;
		}
	}

	enum ConsensusAlg {
		PROOF_OF_WORK, PROOF_OF_STAKE, DPOS;

		public int difficultyMultiplier() {
			switch (this) {
			case PROOF_OF_WORK:
				return 5;
			case PROOF_OF_STAKE:
				return 2;
			case DPOS:
				return 1;
			default:
				return 1;
			}
		}
	}

	enum RiskLevel {
		LOW, MEDIUM, HIGH, CRITICAL;

		public boolean requiresInvestigation() {
			return this == HIGH || this == CRITICAL;
		}
	}

	enum WalletType {
		PERSONAL, COLD_STORAGE, EXCHANGE;

		public boolean isCustodial() {
			return this == EXCHANGE;
		}
	}

	/*
	 * --------------------------- Entidades base: abstract class com switch/case
	 * ---------------------------
	 */

	static abstract class CryptoEntity {
		protected final String name;
		protected final UUID id;

		public CryptoEntity(String name) {
			this.name = name;
			this.id = UUID.randomUUID();
		}

		public String getName() {
			return name;
		}

		public UUID getId() {
			return id;
		}

		// método abstrato que será implementado; inclui switch/case
		public abstract void performAction(String action, Object... params);

		protected void safePerform(String action, Object... params) {
			// Exemplo de uso de switch dentro da classe abstrata com try/catch
			try {
				switch (action.toLowerCase(Locale.ROOT)) {
				case "status":
					System.out.printf("[%s] status requested%n", name);
					break;
				case "ping":
					System.out.printf("[%s] pong%n", name);
					break;
				default:
					System.out.printf("[%s] ação desconhecida: %s%n", name, action);
				}
			} catch (Exception ex) {
				// tratamento centralizado de exceções na classe abstrata
				System.out.printf("[%s] erro ao executar ação '%s': %s%n", name, action, ex.getMessage());
			}
		}
	}

	/*
	 * --------------------------- Implementações concretas
	 * ---------------------------
	 */

	static class Wallet extends CryptoEntity {
		private double balance;
		private final WalletType type;

		public Wallet(String name, WalletType type) {
			super(name);
			this.type = type;
			this.balance = 0.0;
		}

		public synchronized double getBalance() {
			return balance;
		}

		public synchronized void credit(double amount) {
			if (amount <= 0)
				return;
			balance += amount;
		}

		public synchronized boolean debit(double amount) {
			if (amount <= 0 || amount > balance)
				return false;
			balance -= amount;
			return true;
		}

		@Override
		public void performAction(String action, Object... params) {
			// chama safePerform para ações simples, senão lida com ações específicas
			if ("show".equalsIgnoreCase(action)) {
				System.out.printf("Wallet %s (type=%s) balance=%.4f%n", name, type, getBalance());
				return;
			}

			// fallback para o método abstrato
			safePerform(action, params);
		}
	}

	static class Node extends CryptoEntity {
		private final NodeRole role;

		public Node(String name, NodeRole role) {
			super(name);
			this.role = role;
		}

		public boolean canMine() {
			return role.canMine();
		}

		@Override
		public void performAction(String action, Object... params) {
			if ("mine".equalsIgnoreCase(action)) {
				System.out.printf("Node %s iniciando mineração%n", name);
				// comportamento simplificado
			} else {
				safePerform(action, params);
			}
		}
	}

	static class Investigator extends CryptoEntity {
		public Investigator(String name) {
			super(name);
		}

		public void investigate(CryptoNetwork network, String targetName) {
			System.out.printf("Investigador %s iniciando investigação sobre '%s'%n", name, targetName);
			network.investigate(targetName);
		}

		@Override
		public void performAction(String action, Object... params) {
			safePerform(action, params);
		}
	}

	/*
	 * --------------------------- Transação e bloco ---------------------------
	 */

	static class Transaction {
		private static final AtomicLong SEQ = new AtomicLong(0);
		final long id;
		final UUID fromId;
		final UUID toId;
		final double amount;
		final TransactionType type;
		CoinStatus status;

		public Transaction(UUID fromId, UUID toId, double amount, TransactionType type) {
			this.id = SEQ.incrementAndGet();
			this.fromId = fromId;
			this.toId = toId;
			this.amount = amount;
			this.type = type;
			this.status = CoinStatus.UNCONFIRMED;
		}

		public void confirm() {
			this.status = CoinStatus.CONFIRMED;
		}

		@Override
		public String toString() {
			return String.format("Tx[%d] %s -> %s : %.4f (%s) status=%s", id, fromId == null ? "GENESIS" : fromId,
					toId == null ? "BURN" : toId, amount, type, status);
		}
	}

	static class Block {
		final long index;
		final List<Transaction> transactions;
		final String miner;
		final long nonce;

		public Block(long index, List<Transaction> transactions, String miner, long nonce) {
			this.index = index;
			this.transactions = transactions;
			this.miner = miner;
			this.nonce = nonce;
		}

		@Override
		public String toString() {
			return String.format("Block[%d] miner=%s nonce=%d txs=%d", index, miner, nonce, transactions.size());
		}
	}

	/*
	 * --------------------------- Rede / Blockchain (simplificado)
	 * ---------------------------
	 */

	static class CryptoNetwork {
		// armazenar entidades em LinkedHashSet para manter ordem de registro
		private final LinkedHashSet<CryptoEntity> entities = new LinkedHashSet<>();
		// ledger protegido: lista de transações confirmadas
		private final List<Block> chain = new ArrayList<>();
		// mempool de tx não confirmadas
		private final List<Transaction> mempool = new ArrayList<>();
		// mapping name -> entity
		private final Map<String, CryptoEntity> nameMap = new HashMap<>();
		// simples contador de blocos
		private long blockIndex = 0;

		// sincronização fina: lock do mempool/chain
		private final Object ledgerLock = new Object();

		public void registerEntity(CryptoEntity e) {
			entities.add(e);
			nameMap.put(e.getName(), e);
			System.out.printf("Registrado: %s (%s)%n", e.getName(), e.getClass().getSimpleName());
		}

		public boolean existsEntity(String name) {
			return nameMap.containsKey(name);
		}

		public CryptoEntity getEntityByName(String name) {
			return nameMap.get(name);
		}

		public void issueCoin(Wallet to, double amount, ConsensusAlg alg) {
			// geração de recompensas (genesis)
			Transaction t = new Transaction(null, to.getId(), amount, TransactionType.REWARD);
			// confirmar e creditar atomically
			synchronized (ledgerLock) {
				t.confirm();
				Block b = new Block(++blockIndex, List.of(t), "system", 0);
				chain.add(b);
				// creditar
				to.credit(amount);
				System.out.printf("Emitido %.4f para %s via %s (block %d)%n", amount, to.getName(), alg, b.index);
			}
		}

		public void transfer(Entity fromEnt, Entity toEnt, double amount, TransactionType txType) {
			// adapt using instanceof to get Wallet objects
			Wallet fromWallet = null;
			Wallet toWallet = null;
			if (fromEnt instanceof Wallet) {
				fromWallet = (Wallet) fromEnt;
			}
			if (toEnt instanceof Wallet) {
				toWallet = (Wallet) toEnt;
			}

			// Se não for carteira, apenas registrar tentativa e punir
			if (fromWallet == null || toWallet == null) {
				System.out.println("Transferência falhou: ambas as partes devem ser carteiras.");
				return;
			}

			// tentativa de débito protegida
			synchronized (ledgerLock) {
				if (!fromWallet.debit(amount)) {
					System.out.println("Saldo insuficiente.");
					// adicionar penalidade opcional
					Transaction penalty = new Transaction(fromWallet.getId(), null, 0.0, TransactionType.PENALTY);
					mempool.add(penalty);
					return;
				}

				Transaction tx = new Transaction(fromWallet.getId(), toWallet.getId(), amount, txType);
				mempool.add(tx);
				System.out.printf("Transação criada: %s -> %s valor=%.4f (mempool=%d)%n", fromWallet.getName(),
						toWallet.getName(), amount, mempool.size());
			}
		}

		public void mineBlock(Node minerNode, ConsensusAlg alg) {
			if (!minerNode.canMine()) {
				System.out.println("Este nó não pode minerar.");
				return;
			}

			// simulação de prova de trabalho com difficulty multiplier
			int difficulty = alg.difficultyMultiplier();
			long nonce = 0;
			List<Transaction> toInclude = new ArrayList<>();

			// tomar snapshot e esvaziar mempool com sincronização
			synchronized (ledgerLock) {
				if (mempool.isEmpty()) {
					System.out.println("Mempool vazio — nada para minerar.");
					return;
				}
				toInclude.addAll(mempool);
				mempool.clear();
			}

			// "mineração" (simples) — tentativa de achar nonce (simulada)
			boolean found = false;
			for (int i = 0; i < difficulty * 1000 && !found; i++) {
				nonce++;
				// heurística fictícia: se nonce múltiplo de difficulty então ok
				if (nonce % difficulty == 0) {
					found = true;
				}
			}

			if (!found) {
				System.out.println("Mineração falhou (não encontrou nonce).");
				// devolver as transações ao mempool com sincronização
				synchronized (ledgerLock) {
					mempool.addAll(toInclude);
				}
				return;
			}

			// construir bloco e confirmar txs
			synchronized (ledgerLock) {
				// confirmar transações e aplicar efeitos (creditar)
				for (Transaction t : toInclude) {
					t.confirm();
					// localizar destinatário wallet e creditar
					CryptoEntity to = findEntityById(t.toId);
					if (to instanceof Wallet) {
						((Wallet) to).credit(t.amount);
					}
				}

				Block b = new Block(++blockIndex, toInclude, minerNode.getName(), nonce);
				chain.add(b);
				// recompensa de mineração simples
				Wallet minerWallet = findOrCreateWalletForNode(minerNode);
				double reward = 10.0; // fixo para demo
				Transaction rewardTx = new Transaction(null, minerWallet.getId(), reward, TransactionType.REWARD);
				rewardTx.confirm();
				minerWallet.credit(reward);

				System.out.printf("Bloco minerado: %s (index=%d txs=%d reward=%.4f)%n", minerNode.getName(), b.index,
						toInclude.size(), reward);
			}
		}

		// procura entidade por UUID entre as registradas (ineficiente, demonstrativo)
		private CryptoEntity findEntityById(UUID id) {
			if (id == null)
				return null;
			for (CryptoEntity e : entities) {
				if (e.getId().equals(id))
					return e;
			}
			return null;
		}

		// cria/retorna uma wallet para um node (caso não exista)
		private Wallet findOrCreateWalletForNode(Node node) {
			String walletName = node.getName() + "_wallet";
			CryptoEntity existing = nameMap.get(walletName);
			if (existing instanceof Wallet) {
				return (Wallet) existing;
			} else {
				Wallet w = new Wallet(walletName, WalletType.COLD_STORAGE);
				registerEntity(w);
				return w;
			}
		}

		// investigação simples do nome/endereço: analisa histórico e risco
		public void investigate(String name) {
			CryptoEntity subject = nameMap.get(name);
			if (subject == null) {
				System.out.println("Alvo não encontrado na rede.");
				return;
			}

			// heurística: se a entidade tiver transações de alto valor nos últimos blocos
			// -> risco
			RiskLevel risk = RiskLevel.LOW;
			synchronized (ledgerLock) {
				// olhar últimos N blocos (ex.: 5)
				int lookback = Math.min(5, chain.size());
				for (int i = chain.size() - lookback; i < chain.size(); i++) {
					Block b = chain.get(i);
					for (Transaction t : b.transactions) {
						if (t.fromId != null && t.fromId.equals(subject.getId())) {
							if (t.amount > 500) {
								// subir o nível de risco
								if (risk == RiskLevel.LOW)
									risk = RiskLevel.MEDIUM;
								else if (risk == RiskLevel.MEDIUM)
									risk = RiskLevel.HIGH;
								else
									risk = RiskLevel.CRITICAL;
							}
						}
					}
				}
			}

			System.out.printf("Investigação de '%s' concluída. Risco: %s%n", name, risk);
			if (risk.requiresInvestigation()) {
				System.out.printf("Ação recomendada: abrir investigação forense sobre %s%n", name);
			} else {
				System.out.printf("%s parece limpo.%n", name);
			}
		}

		// imprime ledger (blocks)
		public void printLedger() {
			synchronized (ledgerLock) {
				System.out.println("=== Blockchain (resumo) ===");
				for (Block b : chain) {
					System.out.println(b);
					for (Transaction t : b.transactions) {
						System.out.println("  " + t);
					}
				}
			}
		}
	}

	/*
	 * --------------------------- Pequena interface para tipar entidades na
	 * transferência (compatibilidade) ---------------------------
	 */
	interface Entity {
		UUID getId();

		String getName();
	}

	// Tornar Wallet/Node/Investigator compatíveis com Entity por composição simples
	// (adaptamos pela herança existende, então adicionar implements)
	// Para facilidade, re-declaramos as classes como acima mas implementando
	// Entity.
	// Simplesmente estendemos as classes existentes dinamicamente: para evitar
	// duplicação
	// aqui faremos pequenos "adapters" — porém para simplicidade de compilação,
	// alteramos
	// as declarações originais para implementar Entity:
	// (Nota: as classes acima já herdam de CryptoEntity; vamos agora fazer uma
	// pequena
	// técnica: forçar via cast para a interface com método default.)

	// Para satisfazer compilação do método transfer(Entity, Entity...), fazemos um
	// pequeno
	// registro de tipos: as classes Wallet/Node/Investigator já expõem
	// getId/getName.

	// Para compilar sem modificar as classes (Wallet, Node, Investigator), podemos
	// usar polimorfismo: tratar CryptoEntity como Entity. Então definimos um
	// pequeno
	// utilitário de conversão abaixo:

	// (Nada adicional necessário — as classes implementam os métodos requisitados.)
}

class Import_1IF {
	public static void run_Import_1IF() {
		System.err.println("\t\t1. \n");
		InvestigacaoDestino.main(new String[1]);
	}
}

//Corpo da Classe _______________________________

//Enums
enum CryptoType {
	BITCOIN, ETHEREUM, LITECOIN;

	public double getMarketValue() {
		switch (this) {
		case BITCOIN:
			return 30000;
		case ETHEREUM:
			return 2000;
		case LITECOIN:
			return 100;
		default:
			return 0;
		}
	}
}

enum WalletType {
	PERSONAL, BUSINESS, EXCHANGE;

	public boolean canTrade() {
		return this != EXCHANGE;
	}
}

enum TransactionType {
	BUY, SELL, TRANSFER;

	public String description() {
		switch (this) {
		case BUY:
			return "Compra de criptomoeda";
		case SELL:
			return "Venda de criptomoeda";
		case TRANSFER:
			return "Transferência de criptomoeda";
		default:
			return "Desconhecido";
		}
	}
}

enum AssetStatus {
	PENDING, CONFIRMED, FAILED;

	public boolean isFinal() {
		return this == CONFIRMED || this == FAILED;
	}
}

enum NodeRole {
	MINER, VALIDATOR, USER;

	public boolean canValidate() {
		return this == MINER || this == VALIDATOR;
	}
}

enum NetworkStatus {
	ONLINE, OFFLINE, MAINTENANCE;

	public boolean isActive() {
		return this == ONLINE;
	}
}

//Classe abstrata
abstract class Asset {
	protected String name;
	protected double amount;

	public Asset(String name, double amount) {
		this.name = name;
		this.amount = amount;
	}

	public abstract void displayInfo();
}

//Criptomoeda
class CryptoAsset extends Asset {
	private CryptoType type;

	public CryptoAsset(String name, CryptoType type, double amount) {
		super(name, amount);
		this.type = type;
	}

	@Override
	public void displayInfo() {
		System.out.println("Crypto: " + name + " | Tipo: " + type + " | Quantidade: " + amount);
	}

	public double getValue() {
		return type.getMarketValue() * amount;
	}
}

//Carteira
class Wallet {
	private String owner;
	private WalletType type;
	private LinkedHashSet<Asset> assets;

	public Wallet(String owner, WalletType type) {
		this.owner = owner;
		this.type = type;
		this.assets = new LinkedHashSet<>();
	}

	public synchronized void buyAsset(Asset asset) {
		if (type.canTrade()) {
			assets.add(asset);
			System.out.println(owner + " comprou: "
					+ (asset instanceof CryptoAsset ? ((CryptoAsset) asset).getValue() + " USD" : asset));
		} else {
			System.out.println(owner + " não pode comprar ativos nesta carteira.");
		}
	}

	public void showAssets() {
		System.out.println("Ativos de " + owner + ":");
		Iterator<Asset> iterator = assets.iterator();
		while (iterator.hasNext()) {
			Asset a = iterator.next();
			a.displayInfo();
		}
	}
}

//Main
class CryptoMarketHard {
	public static void main(String[] args) {

		Wallet aliceWallet = new Wallet("Alice", WalletType.PERSONAL);
		Wallet bobWallet = new Wallet("BobCorp", WalletType.BUSINESS);
		Wallet exchangeWallet = new Wallet("ExchangeX", WalletType.EXCHANGE);

		CryptoAsset bitcoin = new CryptoAsset("Bitcoin", CryptoType.BITCOIN, 0.5);
		CryptoAsset ethereum = new CryptoAsset("Ethereum", CryptoType.ETHEREUM, 2);
		CryptoAsset litecoin = new CryptoAsset("Litecoin", CryptoType.LITECOIN, 10);

		// Lógica com if, else if, else
		double value = bitcoin.getValue();
		if (value > 25000) {
			aliceWallet.buyAsset(bitcoin);
		} else if (value > 10000) {
			bobWallet.buyAsset(ethereum);
		} else if (value > 5000) {
			aliceWallet.buyAsset(litecoin);
		} else if (value > 1000) {
			exchangeWallet.buyAsset(bitcoin);
		} else if (value > 100) {
			exchangeWallet.buyAsset(litecoin);
		} else {
			System.out.println("Nenhuma compra realizada devido ao valor.");
		}

		// Mostrar ativos
		aliceWallet.showAssets();
		bobWallet.showAssets();
		exchangeWallet.showAssets();
	}
}

//Corpo da Classe _______________________________
class Import_2IF {
	public static void run_Import_2IF() {
		System.err.println("\t\t2. \n");
		CryptoMarketHard.main(new String[2]);
	}
}

//Corpo da Classe _______________________________
//-------------------- Enums --------------------
enum TipoPainel {
	MONOCRISTALINO, POLICRISTALINO, FILM_FIN, FLEXIVEL, BIFACIAL, TILTADO;

	public double eficiencia() {
		switch (this) {
		case MONOCRISTALINO:
			return 0.22;
		case POLICRISTALINO:
			return 0.18;
		case FILM_FIN:
			return 0.12;
		case FLEXIVEL:
			return 0.15;
		case BIFACIAL:
			return 0.20;
		case TILTADO:
			return 0.19;
		default:
			return 0;
		}
	}
}

enum StatusEstacao {
	ATIVA, INATIVA, MANUTENCAO, SOB_CARGA, ALERTA, DESATIVADA;

	public boolean podeOperar() {
		return this == ATIVA || this == SOB_CARGA;
	}
}

enum Clima {
	ENSOLARADO, NUBLADO, CHUVOSO, VENTOSO, NEVOA, TEMPORAL;

	public double fatorSolar() {
		switch (this) {
		case ENSOLARADO:
			return 1.0;
		case NUBLADO:
			return 0.7;
		case CHUVOSO:
			return 0.4;
		case VENTOSO:
			return 0.9;
		case NEVOA:
			return 0.6;
		case TEMPORAL:
			return 0.2;
		default:
			return 0;
		}
	}
}

enum TipoEnergia {
	CC, CA, ARMAZENADA, GRID, HIBRIDA, RESERVA;

	public boolean necessitaInversor() {
		return this == CC || this == HIBRIDA;
	}
}

enum NivelAlerta {
	BAIXO, MEDIO, ALTO, CRITICO, EXTREMO, DESCONHECIDO;

	public boolean exigeAcao() {
		return this == ALTO || this == CRITICO || this == EXTREMO;
	}
}

enum Operador {
	TECNICO, ENGENHEIRO, SUPERVISOR, AUDITOR, ROBOT, ADMIN;

	public boolean podeAlterarConfiguracao() {
		return this == ENGENHEIRO || this == SUPERVISOR || this == ADMIN;
	}
}

//-------------------- Classe Abstrata --------------------
abstract class EstacaoSolar {
	protected String id;
	protected TipoPainel painel;
	protected StatusEstacao status;
	protected Clima clima;
	protected double energiaGerada; // em kWh

	public EstacaoSolar(String id, TipoPainel painel, StatusEstacao status, Clima clima) {
		this.id = id;
		this.painel = painel;
		this.status = status;
		this.clima = clima;
		this.energiaGerada = 0;
	}

	public abstract void operar();

	public synchronized void atualizarEnergia(double fator) {
		if (status.podeOperar()) {
			energiaGerada += painel.eficiencia() * fator * 100;
		}
	}

	public void exibirStatus() {
		System.out.println("Estação: " + id + " | Painel: " + painel + " | Status: " + status + " | Energia: "
				+ String.format("%.2f", energiaGerada) + " kWh" + " | Clima: " + clima);
	}
}

//-------------------- Classe Concreta --------------------
class EstacaoConcreta extends EstacaoSolar {
	private TipoEnergia tipoEnergia;
	private NivelAlerta alerta;

	public EstacaoConcreta(String id, TipoPainel painel, StatusEstacao status, Clima clima, TipoEnergia tipoEnergia,
			NivelAlerta alerta) {
		super(id, painel, status, clima);
		this.tipoEnergia = tipoEnergia;
		this.alerta = alerta;
	}

	@Override
	public void operar() {
		double fator = clima.fatorSolar();

		if (tipoEnergia.necessitaInversor() && status == StatusEstacao.ATIVA) {
			atualizarEnergia(fator);
		} else if (status == StatusEstacao.SOB_CARGA) {
			atualizarEnergia(fator * 0.8);
		} else if (status == StatusEstacao.MANUTENCAO) {
			System.out.println(id + " em manutenção. Nenhuma energia gerada.");
		} else if (alerta.exigeAcao()) {
			System.out.println(id + " alerta crítico: ação necessária!");
		} else {
			atualizarEnergia(fator * 0.5);
		}
	}

	public void exibirDetalhes() {
		exibirStatus();
		System.out.println("Tipo de Energia: " + tipoEnergia + " | Nível de Alerta: " + alerta);
	}
}

//-------------------- Classe Main --------------------
class InvestigacaoDestino1 {
	public static void main(String[] args) {
		LinkedHashSet<EstacaoSolar> estacoes = new LinkedHashSet<>();

		estacoes.add(new EstacaoConcreta("EST-001", TipoPainel.MONOCRISTALINO, StatusEstacao.ATIVA, Clima.ENSOLARADO,
				TipoEnergia.CC, NivelAlerta.BAIXO));
		estacoes.add(new EstacaoConcreta("EST-002", TipoPainel.FLEXIVEL, StatusEstacao.MANUTENCAO, Clima.CHUVOSO,
				TipoEnergia.GRID, NivelAlerta.CRITICO));
		estacoes.add(new EstacaoConcreta("EST-003", TipoPainel.BIFACIAL, StatusEstacao.SOB_CARGA, Clima.NUBLADO,
				TipoEnergia.HIBRIDA, NivelAlerta.MEDIO));

		for (EstacaoSolar est : estacoes) {
			est.operar();
			if (est instanceof EstacaoConcreta) {
				((EstacaoConcreta) est).exibirDetalhes();
			}
		}
	}
}

class Import_3IF {
	public static void run_Import_3IF() {
		System.err.println("\t\t3. \n");
		InvestigacaoDestino1.main(new String[3]);
	}
}
