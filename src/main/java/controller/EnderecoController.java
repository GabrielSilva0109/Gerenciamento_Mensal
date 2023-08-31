package controller;

import model.vo.EnderecoVO;
import model.vo.TabelaVO;
import model.vo.UsuarioVO;

import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;

import exceptions.CampoInvalidoException;
import exceptions.EnderecoInvalidoException;
import model.bo.*;
import model.dao.EnderecoDAO;
import model.dao.ViaCEP;
import model.seletor.TabelaSeletor;

public class EnderecoController {

	EnderecoBO enderecoBO = new EnderecoBO();
	ViaCEP viaCep = new ViaCEP();
	EnderecoDAO endDAO = new EnderecoDAO();
	
	public EnderecoVO cadastrarEnderecoController(EnderecoVO endereco) throws CampoInvalidoException {
	
		validarCamposObrigatorios(endereco);
		
		return enderecoBO.cadastrarEnderecoBO(endereco);
	}

	private void validarCamposObrigatorios(EnderecoVO endereco) throws CampoInvalidoException {

       String mensagemValidacao = "";
		
		mensagemValidacao += validarString(endereco.getCep(), "CEP");
		mensagemValidacao += validarString(endereco.getLogradouro(), "Rua");
		mensagemValidacao += validarString(endereco.getBairro(), "Bairro");
		mensagemValidacao += validarString(endereco.getLocalidade(), "Cidade");
		mensagemValidacao += validarString(endereco.getUf(), "Estado");
		
		if(!mensagemValidacao.isEmpty()) {
			throw new CampoInvalidoException(mensagemValidacao);
		}
		
	}

	private String validarString(String texto, String string) {
		
        boolean valido = (texto != null) && !texto.trim().isEmpty();
		
		if(valido) {
			return "";
		}else {
			return 	"- " + string + "\n" ;
		}
	}

	public boolean atualizarEndController(EnderecoVO endAtualizado) {
		
		return enderecoBO.atualizarEnderecoBO(endAtualizado);
	}

	public EnderecoVO consultarEnderecoPorId(EnderecoVO endereco) {
		
		return enderecoBO.consultarEnderecoBO(endereco);
	}
	
	public EnderecoVO buscarViaCepController(String cep) throws EnderecoInvalidoException, CampoInvalidoException, IOException {
		
		this.validarCEP(cep);
		return viaCep.gerarEnderecoViaCEP(cep);
		
	}

	private void validarCEP(String cep) throws EnderecoInvalidoException, SocketException {
		
		String mensagemValidacao = "";
		
		if (cep.trim().isEmpty() || cep.trim().isBlank()) {
			mensagemValidacao = "Endereço não encontrado!";
		} 
		
		if (cep == null) {
			mensagemValidacao = "Endereço não encontrado!";
		}
		
		if(!mensagemValidacao.isEmpty()) {
			throw new SocketException(mensagemValidacao);
		}
		
	}

	public boolean excluirEndController(UsuarioVO userOnline) {
	
		return endDAO.excluirEndDAO(userOnline);
	}


}
