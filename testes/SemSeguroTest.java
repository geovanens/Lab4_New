import static org.junit.Assert.*;

import org.junit.Test;

public class SemSeguroTest {

	Seguro semSeguro = new SemSeguro();

	@Test
	public void testGetValor() {
		assertEquals(0, semSeguro.getValor());
	}

	@Test
	public void testToString() {
		assertEquals("", semSeguro.toString());
	}

}
