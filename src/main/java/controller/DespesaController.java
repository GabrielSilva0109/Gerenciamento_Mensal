package controller;

import model.vo.DespesaVO;
import model.vo.UsuarioVO;
import model.bo.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exceptions.CampoInvalidoException;
import model.dao.*;

public class DespesaController {
	
	DespesaBO despBO = new DespesaBO();
	DespesaDAO despDAO = new DespesaDAO();
	
	public double calcularDescontoTotalController(UsuarioVO userOnline) {
		
		return despBO.calcularDescontoTotalBO(userOnline);
	}

	//public DespesaVO consultarDespesaController(String string, UsuarioVO userOnline) throws CampoInvalidoException {
		
		//this.validarCamposDespesa(string);
		
		//return despBO.consultarDespesaBO(string, userOnline);
	//}

	private void validarCamposDespesa(String string) throws CampoInvalidoException {
		
		String mensagem = "";
		
		if (string.trim().isEmpty() || string.trim().isBlank()) {
			
			mensagem = "O campo nome é obrigatório!";
		}
		
		if (!mensagem.isEmpty()) {
			
			throw new CampoInvalidoException(mensagem);
			
		}
		
	}

	public DespesaVO inserirDespesaController(DespesaVO despesa) {
		
		return despBO.inserirDespesaBO(despesa);
	}

	public boolean removerDespesaController(UsuarioVO userLogado, String text) {
		
		return despBO.removerDespController(userLogado, text);
	}

	public ArrayList<DespesaVO> consultarTodosController(UsuarioVO userOnline, String despNome) {
		
		return despBO.consultarTodasBO(userOnline, despNome);
	}

	public boolean atualizarDespController(DespesaVO despesaAtualizar, String text) throws SQLException, CampoInvalidoException {
		
		this.validarCampos(despesaAtualizar);
		return despBO.atualizarDespBO(despesaAtualizar, text);
	}

	private void validarCampos(DespesaVO despesaAtualizar) throws CampoInvalidoException {

		String mensagem = "";
		
		if (despesaAtualizar.getDespNome().trim().isEmpty() || despesaAtualizar.getDespNome().trim().isBlank()) {
			mensagem = " O nome é obrigatório!";
		}
		
		if (!mensagem.isEmpty()) {
			throw new CampoInvalidoException(mensagem);
		}
		
	}

	public DespesaVO consultarDespesaController(String despNome, UsuarioVO userOnline) {
		
		return despBO.consultarDespesaBO(despNome, userOnline);
	}
	
     public DespesaVO consultarDespesaController2(String despNome, UsuarioVO userOnline) throws CampoInvalidoException{
		
		return despBO.consultarDespesaBO(despNome, userOnline);
	}

	public boolean excluirDespesasController(UsuarioVO userOnline) {
		 
		return despDAO.excluirDespesasDAO(userOnline);
	}

}
