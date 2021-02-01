package br.com.j.hemopa.digital.dominios;

import java.util.Arrays;

public enum DominioMes {
	
	JANEIRO(1, "JANEIRO", "01") {

		@Override
		public DominioMes getMesAnterior() {

			return DEZEMBRO;
		}
	},
	FEVEREIRO(2, "FEVEREIRO", "02") {

		@Override
		public DominioMes getMesAnterior() {

			return JANEIRO;
		}
	},
	MARCO(3, "MARÇO", "03") {

		@Override
		public DominioMes getMesAnterior() {

			return FEVEREIRO;
		}
	},
	ABRIL(4, "ABRIL", "04") {

		@Override
		public DominioMes getMesAnterior() {

			return MARCO;
		}
	},
	MAIO(5, "MAIO", "05") {

		@Override
		public DominioMes getMesAnterior() {

			return ABRIL;
		}
	},
	JUNHO(6, "JUNHO", "06") {

		@Override
		public DominioMes getMesAnterior() {

			return MAIO;
		}
	},
	JULHO(7, "JULHO", "07") {

		@Override
		public DominioMes getMesAnterior() {

			return JUNHO;
		}
	},
	AGOSTO(8, "AGOSTO", "08") {

		@Override
		public DominioMes getMesAnterior() {

			return JULHO;
		}
	},
	SETEMBRO(9, "SETEMBRO", "09") {

		@Override
		public DominioMes getMesAnterior() {

			return AGOSTO;
		}
	},
	OUTUBRO(10, "OUTUBRO", "10") {

		@Override
		public DominioMes getMesAnterior() {

			return SETEMBRO;
		}
	},
	NOVEMBRO(11, "NOVEMBRO", "11") {

		@Override
		public DominioMes getMesAnterior() {

			return OUTUBRO;
		}
	},
	DEZEMBRO(12, "DEZEMBRO", "12") {

		@Override
		public DominioMes getMesAnterior() {

			return NOVEMBRO;
		}
	};

	private Integer codigo;

	private String descricao;

	private String numero;

	private DominioMes(Integer codigo, String descricao, String numero) {

		this.codigo = codigo;
		this.descricao = descricao;
		this.numero = numero;

	}

	public Integer getCodigo() {

		return this.codigo;
	}

	public String getDescricao() {

		return this.descricao;
	}

	public String getNumero() {

		return this.numero;
	}

	@Override
	public String toString() {

		return this.descricao;
	}

	public static Iterable<DominioMes> list() {

		return Arrays.asList(DominioMes.JANEIRO, DominioMes.FEVEREIRO, DominioMes.MARCO, DominioMes.ABRIL, DominioMes.MAIO, DominioMes.JUNHO,
						DominioMes.JULHO, DominioMes.AGOSTO, DominioMes.SETEMBRO, DominioMes.OUTUBRO, DominioMes.NOVEMBRO, DominioMes.DEZEMBRO);
	}

	public static DominioMes mesAtual(int mes) {

		switch (mes) {
			case 0:
				return JANEIRO;
			case 1:
				return FEVEREIRO;
			case 2:
				return MARCO;
			case 3:
				return ABRIL;
			case 4:
				return MAIO;
			case 5:
				return JUNHO;
			case 6:
				return JULHO;
			case 7:
				return AGOSTO;
			case 8:
				return SETEMBRO;
			case 9:
				return OUTUBRO;
			case 10:
				return NOVEMBRO;
			case 11:
				return DEZEMBRO;

			default:
				return null;
		}
	}

	public abstract DominioMes getMesAnterior();

}
