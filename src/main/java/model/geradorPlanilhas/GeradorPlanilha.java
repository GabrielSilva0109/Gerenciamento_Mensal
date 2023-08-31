package model.geradorPlanilhas;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import model.vo.EnderecoVO;
import model.vo.TabelaVO;


public class GeradorPlanilha {
	
	public String gerarPlanilhaClientes(List<TabelaVO> tabelas, String destinoArquivo) {
		HSSFWorkbook arquivoExcel = new HSSFWorkbook();
		HSSFSheet abaPlanilha = arquivoExcel.createSheet("Tabela");
		
		HSSFRow linhaCabecalho = abaPlanilha.createRow(0);
		linhaCabecalho.createCell(0).setCellValue("Mês");
		linhaCabecalho.createCell(1).setCellValue("Ano");
		linhaCabecalho.createCell(2).setCellValue("Total restante");
		linhaCabecalho.createCell(3).setCellValue("Saldo final");
		
		int contadorLinhas = 1;
		for(TabelaVO t: tabelas) {
			HSSFRow novaLinha = abaPlanilha.createRow(contadorLinhas);
			novaLinha.createCell(0).setCellValue(t.getMes());
			novaLinha.createCell(1).setCellValue(t.getAno());
			novaLinha.createCell(2).setCellValue(t.getTotalRest());
			novaLinha.createCell(3).setCellValue(t.getSaldoFinal());
			contadorLinhas++;
		}
		
		return salvarNoDisco(arquivoExcel, destinoArquivo);
	}
	
	public String gerarPlanilhaEnderecos(List<EnderecoVO> enderecos, String destinoArquivo) {
		//TODO implementar
		
		//Criar arquivo da planilha (workbook)
		HSSFWorkbook arquivoExcel = null;
		
		//Criar aba da planilha (sheet)
		
		//Criar linha de cabeçalho
		
		//Criar linhaS com os dados dos endereços
		
		return salvarNoDisco(arquivoExcel, destinoArquivo);
	}
	
	private String salvarNoDisco(HSSFWorkbook planilha, String caminhoArquivo) {
		String mensagem = "";
		FileOutputStream saida = null;
		String extensao = ".xls";

		try {
			saida = new FileOutputStream(new File(caminhoArquivo + extensao));
			planilha.write(saida);
			mensagem = "Planilha gerada com sucesso!";
		} catch (FileNotFoundException e) {
			mensagem = "Erro ao tentar salvar planilha (sem acesso): " + caminhoArquivo + extensao;
			System.out.println("Causa: " + e.getMessage());
		} catch (IOException e) {
			mensagem = "Erro de I/O ao tentar salvar planilha em: " + caminhoArquivo + extensao;
			System.out.println("Causa: " + e.getMessage());
		} finally {
			if (saida != null) {
				try {
					saida.close();
					planilha.close();
				} catch (IOException e) {
					mensagem = "Erro ao tentar salvar planilha em: " + caminhoArquivo + extensao;
					System.out.println("Causa: " + e.getMessage());
				}
			}
		}

		return mensagem;
	}
}
