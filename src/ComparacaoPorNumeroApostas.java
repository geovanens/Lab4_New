import java.util.Comparator;

public class ComparacaoPorNumeroApostas implements Comparator<Cenario> {

	@Override
	public int compare(Cenario o1, Cenario o2) {
		int result = o2.totalApostas() - o1.totalApostas();
		if (result == 0) {
			return o1.getId() - o2.getId();
		}
		return result;
	}

}
