import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Testes da classe SemSeguro. 
 * @author Geovane do Nascimento Silva - 116211149
 *
 */
public class SemSeguroTest {

	Seguro semSeguro = new SemSeguro();

	/**
	 * Testa se o valor é 0
	 */
	@Test
	public void testGetValor() {
		assertEquals(0, semSeguro.getValor());
	}

	/**
	 * Testa o toString do SemSeguro
	 */
	@Test
	public void testToString() {
		assertEquals("", semSeguro.toString());
	}

}
