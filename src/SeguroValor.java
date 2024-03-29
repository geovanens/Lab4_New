import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * Classe representa um seguro do tipo valor, extensão de Seguro
 * @author Geovane do Nascimento Silva - 116211149
 */
public class SeguroValor extends Seguro{

	/**
	 * Contrutor do seguro por valor. 
	 * @param valor o valor do seguro. 
	 */
	public SeguroValor(int valor) {
		super(valor);
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
	@Override
	public int getValor() {
		return this.valor;
	}
	
	/**
	 * Complemtento para o toString da aposta. retorna string no modelo 
	 * " - ASSEGURADA (VALOR) - R$ XX,XX"
	 */
	@Override
	public String toString() {
		return " - ASSEGURADA(VALOR) - " + valorEmReais();
	}
}
