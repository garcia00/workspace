package br.com.j.hemopa.digital.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.j.hemopa.digital.dominios.Sexo;
import br.com.j.hemopa.digital.dominios.TipoSangue;

@Entity
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PESSOA", unique = true, nullable = false)
	private Long id;

	@Column(name = "nome", nullable = false, length = 200)
	private String nome;

	@Enumerated(EnumType.STRING)
	@Column(nullable = true, length = 15)
	private Sexo sexo;

	@Column(name = "cpf", nullable = false, length = 20)
	private String cpf;

	@Column(name = "rg", nullable = false, length = 20)
	private String rg;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_nascimento")
	private Date dataNascimento;

	@Enumerated(EnumType.STRING)
	@Column(nullable = true, length = 30)
	private TipoSangue sangue;
	
	@Embedded
	private Endereco endereco;

	@Embedded
	private Contato contato;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	private Vagas vagas;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	private Agenda agenda;

	public Vagas getVagas() {
		return vagas;
	}

	public void setVagas(Vagas vagas) {
		this.vagas = vagas;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	@Override
	public String toString() {
		return "Pessoa [id=" + id + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Contato getContato() {
		
		if (contato == null){
			contato = new Contato();
			}
	return contato;
	
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public Endereco getEndereco() {
		
	if (endereco == null){
			endereco = new Endereco();
			}
	return endereco;
	
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	
	public Sexo getSexo() { 
		
		return sexo; 
		
	}
	 
	public void setSexo(Sexo sexo) { 
		
		this.sexo = sexo; 
		
	}
	
	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}


	public TipoSangue getSangue() {
		return sangue;
	}

	public void setSangue(TipoSangue sangue) {
		this.sangue = sangue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
