package com.github.twitterswingsample.view.listener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.github.twitterswingsample.view.listener.authorized.timelineloader.HomeTimelineLoader;
import com.github.twitterswingsample.view.listener.statusbased.UserInfoPanelCreator;
import com.github.twitterswingsample.view.panels.ClientUserPanel;
import com.github.twitterswingsample.view.panels.ConsolePanel;
import com.github.twitterswingsample.view.panels.MyScrollBarUI;
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
		TimelinePanel homeTimeline = new TimelinePanel();
		try {
			homeTimeline.addStatusMouseListenerToStatusProfileImages(new UserInfoPanelCreator(twitter, panel, true));
		} catch (CloneNotSupportedException e1) {
			ConsolePanel.getInstance().printMessage(new String[]{
				"Could not add StatusMouseListener",
				"Please report that bug!"
			});
		}
		homeTimeline.addMouseListenerToStatusProfileImages(new ShortInfoTexter("Show some information about the user"));

		JScrollPane homeTimelineScrollPane = new JScrollPane(homeTimeline);
		homeTimelineScrollPane.getVerticalScrollBar().setUI(new MyScrollBarUI());
		homeTimelineScrollPane.getHorizontalScrollBar().setUI(new MyScrollBarUI());
		timelineTab.add(homeTimelineScrollPane, BorderLayout.CENTER);
		
		JButton reloadHomeTimeline = new JButton("load Home Timeline");
		reloadHomeTimeline.setBackground(new Color(120,172,237));
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