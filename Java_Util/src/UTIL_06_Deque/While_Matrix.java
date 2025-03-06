package UTIL_06_Deque;

import java.util.Deque;
import java.util.LinkedList;

import util.Linhas;

public class While_Matrix {
	public static void While_Run() {
		Linhas linhas = new Linhas();
		String collec = "\n\t Deque<E>: Representa uma fila dupla (pode adicionar/remover de ambas as extremidades). \n";
		System.err.println(collec);

		linhas.run_Caracteres();
		Import_1WM.run_Import_1WM();

		linhas.run_Caracteres();
		Import_2WM.run_Import_2WM();

		linhas.run_Caracteres();
		Import_3WM.run_Import_3WM();

	}
}

//Corpo da Classe _______________________________
class Import_1WM {
	public static void run_Import_1WM() {
		System.err.println("\t\t1.\n");

		// Criando o Deque para armazenar roupas
		Deque<Roupa> guardaRoupa = new LinkedList<>();
		guardaRoupa.add(new RoupaCasual());
		guardaRoupa.add(new RoupaFormal());

		// Usando o while para exibir detalhes das roupas
		while (!guardaRoupa.isEmpty()) {
			Roupa roupa = guardaRoupa.poll();
			roupa.exibirDetalhes();
		}

		// Fórmula para calcular tempo em física (MRUA)
		// Fórmula: v = u + at, onde v é a velocidade final, u é a velocidade inicial, a
		// é a aceleração e t é o tempo.
		double v = 20.0; // velocidade final (m/s)
		double u = 0.0; // velocidade inicial (m/s)
		double a = 2.0; // aceleração (m/s²)
		double t; // tempo (s)

		// Usando if, else if e else para determinar se a aceleração é positiva ou
		// negativa
		if (a > 0) {
			t = (v - u) / a; // Calculando tempo para aceleração positiva
			System.out.println("O tempo necessário para atingir a velocidade final é: " + t + " segundos.");
		} else if (a == 0) {
			t = Double.POSITIVE_INFINITY; // Tempo infinito se a aceleração for zero
			System.out.println("Com aceleração zero, o tempo seria infinito.");
		} else {
			t = (v - u) / a; // Para aceleração negativa
			System.out.println("O tempo necessário para atingir a velocidade final (com aceleração negativa) é: " + t
					+ " segundos.");
		}

		// Usando um laço for para simular o processo de aumento da velocidade ao longo
		// do tempo
		System.out.println("\nSimulando aumento de velocidade com aceleração positiva...");
		for (double tempoSimulado = 0; tempoSimulado <= t; tempoSimulado += 1) {
			double velocidade = u + a * tempoSimulado;
			System.out.println("Tempo: " + tempoSimulado + " s - Velocidade: " + velocidade + " m/s");
		}

		// Usando um laço while para simular o aumento de velocidade de forma mais
		// controlada
		double tempoSimuladoWhile = 0;
		while (tempoSimuladoWhile <= t) {
			double velocidadeWhile = u + a * tempoSimuladoWhile;
			System.out.println("Tempo (While): " + tempoSimuladoWhile + " s - Velocidade: " + velocidadeWhile + " m/s");
			tempoSimuladoWhile += 1;
		}

	}
}

//Corpo da Classe _______________________________
class Import_2WM {
	public static void run_Import_2WM() {
		System.err.println("\t\t2.\n");

		// Inicializando Deque para armazenar tempos de voo
		Deque<Double> temposDeVoo = new LinkedList<>();

		// Definindo variáveis
		double velocidadeInicial = 30.0; // Velocidade em m/s
		double anguloChute = 45.0; // Ângulo do chute em graus

		// Instanciando a classe de cálculo de tempo
		CalculoTempo2 calculo = new CalculoTempoChute();

		// Variáveis para controle de tipo de chute
		ChuteTipo tipoDeChute = ChuteTipo.MEDIO;

		// Estrutura condicional (if, else if, else) para definir ações com base no tipo
		// de chute
		if (tipoDeChute == ChuteTipo.FRAQUINHO) {
			velocidadeInicial = 15.0; // Chute fraco
		} else if (tipoDeChute == ChuteTipo.MEDIO) {
			velocidadeInicial = 30.0; // Chute médio
		} else if (tipoDeChute == ChuteTipo.FORTE) {
			velocidadeInicial = 45.0; // Chute forte
		}

		// Calcular o tempo de voo
		double tempoDeVoo = calculo.calcularTempoDeVoo(velocidadeInicial, anguloChute);

		// Adicionando o tempo de voo ao Deque
		temposDeVoo.add(tempoDeVoo);

		// Loop for para exibir os tempos de voo calculados
		System.out.println("Tempos de voo calculados: ");
		for (Double tempo : temposDeVoo) {
			System.out.println("Tempo de voo: " + tempo + " segundos");
		}

		// Usando while para simular o acompanhamento de diferentes chutes
		int count = 0;
		while (count < 5) {
			System.out.println("Chute " + (count + 1) + ": Tempo de voo " + temposDeVoo.peek() + " segundos.");
			count++;
			// Simulando mudança no tipo de chute após cada iteração
			if (count == 1) {
				tipoDeChute = ChuteTipo.FORTE;
			} else if (count == 2) {
				tipoDeChute = ChuteTipo.MEDIO;
			} else if (count == 3) {
				tipoDeChute = ChuteTipo.FRAQUINHO;
			}
			tempoDeVoo = calculo.calcularTempoDeVoo(velocidadeInicial, anguloChute);
			temposDeVoo.add(tempoDeVoo); // Adicionando novos tempos
		}

	}
}

//Corpo da Classe _______________________________
class Import_3WM {
	public static void run_Import_3WM() {
		System.err.println("\t\t3. \n");

	    // Deque para armazenar medições de chuva
        Deque<Double> medicoesChuva = new LinkedList<>();
        
        // Inicializando a calculadora de tempo
        CalculadoraChuva calculadora = new CalculadoraChuva();
        
        // Adicionando medições ao Deque
        medicoesChuva.add(3.5);  // Intensidade de chuva 3.5mm/h
        medicoesChuva.add(1.2);  // Intensidade de chuva 1.2mm/h
        medicoesChuva.add(5.0);  // Intensidade de chuva 5.0mm/h
        
        // Área da região afetada pela chuva (em metros quadrados)
        double area = 1000.0;

        // Variável para armazenar o tempo calculado
        double tempoChuva;

        // Usando a estrutura if, else if, else para simular o estado do tempo
        EstadoTempo estadoAtual = EstadoTempo.CHUVOSO;

        if (estadoAtual == EstadoTempo.ENSOLARADO) {
            System.out.println("O tempo está ensolarado, não há chuva.");
        } else if (estadoAtual == EstadoTempo.CHUVOSO) {
            System.out.println("O tempo está chuvoso.");
            // Enquanto houver medições de chuva no deque, calcular o tempo
            while (!medicoesChuva.isEmpty()) {
                double intensidadeChuva = medicoesChuva.poll();
                tempoChuva = calculadora.calcularTempo(intensidadeChuva, area);
                System.out.println("Tempo de chuva com intensidade " + intensidadeChuva + "mm/h: " + tempoChuva + " horas.");
            }
        } else if (estadoAtual == EstadoTempo.NUBLADO) {
            System.out.println("O tempo está nublado, há chance de chuva.");
        } else if (estadoAtual == EstadoTempo.TEMPESTADE) {
            System.out.println("O tempo está em tempestade, cuidado!");
        } else {
            System.out.println("Estado do tempo desconhecido.");
        }

        // Exemplo de uso do for para iterar sobre as medições e calcular o tempo
        System.out.println("\nCalculando com um laço for:");
        Double[] intensidades = { 2.5, 3.0, 4.0, 1.5 };
        for (double intensidade : intensidades) {
            tempoChuva = calculadora.calcularTempo(intensidade, area);
            System.out.println("Tempo de chuva com intensidade " + intensidade + "mm/h: " + tempoChuva + " horas.");
        }
	}
}

//1 Corpo da Classe abstract e enum _______________________________
enum TipoRoupa {
	CASUAL, FORMAL, ESPORTIVA, INVERNO
}

abstract class Roupa {
	String nome;
	TipoRoupa tipo;

	public Roupa(String nome, TipoRoupa tipo) {
		this.nome = nome;
		this.tipo = tipo;
	}

	abstract void exibirDetalhes();
}

class RoupaCasual extends Roupa {
	public RoupaCasual() {
		super("Camiseta e Jeans", TipoRoupa.CASUAL);
	}

	@Override
	void exibirDetalhes() {
		System.out.println("Roupa Casual: " + nome + " - Ideal para o dia a dia.");
	}
}

class RoupaFormal extends Roupa {
	public RoupaFormal() {
		super("Terno e Gravata", TipoRoupa.FORMAL);
	}

	@Override
	void exibirDetalhes() {
		System.out.println("Roupa Formal: " + nome + " - Ideal para eventos formais.");
	}
}

//2 Corpo da Classe abstract e enum _______________________________

//Definindo o Enum para as opções de chute
enum ChuteTipo {
	FRAQUINHO, MEDIO, FORTE
}

//Classe Abstrata para cálculo do tempo de voo
abstract class CalculoTempo2 {
	abstract double calcularTempoDeVoo(double velocidade, double angulo);
}

//Implementação concreta da classe CalculoTempo
class CalculoTempoChute extends CalculoTempo2 {
	@Override
	public double calcularTempoDeVoo(double velocidade, double angulo) {
		// A fórmula do tempo de voo é dada por: T = 2 * v * sin(θ) / g
		// Onde:
		// v = velocidade inicial
		// θ = ângulo do chute
		// g = aceleração gravitacional (aproximadamente 9.8 m/s²)

		double g = 9.8; // aceleração gravitacional
		double anguloRadiano = Math.toRadians(angulo); // Convertendo ângulo para radiano
		return (2 * velocidade * Math.sin(anguloRadiano)) / g;
	}
}

//3 Corpo da Classe abstract e enum _______________________________

//Enum para representar os diferentes estados do tempo
enum EstadoTempo {
	ENSOLARADO, CHUVOSO, NUBLADO, TEMPESTADE
}

//Classe abstrata para calcular o tempo
abstract class CalculadoraTempo {
	public abstract double calcularTempo(double intensidadeChuva, double area);
}

//Classe concreta que implementa a lógica de cálculo do tempo de chuva
class CalculadoraChuva extends CalculadoraTempo {

	@Override
	public double calcularTempo(double intensidadeChuva, double area) {
		// Fórmula simples para cálculo do tempo de chuva (exemplo)
		// Tempo = Área / Intensidade da chuva
		return area / intensidadeChuva;
	}
}