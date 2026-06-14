package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

import model.Adherent;
import model.Dao;
import model.Emprunt;
import model.Livre;
import resources.Style;

import java.util.List;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

/**
 * Onglet des emprunts 
 * Permet d'afficher, d'emprunter ou de retourner un livre
 * de la bibliothèque avec un tableau et des listes déroulables
 */
public class EmpruntView extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JTable tableEmprunts;
	private DefaultTableModel tableModel;
    private JComboBox<Livre> comboLivres;
    private JComboBox<Adherent> comboAdherents;
    private JButton btnEmprunter, btnRetourner, btnSupprimer;
    private LivreView livreView;
    
    /**
	 * Constructeur de l'onglet des emprunts
	 * Initialise l'onglet avec un tableau scrollable, 
	 * une zone de listes déroulables et une zone de boutons 
	 */
	public EmpruntView() {
		setLayout(new BorderLayout());
		
		// Tableau des emprunts
        String[] columns = {"ID", "Livre", "Adhérent", "Date d'emprunt", "Date de retour prévue", "Date de retour réelle"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Toutes les cellules sont non éditables
            }
            @Override
		    public Class<?> getColumnClass(int columnIndex) {
		        if (columnIndex == 0) {
		            return Integer.class; 
		        }
		        return String.class;
		    }
        };
        
		tableEmprunts = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(tableEmprunts);
		add(scrollPane, BorderLayout.CENTER);
		
		//Panel des listes déroulables
		JPanel inputsPanel = new JPanel(new GridBagLayout());
		GridBagConstraints cLabelLivres = new GridBagConstraints();
		GridBagConstraints cComboLivres = new GridBagConstraints();
		GridBagConstraints cLabelAdherents = new GridBagConstraints();		
		GridBagConstraints cComboAdherents = new GridBagConstraints();
		
		cLabelLivres.gridx = 0;
		cLabelLivres.gridy = 0;
		cLabelLivres.anchor = GridBagConstraints.WEST;
		inputsPanel.add(new JLabel("Livre : "), cLabelLivres);
		
		cComboLivres.gridx = 1;
		cComboLivres.gridy = 0;
		cComboLivres.fill = GridBagConstraints.HORIZONTAL;
		cComboLivres.weightx = 1;
		comboLivres = new JComboBox<>();
		inputsPanel.add(comboLivres, cComboLivres);
		Style.styleComboBox(comboLivres);
		
		cLabelAdherents.gridx = 0;
		cLabelAdherents.gridy = 1;
		cLabelAdherents.weightx = 0;
		cLabelAdherents.fill = GridBagConstraints.NONE;
		inputsPanel.add(new JLabel("Adhérent : "), cLabelAdherents);
		
		cComboAdherents.gridx = 1;
		cComboAdherents.gridy = 1;
		cComboAdherents.weightx = 1;
		cComboAdherents.fill = GridBagConstraints.HORIZONTAL;
		comboAdherents = new JComboBox<>();
		inputsPanel.add(comboAdherents, cComboAdherents);
		
		//Panel des boutons
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		btnEmprunter = new JButton("Emprunter");
		btnRetourner = new JButton("Retourner");
		btnSupprimer = new JButton("Supprimer");
		
		buttonsPanel.add(btnEmprunter);
		buttonsPanel.add(btnRetourner);
		buttonsPanel.add(btnSupprimer);
		
		add(inputsPanel, BorderLayout.NORTH);
		add(buttonsPanel, BorderLayout.SOUTH);
		
		//Gestion du style
		Style.stylePanel(inputsPanel);
		Style.stylePanel(buttonsPanel);
		Style.styleTable(tableEmprunts);
		Style.styleComboBox(comboAdherents);
		Style.styleSuccessButton(btnEmprunter);
		Style.stylePrimaryButton(btnRetourner);
		Style.styleDangerButton(btnSupprimer);
		
		//Ecouteurs pour les boutons
		//Ecouteur pour le bouton permettant d'emprunter un livre
		btnEmprunter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					emprunterLivre();
			}
		});

		//Ecouteur pour le bouton permettant de retourner un livre
		btnRetourner.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					retournerLivre();
			}
		});
		
		//Ecouteur pour le bouton permettant de supprimer un emprunt
		btnSupprimer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				supprimerEmprunt();
			}
		});
	}

	//setters sur les adherentView et livreView
	public void setLivreView(LivreView livreView) { this.livreView = livreView; }

	//Méthodes
	/**
	 * Charge toutes les données des livres, des adhérents et des emprunts  dans le tableau
	 */
	public void chargerVue() {
		chargerComboLivres();
		chargerComboAdherents();
		chargerEmprunts();
	}
	
	/**
	 * Charge tous les livres et les affiche dans la liste déroulante
	 */
	public void chargerComboLivres() {
		comboLivres.removeAllItems();
		try {
			List<Livre> livres = Dao.getAllLivres();
			comboLivres.addItem(null);
			for (Livre livre : livres) {
				if (livre.isDisponible()) {
					comboLivres.addItem(livre);
				}
			}
		} catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erreur lors du chargement des livres : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Charge tous les adhérents et les affiche dans la liste déroulante
	 */	
	public void chargerComboAdherents() {
		comboAdherents.removeAllItems();
		try {
			List<Adherent> adherents = Dao.getAllAdherents();
			comboAdherents.addItem(null);
			for (Adherent adherent : adherents) {
				comboAdherents.addItem(adherent);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erreur lors du chargement des adhérents : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Charge tous les emprunts et les affiche dans le tableau
	 */
	public void chargerEmprunts() {
		tableModel.setRowCount(0); // Vider le tableau
		try {
		    List<Emprunt> emprunts = Dao.getAllEmprunts();
		    for (Emprunt emprunt : emprunts) {
		        Object[] row = {
		            emprunt.getEmpruntId(), // Assure-toi que cette valeur n'est pas nulle
		            emprunt.getLivre().getTitre(),
		            emprunt.getAdherent().getNom() + " " + emprunt.getAdherent().getPrenom(),
		            emprunt.getDateEmprunt(),
		            emprunt.getDateRetourPrevue(),
		            (emprunt.getDateRetourReelle() != null) ? emprunt.getDateRetourReelle() : null 
		        };
		        tableModel.addRow(row);
		    }
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erreur lors du chargement des emprunts : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Emprunte le livre sélectionné par l'adhérent sélectionné dans les listes déroulantes
	 */ 
	private void emprunterLivre() {
		Livre livre = (Livre) comboLivres.getSelectedItem();
		Adherent adherent = (Adherent) comboAdherents.getSelectedItem();
		if (livre == null || adherent == null) {
			JOptionPane.showMessageDialog(this, "Veuillez sélectionner un livre et un adhérent.", "Erreur", JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			Emprunt emprunt = new Emprunt(livre, adherent, LocalDate.now(), LocalDate.now().plusDays(14));
			Dao.addEmprunt(emprunt);
			chargerEmprunts();
			chargerComboLivres();
			chargerComboAdherents();
			livreView.chargerVue();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erreur lors de l'emprunt d'un livre : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Retourne l'emprunt sélectionné dans le tableau
	 */
	private void retournerLivre() {
		int selectedRow = tableEmprunts.getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(this, "Veuillez sélectionner un emprunt.", "Erreur", JOptionPane.ERROR_MESSAGE);
			return;	
		}
		try {
			int id = (int) tableEmprunts.getValueAt(selectedRow, 0);
			if (tableEmprunts.getValueAt(selectedRow, 5) != null) {
				JOptionPane.showMessageDialog(this, "Cet emprunt est déja retourné. \n Il ne peut être retourné à nouveau.", "Attention", JOptionPane.WARNING_MESSAGE);
				return;
			}
			int confirm = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment retourner cet emprunt ?", "Confirmation", JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_OPTION) {
				Dao.returnEmprunt(id, LocalDate.now());
				chargerEmprunts();
				chargerComboLivres();
				livreView.chargerVue();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erreur lors du retour d'un livre : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	private void supprimerEmprunt() {
		int selectedRow = tableEmprunts.getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(this, "Veuillez sélectionner un emprunt.", "Erreur", JOptionPane.ERROR_MESSAGE);
			return;	
		}
		
		try {
			if (tableEmprunts.getValueAt(selectedRow, 5) == null) {
				JOptionPane.showMessageDialog(this, "Cet emprunt est actuellement en cours. \n Il ne peut être supprimé.", "Attention", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			int confirm = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment supprimer cet emprunt ?", "Confirmation", JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_OPTION) {
				int id = (int) tableEmprunts.getValueAt(selectedRow, 0);
				Dao.deleteEmprunt(id);
				chargerEmprunts();
				chargerComboLivres();
				livreView.chargerVue();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erreur lors de la suppression d'un emprunt : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
}