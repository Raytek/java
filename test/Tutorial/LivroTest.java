package Tutorial;

import junit.framework.TestCase;

import org.junit.Test;

public class LivroTest extends TestCase{

	@Test
	public void testLivro() {
		Livro l = new Livro("Arte da Guerra");
		l.setAutor("Sun Tzu");
		assertEquals("Arte da Guerra", l.titulo);
		assertEquals("Sun Tzu", l.autor);
	}

	@Test
	public void testGetPessoa() {
		Livro l2 = new Livro("A loucura de Avacyn");
		Pessoa p2 = new Pessoa();
		p2.setNome("Jace Beleren");

		l2.setPessoa(p2);

		String quemPegouLivro = l2.getPessoa().getNome();
		assertEquals("Jace Beleren", quemPegouLivro);
	}

    @Test
    public void testToString() {
        Livro l2 = new Livro("Battle For Zendikar");
        Pessoa p2 = new Pessoa();
        p2.setNome("Gideon");

        assertEquals("Battle For Zendikar por Autor Desconhecido; Disponível", l2.toString());

        l2.setPessoa(p2);

        assertEquals("Battle For Zendikar por Autor Desconhecido; Emprestado para Gideon", l2.toString());
    }
}
