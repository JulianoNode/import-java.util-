package UTIL_02_List;

import util.Linhas;

import java.util.ArrayList;
import java.util.List;

public class If_Else_IF {
	public static void If_Else_IF_Run() {

		Linhas linhas = new Linhas();
		String collec = "\n\t List: Representa uma lista ordenada. \n";
		System.err.println(collec);

		linhas.run_Caracteres();
		Import_1IF.run_Import_1IF();

		linhas.run_Caracteres();
		Import_2IF.run_Import_2IF();

		linhas.run_Caracteres();
		Import_3IF.run_Import_3IF();
		
		linhas.run_Caracteres();
		Import_4IF.run_Import_4IF();

		linhas.run_Caracteres();
		Import_5IF.run_Import_5IF();

		linhas.run_Caracteres();
		Import_6IF.run_Import_6IF();
	}
}

//Corpo da Classe
class Import_1IF {

	// Definição do Record Produto
	record Produto(String nome, double preco, String categoria) {
	}

	public static void run_Import_1IF() {
		System.err.println("\t\t1.  Filtrar produtos com base no preço e categoria \n");
		
		List<Produto> produtos = new ArrayList<>();

		// Adicionando produtos
		produtos.add(new Produto("Notebook", 3500.00, "Eletrônico"));
		produtos.add(new Produto("Smartphone", 2500.00, "Eletrônico"));
		produtos.add(new Produto("Livro Java", 150.00, "Livro"));
		produtos.add(new Produto("Cadeira Gamer", 1200.00, "Móveis"));
		produtos.add(new Produto("Mesa Escritório", 800.00, "Móveis"));

		// Filtrar produtos com base no preço e categoria
		filtrarProdutos(produtos, 2000.00, "Eletrônico");
	}

	public static void filtrarProdutos(List<Produto> produtos, double precoMax, String categoria) {
		System.out.println("Produtos filtrados:");
		for (Produto p : produtos) {
			if (p.preco() <= precoMax && p.categoria().equalsIgnoreCase(categoria)) {
				System.out.println(p);
			} else if (p.preco() > precoMax && p.categoria().equalsIgnoreCase(categoria)) {
				System.out.println("Produto acima do preço máximo: " + p.nome());
			} else if (!p.categoria().equalsIgnoreCase(categoria)) {
				System.out.println("Produto de categoria diferente: " + p.nome());
			}
		}

	}

}

//Corpo da Classe

//Anotação personalizada
@interface ProductInfo {
	String category();

	double minPrice();
}

//Classe Produto
@ProductInfo(category = "Eletrônico", minPrice = 100.0)
class Product {
	String name;
	String category;
	double price;

	public Product(String name, String category, double price) {
		this.name = name;
		this.category = category;
		this.price = price;
	}
}

class Import_2IF {
	public static void run_Import_2IF() {
		System.err.println("\t\t2. Categoria de produtos \n");
		
		List<Product> products = new ArrayList<>();
		products.add(new Product("Celular", "Eletrônico", 1200));
		products.add(new Product("Fone de ouvido", "Eletrônico", 200));
		products.add(new Product("Livro", "Educação", 50));
		products.add(new Product("Monitor", "Eletrônico", 700));
		products.add(new Product("Caderno", "Educação", 30));

		// Filtrando produtos
		for (Product p : products) {
			if (p.category.equals("Eletrônico") && p.price > 1000) {
				System.out.println(p.name + " é um eletrônico caro.");
			} else if (p.category.equals("Eletrônico") && p.price > 500) {
				System.out.println(p.name + " é um eletrônico moderado.");
			} else if (p.category.equals("Eletrônico")) {
				System.out.println(p.name + " é um eletrônico barato.");
			} else if (p.category.equals("Educação") && p.price > 40) {
				System.out.println(p.name + " é um material educacional premium.");
			} else {
				System.out.println(p.name + " é um material educacional comum.");
			}
		}

	}
}

//Corpo da Classe
class Import_3IF {

	public record Aluno(String nome, double nota) {
	}

	public static void run_Import_3IF() {
		System.err.println("\t\t3.  Notas dos alunos \n");
		
		List<Aluno> alunos = List.of(new Aluno("Alice", 9.5), new Aluno("Bob", 6.0), new Aluno("Carlos", 4.5),
				new Aluno("Diana", 7.2));

		for (Aluno aluno : alunos) {
			if (aluno.nota() >= 9) {
				System.out.println(aluno.nome() + " está na categoria: Excelente.");
			} else if (aluno.nota() >= 7) {
				System.out.println(aluno.nome() + " está na categoria: Bom.");
			} else if (aluno.nota() >= 5) {
				System.out.println(aluno.nome() + " está na categoria: Regular.");
			} else {
				System.out.println(aluno.nome() + " está na categoria: Insuficiente.");
			}
		}

	}
}

//Corpo da Classe
//Anotação personalizada
@interface Role {
	String level();
}

//Classe Usuário
@Role(level = "Admin")
class User {
	String name;
	String role;

	public User(String name, String role) {
		this.name = name;
		this.role = role;
	}
}

class Import_4IF {

	public static void run_Import_4IF() {
		System.err.println("\t\t4. Verificando níveis de acesso em uma lista \n");
		
		List<User> users = new ArrayList<>();
		users.add(new User("Alice", "Admin"));
		users.add(new User("Bob", "Editor"));
		users.add(new User("Charlie", "Viewer"));
		users.add(new User("David", "Editor"));
		users.add(new User("Eve", "Admin"));

		// Verificando níveis de acesso
		for (User u : users) {
			if (u.role.equals("Admin")) {
				System.out.println(u.name + " tem acesso total ao sistema.");
			} else if (u.role.equals("Editor")) {
				System.out.println(u.name + " pode editar conteúdos.");
			} else if (u.role.equals("Viewer")) {
				System.out.println(u.name + " pode apenas visualizar conteúdos.");
			} else {
				System.out.println(u.name + " tem um papel desconhecido.");
			}
		}
	}
}

//Corpo da Classe

@interface TemperaturaIdeal {
	double valor() default 22.0;
}

class Cidade {
	String nome;
	double[][] temperaturas; // Matriz de temperaturas por dia e horário

	public Cidade(String nome, double[][] temperaturas) {
		this.nome = nome;
		this.temperaturas = temperaturas;
	}

	public double calcularMedia() {
		double soma = 0;
		int count = 0;
		for (double[] dia : temperaturas) {
			for (double temp : dia) {
				soma += temp;
				count++;
			}
		}
		return count > 0 ? soma / count : 0;
	}

	public String classificarClima() {
		double media = calcularMedia();
		if (media >= 30) {
			return "Muito quente";
		} else if (media >= 22) {
			return "Agradável";
		} else if (media >= 15) {
			return "Frio";
		} else {
			return "Muito frio";
		}
	}
}

class Import_5IF {
	public static void run_Import_5IF() {
		System.err.println("\t\t5.  Matriz de temperaturas por dia e horário em uma lista \n");
		

		List<Cidade> cidades = new ArrayList<>();
		cidades.add(new Cidade("São Paulo", new double[][] { { 22, 24, 23 }, { 20, 22, 21 } }));
		cidades.add(new Cidade("Curitiba", new double[][] { { 15, 14, 16 }, { 13, 12, 14 } }));
		cidades.add(new Cidade("Rio de Janeiro", new double[][] { { 30, 32, 31 }, { 29, 30, 33 } }));

		for (Cidade cidade : cidades) {
			System.out.println("Cidade: " + cidade.nome);
			System.out.println("Média de temperatura: " + cidade.calcularMedia());
			System.out.println("Clima: " + cidade.classificarClima());
			System.out.println("-----------");
		}

	}
}

//Corpo da Classe
@interface Aprovacao {
	double notaMinima() default 7.0;
}

class Aluno {
	String nome;
	double[][] notas; // Matriz de notas por matéria e avaliação

	public Aluno(String nome, double[][] notas) {
		this.nome = nome;
		this.notas = notas;
	}

	public double calcularMedia() {
		double soma = 0;
		int count = 0;
		for (double[] materia : notas) {
			for (double nota : materia) {
				soma += nota;
				count++;
			}
		}
		return count > 0 ? soma / count : 0;
	}

	public String getClassificacao() {
		double media = calcularMedia();
		if (media >= 9) {
			return "Excelente";
		} else if (media >= 7) {
			return "Bom";
		} else if (media >= 5) {
			return "Regular";
		} else {
			return "Reprovado";
		}
	}
}

class Import_6IF {

	@Aprovacao(notaMinima = 7.0)

	public static void run_Import_6IF() {
		System.err.println("\t\t5.  Matriz de notas por matéria e avaliação em uma lista \n");
		
		List<Aluno> alunos = new ArrayList<>();
		alunos.add(new Aluno("Carlos", new double[][] { { 8, 7, 9 }, { 6, 8, 7 } }));
		alunos.add(new Aluno("Mariana", new double[][] { { 5, 6, 5 }, { 4, 5, 6 } }));
		alunos.add(new Aluno("Ana", new double[][] { { 10, 9, 10 }, { 9, 10, 10 } }));

		for (Aluno aluno : alunos) {
			System.out.println("Aluno: " + aluno.nome);
			System.out.println("Média: " + aluno.calcularMedia());
			System.out.println("Classificação: " + aluno.getClassificacao());
			System.out.println("-----------");
		}

	}
}
