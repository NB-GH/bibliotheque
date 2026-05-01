package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Adherent;
import model.Dao;

public class AdherentView extends JPanel {
	
	private static final long serialVersionUID = 1L;

	private JTable tableAdherents;
	private DefaultTableModel tableModel;
	private JTextField txtNom, txtPrenom, txtEmail, txtTelephone, txtAdresse;
	private JButton btnAjouter, btnSupprimer, btnModifier;
	
	public AdherentView() {
		setLayout(new BorderLayout());
		
		String[] columns = {"ID", "Nom", "Prenom", "Email", "Téléphone", "Adresse", "Date d'inscription"};
		tableModel = new DefaultTableModel(columns, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; //toutes les cellules sont non editables
			}
		};
		tableAdherents = new JTable(tableModel);
		tableAdherents.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	
		JScrollPane scrollPane = new JScrollPane(tableAdherents);
		add(scrollPane, BorderLayout.CENTER);
		
		//Panel des champs de saisie
		JPanel inputsPanel = new JPanel(new GridLayout(7, 2, 5, 5));
		
		inputsPanel.add(new JLabel("Nom : "));
		txtNom = new JTextField();
		inputsPanel.add(txtNom);
		
		inputsPanel.add(new JLabel("Prénom : "));
		txtPrenom = new JTextField();
		inputsPanel.add(txtPrenom);
		
		inputsPanel.add(new JLabel("Email : "));
		txtEmail = new JTextField();
		inputsPanel.add(txtEmail);
		
		inputsPanel.add(new JLabel("Téléphone : "));
		txtTelephone = new JTextField();
		inputsPanel.add(txtTelephone);
		
		inputsPanel.add(new JLabel("Adresse : "));
        txtAdresse = new JTextField();
        inputsPanel.add(txtAdresse);
        
      //Panel des boutons
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		btnAjouter = new JButton("Ajouter");
		btnSupprimer = new JButton("Supprimer");
		btnModifier = new JButton("Modifier");
		
		buttonsPanel.add(btnAjouter);
		buttonsPanel.add(btnSupprimer);
		buttonsPanel.add(btnModifier);
		
		add(inputsPanel, BorderLayout.NORTH);
		add(buttonsPanel, BorderLayout.SOUTH);
		
		//charger les livres
		chargerAdherents();
		
		//Ecouteur pour la selection dans la table
		tableAdherents.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting() && tableAdherents.getSelectedRow() != -1) {
					fillFieldsFromSelectedRows();
				}
			}
		});
		
		//Ecouteurs pour les boutons
		//Ecouteur pour le bouton permettant d'ajouter un adherent
		btnAjouter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ajouterAdherent();
			}
		});

		//Ecouteur pour le bouton permettant de supprimer un adherent
		btnSupprimer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				supprimerAdherent();
			}
		});
		
		//Ecouteur pour le bouton permettant de modifier un adherent
		btnModifier.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modifierAdherent();
			}
		});
	}
		
	//Methodes
	//Méthode qui permet de charger tous les adherents
	private void chargerAdherents() {
		tableModel.setRowCount(0);//vide le tableau
		try {
			List<Adherent> adherents = Dao.getAllAdherents();
			for (Adherent adherent : adherents) {
				Object[] row = {
						adherent.getAdherentId(),
		                adherent.getNom(),
		                adherent.getPrenom(),
		                adherent.getEmail(),
		                adherent.getTelephone(),
		                adherent.getAdresse(),
		                adherent.getDateInscription()
				};
				tableModel.addRow(row);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erreur lors du chargement des adhérents : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
		}		
	}
	
	//Méthode qui permet de vider tous les champs
	private void viderChamps() {
		txtNom.setText("");
        txtPrenom.setText("");
        txtEmail.setText("");
        txtTelephone.setText("");
        txtAdresse.setText("");
	}
	
	//Méthode qui permet de ???
	private void fillFieldsFromSelectedRows() {
		int selectedRow = tableAdherents.getSelectedRow();
		try {
			if (selectedRow != -1) {
					int id = (int) tableAdherents.getValueAt(selectedRow, 1);
					Adherent adherent = Dao.getAdherentById(id);
					if (adherent != null) {
						txtNom.setText(adherent.getNom());
		                txtPrenom.setText(adherent.getPrenom());
		                txtEmail.setText(adherent.getEmail());
		                txtTelephone.setText(adherent.getTelephone());
		                txtAdresse.setText(adherent.getAdresse());
					}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erreur lors ??? : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	//Méthode qui permet d'ajouter un adherent
	private void ajouterAdherent() {
		String nom = txtNom.getText();
		String prenom = txtPrenom.getText();
		String email = txtEmail.getText();
		String telephone = txtTelephone.getText();
		String adresse = txtAdresse.getText();
		
		if (nom.isEmpty() || prenom.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Veuillez remplir au moins le nom et le prénom.", "Erreur", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		try {
			Adherent adherent = new Adherent(0, nom, prenom, email, telephone, adresse, LocalDate.now());
			Dao.addAdherent(adherent);
			chargerAdherents();
			viderChamps();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erreur lors du chargement des livres : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	//Méthode qui permet de supprimer un adhérent
	private void supprimerAdherent() {
		int selectedRow = tableAdherents.getSelectedRow();
		if(selectedRow == -1) {
			JOptionPane.showMessageDialog(this, "Veuillez sélectionner un adhérent.", "Erreur", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		try {
			int id = (int) tableAdherents.getValueAt(selectedRow, 0);
			int confirm = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment supprimer cet adhérent ?", "Confirmation", JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_OPTION) {
	            Dao.deleteLivre(id);
	            chargerAdherents();
	            viderChamps();
	        }
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erreur lors du chargement des livres : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
		}

	}
	
	//Méthode qui permet de modifier un adhérent
	private void modifierAdherent() {
		int selectedRow = tableAdherents.getSelectedRow();
		if(selectedRow == -1) {
			JOptionPane.showMessageDialog(this, "Veuillez selectionner un adhérent.", "Erreur", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		int id = (int) tableAdherents.getValueAt(selectedRow, 0);
		String nom = txtNom.getText();
		String prenom = txtPrenom.getText();
		String email = txtEmail.getText();
		String telephone = txtTelephone.getText();			
		String adresse = txtAdresse.getText();
		
		if (nom.isEmpty() || prenom.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Veuillez remplir au moins le nom et le prénom.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
		}
		
		try {
			Adherent adherent = new Adherent(id, nom, prenom, email, telephone, adresse, LocalDate.now());
			Dao.updateAdherent(adherent);
			chargerAdherents();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erreur lors du chargement des livres : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
}