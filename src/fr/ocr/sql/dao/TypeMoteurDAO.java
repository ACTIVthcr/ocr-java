package fr.ocr.sql.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.ocr.sql.DAO;
import fr.ocr.sql.DAOException;
import voiture.moteur.TypeMoteur;

public class TypeMoteurDAO extends DAO<TypeMoteur> {

	public TypeMoteurDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(TypeMoteur obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(TypeMoteur obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(TypeMoteur obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TypeMoteur find(int id) throws DAOException {
		TypeMoteur typeMoteur = new TypeMoteur();
		try {
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM type_moteur WHERE id = " + id);
			if (result.first())
				typeMoteur = new TypeMoteur(id, result.getString("description"));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		}
		return typeMoteur;
	}

	@Override
	public List<TypeMoteur> findAll() throws DAOException {
		List<TypeMoteur> typeMoteurList = new ArrayList<>();
		try {
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM type_moteur");
			while (result.next())
				typeMoteurList.add(new TypeMoteur(result.getInt("id"), result.getString("description")));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		}
		return typeMoteurList;
	}

}
