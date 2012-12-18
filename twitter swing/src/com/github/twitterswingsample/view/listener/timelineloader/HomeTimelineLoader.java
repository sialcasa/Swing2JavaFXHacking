package com.github.twitterswingsample.view.listener.timelineloader;

import twitter4j.TwitterException;

import com.github.twitterswingsample.model.Credentials;
import com.github.twitterswingsample.view.panels.TimelinePanel;

public class HomeTimelineLoader extends TimelineLoader{
	
	public HomeTimelineLoader(TimelinePanel panel, Credentials creds) {
		super(panel, creds);
	}

	public void run() {
		try {
			setTimelineContent(getTwitter().getHomeTimeline());
		} catch (TwitterException e) {
			//TODO error message
		}
	}
}
