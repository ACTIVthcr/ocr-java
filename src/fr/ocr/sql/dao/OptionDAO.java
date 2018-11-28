package fr.ocr.sql.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.ocr.sql.DAO;
import fr.ocr.sql.DAOException;
import voiture.option.Option;

public class OptionDAO extends DAO<Option> {

	public OptionDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Option obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Option obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Option obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Option find(int id) throws DAOException {
		Option option = new Option();
		try {
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM option WHERE id = " + id);
			if (result.first())
				option = new Option(id, result.getString("description"), result.getDouble("prix"));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		}
		return option;
	}

	@Override
	public List<Option> findAll() throws DAOException {
		List<Option> optionList = new ArrayList<>();
		try {
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM option");
			while(result.next())
				optionList.add(new Option(result.getInt("id"), result.getString("description"), result.getDouble("prix")));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		}
		return optionList;
	}

}
