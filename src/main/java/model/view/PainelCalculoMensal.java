package model.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.vo.*;
import controller.*;
import javax.swing.JTextField;
import java.text.DecimalFormat;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;
import javax.swing.JTable;


public class PainelCalculoMensal extends JPanel {

		private JButton btnGerarCalculo;
		private JComponent btnNewButton;
		
		TabelaVO tabela = new TabelaVO();
		TabelaVO2 t = new TabelaVO2();
		TabelaController tabelaController = new TabelaController();
		DespesaController despController = new DespesaController();
		
		private ArrayList<TabelaVO> tabelas;
		private String[] nomesColunas = { "Nome", "Mês", "Ano", "Total restante", "Saldo final" };
	
	
	// CONVERSAO DE MES 
	   LocalDate dataAtual = LocalDate.now(); //MES ATUAL
	   Locale brasil = new Locale("pt", "BR");
	   DateTimeFormatter fout1 = DateTimeFormatter.ofPattern("EEEE", brasil);
	   String mes = (dataAtual.getMonth().getDisplayName(TextStyle.FULL, brasil));
	   private JNumberFormatField campSaldo;
	   DecimalFormat formato = new DecimalFormat("#,##0.00");
	   
		private JButton btn_salvarDados;
		private JComponent lblTitulo;
		private JLabel lblMesAtual;
		private JLabel visaoSaldo;
		private JTable tblCalculo;
		private JLabel visaoTotalDescont;
		private JLabel lblTotalDescontado;
		private JPanel panel;
		private JLabel lblSubtitulo;
		
		//Métodos usados no JTable
				private void limparTabela() {
					tblCalculo.setModel(new DefaultTableModel(new Object[][] { nomesColunas, }, 
							nomesColunas));
				}
				
				//Chamado sempre no "Buscar"
				private void atualizarTabelaMeses(UsuarioVO userOnline) {
					this.limparTabela();
					
					DefaultTableModel model = (DefaultTableModel) tblCalculo.getModel();
					//Preenche os valores na tabela linha a linha
					
						Object[] novaLinhaDaTabela = new Object[7];
						novaLinhaDaTabela[0] = userOnline.getNome();
						novaLinhaDaTabela[1] = t.getMes();
						novaLinhaDaTabela[2] = t.getAno();
						novaLinhaDaTabela[3] = t.getTotalRest();
						novaLinhaDaTabela[4] = t.getSaldoFinal();

						model.addRow(novaLinhaDaTabela);
				}

	/**
	 * Create the panel.
	 */
	public PainelCalculoMensal(final UsuarioVO userLogado) {
		setBackground(new Color(0, 255, 255));

		setLayout(null);
		
		campSaldo = new JNumberFormatField();
		campSaldo.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		campSaldo.setBounds(208, 93, 147, 20);
		add(campSaldo);
		campSaldo.setColumns(10);
		
		lblSubtitulo = new JLabel("");
		lblSubtitulo.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblSubtitulo.setBounds(533, 444, 136, 29);
		add(lblSubtitulo);
		
		visaoTotalDescont = new JLabel("Total descontado : ");
		visaoTotalDescont.setFont(new Font("Tahoma", Font.ITALIC, 11));
		visaoTotalDescont.setBounds(10, 357, 104, 14);
		add(visaoTotalDescont);
		
		lblTotalDescontado = new JLabel("");
		lblTotalDescontado.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblTotalDescontado.setBounds(106, 357, 112, 14);
		add(lblTotalDescontado);
		
		tblCalculo = new JTable();
		this.limparTabela();
		tblCalculo.setBounds(10, 223, 465, 116);
		
		add(tblCalculo);
		
		btnGerarCalculo = new JButton("");
		btnGerarCalculo.setIcon(new ImageIcon(PainelCalculoMensal.class.getResource("/icons/chart-histogram.png")));
		btnGerarCalculo.setBorder(null);
		btnGerarCalculo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		btnGerarCalculo.setBackground(new Color(0, 255, 255));
		btnGerarCalculo.addActionListener(new ActionListener() {
		
			    public void actionPerformed(ActionEvent e) {
			
			    String valor1 = campSaldo.getText().replace(".", "");
			    valor1 = valor1.replace(",", ".");
			    
			    double saldo = Double.parseDouble(valor1.replace("R$", ""));
			    String saldoG = formato.format(saldo);
			    
				double rest = userLogado.getSalariol() + saldo;
				String restG = formato.format(rest);
				
				double desc  = despController.calcularDescontoTotalController(userLogado);
				String descG = formato.format(desc);
				
				double saldoFinal = rest - desc; 
				String valorG = formato.format(saldoFinal);
				
				// CONVERSOES DE VALORES
				String saldoF = String.valueOf(saldoG).replace(".", ",");
				String restF = String.valueOf(restG).replace(".", ",");
				String descF = String.valueOf(descG).replace(".", ",");
				String saldoFinalF = String.valueOf(valorG).replace(".", ",");
				
			    //INSERCAO DA TABELA NO BANCO
				tabela.setAno(Integer.toString(dataAtual.getDayOfYear()));
				tabela.setIdUsuario(userLogado.getIdUsuario());
				tabela.setTotalRest(saldo);
			    tabela.setSaldoFinal(saldoFinal);
			    tabela.setMes(mes);
			    
			    //DADOS QUE SERÃO MOSTRADOS NA TABELA
			    lblTotalDescontado.setText("R$ "+descG);
			    t.setAno(Integer.toString(dataAtual.getYear()).toUpperCase());
			    t.setMes(mes);
			    t.setSaldoFinal("R$ "+valorG);
			    t.setTotalRest("R$ " +restG);
			    t.setIdUsuario(""+userLogado.getIdUsuario());
			    
			    lblSubtitulo.setText("Clique para salvar os dados");
			    
			    //Exibe valores na tabela
				atualizarTabelaMeses(userLogado);
			}

		});
		setLayout(null);
		btnGerarCalculo.setBounds(361, 92, 46, 21);
		add(btnGerarCalculo);
		
		btn_salvarDados = new JButton("");
		btn_salvarDados.setBorder(null);
		btn_salvarDados.setIcon(new ImageIcon(PainelCalculoMensal.class.getResource("/icons/diskette.png")));
		btn_salvarDados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int opcaoSelecionada = JOptionPane.showConfirmDialog(null,"Deseja salvar os dados do mês de junho? \n"
						+ "Confirme os dados de " +mes +" abaixo! \n"
								+ "Saldo restante : R$ " +formato.format(tabela.getTotalRest()) +"\n"
										+ "Saldo final : R$ " + formato.format(tabela.getSaldoFinal()));
				if (opcaoSelecionada == JOptionPane.YES_OPTION) {
					tabela = tabelaController.salvarDadosTabelaController(userLogado, tabela);
					JOptionPane.showMessageDialog(null, "Dados do mês: "+mes.toUpperCase() +
							"\nSalvos com sucesso!", "Gerenciamento-Mensal", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Dados do mês: "+mes.toUpperCase() +
							"\nNão serão salvos!", "Gerenciamento-Mensal", JOptionPane.INFORMATION_MESSAGE);	
				}
				
			}
		});
		btn_salvarDados.setBackground(new Color(0, 255, 255));
		btn_salvarDados.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		btn_salvarDados.setBounds(653, 444, 53, 29);
		add(btn_salvarDados);
		
		lblTitulo = new JLabel("Cálculo Mensal do mês de : ");
		lblTitulo.setFont(new Font("Source Serif Pro Semibold", Font.BOLD | Font.ITALIC, 15));
		lblTitulo.setBounds(114, 11, 205, 46);
		add(lblTitulo);
		
		lblMesAtual = new JLabel("");
		lblMesAtual.setFont(new Font("Source Serif Pro Semibold", Font.BOLD | Font.ITALIC, 15));
		lblMesAtual.setBounds(315, 17, 64, 35);
		add(lblMesAtual);
		lblMesAtual.setText(mes.toUpperCase());
		
		visaoSaldo = new JLabel("Informe o saldo restante :  ");
		visaoSaldo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		visaoSaldo.setBounds(51, 96, 181, 14);
		add(visaoSaldo);
		
	    panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 185, 721, 10);
		add(panel);
		
	}
}
