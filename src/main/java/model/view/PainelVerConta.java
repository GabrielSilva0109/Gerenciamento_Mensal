package model.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.DespesaController;
import controller.UsuarioController;
import model.dao.DespesaDAO;
import model.dao.UsuarioDAO;
import model.vo.UsuarioVO;
import model.vo.*;
import controller.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class PainelVerConta extends JPanel {
	
	private DespesaController despController;
	private UsuarioController userController;
	private JLabel despesas_label;
	private JLabel salario_label;
	private JLabel email_label;
	private JLabel cpf_label;
	private JLabel nome_label;
	private JLabel telaSalario;
	private JLabel labelPrincipal_nome;
	private JLabel telaCpf;
	private JLabel telaEmail;
	private JLabel telaNome;
	
	EnderecoVO endereco = new EnderecoVO();
	EnderecoController endController = new EnderecoController();
	DecimalFormat formato = new DecimalFormat("#,##0.00");
	DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private JLabel lblDataNasci;
	private JLabel telaDate;
	private JLabel telaLogin;
	private JLabel lblLogin;
	private JLabel telaEstado;
	private JLabel lblEstado;
	private JLabel telaCidade;
	private JLabel lblCidade;
	private JLabel telaRua;
	private JLabel lblRua;
	private JLabel telaCep;
	private JLabel lblCEP;
	private JLabel telaNumero;
	private JLabel lblNumero;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JButton btnInicio;
	
	/**
	 * Create the panel.
	 */
	public PainelVerConta(final UsuarioVO userOnline) {
		setBackground(new Color(0, 255, 255));

		setLayout(null);
		
		endereco.setIdUsuario(userOnline.getIdUsuario());
		
		endereco = endController.consultarEnderecoPorId(endereco);
		
		telaNome = new JLabel("Nome Completo :");
		telaNome.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		telaNome.setBounds(21, 103, 119, 14);
		add(telaNome);
		
		labelPrincipal_nome = new JLabel("Seus dados cadastrais");
		labelPrincipal_nome.setFont(new Font("Source Serif Pro Semibold", Font.BOLD | Font.ITALIC, 18));
		labelPrincipal_nome.setBounds(179, 26, 205, 25);
		add(labelPrincipal_nome);
		
		telaCpf = new JLabel("CPF :");
		telaCpf.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		telaCpf.setBounds(20, 143, 46, 14);
		add(telaCpf);
		
		telaEmail = new JLabel("Email :");
		telaEmail.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		telaEmail.setBounds(20, 181, 72, 14);
		add(telaEmail);
		
		telaSalario = new JLabel("Salário Bruto : ");
		telaSalario.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		telaSalario.setBounds(20, 215, 96, 14);
		add(telaSalario);
		
		nome_label = new JLabel("");
		nome_label.setFont(new Font("Tahoma", Font.ITALIC, 11));
		nome_label.setBounds(130, 103, 158, 14);
		add(nome_label);
		
		nome_label.setText(userOnline.getNome());
		
		cpf_label = new JLabel("");
		cpf_label.setFont(new Font("Tahoma", Font.ITALIC, 11));
		cpf_label.setBounds(55, 143, 163, 14);
		add(cpf_label);
		
		//CRIPTROGRAFA O CPF
		String cpfCrip = userOnline.getCpf().substring(0, 3);
		
		cpf_label.setText(cpfCrip + ".***.***-**");
		
		email_label = new JLabel("");
		email_label.setFont(new Font("Tahoma", Font.ITALIC, 11));
		email_label.setBounds(65, 181, 179, 14);
		add(email_label);
		
		email_label.setText(userOnline.getEmail());
		
		salario_label = new JLabel("");
		salario_label.setFont(new Font("Tahoma", Font.ITALIC, 11));
		salario_label.setBounds(113, 215, 163, 14);
		add(salario_label);
		
		salario_label.setText("R$ "+ formato.format(userOnline.getSalariol()));
		
		lblDataNasci = new JLabel("");
		lblDataNasci.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblDataNasci.setBounds(152, 245, 124, 14);
		add(lblDataNasci);
		
		String dataFormat = formatoData.format(userOnline.getDataNasci());
		
		lblDataNasci.setText(dataFormat);
		
		telaDate = new JLabel("Data de nascimento : ");
		telaDate.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		telaDate.setBounds(20, 245, 148, 14);
		add(telaDate);
		
		telaLogin = new JLabel("Login : ");
		telaLogin.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		telaLogin.setBounds(20, 278, 46, 14);
		add(telaLogin);
		
		lblLogin = new JLabel("");
		lblLogin.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblLogin.setBounds(65, 278, 153, 14);
		add(lblLogin);
		
		lblLogin.setText(userOnline.getLogin());
		
		telaEstado = new JLabel("Estado : ");
		telaEstado.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		telaEstado.setBounds(305, 103, 65, 14);
		add(telaEstado);
		
		lblEstado = new JLabel("");
		lblEstado.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblEstado.setBounds(357, 103, 65, 14);
		add(lblEstado);
		
		lblEstado.setText(endereco.getUf());
		
		telaCidade = new JLabel("Cidade : ");
		telaCidade.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		telaCidade.setBounds(305, 143, 65, 14);
		add(telaCidade);
		
		lblCidade = new JLabel("");
		lblCidade.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblCidade.setBounds(364, 143, 145, 14);
		add(lblCidade);
		
		lblCidade.setText(endereco.getLocalidade());
		
		telaNumero = new JLabel("Número:");
		telaNumero.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		telaNumero.setBounds(305, 245, 56, 14);
		add(telaNumero);
		
		lblNumero = new JLabel("");
		lblNumero.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNumero.setBounds(364, 245, 115, 14);
		add(lblNumero);
		telaNumero = new JLabel("Número:");
		
		lblNumero.setText(""+endereco.getNumero());
		
		telaRua = new JLabel("Rua :");
		telaRua.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		telaRua.setBounds(305, 181, 65, 14);
		add(telaRua);
		
		lblRua = new JLabel("");
		lblRua.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblRua.setBounds(345, 181, 234, 14);
		add(lblRua);
		
		lblRua.setText(endereco.getLogradouro());
		
		telaCep = new JLabel("CEP : ");
		telaCep.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		telaCep.setBounds(305, 215, 65, 14);
		add(telaCep);
		
		lblCEP = new JLabel("");
		lblCEP.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblCEP.setBounds(339, 215, 140, 14);
		add(lblCEP);
		
		lblCEP.setText(endereco.getCep());
		
		panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 329, 715, 10);
		add(panel);
		
		
		lblNewLabel = new JLabel("Para alterar dados necessário ir para atela de atualização de dados");
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblNewLabel.setBounds(179, 346, 400, 14);
		add(lblNewLabel);
		
		JLabel lblIcon = new JLabel("");
		lblIcon.setIcon(new ImageIcon(PainelVerConta.class.getResource("/icons/bank.png")));
		lblIcon.setBounds(647, 498, 38, 25);
		add(lblIcon);
		
		
	    }
		//Usado para tornar o btnVoltar acessível externamente (por exemplo, pelo MenuPrincipal)
		public JButton getBtnInicio() {
			return btnInicio;
		 }
}

