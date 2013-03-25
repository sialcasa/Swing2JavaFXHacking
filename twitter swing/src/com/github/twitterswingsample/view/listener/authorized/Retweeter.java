package com.github.twitterswingsample.view.listener.authorized;

import javax.swing.JButton;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import com.github.twitterswingsample.view.panels.ConsolePanel;

public class Retweeter extends AuthorizedAction{

	private JButton btn;
	private Status status;
	
	public Retweeter(JButton btn, Twitter twitter, Status status) {
		super(twitter);
		this.btn = btn;
		this.status = status;
	}

	public void run() {
		try {
			long start = System.nanoTime();
			getTwitter().retweetStatus(status.getId());
			long needed = (System.nanoTime() - start) / 1000000;
			
			ConsolePanel.getSingleton().printMessage(new String[]{
				"Tweet of " + status.getUser().getName() + " successfully retweeted",
				needed + " milliseconds needed"
			});
			btn.setEnabled(false);
		} catch (TwitterException e) {
			ConsolePanel.getSingleton().printMessage(new String[]{
					"Unable to retweet status from " + status.getUser().getScreenName() + ":",
					"\"" + status.getText() + "\""
			});
		}
	}
}
