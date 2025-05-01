package UTIL_13_HashSet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import util.Linhas;
import util.VoutarRun;

public class Vetor_Set {
	public static void Vetor_Run() {

		Linhas linhas = new Linhas();
		String collec = "\n\t Nome \n";
		System.err.println(collec);

		linhas.run_Caracteres();
		Import_1Vv.run_Import_1Vv();
		
		linhas.run_Caracteres();
		Import_1V.run_Import_1V();

		linhas.run_Caracteres();
		Import_2V.run_Import_2V();

		linhas.run_Caracteres();
		Import_3V.run_Import_3V();
	}
}

//______________________

enum VelocidadeDDR4 {
    DDR4_2133(2133), DDR4_2400(2400), DDR4_2666(2666), DDR4_3000(3000), DDR4_3200(3200);
    private final int velocidade;
    VelocidadeDDR4(int velocidade) { this.velocidade = velocidade; }
    public int getVelocidade() { return velocidade; }
}

enum Capacidade {
    GB4(4), GB8(8), GB16(16), GB32(32), GB64(64);
    private final int valor;
    Capacidade(int valor) { this.valor = valor; }
    public int getValor() { return valor; }
}

enum TipoModulo {
    UDIMM("Unbuffered"), RDIMM("Registered"), SO_DIMM("Small Outline"), LRDIMM("Load Reduced"), ECC("Error Correction");
    private final String descricao;
    TipoModulo(String descricao) { this.descricao = descricao; }
    public String getDescricao() { return descricao; }
}

enum Fabricante {
    KINGSTON("Kingston"), CORSAIR("Corsair"), CRUCIAL("Crucial"), GSKILL("G.Skill"), HYPERX("HyperX");
    private final String nome;
    Fabricante(String nome) { this.nome = nome; }
    public String getNome() { return nome; }
}

enum Latencia {
    CL14(14), CL15(15), CL16(16), CL17(17), CL18(18);
    private final int cl;
    Latencia(int cl) { this.cl = cl; }
    public int getCl() { return cl; }
}

// Classe abstrata
abstract class MemoriaBase {
    protected String modelo;
    protected VelocidadeDDR4 velocidade;
    protected Capacidade capacidade;
    protected TipoModulo tipo;
    protected Fabricante fabricante;
    protected Latencia latencia;

    public MemoriaBase(String modelo, VelocidadeDDR4 velocidade, Capacidade capacidade,
                       TipoModulo tipo, Fabricante fabricante, Latencia latencia) {
        this.modelo = modelo;
        this.velocidade = velocidade;
        this.capacidade = capacidade;
        this.tipo = tipo;
        this.fabricante = fabricante;
        this.latencia = latencia;
    }

    public abstract void exibirInformacoes();
}

// Classe concreta com sincronização
class MemoriaDDR4 extends MemoriaBase {

    public MemoriaDDR4(String modelo, VelocidadeDDR4 velocidade, Capacidade capacidade,
                       TipoModulo tipo, Fabricante fabricante, Latencia latencia) {
        super(modelo, velocidade, capacidade, tipo, fabricante, latencia);
    }

    @Override
    public synchronized void exibirInformacoes() {
        System.out.println("Modelo: " + modelo);
        System.out.println("Velocidade: " + velocidade.getVelocidade() + "MHz");
        System.out.println("Capacidade: " + capacidade.getValor() + "GB");
        System.out.println("Tipo: " + tipo.getDescricao());
        System.out.println("Fabricante: " + fabricante.getNome());
        System.out.println("Latência: CL" + latencia.getCl());
        System.out.println("-------------------------------------------");
    }
}

// Main com lógica
class SistemaMemoriaDDR4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Set<MemoriaDDR4> memorias = new HashSet<>();

        String[][] caracteristicas = {
            {"HyperX Predator", "DDR4_3200", "GB16", "UDIMM", "HYPERX", "CL16"},
            {"Corsair Vengeance", "DDR4_3000", "GB32", "SO_DIMM", "CORSAIR", "CL18"},
            {"Crucial Ballistix", "DDR4_2666", "GB8", "ECC", "CRUCIAL", "CL15"},
            {"Kingston Fury", "DDR4_2400", "GB4", "RDIMM", "KINGSTON", "CL14"},
            {"GSkill TridentZ", "DDR4_2133", "GB64", "LRDIMM", "GSKILL", "CL17"}
        };

        for (int i = 0; i < caracteristicas.length; i++) {
            String[] c = caracteristicas[i];
            MemoriaDDR4 m = new MemoriaDDR4(
                    c[0],
                    VelocidadeDDR4.valueOf(c[1]),
                    Capacidade.valueOf(c[2]),
                    TipoModulo.valueOf(c[3]),
                    Fabricante.valueOf(c[4]),
                    Latencia.valueOf(c[5])
            );
            memorias.add(m);
        }

        int opcao = 0;
        while (opcao != 3) {
            System.out.println("\n[1] Listar Memórias\n[2] Buscar por Modelo\n[3] Sair");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    for (MemoriaDDR4 m : memorias) {
                        m.exibirInformacoes();
                    }
                    break;

                case 2:
                    System.out.print("Digite o modelo: ");
                    String busca = scanner.nextLine();
                    boolean encontrou = false;
                    for (MemoriaDDR4 m : memorias) {
                        if (m.modelo.equalsIgnoreCase(busca)) {
                            m.exibirInformacoes();
                            encontrou = true;
                        }
                    }
                    if (!encontrou) {
                        System.out.println(">> Memória não encontrada.");
                    }
                    break;

                case 3:
                    System.out.println("Sistema encerrado.");
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }
}


class Import_1Vv {
	public static void run_Import_1Vv() {
		System.err.println("\t\t1. \n");
		SistemaMemoriaDDR4.main(null); 
	}
}


//Corpo da Classe _______________________________
enum Perfil22 {
	CLIENTE("Cliente"), GERENTE("Gerente"), DIRETOR("Diretor"), INVESTIDOR("Investidor"), ADMIN("Administrador");

	private String descricao;

	Perfil22(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public static void listarPerfis() {
		for (Perfil22 p : Perfil22.values()) {
			System.out.println(p + ": " + p.getDescricao());
		}
	}
}

enum TipoConta {
	CORRENTE(1.5), POUPANCA(1.0), SALARIO(0.5), DIGITAL(2.0), PREMIUM(3.0);

	private double taxaPIX;

	TipoConta(double taxaPIX) {
		this.taxaPIX = taxaPIX;
	}

	public double calcularTaxa(double valor) {
		return valor * (taxaPIX / 100);
	}
}

enum StatusTransacao {
	SUCESSO, FALHA, SALDO_INSUFICIENTE, USUARIO_INVALIDO, CONTA_INEXISTENTE;

	public void print() {
		System.out.println("Status da Transação: " + this.name());
	}
}

enum Banco {
	NUBANK("260"), ITAU("341"), BRADESCO("237"), SANTANDER("033"), CAIXA("104");

	private final String codigo;

	Banco(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}
}

enum MetodoPIX {
	CPF, CNPJ, TELEFONE, EMAIL, ALEATORIA;

	public static MetodoPIX aleatorio() {
		MetodoPIX[] metodos = MetodoPIX.values();
		return metodos[new Random().nextInt(metodos.length)];
	}
}

// ====================== Classe Abstrata ========================
abstract class Conta {
	protected String titular;
	protected double saldo;
	protected TipoConta tipoConta;
	protected Banco banco;
	protected Perfil22 perfil;

	public Conta(String titular, double saldo, TipoConta tipoConta, Banco banco, Perfil22 perfil) {
		this.titular = titular;
		this.saldo = saldo;
		this.tipoConta = tipoConta;
		this.banco = banco;
		this.perfil = perfil;
	}

	public abstract void info();

	public synchronized StatusTransacao transferirPIX(Conta destino, double valor) {
		if (destino == null || valor <= 0)
			return StatusTransacao.CONTA_INEXISTENTE;
		if (saldo < valor)
			return StatusTransacao.SALDO_INSUFICIENTE;

		double taxa = tipoConta.calcularTaxa(valor);
		this.saldo -= (valor + taxa);
		destino.saldo += valor;
		return StatusTransacao.SUCESSO;
	}

	public synchronized void exibirSaldo() {
		System.out.printf("Titular: %s | Saldo: %.2f | Tipo: %s\n", titular, saldo, tipoConta);
	}
}

// ====================== Classe Concreta ========================
class ContaBancaria extends Conta {
	public ContaBancaria(String titular, double saldo, TipoConta tipoConta, Banco banco, Perfil22 perfil) {
		super(titular, saldo, tipoConta, banco, perfil);
	}

	@Override
	public void info() {
		System.out.printf("Conta de %s | Banco: %s | Perfil: %s\n", titular, banco.name(), perfil.getDescricao());
	}
}

// ====================== Classe Principal ========================
class SistemaPIX {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		HashSet<Conta> contas = new HashSet<>();

		Conta c1 = new ContaBancaria("Ana", 3000, TipoConta.CORRENTE, Banco.ITAU, Perfil22.CLIENTE);
		Conta c2 = new ContaBancaria("Carlos", 5000, TipoConta.PREMIUM, Banco.NUBANK, Perfil22.INVESTIDOR);
		Conta c3 = new ContaBancaria("Marina", 1500, TipoConta.POUPANCA, Banco.BRADESCO, Perfil22.GERENTE);

		contas.add(c1);
		contas.add(c2);
		contas.add(c3);

		Conta[] contasArray = contas.toArray(new Conta[0]);
		int[][] matrizOperacoes = new int[3][3]; // [Remetente][Destinatario]

		System.out.println("==== LISTA DE CONTAS ====");
		for (int i = 0; i < contasArray.length; i++) {
			System.out.print((i + 1) + " - ");
			contasArray[i].info();
		}

		String continuar = "s";
		while (continuar.equalsIgnoreCase("s")) {
			System.out.print("Informe número do remetente: ");
			int i = sc.nextInt() - 1;

			System.out.print("Informe número do destinatário: ");
			int j = sc.nextInt() - 1;

			System.out.print("Valor da transação via PIX: ");
			double valor = sc.nextDouble();

			if (i >= 0 && i < contasArray.length && j >= 0 && j < contasArray.length) {
				Conta origem = contasArray[i];
				Conta destino = contasArray[j];

				StatusTransacao status = origem.transferirPIX(destino, valor);
				status.print();
				matrizOperacoes[i][j] += 1;
			} else {
				System.out.println("Conta inválida.");
			}

			System.out.print("Deseja continuar? (s/n): ");
			continuar = sc.next();
		}

		System.out.println("\n==== RESUMO FINAL ====");
		for (Conta c : contasArray) {
			c.exibirSaldo();
		}

		System.out.println("\n==== MATRIZ DE TRANSAÇÕES ====");
		for (int i = 0; i < contasArray.length; i++) {
			for (int j = 0; j < contasArray.length; j++) {
				System.out.print(matrizOperacoes[i][j] + "\t");
			}
			System.out.println();
		}

		System.out.println("\n==== SWITCH CASE PARA EXIBIR CÓDIGOS DOS BANCOS ====");
		for (Conta c : contasArray) {
			switch (c.banco) {
			case ITAU -> System.out.println("Banco Itaú - Código: " + c.banco.getCodigo());
			case NUBANK -> System.out.println("Nubank - Código: " + c.banco.getCodigo());
			case BRADESCO -> System.out.println("Bradesco - Código: " + c.banco.getCodigo());
			case CAIXA -> System.out.println("Caixa - Código: " + c.banco.getCodigo());
			case SANTANDER -> System.out.println("Santander - Código: " + c.banco.getCodigo());
			default -> System.out.println("Banco não identificado");
			}
		}
	}
}

class Import_1V {
	public static void run_Import_1V() {
		System.err.println("\t\t1. \n");
		SistemaPIX.main(null);
	}
}

//Corpo da Classe _______________________________

//ENUMS com métodos
enum Perfil2 {
	CLIENTE, GERENTE, ADMIN, PREMIUM, INVESTIDOR;

	public String getDescricao() {
		return switch (this) {
		case CLIENTE -> "Cliente padrão";
		case GERENTE -> "Gerente de contas";
		case ADMIN -> "Administrador do sistema";
		case PREMIUM -> "Cliente Premium";
		case INVESTIDOR -> "Investidor do banco";
		};
	}
}

enum TipoConta2 {
	CORRENTE, POUPANCA, SALARIO, DIGITAL, EMPRESARIAL;

	public double taxaOperacao() {
		return switch (this) {
		case CORRENTE -> 1.5;
		case POUPANCA -> 0.5;
		case SALARIO -> 0.0;
		case DIGITAL -> 0.2;
		case EMPRESARIAL -> 2.0;
		};
	}
}

enum StatusConta {
	ATIVA, BLOQUEADA, ENCERRADA, AGUARDANDO, VERIFICACAO;

	public boolean podeTransacionar() {
		return this == ATIVA || this == VERIFICACAO;
	}
}

enum TipoTransacao {
	TRANSFERENCIA, DEPOSITO, SAQUE, PIX, TED;

	public String codigoTransacao() {
		return name().substring(0, 3) + "-" + ordinal();
	}
}

enum Banco2 {
	BRASIL, CAIXA, ITAU, SANTANDER, NUBANK;

	public String agenciaPrincipal() {
		return switch (this) {
		case BRASIL -> "001-9";
		case CAIXA -> "104-1";
		case ITAU -> "341-7";
		case SANTANDER -> "033-3";
		case NUBANK -> "260-8";
		};
	}
}

//Classe abstrata com métodos sincronizados
abstract class ContaBancaria2 {
	protected String titular;
	protected double saldo;
	protected int numero;
	protected TipoConta2 tipo;
	protected StatusConta status;

	public ContaBancaria2(String titular, int numero, TipoConta2 tipo) {
		this.titular = titular;
		this.numero = numero;
		this.tipo = tipo;
		this.status = StatusConta.ATIVA;
		this.saldo = 1000.0 + new Random().nextInt(1000);
	}

	public abstract void exibirDetalhes();

	public synchronized boolean transferirPara(ContaBancaria2 destino, double valor) {
		if (status.podeTransacionar() && this.saldo >= valor) {
			this.saldo -= valor;
			destino.receber(valor);
			return true;
		}
		return false;
	}

	public synchronized void receber(double valor) {
		this.saldo += valor;
	}

	public double getSaldo() {
		return saldo;
	}

	public String getTitular() {
		return titular;
	}

	public int getNumero() {
		return numero;
	}
}

//Implementação da conta com funcionalidade
class ContaUsuario extends ContaBancaria2 {
	private Perfil2 perfil;

	public ContaUsuario(String titular, int numero, TipoConta2 tipo, Perfil2 perfil) {
		super(titular, numero, tipo);
		this.perfil = perfil;
	}

	@Override
	public void exibirDetalhes() {
		System.out
				.println("Conta de: " + titular + " | Nº: " + numero + " | Perfil: " + perfil + " | Saldo: R$" + saldo);
	}
}

//Main com matriz, vetor, while, for e switch-case
class BancoSimulador {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ContaUsuario[] contas = new ContaUsuario[5];

		// Criando contas (vetor)
		contas[0] = new ContaUsuario("Alice", 1001, TipoConta2.CORRENTE, Perfil2.CLIENTE);
		contas[1] = new ContaUsuario("Bruno", 1002, TipoConta2.POUPANCA, Perfil2.PREMIUM);
		contas[2] = new ContaUsuario("Carla", 1003, TipoConta2.SALARIO, Perfil2.INVESTIDOR);
		contas[3] = new ContaUsuario("Daniel", 1004, TipoConta2.DIGITAL, Perfil2.GERENTE);
		contas[4] = new ContaUsuario("Elisa", 1005, TipoConta2.EMPRESARIAL, Perfil2.ADMIN);

		// Matriz para registrar transações: origem, destino, valor
		String[][] transacoes = { { "1001", "1002", "150" }, { "1003", "1005", "200" }, { "1004", "1001", "100" },
				{ "1002", "1003", "50" }, { "1005", "1004", "300" }, };

		// HashSet para guardar contas únicas envolvidas
		HashSet<Integer> contasEnvolvidas = new HashSet<>();

		int i = 0;
		while (i < transacoes.length) {
			int origem = Integer.parseInt(transacoes[i][0]);
			int destino = Integer.parseInt(transacoes[i][1]);
			double valor = Double.parseDouble(transacoes[i][2]);

			ContaUsuario cOrigem = buscarConta(contas, origem);
			ContaUsuario cDestino = buscarConta(contas, destino);

			if (cOrigem != null && cDestino != null) {
				boolean sucesso = cOrigem.transferirPara(cDestino, valor);
				if (sucesso) {
					contasEnvolvidas.add(origem);
					contasEnvolvidas.add(destino);
					System.out.println("Transferência de R$" + valor + " de " + cOrigem.getTitular() + " para "
							+ cDestino.getTitular() + " realizada.");
				} else {
					System.out.println("Falha ao transferir de " + cOrigem.getTitular() + " para "
							+ cDestino.getTitular() + ". Saldo insuficiente ou conta bloqueada.");
				}
			}
			i++;
		}

		System.out.println("\n--- Detalhes Finais ---");
		for (ContaUsuario c : contas) {
			c.exibirDetalhes();
		}

		// switch-case usando vetor e matriz
		System.out.println("\n--- Operações com switch-case ---");
		String[] operacoes = { "PIX", "SAQUE", "DEPOSITO" };
		for (int j = 0; j < operacoes.length; j++) {
			switch (operacoes[j]) {
			case "PIX" -> System.out.println("Executando operação PIX: " + TipoTransacao.PIX.codigoTransacao());
			case "SAQUE" -> System.out.println("Executando operação SAQUE: " + TipoTransacao.SAQUE.codigoTransacao());
			case "DEPOSITO" ->
				System.out.println("Executando operação DEPOSITO: " + TipoTransacao.DEPOSITO.codigoTransacao());
			default -> System.out.println("Operação desconhecida.");
			}
		}

		// return com for (usando método abaixo)
		System.out.println("\nContas com saldo acima de R$1200:");
		for (ContaUsuario conta : filtrarContasAcimaDe(contas, 1200)) {
			conta.exibirDetalhes();
		}

		sc.close();
	}

	public static ContaUsuario buscarConta(ContaUsuario[] contas, int numero) {
		for (ContaUsuario c : contas) {
			if (c.getNumero() == numero) {
				return c;
			}
		}
		return null;
	}

	public static List<ContaUsuario> filtrarContasAcimaDe(ContaUsuario[] contas, double valor) {
		List<ContaUsuario> lista = new ArrayList<>();
		for (ContaUsuario c : contas) {
			if (c.getSaldo() > valor) {
				lista.add(c);
			}
		}
		return lista;
	}
}

class Import_2V {
	public static void run_Import_2V() {
		System.err.println("\t\t2. \n");
		BancoSimulador.main(null);
	}
}

//Corpo da Classe _______________________________
// Enums utilizados
enum Perfil1 {
    CLIENTE, GERENTE, DIRETOR, AUDITOR, SUPORTE;

    public boolean podeTransferir() {
        return this == CLIENTE || this == GERENTE;
    }

    public boolean podeVerTodasContas() {
        return this == GERENTE || this == DIRETOR || this == AUDITOR;
    }
}

enum TipoConta1 {
    POUPANCA(0.02), CORRENTE(0.01), SALARIO(0.005), EMPRESARIAL(0.015), INVESTIMENTO(0.03);

    private final double rendimento;

    TipoConta1(double rendimento) {
        this.rendimento = rendimento;
    }

    public double calcularRendimento(double saldo) {
        return saldo * rendimento;
    }
}

enum Agencia22 {
    CENTRAL("001"), ZONA_SUL("002"), ZONA_NORTE("003"), LESTE("004"), OESTE("005");

    private final String codigo;

    Agencia22(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }
}

enum StatusConta1 {
    ATIVA, BLOQUEADA, ENCERRADA, EM_ANALISE, SUSPENSA;

    public boolean podeOperar() {
        return this == ATIVA || this == EM_ANALISE;
    }
}

enum Operacao22 {
    DEPOSITO, SAQUE, TRANSFERENCIA, CONSULTA, RENDIMENTO;

    public void log() {
        System.out.println("Operação realizada: " + this.name());
    }
}

// Classe abstrata com métodos sincronizados
abstract class ContaBancaria1 {
    protected String titular;
    protected double saldo;
    protected String numeroConta;
    protected TipoConta1 tipo;
    protected StatusConta1 status;

    public ContaBancaria1(String titular, String numeroConta, TipoConta1 tipo) {
        this.titular = titular;
        this.numeroConta = numeroConta;
        this.tipo = tipo;
        this.status = StatusConta1.ATIVA;
        this.saldo = 0.0;
    }

    public synchronized void depositar(double valor) {
        if (status.podeOperar()) {
            saldo += valor;
            Operacao22.DEPOSITO.log();
        }
    }

    public synchronized void sacar(double valor) {
        if (status.podeOperar() && saldo >= valor) {
            saldo -= valor;
            Operacao22.SAQUE.log();
        }
    }

    public synchronized void transferirPara(ContaBancaria1 destino, double valor) {
        if (status.podeOperar() && saldo >= valor && destino.status.podeOperar()) {
            saldo -= valor;
            destino.saldo += valor;
            Operacao22.TRANSFERENCIA.log();
        }
    }

    public double getSaldo() {
        return saldo;
    }

    public String getTitular() {
        return titular;
    }
}

// Classe concreta de conta
class ContaSimples extends ContaBancaria1 {
    public ContaSimples(String titular, String numeroConta, TipoConta1 tipo) {
        super(titular, numeroConta, tipo);
    }
}

// Classe principal com lógica de banco
class BancoHard22 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ContaBancaria1[][] contas = new ContaBancaria1[3][3];
        HashSet<String> usuarios = new HashSet<>();

        // Contas e usuários simulados
        contas[0][0] = new ContaSimples("Alice", "1001", TipoConta1.CORRENTE);
        contas[0][1] = new ContaSimples("Bob", "1002", TipoConta1.POUPANCA);
        contas[1][0] = new ContaSimples("Carlos", "2001", TipoConta1.INVESTIMENTO);

        usuarios.add("alice_login");
        usuarios.add("bob_login");
        usuarios.add("carlos_login");

        boolean running = true;
        while (running) {
            System.out.print("Login: ");
            String login = scanner.nextLine();

            if (!usuarios.contains(login)) {
                System.out.println("Usuário inválido.");
                continue;
            }

            Perfil1 perfil = switch (login) {
                case "alice_login" -> Perfil1.GERENTE;
                case "bob_login" -> Perfil1.CLIENTE;
                case "carlos_login" -> Perfil1.DIRETOR;
                default -> Perfil1.CLIENTE;
            };

            int op;
            do {
                System.out.println("\n1- Depositar\n2- Sacar\n3- Transferir\n4- Ver Saldo\n5- Rendimento\n6- Sair");
                op = scanner.nextInt();
                scanner.nextLine(); // Limpar quebra de linha

                switch (op) {
                    case 1 -> contas[0][0].depositar(1000);
                    case 2 -> contas[0][0].sacar(500);
                    case 3 -> contas[0][0].transferirPara(contas[0][1], 200);
                    case 4 -> System.out.println("Saldo: " + contas[0][0].getSaldo());
                    case 5 -> {
                        double rendimento = contas[0][0].tipo.calcularRendimento(contas[0][0].getSaldo());
                        System.out.println("Rendimento: " + rendimento);
                    }
                    case 6 -> {
                        running = false;
                        System.out.println("Saindo...");
                    }
                    default -> System.out.println("Opção inválida");
                }
            } while (op != 6);
        }

        VoutarRun voutarRun = new VoutarRun();
        voutarRun.run_CaracteresMenu_Red();
    }
}

// Classe externa para chamada automática
class Import_3V {
    public static void run_Import_3V() {
        System.err.println("\t\t3. \n");
        BancoHard22.main(new String[0]);
    }
}
