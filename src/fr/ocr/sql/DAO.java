package fr.ocr.sql;

import java.sql.Connection;
import java.util.List;

public abstract class DAO<T> {
	protected Connection connect = null;

	public DAO(Connection conn) {
		this.connect = conn;
	}

	/**
	 * Méthode de création
	 * 
	 * @param obj
	 * @return boolean
	 * @throws DAOException
	 */
	public abstract boolean create(T obj) throws DAOException;

	/**
	 * Méthode pour effacer
	 * 
	 * @param obj
	 * @return boolean
	 */
	public abstract boolean delete(T obj) throws DAOException;

	/**
	 * Méthode de mise à jour
	 * 
	 * @param obj
	 * @return boolean
	 */
	public abstract boolean update(T obj) throws DAOException;

	/**
	 * Méthode de recherche des informations
	 * 
	 * @param id
	 * @return T
	 */
	public abstract T find(int id) throws DAOException;

	/**
	 * Méthode de recherche des informations
	 * 
	 * @return T
	 */
	public abstract List<T> findAll() throws DAOException;

}