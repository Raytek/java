package Tutorial;

import java.util.ArrayList;

public class Biblioteca {
    String nome;
    ArrayList<Livro> livros;
    ArrayList<Pessoa> pessoas;

    public Biblioteca(String nome) {
        this.nome = nome;
        livros = new ArrayList<Livro>();
        pessoas = new ArrayList<Pessoa>();
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<Livro> getLivros() {
        return livros;
    }

    public ArrayList<Pessoa> getPessoas() {
        return pessoas;
    }

    public void addLivro(Livro l1) {
        this.livros.add(l1);
    }

    public void removeLivro(Livro l1) {
        this.livros.remove(l1);
    }

    public void addPessoa(Pessoa p1) {
        this.pessoas.add(p1);
    }

    public void removePessoa(Pessoa p1) {
        this.pessoas.remove(p1);
    }

    public boolean emprestimo(Livro l1, Pessoa p1) {
        int livrosEmprestados = this.getLivrosPorPessoa(p1).size();
        if ((l1.getPessoa() == null) && (livrosEmprestados < p1.getMaxLivros())) {
            l1.setPessoa(p1);
            return true;
        } else {
            return false;
        }
    }

    public boolean devolucao(Livro l1) {
        if (l1.getPessoa() != null) {
            l1.setPessoa(null);
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Livro> getLivrosPorPessoa(Pessoa p1) {
        ArrayList<Livro> resultado = new ArrayList<Livro>();
        for (Livro umLivro : this.getLivros()) {
            if ((umLivro.getPessoa() != null) && (umLivro.getPessoa().getNome().equalsIgnoreCase(p1.getNome()))) {
                resultado.add(umLivro);
            }
        }
        return resultado;
    }

    public ArrayList<Livro> getLivrosDisponiveis(Pessoa p1) {
        ArrayList<Livro> resultado = new ArrayList<Livro>();
        for (Livro umLivro : this.getLivros()) {
            if (umLivro.getPessoa() == null) {
                resultado.add(umLivro);
            }
        }
        return resultado;
    }

    public ArrayList<Livro> getLivrosIndisponiveis() {
        ArrayList<Livro> resultado = new ArrayList<Livro>();
        for (Livro umLivro : this.getLivros()) {
            if (umLivro.getPessoa() != null) {
                resultado.add(umLivro);
            }
        }
        return resultado;
    }

    public String toString() {
        return this.getNome() + ": " +
                this.getLivros().size() + " Livros; " +
                this.getPessoas().size() + " Pessoas.";
    }
}
