package fr.ocr.ihm.listener;

import java.awt.event.ActionEvent;

import fr.ocr.sql.DAOException;

public class ViewDetailVehiculeListener extends ButtonListener {
	
	public void actionPerformed(ActionEvent e) {
		id = Integer.parseInt(table.getModel().getValueAt(row, 4).toString());
		try {
			ViewDetailVehiculeDialog viewDetailVehiculeDialog = new ViewDetailVehiculeDialog(null, "Detail d'un vehicule", true, id);
			viewDetailVehiculeDialog.setVisible(true);
		} catch (DAOException e1) {
			e1.printStackTrace();
			return;
		}
	}
}
