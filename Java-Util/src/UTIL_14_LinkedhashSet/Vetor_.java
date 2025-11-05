package UTIL_14_LinkedhashSet;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

import util.Linhas;

public class Vetor_ {
	public static void Vetor_Run() {

		Linhas linhas = new Linhas();
		String collec = "\n\t Nome \n";
		System.err.println(collec);

		linhas.run_Caracteres();
		Import_1V.run_Import_1V();

		linhas.run_Caracteres();
		Import_2V.run_Import_2V();

		linhas.run_Caracteres();
		Import_3V.run_Import_3V();
	}
}

//Corpo da Classe _______________________________
//====================== ENUM com Métodos ==========================
enum TipoConta {
	CORRENTE(1.5), POUPANCA(1.0), SALARIO(0.5);

	private double taxa;

	TipoConta(double taxa) {
		this.taxa = taxa;
	}

	public double aplicarTaxa(double valor) {
		return valor - (valor * (taxa / 100));
	}
}

//====================== CLASSE ABSTRATA ==========================
abstract class ContaBancaria {
	protected String titular;
	protected double saldo;
	protected TipoConta tipo;

	public ContaBancaria(String titular, double saldo, TipoConta tipo) {
		this.titular = titular;
		this.saldo = saldo;
		this.tipo = tipo;
	}

	// método abstrato
	public abstract void exibirInformacoes();

	// método com switch case e try-catch
	public void operacao(int opcao, double valor) {
		try {
			switch (opcao) {
			case 1 -> depositar(valor);
			case 2 -> sacar(valor);
			case 3 -> System.out.println("Saldo: R$ " + saldo);
			default -> System.out.println("Opção inválida!");
			}
		} catch (Exception e) {
			System.out.println("Erro na operação: " + e.getMessage());
		}
	}

	public synchronized void depositar(double valor) {
		saldo += valor;
	}

	public synchronized void sacar(double valor) throws Exception {
		if (saldo >= valor) {
			saldo -= valor;
		} else {
			throw new Exception("Saldo insuficiente!");
		}
	}
}

//====================== SUBCLASSE (com instanceof) ==========================
class ContaCorrente extends ContaBancaria {
	public ContaCorrente(String titular, double saldo) {
		super(titular, saldo, TipoConta.CORRENTE);
	}

	@Override
	public void exibirInformacoes() {
		System.out.println("Conta Corrente - Titular: " + titular + " | Saldo: R$ " + saldo);
	}
}

class ContaPoupanca extends ContaBancaria {
	public ContaPoupanca(String titular, double saldo) {
		super(titular, saldo, TipoConta.POUPANCA);
	}

	@Override
	public void exibirInformacoes() {
		System.out.println("Conta Poupança - Titular: " + titular + " | Saldo: R$ " + saldo);
	}
}

//====================== CLASSE PRINCIPAL ==========================
class SistemaBancarioHard {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		LinkedHashSet<ContaBancaria> contas = new LinkedHashSet<>();

		// Vetor de contas
		ContaBancaria[] vetorContas = new ContaBancaria[3];
		vetorContas[0] = new ContaCorrente("Alice", 1500);
		vetorContas[1] = new ContaPoupanca("Bob", 2000);
		vetorContas[2] = new ContaCorrente("Carlos", 1000);

		// Adicionando no LinkedHashSet (garante ordem e não repete)
		contas.addAll(Arrays.asList(vetorContas));

		// Exibindo contas com instanceof
		for (ContaBancaria c : contas) {
			if (c instanceof ContaCorrente) {
				System.out.println(">> Conta Corrente Detectada");
			} else if (c instanceof ContaPoupanca) {
				System.out.println(">> Conta Poupança Detectada");
			}
			c.exibirInformacoes();
		}

		// Matriz de pagamentos [3 clientes][2 meses]
		double[][] pagamentos = { { 200, 150 }, { 300, 100 }, { 50, 400 } };

		System.out.println("\n=== Processando Pagamentos ===");
		ReentrantLock lock = new ReentrantLock();

		for (int i = 0; i < vetorContas.length; i++) {
			for (int j = 0; j < pagamentos[i].length; j++) {
				double valor = pagamentos[i][j];
				lock.lock();
				try {
					vetorContas[i].depositar(valor);
					System.out.println("Depósito de R$" + valor + " realizado em " + vetorContas[i].titular);
				} finally {
					lock.unlock();
				}
			}
		}

		// Loop while e do-while de menu
		int opcao;
		do {
			System.out.println("\n=== MENU BANCO ===");
			System.out.println("1 - Depositar");
			System.out.println("2 - Sacar");
			System.out.println("3 - Ver Saldo");
			System.out.println("4 - Sair");
			System.out.print("Escolha: ");
			opcao = sc.nextInt();

			if (opcao >= 1 && opcao <= 3) {
				System.out.print("Escolha conta (0-Alice, 1-Bob, 2-Carlos): ");
				int idx = sc.nextInt();

				if (idx >= 0 && idx < vetorContas.length) {
					System.out.print("Valor: ");
					double valor = sc.nextDouble();

					vetorContas[idx].operacao(opcao, valor);
				} else {
					System.out.println("Conta inválida!");
				}
			} else if (opcao != 4) {
				System.out.println("Opção inválida!");
			}

		} while (opcao != 4);

		System.out.println("\n=== Saldos Finais ===");
		for (ContaBancaria c : vetorContas) {
			c.exibirInformacoes();
		}

		// 🚫 NÃO use sc.close() se for continuar lendo System.in depois!
		if (sc != null) {
			sc = null; // libera o scanner sem fechar System.in
		}

		System.out.println("\nScanner finalizado com segurança. Agora pode seguir para outro bloco de código...");
		// Aqui você pode chamar outro método, classe, ou continuar a execução:
		outroBlocoDeCodigo();
	}

	static void outroBlocoDeCodigo() {
		System.out.println("Executando outro bloco de código normalmente...");
		// Aqui você poderia criar outro Scanner se quisesse
		// Scanner sc2 = new Scanner(System.in);
		// ...
	}
}

class Import_1V {
	public static void run_Import_1V() {
		System.err.println("\t\t1. \n");
		SistemaBancarioHard.main(new String[1]);
	}
}

//Corpo da Classe _______________________________
//Enum 1: Tipos de pesquisa
enum TipoPesquisa {
	WEB("Pesquisa na Web"), IMAGEM("Pesquisa de Imagens"), VIDEO("Pesquisa de Vídeos"), NOTICIA("Pesquisa de Notícias"),
	CODIGO("Pesquisa de Códigos"), ACADEMICA("Pesquisa Acadêmica");

	private final String descricao;

	TipoPesquisa(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}

//Enum 2: Nível de precisão
enum NivelPrecisao {
	BAIXA(0.5), MEDIA(0.75), ALTA(0.9), ULTRA(0.99), ABSOLUTA(1.0), EXPERIMENTAL(0.3);

	private final double valor;

	NivelPrecisao(double valor) {
		this.valor = valor;
	}

	public double getValor() {
		return valor;
	}
}

//Enum 3: Estado da IA
enum EstadoIA {
	INICIALIZANDO, ATIVA, PESQUISANDO, ERRO, FINALIZADA, REINICIANDO;

	public boolean estaAtiva() {
		return this == ATIVA || this == PESQUISANDO;
	}
}

//Enum 4: Tipo de erro
enum TipoErro {
	REDE, PERMISSAO, TIMEOUT, DADOS, DESCONHECIDO, NENHUM, EXPERIMENTAL;

	public static TipoErro aleatorio() {
		TipoErro[] valores = values();
		return valores[new Random().nextInt(valores.length)];
	}
}

//Enum 5: Modo de execução
enum ModoExecucao {
	NORMAL, SILENCIOSO, DEBUG, TURBO, TESTE, MANUAL;

	public void exibir() {
		System.out.println("Modo atual: " + this);
	}
}

//Enum 6: Tipo de Resultado
enum TipoResultado {
	TEXTO, IMAGEM, VIDEO, LINK, CODIGO, ERRO;

	public void exibirResultado(String dado) {
		System.out.println("[" + this + "] -> " + dado);
	}
}

//===================== INTERFACE ==========================

interface ComportamentoIA {
	void iniciar();

	void parar();

	void pesquisar(String termo, TipoPesquisa tipo);
}

//===================== CLASSE ABSTRATA ==========================

abstract class InteligenciaArtificial implements ComportamentoIA {
	protected EstadoIA estado = EstadoIA.INICIALIZANDO;
	protected final LinkedHashSet<String> historico = new LinkedHashSet<>();

	public abstract void processarResultado(String termo, TipoResultado tipo);

	public void exibirHistorico() {
		System.out.println("\n=== Histórico de Pesquisas ===");
		for (String s : historico) {
			System.out.println(s);
		}
	}

	public void mudarEstado(EstadoIA novo) {
		estado = novo;
		System.out.println("IA -> Novo estado: " + estado);
	}

	@Override
	public void iniciar() {
		mudarEstado(EstadoIA.ATIVA);
		System.out.println("IA Iniciada com sucesso!");
	}

	@Override
	public void parar() {
		mudarEstado(EstadoIA.FINALIZADA);
		System.out.println("IA Finalizada!");
	}
}

//===================== CLASSE CONCRETA ==========================

class IAPesquisaAvancada extends InteligenciaArtificial implements Runnable {
	private final Random random = new Random();
	private final ReentrantLock lock = new ReentrantLock();
	private final String termo;
	private final TipoPesquisa tipo;

	public IAPesquisaAvancada(String termo, TipoPesquisa tipo) {
		this.termo = termo;
		this.tipo = tipo;
	}

	@Override
	public synchronized void pesquisar(String termo, TipoPesquisa tipo) {
		try {
			mudarEstado(EstadoIA.PESQUISANDO);
			historico.add(tipo.getDescricao() + ": " + termo);

			System.out.println("Pesquisando [" + termo + "] em " + tipo.getDescricao());
			Thread.sleep(random.nextInt(400) + 100);

			TipoErro erro = TipoErro.aleatorio();
			if (erro != TipoErro.NENHUM && erro != TipoErro.EXPERIMENTAL) {
				throw new Exception("Erro: " + erro);
			}

			// Simulando uma matriz de resultados
			String[][] matrizResultados = { { "Java", "GPT", "Neural Network" },
					{ "IA", "Deep Learning", "Pesquisa Avançada" } };

			for (int i = 0; i < matrizResultados.length; i++) {
				for (int j = 0; j < matrizResultados[i].length; j++) {
					if (matrizResultados[i][j].toLowerCase().contains(termo.toLowerCase())) {
						processarResultado(matrizResultados[i][j], TipoResultado.TEXTO);
					}
				}
			}

		} catch (Exception e) {
			mudarEstado(EstadoIA.ERRO);
			System.out.println("Falha na pesquisa: " + e.getMessage());
		} finally {
			mudarEstado(EstadoIA.ATIVA);
		}
	}

	@Override
	public void processarResultado(String termo, TipoResultado tipo) {
		if (this instanceof IAPesquisaAvancada) {
			tipo.exibirResultado("Resultado encontrado: " + termo);
		}
	}

	// Método principal de execução com while, for e switch
	public void executarCiclo(ModoExecucao modo, NivelPrecisao precisao) {
		int ciclo = 0;
		while (ciclo < 2) {
			ciclo++;
			System.out.println("\nCiclo " + ciclo + " | Precisão: " + precisao.getValor());
			switch (modo) {
			case NORMAL -> pesquisar("Java", TipoPesquisa.WEB);
			case DEBUG -> pesquisar("GPT", TipoPesquisa.CODIGO);
			case TURBO -> {
				for (int i = 0; i < 2; i++) {
					pesquisar("IA", TipoPesquisa.IMAGEM);
				}
			}
			default -> System.out.println("Modo desconhecido...");
			}

			if (precisao.getValor() < 0.5) {
				System.out.println("Precisão muito baixa! Recalculando...");
			} else if (precisao.getValor() > 0.9) {
				System.out.println("Precisão máxima atingida!");
			} else {
				System.out.println("Precisão adequada.");
			}
		}
	}

	// THREAD
	@Override
	public void run() {
		lock.lock();
		try {
			System.out.println(Thread.currentThread().getName() + " iniciou uma pesquisa paralela...");
			pesquisar(termo, tipo);
		} finally {
			lock.unlock();
		}
	}
}

//===================== CLASSE MAIN ==========================

class IAPesquisaUltraHard {
	public static void main(String[] args) {
		System.out.println("=== IA de Pesquisa Ultra Hard ===\n");

		// Criando múltiplas IAs em threads
		IAPesquisaAvancada ia1 = new IAPesquisaAvancada("Java", TipoPesquisa.WEB);
		IAPesquisaAvancada ia2 = new IAPesquisaAvancada("GPT", TipoPesquisa.CODIGO);
		IAPesquisaAvancada ia3 = new IAPesquisaAvancada("IA", TipoPesquisa.IMAGEM);

		ia1.iniciar();

		Thread t1 = new Thread(ia1, "Thread-IA1");
		Thread t2 = new Thread(ia2, "Thread-IA2");
		Thread t3 = new Thread(ia3, "Thread-IA3");

		// Executando threads simultaneamente
		t1.start();
		t2.start();
		t3.start();

		// Aguardar todas terminarem
		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (InterruptedException e) {
			System.out.println("Erro ao aguardar threads: " + e.getMessage());
		}

		// Executar ciclo adicional de pesquisa
		ia1.executarCiclo(ModoExecucao.TURBO, NivelPrecisao.ALTA);

		ia1.exibirHistorico();
		ia1.parar();

		System.out.println("\n=== Execução Finalizada ===");
	}
}

class Import_2V {
	public static void run_Import_2V() {
		System.err.println("\t\t2. \n");
		IAPesquisaUltraHard.main(new String[2]);
	}
}
//_______________________________ Corpo da Classe _______________________________

enum TipoPedreiro {
	AJUDANTE(100), MEIO_OFICIAL(150), OFICIAL(200), ENCANADOR(180), ELETRICISTA(170), PEDREIRO_MESTRE(250);

	private final int valorDiaria;

	TipoPedreiro(int valor) {
		this.valorDiaria = valor;
	}

	public int getValorDiaria() {
		return valorDiaria;
	}
}

enum TipoServico {
	ALVENARIA, PINTURA, HIDRAULICA, ELETRICA, ACABAMENTO, LAJE;

	public String descricao() {
		return name().toLowerCase();
	}
}

enum Regiao {
	NORTE, SUL, LESTE, OESTE, CENTRO, ZONA_RURAL;

	public double fatorRegional() {
		return switch (this) {
		case ZONA_RURAL -> 1.2;
		case CENTRO -> 1.3;
		default -> 1.0;
		};
	}
}

enum Turno {
	MANHA, TARDE, NOITE, INTEGRAL, NOTURNO, PLANTAO;

	public boolean adicionalNoturno() {
		return this == NOITE || this == NOTURNO;
	}
}

enum Equipamento {
	BETONEIRA, ANDAIME, MISTURADOR, MARTELETE, GUINCHO, MAQUINA_CORTE;

	public double custoExtra() {
		return switch (this) {
		case MARTELETE, GUINCHO -> 80.0;
		default -> 40.0;
		};
	}
}

enum Experiencia {
	JUNIOR, PLENO, SENIOR, MASTER, ESPECIALISTA, ENGENHEIRO;

	public double fatorExperiencia() {
		return switch (this) {
		case MASTER, ESPECIALISTA, ENGENHEIRO -> 1.5;
		case SENIOR -> 1.3;
		default -> 1.0;
		};
	}
}

abstract class DiariaBase {
	protected int dias;
	protected TipoPedreiro tipo;
	protected double total;

	public abstract void calcularTotal();

	public void mostrarResumo() {
		System.out.printf("Tipo: %-15s | Dias: %d | Total: R$ %.2f%n", tipo, dias, total);
	}

	public synchronized void switchCalculo(TipoServico servico) {
		switch (servico) {
		case ALVENARIA -> total += 50;
		case PINTURA -> total += 30;
		case LAJE -> total += 70;
		case ELETRICA, HIDRAULICA -> total += 60;
		case ACABAMENTO -> total += 40;
		default -> total += 20;
		}
	}
}

class DiariaPedreiro extends DiariaBase {
	private Regiao regiao;
	private Turno turno;
	private Equipamento[] equipamentos;
	private Experiencia experiencia;

	public DiariaPedreiro(TipoPedreiro tipo, int dias, Regiao regiao, Turno turno, Equipamento[] equipamentos,
			Experiencia experiencia) {
		this.tipo = tipo;
		this.dias = dias;
		this.regiao = regiao;
		this.turno = turno;
		this.equipamentos = equipamentos;
		this.experiencia = experiencia;
	}

	@Override
	public synchronized void calcularTotal() {
		total = tipo.getValorDiaria() * dias;
		total *= regiao.fatorRegional();
		total *= experiencia.fatorExperiencia();

		if (turno.adicionalNoturno()) {
			total += dias * 30;
		}

		for (Equipamento eq : equipamentos) {
			total += eq.custoExtra();
		}
	}
}

class SistemaDiarias {
	public static void main(String[] args) {
		HashSet<DiariaBase> registros = new HashSet<>();

		Equipamento[][] matrizEquipamento = { { Equipamento.BETONEIRA, Equipamento.ANDAIME },
				{ Equipamento.MISTURADOR, Equipamento.MARTELETE }, { Equipamento.GUINCHO, Equipamento.MAQUINA_CORTE } };

		DiariaBase[] vetor = new DiariaBase[3];
		vetor[0] = new DiariaPedreiro(TipoPedreiro.OFICIAL, 5, Regiao.CENTRO, Turno.MANHA, matrizEquipamento[0],
				Experiencia.SENIOR);
		vetor[1] = new DiariaPedreiro(TipoPedreiro.ENCANADOR, 3, Regiao.SUL, Turno.NOITE, matrizEquipamento[1],
				Experiencia.PLENO);
		vetor[2] = new DiariaPedreiro(TipoPedreiro.PEDREIRO_MESTRE, 7, Regiao.ZONA_RURAL, Turno.PLANTAO,
				matrizEquipamento[2], Experiencia.ENGENHEIRO);

		for (DiariaBase diaria : vetor) {
			if (diaria instanceof DiariaPedreiro dp) {
				dp.calcularTotal();
				dp.switchCalculo(TipoServico.LAJE);
				registros.add(dp);
			} else {
				System.out.println("Tipo não reconhecido!");
			}
		}

		for (DiariaBase d : registros) {
			d.mostrarResumo();
		}
	}
}

class Import_3V {
	public static void run_Import_3V() {
		System.err.println("\t\t3. \n");
		SistemaDiarias.main(new String[3]);
	}
}
