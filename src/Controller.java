import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe responsável pelas ações de controle do Sistema.
 * @author Geovane do Nascimento Silva - 116211149
 *
 */
public class Controller {
	private final String NL = System.lineSeparator();
	
	private int caixa;
	private double taxa;
	private Validacoes valida;
	private Map<Integer, Cenario> cenarios;
	private List<Cenario> cenariosOrdenados;
	private ComparacaoPorNumeroApostas comparacaoApostas;
	private boolean alterouOrdem;
	
	/**
	 * Método Construtor da classe. 
	 */
	public Controller() {
		this.cenarios = new HashMap<>();
		this.valida = new Validacoes();
		this.comparacaoApostas = new ComparacaoPorNumeroApostas();
		this.alterouOrdem = false;
		
	}
	
	/**
	 * Inicializa o sistema. 
	 * @param caixa o  valor inicial do caixa do sistema. 
	 * @param taxa o valor da taxa para calculo de desconto de apostas perdedoras. 
	 */
	public void inicializaSistema(int caixa, double taxa) {
		valida.inicializacaoInvalida(caixa, taxa);
		this.caixa = caixa;
		this.taxa = taxa;
	}
	
	/**
	 * Cadastra um cenario no sistema caso ele ainda não exista.
	 * @param descricao a descrição do cenário.
	 * @return o tamanho do mapa de cenarios indicando a posicao do atual cenario cadastrado.
	 */
	public int cadastrarCenario(String descricao) {
		valida.descricaoVazia(descricao);
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
		valida.descricaoVazia(descricao);
		valida.bonus(bonus);
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
		valida.apostaInvalida(cenario, cenarios.containsKey(cenario), apostador, valor, previsao, msg);
		
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
		valida.apostaInvalida(cenario, cenarios.containsKey(cenario), apostador, valor, previsao, msg);
		valida.seguroInvalido(valorAssegurado, custo);
		
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
		valida.apostaInvalida(cenario, cenarios.containsKey(cenario), apostador, valor, previsao, msg);
		valida.seguroInvalido(taxaAssegurada, custo);
		
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
		valida.cenarioInvalido(cenario, cenarios.containsKey(cenario), msg);
		
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
		valida.cenarioInvalido(cenario, cenarios.containsKey(cenario), msg);
		return cenarios.get(cenario).exibirApostas();
	}
	
	/**
	 * Exibe o valor total das apostas cadastradas em um cenário existente. 
	 * @param cenario o cenário a ser verificado. 
	 * @return total o valor total das apostas de um cenário, ou 0 caso o cenário não exista. 
	 */
	public int valorApostas(int cenario) {
		String msg = "Erro na consulta do valor total de apostas: ";
		valida.cenarioInvalido(cenario, cenarios.containsKey(cenario), msg);
		
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
		valida.cenarioInvalido(cenario, cenarios.containsKey(cenario), msg);
		
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
		valida.cenarioInvalido(cenario, cenarios.containsKey(cenario), msg);
		valida.cenarioFechado(cenarios.get(cenario).isFechado(), msg);
		
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
		valida.cenarioInvalido(cenario, cenarios.containsKey(cenario), msg);
		valida.cenarioAberto(!cenarios.get(cenario).isFechado(), msg);
		
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
		valida.cenarioInvalido(cenario, cenarios.containsKey(cenario), msg);
		valida.cenarioAberto(!cenarios.get(cenario).isFechado(), msg);
		
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
		valida.alteracaoInvalida(cenario, cenarios.containsKey(cenario), idAssegurada, valor);
		valida.naoContemAposta(cenarios.get(cenario).contemAssegurada(apostaAssegurada));
		
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
		valida.alteracaoInvalida(cenario, cenarios.containsKey(cenario), idAssegurada, taxa);
		valida.naoContemAposta(cenarios.get(cenario).contemAssegurada(apostaAssegurada));
		
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

	/**
     * Gerencia o tipo de ordenação que será executada
     * @param ordem a ordem em que os cenários devem ser ordenados. Pode ser: "nome", "apostas" ou "cadastro". 
     */
	public void alterarOrdem(String ordem) {
		valida.ordemInvalida(ordem);
		if (ordem.toUpperCase().equals("NOME")) {
			ordenarPorNome();
		}
		else if (ordem.toUpperCase().equals("APOSTAS")) {
			ordenaTotalApostas();
		}
		else {
			this.cenariosOrdenados = new ArrayList<>(cenarios.values());
		}
		this.alterouOrdem = true;
		
	}

	/**
	 * Exibe um cenário ordenado. 
	 * @param cenario a posição do cenário em ordem. 
	 * @return a representação em String do cenário. 
	 */
	public String exibirCenarioOrdenado(int cenario) {
		String msg = "Erro na consulta de cenario ordenado: ";
		valida.cenarioInvalido(cenario, cenarios.containsKey(cenario), msg);
		if (!alterouOrdem) {
			this.cenariosOrdenados = new ArrayList<>(cenarios.values());
		}
		return this.cenariosOrdenados.get(cenario-1).toString();
	}
	
	/**
	 * Método auxiliar de ordenação de cenários por ordem de descrição. 
	 */
	private void ordenarPorNome() {
		this.cenariosOrdenados = new ArrayList<>(cenarios.values());
		Collections.sort(this.cenariosOrdenados);
    }
	
	/**
	 * Método auxiliar de ordenação por ordem de total de apostas de um cenário. Cenários com maior numero de apostas 
	 * vem antes dos demais. 
	 */
	private void ordenaTotalApostas() {
		this.cenariosOrdenados = new ArrayList<>(cenarios.values());
		Collections.sort(this.cenariosOrdenados, comparacaoApostas);
	}
}