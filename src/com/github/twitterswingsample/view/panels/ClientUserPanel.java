package com.github.twitterswingsample.view.panels;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;

import twitter4j.Twitter;

import com.github.twitterswingsample.view.listener.HomeTimelinePanelCreator;
import com.github.twitterswingsample.view.listener.ProjectHashtagTimlinePanelCreator;
import com.github.twitterswingsample.view.listener.SearchPanelCreator;
import com.github.twitterswingsample.view.listener.StatusWritePanelCreator;
import com.github.twitterswingsample.view.listener.TabPopupMenuOpener;

/**
 * Panel containing all the content for one user
 * 
 * @author multiprogger
 */
public class ClientUserPanel extends JPanel implements UserInfoPanelContainer {
	
	private JTabbedPane pane;
	
	public ClientUserPanel(Twitter twitter) {
		setLayout(new BorderLayout());
		pane = new JTabbedPane();
		pane.addMouseListener(new TabPopupMenuOpener(pane));
		
		new HomeTimelinePanelCreator(twitter, this).actionPerformed(null);
		add(pane, BorderLayout.CENTER);
		
		JToolBar toolbar = new JToolBar(JToolBar.VERTICAL);
		toolbar.setFloatable(false);
		int imgSize = 30;
		JButton openHomePanel;
		try {
			BufferedImage image = ImageIO.read(getClass().getResource("images/home.png"));
			ImageIcon icon = new ImageIcon(image.getScaledInstance(imgSize, imgSize, Image.SCALE_SMOOTH));
			openHomePanel = new JButton(icon);
			openHomePanel.setToolTipText("See your Home Timeline");
		} catch (IOException e) {
			openHomePanel = new JButton("Home");
		}
		openHomePanel.addActionListener(new HomeTimelinePanelCreator(twitter, this));
		toolbar.add(openHomePanel);
		
		JButton openWritePanel;
		try {
			BufferedImage image = ImageIO.read(getClass().getResource("images/write.png"));
			ImageIcon icon = new ImageIcon(image.getScaledInstance(imgSize, imgSize, Image.SCALE_SMOOTH));
			openWritePanel = new JButton(icon);
			openWritePanel.setToolTipText("Write a tweet");
		} catch (IOException e) {
			openWritePanel = new JButton("write");
		}
		openWritePanel.addActionListener(new StatusWritePanelCreator(twitter, this));
		toolbar.add(openWritePanel);
		
		JButton openSearchPanel;
		try {
			BufferedImage image = ImageIO.read(getClass().getResource("images/search.png"));
			ImageIcon icon = new ImageIcon(image.getScaledInstance(imgSize, imgSize, Image.SCALE_SMOOTH));
			openSearchPanel = new JButton(icon);
			openSearchPanel.setToolTipText("Search for tweets");
		} catch (IOException e) {
			openSearchPanel = new JButton("Search");
		}
		openSearchPanel.addActionListener(new SearchPanelCreator(twitter, this));
		toolbar.add(openSearchPanel);
		
		JButton openProjectHashPanel;
		try {
			BufferedImage image = ImageIO.read(getClass().getResource("images/hash.png"));
			ImageIcon icon = new ImageIcon(image.getScaledInstance(imgSize, imgSize, Image.SCALE_SMOOTH));
			openProjectHashPanel = new JButton(icon);
			openProjectHashPanel.setToolTipText("See tweets about the project");
		} catch (IOException e) {
			openProjectHashPanel = new JButton("project tweets");
		}
		openProjectHashPanel.addActionListener(new ProjectHashtagTimlinePanelCreator(twitter, this));
		toolbar.add(openProjectHashPanel);
		
		add(toolbar, BorderLayout.WEST);
	}
	
	public void addComponent(String title, Icon icon, Component component, String tip){
		pane.addTab(title, icon, component, tip);
	}
	
	public void addComponent(String title, Component component){
		pane.addTab(title, component);
	}

	public void addUserInfoPanel(String title, UserInfoPanel panel) {
		JScrollPane pane = new JScrollPane(panel);
		pane.getVerticalScrollBar().setUI(new MyScrollBarUI());
		pane.getHorizontalScrollBar().setUI(new MyScrollBarUI());
		
		try {
			BufferedImage image = ImageIO.read(getClass().getResource("images/user.png"));
			ImageIcon icon = new ImageIcon(image.getScaledInstance(20, 20, Image.SCALE_SMOOTH));
			addComponent(title, icon, pane, "some information about the user");
		} catch (IOException e) {
			addComponent(title, pane);
		}
	}
}