package com.github.twitterswingsample.view.listener.authorized.statusbased;

import java.awt.event.MouseEvent;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

import com.github.twitterswingsample.view.panels.ConsolePanel;
import com.github.twitterswingsample.view.panels.UserInfoPanelContainer;
import com.github.twitterswingsample.view.panels.UserInfoPanel;

/**
 * MouseListener that creates a frame with {@link UserInfoPanel} in it
 * 
 * @author multiprogger
 */
public class UserInfoPanelCreator extends StatusMouseListener {

	private UserInfoPanelContainer container;
	private boolean insideHomeTimeline;
	
	public UserInfoPanelCreator(Status status, Twitter twitter, UserInfoPanelContainer container, boolean insideHomeTimeline) {
		super(twitter);
		setStatus(status);
		this.container = container;
		this.insideHomeTimeline = insideHomeTimeline;
	}

	public void run() {
		try {
			User user = getStatus().getUser();
			UserInfoPanel panel;
			if (insideHomeTimeline) {
				panel = new UserInfoPanel(getTwitter(), user, true);
			}
			else {
				panel = new UserInfoPanel(getTwitter(), user);
			}
			container.addUserInfoPanel("@" + user.getScreenName(), panel);
		} catch (TwitterException e) {
			ConsolePanel.getInstance().printMessage(new String[]{
				"Could not create user information",
				e.getLocalizedMessage()
			});
		}
	}

	public void mouseClicked(MouseEvent e) {
		new Thread(this).start();
	}

	public void mouseEntered(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
}