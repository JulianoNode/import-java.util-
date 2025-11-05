package UTIL_13_HashSet;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import util.Linhas;
import util.VoutarRun;

public class For_Matrix {
	public static void For_Run() {

		Linhas linhas = new Linhas();
		String collec = "\n\t Nome \n";
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
// Enumeração para os perfis de obra
enum PerfilObra {
	PEDREIRO("Pedreiro"), CARPINTEIRO("Carpinteiro"), AZULEJISTA("Azulejista"), PINTOR("Pintor"),
	ENCANADOR("Encanador"), ELETRICISTA("Eletricista"), SERVENTE("Servente");

	private final String descricao;

	PerfilObra(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public static PerfilObra fromString(String text) {
		for (PerfilObra perfil : PerfilObra.values()) {
			if (perfil.descricao.equalsIgnoreCase(text)) {
				return perfil;
			}
		}
		return null; // Retorna null se a string não corresponder a nenhum perfil
	}
}

// Classe abstrata para representar um usuário
abstract class Usuario {
	private String login;
	private String senha;
	private PerfilObra perfil;

	public String getSenha() {
		return senha;
	}

	public Usuario(String login, String senha, PerfilObra perfil) {
		this.login = login;
		this.senha = senha;
		this.perfil = perfil;
	}

	public String getLogin() {
		return login;
	}

	public PerfilObra getPerfil() {
		return perfil;
	}

	// Método abstrato a ser implementado pelas subclasses
	public abstract void realizarAcao();

	// Método sincronizado (um dos requisitos)
	public synchronized void exibirInformacoes() {
		System.out.println("Login: " + login + ", Perfil: " + perfil.getDescricao());
	}

	// Método a ser "emplastado" (simulando uma ação específica do perfil)
	public void executarTarefaEspecifica() {
		switch (perfil) {
		case PEDREIRO:
			System.out.println("Assentando tijolos...");
			break;
		case CARPINTEIRO:
			System.out.println("Construindo estrutura de madeira...");
			break;
		case AZULEJISTA:
			System.out.println("Aplicando revestimento cerâmico...");
			break;
		case PINTOR:
			System.out.println("Pintando a parede...");
			break;
		case ENCANADOR:
			System.out.println("Instalando tubulações...");
			break;
		case ELETRICISTA:
			System.out.println("Realizando instalações elétricas...");
			break;
		case SERVENTE:
			System.out.println("Auxiliando nas tarefas gerais...");
			break;
		default:
			System.out.println("Tarefa não definida para este perfil.");
		}
	}
}

// Subclasses concretas para cada perfil (implementando realizarAcao)
class Pedreiro extends Usuario {
	public Pedreiro(String login, String senha) {
		super(login, senha, PerfilObra.PEDREIRO);
	}

	@Override
	public void realizarAcao() {
		System.out.println("Pedreiro está construindo paredes.");
	}
}

class Carpinteiro extends Usuario {
	public Carpinteiro(String login, String senha) {
		super(login, senha, PerfilObra.CARPINTEIRO);
	}

	@Override
	public void realizarAcao() {
		System.out.println("Carpinteiro está trabalhando na estrutura do telhado.");
	}
}

class Azulejista extends Usuario {
	public Azulejista(String login, String senha) {
		super(login, senha, PerfilObra.AZULEJISTA);
	}

	@Override
	public void realizarAcao() {
		System.out.println("Azulejista está colocando os azulejos.");
	}
}

class Pintor extends Usuario {
	public Pintor(String login, String senha) {
		super(login, senha, PerfilObra.PINTOR);
	}

	@Override
	public void realizarAcao() {
		System.out.println("Pintor está dando o acabamento nas paredes.");
	}
}

class Encanador extends Usuario {
	public Encanador(String login, String senha) {
		super(login, senha, PerfilObra.ENCANADOR);
	}

	@Override
	public void realizarAcao() {
		System.out.println("Encanador está instalando a parte hidráulica.");
	}
}

class Eletricista extends Usuario {
	public Eletricista(String login, String senha) {
		super(login, senha, PerfilObra.ELETRICISTA);
	}

	@Override
	public void realizarAcao() {
		System.out.println("Eletricista está cuidando da instalação elétrica.");
	}
}

class Servente extends Usuario {
	public Servente(String login, String senha) {
		super(login, senha, PerfilObra.SERVENTE);
	}

	@Override
	public void realizarAcao() {
		System.out.println("Servente está auxiliando nas diversas tarefas.");
	}
}

class SistemaLoginObra {
	private static List<Usuario> usuarios = new ArrayList<>();
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		// Inicializa alguns usuários (simulando um banco de dados)
		usuarios.add(new Pedreiro("pedreiro123", "tijolo"));
		usuarios.add(new Carpinteiro("carpinteiro456", "madeira"));
		usuarios.add(new Azulejista("azulejista789", "ceramica"));
		usuarios.add(new Pintor("pintor012", "tinta"));
		usuarios.add(new Encanador("encanador345", "cano"));
		usuarios.add(new Eletricista("eletricista678", "fio"));
		usuarios.add(new Servente("servente901", "ajuda"));

		System.out.println("Bem-vindo ao Sistema de Login da Construção Civil!");

		@SuppressWarnings("unused")
		int opcao = 0;
		boolean logado = false;

		do {
			try {
				for (int tentativa = 0; tentativa < 3; tentativa++) {
					System.out.print("Digite seu login: ");
					String loginDigitado = scanner.nextLine();
					System.out.print("Digite sua senha: ");
					String senhaDigitada = scanner.nextLine();

					Usuario usuarioLogado = autenticarUsuario(loginDigitado, senhaDigitada);

					if (usuarioLogado != null) {
						System.out.println("\nLogin realizado com sucesso!");
						usuarioLogado.exibirInformacoes();
						usuarioLogado.realizarAcao();
						usuarioLogado.executarTarefaEspecifica();
						logado = true;
						break; // Sai do loop de tentativas
					} else {
						System.out.println("Login ou senha incorretos. Tentativas restantes: " + (2 - tentativa));
					}

					if (tentativa == 2) {
						System.out.println("Número máximo de tentativas excedido. O sistema será encerrado.");
						System.exit(0);
					}
				}
			} catch (InputMismatchException e) {
				System.out.println("Entrada inválida. Por favor, tente novamente.");
				scanner.nextLine(); // Limpa o buffer
				opcao = 0;
			}
		} while (!logado);
	}
	
	public static Usuario autenticarUsuario(String login, String senha) {
	    for (Usuario usuario : usuarios) {
	        if (usuario.getLogin().equals(login) && usuario.getSenha().equals(senha)) {
	            System.err.println("Usuário autenticado com sucesso!!!");
	        	return usuario; // Usuário autenticado com sucesso
	        }
	    }
	    return null; // Nenhum usuário encontrado com login e senha correspondentes
	}
}

class Import_1F {
	public static void run_Import_1F() {
		System.err.println("\t\t1. \n");
		SistemaLoginObra.main(new String[1]);
	}
}

//Corpo da Classe _______________________________
//Enum para representar os tipos de conta
enum TipoConta14 {
	CORRENTE("Conta Corrente"), POUPANCA("Conta Poupança"), INVESTIMENTO("Conta Investimento");

	private final String descricao;

	TipoConta14(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}

class Transacao14 {
	private String tipo; // Ex: "Depósito", "Saque", "Transferência"
	private double valor;
	private LocalDateTime dataHora;
	private String descricao;

	public Transacao14(String tipo, double valor, String descricao) {
		this.tipo = tipo;
		this.valor = valor;
		this.descricao = descricao;
		this.dataHora = LocalDateTime.now();
	}

	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		return String.format("[%s] %s de R$ %.2f - %s", dataHora.format(formatter), tipo, valor, descricao);
	}
}

//Classe abstrata para operações bancárias
abstract class OperacaoBancaria {
	protected int agencia;
	protected String conta;
	protected double saldo;
	protected TipoConta14 tipoConta; // Adicionando o tipo de conta

	protected List<Transacao14> historicoTransacoes = new ArrayList<>();

	public OperacaoBancaria(int agencia, String conta, double saldoInicial, TipoConta14 tipoConta) {
		this.agencia = agencia;
		this.conta = conta;
		this.saldo = saldoInicial;
		this.tipoConta = tipoConta;
	}

	public int getAgencia() {
		return agencia;
	}

	public String getConta() {
		return conta;
	}

	public double getSaldo() {
		return saldo;
	}

	public TipoConta14 getTipoConta() {
		return tipoConta;
	}

	// Métodos abstratos para as operações
	public abstract void sacar(double valor);

	public abstract void depositar(double valor);

	public abstract void transferir(OperacaoBancaria destino, double valor);

	public abstract void consultarSaldo();

	public abstract void gerarExtrato();

	@Override
	public String toString() {
		return String.format("Agência: %04d, Conta: %s (%s), Saldo: R$ %.2f", agencia, conta, tipoConta.getDescricao(),
				saldo);
	}
}

// Classe concreta para Conta Corrente
class ContaCorrente extends OperacaoBancaria {
	public ContaCorrente(int agencia, String conta) {
		super(agencia, conta, 0.0, TipoConta14.CORRENTE);
	}

	@Override
	public synchronized void sacar(double valor) {
		if (valor > 0 && saldo >= valor) {
			saldo -= valor;
			historicoTransacoes.add(new Transacao14("Saque", valor, "Saque realizado"));
			System.out.println("Saque de R$ " + String.format("%.2f", valor) + " realizado com sucesso.");
		} else {
			System.out.println("Saldo insuficiente ou valor inválido para saque.");
		}
	}

	@Override
	public synchronized void depositar(double valor) {
		if (valor > 0) {
			saldo += valor;
			historicoTransacoes.add(new Transacao14("Depósito", valor, "Depósito realizado"));
			System.out.println("Depósito de R$ " + String.format("%.2f", valor) + " realizado com sucesso.");
		} else {
			System.out.println("Valor inválido para depósito.");
		}
	}

	@Override
	public synchronized void transferir(OperacaoBancaria destino, double valor) {
		if (valor > 0 && saldo >= valor) {
			this.sacar(valor);
			destino.depositar(valor);
			historicoTransacoes
					.add(new Transacao14("Transferência", valor, "Transferência para conta " + destino.getConta()));
			System.out.println("Transferência de R$ " + String.format("%.2f", valor) + " para a conta "
					+ destino.getConta() + " realizada com sucesso.");
		} else {
			System.out.println("Saldo insuficiente ou valor inválido para transferência.");
		}
	}

	@Override
	public void consultarSaldo() {
		System.out.println("Saldo atual da Conta Corrente: R$ " + String.format("%.2f", saldo));
	}

	@Override
	public void gerarExtrato() {
		System.out.println("\n--- Extrato Conta Corrente ---");
		System.out.println(this); // Informações da conta
		System.out.println("Histórico de Transações:");
		if (historicoTransacoes.isEmpty()) {
			System.out.println("Nenhuma transação realizada.");
		} else {
			for (Transacao14 t : historicoTransacoes) {
				System.out.println(t);
			}
		}
		System.out.println("-------------------------------\n");
	}
}

class SistemaBancario3 {
	private static Set<ContaCorrente> contas = new HashSet<>();
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		// Inicialização de algumas contas para demonstração
		contas.add(new ContaCorrente(1001, "12345-6"));
		contas.add(new ContaCorrente(1001, "65432-1"));
		contas.add(new ContaCorrente(2005, "98765-4"));

		login();
	}

	public static void login() {
		System.out.println("--- Login ---\nContaCorrente(1001,12345-6 | 1001, 65432-1 | 2005, 98765-4");
		System.out.print("Digite a agência: ");
		int agencia = scanner.nextInt();
		System.out.print("Digite a conta (no formato xxxxx-x): ");
		String conta = scanner.next();
		scanner.nextLine(); // Consumir a quebra de linha

		ContaCorrente contaLogada = null;
		for (ContaCorrente cc : contas) {
			if (cc.getAgencia() == agencia && cc.getConta().equals(conta)) {
				contaLogada = cc;
				break;
			}
		}

		if (contaLogada != null) {
			System.out.println("\nLogin realizado com sucesso!");
			menu(contaLogada);
		} else {
			System.out.println("Agência e/ou conta inválidos.");
			login(); // Tenta o login novamente
		}
	}

	public static void menu(ContaCorrente conta) {
		int opcao = 0;

		while (opcao != 6) {
			System.out.println("\n--- Menu Principal ---");
			System.out.println("1 - Saque");
			System.out.println("2 - Depósito");
			System.out.println("3 - Transferência");
			System.out.println("4 - Consulta de Saldo");
			System.out.println("5 - Extrato");
			System.out.println("6 - Sair");
			System.out.print("Escolha uma opção: ");

			try {
				opcao = scanner.nextInt();
				scanner.nextLine(); // Consumir a quebra de linha

				switch (opcao) {
				case 1:
					System.out.print("Digite o valor para saque: R$ ");
					double valorSaque = scanner.nextDouble();
					scanner.nextLine();
					conta.sacar(valorSaque);
					break;
				case 2:
					System.out.print("Digite o valor para depósito: R$ ");
					double valorDeposito = scanner.nextDouble();
					scanner.nextLine();
					conta.depositar(valorDeposito);
					break;
				case 3:
					System.out.print("Digite a agência da conta de destino: ");
					int agenciaDestino = scanner.nextInt();
					System.out.print("Digite a conta de destino (no formato xxxxx-x): ");
					String contaDestino = scanner.next();
					scanner.nextLine();
					System.out.print("Digite o valor para transferência: R$ ");
					double valorTransferencia = scanner.nextDouble();
					scanner.nextLine();

					ContaCorrente destino = null;
					for (ContaCorrente cc : contas) {
						if (cc.getAgencia() == agenciaDestino && cc.getConta().equals(contaDestino)) {
							destino = cc;
							break;
						}
					}

					if (destino != null) {
						conta.transferir(destino, valorTransferencia);
					} else {
						System.out.println("Conta de destino não encontrada.");
					}
					break;
				case 4:
					conta.consultarSaldo();
					break;
				case 5:
					conta.gerarExtrato();
					break;
				case 6:
					System.out.println("Saindo do sistema...");
					break;
				default:
					System.out.println("Opção inválida. Tente novamente.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Entrada inválida. Por favor, digite um número.");
				scanner.nextLine(); // Limpar o buffer do scanner
				opcao = 0; // Para continuar no loop
			}
		}
	}
}

class Import_2F {
	public static void run_Import_2F() {
		System.err.println("\t\t2. \n");
		SistemaBancario3.main(new String[2]);
	}
}

//Corpo da Classe _______________________________

enum Operacao {
	SAQUE, DEPOSITO, TRANSFERENCIA, CONSULTA, EXTRATO, SAIR;

	public static Operacao fromInt(int op) {
		return values()[op];
	}
}

enum Perfil {
	CLIENTE("Cliente comum"), PREMIUM("Cliente Premium"), ADMIN("Administrador"), VIP("Cliente VIP"),
	GUEST("Convidado"), BLOQUEADO("Conta bloqueada");

	private final String descricao;

	Perfil(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}

enum Agencia {
	A1(101), A2(102), A3(103), A4(104), A5(105), A6(106);

	private final int numero;

	Agencia(int numero) {
		this.numero = numero;
	}

	public int getNumero() {
		return numero;
	}
}

enum TransacaoTipo {
	SAQUE, DEPOSITO, TRANSFERENCIA;

	public String getNome() {
		return this.name();
	}
}

enum HistoricoTipo {
	ENTRADA, SAIDA, MOVIMENTACAO;

	public String toMensagem() {
		return "Tipo: " + this.name();
	}
}

enum SituacaoConta {
	ATIVA(true), INATIVA(false), PENDENTE(false), ENCERRADA(false), EM_ANALISE(false), VERIFICADA(true);

	private final boolean ativa;

	SituacaoConta(boolean ativa) {
		this.ativa = ativa;
	}

	public boolean isAtiva() {
		return ativa;
	}
}

abstract class ContaBase {
	protected int[][] saldos = new int[3][3];
	protected HashSet<String> historico = new HashSet<>();

	public abstract void operacao(int cliente, int agencia, Operacao op, Scanner scanner);

	protected void registrarHistorico(String info) {
		historico.add(info + " | " + new Date());
	}

	public void mostrarExtrato() {
		for (String h : historico) {
			System.out.println(h);
		}
	}
}

class BancoHardFor extends ContaBase {

	@Override
	public synchronized void operacao(int cliente, int agencia, Operacao op, Scanner scanner) {
		switch (op) {
		case SAQUE:
			System.out.print("Valor para saque: ");
			int saque = scanner.nextInt();
			if (saldos[cliente][agencia] >= saque) {
				saldos[cliente][agencia] -= saque;
				registrarHistorico("SAQUE de R$" + saque);
			} else {
				System.out.println("Saldo insuficiente!");
			}
			break;
		case DEPOSITO:
			System.out.print("Valor para depósito: ");
			int deposito = scanner.nextInt();
			saldos[cliente][agencia] += deposito;
			registrarHistorico("DEPÓSITO de R$" + deposito);
			break;
		case TRANSFERENCIA:
			System.out.print("Para qual cliente (0-2): ");
			int destCliente = scanner.nextInt();
			System.out.print("Qual agência destino (0-2): ");
			int destAgencia = scanner.nextInt();
			System.out.print("Valor: ");
			int valor = scanner.nextInt();
			if (saldos[cliente][agencia] >= valor) {
				saldos[cliente][agencia] -= valor;
				saldos[destCliente][destAgencia] += valor;
				registrarHistorico("TRANSFERÊNCIA de R$" + valor + " para cliente " + destCliente);
			} else {
				System.out.println("Saldo insuficiente!");
			}
			break;
		case CONSULTA:
			System.out.println("Saldo atual: R$" + saldos[cliente][agencia]);
			break;
		case EXTRATO:
			mostrarExtrato();
			break;
		case SAIR:
			System.out.println("Saindo...");
			break;
		default:
			System.out.println("Operação inválida.");
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		BancoHardFor banco = new BancoHardFor();

		// Login Simples
		System.out.print("Digite número do cliente (0 a 2): ");
		int cliente = scanner.nextInt();

		System.out.print("Digite número da agência (0 a 2): ");
		int agencia = scanner.nextInt();

		Perfil perfil = Perfil.values()[cliente];
		System.out.println("Perfil do usuário: " + perfil.getDescricao());

		int opcao;
		do {
			System.out.println("\n*** MENU ***");
			System.out.println("0 - SAQUE");
			System.out.println("1 - DEPÓSITO");
			System.out.println("2 - TRANSFERÊNCIA");
			System.out.println("3 - CONSULTA");
			System.out.println("4 - EXTRATO");
			System.out.println("5 - SAIR");
			System.out.print("Escolha uma operação: ");
			opcao = scanner.nextInt();

			if (opcao >= 0 && opcao <= 5) {
				banco.operacao(cliente, agencia, Operacao.fromInt(opcao), scanner);
			} else {
				System.out.println("Opção inválida!");
			}
		} while (opcao != 5);

		VoutarRun voutarRun = new VoutarRun();
		voutarRun.run_CaracteresMenu_Red();
	}
}

class Import_3F {
	public static void run_Import_3F() {
		System.err.println("\t\t3.\n");
		BancoHardFor.main(new String[0]);
	}
}
