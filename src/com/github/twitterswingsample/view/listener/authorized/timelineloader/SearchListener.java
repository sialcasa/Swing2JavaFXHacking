package com.github.twitterswingsample.view.listener.authorized.timelineloader;

import javax.swing.text.JTextComponent;

import com.github.twitterswingsample.view.panels.ConsolePanel;
import com.github.twitterswingsample.view.panels.TimelinePanel;

import twitter4j.Query;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class SearchListener extends TimelineLoader {
	
	private JTextComponent inputField;
	
	public SearchListener(TimelinePanel panel, Twitter twitter, JTextComponent inputField) {
		super(panel, twitter);
		this.inputField = inputField;
	}

	public void run() {
		try {
			getTimelinePanel().replaceContent(getTwitter(), getTwitter().search(new Query(inputField.getText())).getTweets());
		} catch (TwitterException e) {
			ConsolePanel.getInstance().printMessage(new String[]{
				"Query failed",
				e.getLocalizedMessage()
			});
		}
	}
}