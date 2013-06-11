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
			List<Status> oldStatusList = panel.getStatusList();
			
			if(oldStatusList.size() > 0){
				int numberOfNewStatuses = 0;
				for (int i = 0; i < newStatusList.size(); i++) {
					if(newStatusList.get(i).getId() > oldStatusList.get(0).getId()){
						numberOfNewStatuses++;
					}
					else {
						break;
					}
				}
				
				for (int i = 0; i < numberOfNewStatuses; i++) {
					oldStatusList.add(i, newStatusList.get(i));
				}
				
				panel.setContent(getTwitter(), oldStatusList);
			}
			else {
				panel.setContent(getTwitter(), newStatusList);
			}
		} catch (CloneNotSupportedException e) {
			ConsolePanel.getInstance().printMessage(new String[]{
				"Internal Error",
				e.getLocalizedMessage(),
				"Please report that bug!"
			});
		}
	}
}
