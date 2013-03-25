package com.github.twitterswingsample.view.listener.authorized.timelineloader;

import java.util.List;
import twitter4j.Status;
import twitter4j.Twitter;

import com.github.twitterswingsample.view.listener.authorized.AuthorizedAction;
import com.github.twitterswingsample.view.panels.TimelinePanel;

public abstract class TimelineLoader extends AuthorizedAction {

	private TimelinePanel panel;
	
	public TimelineLoader(TimelinePanel panel, Twitter twitter) {
		super(twitter);
		this.panel = panel;
	}
	
	protected void setTimelineContent(List<Status> statuses){
		panel.setContent(statuses);
	}
}
