package br.com.j.hemopa.digital.repository.filter;

import java.io.Serializable;


public class PessoaFilter implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String cpf;
	private String nome;
	
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

}
