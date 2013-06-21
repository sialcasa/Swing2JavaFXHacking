package com.github.twitterswingsample.view.panels;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.github.twitterswingsample.view.TextHighlighter;
import com.github.twitterswingsample.view.listener.StatusHyperlinkListener;
import com.github.twitterswingsample.view.listener.authorized.AuthorizedAction;
import com.github.twitterswingsample.view.listener.authorized.Retweeter;
import com.github.twitterswingsample.view.listener.authorized.statusbased.StatusMouseListener;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.User;

/**
 * Panel displaying one status
 * 
 * @author multiprogger
 */
public class StatusPanel extends JPanel{

	private JLabel profileImage;
	private Status status;
	private List<AuthorizedAction> authorizedActions;
	
	public StatusPanel(Twitter twitter, Status status) {
		authorizedActions = new ArrayList<AuthorizedAction>();
		this.status = status;
		int fontSize = 13;
		Font font = new Font("Arial", Font.BOLD, fontSize);
		setLayout(new GridBagLayout());
		setBackground(Color.LIGHT_GRAY);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(3, 3, 3, 3);
		gbc.anchor = GridBagConstraints.WEST;

		User user = status.getUser();
		try {
			gbc.gridheight = 5;
			profileImage = new JLabel(new ImageIcon(new URL(user.getBiggerProfileImageURL())));
			profileImage.setCursor(new Cursor(Cursor.HAND_CURSOR));
			add(profileImage, gbc);
		} catch (MalformedURLException e) {
			ConsolePanel.getInstance().printMessage(new String[]{"Could not load profile image of " + user.getScreenName()});
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
		retweetBtn.setBorderPainted(false);
		retweetBtn.setFont(font);
		retweetBtn.setBackground(new Color(120,172,237));
		Retweeter retweeter = new Retweeter(retweetBtn, twitter, status);
		authorizedActions.add(retweeter);
		retweetBtn.addActionListener(retweeter);
		try {
			BufferedImage image = ImageIO.read(getClass().getResource("images/retweet.png"));
			ImageIcon icon = new ImageIcon(image.getScaledInstance(fontSize + 5, fontSize + 5, Image.SCALE_SMOOTH));
			retweetBtn.setIcon(icon);
		} catch (IOException e) {
			retweetBtn.setBackground(new Color(0,172,237));
		}
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
				ImageIcon icon = new ImageIcon(image.getScaledInstance(fontSize + 5, fontSize + 5, Image.SCALE_SMOOTH));
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
	
	public synchronized void addMouseListenerToProfileImage(MouseListener l) {
		profileImage.addMouseListener(l);
	}
	
	public synchronized void addStatusMouseListenerToProfileImage(StatusMouseListener l) throws CloneNotSupportedException {
		l = (StatusMouseListener) l.clone();
		l.setStatus(status);
		profileImage.addMouseListener(l);
	}
}