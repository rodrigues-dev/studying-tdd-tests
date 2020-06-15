package br.com.caelum.leilao.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leilao {

	private String descricao;
	private List<Lance> lances;
	
	public Leilao(String descricao) {
		this.descricao = descricao;
		this.lances = new ArrayList<Lance>();
	}
	
	public void propoe(Lance lance) {
		if (validaLance(lance)) {
			lances.add(lance);	
		}
	}

	private boolean validaLance(Lance lance) {
		return lances.isEmpty() || podeDarLance(lance.getUsuario());
	}
	
	public void dobraLance(Usuario usuario) {
		Lance ultimo = ultimoLanceDado(usuario);
		if (ultimo != null)
			propoe(new Lance(usuario, ultimo.getValor()*2));
		
	}

	private Lance ultimoLanceDado(Usuario usuario) {
		Lance ultimo = null;
		
		for (Lance l : lances) 
			if (l.getUsuario().equals(usuario)) 
				ultimo = l;
		return ultimo;
	}

	private boolean podeDarLance(Usuario usuario) {
		return !ultimoLanceDado().getUsuario().equals(usuario) && qtdDeLancesDo(usuario) < 5;
	}

	private int qtdDeLancesDo(Usuario usuario) {
		int total = 0;
		for (Lance l : lances) 
			if (l.getUsuario().equals(usuario)) total++;
		return total;
	}

	private Lance ultimoLanceDado() {
		return lances.get(lances.size()-1);
	}

	public String getDescricao() {
		return descricao;
	}

	public List<Lance> getLances() {
		return Collections.unmodifiableList(lances);
	}

	
	
}
