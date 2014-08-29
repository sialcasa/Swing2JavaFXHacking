package com.github.twitterswingsample.view.listener.authorized.timelineloader;

import twitter4j.Twitter;
import twitter4j.TwitterException;

import com.github.twitterswingsample.view.panels.ConsolePanel;
import com.github.twitterswingsample.view.panels.TimelinePanel;

/**
 * ActionListener that loads the home timeline
 * 
 * @author sourcefranke
 */
public class HomeTimelineLoader extends TimelineLoader{
	
	public HomeTimelineLoader(TimelinePanel panel, Twitter twitter) {
		super(panel, twitter);
	}

	public void run() {
		try {
			long start = System.nanoTime();
			getTimelinePanel().addContent(getTwitter(), getTwitter().getHomeTimeline());
			long needed = (System.nanoTime() - start) / 1000000;
			
			ConsolePanel.getInstance().printMessage(new String[]{
				"Home timeline of " + getTwitter().getScreenName() + " successfully loaded",
				needed + " milliseconds needed"
			});
		} catch (TwitterException e) {
			ConsolePanel.getInstance().printMessage(new String[]{
				"Could not load home timeline",
				e.getLocalizedMessage()
			});
		}
	}
}