import java.util.Comparator;

/**
 * Classe que implementa um comparator de cenarios. 
 * @author Geovane do Nascimento Silva - 116211149
 *
 */
public class ComparacaoPorNumeroApostas implements Comparator<Cenario> {

	/**
	 * Recebe dois cenários e os compara pela quantidade de apostas de cada cenário, caso os dois 
	 * cenários tenha a mesma quantidade de apostas, o cenário com menor identificador é ordenado 
	 * antes do outro. 
	 */
	@Override
	public int compare(Cenario o1, Cenario o2) {
		int result = o2.totalApostas() - o1.totalApostas();
		if (result == 0) {
			return o1.getId() - o2.getId();
		}
		return result;
	}

}
