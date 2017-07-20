
public class Main {

	public static void main(String[] args) {
		Facade facada = new Facade();
		facada.inicializa(99210, 0.01);
		facada.cadastrarCenario("a");
		facada.cadastrarCenario("b");
		facada.cadastrarCenario("c");
		facada.cadastrarCenario("d");
		facada.cadastrarCenario("Este cenario tem seguro");
		facada.cadastrarCenario("Este cenario tambem tem seguro", 1000);
		System.out.println(facada.getCaixa());
		
		facada.cadastrarAposta(6, "Matheus Melanio" , 101 , "N VAI ACONTECER");
		facada.cadastrarAposta(6, "Matheus gaudino" , 201 , "VAI ACONTECER");
		facada.cadastrarAposta(6, "Matheus antunis" , 301 , "N VAI ACONTECER");
		facada.cadastrarAposta(6, "Matheus mathias" , 401 , "VAI ACONTECER");
		System.out.println(facada.getCaixa());
		
		int id1 = facada.cadastrarApostaSeguraValor(6 ,"Antonio Seguro1", 501, "VAI ACONTECER", 200, 50);
		System.out.println(facada.getCaixa());
		int id2 = facada.cadastrarApostaSeguraTaxa(6, "Antonio Seguro2", 501, "VAI ACONTECER", 0.02, 40);
		System.out.println(facada.getCaixa());
//		System.out.println(facada.exibirApostas(6));
		System.out.println(facada.exibirApostas(6));
		System.out.println();
		facada.alterarSeguroValor(6,id2, 100); 
		facada.alterarSeguroTaxa(6, id1 ,0.02);
		facada.fecharAposta(6, false);
		System.out.println(facada.getCaixaCenario(6)); // 98300 + 16 = 98316
		System.out.println(facada.getTotalRateioCenario(6));
		System.out.println(facada.getCaixa());
		System.out.println(facada.exibirApostas(6));
	}

}