package com.github.twitterswingsample.view.panels;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import twitter4j.Twitter;
import com.github.twitterswingsample.view.listener.authorized.timelineloader.HomeTimelineLoader;

public class UserPanel extends JPanel{

	public UserPanel(Twitter twitter) {
		setLayout(new BorderLayout(5, 5));
		
		TimelinePanel homeTimeline = new TimelinePanel(twitter);
		HomeTimelineLoader homeTimelineLoader = new HomeTimelineLoader(homeTimeline, twitter);
		homeTimelineLoader.actionPerformed(null);
		add(new JScrollPane(homeTimeline), BorderLayout.CENTER);
		
		JButton reloadHomeTimeline = new JButton("reload Hometimeline");
		reloadHomeTimeline.addActionListener(homeTimelineLoader);
		add(reloadHomeTimeline, BorderLayout.SOUTH);
	}
}