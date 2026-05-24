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

import java.util.List;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;


public class EmpruntView extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JTable tableEmprunts;
	private DefaultTableModel tableModel;
    private JComboBox<Livre> comboLivres;
    private JComboBox<Adherent> comboAdherents;
    private JButton btnEmprunter, btnRetourner;
    
	public EmpruntView() {
		setLayout(new BorderLayout());
		
		// Tableau des emprunts
        String[] columns = {"ID", "Livre", "Adhérent", "Date d'emprunt", "Date de retour prévue"};
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
		
		//Panel des champs de saisie
		JPanel inputsPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.WEST;
		inputsPanel.add(new JLabel("Livre : "), c);
		
		c.gridx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		comboLivres = new JComboBox<>();
		inputsPanel.add(comboLivres, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0;
		c.fill = GridBagConstraints.NONE;
		inputsPanel.add(new JLabel("Adhérent : "), c);
		
		c.gridx = 1;
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		comboAdherents = new JComboBox<>();
		inputsPanel.add(comboAdherents, c);
		
		//Panel des boutons
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		btnEmprunter = new JButton("Emprunter");
		btnRetourner = new JButton("Retourner");
		
		buttonsPanel.add(btnEmprunter);
		buttonsPanel.add(btnRetourner);
		
		add(inputsPanel, BorderLayout.NORTH);
		add(buttonsPanel, BorderLayout.SOUTH);
		
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
	}

	//Méthodes
	//Méthode pour charger la vue
	public void chargerVue() {
		chargerComboAdherents();
		chargerComboLivres();
		chargerEmprunts();
	}
	
	//Méthode pour charger les livres
	private void chargerComboLivres() {
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
	
	//méthode pour charger les adhérents
	private void chargerComboAdherents() {
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
	
	//méthode pour recharger tous les emprunts
	private void chargerEmprunts() {
		tableModel.setRowCount(0); // Vider le tableau
		try {
		    List<Emprunt> emprunts = Dao.getAllEmprunts();
		    for (Emprunt emprunt : emprunts) {
		        Object[] row = {
		            emprunt.getEmpruntId(), // Assure-toi que cette valeur n'est pas nulle
		            emprunt.getLivre().getTitre(),
		            emprunt.getAdherent().getNom() + " " + emprunt.getAdherent().getPrenom(),
		            emprunt.getDateEmprunt(),
		            emprunt.getDateRetourPrevue()
		        };
		        tableModel.addRow(row);
		    }
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erreur lors du chargement des emprunts : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	//Méthode pour emprunter un livre
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
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erreur lors de l'emprunt d'un livre : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}

	//Méthode pour retourner un livre 
	private void retournerLivre() {
		int selectedRow = tableEmprunts.getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(this, "Veuillez sélectionner un emprunt.", "Erreur", JOptionPane.ERROR_MESSAGE);
			return;	
		}
		try {
			int id = (int) tableEmprunts.getValueAt(selectedRow, 0);
			Dao.returnEmprunt(id);
			chargerEmprunts();
			chargerComboLivres();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erreur lors du retour d'un livre : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
}
