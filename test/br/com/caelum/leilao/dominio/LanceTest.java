package br.com.caelum.leilao.dominio;

import static br.com.caelum.matcher.LeilaoMatcher.temUmLance;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

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
        Leilao leilao = new CriadorDeLeilao().para("Macbook Pro 15")
        		.lance(victor, 2000.0)
        		.constroi();
//      assertEquals(0, leilao.getLances().size());
        assertThat(leilao.getLances().size(), equalTo(1));
        assertThat(leilao, temUmLance(leilao.getLances().get(0)));
    }
	
}
