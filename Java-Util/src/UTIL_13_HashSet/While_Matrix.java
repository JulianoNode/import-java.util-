package UTIL_13_HashSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import util.Linhas;

public class While_Matrix {
	public static void While_Run() {
		Linhas linhas = new Linhas();
		String collec = "\n\t Nome \n";
		System.err.println(collec);

		linhas.run_Caracteres();
		Import_1WM.run_Import_1WM();

		linhas.run_Caracteres();
		Import_1WMw.run_Import_1WMw();

		linhas.run_Caracteres();
		Import_2WM.run_Import_2WM();

		linhas.run_Caracteres();
		Import_3WM.run_Import_3WM();

	}
}

enum Perfil11 {
	CLIENTE, GERENTE, ADMIN, SUPORTE, AUDITOR, VISITANTE;

	public String getDescricao() {
		return switch (this) {
		case CLIENTE -> "Cliente Padrão";
		case GERENTE -> "Gerente da Agência";
		case ADMIN -> "Administrador do Sistema";
		case SUPORTE -> "Equipe de Suporte";
		case AUDITOR -> "Auditor de Segurança";
		case VISITANTE -> "Perfil Visitante";
		};
	}
}

enum TipoConta11 {
	CORRENTE, POUPANCA, SALARIO, INVESTIMENTO, DIGITAL, EMPRESARIAL;

	public boolean permiteSaque() {
		return this != SALARIO;
	}
}

enum Operacao11 {
	SAQUE, DEPOSITO, TRANSFERENCIA, CONSULTA, EXTRATO, SAIR;

	public String acao() {
		return switch (this) {
		case SAQUE -> "Realizando Saque...";
		case DEPOSITO -> "Realizando Depósito...";
		case TRANSFERENCIA -> "Realizando Transferência...";
		case CONSULTA -> "Consultando Saldo...";
		case EXTRATO -> "Imprimindo Extrato...";
		case SAIR -> "Saindo do Sistema...";
		};
	}
}

enum Agencia11 {
	A001("São Paulo"), A002("Rio de Janeiro"), A003("Belo Horizonte"), A004("Salvador"), A005("Curitiba"),
	A006("Porto Alegre");

	private final String cidade;

	Agencia11(String cidade) {
		this.cidade = cidade;
	}

	public String getCidade() {
		return cidade;
	}
}

enum StatusLogin {
	SUCESSO, FALHA, BLOQUEADO, EXPIRADO, TENTATIVAS_EXCEDIDAS, LOGADO;

	public boolean isAcessoPermitido() {
		return this == SUCESSO || this == LOGADO;
	}
}

enum Limite {
	PADRAO(1000), PREMIUM(5000), EXECUTIVO(10000), INFINITO(Integer.MAX_VALUE), EMPRESA(20000), ZERO(0);

	private final int valor;

	Limite(int valor) {
		this.valor = valor;
	}

	public int getValor() {
		return valor;
	}
}

abstract class ContaBancariaBase {
	protected String titular;
	protected int numeroConta;
	protected double saldo;
	protected TipoConta11 tipoConta;
	protected Perfil11 perfil;
	protected List<String> extrato = new ArrayList<>();

	public abstract void operacao(int escolha, List<ContaBancaria11> contas);

	public void exibirDados() {
		System.out.println("Titular: " + titular + " | Conta: " + numeroConta + " | Saldo: R$" + saldo);
	}

	public synchronized void deposito(double valor) {
		saldo += valor;
		extrato.add("Depósito de R$" + valor);
		System.out.println("Depósito realizado com sucesso. Novo saldo: R$" + saldo);
	}

	public synchronized void saque(double valor) {
		if (valor <= saldo) {
			saldo -= valor;
			extrato.add("Saque de R$" + valor);
			System.out.println("Saque realizado com sucesso. Novo saldo: R$" + saldo);
		} else {
			System.out.println("Saldo insuficiente.");
		}
	}

	public synchronized void transferencia(ContaBancaria11 destino, double valor) {
		if (valor <= saldo) {
			saldo -= valor;
			destino.saldo += valor;
			extrato.add("Transferência de R$" + valor + " para conta " + destino.numeroConta);
			destino.extrato.add("Transferência recebida de R$" + valor + " da conta " + this.numeroConta);
			System.out.println("Transferência realizada com sucesso.");
		} else {
			System.out.println("Saldo insuficiente para transferência.");
		}
	}

	public void imprimirExtrato() {
		System.out.println("=== EXTRATO ===");
		if (extrato.isEmpty()) {
			System.out.println("Nenhuma operação registrada.");
		} else {
			extrato.forEach(System.out::println);
		}
	}
}

class ContaBancaria11 extends ContaBancariaBase {

	public ContaBancaria11(String titular, int numeroConta, TipoConta11 tipoConta, Perfil11 perfil,
			double saldoInicial) {
		this.titular = titular;
		this.numeroConta = numeroConta;
		this.tipoConta = tipoConta;
		this.perfil = perfil;
		this.saldo = saldoInicial;
	}

	@Override
	public void operacao(int escolha, List<ContaBancaria11> contas) {
		Scanner sc = new Scanner(System.in);
		Operacao11[] operacoes = Operacao11.values();
		if (escolha < 0 || escolha >= operacoes.length) {
			System.out.println("Operação inválida.");
			return;
		}
		Operacao11 op = operacoes[escolha];
		System.out.println(op.acao());

		switch (op) {
		case DEPOSITO -> {
			System.out.print("Valor para depósito: ");
			double valor = sc.nextDouble();
			deposito(valor);
		}
		case SAQUE -> {
			if (tipoConta.permiteSaque()) {
				System.out.print("Valor para saque: ");
				double valor = sc.nextDouble();
				saque(valor);
			} else {
				System.out.println("Tipo de conta não permite saque.");
			}
		}
		case CONSULTA -> System.out.println("Saldo atual: R$" + saldo);

		case TRANSFERENCIA -> {
			System.out.print("Número da conta destino: ");
			int destinoNumero = sc.nextInt();
			ContaBancaria11 destino = contas.stream().filter(c -> c.numeroConta == destinoNumero).findFirst()
					.orElse(null);
			if (destino != null && destino != this) {
				System.out.print("Valor para transferir: ");
				double valor = sc.nextDouble();
				transferencia(destino, valor);
			} else {
				System.out.println("Conta destino inválida.");
			}
		}
		case EXTRATO -> imprimirExtrato();

		case SAIR -> System.out.println("Encerrando sessão...");
		}
	}
}

class BancoSistemaHard {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		HashSet<Integer> contasCadastradas = new HashSet<>();
		ContaBancaria11[] contas = new ContaBancaria11[3];
		contas[0] = new ContaBancaria11("Alice", 101, TipoConta11.CORRENTE, Perfil11.CLIENTE, 1500);
		contas[1] = new ContaBancaria11("Bob", 102, TipoConta11.POUPANCA, Perfil11.GERENTE, 5000);
		contas[2] = new ContaBancaria11("Carlos", 103, TipoConta11.SALARIO, Perfil11.ADMIN, 2000);

		for (ContaBancaria11 conta : contas) {
			contasCadastradas.add(conta.numeroConta);
		}

		while (true) {
			System.out.print("Digite o número da conta para login (ou -1 para sair): ");
			int entrada = sc.nextInt();
			if (entrada == -1)
				break;

			if (contasCadastradas.contains(entrada)) {
				ContaBancaria11 usuario = Arrays.stream(contas).filter(c -> c.numeroConta == entrada).findFirst()
						.orElse(null);

				if (usuario != null) {
					System.out.println("Login bem-sucedido: " + usuario.titular);
					usuario.exibirDados();

					int opcao;
					do {
						System.out.println("\nOperações disponíveis:");
						for (int i = 0; i < Operacao11.values().length; i++) {
							System.out.println(i + " - " + Operacao11.values()[i]);
						}
						System.out.print("Escolha uma operação: ");
						opcao = sc.nextInt();
						usuario.operacao(opcao, null);
					} while (opcao != Operacao11.SAIR.ordinal());
				}
			} else {
				System.out.println("Conta não encontrada.");
			}
		}

		System.out.println("Sistema finalizado.");
	}
}

class Import_1WMw {
	public static void run_Import_1WMw() {
		System.err.println("\t\t1.\n");
		BancoSistemaHard.main(new String[0]);
	}
}
//Corpo da Classe _______________________________

// Enums com métodos
enum AgenciaEnum {
	AGENCIA_001("Centro"), AGENCIA_002("Zona Norte"), AGENCIA_003("Zona Sul");

	private final String descricao;

	AgenciaEnum(String desc) {
		this.descricao = desc;
	}

	public String getDescricao() {
		return descricao;
	}
}

enum PerfilEnum {
	CLIENTE, FUNCIONARIO, GERENTE;

	public boolean podeOperar() {
		return this != FUNCIONARIO;
	}
}

enum TipoContaEnum {
	CORRENTE, POUPANCA, SALARIO;

	public double taxaManutencao() {
		return this == CORRENTE ? 12.5 : this == POUPANCA ? 5.0 : 0;
	}
}

enum OperacaoEnum {
	DEPOSITO, SAQUE, TRANSFERENCIA;

	public String getDescricao() {
		return switch (this) {
		case DEPOSITO -> "Depósito Realizado";
		case SAQUE -> "Saque Efetuado";
		case TRANSFERENCIA -> "Transferência Concluída";
		};
	}
}

enum MoedaEnum {
	REAL(1.0), DOLAR(5.1), EURO(5.6);

	private final double cotacao;

	MoedaEnum(double cotacao) {
		this.cotacao = cotacao;
	}

	public double converter(double valor) {
		return valor * cotacao;
	}
}

enum StatusLoginEnum {
	SUCESSO, FALHA;

	public String mensagem() {
		return this == SUCESSO ? "Login efetuado com sucesso!" : "Falha no login.";
	}
}

// Classe abstrata com switch case
abstract class OperacoesBancarias {
	protected abstract void realizarOperacao(OperacaoEnum operacao, double valor);

	public void exibirMenuOperacoes() {
		System.out.println("1 - Depósito\n2 - Saque\n3 - Transferência");
	}

	public void escolherOperacao(int opcao, double valor) {
		switch (opcao) {
		case 1 -> realizarOperacao(OperacaoEnum.DEPOSITO, valor);
		case 2 -> realizarOperacao(OperacaoEnum.SAQUE, valor);
		case 3 -> realizarOperacao(OperacaoEnum.TRANSFERENCIA, valor);
		default -> System.out.println("Operação inválida.");
		}
	}
}

// Classe principal
class SistemaBancario extends OperacoesBancarias {
	private final ArrayList<String> usuarios = new ArrayList<>();
	private double saldo = 1000.0;

	public SistemaBancario() {
		usuarios.add("admin");
		usuarios.add("cliente1");
		usuarios.add("gerenteXPTO");
	}

	public synchronized StatusLoginEnum login(String usuario) {
		for (String u : usuarios) {
			if (u.equalsIgnoreCase(usuario)) {
				System.out.println(StatusLoginEnum.SUCESSO.mensagem());
				return StatusLoginEnum.SUCESSO;
			}
		}
		System.out.println(StatusLoginEnum.FALHA.mensagem());
		return StatusLoginEnum.FALHA;
	}

	@Override
	protected synchronized void realizarOperacao(OperacaoEnum operacao, double valor) {
		switch (operacao) {
		case DEPOSITO -> {
			saldo += valor;
			System.out.println(operacao.getDescricao() + ": R$" + valor);
		}
		case SAQUE -> {
			if (valor <= saldo) {
				saldo -= valor;
				System.out.println(operacao.getDescricao() + ": R$" + valor);
			} else {
				System.out.println("Saldo insuficiente.");
			}
		}
		case TRANSFERENCIA -> {
			if (valor <= saldo) {
				saldo -= valor;
				System.out.println(operacao.getDescricao() + " de R$" + valor);
			} else {
				System.out.println("Transferência negada. Saldo insuficiente.");
			}
		}
		}
		System.out.println("Saldo atual: R$" + saldo);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		SistemaBancario sistema = new SistemaBancario();

		System.out.print("Digite seu usuário: ");
		String usuario = scanner.nextLine();

		if (sistema.login(usuario) == StatusLoginEnum.SUCESSO) {
			AgenciaEnum agencia = AgenciaEnum.AGENCIA_001;
			PerfilEnum perfil = PerfilEnum.CLIENTE;
			TipoContaEnum conta = TipoContaEnum.CORRENTE;

			System.out.println("Agência: " + agencia.getDescricao());
			System.out.println("Perfil: " + perfil);
			System.out.println("Tipo de Conta: " + conta);
			System.out.println("Taxa de Manutenção: R$" + conta.taxaManutencao());

			sistema.exibirMenuOperacoes();
			System.out.print("Escolha a operação (1/2/3): ");
			int op = scanner.nextInt();
			System.out.print("Digite o valor: ");
			double valor = scanner.nextDouble();

			sistema.escolherOperacao(op, valor);
		}

		System.out.println("Sistema encerrado.");
	}
}

class Import_1WM {
	public static void run_Import_1WM() {
		System.err.println(
				"\t\t1.usuarios.add(\"admin\");2 usuarios.add(\"cliente1\");3 usuarios.add(\"gerenteXPTO\");\n");
		SistemaBancario.main(new String[0]);
	}
}

//Corpo da Classe _______________________________

// Enums com métodos
enum TipoCanal {
	ABERTO, FECHADO, STREAMING, EDUCATIVO, INTERNACIONAL;

	public String descricao() {
		switch (this) {
		case ABERTO:
			return "Canal Aberto - Gratuito";
		case FECHADO:
			return "Canal por Assinatura";
		case STREAMING:
			return "Serviço de Streaming";
		case EDUCATIVO:
			return "Canal Educativo";
		case INTERNACIONAL:
			return "Canal Estrangeiro";
		default:
			return "Desconhecido";
		}
	}
}

enum ClassificacaoEtaria {
	LIVRE, DEZ, DOZE, QUATORZE, DEZOITO;

	public boolean permitido(int idade) {
		return switch (this) {
		case LIVRE -> true;
		case DEZ -> idade >= 10;
		case DOZE -> idade >= 12;
		case QUATORZE -> idade >= 14;
		case DEZOITO -> idade >= 18;
		};
	}
}

enum Idioma {
	PORTUGUES, INGLES, ESPANHOL, FRANCES, ALEMÃO;

	public String idiomaPrincipal() {
		return switch (this) {
		case PORTUGUES -> "Português";
		case INGLES -> "Inglês";
		case ESPANHOL -> "Espanhol";
		case FRANCES -> "Francês";
		case ALEMÃO -> "Alemão";
		};
	}
}

enum ModoTransmissao {
	HD, FULL_HD, SD, ULTRA_HD, STREAM;

	public String qualidade() {
		return switch (this) {
		case HD -> "720p";
		case FULL_HD -> "1080p";
		case SD -> "480p";
		case ULTRA_HD -> "4K";
		case STREAM -> "Variável";
		};
	}
}

enum FaixaHorario {
	MANHA, TARDE, NOITE, MADRUGADA, INTEGRAL;

	public String intervalo() {
		return switch (this) {
		case MANHA -> "06:00 - 12:00";
		case TARDE -> "12:00 - 18:00";
		case NOITE -> "18:00 - 00:00";
		case MADRUGADA -> "00:00 - 06:00";
		case INTEGRAL -> "24h";
		};
	}
}

// Classe abstrata
abstract class CanalTV {
	protected String nome;
	protected TipoCanal tipo;
	protected ClassificacaoEtaria classificacao;
	protected Idioma idioma;
	protected ModoTransmissao transmissao;
	protected FaixaHorario horario;

	public CanalTV(String nome, TipoCanal tipo, ClassificacaoEtaria classificacao, Idioma idioma,
			ModoTransmissao transmissao, FaixaHorario horario) {
		this.nome = nome;
		this.tipo = tipo;
		this.classificacao = classificacao;
		this.idioma = idioma;
		this.transmissao = transmissao;
		this.horario = horario;
	}

	public abstract void exibirInfo();
}

// Canal concreto
class CanalOperacional extends CanalTV {
	public CanalOperacional(String nome, TipoCanal tipo, ClassificacaoEtaria classificacao, Idioma idioma,
			ModoTransmissao transmissao, FaixaHorario horario) {
		super(nome, tipo, classificacao, idioma, transmissao, horario);
	}

	@Override
	public synchronized void exibirInfo() {
		System.out.println("📺 Canal: " + nome);
		System.out.println("Tipo: " + tipo.descricao());
		System.out.println("Classificação: " + classificacao);
		System.out.println("Idioma: " + idioma.idiomaPrincipal());
		System.out.println("Transmissão: " + transmissao.qualidade());
		System.out.println("Horário: " + horario.intervalo());
		System.out.println("-------------");
	}
}

// Programa principal
class SistemaTV {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		HashSet<CanalTV> canais = new HashSet<>();
		int opcao;
		int idade = 0;

		System.out.print("Informe sua idade: ");
		idade = scanner.nextInt();

		while (true) {
			System.out.println("\nMENU:");
			System.out.println("1 - Adicionar Canal");
			System.out.println("2 - Listar Canais");
			System.out.println("3 - Sair");
			System.out.print("Escolha: ");
			opcao = scanner.nextInt();

			if (opcao == 1) {
				scanner.nextLine(); // limpar buffer
				System.out.print("Nome do Canal: ");
				String nome = scanner.nextLine();

				System.out.println("Tipo (0-ABERTO, 1-FECHADO, 2-STREAMING, 3-EDUCATIVO, 4-INTERNACIONAL): ");
				TipoCanal tipo = TipoCanal.values()[scanner.nextInt()];

				System.out.println("Classificação (0-LIVRE, 1-10+, 2-12+, 3-14+, 4-18+): ");
				ClassificacaoEtaria classEtaria = ClassificacaoEtaria.values()[scanner.nextInt()];

				if (!classEtaria.permitido(idade)) {
					System.out.println("🚫 Você não tem idade permitida para este canal.");
					continue;
				}

				System.out.println("Idioma (0-PT, 1-EN, 2-ES, 3-FR, 4-DE): ");
				Idioma idioma = Idioma.values()[scanner.nextInt()];

				System.out.println("Transmissão (0-HD, 1-FULL_HD, 2-SD, 3-4K, 4-STREAM): ");
				ModoTransmissao modo = ModoTransmissao.values()[scanner.nextInt()];

				System.out.println("Faixa Horária (0-MANHA, 1-TARDE, 2-NOITE, 3-MADRUGADA, 4-INTEGRAL): ");
				FaixaHorario horario = FaixaHorario.values()[scanner.nextInt()];

				CanalTV novo = new CanalOperacional(nome, tipo, classEtaria, idioma, modo, horario);
				canais.add(novo);
				System.out.println("✅ Canal adicionado com sucesso!");

			} else if (opcao == 2) {
				if (canais.isEmpty()) {
					System.out.println("Nenhum canal registrado!");
				} else {
					System.out.println("🧾 Lista de Canais:");
					for (CanalTV canal : canais) {
						canal.exibirInfo();
					}
				}

			} else if (opcao == 3) {
				System.out.println("Encerrando...");
				break;
			} else {
				System.out.println("Opção inválida!");
			}
		}
	}
}

class Import_2WM {
	public static void run_Import_2WM() {
		System.err.println("\t\t2.\n");
		SistemaTV.main(new String[0]);
	}
}

//Corpo da Classe _______________________________
//Enums com métodos
enum ProtocoloHTTPS {
	TLS1_2, TLS1_3, SSL3_0, DESCONHECIDO;

	public boolean isSeguro() {
		return this == TLS1_2 || this == TLS1_3;
	}
}

enum EstadoCertificado {
	VALIDO, EXPIRADO, REVOGADO, INDEFINIDO, FALHA;

	public String statusMensagem() {
		return switch (this) {
		case VALIDO -> "Certificado válido!";
		case EXPIRADO -> "Certificado expirado!";
		case REVOGADO -> "Certificado revogado!";
		case INDEFINIDO -> "Estado indefinido.";
		case FALHA -> "Falha ao verificar.";
		};
	}
}

enum TipoRequisicao {
	GET, POST, PUT, DELETE, PATCH;

	public boolean requerSegurancaAlta() {
		return this == POST || this == PUT || this == DELETE;
	}
}

enum ModoConexao {
	SEGURO, INSEGURO, TESTE, BLOQUEADO, ANALISE;

	public String getDescricao() {
		return switch (this) {
		case SEGURO -> "Conexão segura ativa";
		case INSEGURO -> "Conexão insegura detectada";
		case TESTE -> "Conexão em modo de teste";
		case BLOQUEADO -> "Conexão bloqueada por firewall";
		case ANALISE -> "Conexão em análise";
		};
	}
}

enum ResultadoConexao {
	SUCESSO, ERRO, TIMEOUT, NAO_SUPORTADO, INVALIDO;

	public int codigoStatus() {
		return switch (this) {
		case SUCESSO -> 200;
		case ERRO -> 500;
		case TIMEOUT -> 408;
		case NAO_SUPORTADO -> 501;
		case INVALIDO -> 400;
		};
	}
}

//Classe abstrata base
abstract class ConexaoHTTPS {
	protected String host;
	protected ProtocoloHTTPS protocolo;
	protected EstadoCertificado certificado;

	public ConexaoHTTPS(String host, ProtocoloHTTPS protocolo, EstadoCertificado certificado) {
		this.host = host;
		this.protocolo = protocolo;
		this.certificado = certificado;
	}

	public abstract ResultadoConexao conectar(TipoRequisicao tipo);

	public synchronized void log(String msg) {
		System.out.println("[LOG] " + msg);
	}
}

//Classe concreta
class VerificadorHTTPS extends ConexaoHTTPS {
	public VerificadorHTTPS(String host, ProtocoloHTTPS protocolo, EstadoCertificado certificado) {
		super(host, protocolo, certificado);
	}

	@Override
	public synchronized ResultadoConexao conectar(TipoRequisicao tipo) {
		log("Iniciando conexão com: " + host);
		if (!protocolo.isSeguro()) {
			log("Protocolo inseguro detectado: " + protocolo);
			return ResultadoConexao.NAO_SUPORTADO;
		} else if (certificado != EstadoCertificado.VALIDO) {
			log("Problema no certificado: " + certificado.statusMensagem());
			return ResultadoConexao.INVALIDO;
		} else {
			if (tipo.requerSegurancaAlta()) {
				log("Requisição de tipo sensível: " + tipo);
			}
			log("Conexão bem-sucedida com protocolo " + protocolo);
			return ResultadoConexao.SUCESSO;
		}
	}
}

//Main com vetor, while, switch, if, HashSet
class SistemaHTTPS {
	public static void main(String[] args) {
		VerificadorHTTPS[] conexoes = new VerificadorHTTPS[3];
		conexoes[0] = new VerificadorHTTPS("google.com", ProtocoloHTTPS.TLS1_3, EstadoCertificado.VALIDO);
		conexoes[1] = new VerificadorHTTPS("site-antigo.com", ProtocoloHTTPS.SSL3_0, EstadoCertificado.EXPIRADO);
		conexoes[2] = new VerificadorHTTPS("localhost", ProtocoloHTTPS.TLS1_2, EstadoCertificado.REVOGADO);

		TipoRequisicao[] tipos = TipoRequisicao.values();
		HashSet<String> hostsSeguros = new HashSet<>();

		int i = 0;
		while (i < conexoes.length) {
			for (TipoRequisicao tipo : tipos) {
				ResultadoConexao resultado = conexoes[i].conectar(tipo);

				// switch com return
				String mensagem = switch (resultado) {
				case SUCESSO -> {
					hostsSeguros.add(conexoes[i].host);
					yield "✅ Sucesso ao conectar.";
				}
				case ERRO -> "❌ Erro inesperado!";
				case TIMEOUT -> "⌛ Tempo esgotado.";
				case NAO_SUPORTADO -> "🚫 Protocolo não suportado.";
				case INVALIDO -> "🔒 Certificado inválido.";
				};
				System.out.println("[" + conexoes[i].host + "] " + mensagem);
			}
			System.out.println("---------------");
			i++;
		}

		System.out.println("Hosts seguros identificados:");
		for (String h : hostsSeguros) {
			System.out.println("✔ " + h);
		}
	}
}

class Import_3WM {
	public static void run_Import_3WM() {
		System.err.println("\t\t3. \n");
		SistemaHTTPS.main(new String[0]);
	}
}
