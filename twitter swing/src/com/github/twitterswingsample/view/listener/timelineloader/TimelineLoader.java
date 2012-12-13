package com.github.twitterswingsample.view.listener.timelineloader;

import java.awt.event.ActionListener;
import java.util.List;
import twitter4j.Status;
import com.github.twitterswingsample.view.panels.TimelinePanel;

public abstract class TimelineLoader implements ActionListener{

	private TimelinePanel panel;
	
	public TimelineLoader(TimelinePanel panel) {
		this.panel = panel;
	}
	
	protected void setTimelineContent(List<Status> statuses){
		panel.setContent(statuses);
	}
}
