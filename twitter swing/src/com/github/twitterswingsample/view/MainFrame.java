package com.github.twitterswingsample.view;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

import com.github.twitterswingsample.model.Credentials;
import com.github.twitterswingsample.view.listener.ProgramCloser;
import com.github.twitterswingsample.view.listener.UserSelectionFrameCreator;
import com.github.twitterswingsample.view.listener.timelineloader.HomeTimelineLoader;
import com.github.twitterswingsample.view.panels.TimelinePanel;
import com.github.twitterswingsample.view.panels.UserPanel;

public class MainFrame extends JFrame{
	
	public MainFrame() {
		setBounds(50, 50, 600, 480);
		setTitle("Twitter4J Swing Sample");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout(5, 5));
		
		try {
			setIconImage(ImageIO.read(getClass().getResource("images/icon.png")));
		} catch (IOException e) {}
		
		JMenuBar jmb = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenuItem open = new JMenuItem("Open User Selection"),
				exit = new JMenuItem("Close");
		
		open.addActionListener(new UserSelectionFrameCreator());
		exit.addActionListener(new ProgramCloser());
		
		file.add(open);
		file.addSeparator();
		file.add(exit);
		jmb.add(file);
		setJMenuBar(jmb);
		
		try {
			add(new UserPanel(new Credentials(0)), BorderLayout.CENTER);
		} catch (Exception e) {
			// TODO error message
		}
	}
	
	public static void main(String[] args) throws Exception {
		new MainFrame().setVisible(true);
	}
}