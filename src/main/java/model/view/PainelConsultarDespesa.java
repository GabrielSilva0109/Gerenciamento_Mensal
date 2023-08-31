package model.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.seletor.TabelaSeletor;
import model.vo.*;
import controller.*;
import exceptions.CampoInvalidoException;

import javax.swing.JTextField;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EtchedBorder;
import javax.swing.JTable;

public class PainelConsultarDespesa extends JPanel {
	private JComponent btnNewButton_1;
	
	DespesaVO despesaVO = new DespesaVO();
	DespesaController despController = new DespesaController();
	private JTextField campDespDigitada;
	private JLabel visaoNome;
	DecimalFormat formato = new DecimalFormat("#,##0.00");

	private JLabel lblTitulo;

	private JButton btnConsultar;

	private JLabel lblErro;

	private JLabel lblIcon;
	
	//Atributos da tela (componentes visuais)
			private JTable tblDespesas;
			private JButton btnBuscar;
			
			private TabelaVO tabelaSelecionada = new TabelaVO();
			private TabelaController tabelaController = new TabelaController();
			
			private ArrayList<DespesaVO> despesas = null;
			private String[] nomesColunas = { "Ordem", "Nome da despesa", "Valor"};
			private JLabel lblMesFiltro;
			private JComboBox cbMeses;
			private JButton btnFiltrar;

			private JPanel panel;
			private DespesaVO despesaSelecionada = new DespesaVO();

			private JComponent btnExcluir;
			private JButton btnExclusao;
		
			//Métodos usados no JTable
			private void limparTabela() {
				tblDespesas.setModel(new DefaultTableModel(new Object[][] { nomesColunas, }, 
						nomesColunas));
			}

			//Chamado sempre no "Buscar"
			private void atualizarTabelaDespesas() {
				this.limparTabela();
				
				DefaultTableModel model = (DefaultTableModel) tblDespesas.getModel();
				//Preenche os valores na tabela linha a linha
				for (DespesaVO desp : despesas) {
					Object[] novaLinhaDaTabela = new Object[3];
					novaLinhaDaTabela[0] = desp.getIdDespesa();
					novaLinhaDaTabela[1] = desp.getDespNome();
					novaLinhaDaTabela[2] = "R$"+formato.format(desp.getValor());

					model.addRow(novaLinhaDaTabela);
				}
			}
			
			//Chamado sempre no "Buscar"
			private void atualizarTabelaDespesas2() {
				this.limparTabela();
				
				DefaultTableModel model = (DefaultTableModel) tblDespesas.getModel();
				//Preenche os valores na tabela linha a linha
				
					Object[] novaLinhaDaTabela = new Object[3];
					novaLinhaDaTabela[0] = despesaVO.getIdDespesa();
					novaLinhaDaTabela[1] = despesaVO.getDespNome();
					novaLinhaDaTabela[2] = "R$"+formato.format(despesaVO.getValor());

					model.addRow(novaLinhaDaTabela);
			}

	/**
	 * Create the panel.
	 * @param userOnline 
	 */
	public PainelConsultarDespesa(final UsuarioVO userOnline) {
		
		setBackground(new Color(0, 255, 255));
		setLayout(null);
		
		lblTitulo = new JLabel(" Consultar de despesa ");
		lblTitulo.setFont(new Font("Source Serif Pro Semibold", Font.BOLD | Font.ITALIC, 16));
		lblTitulo.setBounds(204, 11, 183, 23);
		add(lblTitulo);
		
		tblDespesas = new JTable();
		tblDespesas.setBounds(20, 170, 445, 274);
		this.limparTabela();
		add(tblDespesas);
		
		campDespDigitada = new JTextField();
		campDespDigitada.setBorder(null);
		campDespDigitada.setBounds(135, 88, 173, 20);
		campDespDigitada.setColumns(10);
		
		// Adiciona um ouvinte de eventos de teclado ao campo
		campDespDigitada.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                
                // Verifica se o caractere é um número
                if (Character.isDigit(c)) {
                    e.consume(); // Impede que o caractere seja inserido
                }
            }
        });
		add(campDespDigitada);
		
		visaoNome = new JLabel("Nome da despesa :");
		visaoNome.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		visaoNome.setBounds(20, 91, 115, 14);
		add(visaoNome);
		
		tblDespesas.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				int indiceSelecionado = tblDespesas.getSelectedRow();

				if (indiceSelecionado > 0) {
					btnExclusao.setEnabled(true);
					despesaSelecionada = despesas.get(indiceSelecionado - 1);
				} else {
					btnExclusao.setEnabled(false);
				}
			}
		});

		
	    panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 145, 700, 14);
		add(panel);
		
		lblErro = new JLabel("");
		lblErro.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblErro.setForeground(new Color(255, 0, 0));
		lblErro.setBounds(321, 444, 195, 14);
		add(lblErro);
		
		btnExclusao = new JButton("");
		btnExclusao.setBackground(new Color(0, 255, 255));
		btnExclusao.setEnabled(false);
		btnExclusao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			
				int opcaoSelecionada = JOptionPane.showConfirmDialog(null, "Confirma a exclusão da despesa selecionada?");

				if(opcaoSelecionada == JOptionPane.YES_OPTION) {
					
					despController.removerDespesaController(userOnline, despesaSelecionada.getDespNome());
					JOptionPane.showMessageDialog(null, "Despesa removida com sucesso!");
					despesas = (ArrayList<DespesaVO>) despController.consultarTodosController(userOnline, campDespDigitada.getText());
					atualizarTabelaDespesas();
					
				}
				
			}
		});
		btnExclusao.setIcon(new ImageIcon(PainelConsultarDespesa.class.getResource("/icons/remove.png")));
		btnExclusao.setBorder(null);
		btnExclusao.setBounds(468, 171, 32, 23);
		add(btnExclusao);
		
		btnConsultar = new JButton("");
		btnConsultar.setBorder(null);
		btnConsultar.setBackground(new Color(255, 255, 255));
		btnConsultar.setIcon(new ImageIcon(PainelConsultarDespesa.class.getResource("/icons/filter16px.png")));
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				    btnExclusao.setVisible(false);
				    btnExclusao.setEnabled(false);
				    lblErro.setText("");
				    tblDespesas.setRowSelectionAllowed(false);
				    tblDespesas.setEnabled(false);

				    despesaSelecionada = null;
				    
				    	despesaVO = despController.consultarDespesaController(campDespDigitada.getText(), userOnline);
						
						if (despesaVO.getIdDespesa() != 0) {
						     atualizarTabelaDespesas2();
						} else {
							 lblErro.setText("Despesa não encontrada!");
						}	
			}
		});
		btnConsultar.setBounds(303, 88, 38, 20);
		add(btnConsultar);
		
		lblIcon = new JLabel("");
		lblIcon.setIcon(new ImageIcon(PainelConsultarDespesa.class.getResource("/icons/bank.png")));
		lblIcon.setBounds(655, 455, 32, 32);
		add(lblIcon);
		
		JButton btnConsultarTodas = new JButton("Todas");
		btnConsultarTodas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tblDespesas.setRowSelectionAllowed(true);
				tblDespesas.setEnabled(true);
				btnExclusao.setVisible(true);
				btnExclusao.setEnabled(false);
				lblErro.setText("");

				despesas = (ArrayList<DespesaVO>) despController.consultarTodosController(userOnline, campDespDigitada.getText());
				atualizarTabelaDespesas();
				
				if (despesas.size() <= 0) {
					lblErro.setText("Nenhuma despesa cadastrada!");	
				}
			}
		});
		btnConsultarTodas.setBorder(null);
		btnConsultarTodas.setBackground(new Color(255, 255, 255));
		btnConsultarTodas.setFont(new Font("Tahoma", Font.ITALIC, 11));
		btnConsultarTodas.setBounds(351, 88, 62, 20);
		add(btnConsultarTodas);
		
		
	}
}