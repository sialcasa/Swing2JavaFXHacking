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
import javax.swing.JToolBar;

import twitter4j.Twitter;
import twitter4j.TwitterException;

import com.github.twitterswingsample.view.listener.ShortInfoTexter;
import com.github.twitterswingsample.view.listener.StatusWritePanelCreator;
import com.github.twitterswingsample.view.listener.TabPopupMenuOpener;
import com.github.twitterswingsample.view.listener.authorized.timelineloader.HomeTimelineLoader;
import com.github.twitterswingsample.view.listener.statusbased.UserInfoPanelCreator;

/**
 * Panel containing all the content for one user
 * 
 * @author multiprogger
 */
public class ClientUserPanel extends JPanel{
	
	public ClientUserPanel(Twitter twitter) throws CloneNotSupportedException {
		setLayout(new BorderLayout());
		JTabbedPane pane = new JTabbedPane();
		pane.addMouseListener(new TabPopupMenuOpener(pane));
		
		JPanel timelineTab = new JPanel(new BorderLayout(5, 5));
		TimelinePanel homeTimeline = new TimelinePanel();
		homeTimeline.addStatusMouseListenerToStatusProfileImages(new UserInfoPanelCreator(twitter, pane, true));
		homeTimeline.addMouseListenerToStatusProfileImages(new ShortInfoTexter("Show some information about the user"));

		JScrollPane homeTimelineScrollPane = new JScrollPane(homeTimeline);
		homeTimelineScrollPane.getVerticalScrollBar().setUI(new MyScrollBarUI());
		homeTimelineScrollPane.getHorizontalScrollBar().setUI(new MyScrollBarUI());
		timelineTab.add(homeTimelineScrollPane, BorderLayout.CENTER);
		
		JButton reloadHomeTimeline = new JButton("load Home Timeline");
		reloadHomeTimeline.setBackground(new Color(120,172,237));
		reloadHomeTimeline.addActionListener(new HomeTimelineLoader(homeTimeline, twitter));
		timelineTab.add(reloadHomeTimeline, BorderLayout.SOUTH);
		
		String screenName = "";
		try {
			String tip = "";
			try {
				screenName = twitter.getScreenName();
				tip = "Home Timeline of @" + screenName;
			} catch (IllegalStateException e) {
				tip = "Your Home Timeline";
			} catch (TwitterException e) {
				tip = "Your Home Timeline";
			}
			
			BufferedImage image = ImageIO.read(getClass().getResource("images/home.png"));
			ImageIcon icon = new ImageIcon(image.getScaledInstance(20, 20, Image.SCALE_SMOOTH));
			pane.addTab("Home Timeline", icon, timelineTab, tip);
		} catch (IOException e) {
			pane.addTab("Home Timeline", timelineTab);
		}
		add(pane, BorderLayout.CENTER);
		
		JToolBar toolbar = new JToolBar("@" + screenName, JToolBar.VERTICAL);
		toolbar.setFloatable(false);
		int imgSize = 30;
		JButton openWritePanel;
		try {
			BufferedImage image = ImageIO.read(getClass().getResource("images/write.png"));
			ImageIcon icon = new ImageIcon(image.getScaledInstance(imgSize, imgSize, Image.SCALE_SMOOTH));
			openWritePanel = new JButton(icon);
			openWritePanel.setToolTipText("Write a tweet");
		} catch (IOException e) {
			openWritePanel = new JButton("write");
		}
		openWritePanel.addActionListener(new StatusWritePanelCreator(pane, twitter));
		toolbar.add(openWritePanel);
		add(toolbar, BorderLayout.WEST);
	}
}