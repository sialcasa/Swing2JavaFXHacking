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
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;

import twitter4j.Twitter;

import com.github.twitterswingsample.view.listener.HomeTimelinePanelCreator;
import com.github.twitterswingsample.view.listener.StatusWritePanelCreator;
import com.github.twitterswingsample.view.listener.TabPopupMenuOpener;

/**
 * Panel containing all the content for one user
 * 
 * @author multiprogger
 */
public class ClientUserPanel extends JPanel{
	
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
		add(toolbar, BorderLayout.WEST);
	}
	
	public void addComponent(String title, Icon icon, Component component, String tip){
		pane.addTab(title, icon, component, tip);
	}
	
	public void addComponent(String title, Component component){
		pane.addTab(title, component);
	}
}