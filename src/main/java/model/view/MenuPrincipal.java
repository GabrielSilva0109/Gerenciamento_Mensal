package model.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.UsuarioController;
import model.vo.UsuarioVO;

import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.Cursor;

import controller.*;
import javax.swing.JToggleButton;

public class MenuPrincipal extends JFrame {

	private JPanel contentPane;
	
	// Paineis separados
	private PainelVerConta painelVerConta;
	private PainelAlterarDados painelAlterarDados;
	private PainelConsultarDespesa painelConsultarDesp;
	private PainelAdicionarDespesa painelInserirDesp;
	private PainelCalculoMensal painelCalculoMensal;
	private PainelConsultaMes painelConsultaMes;
	private PainelTabelaCompleta painelTabelaCompleta;
	private PainelRemoverDespesa painelRemoverDespesa;
	private PainelAlterarDespesa painelAlterarDespesa;
	
	UsuarioController usuarioController = new UsuarioController();
	EnderecoController endController = new EnderecoController();
	DespesaController despController = new DespesaController();
	TabelaController tabController = new TabelaController();

	private JMenuItem mntmTabelaCompleta;

	private JMenu mnmDespesas;

	private JToggleButton tglBtnTeste;

	private JMenuBar menuBar;

	private JMenu mnmConta;

	private JMenuItem mntmVerConta;

	private JMenuItem mntmAtualizarDados;

	private JMenuItem mntmTrocarConta;

	private JMenuItem mntmEncerrarConta;

	private JPanel pnlAprensent;

	private JLabel lblContato;

	private JLabel lblIconPrincipal;

	private JLabel lblTItuloPrincipal;

	private JMenuItem mntmConsultarDesp;

	private JMenuItem mntmEditarDesp;

	private JMenuItem mntmInserirNovaDespesa;

	private JMenuItem mntmRemoverDesp;

	private JMenu mnmCalculos;

	private JMenuItem mntmCalculoMensal;

	private JMenu mnmTabela;

	private JMenuItem mntmMesEsp;

	private JMenu mnmSobre;

	private AbstractButton mntmSobre;
	private JToggleButton tglTrocaDeCor;

	private JMenuItem mntmTemaClaro;
	
	private boolean dark = false;
	private JMenuItem mntmTemaEscuro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param userLogado 
	 */
	public MenuPrincipal(final UsuarioVO userOnline) {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/icons/bank.png")));
		setBackground(new Color(255, 255, 255));
		setTitle("Gerenciamento Mensal | Menu");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 710, 558);
		setLocationRelativeTo(null);
		
		menuBar = new JMenuBar();
		menuBar.setForeground(new Color(255, 255, 255));
		menuBar.setBackground(new Color(255, 255, 255));
		setJMenuBar(menuBar);
		
		mnmConta = new JMenu("Conta");
		mnmConta.setForeground(new Color(0, 0, 0));
		mnmConta.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/user.png")));
		mnmConta.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 12));
		menuBar.add(mnmConta);
		
		mntmTemaClaro = new JMenuItem("Tema claro");
		mntmTemaClaro.setFont(new Font("Segoe UI", Font.ITALIC, 12));
		
		mntmTemaClaro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				    tglTrocaDeCor.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/dark.png")));
		        	
					mntmTemaClaro.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/light.png")));
					
		        	menuBar.setBackground(new Color(255,255,255));
					pnlAprensent.setBackground(new Color(255,255,255));
					lblContato.setForeground(new Color(0, 0, 0));
					mnmConta.setForeground(new Color(0, 0, 0));
					mnmDespesas.setForeground(new Color(0, 0, 0));
					mnmCalculos.setForeground(new Color(0, 0, 0));
					mnmSobre.setForeground(new Color(0, 0, 0));
					mnmTabela.setForeground(new Color(0, 0, 0));
					
					mntmVerConta.setBackground(new Color(255,255,255));
					mntmVerConta.setForeground(new Color(0, 0, 0));
					
					mntmTemaClaro.setBackground(new Color(255,255,255));
					mntmTemaClaro.setForeground(new Color(0, 0, 0));
					
					mntmTemaEscuro.setBackground(new Color(255,255,255));
					mntmTemaEscuro.setForeground(new Color(0, 0, 0));
					
					mntmAtualizarDados.setBackground(new Color(255,255,255));
					mntmAtualizarDados.setForeground(new Color(0, 0, 0));
					
					mntmTrocarConta.setBackground(new Color(255,255,255));
					mntmTrocarConta.setForeground(new Color(0, 0, 0));
					
					mntmEncerrarConta.setBackground(new Color(255,255,255));
					mntmEncerrarConta.setForeground(new Color(0, 0, 0));

					mntmEditarDesp.setBackground(new Color(255,255,255));
					mntmEditarDesp.setForeground(new Color(0, 0, 0));
					
					mntmConsultarDesp.setBackground(new Color(255,255,255));
					mntmConsultarDesp.setForeground(new Color(0, 0, 0));
					
					mntmInserirNovaDespesa.setBackground(new Color(255,255,255));
					mntmInserirNovaDespesa.setForeground(new Color(0, 0, 0));
					
					mntmRemoverDesp.setBackground(new Color(255,255,255));
					mntmRemoverDesp.setForeground(new Color(0, 0, 0));

					mntmCalculoMensal.setBackground(new Color(255,255,255));
					mntmCalculoMensal.setForeground(new Color(0, 0, 0));
					
					mntmMesEsp.setBackground(new Color(255,255,255));
					mntmMesEsp.setForeground(new Color(0, 0, 0));
					
					mntmTabelaCompleta.setBackground(new Color(255,255,255));
					mntmTabelaCompleta.setForeground(new Color(0, 0, 0));
					
					mntmSobre.setBackground(new Color(255,255,255));
					mntmSobre.setForeground(new Color(0, 0, 0));
		
			}
		});
		
		mntmAtualizarDados = new JMenuItem("Atualizar dados ");
		mntmAtualizarDados.setBackground(new Color(255, 255, 255));
		mntmAtualizarDados.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));
		mntmAtualizarDados.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/pencil.png")));
		mntmAtualizarDados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					painelAlterarDados = new PainelAlterarDados(userOnline);
					setContentPane(painelAlterarDados);
					revalidate(); 
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		mntmVerConta = new JMenuItem("Ver conta ");
		mntmVerConta.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		mntmVerConta.setBackground(new Color(255, 255, 255));
		mntmVerConta.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		mntmVerConta.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/account.png")));
		mntmVerConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//TROCA DE PAINEL NA TELA PRINCIPAL
				painelVerConta = new PainelVerConta(userOnline);
				setContentPane(painelVerConta);
				revalidate(); 
				
			}
		});
		mntmVerConta.setFont(new Font("Segoe UI", Font.ITALIC, 12));
		mnmConta.add(mntmVerConta);
		mntmAtualizarDados.setFont(new Font("Segoe UI", Font.ITALIC, 12));
		mnmConta.add(mntmAtualizarDados);
		
		mntmTrocarConta = new JMenuItem("Trocar usuário");
		mntmTrocarConta.setBackground(new Color(255, 255, 255));
		mntmTrocarConta.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0));
		mntmTrocarConta.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/refresh.png")));
		mntmTrocarConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Traz a tela de login novamente 
				dispose();
				TelaLogin tela = new TelaLogin();
				tela.setVisible(true);
				
			}
		});
		mntmTrocarConta.setFont(new Font("Segoe UI", Font.ITALIC, 12));
		mnmConta.add(mntmTrocarConta);
		
	    mntmEncerrarConta = new JMenuItem("Encerrar conta");
		mntmEncerrarConta.setBackground(new Color(255, 255, 255));
		mntmEncerrarConta.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		mntmEncerrarConta.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/cross.png")));
		mntmEncerrarConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int opcaoSelecionada = JOptionPane.showConfirmDialog(null, "Confirma a exclusão da sua conta?");
				
				if (opcaoSelecionada == JOptionPane.YES_OPTION) {
					
					//Apaga todas os registros relacionados
					endController.excluirEndController(userOnline);
					despController.excluirDespesasController(userOnline);
					tabController.excluirTabelasController(userOnline);
					usuarioController.excluirContaController(userOnline);
					//CHAMA O LOGIN NOVAMENTE
					dispose();
					TelaLogin login = new TelaLogin();
					login.setVisible(true);

				JOptionPane.showMessageDialog(null, "Conta excluída com sucesso!", "GS - Gerenciador de salário", JOptionPane.INFORMATION_MESSAGE);
				} else  {
				   dispose();
				   MenuPrincipal tela = new MenuPrincipal(userOnline);
				   tela.setVisible(true);
				} 	
			}	
		});
		mntmEncerrarConta.setFont(new Font("Segoe UI", Font.ITALIC, 12));
		mnmConta.add(mntmEncerrarConta);
		
		mnmDespesas = new JMenu("Despesas");
		mnmDespesas.setForeground(new Color(0, 0, 0));
		mnmDespesas.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/expenses.png")));
		mnmDespesas.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 12));
		menuBar.add(mnmDespesas);
		
		mntmConsultarDesp = new JMenuItem("Consultar");
		mntmConsultarDesp.setBackground(new Color(255, 255, 255));
		mntmConsultarDesp.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/search.png")));
		mntmConsultarDesp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				painelConsultarDesp = new PainelConsultarDespesa(userOnline);
				setContentPane(painelConsultarDesp);
				revalidate();
				
			}
		});
		
		mntmEditarDesp = new JMenuItem("Editar ");
		mntmEditarDesp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				painelAlterarDespesa = new PainelAlterarDespesa(userOnline);
				setContentPane(painelAlterarDespesa);
				revalidate();
				
			}
		});
		mntmEditarDesp.setBackground(new Color(255, 255, 255));
		mntmEditarDesp.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/pencil.png")));
		mntmEditarDesp.setFont(new Font("Segoe UI", Font.ITALIC, 12));
		mnmDespesas.add(mntmEditarDesp);
		mntmConsultarDesp.setFont(new Font("Segoe UI", Font.ITALIC, 12));
		mnmDespesas.add(mntmConsultarDesp);
		
		mntmInserirNovaDespesa = new JMenuItem("Inserir nova ");
		mntmInserirNovaDespesa.setBackground(new Color(255, 255, 255));
		mntmInserirNovaDespesa.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/add.png")));
		mntmInserirNovaDespesa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				painelInserirDesp = new PainelAdicionarDespesa(userOnline);
				setContentPane(painelInserirDesp);
				revalidate();
				
			}
		});
		mntmInserirNovaDespesa.setFont(new Font("Segoe UI", Font.ITALIC, 12));
		mnmDespesas.add(mntmInserirNovaDespesa);
		
		mntmRemoverDesp = new JMenuItem("Remover despesa");
		mntmRemoverDesp.setBackground(new Color(255, 255, 255));
		mntmRemoverDesp.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/remove.png")));
		mntmRemoverDesp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				painelRemoverDespesa = new PainelRemoverDespesa(userOnline);
				setContentPane(painelRemoverDespesa);
				revalidate();
				
			
			}
		});
		mntmRemoverDesp.setFont(new Font("Segoe UI", Font.ITALIC, 12));
		mnmDespesas.add(mntmRemoverDesp);
		
		mnmCalculos = new JMenu("Cálculos");
		mnmCalculos.setForeground(new Color(0, 0, 0));
		mnmCalculos.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/chart-histogram.png")));
		mnmCalculos.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 12));
		menuBar.add(mnmCalculos);
		
		mntmTemaEscuro = new JMenuItem("Tema Escuro");
		mntmTemaEscuro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tglTrocaDeCor.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/light.png")));
				
				menuBar.setBackground(new Color(0, 0, 0));
				pnlAprensent.setBackground(new Color(0, 0, 0));
				lblContato.setForeground(new Color(255,255,255));
				mnmConta.setForeground(new Color(255,255,255));
				mnmDespesas.setForeground(new Color(255,255,255));
				mnmCalculos.setForeground(new Color(255,255,255));
				mnmSobre.setForeground(new Color(255,255,255));
				mnmTabela.setForeground(new Color(255,255,255));
				
				mntmVerConta.setBackground(new Color(0, 0, 0));
				mntmVerConta.setForeground(new Color(255,255,255));
				
				mntmTemaClaro.setBackground(new Color(0, 0, 0));
				mntmTemaClaro.setForeground(new Color(255,255,255));
				
				mntmTemaEscuro.setBackground(new Color(0, 0, 0));
				mntmTemaEscuro.setForeground(new Color(255,255,255));
				
				mntmAtualizarDados.setBackground(new Color(0, 0, 0));
				mntmAtualizarDados.setForeground(new Color(255,255,255));
				
				mntmTrocarConta.setBackground(new Color(0, 0, 0));
				mntmTrocarConta.setForeground(new Color(255,255,255));
				
				mntmEncerrarConta.setBackground(new Color(0, 0, 0));
				mntmEncerrarConta.setForeground(new Color(255,255,255));

				mntmEditarDesp.setBackground(new Color(0, 0, 0));
				mntmEditarDesp.setForeground(new Color(255,255,255));
				
				mntmConsultarDesp.setBackground(new Color(0, 0, 0));
				mntmConsultarDesp.setForeground(new Color(255,255,255));
				
				mntmInserirNovaDespesa.setBackground(new Color(0, 0, 0));
				mntmInserirNovaDespesa.setForeground(new Color(255,255,255));
				
				mntmRemoverDesp.setBackground(new Color(0, 0, 0));
				mntmRemoverDesp.setForeground(new Color(255,255,255));

				mntmCalculoMensal.setBackground(new Color(0, 0, 0));
				mntmCalculoMensal.setForeground(new Color(255,255,255));
				
				mntmMesEsp.setBackground(new Color(0, 0, 0));
				mntmMesEsp.setForeground(new Color(255,255,255));
				
				mntmTabelaCompleta.setBackground(new Color(0, 0, 0));
				mntmTabelaCompleta.setForeground(new Color(255,255,255));
				
				mntmSobre.setBackground(new Color(0, 0, 0));
				mntmSobre.setForeground(new Color(255,255,255));
				
			}
		});
		mntmTemaEscuro.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/dark.png")));
		mntmTemaEscuro.setBackground(new Color(255, 255, 255));
		mntmTemaEscuro.setFont(new Font("Segoe UI", Font.ITALIC, 12));
		mnmConta.add(mntmTemaEscuro);
		
		mntmTemaClaro.setBackground(new Color(255, 255, 255));
		mntmTemaClaro.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0));
		mntmTemaClaro.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/light.png")));
		mnmConta.add(mntmTemaClaro);
		
		mntmCalculoMensal = new JMenuItem("Cálculo mensal");
		mntmCalculoMensal.setBackground(new Color(255, 255, 255));
		mntmCalculoMensal.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/chart-histogram.png")));
		mntmCalculoMensal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				painelCalculoMensal = new PainelCalculoMensal(userOnline);
				setContentPane(painelCalculoMensal);
				revalidate();
				
			}
		});
		mntmCalculoMensal.setFont(new Font("Segoe UI", Font.ITALIC, 12));
		mnmCalculos.add(mntmCalculoMensal);
		
		mnmTabela = new JMenu("Tabela");
		mnmTabela.setForeground(new Color(0, 0, 0));
		mnmTabela.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/cells.png")));
		mnmTabela.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 12));
		menuBar.add(mnmTabela);
		
		mntmMesEsp = new JMenuItem("Mês específico");
		mntmMesEsp.setBackground(new Color(255, 255, 255));
		mntmMesEsp.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/calendar (1).png")));
		mntmMesEsp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				painelConsultaMes = new PainelConsultaMes(userOnline);
				setContentPane(painelConsultaMes);
				revalidate();
				
			}
		});
		mntmMesEsp.setFont(new Font("Segoe UI", Font.ITALIC, 12));
		mnmTabela.add(mntmMesEsp);
		
	    mntmTabelaCompleta = new JMenuItem("Tabela completa");
		mntmTabelaCompleta.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/cells.png")));
		mntmTabelaCompleta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				painelTabelaCompleta = new PainelTabelaCompleta(userOnline);
				setContentPane(painelTabelaCompleta);
				revalidate();
			}
		});
		mntmTabelaCompleta.setFont(new Font("Segoe UI", Font.ITALIC, 12));
		mnmTabela.add(mntmTabelaCompleta);
		
		mnmSobre = new JMenu("Sobre");
		mnmSobre.setForeground(new Color(0, 0, 0));
		mnmSobre.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/info.png")));
		mnmSobre.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 12));
		menuBar.add(mnmSobre);
		
		mntmSobre = new JMenuItem("Sobre o sistema ");
		mntmSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				    dispose();
				   //Tela com informações sobre os istema sendo exibida em modal
		           TelaSobreSistema tela = new TelaSobreSistema(userOnline);
		           tela.setVisible(true);
				
			}
		});
		mntmSobre.setFont(new Font("Segoe UI", Font.ITALIC, 12));
		mntmSobre.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/about (1).png")));
		mntmSobre.setBackground(new Color(255, 255, 255));
		mnmSobre.add(mntmSobre);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		pnlAprensent = new JPanel();
		pnlAprensent.setBounds(0, 461, 725, 36);
		contentPane.add(pnlAprensent);
		pnlAprensent.setLayout(null);
		
		lblContato = new JLabel("Made by Pedro A/Gabriel (48988471534)");
		lblContato.setFont(new Font("Yu Gothic UI", Font.ITALIC, 9));
		lblContato.setBounds(275, 11, 299, 14);
		pnlAprensent.add(lblContato);
		
		lblIconPrincipal = new JLabel("");
		lblIconPrincipal.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/bankColor2.png")));
		lblIconPrincipal.setBounds(206, 44, 275, 326);
		contentPane.add(lblIconPrincipal);
		
		lblTItuloPrincipal = new JLabel("Sistema para gerenciamento de despesas mensais individuais ");
		lblTItuloPrincipal.setFont(new Font("Source Code Pro Black", Font.ITALIC, 11));
		lblTItuloPrincipal.setBounds(122, 356, 449, 14);
		contentPane.add(lblTItuloPrincipal);
		
		tglTrocaDeCor = new JToggleButton("");
		tglTrocaDeCor.setContentAreaFilled(false);
		tglTrocaDeCor.setBorderPainted(false);
		tglTrocaDeCor.setFocusPainted(false);
		tglTrocaDeCor.setSelectedIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/light.png")));
		tglTrocaDeCor.setBackground(new Color(0, 255, 255));
		tglTrocaDeCor.setBorder(null);
		tglTrocaDeCor.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/dark.png")));
		tglTrocaDeCor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (tglTrocaDeCor.isSelected()) {
					
					//ALTERANDO A COR DOS COMPONENTES 
					
					tglTrocaDeCor.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/light.png")));
					
					menuBar.setBackground(new Color(0, 0, 0));
					pnlAprensent.setBackground(new Color(0, 0, 0));
					lblContato.setForeground(new Color(255,255,255));
					mnmConta.setForeground(new Color(255,255,255));
					mnmDespesas.setForeground(new Color(255,255,255));
					mnmCalculos.setForeground(new Color(255,255,255));
					mnmSobre.setForeground(new Color(255,255,255));
					mnmTabela.setForeground(new Color(255,255,255));
					
					mntmVerConta.setBackground(new Color(0, 0, 0));
					mntmVerConta.setForeground(new Color(255,255,255));
					
					mntmTemaClaro.setBackground(new Color(0, 0, 0));
					mntmTemaClaro.setForeground(new Color(255,255,255));
					
					mntmTemaEscuro.setBackground(new Color(0, 0, 0));
					mntmTemaEscuro.setForeground(new Color(255,255,255));
					
					mntmAtualizarDados.setBackground(new Color(0, 0, 0));
					mntmAtualizarDados.setForeground(new Color(255,255,255));
					
					mntmTrocarConta.setBackground(new Color(0, 0, 0));
					mntmTrocarConta.setForeground(new Color(255,255,255));
					
					mntmEncerrarConta.setBackground(new Color(0, 0, 0));
					mntmEncerrarConta.setForeground(new Color(255,255,255));

					mntmEditarDesp.setBackground(new Color(0, 0, 0));
					mntmEditarDesp.setForeground(new Color(255,255,255));
					
					mntmConsultarDesp.setBackground(new Color(0, 0, 0));
					mntmConsultarDesp.setForeground(new Color(255,255,255));
					
					mntmInserirNovaDespesa.setBackground(new Color(0, 0, 0));
					mntmInserirNovaDespesa.setForeground(new Color(255,255,255));
					
					mntmRemoverDesp.setBackground(new Color(0, 0, 0));
					mntmRemoverDesp.setForeground(new Color(255,255,255));

					mntmCalculoMensal.setBackground(new Color(0, 0, 0));
					mntmCalculoMensal.setForeground(new Color(255,255,255));
					
					mntmMesEsp.setBackground(new Color(0, 0, 0));
					mntmMesEsp.setForeground(new Color(255,255,255));
					
					mntmTabelaCompleta.setBackground(new Color(0, 0, 0));
					mntmTabelaCompleta.setForeground(new Color(255,255,255));
					
					mntmSobre.setBackground(new Color(0, 0, 0));
					mntmSobre.setForeground(new Color(255,255,255));
					
		        } else {
		            // Código a ser executado quando o botão não estiver selecionado
		        
		        	tglTrocaDeCor.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/dark.png")));
		        	
		        	menuBar.setBackground(new Color(255,255,255));
					pnlAprensent.setBackground(new Color(255,255,255));
					lblContato.setForeground(new Color(0, 0, 0));
					mnmConta.setForeground(new Color(0, 0, 0));
					mnmDespesas.setForeground(new Color(0, 0, 0));
					mnmCalculos.setForeground(new Color(0, 0, 0));
					mnmSobre.setForeground(new Color(0, 0, 0));
					mnmTabela.setForeground(new Color(0, 0, 0));
					
					mntmVerConta.setBackground(new Color(255,255,255));
					mntmVerConta.setForeground(new Color(0, 0, 0));
					
					mntmAtualizarDados.setBackground(new Color(255,255,255));
					mntmAtualizarDados.setForeground(new Color(0, 0, 0));
					
					mntmTemaClaro.setBackground(new Color(255,255,255));
					mntmTemaClaro.setForeground(new Color(0, 0, 0));
					
					mntmTemaEscuro.setBackground(new Color(255,255,255));
					mntmTemaEscuro.setForeground(new Color(0, 0, 0));
					
					mntmTrocarConta.setBackground(new Color(255,255,255));
					mntmTrocarConta.setForeground(new Color(0, 0, 0));
					
					mntmEncerrarConta.setBackground(new Color(255,255,255));
					mntmEncerrarConta.setForeground(new Color(0, 0, 0));

					mntmEditarDesp.setBackground(new Color(255,255,255));
					mntmEditarDesp.setForeground(new Color(0, 0, 0));
					
					mntmConsultarDesp.setBackground(new Color(255,255,255));
					mntmConsultarDesp.setForeground(new Color(0, 0, 0));
					
					mntmInserirNovaDespesa.setBackground(new Color(255,255,255));
					mntmInserirNovaDespesa.setForeground(new Color(0, 0, 0));
					
					mntmRemoverDesp.setBackground(new Color(255,255,255));
					mntmRemoverDesp.setForeground(new Color(0, 0, 0));

					mntmCalculoMensal.setBackground(new Color(255,255,255));
					mntmCalculoMensal.setForeground(new Color(0, 0, 0));
					
					mntmMesEsp.setBackground(new Color(255,255,255));
					mntmMesEsp.setForeground(new Color(0, 0, 0));
					
					mntmTabelaCompleta.setBackground(new Color(255,255,255));
					mntmTabelaCompleta.setForeground(new Color(0, 0, 0));
					
					mntmSobre.setBackground(new Color(255,255,255));
					mntmSobre.setForeground(new Color(0, 0, 0));
					
		        }
			}
		});
		tglTrocaDeCor.setBounds(643, 11, 41, 23);
		contentPane.add(tglTrocaDeCor);
		
	}
}