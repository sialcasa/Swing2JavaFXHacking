package com.github.twitterswingsample.view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTabbedPane;

public class TabCloser implements ActionListener {

	private JTabbedPane pane;
	
	public TabCloser(JTabbedPane pane) {
		this.pane = pane;
	}
	
	public void actionPerformed(ActionEvent e) {
		pane.removeTabAt(pane.getSelectedIndex());
	}
}
