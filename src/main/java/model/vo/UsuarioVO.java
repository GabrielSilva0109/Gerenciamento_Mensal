package model.vo;

import java.time.LocalDate;
import java.util.ArrayList;

public class UsuarioVO {

	private int idUsuario;
	private int tipoUsuario;
	private String nome;
	private String cpf;
	private LocalDate dataNasci;
	private String email;
	private double salariol;
	private String login;
	private String senha;
	private ArrayList<DespesaVO> despesas;
	
	public UsuarioVO(int idUsuario, int tipoUsuario, String nome, String cpf, LocalDate dataNasci, String email,
			double salariol, String login, String senha, ArrayList<DespesaVO> despesas) {
		super();
		this.idUsuario = idUsuario;
		this.tipoUsuario = tipoUsuario;
		this.nome = nome;
		this.cpf = cpf;
		this.dataNasci = dataNasci;
		this.email = email;
		this.salariol = salariol;
		this.login = login;
		this.senha = senha;
		this.despesas = despesas;
	}

	public UsuarioVO() {
		super();
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(int tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNasci() {
		return dataNasci;
	}

	public void setDataNasci(LocalDate dataNasci) {
		this.dataNasci = dataNasci;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getSalariol() {
		return salariol;
	}

	public void setSalariol(double salariol) {
		this.salariol = salariol;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public ArrayList<DespesaVO> getDespesas() {
		return despesas;
	}

	public void setDespesas(ArrayList<DespesaVO> despesas) {
		this.despesas = despesas;
	}
	
	
}
