package com.github.twitterswingsample.view.panels;

import java.awt.GridLayout;
import java.util.List;
import javax.swing.JPanel;
import twitter4j.Status;

public class TimelinePanel extends JPanel{

	public TimelinePanel(List<Status> statuses) {
		setLayout(new GridLayout(0, 1, 12, 12));
		
		for (int i = 0; i < statuses.size(); i++) {
			add(new StatusPanel(statuses.get(i)));
		}
	}
}