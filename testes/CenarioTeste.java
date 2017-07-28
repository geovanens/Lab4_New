import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Testes da classe Cenario
 * @author Geovane do Nascimento Silva - 116211149
 *
 */
public class CenarioTeste {
	
	private static final String NL = System.lineSeparator();
	
	Cenario cenarioBase = new Cenario("Vou blocar o período", 1);;
	
	/**
	 * Adiciona apostas para os testes. 
	 */
	@Before
	public void adicona() {
		cenarioBase.addAposta("Eu", 10, true);
		cenarioBase.addAposta("Tu", 200, false, 100);
		cenarioBase.addAposta("Ele", 30, false);
		cenarioBase.addAposta("Nós", 40, true);
	}

	/**
	 * Verifica se um cenario está sendo inicializado conforme o esperado. 
	 */
	@Test
	public void testCenario() {
		Cenario cenario = new Cenario("qqer cenario", 1);
		assertEquals("Nao finalizado", cenario.getEstado());
		assertEquals(0, cenario.valorTotalApostas);
	}

	/**
	 * Testa o metodo de exibicao de apostas do cenário. 
	 */
	@Test
	public void testExibirApostas() {
		String esperado = "Eu - R$ 0,10 - VAI ACONTECER" + NL + 
						  "Tu - R$ 2,00 - N VAI ACONTECER - ASSEGURADA(VALOR) - R$ 1,00" + NL + 
						  "Ele - R$ 0,30 - N VAI ACONTECER" + NL + 
						  "Nós - R$ 0,40 - VAI ACONTECER" + NL;
		
		assertEquals(esperado, cenarioBase.exibirApostas());
	}

	/**
	 * Testa o addApostaSemSeguro verificando se o valor total de apostas no cenário é o esperado após o 
	 * cadastro da aposta. 
	 */
	@Test
	public void testAddApostaSemSeguro() {
		cenarioBase.addAposta("Eu", 10, true);
		assertEquals(290, cenarioBase.getValorTotalApostas());
	}

	/**
	 * Testa o addApostaSeguroValor verificando se o id retornado é igual o esperado. 
	 */
	@Test
	public void testAddApostaSeguroValor() {
		assertEquals(5, cenarioBase.addAposta("Eu", 10, true, 100));
	}

	/**
	 * Testa o addApostaSeguroTaxa verificando se o id retornado é igual o esperado. 
	 */
	@Test
	public void testAddApostaStringSeguroTaxa() {
		assertEquals(5, cenarioBase.addAposta("Eu", 10, true, 0.2));
	}

	/**
	 * Testa se a aposta foi alterada para assegurda valor. 
	 */
	@Test
	public void testAlteraSeguroValor() {
		cenarioBase.addAposta("Eu", 10, true);
		cenarioBase.addAposta("Eu", 100, false, 0.2);
		assertTrue(cenarioBase.alteraSeguroValor(5, 10));
	}

	/**
	 * Testa se a aposta foi alterada para assegurda taxa. 
	 */
	@Test
	public void testAlteraSeguroTaxa() {
		assertTrue(cenarioBase.alteraSeguroTaxa(1, 0.2));
	}
	
	/**
	 * Verifica se o valor total de apostas de um cenário é igual ao esperado. 
	 */
	@Test
	public void getValorTotalApostas() {
		assertEquals(280, cenarioBase.getValorTotalApostas());
	}
	
	/**
	 * Verifica se o numero total de apostas de um cenário é igual ao esperado. 
	 */
	@Test
	public void testTotalApostas() {
		cenarioBase.addAposta("Eu", 10, true);
		cenarioBase.addAposta("tu", 200, true, 0.2);
		cenarioBase.addAposta("ele", 1000, true);
		assertEquals(7, cenarioBase.totalApostas());
	}

	/**
	 * Verifica se após o fechamento de um cenário o estado do mesmo é alterado. 
	 */
	@Test
	public void testFecharApostaNOcorreu() {
		cenarioBase.fecharAposta(false);
		assertEquals("Finalizado (n ocorreu)", cenarioBase.getEstado());
	}
	
	/**
	 * Verifica se após o fechamento de um cenário o estado do mesmo é alterado. 
	 */
	@Test
	public void testFecharApostaOcorreu() {
		cenarioBase.fecharAposta(true);
		assertEquals("Finalizado (ocorreu)", cenarioBase.getEstado());
	}

	/**
	 * Testa se o valor que sera destinado ao caixa do sistema é conforme o esperado.  
	 */
	@Test
	public void testGetCaixaCenario() {
		cenarioBase.fecharAposta(true);
		assertEquals(23, cenarioBase.getCaixaCenario(0.1));
	}

	/**
	 * Testa se o valor que sera destinado aos vencedores é conforme o esperado.  
	 */
	@Test
	public void testGetTotalRateio() {
		cenarioBase.fecharAposta(true);
		assertEquals(207, cenarioBase.getTotalRateio(23, 0.1));
	}

	/**
	 * Testa o estado atual de um cenário. 
	 */
	@Test
	public void testGetEstado() {
		assertEquals("Nao finalizado", cenarioBase.getEstado());
	}

	/**
	 * Testa se o retorno de isFechado é o esperado. 
	 */
	@Test
	public void testIsFechado() {
		assertFalse(cenarioBase.isFechado());
	}

	/**
	 * Testa o setFechadp verificando o estado do cenário apos alteração. 
	 */
	@Test
	public void testSetFechado() {
		cenarioBase.setFechado();
		assertTrue(cenarioBase.isFechado());
	}

	/**
	 * Testa o toString do cenário. 
	 */
	@Test
	public void testToString() {
		assertEquals("1 - Vou blocar o período - Nao finalizado", cenarioBase.toString());
	}

	/**
	 * Testa o valor que sera descontado do caixa e destinado ao perdedores de apostas asseguradas. 
	 */
	@Test
	public void testPerdedoresAssegurados() {
		cenarioBase.fecharAposta(true);
		assertEquals(100, cenarioBase.perdedoresAssegurados());
	}

	/**
	 * Testa se uma aposta assegurada esta cadastrada no cenario e tambem uma aposta normal verificando que nao é 
	 * assegurada. 
	 */
	@Test
	public void testContemAssegurada() {
		cenarioBase.addAposta("Tal", 10, false, 0.5);
		assertTrue(cenarioBase.contemAssegurada(1));
		assertTrue(cenarioBase.contemAssegurada(4));
		assertFalse(cenarioBase.contemAssegurada(3));
	}
	
	/**
	 * Testa se o retorno de getDescricao está conforme o esperado. 
	 */
	@Test
	public void testGetDescricao() {
		assertEquals("Vou blocar o período", cenarioBase.getDescricao());
	}
	
	/**
	 * Testa o compareTo para os 3 casos possiveis. 
	 */
	@Test
	public void testCompareTo() {
		Cenario cen1 = new Cenario("EU VOU", 1);
		Cenario cen2 = new Cenario("EU VOU", 2);
		Cenario cen3 = new Cenario("AMANHA EU VOU", 3);
		assertEquals(0, cen1.compareTo(cen2));
		assertTrue(cen3.compareTo(cen1) < 0);
		assertTrue(cen1.compareTo(cen3) > 0);
	}
	
	/**
	 * Testa se o retorno de getId está conforme o esperado. 
	 */
	@Test
	public void testGetId() {
		assertEquals(1, cenarioBase.getId());
	}

}
