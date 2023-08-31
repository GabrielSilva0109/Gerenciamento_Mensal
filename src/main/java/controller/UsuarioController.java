package controller;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.CampoInvalidoException;
import exceptions.CpfJaUtilizadoException;
import model.bo.UsuarioBO;
import model.vo.UsuarioVO;
import model.dao.*;

public class UsuarioController {
	
	//VALIDACAO DE EMAIL
	private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
	
	UsuarioBO userBO = new UsuarioBO();
	UsuarioDAO userDAO  = new UsuarioDAO();
	boolean emailVerific = false;

	public UsuarioVO realizarLoginController(UsuarioVO userOnline) throws CampoInvalidoException, SQLException {
		
		this.validarCamposObrigatoriosLogin(userOnline);
		
		return userBO.realizarLoginBO(userOnline);
	}

	private void validarCamposObrigatoriosLogin(UsuarioVO userOnline) throws CampoInvalidoException {
		
		String mensagemValidacao = "";
		
		if (userOnline.getLogin().trim().isEmpty() || userOnline.getLogin().trim().isBlank()) {
			
			mensagemValidacao = "Todos os campos são obrigatórios!";
		} 
		
		if (!userOnline.getSenha().trim().isEmpty() && userOnline.getSenha().trim().length() < 6) {
			
			mensagemValidacao = "A senha digitada é inválida!";
		}
		
		if (!userOnline.getSenha().trim().isEmpty() && userOnline.getSenha().trim().length() > 6) {
			
			mensagemValidacao = "A senha digitada é inválida!";
		}
		
		if(!mensagemValidacao.isEmpty()) {
			throw new CampoInvalidoException(mensagemValidacao);
		}
	}

	public boolean excluirContaController(UsuarioVO userOnline) {
		
		return userBO.excluirContaBO(userOnline);
	}

	public UsuarioVO cadastrarUsuarioController(UsuarioVO usuario) throws CpfJaUtilizadoException,
	    CampoInvalidoException {
		
		this.validarCamposObrigatorios(usuario);
			
         return userBO.cadastrarUsuarioBO(usuario);
	}

	private void validarCamposObrigatorios(UsuarioVO u) throws CampoInvalidoException {
		
          String mensagemValidacao = "";
		
		if(u.getNome() == null || u.getNome().trim().length() < 3) {
			mensagemValidacao += " Nome inválido \n";
		} 
		
		if (u.getNome().contains("-")) {
			mensagemValidacao += " Nome inválido \n";
		}
		
			if (u.getNome().contains("=")) {
				mensagemValidacao += " Nome inválido \n";
					}
			
			if (u.getNome().contains("+")) {
				mensagemValidacao += " Nome inválido \n";
			}
			
			if (u.getNome().contains("-")) {
				mensagemValidacao += " Nome inválido \n";
			}
			
			if (u.getNome().contains("{}")) {
				mensagemValidacao += " Nome inválido \n";
			}
			
			if (u.getNome().contains(":;")) {
				mensagemValidacao += " Nome inválido \n";
			}
			
			if (u.getNome().contains("/|")) {
				mensagemValidacao += " Nome inválido \n";
			}
			
			if (u.getEmail() == null || u.getEmail().isEmpty()) {
				mensagemValidacao = "O email é obrigatório!";
			}
		
		mensagemValidacao += validarCpf(u);
		emailVerific = this.isValidEmail(u.getEmail());
		
		if (emailVerific == false) {
			mensagemValidacao = "O email é inválido!";
		}
		
		if(u.getLogin().isEmpty() || u.getLogin() == null) {
			mensagemValidacao += " Login é obrigatório!";
		}
		
		if(u.getSenha().isEmpty() || u.getSenha() == null) {
			mensagemValidacao += " Senha é obrigatória!";
		}
		
		if(u.getSenha().length() < 6 || u.getSenha().length() > 6) {
			mensagemValidacao += " Senha digitada inválida!";
			
		} 
		
		if(u.getSalariol() <= 0 ) {
			mensagemValidacao += " O salário é obrigatório!";
		}
		
		if(!mensagemValidacao.isEmpty()) {
			throw new CampoInvalidoException(mensagemValidacao);
		}
	}

	public static boolean isValidEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

	private String validarCpf(UsuarioVO u) {
	
         String validacao = "";
		
		if(u.getCpf() == null) {
			validacao += "CPF é obrigatório \n" ;
		}else {
			String cpfSemMascara = u.getCpf().replace(".", "");
			cpfSemMascara = u.getCpf().replace("-", "");
			u.setCpf(cpfSemMascara);
			if(u.getCpf().length() != 11) {
				validacao += "CPF deve possuir 11 dígitos\n" ;	
			}
		}
		
		return validacao;
	}

	public boolean atualizarUsuarioController(UsuarioVO userAtualizado) throws SQLException, CampoInvalidoException {
		
		this.validarSenha(userAtualizado);
		return userBO.atualizarUsuarioBO(userAtualizado);
	}

	private void validarSenha(UsuarioVO userAtualizado) throws SQLException, CampoInvalidoException {
		
		String senha = userDAO.consultarSenha(userAtualizado);
		String mensagemValidacao = "";
		
		if (userAtualizado.getSenha().equals(senha)) {
			throw new SQLException();
		} else if (userAtualizado.getSenha().length() < 6 || userAtualizado.getSenha().length() > 6) {
			mensagemValidacao = "Senha inválida!";
			throw new CampoInvalidoException(mensagemValidacao);
		} 
		
	}

	public UsuarioVO consultarUserPorNome(String text, String cpf) {
		
		return userDAO.consultarUsuarioPorNomeDAO(text, cpf);
	}

	public UsuarioVO consultarUserPorLogin(UsuarioVO userLogin) throws SQLException {
		
		return userDAO.consultarUserPorLogin(userLogin);
	}

}
