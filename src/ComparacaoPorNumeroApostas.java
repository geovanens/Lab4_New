import java.util.Comparator;

public class ComparacaoPorNumeroApostas implements Comparator<Cenario> {

	@Override
	public int compare(Cenario o1, Cenario o2) {
		System.out.println(o1 + " " + o1.totalApostas());
		System.out.println(o2 + " " + o2.totalApostas());
		if (o1.totalApostas() - o2.totalApostas() != 0) {
			System.out.println(o1 + " " + o1.totalApostas());
			System.out.println(o2 + " " + o2.totalApostas());
			return o1.totalApostas() - o2.totalApostas();
		}
		else {
			return o1.getId() - o2.getId();
		}
	}

}
