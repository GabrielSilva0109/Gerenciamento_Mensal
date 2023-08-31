package model.vo;

public class DespesaVO {

	private int idDespesa;
	private int idUsuario;
	private String despNome;
	private double valor;
	
	public DespesaVO(int idDespesa, int idUsuario, String despNome, double valor) {
		super();
		this.idDespesa = idDespesa;
		this.idUsuario = idUsuario;
		this.despNome = despNome;
		this.valor = valor;
	}

	public DespesaVO() {
		super();
	}

	public int getIdDespesa() {
		return idDespesa;
	}

	public void setIdDespesa(int idDespesa) {
		this.idDespesa = idDespesa;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getDespNome() {
		return despNome;
	}

	public void setDespNome(String despNome) {
		this.despNome = despNome;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return despNome ;
	}
	
	
}
