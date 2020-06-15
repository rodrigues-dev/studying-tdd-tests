package br.com.caelum.leilao.dominio;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LeilaoTest {

	@Test
	public void deveReceberUmLance() {
		Leilao leilao = new Leilao("Mackbook");
		assertEquals(0, leilao.getLances().size());
		
		leilao.propoe(new Lance(new Usuario("Steve Jobs"), 1500.0));
		assertEquals(1, leilao.getLances().size());
		assertEquals(1500.0, leilao.getLances().get(0).getValor(), 0.00001);
	}
	
	@Test
	public void deveReceberVariosLances() {
		Leilao leilao = new Leilao("Mackbook");
		leilao.propoe(new Lance(new Usuario("Steve Jobs"), 1500.0));
		leilao.propoe(new Lance(new Usuario("Steve Wosniak"), 2000.0));
		
		assertEquals(2, leilao.getLances().size());
		assertEquals(1500.0, leilao.getLances().get(0).getValor(), 0.00001);
		assertEquals(2000.0, leilao.getLances().get(1).getValor(), 0.00001);
	}
	
	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
		Leilao leilao = new Leilao("Carro");
		Usuario victor = new Usuario("Victor");
		
		leilao.propoe(new Lance(victor, 7000.0));
		leilao.propoe(new Lance(victor, 7500.0));
		
		assertEquals(1, leilao.getLances().size());
		assertEquals(7000.0, leilao.getLances().get(0).getValor(), 0.00001);
	}
	
	@Test
	public void naoDeveAceitarMaisQue5LancesDoMesmoUsuario() {
		Leilao leilao = new Leilao("Iphone");
		Usuario victor = new Usuario("Victor");
		Usuario marcelo = new Usuario("Marcelo");
		
		leilao.propoe(new Lance(victor, 1000.0));
		leilao.propoe(new Lance(marcelo, 3000.0));

		leilao.propoe(new Lance(victor, 2000.0));
		leilao.propoe(new Lance(marcelo, 4000.0));

		leilao.propoe(new Lance(victor, 1500.0));
		leilao.propoe(new Lance(marcelo, 5000.0));

		leilao.propoe(new Lance(victor, 1550.0));
		leilao.propoe(new Lance(marcelo, 1300.0));

		leilao.propoe(new Lance(victor, 6000.0));
		leilao.propoe(new Lance(marcelo, 7000.0));
		
		leilao.propoe(new Lance(victor, 10000.0));
		
		assertEquals(10, leilao.getLances().size());
		assertEquals(7000.0, leilao.getLances().get(leilao.getLances().size()-1).getValor(), 0.00001);
	}
	
	@Test
	public void deveDobrarOUltimoLance() {
		Leilao leilao = new Leilao("Bicicleta");
		Usuario victor = new Usuario("Victor");
		Usuario marcelo = new Usuario("Marcelo");
		
		leilao.propoe(new Lance(victor, 100.0));
		leilao.propoe(new Lance(marcelo, 150.0));
		leilao.dobraLance(victor);
		
		assertEquals(3, leilao.getLances().size());
		assertEquals(100.0*2, leilao.getLances().get(leilao.getLances().size()-1).getValor(), 0.00001);
	}
	
	@Test
	public void naoDeveDobrarCasoNaoHajaLanceAnterior() {
		Leilao leilao = new Leilao("Bicicleta");
		Usuario victor = new Usuario("Victor");
		
		leilao.dobraLance(victor);
		
		assertEquals(0, leilao.getLances().size());
	}
	
	@Test
	public void naoDeveDobrarCasoNaoHajaLanceAnteriorComLista() {
		Leilao leilao = new Leilao("Bicicleta");
		Usuario victor = new Usuario("Victor");
		Usuario marcelo = new Usuario("Marcelo");
		
		leilao.propoe(new Lance(marcelo, 100.0));
		
		leilao.dobraLance(victor);
		
		assertEquals(1, leilao.getLances().size());
	}
	
}
