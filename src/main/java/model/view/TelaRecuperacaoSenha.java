package model.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.border.SoftBevelBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.awt.event.ActionEvent;

import model.vo.*;
import controller.*;
import exceptions.CampoInvalidoException;

import javax.swing.JFormattedTextField;

public class TelaRecuperacaoSenha extends JFrame {

	private JPanel contentPane;
	private JTextField campNome;
	private LimitedPasswordField senhaNova;
	private LimitedPasswordField senhaNovaConfirm;
	private JFormattedTextField cpfDigitadoCamp;
	private MaskFormatter mascaraCpf;

    UsuarioVO usuarioConsultado = new UsuarioVO();
    UsuarioController userController = new UsuarioController();
    
    boolean updatePass = false;
	private JLabel lblIcon;
	private JLabel visaoNome;
	private JLabel visaoCPF;
	private JLabel lblConsultado;
	private JLabel visaoNovaSenha;
	private JLabel visaoConfirmSenha;
	private JButton btnSalvar;
	private JButton btnChecarSenha;
	private JButton btnVoltar;
	private JPanel panel;
	private JLabel lblErro;
	private JLabel lblErro2;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaRecuperacaoSenha frame = new TelaRecuperacaoSenha();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public TelaRecuperacaoSenha() throws ParseException {
		setBackground(new Color(255, 255, 255));
		setTitle("Gerenciamento-Mensal | Recuperação de senha ");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaRecuperacaoSenha.class.getResource("/icons/bank.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 467);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		mascaraCpf = new MaskFormatter("###.###.###-##");
		mascaraCpf.setValueContainsLiteralCharacters(false);
		
		cpfDigitadoCamp = new JFormattedTextField(mascaraCpf);
		cpfDigitadoCamp.setFont(new Font("Tahoma", Font.ITALIC, 11));
		cpfDigitadoCamp.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		cpfDigitadoCamp.setBounds(94, 106, 254, 20);
		contentPane.add(cpfDigitadoCamp);
		
		lblIcon = new JLabel("");
		lblIcon.setIcon(new ImageIcon(TelaRecuperacaoSenha.class.getResource("/icons/bank.png")));
		lblIcon.setBounds(432, 394, 32, 30);
		contentPane.add(lblIcon);
		
		panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 193, 521, 10);
		contentPane.add(panel);
		
		visaoNome = new JLabel("Nome completo : ");
		visaoNome.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		visaoNome.setBounds(168, 28, 104, 14);
		contentPane.add(visaoNome);
		
		visaoCPF = new JLabel("Informe seu CPF : ");
		visaoCPF.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		visaoCPF.setBounds(168, 84, 172, 14);
		contentPane.add(visaoCPF);
		
		campNome = new JTextField();
		campNome.setFont(new Font("Tahoma", Font.ITALIC, 11));
		campNome.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		campNome.setBounds(94, 53, 254, 20);
		campNome.setColumns(10);
		
		// Adiciona um ouvinte de eventos de teclado ao campo//Impede numeros 
		campNome.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                
                // Verifica se o caractere é um número
                if (Character.isDigit(c)) {
                    e.consume(); // Impede que o caractere seja inserido
                }
            }
        });
		
		contentPane.add(campNome);
		
		lblConsultado = new JLabel("");
		lblConsultado.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblConsultado.setBounds(60, 214, 402, 14);
		contentPane.add(lblConsultado);
		
		senhaNova = new LimitedPasswordField ();
		senhaNova.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		senhaNova.setBounds(231, 263, 119, 20);
		contentPane.add(senhaNova);
		
		lblErro = new JLabel("");
		lblErro.setForeground(new Color(255, 0, 0));
		lblErro.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblErro.setBounds(198, 137, 150, 14);
		contentPane.add(lblErro);
		
		lblErro2 = new JLabel("");
		lblErro2.setForeground(new Color(255, 0, 0));
		lblErro2.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblErro2.setBounds(258, 359, 196, 14);
		contentPane.add(lblErro2);
		
		senhaNovaConfirm = new LimitedPasswordField ();
		senhaNovaConfirm.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		senhaNovaConfirm.setBounds(233, 328, 117, 20);
		contentPane.add(senhaNovaConfirm);
		
		visaoNovaSenha = new JLabel("Nova senha : ");
		visaoNovaSenha.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		visaoNovaSenha.setBounds(147, 266, 104, 14);
		contentPane.add(visaoNovaSenha);
		
		visaoConfirmSenha = new JLabel("Confirme a senha : ");
		visaoConfirmSenha.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		visaoConfirmSenha.setBounds(119, 331, 132, 14);
		contentPane.add(visaoConfirmSenha);
		
		btnChecarSenha = new JButton("");
		btnChecarSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String cpfSemMascara = "";
				lblErro.setText("");
				
				// removendo mascara
				try {
					cpfSemMascara = (String) mascaraCpf.stringToValue(
							cpfDigitadoCamp.getText());
				} catch (ParseException e1) {
					
					lblErro.setText("O CPF é inválido!");
				}
				
                if (campNome.getText().isEmpty() || cpfDigitadoCamp.getText().isEmpty()) {
					
                	lblErro.setText("O nome é obrigatório!");
                	
				} else {
					//TRAS O OBJETO CONSULTADO COM O NOME INFORMADO
					usuarioConsultado =  userController.consultarUserPorNome(campNome.getText(), cpfSemMascara);
				}
				
				//VERIFICAO DE SENHA
				
				if (usuarioConsultado.getIdUsuario() != 0) {
					
					// realiza troca de senha 
					btnSalvar.setEnabled(true);
					lblConsultado.setText("Tudo certo " + usuarioConsultado.getLogin() + " ! Agora altere sua senha.");
					
				} else {
					
					lblErro.setText("Usuário não verificado!");
					
				}
				
			}
		});
		btnChecarSenha.setIcon(new ImageIcon(TelaRecuperacaoSenha.class.getResource("/icons/padlock.png")));
		btnChecarSenha.setBackground(new Color(0, 255, 255));
		btnChecarSenha.setBorder(null);
		btnChecarSenha.setBounds(358, 66, 32, 46);
		contentPane.add(btnChecarSenha);
		
		btnSalvar = new JButton("");
		btnSalvar.setEnabled(false);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				lblErro2.setText("");
				
				if (verificarSenhasDig(senhaNova.getText(), senhaNovaConfirm.getText())) {
					
					usuarioConsultado.setSenha(senhaNovaConfirm.getText());
					try {
						updatePass = userController.atualizarUsuarioController(usuarioConsultado);
						JOptionPane.showMessageDialog(null, "Senha atualizada com sucesso!", "Gerenciamento-Mensal", 
	                			JOptionPane.INFORMATION_MESSAGE);
	                	dispose();
						TelaLogin login = new TelaLogin();
						login.setVisible(true);
						
					} catch (SQLException | CampoInvalidoException e1) {
						// TODO Auto-generated catch block
						lblErro2.setText(e1.getMessage());
					}
				} else {
					lblErro2.setText("Senhas não coincidem!");
				}
				
			}

			private boolean verificarSenhasDig(String senhaNova, String senhaNovaConfirm) {
				
				boolean retorno = false;
				
				if (senhaNova.equals(senhaNovaConfirm)) {
					retorno = true;
				}
				
				return retorno;
			
			}

		});
		btnSalvar.setBorder(null);
		btnSalvar.setBackground(new Color(0, 255, 255));
		btnSalvar.setIcon(new ImageIcon(TelaRecuperacaoSenha.class.getResource("/icons/diskette.png")));
		btnSalvar.setBounds(345, 315, 57, 44);
		contentPane.add(btnSalvar);
		
		btnVoltar = new JButton("");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				TelaLogin tela = new TelaLogin();
				tela.setVisible(true);
				
			}
		});
		btnVoltar.setBorder(null);
		btnVoltar.setBackground(new Color(0, 255, 255));
		btnVoltar.setIcon(new ImageIcon(TelaRecuperacaoSenha.class.getResource("/icons/back.png")));
		btnVoltar.setBounds(-11, 401, 57, 23);
		contentPane.add(btnVoltar);
		
	}
}
