package com.github.twitterswingsample.view.listener.authorized;

import com.github.twitterswingsample.view.panels.ConsolePanel;
import com.github.twitterswingsample.view.panels.UserInfoPanel;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

/**
 * ActionListener that follows another user
 * 
 * @author sourcefranke
 */
public class Follower extends AuthorizedAction{

	private UserInfoPanel panel;
	private User user;
	
	public Follower(Twitter twitter, User user, UserInfoPanel panel) {
		super(twitter);
		this.panel = panel;
		this.user = user;
	}

	public void run() {
		try {
			getTwitter().createFriendship(user.getId());
			panel.setFollowed(true);
		} catch (TwitterException e) {
			ConsolePanel.getInstance().printMessage(new String[]{
					"unable to follow @" + user.getScreenName()
			});
		}
	}
}