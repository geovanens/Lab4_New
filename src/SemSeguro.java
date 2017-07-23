
public class SemSeguro extends Seguro {

	/**
	 * Contrutor para seguro generico, sem valor.
	 */
	public SemSeguro() {
		super(0);
	}
	
	@Override
	public int getValor() {
		return 0;
	}

	@Override
	public String toString() {
		return "";
	}
}
