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

import com.github.twitterswingsample.view.TwitterButton;
import com.github.twitterswingsample.view.listener.authorized.timelineloader.HomeTimelineLoader;
import com.github.twitterswingsample.view.panels.ClientUserPanel;
import com.github.twitterswingsample.view.panels.TimelinePanel;

import twitter4j.Twitter;
import twitter4j.TwitterException;

public class HomeTimelinePanelCreator implements ActionListener {
	
	private Twitter twitter;
	private ClientUserPanel panel;

	public HomeTimelinePanelCreator(Twitter twitter, ClientUserPanel panel) {
		this.twitter = twitter;
		this.panel = panel;
	}

	public void actionPerformed(ActionEvent e) {
		JPanel timelineTab = new JPanel(new BorderLayout(5, 5));
		TimelinePanel homeTimeline = new TimelinePanel(panel, true);

		JScrollPane homeTimelineScrollPane = new JScrollPane(homeTimeline);
//		homeTimelineScrollPane.getVerticalScrollBar().setUI(new MyScrollBarUI());
//		homeTimelineScrollPane.getHorizontalScrollBar().setUI(new MyScrollBarUI());
		timelineTab.add(homeTimelineScrollPane, BorderLayout.CENTER);
		
		TwitterButton reloadHomeTimeline = new TwitterButton("load Home Timeline");
		reloadHomeTimeline.addActionListener(new HomeTimelineLoader(homeTimeline, twitter));
		timelineTab.add(reloadHomeTimeline, BorderLayout.SOUTH);
		
		String screenName = "@";
		try {
			String tip = "";
			try {
				screenName += twitter.getScreenName();
				tip = "Home Timeline of " + screenName;
			} catch (IllegalStateException e1) {
				tip = "Your Home Timeline";
			} catch (TwitterException e2) {
				tip = "Your Home Timeline";
			}
			
			BufferedImage image = ImageIO.read(getClass().getResource("images/home.png"));
			ImageIcon icon = new ImageIcon(image.getScaledInstance(20, 20, Image.SCALE_SMOOTH));
			panel.addComponent(screenName, icon, timelineTab, tip);
		} catch (IOException e2) {
			panel.addComponent(screenName, timelineTab);
		}
	}
}