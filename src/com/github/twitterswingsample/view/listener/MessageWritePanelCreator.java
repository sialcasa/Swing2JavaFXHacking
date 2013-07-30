package com.github.twitterswingsample.view.listener;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import twitter4j.Twitter;
import twitter4j.User;

import com.github.twitterswingsample.view.panels.ClientUserPanel;
import com.github.twitterswingsample.view.panels.MessageWritePanel;

public class MessageWritePanelCreator implements ActionListener {

	private ClientUserPanel panel;
	private Twitter twitter;
	private User user;
	
	public MessageWritePanelCreator(Twitter twitter, User user, ClientUserPanel panel) {
		this.panel = panel;
		this.twitter = twitter;
		this.user = user;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			BufferedImage image = ImageIO.read(getClass().getResource("images/sendmessage.png"));
			ImageIcon icon = new ImageIcon(image.getScaledInstance(20, 20, Image.SCALE_SMOOTH));
			panel.addComponent("Write message", icon,
					new MessageWritePanel(twitter, user),
					"Write a direct message to @" + user.getScreenName());
		} catch (IOException e2) {
			panel.addComponent("Write message", new MessageWritePanel(twitter, user));
		}
	}
}