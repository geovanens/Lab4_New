import static org.junit.Assert.*;

import org.junit.Test;

public class SeguroValorTest {

	Seguro seguroValor = new SeguroValor(1000);

	@Test
	public void testGetValor() {
		assertEquals(1000, seguroValor.getValor());
	}

	@Test
	public void testToString() {
		assertEquals(" - ASSEGURADA(VALOR) - R$ 10,00", seguroValor.toString());
	}
}
