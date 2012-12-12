package com.github.twitterswingsample.view.panels;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import twitter4j.Status;

public class StatusPanel extends JPanel{

	public StatusPanel(Status status) {
		setLayout(new GridLayout(0, 1, 3, 3));
		add(new JLabel(status.getUser().getName()));
		add(new JLabel(status.getText()));
		add(new JLabel(status.getCreatedAt() + ""));
	}
}