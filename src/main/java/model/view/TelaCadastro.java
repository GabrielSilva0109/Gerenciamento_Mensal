package model.view;

import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Font;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import model.vo.*;
import controller.*;
import exceptions.CampoInvalidoException;
import exceptions.CpfJaUtilizadoException;
import exceptions.EnderecoInvalidoException;

import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JRadioButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JCheckBox;
import model.dao.*;

public class TelaCadastro extends JFrame {

	private JPanel contentPane;
	private JTextField campoNome;
	private JTextField emailCamp;
	private JTextField loginCamp;
	private JTextField ruaCamp;
	private JTextField bairroCamp;
	private JTextField campNumero;
	
    UsuarioController usuarioController = new UsuarioController();
    UsuarioVO usuario = new UsuarioVO();
    EnderecoVO endereco = new EnderecoVO();
    EnderecoVO endPorCEP = new EnderecoVO();
    EnderecoController enderecoController = new EnderecoController();
    ViaCEP viaCep = new ViaCEP();
    
	private MaskFormatter mascaraCpf;
	private MaskFormatter mascaraData;
	private MaskFormatter mascaraCEP;
	private JFormattedTextField cpfCamp;
	private DatePicker dataNasciCamp;
	
	private JLabel visaoNome;
	private JLabel visaoCpf;
	private JLabel visaoData;
	private JLabel visaoEmail;
	private JLabel visaoSalario;
	private JLabel visaoLogin;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private Container visaoRua;
	private JLabel visaoBairro;
	private Container visaoNumero;
	private JFormattedTextField cepCamp;
	private JLabel visaoCep;
	private Container visaoEstado;
	private JLabel visaoCidade;
	private JButton btnVoltar;
	private JButton btnSalvar;
	private LimitedPasswordField campSenha;
	private LimitedPasswordField confirmCampSenha;
	private JTextField campCidade;
	private JNumberFormatField salarioCamp;
	private JComboBox cbEstados;
	private JLabel visaoSenha;
	private JLabel visaoConfirmSenha;
	private JCheckBox cbxAceitaTermos;
	private JButton btnBuscarCEP;
	private JLabel lblErro;
	private JLabel lblErroSenha;
	private JLabel lblErroCpf;
	private JLabel lblErroNome;
	private JLabel lblDataNasciErro;
	private JLabel lblSalarioErro;
	private JLabel lblLoginErro;
	private JLabel lblNewLabel;
	private JLabel lblErroEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastro frame = new TelaCadastro();
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
	public TelaCadastro() throws ParseException {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaCadastro.class.getResource("/icons/bank.png")));
		
		mascaraCpf = new MaskFormatter("###.###.###-##");
		mascaraCpf.setValueContainsLiteralCharacters(false);
		
		mascaraCEP = new MaskFormatter("#####-###");
		mascaraCpf.setValueContainsLiteralCharacters(false);
		
		final String[] listaEstados = {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS",
				 "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"};
		
		setTitle("Gerenciamento-Mensal | Cadastro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 761, 594);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ButtonGroup grupoAceite = new ButtonGroup();
		
		campoNome = new JTextField();
		campoNome.setFont(new Font("Tahoma", Font.ITALIC, 11));
		campoNome.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		campoNome.setBounds(105, 24, 254, 20);
		campoNome.setColumns(10);
		
		// Adiciona um ouvinte de eventos de teclado ao campo//IMPEDE NUMEROS
		campoNome.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                
                // Verifica se o caractere é um número
                if (Character.isDigit(c)) {
                    e.consume(); // Impede que o caractere seja inserido
                }
            }
        });
		contentPane.add(campoNome);
		
		cbxAceitaTermos = new JCheckBox("Aceita os termos de cadastro");
		cbxAceitaTermos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSalvar.setEnabled(true);
				if(!cbxAceitaTermos.isSelected()) {
					btnSalvar.setEnabled(false);
				}
			}
		});
		cbxAceitaTermos.setFont(new Font("Tahoma", Font.ITALIC, 11));
		cbxAceitaTermos.setBackground(new Color(0, 255, 255));
		cbxAceitaTermos.setBorder(null);
		cbxAceitaTermos.setBounds(520, 110, 194, 23);
		contentPane.add(cbxAceitaTermos);
		
		cpfCamp = new JFormattedTextField(mascaraCpf);
		cpfCamp.setFont(new Font("Tahoma", Font.ITALIC, 11));
		cpfCamp.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		cpfCamp.setBounds(105, 67, 254, 20);
		contentPane.add(cpfCamp);
		
		dataNasciCamp = new DatePicker();
		dataNasciCamp.getComponentToggleCalendarButton().setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		dataNasciCamp.getComponentDateTextField().setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		dataNasciCamp.getComponentToggleCalendarButton().setText("");
		dataNasciCamp.getComponentToggleCalendarButton().setIcon(new ImageIcon(TelaCadastro.class.getResource("/icons/calendario16px.png")));
		dataNasciCamp.getComponentToggleCalendarButton().setBackground(new Color(192, 192, 192));
		dataNasciCamp.getComponentDateTextField().setBackground(new Color(192, 192, 192));
		dataNasciCamp.setBounds(136, 111, 223, 20);
		contentPane.add(dataNasciCamp);
		
	    visaoNome = new JLabel("Nome : ");
		visaoNome.setFont(new Font("Tahoma", Font.ITALIC, 11));
		visaoNome.setBounds(20, 27, 46, 14);
		contentPane.add(visaoNome);
		
		visaoCpf = new JLabel("CPF :");
		visaoCpf.setFont(new Font("Tahoma", Font.ITALIC, 11));
		visaoCpf.setBounds(20, 70, 46, 14);
		contentPane.add(visaoCpf);
		
		visaoData = new JLabel("Data de Nascimento : ");
		visaoData.setFont(new Font("Tahoma", Font.ITALIC, 11));
		visaoData.setBounds(20, 114, 106, 14);
		contentPane.add(visaoData);
		
		emailCamp = new JTextField();
		emailCamp.setFont(new Font("Tahoma", Font.ITALIC, 11));
		emailCamp.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		emailCamp.setBounds(105, 161, 254, 20);
		contentPane.add(emailCamp);
		emailCamp.setColumns(10);
		
		visaoEmail = new JLabel("Email : ");
		visaoEmail.setFont(new Font("Tahoma", Font.ITALIC, 11));
		visaoEmail.setBounds(20, 164, 63, 14);
		contentPane.add(visaoEmail);
		
		visaoSalario = new JLabel("Salário líquido: ");
		visaoSalario.setFont(new Font("Tahoma", Font.ITALIC, 11));
		visaoSalario.setBounds(20, 214, 92, 14);
		contentPane.add(visaoSalario);
		
		visaoLogin = new JLabel("Login : ");
		visaoLogin.setFont(new Font("Tahoma", Font.ITALIC, 11));
		visaoLogin.setBounds(20, 261, 46, 14);
		contentPane.add(visaoLogin);
		
		loginCamp = new JTextField();
		loginCamp.setFont(new Font("Tahoma", Font.ITALIC, 11));
		loginCamp.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		loginCamp.setBounds(105, 258, 143, 20);
		contentPane.add(loginCamp);
		loginCamp.setColumns(10);
		
		lblNewLabel_6 = new JLabel("_______________________________________________________");
		lblNewLabel_6.setBounds(399, 304, 423, 14);
		contentPane.add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel("______________________________________________");
		lblNewLabel_7.setBounds(29, 304, 290, 14);
		contentPane.add(lblNewLabel_7);
		
		lblNewLabel_8 = new JLabel("Seu endereço ");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_8.setBounds(312, 304, 115, 14);
		contentPane.add(lblNewLabel_8);
		
		visaoSenha = new JLabel("Senha : ");
		visaoSenha.setFont(new Font("Tahoma", Font.ITALIC, 11));
		visaoSenha.setBounds(526, 27, 63, 14);
		contentPane.add(visaoSenha);
		
		visaoConfirmSenha = new JLabel("Confirme a senha: ");
		visaoConfirmSenha.setFont(new Font("Tahoma", Font.ITALIC, 11));
		visaoConfirmSenha.setBounds(483, 67, 106, 14);
		contentPane.add(visaoConfirmSenha);
		
		campSenha = new LimitedPasswordField ();
		campSenha.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		campSenha.setBounds(582, 24, 95, 20);
		contentPane.add(campSenha);
		
		confirmCampSenha = new LimitedPasswordField ();
		confirmCampSenha.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		confirmCampSenha.setBounds(582, 64, 95, 20);
		contentPane.add(confirmCampSenha);
		
		ruaCamp = new JTextField();
		ruaCamp.setFont(new Font("Tahoma", Font.ITALIC, 11));
		ruaCamp.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		ruaCamp.setBounds(76, 432, 254, 20);
		contentPane.add(ruaCamp);
		ruaCamp.setColumns(10);
		
		lblErroEmail = new JLabel("");
		lblErroEmail.setForeground(new Color(255, 0, 0));
		lblErroEmail.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblErroEmail.setBounds(257, 184, 166, 14);
		contentPane.add(lblErroEmail);
		
		visaoRua = new JLabel("Rua :");
		visaoRua.setFont(new Font("Tahoma", Font.ITALIC, 11));
		visaoRua.setBounds(20, 435, 46, 14);
		contentPane.add(visaoRua);
		
		bairroCamp = new JTextField();
		bairroCamp.setFont(new Font("Tahoma", Font.ITALIC, 11));
		bairroCamp.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		bairroCamp.setBounds(76, 394, 254, 20);
		contentPane.add(bairroCamp);
		bairroCamp.setColumns(10);
		
		visaoBairro = new JLabel("Bairro :");
		visaoBairro.setFont(new Font("Tahoma", Font.ITALIC, 11));
		visaoBairro.setBounds(20, 397, 46, 14);
		contentPane.add(visaoBairro);
		
		campNumero = new JTextField();
		campNumero.setFont(new Font("Tahoma", Font.ITALIC, 11));
		campNumero.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		campNumero.setBounds(76, 474, 122, 20);
		campNumero.setColumns(10);
		
		// Adiciona um ouvinte de eventos de teclado ao campo//IMPEDE RETORNO DE LETRAS
		campNumero.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                
                // Verifica se o caractere não é uma letra
                if (Character.isLetter(c)) {
                    e.consume(); // Impede que o caractere seja inserido
                }
            }
        });
		contentPane.add(campNumero);
		
		visaoNumero = new JLabel("Número :");
		visaoNumero.setFont(new Font("Tahoma", Font.ITALIC, 11));
		visaoNumero.setBounds(20, 477, 46, 14);
		contentPane.add(visaoNumero);
		
		cepCamp = new JFormattedTextField(mascaraCEP);
		cepCamp.setFont(new Font("Tahoma", Font.ITALIC, 11));
		cepCamp.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		cepCamp.setBounds(76, 350, 122, 20);
		contentPane.add(cepCamp);
		
		visaoCep = new JLabel("CEP : ");
		visaoCep.setFont(new Font("Tahoma", Font.ITALIC, 11));
		visaoCep.setBounds(20, 353, 46, 14);
		contentPane.add(visaoCep);
		
	    campCidade = new JTextField();
	    campCidade.setFont(new Font("Tahoma", Font.ITALIC, 11));
	    campCidade.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		campCidade.setBounds(538, 394, 187, 20);
		contentPane.add(campCidade);
		campCidade.setColumns(10);
		
		lblErro = new JLabel("");
		lblErro.setFont(new Font("Tahoma", Font.ITALIC, 9));
		lblErro.setForeground(new Color(255, 0, 0));
		lblErro.setBounds(136, 369, 112, 14);
		contentPane.add(lblErro);
		
		lblErroSenha = new JLabel("");
		lblErroSenha.setForeground(new Color(255, 0, 0));
		lblErroSenha.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblErroSenha.setBounds(592, 42, 133, 14);
		contentPane.add(lblErroSenha);
		
	    lblErroCpf = new JLabel("");
		lblErroCpf.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblErroCpf.setForeground(new Color(255, 0, 0));
		lblErroCpf.setBounds(312, 86, 143, 14);
		contentPane.add(lblErroCpf);
		
		lblErroNome = new JLabel("");
		lblErroNome.setForeground(new Color(255, 0, 0));
		lblErroNome.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblErroNome.setBounds(312, 41, 115, 14);
		contentPane.add(lblErroNome);
		
		lblDataNasciErro = new JLabel("");
		lblDataNasciErro.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblDataNasciErro.setForeground(new Color(255, 0, 0));
		lblDataNasciErro.setBounds(222, 136, 233, 14);
		contentPane.add(lblDataNasciErro);
		
		lblSalarioErro = new JLabel("");
		lblSalarioErro.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblSalarioErro.setForeground(new Color(255, 0, 0));
		lblSalarioErro.setBounds(185, 233, 200, 14);
		contentPane.add(lblSalarioErro);
		
		lblLoginErro = new JLabel("");
		lblLoginErro.setForeground(new Color(255, 0, 0));
		lblLoginErro.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblLoginErro.setBounds(202, 279, 194, 14);
		contentPane.add(lblLoginErro);
		
		visaoEstado = new JLabel("Estado : ");
		visaoEstado.setFont(new Font("Tahoma", Font.ITALIC, 11));
		visaoEstado.setBounds(468, 353, 46, 14);
		contentPane.add(visaoEstado);
		
		cbEstados = new JComboBox(listaEstados);
		cbEstados.setBorder(null);
		cbEstados.setBackground(new Color(192, 192, 192));
		cbEstados.setBounds(538, 349, 63, 22);
		contentPane.add(cbEstados);
		
		salarioCamp = new JNumberFormatField();
		salarioCamp.setFont(new Font("Tahoma", Font.ITALIC, 11));
		salarioCamp.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		salarioCamp.setBounds(105, 211, 143, 20);
		contentPane.add(salarioCamp);
		salarioCamp.setColumns(10);
		
		visaoCidade = new JLabel("Cidade : ");
		visaoCidade.setFont(new Font("Tahoma", Font.ITALIC, 11));
		visaoCidade.setBounds(468, 397, 60, 14);
		contentPane.add(visaoCidade);
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.setBorder(null);
		btnVoltar.setIcon(new ImageIcon(TelaCadastro.class.getResource("/icons/back.png")));
		btnVoltar.setBackground(new Color(0, 255, 255));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// retorna para a tela de login 
				dispose();
				TelaLogin tela = new TelaLogin();
				tela.setVisible(true);
			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.ITALIC, 11));
		btnVoltar.setBounds(257, 527, 115, 23);
		contentPane.add(btnVoltar);
		
		btnBuscarCEP = new JButton("");
		btnBuscarCEP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				this.limparCampos();
				lblErro.setText("");
				
				//Traz os dados do endereco via Cep/API
				
				try {
					endPorCEP = enderecoController.buscarViaCepController(cepCamp.getText());
					
					
				} catch (EnderecoInvalidoException | CampoInvalidoException excecao) {
					
					lblErro.setText("Digite o CEP!");
					
				} catch (IOException e1) {
					
					lblErro.setText("Digite o CEP!");
				}
				ruaCamp.setText(endPorCEP.getLogradouro());
				campCidade.setText(endPorCEP.getLocalidade());
				bairroCamp.setText(endPorCEP.getBairro());
				cbEstados.setSelectedItem(endPorCEP.getUf());
				if (ruaCamp.getText().isEmpty() || campCidade.getText().isEmpty()) {
					lblErro.setText("Endereço não encontrado!");
				} 
				
			}
			

			private void limparCampos() {
				
				ruaCamp.setText("");
				campCidade.setText("");
				bairroCamp.setText("");
				
			}
		});
		btnBuscarCEP.setBackground(new Color(0, 255, 255));
		btnBuscarCEP.setBorder(null);
		btnBuscarCEP.setIcon(new ImageIcon(TelaCadastro.class.getResource("/icons/loupe.png")));
		btnBuscarCEP.setBounds(201, 349, 24, 23);
		contentPane.add(btnBuscarCEP);
		
		btnSalvar = new JButton("Cadastrar");
		btnSalvar.setBorder(null);
		btnSalvar.setIcon(new ImageIcon(TelaCadastro.class.getResource("/icons/diskette.png")));
		btnSalvar.setBackground(new Color(0, 255, 255));
		btnSalvar.setEnabled(false);
		
		ruaCamp.setText(endPorCEP.getLogradouro());
		campCidade.setText(endPorCEP.getLocalidade());
		bairroCamp.setText(endPorCEP.getBairro());
		listaEstados[0] += endPorCEP.getUf();
		
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				//limpa os campos de erro 
				lblErroNome.setText("");
				lblErroCpf.setText("");
				lblDataNasciErro.setText("");
				lblErroSenha.setText("");
				lblLoginErro.setText("");
				lblErroEmail.setText("");
				
				 String valor1 = "";
				
				// DEFININDO TIPO DE USUARIO DE ACORDO COM A REGRA 
				usuario.setTipoUsuario(2);
				
				// removendo mascara
				try {
					String cpfSemMascara = (String) mascaraCpf.stringToValue(
							cpfCamp.getText());
					usuario.setCpf(cpfSemMascara);
				} catch (ParseException e1) {
					lblErroCpf.setText("O CPF é obrigatório!");
				}
	
				// inserindo dados nos objetos 
			
				usuario.setNome(campoNome.getText());
				usuario.setEmail(emailCamp.getText());
				usuario.setDataNasci(dataNasciCamp.getDate());
			    usuario.setLogin(loginCamp.getText());
			    
			    valor1 = salarioCamp.getText().replace(".", "");
			    valor1 = valor1.replace(",", ".");
			    
			    usuario.setSalariol(Double.parseDouble(valor1.replace("R$", "")));
			    
			    //METODO PARA VERIFICAR SENHA 
			    this.verificarSenhasDigitadas(campSenha.getText(), confirmCampSenha.getText());
			    
			    try {
			    //cadastramento no banco, chamando validacoes
			    if (usuario.getSenha() != null) {
			    	usuarioController.cadastrarUsuarioController(usuario);
			      }
			    } catch (CpfJaUtilizadoException | CampoInvalidoException excecao) {
			    	
			    	if (campoNome.getText().trim().isEmpty() || campoNome.getText().trim().isBlank()) {
			    		lblErroNome.setText("O nome é obrigatório!");
			    	} else if(usuario.getDataNasci() == null) {
			    		lblDataNasciErro.setText("A data de nascimento é obrigatória!");
			    	} else if (usuario.getSenha().isEmpty() || usuario.getSenha().isBlank()) {
			    		lblErroSenha.setText("A senha é obrigatória!");
			    	} else if (loginCamp.getText().isEmpty() || loginCamp.getText().trim().isBlank()) {
			    		lblLoginErro.setText("O login é obrigatório!");
			    	} else if(usuario.getSalariol() <= 0) {
			    		lblSalarioErro.setText("O salário deve ser informado!");
			    	} else {
			    		lblErroEmail.setText(excecao.getMessage());
			    	}
			 
			    }
			    
			    //INICIA O OBJETO DE ENDERECO
                EnderecoVO endereco = new EnderecoVO();
				
				endereco.setIdUsuario(usuario.getIdUsuario());
				endereco.setBairro(bairroCamp.getText());
				endereco.setCep(cepCamp.getText());
				endereco.setLogradouro(ruaCamp.getText());
				
				if (!campNumero.getText().trim().isEmpty()) {
					endereco.setNumero(Integer.parseInt(campNumero.getText()));
					endereco.setLocalidade(campCidade.getText());
					endereco.setUf((String) cbEstados.getSelectedItem());
				} else {
					endereco.setLocalidade(campCidade.getText());
					endereco.setUf((String) cbEstados.getSelectedItem());
				}
	
				 //cadastramento no banco, chamando validacoes
				try {
					endereco = enderecoController.cadastrarEnderecoController(endereco);
				} catch (CampoInvalidoException e2) {
					JOptionPane.showMessageDialog(null, "Informe os seguintes campos\n " + e2.getMessage());
				}
				
				if (usuario.getIdUsuario() != 0 && endereco.getIdEndereco() != 0) {
					JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!", 
							"Sucesso", JOptionPane.INFORMATION_MESSAGE);
					dispose();
					MenuPrincipal tela = new MenuPrincipal(usuario);
					tela.setVisible(true);
					
				} 
			}

			private void verificarSenhasDigitadas(String text, String text2) {
			
				if (text.equals(text2)) {
					usuario.setSenha(text2);
					
				} else {
					JOptionPane.showMessageDialog(null, "Senhas não coincidem!", "Gerenciamento-Mensal" ,JOptionPane.ERROR_MESSAGE);
				}
				
			}

		});
		btnSalvar.setFont(new Font("Tahoma", Font.ITALIC, 11));
		btnSalvar.setBounds(371, 527, 128, 23);
		contentPane.add(btnSalvar);
		
		lblNewLabel = new JLabel("(Opcional)");
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblNewLabel.setBounds(202, 477, 70, 14);
		contentPane.add(lblNewLabel);
		
		
			}
		}

