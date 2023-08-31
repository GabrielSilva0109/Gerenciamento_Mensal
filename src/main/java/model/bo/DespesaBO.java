package model.bo;

import model.vo.DespesaVO;
import model.vo.UsuarioVO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dao.*;

public class DespesaBO {
	
	DespesaDAO despDAO = new DespesaDAO();

	public double calcularDescontoTotalBO(UsuarioVO userOnline) {
		
		return despDAO.calcularDescontoTotalDAO(userOnline);
	}

	public DespesaVO consultarDespesaBO(String string, UsuarioVO userOnline) {
		
		return despDAO.consultarDespesaPorNomeDAO(string, userOnline);
	}

	public DespesaVO inserirDespesaBO(DespesaVO despesa) {
		
		return despDAO.inserirDespesaDAO(despesa);
	}

	public boolean removerDespController(UsuarioVO userLogado, String text) {
		
		return despDAO.removerDespDAO(userLogado, text);
	}

	public boolean atualizarDespBO(DespesaVO despesaAtualizar ,String text) throws SQLException {
		
		return despDAO.atualizarDespDAO(despesaAtualizar, text);
	}

	public ArrayList<DespesaVO> consultarTodasBO(UsuarioVO userOnline, String despNome) {
		
			return despDAO.consultarTodasDAO(userOnline);
	}


}
