package br.com.caelum.imposto;

import br.com.caelum.leilao.dominio.NotaFiscal;

public class CalculaImposto {

	public double calculaImposto(NotaFiscal nf) { 
		  if(nf.getValor() > 2000) return nf.getValor() * 0.03;
		  return nf.getValor() * 0.02;
	}

}
