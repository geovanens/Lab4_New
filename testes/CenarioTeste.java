import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CenarioTeste {
	
	private static final String NL = System.lineSeparator();
	
	Cenario cenarioBase = new Cenario("Vou blocar o período", 1);;
	
	@Before
	public void inicializa() {
		cenarioBase.addAposta("Eu", 10, true);
		cenarioBase.addAposta("Tu", 200, false, 100);
		cenarioBase.addAposta("Ele", 30, false);
		cenarioBase.addAposta("Nós", 40, true);
	}

	@Test
	public void testCenario() {
		Cenario cenario = new Cenario("qqer cenario", 1);
		assertEquals("Nao finalizado", cenario.getEstado());
		assertEquals(0, cenario.valorTotalApostas);
	}

	@Test
	public void testExibirApostas() {
		String esperado = "Eu - R$ 0,10 - VAI ACONTECER" + NL + 
						  "Tu - R$ 2,00 - N VAI ACONTECER - ASSEGURADA(VALOR) - R$ 1,00" + NL + 
						  "Ele - R$ 0,30 - N VAI ACONTECER" + NL + 
						  "Nós - R$ 0,40 - VAI ACONTECER" + NL;
		
		assertEquals(esperado, cenarioBase.exibirApostas());
	}

	@Test
	public void testAddApostaSemSeguro() {
		cenarioBase.addAposta("Eu", 10, true);
		assertEquals(290, cenarioBase.getValorTotalApostas());
	}

	@Test
	public void testAddApostaSeguroValor() {
		assertEquals(5, cenarioBase.addAposta("Eu", 10, true, 100));
	}

	@Test
	public void testAddApostaStringSeguroTaxa() {
		assertEquals(5, cenarioBase.addAposta("Eu", 10, true, 0.2));
	}

	@Test
	public void testAlteraSeguroValor() {
		cenarioBase.addAposta("Eu", 10, true);
		cenarioBase.addAposta("Eu", 100, false, 0.2);
		assertTrue(cenarioBase.alteraSeguroValor(5, 10));
	}

	@Test
	public void testAlteraSeguroTaxa() {
		assertTrue(cenarioBase.alteraSeguroTaxa(1, 0.2));
	}
	
	@Test
	public void getValorTotalApostas() {
		assertEquals(280, cenarioBase.getValorTotalApostas());
	}
	
	@Test
	public void testTotalApostas() {
		cenarioBase.addAposta("Eu", 10, true);
		cenarioBase.addAposta("tu", 200, true, 0.2);
		cenarioBase.addAposta("ele", 1000, true);
		assertEquals(7, cenarioBase.totalApostas());
	}

	@Test
	public void testFecharAposta() {
		cenarioBase.fecharAposta(false);
		assertEquals("Finalizado (nao ocorreu)", cenarioBase.getEstado());
	}

	@Test
	public void testGetCaixaCenario() {
		cenarioBase.fecharAposta(true);
		assertEquals(23, cenarioBase.getCaixaCenario(0.1));
	}

	@Test
	public void testGetTotalRateio() {
		cenarioBase.fecharAposta(true);
		assertEquals(207, cenarioBase.getTotalRateio(23, 0.1));
	}

	@Test
	public void testGetEstado() {
		assertEquals("Nao finalizado", cenarioBase.getEstado());
	}

	@Test
	public void testIsFechado() {
		assertFalse(cenarioBase.isFechado());
	}

	@Test
	public void testSetFechado() {
		cenarioBase.setFechado();
		assertTrue(cenarioBase.isFechado());
	}

	@Test
	public void testToString() {
		assertEquals("1 - Vou blocar o período - Nao finalizado", cenarioBase.toString());
	}

	@Test
	public void testPerdedoresAssegurados() {
		cenarioBase.fecharAposta(true);
		assertEquals(100, cenarioBase.perdedoresAssegurados());
	}

	@Test
	public void testContemAssegurada() {
		cenarioBase.addAposta("Tal", 10, false, 0.5);
		assertTrue(cenarioBase.contemAssegurada(1));
		assertTrue(cenarioBase.contemAssegurada(4));
		assertFalse(cenarioBase.contemAssegurada(3));
	}

}
