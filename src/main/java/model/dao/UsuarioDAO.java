package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import model.vo.UsuarioVO;

public class UsuarioDAO {

	public UsuarioVO realizarLoginDAO(UsuarioVO userLogado) throws SQLException {
		
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		UsuarioVO usuarioVO = new UsuarioVO();
		
		String query = "SELECT * "
				+ "FROM USUARIO u "
				+ "WHERE u.login = '" + userLogado.getLogin() + "' "
				+ "AND u.senha = '" + userLogado.getSenha() + "' ";
		
		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
				usuarioVO.setIdUsuario(Integer.parseInt(resultado.getString(1)));
				usuarioVO.setTipoUsuario(Integer.parseInt(resultado.getString(2)));
				usuarioVO.setNome(resultado.getString(3));
				usuarioVO.setCpf(resultado.getString(4));
				usuarioVO.setDataNasci(LocalDate.parse(resultado.getString(5),
						DateTimeFormatter.ofPattern("yyy-MM-dd")));
				usuarioVO.setEmail(resultado.getString(6));
				usuarioVO.setSalariol(Double.parseDouble(resultado.getString(7)));
				usuarioVO.setLogin(resultado.getString(8));
				usuarioVO.setSenha(resultado.getString(9));
				
			}
		} catch (SQLException erro) {
			
			throw new SQLException();
			
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		
		return usuarioVO;
	}
	
	public UsuarioVO cadastrarUsuarioDAO(UsuarioVO usuario) {
		String query ="INSERT INTO usuario (tipousuario, nome, cpf, datanasci, email, salariol, login,"
				+ " senha ) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			pstmt.setInt(1, usuario.getTipoUsuario());
			pstmt.setString(2, usuario.getNome());
			pstmt.setString(3, usuario.getCpf());
			pstmt.setObject(4, usuario.getDataNasci());
			pstmt.setString(5, usuario.getEmail());
			pstmt.setDouble(6, usuario.getSalariol());
			pstmt.setString(7, usuario.getLogin());
			pstmt.setString(8, usuario.getSenha());
			pstmt.execute();
			ResultSet resultado = pstmt.getGeneratedKeys();	
			if(resultado.next()) {
				usuario.setIdUsuario(Integer.parseInt(resultado.getString(1)));
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar a query do método cadastrarUsuarioDAO");
			System.out.println("Erro: " + erro.getMessage());
		}finally {
			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);
		}
		return usuario;
	
	}

	public boolean excluirContaDAO(UsuarioVO userOnline) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean retorno = false;
		
		String query = "DELETE FROM usuario "
		             + "WHERE idusuario = " + userOnline.getIdUsuario();
		
		try {
			if(stmt.executeUpdate(query) == 1) {
				retorno = true;                                                                      
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar a query do método excluirContaDAO");
			System.out.println("Erro: " + erro.getMessage());	
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return retorno;
	}
	
	public boolean atualizarUsuarioDAO(UsuarioVO usuarioAtualizado) {
		
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean retorno = false;
		
		String query = "UPDATE usuario SET nome = '" + usuarioAtualizado.getNome()
				+ "', email = '" + usuarioAtualizado.getEmail()
				+ "', salariol = " + usuarioAtualizado.getSalariol()
				+ ", login = '" + usuarioAtualizado.getLogin()
				+ "', senha = '" + usuarioAtualizado.getSenha()
				+ "' WHERE idusuario = " + usuarioAtualizado.getIdUsuario();
		 
		try {
			if(stmt.executeUpdate(query) == 1) {
				retorno = true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar a query do método atualizarUsuarioDAO");
			System.out.println("Erro: " + erro.getMessage());	
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return retorno;
		
	}

	public boolean cpfJaUtilizado(String cpf) {
		
		boolean cpfJaUtilizado = false;
		Connection conexao = Banco.getConnection();
		String sql = " select count(*) from usuario "
				   + " where cpf = ? ";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			query.setString(1, cpf);
			ResultSet resultado = query.executeQuery();
			
			if(resultado.next()) {
				cpfJaUtilizado = resultado.getInt(1) > 0;
			}
		}catch (Exception e) {
			System.out.println("Erro ao verificar uso do CPF " + cpf 
					+ "\n Causa:" + e.getMessage());
		}finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		
		return cpfJaUtilizado;
		
	}

	public UsuarioVO consultarUsuarioPorNomeDAO(String text, String cpf) {
		
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		UsuarioVO usuarioConsultado = new UsuarioVO();
		
		String query = "SELECT u.idusuario, u.tipousuario, u.nome, u.cpf, u.datanasci, "
				+ "u.email, u.salariol, u.login, u.senha "
				+ "FROM USUARIO u "
				+ "WHERE u.nome = '" + text +"' "
				+ "AND u.cpf = " + cpf;
		
		try {
			resultado = stmt.executeQuery(query);
			while(resultado.next()) {
				usuarioConsultado.setIdUsuario(Integer.parseInt(resultado.getString(1)));
				usuarioConsultado.setTipoUsuario(Integer.parseInt(resultado.getString(2)));
				usuarioConsultado.setNome(resultado.getString(3));
				usuarioConsultado.setCpf(resultado.getString(4));
				usuarioConsultado.setDataNasci(LocalDate.parse(resultado.getString(5), 
						DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				usuarioConsultado.setEmail(resultado.getString(6));
				usuarioConsultado.setSalariol(resultado.getDouble(7));
				usuarioConsultado.setLogin(resultado.getString(8));
				usuarioConsultado.setSenha(resultado.getString(9));
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar a query do método consultarUsuarioPorNomeDAO");
			System.out.println("Erro: " + erro.getMessage());
		}finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return usuarioConsultado;

	}

	public String consultarSenha(UsuarioVO userAtualizado) {
		
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		String senhaConsultada = "";
		
		String query = "SELECT u.senha "
				+ "FROM USUARIO u "
				+ "WHERE u.idusuario = " + userAtualizado.getIdUsuario();
				
		try {
			resultado = stmt.executeQuery(query);
			while(resultado.next()) {
				senhaConsultada = resultado.getString(1);
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar a query do método consultarSenha");
			System.out.println("Erro: " + erro.getMessage());
		}finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return senhaConsultada;
	
	}

	public UsuarioVO consultarUserPorLogin(UsuarioVO userLogin) throws SQLException {
		
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		
		String query = "SELECT * "
				+ "FROM USUARIO u "
				+ "WHERE u.login = '" + userLogin.getLogin() + "' ";
				
		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
				userLogin.setIdUsuario(Integer.parseInt(resultado.getString(1)));
				userLogin.setTipoUsuario(Integer.parseInt(resultado.getString(2)));
				userLogin.setNome(resultado.getString(3));
				userLogin.setCpf(resultado.getString(4));
				userLogin.setDataNasci(LocalDate.parse(resultado.getString(5),
						DateTimeFormatter.ofPattern("yyy-MM-dd")));
				userLogin.setEmail(resultado.getString(6));
				userLogin.setSalariol(Double.parseDouble(resultado.getString(7)));
				userLogin.setLogin(resultado.getString(8));
				userLogin.setSenha(resultado.getString(9));
			}
		} catch (SQLException erro) {
			
			throw new SQLException();
			
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return userLogin;
	}

}
