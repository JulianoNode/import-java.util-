package UTIL_04_Map;

import util.Linhas;

import java.lang.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class While_Matrix {
	public static void While_Run() {
		Linhas linhas = new Linhas();
		String collec = "\n\t Map<K, V>: Representa um mapeamento de chave-valor. \n";
		System.err.println(collec);

		linhas.run_Caracteres();
		Import_1WM.run_Import_1WM();

		linhas.run_Caracteres();
		Import_2WM.run_Import_2WM();

		linhas.run_Caracteres();
		Import_3WM.run_Import_3WM();

	}
}

//Corpo da Classe ___________________________________
//Definição da anotação personalizada
@interface Descricoes {
	String cidade();

	String pais();

	String estacao();

	String fonte();
}

//Enum para categorias de temperatura
enum Temperatura {
	FRIO, AMENO, QUENTE, EXTREMO;
}

class Import_1WM {
	@Descricoes(cidade = "São Paulo", pais = "Brasil", estacao = "Verão", fonte = "Meteorologia Oficial")

	public static void run_Import_1WM() {
		System.err.println("\t\t1.Explicação do código:\r\n"
				+ "Criamos um enum chamado Temperatura com diferentes níveis de temperatura.\r\n"
				+ "Criamos uma @interface chamada Descricao com quatro String como metadados.\r\n"
				+ "Utilizamos Map<String, Double> para armazenar temperaturas de cidades.\r\n"
				+ "Implementamos laços for e do-while para manipular os dados.\n");

		Map<String, Double> temperaturas = new HashMap<>();
		temperaturas.put("São Paulo", 30.5);
		temperaturas.put("Nova York", -2.0);
		temperaturas.put("Tóquio", 18.0);
		temperaturas.put("Dubai", 42.0);

		for (Map.Entry<String, Double> entrada : temperaturas.entrySet()) {
			String cidade = entrada.getKey();
			double temp = entrada.getValue();
			System.out.println("Cidade: " + cidade + " \t| Temperatura: " + temp + "°C \t| Categoria: "
					+ classificarTemperatura(temp));
		}
	}

	public static Temperatura classificarTemperatura(double temp) {
		if (temp < 10)
			return Temperatura.FRIO;
		if (temp < 25)
			return Temperatura.AMENO;
		if (temp < 35)
			return Temperatura.QUENTE;
		return Temperatura.EXTREMO;

	}
}

//Corpo da Classe ___________________________________

//Definição da anotação personalizada
@interface TemperInfo {
	String location();
	String date();
	String time();
	String recordedBy();
	float minTemp();
	float maxTemp();
}

//Enum para representar tipos de temperatura
enum TemperatureType {
	CELSIUS, FAHRENHEIT;
}

class Import_2WM {
	public static void run_Import_2WM() {
		System.err.println(
				"\t\t2.Este código implementa um sistema simples de registro de temperaturas, utilizando os conceitos que você solicitou.\n");

		Map<String, Float> temperatureData = new HashMap<>();

		// Usando um loop for para adicionar temperaturas
		for (int i = 1; i <= 5; i++) {
			temperatureData.put("Cidade" + i, 20.0f + i);
		}

		// Usando um loop do-while para exibir temperaturas
		int count = 0;
		do {
			String city = "Cidade" + (count + 1);
			System.out.println("Temperatura em " + city + ": " + temperatureData.get(city) + " °C");
			count++;
		} while (count < temperatureData.size());
	}

	// Exemplo de uso da anotação
	@TemperInfo(location = "São Paulo", date = "04/03/2025", time = "14:00", recordedBy = "Meteorologista", minTemp = 18.5f, maxTemp = 28.7f)
	public void recordTemperature() {
		System.out.println("Registrando temperatura...");
	}

}

//Corpo da Classe ___________________________________

//Enum representando as estações do ano
enum Estacao {
	PRIMAVERA, VERAO, OUTONO, INVERNO;
}

@Retention(RetentionPolicy.RUNTIME) // Necessário para acessar em tempo de execução
@Target(ElementType.TYPE) // Define onde pode ser usada (classe, método, etc.)
@interface MedicaoInfo {
	String local();
	String unidade();
	String sensor();
	String responsavel();
	float min();
	float max();
	float media();
	float desvio();
}

@MedicaoInfo(local = "Estação Central", unidade = "Celsius", sensor = "Temp-Sensor-3000", responsavel = "Dr. Silva", min = -10.0f, max = 40.0f, media = 15.0f, desvio = 5.0f)
class MinhaClasse {
}
class Import_3WM {

	
	public static void run_Import_3WM() {
		System.err.println("\t\t3. O que o código faz:\r\n"
				+ "Cria uma matriz de temperaturas (4x4) representando medições ao longo de diferentes estações.\r\n"
				+ "Usa um Map<String, Float> para armazenar a média das temperaturas por estação.\r\n"
				+ "Usa um enum para definir as estações do ano.\r\n"
				+ "Aplica uma @interface personalizada para anotar informações sobre as medições.\r\n"
				+ "Utiliza do-while para iterar pela matriz e calcular médias.\n");

		final int LINHAS = 4; // Número de estações
		final int COLUNAS = 4; // Número de medições por estação

		float[][] temperaturas = new float[LINHAS][COLUNAS];
		Random random = new Random();

		// Gerando temperaturas aleatórias entre -10 e 40 graus
		int i = 0;
		do {
			int j = 0;
			do {
				temperaturas[i][j] = -10 + random.nextFloat() * 50;
				j++;
			} while (j < COLUNAS);
			i++;
		} while (i < LINHAS);

		// Criando um mapa para armazenar médias por estação
		Map<String, Float> mediaPorEstacao = new HashMap<>();

		// Calculando média para cada estação
		i = 0;
		do {
			float soma = 0;
			int j = 0;
			do {
				soma += temperaturas[i][j];
				j++;
			} while (j < COLUNAS);

			float media = soma / COLUNAS;
			mediaPorEstacao.put(Estacao.values()[i].name(), media);
			i++;
		} while (i < LINHAS);

		// Exibindo resultados
		System.out.println("Médias de Temperatura por Estação:");
		for (Map.Entry<String, Float> entry : mediaPorEstacao.entrySet()) {
			System.out.println("Estação " + entry.getKey() + " \t| Temperatura " + entry.getValue() + " °C");
		}

		System.out.println("\n\n");
		 Class<?> clazz = MinhaClasse.class;
		 // Verifica se a anotação está presente
        if (clazz.isAnnotationPresent(MedicaoInfo.class)) {
		MedicaoInfo info = clazz.getAnnotation(MedicaoInfo.class);
		System.out.println(" O valor da anotação é :" + info.local());
		System.out.println(" O valor da anotação é :" + info.responsavel());
		System.out.println(" O valor da anotação é :" + info.sensor());
		System.out.println(" O valor da anotação é :" + info.unidade());
		System.out.println(" O valor da anotação é :" + info.desvio());
		System.out.println(" O valor da anotação é :" + info.max());
		System.out.println(" O valor da anotação é :" + info.media());
		System.out.println(" O valor da anotação é :" + info.min());
		
        } else {
            System.out.println("A anotação não está presente.");
        }
		
	}
}