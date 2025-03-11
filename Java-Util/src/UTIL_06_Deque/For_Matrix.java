package UTIL_06_Deque;

import java.util.ArrayDeque;
import java.util.Deque;

import util.Linhas;

public class For_Matrix {
	public static void For_Run() {

		Linhas linhas = new Linhas();
		String collec = "\n\t Deque<E>: Representa uma fila dupla (pode adicionar/remover de ambas as extremidades). \n";
		System.err.println(collec);

		linhas.run_Caracteres();
		Import_1F.run_Import_1F();

		linhas.run_Caracteres();
		Import_2F.run_Import_2F();

		linhas.run_Caracteres();
		Import_3F.run_Import_3F();

	}
}

//Corpo da Classe _______________________________
class Import_1F {
	public static void run_Import_1F() {
		System.err.println("\t\t1. \n");

		Deque<AviaoF22> historico = new ArrayDeque<>();

		// Simulação com diferentes deslocamentos e tempos
		double[][] dados = { { 5000, 20 }, { 2000, 10 }, { 10000, 50 }, { 3000, 5 }, { 15000, 30 } };

		for (double[] dado : dados) {
			AviaoF22 aviao = new AviaoF22(dado[0], dado[1]);
			double velocidade = aviao.calcularVelocidade();
			VelocidadeStatus status = aviao.verificarStatusVelocidade(velocidade);
			historico.add(aviao);

			System.out.printf("Avião: %s | Velocidade: %.2f m/s | Status: %s\n", aviao.nome, velocidade, status);
		}

	}
}

//Corpo da Classe _______________________________
class Import_2F {
	public static void run_Import_2F() {
		System.err.println("\t\t2. \n");

		double[][] dadosAviao = { { 500, 2 }, // 500 km em 2 horas
				{ 1000, 4 }, // 1000 km em 4 horas
				{ 300, 1 } // 300 km em 1 hora
		};

		double[][] dadosHelicoptero = { { 200, 2 }, // 200 km em 2 horas
				{ 150, 1.5 }, // 150 km em 1.5 horas
				{ 400, 5 } // 400 km em 5 horas
		};

		VeiculoAereo aviao = new VeiculoAereo("Boeing 747", TipoAeronave.AVIAO);
		VeiculoAereo helicoptero = new VeiculoAereo("Apache AH-64", TipoAeronave.HELICOPTERO);

		aviao.calcularVelocidades(dadosAviao);
		helicoptero.calcularVelocidades(dadosHelicoptero);
	}
}

//Corpo da Classe _______________________________
class Import_3F {
	public static void run_Import_3F() {
		System.err.println("\t\t3.\n");
		// Criando um Deque para armazenar veículos
		Deque<Veiculo2> veiculos = new ArrayDeque<>();

		// Adicionando diferentes veículos
		veiculos.add(new Moto(1000, 50)); // 20 m/s
		veiculos.add(new Carro(2000, 80)); // 25 m/s
		veiculos.add(new Caminhao(5000, 300)); // 16.67 m/s
		veiculos.add(new Aviao2(50000, 600)); // 83.33 m/s
		veiculos.add(new Helicoptero(30000, 500)); // 60 m/s
		veiculos.add(new Caca(80000, 400)); // 200 m/s

		// Iterando sobre os veículos e exibindo informações
		for (Veiculo2 veiculo : veiculos) {
			veiculo.exibirInfo();
		}

	}
}

//1 Corpo da Classe com classe abstrata e enum. _______________________________
//Enum para representar diferentes estágios de velocidade
enum VelocidadeStatus {
	BAIXA, MEDIA, ALTA, HIPERSONICA;
}

//Classe abstrata para representar um avião caça
abstract class AviaoCaca {
	protected String nome;
	protected double deslocamento;
	protected double tempo;

	public AviaoCaca(String nome, double deslocamento, double tempo) {
		this.nome = nome;
		this.deslocamento = deslocamento;
		this.tempo = tempo;
	}

	public abstract double calcularVelocidade();
}

//Classe concreta que implementa o cálculo da velocidade
class AviaoF22 extends AviaoCaca {
	public AviaoF22(double deslocamento, double tempo) {
		super("F-22 Raptor", deslocamento, tempo);
	}

	@Override
	public double calcularVelocidade() {
		return deslocamento / tempo;
	}

	public VelocidadeStatus verificarStatusVelocidade(double velocidade) {
		if (velocidade < 300) {
			return VelocidadeStatus.BAIXA;
		} else if (velocidade < 800) {
			return VelocidadeStatus.MEDIA;
		} else if (velocidade < 1500) {
			return VelocidadeStatus.ALTA;
		} else {
			return VelocidadeStatus.HIPERSONICA;
		}
	}
}

//2 Corpo da Classe com classe abstrata e enum. _______________________________

//Enum para os tipos de aeronaves
enum TipoAeronave {
	AVIAO, HELICOPTERO;
}

//Classe abstrata representando uma aeronave
abstract class Aeronave {
	protected String nome;
	protected TipoAeronave tipo;

	public Aeronave(String nome, TipoAeronave tipo) {
		this.nome = nome;
		this.tipo = tipo;
	}

	public abstract void calcularVelocidades(double[][] dados); // Método abstrato
}

//Classe concreta que herda de Aeronave
class VeiculoAereo extends Aeronave {
	public VeiculoAereo(String nome, TipoAeronave tipo) {
		super(nome, tipo);
	}

	@Override
	public void calcularVelocidades(double[][] dados) {
		Deque<Double> velocidades = new ArrayDeque<>();

		for (int i = 0; i < dados.length; i++) {
			double deltaS = dados[i][0]; // Distância percorrida
			double deltaT = dados[i][1]; // Tempo gasto

			if (deltaT == 0) {
				System.out.println("Erro: Tempo não pode ser zero!");
				continue;
			}

			double velocidade = deltaS / deltaT;
			velocidades.add(velocidade);
		}

		System.out.println("Velocidades calculadas para " + nome + " (" + tipo + "): " + velocidades);
	}
}

//3 Corpo da Classe com classe abstrata e enum. _______________________________

//Enum para representar os tipos de veículos
enum TipoVeiculo {
	MOTO, CARRO, CAMINHAO, AVIAO, HELICOPTERO, CACAO;
}

//Classe abstrata que define o modelo base para um veículo
abstract class Veiculo2 {
	protected TipoVeiculo tipo;
	protected double distancia; // metros
	protected double tempo; // segundos

	public Veiculo2(TipoVeiculo tipo, double distancia, double tempo) {
		this.tipo = tipo;
		this.distancia = distancia;
		this.tempo = tempo;
	}

	// Método abstrato para calcular a velocidade
	public abstract double calcularVelocidade();

	// Exibir informações do veículo
	public void exibirInfo() {
		System.out.println(tipo + " -> Velocidade: " + calcularVelocidade() + " m/s");
	}
}

//Implementação da classe para cada tipo de veículo
class Moto extends Veiculo2 {
	public Moto(double distancia, double tempo) {
		super(TipoVeiculo.MOTO, distancia, tempo);
	}

	@Override
	public double calcularVelocidade() {
		return distancia / tempo;
	}
}

class Carro extends Veiculo2 {
	public Carro(double distancia, double tempo) {
		super(TipoVeiculo.CARRO, distancia, tempo);
	}

	@Override
	public double calcularVelocidade() {
		return distancia / tempo;
	}
}

class Caminhao extends Veiculo2 {
	public Caminhao(double distancia, double tempo) {
		super(TipoVeiculo.CAMINHAO, distancia, tempo);
	}

	@Override
	public double calcularVelocidade() {
		return distancia / tempo;
	}
}

class Aviao2 extends Veiculo2 {
	public Aviao2(double distancia, double tempo) {
		super(TipoVeiculo.AVIAO, distancia, tempo);
	}

	@Override
	public double calcularVelocidade() {
		return distancia / tempo;
	}
}

class Helicoptero extends Veiculo2 {
	public Helicoptero(double distancia, double tempo) {
		super(TipoVeiculo.HELICOPTERO, distancia, tempo);
	}

	@Override
	public double calcularVelocidade() {
		return distancia / tempo;
	}
}

class Caca extends Veiculo2 {
	public Caca(double distancia, double tempo) {
		super(TipoVeiculo.CACAO, distancia, tempo);
	}

	@Override
	public double calcularVelocidade() {
		return distancia / tempo;
	}
}
