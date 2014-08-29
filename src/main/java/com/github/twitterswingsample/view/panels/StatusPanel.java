package com.github.twitterswingsample.view.panels;

import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.github.twitterswingsample.view.TextHighlighter;
import com.github.twitterswingsample.view.TwitterButton;
import com.github.twitterswingsample.view.listener.ShortInfoTexter;
import com.github.twitterswingsample.view.listener.StatusHyperlinkListener;
import com.github.twitterswingsample.view.listener.authorized.Retweeter;
import com.github.twitterswingsample.view.listener.authorized.statusbased.UserInfoPanelCreator;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.User;

/**
 * Panel displaying one status
 * 
 * @author sourcefranke
 */
public class StatusPanel extends JPanel{

	private JLabel profileImage;
	private Status status;
	
	public StatusPanel(Twitter twitter, Status status, ClientUserPanel cuPanel, boolean isHomeTimeline) {
		this.status = status;
		setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(3, 3, 3, 3);
		gbc.anchor = GridBagConstraints.WEST;

		User user = status.getUser();
		try {
			gbc.gridheight = 5;
			profileImage = new JLabel(new ImageIcon(new URL(user.getBiggerProfileImageURL())));
			profileImage.setCursor(new Cursor(Cursor.HAND_CURSOR));
			profileImage.addMouseListener(new UserInfoPanelCreator(status, twitter, cuPanel, isHomeTimeline));
			profileImage.addMouseListener(new ShortInfoTexter("Show some information about the user"));
			add(profileImage, gbc);
		} catch (MalformedURLException e) {
			ConsolePanel.getInstance().printMessage(new String[]{"Could not load profile image of " + user.getScreenName()});
		}	
		
		gbc.gridheight = 1;
		gbc.gridwidth = 5;
		gbc.gridx = 1;
		JLabel screenName = new JLabel("@" + user.getScreenName());
		add(screenName, gbc);
		
		/* Edit the text of the tweet and insert it into the StatusPanel */
		gbc.gridy = 1;
		JEditorPane tweetText = new JEditorPane();
		tweetText.setContentType("text/html");
		tweetText.setText("<font style='font-family: Arial; color: #000000'>"
				+ TextHighlighter.highlightAll(status.getText()) + "</font>");
		tweetText.setEditable(false);
        tweetText.addHyperlinkListener(new StatusHyperlinkListener());
        add(tweetText, gbc);

		gbc.gridy = 2;
		JLabel created = new JLabel(status.getCreatedAt() + "");
		add(created, gbc);

		gbc.gridy = 4;
		TwitterButton retweetBtn = new TwitterButton("retweet");
		Retweeter retweeter = new Retweeter(retweetBtn, twitter, status);
		retweetBtn.addActionListener(retweeter);
		int imgSize = 17;
		try {
			BufferedImage image = ImageIO.read(getClass().getResource("images/retweet.png"));
			ImageIcon icon = new ImageIcon(image.getScaledInstance(imgSize, imgSize, Image.SCALE_SMOOTH));
			retweetBtn.setIcon(icon);
		} catch (IOException e) {}
		if(status.isRetweetedByMe()){
			retweetBtn.setEnabled(false);
		}
		add(retweetBtn, gbc);
		
		gbc.gridwidth = 1;
		gbc.gridy = 3;
		if(status.getRetweetCount() > 0){
			JLabel retweeted = new JLabel();
			try {
				BufferedImage image = ImageIO.read(getClass().getResource("images/retweeted.png"));
				ImageIcon icon = new ImageIcon(image.getScaledInstance(imgSize, imgSize, Image.SCALE_SMOOTH));
				retweeted.setIcon(icon);
				retweeted.setToolTipText("Retweeted");
				retweeted.setText(status.getRetweetCount() + "x");
			} catch (IOException e) {
				retweeted.setText("Retweeted " + status.getRetweetCount() + "x");
			}
			add(retweeted, gbc);
		}
	}
	
	public Status getStatus(){
		return status;
	}
}