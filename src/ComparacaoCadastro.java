import java.util.Comparator;

public class ComparacaoCadastro implements Comparator<Cenario> {

	@Override
	public int compare(Cenario o1, Cenario o2) {
		return o1.getId() - o2.getId();
	}

}
