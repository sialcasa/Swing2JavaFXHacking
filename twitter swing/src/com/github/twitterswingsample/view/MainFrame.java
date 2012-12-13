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

import com.github.twitterswingsample.view.listener.ProgramCloser;
import com.github.twitterswingsample.view.listener.UserSelectionFrameCreator;
import com.github.twitterswingsample.view.listener.timelineloader.HomeTimelineLoader;
import com.github.twitterswingsample.view.panels.TimelinePanel;

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
		
		TimelinePanel homeTimeline = new TimelinePanel();
		try {
			homeTimeline.setContent(TwitterFactory.getSingleton().getHomeTimeline());
		} catch (TwitterException e) {}
		add(new JScrollPane(homeTimeline), BorderLayout.CENTER);
		
		JButton reloadHomeTimeline = new JButton("reload Hometimeline");
		reloadHomeTimeline.addActionListener(new HomeTimelineLoader(homeTimeline));
		add(reloadHomeTimeline, BorderLayout.SOUTH);
	}
	
	public static void main(String[] args) throws Exception {
		File file = new File("login.xml");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(file);
		doc.getDocumentElement().normalize();
		NodeList nodeLst = doc.getElementsByTagName("user");
		Element e = (Element) nodeLst.item(0);
		
		Element subElement = (Element)e.getElementsByTagName("consumerkey").item(0);
		String consumerkey = subElement.getChildNodes().item(0).getNodeValue();
		subElement = (Element)e.getElementsByTagName("consumersecret").item(0);
		String consumersecret = subElement.getChildNodes().item(0).getNodeValue();
		subElement = (Element)e.getElementsByTagName("accesstoken").item(0);
		String accesstoken = subElement.getChildNodes().item(0).getNodeValue();
		subElement = (Element)e.getElementsByTagName("accesstokensecret").item(0);
		String accesstokensecret = subElement.getChildNodes().item(0).getNodeValue();
		 		
		Twitter twitter = TwitterFactory.getSingleton();
		twitter.setOAuthConsumer(consumerkey, consumersecret);
		twitter.setOAuthAccessToken(new AccessToken(accesstoken, accesstokensecret));
		
		new MainFrame().setVisible(true);
	}
}