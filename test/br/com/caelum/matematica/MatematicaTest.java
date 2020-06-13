package br.com.caelum.matematica;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MatematicaTest {

	@Test
	public void casoNumeroMaiorQue30 () {
		Matematica matematica = new Matematica();
		assertEquals(31*4, matematica.contaMaluca(31));
	}

	@Test
	public void casoNumeroMenorQue30EMaiorQue10 () {
		Matematica matematica = new Matematica();
		assertEquals(15*3, matematica.contaMaluca(15));
	}

	@Test
	public void casoNumeroMenorQue10 () {
		Matematica matematica = new Matematica();	
		assertEquals(9*2, matematica.contaMaluca(9));
	}
	
	@Test
	public void casoNumeroIgualA30 () {
		Matematica matematica = new Matematica();
		assertEquals(30*3, matematica.contaMaluca(30));
	}
	
	@Test
	public void casoNumeroIgualA10 () {
		Matematica matematica = new Matematica();
		assertEquals(10*2, matematica.contaMaluca(10));
	}

}
