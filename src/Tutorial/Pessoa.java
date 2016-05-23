package Tutorial;

public class Pessoa {
	
	private String nome;
	private int maxLivros;
	
	public Pessoa(){
		nome = "Nome desconhecido";
		maxLivros = 3;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getMaxLivros() {
		return maxLivros;
	}

	public void setMaxLivros(int maxLivros) {
		this.maxLivros = maxLivros;
	}

}
