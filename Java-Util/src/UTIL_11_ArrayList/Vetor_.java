package UTIL_11_ArrayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import util.Linhas;
import util.VoutarMenu;

public class Vetor_ {
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

//Corpo da Classe _______________________________

// Enum para os diferentes perfis de usuário
enum Perfilx {
    CLIENTE {
        public String getDescricao() {
            return "Cliente comum";
        }
    },
    GERENTE {
        public String getDescricao() {
            return "Gerente da agência";
        }
    },
    ADMIN {
        public String getDescricao() {
            return "Administrador do sistema";
        }
    };
    
    public abstract String getDescricao();
}

// Enum para os tipos de conta
enum TipoConta {
    POUPANCA, CORRENTE, SALARIO;
}

// Enum para os tipos de agência
enum Agencia {
    CENTRAL, FILIAL1, FILIAL2, PREMIUM;
}

// Enum para status de login
enum StatusLogin {
    SUCESSO, FALHA;
}

// Enum para tipos de transação
enum Transacao {
    DEPOSITO, SAQUE, TRANSFERENCIA;
}

// Classe abstrata Conta
abstract class Contax {
    protected int numero;
    protected double saldo;
    protected TipoConta tipoConta;
    
    public Contax(int numero, double saldo, TipoConta tipoConta) {
        this.numero = numero;
        this.saldo = saldo;
        this.tipoConta = tipoConta;
    }
    
    public synchronized void depositar(double valor) {
        this.saldo += valor;
    }
    
    public synchronized boolean sacar(double valor) {
        if (valor <= saldo) {
            saldo -= valor;
            return true;
        }
        return false;
    }
    
    public double getSaldo() {
        return saldo;
    }
    
    public abstract void exibirDetalhes();
}

// Classe concreta de ContaCorrente
class ContaCorrente extends Contax {
    public ContaCorrente(int numero, double saldo) {
        super(numero, saldo, TipoConta.CORRENTE);
    }
    
    @Override
    public void exibirDetalhes() {
        System.out.println("Conta Corrente - Número: " + numero + ", Saldo: " + saldo);
    }
}

// Classe de Usuário
class Usuariox {
    private String nome;
    private Perfilx perfil;
    Contax conta;
    private Agencia agencia;
    
    public Usuariox(String nome, Perfilx perfil, Contax conta, Agencia agencia) {
        this.nome = nome;
        this.perfil = perfil;
        this.conta = conta;
        this.agencia = agencia;
    }
    
    public StatusLogin login(String nomeEntrada) {
        return this.nome.equals(nomeEntrada) ? StatusLogin.SUCESSO : StatusLogin.FALHA;
    }
    
    public void exibirInformacoes() {
        System.out.println("Usuário: " + nome + ", Perfil: " + perfil.getDescricao() + ", Agência: " + agencia);
        conta.exibirDetalhes();
    }
}

class SistemaBancario {
    public static void mainSBC() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Usuariox> usuarios = new ArrayList<>();

        // Criando usuários fictícios
        usuarios.add(new Usuariox("João", Perfilx.CLIENTE, new ContaCorrente(101, 5000), Agencia.CENTRAL));
        usuarios.add(new Usuariox("Maria", Perfilx.GERENTE, new ContaCorrente(102, 10000), Agencia.FILIAL1));
        usuarios.add(new Usuariox("Carlos", Perfilx.ADMIN, new ContaCorrente(103, 20000), Agencia.FILIAL2));
        usuarios.add(new Usuariox("Juca", Perfilx.ADMIN, new ContaCorrente(104, 00), Agencia.PREMIUM));

        System.out.print("Digite seu nome de usuário: ");
        String nomeEntrada = scanner.nextLine();

        boolean loginSucesso = false;
        Usuariox usuarioLogado = null;

        for (Usuariox usuario : usuarios) {
            if (usuario.login(nomeEntrada) == StatusLogin.SUCESSO) {
                usuarioLogado = usuario;
                loginSucesso = true;
                break;
            }
        }

        if (loginSucesso) {
            System.out.println("Login bem-sucedido!");
            usuarioLogado.exibirInformacoes();
            
            while (true) {
                System.out.println("\nEscolha uma operação: ");
                System.out.println("1 - Depositar");
                System.out.println("2 - Sacar");
                System.out.println("3 - Sair");
                int opcao = scanner.nextInt();
                
                switch (opcao) {
                    case 1:
                        System.out.print("Digite o valor do depósito: ");
                        double deposito = scanner.nextDouble();
                        usuarioLogado.conta.depositar(deposito);
                        System.out.println("Novo saldo: " + usuarioLogado.conta.getSaldo());
                        break;
                    case 2:
                        System.out.print("Digite o valor do saque: ");
                        double saque = scanner.nextDouble();
                        if (usuarioLogado.conta.sacar(saque)) {
                            System.out.println("Saque realizado. Novo saldo: " + usuarioLogado.conta.getSaldo());
                        } else {
                            System.out.println("Saldo insuficiente!");
                        }
                        break;
                    case 3:
                        System.out.println("Saindo...");
                        return;
                    default:
                        System.out.println("Opção inválida.");
                }
            }
        } else {
            System.out.println("Login falhou!");
        }

		// Voutar para o MEUNU
		VoutarMenu voutarMenu = new VoutarMenu();
		voutarMenu.run_CaracteresMenu_Red();
    }
}

class Import_1Vv {
	public static void run_Import_1Vv() {
		System.err.println("\t\t1. \n");
		SistemaBancario.mainSBC();
	}
}

//Corpo da Classe _______________________________

//Enum para definir tipos de células na matriz
enum TipoCelula {
	TEXTO {
		@Override
		public String formatar(String conteudo) {
			return conteudo.toUpperCase();
		}
	},
	NUMERO {
		@Override
		public String formatar(String conteudo) {
			return "#" + conteudo;
		}
	},
	DATA {
		@Override
		public String formatar(String conteudo) {
			return conteudo.replace("/", "-");
		}
	};

	public abstract String formatar(String conteudo);
}

//Classe abstrata para operações de tabela
abstract class Tabela {
	protected ArrayList<ArrayList<String>> matriz = new ArrayList<>();

	public abstract void adicionarLinha(String... valores);

	public abstract void removerLinha(int indice);

	public abstract void exibirTabela();
}

//Implementação concreta de tabela sincronizada
class TabelaSincronizada extends Tabela {

	@Override
	public synchronized void adicionarLinha(String... valores) {
		ArrayList<String> novaLinha = new ArrayList<>(Arrays.asList(valores));
		matriz.add(novaLinha);
	}

	@Override
	public synchronized void removerLinha(int indice) {
		if (indice >= 0 && indice < matriz.size()) {
			matriz.remove(indice);
		}
	}

	@Override
	public synchronized void exibirTabela() {
		for (ArrayList<String> linha : matriz) {
			System.out.println(linha);
		}
	}
}

//Classe principal para execução
class Main {
	public static void main() {
		TabelaSincronizada tabela = new TabelaSincronizada();

		// Adicionando linhas com diferentes tipos de conteúdo
		tabela.adicionarLinha(TipoCelula.TEXTO.formatar("Nome"), TipoCelula.NUMERO.formatar("123"),
				TipoCelula.DATA.formatar("10/02/2025"));
		tabela.adicionarLinha(TipoCelula.TEXTO.formatar("Produto"), TipoCelula.NUMERO.formatar("456"),
				TipoCelula.DATA.formatar("15/03/2025"));

		// Exibição inicial da tabela
		System.out.println("Tabela Inicial:");
		tabela.exibirTabela();

		// Removendo linha
		tabela.removerLinha(0);

		// Exibição após remoção
		System.out.println("Tabela Após Remoção:");
		tabela.exibirTabela();
	}
}

class Import_1V {
	public static void run_Import_1V() {
		System.err.println("\t\t1. \n");
		Main.main();
	}
}

//Corpo da Classe _______________________________

// Enum para definir os perfis dos usuários
enum PerfilC {
	ADMIN, CLIENTE, GERENTE, ANALISTA, INVESTIDOR, SUPERVISOR;
}

// Enum para definir os tipos de contas bancárias
enum TipoContav {
	POUPANCA, CORRENTE, SALARIO, UNIVERSITARIA, PREMIUM, DIGITAL;
}

// Enum para definir status da conta
enum StatusConta {
	ATIVA, BLOQUEADA, ENCERRADA, PENDENTE, ANALISE, SUSPENSA;
}

// Enum para definir tipos de transação
enum TipoTransacao {
	DEPOSITO, SAQUE, TRANSFERENCIA, PAGAMENTO, INVESTIMENTO, PIX;
}

// Enum para definir os tipos de agência
enum Agenciaz {
	CENTRAL, FILIAL, ONLINE, MOVEL, VIP, INTERNACIONAL;
}

// Enum para definir operações bancárias
enum Operacao {
	ABRIR_CONTA, ENCERRAR_CONTA, CONSULTAR_SALDO, REALIZAR_TRANSFERENCIA, SOLICITAR_EMPRESTIMO, MODIFICAR_DADOS;
}

// Classe abstrata Conta
abstract class Contas {
	protected int numero;
	protected double saldo;
	protected String titular;
	protected StatusConta status;
	protected TipoContav tipo;

	public Contas(int numero, double saldo, TipoContav tipo, String titular) {
		this.numero = numero;
		this.saldo = saldo;
		this.tipo = tipo;
		this.titular = titular;
		this.status = StatusConta.ATIVA;
	}

	public synchronized void depositar(double valor) {
		if (valor > 0) {
			saldo += valor;
			System.out.println("Depósito realizado. Novo saldo: " + saldo);
		} else {
			System.out.println("Valor inválido para depósito!");
		}
	}

	public synchronized void sacar(double valor) {
		if (valor > 0 && saldo >= valor) {
			saldo -= valor;
			System.out.println("Saque realizado. Novo saldo: " + saldo);
		} else {
			System.out.println("Saldo insuficiente ou valor inválido!");
		}
	}

	public synchronized double consultarSaldo() {
		return saldo;
	}

	public abstract void infoConta();
}

// Classe ContaBancaria que herda Conta
class ContaBancaria extends Contas {
	String titular;
	private Agenciaz agencia;
	private PerfilC perfil;

	public ContaBancaria(int numero, double saldo, TipoContav tipo, String titular, Agenciaz agencia, PerfilC perfil) {
		super(numero, saldo, tipo, titular);
		this.agencia = agencia;
		this.perfil = perfil;
	}

	@Override
	public void infoConta() {
		System.out.println("Titular: " + titular);
		System.out.println("Agência: " + agencia);
		System.out.println("Perfil: " + perfil);
		System.out.println("Número: " + numero);
		System.out.println("Tipo: " + tipo);
		System.out.println("Saldo: " + saldo);
		System.out.println("Status: " + status);
	}
}

// Classe principal com ArrayList e controle de login
class BancoApp {
	public static void mainC() {
		Scanner scanner = new Scanner(System.in);
		ArrayList<ContaBancaria> contas = new ArrayList<>();

		// Criando contas iniciais
		contas.add(
				new ContaBancaria(1001, 5000.0, TipoContav.CORRENTE, "Carlos Silva", Agenciaz.CENTRAL, PerfilC.CLIENTE));
		contas.add(new ContaBancaria(1002, 15000.0, TipoContav.PREMIUM, "Ana Souza", Agenciaz.VIP, PerfilC.GERENTE));

		while (true) {
			System.out.println("\n=== SISTEMA BANCÁRIO ===");
			System.out.println("1 - Login");
			System.out.println("2 - Criar Conta");
			System.out.println("3 - Sair");
			System.out.print("Escolha: ");
			int opcao = scanner.nextInt();

			if (opcao == 1) {
				System.out.print("Informe o número da conta: ");
				int numeroConta = scanner.nextInt();

				boolean encontrou = false;
				for (ContaBancaria conta : contas) {
					if (conta.numero == numeroConta) {
						encontrou = true;
						System.out.println("\nBem-vindo(a), " + conta.titular);
						conta.infoConta();

						boolean menu = true;
						while (menu) {
							System.out.println("\n1 - Consultar Saldo");
							System.out.println("2 - Depositar");
							System.out.println("3 - Sacar");
							System.out.println("4 - Sair");
							System.out.print("Escolha: ");
							int escolha = scanner.nextInt();

							if (escolha == 1) {
								System.out.println("Saldo: " + conta.consultarSaldo());
							} else if (escolha == 2) {
								System.out.print("Valor para depósito: ");
								double valor = scanner.nextDouble();
								conta.depositar(valor);
							} else if (escolha == 3) {
								System.out.print("Valor para saque: ");
								double valor = scanner.nextDouble();
								conta.sacar(valor);
							} else if (escolha == 4) {
								menu = false;
							} else {
								System.out.println("Opção inválida!");
							}
						}
					}
				}
				if (!encontrou) {
					System.out.println("Conta não encontrada!");
				}
			} else if (opcao == 2) {
				System.out.print("Nome do titular: ");
				scanner.nextLine();
				String titular = scanner.nextLine();
				System.out.print("Número da conta: ");
				int numero = scanner.nextInt();
				System.out.print("Saldo inicial: ");
				double saldo = scanner.nextDouble();
				System.out.println(
						"Escolha a agência (0-CENTRAL, 1-FILIAL, 2-ONLINE, 3-MOVEL, 4-VIP, 5-INTERNACIONAL): ");
				Agenciaz agencia = Agenciaz.values()[scanner.nextInt()];
				System.out.println(
						"Escolha o perfil (0-ADMIN, 1-CLIENTE, 2-GERENTE, 3-ANALISTA, 4-INVESTIDOR, 5-SUPERVISOR): ");
				PerfilC perfil = PerfilC.values()[scanner.nextInt()];

				contas.add(new ContaBancaria(numero, saldo, TipoContav.CORRENTE, titular, agencia, perfil));
				System.out.println("Conta criada com sucesso!");
			} else if (opcao == 3) {
				System.out.println("Saindo...");
				break;
			} else {
				System.out.println("Opção inválida!");
			}
		}
		// Voutar para o MEUNU
		VoutarMenu voutarMenu = new VoutarMenu();
		voutarMenu.run_CaracteresMenu_Red();
	}
}

class Import_2V {
	public static void run_Import_2V() {
		System.err.println("\t\t2. \n");
		BancoApp.mainC();
	}
}

//Corpo da Classe _______________________________

// Enum para diferentes tipos de contas
enum TipoConta5 {
	POUPANCA, CORRENTE, SALARIO;
}

// Enum para status da conta
enum StatusConta5 {
	ATIVA, BLOQUEADA, ENCERRADA;
}

// Enum para tipos de agência
enum TipoAgenciaj {
	DIGITAL, FISICA, PREMIUM;
}

// Enum para comandos do sistema
enum Comandos {
	LOGIN, LOGOUT, VERIFICAR_SALDO, DEPOSITAR, SACAR, ENCERRAR_CONTA;
}

// Enum para nível de acesso
enum NivelAcesso {
	CLIENTE, GERENTE, ADMIN;
}

// Enum para resposta de operação
enum RespostaOperacao {
	SUCESSO, FALHA;
}

// Classe abstrata representando uma conta bancária
abstract class ContaBancaria5 {
	protected String agencia;
	protected String numeroConta;
	protected double saldo;
	protected StatusConta5 status;
	protected TipoConta5 tipo;

	public ContaBancaria5(String agencia, String numeroConta, double saldo, TipoConta5 tipo) {
		this.agencia = agencia;
		this.numeroConta = numeroConta;
		this.saldo = saldo;
		this.tipo = tipo;
		this.status = StatusConta5.ATIVA;
	}

	public synchronized RespostaOperacao depositar(double valor) {
		if (valor > 0) {
			saldo += valor;
			return RespostaOperacao.SUCESSO;
		}
		return RespostaOperacao.FALHA;
	}

	public synchronized RespostaOperacao sacar(double valor) {
		if (valor > 0 && saldo >= valor && status == StatusConta5.ATIVA) {
			saldo -= valor;
			return RespostaOperacao.SUCESSO;
		}
		return RespostaOperacao.FALHA;
	}

	public synchronized double verificarSaldo() {
		return saldo;
	}
}

// Classe concreta
class ContaCorrentes extends ContaBancaria5 {
	public ContaCorrentes(String agencia, String numeroConta, double saldo) {
		super(agencia, numeroConta, saldo, TipoConta5.CORRENTE);
	}
}

class Banco {
	private ArrayList<ContaBancaria5> contas = new ArrayList<>();

	public synchronized void adicionarConta(ContaBancaria5 conta) {
		contas.add(conta);
	}

	public synchronized ContaBancaria5 buscarConta(String agencia, String numeroConta) {
		for (ContaBancaria5 conta : contas) {
			if (conta.agencia.equals(agencia) && conta.numeroConta.equals(numeroConta)) {
				return conta;
			}
		}
		return null;
	}
}

class LoginBanco {
	public static void mainLB() {
		Scanner scanner = new Scanner(System.in);
		Banco banco = new Banco();

		banco.adicionarConta(new ContaCorrentes("1234", "56789", 1000.0));
		banco.adicionarConta(new ContaCorrentes("4321", "98765", 2000.0));

		while (true) {
			System.out.print("Digite a agência: ");
			String agencia = scanner.next();
			System.out.print("Digite a conta: ");
			String numeroConta = scanner.next();

			ContaBancaria5 conta = banco.buscarConta(agencia, numeroConta);

			if (conta != null) {
				System.out.println("Login bem-sucedido!");
				System.out.println("Saldo atual: " + conta.verificarSaldo());
				break;
			} else {
				System.out.println("Conta não encontrada. Tente novamente.");
			}
		}
		// Voutar para o MEUNU
		VoutarMenu voutarMenu = new VoutarMenu();
		voutarMenu.run_CaracteresMenu_Red();
	}
}

class Import_3V {
	public static void run_Import_3V() {
		System.err.println("\t\t3. \n");
		LoginBanco.mainLB();
	}
}