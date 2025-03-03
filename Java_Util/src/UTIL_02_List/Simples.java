package UTIL_02_List;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import util.Linhas;

public class Simples {
	public static void Simples_Run() {

		Linhas linhas = new Linhas();
		String collec = "\n\t Nome\n";
		System.err.println(collec);

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

//Corpo da Classe

@SuppressWarnings("serial")
class Import_1S extends ArrayList<String> {
	@Override
	public boolean add(String e) {
		System.out.println("Adicionando: " + e);
		return super.add(e);
	}

	public static void run_Import_1S() {
		System.err.println("\t\t1. Selecionado Itens de uma lista \n");
		
		List<String> lista = new Import_1S();
		lista.add("Item 1");
		lista.add("Item 2");
		lista.add("Item 3");
		lista.add("Item 4");

	}
}

//Corpo da Classe

@FunctionalInterface
interface OperacaoLista {
	void executar(List<String> lista);
}

class Import_2S {
	public static void run_Import_2S() {
		System.err.println("\t\t2. Imprimindo um alista de Nomes\n");
		
		OperacaoLista imprimir = lista -> lista.forEach(System.err::println);
		List<String> nomes = Arrays.asList("Ana", "Carlos", "Beatriz");
		imprimir.executar(nomes);

	}
}

//Corpo da Classe

@interface ValidarLista {
	String mensagem() default "Lista não pode estar vazia!";
}

class ListaUtil {
	@ValidarLista(mensagem = "A lista de nomes não pode ser vazia!")
	public static void validarLista(List<String> lista) {
		if (lista.isEmpty()) {
			throw new IllegalArgumentException("Erro: " + ValidarLista.class.getDeclaredMethods()[0].getDefaultValue());
		}
	}
}

class Import_3S {
	public static void run_Import_3S() {
		System.err.println("\t\t3. Saída: Erro: Lista não pode estar vazia!\n");
		List<String> nomes = new ArrayList<>();

		try {
			ListaUtil.validarLista(nomes);
		} catch (Exception e) {
			System.out.println(e.getMessage()); // Saída: Erro: Lista não pode estar vazia!
		}

	}
}

//Corpo da Classe
@FunctionalInterface
interface Filtro {
	boolean testar(String s);
}

class Import_4S {
	public static void run_Import_4S() {
		System.err.println("\t\t4. Saida de filtro com o caracter M \n");
		
		List<String> nomes = new ArrayList<>();
		nomes.add("João");
		nomes.add("Maria");
		nomes.add("Carlos");
		nomes.add("Marcia");
		nomes.add("Ana");
		nomes.add("Mihh");
		nomes.add("Juliano");
		nomes.add("Mucaco");
		System.out.println(nomes);
		System.err.println("\nFiltrando com o caracter ' M ' \n");
		Filtro filtro = nome -> nome.startsWith("M");
		nomes.removeIf(nome -> !filtro.testar(nome));
		System.out.println(nomes); // Saída: [Maria]

	}
}

//Corpo da Classe
class Produto {
	private String nome;

	public Produto(String nome) {
		this.nome = nome;
	}

	@Deprecated
	public String getNomeAntigo() {
		return nome;
	}

	public String getNomeNovo() {
		return nome;
	}
}

class Import_5S {
	public static void run_Import_5S() {
		System.err.println("\t\t5. Lista de Aparelhos\n");
		
		List<Produto> produtos = new ArrayList<>();
		produtos.add(new Produto("Celular"));
		produtos.add(new Produto("Teclado"));
		produtos.add(new Produto("Radio"));

		System.out.println(produtos.get(0).getNomeAntigo()); // Aviso de método obsoleto
		System.out.println(produtos.get(1).getNomeAntigo()); // Aviso de método obsoleto
		System.out.println(produtos.get(2).getNomeAntigo()); // Aviso de método obsoleto

	}
}