package com.github.twitterswingsample.view.listener.authorized.timelineloader;

import java.util.List;
import twitter4j.Status;
import twitter4j.Twitter;

import com.github.twitterswingsample.view.listener.authorized.AuthorizedAction;
import com.github.twitterswingsample.view.panels.ConsolePanel;
import com.github.twitterswingsample.view.panels.TimelinePanel;

/**
 * Abstract ActionListener that loads a timeline
 * 
 * @author multiprogger
 */
public abstract class TimelineLoader extends AuthorizedAction {

	private TimelinePanel panel;
	
	public TimelineLoader(TimelinePanel panel, Twitter twitter) {
		super(twitter);
		this.panel = panel;
	}
	
	protected void setTimelineContent(List<Status> newStatusList) {
		try {
			panel.addContent(getTwitter(), newStatusList);
		} catch (CloneNotSupportedException e) {
			ConsolePanel.getInstance().printMessage(new String[]{
				"Internal Error",
				"Please report that bug!",
				e.getLocalizedMessage()
			});
		}
	}
}
