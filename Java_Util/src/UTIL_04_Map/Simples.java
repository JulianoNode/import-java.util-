package UTIL_04_Map;

import util.Linhas;
import java.util.*;

public class Simples {
	public static void Simples_Run() {

		Linhas linhas = new Linhas();
		String collec = "\n\t Map<K, V>: Representa um mapeamento de chave-valor. \n";
		System.err.println(collec);
		
		Escalas.run_Escala();
		
		linhas.run_Caracteres();
		Import_1S.run_Import_1S();

		linhas.run_Caracteres();
		Import_2S.run_Import_2S();

		linhas.run_Caracteres();
		Import_3S.run_Import_3S();

		linhas.run_Caracteres();
		Import_4S.run_Import_4S();

		linhas.run_Caracteres();
		Import_5S.run_Import_5S();
	}
}

class Escalas{
	public static void run_Escala() {
		System.out.println("Existem três escalas de temperatura: "
				+ "Kelvin (K), Celsius (C) e Fahrenheit (F). Ao medir a temperatura da atmosfera de sua casa, \n"
				+ "por exemplo, você não está simplesmente registrando a sensação térmica que seu corpo sente.\n"
				+ " Na verdade, está medindo a energia cinética das partículas de gás em sua casa.");
		System.out.println("As escalas de temperatura mais utilizadas são Celsius (°C), Fahrenheit (°F) e Kelvin (K). \r\n"
				+ "Escala Celsius\r\n"
				+ "A escala Celsius é utilizada em vários países, incluindo o Brasil. \r\n"
				+ "O ponto de fusão da água é de 0°C e o ponto de ebulição é de 100°C. \r\n"
				+ "Escala Fahrenheit\r\n"
				+ "A escala Fahrenheit é muito utilizada nos Estados Unidos, Inglaterra e outros países de língua inglesa. \r\n"
				+ "O ponto de fusão da água é de 32°F e o ponto de ebulição é de 212°F. \r\n"
				+ "Escala Kelvin\r\n"
				+ "A escala Kelvin é a escala termométrica adotada pelo Sistema Internacional. \r\n"
				+ "O ponto de fusão da água é de 273K e o ponto de ebulição é de 373K. \r\n"
				+ "A escala Kelvin foi criada pelo físico e engenheiro britânico William Thomson, conhecido como Lorde Kelvin. ");
	}
}

//Corpo da Classe
enum UnitTemperature {
	CELSIUS, FAHRENHEIT, KELVIN;
}

class Import_1S {
	public static void run_Import_1S() {
		System.err.println("\t\t1. Conversão de Temperatura Simples\r\n"
				+ "Utiliza um enum para definir as unidades e um Map para armazenar fatores (como exemplo ilustrativo). "
				+ "O método converter realiza a conversão entre Celsius, Fahrenheit e Kelvin.\n");

		double tempFahrenheit = converter(100, UnidadeTemperatura.CELSIUS, UnidadeTemperatura.FAHRENHEIT);
		System.out.println("100°C em Fahrenheit: " + tempFahrenheit);
	}

	private static final Map<UnidadeTemperatura, Double> fatorConversao = new HashMap<>();

	static {
		// Fator de conversão em relação à Celsius
		fatorConversao.put(UnidadeTemperatura.CELSIUS, 1.0);
		fatorConversao.put(UnidadeTemperatura.FAHRENHEIT, 1.8); // Exemplo: Celsius * 1.8 + 32
		fatorConversao.put(UnidadeTemperatura.KELVIN, 1.0); // Apenas adiciona 273.15
	}

	@SuppressWarnings("incomplete-switch")
	public static double converter(double valor, UnidadeTemperatura origem, UnidadeTemperatura destino) {
		double valorEmCelsius = 0;
		// Converte de origem para Celsius
		switch (origem) {
		case CELSIUS:
			valorEmCelsius = valor;
			break;
		case FAHRENHEIT:
			valorEmCelsius = (valor - 32) / 1.8;
			break;
		case KELVIN:
			valorEmCelsius = valor - 273.15;
			break;
		}
		// Converte de Celsius para a unidade destino
		double resultado = 0;
		switch (destino) {
		case CELSIUS:
			resultado = valorEmCelsius;
			break;
		case FAHRENHEIT:
			resultado = valorEmCelsius * 1.8 + 32;
			break;
		case KELVIN:
			resultado = valorEmCelsius + 273.15;
			break;
		}
		return resultado;
	}
}

//Corpo da Classe
enum UTemperatura {
	CELSIUS, FAHRENHEIT, KELVIN;
}

// Annotation para indicar a unidade de temperatura
@interface InfoTemperatura {
	UTemperatura unidade();
}

class Import_2S {
	public static void run_Import_2S() {
		System.err.println("\t\t2. Mapa de Temperaturas por Cidade com Annotation\r\n"
				+ "Cria um Map para armazenar temperaturas de cidades e utiliza uma annotation customizada "
				+ "@InfoTemperatura para indicar a unidade utilizada (neste caso, Celsius).\n");

		System.out.println("Temperaturas das cidades (em Celsius):");
		for (String cidade : temperaturas.keySet()) {
			System.out.println(cidade + ": " + temperaturas.get(cidade) + "°C");
		}
	}

	// Mapa com cidades e suas temperaturas em Celsius
	@InfoTemperatura(unidade = UTemperatura.CELSIUS)
	private static Map<String, Double> temperaturas = new HashMap<>();

	static {
		temperaturas.put("São Paulo", 22.5);
		temperaturas.put("Rio de Janeiro", 25.0);
		temperaturas.put("Belo Horizonte", 20.0);
	}
}

//Corpo da Classe
enum UnidadeTempe {
	CELSIUS, FAHRENHEIT, KELVIN;
}

// Annotation para identificar métodos de conversão
@interface Conversao {
	String descricao() default "Método de conversão de temperatura";
}

class Import_3S {
	public static void run_Import_3S() {
		System.err.println("\t\t3. Método de Conversão Marcado com Annotation\r\n"
				+ "Cria uma annotation chamada @Conversao para identificar o método de conversão de temperatura, "
				+ "combinando o uso de enum, Map e annotations.\n");

		double valorConvertido = converter(68, UnidadeTemperatura.FAHRENHEIT, UnidadeTemperatura.CELSIUS);
		System.out.println("68°F em Celsius: " + valorConvertido);

	}

	private static final Map<UnidadeTemperatura, Double> fator = new HashMap<>();

	static {
		fator.put(UnidadeTemperatura.CELSIUS, 1.0);
		fator.put(UnidadeTemperatura.FAHRENHEIT, 1.8);
		fator.put(UnidadeTemperatura.KELVIN, 1.0);
	}

	@SuppressWarnings("incomplete-switch")
	@Conversao(descricao = "Converte temperatura entre unidades")
	public static double converter(double valor, UnidadeTemperatura origem, UnidadeTemperatura destino) {
		double tempCelsius = 0;
		switch (origem) {
		case CELSIUS:
			tempCelsius = valor;
			break;
		case FAHRENHEIT:
			tempCelsius = (valor - 32) / 1.8;
			break;
		case KELVIN:
			tempCelsius = valor - 273.15;
			break;
		}

		switch (destino) {
		case CELSIUS:
			return tempCelsius;
		case FAHRENHEIT:
			return tempCelsius * 1.8 + 32;
		case KELVIN:
			return tempCelsius + 273.15;
		default:
			return tempCelsius;
		}
	}
}

//Corpo da Classe
@interface TypeTemperatura {
	UnitTemperatura valor();
}

enum UnitTemperatura {
	CELSIUS, FAHRENHEIT, KELVIN;
}

@TypeTemperatura(valor = UnitTemperatura.CELSIUS)
class Import_4S {
	public static void run_Import_4S() {
		System.err.println("\t\t4.  Annotation @TipoTemperatura em Enum e Classe\r\n"
				+ "Define uma annotation @TipoTemperatura que recebe um valor do enum e a "
				+ "utiliza para anotar uma classe que registra temperaturas.\n");

		Import_4S registro = new Import_4S();
		registro.adicionarRegistro("Curitiba", 18.0);
		registro.adicionarRegistro("Recife", 28.0);
		registro.mostrarRegistros();

	}

	private Map<String, Double> registros = new HashMap<>();

	public void adicionarRegistro(String local, double temperatura) {
		registros.put(local, temperatura);
	}

	public void mostrarRegistros() {
		for (String local : registros.keySet()) {
			System.out.println("Local: " + local + " - Temperatura: " + registros.get(local) + "°C");
		}
	}
}

//Corpo da Classe
enum UnidadeTemperatura {
	CELSIUS, FAHRENHEIT, KELVIN, REAUMUR, RANKINE, ROMER, DELISLE, NEWTON;
}

@interface Descricao {
	String texto();
}

@Descricao(texto = "Utilitário para manipulação de temperaturas")
class Import_5S {
	public static void run_Import_5S() {
		System.err.println("\t\t5. Utilitário de Temperatura com Enum, Annotation e Map\r\n"
				+ "Combina os conceitos vistos: um enum para unidades, uma annotation customizada "
				+ "@Descricao para descrever a classe utilitária e um Map para armazenar valores de temperatura.\n");

		Import_5S util = new Import_5S();
		util.definirValor(UnidadeTemperatura.CELSIUS, 25.0);
		util.definirValor(UnidadeTemperatura.FAHRENHEIT, 77.0);
		util.definirValor(UnidadeTemperatura.KELVIN, 298.15);
		util.definirValor(UnidadeTemperatura.REAUMUR, 20);
		
		util.definirValor(UnidadeTemperatura.RANKINE, 536.67);
		util.definirValor(UnidadeTemperatura.ROMER, 20.625);
		util.definirValor(UnidadeTemperatura.DELISLE, 112.5);
		util.definirValor(UnidadeTemperatura.NEWTON, 8.25);

		System.out.println("Valor em Celsius    (°C) : " + util.obterValor(UnidadeTemperatura.CELSIUS)+"°C");
		System.out.println("Valor em Fahrenheit (°F) : " + util.obterValor(UnidadeTemperatura.FAHRENHEIT)+"°F");
		System.out.println("Valor em Kelvin     (°K) : " + util.obterValor(UnidadeTemperatura.KELVIN)+"°K");	
		
		System.out.println("Valor em Réaumur    (°R) : " + util.obterValor(UnidadeTemperatura.REAUMUR)+"°R");
		System.out.println("Valor em Rankine    (°Ra): " + util.obterValor(UnidadeTemperatura.RANKINE)+"°Ra");
		System.out.println("Valor em Romer      (°Rø): " + util.obterValor(UnidadeTemperatura.ROMER)+"°Rø");
		System.out.println("Valor em Delisle    (°De): " + util.obterValor(UnidadeTemperatura.DELISLE)+"°De");
		System.out.println("Valor em Newton     (°N) : " + util.obterValor(UnidadeTemperatura.NEWTON)+"°N");

	}

	private Map<UnidadeTemperatura, Double> valores = new HashMap<>();

	public void definirValor(UnidadeTemperatura unidade, double valor) {
		valores.put(unidade, valor);
	}

	public double obterValor(UnidadeTemperatura unidade) {
		return valores.getOrDefault(unidade, 0.0);
	}
}
