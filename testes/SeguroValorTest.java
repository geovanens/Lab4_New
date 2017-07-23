import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Testes da classe SeguroValor. 
 * @author Geovane do Nascimento Silva - 116211149
 *
 */
public class SeguroValorTest {

	Seguro seguroValor = new SeguroValor(1000);

	/**
	 * Testa se o valor do seguro é igual o inicializado. 
	 */
	@Test
	public void testGetValor() {
		assertEquals(1000, seguroValor.getValor());
	}

	/**
	 * Testa o toString do seguroValor
	 */
	@Test
	public void testToString() {
		assertEquals(" - ASSEGURADA(VALOR) - R$ 10,00", seguroValor.toString());
	}
}
