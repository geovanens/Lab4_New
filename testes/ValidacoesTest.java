import org.junit.Before;
import org.junit.Test;

public class ValidacoesTest {
	
	Controller control = new Controller();
	@Before
	public void inicializa() {
		control.cadastrarCenario("VOU");
		control.cadastrarAposta(1, "EU", 10, "VAI ACONTECER", 5, 2);
		control.cadastrarAposta(1, "EU", 10, "VAI ACONTECER", 0.2, 2);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testInicializacaoInvalidaCaixaInvalido() {
		control.inicializaSistema(-1, 0.1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInicializacaoInvalidaTaxaInvalida() {
		control.inicializaSistema(0, -1);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testDescricaoVazia() {
		control.cadastrarCenario("");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testDescricaoVaziaBonus() {
		control.cadastrarCenario("", 100);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testDescricaoEspacos() {
		control.cadastrarCenario("    ");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testDescricaoEspacosBonus() {
		control.cadastrarCenario("    ", 100);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testApostaInvalidaApostadorVazio() {
		control.cadastrarAposta(1, "", 10, "VAI ACONTECER");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testApostaInvalidaApostadorNulo() {
		control.cadastrarAposta(1, "   ", 10, "VAI ACONTECER");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testApostaInvalidaValorInvalido() {
		control.cadastrarAposta(1, "EU", 0, "VAI ACONTECER");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testApostaInvalidaPrevisaoNula() {
		control.cadastrarAposta(1, "", 10, "");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testApostaInvalidaPrevisaoInvalida() {
		control.cadastrarAposta(1, "", 10, "EM CIMA DO MURO");
	}
	

	@Test(expected=IllegalArgumentException.class)
	public void testCenarioInvalido() {
		control.cadastrarAposta(0, "EU", 10, "VAI ACONTECER");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCenarioNaoCadastrado() {
		control.cadastrarAposta(2, "EU", 10, "VAI ACONTECER");
	}

	@Test(expected=IllegalArgumentException.class)
	public void testCenarioFechado() {
		control.fecharAposta(1, true);
		control.fecharAposta(1, true);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testCenarioAberto() {
		control.getCaixaCenario(1);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testBonus() {
		control.cadastrarCenario("IAPOI", 0);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSeguroInvalidoValorInvalido() {
		control.cadastrarAposta(1, "EU", 0, "VAI ACONTECER", 5, 2);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSeguroInvalidoCustoInvalido() {
		control.cadastrarAposta(1, "EU", 10, "VAI ACONTECER", 5, 0);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSeguroInvalidoTaxaInvalida() {
		control.cadastrarAposta(1, "EU", 10, "VAI ACONTECER", 0, 5);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSeguroInvalidoTaxaCustoInvalido() {
		control.cadastrarAposta(1, "EU", 10, "VAI ACONTECER", 0.02, 0);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testNaoContemAposta() {
		control.alteraSeguroValor(2, 1, 100);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testAlteracaoValorInvalidaIdInvalido() {
		control.alteraSeguroValor(1, 0, 10);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAlteracaoValorInvalidaValorInvalido() {
		control.alteraSeguroValor(1, 1, 0);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testAlteracaoTaxaInvalidaIdInvalido() {
		control.alteraSeguroTaxa(1, 0, 0.2);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAlteracaoTaxaInvalidaTaxaInvalida() {
		control.alteraSeguroTaxa(1, 2, 0);
	}

}
