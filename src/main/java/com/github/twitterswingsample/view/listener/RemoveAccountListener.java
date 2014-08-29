package com.github.twitterswingsample.view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import com.github.twitterswingsample.model.Credentials;
import com.github.twitterswingsample.view.panels.ConsolePanel;

public class RemoveAccountListener implements ActionListener {

	private Credentials creds;
	private int id;
	
	public RemoveAccountListener(Credentials creds, int id) {
		this.creds = creds;
		this.id = id;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			creds.removeUser(id);
			ConsolePanel.getInstance().printMessage(new String[]{
					"Removed account from database",
					"Change takes effect the next time you start this client"
			});
		} catch (ClassNotFoundException | SQLException e1) {
			ConsolePanel.getInstance().printMessage(new String[]{
					"Account couldn't be removed from database",
					"Maybe you already deleted that account",
					e1.getLocalizedMessage()
			});
		}
	}
}