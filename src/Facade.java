import easyaccept.EasyAccept;

/**
 * Facade do sistema, faz todas as chamadas de metodos do sitema.
 * @author Geovane do Nascimento Silva - 116211149
 *
 */
public class Facade {
	
	/**
	 * Método main para testes de aceitação. 
	 * @param args array de argumentos. 
	 */
	public static void main(String[] args) {
		args = new String[] {"Facade", "acceptance_test/us1_test.txt", "acceptance_test/us2_test.txt", 
				"acceptance_test/us3_test.txt", "acceptance_test/us4_test.txt",
				"acceptance_test/us5_test.txt", "acceptance_test/us6_test.txt"};
		EasyAccept.main(args);
	}
	
	private Controller controleDoSistema;
	
	/**
	 * Metodo construtor da Facade
	 */
	public Facade() {
		controleDoSistema = new Controller();
	}
	
	/**
	 * Inicializa o sistema. 
	 * @param caixa o valor incial de caixa do sistem
	 * @param taxa a taxa de desconto para apostas perdedoras
	 */
	public void inicializa(int caixa, double taxa) {
		controleDoSistema.inicializaSistema(caixa, taxa);
	}
	
	/**
	 * Envia os dados ao controleDoSistema para que um cenário seja cadastrado.
	 * @param descricao a descrição do cenário a ser cadastrado. 
	 * @return um inteiro com a posicao do cenario cadastrado. 
	 */
	public int cadastrarCenario(String descricao) {
		return controleDoSistema.cadastrarCenario(descricao);
	}
	
	/**
	 * Envia os dados ao controleDoSistema para que um cenário seja cadastrado.
	 * @param descricao a descrição do cenário a ser cadastrado.
	 * @param bonus o bonus dedicado ao cenario.  
	 * @return um inteiro com a posicao do cenario cadastrado. 
	 */
	public int cadastrarCenario(String descricao, int bonus) {
		return controleDoSistema.cadastrarCenario(descricao, bonus);
	}
	
	/**
	 * Solicita ao controleDoSistema a representação em String de um cenário.
	 * @param cenario o id do cenário a ser retornado
	 * @return a representação em String do cenário. 
	 */
	public String exibirCenario(int cenario) {
		String dados = controleDoSistema.exibirCenario(cenario);
		
		return dados;
	}
	
	/**
	 * Solicita ao controleDoSistema a representação em String de todos os cenários cadastrados.
	 * @return a representação em String com todos os cenários cadastrados. 
	 */
	public String exibirCenarios() {
		String dados = controleDoSistema.exibirCenarios();
		
		return dados;
	}
	
	/**
	 * Envia os dados necessarios ao controleDoSistema para que uma aposta seja cadastrada em um cenário. 
	 * @param cenario id do cenário em que a aposta será cadastrada. 
	 * @param apostador o nome do apostador. 
	 * @param valor o valor da apostas. 
	 * @param previsao a previsao da aposta, se vai acontecer ou não. 
	 */
	public void cadastrarAposta(int cenario, String apostador, int valor, String previsao) {
		controleDoSistema.cadastrarAposta(cenario, apostador, valor, previsao);
	}
	
	/**
	 * Envia os dados necessarios ao controleDoSistema para que uma aposta assegurada por valor seja cadastrada em um cenário. 
	 * @param cenario id do cenário em que a aposta será cadastrada. 
	 * @param apostador o nome do apostador. 
	 * @param valor o valor da apostas. 
	 * @param previsao a previsao da aposta, se vai acontecer ou não. 
	 * @param valorAssegurado o valor assegurado pela aposta. 
	 * @param custo o valor destinado ao caixa que o apostador paga pela aposta
	 * @return o id da aposta em um cenário do sistema. 
	 */
	public int cadastrarApostaSeguraValor(int cenario, String apostador, int valor, String previsao, int valorAssegurado, int custo) {
    	return controleDoSistema.cadastrarAposta(cenario, apostador, valor, previsao, valorAssegurado, custo);
    }
    
	/**
	 * Envia os dados necessarios ao controleDoSistema para que uma aposta assegurada por taxa seja cadastrada em um cenário. 
	 * @param cenario id do cenário em que a aposta será cadastrada. 
	 * @param apostador o nome do apostador. 
	 * @param valor o valor da apostas. 
	 * @param previsao a previsao da aposta, se vai acontecer ou não. 
	 * @param taxaAssegurada a taxa assegurado pela aposta. 
	 * @param custo o valor destinado ao caixa que o apostador paga pela aposta
	 * @return o id da aposta em um cenário do sistema. 
	 */
    public int cadastrarApostaSeguraTaxa(int cenario, String apostador, int valor, String previsao, double taxaAssegurada, int custo) {
    	return controleDoSistema.cadastrarAposta(cenario, apostador, valor, previsao, taxaAssegurada, custo);
    }
    
    /**
     * Envia os dados necessarios ao controleDoSistema para que uma aposta do tipo taxa seja alterada para o tipo valor.
     * @param cenario o cenario em que a aposta assegurada está cadastrada. 
     * @param apostaAssegurada o id da aposta no cenário. 
     * @param valor o valor que passará a ser assegurado pela aposta. 
     * @return o id da aposta em um cenário do sistema. 
     */
    public int alterarSeguroValor(int cenario, int apostaAssegurada, int valor) {
    	return controleDoSistema.alteraSeguroValor(cenario, apostaAssegurada, valor);
    }
    
    /**
     * Envia os dados necessarios ao controleDoSistema para que uma aposta do tipo valor seja alterada para o tipo taxa.
     * @param cenario o cenario em que a aposta assegurada está cadastrada. 
     * @param apostaAssegurada o id da aposta no cenário. 
     * @param taxa a taxa que passará a ser assegurada pela aposta. 
     * @return o id da aposta em um cenário do sistema. 
     */
    public int alterarSeguroTaxa(int cenario, int apostaAssegurada, double taxa) {
    	return controleDoSistema.alteraSeguroTaxa(cenario, apostaAssegurada, taxa);
    }
	
	/**
	 * Solicita ao controleDoSistema o valor total de apostas de um cenário
	 * @param cenario id do cenário a ser verificado. 
	 * @return o total em centavos de apostas cadastradas no cenário. 
	 */
	public int valorTotalDeApostas(int cenario) {
		int total = controleDoSistema.valorApostas(cenario);
		return total;
	}
	
	/**
	 * Solicita ao controleDoSistema quantas apostas tem cadastradas em um determinado cenário. 
	 * @param cenario o id do cenário a ser verificado. 
	 * @return o total de apostas cadastradas no cenário. 
	 */
	public int totalDeApostas(int cenario) {
		int total = controleDoSistema.totalApostas(cenario);
		return total;
	}
	
	/**
	 * Solicita ao controleDoSistema os dados das apostas cadastradas em um cenário. 
	 * @param cenario o id do cenário a ser verificado
	 * @return a representação em String de todas as apostas do cenário. 
	 */
	public String exibirApostas(int cenario) {
		String dados = controleDoSistema.exibirApostas(cenario);
		
		return dados;
	}
	
	/**
	 * Envia ao controleDoSistema os dados necessário para fechar as apostas de um cenário. 
	 * @param cenario o id do cenário a ser fechado. 
	 * @param ocorreu identifica se o cenário ocorreu ou não.
	 */
	public void fecharAposta(int cenario, boolean ocorreu) {
		controleDoSistema.fecharAposta(cenario, ocorreu);
	}
	
	/**
	 * Solicita ao controleDoSistema o valor daquele cenário que será destinado ao caixa do sistema. 
	 * @param cenario o id do cenário a ser verificado. 
	 * @return o valor total do cenario que será destinado ao caixa do sistema. 
	 */
    public int getCaixaCenario(int cenario) {
    	int caixa = controleDoSistema.getCaixaCenario(cenario);
    	return caixa;
    }
    
    /**
     * Solicita ao controleDoSistema o total de rateio que será dividido entre os apostadores que venceram. 
     * @param cenario o id do cenário a ser verificado. 
     * @return o total do rateio a ser dividido entre os vencedores.
     */
    public int getTotalRateioCenario(int cenario) {
    	int rateio = controleDoSistema.getTotalRateioCenario(cenario);
    	return rateio;
    }
    
    /**
     * Solicita ao controleDoSistema o valor do caixa do sistema.
     * @return o valor do caixa do sistema. 
     */
    public int getCaixa() {
    	return controleDoSistema.getCaixa();
    }
    
    public void alterarOrdem(String ordem) {
    	controleDoSistema.alterarOrdem(ordem);
    }
    
    public String exibirCenarioOrdenado(int cenario) {
    	return controleDoSistema.exibirCenarioOrdenado(cenario);
    }
}