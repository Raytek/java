package Tutorial;

public class Livro {

	String titulo;
	String autor;
	Pessoa pessoa;

	public Livro(String titulo) {
		this.titulo = titulo;
		this.autor = "Autor Desconhecido";
	}

	public String getTitulo() {
		return titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public String toString() {
        String situacao;
        if (this.getPessoa() == null) {
            situacao = "Disponível";
        } else {
            situacao = "Emprestado para " + this.getPessoa().getNome();
        }
        return this.getTitulo() + " por " + this.getAutor() + "; " + situacao;
    }
}
