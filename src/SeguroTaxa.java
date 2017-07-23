/**
 * Classe representa um seguro do tipo taxa
 * @author Geovane do Nascimento Silva - 116211149
 *
 */
public class SeguroTaxa extends Seguro{
	private double taxa;
	
	/**
	 * Contrutor do seguro por taxa. 
	 * @param taxa a taxa do seguro. 
	 * @param valorAposta o valor da aposta a qual o seguro pertence. 
	 */
	public SeguroTaxa(double taxa, int valorAposta) {
		super(taxa, valorAposta);
		this.taxa = taxa;
	}

	/**
	 * Método auxiliar que transforma a taxa em percentual. 
	 * @return o valor em porcentagem que a taxa representa.
	 */
	private int seguroPercent() {
	    int percent = (int) (this.taxa * 100);
	    return percent;
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
	 * " - ASSEGURADA (TAXA) - X%"
	 */
	@Override
	public String toString() {
		return " - ASSEGURADA(TAXA) - " + seguroPercent() + "%";
	}
}
