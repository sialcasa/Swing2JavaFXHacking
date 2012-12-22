package com.github.twitterswingsample.view.listener.timelineloader;

import twitter4j.TwitterException;
import com.github.twitterswingsample.model.Credentials;
import com.github.twitterswingsample.view.panels.ConsolePanel;
import com.github.twitterswingsample.view.panels.TimelinePanel;

public class HomeTimelineLoader extends TimelineLoader{
	
	public HomeTimelineLoader(TimelinePanel panel, Credentials creds) {
		super(panel, creds);
	}

	public void run() {
		try {
			long start = System.nanoTime();
			setTimelineContent(getTwitter().getHomeTimeline());
			long needed = (System.nanoTime() - start) / 1000000;
			ConsolePanel.getSingleton().printMessage(
					new String[]{
							"Home timeline successfully loaded",
							needed + " milliseconds needed"
					});
		} catch (TwitterException e) {
			ConsolePanel.getSingleton().printException("Could not load home timeline", e);
		}
	}
}
