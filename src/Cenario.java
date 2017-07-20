import java.util.ArrayList;

/**
 * Classe responsável pelos cenários de apostas. 
 * 
 * @author Geovane do Nascimento Silva - 116211149
 *
 */
public class Cenario {
	private final String NL = System.lineSeparator();
	
	protected Status status;
	protected String descricao;
	protected int id;
	protected int caixa;
	protected boolean fechado;
	protected ArrayList<Aposta> apostas;
	
	/**
	 * Metódo contrutor da Classe Cenario
	 * @param descricao a descrição do cenário
	 * @param id o identificador do cenário
	 */
	public Cenario(String descricao, int id) {
		this.status = Status.NAO_FINALIZADO;
		this.descricao = descricao;
		this.id = id;
		this.caixa = 0;
		this.fechado = false;
		this.apostas = new ArrayList<>();
	}
	
	/**
	 * Exibe as apostas existentes em um cenário. 
	 * @return dados a representação em String das apostas do cenário. 
	 */
	public String exibirApostas() {
		String dados = "";
		for (Aposta aposta : apostas) {
			dados += aposta.toString() + NL;
		}
		return dados;
	}

	/**
	 * Adiciona uma aposta ao cenário caso o cenario.
	 * @param nome o nome do apostador. 
	 * @param valor o valor da aposta. 
	 * @param previsao a previsão da aposta. 
	 */
	public void addAposta(String nome, int valor, boolean previsao) {
		Aposta aposta = new Aposta(nome, valor, previsao);
		apostas.add(aposta);
		caixa += valor;
	}
	
	/**
	 * Adiciona uma aposta assegurada por valor ao cenario. 
	 * @param apostador o nome do apostador.
	 * @param valor o valor da aposta.
	 * @param previsao a previsão informando se o cenário irá ou não acontecer.
	 * @param valorAssegurado o valor a ser assegurado pela aposta. 
	 * @return o id da aposta assegurada no cenario. 
	 */
	public int addAposta(String apostador, int valor, boolean previsao, int valorAssegurado) {
		Aposta aposta = new Aposta(apostador, valor, previsao, valorAssegurado);
		apostas.add(aposta);
		caixa += valor;

		return apostas.size();
	}

	/*
	 * Adiciona uma aposta assegurada por taxa ao cenario. 
	 * @param apostador o nome do apostador.
	 * @param valor o valor da aposta.
	 * @param previsao a previsão informando se o cenário irá ou não acontecer.
	 * @param taxaAssegurada a taxa a ser assegurado pela aposta. 
	 * @return o id da aposta assegurada no cenario. 
	 */
	public int addAposta(String apostador, int valor, boolean previsao, double taxaAssegurada) {
		Aposta aposta = new Aposta(apostador, valor, previsao, taxaAssegurada);
		apostas.add(aposta);
		caixa += valor;
		
		return apostas.size();
	}
	
	/**
	 * Altera uma aposta do tipo taxa para valor.
	 * @param apostaAssegurada o id da aposta a ser alterada. 
	 * @param valor o novo valor da aposta assegurada. 
	 */
	public void alteraSeguroValor(int apostaAssegurada, int valor) {
		apostas.get(apostaAssegurada).setSeguro(valor);
	}

	/**
	 * Altera uma aposta do tipo valor para taxa.
	 * @param apostaAssegurada o id da aposta a ser alterada. 
	 * @param taxa a nova taxa da aposta assegurada. 
	 */
	public void alteraSeguroTaxa(int apostaAssegurada, double taxa) {
		apostas.get(apostaAssegurada).setSeguro(taxa);
	}

	/**
	 * Pega o total em centavos do valor apostado no cenário. 
	 * @return valorTotalApostas 
	 */
	public int getCaixa() {
		return this.caixa;
	}
	
	/**
	 * Pega o número total de apostas do cenário. 
	 * @return o total de apostas do cenário. 
	 */
	public int totalApostas() {
		return apostas.size();
	}

	/**
	 * Fecha as apostas em um cenário e altera o status do mesmo. 
	 * @param ocorreu identifica se o cenario ocorreu ou não
	 */
	public void fecharAposta(boolean ocorreu) {
		this.status = (ocorreu? Status.OCORREU: Status.NAO_OCORREU);
		setFechado();
	}
	
	/**
	 * Calcula a quantia de apostas do cenário que será destinado ao caixa do sistema. 
	 * @param taxa a taxa de lucro em cima das apostas perdedoras 
	 * @return o valor destinado ao caixa. 
	 */
	public int getCaixaCenario(double taxa){
		return (int) (perdedores() * taxa);
	}
	
	/**
	 * Cacula a quantia de apostas do cenário que será destinado aos vencedores. 
	 * @param getCaixaCenario o valor destinado ao caixa.
	 * @param taxa a taxa de desconto do sistema. 
	 * @return rateio a quantia destinada aos vencedores. 
	 */
	public int getTotalRateio(int getCaixaCenario, double taxa) {
		int rateio = 0;
		rateio += perdedores() - getCaixaCenario;
		return rateio;
	}
	
	/**
	 * @return a situação do cenario, se ainda nao está finalizado, ou caso contratio se 
	 * ocorreu ou não.
	 */
	public String getEstado() {
		return status.getValor();
	}
	
//	/**
//	 * Altera o estado do cenario
//	 * @param ocorreu identifica se o cenario ocorreu ou não. 
//	 */
//	public void setEstado(boolean ocorreu) {
//		status = ocorreu? Status.OCORREU : Status.NAO_OCORREU;
//	}
	
	
	/**
	 * 
	 * @return fechado a identificação se um cenário foi finalizado ou não. 
	 */
	public boolean isFechado() {
		return fechado;
	}

	/**
	 * Altera a variavel fechado 
	 */
	public void setFechado() {
		this.fechado = true;
	}
	
	/**
	 * Retorna a representação em String de um cenário. A representação segue o modelo 
	 * "ID - DECRIÇÃO - STATUS"
	 */
	@Override
	public String toString() {
		return this.id + " - " + this.descricao + " - " + this.status.getValor();
	}

	/**
	 * Calcula o valor do cenario que sera destinado aos seguros de apostas. 
	 * @return assegurados valor destinados aos seguros das apostas. 
	 */
	public int perdedoresAssegurados() {
		int assegurados = 0;
		for (Aposta aposta: apostas) {
			if (!ganhou(aposta))
				assegurados += aposta.getValorAssegurado();
		}
		return assegurados;
	}
	
	/**
	 * Verifica se uma aposta assegurada está cadastrada no cenário. 
	 * @param apostaAssegurada o id da aposta a ser verificada. 
	 * @return se a aposta está cadastrada ou não. 
	 */
	public boolean contem(int apostaAssegurada) {
		if (!apostas.get(apostaAssegurada).equals(null)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Verifica se uma aposta é considerada vencedora.
	 * @param aposta a aposta a ser verificada. 
	 * @return o valor total lucrado com base nos apostadores que perderam. 
	 */
	private boolean ganhou(Aposta aposta) {
		if(!aposta.isPrevisao() && this.status.equals(Status.OCORREU)){
			return false;
		}else if(aposta.isPrevisao() && this.status.equals(Status.NAO_OCORREU)){
			return false;
		}
		return true;
	}
	
	/**
	 * Calcula o valor total dos apostadores que perderam.
	 * @return o valor total lucrado com base nos apostadores que perderam. 
	 */
	private int perdedores() {
		int total = 0;
		
		for(Aposta aposta : this.apostas){
			if (!ganhou(aposta)) {
				total += aposta.getValor();
			}
		}
		return total;
	}
}