package br.com.caelum.leilao.servico;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Usuario;

public class FiltroDeLancesTest {

	@Test
	public void deveSelecionarLancesEntre1000E3000() {
		Usuario joao = new Usuario("Joao");

		FiltroDeLances filtro = new FiltroDeLances();
		List<Lance> resultado = filtro.filtra(Arrays.asList(
				new Lance(joao, 2000), new Lance(joao, 1000),
				new Lance(joao, 3000), new Lance(joao, 800)));

		assertEquals(1, resultado.size());
		assertEquals(2000, resultado.get(0).getValor(), 0.00001);
	}

	@Test
	public void deveSelecionarLancesEntre500E700() {
		Usuario joao = new Usuario("Joao");

		FiltroDeLances filtro = new FiltroDeLances();
		List<Lance> resultado = filtro.filtra(
				Arrays.asList(
						new Lance(joao, 600), 
						new Lance(joao, 500), 
						new Lance(joao, 700), 
						new Lance(joao, 800)));

		assertEquals(1, resultado.size());
		assertEquals(600, resultado.get(0).getValor(), 0.00001);
	}

	@Test
	public void deveSelecionarLancesMaioresQue5000() {
		Usuario joao = new Usuario("joao");

		FiltroDeLances filtro = new FiltroDeLances();

		List<Lance> resultado = filtro.filtra(Arrays.asList(
				new Lance(joao, 800.0), 
				new Lance(joao, 5100.0)));

		assertEquals(1, resultado.size());
		assertEquals(5100.0, resultado.get(0).getValor(), 0.00001);
	}

	@Test
	public void deveEliminarLancesMenoresDe500() {
		Usuario joao = new Usuario("joao");

		FiltroDeLances filtro = new FiltroDeLances();

		List<Lance> resultado = filtro.filtra(Arrays.asList(
				new Lance(joao, 400.0), 
				new Lance(joao, 500.0)));

		assertEquals(0, resultado.size());
	}
	
	@Test
	public void deveEliminarLancesEntre700E1000() {
		Usuario joao = new Usuario("joao");

		FiltroDeLances filtro = new FiltroDeLances();

		List<Lance> resultado = filtro.filtra(Arrays.asList(
				new Lance(joao, 700.0), 
				new Lance(joao, 701.0),
				new Lance(joao, 1000.0), 
				new Lance(joao, 900.0)));

		assertEquals(0, resultado.size());
	}
	
	@Test
	public void deveEliminarLancesEntre3000E5000() {
		Usuario joao = new Usuario("joao");

		FiltroDeLances filtro = new FiltroDeLances();

		List<Lance> resultado = filtro.filtra(Arrays.asList(
				new Lance(joao, 3001.0), 
				new Lance(joao, 4000.0),
				new Lance(joao, 4999.0)));
		
		assertEquals(0, resultado.size());
	}

}
