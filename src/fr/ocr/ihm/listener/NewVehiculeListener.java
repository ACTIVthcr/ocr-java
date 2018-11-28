package fr.ocr.ihm.listener;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import fr.ocr.sql.DAOException;
import fr.ocr.sql.DAOTableFactory;
import fr.ocr.sql.DatabaseTable;
import fr.ocr.sql.HsqldbConnection;

public class NewVehiculeListener implements ActionListener {

	private JFrame frame;

	public NewVehiculeListener(JFrame f) {
		frame = f;
	}

	public void actionPerformed(ActionEvent e) {
		AjouterVehiculeDialog dialog = null;
		try {
			dialog = new AjouterVehiculeDialog(frame, "Ajouter un nouveau vehicule", true);
		} catch (DAOException e1) {
			e1.printStackTrace();
			return;
		}
		dialog.setVisible(true);
		frame.getContentPane().removeAll();
		frame.getContentPane().add(new JScrollPane(DAOTableFactory.getTable(HsqldbConnection.getInstance(), DatabaseTable.VEHICULE)), BorderLayout.CENTER);
		frame.getContentPane().revalidate();
	}
}
