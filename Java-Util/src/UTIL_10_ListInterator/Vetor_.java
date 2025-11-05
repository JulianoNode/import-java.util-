package UTIL_10_ListInterator;

import util.Linhas;
import util.VoutarRun;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Vetor_ {
	public static void Vetor_Run() {

		Linhas linhas = new Linhas();
		String collec = "\n\t ListIterator<E>: Iterador para listas que permite percorrer em ambas as direções. \n";
		System.err.println(collec);

		linhas.run_Caracteres();
		Import_1V.run_Import_1V();

		linhas.run_Caracteres();
		Import_2V.run_Import_2V();

		linhas.run_Caracteres();
		Import_3V.run_Import_3V();

		linhas.run_Caracteres();
		Import_3VP.run_Import_3VP();
	}
}

//Corpo da Classe _______________________________
enum NivelAcessof {
	ADMIN, SUPERVISOR, TRABALHADOR, GERENTE, ENCARREGADO;

	public void descricao() {
		switch (this) {
		case ADMIN:
			System.out.println("Acesso total ao sistema.");
			break;
		case SUPERVISOR:
			System.out.println("Acesso para gerenciar equipes.");
			break;
		case TRABALHADOR:
			System.out.println("Acesso limitado ao desempenho das tarefas.");
			break;
		case GERENTE:
			System.out.println("Acesso para gerenciar projetos.");
			break;
		case ENCARREGADO:
			System.out.println("Acesso para supervisionar equipes de trabalho.");
			break;
		default:
			System.out.println("Acesso desconhecido.");
			break;
		}
	}
}

enum Cargof {
	PEDREIRO, ELETRICISTA, ENGENHEIRO, ENCANADOR, MECANICO;

	public void tarefa() {
		switch (this) {
		case PEDREIRO:
			System.out.println("Responsável pela construção e reparos de alvenarias.");
			break;
		case ELETRICISTA:
			System.out.println("Responsável pela instalação e manutenção elétrica.");
			break;
		case ENGENHEIRO:
			System.out.println("Responsável pelo planejamento e execução de obras.");
			break;
		case ENCANADOR:
			System.out.println("Responsável pela instalação e manutenção hidráulica.");
			break;
		case MECANICO:
			System.out.println("Responsável por manutenção e reparo de máquinas.");
			break;
		default:
			System.out.println("Cargo desconhecido.");
			break;
		}
	}
}

abstract class Funcionariof {
	String nome;
	NivelAcessof nivelAcesso;
	Cargof cargo;

	Funcionariof(String nome, NivelAcessof nivelAcesso, Cargof cargo) {
		this.nome = nome;
		this.nivelAcesso = nivelAcesso;
		this.cargo = cargo;
	}

	public abstract void realizarTarefa();
}

class Trabalhador extends Funcionariof {
	Trabalhador(String nome, NivelAcessof nivelAcesso, Cargof cargo) {
		super(nome, nivelAcesso, cargo);
	}

	@Override
	public void realizarTarefa() {
		System.out.println(nome + " está realizando sua tarefa de " + cargo);
	}
}

class Gerente extends Funcionariof {
	Gerente(String nome, NivelAcessof nivelAcesso, Cargof cargo) {
		super(nome, nivelAcesso, cargo);
	}

	@Override
	public void realizarTarefa() {
		System.out.println(nome + " está gerenciando as operações da obra.");
	}
}

class Supervisor extends Funcionariof {
	Supervisor(String nome, NivelAcessof nivelAcesso, Cargof cargo) {
		super(nome, nivelAcesso, cargo);
	}

	@Override
	public void realizarTarefa() {
		System.out.println(nome + " está supervisionando a equipe.");
	}
}

class SistemaLoginObra {
	private static List<Funcionariof> funcionarios = new ArrayList<>();
	private static final Scanner scanner = new Scanner(System.in);

	public static void main_F() {
		// Inicialização de alguns funcionários
		funcionarios.add(new Trabalhador("José", NivelAcessof.TRABALHADOR, Cargof.PEDREIRO));
		funcionarios.add(new Gerente("Maria", NivelAcessof.GERENTE, Cargof.ENGENHEIRO));
		funcionarios.add(new Supervisor("Carlos", NivelAcessof.SUPERVISOR, Cargof.ELETRICISTA));
		funcionarios.add(new Supervisor("Juliano", NivelAcessof.ADMIN, Cargof.ENGENHEIRO));

		// Menu de Login
		loginFuncionario();
	}

	private static void loginFuncionario() {
		System.out.println("Bem-vindo ao sistema de login da obra.");
		System.out.print("Digite seu nome de usuário: ");
		String usuario = scanner.nextLine();
		System.out.print("Digite sua senha: ");
		String senha = scanner.nextLine();

		// Simulação de verificação de login
		if (usuario.equals("José") && senha.equals("1234")) {
			autenticarFuncionario(usuario);
		} else if (usuario.equals("Maria") && senha.equals("abcd")) {
			autenticarFuncionario(usuario);
		} else if (usuario.equals("Carlos") && senha.equals("senha123")) {
			autenticarFuncionario(usuario);
		} else if (usuario.equals("Juliano") && senha.equals("123")) {
			autenticarFuncionario(usuario);
		} else {
			System.out.println("Login falhou. Usuário ou senha incorretos.");
		}
	}

	private static void autenticarFuncionario(String usuario) {
		System.out.println("Login bem-sucedido! Bem-vindo, " + usuario);
		// Simulando o acesso aos dados do funcionário
		for (Funcionariof f : funcionarios) {
			if (f.nome.equals(usuario)) {
				f.nivelAcesso.descricao();
				f.cargo.tarefa();
				break;
			}
		}

		// Usando ListIterator para navegar entre os funcionários
		ListIterator<Funcionariof> iterator = funcionarios.listIterator();
		while (iterator.hasNext()) {
			Funcionariof f = iterator.next();
			if (f.nome.equals(usuario)) {
				f.realizarTarefa();
			}
		}

		// Implementando sincronização
		synchronized (funcionarios) {
			System.out.println("Processamento sincronizado para " + usuario);
		}
	}
}

class Import_1V {
	public static void run_Import_1V() {
		System.err.println("\t\t1. \n");
		SistemaLoginObra.main_F();
	}
}

//Corpo da Classe _______________________________

class ClienteBanco {

	// Enum de Nível de Acesso
	public enum NivelAcesso {
		CLIENTE(1), GERENTE(2), ADMIN(3);

		private final int nivel;

		NivelAcesso(int nivel) {
			this.nivel = nivel;
		}

		public int getNivel() {
			return this.nivel;
		}

		// Funções de Enum
		public static void verificaNivel(NivelAcesso nivel) {
			switch (nivel) {
			case CLIENTE:
				System.out.println("Acesso Cliente - Apenas visualização");
				break;
			case GERENTE:
				System.out.println("Acesso Gerente - Acesso a relatórios");
				break;
			case ADMIN:
				System.out.println("Acesso Admin - Acesso completo");
				break;
			}
		}

		public static void listarNiveis() {
			for (NivelAcesso nivel : NivelAcesso.values()) {
				System.out.println(nivel + ": " + nivel.getNivel());
			}
		}

		public static boolean validaNivel(int nivel) {
			return nivel >= 1 && nivel <= 3;
		}
	}

	// Classe Abstrata para Cliente e Gerente
	abstract static class Usuario {
		String nome;
		String senha;

		abstract void login(String senha);
	}

	// Classe Cliente
	static class Cliente extends Usuario {
		private NivelAcesso acesso;

		Cliente(String nome, String senha, NivelAcesso acesso) {
			this.nome = nome;
			this.senha = senha;
			this.acesso = acesso;
		}

		@Override
		void login(String senha) {
			if (this.senha.equals(senha)) {
				System.out.println("Login do cliente " + nome + " bem-sucedido!");
				NivelAcesso.verificaNivel(acesso);
			} else {
				System.out.println("Senha incorreta!");
			}
		}
	}

	// Classe Gerente
	static class Gerente extends Usuario {
		private NivelAcesso acesso;

		Gerente(String nome, String senha, NivelAcesso acesso) {
			this.nome = nome;
			this.senha = senha;
			this.acesso = acesso;
		}

		@Override
		void login(String senha) {
			if (this.senha.equals(senha)) {
				System.out.println("Login do gerente " + nome + " bem-sucedido!");
				NivelAcesso.verificaNivel(acesso);
			} else {
				System.out.println("Senha incorreta!");
			}
		}
	}

	// Classe de Sincronização
	static class SincronizacaoBanco {
		private static final Object lock = new Object();

		public static void sincronizar() {
			synchronized (lock) {
				System.out.println("Sincronização em andamento...");
				try {
					Thread.sleep(1000); // Simula um atraso na sincronização
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Sincronização concluída.");
			}
		}
	}

	public static void main_b() {
		// Criando usuários
		Cliente cliente1 = new Cliente("João", "senha123", NivelAcesso.CLIENTE);
		Gerente gerente1 = new Gerente("Carlos", "admin123", NivelAcesso.GERENTE);

		// Vetor de usuários
		List<Usuario> usuarios = new ArrayList<>();
		usuarios.add(cliente1);
		usuarios.add(gerente1);

		// Exibindo níveis de acesso
		NivelAcesso.listarNiveis();

		// Testando login
		Scanner scanner = new Scanner(System.in);
		System.out.print("Digite o nome do usuário: ");
		String nome = scanner.nextLine();
		System.out.print("Digite a senha: ");
		String senha = scanner.nextLine();

		// Usando ListIterator para percorrer usuários
		ListIterator<Usuario> iterador = usuarios.listIterator();
		boolean usuarioEncontrado = false;

		while (iterador.hasNext()) {
			Usuario usuario = iterador.next();
			if (usuario.nome.equals(nome)) {
				usuario.login(senha);
				usuarioEncontrado = true;
				break;
			}
		}

		if (!usuarioEncontrado) {
			System.out.println("Usuário não encontrado.");
		}

		// Exemplo de loop for com matriz
		String[][] relatorios = { { "Relatório1", "2023-03-22" }, { "Relatório2", "2023-03-23" } };
		System.out.println("\nRelatórios Disponíveis:");
		for (int i = 0; i < relatorios.length; i++) {
			System.out.println("Nome: " + relatorios[i][0] + ", Data: " + relatorios[i][1]);
		}

		// Exemplo de uso de do-while
		int tentativa = 0;
		boolean sucesso = false;
		do {
			System.out.print("\nDigite sua senha para acessar o banco: ");
			String senhaLogin = scanner.nextLine();
			if (senhaLogin.equals("admin123")) {
				System.out.println("Login realizado com sucesso!");
				sucesso = true;
			} else {
				System.out.println("Senha incorreta. Tente novamente.");
			}
			tentativa++;
		} while (!sucesso && tentativa < 3);

		if (!sucesso) {
			System.out.println("Número máximo de tentativas excedido.");
		}

		// Exemplo de sincronização
		SincronizacaoBanco.sincronizar();

		VoutarRun voutarRun = new VoutarRun();
		voutarRun.run_CaracteresMenu_Red();
	}
}

class Import_2V {
	public static void run_Import_2V() {
		System.err.println("\t\t2. \n");
		ClienteBanco.main_b();
	}
}

//Corpo da Classe _______________________________

abstract class CartaoCredito {
	protected String numero;
	protected double saldo;
	protected int parcelas;

	public CartaoCredito(String numero, double saldo, int parcelas) {
		this.numero = numero;
		this.saldo = saldo;
		this.parcelas = parcelas;
	}

	public abstract void mostrarDetalhes();
}

enum NivelAcessoc {
	BAIXO, MEDIO, ALTO;

	public void exibirNivelAcesso() {
		switch (this) {
		case BAIXO:
			System.out.println("Acesso baixo - Apenas transações pequenas");
			NivelAcessoc.parcelarCredito(0, 0);
			break;
		case MEDIO:
			System.out.println("Acesso médio - Transações de valor médio");
			NivelAcessoc.parcelarCredito(0, 0);
			break;
		case ALTO:
			System.out.println("Acesso alto - Permite transações de grandes valores");
			NivelAcessoc.parcelarCredito(0, 0);
			break;
		}
	}

	// Função de enum com lógica de parcelamento
	public static void parcelarCredito(double saldo, int parcelas) {
		if (parcelas <= 12 && parcelas > 0) {
			double valorParcela = saldo / parcelas;
			System.out.println("Parcelamento em " + parcelas + " vezes: R$ " + valorParcela);
		} else {
			System.out.println("Número de parcelas inválido! O máximo é 12.");
		}
	}
}

enum TipoCartao {
	COMUM, PREMIUM, BLACK, GOLD;

	// Função de enum de tipo de cartão
	public void detalhesCartao() {
		switch (this) {
		case COMUM:
			System.out.println("Cartão Comum: Limite padrão de R$ 3.000,00");
			System.out.println("Cartão Comum: Benefícios limitados.");
			System.out.println("Cartão Comum: Geralmente sem anuidade ou com anuidade baixa.");
			System.out.println("Cartão Comum: Limite de crédito baixo.");
			break;
		case PREMIUM:
			System.out.println("\nCartão Premium: Limite elevado de R$ 10.000,00");
			System.out.println(
					"Cartão Premium: Benefícios exclusivos como acesso a salas VIP em aeroportos, seguros de viagem, "
							+ "e programas de recompensas mais vantajosos.");
			System.out.println("Cartão Premium: Limite de crédito elevado");
			System.out.println("Cartão Premium: Alta exigência de crédito.");
			break;
		case BLACK:
			System.out.println("\nCartão Black: Limite elevado de R$ 30.000,00");
			System.out.println("Cartão Black: Cartões de alto padrão, com acesso a uma gama exclusiva de benefícios "
					+ "(ex.: concierge pessoal, viagens de luxo).\r\n");
			System.out.println("Cartão Black: Limite de crédito muito alto.");
			System.out.println("Cartão Black: Requer uma excelente pontuação de crédito e renda alta.");

			break;
		case GOLD:
			System.out.println("\n10" + "Cartão Gold: Limite elevado de R$ 150.000,00");
			System.out.println("Cartão Gold: Benefícios mais avançados que o Classic, como assistência"
					+ " em viagem e proteção de compras.\r\n");
			System.out.println("Cartão Gold: Limite de crédito mais alto.");
			System.out.println("Cartão Gold: Requer uma boa pontuação de crédito.");
			break;
		}
	}
}

abstract class Pagamentos {
	protected double valor;

	public Pagamentos(double valor) {
		this.valor = valor;
	}

	public abstract void processarPagamento();
}

class PagamentoParcelado extends Pagamentos {
	private int parcelas;

	public PagamentoParcelado(double valor, int parcelas) {
		super(valor);
		this.parcelas = parcelas;
	}

	@Override
	public void processarPagamento() {
		double parcela = valor / parcelas;
		System.out.println("Pagamento parcelado em " + parcelas + " vezes de R$ " + parcela);
	}
}

class CartaoDeCreditoComum extends CartaoCredito {
	public CartaoDeCreditoComum(String numero, double saldo, int parcelas) {
		super(numero, saldo, parcelas);
	}

	@Override
	public void mostrarDetalhes() {
		System.out.println("\nCartão Comum:");
		System.out.println("Número: " + numero);
		System.out.println("Saldo disponível: R$ " + saldo);
		NivelAcessoc parcelamento = (saldo > 0) ? NivelAcessoc.ALTO : NivelAcessoc.BAIXO;
		parcelamento.exibirNivelAcesso();
	}
}

class CartaoDeCreditoPremium extends CartaoCredito {
	public CartaoDeCreditoPremium(String numero, double saldo, int parcelas) {
		super(numero, saldo, parcelas);
	}

	@Override
	public void mostrarDetalhes() {
		System.out.println("\nCartão Premium:");
		System.out.println("Número: " + numero);
		System.out.println("Saldo disponível: R$ " + saldo);
		NivelAcessoc parcelamento = (saldo > 5000) ? NivelAcessoc.ALTO : NivelAcessoc.MEDIO;
		parcelamento.exibirNivelAcesso();
	}
}

class CartaoDeCreditoGold extends CartaoCredito {
	public CartaoDeCreditoGold(String numero, double saldo, int parcelas) {
		super(numero, saldo, parcelas);
	}

	@Override
	public void mostrarDetalhes() {
		System.out.println("\nCartão Gold:");
		System.out.println("Número: " + numero);
		System.out.println("Saldo disponível: R$ " + saldo);
		NivelAcessoc parcelamento = (saldo > 15000) ? NivelAcessoc.ALTO : NivelAcessoc.MEDIO;
		parcelamento.exibirNivelAcesso();
	}
}

class CartaoDeCreditoBlack extends CartaoCredito {
	public CartaoDeCreditoBlack(String numero, double saldo, int parcelas) {
		super(numero, saldo, parcelas);
	}

	@Override
	public void mostrarDetalhes() {
		System.out.println("\nCartão Black:");
		System.out.println("Número: " + numero);
		System.out.println("Saldo disponível: R$ " + saldo);
		NivelAcessoc parcelamento = (saldo > 29000) ? NivelAcessoc.ALTO : NivelAcessoc.MEDIO;
		parcelamento.exibirNivelAcesso();
	}
}

class SistemaCartaoCredito {
	private List<CartaoCredito> cartoes = new ArrayList<>();

	public void adicionarCartao(CartaoCredito cartao) {
		cartoes.add(cartao);
	}

	public void exibirDetalhesCartao() {
		ListIterator<CartaoCredito> iterator = cartoes.listIterator();
		while (iterator.hasNext()) {
			iterator.next().mostrarDetalhes();
		}
	}

	public synchronized void processarParcelamento(double valor, int parcelas) {
		NivelAcessoc.parcelarCredito(valor, parcelas);
	}
}

class Main_Parcelas {
	public static void main_Par() {
		CartaoCredito cartaoComum = new CartaoDeCreditoComum("1234 5678 9101 1121", 1500.00, 12);
		CartaoCredito cartaoPremium = new CartaoDeCreditoPremium("2234 5678 9101 1122", 7000.00, 12);
		CartaoCredito cartaoGold = new CartaoDeCreditoGold("1234 5678 9101 1121", 12500.00, 12);
		CartaoCredito cartaoBlack = new CartaoDeCreditoBlack("2234 5678 9101 1122", 27000.00, 12);

		SistemaCartaoCredito sistema = new SistemaCartaoCredito();
		sistema.adicionarCartao(cartaoComum);
		sistema.adicionarCartao(cartaoPremium);
		sistema.adicionarCartao(cartaoGold);
		sistema.adicionarCartao(cartaoBlack);

		// Exibir detalhes dos cartões
		sistema.exibirDetalhesCartao();

		// Processar parcelamento
		sistema.processarParcelamento(1500.00, 12);

		// Lógica de acesso
		NivelAcessoc nivelAcesso = NivelAcessoc.MEDIO;
		nivelAcesso.exibirNivelAcesso();

		TipoCartao tipo = TipoCartao.PREMIUM;
		tipo.detalhesCartao();
	}
}

class Import_3V {
	public static void run_Import_3V() {
		System.err.println("\t\t3. \n");
		Main_Parcelas.main_Par();
	}
}

//Enum para os tipos de cartões de crédito
enum TipoCartaov {
	COMUM(1000), PREMIUM(5000), BLACK(10000), GOLD(20000);

	private final int limite;

	TipoCartaov(int limite) {
		this.limite = limite;
	}

	public int getLimite() {
		return limite;
	}
}

//Enum para níveis de acesso
enum NivelAcessov {
	BAIXO, MEDIO, ALTO, VIP, SUPER_VIP, MASTER, FANTASTICO, EXCLUSIVO, LUXO, ELITE;

	// Funções do Enum - Complexo
	public String getDescricao() {
		switch (this) {
		case BAIXO:
			return "Acesso básico";
		case MEDIO:
			return "Acesso intermediário";
		case ALTO:
			return "Acesso alto";
		case VIP:
			return "Acesso VIP";
		case SUPER_VIP:
			return "Acesso Super VIP";
		case MASTER:
			return "Acesso Master";
		case FANTASTICO:
			return "Acesso Fantástico";
		case EXCLUSIVO:
			return "Acesso Exclusivo";
		case LUXO:
			return "Acesso Luxo";
		case ELITE:
			return "Acesso Elite";
		default:
			return "Acesso não definido";
		}
	}
}

//Classe abstrata representando um cartão de crédito
abstract class CartaoCreditov {
	protected TipoCartaov tipo;
	protected double saldo;
	protected int parcelas;

	public abstract void realizarPagamento(double valor, int parcelas);

	public abstract void consultarLimite();

	public void exibirInfo() {
		System.out.println("Tipo de Cartão: " + tipo);
		System.out.println("Saldo disponível: R$" + saldo);
		System.out.println("Parcelas: " + parcelas);
		consultarLimite();
	}
}

//Classe concreta representando o Cartão de Crédito Premium
class CartaoCreditoPremium extends CartaoCreditov {

	public CartaoCreditoPremium() {
		this.tipo = TipoCartaov.PREMIUM;
		this.saldo = tipo.getLimite();
	}

	@Override
	public void realizarPagamento(double valor, int parcelas) {
		if (valor > saldo) {
			System.out.println("Pagamento não autorizado! Limite insuficiente.");
		} else {
			this.parcelas = parcelas;
			this.saldo -= valor;
			System.out.println("Pagamento realizado em " + parcelas + "x de R$" + valor / parcelas);
		}
	}

	@Override
	public void consultarLimite() {
		System.out.println("Limite de crédito: R$" + tipo.getLimite());
	}

	public void realizarPagamentoSincronizado(int i, int parcela) {

	}
}

//Classe concreta representando o Cartão de Crédito Gold
class CartaoCreditoGold extends CartaoCreditov {

	public CartaoCreditoGold() {
		this.tipo = TipoCartaov.GOLD;
		this.saldo = tipo.getLimite();
	}

	@Override
	public void realizarPagamento(double valor, int parcelas) {
		if (valor > saldo) {
			System.out.println("Pagamento não autorizado! Limite insuficiente.");
		} else {
			this.parcelas = parcelas;
			this.saldo -= valor;
			System.out.println("Pagamento realizado em " + parcelas + "x de R$" + valor / parcelas);
		}
	}

	@Override
	public void consultarLimite() {
		System.out.println("Limite de crédito: R$" + tipo.getLimite());
	}
}

//Interface para sincronização de operações de pagamento
interface PagamentoSincronizado {
	Lock lock = new ReentrantLock();

	default void realizarPagamentoSincronizado(double valor, int parcelas) {
		lock.lock();
		try {
			System.out.println("Iniciando pagamento...");
			realizarPagamento(valor, parcelas);
		} finally {
			lock.unlock();
		}
	}

	void realizarPagamento(double valor, int parcelas);
}

//Classe principal para simulação de uso
class CartaoCreditoMain {

	public static void main_V() {
		// Criação de cartões
		CartaoCreditoPremium cartaoPremium = new CartaoCreditoPremium();
		CartaoCreditoGold cartaoGold = new CartaoCreditoGold();

		// Exibição de informações
		cartaoPremium.exibirInfo();
		cartaoGold.exibirInfo();

		// Testando pagamento
		cartaoPremium.realizarPagamento(1500, 12);
		cartaoGold.realizarPagamento(2000, 12);
		cartaoGold.realizarPagamento(2000, 10);

		// Lista de Níveis de Acesso
		List<NivelAcessov> niveis = Arrays.asList(NivelAcessov.values());

		// Utilizando ListIterator para percorrer os Níveis de Acesso
		ListIterator<NivelAcessov> iterator = niveis.listIterator();
		while (iterator.hasNext()) {
			NivelAcessov acesso = iterator.next();
			System.out.println("Nível de Acesso: " + acesso + " - Descrição: " + acesso.getDescricao());
		}

		// Simulando parcelas
		int[] parcelas = { 12, 6, 3, 1 };
		for (int parcela : parcelas) {
			System.out.println("Parcelamento em " + parcela + "x:");
			cartaoPremium.realizarPagamentoSincronizado(1200, parcela);
		}
	}
}

class Import_3VP {
	public static void run_Import_3VP() {
		System.err.println("\t\t3. \n");
		CartaoCreditoMain.main_V();
	}
}