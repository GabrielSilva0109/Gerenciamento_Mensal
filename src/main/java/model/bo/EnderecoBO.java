package model.bo;

import model.vo.EnderecoVO;
import model.vo.UsuarioVO;
import model.dao.*;

public class EnderecoBO {
	
	EnderecoDAO enderecoDAO = new EnderecoDAO();

	public EnderecoVO cadastrarEnderecoBO(EnderecoVO endereco) {
		
		return enderecoDAO.cadastrarEnderecoDAO(endereco);
	}

	public boolean atualizarEnderecoBO(EnderecoVO endAtualizado) {
	
		return enderecoDAO.atualizarEndDAO(endAtualizado);
	}

	public EnderecoVO consultarEnderecoBO(EnderecoVO endereco) {
		// TODO Auto-generated method stub
		return enderecoDAO.consultarEnderecoDAO(endereco);
	}

}
