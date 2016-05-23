package Tutorial;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;

public class BibliotecaTest extends TestCase{
    private Biblioteca b1;
    private Livro l1;
    private Livro l2;
    private Pessoa p1;
    private Pessoa p2;

    @Test
    public void testBiblioteca() {
        Biblioteca b1 = new Biblioteca("Nerd Center");

        assertEquals("Nerd Center", b1.nome);

        assertTrue(b1.livros instanceof ArrayList);
        assertTrue(b1.pessoas instanceof ArrayList);
    }

    public void setUp() {
        l1 = new Livro("Shadows Over Innistrad");
        l2 = new Livro("Oath of the Gatewatch");

        p1 = new Pessoa();
        p2 = new Pessoa();

        p1.setNome("Vinícius");
        p2.setNome("Thayná");

        b1 = new Biblioteca("Wizard");
    }

    @Test
    public void testAddLivro() {
        setUp();
        assertEquals(0, b1.getLivros().size());

        b1.addLivro(l1);
        b1.addLivro(l2);

        assertEquals(2, b1.getLivros().size());
        assertEquals(0, b1.getLivros().indexOf(l1));
        assertEquals(1, b1.getLivros().indexOf(l2));

        b1.removeLivro(l1);
        assertEquals(1, b1.getLivros().size());
        assertEquals(0, b1.getLivros().indexOf(l2));

        b1.removeLivro(l2);
        assertEquals(0, b1.getLivros().size());

        b1.addPessoa(p1);
        b1.addPessoa(p2);

        assertEquals(2, b1.getPessoas().size());
        assertEquals(0, b1.getPessoas().indexOf(p1));
        assertEquals(1, b1.getPessoas().indexOf(p2));

        b1.removePessoa(p1);
        assertEquals(1, b1.getPessoas().size());
        assertEquals(0, b1.getPessoas().indexOf(p2));

        b1.removePessoa(p2);
        assertEquals(0, b1.getPessoas().size());
    }

    @Test
    public void testMovimentaLivro() {
        setUp();

        addItems();

        assertTrue("O Livro não pode ser emprestado!", b1.emprestimo(l1, p1));

        assertEquals("Vinícius", l1.getPessoa().getNome());

        assertFalse("O Livro foi emprestado indevidamente!", b1.emprestimo(l1, p2));

        assertTrue("O Livro não pode ser devolvido!", b1.devolucao(l1));

        assertFalse("O Livro foi devolvido indevidamente! (Já devolvido)", b1.devolucao(l1));

        assertFalse("O livro foi devolvido indevidamente! (Nunca emprestado)", b1.devolucao(l2));

        setUp();
        p1.setMaxLivros(1);
        addItems();

        assertTrue("O Livro não pode ser emprestado!", b1.emprestimo(l2, p1));
        assertFalse("O Livro foi emprestado indevidamente!", b1.emprestimo(l1, p1));
    }

    private void addItems() {
        b1.addLivro(l1);
        b1.addLivro(l2);
        b1.addPessoa(p1);
        b1.addPessoa(p2);
    }

    @Test
    public void testGetLivrosPorPessoa() {
        setUp();
        addItems();
        assertEquals(0, b1.getLivrosPorPessoa(p1).size());

        b1.emprestimo(l1, p1);

        ArrayList<Livro> testeLivro = b1.getLivrosPorPessoa(p1);
        assertEquals(1, testeLivro.size());
        assertEquals(0, testeLivro.indexOf(l1));

        b1.emprestimo(l2, p1);
        testeLivro = b1.getLivrosPorPessoa(p1);
        assertEquals(2, testeLivro.size());
        assertEquals(1, testeLivro.indexOf(l2));
    }

    @Test
    public void testGetLivrosDisponiveis() {
        setUp();
        addItems();
        ArrayList<Livro> testeLivro = b1.getLivrosDisponiveis(p1);
        assertEquals(2, testeLivro.size());
        assertEquals(1, testeLivro.indexOf(l2));

        b1.emprestimo(l1, p1);
        testeLivro = b1.getLivrosDisponiveis(p1);
        assertEquals(1, testeLivro.size());
        assertEquals(0, testeLivro.indexOf(l2));

        b1.emprestimo(l2, p1);
        testeLivro = b1.getLivrosDisponiveis(p1);
        assertEquals(0, testeLivro.size());
    }

    @Test
    public void testGetLivrosIndisponiveis() {
        setUp();
        addItems();
        assertEquals(0, b1.getLivrosIndisponiveis().size());

        b1.emprestimo(l1, p1);

        ArrayList<Livro> testeLivro = b1.getLivrosIndisponiveis();
        assertEquals(1, testeLivro.size());
        assertEquals(0, testeLivro.indexOf(l1));

        b1.emprestimo(l2, p2);
        testeLivro = b1.getLivrosIndisponiveis();
        assertEquals(2, testeLivro.size());
        assertEquals(1, testeLivro.indexOf(l2));
    }

    @Test
    public void testToString() {
        setUp();
        addItems();
        assertEquals("Wizard: 2 Livros; 2 Pessoas.", b1.toString());
    }
}
