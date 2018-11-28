package fr.ocr.sql.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.ocr.sql.DAO;
import fr.ocr.sql.DAOException;
import voiture.option.Option;

public class VehiculeOptionDAO extends DAO<List<Option>> {

	public VehiculeOptionDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(List<Option> options) {
		return false;
	}

	@Override
	public boolean delete(List<Option> obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(List<Option> obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Option> find(int id) throws DAOException {
		List<Option> optionList = new ArrayList<>();
		try {
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM vehicule_option WHERE id_vehicule = " + id);
			while (result.next()) {
				int idOption = result.getInt("ID_OPTION");
				OptionDAO optionDao = new OptionDAO(this.connect);
				optionList.add(optionDao.find(idOption));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		}
		return optionList;
	}

	@Override
	public List<List<Option>> findAll() {
		return new ArrayList<>();
	}

}
