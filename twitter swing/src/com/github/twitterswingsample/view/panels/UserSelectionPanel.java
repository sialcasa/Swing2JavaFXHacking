package com.github.twitterswingsample.view.panels;

import java.awt.BorderLayout;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class UserSelectionPanel extends JPanel{

	private JCheckBox allUsers;
	
	public UserSelectionPanel() {
		setLayout(new BorderLayout(5, 5));
		
		
		allUsers = new JCheckBox("all Users");
		add(allUsers, BorderLayout.SOUTH);
	}
}