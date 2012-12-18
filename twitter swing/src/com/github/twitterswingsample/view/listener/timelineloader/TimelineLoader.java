package com.github.twitterswingsample.view.listener.timelineloader;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import twitter4j.Status;

import com.github.twitterswingsample.model.Credentials;
import com.github.twitterswingsample.view.listener.AuthorizedAction;
import com.github.twitterswingsample.view.panels.TimelinePanel;

public abstract class TimelineLoader extends AuthorizedAction implements ActionListener, Runnable{

	private TimelinePanel panel;
	
	public TimelineLoader(TimelinePanel panel, Credentials creds) {
		super(creds);
		this.panel = panel;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		new Thread(this).start();
	}
	
	protected void setTimelineContent(List<Status> statuses){
		panel.setContent(statuses);
	}
}
