package UTIL_14_LinkedhashSet;

import java.util.Iterator;
import java.util.LinkedHashSet;

import util.Linhas;

public class For_Matrix {
	public static void For_Run() {

		Linhas linhas = new Linhas();
		String collec = "\n\t Nome \n";
		System.err.println(collec);

		linhas.run_Caracteres();
		Import_1F.run_Import_1F();

		linhas.run_Caracteres();
		Import_2F.run_Import_2F();

		linhas.run_Caracteres();
		Import_3F.run_Import_3F();

	}
}

//Corpo da Classe _______________________________
//========================== ENUNS NÍVEL HARD ==========================
enum Origem {
	SALARIO, INVESTIMENTO, CRIPTO, TRANSFERENCIA, DESCONHECIDA, LAVAGEM;

	public boolean origemLegal() {
		return this == SALARIO || this == INVESTIMENTO || this == TRANSFERENCIA;
	}
}

enum Destino {
	BANCO, PARAISO_FISCAL, CARTEIRA_CRIPTO, EMPRESA, FAMILIAR, DESCONHECIDO;

	public boolean destinoSuspeito() {
		return this == PARAISO_FISCAL || this == CARTEIRA_CRIPTO || this == DESCONHECIDO;
	}
}

enum StatusInvestigacao {
	EM_ANALISE, CONFIRMADO, ENCERRADO, ALERTA, URGENTE, LIMPO;

	public String mensagemStatus() {
		switch (this) {
		case EM_ANALISE:
			return "🕵️‍♂️ Em análise...";
		case CONFIRMADO:
			return "✅ Confirmado.";
		case ENCERRADO:
			return "🔒 Encerrado.";
		case ALERTA:
			return "⚠️ Alerta detectado!";
		case URGENTE:
			return "🚨 Investigação Urgente!";
		case LIMPO:
			return "🟢 Nenhum problema detectado.";
		default:
			return "Desconhecido.";
		}
	}
}

enum TipoTransacao {
	DEPOSITO, SAQUE, PIX, TED, INVESTIMENTO, CRIPTO;

	public boolean movimentaValorAlto(double valor) {
		return valor > 10000;
	}
}

enum NivelSuspeita {
	BAIXO, MEDIO, ALTO, EXTREMO, DESCONHECIDO, BLOQUEADO;

	public static NivelSuspeita calcular(double valor, boolean origemLegal, boolean destinoSuspeito) {
		if (valor < 1000 && origemLegal && !destinoSuspeito)
			return BAIXO;
		else if (valor < 5000 && origemLegal)
			return MEDIO;
		else if (valor >= 5000 && destinoSuspeito)
			return ALTO;
		else if (valor >= 20000)
			return EXTREMO;
		else if (!origemLegal && destinoSuspeito)
			return BLOQUEADO;
		else
			return DESCONHECIDO;
	}
}

enum TipoRelatorio {
	BASICO, COMPLETO, URGENTE, DETALHADO, OCULTO, SIGILOSO;

	public void exibirFormato() {
		switch (this) {
		case BASICO -> System.out.println("Relatório Básico Gerado.");
		case COMPLETO -> System.out.println("Relatório Completo Gerado.");
		case URGENTE -> System.out.println("🚨 Relatório URGENTE emitido!");
		case DETALHADO -> System.out.println("🔍 Relatório Detalhado em andamento.");
		case OCULTO -> System.out.println("🔒 Relatório Oculto. Acesso restrito.");
		case SIGILOSO -> System.out.println("🕶️ Relatório SIGILOSO gerado com sucesso.");
		}
	}
}

//========================== CLASSE ABSTRATA ==========================
abstract class Transacao {
	protected String titular;
	protected double valor;
	protected Origem origem;
	protected Destino destino;
	protected TipoTransacao tipo;

	public Transacao(String titular, double valor, Origem origem, Destino destino, TipoTransacao tipo) {
		this.titular = titular;
		this.valor = valor;
		this.origem = origem;
		this.destino = destino;
		this.tipo = tipo;
	}

	public abstract void analisarTransacao();

	public synchronized void gerarRelatorio(TipoRelatorio relatorio) {
		System.out.println("\n--- Gerando Relatório ---");
		relatorio.exibirFormato();

		switch (relatorio) {
		case BASICO, COMPLETO, URGENTE -> System.out.println("Transação: " + titular + " -> " + valor + " R$");
		case DETALHADO, OCULTO, SIGILOSO ->
			System.out.println("Detalhes sigilosos de " + titular + " sendo processados...");
		default -> System.out.println("Tipo de relatório desconhecido.");
		}
	}
}

//========================== CLASSE CONCRETA ==========================
class InvestigacaoTransacao extends Transacao {

	public InvestigacaoTransacao(String titular, double valor, Origem origem, Destino destino, TipoTransacao tipo) {
		super(titular, valor, origem, destino, tipo);
	}

	@Override
	public void analisarTransacao() {
		System.out.println("\nInvestigando titular: " + titular);

		// Lógica principal com múltiplos if e else if
		if (valor < 1000 && origem.origemLegal()) {
			System.out.println("💰 Transação de baixo valor. Sem suspeitas.");
		} else if (valor >= 1000 && valor < 10000 && destino.destinoSuspeito()) {
			System.out.println("⚠️ Valor moderado, mas destino suspeito.");
		} else if (valor >= 10000 && !origem.origemLegal()) {
			System.out.println("🚨 Alta quantia sem origem legal confirmada.");
		} else if (tipo.movimentaValorAlto(valor)) {
			System.out.println("💸 Valor elevado em transação detectado.");
		} else if (destino.destinoSuspeito()) {
			System.out.println("🕵️‍♂️ Destino com histórico suspeito detectado.");
		} else if (origem == Origem.LAVAGEM) {
			System.out.println("❌ Origem confirmada como lavagem de dinheiro.");
		} else {
			System.out.println("🟢 Transação normal.");
		}

		NivelSuspeita nivel = NivelSuspeita.calcular(valor, origem.origemLegal(), destino.destinoSuspeito());
		System.out.println("Nível de Suspeita: " + nivel);
	}
}

//========================== CLASSE PRINCIPAL ==========================
class InvestigacaoDestinoDinheiro {
	public static void main(String[] args) {
		LinkedHashSet<Transacao> transacoes = new LinkedHashSet<>();

		transacoes.add(new InvestigacaoTransacao("Carlos", 500, Origem.SALARIO, Destino.BANCO, TipoTransacao.PIX));
		transacoes.add(new InvestigacaoTransacao("Fernanda", 15000, Origem.CRIPTO, Destino.CARTEIRA_CRIPTO,
				TipoTransacao.CRIPTO));
		transacoes.add(new InvestigacaoTransacao("João", 22000, Origem.DESCONHECIDA, Destino.PARAISO_FISCAL,
				TipoTransacao.TED));
		transacoes.add(new InvestigacaoTransacao("Luiza", 8000, Origem.INVESTIMENTO, Destino.EMPRESA,
				TipoTransacao.INVESTIMENTO));

		// Uso de for + instanceof + synchronized
		for (Transacao t : transacoes) {
			if (t instanceof InvestigacaoTransacao inv) {
				inv.analisarTransacao();
				inv.gerarRelatorio(TipoRelatorio.COMPLETO);
			}
		}

		System.out.println("\n--- FINALIZAÇÃO DA INVESTIGAÇÃO ---");
		Iterator<Transacao> it = transacoes.iterator();
		while (it.hasNext()) {
			Transacao t = it.next();
			System.out.println("✔️ Transação verificada: " + t.titular);
		}

		System.out.println("\nProcesso de investigação concluído com sucesso ✅");
	}
}

class Import_1F {
	public static void run_Import_1F() {
		System.err.println("\t\t1. \n");
		InvestigacaoDestinoDinheiro.main(new String[1]);
	}
}

//Corpo da Classe _______________________________

// ================= ENUMS DE ALTO NÍVEL =======================
enum TipoTributo {
	IRPF(0.075), IRPJ(0.15), COFINS(0.03), CSLL(0.09), ISS(0.02), PIS(0.016);

	private double aliquota;

	TipoTributo(double aliquota) {
		this.aliquota = aliquota;
	}

	public double calcularImposto(double base) {
		return base * aliquota;
	}

	public String descricao() {
		return name() + " - Alíquota: " + (aliquota * 100) + "%";
	}
}

enum TipoPessoa {
	FISICA, JURIDICA, AUTONOMO, EMPRESA, MICROEMPREENDEDOR, GOVERNAMENTAL;

	public boolean isPessoaFisica() {
		return this == FISICA || this == AUTONOMO;
	}
}

enum SituacaoTributaria {
	REGULAR, IRREGULAR, EM_ANALISE, SUSPENSA, CANCELADA, INATIVA;

	public boolean podeEmitirNota() {
		return this == REGULAR || this == EM_ANALISE;
	}
}

enum ReceitaOrigem {
	SALARIO, SERVICO, LUCRO, VENDA, IMPORTACAO, EXPORTACAO;

	public double calcularReceitaComBonus(double valor) {
		switch (this) {
		case EXPORTACAO -> {
			return valor * 1.1;
		}
		case IMPORTACAO -> {
			return valor * 0.9;
		}
		default -> {
			return valor;
		}
		}
	}
}

enum NivelFiscalizacao {
	BAIXO, MEDIO, ALTO, CRITICO, ESPECIAL, URGENTE;

	public String mensagemFiscal() {
		return switch (this) {
		case BAIXO -> "Fiscalização leve.";
		case MEDIO -> "Verificação intermediária.";
		case ALTO -> "Atenção! Risco tributário elevado.";
		case CRITICO -> "Auditoria completa necessária!";
		case ESPECIAL -> "Monitoramento em tempo real.";
		case URGENTE -> "Investigação federal ativa!";
		};
	}
}

enum RegiaoFederal {
	NORTE, NORDESTE, CENTRO_OESTE, SUDESTE, SUL, INTERNACIONAL;

	public double ajustarTaxaRegional(double valor) {
		return switch (this) {
		case NORTE -> valor * 0.95;
		case NORDESTE -> valor * 0.97;
		case CENTRO_OESTE -> valor * 1.0;
		case SUDESTE -> valor * 1.05;
		case SUL -> valor * 1.03;
		case INTERNACIONAL -> valor * 1.15;
		};
	}
}

// ===================== CLASSE ABSTRATA =========================
abstract class ReceitaFederal {
	protected String contribuinte;
	protected double rendaBase;
	protected TipoPessoa tipoPessoa;
	protected SituacaoTributaria situacao;

	public ReceitaFederal(String contribuinte, double rendaBase, TipoPessoa tipoPessoa, SituacaoTributaria situacao) {
		this.contribuinte = contribuinte;
		this.rendaBase = rendaBase;
		this.tipoPessoa = tipoPessoa;
		this.situacao = situacao;
	}

	public abstract double calcularTributo(TipoTributo tributo);

	public void analisarSituacao() {
		switch (situacao) {
		case REGULAR -> System.out.println(contribuinte + " está com situação REGULAR.");
		case IRREGULAR -> System.out.println(contribuinte + " precisa regularizar seus tributos!");
		case EM_ANALISE -> System.out.println(contribuinte + " está em análise pela Receita Federal.");
		default -> System.out.println("Situação não especificada para " + contribuinte);
		}
	}
}

// ===================== CLASSE CONCRETA =========================
class DeclaracaoTributaria extends ReceitaFederal {

	public DeclaracaoTributaria(String contribuinte, double rendaBase, TipoPessoa tipoPessoa,
			SituacaoTributaria situacao) {
		super(contribuinte, rendaBase, tipoPessoa, situacao);
	}

	@Override
	public synchronized double calcularTributo(TipoTributo tributo) {
		double imposto = tributo.calcularImposto(rendaBase);
		System.out.println("Cálculo do tributo (" + tributo.name() + ") para " + contribuinte + ": R$ " + imposto);
		return imposto;
	}

	public boolean instanceofVerificacao(Object obj) {
		if (obj instanceof DeclaracaoTributaria) {
			System.out.println("✔ Objeto é uma instância de DeclaracaoTributaria");
			return true;
		} else if (obj instanceof ReceitaFederal) {
			System.out.println("✔ Objeto é uma instância genérica de ReceitaFederal");
			return true;
		} else {
			System.out.println("✖ Objeto desconhecido para verificação tributária.");
			return false;
		}
	}
}

// ===================== CLASSE PRINCIPAL =========================
class ReceitaFederalSistema {
	public static void main(String[] args) {

		LinkedHashSet<DeclaracaoTributaria> declaracoes = new LinkedHashSet<>();

		declaracoes.add(new DeclaracaoTributaria("Alice LTDA", 85000, TipoPessoa.JURIDICA, SituacaoTributaria.REGULAR));
		declaracoes.add(new DeclaracaoTributaria("Carlos MEI", 12000, TipoPessoa.MICROEMPREENDEDOR,
				SituacaoTributaria.EM_ANALISE));
		declaracoes.add(new DeclaracaoTributaria("João Silva", 50000, TipoPessoa.FISICA, SituacaoTributaria.IRREGULAR));

		for (DeclaracaoTributaria d : declaracoes) {
			d.analisarSituacao();

			// Estrutura condicional complexa
			if (d.tipoPessoa == TipoPessoa.FISICA) {
				d.calcularTributo(TipoTributo.IRPF);
			} else if (d.tipoPessoa == TipoPessoa.JURIDICA) {
				d.calcularTributo(TipoTributo.IRPJ);
			} else if (d.tipoPessoa == TipoPessoa.MICROEMPREENDEDOR) {
				d.calcularTributo(TipoTributo.ISS);
			}

			if (d.situacao == SituacaoTributaria.REGULAR) {
				System.out.println("Situação regular, permitido emitir nota.");
			} else if (d.situacao == SituacaoTributaria.IRREGULAR) {
				System.out.println("Multa aplicada! Regularização necessária.");
			} else if (d.situacao == SituacaoTributaria.EM_ANALISE) {
				System.out.println("Em análise, sem bloqueios.");
			}

			d.instanceofVerificacao(d);
			System.out.println("------------------------------------------");
		}

		// Verificando mensagens de fiscalização
		for (NivelFiscalizacao nivel : NivelFiscalizacao.values()) {
			System.out.println("[" + nivel + "] → " + nivel.mensagemFiscal());
		}

		System.out.println("Sistema de Receita Federal finalizado com sucesso ✅");
	}
}

class Import_2F {
	public static void run_Import_2F() {
		System.err.println("\t\t2. \n");
		ReceitaFederalSistema.main(new String[2]);
	}
}

//Corpo da Classe _______________________________

// ---------------- ENUMS ---------------- //

enum Partido {
    DEMOCRATICO("Democrático"), REPUBLICANO("Republicano"),
    VERDE("Verde"), LIBERAL("Liberal"),
    SOCIALISTA("Socialista"), INDEPENDENTE("Independente");

    private final String descricao;

    Partido(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void apresentarPartido() {
        System.out.println("🗳 Partido: " + descricao);
    }
}

enum NivelEducacao {
    FUNDAMENTAL, MEDIO, SUPERIOR, POS, MESTRADO, DOUTORADO;

    public void mostrarFormacao() {
        System.out.println("🎓 Formação: " + name());
    }
}

enum Regiao1 {
    NORTE, NORDESTE, CENTRO_OESTE, SUDESTE, SUL;

    public String capitalPrincipal() {
        switch (this) {
            case NORTE: return "Manaus";
            case NORDESTE: return "Salvador";
            case CENTRO_OESTE: return "Brasília";
            case SUDESTE: return "São Paulo";
            case SUL: return "Curitiba";
            default: return "Desconhecida";
        }
    }
}

enum Cargo {
    PRESIDENTE, GOVERNADOR, SENADOR, DEPUTADO, PREFEITO, VEREADOR;

    public boolean cargoFederal() {
        return this == PRESIDENTE || this == SENADOR || this == DEPUTADO;
    }
}

enum SituacaoPolitica {
    LIMPO, INVESTIGADO, CASSADO, REELEITO, NOVATO, VETERANO;

    public void status() {
        System.out.println("📊 Situação: " + name());
    }
}

enum TipoCampanha {
    DIGITAL, PRESENCIAL, MISTA, VOLUNTARIA, FINANCIADA, COMUNITARIA;

    public double custoEstimado() {
        switch (this) {
            case DIGITAL: return 20000;
            case PRESENCIAL: return 50000;
            case MISTA: return 70000;
            case VOLUNTARIA: return 10000;
            case FINANCIADA: return 120000;
            case COMUNITARIA: return 15000;
            default: return 0;
        }
    }
}

// ---------------- CLASSE ABSTRATA ---------------- //

abstract class Politico {
    protected String nome;
    protected Partido partido;
    protected Cargo cargo;
    protected int votos;

    public Politico(String nome, Partido partido, Cargo cargo) {
        this.nome = nome;
        this.partido = partido;
        this.cargo = cargo;
        this.votos = 0;
    }

    public abstract void campanha(TipoCampanha tipo);

    public synchronized void votar() {
        votos++;
    }

    public void mostrarInfo() {
        System.out.println("👤 " + nome + " | Partido: " + partido.getDescricao() + " | Cargo: " + cargo);
    }

    // Switch interno (nível hard)
    public void analiseCampanha(TipoCampanha tipo) {
        switch (tipo) {
            case DIGITAL -> System.out.println("📱 Campanha Online — baixo custo e grande alcance.");
            case PRESENCIAL -> System.out.println("🏛 Campanha de rua — custo alto, contato direto com o eleitor.");
            case MISTA -> System.out.println("💻📍 Estratégia híbrida de campanha.");
            default -> System.out.println("🔎 Estratégia especial de campanha.");
        }
    }
}

// ---------------- SUBCLASSE ---------------- //

class Candidato extends Politico {
    private NivelEducacao formacao;
    private SituacaoPolitica situacao;
    private Regiao1 regiao;

    public Candidato(String nome, Partido partido, Cargo cargo, NivelEducacao formacao, SituacaoPolitica situacao, Regiao1 regiao) {
        super(nome, partido, cargo);
        this.formacao = formacao;
        this.situacao = situacao;
        this.regiao = regiao;
    }

    @Override
    public synchronized void campanha(TipoCampanha tipo) {
        analiseCampanha(tipo);
        System.out.println("💬 " + nome + " iniciou campanha do tipo: " + tipo + " com custo estimado de " + tipo.custoEstimado());
    }

    public void mostrarDetalhes() {
        mostrarInfo();
        formacao.mostrarFormacao();
        situacao.status();
        System.out.println("🌎 Região: " + regiao + " — Capital: " + regiao.capitalPrincipal());
        System.out.println("--------------------------------------------");
    }
}

// ---------------- CLASSE PRINCIPAL ---------------- //

class EleicaoNacional {

    public static void main(String[] args) {
        LinkedHashSet<Politico> candidatos = new LinkedHashSet<>();

        // Adicionando políticos
        candidatos.add(new Candidato("Ana Silva", Partido.DEMOCRATICO, Cargo.PRESIDENTE, NivelEducacao.DOUTORADO, SituacaoPolitica.LIMPO, Regiao1.SUDESTE));
        candidatos.add(new Candidato("Carlos Souza", Partido.REPUBLICANO, Cargo.GOVERNADOR, NivelEducacao.MESTRADO, SituacaoPolitica.REELEITO, Regiao1.SUL));
        candidatos.add(new Candidato("João Mendes", Partido.VERDE, Cargo.SENADOR, NivelEducacao.SUPERIOR, SituacaoPolitica.NOVATO, Regiao1.NORDESTE));
        candidatos.add(new Candidato("Paula Lima", Partido.LIBERAL, Cargo.DEPUTADO, NivelEducacao.POS, SituacaoPolitica.VETERANO, Regiao1.CENTRO_OESTE));

        // ---------------- Lógica Hard ---------------- //
        int contador = 0;
        for (Politico p : candidatos) {
            if (p instanceof Candidato c) {
                c.campanha(TipoCampanha.MISTA);
                if (c.cargo.cargoFederal()) {
                    System.out.println("⚙️ Candidato Federal detectado.");
                    c.votar();
                    c.votar();
                } else if (c.cargo == Cargo.GOVERNADOR) {
                    System.out.println("🏛 Candidato Estadual — 1 voto aplicado.");
                    c.votar();
                } else if (c.cargo == Cargo.DEPUTADO) {
                    System.out.println("🗳 Candidato Estadual — 3 votos aplicados.");
                    c.votar();
                    c.votar();
                    c.votar();
                }

                if (c.votos > 2) {
                    System.out.println("✅ " + c.nome + " está com alta popularidade!");
                } else if (c.votos == 2) {
                    System.out.println("⚖️ Popularidade moderada.");
                } else if (c.votos < 2) {
                    System.out.println("🚨 Popularidade baixa.");
                }

                c.mostrarDetalhes();
                contador++;
            }
        }

        // Segunda parte do if-else hard
        if (contador > 3) {
            System.out.println("📢 Muitos candidatos ativos!");
        } else if (contador == 3) {
            System.out.println("📊 Quantidade equilibrada de candidatos.");
        } else if (contador < 3) {
            System.out.println("⚠️ Poucos candidatos na disputa.");
        }
    }
}

class Import_3F {
	public static void run_Import_3F() {
		System.err.println("\t\t3.\n");
		EleicaoNacional.main(new String[3]);
	}
}
