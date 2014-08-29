package com.github.twitterswingsample.view.listener.authorized.timelineloader;

import twitter4j.Query;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import com.github.twitterswingsample.view.panels.ConsolePanel;
import com.github.twitterswingsample.view.panels.TimelinePanel;

public class ProjectHashtagTimelineLoader extends TimelineLoader{
	
	public ProjectHashtagTimelineLoader(TimelinePanel panel, Twitter twitter) {
		super(panel, twitter);
	}

	public void run() {
		try {
			long start = System.nanoTime();
			getTimelinePanel().replaceContent(getTwitter(), getTwitter().search(new Query("#T4JSS")).getTweets());
			long needed = (System.nanoTime() - start) / 1000000;
			
			ConsolePanel.getInstance().printMessage(new String[]{
				"Timeline for #T4JSS successfully loaded",
				needed + " milliseconds needed"
			});
		} catch (TwitterException e) {
			ConsolePanel.getInstance().printMessage(new String[]{
				"Could not load project hashtag timeline",
				e.getLocalizedMessage()
			});
		}
	}
}