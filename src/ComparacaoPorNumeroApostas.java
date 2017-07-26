import java.util.Comparator;

public class ComparacaoPorNumeroApostas implements Comparator<Cenario> {

	@Override
	public int compare(Cenario o1, Cenario o2) {
		return o1.totalApostas() - o2.totalApostas();
	}

}
