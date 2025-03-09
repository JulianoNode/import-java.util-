package UTIL_07_Collections;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

import util.VoutarRun;

//Classe principal para executar o CRUD
public class ConvertMoedas {

	public static void Run_Convert() {
		Scanner scanner = new Scanner(System.in);
		List<ConversorMoeda1> listaMoedas = new ArrayList<>();
		int opcao;

		do {
			System.out.println("\n=== Sistema de Conversão de Moedas ===");
			System.out.println("1. Adicionar Moeda");
			System.out.println("2. Converter Moeda");
			System.out.println("3. Listar Moedas");
			System.out.println("4. Ordenar Moedas");
			System.out.println("5. Buscar Moeda");
			System.out.println("6. Sair");
			System.out.print("Escolha uma opção: ");
			opcao = scanner.nextInt();

			switch (opcao) {
			case 1:
				System.out.print("Digite a moeda (USD, EUR, JPY, CNY, CHF, BRL, GBP): ");
				MoedaEnum moeda = MoedaEnum.valueOf(scanner.next().toUpperCase());
				System.out.print("Digite o valor: ");
				double valor = scanner.nextDouble();
				listaMoedas.add(new ConversorMoeda1(moeda, valor));
				break;

			case 2:
				System.out.print("Digite a moeda de origem: ");
				MoedaEnum origem = MoedaEnum.valueOf(scanner.next().toUpperCase());
				System.out.print("Digite a moeda de destino: ");
				MoedaEnum destino = MoedaEnum.valueOf(scanner.next().toUpperCase());
				System.out.print("Digite a quantia: ");
				double quantia = scanner.nextDouble();

				ConversorMoeda1 conversor = new ConversorMoeda1(origem, 0);
				double resultado = conversor.converterPara(destino, quantia);
				System.out.println("Conversão: " + quantia + " " + origem + " = " + resultado + " " + destino);
				break;

			case 3:
				System.out.println("Lista de Moedas: ");
				for (ConversorMoeda1 moedaObj : listaMoedas) {
					System.out.println(moedaObj.tipo + " - " + moedaObj.valor);
				}
				break;

			case 4:
				ConversorMoeda1.listarMoedasOrdenadas(listaMoedas);
				break;

			case 5:
				System.out.print("Digite a moeda a buscar: ");
				MoedaEnum buscarMoeda = MoedaEnum.valueOf(scanner.next().toUpperCase());
				boolean encontrada = false;
				for (ConversorMoeda1 m : listaMoedas) {
					if (m.tipo == buscarMoeda) {
						System.out.println("Moeda encontrada: " + m.tipo + " - " + m.valor);
						encontrada = true;
						break;
					}
				}
				if (!encontrada) {
					System.out.println("Moeda não encontrada.");
				}
				break;

			case 6:
				System.out.println("Saindo...");
				break;

			default:
				System.out.println("Opção inválida!");
			}
		} while (opcao != 6);
		VoutarRun voutarRun = new VoutarRun();
		voutarRun.run_CaracteresMenu_Red();
	}

}

//Enum para representar diferentes moedas
enum MoedaEnum {
	USD, EUR, JPY, CNY, CHF, BRL, GBP;
}

//Classe abstrata para definição base
abstract class Moeda1 {
	protected MoedaEnum tipo;
	protected double valor;

	public Moeda1(MoedaEnum tipo, double valor) {
		this.tipo = tipo;
		this.valor = valor;
	}

	public abstract double converterPara(MoedaEnum destino, double quantia);
}

//Classe concreta para conversão de moedas
class ConversorMoeda1 extends Moeda1 {
	private static final Map<MoedaEnum, Double> taxaCambio;
	private static final ReentrantLock lock = new ReentrantLock();

	static {
		taxaCambio = new HashMap<>();
		taxaCambio.put(MoedaEnum.USD, 1.0);
		taxaCambio.put(MoedaEnum.EUR, 0.91);
		taxaCambio.put(MoedaEnum.JPY, 136.5);
		taxaCambio.put(MoedaEnum.CNY, 6.89);
		taxaCambio.put(MoedaEnum.CHF, 0.97);
		taxaCambio.put(MoedaEnum.BRL, 5.13);
		taxaCambio.put(MoedaEnum.GBP, 0.79);
	}

	public ConversorMoeda1(MoedaEnum tipo, double valor) {
		super(tipo, valor);
	}

	@Override
	public double converterPara(MoedaEnum destino, double quantia) {
		double taxaOrigem = taxaCambio.get(this.tipo);
		double taxaDestino = taxaCambio.get(destino);
		return (quantia / taxaOrigem) * taxaDestino;
	}

	public static void listarMoedasOrdenadas(List<ConversorMoeda1> lista) {
		Collections.sort(lista, Comparator.comparingDouble(m -> m.valor));
		lista.forEach(m -> System.out.println(m.tipo + " - " + m.valor));
	}
}