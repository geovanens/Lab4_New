/**
 * Enum responsável por armazenar as constantes de status de um cenário. 
 * @author Geovane do Nascimento Silva - 116211149
 *
 */
public enum Status {
	NAO_FINALIZADO("Nao finalizado"),
	OCORREU("Finalizado (ocorreu)"),
	NAO_OCORREU("Finalizado (nao ocorreu)");
	private final String valor;
	
	/**
	 * 
	 * @param valor valor a ser associado a constante. 
	 */
	Status(String valor) {
		this.valor = valor;
	}
	
	/**
	 * 
	 * @return o valor em String de uma constante. 
	 */
	public String getValor() {
		return valor;
	}
}