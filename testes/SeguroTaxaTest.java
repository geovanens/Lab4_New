import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Testes da classe SeguroTaxa. 
 * @author Geovane do Nascimento Silva - 116211149
 *
 */
public class SeguroTaxaTest {
	
	Seguro seguroTaxa = new SeguroTaxa(0.1, 1000);

	/**
	 * Testa se o valor do seguro é igual o inicializado. 
	 */
	@Test
	public void testGetValor() {
		assertEquals(100, seguroTaxa.getValor());
	}

	/**
	 * Testa o toString do seguroTaxa
	 */
	@Test
	public void testToString() {
		assertEquals(" - ASSEGURADA(TAXA) - 10%", seguroTaxa.toString());
	}
}
