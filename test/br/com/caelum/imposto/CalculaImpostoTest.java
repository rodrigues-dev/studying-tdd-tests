package br.com.caelum.imposto;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.caelum.leilao.dominio.NotaFiscal;

public class CalculaImpostoTest {

	@Test
	public void deveCalcularImpostoParaValoresMaioresDe2000() {
		CalculaImposto operacao = new CalculaImposto();
		NotaFiscal nf = new NotaFiscal(2001.0);
		
		assertEquals(2001.0*0.03, operacao.calculaImposto(nf), 0.00001);
	}
	
	@Test
	public void deveCalcularImpostoParaValoresMenoresDe2000() {
		CalculaImposto operacao = new CalculaImposto();
		NotaFiscal nf = new NotaFiscal(2000.0);
		
		assertEquals(2000.0*0.02, operacao.calculaImposto(nf), 0.00001);
	}
	
}
