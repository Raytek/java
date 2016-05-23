package Tutorial;

import junit.framework.TestCase;
import org.junit.Test;

public class PessoaTest extends TestCase{

	@Test
	public void testPessoa() {
		Pessoa p = new Pessoa();
		assertEquals("Nome desconhecido", p.getNome());
	}

	@Test
	public void testSetNome() {
		Pessoa p = new Pessoa();
		p.setNome("Joao");
		assertEquals("Joao", p.getNome());
	}

	@Test
	public void testSetMaxLivros() {
		Pessoa p = new Pessoa();
		assertEquals(3, p.getMaxLivros());
	}

}
