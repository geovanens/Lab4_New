import org.junit.Before;
import org.junit.Test;

/**
 * Testes da classe Validacoes. 
 * @author Geovane do Nascimento Silva - 116211149
 *
 */
public class ValidacoesTest {
	
	Controller control = new Controller();
	
	/**
	 * Cadastra um cenário e apostas para os testes de exceções. 
	 */
	@Before
	public void inicializa() {
		control.cadastrarCenario("VOU");
		control.cadastrarAposta(1, "EU", 10, "VAI ACONTECER", 5, 2);
		control.cadastrarAposta(1, "EU", 10, "VAI ACONTECER", 0.2, 2);
	}

	/**
	 * Testa se ao inicializar o sistema com valor negativo a exceção é lançada. 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testInicializacaoInvalidaCaixaInvalido() {
		control.inicializaSistema(-1, 0.1);
	}
	
	/**
	 * Testa se ao inicializar o sistema com taxa negativa a exceção é lançada. 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testInicializacaoInvalidaTaxaInvalida() {
		control.inicializaSistema(0, -1);
	}

	/**
	 * Testa se ao cadastra um cenario com descrição vazia a exceção é lançada. 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testDescricaoVazia() {
		control.cadastrarCenario("");
	}
	
	/**
	 * Testa se ao cadastra um cenario bonus com descrição vazia a exceção é lançada. 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testDescricaoVaziaBonus() {
		control.cadastrarCenario("", 100);
	}
	
	/**
	 * Testa se ao cadastra um cenario com descrição composta de espaços a exceção é lançada. 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testDescricaoEspacos() {
		control.cadastrarCenario("    ");
	}
	
	/**
	 * Testa se ao cadastra um cenario bonus com descrição composta de espaços a exceção é lançada. 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testDescricaoEspacosBonus() {
		control.cadastrarCenario("    ", 100);
	}

	/**
	 * Testa se ao cadastra uma aposta com apostador vazio exceção é lançada. 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testApostaInvalidaApostadorVazio() {
		control.cadastrarAposta(1, "", 10, "VAI ACONTECER");
	}
	
	/**
	 * Testa se ao cadastra uma aposta com apostador composto de espaços a exceção é lançada. 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testApostaInvalidaApostadorNulo() {
		control.cadastrarAposta(1, "   ", 10, "VAI ACONTECER");
	}
	
	/**
	 * Testa se ao cadastra uma aposta com valor invalido a exceção é lançada. 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testApostaInvalidaValorInvalido() {
		control.cadastrarAposta(1, "EU", 0, "VAI ACONTECER");
	}
	
	/**
	 * Testa se ao cadastra uma aposta com previsao vazia a exceção é lançada. 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testApostaInvalidaPrevisaoNula() {
		control.cadastrarAposta(1, "EU", 10, "");
	}
	
	/**
	 * Testa se ao cadastra uma aposta com previsao inválida a exceção é lançada. 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testApostaInvalidaPrevisaoInvalida() {
		control.cadastrarAposta(1, "EU", 10, "EM CIMA DO MURO");
	}
	
	/**
	 * Testa se ao cadastra uma aposta em um cenario invalido a exceção é lançada. 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCenarioInvalido() {
		control.cadastrarAposta(0, "EU", 10, "VAI ACONTECER");
	}
	
	/**
	 * Testa se ao cadastra uma aposta em um cenario não cadastrado a exceção é lançada. 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCenarioNaoCadastrado() {
		control.cadastrarAposta(2, "EU", 10, "VAI ACONTECER");
	}

	/**
	 * Testa se ao tentar fechar um cenário já fechado a exceção é lançada. 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCenarioFechado() {
		control.fecharAposta(1, true);
		control.fecharAposta(1, true);
	}

	/**
	 * Testa se ao tentar pegar o valor destinado ao caixa do sistema em um 
	 * cenário que ainda está aberto a exceção é lançada. 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCenarioAberto1() {
		control.getCaixaCenario(1);
	}
	
	/**
	 * Testa se ao tentar pegar o valor destinado aos vencedores em um 
	 * cenário que ainda está aberto a exceção é lançada. 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCenarioAberto2() {
		control.getTotalRateioCenario(1);
	}

	/**
	 * Testa se ao tentar cadastrar um cenário bonus com bonus inválido a exceção é lançada. 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testBonus() {
		control.cadastrarCenario("IAPOI", 0);
	}

	/**
	 * Testa se ao tentar cadastrar uma aposta assegurada por valor com valor inválido a exceção é lançada. 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testSeguroInvalidoValorInvalido() {
		control.cadastrarAposta(1, "EU", 10, "VAI ACONTECER",0, 2);
	}
	
	/**
	 * Testa se ao tentar cadastrar uma aposta assegurada por valor com custo inválido a exceção é lançada. 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testSeguroInvalidoCustoInvalido() {
		control.cadastrarAposta(1, "EU", 10, "VAI ACONTECER", 5, 0);
	}

	/**
	 * Testa se ao tentar cadastrar uma aposta assegurada por taxa com taxa inválido a exceção é lançada. 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testSeguroInvalidoTaxaInvalida() {
		control.cadastrarAposta(1, "EU", 10, "VAI ACONTECER", 0, 5);
	}
	
	/**
	 * Testa se ao tentar cadastrar uma aposta assegurada por taxa com custo inválido a exceção é lançada. 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testSeguroInvalidoTaxaCustoInvalido() {
		control.cadastrarAposta(1, "EU", 10, "VAI ACONTECER", 0.02, 0);
	}

	/**
	 * Testa se ao tentar alterar o tipo de uma aposta assegurada nao cadastrada a exceção é lançada. 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testNaoContemAposta() {
		control.alteraSeguroValor(2, 1, 100);
	}

	/**
	 * Testa se ao tentar alterar o tipo de seguro de uma aposta com id inválido a exceção é lançada. 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testAlteracaoValorInvalidaIdInvalido() {
		control.alteraSeguroValor(1, 0, 10);
	}
	
	/**
	 * Testa se ao tentar alterar o tipo de seguro de uma aposta com valor inválido a exceção é lançada. 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testAlteracaoValorInvalidaValorInvalido() {
		control.alteraSeguroValor(1, 1, 0);
	}

	/**
	 * Testa se ao tentar alterar o tipo de seguro de uma aposta para taxa com id inválido a exceção é lançada. 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testAlteracaoTaxaInvalidaIdInvalido() {
		control.alteraSeguroTaxa(1, 0, 0.2);
	}
	
	/**
	 * Testa se ao tentar alterar o tipo de seguro de uma aposta para taxa com taxa inválido a exceção é lançada. 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testAlteracaoTaxaInvalidaTaxaInvalida() {
		control.alteraSeguroTaxa(1, 2, 0);
	}

}
