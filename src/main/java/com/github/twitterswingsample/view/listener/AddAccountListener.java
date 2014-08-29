package com.github.twitterswingsample.view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import com.github.twitterswingsample.model.Credentials;
import com.github.twitterswingsample.view.LoginDataDialog;
import com.github.twitterswingsample.view.frames.MainFrame;
import com.github.twitterswingsample.view.panels.ConsolePanel;

public class AddAccountListener implements ActionListener{

	private MainFrame frame;
	private LoginDataDialog ldd;
	
	public AddAccountListener(MainFrame frame, LoginDataDialog ldd) {
		this.frame = frame;
		this.ldd = ldd;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			Credentials creds = new Credentials();
			int id = creds.addUser(ldd.getName(), ldd.getAPIKey(), ldd.getAPISecret(),
					ldd.getAccessToken(), ldd.getAccessTokenSecret());
			frame.addUser(creds, id);
			ConsolePanel.getInstance().printMessage(new String[]{
					"Account successfully added"
			});
			ldd.dispose();
		} catch (ClassNotFoundException | SQLException e1) {
			ConsolePanel.getInstance().printMessage(new String[]{
					"SQL Exception thrown, there is an incorrect SQL statement",
					"Please report that bug!",
					e1.getLocalizedMessage()
			});
		}
	}
}