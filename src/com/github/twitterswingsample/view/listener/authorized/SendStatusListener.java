package com.github.twitterswingsample.view.listener.authorized;

import com.github.twitterswingsample.view.panels.ConsolePanel;
import com.github.twitterswingsample.view.panels.StatusWritePanel;

import twitter4j.Twitter;
import twitter4j.TwitterException;

public class SendStatusListener  extends AuthorizedAction {

	private StatusWritePanel panel;
	
	public SendStatusListener(Twitter twitter, StatusWritePanel panel) {
		super(twitter);
		this.panel = panel;
	}

	public void run() {
		try {
			getTwitter().updateStatus(panel.getStatusText());
			ConsolePanel.getInstance().printMessage(new String[]{
					"Successfully tweeted text",
			});
		} catch (TwitterException e) {
			ConsolePanel.getInstance().printMessage(new String[]{
					"Could not tweet text",
					e.getErrorMessage()
			});
		}
	}
}