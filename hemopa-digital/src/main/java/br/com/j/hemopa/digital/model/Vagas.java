package br.com.j.hemopa.digital.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import br.com.j.hemopa.digital.service.NegocioException;

@Entity
public class Vagas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_vagas", unique = true, nullable = false)
	private Long id;

	@Column(name = "quantidade_vagas", nullable = false, length = 5)
	private Integer quantidade;

	@Column(name = "vaga", nullable = false, precision = 10, scale = 2)
	private BigDecimal vaga;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_pessoa", nullable = false)
	private Pessoa pessoa;

	
	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public void baixarEstoque(Integer quantidade) throws NegocioException {
		int novaQuantidade = this.getQuantidade() - quantidade;

		if (novaQuantidade < 0) {
			throw new NegocioException("Não há disponibilidade de vagas" + quantidade + ".");
		}

		this.setQuantidade(novaQuantidade);
	}

	public void adicionarEstoque(Integer quantidade) {
		this.setQuantidade(getQuantidade() + quantidade);
	}

	public BigDecimal getVaga() {
		return vaga;
	}

	public void setVaga(BigDecimal vaga) {
		this.vaga = vaga;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
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
		Vagas other = (Vagas) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
