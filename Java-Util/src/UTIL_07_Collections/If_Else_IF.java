package UTIL_07_Collections;

import util.Linhas;
import java.util.*;

public class If_Else_IF {
	public static void If_Else_IF_Run() {

		Linhas linhas = new Linhas();
		String collec = "\n\t Collections: Métodos utilitários para trabalhar com coleções (ex.: ordenação, busca, sincronização). \n";
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
class Import_1IF {
	public static void run_Import_1IF() {
		System.err.println("\t\t1. Perfil \n");

		CrudPerfil crudPerfil = new CrudPerfil();

		// Adicionando Perfis
		Perfil6 perfil1 = new PerfilUsuario("João Silva", "joao@email.com", Status.ATIVO, TipoPerfil6.USUARIO,
				NivelAcesso.MEDIO);
		Perfil6 perfil2 = new PerfilUsuario("Ana Souza", "ana@email.com", Status.PENDENTE, TipoPerfil6.ADMIN,
				NivelAcesso.ALTO);
		Perfil6 perfil3 = new PerfilUsuario("Carlos Pereira", "carlos@email.com", Status.INATIVO, TipoPerfil6.GUEST,
				NivelAcesso.BAIXO);

		crudPerfil.adicionarPerfil(perfil1);
		crudPerfil.adicionarPerfil(perfil2);
		crudPerfil.adicionarPerfil(perfil3);

		// Exibindo Perfis
		crudPerfil.exibirPerfis();

		// Atualizando Perfil
		Perfil6 perfilAtualizado = new PerfilUsuario("Carlos Pereira", "carlos@email.com", Status.ATIVO,
				TipoPerfil6.USUARIO, NivelAcesso.MEDIO);
		crudPerfil.atualizarPerfil("carlos@email.com", perfilAtualizado);

		// Exibindo Perfis após atualização
		crudPerfil.exibirPerfis();

		// Ordenação
		crudPerfil.ordenarPorNome();
		crudPerfil.exibirPerfis();

		// Sincronização
		Perfil6 perfil4 = new PerfilUsuario("Beatriz Lima", "beatriz@email.com", Status.ATIVO, TipoPerfil6.USUARIO,
				NivelAcesso.BAIXO);
		crudPerfil.adicionarPerfilSincronizado(perfil4);

		// Exibindo Perfis após adicionar com sincronização
		crudPerfil.exibirPerfis();
	}
}

// Enum para o Status do Perfil
enum Status {
	ATIVO, INATIVO, PENDENTE
}

// Enum para o Tipo de Perfil
enum TipoPerfil6 {
	ADMIN, USUARIO, GUEST
}

// Enum para o Nivel de Acesso
enum NivelAcesso {
	ALTO, MEDIO, BAIXO
}

// Classe abstrata para Perfil
abstract class Perfil6 {
	protected String nome;
	protected String email;
	protected Status status;
	protected TipoPerfil6 tipoPerfil6;
	protected NivelAcesso nivelAcesso;

	// Construtor
	public Perfil6(String nome, String email, Status status, TipoPerfil6 tipoPerfil6, NivelAcesso nivelAcesso) {
		this.nome = nome;
		this.email = email;
		this.status = status;
		this.tipoPerfil6 = tipoPerfil6;
		this.nivelAcesso = nivelAcesso;
	}

	// Método abstrato para exibir as informações do Perfil
	public abstract void exibirInformacoes();

	// Métodos Getters
	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public Status getStatus() {
		return status;
	}

	public TipoPerfil6 getTipoPerfil() {
		return tipoPerfil6;
	}

	public NivelAcesso getNivelAcesso() {
		return nivelAcesso;
	}
}

// Classe concreta de Perfil de Usuário
class PerfilUsuario extends Perfil6 {

	public PerfilUsuario(String nome, String email, Status status, TipoPerfil6 tipoPerfil6, NivelAcesso nivelAcesso) {
		super(nome, email, status, tipoPerfil6, nivelAcesso);
	}

	@Override
	public void exibirInformacoes() {
		System.err.println("______________________________________________________________");
		System.out.println("Nome: " + nome);
		System.out.println("Email: " + email);
		System.out.println("Status: " + status);
		System.out.println("Tipo de Perfil: " + tipoPerfil6);
		System.out.println("Nivel de Acesso: " + nivelAcesso);
	}
}

// Classe de CRUD de Perfil
class CrudPerfil {
	private List<Perfil6> perfis;

	// Construtor
	public CrudPerfil() {
		this.perfis = new ArrayList<>();
	}

	// Criar
	public void adicionarPerfil(Perfil6 perfil) {
		perfis.add(perfil);
	}

	// Atualizar
	public void atualizarPerfil(String email, Perfil6 novoPerfil) {
		for (int i = 0; i < perfis.size(); i++) {
			if (perfis.get(i).getEmail().equals(email)) {
				perfis.set(i, novoPerfil);
				System.out.println("Perfil atualizado!");
				return;
			}
		}
		System.out.println("Perfil não encontrado!");
	}

	// Deletar
	public void deletarPerfil(String email) {
		perfis.removeIf(perfil -> perfil.getEmail().equals(email));
		System.out.println("Perfil deletado!");
	}

	// Buscar
	public Perfil6 buscarPerfil(String email) {
		for (Perfil6 perfil : perfis) {
			if (perfil.getEmail().equals(email)) {
				return perfil;
			}
		}
		return null;
	}

	// Exibir todos os Perfis
	public void exibirPerfis() {
		if (perfis.isEmpty()) {
			System.out.println("Nenhum perfil cadastrado.");
		} else {
			for (Perfil6 perfil : perfis) {
				perfil.exibirInformacoes();
			}
		}
	}

	// Ordenação - por Nome
	public void ordenarPorNome() {
		Collections.sort(perfis, Comparator.comparing(Perfil6::getNome));
		System.out.println("Perfis ordenados por Nome:");
	}

	// Ordenação - por Tipo de Perfil
	public void ordenarPorTipoPerfil() {
		Collections.sort(perfis, Comparator.comparing(Perfil6::getTipoPerfil));
		System.out.println("Perfis ordenados por Tipo de Perfil:");
	}

	// Sincronização - para garantir que o acesso aos perfis seja thread-safe
	public synchronized void adicionarPerfilSincronizado(Perfil6 perfil) {
		perfis.add(perfil);
		System.out.println("Perfil adicionado com segurança.");
	}
}

//Corpo da Classe _______________________________
class Import_2IF {
	public static void run_Import_2IF() {
		System.err.println("\t\t2. Link de afiliado\n");

		LinkAffiliadoService service = new LinkAffiliadoService();

		// Realizando algumas ações CRUD
		service.realizarAcao(Acao.CRIAR, "https://www.amazon.com.br", -1);
		service.realizarAcao(Acao.CRIAR, "https://www.bol.com.br", -1);
		service.realizarAcao(Acao.CRIAR, "https://www.aliexpress.com", -1);

		// Buscando um link
		System.out.println(service.buscar("https://www.amazon.com.br"));

		// Lendo os links
		service.realizarAcao(Acao.LER, "", 1);

		// Atualizando um link
		service.realizarAcao(Acao.ATUALIZAR, "https://www.novo-amazon.com", 0);

		// Deletando um link
		service.realizarAcao(Acao.DELETAR, "", 1);

		// Ordenando os links
		service.ordenar();

		// Exibindo todos os links restantes
		System.out.println("Links restantes: " + service.links);
	}
}

// Enum para o status do link
enum LinkStatus {
	ATIVO, INATIVO, PENDENTE
}

// Enum para os tipos de links de afiliados
enum LinkAffiliado {
	AMAZON, BOL, ALIEXPRESS
}

// Enum para ações no CRUD
enum Acao {
	CRIAR, LER, ATUALIZAR, DELETAR
}

// Classe abstrata para o CRUD de links de afiliados
abstract class LinkAffiliadoCRUD {
	protected List<String> links = new ArrayList<>();

	// Método para criar (adicionar) um novo link
	public abstract void criar(String link);

	// Método para ler (buscar) um link
	public abstract String ler(int indice);

	// Método para atualizar um link
	public abstract void atualizar(int indice, String novoLink);

	// Método para deletar um link
	public abstract void deletar(int indice);

	// Método para ordenar os links
	public abstract void ordenar();

	// Método para buscar um link por nome
	public abstract String buscar(String link);

	// Método sincronizado para realizar operações com segurança em múltiplas
	// threads
	public synchronized void realizarAcao(Acao acao, String link, int indice) {
		switch (acao) {
		case CRIAR:
			criar(link);
			break;
		case LER:
			System.out.println(ler(indice));
			break;
		case ATUALIZAR:
			atualizar(indice, link);
			break;
		case DELETAR:
			deletar(indice);
			break;
		}
	}
}

// Classe concreta que implementa os métodos abstratos de CRUD
class LinkAffiliadoService extends LinkAffiliadoCRUD {

	// Implementação de criar (adicionar) um novo link
	@Override
	public void criar(String link) {
		links.add(link);
		System.out.println("Link adicionado: " + link);
	}

	// Implementação de ler (buscar) um link
	@Override
	public String ler(int indice) {
		return (indice >= 0 && indice < links.size()) ? links.get(indice) : "Link não encontrado";
	}

	// Implementação de atualizar um link
	@Override
	public void atualizar(int indice, String novoLink) {
		if (indice >= 0 && indice < links.size()) {
			links.set(indice, novoLink);
			System.out.println("Link atualizado para: " + novoLink);
		} else {
			System.out.println("Link não encontrado para atualização");
		}
	}

	// Implementação de deletar um link
	@Override
	public void deletar(int indice) {
		if (indice >= 0 && indice < links.size()) {
			String linkRemovido = links.remove(indice);
			System.out.println("Link removido: " + linkRemovido);
		} else {
			System.out.println("Link não encontrado para remoção");
		}
	}

	// Implementação de ordenação dos links
	@Override
	public void ordenar() {
		Collections.sort(links);
		System.out.println("Links ordenados");
	}

	// Implementação de buscar um link
	@Override
	public String buscar(String link) {
		return links.contains(link) ? "Link encontrado: " + link : "Link não encontrado";
	}
}

//Corpo da Classe _______________________________
class Import_3IF {

	private List<TempoReal> tempos;

	public Import_3IF() {
		tempos = Collections.synchronizedList(new ArrayList<>());
	}

	// Criar
	public void adicionarTempo(TempoReal tempo) {
		tempos.add(tempo);
	}

	// Ler
	public TempoReal buscarTempo(int index) {
		if (index >= 0 && index < tempos.size()) {
			return tempos.get(index);
		}
		return null;
	}

	// Atualizar
	public void atualizarTempo(int index, TempoReal novoTempo) {
		if (index >= 0 && index < tempos.size()) {
			tempos.set(index, novoTempo);
		}
	}

	// Deletar
	public void deletarTempo(int index) {
		if (index >= 0 && index < tempos.size()) {
			tempos.remove(index);
		}
	}

	// Ordenação
	public void ordenarPorHora() {
		tempos.sort(Comparator.comparingInt(t -> t.getHora().getHora()));
	}

	public void ordenarPorMinuto() {
		tempos.sort(Comparator.comparingInt(t -> t.getMinuto().getMinuto()));
	}

	public void ordenarPorSegundo() {
		tempos.sort(Comparator.comparingInt(t -> t.getSegundo().getSegundo()));
	}

	// Busca
	public TempoReal buscarPorHora(Hora hora) {
		for (TempoReal tempo : tempos) {
			if (tempo.getHora() == hora) {
				return tempo;
			}
		}
		return null;
	}

	// Exibir todos os tempos
	public void exibirTempos() {
		for (TempoReal tempo : tempos) {
			System.out.println(tempo);
		}
	}

	public static void run_Import_3IF() {
		System.err.println("\t\t3. Horas, Minutos, Segundos\n");

		Import_3IF crudHora = new Import_3IF();

		// Adicionando tempos
		crudHora.adicionarTempo(new TempoReal(Hora.HORA_10, Minuto.MINUTO_30, Segundo.SEGUNDO_45));
		crudHora.adicionarTempo(new TempoReal(Hora.HORA_05, Minuto.MINUTO_15, Segundo.SEGUNDO_30));
		crudHora.adicionarTempo(new TempoReal(Hora.HORA_12, Minuto.MINUTO_00, Segundo.SEGUNDO_00));

		// Exibindo tempos
		System.out.println("Tempos antes da ordenação:");
		crudHora.exibirTempos();

		// Ordenando por hora
		crudHora.ordenarPorHora();
		System.out.println("\nTempos ordenados por hora:");
		crudHora.exibirTempos();

		// Buscando um tempo específico
		TempoReal tempoBuscado = crudHora.buscarPorHora(Hora.HORA_05);
		System.out.println("\nTempo encontrado: " + (tempoBuscado != null ? tempoBuscado : "Não encontrado"));

		// Deletando um tempo
		crudHora.deletarTempo(1);
		System.out.println("\nTempos após deletar o índice 1:");
		crudHora.exibirTempos();

	}

}

enum Hora {
	HORA_00(0), HORA_01(1), HORA_02(2), HORA_03(3), HORA_04(4), HORA_05(5), HORA_06(6), HORA_07(7), HORA_08(8),
	HORA_09(9), HORA_10(10), HORA_11(11), HORA_12(12), HORA_13(13), HORA_14(14), HORA_15(15), HORA_16(16), HORA_17(17),
	HORA_18(18), HORA_19(19), HORA_20(20), HORA_21(21), HORA_22(22), HORA_23(23);

	private final int hora;

	Hora(int hora) {
		this.hora = hora;
	}

	public int getHora() {
		return hora;
	}
}

enum Minuto {
	MINUTO_00(0), MINUTO_01(1), MINUTO_02(2), MINUTO_03(3), MINUTO_04(4), MINUTO_05(5), MINUTO_06(6), MINUTO_07(7),
	MINUTO_08(8), MINUTO_09(9), MINUTO_10(10), MINUTO_11(11), MINUTO_12(12), MINUTO_13(13), MINUTO_14(14),
	MINUTO_15(15), MINUTO_16(16), MINUTO_17(17), MINUTO_18(18), MINUTO_19(19), MINUTO_20(20), MINUTO_21(21),
	MINUTO_22(22), MINUTO_23(23), MINUTO_24(24), MINUTO_25(25), MINUTO_26(26), MINUTO_27(27), MINUTO_28(28),
	MINUTO_29(29), MINUTO_30(30), MINUTO_31(31), MINUTO_32(32), MINUTO_33(33), MINUTO_34(34), MINUTO_35(35),
	MINUTO_36(36), MINUTO_37(37), MINUTO_38(38), MINUTO_39(39), MINUTO_40(40), MINUTO_41(41), MINUTO_42(42),
	MINUTO_43(43), MINUTO_44(44), MINUTO_45(45), MINUTO_46(46), MINUTO_47(47), MINUTO_48(48), MINUTO_49(49),
	MINUTO_50(50), MINUTO_51(51), MINUTO_52(52), MINUTO_53(53), MINUTO_54(54), MINUTO_55(55), MINUTO_56(56),
	MINUTO_57(57), MINUTO_58(58), MINUTO_59(59);

	private final int minuto;

	Minuto(int minuto) {
		this.minuto = minuto;
	}

	public int getMinuto() {
		return minuto;
	}
}

enum Segundo {
	SEGUNDO_00(0), SEGUNDO_01(1), SEGUNDO_02(2), SEGUNDO_03(3), SEGUNDO_04(4), SEGUNDO_05(5), SEGUNDO_06(6),
	SEGUNDO_07(7), SEGUNDO_08(8), SEGUNDO_09(9), SEGUNDO_10(10), SEGUNDO_11(11), SEGUNDO_12(12), SEGUNDO_13(13),
	SEGUNDO_14(14), SEGUNDO_15(15), SEGUNDO_16(16), SEGUNDO_17(17), SEGUNDO_18(18), SEGUNDO_19(19), SEGUNDO_20(20),
	SEGUNDO_21(21), SEGUNDO_22(22), SEGUNDO_23(23), SEGUNDO_24(24), SEGUNDO_25(25), SEGUNDO_26(26), SEGUNDO_27(27),
	SEGUNDO_28(28), SEGUNDO_29(29), SEGUNDO_30(30), SEGUNDO_31(31), SEGUNDO_32(32), SEGUNDO_33(33), SEGUNDO_34(34),
	SEGUNDO_35(35), SEGUNDO_36(36), SEGUNDO_37(37), SEGUNDO_38(38), SEGUNDO_39(39), SEGUNDO_40(40), SEGUNDO_41(41),
	SEGUNDO_42(42), SEGUNDO_43(43), SEGUNDO_44(44), SEGUNDO_45(45), SEGUNDO_46(46), SEGUNDO_47(47), SEGUNDO_48(48),
	SEGUNDO_49(49), SEGUNDO_50(50), SEGUNDO_51(51), SEGUNDO_52(52), SEGUNDO_53(53), SEGUNDO_54(54), SEGUNDO_55(55),
	SEGUNDO_56(56), SEGUNDO_57(57), SEGUNDO_58(58), SEGUNDO_59(59);

	private final int segundo;

	Segundo(int segundo) {
		this.segundo = segundo;
	}

	public int getSegundo() {
		return segundo;
	}
}

abstract class Tempo {
	protected Hora hora;
	protected Minuto minuto;
	protected Segundo segundo;

	public Tempo(Hora hora, Minuto minuto, Segundo segundo) {
		this.hora = hora;
		this.minuto = minuto;
		this.segundo = segundo;
	}

	public abstract String toString();

	public Hora getHora() {
		return hora;
	}

	public Minuto getMinuto() {
		return minuto;
	}

	public Segundo getSegundo() {
		return segundo;
	}

	// Método para formatar a hora, minuto e segundo
	public String formatarTempo() {
		return String.format("%02d:%02d:%02d", hora.getHora(), minuto.getMinuto(), segundo.getSegundo());
	}
}

class TempoReal extends Tempo {
	public TempoReal(Hora hora, Minuto minuto, Segundo segundo) {
		super(hora, minuto, segundo);
	}

	@Override
	public String toString() {
		return formatarTempo();
	}
}