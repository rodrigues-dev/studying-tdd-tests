package br.com.caelum.leilao.servico;

import static org.junit.Assert.assertEquals;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.caelum.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class AvaliadorTest {

	private Avaliador leiloeiro;
	private Usuario joao;
	private Usuario jose;
	private Usuario maria;

	@Before //executa antes de cada metodo de teste
	public void setUp() {
		this.leiloeiro = new Avaliador();
		this.joao = new Usuario("João");
		this.jose = new Usuario("Jose");
		this.maria = new Usuario("Maria");
	}
	
	/*
	 * Utilizamos métodos @After quando nossos testes consomem 
	 * recursos que precisam ser finalizados. Exemplos podem ser 
	 * testes que acessam banco de dados, abrem arquivos, abrem sockets, e etc.
	 */
	@After //executa ao final de cada metodo de teste
	public void testFinal() {
		System.out.println("fim");
	}
	
	@BeforeClass //executa uma vez antes do primeiro método de teste ser executado
	public static void testandoBeforeClass() {
	  System.out.println("before class");
	}

	@AfterClass //executa uma vez depois do ultimo método de teste ser executado
	public static void testandoAfterClass() {
	  System.out.println("after class");
	}
	
	@Test(expected = RuntimeException.class) // expected: informa ao JUint a classe esperada no teste.
	public void naoDeveAvaliarLeiloesSemNumUmLanceDado() {
		Leilao leilao = new CriadorDeLeilao().para("PSP").constroi();
		leiloeiro.avalia(leilao);
	}
	
	@Test
	public void encontraMaiorEMenorLance() {
		// parte 1: cenario
		Leilao leilao = new CriadorDeLeilao().para("Playstation")
				.lance(joao, 250.0)
				.lance(jose, 300.0)
				.lance(maria, 400.0)
				.constroi();

		// parte 2: acao
		leiloeiro.avalia(leilao);

		// parte 3: avaliacao
		
		//usando Junit
		assertEquals(400.0, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(250.0, leiloeiro.getMenorLance(), 0.00001);
		
		// melhorando a legibilidade com Hamcrest
		assertThat(leiloeiro.getMaiorLance(), equalTo(400.0));
		assertThat(leiloeiro.getMenorLance(), equalTo(250.0));
	}

	@Test
	public void encontrarAMediaDeTresLances() {
		// parte 1: cenario
		Leilao leilao = new CriadorDeLeilao().para("Celular")
				.lance(joao, 200.0)
				.lance(jose, 300.0)
				.lance(maria, 400.0)
				.constroi();
				
		// parte 2: acao
		leiloeiro.avalia(leilao);

		// parte 3: avaliacao
		assertEquals(300.0, leiloeiro.getMedia(), 0.00001);

	}
	
	@Test
	public void leilaoComUmLance() {
		// parte 1: cenario
		Leilao leilao = new CriadorDeLeilao().para("Carro")
				.lance(joao, 2000.0)
				.constroi();
				
		// parte 2: acao
		leiloeiro.avalia(leilao);

		// parte 3: avaliacao
		assertEquals(2000.0, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(2000.0, leiloeiro.getMenorLance(), 0.00001);
	}

	@Test
	public void deveEncontrarOsTresMaioresLances() {
		// parte 1: cenario
		/*
		 * CriadorDeLeilao(): ajuda a criar um cenário de teste.
		 * Esse tipo de classe é chamada de test data builders
		 */
		Leilao leilao = new CriadorDeLeilao().para("TV")
				.lance(joao, 100.0)
				.lance(jose, 400.0)
				.lance(maria, 300.0)
				.lance(joao, 500.0)
				.constroi();
		
		leiloeiro.avalia(leilao);
		
		List<Lance> maiores = leiloeiro.getMaioresTresLances();
		
		assertEquals(3, maiores.size());
		// usando Junit
		assertEquals(500.0, maiores.get(0).getValor(), 0.00001);
		assertEquals(400.0, maiores.get(1).getValor(), 0.00001);
		assertEquals(300.0, maiores.get(2).getValor(), 0.00001);
		
		// melhorando a legibilidade com Hamcrest
		assertThat(maiores, hasItems(
				new Lance(joao, 500.0),
				new Lance(jose, 400.0),
				new Lance(maria, 300.0)
		));
	}
	
//	@Test
//	public void deveDevolverListaVaziaCasoNaoHajaLances () {
//		Leilao leilao = new CriadorDeLeilao().para("Caneta").constroi();
//		
//		leiloeiro.avalia(leilao);
//		
//		List<Lance> maiores = leiloeiro.getMaioresTresLances();
//		
//		assertEquals(0,  maiores.size());
//	}
	
	@Test
	public void deveDevolverTodosLancesCasoNaoHajaNoMinimo3 () {
		Leilao leilao = new CriadorDeLeilao().para("PC Gamer")
				.lance(joao, 100.0)
				.lance(jose, 200.00)
				.constroi();
				
		leiloeiro.avalia(leilao);
		
		List<Lance> maiores = leiloeiro.getMaioresTresLances();
		
		assertEquals(2, maiores.size());
		assertEquals(200, maiores.get(0).getValor(), 0.00001);
		assertEquals(100, maiores.get(1).getValor(), 0.00001);
	}

}
