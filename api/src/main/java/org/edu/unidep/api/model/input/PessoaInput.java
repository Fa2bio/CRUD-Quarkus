package org.edu.unidep.api.model.input;

public class PessoaInput {

	private String nome;
	private String dataAniversario;
	private String sangue;
	private String cpf;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDataAniversario() {
		return dataAniversario;
	}
	public void setDataAniversario(String dataAniversario) {
		this.dataAniversario = dataAniversario;
	}
	public String getSangue() {
		return sangue;
	}
	public void setSangue(String sangue) {
		this.sangue = sangue;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
