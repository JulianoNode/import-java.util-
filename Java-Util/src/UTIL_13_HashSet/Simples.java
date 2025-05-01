package UTIL_13_HashSet;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import util.Linhas;
import util.VoutarRun;

public class Simples {
	public static void Simples_Run() {

		Linhas linhas = new Linhas();
		String collec = "\n\t Nome\n";
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


// ENUMS com métodos
enum Cargo {
    SERVENTE {
        public double valor() { return 100; }
    },
    PEDREIRO {
        public double valor() { return 150; }
    },
    MESTRE_OBRAS {
        public double valor() { return 200; }
    },
    ENGENHEIRO {
        public double valor() { return 300; }
    },
    AJUDANTE {
        public double valor() { return 80; }
    },
    TECNICO {
        public double valor() { return 180; }
    };

    public abstract double valor();
}

// CLASSE abstrata com switch-case
abstract class Funcionario {
    String nome;
    Cargo cargo;

    public Funcionario(String nome, Cargo cargo) {
        this.nome = nome;
        this.cargo = cargo;
    }

    public abstract double calcularValorDiaria();

    public void mostrarCargo() {
        switch (cargo) {
            case SERVENTE -> System.out.println(nome + " é Servente");
            case PEDREIRO -> System.out.println(nome + " é Pedreiro");
            case MESTRE_OBRAS -> System.out.println(nome + " é Mestre de Obras");
            case ENGENHEIRO -> System.out.println(nome + " é Engenheiro");
            case AJUDANTE -> System.out.println(nome + " é Ajudante");
            case TECNICO -> System.out.println(nome + " é Técnico");
            default -> System.out.println(nome + " tem cargo indefinido");
        }
    }
}

// Classe concreta usando instanceof e synchronized
class Diaria extends Funcionario {

    private double valorExtra;

    public Diaria(String nome, Cargo cargo, double valorExtra) {
        super(nome, cargo);
        this.valorExtra = valorExtra;
    }

    @Override
    public synchronized double calcularValorDiaria() {
        double base = cargo.valor();
        return base + valorExtra;
    }

    public boolean isTipoEngenharia() {
        return this instanceof Diaria && cargo == Cargo.ENGENHEIRO;
    }
}

class SistemaDiaria {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Set<Double> valoresUnicos = new HashSet<>();

        System.out.println("Digite o número de pedreiros:");
        int n = scanner.nextInt();
        scanner.nextLine();

        double somaTotal = 0;

        for (int i = 0; i < n; i++) {
            System.out.println("\n--- PEDREIRO " + (i + 1) + " ---");

            System.out.print("Nome: ");
            String nome = scanner.nextLine();

            System.out.print("Cargo (SERVENTE, PEDREIRO, MESTRE_OBRAS, ENGENHEIRO, AJUDANTE, TECNICO): ");
            Cargo cargo = Cargo.valueOf(scanner.nextLine().toUpperCase());

            System.out.print("Valor extra da diária (ex: 50): ");
            double extra = scanner.nextDouble();
            scanner.nextLine();

            Diaria diaria = new Diaria(nome, cargo, extra);
            diaria.mostrarCargo();

            double valor = diaria.calcularValorDiaria();

            if (valoresUnicos.add(valor)) {
                System.out.println("Valor adicionado: R$" + valor);
                somaTotal += valor;
            } else {
                System.out.println("Valor duplicado! Não será somado novamente.");
            }

            if (diaria.isTipoEngenharia()) {
                System.out.println("⚙️ Engenheiro identificado.");
            }
        }

        System.out.println("\n🔢 Soma total das diárias únicas: R$" + somaTotal);
        VoutarRun voutarRun = new VoutarRun();
		voutarRun.run_CaracteresMenu_Red();
    }
}

class Import_1S {
	public static void run_Import_1S() {
		System.err.println("\t\t1. \n");
		SistemaDiaria.main(new String[1]);
	}
}

//Corpo da Classe _______________________________
//Enum para Tipos de Operações
enum Operacao8 {
	SAQUE, DEPOSITO, TRANSFERENCIA, CONSULTA, EXTRATO, SAIR;

	public static void listarOperacoes() {
		for (Operacao8 op : values()) {
			System.out.println(op.ordinal() + " - " + op.name());
		}
	}
}

//Enum para Tipo de Conta
enum TipoConta8 {
	CORRENTE, POUPANCA, SALARIO;

	public void exibeTipo() {
		System.out.println("Tipo de Conta: " + this.name());
	}
}

//Enum para Perfil de Usuário
enum Perfil8 {
	CLIENTE, GERENTE, ADMIN;

	public boolean temPermissaoTotal() {
		return this == ADMIN;
	}
}

//Enum para Agência
enum Agencia9 {
	A001, A002, A003;

	public String codigo() {
		return this.name();
	}
}

//Enum para Status da Conta
enum StatusConta8 {
	ATIVA, BLOQUEADA, ENCERRADA;

	public boolean podeOperar() {
		return this == ATIVA;
	}
}

//Enum para Histórico
enum HistoricoOperacao {
	SUCESSO, FALHA;

	public String mensagem() {
		return this == SUCESSO ? "Operação realizada com sucesso!" : "Falha na operação!";
	}
}

//Classe Abstrata para Conta
abstract class ContaBanco {
	protected int numeroConta;
	protected double saldo;
	protected Agencia9 agencia;
	protected TipoConta8 tipoConta;
	protected StatusConta8 status;
	protected Perfil8 perfil;

	public ContaBanco(int numeroConta, Agencia9 agencia, TipoConta8 tipoConta, Perfil8 perfil) {
		this.numeroConta = numeroConta;
		this.agencia = agencia;
		this.tipoConta = tipoConta;
		this.perfil = perfil;
		this.saldo = 0.0;
		this.status = StatusConta8.ATIVA;
	}

	public synchronized void operacao(Operacao8 operacao, Scanner scanner, HashSet<ContaBanco> contas) {
		switch (operacao) {
		case SAQUE:
			System.out.print("Valor do saque: ");
			double saque = scanner.nextDouble();
			if (saldo >= saque) {
				saldo -= saque;
				System.out.println(HistoricoOperacao.SUCESSO.mensagem());
			} else {
				System.out.println(HistoricoOperacao.FALHA.mensagem());
			}
			break;
		case DEPOSITO:
			System.out.print("Valor do depósito: ");
			double deposito = scanner.nextDouble();
			saldo += deposito;
			System.out.println(HistoricoOperacao.SUCESSO.mensagem());
			break;
		case TRANSFERENCIA:
			System.out.print("Número da conta destino: ");
			int destino = scanner.nextInt();
			System.out.print("Valor da transferência: ");
			double valor = scanner.nextDouble();
			boolean achou = false;
			for (ContaBanco conta : contas) {
				if (conta.numeroConta == destino && conta.status.podeOperar()) {
					if (saldo >= valor) {
						saldo -= valor;
						conta.saldo += valor;
						System.out.println(HistoricoOperacao.SUCESSO.mensagem());
					} else {
						System.out.println(HistoricoOperacao.FALHA.mensagem());
					}
					achou = true;
					break;
				}
			}
			if (!achou) {
				System.out.println("Conta destino inválida ou bloqueada.");
			}
			break;
		case CONSULTA:
			System.out.println("Saldo atual: " + saldo);
			break;
		case EXTRATO:
			System.out.println("Extrato - Conta: " + numeroConta + " - Saldo: " + saldo);
			break;
		case SAIR:
			System.out.println("Saindo...");
			break;
		default:
			System.out.println("Operação inválida.");
		}
	}

	public boolean validarLogin(int numero, Agencia9 agencia) {
		return this.numeroConta == numero && this.agencia == agencia;
	}
}

//Classe Concreta (não abstrata)
class ContaCorrente8 extends ContaBanco {
	public ContaCorrente8(int numeroConta, Agencia9 agencia, TipoConta8 tipoConta, Perfil8 perfil) {
		super(numeroConta, agencia, tipoConta, perfil);
	}
}

//Principal
class BancoApp {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		HashSet<ContaBanco> contas = new HashSet<>();

		// Criando contas
		contas.add(new ContaCorrente8(1001, Agencia9.A001, TipoConta8.CORRENTE, Perfil8.CLIENTE));
		contas.add(new ContaCorrente8(1002, Agencia9.A002, TipoConta8.POUPANCA, Perfil8.CLIENTE));
		contas.add(new ContaCorrente8(9999, Agencia9.A003, TipoConta8.SALARIO, Perfil8.ADMIN)); // conta admin

		System.out.println("=== LOGIN ===");
		System.out.print("Número da Conta: ");
		int numeroConta = scanner.nextInt();
		System.out.print("Agência (A001, A002, A003): ");
		String agenciaInput = scanner.next();
		Agencia9 agencia = Agencia9.valueOf(agenciaInput);

		ContaBanco contaLogada = null;
		for (ContaBanco conta : contas) {
			if (conta.validarLogin(numeroConta, agencia)) {
				contaLogada = conta;
				break;
			}
		}

		if (contaLogada != null) {
			System.out.println("Login efetuado com sucesso!");

			if (contaLogada instanceof ContaCorrente8) {
				System.out.println("Conta Corrente detectada!");
			}

			Operacao8 op;
			do {
				System.out.println("\nEscolha a operação:");
				Operacao8.listarOperacoes();
				int opcao = scanner.nextInt();
				op = Operacao8.values()[opcao];
				contaLogada.operacao(op, scanner, contas);
			} while (op != Operacao8.SAIR);
		} else {
			System.out.println("Conta ou Agência inválida!");
		}

		VoutarRun voutarRun = new VoutarRun();
		voutarRun.run_CaracteresMenu_Red();
	}
}

class Import_2S {
	public static void run_Import_2S() {
		System.err.println("\t\t2. \n");
		BancoApp.main(new String[2]);
	}
}

//Corpo da Classe _______________________________
// Nível Hard * 6: Enum com Métodos
enum PerfilObra55 {
	PEDREIRO("Pedreiro", 150.0), CARPINTEIRO("Carpinteiro", 140.0), AZULEJISTA("Azulejista", 160.0),
	PINTOR("Pintor", 120.0), ENCANADOR("Encanador", 170.0), ELETRICISTA("Eletricista", 180.0),
	SERVENTE("Servente", 100.0);

	private final String descricao;
	private final double valorDiaria;

	PerfilObra55(String descricao, double valorDiaria) {
		this.descricao = descricao;
		this.valorDiaria = valorDiaria;
	}

	public String getDescricao() {
		return descricao;
	}

	public double getValorDiaria() {
		return valorDiaria;
	}

	public String exibirDetalhes() {
		return "Perfil: " + descricao + ", Valor da Diária: R$" + String.format("%.2f", valorDiaria);
	}
}

// Nível Hard * 6: Classe Abstract com switch case
abstract class Usuario55 {
	private String login;
	private String senha;
	private PerfilObra55 perfil;

	public Usuario55(String login, String senha, PerfilObra55 perfil) {
		this.login = login;
		this.senha = senha;
		this.perfil = perfil;
	}

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}

	public PerfilObra55 getPerfil() {
		return perfil;
	}

	// Método a ser implementado com sincronização
	public synchronized String obterInformacoesUsuario() {
		return "Login: " + login + ", " + perfil.exibirDetalhes();
	}

	public abstract String realizarAcaoEspecifica();
}

class UsuarioObra55 extends Usuario55 {
	public UsuarioObra55(String login, String senha, PerfilObra55 perfil) {
		super(login, senha, perfil);
	}

	@Override
	public String realizarAcaoEspecifica() {
		switch (getPerfil()) {
		case PEDREIRO:
			return "Preparando alvenaria...";
		case CARPINTEIRO:
			return "Construindo estrutura de madeira...";
		case AZULEJISTA:
			return "Aplicando revestimento cerâmico...";
		case PINTOR:
			return "Realizando pintura das paredes...";
		case ENCANADOR:
			return "Instalando tubulações...";
		case ELETRICISTA:
			return "Executando instalações elétricas...";
		case SERVENTE:
			return "Auxiliando nas tarefas gerais da obra...";
		default:
			return "Ação não definida para este perfil.";
		}
	}
}

class SistemaLoginObra55 {
	private Set<Usuario55> usuarios = new HashSet<>();

	public synchronized boolean adicionarUsuario(Usuario55 usuario) {
		return usuarios.add(usuario);
	}

	public synchronized Usuario55 autenticarUsuario(String login, String senha) {
		for (Usuario55 usuario : usuarios) {
			if (usuario.getLogin().equals(login) && verificarSenha(usuario, senha)) {
				return usuario;
			}
		}
		return null;
	}

	// Método para verificar a senha de forma simples (substitua por algo mais
	// seguro)
	private boolean verificarSenha(Usuario55 usuario, String senha) {
		return senha.equals("senhaPadrao"); // Aqui você poderia utilizar algo mais seguro
	}

	public static void main(String[] args) {
		SistemaLoginObra55 sistema = new SistemaLoginObra55();

		// Criando alguns usuários
		sistema.adicionarUsuario(new UsuarioObra55("pedreiro123", "senhaPadrao", PerfilObra55.PEDREIRO));
		sistema.adicionarUsuario(new UsuarioObra55("carpinteiro456", "senhaPadrao", PerfilObra55.CARPINTEIRO));
		sistema.adicionarUsuario(new UsuarioObra55("eletricista789", "senhaPadrao", PerfilObra55.ELETRICISTA));

		// Tentando autenticar um usuário
		Usuario55 usuarioAutenticado = sistema.autenticarUsuario("pedreiro123", "senhaPadrao");

		if (usuarioAutenticado != null) {
			System.out.println("Login realizado com sucesso!");
			System.out.println(usuarioAutenticado.obterInformacoesUsuario());
			System.out.println("Ação do perfil: " + usuarioAutenticado.realizarAcaoEspecifica());
		} else {
			System.out.println("Falha na autenticação.");
		}
	}
}

class Import_3S {
	public static void run_Import_3S() {
		System.err.println("\t\t3. \n");
		SistemaLoginObra55.main(new String[3]);
	}
}