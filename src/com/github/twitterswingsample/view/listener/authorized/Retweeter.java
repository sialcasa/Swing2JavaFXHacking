package com.github.twitterswingsample.view.listener.authorized;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import com.github.twitterswingsample.view.TwitterButton;
import com.github.twitterswingsample.view.panels.ConsolePanel;

/**
 * ActionListener that retweets a status
 * 
 * @author sourcefranke
 */
public class Retweeter extends AuthorizedAction{

	private TwitterButton btn;
	private Status status;
	
	/**
	 * 
	 * 
	 * @param btn
	 * @param twitter 
	 * @param status to be retweeted
	 */
	public Retweeter(TwitterButton btn, Twitter twitter, Status status) {
		super(twitter);
		this.btn = btn;
		this.status = status;
	}

	public void run() {
		try {
			long start = System.nanoTime();
			getTwitter().retweetStatus(status.getId());
			long needed = (System.nanoTime() - start) / 1000000;
			
			ConsolePanel.getInstance().printMessage(new String[]{
				"Tweet of " + status.getUser().getName() + " successfully retweeted",
				needed + " milliseconds needed"
			});
			btn.setEnabled(false);
		} catch (TwitterException e) {
			ConsolePanel.getInstance().printMessage(new String[]{
					"Unable to retweet status from " + status.getUser().getScreenName() + ":",
					"\"" + status.getText() + "\""
			});
		}
	}
}
