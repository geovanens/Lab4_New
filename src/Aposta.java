import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * Classe responsável pelas apostas que são cadastradas nos possíveis cenários
 * 
 * @author Geovane do Nascimento Silva - 116211149
 *
 */
public class Aposta {
	protected String apostador;
	protected int valor;
	protected boolean previsao;
	protected Seguro seguro;

	/**
	 * Método Contrutor da de uma aposta sem seguro.
	 * @param apostador o nome do apostador.
	 * @param valor o valor a ser apostado.
	 * @param previsao a previsao informando se o cenário irá ou não acontecer.
	 */
	public Aposta(String apostador, int valor, boolean previsao) {
		this.apostador = apostador;
		this.valor = valor;
		this.previsao = previsao;
		this.seguro = new Seguro();
	}
	
	public Aposta(String apostador, int valor, boolean previsao, int valorAssegurado) {
		this.apostador = apostador;
		this.valor = valor;
		this.previsao = previsao;
		this.seguro = new SeguroValor(valorAssegurado);
	}

	public Aposta(String apostador, int valor, boolean previsao, double taxaAssegurada) {
		this.apostador = apostador;
		this.valor = valor;
		this.previsao = previsao;
		this.seguro = new SeguroTaxa(taxaAssegurada, valor);
	}

	/**
	 * Método responsável por transformar o valor de centavos em reais.
	 * @return formatado o valor em reais. 
	 */
	public String valorEmReais() {
		double valorEmReais = this.valor / 100.0;
		BigDecimal valor = new BigDecimal (valorEmReais);  
		NumberFormat nf = NumberFormat.getCurrencyInstance();  
		String formatado = nf.format (valor);
		return formatado;
	}
	
	/**
	 * Método responsável pela representação em String de uma aposta. A representação segue o modelo 
	 * "NOME - R$ VALOR - PREVISAO"
	 * 
	 * @return a representação em String de uma aposta.
	 */
	@Override
	public String toString() {
		return this.apostador + " - " +  valorEmReais() + " - " + 
				(this.previsao?"VAI ACONTECER" : "N VAI ACONTECER") + seguro.toString(); 
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
	
	public int getValorAssegurado() {
		return seguro.getValor();
	}
	
	public void setSeguro(int valor) {
		this.seguro = new SeguroValor(valor);
	}
	
	public void setSeguro(double taxa) {
		this.seguro = new SeguroTaxa(taxa, this.valor);
	}
}