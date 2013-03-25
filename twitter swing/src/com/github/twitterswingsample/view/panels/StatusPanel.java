package com.github.twitterswingsample.view.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.github.twitterswingsample.view.listener.TwitterUserPresentation;
import com.github.twitterswingsample.view.listener.authorized.Retweeter;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.User;

public class StatusPanel extends JPanel{

	public StatusPanel(Twitter twitter, Status status) {
		setLayout(new GridBagLayout());
		
		this.setBackground(Color.LIGHT_GRAY);
		
		User user = status.getUser();
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(3, 3, 3, 3);
		gbc.anchor = GridBagConstraints.WEST;
		
		try {
			gbc.gridheight = 5;
			JLabel label = new JLabel(new ImageIcon(new URL(user.getBiggerProfileImageURL())));
			label.addMouseListener(new TwitterUserPresentation(twitter, user));
			add(label, gbc);
		} catch (MalformedURLException e) {
			ConsolePanel.getSingleton().printMessage(new String[]{
					"Could not load profile image of " + user.getScreenName()
			});
		}	
		
		gbc.gridheight = 1;
		gbc.gridwidth = 5;
		gbc.gridx = 1;
		add(new JLabel("@" + user.getScreenName()), gbc);

		gbc.gridy = 1;
		JEditorPane tweetText = new JEditorPane();
		tweetText.setContentType("text/html");
		tweetText.setText("<font color='#000000'>" + status.getText()
	                + "</font>");
	        tweetText.setPreferredSize(new Dimension(420, 45));
	        tweetText.setEditable(false);
	        tweetText.setBackground(Color.LIGHT_GRAY);
		add(tweetText, gbc);

		gbc.gridy = 2;
		add(new JLabel(status.getCreatedAt() + ""), gbc);

		gbc.gridy = 4;
		JButton retweetBtn = new JButton("retweet");
		retweetBtn.setBackground(new Color(0,172,237));
		retweetBtn.addActionListener(new Retweeter(retweetBtn, twitter, status));
		if(status.isRetweetedByMe()){
			retweetBtn.setEnabled(false);
		}
		add(retweetBtn, gbc);
		
		gbc.gridwidth = 1;
		gbc.gridy = 3;
		if(status.getRetweetCount() > 0){
			add(new JLabel("Retweeted " + status.getRetweetCount() + " times"), gbc);
		}
	}
}