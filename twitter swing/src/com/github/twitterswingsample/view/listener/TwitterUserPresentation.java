package com.github.twitterswingsample.view.listener;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

import com.github.twitterswingsample.view.panels.ConsolePanel;
import com.github.twitterswingsample.view.panels.MyScrollBarUI;
import com.github.twitterswingsample.view.panels.TwitterUserPanel;

/**
 * MouseListener that creates a frame with {@link TwitterUserPanel} in it
 * 
 * @author multiprogger
 */
public class TwitterUserPresentation implements MouseListener, Runnable {

	private Twitter twitter;
	private User user;
	private JTabbedPane pane;
	
	public TwitterUserPresentation(Twitter twitter, User user, JTabbedPane pane) {
		super();
		this.twitter = twitter;
		this.user = user;
		this.pane = pane;
	}

	public void run() {
		try {
			try {
				BufferedImage image = ImageIO.read(getClass().getResource("images/user.png"));
				ImageIcon icon = new ImageIcon(image.getScaledInstance(20, 20, Image.SCALE_SMOOTH));
				JScrollPane scrollPane = new JScrollPane(new TwitterUserPanel(twitter, user));
				scrollPane.getVerticalScrollBar().setUI(new MyScrollBarUI());
				scrollPane.getHorizontalScrollBar().setUI(new MyScrollBarUI());
				pane.addTab("@" + user.getScreenName(), icon, scrollPane, "Some info about @" + user.getName());
			} catch (IOException e) {
				ConsolePanel.getSingleton().printMessage(new String[]{
					"Could not set an icon for the tab",
					e.getMessage()
				});
			}
			
		} catch (TwitterException e) {
			ConsolePanel.getSingleton().printMessage(new String[]{
				"Could not create user information",
				e.getMessage()
			});
		}
	}

	public void mouseClicked(MouseEvent e) {
		new Thread(this).start();
	}

	public void mouseEntered(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
}