package com.github.twitterswingsample.view.listener.authorized;

import com.github.twitterswingsample.view.panels.ConsolePanel;
import com.github.twitterswingsample.view.panels.UserInfoPanel;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

/**
 * ActionListener that unfollows another user
 * 
 * @author sourcefranke
 */
public class UnFollower extends AuthorizedAction{

	private UserInfoPanel panel;
	private User user;
	
	public UnFollower(Twitter twitter, User user, UserInfoPanel panel) {
		super(twitter);
		this.panel = panel;
		this.user = user;
	}

	public void run() {
		try {
			getTwitter().destroyFriendship(user.getId());
			panel.setFollowed(false);
		} catch (TwitterException e) {
			ConsolePanel.getInstance().printMessage(new String[]{
					"unable to unfollow @" + user.getScreenName()
			});
		}
	}
}