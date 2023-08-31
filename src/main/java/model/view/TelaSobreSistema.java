package model.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.vo.UsuarioVO;

import java.awt.Toolkit;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import model.vo.*;
import javax.swing.JLabel;

public class TelaSobreSistema extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton okButton;
	private JLabel lblImagem;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TelaSobreSistema dialog = new TelaSobreSistema(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TelaSobreSistema(final UsuarioVO userLogado) {
		setModal(true);
		setTitle("Gerenciamento Mensal | Sobre o Sistema");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaSobreSistema.class.getResource("/icons/bank (1).png")));
		setBounds(100, 100, 623, 488);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(0, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			okButton = new JButton("");
			okButton.setFocusPainted(false);
			okButton.setContentAreaFilled(false);
			okButton.setBorderPainted(false);
			okButton.setBorder(null);
			okButton.setBounds(562, 416, 57, 33);
			contentPanel.add(okButton);
			okButton.setIcon(new ImageIcon(TelaSobreSistema.class.getResource("/icons/check-mark.png")));
			okButton.setBackground(new Color(0, 255, 255));
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					MenuPrincipal menu = new MenuPrincipal(userLogado);
					menu.setVisible(true);
				}
			});
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		
		lblImagem = new JLabel("");
		lblImagem.setIcon(new ImageIcon(TelaSobreSistema.class.getResource("/icons/sobreSistema.png")));
		lblImagem.setBounds(29, 35, 537, 347);
		contentPanel.add(lblImagem);
	}
}
