package fr.ocr.ihm.listener;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import fr.ocr.sql.AbstractDAOFactory;
import fr.ocr.sql.DAO;
import fr.ocr.sql.DAOException;
import voiture.Vehicule;

//Notre listener pour le bouton
public class ButtonListener implements ActionListener {
	protected int column, row, id;
	protected JTable table;

	public void setColumn(int col) {
		this.column = col;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public void actionPerformed(ActionEvent event) {
		id = Integer.parseInt(table.getModel().getValueAt(row, 4).toString());
		try {
			AbstractDAOFactory daoFacotry = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
			DAO vehiculeDAO = daoFacotry.getVehiculeDAO();
			Vehicule vehicule = (Vehicule) vehiculeDAO.find(id);
			if (vehiculeDAO.delete(vehicule)) {
				JOptionPane.showMessageDialog(null, "Le vehicule a bien été supprimé", "Information", JOptionPane.INFORMATION_MESSAGE);
				Robot robot = new Robot();
				// Je triche ici je simule le renouvellement de page avec le raccourci.
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				robot.keyRelease(KeyEvent.VK_SHIFT);
				robot.keyRelease(KeyEvent.VK_V);
			} else {
				JOptionPane.showMessageDialog(null, "Le vehicule a déja été supprimé", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		} catch (DAOException | AWTException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}
}