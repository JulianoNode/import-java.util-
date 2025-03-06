package UTIL_06_Deque;

import util.Linhas;
import util.VoutarRun;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class If_Else_IF {
	public static void If_Else_IF_Run() {

		Linhas linhas = new Linhas();
		String collec = "\n\t Deque<E>: Representa uma fila dupla (pode adicionar/remover de ambas as extremidades). \n";
		System.err.println(collec);

		linhas.run_Caracteres();
		Import_1IF.run_Import_1IF();

		linhas.run_Caracteres();
		Import_3IF.run_Import_3IF();

		linhas.run_Caracteres();
		Import_2IF.run_Import_2IF();

	}
}

//Corpo da Classe _______________________________
class Import_1IF {
	public static void run_Import_1IF() {
		System.err.println(
				"\t\t1. Define um enum chamado ClassificacaoVelocidade para classificar a velocidade do objeto.\n");

		double[] velocidades = { 0, 15, 45, 90, 150 };
		MedidorVelocidade medidor = new MedidorVelocidade(velocidades);
		medidor.processarVelocidades();
	}
}

//Corpo da Classe _______________________________
class Import_2IF {
	public static void run_Import_2IF() {
		System.err.println("\t\t2.\n");

		Scanner scanner = new Scanner(System.in);
		Deque<Double> valores = new ArrayDeque<>();

		System.out.println("Escolha o tipo de cálculo:");
		System.out.println("1 - Velocidade");
		System.out.println("2 - Deslocamento");
		System.out.println("3 - Tempo");
		int escolha = scanner.nextInt();

		TipoCalculo tipo = null;
		CalculoFisico calculo = null;

		if (escolha == 1) {
			tipo = TipoCalculo.VELOCIDADE;
			calculo = new CalculoVelocidade();
			System.out.print("Digite o deslocamento (m): ");
			valores.add(scanner.nextDouble());
			System.out.print("Digite o tempo (s): ");
			valores.add(scanner.nextDouble());
		} else if (escolha == 2) {
			tipo = TipoCalculo.DESLOCAMENTO;
			calculo = new CalculoDeslocamento();
			System.out.print("Digite a velocidade (m/s): ");
			valores.add(scanner.nextDouble());
			System.out.print("Digite o tempo (s): ");
			valores.add(scanner.nextDouble());
		} else if (escolha == 3) {
			tipo = TipoCalculo.TEMPO;
			calculo = new CalculoTempo();
			System.out.print("Digite o deslocamento (m): ");
			valores.add(scanner.nextDouble());
			System.out.print("Digite a velocidade (m/s): ");
			valores.add(scanner.nextDouble());
		} else {
			System.out.println("Opção inválida!");
			scanner.close();
			return;
		}

		double resultado = calculo.calcular(valores.poll(), valores.poll());
		System.out.println("Resultado (" + tipo + "): " + resultado);

		VoutarRun voutarRun = new VoutarRun();
		voutarRun.run_CaracteresMenu_Red();
	}
}

//Corpo da Classe _______________________________
class Import_3IF {
	public static void run_Import_3IF() {
		System.err.println("\t\t3. \n");

		Deque<Aviao> filaAvioes = new LinkedList<>();

		// Criando aviões com diferentes configurações
		filaAvioes.add(new Aviao("Boeing 747", 1000, 2, UnidadeVelocidade.KMH));
		filaAvioes.add(new Aviao("Airbus A320", 1500, 3, UnidadeVelocidade.MPH));
		filaAvioes.add(new Aviao("F-22 Raptor", 1200, 1, UnidadeVelocidade.MS));

		// Processando a fila de aviões
		while (!filaAvioes.isEmpty()) {
			Aviao aviao = filaAvioes.poll();
			System.out.println("Modelo: " + aviao.modelo);
			System.out.println("Velocidade: " + aviao.calcularVelocidade() + " " + aviao.unidade);
			System.out.println("-----------------------");
		}
	}
}

//1 Corpo de classe abstract e enum ______________

//Enum para classificar a velocidade
enum ClassificacaoVelocidade {
	PARADO, LENTO, MODERADO, RAPIDO, MUITO_RAPIDO;
}

//Classe abstrata para representar um objeto em movimento
abstract class ObjetoMovel {
	protected double[] velocidades;

	public ObjetoMovel(double[] velocidades) {
		this.velocidades = velocidades;
	}

	public abstract ClassificacaoVelocidade classificarVelocidade(double velocidade);
}

//Classe concreta que calcula e classifica velocidades
class MedidorVelocidade extends ObjetoMovel {
	private Deque<Double> historicoVelocidades;

	public MedidorVelocidade(double[] velocidades) {
		super(velocidades);
		historicoVelocidades = new ArrayDeque<>();
	}

	@Override
	public ClassificacaoVelocidade classificarVelocidade(double velocidade) {
		if (velocidade == 0) {
			return ClassificacaoVelocidade.PARADO;
		} else if (velocidade > 0 && velocidade <= 20) {
			return ClassificacaoVelocidade.LENTO;
		} else if (velocidade > 20 && velocidade <= 60) {
			return ClassificacaoVelocidade.MODERADO;
		} else if (velocidade > 60 && velocidade <= 120) {
			return ClassificacaoVelocidade.RAPIDO;
		} else {
			return ClassificacaoVelocidade.MUITO_RAPIDO;
		}
	}

	public void processarVelocidades() {
		System.out.println("Processando velocidades...");
		for (double velocidade : velocidades) {
			historicoVelocidades.add(velocidade);
			System.out.println("Velocidade: " + velocidade + " km/h - " + classificarVelocidade(velocidade));
		}
	}
}

//2 Corpo de classe abstract e enum ______________

//Enum para os tipos de cálculo possíveis
enum TipoCalculo {
	VELOCIDADE, DESLOCAMENTO, TEMPO;
}

//Classe abstrata para representar um cálculo físico
abstract class CalculoFisico {
	abstract double calcular(double a, double b);
}

//Implementação dos cálculos específicos
class CalculoVelocidade extends CalculoFisico {
	@Override
	double calcular(double deslocamento, double tempo) {
		if (tempo == 0)
			throw new ArithmeticException("O tempo não pode ser zero!");
		return deslocamento / tempo;
	}
}

class CalculoDeslocamento extends CalculoFisico {
	@Override
	double calcular(double velocidade, double tempo) {
		return velocidade * tempo;
	}
}

class CalculoTempo extends CalculoFisico {
	@Override
	double calcular(double deslocamento, double velocidade) {
		if (velocidade == 0)
			throw new ArithmeticException("A velocidade não pode ser zero!");
		return deslocamento / velocidade;
	}
}

//3 Corpo de classe abstract e enum ______________

//Enum para representar diferentes unidades de velocidade
enum UnidadeVelocidade {
	KMH, MPH, MS;
}

//Classe abstrata para um veículo
abstract class Veiculo {
	protected String modelo;
	protected double distancia;
	protected double tempo;

	public Veiculo(String modelo, double distancia, double tempo) {
		this.modelo = modelo;
		this.distancia = distancia;
		this.tempo = tempo;
	}

	public abstract double calcularVelocidade();
}

//Classe concreta para Avião
class Aviao extends Veiculo {
	UnidadeVelocidade unidade;

	public Aviao(String modelo, double distancia, double tempo, UnidadeVelocidade unidade) {
		super(modelo, distancia, tempo);
		this.unidade = unidade;
	}

	@Override
	public double calcularVelocidade() {
		double velocidade = distancia / tempo; // Fórmula da velocidade: v = d/t

		if (unidade == UnidadeVelocidade.MPH) {
			return velocidade * 0.621371; // Convertendo para Milhas por Hora
		} else if (unidade == UnidadeVelocidade.MS) {
			return velocidade / 3.6; // Convertendo para Metros por Segundo
		} else {
			return velocidade; // KMH (Padrão)
		}
	}
}
