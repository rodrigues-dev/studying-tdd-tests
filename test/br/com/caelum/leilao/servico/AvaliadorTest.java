package br.com.caelum.leilao.servico;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class AvaliadorTest {

	@Test
	public void encontraMiorEMenorLance() {
		// parte 1: cenario
		Usuario joao = new Usuario("João");
		Usuario jose = new Usuario("Jose");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("Playstation");

		leilao.propoe(new Lance(joao, 250.0));
		leilao.propoe(new Lance(jose, 300.0));
		leilao.propoe(new Lance(maria, 400.0));

		// parte 2: acao
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		// parte 3: avaliacao
		double maiorEsperado = 400.0;
		double menorEsperado = 250.0;

		assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);

	}

	@Test
	public void encontrarAMediaDeTresLances() {
		// parte 1: cenario
		Usuario joao = new Usuario("João");
		Usuario jose = new Usuario("Jose");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("Playstation");

		leilao.propoe(new Lance(joao, 200.0));
		leilao.propoe(new Lance(jose, 300.0));
		leilao.propoe(new Lance(maria, 400.0));

		// parte 2: acao
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		// parte 3: avaliacao
		double media = 300.0;

		assertEquals(media, leiloeiro.getMedia(), 0.00001);

	}
	
	@Test
	public void leilaoComUmLance() {
		// parte 1: cenario
		Usuario joao = new Usuario("João");
		Leilao leilao = new Leilao("Playstation");

		leilao.propoe(new Lance(joao, 200.0));

		// parte 2: acao
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		// parte 3: avaliacao
		assertEquals(200.0, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(200.0, leiloeiro.getMenorLance(), 0.00001);
	}

	@Test
	public void deveEncontrarOsTresMaioresLances() {
		// parte 1: cenario
		Usuario joao = new Usuario("João");
		Usuario jose = new Usuario("Jose");
		Leilao leilao = new Leilao("TV");
		
		leilao.propoe(new Lance(joao, 100.0));
		leilao.propoe(new Lance(jose, 400.0));
		leilao.propoe(new Lance(joao, 300.0));
		leilao.propoe(new Lance(jose, 500.0));
		
		Avaliador leiloeiro = new Avaliador();
		
		leiloeiro.avalia(leilao);
		
		List<Lance> maiores = leiloeiro.getMaioresTresLances();
		
		assertEquals(3, maiores.size());
		assertEquals(500.0, maiores.get(0).getValor(), 0.00001);
		assertEquals(400.0, maiores.get(1).getValor(), 0.00001);
		assertEquals(300.0, maiores.get(2).getValor(), 0.00001);
	}
	
	@Test
	public void deveDevolverListaVaziaCasoNaoHajaLances () {
		Leilao leilao = new Leilao("Carro");
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		List<Lance> maiores = leiloeiro.getMaioresTresLances();
		
		assertEquals(0,  maiores.size());
	}
	
	@Test
	public void deveDevolverTodosLancesCasoNaoHajaNoMinimo3 () {
		Usuario joao = new Usuario("joao");
		Leilao leilao = new Leilao("Celular");
		
		leilao.propoe(new Lance(joao, 100.0));
		leilao.propoe(new Lance(joao, 200.0));
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		List<Lance> maiores = leiloeiro.getMaioresTresLances();
		
		assertEquals(2, maiores.size());
		assertEquals(200, maiores.get(0).getValor(), 0.00001);
		assertEquals(100, maiores.get(1).getValor(), 0.00001);
	}

}
