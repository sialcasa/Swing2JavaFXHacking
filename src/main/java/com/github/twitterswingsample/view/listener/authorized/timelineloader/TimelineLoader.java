package com.github.twitterswingsample.view.listener.authorized.timelineloader;

import twitter4j.Twitter;

import com.github.twitterswingsample.view.listener.authorized.AuthorizedAction;
import com.github.twitterswingsample.view.panels.TimelinePanel;

/**
 * Abstract ActionListener that loads a timeline
 * 
 * @author sourcefranke
 */
public abstract class TimelineLoader extends AuthorizedAction {

	private TimelinePanel panel;
	
	public TimelineLoader(TimelinePanel panel, Twitter twitter) {
		super(twitter);
		this.panel = panel;
	}

	protected TimelinePanel getTimelinePanel() {
		return panel;
	}
}