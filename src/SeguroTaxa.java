
public class SeguroTaxa extends Seguro{
	private double taxa;
	public SeguroTaxa(double taxa, int valorAposta) {
		this.taxa = taxa;
		this.valor = (int) (taxa * valorAposta);
	}

	private int seguroPercent() {
	    int percent = (int) (taxa * 100);
	    return percent;
	}
	@Override
	public String toString() {
		return " - ASSEGURADA (TAXA) - " + seguroPercent() + "%";
	}
}
