package fr.ocr.sql.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.ocr.sql.DAO;
import fr.ocr.sql.DAOException;
import voiture.moteur.Moteur;
import voiture.moteur.TypeMoteur;

public class MoteurDAO extends DAO<Moteur> {

	public MoteurDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Moteur obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Moteur obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Moteur obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Moteur find(int id) throws DAOException {
		Moteur moteur = new Moteur();
		try {
			String query = "SELECT moteur.id,moteur.moteur,moteur.cylindre,moteur.prix,type_moteur.id, type_moteur.description FROM moteur";
			query += " INNER JOIN type_moteur ON moteur.moteur=type_moteur.id ";
			query += "WHERE moteur.id = " + id;
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(query);
			if (result.first()) {
				int idMoteur = result.getInt("moteur");
				String description = result.getString("description");
				moteur = new Moteur(id, new TypeMoteur(idMoteur, description), result.getString("cylindre"), result.getDouble("prix"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		}
		return moteur;
	}

	@Override
	public List<Moteur> findAll() throws DAOException {
		List<Moteur> moteurList = new ArrayList<>();
		try {
			String query = "SELECT moteur.id,moteur.moteur,moteur.cylindre,moteur.prix,type_moteur.id, type_moteur.description FROM moteur";
			query += " INNER JOIN type_moteur ON moteur.moteur=type_moteur.id";
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(query);
			while (result.next()) {
				int idMoteur = result.getInt("moteur");
				String description = result.getString("description");
				moteurList.add(new Moteur(result.getInt("id"), new TypeMoteur(idMoteur, description), result.getString("cylindre"), result.getDouble("prix")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		}
		return moteurList;
	}

}
