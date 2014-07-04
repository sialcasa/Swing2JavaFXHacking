package com.github.twitterswingsample.view.listener;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwitchAccountListener implements ActionListener {

	private CardLayout layout;
	private Container parent;
	private String name;
	
	public SwitchAccountListener(CardLayout layout, Container parent, String name) {
		this.layout = layout;
		this.parent = parent;
		this.name = name;
	}

	public void actionPerformed(ActionEvent e) {
		layout.show(parent, name);
	}
}