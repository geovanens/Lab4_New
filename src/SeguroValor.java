import java.math.BigDecimal;
import java.text.NumberFormat;

public class SeguroValor extends Seguro{

	public SeguroValor(int valor) {
		this.valor = valor;
	}

	private String valorEmReais() {
		double valorEmReais = this.valor / 100.0;
		BigDecimal valor = new BigDecimal (valorEmReais);  
		NumberFormat nf = NumberFormat.getCurrencyInstance();  
		String formatado = nf.format (valor);
		return formatado;
	}
	
	@Override
	public String toString() {
		return " - ASSEGURADA(VALOR) - " + valorEmReais();
	}

}
