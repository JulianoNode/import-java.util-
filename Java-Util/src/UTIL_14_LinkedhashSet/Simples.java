package UTIL_14_LinkedhashSet;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

import util.Linhas;

public class Simples {
	public static void Simples_Run() {

		Linhas linhas = new Linhas();
		String collec = "\n\t Nome\n";
		System.err.println(collec);

		// linhas.run_Caracteres();
		// Import_1S.run_Import_1S();

		linhas.run_Caracteres();
		Import_2S.run_Import_2S();

		linhas.run_Caracteres();
		Import_3S.run_Import_3S();

	}
}

//------------------- ENUMS NIVEL HARD -------------------

enum TipoPedreiro8 {
	PEDREIRO(180), AJUDANTE(120), ENCANADOR(200), PINTOR(150), CARPINTEIRO(170), AZULEJISTA(190);

	private double diaria;

	TipoPedreiro8(double diaria) {
		this.diaria = diaria;
	}

	public double getDiaria() {
		return diaria;
	}

	public String descricao() {
		return name() + " - R$" + diaria + " por dia";
	}
}

enum NivelExperiencia {
	INICIANTE(0.8), INTERMEDIARIO(1.0), AVANCADO(1.2), MESTRE(1.5), SUPREMO(2.0), LENDARIO(3.0);

	private double multiplicador;

	NivelExperiencia(double multiplicador) {
		this.multiplicador = multiplicador;
	}

	public double getMultiplicador() {
		return multiplicador;
	}

	public String descricao() {
		return name() + " (x" + multiplicador + ")";
	}
}

enum TipoObra {
	CASA(1.0), PREDIO(1.5), REFORMA(0.8), ESCOLA(1.3), IGREJA(1.7), SHOPPING(2.0);

	private double fator;

	TipoObra(double fator) {
		this.fator = fator;
	}

	public double getFator() {
		return fator;
	}
}

enum Pagamento {
	DINHEIRO, PIX, CARTAO, TRANSFERENCIA, CHEQUE, CRYPTO;

	public String tipoPagamento() {
		switch (this) {
		case PIX -> {
			return "Pagamento instantâneo via PIX";
		}
		case CARTAO -> {
			return "Pagamento via cartão";
		}
		case TRANSFERENCIA -> {
			return "Transferência bancária";
		}
		case CHEQUE -> {
			return "Pagamento via cheque";
		}
		case CRYPTO -> {
			return "Pagamento via criptomoeda";
		}
		default -> {
			return "Dinheiro em espécie";
		}
		}
	}
}

enum EstadoTrabalho {
	INICIADO, EM_ANDAMENTO, CONCLUIDO, CANCELADO, ATRASADO, PAGO;

	public boolean podeFinalizar() {
		return this == CONCLUIDO || this == PAGO;
	}
}

enum BonusDiario {
	NENHUM(0), PONTUAL(20), EFICIENTE(40), EXEMPLAR(70), LIDER(100), HEROI(150);

	private double bonus;

	BonusDiario(double bonus) {
		this.bonus = bonus;
	}

	public double getBonus() {
		return bonus;
	}
}

//------------------- CLASSE ABSTRATA -------------------

abstract class Trabalhador {
	protected String nome;
	protected TipoPedreiro8 tipo;
	protected NivelExperiencia nivel;
	protected TipoObra obra;
	protected BonusDiario bonus;
	protected int diasTrabalhados;

	public Trabalhador(String nome, TipoPedreiro8 tipo, NivelExperiencia nivel, TipoObra obra, BonusDiario bonus,
			int dias) {
		this.nome = nome;
		this.tipo = tipo;
		this.nivel = nivel;
		this.obra = obra;
		this.bonus = bonus;
		this.diasTrabalhados = dias;
	}

	public abstract double calcularPagamento();

	public void exibirInformacoes() {
		System.out.println("\n--- DADOS DO PEDREIRO ---");
		System.out.println("Nome: " + nome);
		System.out.println("Tipo: " + tipo.descricao());
		System.out.println("Nível: " + nivel.descricao());
		System.out.println("Obra: " + obra);
		System.out.println("Bônus: R$" + bonus.getBonus());
		System.out.println("Dias Trabalhados: " + diasTrabalhados);
	}
}

//------------------- CLASSE CONCRETA SINCRONIZADA -------------------

class PedreiroHard extends Trabalhador {

	public PedreiroHard(String nome, TipoPedreiro8 tipo, NivelExperiencia nivel, TipoObra obra, BonusDiario bonus,
			int dias) {
		super(nome, tipo, nivel, obra, bonus, dias);
	}

	@Override
	public synchronized double calcularPagamento() {
		double total = tipo.getDiaria() * diasTrabalhados * nivel.getMultiplicador() * obra.getFator()
				+ bonus.getBonus();
		return total;
	}
}

//------------------- GERENCIADOR SINCRONIZADO -------------------

class GerenciadorPedreiros {
	private final LinkedHashSet<PedreiroHard> equipe = new LinkedHashSet<>();

	// método sincronizado de adição
	public synchronized void adicionar(PedreiroHard p) {
		equipe.add(p);
	}

	// método sincronizado de ordenação (por valor total)
	public synchronized List<PedreiroHard> ordenarPorValor() {
		List<PedreiroHard> lista = new ArrayList<>(equipe);
		lista.sort((a, b) -> Double.compare(b.calcularPagamento(), a.calcularPagamento()));
		return lista;
	}

	// método sincronizado de busca
	public synchronized Optional<PedreiroHard> buscarPorNome(String nome) {
		for (PedreiroHard p : equipe) {
			if (p.nome.equalsIgnoreCase(nome)) {
				return Optional.of(p);
			}
		}
		return Optional.empty();
	}

	public LinkedHashSet<PedreiroHard> getEquipe() {
		return equipe;
	}
}

//------------------- CLASSE PRINCIPAL -------------------

class DiarioPedreiroHardPlus {
	public static void mainP(String[] args) {

		GerenciadorPedreiros gerenciador = new GerenciadorPedreiros();
		Scanner sc = new Scanner(System.in);

		try {
			System.out.println("=== SISTEMA HARD+ DE DIÁRIAS DE PEDREIRO ===");

			for (int i = 0; i < 3; i++) {
				System.out.print("\nDigite o nome do pedreiro " + (i + 1) + ": ");
				String nome = sc.nextLine();

				System.out.println("Escolha o tipo de pedreiro (0-5): ");
				for (TipoPedreiro8 t : TipoPedreiro8.values()) {
					System.out.println(t.ordinal() + " - " + t.descricao());
				}
				TipoPedreiro8 tipo = TipoPedreiro8.values()[sc.nextInt()];

				System.out.println("Escolha o nível de experiência (0-5): ");
				for (NivelExperiencia n : NivelExperiencia.values()) {
					System.out.println(n.ordinal() + " - " + n.descricao());
				}
				NivelExperiencia nivel = NivelExperiencia.values()[sc.nextInt()];

				System.out.println("Escolha o tipo de obra (0-5): ");
				for (TipoObra o : TipoObra.values()) {
					System.out.println(o.ordinal() + " - " + o.name());
				}
				TipoObra obra = TipoObra.values()[sc.nextInt()];

				System.out.println("Escolha o bônus (0-5): ");
				for (BonusDiario b : BonusDiario.values()) {
					System.out.println(b.ordinal() + " - " + b.name() + " (+R$" + b.getBonus() + ")");
				}
				BonusDiario bonus = BonusDiario.values()[sc.nextInt()];

				System.out.print("Quantos dias foram trabalhados: ");
				int dias = sc.nextInt();
				sc.nextLine(); // limpa buffer

				PedreiroHard p = new PedreiroHard(nome, tipo, nivel, obra, bonus, dias);
				gerenciador.adicionar(p);
			}

			// SWITCH + INSTANCEOF + ITERATOR
			System.out.println("\n=== RESULTADOS GERAIS ===");
			Iterator<PedreiroHard> it = gerenciador.getEquipe().iterator();
			int cont = 1;

			while (it.hasNext()) {
				Trabalhador t = it.next();
				if (t instanceof PedreiroHard ph) {
					ph.exibirInformacoes();
					double total = ph.calcularPagamento();

					switch (cont) {
					case 1 -> System.out.println("Pagamento via " + Pagamento.PIX.tipoPagamento());
					case 2 -> System.out.println("Pagamento via " + Pagamento.CARTAO.tipoPagamento());
					default -> System.out.println("Pagamento via " + Pagamento.DINHEIRO.tipoPagamento());
					}
					System.out.println("Valor total a receber: R$" + total);
					cont++;
				}
			}

			// ---------- MÉTODOS SINCRONIZADOS DE ORDENAR E BUSCAR ----------
			System.out.println("\n=== LISTA ORDENADA POR VALOR TOTAL ===");
			List<PedreiroHard> ordenados = gerenciador.ordenarPorValor();
			for (PedreiroHard p : ordenados) {
				System.out.println(p.nome + " → R$" + p.calcularPagamento());
			}

			System.out.print("\nDigite o nome de um pedreiro para buscar: ");
			String buscaNome = sc.nextLine();

			Optional<PedreiroHard> encontrado = gerenciador.buscarPorNome(buscaNome);
			if (encontrado.isPresent()) {
				PedreiroHard p = encontrado.get();
				System.out.println("\nPedreiro encontrado!");
				p.exibirInformacoes();
				System.out.println("Total: R$" + p.calcularPagamento());
			} else {
				System.out.println("Nenhum pedreiro encontrado com esse nome.");
			}

		} catch (Exception e) {
			System.out.println("Erro durante o processamento: " + e.getMessage());
		} finally {
			try {
				sc.close();
				System.out.println("\nScanner fechado com sucesso!");
			} catch (Exception ex) {
				System.out.println("Erro ao fechar o Scanner.");
			}
		}
	}
}

//Corpo da Classe _______________________________
class Import_1S {
	public static void run_Import_1S() {
		System.err.println("\t\t1. \n");
		DiarioPedreiroHardPlus.mainP(new String[1]);
	}
}

//---------- ENUMS ----------
enum TipoCamera {
	FRONTAL(8), TRASEIRA(64), MACRO(12), ULTRAWIDE(48), TELEPHOTO(50), NOITE(108);

	private int megapixels;

	TipoCamera(int megapixels) {
		this.megapixels = megapixels;
	}

	public int getMegapixels() {
		return megapixels;
	}

	public void exibirInfo() {
		System.out.println("📷 Tipo: " + name() + " | Megapixels: " + megapixels);
	}
}

enum ModoInvestigacao {
	DETECTAR_ROSTO, CAPTURA_NOITE, ZOOM_MAX, ANALISE_AMBIENTE, RECONHECIMENTO, SIGILO;

	public void acionarModo() {
		System.out.println("🔍 Modo " + name() + " ativado para a investigação.");
	}
}

enum EstadoCelular {
	LIGADO, DESLIGADO, GRAVANDO, STANDBY, ALERTA, DESCONHECIDO;

	public boolean podeInvestigar() {
		return this == LIGADO || this == GRAVANDO;
	}
}

enum TipoArquivo {
	FOTO, VIDEO, AUDIO, TEXTO, LOCALIZACAO, LOG;

	public void processarArquivo() {
		System.out.println("💾 Processando arquivo do tipo: " + name());
	}
}

enum PermissaoAcesso {
	PUBLICO, PRIVADO, RESTRITO, CONFIDENCIAL, ULTRA_SECRETO, MILITAR;

	public boolean acessoPermitido(String usuario) {
		return this == PUBLICO || usuario.equalsIgnoreCase("Investigador");
	}
}

enum NivelInvestigacao {
	BAIXO, MEDIO, ALTO, CRITICO, ULTRA, EXTREMO;

	public void exibirNivel() {
		System.out.println("🧩 Nível de investigação: " + name());
	}
}

//---------- CLASSE ABSTRATA ----------
abstract class CameraInvestigacao {
	protected String modelo;
	protected TipoCamera tipo;

	public CameraInvestigacao(String modelo, TipoCamera tipo) {
		this.modelo = modelo;
		this.tipo = tipo;
	}

	public abstract void capturarImagem(ModoInvestigacao modo);

	public abstract void analisarCena();

	// Método sincronizado de registro de evidência
	public synchronized void registrarEvidencia(TipoArquivo tipoArquivo) {
		tipoArquivo.processarArquivo();
		System.out.println("🗂️ Evidência registrada no banco de dados de investigação.\n");
	}

	// Método com switch case
	public void modoOperacao(ModoInvestigacao modo) {
		switch (modo) {
		case DETECTAR_ROSTO -> System.out.println("👁️ Detectando rostos na cena...");
		case CAPTURA_NOITE -> System.out.println("🌙 Modo noturno ativo...");
		case ZOOM_MAX -> System.out.println("🔎 Zoom máximo aplicado...");
		case ANALISE_AMBIENTE -> System.out.println("🌐 Analisando ambiente...");
		case RECONHECIMENTO -> System.out.println("🧠 Reconhecendo padrões...");
		case SIGILO -> System.out.println("🤫 Operando em modo silencioso...");
		default -> System.out.println("⚠️ Modo desconhecido!");
		}
	}
}

//---------- CLASSES CONCRETAS ----------
class CameraFrontal extends CameraInvestigacao {
	public CameraFrontal(String modelo) {
		super(modelo, TipoCamera.FRONTAL);
	}

	@Override
	public void capturarImagem(ModoInvestigacao modo) {
		modo.acionarModo();
		System.out.println("📱 Captura frontal executada no modo " + modo + ".");
	}

	@Override
	public void analisarCena() {
		System.out.println("🔬 Analisando iluminação facial...");
	}
}

class CameraTraseira extends CameraInvestigacao {
	public CameraTraseira(String modelo) {
		super(modelo, TipoCamera.TRASEIRA);
	}

	@Override
	public void capturarImagem(ModoInvestigacao modo) {
		modo.acionarModo();
		System.out.println("📸 Captura traseira em modo " + modo + ".");
	}

	@Override
	public void analisarCena() {
		System.out.println("🔎 Foco e distância ajustados automaticamente.");
	}
}

//---------- CLASSE DE LOG CRIPTOGRAFADO ----------
class LogCriptografado {
	private final StringBuilder logData = new StringBuilder();

	// Método sincronizado que simula criptografia e registro com delay
	public synchronized void registrarEvento(String evento) {
		try {
			String criptografado = criptografar(evento);
			logData.append(criptografado).append("\n");
			Thread.sleep(300); // Simula delay de processamento
			System.out.println("🧾 Log criptografado salvo: " + criptografado);
		} catch (InterruptedException e) {
			System.out.println("⚠️ Erro no registro de log: " + e.getMessage());
		}
	}

	private String criptografar(String texto) {
		StringBuilder sb = new StringBuilder();
		for (char c : texto.toCharArray()) {
			sb.append((char) (c + 3)); // simples cifragem tipo César
		}
		return sb.toString();
	}

	public void exibirLogDecifrado() {
		System.out.println("\n🔓 Decodificando logs armazenados...");
		String[] linhas = logData.toString().split("\n");
		for (String linha : linhas) {
			String decifrado = descriptografar(linha);
			System.out.println("🪪 LOG: " + decifrado);
		}
	}

	private String descriptografar(String texto) {
		StringBuilder sb = new StringBuilder();
		for (char c : texto.toCharArray()) {
			sb.append((char) (c - 3));
		}
		return sb.toString();
	}
}

//---------- MAIN ----------
class InvestigacaoCelularHardPlus {
	public static void main(String[] args) {
		Scanner sc = null;
		try {
			sc = new Scanner(System.in);
			LinkedHashSet<CameraInvestigacao> cameras = new LinkedHashSet<>();
			LogCriptografado log = new LogCriptografado();

			cameras.add(new CameraFrontal("Galaxy-X"));
			cameras.add(new CameraTraseira("Pixel-Spy"));
			System.out.print("(  Pixel-Spy  ) (  Galaxy-X  )");
			System.out.print("Digite o nome do investigador: ");
			String nome = sc.nextLine();

			PermissaoAcesso permissao = nome.equalsIgnoreCase("Investigador") ? PermissaoAcesso.CONFIDENCIAL
					: PermissaoAcesso.PUBLICO;

			System.out.println("\n--- Sistema de Investigação Ativado ---");
			NivelInvestigacao.EXTREMO.exibirNivel();
			log.registrarEvento("Investigador logado: " + nome);

			Iterator<CameraInvestigacao> it = cameras.iterator();
			while (it.hasNext()) {
				CameraInvestigacao cam = it.next();
				cam.tipo.exibirInfo();
				cam.capturarImagem(ModoInvestigacao.ZOOM_MAX);
				cam.modoOperacao(ModoInvestigacao.RECONHECIMENTO);
				cam.analisarCena();

				if (cam instanceof CameraFrontal) {
					System.out.println("🔔 (instanceof) Câmera frontal identificada para reconhecimento facial.");
					log.registrarEvento("Câmera frontal ativada por " + nome);
				}

				if (permissao.acessoPermitido(nome)) {
					cam.registrarEvidencia(TipoArquivo.FOTO);
					log.registrarEvento("Evidência registrada por " + nome);
				} else {
					System.out.println("🚫 Acesso negado ao registro de evidência.");
					log.registrarEvento("Acesso negado para usuário " + nome);
				}

				System.out.println("--------------------------------------\n");
			}

			log.exibirLogDecifrado();

		} catch (Exception e) {
			System.out.println("❌ Erro no sistema: " + e.getMessage());
		} finally {
			try {
				if (sc != null)
					sc.close();
			} catch (Exception e2) {
				System.out.println("⚠️ Scanner já foi fechado com segurança.");
			}
		}
		System.out.println("✅ Operação finalizada com segurança!");
	}
}

//Corpo da Classe _______________________________
class Import_2S {
	public static void run_Import_2S() {
		System.err.println("\t\t2. \n");
		InvestigacaoCelularHardPlus.main(new String[2]);
	}
}

//---------------- ENUMS -----------------
enum TipoImagem {
	JPEG(".jpeg"), PNG(".png"), BMP(".bmp"), GIF(".gif"), TIFF(".tiff"), WEBP(".webp");

	private final String extensao;

	TipoImagem(String extensao) {
		this.extensao = extensao;
	}

	public String getExtensao() {
		return extensao;
	}

	public boolean suportaMetadados() {
		return this != GIF && this != WEBP;
	}
}

enum CategoriaMetadado {
	EXIF, IPTC, XMP, GPS, CAMERA, SISTEMA;

	public String descricao() {
		switch (this) {
		case EXIF:
			return "Informações de captura da câmera";
		case IPTC:
			return "Informações de autoria e copyright";
		case XMP:
			return "Metadados extensíveis Adobe";
		case GPS:
			return "Coordenadas geográficas";
		case CAMERA:
			return "Modelo e marca da câmera";
		default:
			return "Informações do sistema operacional";
		}
	}
}

enum PermissaoEdicao {
	PUBLICO, RESTRITO, PRIVADO, ADMINISTRADOR, AVANCADO, SISTEMA;

	public boolean podeEditar() {
		return this == ADMINISTRADOR || this == AVANCADO;
	}
}

enum EstadoArquivo {
	ORIGINAL, EDITADO, CORROMPIDO, BLOQUEADO, SINCRONIZADO, ARQUIVADO;

	public String getEstadoDescricao() {
		return name() + " - Status atual do arquivo";
	}
}

enum ModoSincronizacao {
	RAPIDO, COMPLETO, DIFERENCIAL, AUTO, MANUAL, BACKUP;

	public synchronized void sincronizar() {
		System.out.println("🔄 Sincronizando em modo: " + this.name());
		try {
			Thread.sleep(600);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			System.err.println("Erro de sincronização: " + e.getMessage());
		}
	}
}

enum TipoEdicao {
	ALTERAR_AUTOR, ALTERAR_DATA, INSERIR_GPS, REMOVER_EXIF, ADICIONAR_TAG, LIMPAR_TODOS;

	public void executarAcao(MetadadoImagem meta) {
		switch (this) {
		case ALTERAR_AUTOR -> meta.setAutor("Novo Autor: Usuário Hard");
		case ALTERAR_DATA -> meta.setData("2025-10-18");
		case INSERIR_GPS -> meta.setGps("Latitude:-23.55, Longitude:-46.63");
		case REMOVER_EXIF -> meta.removerCategoria(CategoriaMetadado.EXIF);
		case ADICIONAR_TAG -> meta.adicionarTag("Editado via Sistema Hard");
		case LIMPAR_TODOS -> meta.limparTudo();
		}
	}
}

//---------------- CLASSE ABSTRATA -----------------
abstract class MetadadoImagem {
	protected String nomeArquivo;
	protected TipoImagem tipo;
	protected LinkedHashSet<String> tags = new LinkedHashSet<>();
	protected Map<CategoriaMetadado, String> dados = new HashMap<>();

	public MetadadoImagem(String nomeArquivo, TipoImagem tipo) {
		this.nomeArquivo = nomeArquivo;
		this.tipo = tipo;
	}

	public abstract void exibirResumo();

	public void adicionarTag(String tag) {
		tags.add(tag);
	}

	public void removerCategoria(CategoriaMetadado categoria) {
		dados.remove(categoria);
	}

	public void limparTudo() {
		tags.clear();
		dados.clear();
	}

	public synchronized void atualizarMetadado(CategoriaMetadado categoria, String valor) {
		dados.put(categoria, valor);
	}

	public void setAutor(String autor) {
		atualizarMetadado(CategoriaMetadado.IPTC, autor);
	}

	public void setData(String data) {
		atualizarMetadado(CategoriaMetadado.EXIF, "Data de Captura: " + data);
	}

	public void setGps(String gps) {
		atualizarMetadado(CategoriaMetadado.GPS, gps);
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public TipoImagem getTipo() {
		return tipo;
	}

	public void mostrarTags() {
		System.out.println("📌 Tags: " + tags);
	}

	public void mostrarMetadados() {
		for (Map.Entry<CategoriaMetadado, String> e : dados.entrySet()) {
			System.out.println("🧩 " + e.getKey() + ": " + e.getValue());
		}
	}

	// Salvar os metadados num arquivo .txt
	public synchronized void salvarEmArquivo() {
		String nomeSaida = nomeArquivo + "_metadados.txt";
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeSaida))) {
			writer.write("===== METADADOS DA IMAGEM =====\n");
			writer.write("Nome: " + nomeArquivo + "\n");
			writer.write("Tipo: " + tipo.getExtensao() + "\n\n");
			writer.write("=== Metadados ===\n");
			for (Map.Entry<CategoriaMetadado, String> e : dados.entrySet()) {
				writer.write(e.getKey() + " -> " + e.getValue() + "\n");
			}
			writer.write("\n=== Tags ===\n");
			for (String t : tags) {
				writer.write("- " + t + "\n");
			}
			writer.write("===============================\n");
			System.out.println("💾 Metadados salvos em: " + nomeSaida);
		} catch (IOException e) {
			System.err.println("⚠️ Erro ao salvar arquivo: " + e.getMessage());
		}
	}
}

//---------------- SUBCLASSE CONCRETA -----------------
class MetadadoFoto extends MetadadoImagem {
	public MetadadoFoto(String nomeArquivo, TipoImagem tipo) {
		super(nomeArquivo, tipo);
	}

	@Override
	public void exibirResumo() {
		System.out.println("🖼️ Arquivo: " + nomeArquivo + " | Tipo: " + tipo.getExtensao());
		System.out.println("🗂️ Total de Metadados: " + dados.size());
	}
}

//---------------- MAIN -----------------
class SistemaMetadadoHard {
	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			LinkedHashSet<MetadadoImagem> imagens = new LinkedHashSet<>();

			MetadadoImagem img1 = new MetadadoFoto("foto1", TipoImagem.JPEG);
			MetadadoImagem img2 = new MetadadoFoto("foto2", TipoImagem.PNG);
			imagens.add(img1);
			imagens.add(img2);

			// Metadados iniciais
			img1.atualizarMetadado(CategoriaMetadado.CAMERA, "Canon EOS");
			img1.atualizarMetadado(CategoriaMetadado.EXIF, "Data: 2025-01-01");
			img2.atualizarMetadado(CategoriaMetadado.CAMERA, "Nikon D7500");

			// Edição
			for (MetadadoImagem img : imagens) {
				System.out.println("\n===== Editando " + img.getNomeArquivo() + " =====");
				TipoEdicao acao = TipoEdicao.values()[new Random().nextInt(TipoEdicao.values().length)];
				System.out.println("Ação sorteada: " + acao);
				acao.executarAcao(img);

				if (img instanceof MetadadoFoto) {
					System.out.println("📸 É uma instância de MetadadoFoto (instanceof confirmado)");
				}

				img.exibirResumo();
				img.mostrarMetadados();
				img.mostrarTags();

				// Salva o arquivo
				img.salvarEmArquivo();
			}

			// Sincronização
			ModoSincronizacao modo = ModoSincronizacao.values()[new Random()
					.nextInt(ModoSincronizacao.values().length)];
			modo.sincronizar();

			// Teste de permissões
			for (PermissaoEdicao p : PermissaoEdicao.values()) {
				System.out.println("🔒 " + p + " pode editar? " + p.podeEditar());
			}

			// Estado de arquivo
			for (EstadoArquivo e : EstadoArquivo.values()) {
				System.out.println(e.getEstadoDescricao());
			}

			System.out.println("\n✅ Sistema finalizado com sucesso.");
		} catch (Exception e) {
			System.err.println("⚠️ Erro geral no sistema de metadados: " + e.getMessage());
		}
	}
}

//Corpo da Classe _______________________________
class Import_3S {
	public static void run_Import_3S() {
		System.err.println("\t\t3. \n");
		SistemaMetadadoHard.main(new String[3]);
	}
}
