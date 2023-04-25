package org.edu.unidep.api.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.edu.unidep.domain.model.Endereco;
import org.hibernate.validator.constraints.br.CPF;

public class PessoaInput {
	
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
	
	@NotNull(message = "Endereco da pessoa não pode ser nulo.")
	private Endereco endereco;
	
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
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
}
