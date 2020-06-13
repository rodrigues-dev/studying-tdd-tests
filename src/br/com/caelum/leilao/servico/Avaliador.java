package br.com.caelum.leilao.servico;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

public class Avaliador {

	private double maiorDeTodos = Double.NEGATIVE_INFINITY;
	private double menorDeTodos = Double.POSITIVE_INFINITY;
	private double media = 0;
	private List<Lance> maioresLances;

	public void avalia(Leilao leilao) {
		for (Lance lance : leilao.getLances()) {
			if (lance.getValor() > maiorDeTodos)
				maiorDeTodos = lance.getValor();
			if (lance.getValor() < menorDeTodos)
				menorDeTodos = lance.getValor();
			media = media + lance.getValor();
		}
		media = media / leilao.getLances().size();

		pegaOsMaioresNo(leilao);
	}

	private void pegaOsMaioresNo(Leilao leilao) {
		maioresLances = new ArrayList<Lance>(leilao.getLances());

		maioresLances.sort((Lance o1, Lance o2) -> {
			if (o1.getValor() < o2.getValor())
				return 1;
			if (o1.getValor() > o2.getValor())
				return -1;
			return 0;
		});
		maioresLances = maioresLances.subList(0, maioresLances.size() > 3 ? 3 : maioresLances.size());
	}

	public List<Lance> getMaioresTresLances() {
		return maioresLances;
	}

	public double getMedia() {
		return media;
	}

	public double getMaiorLance() {
		return maiorDeTodos;
	}

	public double getMenorLance() {
		return menorDeTodos;
	}

}
