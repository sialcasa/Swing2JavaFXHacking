package com.github.twitterswingsample.view.listener;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import twitter4j.Twitter;

import com.github.twitterswingsample.view.TwitterButton;
import com.github.twitterswingsample.view.listener.authorized.timelineloader.ProjectHashtagTimelineLoader;
import com.github.twitterswingsample.view.panels.ClientUserPanel;
import com.github.twitterswingsample.view.panels.TimelinePanel;

public class ProjectHashtagTimlinePanelCreator implements ActionListener {
	
	private Twitter twitter;
	private ClientUserPanel panel;

	public ProjectHashtagTimlinePanelCreator(Twitter twitter, ClientUserPanel panel) {
		this.twitter = twitter;
		this.panel = panel;
	}

	public void actionPerformed(ActionEvent e) {
		JPanel timelineTab = new JPanel(new BorderLayout(5, 5));
		TimelinePanel timeline = new TimelinePanel(panel, false);

		JScrollPane timelineScrollPane = new JScrollPane(timeline);
//		timelineScrollPane.getVerticalScrollBar().setUI(new MyScrollBarUI());
//		timelineScrollPane.getHorizontalScrollBar().setUI(new MyScrollBarUI());
		timelineTab.add(timelineScrollPane, BorderLayout.CENTER);
		
		TwitterButton reloadTimeline = new TwitterButton("load #T4JSS Tweets");
		reloadTimeline.addActionListener(new ProjectHashtagTimelineLoader(timeline, twitter));
		timelineTab.add(reloadTimeline, BorderLayout.SOUTH);
		
		String screenName = "#T4JSS";
		try {
			BufferedImage image = ImageIO.read(getClass().getResource("images/hash.png"));
			ImageIcon icon = new ImageIcon(image.getScaledInstance(20, 20, Image.SCALE_SMOOTH));
			panel.addComponent(screenName, icon, timelineTab, "Tweets containing the hashtag #T4JSS");
		} catch (IOException e2) {
			panel.addComponent(screenName, timelineTab);
		}
	}
}