package model.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.EnderecoController;
import exceptions.CampoInvalidoException;
import exceptions.EnderecoInvalidoException;
import controller.TabelaController;
import model.seletor.TabelaSeletor;
import model.vo.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

import exceptions.EnderecoInvalidoException;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JFileChooser;

public class PainelTabelaCompleta extends JPanel {

	//Atributos da tela (componentes visuais)
		private JTable tblTabelas;
		private JButton btnBuscar;
		private JButton btnEditar;
		private JButton btnExcluir;
		
		//Objeto usado para armazenar o endereço que o usuário selecionar na tabela (tblEnderecos)
		private TabelaVO tabelaSelecionada = new TabelaVO();
		private TabelaController tabelaController = new TabelaController();
		//Lista para armezenar os endereços consultados no banco
		private ArrayList<TabelaVO> tabelas;
		private String[] nomesColunas = { "Nome", "Mês", "Ano", "Total restante", "Saldo final" };
		private TabelaSeletor tabSeletor = new TabelaSeletor();
		private JLabel lblMesFiltro;
		private JComboBox cbMeses;
		private JButton btnFiltrar;
		private final int TAMANHO_PAGINA = 5;
		private int paginaAtual = 1;
		private int totalPaginas = 0;
		private TabelaSeletor seletor = new TabelaSeletor();
		private JComboBox cbAnos;
		private JLabel lblErro;
		private JLabel lblNewLabel;
	
		//Métodos usados no JTable
		private void limparTabela() {
			tblTabelas.setModel(new DefaultTableModel(new Object[][] { nomesColunas, }, 
					nomesColunas));
		}

		//Chamado sempre no "Buscar"
		private void atualizarTabelaMeses(UsuarioVO userOnline) {
			this.limparTabela();
			
			DefaultTableModel model = (DefaultTableModel) tblTabelas.getModel();
			//Preenche os valores na tabela linha a linha
			for (TabelaVO t : tabelas) {
				Object[] novaLinhaDaTabela = new Object[nomesColunas.length];
				novaLinhaDaTabela[0] = userOnline.getNome();
				novaLinhaDaTabela[1] = t.getMes();
				novaLinhaDaTabela[2] = t.getAno();
				novaLinhaDaTabela[3] = t.getTotalRest();
				novaLinhaDaTabela[4] = t.getSaldoFinal();

				model.addRow(novaLinhaDaTabela);
			}
		}
		
	/**
	 * Create the panel.
	 */
	public PainelTabelaCompleta(final UsuarioVO userOnline) {
		
		setBackground(new Color(0, 255, 255));
		setLayout(null);
		btnBuscar = new JButton("Tabela completa ");
		btnBuscar.setBorder(null);
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		btnBuscar.setBackground(new Color(0, 255, 255));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		          atualizarTabelaMesesTodos(userOnline);
			
			}

		});
		btnBuscar.setBounds(517, 22, 117, 23);
		add(btnBuscar);
		
		String[] meses = {"Janeiro", "Ferereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro"
				,"Novembro", "Dezembro"};
		
		String[] anos = {"2022", "2023"};
		
		tblTabelas = new JTable();
		tblTabelas.setEnabled(false);
		tblTabelas.setForeground(new Color(0, 0, 0));
		this.limparTabela();
		tblTabelas.setBounds(10, 56, 655, 383);
		
		add(tblTabelas);
		
		btnFiltrar = new JButton("");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarTabelaComFiltro(userOnline);
				
			}
		});
		btnFiltrar.setBorder(null);
		btnFiltrar.setIcon(new ImageIcon(PainelTabelaCompleta.class.getResource("/icons/filter.png")));
		btnFiltrar.setBackground(new Color(0, 255, 255));
		btnFiltrar.setBounds(632, 22, 33, 23);
		add(btnFiltrar);
		
		cbMeses = new JComboBox(meses);
		cbMeses.setBorder(null);
		cbMeses.setFont(new Font("Tahoma", Font.ITALIC, 11));
		cbMeses.setBackground(new Color(192, 192, 192));
		cbMeses.setBounds(143, 22, 100, 22);
		add(cbMeses);
		
		lblErro = new JLabel("");
		lblErro.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblErro.setForeground(new Color(255, 0, 0));
		lblErro.setBounds(271, 444, 224, 23);
		add(lblErro);
		
		lblMesFiltro = new JLabel("Mês");
		lblMesFiltro.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblMesFiltro.setBounds(143, 11, 46, 14);
		add(lblMesFiltro);
		
		cbAnos = new JComboBox(anos);
		cbAnos.setFont(new Font("Tahoma", Font.ITALIC, 11));
		cbAnos.setBorder(null);
		cbAnos.setBackground(new Color(192, 192, 192));
		cbAnos.setBounds(281, 22, 91, 22);
		add(cbAnos);
		
		JLabel lblAnoFiltro = new JLabel("Ano");
		lblAnoFiltro.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblAnoFiltro.setBounds(281, 11, 46, 14);
		add(lblAnoFiltro);
		
		JButton btnGerarPlanilha = new JButton("");
		btnGerarPlanilha.setIcon(new ImageIcon(PainelTabelaCompleta.class.getResource("/icons/xls.png")));
		btnGerarPlanilha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser janelaSelecaoDestinoArquivo = new JFileChooser();
				janelaSelecaoDestinoArquivo.setDialogTitle("Selecione um destino para a planilha...");
				janelaSelecaoDestinoArquivo.setBackground(new Color(255, 255, 255));
				
				int opcaoSelecionada = janelaSelecaoDestinoArquivo.showSaveDialog(null);
				if (opcaoSelecionada == JFileChooser.APPROVE_OPTION) {
					String caminhoEscolhido = janelaSelecaoDestinoArquivo.getSelectedFile().getAbsolutePath();
					String resultado;
					try {
						resultado = tabelaController.gerarPlanilha(tabelas, caminhoEscolhido);
						JOptionPane.showMessageDialog(null, resultado);
					} catch (CampoInvalidoException e1) {
						JOptionPane.showConfirmDialog(null, e1.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		btnGerarPlanilha.setBorder(null);
		btnGerarPlanilha.setBackground(new Color(0, 255, 255));
		btnGerarPlanilha.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		btnGerarPlanilha.setBounds(583, 444, 40, 39);
		add(btnGerarPlanilha);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PainelTabelaCompleta.class.getResource("/icons/bank.png")));
		lblNewLabel.setBounds(632, 450, 33, 23);
		add(lblNewLabel);
		
		atualizarQuantidadePaginas();
		
	}
	
	
	protected void atualizarTabelaMesesTodos(UsuarioVO userOnline) {
		
		lblErro.setText("");
		this.limparTabela();

		TabelaVO tabelaVO = new TabelaVO();
		tabelaVO.setIdUsuario(userOnline.getIdUsuario());
		ArrayList<TabelaVO> tabelaCompleta;
		
		TabelaController tabelaController = new TabelaController();
	    tabelaCompleta = (ArrayList<TabelaVO>) tabelaController.consultarTodasController(tabelaVO);
		
		DefaultTableModel model = (DefaultTableModel) tblTabelas.getModel();
		//Preenche os valores na tabela linha a linha
		for (TabelaVO t : tabelaCompleta) {
			Object[] novaLinhaDaTabela = new Object[7];
			novaLinhaDaTabela[0] = userOnline.getNome();
			novaLinhaDaTabela[1] = t.getMes();
			novaLinhaDaTabela[2] = t.getAno();
			novaLinhaDaTabela[3] = t.getTotalRest();
			novaLinhaDaTabela[4] = t.getSaldoFinal();

			model.addRow(novaLinhaDaTabela);
		}
		
	}

	private void atualizarQuantidadePaginas() {
		//Cálculo do total de páginas (poderia ser feito no backend)
		int totalRegistros = tabelaController.contarTotalRegistrosComFiltros(seletor);
		
		//QUOCIENTE da divisão inteira
		totalPaginas = totalRegistros / TAMANHO_PAGINA;
		
		//RESTO da divisão inteira
		if(totalRegistros % TAMANHO_PAGINA > 0) { 
			totalPaginas++;
		}
	}
	 protected void buscarTabelaComFiltro(UsuarioVO userOnline) {
		lblErro.setText("");
		tabSeletor = new TabelaSeletor();
		tabSeletor.setLimite(TAMANHO_PAGINA);
		tabSeletor.setPagina(paginaAtual);
		tabSeletor.setMes((String) cbMeses.getSelectedItem());
		tabSeletor.setAno((String) cbAnos.getSelectedItem());
		tabelas = (ArrayList<TabelaVO>) tabelaController.consultarComFiltros(tabSeletor);
		
		if (tabelas.size() == 0) {
			lblErro.setText("Nenhum dado encontrado!");
		}
		
		atualizarTabelaMeses(userOnline);
		atualizarQuantidadePaginas();
	}
}