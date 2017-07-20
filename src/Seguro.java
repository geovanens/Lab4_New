import java.math.BigDecimal;
import java.text.NumberFormat;

public class Seguro {
	protected int valor;
	protected String tipo;
	protected double taxa;
	
	public Seguro() {
		this.tipo = "Sem seguro";
		this.valor = 0;
	}
	
	public Seguro(int valor) {
		this.tipo = "VALOR";
		this.valor = valor;
	}
	
	public Seguro(double taxa, int valorAposta) {
		this.tipo = "TAXA";
		this.taxa = taxa;
		this.valor = (int) (taxa * valorAposta);
	}

	private int seguroPercent() {
	    int percent = (int) (taxa * 100);
	    return percent;
	}

	private String valorEmReais() {
		double valorEmReais = this.valor / 100.0;
		BigDecimal valor = new BigDecimal (valorEmReais);  
		NumberFormat nf = NumberFormat.getCurrencyInstance();  
		String formatado = nf.format (valor);
		return formatado;
	}

	public int getValor() {
		return this.valor;
	}
	
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


	public void setSeguro(int valor) {
		this.tipo = "VALOR";
		this.valor = valor;	
	}
	
	public void setSeguro(double taxa, int valorAposta) {
		this.tipo = "TAXA";
		this.taxa = taxa;	
		this.valor = (int) (taxa * valorAposta);
	}
}
