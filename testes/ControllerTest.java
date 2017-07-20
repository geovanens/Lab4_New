import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Testes da classe Facade, como a mesma é uma classe que chama as demais todos as classes são testadas. 
 * @author Geovane do Nascimento Silva - 116211149
 *  *
 */
public class ControllerTest {
	
	private final String NL = System.lineSeparator();
	private Controller facada = new Controller();
	
	/**
	 * Inicializa sistema, cadastra cenarios basicos e apostas basicas. 
	 */
	@Before
	public void inicializa() {
		facada.inicializaSistema(15000, 0.5);
		facada.cadastrarCenario("EU VOU!");
		facada.cadastrarCenario("EU NÃO VOU");
		facada.cadastrarAposta(1, "Geovane", 15000, "VAI ACONTECER");
		facada.cadastrarAposta(1, "anonimo", 1000, "VAI ACONTECER");
		facada.cadastrarAposta(1, "Spider", 5000, "N VAI ACONTECER");
		assertEquals(21000, facada.valorApostas(1));
	}

	/**
	 * Verifica se o getCaixa incial está conforme o inicializado. 
	 */
	@Test
	public void testInicializaSistema() {
		assertEquals(15000, facada.getCaixa());
	}

	/**
	 * testa se o id de retorno de um cenário cadastrado. 
	 */
	@Test
	public void testCadastrarCenario() {
		assertEquals(3, facada.cadastrarCenario("TU VAI!"));
		assertEquals(4, facada.cadastrarCenario("TU NÃO VAI!"));
	}
	
	/**
	 * testa se o id de retorno de um cenário bônus cadastrado. 
	 */
	@Test
	public void testCadastrarCenarioBonus() {
		assertEquals(3, facada.cadastrarCenario("TU VAI!", 100));
		assertEquals(4, facada.cadastrarCenario("TU NÃO VAI!", 200));
	}
	
	/**
	 * Compara se o numero total de apostas é igual ao esperado, confirmando o cadastro. 
	 */
	@Test
	public void testCadastrarAposta() {
		assertEquals(3, facada.totalApostas(1));
	}
	
	/**
	 * Testa se o id retornado pelo cadastro de uma aposta segura por valor é igual ao esperado. 
	 */
	@Test
	public void testCadastrarApostaSeguraValor() {
		assertEquals(1, facada.cadastrarAposta(2, "geo", 100000, "VAI ACONTECER", 2500, 500));
		assertEquals(2, facada.cadastrarAposta(2, "anonimo", 200000, "N VAI ACONTECER", 2500, 500));
	}
	
	/**
	 * Testa se o id retornado pelo cadastro de uma aposta segura por taxa é igual ao esperado. 
	 */
	@Test
	public void testCadastrarApostaSeguraTaxa() {
		assertEquals(1, facada.cadastrarAposta(2, "geo", 100000, "VAI ACONTECER", 0.3, 500));
		assertEquals(2, facada.cadastrarAposta(2, "anonimo", 200000, "N VAI ACONTECER", 0.5, 500));
	}

	/**
	 * Testa se o retorno do metodo de exibição de um cenário é igual ao esperado. 
	 */
	@Test
	public void testExibirCenario() {
		assertEquals("1 - EU VOU! - Nao finalizado", facada.exibirCenario(1));
	}
	
	/**
	 * Testa se o retorno do método de exibição de um cenário é igual ao esperado para um cenário bônus. 
	 */
	@Test
	public void testExibirCenarioBonus() {
		facada.cadastrarCenario("EU VOU", 1000);
		assertEquals("3 - EU VOU - Nao finalizado - R$ 10,00", facada.exibirCenario(3));
	}

	/**
	 * Testa se o retorno do método de exibição de todos os cenários é igual ao esperado. 
	 */
	@Test
	public void testExibirCenarios() {
		String cenarios = "1 - EU VOU! - Nao finalizado" + NL + "2 - EU NÃO VOU - Nao finalizado" + NL;
		assertEquals(cenarios, facada.exibirCenarios());
		facada.cadastrarCenario("NÓS VAMOS", 1000);
		String cenarios2 = "1 - EU VOU! - Nao finalizado" + NL + "2 - EU NÃO VOU - Nao finalizado" + NL
				+ "3 - NÓS VAMOS - Nao finalizado - R$ 10,00" + NL;
		assertEquals(cenarios2, facada.exibirCenarios());
	}

	/**
	 * Testa o valor total de apostas cadastradas em um cenário. 
	 */
	@Test
	public void testValorTotalDeApostas() {
		assertEquals(21000, facada.valorApostas(1));
		assertEquals(0, facada.valorApostas(2));
	}

	/**
	 * Testa o numero total de apostas cadastradas em um cenário. 
	 */
	@Test
	public void testTotalDeApostas() {
		assertEquals(3, facada.totalApostas(1));
		assertEquals(0, facada.totalApostas(2));
	}

	/**
	 * Testa se o retorno do método de exibição de apostas é igual ao esperado. 
	 */
	@Test
	public void testExibirApostas() {
		String apostas = "Geovane - R$ 150,00 - VAI ACONTECER" + NL 
				+ "anonimo - R$ 10,00 - VAI ACONTECER" + NL
				+ "Spider - R$ 50,00 - N VAI ACONTECER" + NL;
		
		assertEquals(apostas, facada.exibirApostas(1));
	}

	/**
	 * Testa o meétodo que retorna o valor destinado ao caixa do sistema.
	 */
	@Test
	public void testGetCaixaCenario() {
		facada.fecharAposta(1, false);
		assertEquals(8000, facada.getCaixaCenario(1));
	}
	
	/**
	 * Testa o meétodo que retorna o valor destinado aos apostadores vencedores. 
	 */
	@Test
	public void testGetTotalRateioCenario() {
		facada.fecharAposta(1, false);
		assertEquals(8000, facada.getTotalRateioCenario(1));
	}
	
	/**
	 * Testa o meétodo que retorna o valor destinado aos apostadores vencedores em um cenário bônus. 
	 */
	@Test
	public void testGetTotalRateioCenarioBonus() {
		facada.cadastrarCenario("EU VOU", 1000);
		facada.cadastrarAposta(3, "Geovane", 15000, "VAI ACONTECER");
		facada.cadastrarAposta(3, "anonimo", 1000, "VAI ACONTECER");
		facada.fecharAposta(3, false);
		assertEquals(9000, facada.getTotalRateioCenario(3));
		
		
		facada.cadastrarCenario("TU VAI", 1000);
		facada.cadastrarAposta(4, "Geovane", 3500, "VAI ACONTECER");
		facada.cadastrarAposta(4, "anonimo", 5300, "VAI ACONTECER");
		facada.fecharAposta(4, true);
		assertEquals(1000, facada.getTotalRateioCenario(4));
		
	}
	
	/**
	 * Testa se o retorno do metodo de alteração de seguro de tipo valor para taxa é igual ao esperado. 
	 */
	@Test
	public void testAlterarSegurValor() {
		facada.cadastrarAposta(2, "geo", 100000, "VAI ACONTECER", 2500, 500);
		facada.cadastrarAposta(2, "anonimo", 200000, "N VAI ACONTECER", 2500, 500);
		assertEquals(1, facada.alteraSeguroTaxa(2, 1, 0.4));
		assertEquals(2, facada.alteraSeguroTaxa(2, 2, 0.4));
		
	}
	
	/**
	 * Testa se o retorno do metodo de alteração de seguro de tipo taxa para  valor  é igual ao esperado. 
	 */
	@Test
	public void testAlterarSegurTaxa() {
		facada.cadastrarAposta(2, "geo", 100000, "VAI ACONTECER", 0.3, 500);
		facada.cadastrarAposta(2, "anonimo", 200000, "N VAI ACONTECER", 0.5, 500);
		assertEquals(1, facada.alteraSeguroValor(2, 1, 1500));
		assertEquals(2, facada.alteraSeguroValor(2, 2, 2000));
	}

	/**
	 * Testa se o valor do getCaixa é conforme o esperado após o fechamento de um cenário. 
	 */
	@Test
	public void testGetCaixa() {
		facada.fecharAposta(1, false);
		assertEquals(23000, facada.getCaixa());
	}
	
	/**
	 * Testa se o retorno do getTaxa é conforme o esperado. 
	 */
	@Test
	public void testGetTaxa() {
		facada.fecharAposta(1, false);
		assertEquals(0.5, facada.getTaxa(), 0.0);
	}
	
	/**
	 * Testa se o valor do getCaixa após o cadastro de cenários bônus. 
	 */
	@Test
	public void testGetCaixaAposCadastroBonus() {
		facada.cadastrarCenario("TU VAI!", 100);
		facada.cadastrarCenario("TU NÃO VAI!", 200);
		assertEquals(14700, facada.getCaixa());
	}
}