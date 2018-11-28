package fr.ocr.ihm;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import fr.ocr.ihm.listener.NewVehiculeListener;
import fr.ocr.ihm.listener.ViewMenuListener;
import fr.ocr.sql.DAOTableFactory;
import fr.ocr.sql.DatabaseTable;
import fr.ocr.sql.HsqldbConnection;

public class Garage extends JFrame {

	// Les diff�rents objets de notre IHM
	private JMenuBar bar = new JMenuBar();
	private JMenu menuVehicule = new JMenu("Vehicule");
	private JMenuItem menuVehiculeAjouter = new JMenuItem("Ajouter");
	private JMenuItem menuVehiculeVoir = new JMenuItem("Voir");

	private JMenu menuMarque = new JMenu("Marque");
	private JMenuItem menuMarqueVoir = new JMenuItem("Voir");

	private JMenu menuMoteur = new JMenu("Moteur");
	private JMenuItem menuMoteurVoir = new JMenuItem("Voir");

	private JMenu menuOption = new JMenu("Option");
	private JMenuItem menuOptionVoir = new JMenuItem("Voir");

	private JMenu menuTypemoteur = new JMenu("Type de moteur");
	private JMenuItem menuTypemoteurVoir = new JMenuItem("Voir");

	private JTable tableau;
	private JButton change = new JButton("Changer la taille");
	// Contenu de notre combo
	private String[] comboData = { "Tr�s bien", "Bien", "Mal" };

	private JPanel contentPane = new JPanel();

	public Garage() {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("JTable");
		this.setSize(800, 400);
		// Donn�es de notre tableau
		tableau = DAOTableFactory.getTable(HsqldbConnection.getInstance(), DatabaseTable.VEHICULE);
		this.getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);
		this.setLocationRelativeTo(null);
		initMenu();
	}

	/**
	 * M�thode qui initialise les points de menu
	 */
	private void initMenu() {
		menuVehicule.add(menuVehiculeVoir);
		menuVehicule.add(menuVehiculeAjouter);
		menuVehiculeAjouter.addActionListener(new NewVehiculeListener(this));
		menuVehiculeAjouter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_MASK + KeyEvent.SHIFT_DOWN_MASK));
		menuVehiculeVoir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_MASK + KeyEvent.SHIFT_DOWN_MASK));
		menuVehiculeVoir.addActionListener(new ViewMenuListener(this, DatabaseTable.VEHICULE));
		menuVehicule.setMnemonic('v');

		menuMarque.add(menuMarqueVoir);
		menuMarque.setMnemonic('a');
		menuMarqueVoir.addActionListener(new ViewMenuListener(this, DatabaseTable.MARQUE));

		menuMoteur.add(menuMoteurVoir);
		menuMoteur.setMnemonic('m');
		menuMoteurVoir.addActionListener(new ViewMenuListener(this, DatabaseTable.MOTEUR));

		menuOption.add(menuOptionVoir);
		menuOption.setMnemonic('o');
		menuOptionVoir.addActionListener(new ViewMenuListener(this, DatabaseTable.OPTION));

		menuTypemoteur.add(menuTypemoteurVoir);
		menuTypemoteur.setMnemonic('t');
		menuTypemoteurVoir.addActionListener(new ViewMenuListener(this, DatabaseTable.TYPEMOTEUR));

		bar.add(menuVehicule);
		bar.add(menuMarque);
		bar.add(menuMoteur);
		bar.add(menuOption);
		bar.add(menuTypemoteur);

		this.setJMenuBar(bar);
	}

	public static void main(String[] args) {
		Garage g = new Garage();
		g.setVisible(true);
	}

}
