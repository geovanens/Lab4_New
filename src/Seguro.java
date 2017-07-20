import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * Classe responsavél por representar um seguro de uma aposta.
 * @author Geovane do Nascimento Silva - 116211149
 *
 */
public class Seguro {
	protected int valor;
	protected String tipo;
	protected double taxa;
	
	/**
	 * Contrutor para seguro generico, sem valor.
	 */
	public Seguro() {
		this.tipo = "Sem seguro";
		this.valor = 0;
	}
	
	/**
	 * Contrutor para seguro por valor
	 * @param valor o valor do seguro
	 */
	public Seguro(int valor) {
		this.tipo = "VALOR";
		this.valor = valor;
	}
	
	/**
	 * Construtor para seguro por taxa. 
	 * @param taxa a taxa do seguro. 
	 * @param valorAposta o valor da aposta a qual o seguro pertence.
	 */
	public Seguro(double taxa, int valorAposta) {
		this.tipo = "TAXA";
		this.taxa = taxa;
		this.valor = (int) (taxa * valorAposta);
	}

	/**
	 * Método auxiliar que transforma a taxa em percentual. 
	 * @return o valor em porcentagem que a taxa representa.
	 */
	private int seguroPercent() {
	    int percent = (int) (taxa * 100);
	    return percent;
	}

	/**
	 * Método auxiliar para tranformar o valor do seguro em Reais.
	 * @return o valor em reais de um seguro. 
	 */
	private String valorEmReais() {
		double valorEmReais = this.valor / 100.0;
		BigDecimal valor = new BigDecimal (valorEmReais);  
		NumberFormat nf = NumberFormat.getCurrencyInstance();  
		String formatado = nf.format (valor);
		return formatado;
	}

	/**
	 * Pega o valor do seguro. 
	 * @return o valor do seguro. 
	 */
	public int getValor() {
		return this.valor;
	}
	
	/**
	 * Altera o tipo do seguro de taxa para valor e altera seu valor.
	 * @param valor o valor do seguro. 
	 */
	public void setSeguro(int valor) {
		this.tipo = "VALOR";
		this.valor = valor;	
	}
	
	/**
	 * Altera o tipo do seguro de valor para taxa e altera a taxa e o valor. 
	 * @param taxa a taxa do seguro. 
	 * @param valorAposta o valor da aposta a qual o seguro pertence. 
	 */
	public void setSeguro(double taxa, int valorAposta) {
		this.tipo = "TAXA";
		this.taxa = taxa;	
		this.valor = (int) (taxa * valorAposta);
	}
	
	/**
	 * Complemtento para o toString da aposta. 
	 */
	@Override
	public String toString() {
		if(this.tipo.equals("VALOR")) {
			return " - ASSEGURADA(VALOR) - " + valorEmReais();
		}
		else if (this.tipo.equals("TAXA")) {
			return " - ASSEGURADA (TAXA) - " + seguroPercent() + "%";
		}
		return "";
	}
}
