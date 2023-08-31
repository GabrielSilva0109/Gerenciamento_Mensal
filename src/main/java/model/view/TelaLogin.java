package model.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import model.dao.*;
import model.vo.*;
import controller.*;
import exceptions.CampoInvalidoException;

import javax.swing.JFormattedTextField;
import java.awt.TextField;
import java.awt.Label;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JProgressBar;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.border.BevelBorder;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;


public class TelaLogin extends JFrame {
	
	//USUARIO CRIADO/VARIAVEIS
	 UsuarioVO userVerificado = new UsuarioVO();
	 UsuarioVO userRecuperado = new UsuarioVO();
	 
	 UsuarioVO userOnline = new UsuarioVO();
	 UsuarioVO userLogin = new UsuarioVO();
	 String userSenha = "";
	 UsuarioController userController = new UsuarioController();
	
		private JPanel contentPane;
		private JTextField loginField;
		private LimitedPasswordField senhaField;
		private JButton btn_entrar;
		private JLabel lblTitulo;
		private Label visaoUser;
		private Label visaoPass;
		private JButton btnCadastrar;
		private JLabel lblSobre;
		private JPanel panel;
		private JButton btnRecuperacaoSenha;
		private JLabel lblErro;
		private String A;
		


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 // Define o ícone da aplicação
					TelaLogin frame = new TelaLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaLogin() {
		
		setForeground(new Color(64, 128, 128));
		setFont(new Font("Source Serif Pro Semibold", Font.ITALIC, 12));
		setBackground(new Color(255, 255, 255));
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaLogin.class.getResource("/icons/bank (1).png")));
		setTitle("Gerenciamento-Mensal | Login");
		setResizable(false);
		setBounds(100, 100, 479, 372);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		visaoUser = new Label("Usuário :");
		visaoUser.setFont(new Font("Dialog", Font.ITALIC, 11));
		visaoUser.setBounds(123, 149, 51, 12);
		contentPane.add(visaoUser);
		
        visaoPass = new Label("Senha :");
		visaoPass.setFont(new Font("Dialog", Font.ITALIC, 11));
		visaoPass.setBounds(123, 167, 51, 27);
		contentPane.add(visaoPass);
		
		loginField = new JTextField();
		loginField.setFont(new Font("Tahoma", Font.ITALIC, 11));
		loginField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		loginField.setBounds(181, 149, 91, 20);
		contentPane.add(loginField);
		loginField.setColumns(10);
		
		senhaField = new LimitedPasswordField();
		senhaField.setFont(new Font("Tahoma", Font.PLAIN, 11));
		senhaField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		senhaField.setBounds(181, 174, 91, 20);
		contentPane.add(senhaField);
		
		btn_entrar = new JButton("Entrar");
		btn_entrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				lblErro.setText("");
				
				// VERIFICAR SENHA PARA LOGIN DIGITADO
				userLogin.setLogin(loginField.getText());
				
				try {
					userLogin = userController.consultarUserPorLogin(userLogin);
					
					if (userLogin.getIdUsuario() != 0 && userLogin.getSenha().equals(senhaField.getText())) {
						
                        JOptionPane.showMessageDialog(null, "Login efetuado com sucesso "+userLogin.getLogin()+ "!", "Gerenciamento-Mensal", JOptionPane.INFORMATION_MESSAGE);
    					
    					//METODO PARA TROCA DE FRAME
    					dispose();
    					MenuPrincipal menu = new MenuPrincipal(userLogin);
    					menu.setVisible(true);
						
					} else if(userLogin.getIdUsuario() == 0) {
						lblErro.setText("Usuário ou Senha incorretos!");
					} else if (!userLogin.getSenha().equals(senhaField.getText())) {
						lblErro.setText("Usuário ou Senha incorretos!");
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					lblErro.setText(e1.getMessage());
				}
				
		   }
		
	    });
		btn_entrar.setIcon(new ImageIcon(TelaLogin.class.getResource("/icons/login.png")));
	 
		btn_entrar.setBackground(new Color(192, 192, 192));
		btn_entrar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		btn_entrar.setBounds(97, 221, 118, 23);
		contentPane.add(btn_entrar);
		btn_entrar.requestFocusInWindow();
		
	    panel = new JPanel();
		panel.setBackground(new Color(0, 255, 255));
		panel.setBounds(0, 0, 507, 104);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblErro = new JLabel("");
		lblErro.setForeground(new Color(255, 0, 0));
		lblErro.setFont(new Font("Tahoma", Font.ITALIC, 9));
		lblErro.setBounds(228, 196, 157, 14);
		contentPane.add(lblErro);
	
		lblTitulo = new JLabel("Entre com suas credenciais");
		lblTitulo.setBounds(108, 47, 319, 14);
		panel.add(lblTitulo);
		lblTitulo.setFont(new Font("Source Serif Pro Semibold", Font.BOLD | Font.ITALIC, 18));
		
		btnCadastrar = new JButton("Cadastro");
		btnCadastrar.setIcon(new ImageIcon(TelaLogin.class.getResource("/icons/add-user.png")));
		btnCadastrar.setBackground(new Color(192, 192, 192));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Chama a tela de cadastro
		        dispose();
				TelaCadastro tela;
				try {
					tela = new TelaCadastro();
					tela.setVisible(true);
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(null, e1.getStackTrace(), "Gerenciamento-Mensal", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnCadastrar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		btnCadastrar.setBounds(228, 221, 124, 23);
		contentPane.add(btnCadastrar);
		
		lblSobre = new JLabel("Gerenciamento-Mensal/login");
		lblSobre.setFont(new Font("Tahoma", Font.ITALIC, 8));
		lblSobre.setBounds(10, 319, 205, 14);
		contentPane.add(lblSobre);
		
		btnRecuperacaoSenha = new JButton("Esqueceu sua senha? ");
		btnRecuperacaoSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			dispose();	
			TelaRecuperacaoSenha tela;
			try {
				tela = new TelaRecuperacaoSenha();
				tela.setVisible(true);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			   }
			}
		});
		btnRecuperacaoSenha.setBackground(new Color(192, 192, 192));
		btnRecuperacaoSenha.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 8));
		btnRecuperacaoSenha.setBorder(null);
		btnRecuperacaoSenha.setBounds(116, 246, 147, 23);
		contentPane.add(btnRecuperacaoSenha);
		
	}
}
