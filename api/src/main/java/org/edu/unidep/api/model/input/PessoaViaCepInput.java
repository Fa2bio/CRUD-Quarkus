package org.edu.unidep.api.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

public class PessoaViaCepInput {

	@NotNull(message = "Nome da pessoa não pode ser nulo.")
	@NotBlank(message = "Nome da pessoa não pode estar em braco ou com apenas espaços.")
	private String nome;
	
	@NotNull(message = "Nome da pessoa não pode ser nulo.")
	@NotBlank(message = "Nome da pessoa não pode estar em braco ou com apenas espaços.")
	private String dataAniversario;
	
	@NotNull(message = "Nome da pessoa não pode ser nulo.")
	@NotBlank(message = "Nome da pessoa não pode estar em braco ou com apenas espaços.")
	private String sangue;
	
	@NotNull(message = "Nome da pessoa não pode ser nulo.")
	@NotBlank(message = "Nome da pessoa não pode estar em braco ou com apenas espaços.")
	@CPF(message = "O CPF informado deve ser válido de acordo com as normas da Receita Federal Brasileira.")
	private String cpf;
	
	@NotNull(message = "Nome da pessoa não pode ser nulo.")
	@NotBlank(message = "Nome da pessoa não pode estar em braco ou com apenas espaços.")
	private String cep;
	
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
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	
}