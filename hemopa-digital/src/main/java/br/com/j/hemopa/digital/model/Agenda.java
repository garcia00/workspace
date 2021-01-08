package br.com.j.hemopa.digital.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.j.hemopa.digital.dominios.Horario;
import br.com.j.hemopa.digital.dominios.TipoEvento;
import br.com.j.hemopa.digital.dominios.UnidadesHemopa;

@Entity
public class Agenda {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_Agenda", unique = true, nullable = false)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 15)
	private Horario horario;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "dataDoacao")
	private Date dataDoacao;
	
	@Column(name = "isChekin", columnDefinition = "boolean default false")
	private boolean isChekin;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 30)
	private UnidadesHemopa unidadeHemopa;
	
	@OneToOne
	@JoinColumn(name = "ID_PESSOA")
	private Pessoa pessoa;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 15)
	private TipoEvento tipoEvento = TipoEvento.MARCADO;

	public Agenda() {
		super();
	}
	
	public TipoEvento getTipoEvento() {
		return tipoEvento;
	}

	public void setTipoEvento(TipoEvento tipoEvento) {
		this.tipoEvento = tipoEvento;
	}

	public Pessoa getPessoa() {
		if (pessoa == null){
			pessoa = new Pessoa();
			}
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Date getDataDoacao() {
		return dataDoacao;
	}

	public void setDataDoacao(Date dataDoacao) {
		this.dataDoacao = dataDoacao;
	}

	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}

	public boolean isChekin() {
		return isChekin;
	}

	public void setChekin(boolean isChekin) {
		this.isChekin = isChekin;
	}

	public UnidadesHemopa getUnidadeHemopa() {
		return unidadeHemopa;
	}

	public void setUnidadeHemopa(UnidadesHemopa unidadeHemopa) {
		this.unidadeHemopa = unidadeHemopa;
	}
	
	@Transient
	public boolean isMarcado() {
		return TipoEvento.MARCADO.equals(this.tipoEvento);
	}
	
	@Transient
	public boolean isConfirmado() {
		return TipoEvento.CONFIRMADO.equals(this.tipoEvento);
	}
	
	@Transient
	public boolean isCancelado() {
		return TipoEvento.CANCELADO.equals(this.tipoEvento);
	}
	
	@Transient
	public boolean isRemarcado() {
		return TipoEvento.REMARCAR.equals(this.tipoEvento);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agenda other = (Agenda) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
