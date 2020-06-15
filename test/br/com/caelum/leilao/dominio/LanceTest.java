package br.com.caelum.leilao.dominio;

import static org.junit.Assert.assertEquals;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static br.com.caelum.matcher.LeilaoMatcher.temUmLance;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.builder.CriadorDeLeilao;

public class LanceTest {

	private Usuario marcelo;
	private Usuario victor;

	@Before
	public void setUp() {
		this.victor = new Usuario("Victor");
		this.marcelo = new Usuario("Marcelo");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void naoDeveReceberValorIgualAZero() {
		new Lance(victor, 0.0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void naoDeveReceberValorMenorQueAZero() {
		new Lance(marcelo, -1.0);
	}
	
	@Test
    public void deveReceberUmLance() {
        Leilao leilao = new CriadorDeLeilao().para("Macbook Pro 15").constroi();
        assertEquals(0, leilao.getLances().size());

        Lance lance = new Lance(new Usuario("Steve Jobs"), 2000);
        leilao.propoe(lance);

        assertThat(leilao.getLances().size(), equalTo(1));
        assertThat(leilao, temUmLance(lance));
    }
	
}
