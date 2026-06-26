package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import model.Adherent;
import model.Avis;
import model.Dao;
import model.Livre;

public class AvisView extends JPanel{

	private static final long serialVersionUID = 1L;

	private JComboBox<Livre> comboLivres;
    private JComboBox<Adherent> comboAdherents;
    private JButton btnAjouter;
    private JTextArea textArea = new JTextArea();

    public AvisView() {
    	setLayout(new BorderLayout());
    	
    	textArea.add(new JLabel("Ajouter un avis : "));
    	
    	JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		btnAjouter = new JButton("Ajouter");
		buttonsPanel.add(btnAjouter);
		
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
		
		add(inputsPanel, BorderLayout.NORTH);
		add(textArea, BorderLayout.CENTER);
		add(buttonsPanel, BorderLayout.SOUTH);
		
		//Ecouteurs pour les boutons
		//Ecouteur pour le bouton permettant d'ajouter un avis
		btnAjouter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					ajouterAvis();
			}
		});
    }
    
    //Méthodes
  	/**
  	 * Charge toutes les données des livres et des adhérents 
  	 */
  	public void chargerVue() {
  		chargerComboLivres();
  		chargerComboAdherents();
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
  				comboLivres.addItem(livre);
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
  	
	private void ajouterAvis() {
		Livre livre = (Livre) comboLivres.getSelectedItem();
		Adherent adherent = (Adherent) comboAdherents.getSelectedItem();
		String texte = (String) textArea.getText();
		if (livre == null || adherent == null) {
			JOptionPane.showMessageDialog(this, "Veuillez sélectionner un livre et un adhérent.", "Erreur", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (texte == null) {
			JOptionPane.showMessageDialog(this, "Veuillez saisir un avis.", "Erreur", JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			Avis avis = new Avis(livre, adherent, texte, LocalDate.now());
			Dao.addAvis(avis);
			chargerVue();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erreur lors de l'emprunt d'un livre : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}

}
