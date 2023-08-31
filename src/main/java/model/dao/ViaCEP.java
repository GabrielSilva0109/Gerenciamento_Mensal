package model.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.Gson;

import exceptions.CampoInvalidoException;
import model.vo.*;

  public class ViaCEP {
	  
	  EnderecoVO endereco = new EnderecoVO();
	  
	  public EnderecoVO gerarEnderecoViaCEP(String cep) throws CampoInvalidoException, IOException {
		  
        try {
        	this.validarCep(cep);
            // Monta a URL de requisição
            URL url = new URL("https://viacep.com.br/ws/" + cep + "/json/");

            // Abre a conexão HTTP
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Obtém a resposta da requisição
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Parseia a resposta JSON
            String json = response.toString();
            // Trate a string JSON como necessário (por exemplo, utilizando bibliotecas como o Gson ou o Jackson)

            // Exemplo de extração dos dados do JSON
            String logradouro = ""; // variável para armazenar o logradouro
            // Parseia o JSON para obter o logradouro
            // Aqui você pode utilizar bibliotecas como Gson ou Jackson para facilitar a extração dos dados
            // Exemplo utilizando Gson:
             Gson gson = new Gson();
             
             endereco = gson.fromJson(json, EnderecoVO.class);
             logradouro = endereco.getLogradouro();

            // Fecha a conexão
            connection.disconnect();
        } catch (IOException e) {
            //e.printStackTrace();
        	throw new IOException();
        }
		return endereco;
    }

	private void validarCep(String cep) throws CampoInvalidoException {
		
		String mensagem = "";
		
		if (cep.trim().isBlank()) {
			mensagem = "Digite o CEP!";
		} else if (cep.trim().isEmpty()) {
			mensagem = "Digite o CEP!";
		} else if (cep == null) {
			mensagem = "Digite o CEP!";
		} 
		
		if (!mensagem.trim().isEmpty()) {
			throw new CampoInvalidoException(mensagem);
		}
		
	}
  }