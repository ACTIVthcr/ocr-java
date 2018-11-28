package fr.ocr.sql;

public abstract class AbstractDAOFactory {

	public static final int DAO_FACTORY = 0;

	public abstract DAO getMarqueDAO();

	public abstract DAO getTypeMoteurDAO();

	public abstract DAO getMoteurDAO();

	public abstract DAO getOptionDAO();

	public abstract DAO getVehiculeDAO();
	
	public abstract DAO getVehiculeOptionDAO();

	// Méthode permettant de récupérer les Factory
	public static AbstractDAOFactory getFactory(int type) {
		switch (type) {
		case DAO_FACTORY:
			return new DAOFactory();
		default:
			return null;
		}
	}

}
