package com.github.twitterswingsample.view.listener.authorized;

import java.util.Date;

import com.github.twitterswingsample.view.panels.ConsolePanel;
import com.github.twitterswingsample.view.panels.MessageWritePanel;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

public class SendMessageListener extends AuthorizedAction {

	private User user;
	private MessageWritePanel panel;
	
	public SendMessageListener(Twitter twitter, User user, MessageWritePanel panel) {
		super(twitter);
		this.user = user;
		this.panel = panel;
	}

	public void run() {
		try {
			Date start = new Date();
			getTwitter().sendDirectMessage(user.getId(), panel.getMessageText());
			long milliseconds = new Date().getTime() - start.getTime();
			ConsolePanel.getInstance().printMessage(new String[]{
				"Successfully sent message to " + user.getName(),
				milliseconds + " milliseconds needed"
			});
		} catch (TwitterException e) {
			ConsolePanel.getInstance().printMessage(new String[]{
				"Could not send message",
				e.getLocalizedMessage()
			});
		}
	}
}