package UTIL_12_LinkedList;

import java.util.Base64;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import util.Linhas;

public class Simples {
	public static void Simples_Run() throws Exception {

		Linhas linhas = new Linhas();
		String collec = "\n\t Nome\n";
		System.err.println(collec);

		linhas.run_Caracteres();
		Import_1S.run_Import_1S();

		linhas.run_Caracteres();
		Import_2S.run_Import_2S();

		linhas.run_Caracteres();
		Import_3S.run_Import_3S();
	}
}

//Corpo da Classe _______________________________
// ENUMS com métodos
enum Marca {
    INTEL, AMD, APPLE, QUALCOMM, SAMSUNG;

    public String getDescricao() {
        return switch (this) {
            case INTEL -> "Processador Intel";
            case AMD -> "Processador AMD";
            case APPLE -> "Processador Apple Silicon";
            case QUALCOMM -> "Processador Snapdragon";
            case SAMSUNG -> "Processador Exynos";
        };
    }
}

enum Frequencia {
    BAIXA(1.8), MEDIA(2.5), ALTA(3.6), EXTREMA(5.0), TURBO(6.2);

    private final double ghz;
    Frequencia(double ghz) {
        this.ghz = ghz;
    }
    public double getGHz() {
        return ghz;
    }
}

enum Nucleos {
    DUAL(2), QUAD(4), HEXA(6), OCTA(8), DECA(10);

    private final int quantidade;
    Nucleos(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getQuantidade() {
        return quantidade;
    }
}

enum Cache {
    L1(2), L2(4), L3(8), L4(12), L5(16);

    private final int mb;
    Cache(int mb) {
        this.mb = mb;
    }

    public int getMB() {
        return mb;
    }
}

enum Arquitetura {
    X86("x86"), X64("x64"), ARM("ARM"), ARM64("ARM64"), RISC("RISC");

    private final String tipo;
    Arquitetura(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}

// Classe abstrata
abstract class Processador {
    protected Marca marca;
    protected Frequencia frequencia;
    protected Nucleos nucleos;
    protected Cache cache;
    protected Arquitetura arquitetura;

    public Processador(Marca marca, Frequencia frequencia, Nucleos nucleos, Cache cache, Arquitetura arquitetura) {
        this.marca = marca;
        this.frequencia = frequencia;
        this.nucleos = nucleos;
        this.cache = cache;
        this.arquitetura = arquitetura;
    }

    public abstract void exibirDetalhes();
}

// Classe concreta que estende Processador
class CPU extends Processador {
    public CPU(Marca marca, Frequencia frequencia, Nucleos nucleos, Cache cache, Arquitetura arquitetura) {
        super(marca, frequencia, nucleos, cache, arquitetura);
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("=== CPU ===");
        System.out.println("Marca: " + marca.getDescricao());
        System.out.println("Frequência: " + frequencia.getGHz() + " GHz");
        System.out.println("Núcleos: " + nucleos.getQuantidade());
        System.out.println("Cache: " + cache.getMB() + " MB");
        System.out.println("Arquitetura: " + arquitetura.getTipo());
    }
}

// Classe de Gerenciamento com LinkedList e sincronização
class GerenciadorProcessadores {
    private final LinkedList<Processador> lista = new LinkedList<>();

    public synchronized void adicionar(Processador p) {
        lista.add(p);
        System.out.println("Processador adicionado com sucesso!");
    }

    public synchronized void listar() {
        if (lista.isEmpty()) {
            System.out.println("Nenhum processador cadastrado.");
        } else {
            Iterator<Processador> it = lista.iterator();
            while (it.hasNext()) {
                it.next().exibirDetalhes();
                System.out.println("-------------------------");
            }
        }
    }
}

// Classe principal
class Main {
    public static void main(String[] args) {
        GerenciadorProcessadores gerenciador = new GerenciadorProcessadores();

        // Criando processadores
        Processador p1 = new CPU(Marca.INTEL, Frequencia.ALTA, Nucleos.OCTA, Cache.L3, Arquitetura.X64);
        Processador p2 = new CPU(Marca.APPLE, Frequencia.EXTREMA, Nucleos.DECA, Cache.L5, Arquitetura.ARM64);

        // Adicionando à lista com sincronização
        gerenciador.adicionar(p1);
        gerenciador.adicionar(p2);

        // Listando processadores
        gerenciador.listar();
    }
}

class Import_1S {
	public static void run_Import_1S() {
		System.err.println("\t\t1. \n");
		Main.main(null);
	}
}

//Corpo da Classe _______________________________

// Enums com métodos
enum PerfilTipoj {
	ADMIN, USUARIO, CONVIDADO;

	public String descricao() {
		return "Perfil: " + this.name();
	}
}

enum AcessoNivel {
	ALTO, MEDIO, BAIXO;

	public boolean isPermitido() {
		return this != BAIXO;
	}

	public String descricao() {
		return switch (this) {
		case ALTO -> "Acesso total ao sistema";
		case MEDIO -> "Acesso limitado a algumas funções";
		case BAIXO -> "Acesso restrito";
		};
	}
}

enum StatusConta {
	ATIVO, INATIVO, BLOQUEADO;

	public boolean podeLogar() {
		return this == ATIVO;
	}
}

enum CriptoTipo {
	  BASE64, SIMPLES, CRIPTO_FORTE;

    private static final String ALGORITMO = "AES";
    private static final String CHAVE_SECRETA = "1234567890abcdef"; // 16 caracteres (128 bits)

    public String criptografar(String input) throws Exception {
        switch (this) {
            case BASE64:
                return Base64.getEncoder().encodeToString(input.getBytes());
            case SIMPLES:
                return new StringBuilder(input).reverse().toString();
            case CRIPTO_FORTE:
                SecretKeySpec key = new SecretKeySpec(CHAVE_SECRETA.getBytes(), ALGORITMO);
                Cipher cipher = Cipher.getInstance(ALGORITMO);
                cipher.init(Cipher.ENCRYPT_MODE, key);
                byte[] encrypted = cipher.doFinal(input.getBytes());
                return Base64.getEncoder().encodeToString(encrypted);
            default:
                return input;
        }
    }

    public String descriptografar(String input) throws Exception {
        switch (this) {
            case BASE64:
                return new String(Base64.getDecoder().decode(input));
            case SIMPLES:
                return new StringBuilder(input).reverse().toString();
            case CRIPTO_FORTE:
                SecretKeySpec key = new SecretKeySpec(CHAVE_SECRETA.getBytes(), ALGORITMO);
                Cipher cipher = Cipher.getInstance(ALGORITMO);
                cipher.init(Cipher.DECRYPT_MODE, key);
                byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(input));
                return new String(decrypted);
            default:
                return input;
        }
    }
}

enum MetodoLogin {
	EMAIL, CPF, USUARIO;

	public String metodo() {
		return "Login via: " + this.name();
	}
}

// Classe abstrata
abstract class PerfilBase {
	protected String nome;
	protected String senhaCriptografada;

	public PerfilBase(String nome) {
		this.nome = nome;
	}

	public abstract void criptografarSenha(String senha, CriptoTipo tipo) throws Exception;

	public abstract String descriptografarSenha(CriptoTipo tipo) throws Exception;
}

// Classe concreta com sincronização
class PerfilUsuario extends PerfilBase {
	private AcessoNivel acesso;
	private StatusConta status;

	public PerfilUsuario(String nome, PerfilTipoj tipo, AcessoNivel acesso, StatusConta status) {
		super(nome);
		this.acesso = acesso;
		this.status = status;
	}

	@Override
	public synchronized void criptografarSenha(String senha, CriptoTipo tipo) throws Exception {
		this.senhaCriptografada = tipo.criptografar(senha);
	}

	@Override
	public synchronized String descriptografarSenha(CriptoTipo tipo) throws Exception {
		return tipo.descriptografar(senhaCriptografada);
	}

	public synchronized void exibirPerfil(CriptoTipo tipo) throws Exception {
		System.out.println("Nome: " + nome);
		System.out.println("Senha criptografada: " + senhaCriptografada);
		System.out.println("Senha original: " + descriptografarSenha(tipo));
		System.out.println("Tipo: " + tipo.name());
		System.out.println("Acesso: " + acesso.name());
		System.out.println("Status: " + status.name());
	}

}

// Main com LinkedList
class PerfilCriptografiaHard {
	public static void main(String[] args) throws Exception {
		LinkedList<PerfilUsuario> perfis = new LinkedList<>();

		PerfilUsuario user1 = new PerfilUsuario("Alice", PerfilTipoj.ADMIN, AcessoNivel.ALTO, StatusConta.ATIVO);
		PerfilUsuario user2 = new PerfilUsuario("Bob", PerfilTipoj.USUARIO, AcessoNivel.MEDIO, StatusConta.INATIVO);
		PerfilUsuario user3 = new PerfilUsuario("Zé Criptografado", PerfilTipoj.ADMIN, AcessoNivel.ALTO, StatusConta.ATIVO);
		user1.criptografarSenha("123456", CriptoTipo.BASE64);
		user2.criptografarSenha("123456", CriptoTipo.SIMPLES);		
		user3.criptografarSenha("123456", CriptoTipo.CRIPTO_FORTE);

		synchronized (perfis) {
			perfis.add(user1);
			perfis.add(user2);
			perfis.add(user3);
		}

		for (PerfilUsuario p : perfis) {
			System.out.println("\n--- Perfil ---");
			p.exibirPerfil(CriptoTipo.BASE64);
		}
	}
}

class Import_2S {
	public static void run_Import_2S() throws Exception {
		System.err.println("\t\t2. \n");
		PerfilCriptografiaHard.main(null);
	}
}

//Corpo da Classe _______________________________

// Enum 1: Níveis de Perfil
enum PerfilNivel {
	ADMIN, USER, GUEST, MODERATOR, VIP;

	public String descricao() {
		return switch (this) {
		case ADMIN -> "Administrador completo";
		case USER -> "Usuário comum";
		case GUEST -> "Visitante";
		case MODERATOR -> "Moderador do sistema";
		case VIP -> "Usuário com privilégios especiais";
		};
	}
}

// Enum 2: Status de Conta
enum StatusContaj {
	ATIVO, INATIVO, BLOQUEADO, PENDENTE, EXPIRADO;

	public boolean podeLogar() {
		return this == ATIVO || this == PENDENTE;
	}
}

// Enum 3: Tipo de Criptografia
enum TipoCriptografia {
	BASE64, REVERSA, DUPLO, SIMPLES, NENHUMA;

	public String criptografar(String entrada) {
		return switch (this) {
		case BASE64 -> Base64.getEncoder().encodeToString(entrada.getBytes());
		case REVERSA -> new StringBuilder(entrada).reverse().toString();
		case DUPLO -> Base64.getEncoder().encodeToString(new StringBuilder(entrada).reverse().toString().getBytes());
		case SIMPLES -> entrada.replaceAll(".", "*");
		case NENHUMA -> entrada;
		};
	}
}

// Enum 4: Tipo de Acesso
enum TipoAcessos {
	COMPLETO, LIMITADO, VISUALIZACAO, PERSONALIZADO, BLOQUEADO;

	public boolean possuiPermissao() {
		return this != BLOQUEADO;
	}
}

// Enum 5: Origem de Cadastro
enum OrigemCadastro {
	WEB, MOBILE, API, ADMIN, IMPORTACAO;

	public String origemDescricao() {
		return "Cadastro realizado via " + this.name();
	}
}

// Classe abstrata com sincronização
abstract class Perfils {
	protected String nome;
	protected String senhaCriptografada;
	protected PerfilNivel nivel;
	protected StatusContaj status;
	protected TipoAcessos acesso;

	public Perfils(String nome, String senha, PerfilNivel nivel, StatusContaj status, TipoAcessos acesso,
			TipoCriptografia tipoCriptografia) {
		this.nome = nome;
		this.senhaCriptografada = tipoCriptografia.criptografar(senha);
		this.nivel = nivel;
		this.status = status;
		this.acesso = acesso;
	}

	public abstract void exibirDetalhes();

	public synchronized void atualizarStatus(StatusContaj novoStatus) {
		this.status = novoStatus;
	}

	public synchronized boolean podeAcessar() {
		return status.podeLogar() && acesso.possuiPermissao();
	}
}

// Implementação concreta do perfil
class PerfilUsuarioj extends Perfils {
	OrigemCadastro origem;

	public PerfilUsuarioj(String nome, String senha, PerfilNivel nivel, StatusContaj status, TipoAcessos acesso,
			OrigemCadastro origem, TipoCriptografia criptografia) {
		super(nome, senha, nivel, status, acesso, criptografia);
		this.origem = origem;
	}

	@Override
	public void exibirDetalhes() {
		System.out.println("Nome: " + nome);
		System.out.println("Senha Criptografada: " + senhaCriptografada);
		System.out.println("Nível: " + nivel + " - " + nivel.descricao());
		System.out.println("Status: " + status);
		System.out.println("Acesso: " + acesso + " | Permissão: " + acesso.possuiPermissao());
		System.out.println("Origem: " + origem.origemDescricao());
	}
}

// Classe principal
class SistemaPerfil {
	private static final LinkedList<PerfilUsuarioj> listaPerfis = new LinkedList<>();

	public static synchronized void adicionarPerfil(PerfilUsuarioj perfil) {
		listaPerfis.add(perfil);
		System.out.println("Perfil adicionado com sucesso!");
	}

	public static synchronized void listarPerfis() {
		Iterator<PerfilUsuarioj> it = listaPerfis.iterator();
		while (it.hasNext()) {
			PerfilUsuarioj p = it.next();
			p.exibirDetalhes();
			System.out.println("Pode acessar o sistema? " + p.podeAcessar());
			System.out.println("-----------------------");
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Digite o nome do usuário: ");
		String nome = scanner.nextLine();

		System.out.print("Digite a senha: ");
		String senha = scanner.nextLine();

		PerfilUsuarioj novo = new PerfilUsuarioj(nome, senha, PerfilNivel.ADMIN, StatusContaj.ATIVO,
				TipoAcessos.COMPLETO, OrigemCadastro.WEB, TipoCriptografia.DUPLO);

		adicionarPerfil(novo);
		listarPerfis();
	}
}

class Import_3S {
	public static void run_Import_3S() {
		System.err.println("\t\t3. \n");
		SistemaPerfil.main(null);
	}
}
