package com.github.twitterswingsample.model;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

/**
 * Handles consumerkey, consumersecret, accesstoken, and accesstokensecret for access to a twitter account.
 * Reads out 'login.xml' and puts the four strings into a {@link Twitter} object.
 * 
 * @author multiprogger
 */
public class Credentials {
	
	private NodeList list;
	
	public Credentials() throws ParserConfigurationException, SAXException, IOException {
		File f = new File(System.getProperty("user.dir") + "/login.xml");
		FileInputStream fis = new FileInputStream(f);
		BufferedInputStream bis = new BufferedInputStream(fis);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(bis);
		doc.getDocumentElement().normalize();
		list = doc.getElementsByTagName("user");
	}
	
	public int getNumberOfUsers() throws FileNotFoundException, SAXException, ParserConfigurationException, IOException{
		return list.getLength();
	}

	public String getName(int i) {
		Element e = (Element) list.item(i);
		return e.getAttribute("name");
	}
	
	public Twitter getTwitter(int i){
		Element e = (Element) list.item(i);
		Element subElement = (Element)e.getElementsByTagName("consumerkey").item(0);
		String consumerkey = subElement.getChildNodes().item(0).getNodeValue();
		subElement = (Element)e.getElementsByTagName("consumersecret").item(0);
		String consumersecret = subElement.getChildNodes().item(0).getNodeValue();
		subElement = (Element)e.getElementsByTagName("accesstoken").item(0);
		String accesstoken = subElement.getChildNodes().item(0).getNodeValue();
		subElement = (Element)e.getElementsByTagName("accesstokensecret").item(0);
		String accesstokensecret = subElement.getChildNodes().item(0).getNodeValue();
		
		Twitter twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(consumerkey, consumersecret);
		twitter.setOAuthAccessToken(new AccessToken(accesstoken, accesstokensecret));
		return twitter;
	}
}