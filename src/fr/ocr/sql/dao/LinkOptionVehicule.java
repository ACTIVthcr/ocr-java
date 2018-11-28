package fr.ocr.sql.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import fr.ocr.sql.DAO;
import fr.ocr.sql.DAOException;
import voiture.Vehicule;
import voiture.option.Option;

public class LinkOptionVehicule extends DAO<Vehicule> {

	public LinkOptionVehicule(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Vehicule vehicule) throws DAOException {
		try {
			int i = 0;
			DAO vehiculeDAO = new VehiculeDAO(connect);
			String query = "INSERT INTO vehicule_option (id_option, id_vehicule) VALUES(?, (SELECT id FROM vehicule WHERE id=?))";
			for (Option option : vehicule.getOptions()) {
				PreparedStatement preparedStatement = connect.prepareStatement(query);
				int vehiculeId = vehicule.getId();
				int optionId = option.getId();
				preparedStatement.setInt(1, optionId);
				preparedStatement.setInt(2, vehiculeId);
				i += preparedStatement.executeUpdate();
			}
			return i > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public boolean delete(Vehicule vehicule) throws DAOException {
		String query = "DELETE FROM vehicule_option WHERE id_vehicule = ?";
		try {
			PreparedStatement result = this.connect.prepareStatement(query);
			result.setInt(1, vehicule.getId());
			int execute = result.executeUpdate();
			return execute > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public boolean update(Vehicule obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Vehicule find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vehicule> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
