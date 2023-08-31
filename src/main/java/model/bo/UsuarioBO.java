package model.bo;

import java.sql.SQLException;

import exceptions.CpfJaUtilizadoException;
import model.dao.UsuarioDAO;
import model.vo.UsuarioVO;

public class UsuarioBO {
	
	UsuarioDAO userDAO = new UsuarioDAO();
	
	public UsuarioVO realizarLoginBO(UsuarioVO userOnline) throws SQLException {
		UsuarioVO usuarioVO = new UsuarioVO();
		
		
		usuarioVO = userDAO.realizarLoginDAO(userOnline);
		
		return usuarioVO;
	}

	public boolean excluirContaBO(UsuarioVO userOnline) {
	
		return userDAO.excluirContaDAO(userOnline);
	}

	public boolean atualizarUsuarioBO(UsuarioVO userAtualizado) {
		
		return userDAO.atualizarUsuarioDAO(userAtualizado);
	}

	public UsuarioVO cadastrarUsuarioBO(UsuarioVO usuario) throws CpfJaUtilizadoException{
		
		if (userDAO.cpfJaUtilizado(usuario.getCpf())) {
			throw new CpfJaUtilizadoException("Este CPF já está cadastrado!");
		}
		
		//SE ESTIVER CORRETO SALVA O USUARIO
		return userDAO.cadastrarUsuarioDAO(usuario);
	}

}
