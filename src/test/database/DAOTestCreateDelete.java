package test.database;

import java.util.ArrayList;
import java.util.List;

import fr.ocr.sql.AbstractDAOFactory;
import fr.ocr.sql.DAO;
import fr.ocr.sql.DAOException;
import voiture.Marque;
import voiture.Vehicule;
import voiture.moteur.Moteur;
import voiture.option.Option;

public class DAOTestCreateDelete {

	public static void main(String[] args) throws DAOException {
		AbstractDAOFactory abstractDAOFactory = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		DAO<Vehicule> vehiculeDAO = abstractDAOFactory.getVehiculeDAO();
		DAO<Option> optionDAO = abstractDAOFactory.getOptionDAO();
		DAO<Marque> marqueDAO = abstractDAOFactory.getMarqueDAO();
		DAO<Moteur> moteurDAO = abstractDAOFactory.getMoteurDAO();
		List<Option> options = new ArrayList<>();
		options.add(optionDAO.find(0));
		options.add(optionDAO.find(2));
		options.add(optionDAO.find(3));
		Vehicule vehicule = new Vehicule(0, "Test1", marqueDAO.find(0), moteurDAO.find(0), options, 500d);
		vehiculeDAO.create(vehicule);
		Vehicule vehicule2 = new Vehicule(0, "Test2", marqueDAO.find(0), moteurDAO.find(0), options, 500d);
		vehiculeDAO.create(vehicule2);
		Main.main(args);
		vehiculeDAO.delete(vehicule);
		vehiculeDAO.delete(vehicule2);
		Main.main(args);
	}

}
