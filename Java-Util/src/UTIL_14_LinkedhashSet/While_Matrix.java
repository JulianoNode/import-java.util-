package UTIL_14_LinkedhashSet;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.locks.ReentrantLock;

import util.Linhas;

public class While_Matrix {
	public static void While_Run() {
		Linhas linhas = new Linhas();
		String collec = "\n\t Nome \n";
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
//Enum 1: Define o nível de acesso do perfil
enum NivelAcesso {
	ADMINISTRADOR, MODERADOR, PADRAO, CONVIDADO
}

//Enum 2: Define o status atual do perfil
enum StatusPerfil {
	ATIVO, INATIVO, SUSPENSO, EM_ANALISE
}

//Enum 3: Define o tipo de notificação preferido
enum TipoNotificacao {
	EMAIL, SMS, PUSH, NENHUM
}

//Enum 4: Define o departamento ou setor
enum Departamento {
	TI, FINANCEIRO, RH, MARKETING
}

//Enum 5: Define a região de atuação
enum Regiao9 {
	SUL, SUDESTE, CENTRO_OESTE, NORTE, NORDESTE
}

//Enum 6: Define as permissões básicas
enum Permissao {
	LEITURA, ESCRITA, DELECAO, NULA
}

//Classe Abstrata base para Perfil
abstract class EntidadeBase {
	protected final int id;
	protected final LocalDateTime dataCriacao;

	public EntidadeBase(int id) {
		this.id = id;
		this.dataCriacao = LocalDateTime.now();
	}

	public int getId() {
		return id;
	}

	public abstract String exibirDetalhes();
}

//Classe que representa o Perfil e estende a classe abstrata
class Perfil extends EntidadeBase implements Comparable<Perfil> {
	String nome;
	NivelAcesso nivel;
	StatusPerfil status;
	TipoNotificacao notificacao;
	Departamento departamento;
	Regiao9 regiao9;
	Permissao permissao;
	Regiao9 regiao;

	public Perfil(int id, String nome, NivelAcesso nivel, StatusPerfil status, TipoNotificacao notificacao,
			Departamento departamento, Regiao9 regiao9, Permissao permissao) {
		super(id);
		this.nome = nome;
		this.nivel = nivel;
		this.status = status;
		this.notificacao = notificacao;
		this.departamento = departamento;
		this.regiao9 = regiao9;
		this.permissao = permissao;
	}

	// Getters e Setters (para o Update - U do CRUD)
	// ... (omitidos para brevidade, mas seriam necessários para um CRUD completo)

	// Método obrigatório da classe abstrata
	@Override
	public String exibirDetalhes() {
		return "ID: " + id + ", Nome: " + nome + ", Nível: " + nivel + ", Status: " + status + ", Criado em: "
				+ dataCriacao;
	}

	// Método de Ordenação (Comparable)
	@Override
	public int compareTo(Perfil outro) {
		// Ordenação pelo ID do Perfil
		return Integer.compare(this.id, outro.id);
	}
}

class GerenciadorPerfis {
	// Usando LinkedHashSet para manter a ordem de inserção (como solicitado)
	private final Set<Perfil> perfis;
	private int proximoId = 1;

	public GerenciadorPerfis() {
		this.perfis = new LinkedHashSet<>();
	}

	// ==========================================================
	// MÉTODO 1: CREATE (C) com if/else if/else e instanceof
	// ==========================================================
	public void adicionar(EntidadeBase entidade) {
		// 1. Uso de Pattern Matching for instanceof (Java 16+)
		// Verifica se é um Perfil E, se for, faz o casting para 'novoPerfil'
		if (entidade instanceof Perfil novoPerfil) {

			// 2. Lógica de Atribuição de ID
			// Se o ID do Perfil for inválido (< 1), recria/substitui o objeto
			// com o ID gerenciado pelo CRUD (this.proximoId++)
			if (novoPerfil.getId() < 1) {
				// Cria um NOVO objeto Perfil com o ID gerado pelo gerenciador
				// (Assumindo que 'nome', 'nivel', etc., são acessíveis ou temos getters)
				// NOTA: Para que este bloco funcione, os atributos 'nome', 'nivel', etc.
				// na classe Perfil precisam ser públicos, ou ter getters acessíveis aqui.
				// No seu código anterior, eles eram privados ou package-private dentro da
				// classe Perfil.

				Perfil perfilComNovoId = new Perfil(this.proximoId++, novoPerfil.nome, novoPerfil.nivel,
						novoPerfil.status, novoPerfil.notificacao, novoPerfil.departamento, novoPerfil.regiao,
						novoPerfil.permissao);

				// Substitui a referência para usar a versão com o ID correto
				novoPerfil = perfilComNovoId;
			}

			// 3. Adiciona ao conjunto e usa if/else para feedback
			if (perfis.add(novoPerfil)) {
				System.out.println(
						"Perfil " + novoPerfil.nome + " (ID: " + novoPerfil.getId() + ") adicionado com sucesso.");
			} else {
				// LinkedHashSet retorna false se o elemento já existe (baseado em
				// hashCode/equals)
				System.out.println("Falha ao adicionar Perfil. Perfil com ID " + novoPerfil.getId()
						+ " já existe ou ID duplicado.");
			}
		} else {
			// Bloco else para o instanceof (Entidade não é um Perfil)
			System.out.println("Erro: A entidade fornecida não é um Perfil. Objeto ignorado.");
			// Não é necessário o 'return' pois o código que adiciona está dentro do 'if'
		}
	}

	// ==========================================================
	// MÉTODO 2: READ (R) com for e switch
	// ==========================================================
	public void listarTodos(String tipoListagem) {
		// Usa o switch para determinar como a listagem deve ocorrer
		switch (tipoListagem.toLowerCase()) {
		case "simples":
			System.out.println("\n--- Lista de Perfis (Simples) ---");
			// Loop for-each para iterar a coleção
			for (Perfil p : this.perfis) {
				System.out.println("ID: " + p.getId() + ", Nome: " + p.nome);
			}
			break;

		case "detalhada":
			System.out.println("\n--- Lista de Perfis (Detalhada) ---");
			// Loop for-each
			for (Perfil p : this.perfis) {
				System.out.println(p.exibirDetalhes());
			}
			break;

		default:
			System.out.println("Tipo de listagem inválido. Use 'simples' ou 'detalhada'.");
			break;
		}
	}

	// ==========================================================
	// MÉTODO 3: BUSCA (R - Read/Busca) com do-while e if/else
	// ==========================================================
	public Perfil buscarPorId(int idBusca) {
		Iterator<Perfil> it = perfis.iterator();
		Perfil encontrado = null;

		// Loop do-while para garantir que a busca ocorra pelo menos uma vez
		do {
			if (it.hasNext()) {
				Perfil p = it.next();

				// if para a condição de busca
				if (p.getId() == idBusca) {
					encontrado = p;
					break; // Sai do loop
				}
			} else {
				// else para quando não houver mais elementos
				break; // Sai do loop se não houver mais
			}
		} while (it.hasNext());

		if (encontrado != null) {
			System.out.println("\n--- Perfil Encontrado ---");
		} else {
			System.out.println("\nPerfil com ID " + idBusca + " não encontrado.");
		}
		return encontrado;
	}

	// ==========================================================
	// MÉTODO 4: ORDENAÇÃO
	// ==========================================================
	public void ordenarPerfis() {
		System.out.println("\n--- Perfis Ordenados por ID ---");
		// TreeSet usa a implementação Comparable de Perfil para ordenar
		Set<Perfil> perfisOrdenados = new TreeSet<>(this.perfis);

		for (Perfil p : perfisOrdenados) {
			System.out.println("ID: " + p.getId() + ", Nome: " + p.nome);
		}
	}

	// ==========================================================
	// MÉTODO 5: SINCRONIZAÇÃO (Exemplo de método sincronizado)
	// ==========================================================
	// Usamos 'synchronized' para garantir que apenas uma thread acesse este método
	// por vez, essencial em ambientes multithread para evitar condições de corrida.
	public synchronized void sincronizarAcesso() {
		System.out.println("\n--- Bloco Sincronizado ---");
		// Acesso ou modificação de recursos compartilhados (como 'perfis') deve
		// ser feito dentro de um bloco sincronizado ou método sincronizado.
		if (this.perfis.isEmpty()) {
			System.out.println("Coleção vazia. Nenhuma sincronização de dados necessária.");
		} else {
			System.out.println("Coleção em uso seguro (sincronizado).");
		}
	}

	// ==========================================================
	// MÉTODO 6: Exemplo de UPDATE (U) com if/else if/else
	// ==========================================================
	public boolean atualizarStatus(int idBusca, StatusPerfil novoStatus) {
		Perfil p = buscarPorId(idBusca);

		if (p == null) {
			System.out.println("Atualização falhou: Perfil não existe.");
			return false;
		} else if (p.status == novoStatus) {
			System.out.println("Status já é " + novoStatus + ". Nenhuma alteração.");
			return true;
		} else {
			// A atualização real
			p.status = novoStatus;
			System.out.println("Status do Perfil " + p.nome + " atualizado para " + novoStatus);
			return true;
		}
	}

	// =мениться 7: DELETE (D)
	public void removerPorId(int id) {
		Iterator<Perfil> iterator = perfis.iterator();
		boolean removido = false;

		while (iterator.hasNext()) {
			Perfil p = iterator.next();
			if (p.getId() == id) {
				iterator.remove();
				System.out.println("\nPerfil com ID " + id + " removido.");
				removido = true;
				break;
			}
		}

		if (!removido) {
			System.out.println("\nRemoção falhou: Perfil com ID " + id + " não encontrado.");
		}
	}

	// ==========================================================
	// MÉTODO 8: Main para demonstrar a execução
	// ==========================================================
	public static void main(String[] args) {
		GerenciadorPerfis crud = new GerenciadorPerfis();

		// C - CREATE
		// ID 1
		Perfil p1 = new Perfil(1, "João Admin", NivelAcesso.ADMINISTRADOR, StatusPerfil.ATIVO, TipoNotificacao.EMAIL,
				Departamento.TI, Regiao9.SUDESTE, Permissao.DELECAO);
		crud.adicionar(p1);

		// ID 2
		Perfil p2 = new Perfil(2, "Maria User", NivelAcesso.PADRAO, StatusPerfil.INATIVO, TipoNotificacao.PUSH,
				Departamento.MARKETING, Regiao9.SUL, Permissao.LEITURA);
		crud.adicionar(p2);

		// ID 3
		Perfil p3 = new Perfil(3, "Carlos Mod", NivelAcesso.MODERADOR, StatusPerfil.EM_ANALISE, TipoNotificacao.SMS,
				Departamento.RH, Regiao9.NORDESTE, Permissao.ESCRITA);
		crud.adicionar(p3);

		// Exemplo de instanceof com um objeto inválido (não é Perfil)
		crud.adicionar(new EntidadeBase(4) {
			@Override
			public String exibirDetalhes() {
				return "Entidade Genérica";
			}
		});

		// R - READ (Listagem)
		crud.listarTodos("simples");
		crud.listarTodos("detalhada");

		// BUSCA (R) com do-while
		crud.buscarPorId(2);
		crud.buscarPorId(99);

		// ORDENAÇÃO
		crud.ordenarPerfis();

		// SINCRONIZAÇÃO
		crud.sincronizarAcesso();

		// U - UPDATE com if/else if/else
		crud.atualizarStatus(2, StatusPerfil.ATIVO);
		crud.atualizarStatus(2, StatusPerfil.ATIVO); // Testa o else if

		// D - DELETE
		crud.removerPorId(3);

		// R - READ (Após remoção)
		crud.listarTodos("simples");
	}
}

class Import_1WM {
	public static void run_Import_1WM() {
		System.err.println("\t\t1.\n");
		GerenciadorPerfis.main(new String[1]);

	}
}

//Corpo da Classe _______________________________

//---------------- ENUMS -----------------
enum TipoBanco {
	PUBLICO("Público"), PRIVADO("Privado"), DIGITAL("Digital"), COOPERATIVO("Cooperativo"),
	INVESTIMENTO("Investimento"), INTERNACIONAL("Internacional");

	private String descricao;

	TipoBanco(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}

enum Moeda {
	BRL("Real"), USD("Dólar"), EUR("Euro"), GBP("Libra"), JPY("Iene"), BTC("Bitcoin");

	private String nome;

	Moeda(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
}

enum NivelFiscal {
	BAIXO(1), MEDIO(2), ALTO(3), CRITICO(4), EMERGENCIA(5), NORMAL(0);

	private int nivel;

	NivelFiscal(int nivel) {
		this.nivel = nivel;
	}

	public int getNivel() {
		return nivel;
	}
}

enum StatusBanco {
	ATIVO, INATIVO, SOB_INVESTIGACAO, FALIDO, PENDENTE, EM_RECUPERACAO;
}

enum TipoTransacao9 {
	DEPOSITO, SAQUE, TRANSFERENCIA, AUDITORIA, INVESTIMENTO, CONVERSAO;

	public void imprimirTipo() {
		System.out.println("Tipo de transação executada: " + this.name());
	}
}

enum RegiaoBanco {
	NORTE, NORDESTE, CENTRO_OESTE, SUDESTE, SUL, INTERNACIONAL;
}

//--------------- CLASSE ABSTRATA ----------------
abstract class BancoCentralAbstract {
	protected String nome;
	protected TipoBanco tipo;
	protected Moeda moedaBase;
	protected double reservas;

	public BancoCentralAbstract(String nome, TipoBanco tipo, Moeda moedaBase, double reservas) {
		this.nome = nome;
		this.tipo = tipo;
		this.moedaBase = moedaBase;
		this.reservas = reservas;
	}

	public abstract void executarAcaoEspecial();

	// Switch com ações especiais
	public void acaoPorStatus(StatusBanco status) {
		switch (status) {
		case ATIVO -> System.out.println(nome + " está operando normalmente.");
		case SOB_INVESTIGACAO -> System.out.println(nome + " está sob investigação do Banco Central!");
		case FALIDO -> System.out.println(nome + " foi declarado falido.");
		case EM_RECUPERACAO -> System.out.println(nome + " está em processo de recuperação judicial.");
		default -> System.out.println("Status indefinido para " + nome);
		}
	}

	@Override
	public String toString() {
		return nome + " | Tipo: " + tipo.getDescricao() + " | Moeda: " + moedaBase.getNome() + " | Reservas: "
				+ reservas;
	}
}

//--------------- CLASSE CONCRETA ----------------
class BancoNacional extends BancoCentralAbstract {
	public BancoNacional(String nome, TipoBanco tipo, Moeda moedaBase, double reservas) {
		super(nome, tipo, moedaBase, reservas);
	}

	@Override
	public void executarAcaoEspecial() {
		System.out.println("Banco " + nome + " realiza operação de liquidez no mercado.");
	}
}

//--------------- CLASSE PRINCIPAL ----------------
class BancoCentralSystem {

	private static final LinkedHashSet<BancoCentralAbstract> bancos = new LinkedHashSet<>();
	private static final ReentrantLock lock = new ReentrantLock();

	// 1️⃣ Método de Adição (sincronizado)
	public synchronized void adicionarBanco(BancoCentralAbstract banco) {
		bancos.add(banco);
		System.out.println("✅ Banco adicionado: " + banco.nome);
	}

	// 2️⃣ Método de Remoção (sincronizado)
	public synchronized void removerBanco(String nome) {
		bancos.removeIf(b -> b.nome.equalsIgnoreCase(nome));
		System.out.println("❌ Banco removido: " + nome);
	}

	// 3️⃣ Método de Exibição
	public void exibirBancos() {
		System.out.println("\n📊 Lista de Bancos Registrados no Sistema:");
		bancos.forEach(System.out::println);
	}

	// 4️⃣ Método de Busca
	public void buscarBanco(String nome) {
		for (BancoCentralAbstract b : bancos) {
			if (b.nome.equalsIgnoreCase(nome)) {
				System.out.println("🔍 Banco encontrado: " + b);
				return;
			}
		}
		System.out.println("⚠️ Banco não encontrado: " + nome);
	}

	// 5️⃣ Método de Ordenação
	public void ordenarPorReservas() {
		List<BancoCentralAbstract> lista = new ArrayList<>(bancos);
		lista.sort(Comparator.comparingDouble(b -> b.reservas));
		System.out.println("\n🏦 Bancos ordenados por reservas:");
		lista.forEach(System.out::println);
	}

	// 6️⃣ Método de Sincronização com Lock
	public void sincronizarSistema() {
		lock.lock();
		try {
			System.out.println("🔒 Sincronizando dados bancários com o Banco Central...");
			Thread.sleep(500);
			System.out.println("✅ Sincronização concluída com sucesso!");
		} catch (InterruptedException e) {
			System.err.println("Erro de sincronização.");
		} finally {
			lock.unlock();
		}
	}

	// 7️⃣ Método instanceof
	public void verificarTipo(Object obj) {
		if (obj instanceof BancoCentralAbstract) {
			System.out.println("O objeto é uma instância válida de BancoCentralAbstract.");
		} else {
			System.out.println("O objeto não pertence ao tipo esperado.");
		}
	}

	// 8️⃣ Método para calcular total de reservas
	public double calcularTotalReservas() {
		return bancos.stream().mapToDouble(b -> b.reservas).sum();
	}

	// 9️⃣ Método para criar novo banco interativamente (com verificação de
	// duplicados)
	public void criarBanco() {
		Scanner sc = new Scanner(System.in);
		try {
			System.out.println("\n🆕 --- Criação de Novo Banco ---");
			System.out.print("Nome do Banco: ");
			String nome = sc.nextLine().trim();

			// 🔒 Verificação de duplicidade
			boolean existe = false;
			for (BancoCentralAbstract b : bancos) {
				if (b.nome.equalsIgnoreCase(nome)) {
					existe = true;
					break;
				}
			}

			if (existe) {
				System.out.println("⚠️ Já existe um banco com esse nome no sistema. Escolha outro nome.");
				return;
			}

			System.out.println("Escolha o Tipo de Banco:");
			int i = 1;
			for (TipoBanco t : TipoBanco.values()) {
				System.out.println(i++ + ". " + t.getDescricao());
			}
			System.out.print("Opção: ");
			int tipoEscolhido = Integer.parseInt(sc.nextLine());

			TipoBanco tipo = TipoBanco.values()[tipoEscolhido - 1];

			System.out.println("\nEscolha a Moeda Base:");
			int j = 1;
			for (Moeda m : Moeda.values()) {
				System.out.println(j++ + ". " + m.getNome());
			}
			System.out.print("Opção: ");
			int moedaEscolhida = Integer.parseInt(sc.nextLine());
			Moeda moeda = Moeda.values()[moedaEscolhida - 1];

			System.out.print("\nDigite o valor das reservas: ");
			double reservas = Double.parseDouble(sc.nextLine());

			// 🔍 Validação com if / else if
			if (reservas < 0) {
				System.out.println("❌ Valor inválido. Reservas não podem ser negativas.");
				return;
			} else if (reservas == 0) {
				System.out.println("⚠️ Reservas zeradas. O banco será criado, mas em alerta.");
			} else if (reservas > 10000000) {
				System.out.println("💰 Reservas extremamente altas! Banco de grande porte detectado.");
			}

			BancoNacional novoBanco = new BancoNacional(nome, tipo, moeda, reservas);

			// 🔒 Adiciona banco de forma sincronizada
			adicionarBanco(novoBanco);

			// 🔁 Switch case para ação do tipo de banco
			switch (tipo) {
			case PUBLICO -> System.out.println("🏛 Banco Público registrado com prioridade nacional.");
			case PRIVADO -> System.out.println("🏦 Banco Privado aprovado para operação interna.");
			case DIGITAL -> System.out.println("💻 Banco Digital cadastrado para operação online.");
			case INVESTIMENTO -> System.out.println("📈 Banco de Investimento sob supervisão do Banco Central.");
			case INTERNACIONAL -> System.out.println("🌍 Banco Internacional aprovado para operações cambiais.");
			default -> System.out.println("🏛 Banco cadastrado com classificação geral.");
			}

		} catch (NumberFormatException e) {
			System.err.println("❗ Erro de formato numérico. Digite apenas números válidos.");
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("❗ Opção de tipo ou moeda inválida. Escolha um número do menu.");
		} catch (Exception e) {
			System.err.println("❗ Erro inesperado ao criar banco: " + e.getMessage());
		}
	}

	// ---------------- MAIN -----------------
	public static void main(String[] args) {
		BancoCentralSystem sistema = new BancoCentralSystem();
		Scanner sc = new Scanner(System.in);

		// Adiciona alguns bancos
		sistema.adicionarBanco(new BancoNacional("Banco do Brasil", TipoBanco.PUBLICO, Moeda.BRL, 1000000));
		sistema.adicionarBanco(new BancoNacional("Itaú", TipoBanco.PRIVADO, Moeda.BRL, 850000));
		sistema.adicionarBanco(new BancoNacional("Inter", TipoBanco.DIGITAL, Moeda.BRL, 300000));

		int opcao = 0;
		do {
			System.out.println("\n===== BANCO CENTRAL - MENU =====");
			System.out.println("0. Criar novo banco"); // nova opção
			System.out.println("1. Exibir bancos");
			System.out.println("2. Buscar banco");
			System.out.println("3. Remover banco");
			System.out.println("4. Ordenar por reservas");
			System.out.println("5. Sincronizar sistema");
			System.out.println("6. Verificar tipo");
			System.out.println("7. Mostrar total de reservas");
			System.out.println("8. Executar ação especial");
			System.out.println("9. Sair");
			System.out.print("Escolha: ");

			try {
				opcao = Integer.parseInt(sc.nextLine());

				switch (opcao) {
				case 0 -> sistema.criarBanco(); // chama o método novo
				case 1 -> sistema.exibirBancos();
				case 2 -> {
					System.out.print("Digite o nome: ");
					sistema.buscarBanco(sc.nextLine());
				}
				case 3 -> {
					System.out.print("Nome do banco a remover: ");
					sistema.removerBanco(sc.nextLine());
				}
				case 4 -> sistema.ordenarPorReservas();
				case 5 -> sistema.sincronizarSistema();
				case 6 -> sistema.verificarTipo(new BancoNacional("Teste", TipoBanco.INTERNACIONAL, Moeda.USD, 500000));
				case 7 -> System.out.println("💰 Total de Reservas: " + sistema.calcularTotalReservas());
				case 8 -> {
					for (BancoCentralAbstract b : bancos) {
						b.executarAcaoEspecial();
					}
				}
				case 9 -> System.out.println("Encerrando o sistema...");
				default -> System.out.println("Opção inválida.");
				}
			} catch (NumberFormatException e) {
				System.err.println("❗ Entrada inválida, digite apenas números.");
			}
		} while (opcao != 9);

		// 🚫 NÃO use sc.close() se for continuar lendo System.in depois!
		if (sc != null) {
			sc = null; // libera o scanner sem fechar System.in
		}

	}
}

class Import_2WM {
	public static void run_Import_2WM() {
		System.err.println("\t\t2.\n");
		BancoCentralSystem.main(new String[2]);
	}
}

//Corpo da Classe _______________________________
//======================= ENUMS ===========================
enum TipoPorta {
	ENTRADA(80), SAIDA(443), ADMIN(22), FTP(21), DNS(53), SMTP(25);

	private int numero;

	TipoPorta(int numero) {
		this.numero = numero;
	}

	public int getNumero() {
		return numero;
	}

	public void infoPorta() {
		System.out.println("🔹 Porta " + name() + " número: " + numero);
	}
}

enum NivelAcesso7 {
	PUBLICO, RESTRITO, INTERNO, ROOT, EXTERNO, DESCONHECIDO;

	public static NivelAcesso7 avaliar(int valor) {
		if (valor < 3)
			return PUBLICO;
		else if (valor < 5)
			return RESTRITO;
		else if (valor == 5)
			return ROOT;
		else
			return DESCONHECIDO;
	}
}

enum TipoEndereco {
	IPV4, IPV6, LOCALHOST, REDE_PRIVADA, REDE_PUBLICA, DESCONHECIDO;

	public static TipoEndereco detectar(String ip) {
		if (ip.startsWith("192."))
			return REDE_PRIVADA;
		else if (ip.contains(":"))
			return IPV6;
		else if (ip.equals("127.0.0.1"))
			return LOCALHOST;
		else if (ip.contains("."))
			return IPV4;
		else
			return DESCONHECIDO;
	}
}

enum StatusConexao {
	ATIVA, INATIVA, BLOQUEADA, EM_ANALISE, DESCONHECIDA, ENCERRADA;

	public void printStatus() {
		System.out.println("📡 Status atual: " + name());
	}
}

enum TipoOrdenacao {
	ASC, DESC, RANDOM, NENHUMA, ALFABETICA, TAMANHO;

	public void aplicarOrdem(List<String> lista) {
		switch (this) {
		case ASC -> Collections.sort(lista);
		case DESC -> {
			Collections.sort(lista);
			Collections.reverse(lista);
		}
		case RANDOM -> Collections.shuffle(lista);
		case ALFABETICA -> lista.sort(Comparator.comparing(String::toString));
		case TAMANHO -> lista.sort(Comparator.comparingInt(String::length));
		default -> System.out.println("Sem ordenação definida");
		}
	}
}

enum TipoBusca {
	IP, PORTA, STATUS, ACESSO, ENDERECO, NENHUM;

	public boolean buscar(String alvo, String termo) {
		return alvo.contains(termo);
	}
}

//======================= CLASSE ABSTRATA ===========================
abstract class ConexaoRede {
	protected String ip;
	protected TipoPorta porta;
	protected StatusConexao status;
	protected NivelAcesso7 nivel;

	public ConexaoRede(String ip, TipoPorta porta, StatusConexao status, NivelAcesso7 restrito) {
		this.ip = ip;
		this.porta = porta;
		this.status = status;
		this.nivel = restrito;
	}

	public abstract void monitorar();

	// switch-case abstrato implementado
	public void diagnostico() {
		switch (status) {
		case ATIVA -> System.out.println("✅ Conexão ativa em: " + ip + " porta " + porta.getNumero());
		case BLOQUEADA -> System.out.println("🚫 Porta bloqueada: " + porta.name());
		case EM_ANALISE -> System.out.println("🔍 Em análise de tráfego: " + ip);
		default -> System.out.println("⚠️ Status desconhecido...");
		}
	}
}

//======================= CLASSE CONCRETA ===========================
class LogConexao extends ConexaoRede {

	private TipoEndereco tipoEndereco; // guarda o tipo detectado
	private LocalDateTime dataRegistro; // momento do log

	// Construtor
	public LogConexao(String ip, TipoPorta porta, StatusConexao status, NivelAcesso7 restrito) {
		super(ip, porta, status, restrito);
		this.tipoEndereco = TipoEndereco.detectar(ip);
		this.dataRegistro = LocalDateTime.now();
	}

	// Método sincronizado
	@Override
	public synchronized void monitorar() {
		System.out.println("📡 Monitorando conexão:");
		System.out.println("   IP..........: " + ip);
		System.out.println("   Tipo Endereço: " + tipoEndereco.name());
		System.out.println("   Porta.......: " + porta.getNumero() + " (" + porta.name() + ")");
		System.out.println("   Status......: " + status.name());
		System.out.println("   Nível Acesso: " + nivel.name());
		System.out.println("   Data Log....: " + dataRegistro);

		// ======= instanceof e verificação lógica =======
		Object objeto = this; // simulando uma checagem de tipo

		if (objeto instanceof LogConexao log) {
			System.out.println("   ✅ Verificação: objeto é instância de LogConexao");
			if (log.tipoEndereco == TipoEndereco.LOCALHOST) {
				System.out.println("   💻 Conexão local detectada (127.0.0.1)");
			} else if (log.tipoEndereco == TipoEndereco.IPV4) {
				System.out.println("   🌐 IPv4 detectado - Conexão padrão.");
			} else if (log.tipoEndereco == TipoEndereco.IPV6) {
				System.out.println("   🧬 IPv6 detectado - Rede avançada.");
			} else if (log.tipoEndereco == TipoEndereco.REDE_PRIVADA) {
				System.out.println("   🏠 Rede Privada detectada (LAN).");
			} else if (log.tipoEndereco == TipoEndereco.REDE_PUBLICA) {
				System.out.println("   🚀 Rede Pública detectada (Internet).");
			} else {
				System.out.println("   ⚠️ Tipo de endereço desconhecido.");
			}
		} else {
			System.out.println("   ❌ Objeto não é uma instância de LogConexao!");
		}

		System.out.println("----------------------------------------------");
	}
}

//======================= CLASSE PRINCIPAL ===========================
class PortaEntradaSaida {

	private static final LinkedHashSet<LogConexao> registros = new LinkedHashSet<>();
	private static final ReentrantLock lock = new ReentrantLock();

	// 1️⃣ Método de inserção (com sincronização)
	public static synchronized void adicionarConexao(LogConexao log) {
		registros.add(log);
		System.out.println("➕ Log adicionado: " + log.ip);
	}

	// 2️⃣ Método de busca
	public static void buscarPorIP(String termo) {
		System.out.println("\n🔍 Buscando conexões com termo: " + termo);
		for (LogConexao log : registros) {
			if (log.ip.contains(termo)) {
				System.out.println("Encontrado -> " + log.ip + " | Porta " + log.porta);
			}
		}
	}

	// 3️⃣ Método de ordenação por IP
	public static void ordenarIPs() {
		List<String> lista = new ArrayList<>();
		for (LogConexao log : registros)
			lista.add(log.ip);

		TipoOrdenacao.ASC.aplicarOrdem(lista);
		System.out.println("\n📑 IPs Ordenados: " + lista);
	}

	// 4️⃣ Método de sincronização (manual com lock)
	public static void sincronizar() {
		lock.lock();
		try {
			System.out.println("🔄 Sincronizando dados da rede...");
			Thread.sleep(300);
		} catch (InterruptedException e) {
			System.err.println("Erro de sincronização.");
		} finally {
			lock.unlock();
		}
	}

	// 5️⃣ Método instanceof (para validar tipo de objeto)
	public static void verificarInstancia(Object obj) {
		if (obj instanceof LogConexao) {
			System.out.println("✔ Objeto é uma instância de LogConexao.");
		} else {
			System.out.println("❌ Objeto não pertence à classe esperada.");
		}
	}

	// 6️⃣ Método de exibição com for e if/else if
	public static void exibirRegistros() {
		System.out.println("\n📋 Registros atuais:");
		for (LogConexao log : registros) {
			if (log.status == StatusConexao.ATIVA)
				System.out.println("🟢 " + log.ip);
			else if (log.status == StatusConexao.BLOQUEADA)
				System.out.println("🔴 " + log.ip);
			else if (log.status == StatusConexao.EM_ANALISE)
				System.out.println("🟡 " + log.ip);
			else
				System.out.println("⚪ " + log.ip);
		}
	}

	// 7️⃣ Método com try-catch
	public static void executarMonitoramento() {
		try {
			for (LogConexao log : registros) {
				log.monitorar();
			}
		} catch (Exception e) {
			System.err.println("Erro ao monitorar conexões.");
		}
	}

	// 8️⃣ Método com do-while e switch dentro
	public static void menuOperacoes() {
		Scanner sc = new Scanner(System.in);
		int opcao;
		do {
			System.out.println("\n=== MENU DE OPERAÇÕES ===");
			System.out.println("1 - Exibir Registros");
			System.out.println("2 - Buscar IP");
			System.out.println("3 - Ordenar IPs");
			System.out.println("4 - Sincronizar");
			System.out.println("0 - Sair");
			System.out.print("Escolha: ");
			opcao = sc.nextInt();

			switch (opcao) {
			case 1 -> exibirRegistros();
			case 2 -> buscarPorIP("192");
			case 3 -> ordenarIPs();
			case 4 -> sincronizar();
			case 0 -> System.out.println("Encerrando...");
			default -> System.out.println("Opção inválida!");
			}

		} while (opcao != 0);

		// 🚫 NÃO use sc.close() se for continuar lendo System.in depois!
		if (sc != null) {
			sc = null; // libera o scanner sem fechar System.in
		}

	}

	// ======================= MAIN ===========================
	public static void main(String[] args) {
		System.out.println("💾 Sistema de Porta de Entrada e Saída - Monitor de IP\n");

		adicionarConexao(new LogConexao("192.168.0.1", TipoPorta.ENTRADA, StatusConexao.ATIVA, NivelAcesso7.RESTRITO));
		adicionarConexao(new LogConexao("10.0.0.15", TipoPorta.ADMIN, StatusConexao.BLOQUEADA, NivelAcesso7.ROOT));
		adicionarConexao(new LogConexao("127.0.0.1", TipoPorta.SAIDA, StatusConexao.EM_ANALISE, NivelAcesso7.PUBLICO));

		verificarInstancia(registros.iterator().next());
		executarMonitoramento();
		menuOperacoes();
	}
}

class Import_3WM {
	public static void run_Import_3WM() {
		System.err.println("\t\t3. \n");
		PortaEntradaSaida.main(new String[3]);
	}
}
