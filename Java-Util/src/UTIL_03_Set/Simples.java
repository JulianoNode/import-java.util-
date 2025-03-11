package UTIL_03_Set;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import util.Linhas;

public class Simples {
	public static void Simples_Run() {

		Linhas linhas = new Linhas();
		String collec = "\n\t Set: Representa um conjunto (não permite elementos duplicados). \n";
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
class Import_1S {
	public static void run_Import_1S() {

		System.out.println("\t Set<E>: Representa um conjunto (não permite elementos duplicados). ");

		// Criando um Set de inteiros
		Set<Integer> numeros = new HashSet<>();

		// Adicionando elementos
		numeros.add(1);
		numeros.add(2);
		numeros.add(3);
		numeros.add(2); // Este valor não será adicionado, pois já existe no Set

		// Imprimindo os elementos do Set
		System.out.println("Elementos no Set:");
		for (Integer numero : numeros) {
			System.out.println(numero);
		}

		// Verificando se o Set contém um elemento
		boolean contem = numeros.contains(2);
		System.out.println("O Set contém o número 2? " + contem);

		// Removendo um elemento
		numeros.remove(1);
		System.out.println("Após remoção do número 1:");
		for (Integer numero : numeros) {
			System.out.println(numero);
		}

		// Verificando o tamanho do Set
		System.out.println("Tamanho do Set: " + numeros.size());

	}
}

//Corpo da Classe
record Produto(String nome, double preco) {
}

class Import_2S {
	public static void run_Import_2S() {
		System.err.println("\t\t2. Verificando se um Set contém um elemento específico\n");

		Set<Produto> produtos = new HashSet<>();
		produtos.add(new Produto("Celular", 1500.0));
		produtos.add(new Produto("Notebook", 3000.0));
		produtos.add(new Produto("Celular", 1500.0)); // Não será adicionado

		produtos.forEach(System.out::println);

	}
}

//Corpo da Classe
record Aluno(String nome, int matricula) {
}

class Import_3S {
	public static void run_Import_3S() {
		System.err.println("\t\t3. Este exemplo percorre um conjunto de registros e imprime seus valores.\n");

		Set<Aluno> alunos = new HashSet<>();
		alunos.add(new Aluno("Carlos", 101));
		alunos.add(new Aluno("Maria", 102));
		alunos.add(new Aluno("João", 103));

		System.out.println("Antes da remoção: " + alunos);
		alunos.remove(new Aluno("Maria", 102)); // Remove pelo valor
		System.out.println("Depois da remoção: " + alunos);

	}
}

//Corpo da Classe
@Retention(RetentionPolicy.RUNTIME)
@interface Permissoes {
	String[] valores();
}

@Permissoes(valores = { "ADMIN", "USER", "GESTOR", "CIO"})
class Usuario {
	// Classe representando um usuário com permissões
}

class Import_4S {
	public static void run_Import_4S() {
		System.err.println("\t\t4. Uso de Set para Converter os Valores da Anotação\n");

		Permissoes permissoes = Usuario.class.getAnnotation(Permissoes.class);

		if (permissoes != null) {
			Set<String> permissoesSet = new HashSet<>(Arrays.asList(permissoes.valores()));
			System.out.println("Permissões do usuário: " + permissoesSet);
		}
	}
}

//Corpo da Classe
enum Perfil {
	ADMIN, USER, GUEST
}

@Retention(RetentionPolicy.RUNTIME)
@interface Acesso {
	Perfil[] perfis();
}

@Acesso(perfis = { Perfil.ADMIN, Perfil.USER, Perfil.GUEST })
class Sistema {}

class Import_5S {
	public static void run_Import_5S() {
		System.err.println("\t\t5. Conversão dos Valores Enum para Set<Perfil>\n");
		
		Acesso acesso = Sistema.class.getAnnotation(Acesso.class);

		if (acesso != null) {
			Set<Perfil> perfisSet = new HashSet<>(Arrays.asList(acesso.perfis()));
			System.out.println("Perfis do sistema: " + perfisSet);
		}
	}
}
