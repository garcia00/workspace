package br.com.j.hemopa.digital.dominios;

public enum TipoEvento {
	
	
    CONFIRMADO("CONFIRMADO", "Confirmado"),
    MARCADO("MARCADO", "Marcados"),
    REMARCAR("Remarcar", "Remarcar"),
    RESERVADO("Reservado", "Reservado"),
    CANCELADO("Cancelado", "cancelado");
	

    private final String descricao;
    private final String css;

    private TipoEvento(String descricao, String css) {
        this.css = css;
        this.descricao = descricao;
    }

    public String getCss() {
        return css;
    }

    public String getDescricao() {
        return descricao;
    }

}
