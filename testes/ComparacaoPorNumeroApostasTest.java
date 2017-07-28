import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Testes do comparator. 
 * @author Geovane do Nascimento Silva - 116211149
 *
 */
public class ComparacaoPorNumeroApostasTest {

	ComparacaoPorNumeroApostas comparador = new ComparacaoPorNumeroApostas();
	Cenario cen1 = new Cenario("EU VOU", 1);
	Cenario cen2 = new Cenario("EU VOU", 1);;
	
	/**
	 * Cadastra apostas nos cenários de testes. 
	 */
	@Before
	public void addApostas() {
		cen1.addAposta("EU", 20, false);
		cen1.addAposta("TU", 15, true);
		
		cen2.addAposta("Geo", 10, true);
	}
	
	/**
	 * Testa o comparator para o caso de cenarios com numeros de apostas diferentes. 
	 */
	@Test
	public void testCompare() {
		assertTrue(comparador.compare(cen1, cen2) < 0);
		assertTrue(comparador.compare(cen2, cen1) > 0);
	}
	
	/**
	 * Testa o comparator para o caso de cenarios com numeros de iguai. 
	 */
	@Test
	public void testCompare2() {
		cen2.addAposta("VC", 30, false);
		assertEquals(comparador.compare(cen1, cen2), 0);
	}

}
