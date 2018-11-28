package fr.ocr.sql;

import java.sql.Connection;
import java.util.List;

import fr.ocr.sql.dao.LinkOptionVehicule;
import fr.ocr.sql.dao.MarqueDAO;
import fr.ocr.sql.dao.MoteurDAO;
import fr.ocr.sql.dao.OptionDAO;
import fr.ocr.sql.dao.TypeMoteurDAO;
import fr.ocr.sql.dao.VehiculeDAO;
import fr.ocr.sql.dao.VehiculeOptionDAO;
import voiture.Marque;
import voiture.Vehicule;
import voiture.moteur.Moteur;
import voiture.moteur.TypeMoteur;
import voiture.option.Option;

public class DAOFactory extends AbstractDAOFactory {
	protected static final Connection conn = GarageConnection.getInstance();

	public DAO<Marque> getMarqueDAO() {
		return new MarqueDAO(conn);
	}

	public DAO<TypeMoteur> getTypeMoteurDAO() {
		return new TypeMoteurDAO(conn);
	}

	public DAO<Moteur> getMoteurDAO() {
		return new MoteurDAO(conn);
	}

	public DAO<Option> getOptionDAO() {
		return new OptionDAO(conn);
	}

	public DAO<Vehicule> getVehiculeDAO() {
		return new VehiculeDAO(conn);
	}

	public DAO<List<Option>> getVehiculeOptionDAO() {
		return new VehiculeOptionDAO(conn);
	}

	public DAO<Vehicule> getLinkOptionVehiculeDAO() {
		return new LinkOptionVehicule(conn);
	}

}
