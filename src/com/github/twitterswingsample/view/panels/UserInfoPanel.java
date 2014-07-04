package com.github.twitterswingsample.view.panels;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.github.twitterswingsample.view.TwitterButton;
import com.github.twitterswingsample.view.listener.MessageWritePanelCreator;
import com.github.twitterswingsample.view.listener.authorized.Follower;
import com.github.twitterswingsample.view.listener.authorized.UnFollower;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

/**
 * Panel displaying some information about an single user
 * 
 * @author sourcefranke
 */
public class UserInfoPanel extends JPanel{
	
	private TwitterButton followBtn;
	private ActionListener follower, unFollower;
	private boolean followed;
	private User user;

	public UserInfoPanel(Twitter twitter, User user, ClientUserPanel panel) throws IllegalStateException, TwitterException {
		this(twitter, user, panel, twitter.showFriendship(twitter.getId(), user.getId()).isSourceFollowingTarget());
	}
	
	public UserInfoPanel(Twitter twitter, User user, ClientUserPanel panel, boolean followed) throws IllegalStateException {
		if (user == null) {
			setLayout(new FlowLayout());
			add(new JLabel("no user defined"));
		}
		else {
			this.user = user;
			this.followed = followed;
			follower = new Follower(twitter, user, this);
			unFollower = new UnFollower(twitter, user, this);
			setLayout(new GridBagLayout());
			
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.WEST;
			gbc.insets = new Insets(5, 5, 5, 5);
			
			try {
				gbc.gridheight = 10;
				gbc.gridy = 1;
				add(new JLabel(new ImageIcon(new URL(user.getOriginalProfileImageURL()))), gbc);
				gbc.gridheight = 1;
			} catch (MalformedURLException e) {
				ConsolePanel.getInstance().printMessage(new String[]{
						"Could not load profile image of " + user.getScreenName()
				});
			}
			
			gbc.gridwidth = 3;
			gbc.gridx = 1;
			gbc.gridy = 0;
			add(new JLabel(user.getName() + " - @" + user.getScreenName()), gbc);
	
			gbc.gridwidth = 10;
			gbc.gridy++;
			add(new JLabel(user.getDescription()), gbc);
	
			gbc.gridy++;
			add(new JLabel("is following " + user.getFriendsCount() + " other users"), gbc);
	
			gbc.gridy++;
			add(new JLabel("is followed by " + user.getFollowersCount() + " other users"), gbc);
	
			gbc.gridy++;
			add(new JLabel("is listed in " + user.getListedCount() + " lists"), gbc);
			
			gbc.gridy++;
			add(new JLabel("has written " + user.getStatusesCount() + " tweets"), gbc);
			
			gbc.gridy++;
			add(new JLabel("has favourited " + user.getFavouritesCount() + " tweets"), gbc);
			
			gbc.gridy++;
			add(new JLabel("has created his account at " + user.getCreatedAt()), gbc);

			gbc.gridwidth = 1;
			followBtn = new TwitterButton();
			gbc.gridy++;
			add(followBtn, gbc);
			
			TwitterButton sendMessage;
			try {
				BufferedImage image = ImageIO.read(getClass().getResource("images/sendmessage.png"));
				ImageIcon icon = new ImageIcon(image.getScaledInstance(20, 20, Image.SCALE_SMOOTH));
				sendMessage = new TwitterButton(icon);
			} catch (IOException e2) {
				sendMessage = new TwitterButton("write Message");
			}
			sendMessage.setToolTipText("Write a direct message to " + user.getScreenName());
			sendMessage.addActionListener(new MessageWritePanelCreator(twitter, user, panel));
			gbc.gridx++;
			add(sendMessage, gbc);
			
			setFollowed(followed);
		}
	}

	public User getUser() {
		return user;
	}

	public boolean isMarkedAsFollowed() {
		return followed;
	}
	
	public void setFollowed(boolean followed) {
		this.followed = followed;
		if(followed){
			followBtn.setText("unfollow");
			for (ActionListener listener : followBtn.getActionListeners()) {
				followBtn.removeActionListener(listener);
			}
			followBtn.addActionListener(unFollower);
		}
		else {
			followBtn.setText("follow");
			for (ActionListener listener : followBtn.getActionListeners()) {
				followBtn.removeActionListener(listener);
			}
			followBtn.addActionListener(follower);
		}
	}
}