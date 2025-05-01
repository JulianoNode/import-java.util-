package UTIL_12_LinkedList;

import util.Linhas;
import util.VoutarMenu;

import java.util.LinkedList;
import java.util.Scanner;

public class Vetor_Set {
	public static void Vetor_Run() {

		Linhas linhas = new Linhas();
		String collec = "\n\t Nome \n";
		System.err.println(collec);
		linhas.run_Caracteres();
		Import_1Vk.run_Import_1Vk();

		linhas.run_Caracteres();
		Import_1V.run_Import_1V();

		linhas.run_Caracteres();
		Import_2V.run_Import_2V();

		linhas.run_Caracteres();
		Import_3V.run_Import_3V();
	}
}

//Corpo da Classe _______________________________
//Enum com taxas de conversão

enum Moeda {
    EURO(0.91), LIBRA(0.76), IENE(110.45), DOLAR_CANADENSE(1.27), REAL(5.23), 
    FRANCO_SUIÇO(0.98), BITCOIN(0.000023);

    private double taxaCambio;

    Moeda(double taxaCambio) {
        this.taxaCambio = taxaCambio;
    }

    public double converter(double valorUSD) {
        return valorUSD * taxaCambio;
    }
}

// Classe abstrata para conversão
abstract class Conversor {
    public abstract double converterMoeda(double valor, Moeda moeda);
}

// Implementação da conversão com sincronização
class ConversorMoeda extends Conversor {
    private final Object lock = new Object();

    @Override
    public double converterMoeda(double valor, Moeda moeda) {
        synchronized (lock) {
            return moeda.converter(valor);
        }
    }
}

class CurrencyExchangek {
    public static void mainK() {
        Scanner scanner = new Scanner(System.in);
        ConversorMoeda conversor = new ConversorMoeda();
        LinkedList<Double> valoresConvertidos = new LinkedList<>();

        int opcao = 0;
        do {
            System.out.println("Digite o valor em USD:");
            double valorUSD = scanner.nextDouble();
            System.out.println("Escolha a moeda para conversão:");
            System.out.println("1 - Euro\n2 - Libra\n3 - Iene\n4 - Dólar Canadense\n5 - Real\n6 - Franco Suíço\n7 - Bitcoin");
            int escolha = scanner.nextInt();
            Moeda moedaSelecionada;

            switch (escolha) {
                case 1: moedaSelecionada = Moeda.EURO; break;
                case 2: moedaSelecionada = Moeda.LIBRA; break;
                case 3: moedaSelecionada = Moeda.IENE; break;
                case 4: moedaSelecionada = Moeda.DOLAR_CANADENSE; break;
                case 5: moedaSelecionada = Moeda.REAL; break;
                case 6: moedaSelecionada = Moeda.FRANCO_SUIÇO; break;
                case 7: moedaSelecionada = Moeda.BITCOIN; break;
                default: 
                    System.out.println("Opção inválida.Tente novamente.");
                    continue;
            }

            double valorConvertido = conversor.converterMoeda(valorUSD, moedaSelecionada);
            valoresConvertidos.add(valorConvertido);
            System.out.println("Valor convertido: %.4f\n" + valorConvertido);

            System.out.println("Deseja converter outro valor? (1 - Sim, 2 - Não)");
            opcao = scanner.nextInt();
        } while (opcao == 1);

        System.out.println("Histórico de conversões: " + valoresConvertidos);
		// Voutar para o MEUNU
		VoutarMenu voutarMenu = new VoutarMenu();
		voutarMenu.run_CaracteresMenu_Red();
    }
}

class Import_1Vk {
	public static void run_Import_1Vk() {
		System.err.println("\t\t1. \n");
		CurrencyExchangek.mainK();
	}
}

//Corpo da Classe _______________________________
//Enum com taxas de conversão
enum Currency {
	REAL_TO_DOLLAR(0.20), DOLLAR_TO_REAL(5.00);

	private final double rate;

	Currency(double rate) {
		this.rate = rate;
	}

	public double convert(double amount) {
		return amount * rate;
	}
}

//Classe abstrata
abstract class Converter {
	public abstract double convertCurrency(double amount, Currency currency);
}

//Implementação concreta
class CurrencyConverter extends Converter {
	@Override
	public synchronized double convertCurrency(double amount, Currency currency) {
		return currency.convert(amount);
	}
}

class CurrencyExchange {
	public static void mainM() {
		Scanner scanner = new Scanner(System.in);
		LinkedList<String> transactions = new LinkedList<>();
		CurrencyConverter converter = new CurrencyConverter();

		double[][] history = new double[5][2]; // Matriz de histórico
		int index = 0;

		while (true) {
			System.out.println("\nSelecione a conversão:");
			System.out.println("1. Real para Dólar");
			System.out.println("2. Dólar para Real");
			System.out.println("3. Sair");
			System.out.print("Escolha: ");

			int choice = scanner.nextInt();
			if (choice == 3) {
				System.out.println("\nObrigado por usar o conversor!");
				break;
			}

			System.out.print("Digite o valor: ");
			double value = scanner.nextDouble();
			double result = 0;
			String transaction = "";

			switch (choice) {
			case 1:
				result = converter.convertCurrency(value, Currency.REAL_TO_DOLLAR);
				transaction = String.format("R$: %.2f -> $: %.2f", value, result);
				break;
			case 2:
				result = converter.convertCurrency(value, Currency.DOLLAR_TO_REAL);
				transaction = String.format("$ %.2f -> R$ %.2f", value, result);
				break;
			default:
				System.out.println("Opção inválida!\n");
				continue;
			}

			transactions.addFirst(transaction);
			history[index % 5][0] = value;
			history[index % 5][1] = result;
			index++;

			System.out.println("\nValor convertido: " + transaction);

			System.out.println("Últimas transações:");
			for (String t : transactions) {
				System.out.println(t);
			}

			if (transactions.size() > 5) {
				transactions.removeLast();
			}
		}

		// Voutar para o MEUNU
		VoutarMenu voutarMenu = new VoutarMenu();
		voutarMenu.run_CaracteresMenu_Red();
	}
}

class Import_1V {
	public static void run_Import_1V() {
		System.err.println("\t\t1. \n");
		CurrencyExchange.mainM();
	}
}

//Corpo da Classe _______________________________
//Enum para Tipo de Conta
enum TipoConta {
	POUPANCA, CORRENTE;
}

//Enum para Moeda
enum Moeda1 {
	REAL(5.0), DOLAR(1.0);

	private double taxa;

	Moeda1(double taxa) {
		this.taxa = taxa;
	}

	public double converterParaReal(double valor) {
		return valor * taxa;
	}

	public double converterParaDolar(double valor) {
		return valor / taxa;
	}
}

//Enum para Tipo de Transação
enum TipoTransacao {
	DEPOSITO, SAQUE, TRANSFERENCIA;
}

//Enum para Status da Transação
enum StatusTransacao {
	SUCESSO, FALHA;
}

//Enum para Operações Bancárias
enum Operacao {
	CONSULTAR_SALDO, REALIZAR_TRANSACAO, SAIR;
}

//Classe abstrata Conta
abstract class Conta {
	protected String titular;
	protected double saldo;
	protected TipoConta tipoConta;
	protected Moeda1 moeda;

	public Conta(String titular, double saldo, TipoConta tipoConta, Moeda1 moeda) {
		this.titular = titular;
		this.saldo = saldo;
		this.tipoConta = tipoConta;
		this.moeda = moeda;
	}

	public synchronized boolean depositar(double valor) {
		if (valor > 0) {
			saldo += valor;
			return true;
		}
		return false;
	}

	public synchronized boolean sacar(double valor) {
		if (valor > 0 && saldo >= valor) {
			saldo -= valor;
			return true;
		}
		return false;
	}

	public synchronized boolean transferir(Conta destino, double valor) {
		if (sacar(valor)) {
			destino.depositar(valor);
			return true;
		}
		return false;
	}

	public void exibirSaldo() {
		System.out.println("Titular: " + titular + " | Saldo: " + saldo + " " + moeda);
	}
}

//Implementação da Conta Corrente
class ContaCorrente extends Conta {
	public ContaCorrente(String titular, double saldo, Moeda1 moeda) {
		super(titular, saldo, TipoConta.CORRENTE, moeda);
	}
}

//Implementação Principal
class BancoT {
	public static void mainT() {
		Scanner scanner = new Scanner(System.in);
		LinkedList<Conta> contas = new LinkedList<>();

		// Criando Contas
		contas.add(new ContaCorrente("Alice", 1000, Moeda1.REAL));
		contas.add(new ContaCorrente("Bob", 500, Moeda1.DOLAR));

		while (true) {
			System.out.println("1 - Consultar Saldo");
			System.out.println("2 - Realizar Transação");
			System.out.println("3 - Sair");
			int escolha = scanner.nextInt();

			switch (escolha) {
			case 1: // Consultar Saldo
				for (Conta conta : contas) {
					conta.exibirSaldo();
				}
				break;
			case 2: // Transferência
				System.out.println("Escolha a conta de origem: 0 para Alice, 1 para Bob");
				int origem = scanner.nextInt();
				System.out.println("Escolha a conta de destino: 0 para Alice, 1 para Bob");
				int destino = scanner.nextInt();
				System.out.println("Digite o valor da transferência:");
				double valor = scanner.nextDouble();

				if (origem != destino) {
					if (contas.get(origem).transferir(contas.get(destino), valor)) {
						System.out.println("Transferência realizada com sucesso!");
					} else {
						System.out.println("Falha na transferência!");
					}
				} else {
					System.out.println("Não é possível transferir para a mesma conta.");
				}
				break;
			case 3: // Sair
				System.out.println("Encerrando o sistema bancário.");
				// Voutar para o MEUNU
				VoutarMenu voutarMenu = new VoutarMenu();
				voutarMenu.run_CaracteresMenu_Red();
				return;
			default:
				System.out.println("Opção inválida.");
			}
		}
	}
}

class Import_2V {
	public static void run_Import_2V() {
		System.err.println("\t\t2. \n");
		BancoT.mainT();
	}
}

//Corpo da Classe _______________________________

//Enum para Tipo de Conta
enum TipoContaz {
	CORRENTE, POUPANCA, SALARIO;
}

//Enum para Tipo de Perfil
enum TipoPerfil {
	CLIENTE, GERENTE, ADMIN;
}

//Enum para Status de Transação
enum StatusTransacaoz {
	SUCESSO, FALHA;
}

//Enum para Tipo de Operação
enum TipoOperacao {
	DEPOSITO, SAQUE, TRANSFERENCIA;
}

//Enum para Agências
enum Agencia {
	AGENCIA_001, AGENCIA_002, AGENCIA_003;
}

//Classe abstrata para Conta Bancária
abstract class Contaz {
	protected String titular;
	protected double saldo;
	protected TipoContaz tipoConta;
	protected Agencia agencia;
	protected LinkedList<String> transacoes;

	public Contaz(String titular, double saldo, TipoContaz tipoConta, Agencia agencia) {
		this.titular = titular;
		this.saldo = saldo;
		this.tipoConta = tipoConta;
		this.agencia = agencia;
		this.transacoes = new LinkedList<>();
	}

	public synchronized boolean depositar(double valor) {
		if (valor > 0) {
			saldo += valor;
			transacoes.add("Deposito: +R$" + valor);
			return true;
		}
		return false;
	}

	public synchronized boolean sacar(double valor) {
		if (valor > 0 && saldo >= valor) {
			saldo -= valor;
			transacoes.add("Saque: -R$" + valor);
			return true;
		}
		return false;
	}

	public synchronized boolean transferir(Contaz destino, double valor) {
		if (this.sacar(valor)) {
			destino.depositar(valor);
			transacoes.add("Transferência para " + destino.titular + ": -R$" + valor);
			return true;
		}
		return false;
	}

	public void exibirSaldo() {
		System.out.println("Titular: " + titular + " | Saldo: R$" + saldo);
	}
}

//Classe específica para Conta Cliente
class ContaCliente extends Contaz {
	public ContaCliente(String titular, double saldo, TipoContaz tipoConta, Agencia agencia) {
		super(titular, saldo, tipoConta, agencia);
	}
}

//Sistema principal
class BancoSistema {
	public static void mainBS() {
		Scanner scanner = new Scanner(System.in);
		LinkedList<Contaz> contas = new LinkedList<>();

		// Criando contas de exemplo
		Contaz cliente1 = new ContaCliente("Carlos Silva", 5000, TipoContaz.CORRENTE, Agencia.AGENCIA_001);
		Contaz cliente2 = new ContaCliente("Ana Souza", 3000, TipoContaz.POUPANCA, Agencia.AGENCIA_002);
		Contaz cliente3 = new ContaCliente("Juca Rocha", 10000, TipoContaz.POUPANCA, Agencia.AGENCIA_003);
		contas.add(cliente1);
		contas.add(cliente2);
		contas.add(cliente3);

		while (true) {
			System.out.println("1. Depositar\n2. Sacar\n3. Transferir\n4. Sair");
			int opcao = scanner.nextInt();

			switch (opcao) {
			case 1:
				System.out.print("Digite o nome do titular: ");
				scanner.nextLine();
				String titularDeposito = scanner.nextLine();
				for (Contaz conta : contas) {
					if (conta.titular.equalsIgnoreCase(titularDeposito)) {
						System.out.print("Digite o valor do depósito: ");
						double valor = scanner.nextDouble();
						if (conta.depositar(valor)) {
							System.out.println("Depósito realizado com sucesso!");
						} else {
							System.out.println("Erro no depósito.");
						}
					}
				}
				break;
			case 2:
				System.out.print("Digite o nome do titular: ");
				scanner.nextLine();
				String titularSaque = scanner.nextLine();
				for (Contaz conta : contas) {
					if (conta.titular.equalsIgnoreCase(titularSaque)) {
						System.out.print("Digite o valor do saque: ");
						double valor = scanner.nextDouble();
						if (conta.sacar(valor)) {
							System.out.println("Saque realizado com sucesso!");
						} else {
							System.out.println("Erro no saque.");
						}
					}
				}
				break;
			case 3:
				System.out.print("Titular da conta origem: ");
				scanner.nextLine();
				String titularOrigem = scanner.nextLine();
				System.out.print("Titular da conta destino: ");
				String titularDestino = scanner.nextLine();
				System.out.print("Valor da transferência: ");
				double valorTransferencia = scanner.nextDouble();

				Contaz origem = null, destino = null;
				for (Contaz conta : contas) {
					if (conta.titular.equalsIgnoreCase(titularOrigem)) {
						origem = conta;
					} else if (conta.titular.equalsIgnoreCase(titularDestino)) {
						destino = conta;
					}
				}
				if (origem != null && destino != null && origem.transferir(destino, valorTransferencia)) {
					System.out.println("Transferência realizada com sucesso!");
				} else {
					System.out.println("Erro na transferência.");
				}
				break;
			case 4:
				System.out.println("Saindo do sistema...");
				// Voutar para o MEUNU
				VoutarMenu voutarMenu = new VoutarMenu();
				voutarMenu.run_CaracteresMenu_Red();
				return;
			default:
				System.out.println("Opção inválida!");
			}
		}
	}
}

class Import_3V {
	public static void run_Import_3V() {
		System.err.println("\t\t3. \n");

		System.out.println(" Carlos Silva, 5000 | Ana Souza, 3000 | Juca Rocha, 10000 ");
		BancoSistema.mainBS();
	}
}
