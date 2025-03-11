package UTIL_04_Map;

import util.Linhas;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class For_Matrix {
	public static void For_Run() {

		Linhas linhas = new Linhas();
		String collec = "\n\t Map<K, V>: Representa um mapeamento de chave-valor..\n";
		System.err.println(collec);

		linhas.run_Caracteres();
		Import_1F.run_Import_1F();

		linhas.run_Caracteres();
		Import_2F.run_Import_2F();

		linhas.run_Caracteres();
		Import_3F.run_Import_3F();

	}
}

//Corpo da Calsse _______________________________
@interface TemperatureInfo {
	String description() default "Temperature Data";
}

enum TempStatus {
	FREEZING, COLD, WARM, HOT;
}

class Import_1F {
	public static void run_Import_1F() {
		System.err.println("\t\t1.\n");

		Map<String, Double> temperatures = new LinkedHashMap<>();
		temperatures.put("Natal   ", 30.5);
		temperatures.put("Fortaleza", 28.0);
		temperatures.put("Recife  ", 26.0);
		temperatures.put("Salvador", 32.0);
		temperatures.put("Maceió    ", 29.0);
		temperatures.put("Curitiba", 12.0);
		temperatures.put("São Paulo", 18.0);
		temperatures.put("Rio de Janeiro", 26.0);
		temperatures.put("Porto Alegre", 14.0);
		temperatures.put("Fortaleza", 30.0);
		temperatures.put("Fortaleza", 32.5);
		temperatures.put("Recife    ", 28.8);
		temperatures.put("Salvador", -9.1);
		temperatures.put("São Luís", 22.8);
		temperatures.put("Minas Gerais", 36.3);
		temperatures.put("Bahia     ", 18.9);
		temperatures.put("Santiago", 15.5);
		temperatures.put("São Bento", -3.3);

		for (Map.Entry<String, Double> entry : temperatures.entrySet()) {
			String city = entry.getKey();
			double temp = entry.getValue();
			TempStatus status = getTempStatus(temp);
			System.out.println("Cidade: " + city + " \t| Temperatura: " + temp + "°C \t| Status: " + status);
		}
	}

	@TemperatureInfo(description = "Classifica a temperatura com base nos graus Celsius")
	public static TempStatus getTempStatus(double temp) {
		if (temp < 5) {
			return TempStatus.FREEZING;
		} else if (temp < 20) {
			return TempStatus.COLD;
		} else if (temp < 30) {
			return TempStatus.WARM;
		} else {
			return TempStatus.HOT;
		}
	}
}

//Corpo da Calsse _______________________________
enum TempCategory {
	FREEZING, COOL, MODERATE, WARM, HOT;
}

@interface TempInfo {
	String info() default "Temperature Category";
}

class Import_2F {
	public static void run_Import_2F() {
		System.err.println("\t\t2. Classificação de Categorias de Temperatura\r\n"
				+ "Este exemplo utiliza um Map para armazenar temperaturas de cidades e "
				+ "\num array para iterar sobre elas. O método getCategory classifica a "
				+ "\ntemperatura usando uma enumeração TempCategory e estruturas if/else if, "
				+ "\nestando anotado com @TemperatureInfo.\n");

		Map<String, Double> cityTemps = new HashMap<>();
		cityTemps.put("Brasília", 18.0);
		cityTemps.put("Belo Horizonte", 22.0);
		cityTemps.put("Florianópolis", 16.0);
		cityTemps.put("Salvador", 30.0);
		cityTemps.put("Manaus AM", 33.0);

		String[] cities = { "Brasília", "Belo Horizonte", "Florianópolis", "Salvador", "Manaus AM" };
		for (String city : cities) {
			double temp = cityTemps.get(city);
			TempCategory category = getCategory(temp);
			System.out.println("Cidade: " + city + " \t| Temperatura: " + temp + "°C \t| Categoria: " + category);
		}

	}

	@TempInfo(info = "Determina a categoria de temperatura")
	public static TempCategory getCategory(double temp) {
		if (temp <= 0) {
			return TempCategory.FREEZING;
		} else if (temp <= 15) {
			return TempCategory.COOL;
		} else if (temp <= 25) {
			return TempCategory.MODERATE;
		} else if (temp <= 35) {
			return TempCategory.WARM;
		} else {
			return TempCategory.HOT;
		}
	}
}

//Corpo da Calsse _______________________________
@interface Temperature {
	String note() default "Temperature classification method";
}

enum TemperatureStatus {
	FRIO, AMENO, QUENTE;
}

class Import_3F {
	public static void run_Import_3F() {
		System.err.println("\t\t3. Estatísticas de Temperatura por Cidade\r\n"
				+ "Este exemplo reúne todos os elementos: um Map com temperaturas de cidades, "
				+ "\nenumeração TemperatureStatus para classificar (usando if/else if) e uma annotation "
				+ "\ncustomizada aplicada ao método classify. Além de iterar com for, o programa acumula "
				+ "\nestatísticas (quantidade de cidades em cada categoria).\n");

		Map<String, Double> cityTemps = new HashMap<>();
		cityTemps.put("Curitiba", 12.0);
		cityTemps.put("São Paulo", 28.0);
		cityTemps.put("Rio de Janeiro", 26.0);
		cityTemps.put("Porto Alegre", 14.0);
		cityTemps.put("Fortaleza", 30.0);
		cityTemps.put("Fortaleza", 32.5);
		cityTemps.put("Recife  ", 28.8);
		cityTemps.put("Salvador", -9.1);
		cityTemps.put("São Luís", 22.8);
		cityTemps.put("Minas Gerais", 36.3);
		cityTemps.put("Bahia   ", 18.9);
		cityTemps.put("Santiago", 15.5);
		cityTemps.put("São Bento", -3.3);
		cityTemps.put("Santos   ", 35.5);

		int countFrio = 0;
		int countAmeno = 0;
		int countQuente = 0;

		for (Map.Entry<String, Double> entry : cityTemps.entrySet()) {
			
			String city = entry.getKey();
			double temp = entry.getValue();
			TemperatureStatus status = classify(temp);
			
			System.out.println("Cidade: " + city + "\t| Temperatura: " + temp + "°C \t| Status: " + status);
			if (status == TemperatureStatus.FRIO) {
				countFrio++;
			} else if (status == TemperatureStatus.AMENO) {
				countAmeno++;
			} else {
				countQuente++;
			}
		}

		System.out.println("\nResumo:");
		System.out.println("Frio: " + countFrio);
		System.out.println("Ameno: " + countAmeno);
		System.out.println("Quente: " + countQuente);

	}

	@Temperature(note = "Classifica a temperatura em FRIO, AMENO ou QUENTE")
	public static TemperatureStatus classify(double temp) {
		if (temp < 15) {
			return TemperatureStatus.FRIO;
		} else if (temp < 25) {
			return TemperatureStatus.AMENO;
		} else {
			return TemperatureStatus.QUENTE;
		}
	}

}
