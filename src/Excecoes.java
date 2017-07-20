/**
 * Classe responsável pela verificação e  de parametros de métodos que serão usados na classe SystemController. 
 * Em caso de algum parametro considerado inválido uma "IllegalArgumentException" é lançada. 
 * @author Geovane do Nascimento Silva - 116211149
 *
 */
public class Excecoes {
	
	/**
	 * Verifica os parametros de inicialização do sistema. 
	 * @param caixa o valor de caixa a ser verificado. Este valor nao pode ser menor que zero. 
	 * @param taxa valor de taxa a ser virifcada. Este valor nao pode ser menor que zero. 
	 */
	public void inicializacaoInvalida(int caixa, double taxa) {
		if (caixa < 0) {
			throw new IllegalArgumentException("Erro na inicializacao: Caixa nao pode ser inferior a 0");
		}
		if (taxa < 0) {
			throw new IllegalArgumentException("Erro na inicializacao: Taxa nao pode ser inferior a 0");
		}
	}

	/**
	 * Verifica o parametro de descrição de um cenário. 
	 * @param descricao a descrição a ser verificada. Uma descrição composta apenas de espaços, ou vazia é considerada inválida. 
	 */
	public void descricaoVazia(String descricao) {
		if (descricao.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro no cadastro de cenario: Descricao nao pode ser vazia");
		}
	}

	/**
	 * Verifica os parametros para o cadastro de uma aposta.
	 * @param cenario o id cenario a ser verificado. Este valor não pode ser menor ou igual a zero. 
	 * @param containsKey identifica se o cenario está cadastrado no sistema. 
	 * @param apostador o nome do apostador. Um apostador composta apenas de espaços, ou vazio é considerada inválido. 
	 * @param valor o valor da aposta. Este valor não pode ser menor ou igual a zero.
	 * @param previsao a previsao da aposta. Uma previsao composta apenas de espaços, ou vazia é considerada inválida. 
	 * A previsao também não pode ser diferente de "VAI ACONTECER" ou "N VAI ACONTECER". 
	 * @param msg a mensagem que identifica qual o metodo do sistema que esta em execução. 
	 */
	public void apostaInvalida(int cenario, boolean containsKey,  String apostador, int valor, String previsao, String msg) {
		cenarioInvalido(cenario, containsKey, msg);
		
		if (apostador.trim().isEmpty()) {
			throw new IllegalArgumentException(msg + "Apostador nao pode ser vazio ou nulo");
		}
		else if (valor <= 0) {
			throw new IllegalArgumentException(msg + "Valor nao pode ser menor ou igual a zero");
		}
		else if (previsao.trim().isEmpty()) {
			throw new IllegalArgumentException(msg + "Previsao nao pode ser vazia ou nula");
		}
		else if ((!previsao.equals("VAI ACONTECER")) && (!previsao.equals("N VAI ACONTECER"))) {
			throw new IllegalArgumentException(msg + "Previsao invalida");
		}
	}

	/**
	 * Verifica os parametros referentes a um cenário. Usado em vários metodos do sistema.
	 * @param cenario o id cenario a ser verificado. Este valor não pode ser menor ou igual a zero. 
	 * @param containsKey identifica se o cenario está cadastrado no sistema. 
	 * @param msg a mensagem que identifica qual o metodo do sistema que esta em execução. 
	 */
	public void cenarioInvalido(int cenario, boolean containsKey, String msg) {
		if (cenario <= 0) {
			throw new IllegalArgumentException(msg + "Cenario invalido");
		}
		else if (!containsKey) {
			throw new IllegalArgumentException(msg + "Cenario nao cadastrado");
		}
		
	}

	/**
	 * Verificado se um cenário já está fechado. 
	 * @param fechado identifica se o cenário está fechado ou não. 
	 * @param msg a mensagem que identifica qual o metodo do sistema que esta em execução. 
	 */
	public void cenarioFechado(boolean fechado, String msg) {
		if (fechado) {
			throw new IllegalArgumentException(msg + "Cenario ja esta fechado");
		}
	}

	/**
	 * Verifica se um cenário ainda está aberto.
	 * @param aberto identifica se o cenário está aberto. 
	 * @param msg a mensagem que identifica qual o metodo do sistema que esta em execução. 
	 */
	public void cenarioAberto(boolean aberto, String msg) {
		if (aberto) {
			throw new IllegalArgumentException(msg + "Cenario ainda esta aberto");
		}
		
	}

	/**
	 * Verifica o bonus para o cadastro de um cenário bônus. 
	 * @param bonus o bonus a ser verificado. Este valor não pode ser menor ou igual a zero. 
	 */
	public void bonus(int bonus) {
		if (bonus <= 0) {
			throw new IllegalArgumentException("Erro no cadastro de cenario: Bonus invalido");
		}
	}

	/**
	 * Verifica o valor assegurado por uma aposta e o custo da aposta.
	 * @param valorAssegurado o valor assegurado pela aposta a ser verificado. Este valor não pode ser menor ou igual a zero. 
	 * @param custo o custo da aposta a ser verificado. Este valor não pode ser menor ou igual a zero. 
	 */
	public void seguroInvalido(int valorAssegurado, int custo) {
		if (valorAssegurado <= 0) {
			throw new IllegalArgumentException("Erro no cadastro de aposta: Valor assegurado não pode ser menor ou igual a zero");
		}
		else if (custo <= 0) {
			throw new IllegalArgumentException("Erro no cadastro de aposta: Custo não pode ser menor ou igual a zero");
		}
	}
	
	/**
	 * Verifica o valor assegurado por uma aposta e o custo da aposta.
	 * @param taxaAssegurada a taxa assegurada pela aposta a ser verificado. Este valor não pode ser menor ou igual a zero. 
	 * @param custo o custo da aposta a ser verificado. Este valor não pode ser menor ou igual a zero. 
	 */
	public void seguroInvalido(double taxaAssegurada, int custo) {
		if (taxaAssegurada <= 0) {
			throw new IllegalArgumentException("Erro no cadastro de aposta: Taxa não pode ser menor ou igual a zero");
		}
		else if (custo <= 0) {
			throw new IllegalArgumentException("Erro no cadastro de aposta: Custo não pode ser menor ou igual a zero");
		}
	}

	/**
	 * Verifica se uma  aposta assegurada está cadastrada no cenário. 
	 * @param contem identifica se a aposta assegurada está cadastada.
	 */
	public void naoContemAposta(boolean contem) {
		if(!contem) {
			throw new IllegalArgumentException("Erro na alteracao da aposta: Cenario nao Cadastrado");
		}
		
	}

	/**
	 * Verifica os parametros para alteração de um cenário. 
	 * @param cenario o id cenario a ser verificado. Este valor não pode ser menor ou igual a zero. 
	 * @param containsKey identifica se o cenario está cadastrado no sistema. 
	 * @param apostaAssegurada o id da aposta assegurada. Este valor não pode ser menor ou igual a zero. 
	 * @param valor o valor da aposta a ser verificado. Este valor não pode ser menor ou igual a zero. 
	 */
	public void alteracaoInvalida(int cenario, boolean containsKey, int apostaAssegurada, int valor) {
		if (cenario <= 0) {
			throw new IllegalArgumentException("Erro na alteracao de aposta: Cenario invalido");
		}
		else if (!containsKey) {
			throw new IllegalArgumentException("Erro na alteracao de aposta: Cenario nao cadastrado");
		}
		else if (apostaAssegurada <= 0) {
			throw new IllegalArgumentException("Erro na alteracao de aposta: Aposta invalida");
		}
		else if (valor <= 0) {
			throw new IllegalArgumentException("Erro na alteracao de aposta: Valor nao poder ser menor ou igual a zero");
		}
		
	}

	/**
	 * Verifica os parametros para alteração de um cenário. 
	 * @param cenario o id cenario a ser verificado. Este valor não pode ser menor ou igual a zero. 
	 * @param containsKey identifica se o cenario está cadastrado no sistema. 
	 * @param apostaAssegurada o id da aposta assegurada. Este valor não pode ser menor ou igual a zero. 
	 * @param taxa a taxa da aposta a ser verificado. Este valor não pode ser menor ou igual a zero. 
	 */
	public void alteracaoInvalida(int cenario, boolean containsKey, int apostaAssegurada, double taxa) {
		if (cenario <= 0) {
			throw new IllegalArgumentException("Erro na alteracao de aposta: Cenario invalido");
		}
		else if (!containsKey) {
			throw new IllegalArgumentException("Erro na alteracao de aposta: Cenario nao cadastrado");
		}
		else if (apostaAssegurada <= 0) {
			throw new IllegalArgumentException("Erro na alteracao de aposta: Aposta invalida");
		}
		else if (taxa <= 0) {
			throw new IllegalArgumentException("Erro na alteracao de aposta: Taxa n poder ser menor ou igual a 0");
		}
	}
}