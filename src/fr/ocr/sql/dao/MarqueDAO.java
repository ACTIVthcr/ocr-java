package fr.ocr.sql.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.ocr.sql.DAO;
import fr.ocr.sql.DAOException;
import voiture.Marque;

public class MarqueDAO extends DAO<Marque> {

	public MarqueDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Marque obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Marque obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Marque obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Marque find(int id) throws DAOException {
		Marque marque = new Marque();
		try {
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM marque WHERE id = " + id);
			if (result.first())
				marque = new Marque(id, result.getString("nom"));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		}
		return marque;
	}
	
	public List<Marque> findAll() throws DAOException {
		List<Marque> marqueList = new ArrayList<>();
		try {
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM marque");
			while(result.next())
				marqueList.add(new Marque(result.getInt("id"), result.getString("nom")));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		}
		return marqueList;
	}

}
