package com.github.twitterswingsample.view.panels;

import java.awt.GridLayout;
import java.util.List;
import javax.swing.JPanel;
import twitter4j.Status;

public class TimelinePanel extends JPanel{

	public TimelinePanel() {
		setLayout(new GridLayout(0, 1, 12, 12));
	}

	public TimelinePanel(List<Status> statuses) {
		this();
		fillPanel(statuses);
	}
	
	public void setContent(List<Status> statuses){
		setVisible(false);
		removeAll();
		fillPanel(statuses);
		setVisible(true);
	}
	
	private void fillPanel(List<Status> statuses){
		for (int i = 0; i < statuses.size(); i++) {
			add(new StatusPanel(statuses.get(i)));
		}
	}
}