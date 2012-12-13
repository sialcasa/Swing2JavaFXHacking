package com.github.twitterswingsample.view.listener.timelineloader;

import java.awt.event.ActionEvent;

import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import com.github.twitterswingsample.view.panels.TimelinePanel;

public class HomeTimelineLoader extends TimelineLoader{
	
	public HomeTimelineLoader(TimelinePanel panel) {
		super(panel);
	}

	public void actionPerformed(ActionEvent arg0) {
		try {
			setTimelineContent(TwitterFactory.getSingleton().getHomeTimeline());
		} catch (TwitterException e) {
			//TODO error message
		}
	}
}
