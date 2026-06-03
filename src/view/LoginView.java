package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import model.Dao;
import model.Utilisateur;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;

public class LoginView extends JDialog {
	
	private static final long serialVersionUID = 1L;

	private JTextField txtLogin;
	private JPasswordField txtPassword;
	private Utilisateur connecte;
	
	public LoginView(JFrame frame) {
		super(frame, "Connexion", true); 
        setSize(300, 180);
        setLocationRelativeTo(frame);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
		panel.add(new JLabel("Identifiant : "));
		txtLogin = new JTextField();
		panel.add(txtLogin);

		panel.add(new JLabel("Mot de passe : "));
		txtPassword = new JPasswordField();
		panel.add(txtPassword);
		
		panel.add(Box.createVerticalStrut(25));
		
		JPanel buttonPanel = new JPanel();
		panel.add(buttonPanel);

		JButton btnLogin = new JButton("Se connecter");
		buttonPanel.add(btnLogin);
		getRootPane().setDefaultButton(btnLogin);
		
		setContentPane(panel);
		
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				connecterUtilisateur();
			}
		});
	}
	
	private void connecterUtilisateur() {
		String login = txtLogin.getText();
		String password = new String(txtPassword.getPassword());
		
		if (login.isEmpty() || password.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Veuillez remplir votre identifiant et votre mot de passe.", "Erreur", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		try {
			Utilisateur utilisateur = Utilisateur.sansRole(login, password);
			connecte = Dao.loginUtilisateur(utilisateur);
			if (connecte == null) {
				JOptionPane.showMessageDialog(this, "Identifiant ou mot de passe incorrect !", "Erreur", JOptionPane.ERROR_MESSAGE);
				txtPassword.setText("");
			} else {
				dispose();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erreur lors de la connexion : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public Utilisateur getConnecte() { return connecte ; } 
}
