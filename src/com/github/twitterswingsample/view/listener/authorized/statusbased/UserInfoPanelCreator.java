package com.github.twitterswingsample.view.listener.authorized.statusbased;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

import com.github.twitterswingsample.view.panels.ClientUserPanel;
import com.github.twitterswingsample.view.panels.ConsolePanel;
import com.github.twitterswingsample.view.panels.UserInfoPanel;

/**
 * MouseListener that creates a frame with {@link UserInfoPanel} in it
 * 
 * @author sourcefranke
 */
public class UserInfoPanelCreator extends StatusMouseListener {

	private ClientUserPanel panel;
	private boolean insideHomeTimeline;
	
	public UserInfoPanelCreator(Status status, Twitter twitter, ClientUserPanel panel, boolean insideHomeTimeline) {
		super(twitter);
		setStatus(status);
		this.panel = panel;
		this.insideHomeTimeline = insideHomeTimeline;
	}

	public void run() {
		try {
			User user = getStatus().getUser();
			UserInfoPanel infoPanel;
			if (insideHomeTimeline) {
				infoPanel = new UserInfoPanel(getTwitter(), user, panel, true);
			}
			else {
				infoPanel = new UserInfoPanel(getTwitter(), user, panel);
			}
			
			JScrollPane pane = new JScrollPane(infoPanel);
			try {
				BufferedImage image = ImageIO.read(getClass().getResource("images/user.png"));
				ImageIcon icon = new ImageIcon(image.getScaledInstance(20, 20, Image.SCALE_SMOOTH));
				panel.addComponent("@" + user.getScreenName(), icon, pane, "some information about the user");
			} catch (IOException e) {
				panel.addComponent("@" + user.getScreenName(), pane);
			}
		} catch (TwitterException e) {
			ConsolePanel.getInstance().printMessage(new String[]{
				"Could not create user information",
				e.getLocalizedMessage()
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