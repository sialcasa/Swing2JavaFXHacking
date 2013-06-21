package com.github.twitterswingsample.view.listener;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.github.twitterswingsample.view.panels.ClientUserPanel;
import com.github.twitterswingsample.view.panels.StatusWritePanel;

import twitter4j.Twitter;

public class StatusWritePanelCreator implements ActionListener{

	private ClientUserPanel panel;
	private Twitter twitter;
	
	public StatusWritePanelCreator(Twitter twitter, ClientUserPanel panel) {
		this.panel = panel;
		this.twitter = twitter;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			BufferedImage image = ImageIO.read(getClass().getResource("images/write.png"));
			ImageIcon icon = new ImageIcon(image.getScaledInstance(20, 20, Image.SCALE_SMOOTH));
			panel.addComponent("Write", icon, new StatusWritePanel(twitter), "Write a tweet");
		} catch (IOException e2) {
			panel.addComponent("Write", new StatusWritePanel(twitter));
		}
	}
}