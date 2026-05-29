package view;

import model.Dao;
import model.Livre;
import resources.Style;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import java.util.List;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 * Onglet des livres 
 * Permet d'afficher, ajouter, supprimer et modifier les livres 
 * de la bibliothèque avec un tableau et des champs de texte  
 */
public class LivreView extends JPanel {
	
	private static final long serialVersionUID = 1L;

	private JTable tableLivres;
	private DefaultTableModel tableModel;
	private JTextField txtTitre, txtAuteur, txtIsbn, txtCategorie;
	private JCheckBox chkDisponible;
	private JButton btnAjouter, btnSupprimer, btnModifier;
	private EmpruntView empruntView;
	
	/**
	 * Constructeur de l'onglet des livres
	 * Initialise l'onglet avec un tableau scrollable, 
	 * une zone de champs de saisie et une zone de boutons 
	 */
	public LivreView() {
		setLayout(new BorderLayout());
		
		String[] columns = {"ID", "Titre", "Auteur", "ISBN", "Catégorie", "Disponible", "Date d'ajout"};
		tableModel = new DefaultTableModel(columns, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; //toutes les cellules sont non editables
			}
			@Override
		    public Class<?> getColumnClass(int columnIndex) {
		        if (columnIndex == 0) {
		            return Integer.class; 
		        }
		        return String.class;
		    }
		};
		tableLivres = new JTable(tableModel);
		tableLivres.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				
		JScrollPane scrollPane = new JScrollPane(tableLivres);
		add(scrollPane, BorderLayout.CENTER);
		
		//Panel des champs de saisie
		JPanel inputsPanel = new JPanel(new GridLayout(5, 2, 5, 5));
		
		inputsPanel.add(new JLabel("Titre : "));
		txtTitre = new JTextField();
		inputsPanel.add(txtTitre);
		
		inputsPanel.add(new JLabel("Auteur : "));
		txtAuteur = new JTextField();
		inputsPanel.add(txtAuteur);
		
		inputsPanel.add(new JLabel("ISBN : "));
		txtIsbn = new JTextField();
		inputsPanel.add(txtIsbn);
		
		inputsPanel.add(new JLabel("Catégorie : "));
		txtCategorie = new JTextField();
		inputsPanel.add(txtCategorie);
		
		inputsPanel.add(new JLabel("Disponible"));
		chkDisponible = new JCheckBox();
		inputsPanel.add(chkDisponible);
		
		//Panel des boutons
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		btnAjouter = new JButton("Ajouter");
		btnSupprimer = new JButton("Supprimer");
		btnModifier = new JButton("Modifier");
		
		buttonsPanel.add(btnAjouter);
		buttonsPanel.add(btnSupprimer);
		buttonsPanel.add(btnModifier);
		
		add(inputsPanel,BorderLayout.NORTH);
		add(buttonsPanel,BorderLayout.SOUTH);
		
		
		//Ecouteur pour la selection dans la table
		tableLivres.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting() && tableLivres.getSelectedRow() != -1) {
					fillFieldsFromSelectedRows();
				}
			}
		});
		
		//Gestion du style 
		Style.stylePanel(inputsPanel);
		Style.stylePanel(buttonsPanel);
		Style.styleTable(tableLivres);
		Style.styleTextField(txtTitre);
		Style.styleTextField(txtAuteur);
		Style.styleTextField(txtIsbn);
		Style.styleCheckBox(chkDisponible);
		Style.styleTextField(txtCategorie);
		Style.styleSuccessButton(btnAjouter);
		Style.stylePrimaryButton(btnModifier);
		Style.styleDangerButton(btnSupprimer);
		
		//Ecouteurs pour les boutons
		//Ecouteur pour le bouton permettant d'ajouter un livre
		btnAjouter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ajouterLivre();
			}
		});

		//Ecouteur pour le bouton permettant de supprimer un livre
		btnSupprimer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				supprimerLivre();
			}
		});
		
		//Ecouteur pour le bouton permettant de modifier un livre
		btnModifier.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modifierLivre();
			}
		});
	}
		
	//setter sur empruntView
	public void setEmpruntView(EmpruntView empruntView) { this.empruntView = empruntView; }
	
	//Methodes
	/**
	 * Charge les données des livres dans le tableau
	 */
	public void chargerVue() {
		chargerLivres();
	}
	
	/**
	 * Charge tous les livres et les affiche dans le tableau
	 */
	private void chargerLivres() {
		tableModel.setRowCount(0); //vide le tableau
		try {
			List<Livre> livres = Dao.getAllLivres();
			for (Livre livre : livres) {
				Object[] row = {
					livre.getLivreId(),
					livre.getTitre(),
					livre.getAuteur(),
					livre.getIsbn(),
					livre.getCategorie(),
					livre.isDisponible() ? "Oui" : "Non",
					livre.getDateAjout()
				};
				tableModel.addRow(row);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erreur lors du chargement des livres : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Vide tous les champs de texte
	 */
	private void viderChamps() {
		txtTitre.setText("");
        txtAuteur.setText("");
        txtIsbn.setText("");
        txtCategorie.setText("");
	}
	
	/**
	 * Remplit les champs avec les données d'un livre
	 */
	private void fillFieldsFromSelectedRows() {
		int selectedRow = tableLivres.getSelectedRow();
		try {
			if (selectedRow != -1) {
				int id = (int) tableLivres.getValueAt(selectedRow, 0);
				Livre livre = Dao.getLivreById(id);
				if (livre != null) {
					txtTitre.setText(livre.getTitre());
					txtAuteur.setText(livre.getAuteur());
					txtIsbn.setText(livre.getIsbn());
					txtCategorie.setText(livre.getCategorie());
					chkDisponible.setSelected(livre.isDisponible());
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erreur lors du remplissage d'un livre : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Ajoute un livre avec les données saisies dans les champs de texte
	 */
	private void ajouterLivre() {
		String titre = txtTitre.getText();
		String auteur = txtAuteur.getText();
		String isbn = txtIsbn.getText();
		boolean disponible = chkDisponible.isSelected();
		String categorie = txtCategorie.getText();
		
		if (titre.isEmpty() || auteur.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Veuillez remplir au moins le titre et l'auteur.", "Erreur", JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			Livre livre = new Livre(0, titre, auteur, isbn, disponible, categorie, LocalDate.now());
			Dao.addLivre(livre);
			chargerLivres();
			empruntView.chargerComboLivres();
			viderChamps();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout d'un livre : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Supprime le livre sélectionné dans le tableau
	 */
	private void supprimerLivre() {
		int selectedRow = tableLivres.getSelectedRow();
		if(selectedRow == -1) {
			JOptionPane.showMessageDialog(this, "Veuillez sélectionner un livre.", "Erreur", JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			int id = (int) tableLivres.getValueAt(selectedRow, 0);
			int confirm = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment supprimer ce livre ?", "Confirmation", JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_OPTION) {
	            Dao.deleteLivre(id);
	            chargerLivres();
	            empruntView.chargerComboLivres();
	            empruntView.chargerEmprunts();
	            viderChamps();
	        }
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erreur lors de la suppression d'un livre : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Modifie un livre par les données saisies dans les champs de texte
	 */
	private void modifierLivre() {
		int selectedRow = tableLivres.getSelectedRow();
		if(selectedRow == -1) {
			JOptionPane.showMessageDialog(this, "Veuillez selectionner un livre.", "Erreur", JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			int id = (int) tableLivres.getValueAt(selectedRow, 0);
			String titre = txtTitre.getText();
			String auteur = txtAuteur.getText();
			String isbn = txtIsbn.getText();
			boolean disponible = chkDisponible.isSelected();
			String categorie = txtCategorie.getText();
			
			if (titre.isEmpty() || auteur.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Veuillez remplir au moins le titre et l'auteur.", "Erreur", JOptionPane.ERROR_MESSAGE);
	            return;
			}
			if ((Dao.getEmpruntIdByLivreTitre(titre) != null) && disponible) {
				JOptionPane.showMessageDialog(this, "Ce livre est actuellement emprunté. \n Il ne peut être marqué comme disponible.", "Attention", JOptionPane.WARNING_MESSAGE);
				disponible = false;
			}
			
			Livre livre = new Livre(id, titre, auteur, isbn, disponible, categorie);
			Dao.updateLivre(livre);
			chargerLivres();
			empruntView.chargerComboLivres();
			empruntView.chargerEmprunts();
			viderChamps();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erreur lors de la modification d'un livre : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
}