package Tutorial;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;

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

    public void setNome(String nome) {
        this.nome = nome;
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

    public static void main(String[] args) {
        try {

            Biblioteca biblioteca = new Biblioteca("Biblioteca");

            int opcao;
            String nomeLivro, nomeUsuario, auxOpcao;

            do {

                auxOpcao = JOptionPane.showInputDialog(
                        null,
                        "1 - Renomear Biblioteca.\n" +
                        "2 - Cadastrar Livro.\n" +
                        "3 - Cadastrar Pessoa.\n" +
                        "4 - Emprestar Livro.\n" +
                        "5 - Devolver Livro.\n" +
                        "6 - Imprimir Status.\n" +
                        "7 - Remover Livro.\n" +
                        "8 - Remover Pessoa\n" +
                        "0 - Sair",
                        "Tela inicial",
                        JOptionPane.PLAIN_MESSAGE);

                if (auxOpcao == null) {
                    opcao = 0;
                }else {
                    opcao = Integer.parseInt(auxOpcao);
                }

                switch (opcao) {
                    case 0:
                        JOptionPane.showMessageDialog(null, "Até logo...");
                        break;

                    case 1:
                        String nome = JOptionPane.showInputDialog("Digite um nome para a biblioteca: ");
                        if (biblioteca.stringNaoNula(nome)) {
                            biblioteca.setNome(nome);
                        }
                        break;

                    case 2:
                        String tituloLivro = JOptionPane.showInputDialog("Digite o nome do livro: ");
                        if (biblioteca.stringNaoNula(tituloLivro)) {
                            Livro livro = new Livro(tituloLivro);
                            String autor = JOptionPane.showInputDialog("Digite o nome do autor: ");
                            if (biblioteca.stringNaoNula(autor)) {
                                if (!autor.equalsIgnoreCase("")) {
                                    livro.setAutor(autor);
                                }
                                biblioteca.addLivro(livro);
                            }
                        }
                        break;

                    case 3:
                        String nomePessoa = JOptionPane.showInputDialog("Digite o nome do usuario: ");
                        if (biblioteca.stringNaoNula(nomePessoa)) {
                            Pessoa usuario = new Pessoa();
                            usuario.setNome(nomePessoa);
                            biblioteca.addPessoa(usuario);
                        }
                        break;

                    case 4:
                        nomeLivro = JOptionPane.showInputDialog("Digite o nome do livro: ");
                        if (biblioteca.stringNaoNula(nomeLivro)) {
                            for (Livro l : biblioteca.getLivros()) {
                                if (l.getTitulo().equalsIgnoreCase(nomeLivro)) {
                                    nomeUsuario = JOptionPane.showInputDialog("Digite o nome do usuario: ");
                                    if (biblioteca.stringNaoNula(nomeUsuario)){
                                        for (Pessoa p : biblioteca.getPessoas()) {
                                            if (p.getNome().equalsIgnoreCase(nomeUsuario)) {
                                                biblioteca.emprestimo(l, p);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        break;

                    case 5:
                        nomeLivro = JOptionPane.showInputDialog("Digite o nome do livro: ");
                        if (biblioteca.stringNaoNula(nomeLivro)) {
                            for (Livro l : biblioteca.getLivros()) {
                                if (l.getTitulo().equalsIgnoreCase(nomeLivro)) {
                                    biblioteca.devolucao(l);
                                }
                            }
                        }
                        break;

                    case 6:
                        biblioteca.imprimeStatus();
                        break;

                    case 7:
                        nomeLivro = JOptionPane.showInputDialog("Digite o nome do livro: ");
                        if (biblioteca.stringNaoNula(nomeLivro)) {
                            Iterator<Livro> iterLivro = biblioteca.getLivros().iterator();
                            while (iterLivro.hasNext()) {
                                if (iterLivro.next().getTitulo().equalsIgnoreCase(nomeLivro)) {
                                    iterLivro.remove();
                                }
                            }
                        }
                        break;

                    case 8:
                        nomeUsuario = JOptionPane.showInputDialog("Digite o nome do usuario: ");
                        if (biblioteca.stringNaoNula(nomeUsuario)) {
                            Iterator<Pessoa> iterPessoa = biblioteca.getPessoas().iterator();
                            while (iterPessoa.hasNext()) {
                                if (iterPessoa.next().getNome().equalsIgnoreCase(nomeUsuario)) {
                                    iterPessoa.remove();
                                }
                            }
                        }
                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "Opção Inválida!\n\n\nTente novamente ;)", "Erro 404", JOptionPane.WARNING_MESSAGE);
                        break;
                }

            } while (opcao != 0);
        }catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ops! Ocorreu um erro durante a entrada dos dados.", "Erro 500", JOptionPane.ERROR_MESSAGE);
        }catch (NullPointerException e){
            JOptionPane.showMessageDialog(null, "Ops! Entidade não encontrada.", "Erro 500", JOptionPane.ERROR_MESSAGE);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e, "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void imprimeStatus() {
        StringBuilder sb = new StringBuilder();

        sb.append("Status da Biblioteca:\n" + this.toString() + "\n");

        for (Livro umLivro : this.getLivros()) {
            sb.append(umLivro);
            sb.append("\n");
        }


        for (Pessoa umaPessoa : this.getPessoas()) {
            int contador = this.getLivrosPorPessoa(umaPessoa).size();
            sb.append(umaPessoa.getNome() + " tem " + contador + " dos meus livros\n");
        }

        sb.append("\nLivros indisponíveis: " + this.getLivrosIndisponiveis().size());
        sb.append("\n --- Fim do Relatório de Status --- ");

        JOptionPane.showMessageDialog(null, sb.toString(), "Status", JOptionPane.INFORMATION_MESSAGE);
    }

    private boolean stringNaoNula(String string) {
        if (string == null){
            return false;
        }else {
            return true;
        }
    }
}
