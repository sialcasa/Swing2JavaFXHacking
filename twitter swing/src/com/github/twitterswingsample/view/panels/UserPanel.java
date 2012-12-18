package com.github.twitterswingsample.view.panels;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.github.twitterswingsample.model.Credentials;
import com.github.twitterswingsample.view.listener.timelineloader.HomeTimelineLoader;

public class UserPanel extends JPanel{

	public UserPanel(Credentials creds) {
		setLayout(new BorderLayout(5, 5));
		
		TimelinePanel homeTimeline = new TimelinePanel();
		HomeTimelineLoader homeTimelineLoader = new HomeTimelineLoader(homeTimeline, creds);
		homeTimelineLoader.actionPerformed(null);
		add(new JScrollPane(homeTimeline), BorderLayout.CENTER);
		
		JButton reloadHomeTimeline = new JButton("reload Hometimeline");
		reloadHomeTimeline.addActionListener(homeTimelineLoader);
		add(reloadHomeTimeline, BorderLayout.SOUTH);
	}
}