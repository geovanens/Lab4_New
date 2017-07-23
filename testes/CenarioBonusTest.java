import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CenarioBonusTest {
	
	Cenario cenarioBonus = new CenarioBonus("EU VOU", 1, 1000);
	
	@Before
	public void inicializa() {
		cenarioBonus.addAposta("EU", 1000, true);
		cenarioBonus.addAposta("TU", 2000, false, 100);
		cenarioBonus.addAposta("EU", 3000, true, 0.08);
	}
	
	@Test
	public void testGetTotalRateio() {
		cenarioBonus.fecharAposta(true);
		int paraCaixa = cenarioBonus.getCaixaCenario(0.01);
		assertEquals(2980, cenarioBonus.getTotalRateio(paraCaixa, 0.01));
	}

	@Test
	public void testToString() {
		assertEquals("1 - EU VOU - Nao finalizado - R$ 10,00", cenarioBonus.toString());
	}

}
