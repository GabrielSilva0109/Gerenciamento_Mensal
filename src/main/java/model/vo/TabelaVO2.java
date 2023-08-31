package model.vo;

public class TabelaVO2 {

	private String idTabela;
    private String idUsuario;
    private String mes;
    private String ano;
    private String totalRest;
    private String saldoFinal;
    
	public TabelaVO2() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TabelaVO2(String idTabela, String idUsuario, String mes, String ano, String totalRest, String saldoFinal) {
		super();
		this.idTabela = idTabela;
		this.idUsuario = idUsuario;
		this.mes = mes;
		this.ano = ano;
		this.totalRest = totalRest;
		this.saldoFinal = saldoFinal;
	}

	public String getIdTabela() {
		return idTabela;
	}

	public void setIdTabela(String idTabela) {
		this.idTabela = idTabela;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getTotalRest() {
		return totalRest;
	}

	public void setTotalRest(String totalRest) {
		this.totalRest = totalRest;
	}

	public String getSaldoFinal() {
		return saldoFinal;
	}

	public void setSaldoFinal(String saldoFinal) {
		this.saldoFinal = saldoFinal;
	}

	
}
