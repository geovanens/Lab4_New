import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * Classe responsável pelas apostas que são cadastradas nos possíveis cenários. 
 * 
 * @author Geovane do Nascimento Silva - 116211149
 *
 */
public class Aposta {
	private String apostador;
	private int valor;
	private boolean previsao;
	private Seguro seguro;

	/**
	 * Método Contrutor de uma aposta normal(sem seguro).
	 * @param apostador o nome do apostador.
	 * @param valor o valor a ser apostado.
	 * @param previsao a previsao informando se o cenário irá ou não acontecer.
	 */
	public Aposta(String apostador, int valor, boolean previsao) {
		this.apostador = apostador;
		this.valor = valor;
		this.previsao = previsao;
		this.seguro = new SemSeguro();
	}
	
	/**
	 * Método Contrutor de uma aposta com seguro por valor.
	 * @param apostador o nome do apostador.
	 * @param valor o valor a ser apostado.
	 * @param previsao a previsao informando se o cenário irá ou não acontecer.
	 * @param valorAssegurado o valor a ser assegurado pela aposta. Caso o apostador perca a aposta, este 
	 * valor retorna para ele. 
	 */
	public Aposta(String apostador, int valor, boolean previsao, int valorAssegurado) {
		this.apostador = apostador;
		this.valor = valor;
		this.previsao = previsao;
		this.seguro = new SeguroValor(valorAssegurado);
	}

	/**
	 * Método Contrutor de uma aposta com seguro por taxa.
	 * @param apostador o nome do apostador.
	 * @param valor o valor a ser apostado.
	 * @param previsao a previsao informando se o cenário irá ou não acontecer.
	 * @param taxaAssegurada a taxa do valor da aposta que será assegurada. Caso o apostador perca a aposta, este 
	 * valor retorna para ele (taxa * valor).  
	 */
	public Aposta(String apostador, int valor, boolean previsao, double taxaAssegurada) {
		this.apostador = apostador;
		this.valor = valor;
		this.previsao = previsao;
		this.seguro = new SeguroTaxa(taxaAssegurada, valor);
	}

	/**
	 * Método responsável por transformar o valor de uma aposta de centavos em reais.
	 * @return formatado o valor em reais. 
	 */
	private String valorEmReais() {
		double valorEmReais = this.valor / 100.0;
		BigDecimal valor = new BigDecimal (valorEmReais);  
		NumberFormat nf = NumberFormat.getCurrencyInstance();  
		String formatado = nf.format (valor);
		return formatado;
	}
	
	/**
	 * Pega o nome do apostador
	 * @return o nome do apostador.
	 */
	public String getNome() {
		return this.apostador;
	}
	
	/**
	 * Pega o valor da aposta
	 * @return o valor da aposta.
	 */
	public int getValor() {
		return this.valor;
	}
	
	/**
	 * pega a previsao da aposta
	 * @return a previsão da aposta.
	 */
	public boolean isPrevisao() {
		return this.previsao;
	}
	
	/**
	 * Pega o valor assegurado da aposta. 
	 * @return o valor do seguro da aposta. 
	 */
	public int getValorAssegurado() {
		return seguro.getValor();
	}
	
	/**
	 * Altera um seguro de taxa para valor.
	 * @param valor o valor do seguro. 
	 * @return true se for alterado ou falso em caso contrário .
	 */
	public boolean setSeguro(int valor) {
		if (this.seguro instanceof SeguroTaxa) {
			this.seguro = new SeguroValor(valor);
			return true;
		}
		return false;
	}
	
	/**
	 * Altera um seguro de valor para taxa
	 * @param taxa a  taxa do seguro. 
	 * @return true se for alterado ou falso em caso contrário .
	 */
	public boolean setSeguro(double taxa) {
		if (this.seguro instanceof SeguroValor) {
			this.seguro = new SeguroTaxa(taxa, this.valor);
			return true;
		}
		return false;
	}
	
	/**
	 * Método responsável pela representação em String de uma aposta. A representação segue o modelo 
	 * "NOME - R$ VALOR - PREVISAO". No caso de apostas assegurada o modelo é 
	 * "NOME - R$ VALOR - PREVISAO - ASSEGURADA (VALOR) - R$ XX,XX" ou 
	 * "NOME - R$ VALOR - PREVISAO - ASSEGURADA (TAXA) - X%"
	 * 
	 * @return a representação em String de uma aposta.
	 */
	@Override
	public String toString() {
		return this.apostador + " - " +  valorEmReais() + " - " + 
				(this.previsao?"VAI ACONTECER" : "N VAI ACONTECER") + seguro.toString(); 
	}
}
