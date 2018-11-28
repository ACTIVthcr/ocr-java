package fr.ocr.ihm.listener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.ocr.sql.AbstractDAOFactory;
import fr.ocr.sql.DAO;
import fr.ocr.sql.DAOException;
import voiture.Vehicule;
import voiture.option.Option;

public class ViewDetailVehiculeDialog extends JDialog {

	boolean senData;
	private JLabel nomLabel, marqueLabel, typeMoteurLabel, prixLabel, optionsLabel, panTotalLabel;
	private int id;

	public ViewDetailVehiculeDialog(JFrame parent, String title, boolean modal, int id) throws DAOException {
		super(parent, title, modal);
		this.setSize(750, 400);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.id = id;
		this.initComponent();
	}

	private void initComponent() throws DAOException {

		AbstractDAOFactory daoFacotry = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		DAO vehiculeDAO = daoFacotry.getVehiculeDAO();
		Vehicule vehicule = (Vehicule) vehiculeDAO.find(id);

		// Le nom
		JPanel panNom = new JPanel();
		panNom.setBackground(Color.white);
		panNom.setPreferredSize(new Dimension(300, 60));
		panNom.setBorder(BorderFactory.createTitledBorder("Nom du véhicule"));
		nomLabel = new JLabel(vehicule.getNom());
		panNom.add(nomLabel);

		// La Marque
		JPanel panMarque = new JPanel();
		panMarque.setBackground(Color.white);
		panMarque.setPreferredSize(new Dimension(300, 60));
		panMarque.setBorder(BorderFactory.createTitledBorder("Marque du véhicule"));

		marqueLabel = new JLabel(vehicule.getMarque().toString());
		panMarque.add(marqueLabel);

		// Le Type de Moteur
		JPanel panTypeMoteur = new JPanel();
		panTypeMoteur.setBackground(Color.white);
		panTypeMoteur.setBorder(BorderFactory.createTitledBorder("Type de moteur du véhicule"));
		panTypeMoteur.setPreferredSize(new Dimension(600, 60));
		typeMoteurLabel = new JLabel(vehicule.getMoteur().toString());
		panTypeMoteur.add(typeMoteurLabel);

		// Le Prix
		JPanel panPrix = new JPanel();
		panPrix.setBackground(Color.white);
		panPrix.setBorder(BorderFactory.createTitledBorder("Prix du véhicule"));
		panPrix.setPreferredSize(new Dimension(300, 60));
		prixLabel = new JLabel("Prix sans option : " + vehicule.getPrix().toString() + "€");
		panPrix.add(prixLabel);

		// Les Options Disponibles
		JPanel panOptions = new JPanel();
		panOptions.setBackground(Color.white);
		panOptions.setBorder(BorderFactory.createTitledBorder("Options Disponibles"));
		panOptions.setPreferredSize(new Dimension(600, 60));
		List<Option> options = vehicule.getOptions();
		String optionsString = "";
		for (Option option : options) {
			optionsString += option.getNom() + "(" + option.getPrix() + " €) ";
		}
		optionsLabel = new JLabel(optionsString);
		panOptions.add(optionsLabel);

		// Le Prix Total
		JPanel panTotalPrix = new JPanel();
		panTotalPrix.setBackground(Color.white);
		panTotalPrix.setBorder(BorderFactory.createTitledBorder("Prix TOTAL du véhicule"));
		panTotalPrix.setBackground(Color.GREEN);
		panTotalPrix.setPreferredSize(new Dimension(600, 60));
		panTotalLabel = new JLabel(vehicule.getPrixTotal().toString() + "€");
		panTotalPrix.add(panTotalLabel);

		JPanel content = new JPanel();
		content.setBackground(Color.white);
		content.add(panNom);
		content.add(panMarque);
		content.add(panTypeMoteur);
		content.add(panPrix);
		content.add(panOptions);
		content.add(panTotalPrix);
		this.getContentPane().add(content, BorderLayout.CENTER);
	}

}
