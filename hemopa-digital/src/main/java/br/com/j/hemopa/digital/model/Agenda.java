package br.com.j.hemopa.digital.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.j.hemopa.digital.dominios.DominioPeriodoExpediente;
import br.com.j.hemopa.digital.dominios.DominioSimNao;
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
		
	@Column(name = "dia")
	@Temporal(TemporalType.DATE)
	private Date dia;
	
	@Column(name = "isChekin", columnDefinition = "boolean default false")
	private boolean isChekin;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 30)
	private UnidadesHemopa unidadeHemopa;
	
	@ManyToOne
	@JoinColumn(name = "ID_PESSOA")
	private Pessoa pessoa;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 15)
	private TipoEvento tipoEvento = TipoEvento.MARCADO;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "PERIODO")
	private DominioPeriodoExpediente periodo;
	
	@Column(name = "DATA_INICIO", length = 15)
	@Temporal(TemporalType.DATE)
	private Date dataInicio;
	
	@Column(name = "DATA_FIM", length = 15)
	@Temporal(TemporalType.DATE)
	private Date dataFim;

	@Column(name = "HORARIO_INICIO", length = 5)
	private String horarioInicio;

	@Column(name = "HORARIO_FIM", length = 5)
	private String horarioFim;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "RESERVARDO")
	private DominioSimNao reservado;

	public Agenda() {
		super();
	}
	
	public Agenda(Pessoa pessoa) {
		
		if (pessoa == null){
			
			pessoa = new Pessoa();
			
			}
		this.pessoa = new Pessoa();
	}
	
	public TipoEvento getTipoEvento() {
		return tipoEvento;
	}

	public void setTipoEvento(TipoEvento tipoEvento) {
		this.tipoEvento = tipoEvento;
	}
	
	public void agenda(Pessoa pessoa) {
		
		this.getPessoa();
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
	
	@Transient
	public boolean isReservado() {
		return TipoEvento.RESERVADO.equals(this.tipoEvento);
	}

	public DominioPeriodoExpediente getPeriodo() {
		return periodo;
	}

	public void setPeriodo(DominioPeriodoExpediente periodo) {
		this.periodo = periodo;
	}

	public String getHorarioInicio() {
		return horarioInicio;
	}

	public void setHorarioInicio(String horarioInicio) {
		this.horarioInicio = horarioInicio;
	}

	public String getHorarioFim() {
		return horarioFim;
	}

	public void setHorarioFim(String horarioFim) {
		this.horarioFim = horarioFim;
	}

	public DominioSimNao getReservado() {
		return reservado;
	}

	public void setReservado(DominioSimNao reservado) {
		this.reservado = reservado;
	}

	public Date getDia() {
		return dia;
	}

	public void setDia(Date dia) {
		this.dia = dia;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
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

	@Override
	public String toString() {
		return "Agenda [id=" + id + ", horario=" + horario + ", dia=" + dia + ", isChekin=" + isChekin
				+ ", unidadeHemopa=" + unidadeHemopa + ", pessoa=" + pessoa + ", tipoEvento=" + tipoEvento
				+ ", periodo=" + periodo + ", dataInicio=" + dataInicio + ", dataFim=" + dataFim + ", horarioInicio="
				+ horarioInicio + ", horarioFim=" + horarioFim + ", reservado=" + reservado + "]";
	}
	
	

}
