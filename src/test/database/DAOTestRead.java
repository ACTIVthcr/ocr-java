package test.database;

import java.util.ArrayList;
import java.util.List;

import fr.ocr.sql.AbstractDAOFactory;
import fr.ocr.sql.DAO;
import fr.ocr.sql.DAOException;
import voiture.Marque;
import voiture.Vehicule;
import voiture.moteur.Moteur;
import voiture.moteur.TypeMoteur;
import voiture.option.Option;

public class DAOTestRead {

	public static void main(String[] args) throws DAOException {

		AbstractDAOFactory abstractDAOFactory = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);

		System.out.println("TEST MARQUE DAO");
		DAO<Marque> marqueDAO = abstractDAOFactory.getMarqueDAO();
		System.out.println("MARQUE");
		for (int i = 0; i < 3; i++) {
			Marque marque = marqueDAO.find(i);
			System.out.println(marque);
		}
		delim();
		DAO<TypeMoteur> typeMoteurDAO = abstractDAOFactory.getTypeMoteurDAO();
		System.out.println("TEST TYPE_MOTEUR");
		for (int i = 0; i < 3; i++) {
			TypeMoteur typeMoteur = typeMoteurDAO.find(i);
			System.out.println(typeMoteur);
		}
		delim();
		DAO<Moteur> moteurDAO = abstractDAOFactory.getMoteurDAO();
		System.out.println("TEST MOTEUR");
		for (int i = 0; i < 8; i++) {
			Moteur moteur = moteurDAO.find(i);
			System.out.println(moteur);
		}
		delim();
		DAO<Option> optionDAO = abstractDAOFactory.getOptionDAO();
		System.out.println("TEST OPTION");
		for (int i = 0; i < 5; i++) {
			Option option = optionDAO.find(i);
			System.out.println(option);
		}
		delim();
		DAO<List<Option>> vehiculeOptionDAO = abstractDAOFactory.getVehiculeOptionDAO();
		System.out.println("TEST VEHICULE OPTION");
		for (int i = 14; i < 19; i += 2) {
			List<Option> optionList = vehiculeOptionDAO.find(i);
			System.out.println("VEHICULE " + i + " OPTIONS");
			for (Option option : optionList) {
				System.out.println(option);
			}
		}
		delim();
		DAO<Vehicule> vehiculeDAO = abstractDAOFactory.getVehiculeDAO();
		System.out.println("TEST VEHICULE");
		for (int i = 14; i < 19; i += 2) {
			Vehicule vehicule = vehiculeDAO.find(i);
			System.out.println(vehicule);
		}
		delim();
		List<Vehicule> vehicules = vehiculeDAO.findAll();
		for (Vehicule vehicule : vehicules) {
			System.out.println(vehicule);
		}
		

	}

	private static void delim() {
		System.out.println("----------------------------");
	}

}
