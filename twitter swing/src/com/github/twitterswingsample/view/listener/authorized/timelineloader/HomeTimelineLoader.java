package com.github.twitterswingsample.view.listener.authorized.timelineloader;

import twitter4j.Twitter;
import twitter4j.TwitterException;

import com.github.twitterswingsample.model.Credentials;
import com.github.twitterswingsample.view.panels.ConsolePanel;
import com.github.twitterswingsample.view.panels.TimelinePanel;

public class HomeTimelineLoader extends TimelineLoader{
	
	public HomeTimelineLoader(TimelinePanel panel, Twitter twitter) {
		super(panel, twitter);
	}

	public void run() {
		try {
			long start = System.nanoTime();
			setTimelineContent(getTwitter().getHomeTimeline());
			long needed = (System.nanoTime() - start) / 1000000;
			
			ConsolePanel.getSingleton().printMessage(new String[]{
				"Home timeline of successfully loaded",
				needed + " milliseconds needed"
			});
		} catch (TwitterException e) {
			ConsolePanel.getSingleton().printMessage(new String[]{"Could not load home timeline"});
		}
	}
}
