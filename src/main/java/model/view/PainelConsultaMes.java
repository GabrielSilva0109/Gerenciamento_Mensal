package model.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.vo.*;
import controller.*;
import exceptions.CampoInvalidoException;

import javax.swing.JProgressBar;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JComboBox;

public class PainelConsultaMes extends JPanel {
	private JComponent btnNewButton;
	private JLabel lblNewLabel_1;
	private JButton btnConsultar;
	private JLabel lblTitulo;
	
	TabelaVO tabelaVO = new TabelaVO();
	TabelaController tabelaController = new TabelaController();
	DecimalFormat formato = new DecimalFormat("#,##0.00");
	
	private JTextField mesDigitadoCamp;
	private JPanel panel;
	private JLabel lblSubTitulo;
	private JLabel lblMes;
	private JLabel lblNewLabel_3;
	private JLabel visaoAno;
	private JLabel visaoTotalRestante;
	private JLabel visaoSaldoFinal;
	private JLabel lblIcon;
	private JLabel lblSaldoFinal;
	private JLabel lblErro;
	private JComboBox cbAnos;
	private JButton btnRemover;

	/**
	 * Create the panel.
	 * @param userOnline 
	 */
	public PainelConsultaMes(final UsuarioVO userOnline) {
		setBackground(new Color(0, 255, 255));

        setLayout(null);
		
		lblNewLabel_1 = new JLabel("Consulta de mês regular");
		lblNewLabel_1.setFont(new Font("Source Serif Pro Semibold", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_1.setBounds(151, 11, 236, 30);
		add(lblNewLabel_1);
		
		lblMes = new JLabel("New label");
		lblMes.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblMes.setBounds(157, 150, 139, 14);
		add(lblMes);
		lblMes.setText("");
		
        String[] anos = {"2022", "2023"};
		
		cbAnos = new JComboBox(anos);
		cbAnos.setBackground(new Color(192, 192, 192));
		cbAnos.setBounds(390, 76, 75, 22);
		add(cbAnos);
		
		btnRemover = new JButton("");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int opcaoSelecionada = JOptionPane.showConfirmDialog(null, "Confirma a exclusão dos dados mensais selecionados?");

				if(opcaoSelecionada == JOptionPane.YES_OPTION) {
					
					tabelaController.removerTabelaController(userOnline, tabelaVO);
					JOptionPane.showMessageDialog(null, "Despesa removida com sucesso!");
					
				}
				
			}
		});
		btnRemover.setBackground(new Color(0, 255, 255));
		//btnRemover.setEnabled(false);
		btnRemover.setBorder(null);
		btnRemover.setIcon(new ImageIcon(PainelConsultaMes.class.getResource("/icons/remove.png")));
		btnRemover.setBounds(614, 456, 34, 30);
		add(btnRemover);
		
		btnConsultar = new JButton("");
		btnConsultar.setBorder(null);
		btnConsultar.setIcon(new ImageIcon(PainelConsultaMes.class.getResource("/icons/loupe.png")));
		btnConsultar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		btnConsultar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				lblErro.setText("");
				String mesConsultado = mesDigitadoCamp.getText();
				
				tabelaVO.setMes(mesConsultado);
				tabelaVO.setIdUsuario(userOnline.getIdUsuario());
				
				try {
					tabelaVO = tabelaController.consultarMesController(tabelaVO, (String) cbAnos.getSelectedItem());
                    if (tabelaVO.getIdTabela() == 0) {
						
                    	lblErro.setText("Nenhum dado encontrado!");
						
					} else {
						
						lblMes.setText(tabelaVO.getMes());
					
						visaoAno.setText("Ano:    "+ tabelaVO.getAno());
						
						visaoSaldoFinal.setText("Saldo final:   R$ "+ formato.format(tabelaVO.getSaldoFinal()));
						
						visaoTotalRestante.setText("Total restante no mês:   R$ " + formato.format(tabelaVO.getTotalRest()));
					}
					
				} catch (CampoInvalidoException e1) {
					
					lblErro.setText("O nome é obrigatório!");
					
				}
				
				btnRemover.setEnabled(true);
			}
		});
		btnConsultar.setBackground(new Color(255, 255, 255));
		btnConsultar.setBounds(346, 76, 34, 20);
		add(btnConsultar);
		
		lblTitulo = new JLabel("Informe o mês a ser consultado : ");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		lblTitulo.setBounds(58, 73, 222, 23);
		add(lblTitulo);
		
		lblErro = new JLabel("");
		lblErro.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblErro.setForeground(new Color(255, 0, 0));
		lblErro.setBounds(322, 98, 139, 20);
		add(lblErro);
	
		mesDigitadoCamp = new JTextField();
		mesDigitadoCamp.setFont(new Font("Tahoma", Font.ITALIC, 11));
		mesDigitadoCamp.setBorder(null);
		mesDigitadoCamp.setBounds(233, 76, 117, 20);
		mesDigitadoCamp.setColumns(10);
		
		// Adiciona um ouvinte de eventos de teclado ao campo//IMPEDE NUMEROS
		mesDigitadoCamp.addKeyListener(new KeyAdapter() {
		            @Override
		            public void keyTyped(KeyEvent e) {
		                char c = e.getKeyChar();
		                
		                // Verifica se o caractere é um número
		                if (Character.isDigit(c)) {
		                    e.consume(); // Impede que o caractere seja inserido
		                }
		            }
		        });
				add(mesDigitadoCamp);
		
		lblSaldoFinal = new JLabel("");
		lblSaldoFinal.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblSaldoFinal.setBounds(135, 301, 117, 14);
		add(lblSaldoFinal);
		
		panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 129, 680, 10);
		add(panel);
		
	    lblSubTitulo = new JLabel("Mês consultado : ");
		lblSubTitulo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblSubTitulo.setBounds(58, 150, 117, 14);
		add(lblSubTitulo);
		
		lblNewLabel_3 = new JLabel("____________________________________________________");
		lblNewLabel_3.setBounds(43, 160, 418, 14);
		add(lblNewLabel_3);
		
		visaoAno = new JLabel("Ano : ");
		visaoAno.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		visaoAno.setBounds(65, 213, 198, 14);
		add(visaoAno);
		
		visaoTotalRestante = new JLabel("Total restante no mês :  ");
		visaoTotalRestante.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		visaoTotalRestante.setBounds(65, 256, 322, 14);
		add(visaoTotalRestante);
		
		visaoSaldoFinal = new JLabel("Saldo final :  ");
		visaoSaldoFinal.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		visaoSaldoFinal.setBounds(65, 301, 285, 14);
		add(visaoSaldoFinal);
		
		lblIcon = new JLabel("");
		lblIcon.setIcon(new ImageIcon(PainelConsultaMes.class.getResource("/icons/bank.png")));
		lblIcon.setBounds(658, 456, 34, 30);
		add(lblIcon);
		
		
	}
}
