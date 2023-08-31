package model.vo;

public class TabelaVO {

	private int idTabela;
    private int idUsuario;
    private String mes;
    private String ano;
    private double totalRest;
    private double saldoFinal;
    
	public TabelaVO(int idTabela, int idUsuario, String mes, String ano, double totalRest, double saldoFinal) {
		super();
		this.idTabela = idTabela;
		this.idUsuario = idUsuario;
		this.mes = mes;
		this.ano = ano;
		this.totalRest = totalRest;
		this.saldoFinal = saldoFinal;
	}

	public TabelaVO() {
		super();
		
	}

	public int getIdTabela() {
		return idTabela;
	}

	public void setIdTabela(int idTabela) {
		this.idTabela = idTabela;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
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

	public double getTotalRest() {
		return totalRest;
	}

	public void setTotalRest(double totalRest) {
		this.totalRest = totalRest;
	}

	public double getSaldoFinal() {
		return saldoFinal;
	}

	public void setSaldoFinal(double saldoFinal) {
		this.saldoFinal = saldoFinal;
	}
    
}
