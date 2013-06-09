package com.github.twitterswingsample.view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import twitter4j.Twitter;
import twitter4j.TwitterException;

import com.github.twitterswingsample.view.listener.TabPopupMenuOpener;
import com.github.twitterswingsample.view.listener.authorized.timelineloader.HomeTimelineLoader;

/**
 * Panel containing all the content for one user
 * 
 * @author multiprogger
 */
public class ClientUserPanel extends JPanel{
	
	public ClientUserPanel(Twitter twitter) {
		setLayout(new BorderLayout(5, 5));
		JTabbedPane pane = new JTabbedPane();
		pane.addMouseListener(new TabPopupMenuOpener(pane));
		
		JPanel timelineTab = new JPanel(new BorderLayout(5, 5));
		TimelinePanel homeTimeline = new TimelinePanel(twitter, pane);
		HomeTimelineLoader homeTimelineLoader = new HomeTimelineLoader(homeTimeline, twitter);
		homeTimelineLoader.actionPerformed(null);
		JScrollPane homeTimelineScrollPane = new JScrollPane(homeTimeline);
		homeTimelineScrollPane.getVerticalScrollBar().setUI(new MyScrollBarUI());
		homeTimelineScrollPane.getHorizontalScrollBar().setUI(new MyScrollBarUI());
		timelineTab.add(homeTimelineScrollPane, BorderLayout.CENTER);
		JButton reloadHomeTimeline = new JButton("reload Hometimeline");
		reloadHomeTimeline.setBackground(new Color(120,172,237));
		reloadHomeTimeline.addActionListener(homeTimelineLoader);
		timelineTab.add(reloadHomeTimeline, BorderLayout.SOUTH);
		
		try {
			String tip = "";
			try {
				tip = "Home Timeline of @" + twitter.getScreenName();
			} catch (IllegalStateException | TwitterException e) {
				tip = "Your Home Timeline";
			}
			
			BufferedImage image = ImageIO.read(getClass().getResource("images/home.png"));
			ImageIcon icon = new ImageIcon(image.getScaledInstance(20, 20, Image.SCALE_SMOOTH));
			pane.addTab("Home Timeline", icon, timelineTab, tip);
		} catch (IOException e) {
			pane.addTab("Home Timeline", timelineTab);
		}
		add(pane, BorderLayout.CENTER);
	}
}