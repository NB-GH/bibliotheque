package resources;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 * Classe contenant les styles graphiques de l'interface utilisateur
 */
public class Style {
	/**
	 * Couleurs de l'interface
	 */
	public static final Color BACKGROUND = new Color(244, 246, 249);
	public static final Color PANEL = Color.WHITE;
	public static final Color PRIMARY = new Color(52, 152, 219);
	public static final Color PRIMARY_DARK = new Color(31, 97, 141);
	public static final Color SUCCESS = new Color(39, 174, 96);
	public static final Color DANGER = new Color(231, 76, 60);
	public static final Color TEXT = new Color(44, 62, 80);
	public static final Color BORDER = new Color(220, 223, 230);
	public static final Color TABLE_SELECTION = new Color(214, 234, 248);
	
	/**
	 * Polices de l'interface
	 */
	public static final Font FONT = new Font("Segoe UI", Font.PLAIN, 14);
	public static final Font BUTTON_FONT = new Font("Segoe UI", Font.BOLD, 14);
	public static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 18);

	/**
	 * Bordures de l'interface
	 */
	public static final Border INPUT_BORDER = BorderFactory.createCompoundBorder(
		BorderFactory.createLineBorder(BORDER),
		BorderFactory.createEmptyBorder(3,8,3,8)
	);
	public static final Border BUTTON_BORDER = BorderFactory.createEmptyBorder(10, 18, 10, 18);
	
	/**
	 * Applique un style personnalisé à un panneau
	 * @param panel panneau qu'on souhaite personnaliser
	 */
	public static void stylePanel(JPanel panel) {
		panel.setBackground(PANEL);
		panel.setBorder(
			BorderFactory.createEmptyBorder(10, 10, 10, 10)
		);
	}

	/**
	 * Applique un style personnalisé à un label
	 * @param label label qu'on souhaite personnaliser
	 */
	public static void styleLabel(JLabel label) {
		label.setFont(FONT);
		label.setForeground(TEXT);
	}
	
	/**
	 * Applique un style personnalisé à un bouton
	 * @param btn bouton qu'on souhaite personnaliser
	 */
	public static void stylePrimaryButton(JButton btn) {
		btn.setBackground(PRIMARY);
		btn.setForeground(Color.WHITE);
		btn.setFont(BUTTON_FONT);
		btn.setFocusPainted(false);
		btn.setBorder(BUTTON_BORDER);
	}
	public static void styleDangerButton(JButton btn) {
		btn.setBackground(DANGER);
		btn.setForeground(Color.WHITE);
		btn.setFont(BUTTON_FONT);
		btn.setFocusPainted(false);
		btn.setBorder(BUTTON_BORDER);
	}
	public static void styleSuccessButton(JButton btn) {
		btn.setBackground(SUCCESS);
		btn.setForeground(Color.WHITE);
		btn.setFont(BUTTON_FONT);
		btn.setFocusPainted(false);
		btn.setBorder(BUTTON_BORDER);
	}

	/**
	 * Applique un style personnalisé à une case à cocher
	 * @param chk case à cocher qu'on souhaite personnaliser 
	 */
	public static void styleCheckBox(JCheckBox chk) {
		chk.setFont(FONT);
		chk.setBackground(PANEL);
		chk.setForeground(TEXT);
		
	}
	
	/**
	 * Applique un style personnalisé à un champ texte
	 * @param txt champ texte qu'on souhaite personnaliser
	 */
	public static void styleTextField(JTextField txt) {
		txt.setFont(FONT);
		txt.setForeground(TEXT);
		txt.setBorder(INPUT_BORDER);
		txt.setBackground(Color.WHITE);
	}

	/**
	 * applique un style personnalisé à une liste déroulante
	 * @param combo liste déroulante qu'on souhaite personnaliser
	 */
	public static void styleComboBox(JComboBox<?> combo) {
		combo.setFont(FONT);
		combo.setBackground(Color.WHITE);
		combo.setForeground(TEXT);
		combo.setBorder(INPUT_BORDER);
	}
	
	/**
	 * Applique un style personnalisé à un tableau
	 * @param table tableau qu'on souhaite personnaliser
	 */
	public static void styleTable(JTable table) {
		table.setFont(FONT);
		table.setRowHeight(22);
		table.setForeground(TEXT);
		table.setGridColor(BORDER);
		table.setSelectionBackground(TABLE_SELECTION);
		table.setSelectionForeground(TEXT);
		table.getTableHeader().setFont(TITLE_FONT);
		table.getTableHeader().setBackground(PRIMARY);
		table.getTableHeader().setForeground(Color.WHITE);
	}
	
	/**
	 * Applique un style personnalisé à un panneau d'onglets
	 * @param tabb panneau qu'on souhaite personnaliser
	 */
	public static void styleTabbedPane(JTabbedPane tabb) {
		tabb.setFont(BUTTON_FONT);
		tabb.setBackground(PANEL);
		tabb.setForeground(TEXT);
	}
	
	/**
	 * Applique le style global à un composant Swing
	 * @param component composant qu'on souhaite styliser
	 */
	public static void styleComponent(JComponent component) {
		component.setFont(FONT);
		component.setForeground(TEXT);
	}
}