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
import resources.Style;

/**
 * Onglet des adhérents Permet d'afficher, ajouter, supprimer et modifier les
 * adhérents de la bibliothèque avec un tableau et des champs de texte
 */
public class AdherentView extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTable tableAdherents;
	private DefaultTableModel tableModel;
	private JTextField txtNom, txtPrenom, txtEmail, txtTelephone, txtAdresse;
	private JButton btnAjouter, btnSupprimer, btnModifier;
	private EmpruntView empruntView;

	/**
	 * Constructeur de l'onglet des adhérents Initialise l'onglet avec un tableau
	 * scrollable, une zone de champs de saisie et une zone de boutons
	 */
	public AdherentView() {
		setLayout(new BorderLayout());

		String[] columns = { "ID", "Nom", "Prenom", "Email", "Téléphone", "Adresse", "Date d'inscription" };
		tableModel = new DefaultTableModel(columns, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // toutes les cellules sont non editables
			}

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				if (columnIndex == 0) {
					return Integer.class;
				}
				return String.class;
			}
		};
		tableAdherents = new JTable(tableModel);
		tableAdherents.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(tableAdherents);
		add(scrollPane, BorderLayout.CENTER);

		// Panel des champs de saisie
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

		// Panel des boutons
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		btnAjouter = new JButton("Ajouter");
		btnSupprimer = new JButton("Supprimer");
		btnModifier = new JButton("Modifier");

		buttonsPanel.add(btnAjouter);
		buttonsPanel.add(btnSupprimer);
		buttonsPanel.add(btnModifier);

		add(inputsPanel, BorderLayout.NORTH);
		add(buttonsPanel, BorderLayout.SOUTH);

		// Ecouteur pour la selection dans la table
		tableAdherents.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting() && tableAdherents.getSelectedRow() != -1) {
					fillFieldsFromSelectedRows();
				}
			}
		});

		// Gestion du style
		Style.stylePanel(inputsPanel);
		Style.stylePanel(buttonsPanel);
		Style.styleTable(tableAdherents);
		Style.styleTextField(txtNom);
		Style.styleTextField(txtPrenom);
		Style.styleTextField(txtEmail);
		Style.styleTextField(txtTelephone);
		Style.styleTextField(txtAdresse);
		Style.styleSuccessButton(btnAjouter);
		Style.stylePrimaryButton(btnModifier);
		Style.styleDangerButton(btnSupprimer);

		// Ecouteurs pour les boutons
		// Ecouteur pour le bouton permettant d'ajouter un adherent
		btnAjouter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ajouterAdherent();
			}
		});

		// Ecouteur pour le bouton permettant de supprimer un adherent
		btnSupprimer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				supprimerAdherent();
			}
		});

		// Ecouteur pour le bouton permettant de modifier un adherent
		btnModifier.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modifierAdherent();
			}
		});
	}

	// setter sur empruntView
	public void setEmpruntView(EmpruntView empruntView) {
		this.empruntView = empruntView;
	}

	// Methodes
	/**
	 * Charge les données des adhérents dans le tableau
	 */
	public void chargerVue() {
		chargerAdherents();
	}

	/**
	 * Charge tous les adhérents et les affiche dans le tableau
	 */
	private void chargerAdherents() {
		tableModel.setRowCount(0); // vide le tableau
		try {
			List<Adherent> adherents = Dao.getAllAdherents();
			for (Adherent adherent : adherents) {
				Object[] row = { adherent.getAdherentId(), adherent.getNom(), adherent.getPrenom(), adherent.getEmail(),
						adherent.getTelephone(), adherent.getAdresse(), adherent.getDateInscription() };
				tableModel.addRow(row);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erreur lors du chargement des adhérents : " + e.getMessage(), "Erreur",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Vide tous les champs de texte
	 */
	private void viderChamps() {
		txtNom.setText("");
		txtPrenom.setText("");
		txtEmail.setText("");
		txtTelephone.setText("");
		txtAdresse.setText("");
	}

	/**
	 * Remplit les champs avec les données d'un adhérent
	 */
	private void fillFieldsFromSelectedRows() {
		int selectedRow = tableAdherents.getSelectedRow();
		try {
			if (selectedRow != -1) {
				int id = (int) tableAdherents.getValueAt(selectedRow, 0);
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
			JOptionPane.showMessageDialog(this, "Erreur lors du remplissage d'un adhérent : " + e.getMessage(),
					"Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Ajoute un adherent avec les données saisies dans les champs de texte
	 */
	private void ajouterAdherent() {
		String nom = txtNom.getText();
		String prenom = txtPrenom.getText();
		String email = txtEmail.getText();
		String telephone = txtTelephone.getText();
		String adresse = txtAdresse.getText();

		if (nom.isEmpty() || prenom.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Veuillez remplir au moins le nom et le prénom.", "Erreur",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		try {
			Adherent adherent = new Adherent(0, nom, prenom, email, telephone, adresse, LocalDate.now());
			Dao.addAdherent(adherent);
			chargerAdherents();
			viderChamps();
			empruntView.chargerComboAdherents();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout d'un adhérent : " + e.getMessage(), "Erreur",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Supprime l'adhérent sélectionné dans le tableau
	 */
	private void supprimerAdherent() {
		int selectedRow = tableAdherents.getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(this, "Veuillez sélectionner un adhérent.", "Erreur",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		try {
			int id = (int) tableAdherents.getValueAt(selectedRow, 0);
			int confirm = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment supprimer cet adhérent ?",
					"Confirmation", JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_OPTION) {
				Dao.deleteAdherent(id);
				chargerAdherents();
				empruntView.chargerComboAdherents();
				empruntView.chargerEmprunts();
				viderChamps();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erreur lors du la suppression d'un adhérent : " + e.getMessage(),
					"Erreur", JOptionPane.ERROR_MESSAGE);
		}

	}

	/**
	 * Modifie un adhérent par les données saisies dans les champs de texte
	 */
	private void modifierAdherent() {
		int selectedRow = tableAdherents.getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(this, "Veuillez selectionner un adhérent.", "Erreur",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		int id = (int) tableAdherents.getValueAt(selectedRow, 0);
		String nom = txtNom.getText();
		String prenom = txtPrenom.getText();
		String email = txtEmail.getText();
		String telephone = txtTelephone.getText();
		String adresse = txtAdresse.getText();

		if (nom.isEmpty() || prenom.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Veuillez remplir au moins le nom et le prénom.", "Erreur",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		try {
			Adherent adherent = new Adherent(id, nom, prenom, email, telephone, adresse);
			Dao.updateAdherent(adherent);
			chargerAdherents();
			empruntView.chargerComboAdherents();
			empruntView.chargerEmprunts();
			viderChamps();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erreur lors de la modification d'un adhérent : " + e.getMessage(),
					"Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
}