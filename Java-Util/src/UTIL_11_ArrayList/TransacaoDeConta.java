package UTIL_11_ArrayList;

import java.util.ArrayList;
import java.util.Scanner;

import util.Linhas;

public class TransacaoDeConta {
	public static void TransacaoDeConta_Run() {

		Linhas linhas = new Linhas();
		String collec = "\n\t TransacaoDeConta \n";
		System.err.println(collec);

		linhas.run_Caracteres();
		Import_1Vr.run_Import_1Vr();
	}
}

//Corpo da Classe _______________________________

class Import_1Vr {
	public static void run_Import_1Vr() {
		System.err.println("\t\t1. \n");
		BancoBB.mainBB();
	}
}

// Enum para perfis de usuários
enum Perfilp {
	CLIENTE, GERENTE, ADMIN;
}

// Enum para tipos de conta
enum TipoContap {
	CORRENTE, POUPANCA;
}

// Enum para status de transação
enum StatusTransacao {
	SUCESSO, FALHA;
}

// Enum para tipo de transação
enum TipoTransacaop {
	DEPOSITO, SAQUE, TRANSFERENCIA;
}

// Classe abstrata Conta
abstract class Contap {
	protected String titular;
	protected double saldo;
	protected TipoContap tipo;

	public Contap(String titular, double saldo, TipoContap tipo) {
		this.titular = titular;
		this.saldo = saldo;
		this.tipo = tipo;
	}

	public synchronized StatusTransacao depositar(double valor) {
		if (valor > 0) {
			saldo += valor;
			return StatusTransacao.SUCESSO;
		}
		return StatusTransacao.FALHA;
	}

	public synchronized StatusTransacao sacar(double valor) {
		if (valor > 0 && saldo >= valor) {
			saldo -= valor;
			return StatusTransacao.SUCESSO;
		}
		return StatusTransacao.FALHA;
	}

	public synchronized StatusTransacao transferir(Contap destino, double valor) {
		if (sacar(valor) == StatusTransacao.SUCESSO) {
			destino.depositar(valor);
			return StatusTransacao.SUCESSO;
		}
		return StatusTransacao.FALHA;
	}

	public abstract void exibirInformacoes();
}

// Classe ContaCorrente
abstract class ContaCorrentep extends Contap {
	public ContaCorrentep(String titular, double saldo) {
		super(titular, saldo, TipoContap.CORRENTE);
	}
}

// Classe ContaPoupanca
abstract class ContaPoupanca extends Contap {
	public ContaPoupanca(String titular, double saldo) {
		super(titular, saldo, TipoContap.POUPANCA);
	}
}

// Classe abstrata para usuário
abstract class Usuariop {
	protected String nome;
	protected Perfilp perfil;
	protected ArrayList<Contap> contas;

	public Usuariop(String nome, Perfilp perfil) {
		this.nome = nome;
		this.perfil = perfil;
		this.contas = new ArrayList<>();
	}

	public void adicionarConta(Contap conta) {
		contas.add(conta);
	}

	public void exibirContas() {
		for (Contap conta : contas) {
			conta.exibirInformacoes();
		}
	}
}

class BancoBB {
	private static ArrayList<Usuariop> usuarios = new ArrayList<>();
	private static Scanner scanner = new Scanner(System.in);

	public static void mainBB() {
		while (true) {
			System.out.println("1. Criar Usuário\n2. Realizar Transação\n3. Sair");
			int opcao = scanner.nextInt();
			scanner.nextLine();

			switch (opcao) {
			case 1:
				criarUsuario();
				break;
			case 2:
				realizarTransacao();
				break;
			case 3:
				System.out.println("Encerrando sistema...");
				return;
			default:
				System.out.println("Opção inválida!");
			}
		}
	}

	private static void criarUsuario() {
		System.out.print("Nome do usuário: ");
		String nome = scanner.nextLine();
		System.out.println("Perfil (1-CLIENTE, 2-GERENTE, 3-ADMIN): ");
		int perfilOpcao = scanner.nextInt();
		Perfilp perfil = Perfilp.values()[perfilOpcao - 1];
		Usuariop usuario = new Usuariop(nome, perfil) {
		};
		usuarios.add(usuario);
		System.out.println("Usuário criado com sucesso!");
	}

	private static void realizarTransacao() {
		System.out.print("Nome do remetente: ");
		String remetenteNome = scanner.nextLine();
		System.out.print("Nome do destinatário: ");
		String destinatarioNome = scanner.nextLine();
		System.out.print("Valor: ");
		double valor = scanner.nextDouble();

		Usuariop remetente = buscarUsuario(remetenteNome);
		Usuariop destinatario = buscarUsuario(destinatarioNome);

		if (remetente != null && destinatario != null && remetente.contas != null && !remetente.contas.isEmpty()
				&& destinatario.contas != null && !destinatario.contas.isEmpty()) {

			Contap contaOrigem = remetente.contas.get(0);
			Contap contaDestino = destinatario.contas.get(0);

			if (contaOrigem != null && contaDestino != null) {
				if (valor > 0) {
					try {
						StatusTransacao status = contaOrigem.transferir(contaDestino, valor);
						System.out.println("Transação: " + status);
					} catch (Exception e) {
						System.out.println("Erro ao transferir: " + e.getMessage());
					}
				} else {
					System.out.println("Valor da transferência deve ser maior que zero!");
				}
			} else {
				System.out.println("Conta de origem ou destino é inválida!");
			}
		} else {
			System.out.println("Usuário ou conta inválida!");
		}

	}

	private static Usuariop buscarUsuario(String nome) {
		for (Usuariop usuario : usuarios) {
			if (usuario.nome.equalsIgnoreCase(nome)) {
				return usuario;
			}
		}
		return null;
	}
}
