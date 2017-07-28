import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Testes da classe Controller 
 * @author Geovane do Nascimento Silva - 116211149
 *  *
 */
public class ControllerTest {
	
	private final String NL = System.lineSeparator();
	private Controller control = new Controller();
	
	/**
	 * Inicializa sistema, cadastra cenarios basicos e apostas basicas. 
	 */
	@Before
	public void inicializa() {
		control.inicializaSistema(15000, 0.5);
		control.cadastrarCenario("EU VOU!");
		control.cadastrarCenario("EU NÃO VOU");
		control.cadastrarAposta(1, "Geovane", 15000, "VAI ACONTECER");
		control.cadastrarAposta(1, "anonimo", 1000, "VAI ACONTECER");
		control.cadastrarAposta(1, "Spider", 5000, "N VAI ACONTECER");
		assertEquals(21000, control.valorApostas(1));
	}

	/**
	 * Verifica se o getCaixa incial está conforme o inicializado. 
	 */
	@Test
	public void testInicializaSistema() {
		assertEquals(15000, control.getCaixa());
	}

	/**
	 * testa se o id de retorno de um cenário cadastrado. 
	 */
	@Test
	public void testCadastrarCenario() {
		assertEquals(3, control.cadastrarCenario("TU VAI!"));
		assertEquals(4, control.cadastrarCenario("TU NÃO VAI!"));
	}
	
	/**
	 * testa se o id de retorno de um cenário bônus cadastrado. 
	 */
	@Test
	public void testCadastrarCenarioBonus() {
		assertEquals(3, control.cadastrarCenario("TU VAI!", 100));
		assertEquals(4, control.cadastrarCenario("TU NÃO VAI!", 200));
	}
	
	/**
	 * Compara se o numero total de apostas é igual ao esperado, confirmando o cadastro. 
	 */
	@Test
	public void testCadastrarAposta() {
		assertEquals(3, control.totalApostas(1));
	}
	
	/**
	 * Testa se o id retornado pelo cadastro de uma aposta segura por valor é igual ao esperado. 
	 */
	@Test
	public void testCadastrarApostaSeguraValor() {
		assertEquals(1, control.cadastrarAposta(2, "geo", 100000, "VAI ACONTECER", 2500, 500));
		assertEquals(2, control.cadastrarAposta(2, "anonimo", 200000, "N VAI ACONTECER", 2500, 500));
	}
	
	/**
	 * Testa se o id retornado pelo cadastro de uma aposta segura por taxa é igual ao esperado. 
	 */
	@Test
	public void testCadastrarApostaSeguraTaxa() {
		assertEquals(1, control.cadastrarAposta(2, "geo", 100000, "VAI ACONTECER", 0.3, 500));
		assertEquals(2, control.cadastrarAposta(2, "anonimo", 200000, "N VAI ACONTECER", 0.5, 500));
	}

	/**
	 * Testa se o retorno do metodo de exibição de um cenário é igual ao esperado. 
	 */
	@Test
	public void testExibirCenario() {
		assertEquals("1 - EU VOU! - Nao finalizado", control.exibirCenario(1));
	}
	
	/**
	 * Testa se o retorno do método de exibição de um cenário é igual ao esperado para um cenário bônus. 
	 */
	@Test
	public void testExibirCenarioBonus() {
		control.cadastrarCenario("EU VOU", 1000);
		assertEquals("3 - EU VOU - Nao finalizado - R$ 10,00", control.exibirCenario(3));
	}

	/**
	 * Testa se o retorno do método de exibição de todos os cenários é igual ao esperado. 
	 */
	@Test
	public void testExibirCenarios() {
		String cenarios = "1 - EU VOU! - Nao finalizado" + NL + "2 - EU NÃO VOU - Nao finalizado" + NL;
		assertEquals(cenarios, control.exibirCenarios());
		control.cadastrarCenario("NÓS VAMOS", 1000);
		String cenarios2 = "1 - EU VOU! - Nao finalizado" + NL + "2 - EU NÃO VOU - Nao finalizado" + NL
				+ "3 - NÓS VAMOS - Nao finalizado - R$ 10,00" + NL;
		assertEquals(cenarios2, control.exibirCenarios());
	}

	/**
	 * Testa o valor total de apostas cadastradas em um cenário. 
	 */
	@Test
	public void testValorTotalDeApostas() {
		assertEquals(21000, control.valorApostas(1));
		assertEquals(0, control.valorApostas(2));
	}

	/**
	 * Testa o numero total de apostas cadastradas em um cenário. 
	 */
	@Test
	public void testTotalDeApostas() {
		assertEquals(3, control.totalApostas(1));
		assertEquals(0, control.totalApostas(2));
	}

	/**
	 * Testa se o retorno do método de exibição de apostas é igual ao esperado. 
	 */
	@Test
	public void testExibirApostas() {
		String apostas = "Geovane - R$ 150,00 - VAI ACONTECER" + NL 
				+ "anonimo - R$ 10,00 - VAI ACONTECER" + NL
				+ "Spider - R$ 50,00 - N VAI ACONTECER" + NL;
		
		assertEquals(apostas, control.exibirApostas(1));
	}

	/**
	 * Testa o meétodo que retorna o valor destinado ao caixa do sistema.
	 */
	@Test
	public void testGetCaixaCenario() {
		control.fecharAposta(1, false);
		assertEquals(8000, control.getCaixaCenario(1));
	}
	
	/**
	 * Testa o meétodo que retorna o valor destinado aos apostadores vencedores. 
	 */
	@Test
	public void testGetTotalRateioCenario() {
		control.fecharAposta(1, false);
		assertEquals(8000, control.getTotalRateioCenario(1));
	}
	
	/**
	 * Testa o meétodo que retorna o valor destinado aos apostadores vencedores em um cenário bônus. 
	 */
	@Test
	public void testGetTotalRateioCenarioBonus() {
		control.cadastrarCenario("EU VOU", 1000);
		control.cadastrarAposta(3, "Geovane", 15000, "VAI ACONTECER");
		control.cadastrarAposta(3, "anonimo", 1000, "VAI ACONTECER");
		control.fecharAposta(3, false);
		assertEquals(9000, control.getTotalRateioCenario(3));
		
		
		control.cadastrarCenario("TU VAI", 1000);
		control.cadastrarAposta(4, "Geovane", 3500, "VAI ACONTECER");
		control.cadastrarAposta(4, "anonimo", 5300, "VAI ACONTECER");
		control.fecharAposta(4, true);
		assertEquals(1000, control.getTotalRateioCenario(4));
		
	}
	
	/**
	 * Testa se o retorno do metodo de alteração de seguro de tipo valor para taxa é igual ao esperado. 
	 */
	@Test
	public void testAlterarSegurValor() {
		control.cadastrarAposta(2, "geo", 100000, "VAI ACONTECER", 2500, 500);
		control.cadastrarAposta(2, "anonimo", 200000, "N VAI ACONTECER", 2500, 500);
		assertEquals(1, control.alteraSeguroTaxa(2, 1, 0.4));
		assertEquals(2, control.alteraSeguroTaxa(2, 2, 0.4));
		
	}
	
	/**
	 * Testa se o retorno do metodo de alteração de seguro de tipo taxa para  valor  é igual ao esperado. 
	 */
	@Test
	public void testAlterarSegurTaxa() {
		control.cadastrarAposta(2, "geo", 100000, "VAI ACONTECER", 0.3, 500);
		control.cadastrarAposta(2, "anonimo", 200000, "N VAI ACONTECER", 0.5, 500);
		assertEquals(1, control.alteraSeguroValor(2, 1, 1500));
		assertEquals(2, control.alteraSeguroValor(2, 2, 2000));
	}

	/**
	 * Testa se o valor do getCaixa é conforme o esperado após o fechamento de um cenário. 
	 */
	@Test
	public void testGetCaixa() {
		control.fecharAposta(1, false);
		assertEquals(23000, control.getCaixa());
	}
	
	/**
	 * Testa se o retorno do getTaxa é conforme o esperado. 
	 */
	@Test
	public void testGetTaxa() {
		control.fecharAposta(1, false);
		assertEquals(0.5, control.getTaxa(), 0.0);
	}
	
	/**
	 * Testa se o valor do getCaixa após o cadastro de cenários bônus. 
	 */
	@Test
	public void testGetCaixaAposCadastroBonus() {
		control.cadastrarCenario("TU VAI!", 100);
		control.cadastrarCenario("TU NÃO VAI!", 200);
		assertEquals(14700, control.getCaixa());
	}
	
	/**
	 * Testa se o valor do getCaixa após o fechamento de um cenário.  
	 */
	@Test
	public void testFecharAposta() {
		control.fecharAposta(1, true);
		assertEquals(17500, control.getCaixa());
	}
	
	/**
	 * Testa o metodo de ordenar os cenarios para o caso de ordenação por ordem de cadastro. 
	 */
	@Test
	public void testAlterarOrdemCadastro() {
		control.alterarOrdem("cadastro");
		assertEquals("1 - EU VOU! - Nao finalizado", control.exibirCenarioOrdenado(1));
		assertEquals("2 - EU NÃO VOU - Nao finalizado", control.exibirCenarioOrdenado(2));
	}
	
	/**
	 * Testa o metodo de ordenar os cenarios para o caso de ordenação por ordem de nome.
	 */
	@Test
	public void testAlterarOrdemNome() {
		control.alterarOrdem("nome");
		assertEquals("2 - EU NÃO VOU - Nao finalizado", control.exibirCenarioOrdenado(1));
		assertEquals("1 - EU VOU! - Nao finalizado", control.exibirCenarioOrdenado(2));
	}
	
	/**
	 * Testa o metodo de ordenar os cenarios para o caso de ordenação por ordem de total de apostas do cenario.
	 */
	@Test
	public void testAlterarOrdemApostas() {
		control.alterarOrdem("apostas");
		assertEquals("1 - EU VOU! - Nao finalizado", control.exibirCenarioOrdenado(1));
		assertEquals("2 - EU NÃO VOU - Nao finalizado", control.exibirCenarioOrdenado(2));
	}
	
	/**
	 * Testa o metodo de ordenar os cenarios para o caso de ordenação por ordem de total de apostas do cenario 
	 * em caso de o total de apostas ser igual. 
	 */
	@Test
	public void testAlterarOrdemApostasIguais() {
		control.cadastrarAposta(2, "EU", 10, "VAI ACONTECER");
		control.cadastrarAposta(2, "TU", 20, "VAI ACONTECER");
		control.cadastrarAposta(2, "ELE", 30, "VAI ACONTECER");
		control.alterarOrdem("apostas");
		assertEquals("1 - EU VOU! - Nao finalizado", control.exibirCenarioOrdenado(1));
		assertEquals("2 - EU NÃO VOU - Nao finalizado", control.exibirCenarioOrdenado(2));
	}
	
	/**
	 * Testa o metodo de exibir um cenario ordenado com um parametro invalido, exceção é esperada
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testExibirCenarioOrdenadoInvalido() {
		control.exibirCenarioOrdenado(0);
	}
	
	/**
	 * Testa o metodo de exibir um cenario ordenado com um cenario nao cadastrado, exceção é esperada
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testExibirCenarioOrdenadoNaoCadastrado() {
		control.exibirCenarioOrdenado(3);
	}
}