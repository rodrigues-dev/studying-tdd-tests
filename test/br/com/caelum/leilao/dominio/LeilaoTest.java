package br.com.caelum.leilao.dominio;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.builder.CriadorDeLeilao;

public class LeilaoTest {

	private Usuario victor;
	private Usuario marcelo;

	@Before
	public void setUp() {
		this.victor = new Usuario("Victor");
		this.marcelo = new Usuario("Marcelo");
	}
	
	@Test
	public void deveReceberUmLance() {
//		Leilao leilao = new Leilao("Mackbook");
		
		Leilao leilao = new CriadorDeLeilao().para("Macbook")
				.lance(victor, 1500.0)
				.constroi();
				
//		assertEquals(0, leilao.getLances().size());
		
		assertEquals(1, leilao.getLances().size());
		assertEquals(1500.0, leilao.getLances().get(0).getValor(), 0.00001);
	}
	
	@Test
	public void deveReceberVariosLances() {
		Leilao leilao = new CriadorDeLeilao().para("Macbook")
				.lance(marcelo, 1500.0)
				.lance(victor, 2000.0)
				.constroi();
		
		assertEquals(2, leilao.getLances().size());
		assertEquals(1500.0, leilao.getLances().get(0).getValor(), 0.00001);
		assertEquals(2000.0, leilao.getLances().get(1).getValor(), 0.00001);
	}
	
	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
		Leilao leilao = new CriadorDeLeilao().para("Carro")
				.lance(victor, 7000.0)
				.lance(victor, 7500.0)
				.constroi();
		
		assertEquals(1, leilao.getLances().size());
		assertEquals(7000.0, leilao.getLances().get(0).getValor(), 0.00001);
	}
	
	@Test
	public void naoDeveAceitarMaisQue5LancesDoMesmoUsuario() {
		Leilao leilao = new CriadorDeLeilao().para("Iphone")
				.lance(victor, 1000.0)
				.lance(marcelo, 3000.0)
				.lance(victor, 2000.0)
				.lance(marcelo, 4000.0)
				.lance(victor, 1500.0)
				.lance(marcelo, 5000.0)
				.lance(victor, 1550.0)
				.lance(marcelo, 1300.0)
				.lance(victor, 6000.0)
				.lance(marcelo, 7000.0)
				.lance(victor, 10000.0)
				.constroi();
		
		assertEquals(10, leilao.getLances().size());
		assertEquals(7000.0, leilao.getLances().get(leilao.getLances().size()-1).getValor(), 0.00001);
	}
	
	@Test
	public void deveDobrarOUltimoLance() {
		Leilao leilao = new CriadorDeLeilao().para("Câmera")
				.lance(victor, 100.0)
				.lance(marcelo, 150.0)
				.dobraLance(victor)
				.constroi();
		
		assertEquals(3, leilao.getLances().size());
		assertEquals(100.0*2, leilao.getLances().get(leilao.getLances().size()-1).getValor(), 0.00001);
	}
	
	@Test
	public void naoDeveDobrarCasoNaoHajaLanceAnterior() {
		Leilao leilao = new CriadorDeLeilao().para("Caixa de Som")
				.dobraLance(victor)
				.constroi();
				
		assertEquals(0, leilao.getLances().size());
	}
	
	@Test
	public void naoDeveDobrarCasoNaoHajaLanceAnteriorComLista() {
		Leilao leilao = new CriadorDeLeilao().para("Cama")
				.lance(marcelo, 100.0)
				.dobraLance(victor)
				.constroi();
		
		assertEquals(1, leilao.getLances().size());
	}
	
}
