package UTIL_12_LinkedList;

import java.util.Base64;
import java.util.LinkedList;
import java.util.Scanner;

import util.Linhas;
import util.VoutarMenu;

public class If_Else_IF {
	public static void If_Else_IF_Run() {

		Linhas linhas = new Linhas();
		String collec = "\n\t Nemo \n";
		System.err.println(collec);

		linhas.run_Caracteres();
		Import_1IF.run_Import_1IF();

		linhas.run_Caracteres();
		Import_2IF.run_Import_2IF();

		linhas.run_Caracteres();
		Import_3IF.run_Import_3IF();
	}
}

//Corpo da Classe _______________________________
// ENUMS COM MÉTODOS

enum PerfilTipo {
	ADMIN, USUARIO, MODERADOR, GUEST, DEV;

	public String descricao() {
		return switch (this) {
		case ADMIN -> "Administrador com acesso total";
		case USUARIO -> "Usuário comum";
		case MODERADOR -> "Moderador de conteúdo";
		case GUEST -> "Visitante";
		case DEV -> "Desenvolvedor";
		};
	}
}

enum Criptografia {
	BASE64 {
		public String criptografar(String texto) {
			return Base64.getEncoder().encodeToString(texto.getBytes());
		}

		public String descriptografar(String textoCriptografado) {
			return new String(Base64.getDecoder().decode(textoCriptografado));
		}
	},
	REVERSO {
		public String criptografar(String texto) {
			return new StringBuilder(texto).reverse().toString();
		}

		public String descriptografar(String textoCriptografado) {
			return new StringBuilder(textoCriptografado).reverse().toString();
		}
	};

	public abstract String criptografar(String texto);

	public abstract String descriptografar(String textoCriptografado);
}

enum Status {
	ATIVO, INATIVO, BLOQUEADO, AGUARDANDO, EXCLUIDO;

	public boolean podeAcessar() {
		return this == ATIVO;
	}
}

enum Acesso {
	TOTAL, LIMITADO, MEDIO, RESTRITO, NENHUM;

	public int nivel() {
		return switch (this) {
		case TOTAL -> 5;
		case MEDIO -> 3;
		case LIMITADO -> 2;
		case RESTRITO -> 1;
		case NENHUM -> 0;
		};
	}
}

enum Permissao {
	ESCREVER, LER, EXECUTAR, DELETAR, COMPARTILHAR;

	public boolean permitidoPara(PerfilTipo tipo) {
		return switch (tipo) {
		case ADMIN, DEV -> true;
		case USUARIO -> this != DELETAR && this != COMPARTILHAR;
		case GUEST -> this == LER;
		default -> false;
		};
	}
}

// CLASSE ABSTRATA

abstract class AbstractPerfilManager {
	protected LinkedList<Perfil> perfis = new LinkedList<>();

	public abstract void adicionarPerfil(Perfil p);

	public abstract void listarPerfis();

	public abstract void buscarPorNome(String nome);
}

// CLASSE PERFIL

class Perfil {
	String nome;
	String email;
	String senhaCriptografada;
	PerfilTipo tipo;
	Status status;

	public Perfil(String nome, String email, String senha, PerfilTipo tipo, Criptografia tipoCripto) {
		this.nome = nome;
		this.email = email;
		this.tipo = tipo;
		this.status = Status.ATIVO;
		this.senhaCriptografada = tipoCripto.criptografar(senha);
	}

	public String getSenhaDescriptografada(Criptografia tipoCripto) {
		return tipoCripto.descriptografar(senhaCriptografada);
	}

	@Override
	public String toString() {
		return "Perfil{" + "nome='" + nome + '\'' + ", email='" + email + '\'' + ", tipo=" + tipo + ", status=" + status
				+ "Criptografia="+Criptografia.BASE64.criptografar(senhaCriptografada) +'}';
	}
}

// GERENCIADOR COM MÉTODOS SINCRONIZADOS

class GerenciadorPerfil extends AbstractPerfilManager {

	@Override
	public synchronized void adicionarPerfil(Perfil p) {
		perfis.add(p);
		System.out.println("Perfil adicionado com sucesso: " + p.nome);
	}

	@Override
	public synchronized void listarPerfis() {
		System.out.println("=== Lista de Perfis ===");
		for (Perfil p : perfis) {
			System.out.println(p);
		}
	}

	@Override
	public synchronized void buscarPorNome(String nome) {
		boolean encontrado = false;
		for (Perfil p : perfis) {
			if (p.nome.equalsIgnoreCase(nome)) {
				System.out.println("Encontrado: " + p);
				encontrado = true;
			}
		}
		if (!encontrado) {
			System.out.println("Nenhum perfil com esse nome.");
		}
	}
}

// MAIN COM IF, ELSE IF, ELSE IF

class PerfilCriptografadoApp {
	public static void main(String[] args) {
		GerenciadorPerfil gp = new GerenciadorPerfil();
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("\n[1] Adicionar Perfil\n[2] Listar\n[3] Buscar\n[4] Sair");
			int opcao = scanner.nextInt();
			scanner.nextLine();

			if (opcao == 1) {
				System.out.print("Nome: ");
				String nome = scanner.nextLine();
				System.out.print("Email: ");
				String email = scanner.nextLine();
				System.out.print("Senha: ");
				String senha = scanner.nextLine();

				System.out.print("Tipo (ADMIN, USUARIO, MODERADOR, GUEST, DEV): ");
				PerfilTipo tipo = PerfilTipo.valueOf(scanner.nextLine().toUpperCase());

				System.out.print("Criptografia (BASE64 ou REVERSO): ");
				Criptografia cripto = Criptografia.valueOf(scanner.nextLine().toUpperCase());

				Perfil p = new Perfil(nome, email, senha, tipo, cripto);
				gp.adicionarPerfil(p);

			} else if (opcao == 2) {
				gp.listarPerfis();

			} else if (opcao == 3) {
				System.out.print("Nome para buscar: ");
				String nome = scanner.nextLine();
				gp.buscarPorNome(nome);

			} else {
				System.out.println("Encerrando.");
				break;
			}
		}
		// Voutar para o MEUNU
		VoutarMenu voutarMenu = new VoutarMenu();
		voutarMenu.run_CaracteresMenu_Red();
	}
}

class Import_1IF {
	public static void run_Import_1IF() {
		System.err.println("\t\t1. \n");
		PerfilCriptografadoApp.main(null);
	}
}

//Corpo da Classe _______________________________

// 5 Enums com métodos
enum Perfilp {
	ADMIN("Acesso Total"), USUARIO("Acesso Padrão"), SUPORTE("Acesso Suporte"), GERENTE("Acesso Gerencial"),
	CONVIDADO("Acesso Limitado");

	private String descricao;

	Perfilp(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void exibirPerfil() {
		System.out.println("Perfil: " + this.name() + " - " + descricao);
	}
}

enum StatusLogin {
	SUCESSO, FALHA, BLOQUEADO, EXPIRADO, PENDENTE;

	public void mensagem() {
		switch (this) {
		case SUCESSO -> System.out.println("Login efetuado com sucesso.");
		case FALHA -> System.out.println("Falha ao efetuar login.");
		case BLOQUEADO -> System.out.println("Conta bloqueada.");
		case EXPIRADO -> System.out.println("Sessão expirada.");
		case PENDENTE -> System.out.println("Aguardando confirmação.");
		}
	}
}

enum Permissaop {
	LEITURA, ESCRITA, EXECUCAO, TOTAL, NENHUMA;

	public boolean temPermissaoTotal() {
		return this == TOTAL;
	}
}

enum TipoAcesso {
	INTERNO, EXTERNO, MOBILE, DESKTOP, API;

	public void tipoConexao() {
		System.out.println("Acesso por: " + this.name());
	}
}

enum ResultadoLogin {
	OK, ERRO, TIMEOUT, BLOQUEIO, DESCONHECIDO;

	public boolean isSucesso() {
		return this == OK;
	}
}

// Classe abstrata
abstract class UsuarioBase {
	protected String nome;
	protected String senha;
	protected Perfilp perfil;

	public UsuarioBase(String nome, String senha, Perfilp perfil) {
		this.nome = nome;
		this.senha = senha;
		this.perfil = perfil;
	}

	public abstract boolean autenticar(String nome, String senha);

	public void exibirDados() {
		System.out.println("Usuário: " + nome + " | Perfil: " + perfil.name());
	}

	public Perfilp getPerfil() {
		return perfil;
	}
}

// Classe concreta herdando de classe abstrata
class Usuario extends UsuarioBase {
	public Usuario(String nome, String senha, Perfilp perfil) {
		super(nome, senha, perfil);
	}

	@Override
	public boolean autenticar(String nome, String senha) {
		return this.nome.equals(nome) && this.senha.equals(senha);
	}
}

// Classe principal
class SistemaLoginHard {
	private static final LinkedList<Usuario> usuarios = new LinkedList<>();

	// Método sincronizado
	public synchronized static void registrarUsuario(Usuario usuario) {
		usuarios.add(usuario);
		System.out.println("Usuário registrado: " + usuario.nome);
	}

	// Método sincronizado
	public synchronized static StatusLogin efetuarLogin(String nome, String senha) {
		for (Usuario usuario : usuarios) {
			if (usuario.autenticar(nome, senha)) {
				usuario.exibirDados();
				usuario.perfil.exibirPerfil();
				return StatusLogin.SUCESSO;
			}
		}
		return StatusLogin.FALHA;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// Registro inicial
		registrarUsuario(new Usuario("admin", "123", Perfilp.ADMIN));
		registrarUsuario(new Usuario("joao", "123", Perfilp.USUARIO));
		registrarUsuario(new Usuario("lucas", "123", Perfilp.SUPORTE));

		System.out.print("Digite seu login: ");
		String login = scanner.nextLine();

		System.out.print("Digite sua senha: ");
		String senha = scanner.nextLine();

		StatusLogin status = efetuarLogin(login, senha);

		if (status == StatusLogin.SUCESSO) {
			status.mensagem();
		} else if (status == StatusLogin.FALHA) {
			status.mensagem();
		} else if (status == StatusLogin.BLOQUEADO) {
			status.mensagem();
		} else {
			System.out.println("Outro status: " + status);
		}

		// Demonstração de enum com método
		Permissaop permissao = Permissaop.TOTAL;
		System.out.println("Tem permissão total? " + permissao.temPermissaoTotal());

		TipoAcesso acesso = TipoAcesso.DESKTOP;
		acesso.tipoConexao();

		ResultadoLogin resultado = ResultadoLogin.OK;
		System.out.println("Login foi sucesso? " + resultado.isSucesso());
	}
}

class Import_2IF {
	public static void run_Import_2IF() {
		System.err.println("\t\t2.\n");
		SistemaLoginHard.main(null);
	}
}

//Corpo da Classe _______________________________

// Enums com métodos
enum CorPrimaria {
	VERMELHO, AZUL, AMARELO;

	public String descricao() {
		return "Cor primária básica: " + this.name();
	}
}

enum TipoTinta {
	LATEX("Interior"), ACRILICA("Exterior"), EPOXI("Indústrias"), SPRAY("Artesanato"), OLEO("Artística");

	private String uso;

	TipoTinta(String uso) {
		this.uso = uso;
	}

	public String getUso() {
		return uso;
	}
}

enum Acabamento {
	FOSCO, SEMI_BRILHO, BRILHANTE, TEXTURIZADO, METALICO;

	public String efeitoVisual() {
		return "Efeito do acabamento: " + this.name();
	}
}

enum Intensidade {
	FORTE, MEDIA, FRACA, NEON, PASTEL;

	public String nivel() {
		return "Intensidade: " + this.name();
	}
}

enum Ambiente {
	INTERNO, EXTERNO, INDUSTRIAL, ARTISTICO, DECORATIVO;

	public String local() {
		return "Usar no ambiente: " + this.name();
	}
}

// Classe abstrata
abstract class Tinta {
	String nome;
	TipoTinta tipo;
	Acabamento acabamento;

	public Tinta(String nome, TipoTinta tipo, Acabamento acabamento) {
		this.nome = nome;
		this.tipo = tipo;
		this.acabamento = acabamento;
	}

	public abstract void exibirDetalhes();
}

// Classe concreta
class PedidoTinta extends Tinta {
	CorPrimaria corPrimaria;
	Intensidade intensidade;
	Ambiente ambiente;

	public PedidoTinta(String nome, TipoTinta tipo, Acabamento acabamento, CorPrimaria corPrimaria,
			Intensidade intensidade, Ambiente ambiente) {
		super(nome, tipo, acabamento);
		this.corPrimaria = corPrimaria;
		this.intensidade = intensidade;
		this.ambiente = ambiente;
	}

	@Override
	public void exibirDetalhes() {
		System.out.println("\n=== Pedido de Tinta ===");
		System.out.println("Nome: " + nome);
		System.out.println(tipo.getUso());
		System.out.println(acabamento.efeitoVisual());
		System.out.println(corPrimaria.descricao());
		System.out.println(intensidade.nivel());
		System.out.println(ambiente.local());
	}
}

// Classe principal com sincronização e lógica
class EstoqueTinta {

	static int[][] estoque = new int[5][5]; // matriz de estoque
	static LinkedList<PedidoTinta> filaPedidos = new LinkedList<>();

	public static synchronized void adicionarPedido(PedidoTinta pedido) {
		filaPedidos.add(pedido);
		System.out.println("Pedido adicionado à fila.");
	}

	public static synchronized void processarPedidos() {
		while (!filaPedidos.isEmpty()) {
			PedidoTinta pedido = filaPedidos.poll();
			pedido.exibirDetalhes();

			int linha = pedido.corPrimaria.ordinal();
			int coluna = pedido.acabamento.ordinal();

			if (estoque[linha][coluna] > 0) {
				estoque[linha][coluna]--;
				System.out.println("Pedido atendido. Estoque atualizado.");
			} else {
				System.out.println("Estoque insuficiente para este pedido!");
			}
		}
	}

	public static void popularEstoque() {
		for (int i = 0; i < estoque.length; i++) {
			for (int j = 0; j < estoque[i].length; j++) {
				estoque[i][j] = 10; // cada posição começa com 10 unidades
			}
		}
	}

	public static void visualizarEstoque() {
		System.out.println("\n📦 Estoque Atual:");
		for (int i = 0; i < estoque.length; i++) {
			for (int j = 0; j < estoque[i].length; j++) {
				System.out.print(estoque[i][j] + "\t");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		popularEstoque();
		Scanner sc = new Scanner(System.in);

		System.out.println("### SISTEMA DE GERENCIAMENTO DE TINTAS ###");

		for (int i = 0; i < 3; i++) {
			System.out.println("\nCriar novo pedido [" + (i + 1) + "]");

			System.out.print("Nome da tinta: ");
			String nome = sc.nextLine();

			TipoTinta tipo = TipoTinta.values()[i % TipoTinta.values().length];
			Acabamento acabamento = Acabamento.values()[i % Acabamento.values().length];
			CorPrimaria cor = CorPrimaria.values()[i % CorPrimaria.values().length];
			Intensidade intensidade = Intensidade.values()[i % Intensidade.values().length];
			Ambiente ambiente = Ambiente.values()[i % Ambiente.values().length];

			PedidoTinta pedido = new PedidoTinta(nome, tipo, acabamento, cor, intensidade, ambiente);
			adicionarPedido(pedido);
		}

		visualizarEstoque();
		processarPedidos();
		visualizarEstoque();

		// switch case com matriz
		System.out.println("\nVerificar recomendação com switch:");
		int corIndex = 1;
		int acabamentoIndex = 2;

		switch (estoque[corIndex][acabamentoIndex]) {
		case 0:
			System.out.println("⚠ Estoque zerado! Favor repor.");
			break;
		case 1, 2, 3:
			System.out.println("⚠ Estoque baixo!");
			break;
		default:
			System.out.println("✔ Estoque estável.");
		}
		// Voutar para o MEUNU
		VoutarMenu voutarMenu = new VoutarMenu();
		voutarMenu.run_CaracteresMenu_Red();
	}
}

class Import_3IF {
	public static void run_Import_3IF() {
		System.err.println("\t\t3. \n");
		EstoqueTinta.main(null);
	}
}
