/**
 * Classe responsavél por representar um seguro de uma aposta.
 * @author Geovane do Nascimento Silva - 116211149
 *
 */
public abstract class Seguro {
	protected int valor;
	protected double taxa;
	
	/**
	 * Contrutor para seguro generico, sem valor.
	 */
	public Seguro() {
		this.valor = 0;
	}
	
	/**
	 * Contrutor para seguro por valor
	 * @param valor o valor do seguro
	 */
	public Seguro(int valor) {
		this.valor = valor;
	}
	
	/**
	 * Construtor para seguro por taxa. 
	 * @param taxa a taxa do seguro. 
	 * @param valorAposta o valor da aposta a qual o seguro pertence.
	 */
	public Seguro(double taxa, int valorAposta) {
		this.taxa = taxa;
		this.valor = (int) (taxa * valorAposta);
	}

	/**
	 * Pega o valor do seguro. 
	 * @return o valor do seguro. 
	 */
	public abstract int getValor();
	
	
	/**
	 * Complemtento para o toString da aposta. 
	 */
	@Override
	public abstract String toString();
}
