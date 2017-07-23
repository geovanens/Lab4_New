import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Testes da classe Aposta. 
 * @author Geovane do Nascimento Silva - 116211149
 *
 */
public class ApostaTest {

	/**
	 * Testa o contrutor de uma aposta sem seguro a partir do toString. 
	 */
	@Test
	public void testApostaSemSeguro() {
		Aposta aposta = new Aposta("Geovane", 1000, true);
		assertEquals("Geovane - R$ 10,00 - VAI ACONTECER", aposta.toString());
	}

	/**
	 * Testa o contrutor de uma aposta com seguro de valor a partir do toString. 
	 */
	@Test
	public void testApostaSeguroValor() {
		Aposta aposta = new Aposta("Geovane", 1000, true, 500);
		assertEquals("Geovane - R$ 10,00 - VAI ACONTECER - ASSEGURADA(VALOR) - R$ 5,00", aposta.toString());
	}

	/**
	 * Testa o contrutor de uma aposta com seguro de taxa a partir do toString. 
	 */
	@Test
	public void testApostaSeguroTaxa() {
		Aposta aposta = new Aposta("Geovane", 1000, true, 0.2);
		assertEquals("Geovane - R$ 10,00 - VAI ACONTECER - ASSEGURADA(TAXA) - 20%", aposta.toString());
	}

	/**
	 * Testa o método getNome para os 3 tipos de apostas do sistema. 
	 */
	@Test
	public void testGetNome() {
		Aposta aposta1 = new Aposta("Geovane", 1000, true);
		Aposta aposta2 = new Aposta("Anonimo", 1000, true, 500);
		Aposta aposta3 = new Aposta("Fulano", 1000, true, 0.2);
		assertEquals("Geovane", aposta1.getNome());
		assertEquals("Anonimo", aposta2.getNome());
		assertEquals("Fulano", aposta3.getNome());
		
	}

	/**
	 * Testa o método getValor para os 3 tipos de apostas do sistema. 
	 */
	@Test
	public void testGetValor() {
		Aposta aposta1 = new Aposta("Geovane", 1000, true);
		Aposta aposta2 = new Aposta("Anonimo", 2000, true, 500);
		Aposta aposta3 = new Aposta("Fulano", 3000, true, 0.2);
		assertEquals(1000, aposta1.getValor());
		assertEquals(2000, aposta2.getValor());
		assertEquals(3000, aposta3.getValor());
	}

	/**
	 * Testa o método isPrevisao para os 3 tipos de apostas do sistema. 
	 */
	@Test
	public void testIsPrevisao() {
		Aposta aposta1 = new Aposta("Geovane", 1000, true);
		Aposta aposta2 = new Aposta("Anonimo", 2000, false, 500);
		Aposta aposta3 = new Aposta("Fulano", 3000, true, 0.2);
		assertTrue(aposta1.isPrevisao());
		assertFalse(aposta2.isPrevisao());
		assertTrue(aposta3.isPrevisao());
	}

	/**
	 * Testa o método getValorAssegurado para os 3 tipos de apostas do sistema. 
	 */
	@Test
	public void testGetValorAssegurado() {
		Aposta aposta1 = new Aposta("Geovane", 1000, true);
		Aposta aposta2 = new Aposta("Anonimo", 2000, false, 500);
		Aposta aposta3 = new Aposta("Fulano", 3000, true, 0.2);
		assertEquals(0, aposta1.getValorAssegurado());
		assertEquals(500, aposta2.getValorAssegurado());
		assertEquals(600, aposta3.getValorAssegurado());
	}

	/**
	 * Testa o metodo setSeguroValor.
	 */
	@Test
	public void testSetSeguroValor() {
		Aposta aposta = new Aposta("Fulano", 3000, true, 0.2);
		aposta.setSeguro(500);
		assertEquals(500, aposta.getValorAssegurado());
	}

	/**
	 * Testa o metodo setSeguroTaxa.
	 */
	@Test
	public void testSetSeguroTaxa() {
		Aposta aposta = new Aposta("Anonimo", 2000, false, 500);
		aposta.setSeguro(0.5);
		assertEquals(1000, aposta.getValorAssegurado());
	}

	/**
	 * Testa o toString para os 3 tipos de apostas do sistema. 
	 */
	@Test
	public void testToString() {
		Aposta aposta1 = new Aposta("Geovane", 1000, true);
		Aposta aposta2 = new Aposta("Anon", 20000, false, 500);
		Aposta aposta3 = new Aposta("Fulano", 300000, true, 0.2);
		
		assertEquals("Geovane - R$ 10,00 - VAI ACONTECER", aposta1.toString());
		assertEquals("Anon - R$ 200,00 - N VAI ACONTECER - ASSEGURADA(VALOR) - R$ 5,00", aposta2.toString());
		assertEquals("Fulano - R$ 3.000,00 - VAI ACONTECER - ASSEGURADA(TAXA) - 20%", aposta3.toString());
	}

}
