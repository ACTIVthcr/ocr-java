package fr.ocr.sql.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.ocr.sql.DAO;
import fr.ocr.sql.DAOException;
import voiture.Vehicule;
import voiture.option.Option;

public class VehiculeDAO extends DAO<Vehicule> {

	public VehiculeDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Vehicule vehicule) throws DAOException {

		try {
			ResultSet nextID = connect.prepareStatement("CALL NEXT VALUE FOR seq_vehicule_id").executeQuery();
			if (nextID.next()) {
				vehicule.setId(nextID.getInt(1));
				String query = "INSERT INTO vehicule (id, marque, prix, moteur, nom) VALUES(?,?,?,?,?)";
				PreparedStatement preparedStatement = connect.prepareStatement(query);
				preparedStatement.setInt(1, vehicule.getId());
				preparedStatement.setInt(2, vehicule.getMarque().getId());
				preparedStatement.setDouble(3, vehicule.getPrix());
				preparedStatement.setInt(4, vehicule.getMoteur().getId());
				preparedStatement.setString(5, vehicule.getNom());
				int execute = preparedStatement.executeUpdate();
				DAO linkVehiculeOptionDAO = new LinkOptionVehicule(connect);
				boolean createOptions = linkVehiculeOptionDAO.create(vehicule);
				if (createOptions) {
					return execute > 0;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public boolean delete(Vehicule vehicule) throws DAOException {
		DAO linkVehiculeOptionDAO = new LinkOptionVehicule(connect);
		linkVehiculeOptionDAO.delete(vehicule);
		String query = "DELETE FROM vehicule WHERE id = ?";
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
	public Vehicule find(int id) throws DAOException {
		Vehicule vehicule = new Vehicule();
		try {
			String query = "SELECT vehicule.id, vehicule.marque, vehicule.moteur, vehicule.prix, vehicule.nom";
			query += " FROM vehicule";
			query += " WHERE vehicule.id = " + id;
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(query);
			if (result.first()) {
				vehicule = new Vehicule();
				vehicule.setId(result.getInt("id"));
				vehicule.setNom(result.getString("nom"));
				vehicule.setPrix(result.getDouble("prix"));
				VehiculeOptionDAO vehiculeOptionDAO = new VehiculeOptionDAO(connect);
				List<Option> listOption = vehiculeOptionDAO.find(result.getInt("id"));
				vehicule.setListOptions(listOption);
				MoteurDAO moteurDAO = new MoteurDAO(this.connect);
				vehicule.setMoteur(moteurDAO.find(result.getInt("moteur")));
				MarqueDAO marqueDAO = new MarqueDAO(this.connect);
				vehicule.setMarque(marqueDAO.find(result.getInt("marque")));
			} else {
				System.err.println("no result");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		}
		return vehicule;
	}

	@Override
	public List<Vehicule> findAll() throws DAOException {
		List<Vehicule> vehiculeList = new ArrayList<>();
		try {
			String query = "SELECT * FROM vehicule";
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(query);
			while (result.next()) {
				Vehicule vehicule = new Vehicule();
				vehicule.setId(result.getInt("id"));
				vehicule.setNom(result.getString("nom"));
				vehicule.setPrix(result.getDouble("prix"));
				VehiculeOptionDAO vehiculeOptionDAO = new VehiculeOptionDAO(connect);
				List<Option> listOption = vehiculeOptionDAO.find(result.getInt("id"));
				vehicule.setListOptions(listOption);
				MoteurDAO moteurDAO = new MoteurDAO(this.connect);
				vehicule.setMoteur(moteurDAO.find(result.getInt("moteur")));
				MarqueDAO marqueDAO = new MarqueDAO(this.connect);
				vehicule.setMarque(marqueDAO.find(result.getInt("marque")));
				vehiculeList.add(vehicule);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		}
		return vehiculeList;
	}

}
