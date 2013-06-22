package com.github.twitterswingsample.view.listener;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.github.twitterswingsample.view.panels.ClientUserPanel;
import com.github.twitterswingsample.view.panels.SearchPanel;

import twitter4j.Twitter;

public class SearchPanelCreator implements ActionListener {

	private Twitter twitter;
	private ClientUserPanel panel;
	
	public SearchPanelCreator(Twitter twitter, ClientUserPanel panel) {
		this.twitter = twitter;
		this.panel = panel;
	}

	public void actionPerformed(ActionEvent e) {
		SearchPanel search = new SearchPanel(twitter, panel);
		try {
			BufferedImage image = ImageIO.read(getClass().getResource("images/search.png"));
			ImageIcon icon = new ImageIcon(image.getScaledInstance(20, 20, Image.SCALE_SMOOTH));
			panel.addComponent("Search", icon, search, "Serch for tweets");
		} catch (IOException e1) {
			panel.addComponent("Search", search);
		}
	}
}