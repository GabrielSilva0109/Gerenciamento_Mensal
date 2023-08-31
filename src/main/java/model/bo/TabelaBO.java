package model.bo;

import model.vo.TabelaVO;
import model.vo.UsuarioVO;

import java.util.ArrayList;

import model.dao.*;
import model.seletor.TabelaSeletor;

public class TabelaBO {

	TabelaDAO tabDAO = new TabelaDAO();
	
	
	public TabelaVO salvarDadosTabelaBO(UsuarioVO userLogado, TabelaVO tabela) {
		
		return tabDAO.salvarDadosTabelaDAO(userLogado, tabela);
	}


	public TabelaVO consultarMesBO(TabelaVO tabelaVO, String ano) {
		
		return tabDAO.consultarMesTabelaDAO(tabelaVO, ano);
	}


	public ArrayList<TabelaVO> consultarTabelaBO(TabelaVO tabelaVO) {
		// TODO Auto-generated method stub
		return tabDAO.consultarTabelaCompletaDAO(tabelaVO);
	}


	public ArrayList<TabelaVO> consultarComFiltrosBO(TabelaSeletor tabSeletor) {
		
		return tabDAO.consultarComFiltrosDAO(tabSeletor);
	}


	public int contarTotalRegistrosComFiltros(TabelaSeletor seletor) {
		// TODO Auto-generated method stub
		return tabDAO.contarTotalRegistrosComFiltros(seletor);
	}


	public boolean removerTabelaBO(UsuarioVO userOnline, TabelaVO tabelaSelecionada) {
		// TODO Auto-generated method stub
		return tabDAO.removerTabelaDAO(userOnline, tabelaSelecionada);
	}

}
