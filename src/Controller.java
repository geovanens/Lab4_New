import java.util.HashMap;

/**
 * Classe responsável pelas ações de controle do Sistema.
 * @author Geovane do Nascimento Silva - 116211149
 *
 */
public class Controller {
	private final String NL = System.lineSeparator();
	
	private int caixa;
	private double taxa;
	private Validacoes excecoes;
	private HashMap<Integer, Cenario> cenarios;
	
	/**
	 * Método Construtor da classe. 
	 */
	public Controller() {
		cenarios = new HashMap<>();
		excecoes = new Validacoes();
	}
	
	/**
	 * Inicializa o sistema. 
	 * @param caixa o  valor inicial do caixa do sistema. 
	 * @param taxa o valor da taxa para calculo de desconto de apostas perdedoras. 
	 */
	public void inicializaSistema(int caixa, double taxa) {
		excecoes.inicializacaoInvalida(caixa, taxa);
		this.caixa = caixa;
		this.taxa = taxa;
	}
	
	/**
	 * Cadastra um cenario no sistema caso ele ainda não exista.
	 * @param descricao a descrição do cenário.
	 * @return o tamanho do mapa de cenarios indicando a posicao do atual cenario cadastrado.
	 */
	public int cadastrarCenario(String descricao) {
		excecoes.descricaoVazia(descricao);
		cenarios.put(cenarios.size()+1, new Cenario(descricao, cenarios.size()+1));
		return cenarios.size();
	}
	
	/**
	 * Cadastra um cenario bonus no sistema caso ele ainda não exista.
	 * @param descricao a descrição do cenário.
	 * @param bonus o bonus dedicado ao cenario. 
	 * @return o tamanho do mapa de cenarios indicando a posicao do atual cenario cadastrado.
	 */
	public int cadastrarCenario(String descricao, int bonus) {
		excecoes.descricaoVazia(descricao);
		excecoes.bonus(bonus);
		cenarios.put(cenarios.size()+1, new CenarioBonus(descricao, cenarios.size()+1, bonus));
		caixa -= bonus;
		return cenarios.size();
	}
	
	/**
	 * Cadastra uma aposta em um cenário se o cenário existir. 
	 * @param cenario o cenario a onde a aposta será cadastrada. 
	 * @param apostador o nome do apostador. 
	 * @param valor o valor da aposta. 
	 * @param previsao a previsão da aposta, se acontecerá ou não. 
	 */
	public void cadastrarAposta(int cenario, String apostador, int valor, String previsao) {
		String msg = "Erro no cadastro de aposta: ";
		excecoes.apostaInvalida(cenario, cenarios.containsKey(cenario), apostador, valor, previsao, msg);
		
		boolean previsaoCadastro;
		previsaoCadastro = previsao.equals("VAI ACONTECER")?true : false;
		cenarios.get(cenario).addAposta(apostador, valor, previsaoCadastro);
	}
	
	/**
	 * Cadastra uma aposta assegurada por valor em um cenário se o cenário existir. 
	 * @param cenario o cenario a onde a aposta será cadastrada. 
	 * @param apostador o nome do apostador. 
	 * @param valor o valor da aposta. 
	 * @param previsao a previsão da aposta, se acontecerá ou não. 
	 * @param valorAssegurado o valor assegurado pela aposta. 
	 * @param custo o custo que o apostador paga pela aposta. 
	 * @return o id da aposta assegurada no cenário. 
	 */
	public int cadastrarAposta(int cenario, String apostador, int valor, String previsao, int valorAssegurado, int custo) {
		String msg = "Erro no cadastro de aposta assegurada por valor: ";
		excecoes.apostaInvalida(cenario, cenarios.containsKey(cenario), apostador, valor, previsao, msg);
		excecoes.seguroInvalido(valorAssegurado, custo);
		
		caixa += custo;
		boolean previsaoCadastro;
		previsaoCadastro = previsao.equals("VAI ACONTECER")?true : false;
		return cenarios.get(cenario).addAposta(apostador, valor, previsaoCadastro, valorAssegurado);
	}

	/**
	 * Cadastra uma aposta assegurada por taxa em um cenário se o cenário existir. 
	 * @param cenario o cenario a onde a aposta será cadastrada. 
	 * @param apostador o nome do apostador. 
	 * @param valor o valor da aposta. 
	 * @param previsao a previsão da aposta, se acontecerá ou não. 
	 * @param taxaAssegurada a taxa assegurada pela aposta. 
	 * @param custo o custo que o apostador paga pela aposta. 
	 * @return o id da aposta assegurada no cenário. 
	 */
	public int cadastrarAposta(int cenario, String apostador, int valor, String previsao, double taxaAssegurada, int custo) {
		String msg = "Erro no cadastro de aposta assegurada por taxa: ";
		excecoes.apostaInvalida(cenario, cenarios.containsKey(cenario), apostador, valor, previsao, msg);
		excecoes.seguroInvalido(taxaAssegurada, custo);
		
		caixa += custo;
		boolean previsaoCadastro;
		previsaoCadastro = previsao.equals("VAI ACONTECER")?true : false;
		return cenarios.get(cenario).addAposta(apostador, valor, previsaoCadastro, taxaAssegurada);
	}
	
	/**
	 * Exibe a representação em String de um cenário existente. 
	 * @param cenario o cenário a ser exibido. 
	 * @return representação em String do cenário. 
	 */
	public String exibirCenario(int cenario) {
		String msg = "Erro na consulta de cenario: ";
		excecoes.cenarioInvalido(cenario, cenarios.containsKey(cenario), msg);
		
		return cenarios.get(cenario).toString();
	}
	
	/**
	 * Exibe a representação em String de todos os cenários cadastrados no sistema. 
	 * @return cenariosCadastrados a representação em String dos cenarios cadastrados. 
	 */
	public String exibirCenarios() {
		String cenariosCadastrados = "";
		for (Cenario cenario: cenarios.values()) {
			cenariosCadastrados += cenario.toString() + NL;
		}
		return cenariosCadastrados;
	}
	
	/**
	 * Exibe as apostas cadastradas em um cenário. 
	 * @param cenario o cenário do qual as apostas serão exibidas. 
	 * @return a representação em String das apostas de um cenário. 
	 */
	public String exibirApostas(int cenario) {
		String msg = "Erro na exibicao de apostas: ";
		excecoes.cenarioInvalido(cenario, cenarios.containsKey(cenario), msg);
		return cenarios.get(cenario).exibirApostas();
	}
	
	/**
	 * Exibe o valor total das apostas cadastradas em um cenário existente. 
	 * @param cenario o cenário a ser verificado. 
	 * @return total o valor total das apostas de um cenário, ou 0 caso o cenário não exista. 
	 */
	public int valorApostas(int cenario) {
		String msg = "Erro na consulta do valor total de apostas: ";
		excecoes.cenarioInvalido(cenario, cenarios.containsKey(cenario), msg);
		
		int total = 0;
		Cenario cenarioVerificar = cenarios.get(cenario);
		total = cenarioVerificar.getValorTotalApostas();
		return total;
	}

	/**
	 * Exibe o total de apostas de um cenário caso ele exista. 
	 * @param cenario o cenário a ser verificado. 
	 * @return o total de apostas do cenário, ou 0 caso o cenário não exista. 
	 */
	public int totalApostas(int cenario) {
		String msg = "Erro na consulta do total de apostas: ";
		excecoes.cenarioInvalido(cenario, cenarios.containsKey(cenario), msg);
		
		return cenarios.get(cenario).totalApostas();
	}

	/**
	 * Fechar as apostas de um cenário e atualiza o caixa do sistema, adicionado a porcentagem das 
	 * apostas perdedoras. 
	 * @param cenario o cenarios a ser fechado. 
	 * @param ocorreu identifica se o cenário ocorreu ou não. 
	 */
	public void fecharAposta(int cenario, boolean ocorreu) {
		String msg = "Erro ao fechar aposta: ";
		excecoes.cenarioInvalido(cenario, cenarios.containsKey(cenario), msg);
		excecoes.cenarioFechado(cenarios.get(cenario).isFechado(), msg);
		
		this.cenarios.get(cenario).fecharAposta(ocorreu);
		caixa += getCaixaCenario(cenario);
		caixa -= cenarios.get(cenario).perdedoresAssegurados();
	}

	/**
	 * Verifica quanto do valor de apostas de um cenário será destinado ao caixa do sistema. 
	 * @param cenario o cenário a ser verificado. 
	 * @return o valor total em centavos que será destinado ao caixa do sistema, ou 0 caso 
	 * o cenário não exista.
	 */
	public int getCaixaCenario(int cenario) {
		String msg = "Erro na consulta do caixa do cenario: ";
		excecoes.cenarioInvalido(cenario, cenarios.containsKey(cenario), msg);
		excecoes.cenarioAberto(!cenarios.get(cenario).isFechado(), msg);
		
		return this.cenarios.get(cenario).getCaixaCenario(taxa);
	}

	/**
	 * Verifica quanto do valor de apostas de um cenário será distribuido entre os apostadores vencedores. 
	 * @param cenario o cenário a ser verificado. 
	 * @return o valor total em centavos que será distribuido entre os apostadores vencedores, ou 0 caso 
	 * o cenário não exista.
	 */
	public int getTotalRateioCenario(int cenario) {
		String msg = "Erro na consulta do total de rateio do cenario: ";
		excecoes.cenarioInvalido(cenario, cenarios.containsKey(cenario), msg);
		excecoes.cenarioAberto(!cenarios.get(cenario).isFechado(), msg);
		
		int getCaixaCenario = getCaixaCenario(cenario);
		return this.cenarios.get(cenario).getTotalRateio(getCaixaCenario, taxa);
	}
	
	/**
     * Altera uma aposta do tipo taxa para o tipo valor.
     * @param cenario o cenario em que a aposta assegurada está cadastrada. 
     * @param idAssegurada o id da aposta no cenário. 
     * @param valor o valor que passará a ser assegurado pela aposta. 
     * @return o id da aposta em um cenário do sistema. 
     */
	public int alteraSeguroValor(int cenario, int idAssegurada, int valor) {
		int apostaAssegurada = idAssegurada-1;
		excecoes.alteracaoInvalida(cenario, cenarios.containsKey(cenario), idAssegurada, valor);
		excecoes.naoContemAposta(cenarios.get(cenario).contemAssegurada(apostaAssegurada));
		
		cenarios.get(cenario).alteraSeguroValor(apostaAssegurada, valor);
		return idAssegurada;
	}

	/**
     * Altera uma aposta do tipo valor para o tipo taxa.
     * @param cenario o cenario em que a aposta assegurada está cadastrada. 
     * @param idAssegurada o id da aposta no cenário. 
     * @param taxa a taxa que passará a ser assegurada pela aposta. 
     * @return o id da aposta em um cenário do sistema. 
     */
	public int alteraSeguroTaxa(int cenario, int idAssegurada, double taxa) {
		int apostaAssegurada = idAssegurada-1;
		excecoes.alteracaoInvalida(cenario, cenarios.containsKey(cenario), idAssegurada, taxa);
		excecoes.naoContemAposta(cenarios.get(cenario).contemAssegurada(apostaAssegurada));
		
		cenarios.get(cenario).alteraSeguroTaxa(apostaAssegurada, taxa);
		return idAssegurada;
	}
	
	/**
	 * Pega o valor do caixa do sistema. 
	 * @return O valor atual do caixa do sistema.
	 */
	public int getCaixa() {
		return this.caixa;
	}
    
	/**
	 * Pega o valor da taxa cadastrada. 
	 * @return a taxa cadastrada. 
	 */
	public double getTaxa() {
		return this.taxa;
	}
}