package model.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.time.LocalDate;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.view.*;
import model.vo.*;
import controller.DespesaController;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;


public class PainelAdicionarDespesa extends JPanel {
	
	private JLabel visaoTitulo;
	private JButton btnNewButton;
	private JTextField nomeDespesa_camp;
	private JNumberFormatField valorDespesaCamp;
	private JLabel visaoNome;
	private JLabel visaoValor;
	
	DespesaController despController = new DespesaController();
	DespesaVO despesa = new DespesaVO();
	private final JLabel lblIcon = new JLabel("");
	DecimalFormat formato = new DecimalFormat("#,##0.00");
	
	/**
	 * Create the panel.
	 */
	public PainelAdicionarDespesa(final UsuarioVO userLogado) {
		setBackground(new Color(0, 255, 255));

		setLayout(null);
		
		visaoTitulo = new JLabel(" Inserira os dados da despesa nova  ");
		visaoTitulo.setFont(new Font("Source Serif Pro Semibold", Font.BOLD | Font.ITALIC, 14));
		visaoTitulo.setBounds(115, 36, 253, 14);
		add(visaoTitulo);
		
		nomeDespesa_camp = new JTextField();
		nomeDespesa_camp.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		nomeDespesa_camp.setBounds(142, 97, 131, 20);
		nomeDespesa_camp.setColumns(10);
		
		 // Adiciona um ouvinte de eventos de teclado ao campo
		nomeDespesa_camp.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                
                // Verifica se o caractere é um número
                if (Character.isDigit(c)) {
                    e.consume(); // Impede que o caractere seja inserido
                }
            }
        });
		add(nomeDespesa_camp);
		
		valorDespesaCamp = new JNumberFormatField();
		valorDespesaCamp.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		valorDespesaCamp.setBounds(142, 146, 131, 20);
		add(valorDespesaCamp);
		valorDespesaCamp.setColumns(10);
		
		visaoNome = new JLabel("Nome para a despesa : ");
		visaoNome.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		visaoNome.setBounds(20, 100, 121, 14);
		add(visaoNome);
		
		visaoValor = new JLabel("Valor : ");
		visaoValor.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		visaoValor.setBounds(89, 149, 106, 14);
		add(visaoValor);
		
		
		JButton btn_inserir_camp = new JButton(" Inserir ");
		btn_inserir_camp.setBorder(null);
		btn_inserir_camp.setIcon(new ImageIcon(PainelAdicionarDespesa.class.getResource("/icons/add.png")));
		btn_inserir_camp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String valor1 = "";
				
				//INSERE DADOS AO NOVO OBJETO
				LocalDate dataAtual = LocalDate.now();
				
				if (nomeDespesa_camp.getText().isBlank() || nomeDespesa_camp.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Todos os campos são obrigatórios! ", "Gerenciamento-Mensal", JOptionPane.WARNING_MESSAGE);
				} else if (valorDespesaCamp.getText().isEmpty() || valorDespesaCamp.getText().isEmpty() ) {
					JOptionPane.showMessageDialog(null, "Todos os campos são obrigatórios! ", "Gerenciamento-Mensal", JOptionPane.WARNING_MESSAGE);
				} else {
					despesa.setIdUsuario(userLogado.getIdUsuario());
					despesa.setDespNome(nomeDespesa_camp.getText());
					
					valor1 = valorDespesaCamp.getText().replace(".", "");
					valor1 = valor1.replace(",", ".");
					
					despesa.setValor(Double.parseDouble(valor1.replace("R$", "")));
					//INSERE DESPESA NOVA AO BANCO
					 despesa = despController.inserirDespesaController(despesa);
				}
								
				if (despesa.getIdDespesa() != 0) {
					JOptionPane.showMessageDialog(null, "Despesa nova inserida com sucesso! ", "Gerenciamento-Mensal", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Não foi possível inserir! ", "Gerenciamento-Mensal", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		btn_inserir_camp.setBackground(new Color(0, 255, 255));
		btn_inserir_camp.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		btn_inserir_camp.setBounds(142, 177, 140, 31);
		add(btn_inserir_camp);
		lblIcon.setIcon(new ImageIcon(PainelAdicionarDespesa.class.getResource("/icons/bank.png")));
		lblIcon.setBounds(653, 457, 36, 31);
		add(lblIcon);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 228, 689, 10);
		add(panel);
		
		JLabel lblNewLabel = new JLabel("Digite o nome e o valor da despesa a ser adicionada");
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblNewLabel.setBounds(261, 249, 356, 14);
		add(lblNewLabel);
		
	}
}
