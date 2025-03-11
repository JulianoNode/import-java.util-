package UTIL_04_Map;

import util.Linhas;

public class If_Else_IF {
	public static void If_Else_IF_Run() {

		Linhas linhas = new Linhas();
		String collec = "\n\t Map<K, V>: Representa um mapeamento de chave-valor. \n";
		System.err.println(collec);

		linhas.run_Caracteres();
		Import_1IF.run_Import_1IF();

		linhas.run_Caracteres();
		Import_2IF.run_Import_2IF();

		linhas.run_Caracteres();
		Import_3IF.run_Import_3IF();
	}
}

class Import_1IF {
	public static void run_Import_1IF() {
		System.err.println("\t\t1. Verificador de Temperatura por Cidade\r\n"
				+ "Este código define um enum para os níveis de temperatura, uma annotation para documentar o \n"
				+ "método e utiliza um Map para armazenar temperaturas de diferentes cidades.\n");
		// Uso de Map para armazenar temperaturas de diferentes cidades
		java.util.Map<String, Integer> temperatureMap = new java.util.HashMap<>();
		temperatureMap.put("Cidade A", 5);
		temperatureMap.put("Cidade B", 18);
		temperatureMap.put("Cidade C", 30);

		// Verifica e exibe o nível de temperatura para cada cidade
		for (String city : temperatureMap.keySet()) {
			TemperatureLevel level = checkTemperature(temperatureMap.get(city));
			System.out.println("A temperatura em " + city + " é " + level);
		}
	}

	// Enum definindo os níveis de temperatura
	enum TemperatureLevel {
		COLD, WARM, HOT;
	}

	// Annotation personalizada para documentar informações sobre a temperatura
	@interface TemperatureInfo {
		String description() default "Informação sobre a faixa de temperatura";
	}

	// Método anotado que verifica a temperatura e retorna o nível correspondente
	@TemperatureInfo(description = "Determina o nível de temperatura com base no valor informado")
	public static TemperatureLevel checkTemperature(int temperature) {
		if (temperature < 10) {
			return TemperatureLevel.COLD;
		} else if (temperature < 25) {
			return TemperatureLevel.WARM;
		} else {
			return TemperatureLevel.HOT;
		}
	}
}

class Import_2IF {
	public static void run_Import_2IF() {
		System.err.println("\t\t2.Monitor de Temperatura de Sensores\r\n"
				+ "Neste exemplo, o enum representa o status da temperatura. Um Map armazena leituras de sensores,"
				+ "\ne a annotation documenta o método que determina o status baseado na leitura.\n");

		// Uso de Map para armazenar leituras de diferentes sensores
		java.util.Map<String, Double> sensorData = new java.util.HashMap<>();
		sensorData.put("Sensor 1", -3.5);
		sensorData.put("Sensor 2", 8.0);
		sensorData.put("Sensor 3", 22.0);
		sensorData.put("Sensor 4", 29.5);
		sensorData.put("Sensor 5", 37.0);

		// Verifica e exibe o status de cada sensor
		for (String sensor : sensorData.keySet()) {
			TemperatureStatus status = getStatus(sensorData.get(sensor));
			System.out.println("O " + sensor + " registrou " + sensorData.get(sensor) + "°C e está " + status);
		}
	}

	// Enum definindo os status de temperatura
	public enum TemperatureStatus {
		FREEZING, COOL, MILD, WARM, HOT;
	}

	// Annotation para indicar informações sobre os limites de temperatura
	@interface TemperatureThreshold {
		String info() default "Níveis de limite para a temperatura";
	}

	// Método anotado que retorna o status baseado na temperatura lida
	@TemperatureThreshold(info = "Retorna o status com base na leitura da temperatura")
	public static TemperatureStatus getStatus(double temperature) {
		if (temperature < 0) {
			return TemperatureStatus.FREEZING;
		} else if (temperature < 15) {
			return TemperatureStatus.COOL;
		} else if (temperature < 25) {
			return TemperatureStatus.MILD;
		} else if (temperature < 35) {
			return TemperatureStatus.WARM;
		} else {
			return TemperatureStatus.HOT;
		}
	}
}

class Import_3IF {
	public static void run_Import_3IF() {
		System.err.println("\t\t3. Relatório de Temperatura por Cidade\r\n"
				+ "Aqui, o enum define faixas de temperatura, a annotation marca o método de determinação de faixa, "
				+ "\ne um Map é utilizado para associar cidades às suas faixas de temperatura.\n");
		
        // Map que associa cidades a temperaturas (em °C)
		java.util.Map<String, Integer> cityTemperatures = new java.util.HashMap<>();
        cityTemperatures.put("Fortaleza", 32);
        cityTemperatures.put("Recife", 28);
        cityTemperatures.put("Salvador", -9);
        cityTemperatures.put("São Luís", 22);
        cityTemperatures.put("Minas Gerais", 36);
        cityTemperatures.put("Bahia", 18);
        cityTemperatures.put("Santiago", 15);
        cityTemperatures.put("São Bento", -3);

        // Cria um relatório associando cada cidade à sua faixa de temperatura
        java.util.Map<String, TempRange> report = new java.util.HashMap<>();
        for (java.util.Map.Entry<String, Integer> entry : cityTemperatures.entrySet()) {
            report.put(entry.getKey(), determineRange(entry.getValue()));
            
        }
     
        // Exibe o relatório
        for (String city : report.keySet()) {
            System.out.println("Cidade: " + city + " - Faixa de temperatura: " + report.get(city));
            
        }
	}
	
    // Enum para as faixas de temperatura
    public enum TempRange {
        LOW, MEDIUM, HIGH;
    }

    // Annotation personalizada para identificar informações do relatório
    @interface ReportInfo {
        String author() default "Desconhecido";
    }

    // Método anotado que determina a faixa de temperatura com base no valor informado
    @ReportInfo(author = "Programador Exemplo")
    public static TempRange determineRange(int temperature) {
        if (temperature < 15) {
            return TempRange.LOW;
        } else if (temperature < 30) {
            return TempRange.MEDIUM;
        } else {
            return TempRange.HIGH;
        }
    }

}
