package model.vo;

public class EnderecoVO {

	private int idEndereco;
	private int idUsuario;
	private String logradouro;
	private String bairro;
	private int numero;
	private String cep;
	private String uf;
	private String localidade;
	
	public EnderecoVO(int idEndereco, int idUsuario, String logradouro, String bairro, int numero, String cep,
			String uf, String localidade) {
		super();
		this.idEndereco = idEndereco;
		this.idUsuario = idUsuario;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.numero = numero;
		this.cep = cep;
		this.uf = uf;
		this.localidade = localidade;
	}

	public EnderecoVO() {
		super();
	}

	public int getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(int idEndereco) {
		this.idEndereco = idEndereco;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	
}
