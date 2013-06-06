package com.github.twitterswingsample.view.panels;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.JScrollPane;

import com.github.twitterswingsample.view.TextHighlighter;
import com.github.twitterswingsample.view.listener.StatusHyperlinkListener;
import com.github.twitterswingsample.view.listener.TwitterUserPresentation;
import com.github.twitterswingsample.view.listener.authorized.Retweeter;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.User;

/**
 * Panel displaying one status
 * 
 * @author multiprogger
 */
public class StatusPanel extends JPanel{

	public StatusPanel(Twitter twitter, Status status) {
		Font font = new Font("Arial", Font.BOLD, 13);
		setLayout(new GridBagLayout());
		setBackground(Color.LIGHT_GRAY);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(3, 3, 3, 3);
		gbc.anchor = GridBagConstraints.WEST;

		User user = status.getUser();
		try {
			gbc.gridheight = 5;
			JLabel label = new JLabel(new ImageIcon(new URL(user.getBiggerProfileImageURL())));
			label.addMouseListener(new TwitterUserPresentation(twitter, user));
			label.setCursor(new Cursor(Cursor.HAND_CURSOR));
			add(label, gbc);
		} catch (MalformedURLException e) {
			ConsolePanel.getSingleton().printMessage(new String[]{"Could not load profile image of " + user.getScreenName()});
		}	
		
		gbc.gridheight = 1;
		gbc.gridwidth = 5;
		gbc.gridx = 1;
		JLabel screenName = new JLabel("@" + user.getScreenName());
		screenName.setFont(font);
		add(screenName, gbc);
		
		/* Edit the text of the tweet and insert it into the StatusPanel */
		gbc.gridy = 1;
		JEditorPane tweetText = new JEditorPane();
		tweetText.setContentType("text/html");
		tweetText.setText("<font style='font-family: Arial; color: #000000'>"
				+ TextHighlighter.highlightAll(status.getText()) + "</font>");
        tweetText.setPreferredSize(new Dimension(420, 45));
        tweetText.setEditable(false);
        tweetText.setBackground(Color.LIGHT_GRAY);
        tweetText.addHyperlinkListener(new StatusHyperlinkListener());
		add(tweetText, gbc);

		gbc.gridy = 2;
		JLabel created = new JLabel(status.getCreatedAt() + "");
		created.setFont(font);
		add(created, gbc);

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
			JLabel retweeted = new JLabel("Retweeted " + status.getRetweetCount() + " times");
			retweeted.setFont(font);
			add(retweeted, gbc);
		}
	}
}