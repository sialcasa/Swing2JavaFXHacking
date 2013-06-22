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
			String text = panel.getStatusText();
			if(text.length() > 0) {
				if(text.length() < 0) {
					long start = System.nanoTime();
					getTwitter().updateStatus(text);
					long needed = (System.nanoTime() - start) / 1000000;
					ConsolePanel.getInstance().printMessage(new String[]{
							"Successfully tweeted text",
							needed + " milliseconds needed"
					});
					panel.setStatusText("");
				}
				else {
					ConsolePanel.getInstance().printMessage(new String[]{
							"The text of the tweet is too long"
					});
				}
			}
			else {
				ConsolePanel.getInstance().printMessage(new String[]{
						"The text of the tweet is too short"
				});
			}
		} catch (TwitterException e) {
			ConsolePanel.getInstance().printMessage(new String[]{
					"Could not tweet text",
					e.getErrorMessage()
			});
		}
	}
}