import java.math.BigDecimal;
import java.text.NumberFormat;


/**
 * Classe responsável pelos cenários bônus de apostas. A classe é uma extensão da classe Cenario. 
 * 
 * @author Geovane do Nascimento Silva - 116211149
 *
 */
public class CenarioBonus extends Cenario {
	private int bonus;

	/**
	 * Método contrutor da classe CenarioBonus. 
	 * @param descricao a descrição do cenário
	 * @param id o identificador do cenário
	 * @param bonus o valor bonus do cenario destinado aos vencedores. 
	 */
	public CenarioBonus(String descricao, int id, int bonus) {
		super(descricao, id);
		this.bonus = bonus;
	}
	
	/**
	 * Cacula a quantia de apostas do cenário que será destinado aos vencedores. 
	 * @param getCaixaCenario o valor destinado ao caixa.
	 * @param taxa a taxa de desconto do sistema. 
	 * @return rateio a quantia destinada aos vencedores somado ao bônus do cenário. 
	 */
	@Override
	public int getTotalRateio(int getCaixaCenario, double taxa) {
		int rateio = bonus;
		rateio += (getCaixaCenario / taxa) - getCaixaCenario;
		return rateio;
	}
	
	/**
	 * Método responsável por transformar o valor do bonus de centavos em reais.
	 * @return reais o valor em reais
	 */
	private String bonusEmReais() {
		double valorEmReais = this.bonus / 100.0;
		BigDecimal valor = new BigDecimal (valorEmReais);  
		NumberFormat nf = NumberFormat.getCurrencyInstance();  
		String formatado = nf.format (valor);
		return formatado;
	}
	
	/**
	 * Retorna a representação em String de um cenário bonus. A representação segue o modelo 
	 * "ID - DECRIÇÃO - STATUS - BÔNUS"
	 */
	@Override
	public String toString() {
		return super.toString() +" - " +  bonusEmReais();
	}

}