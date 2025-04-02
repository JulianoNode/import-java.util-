package UTIL_11_ArrayList;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import util.Linhas;

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

// Enum para Tipos de ID de Perfil
enum TipoID {
	ADMIN {
		@Override
		public String getPrefixo() {
			return"ADM-";
		}
	},
	MODERADOR {
		@Override
		public String getPrefixo() {
			return"MOD-";
		}
	},
	USUARIO {
		@Override
		public String getPrefixo() {
			return"USR-";
		}
	};

	public abstract String getPrefixo();
}

// Enum para Status do Perfil
enum StatusPerfil {
	ATIVO, INATIVO, SUSPENSO;
}

// Enum para Permissões do Perfil
enum Permissao {
	LEITURA, ESCRITA, EXECUCAO;
}

// Classe Abstrata de Perfil
abstract class Perfil {
	protected String id;
	protected String nome;
	protected TipoID tipo;
	protected StatusPerfil status;
	protected List<Permissao> permissoes;

	public Perfil(String nome, TipoID tipo) {
		this.id = gerarID(tipo);
		this.nome = nome;
		this.tipo = tipo;
		this.status = StatusPerfil.ATIVO;
		this.permissoes = new ArrayList<>();
	}

	private String gerarID(TipoID tipo) {
		return tipo.getPrefixo() + UUID.randomUUID().toString().substring(0, 8);
	}

	public abstract void adicionarPermissao(Permissao permissao);

	public abstract void removerPermissao(Permissao permissao);

	public void alterarStatus(StatusPerfil novoStatus) {
		this.status = novoStatus;
	}

	@Override
	public String toString() {
		return "ID: " + id + ", Nome: " + nome + ", Tipo: " + tipo + ", Status: " + status + ", Permissões: "
				+ permissoes;
	}
}

// Classe AdminPerfil
class AdminPerfil extends Perfil {
	public AdminPerfil(String nome) {
		super(nome, TipoID.ADMIN);
	}

	@Override
	public void adicionarPermissao(Permissao permissao) {
		if (!permissoes.contains(permissao)) {
			permissoes.add(permissao);
		}
	}

	@Override
	public void removerPermissao(Permissao permissao) {
		permissoes.remove(permissao);
	}
}

// Classe ModeradorPerfil
class ModeradorPerfil extends Perfil {
	public ModeradorPerfil(String nome) {
		super(nome, TipoID.MODERADOR);
	}

	@Override
	public void adicionarPermissao(Permissao permissao) {
		if (permissao != Permissao.EXECUCAO) {
			permissoes.add(permissao);
		}
	}

	@Override
	public void removerPermissao(Permissao permissao) {
		permissoes.remove(permissao);
	}
}

// Classe UsuarioPerfil
class UsuarioPerfil extends Perfil {
	public UsuarioPerfil(String nome) {
		super(nome, TipoID.USUARIO);
	}

	@Override
	public void adicionarPermissao(Permissao permissao) {
		if (permissao == Permissao.LEITURA) {
			permissoes.add(permissao);
		}
	}

	@Override
	public void removerPermissao(Permissao permissao) {
		permissoes.remove(permissao);
	}
}

// Teste da implementação
class MainPerfil {
	public static void mainPP() {
		List<Perfil> perfis = new ArrayList<>();

		Perfil admin = new AdminPerfil("Alice");
		admin.adicionarPermissao(Permissao.ESCRITA);
		admin.adicionarPermissao(Permissao.EXECUCAO);

		Perfil moderador = new ModeradorPerfil("Bob");
		moderador.adicionarPermissao(Permissao.LEITURA);
		moderador.adicionarPermissao(Permissao.ESCRITA);

		Perfil usuario = new UsuarioPerfil("Charlie");
		usuario.adicionarPermissao(Permissao.LEITURA);
		

		perfis.add(admin);
		perfis.add(moderador);
		perfis.add(usuario);
		
		for (Perfil p : perfis) {
			System.out.println(p);
		}
	}
}

class Import_1IF {
	public static void run_Import_1IF() {
		System.err.println("\t\t1. \n");
		MainPerfil.mainPP();
	}
}

//Corpo da Classe _______________________________

//Enum para os tipos de veículos
enum TipoVeiculo {
	CARRO(1.1), MOTO(1.05), CAMINHAO(1.2);

	private final double fatorImposto;

	TipoVeiculo(double fatorImposto) {
		this.fatorImposto = fatorImposto;
	}

	public double getFatorImposto() {
		return fatorImposto;
	}
}

//Enum para os estados e taxas de imposto
enum Estado1 {
	SP(0.04), RJ(0.035), MG(0.045);

	private final double taxa;

	Estado1(double taxa) {
		this.taxa = taxa;
	}

	public double getTaxa() {
		return taxa;
	}
}

//Enum para os tipos de imposto
enum TipoImposto {
	IPVA(0.03), SEGURO_OBRIGATORIO(0.01), LICENCIAMENTO(0.005);

	private final double percentual;

	TipoImposto(double percentual) {
		this.percentual = percentual;
	}

	public double getPercentual() {
		return percentual;
	}
}

//Classe abstrata Veiculo
abstract class Veiculo {
	protected String modelo;
	protected double valorBase;
	protected Estado1 estado;
	protected TipoVeiculo tipo;

	public Veiculo(String modelo, double valorBase, Estado1 estado, TipoVeiculo tipo) {
		this.modelo = modelo;
		this.valorBase = valorBase;
		this.estado = estado;
		this.tipo = tipo;
	}

	public abstract double calcularImposto();

	public abstract void exibirDetalhes();
}

//Classe abstrata para cálculo de seguro
abstract class SeguroVeicular {
	protected double calcularSeguro(double valorBase) {
		return valorBase * 0.02;
	}
}

//Classe abstrata para cálculo de licenciamento
abstract class Licenciamento {
	protected double calcularLicenciamento(double valorBase) {
		return valorBase * TipoImposto.LICENCIAMENTO.getPercentual();
	}
}

//Classe concreta Carro
class Carro extends Veiculo {
	public Carro(String modelo, double valorBase, Estado1 estado) {
		super(modelo, valorBase, estado, TipoVeiculo.CARRO);
	}

	@Override
	public double calcularImposto() {
		return valorBase * estado.getTaxa() * tipo.getFatorImposto();
	}

	@Override
	public void exibirDetalhes() {
		System.out.println("Modelo: " + modelo + ", Imposto: R$ " + calcularImposto());
	}
}

//Classe principal para gerenciamento de veículos
class GerenciadorVeiculos {
	public static void mainGV() {
		ArrayList<Veiculo> veiculos = new ArrayList<>();

		veiculos.add(new Carro("Honda Civic", 60000, Estado1.SP));
		veiculos.add(new Carro("Toyota Corolla", 70000, Estado1.RJ));
		veiculos.add(new Carro("Ford Focus", 50000, Estado1.MG));

		for (Veiculo v : veiculos) {
			v.exibirDetalhes();
		}
	}
}

class Import_2IF {
	public static void run_Import_2IF() {
		System.err.println("\t\t2.\n");
		GerenciadorVeiculos.mainGV();
	}
}

//Corpo da Classe _______________________________

// Enum complexo para Estados com alíquotas de impostos
enum Estados {
	SP(0.18), RJ(0.16), MG(0.17), RS(0.15), PR(0.14);

	private final double imposto;

	Estados(double imposto) {
		this.imposto = imposto;
	}

	public double getImposto() {
		return imposto;
	}
}

// Enum complexo para tipos de documentos
enum TipoDocumentos {
	CPF, CNPJ;
}

// Enum complexo para categoria de empresa
enum CategoriaEmpresa {
	MEI, SIMPLES_NACIONAL, LUCRO_PRESUMIDO, LUCRO_REAL;
}

// Classe abstrata para Pessoa
abstract class Pessoas {
	protected String documento;
	protected Estados estado;

	public Pessoas(String documento, Estados estado) {
		this.documento = documento;
		this.estado = estado;
	}

	public abstract double calcularImposto(double valor);
}

// Classe abstrata para Pessoa Física
abstract class PessoaFisica extends Pessoas {
	public PessoaFisica(String documento, Estados estado) {
		super(documento, estado);
	}

	public abstract boolean validarCPF();
}

// Classe abstrata para Pessoa Jurídica
abstract class PessoaJuridica extends Pessoas {
	protected CategoriaEmpresa categoria;

	public PessoaJuridica(String documento, Estados estado, CategoriaEmpresa categoria) {
		super(documento, estado);
		this.categoria = categoria;
	}

	public abstract boolean validarCNPJ();
}

// Implementação concreta de Pessoa Física
class Cliente extends PessoaFisica {
	public Cliente(String documento, Estados estado) {
		super(documento, estado);
	}

	@Override
	public double calcularImposto(double valor) {
		return valor * estado.getImposto();
	}

	@Override
	public boolean validarCPF() {
		return documento.matches("\\d{11}");
	}
}

// Implementação concreta de Pessoa Jurídica
class Empresa extends PessoaJuridica {
	public Empresa(String documento, Estados estado, CategoriaEmpresa categoria) {
		super(documento, estado, categoria);
	}

	@Override
	public double calcularImposto(double valor) {
		double impostoBase = estado.getImposto();

		if (categoria == CategoriaEmpresa.MEI) {
			return valor * (impostoBase - 0.05);
		} else if (categoria == CategoriaEmpresa.SIMPLES_NACIONAL) {
			return valor * (impostoBase - 0.02);
		} else if (categoria == CategoriaEmpresa.LUCRO_PRESUMIDO) {
			return valor * impostoBase;
		} else if (categoria == CategoriaEmpresa.LUCRO_REAL) {
			return valor * (impostoBase + 0.03);
		} else {
			return valor * impostoBase;
		}
	}

	@Override
	public boolean validarCNPJ() {
		return documento.matches("\\d{14}");
	}
}

class SistemaFiscal {
	public static void main_SF() {
		List<Pessoas> contribuintes = new ArrayList<>();

		Cliente cliente1 = new Cliente("12345678901", Estados.SP);
		Empresa empresa1 = new Empresa("11222333444455", Estados.RJ, CategoriaEmpresa.SIMPLES_NACIONAL);

		contribuintes.add(cliente1);
		contribuintes.add(empresa1);

		double valor = 10000.0;

		for (Pessoas p : contribuintes) {
			System.out.println("Documento: " + p.documento);
			System.out.println("Estado: " + p.estado);
			System.out.println("Imposto devido: R$ " + p.calcularImposto(valor));
			System.out.println("-----------------------------------");
		}
	}
}

class Import_3IF {
	public static void run_Import_3IF() {
		System.err.println("\t\t3. \n");
		SistemaFiscal.main_SF();
	}
}
