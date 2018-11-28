package fr.ocr.ihm.listener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import fr.ocr.sql.AbstractDAOFactory;
import fr.ocr.sql.DAO;
import fr.ocr.sql.DAOException;
import voiture.Marque;
import voiture.Vehicule;
import voiture.moteur.Moteur;
import voiture.option.Option;

public class AjouterVehiculeDialog extends JDialog {
	
	boolean senData;
	private JLabel nomLabel, marqueLabel, moteurLabel;
	private JComboBox<Marque> marqueComboBox;
	private JComboBox<Moteur> moteurComboBox;
	private JRadioButton boutonToitOuvrant, boutonClimatisation, boutonGPS, boutonSiege, boutonBarre;
	private JTextField prix;
	private JTextField nom;

	public AjouterVehiculeDialog(JFrame parent, String title, boolean modal) throws DAOException {
		super(parent, title, modal);
		this.setSize(750, 350);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.initComponent();
	}
	
	private void initComponent() throws DAOException {

		// Le nom
		JPanel panNom = new JPanel();
		panNom.setBackground(Color.white);
		panNom.setPreferredSize(new Dimension(300, 60));
		nom = new JTextField();
		nom.setPreferredSize(new Dimension(100, 25));
		panNom.setBorder(BorderFactory.createTitledBorder("Nom vehicule"));
		nomLabel = new JLabel("Saisir un nom :");
		panNom.add(nomLabel);
		panNom.add(nom);

		// La marque
		JPanel panMarque = new JPanel();
		panMarque.setBackground(Color.white);
		panMarque.setPreferredSize(new Dimension(300, 60));
		panMarque.setBorder(BorderFactory.createTitledBorder("Marque du vehicule"));
		marqueComboBox = new JComboBox();
		AbstractDAOFactory daoFacotry = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		DAO marqueDAO = daoFacotry.getMarqueDAO();
		List<Marque> marqueList = marqueDAO.findAll();
		for (Marque marque : marqueList) {
			marqueComboBox.addItem(marque);
		}
		marqueLabel = new JLabel("Marque : ");
		panMarque.add(marqueLabel);
		panMarque.add(marqueComboBox);

		// Type de moteur du vehicule
		JPanel panMoteur = new JPanel();
		panMoteur.setBackground(Color.white);
		panMoteur.setPreferredSize(new Dimension(600, 60));
		panMoteur.setBorder(BorderFactory.createTitledBorder("Type de moteur du vehicule"));
		moteurComboBox = new JComboBox();
		DAO moteurDAO = daoFacotry.getMoteurDAO();
		List<Moteur> moteurs = moteurDAO.findAll();
		for (Moteur moteur : moteurs) {
			moteurComboBox.addItem(moteur);
		}
		moteurLabel = new JLabel("Type de moteur");
		panMoteur.add(moteurLabel);
		panMoteur.add(moteurComboBox);

		// Le Prix
		JPanel panPrix = new JPanel();
		panPrix.setBackground(Color.white);
		panPrix.setPreferredSize(new Dimension(300, 60));
		panPrix.setBorder(BorderFactory.createTitledBorder("Prix"));
		JLabel prixLabel = new JLabel("Prix : ");
		JLabel prix2Label = new JLabel(" €");
		prix = new JTextField();
		prix.setPreferredSize(new Dimension(90, 25));
		panPrix.add(prixLabel);
		panPrix.add(prix);
		panPrix.add(prix2Label);

		// Les options
		JPanel panOptions = new JPanel();
		panOptions.setBackground(Color.white);
		panOptions.setBorder(BorderFactory.createTitledBorder("Options disponibles"));
		panOptions.setPreferredSize(new Dimension(600, 60));
		DAO optionDAO = daoFacotry.getOptionDAO();
		List<Option> optionList = optionDAO.findAll();
		boutonToitOuvrant = new JRadioButton(optionList.get(0).getNom());
		boutonClimatisation = new JRadioButton(optionList.get(1).getNom());
		boutonGPS = new JRadioButton(optionList.get(2).getNom());
		boutonSiege = new JRadioButton(optionList.get(3).getNom());
		boutonBarre = new JRadioButton(optionList.get(4).getNom());

		panOptions.add(boutonToitOuvrant);
		panOptions.add(boutonClimatisation);
		panOptions.add(boutonGPS);
		panOptions.add(boutonSiege);
		panOptions.add(boutonBarre);

		JPanel content = new JPanel();
		content.setBackground(Color.white);
		content.add(panNom);
		content.add(panMarque);
		content.add(panMoteur);
		content.add(panPrix);
		content.add(panOptions);

		JPanel control = new JPanel();
		JButton okBouton = new JButton("OK");

		okBouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				senData = true;
				double prixVehicule = 0;
				if (!prix.getText().isEmpty()) {
					try {
						prixVehicule = Double.parseDouble(prix.getText().trim());
					} catch (NumberFormatException e) {
						e.printStackTrace();
						senData = false;
						JOptionPane.showMessageDialog(null, "Le champs \"prix\" doit être un nombre", "Erreur de format", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					senData = false;
					JOptionPane.showMessageDialog(null, "Le champs \"prix\" est obligatoire", "Champs obligatoire manquant", JOptionPane.ERROR_MESSAGE);
				}
				String nomVehicule = null;
				if (!nom.getText().isEmpty()) {
					nomVehicule = nom.getText();
				} else {
					senData = false;
					JOptionPane.showMessageDialog(null, "Le champs \"nom\" est obligatoire", "Champs obligatoire manquant", JOptionPane.ERROR_MESSAGE);
				}
				Moteur moteurVehicule = getMoteur();
				Marque marqueVehicule = getMarque();
				List<Option> optionsVehicule;
				try {
					optionsVehicule = getOptions();
				} catch (DAOException e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, "Un problème est survenue", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				AbstractDAOFactory daoFacotry = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
				DAO vehiculeDAO = daoFacotry.getVehiculeDAO();
				List<Vehicule> vehicules = null;
				try {
					vehicules = vehiculeDAO.findAll();
				} catch (DAOException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Un problème est survenue", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				Vehicule vehicule = new Vehicule(0, nomVehicule, marqueVehicule, moteurVehicule, optionsVehicule, prixVehicule);
				if (senData) {
					try {
						vehiculeDAO.create(vehicule);
					} catch (DAOException e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "Un problème est survenue", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					setVisible(false);
				} 
			}

			public Marque getMarque() {
				return (Marque) marqueComboBox.getSelectedItem();
			}

			public Moteur getMoteur() {
				return (Moteur) moteurComboBox.getSelectedItem();
			}

			public List<Option> getOptions() throws DAOException {
				List<Option> options = new ArrayList<>();
				AbstractDAOFactory daoFacotry = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
				DAO optionDAO = daoFacotry.getOptionDAO();
				List<Option> optionList = optionDAO.findAll();
				if (boutonToitOuvrant.isSelected()) {
					options.add(optionList.get(0));
				}
				if (boutonToitOuvrant.isSelected()) {
					options.add(optionList.get(1));
				}
				if (boutonToitOuvrant.isSelected()) {
					options.add(optionList.get(2));
				}
				if (boutonToitOuvrant.isSelected()) {
					options.add(optionList.get(3));
				}
				return options;
			}

		});

		JButton cancelBouton = new JButton("Annuler");
		cancelBouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});

		control.add(okBouton);
		control.add(cancelBouton);

		this.getContentPane().add(content, BorderLayout.CENTER);
		this.getContentPane().add(control, BorderLayout.SOUTH);
	}

}
