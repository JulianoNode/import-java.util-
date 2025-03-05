package UTIL_05_Queue;

import java.util.Queue;
import java.util.LinkedList;

import util.Linhas;

public class Vetor_ {
	public static void Vetor_Run() {

		Linhas linhas = new Linhas();
		String collec = "\n\t Queue<E>: Representa uma fila (FIFO). \n";
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
abstract class Veiculo {
	private String modelo;
	private int ano;

	public Veiculo(String modelo, int ano) {
		this.modelo = modelo;
		this.ano = ano;
	}

	public abstract void exibirInfo();

	public String getModelo() {
		return modelo;
	}

	public int getAno() {
		return ano;
	}
}

class Carro extends Veiculo {
	public Carro(String modelo, int ano) {
		super(modelo, ano);
	}

	@Override
	public void exibirInfo() {
		System.out.println("Carro Modelo: " + getModelo() + ", Ano: " + getAno());
	}
}

class Moto extends Veiculo {
	private int roda;

	public Moto(String modelo, int ano, int roda) {
		super(modelo, ano);
		this.roda = roda;
	}

	@Override
	public void exibirInfo() {
		System.out.println("Moto Modelo: " + getModelo() + ", Ano: " + getAno() + ", Roda: " + roda);
	}
}

class Caminhao extends Veiculo {

	private String peca;

	public Caminhao(String modelo, int ano, String peca) {
		super(modelo, ano);
		this.peca = peca;
	}

	@Override
	public void exibirInfo() {
		System.out.println("Camilhão Modelo: " + getModelo() + ", Ano: " + getAno() + ", Peça: " + peca);
	}
}

class Import_1V {
	public static void run_Import_1V() {
		System.err.println("\t\t1. Classe abstrata Veículo e implementação de Fila de Veículos\n");

		Queue<Veiculo> veiculos = new LinkedList<>();
		veiculos.add(new Carro("Fusca", 1978));
		veiculos.add(new Moto("Honda CBR", 2020, 2));
		veiculos.add(new Caminhao("Honda Volvo", 2024, "Cabine"));

		while (!veiculos.isEmpty()) {
			Veiculo veiculo = veiculos.poll();
			veiculo.exibirInfo();
		}
	}
}

//Corpo da Classe _______________________________

abstract class Veiculo2 {
	private String modelo;

	public Veiculo2(String modelo) {
		this.modelo = modelo;
	}

	public abstract void exibirInfo();

	public String getModelo() {
		return modelo;
	}
}

class Carro2 extends Veiculo2 {
	public Carro2(String modelo) {
		super(modelo);
	}

	@Override
	public void exibirInfo() {
		System.out.println("Carro: " + getModelo());
	}
}

class Moto2 extends Veiculo2 {
	public Moto2(String modelo) {
		super(modelo);
	}

	@Override
	public void exibirInfo() {
		System.out.println("Moto: " + getModelo());
	}
}

class Import_2V {
	public static void run_Import_2V() {
		System.err.println("\t\t2. Filtro de Veículos com If, Else If, Else\n");
		Queue<Veiculo2> filaVeiculos = new LinkedList<>();
		filaVeiculos.add(new Carro2("Fusca"));
		filaVeiculos.add(new Moto2("Honda"));

		while (!filaVeiculos.isEmpty()) {
			Veiculo2 veiculo = filaVeiculos.poll();
			if (veiculo instanceof Carro2) {
				System.out.println("Este é um Carro:");
				veiculo.exibirInfo();
			} else if (veiculo instanceof Moto2) {
				System.out.println("Esta é uma Moto:");
				veiculo.exibirInfo();
			} else {
				System.out.println("Tipo desconhecido de veículo");
			}
		}
	}
}

//Corpo da Classe _______________________________

abstract class Veiculo3 {
	private String modelo;

	public Veiculo3(String modelo) {
		this.modelo = modelo;
	}

	public abstract void exibirInfo();

	public String getModelo() {
		return modelo;
	}
}

class Carro3 extends Veiculo3 {
	public Carro3(String modelo) {
		super(modelo);
	}

	@Override
	public void exibirInfo() {
		System.out.println("Carro: " + getModelo());
	}
}

class Moto3 extends Veiculo3 {
	public Moto3(String modelo) {
		super(modelo);
	}

	@Override
	public void exibirInfo() {
		System.out.println("Moto: " + getModelo());
	}
}

class Import_3V {
	public static void run_Import_3V() {
		System.err.println("\t\t3. Controle de Fila de Veículos com If, Else, While\n");

		Veiculo2[] veiculos = new Veiculo2[4];
		veiculos[0] = new Carro2("Fusca");
		veiculos[1] = new Moto2("Honda");
		veiculos[2] = new Carro2("Chevette");
		veiculos[3] = new Moto2("Yamaha");

		int i = 0;
		while (i < veiculos.length) {
			if (veiculos[i] instanceof Carro2) {
				System.out.println("Este é um Carro:");
				veiculos[i].exibirInfo();
			} else if (veiculos[i] instanceof Moto2) {
				System.out.println("Esta é uma Moto:");
				veiculos[i].exibirInfo();
			} else {
				System.out.println("Veículo desconhecido.");
			}
			i++;
		}

	}
}