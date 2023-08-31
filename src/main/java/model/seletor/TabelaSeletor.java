package model.seletor;

public class TabelaSeletor extends BaseSeletor {

	private String mes;
	private String ano;
	
	
	@Override
	public boolean temFiltro() {
		return (this.mes != null && this.mes.trim().length() > 0)
				|| (this.ano != null && this.ano.trim().length() > 0);				
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

	

}
