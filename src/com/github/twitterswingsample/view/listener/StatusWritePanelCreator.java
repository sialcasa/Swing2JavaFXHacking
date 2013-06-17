package com.github.twitterswingsample.view.listener;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.github.twitterswingsample.view.panels.StatusWritePanel;

import twitter4j.Twitter;

public class StatusWritePanelCreator implements ActionListener{

	private Container container;
	private Twitter twitter;
	
	public StatusWritePanelCreator(Container container, Twitter twitter) {
		this.container = container;
		this.twitter = twitter;
	}

	public void actionPerformed(ActionEvent e) {
		container.add("Write", new StatusWritePanel(twitter));
	}
}