import static org.junit.Assert.*;

import org.junit.Test;

public class SeguroTaxaTest {
	
	Seguro seguroTaxa = new SeguroTaxa(0.1, 1000);

	@Test
	public void testGetValor() {
		assertEquals(100, seguroTaxa.getValor());
	}

	@Test
	public void testToString() {
		assertEquals(" - ASSEGURADA(TAXA) - 10%", seguroTaxa.toString());
	}
}
