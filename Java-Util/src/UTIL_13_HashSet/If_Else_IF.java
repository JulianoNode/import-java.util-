package UTIL_13_HashSet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

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


// Enum representando os tipos de serviço do pedreiro com valores e métodos
enum TipoServico {
    ALVENARIA(80.0) {
        @Override
        public double calcularPagamento(int dias) {
            return getValorDiaria() * dias;
        }
    },
    REVESTIMENTO(95.0) {
        @Override
        public double calcularPagamento(int dias) {
            return getValorDiaria() * dias * 1.1; // Acréscimo por especialização
        }
    },
    PINTURA(60.0) {
        @Override
        public double calcularPagamento(int dias) {
            return getValorDiaria() * dias;
        }
    };

    private final double valorDiaria;

    TipoServico(double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    public abstract double calcularPagamento(int dias);
}

// Classe abstrata representando um serviço com cálculo de pagamento
abstract class Servico {
    private int diasTrabalhados;

    public Servico(int diasTrabalhados) {
        this.diasTrabalhados = diasTrabalhados;
    }

    public int getDiasTrabalhados() {
        return diasTrabalhados;
    }

    // Método sincronizado para obter o tipo de serviço (simulação de acesso seguro)
    public synchronized String getTipoServico() {
        if (this instanceof AlvenariaServico) {
            return "Alvenaria";
        } else if (this instanceof RevestimentoServico) {
            return "Revestimento";
        } else if (this instanceof PinturaServico) {
            return "Pintura";
        }
        return "Serviço Desconhecido";
    }

    // Método abstrato para calcular o pagamento (será implementado nas subclasses)
    public abstract double calcularPagamentoTotal();
}

// Subclasses concretas de Servico, cada uma associada a um TipoServico
class AlvenariaServico extends Servico {
    private final TipoServico tipo = TipoServico.ALVENARIA;

    public AlvenariaServico(int diasTrabalhados) {
        super(diasTrabalhados);
    }

    @Override
    public double calcularPagamentoTotal() {
        return tipo.calcularPagamento(getDiasTrabalhados());
    }
}

class RevestimentoServico extends Servico {
    private final TipoServico tipo = TipoServico.REVESTIMENTO;

    public RevestimentoServico(int diasTrabalhados) {
        super(diasTrabalhados);
    }

    @Override
    public double calcularPagamentoTotal() {
        return tipo.calcularPagamento(getDiasTrabalhados());
    }
}

class PinturaServico extends Servico {
    private final TipoServico tipo = TipoServico.PINTURA;

    public PinturaServico(int diasTrabalhados) {
        super(diasTrabalhados);
    }

    @Override
    public double calcularPagamentoTotal() {
        return tipo.calcularPagamento(getDiasTrabalhados());
    }
}

class DiariaPedreiroHardcore {
    public static void main(String[] args) {
        Set<Servico> servicosRealizados = new HashSet<>();
        servicosRealizados.add(new AlvenariaServico(10));
        servicosRealizados.add(new RevestimentoServico(5));
        servicosRealizados.add(new PinturaServico(8));
        servicosRealizados.add(new AlvenariaServico(7)); // Adicionando outro serviço

        double totalAPagar = 0;

        for (Servico servico : servicosRealizados) {
            System.out.println("Tipo de Serviço: " + servico.getTipoServico());
            System.out.println("Dias Trabalhados: " + servico.getDiasTrabalhados());

            // Usando instanceof para um tratamento específico (embora o polimorfismo seja preferível)
            if (servico instanceof AlvenariaServico) {
                System.out.println("Calculando pagamento de alvenaria...");
            } else if (servico instanceof RevestimentoServico) {
                System.out.println("Calculando pagamento de revestimento com taxa extra...");
            } else if (servico instanceof PinturaServico) {
                System.out.println("Calculando pagamento de pintura...");
            }

            // Usando switch case para ações baseadas no tipo de serviço (obtido de forma sincronizada)
            switch (servico.getTipoServico()) {
                case "Alvenaria":
                    totalAPagar += servico.calcularPagamentoTotal();
                    break;
                case "Revestimento":
                    totalAPagar += servico.calcularPagamentoTotal();
                    break;
                case "Pintura":
                    totalAPagar += servico.calcularPagamentoTotal();
                    break;
                default:
                    System.out.println("Serviço não reconhecido para cálculo.");
            }
            System.out.println("Pagamento deste serviço: R$" + String.format("%.2f", servico.calcularPagamentoTotal()));
            System.out.println("--------------------");
        }

        System.out.println("Valor total a pagar ao pedreiro (por 10 dias - SIMULAÇÃO): "
        		+ "R$" + String.format("%.2f", totalAPagar * (10.0/ (double) servicosRealizados.stream().mapToInt(Servico::getDiasTrabalhados).sum() * servicosRealizados.size())));
    }
}
class Import_1IF {
	public static void run_Import_1IF() {
		System.err.println("\t\t1. \n");
		DiariaPedreiroHardcore.main(new String[1]);
	}
}

//Corpo da Classe _______________________________

enum Perfil9 {
	CLIENTE, GERENTE;

	public String getDescricao() {
		switch (this) {
		case CLIENTE:
			return "Acesso para clientes.";
		case GERENTE:
			return "Acesso para gerentes.";
		default:
			return "Perfil desconhecido.";
		}
	}
}

abstract class ContaBancaria9 {
	protected String agencia;
	protected String conta;
	protected double saldo;
	protected List<String> extrato;

	public ContaBancaria9(String agencia, String conta) {
		this.agencia = agencia;
		this.conta = conta;
		this.saldo = 0.0;
		this.extrato = new ArrayList<>();
		this.extrato.add("Conta criada em: " + java.time.LocalDateTime.now());
	}

	public String getAgencia() {
		return agencia;
	}

	public String getConta() {
		return conta;
	}

	public synchronized double getSaldo() {
		return saldo;
	}

	public synchronized void sacar(double valor) {
		if (valor > 0 && saldo >= valor) {
			saldo -= valor;
			extrato.add("Saque de: " + valor + " em: " + java.time.LocalDateTime.now());
			System.out.println("Saque realizado com sucesso.");
		} else {
			System.out.println("Saldo insuficiente ou valor inválido para saque.");
		}
	}

	public synchronized void depositar(double valor) {
		if (valor > 0) {
			saldo += valor;
			extrato.add("Depósito de: " + valor + " em: " + java.time.LocalDateTime.now());
			System.out.println("Depósito realizado com sucesso.");
		} else {
			System.out.println("Valor inválido para depósito.");
		}
	}

	public synchronized void transferir(double valor, ContaBancaria9 destino) {
		if (valor > 0 && saldo >= valor) {
			this.sacarInterno(valor);
			destino.depositarInterno(valor);
			extrato.add("Transferência de: " + valor + " para " + destino.getAgencia() + "/" + destino.getConta()
					+ " em: " + java.time.LocalDateTime.now());
			System.out.println("Transferência realizada com sucesso.");
		} else {
			System.out.println("Saldo insuficiente ou valor inválido para transferência.");
		}
	}

	protected synchronized void sacarInterno(double valor) {
		this.saldo -= valor;
	}

	protected synchronized void depositarInterno(double valor) {
		this.saldo += valor;
	}

	public synchronized void consultarSaldo() {
		System.out.println("Saldo atual: " + saldo);
	}

	public synchronized void emitirExtrato() {
		System.out.println("\n--- Extrato Bancário ---");
		for (String movimento : extrato) {
			System.out.println(movimento);
		}
		System.out.println("-----------------------");
	}
}

class ContaCliente extends ContaBancaria9 {
	public ContaCliente(String agencia, String conta) {
		super(agencia, conta);
	}
}

class ContaGerente extends ContaBancaria9 {
	public ContaGerente(String agencia, String conta) {
		super(agencia, conta);
	}

	public synchronized void realizarOperacaoGerencial() {
		System.out.println("Operação gerencial realizada.");
		extrato.add("Operação gerencial realizada em: " + java.time.LocalDateTime.now());
	}
}

class GerenciadorContas {
	private HashSet<ContaBancaria9> contas;

	public GerenciadorContas() {
		this.contas = new HashSet<>();
		// Inicializando algumas contas para teste
		contas.add(new ContaCliente("123", "456"));
		contas.add(new ContaGerente("789", "012"));
	}

	public ContaBancaria9 autenticar(Perfil9 perfil, String agencia, String conta) {
		for (ContaBancaria9 c : contas) {
			if (c.getAgencia().equals(agencia) && c.getConta().equals(conta)) {
				if (perfil == Perfil9.CLIENTE && c instanceof ContaCliente) {
					return c;
				} else if (perfil == Perfil9.GERENTE && c instanceof ContaGerente) {
					return c;
				}
			}
		}
		return null;
	}

	public ContaBancaria9 buscarConta(String agencia, String conta) {
		for (ContaBancaria9 c : contas) {
			if (c.getAgencia().equals(agencia) && c.getConta().equals(conta)) {
				return c;
			}
		}
		return null;
	}
}

class SistemaBancario65 {

	public static void main(String[] args) {
		GerenciadorContas gerenciador = new GerenciadorContas();
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("\nBem-vindo ao Sistema Bancário!");
			System.out.println("Digite seu Perfil (CLIENTE ou GERENTE):");
			String perfilInput = scanner.nextLine().toUpperCase();

			Perfil9 perfil;
			try {
				perfil = Perfil9.valueOf(perfilInput);
			} catch (IllegalArgumentException e) {
				System.out.println("Perfil inválido. Tente novamente.");
				continue;
			}

			System.out.println("Digite sua Agência:");
			String agencia = scanner.nextLine();
			System.out.println("Digite sua Conta:");
			String conta = scanner.nextLine();

			ContaBancaria9 contaLogada = gerenciador.autenticar(perfil, agencia, conta);

			if (contaLogada != null) {
				System.out.println("\nLogin realizado com sucesso!");
				menu(scanner, contaLogada, gerenciador);
			} else {
				System.out.println("Credenciais inválidas. Tente novamente.");
			}
		}
	}

	public static void menu(Scanner scanner, ContaBancaria9 conta, GerenciadorContas gerenciador) {
		while (true) {
			System.out.println("\nEscolha uma operação:");
			System.out.println("1 - SAQUE");
			System.out.println("2 - DEPOSITO");
			System.out.println("3 - TRANSFERENCIA");
			System.out.println("4 - CONSULTA");
			System.out.println("5 - EXTRATO");
			System.out.println("6 - SAIR");

			String opcao = scanner.nextLine();

			switch (opcao) {
			case "1":
				System.out.println("Digite o valor do saque:");
				double valorSaque = scanner.nextDouble();
				scanner.nextLine(); // Consumir a quebra de linha
				conta.sacar(valorSaque);
				break;
			case "2":
				System.out.println("Digite o valor do depósito:");
				double valorDeposito = scanner.nextDouble();
				scanner.nextLine(); // Consumir a quebra de linha
				conta.depositar(valorDeposito);
				break;
			case "3":
				System.out.println("Digite a agência de destino:");
				String agenciaDestino = scanner.nextLine();
				System.out.println("Digite a conta de destino:");
				String contaDestino = scanner.nextLine();
				System.out.println("Digite o valor da transferência:");
				double valorTransferencia = scanner.nextDouble();
				scanner.nextLine(); // Consumir a quebra de linha
				ContaBancaria9 contaDestinatario = gerenciador.buscarConta(agenciaDestino, contaDestino);
				if (contaDestinatario != null) {
					conta.transferir(valorTransferencia, contaDestinatario);
				} else {
					System.out.println("Conta de destino não encontrada.");
				}
				break;
			case "4":
				conta.consultarSaldo();
				break;
			case "5":
				conta.emitirExtrato();
				break;
			case "6":
				System.out.println("Obrigado por utilizar nosso sistema!");
				return;
			default:
				System.out.println("Opção inválida. Tente novamente.");
			}
		}
	}
}

class Import_2IF {
	public static void run_Import_2IF() {
		System.err.println("\t\t2.\n");
		SistemaBancario65.main(new String[2]);
	}
}

//Corpo da Classe _______________________________

// Enumeração para os perfis de obra com seus valores diários
enum PerfilObra1 {
	PEDREIRO(120.00), CARPINTEIRO(110.00), AZULEJISTA(105.00), PINTOR(95.00), ENCANADOR(100.00), ELETRICISTA(115.00),
	SERVENTE(80.00);

	private final double valorDiaria;

	PerfilObra1(double valorDiaria) {
		this.valorDiaria = valorDiaria;
	}

	public double getValorDiaria() {
		return valorDiaria;
	}

	public String getDescricao() {
		// Método para obter uma descrição mais amigável do perfil
		return name().toLowerCase();
	}
}

// Classe abstrata para representar um Usuário
abstract class Usuario1 {
	private String login;
	private String senha;
	private PerfilObra1 perfil;

	public Usuario1(String login, String senha, PerfilObra1 perfil) {
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

	public PerfilObra1 getPerfil() {
		return perfil;
	}

	// Método abstrato para exibir informações específicas do usuário
	public abstract void exibirInformacoes();

	// Método a ser implementado para sincronização (apenas um esqueleto aqui)
	public synchronized void sincronizarDados() {
		System.out.println("Método de sincronização chamado para o usuário: " + login);
		// Lógica de sincronização seria implementada aqui
	}
}

// Classe concreta para um Usuário de Obra
class UsuarioObra extends Usuario1 {
	public UsuarioObra(String login, String senha, PerfilObra1 perfil) {
		super(login, senha, perfil);
	}

	@Override
	public void exibirInformacoes() {
		System.out.println("Login: " + getLogin());
		System.out.println(
				"Perfil de Obra: " + getPerfil().getDescricao() + " (Diária: R$" + getPerfil().getValorDiaria() + ")");
	}
}

class SistemaLoginObra1 {
	private static HashSet<Usuario1> usuariosCadastrados = new HashSet<>();

	public static void main(String[] args) {
		// Cadastrando alguns usuários iniciais
		usuariosCadastrados.add(new UsuarioObra("pedreiro123", "senha123", PerfilObra1.PEDREIRO));
		usuariosCadastrados.add(new UsuarioObra("carpinteiro456", "senha456", PerfilObra1.CARPINTEIRO));
		usuariosCadastrados.add(new UsuarioObra("servente789", "senha789", PerfilObra1.SERVENTE));

		Scanner scanner = new Scanner(System.in);

		System.out.println("Bem-vindo ao Sistema de Login de Obra!");

		int opcao = 0;
		boolean logado = false;
		while (!logado) {
			try {
				System.out.print("Digite seu login: ");
				String loginDigitado = scanner.nextLine();

				System.out.print("Digite sua senha: ");
				String senhaDigitada = scanner.nextLine();

				Usuario1 usuarioLogado = autenticarUsuario(loginDigitado, senhaDigitada);

				if (usuarioLogado != null) {
					System.out.println("\nLogin realizado com sucesso!");
					usuarioLogado.exibirInformacoes();
					usuarioLogado.sincronizarDados(); // Chamando o método de sincronização
					logado = true; // Define que o login foi bem-sucedido
				} else {
					System.out.println("\nLogin falhou. Usuário ou senha incorretos.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Entrada inválida. Por favor, tente novamente.");
				scanner.nextLine(); // Limpa o buffer
				opcao = 0;
			}
		}
		while (!logado)
			;
	}

	public static Usuario1 autenticarUsuario(String login, String senha) {
		for (Usuario1 usuario : usuariosCadastrados) {
			if (usuario instanceof UsuarioObra usuarioObra) {
				if (usuarioObra.getLogin().equals(login) && usuarioObra.getSenha().equals(senha)) {
					return usuarioObra;
				}
			} else {
				System.out.println("Tipo de usuário desconhecido.");
			}
		}
		return null;
	}
}

class Import_3IF {
	public static void run_Import_3IF() {
		System.err.println("\t\t3. \n");
		SistemaLoginObra1.main(new String[3]);
	}
}
