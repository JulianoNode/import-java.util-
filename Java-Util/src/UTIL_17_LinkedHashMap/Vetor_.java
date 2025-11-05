package UTIL_17_LinkedHashMap;

import util.Linhas;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


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
class Import_1V {
	public static void run_Import_1V() {
		System.err.println("\t\t1. \n");

	}
}

//Corpo da Classe _______________________________
class Import_2V {
	public static void run_Import_2V() {
		System.err.println("\t\t2. \n");

	}
}

//Corpo da Classe _______________________________
class Import_3V {
	public static void run_Import_3V() {
		System.err.println("\t\t3. \n");

	}
}

//1 Corpo da Classe Abstract e Enum _______________________________

// ========================== ENUMS (6) ==========================
enum TipoServico {
	PEDREIRO(150.0), CARPINTEIRO(140.0), AZULEJISTA(160.0), PINTOR(130.0), ELETRICISTA(180.0), ENCANADOR(175.0);

	private double valorDiaria;

	TipoServico(double valorDiaria) {
		this.valorDiaria = valorDiaria;
	}

	public double getValorDiaria() {
		return valorDiaria;
	}

	public void mostrarDetalhes() {
		System.out.println(name() + " recebe R$" + valorDiaria + " por dia.");
	}
}

enum Turno {
	MANHA, TARDE, NOITE;

	public void mostrarTurno() {
		System.out.println("Turno: " + name());
	}
}

enum Dificuldade {
	BAIXA(1.0), MEDIA(1.3), ALTA(1.6), EXTREMA(2.0);

	private double multiplicador;

	Dificuldade(double multiplicador) {
		this.multiplicador = multiplicador;
	}

	public double getMultiplicador() {
		return multiplicador;
	}
}

enum Ferramenta {
	COLHER_DE_PEDREIRO, PA, BETONEIRA, MARTELO, SERRA, TALHADEIRA;

	public void usarFerramenta() {
		System.out.println("Usando ferramenta: " + name());
	}
}

enum TipoObra {
	CASA, PREDIO, MURO, REFORMA, PISCINA, JARDIM;

	public void exibir() {
		System.out.println("Tipo de obra: " + name());
	}
}

enum Bonus {
	NENHUM(0.0), OTIMO(0.15), EXCELENTE(0.25), PERFEITO(0.35);

	private double percentual;

	Bonus(double percentual) {
		this.percentual = percentual;
	}

	public double aplicarBonus(double valor) {
		return valor + (valor * percentual);
	}
}

// ========================== CLASSE ABSTRATA ==========================
abstract class Trabalhador {
	protected String nome;
	protected TipoServico tipo;
	protected Dificuldade dificuldade;
	protected Bonus bonus;
	protected int diasTrabalhados;

	public Trabalhador(String nome, TipoServico tipo, Dificuldade dificuldade, Bonus bonus, int dias) {
		this.nome = nome;
		this.tipo = tipo;
		this.dificuldade = dificuldade;
		this.bonus = bonus;
		this.diasTrabalhados = dias;
	}

	public abstract double calcularPagamento();

	public synchronized void mostrarResumo() {
		System.out.println("\n=== RESUMO DO TRABALHADOR ===");
		System.out.println("Nome: " + nome);
		System.out.println("Função: " + tipo);
		System.out.println("Dias: " + diasTrabalhados);
		System.out.println("Dificuldade: " + dificuldade);
		System.out.println("Bônus: " + bonus);
	}

	public void switchObra(TipoObra obra) {
		switch (obra) {
		case CASA -> System.out.println(nome + " está trabalhando em uma CASA.");
		case PREDIO -> System.out.println(nome + " está trabalhando em um PRÉDIO.");
		case MURO -> System.out.println(nome + " está construindo um MURO.");
		case REFORMA -> System.out.println(nome + " está fazendo uma REFORMA.");
		case PISCINA -> System.out.println(nome + " está construindo uma PISCINA.");
		case JARDIM -> System.out.println(nome + " está cuidando de um JARDIM.");
		default -> System.out.println("Tipo de obra desconhecido.");
		}
	}
}

// ========================== CLASSE CONCRETA ==========================
class Pedreiro extends Trabalhador {
	public Pedreiro(String nome, TipoServico tipo, Dificuldade dificuldade, Bonus bonus, int dias) {
		super(nome, tipo, dificuldade, bonus, dias);
	}

	@Override
	public synchronized double calcularPagamento() {
		double base = tipo.getValorDiaria() * diasTrabalhados * dificuldade.getMultiplicador();
		return bonus.aplicarBonus(base);
	}
}

// ========================== CLASSE MAIN ==========================
class DiariaPedreiroHard {
	public static void main(String[] args) {

		// LinkedHashSet para manter ordem e evitar duplicatas
		LinkedHashSet<Trabalhador> equipe = new LinkedHashSet<>();

		// Vetor com trabalhadores
		Trabalhador[] vetorTrabalhadores = new Trabalhador[3];
		vetorTrabalhadores[0] = new Pedreiro("João", TipoServico.PEDREIRO, Dificuldade.ALTA, Bonus.EXCELENTE, 5);
		vetorTrabalhadores[1] = new Pedreiro("Carlos", TipoServico.PINTOR, Dificuldade.MEDIA, Bonus.OTIMO, 7);
		vetorTrabalhadores[2] = new Pedreiro("Marcos", TipoServico.ELETRICISTA, Dificuldade.EXTREMA, Bonus.PERFEITO,
				10);

		// Adiciona ao LinkedHashSet
		Collections.addAll(equipe, vetorTrabalhadores);

		// Matriz para simular dias trabalhados
		double[][] matrizDiarias = new double[3][3];
		AtomicInteger contador = new AtomicInteger(1);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				matrizDiarias[i][j] = contador.getAndIncrement() * 10;
			}
		}

		// Mostrar matriz de exemplo
		System.out.println("\nMatriz de diárias (exemplo de cálculos simulados):");
		for (double[] linha : matrizDiarias) {
			for (double valor : linha) {
				System.out.print(valor + "\t");
			}
			System.out.println();
		}

		// While + instanceof + cálculos
		Iterator<Trabalhador> it = equipe.iterator();
		while (it.hasNext()) {
			Trabalhador t = it.next();

			if (t instanceof Pedreiro p) {
				p.mostrarResumo();
				p.switchObra(TipoObra.values()[(int) (Math.random() * TipoObra.values().length)]);
				double pagamento = p.calcularPagamento();

				// if / else if / else
				if (pagamento < 1000) {
					System.out.println("Pagamento Baixo: R$" + pagamento);
				} else if (pagamento < 2000) {
					System.out.println("Pagamento Médio: R$" + pagamento);
				} else {
					System.out.println("Pagamento Alto: R$" + pagamento);
				}
			}
		}

		// FOR + enum métodos
		System.out.println("\n=== Detalhes dos Serviços ===");
		for (TipoServico serv : TipoServico.values()) {
			serv.mostrarDetalhes();
		}

		// Sincronização simulada
		synchronized (equipe) {
			System.out.println("\nEquipe sincronizada. Total de trabalhadores: " + equipe.size());
		}
	}
}

//2 Corpo da Classe Abstract e Enum _______________________________

//3 Corpo da Classe Abstract e Enum _______________________________