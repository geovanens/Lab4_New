/**
 * Classe representa um seguro do SemSeguro, extensão de Seguro
 * @author Geovane do Nascimento Silva - 116211149
 */
public class SemSeguro extends Seguro {

	/**
	 * Contrutor para seguro generico, sem valor.
	 */
	public SemSeguro() {
		super(0);
	}
	
	/**
	 * Pega o valor do seguro. 
	 * @return o valor do seguro, sempre 0 nesse caso. 
	 */
	@Override
	public int getValor() {
		return 0;
	}

	/**
	 * Retorna string vazia.
	 */
	@Override
	public String toString() {
		return "";
	}
}
