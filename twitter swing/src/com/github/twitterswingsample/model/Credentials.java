package com.github.twitterswingsample.model;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import twitter4j.auth.AccessToken;

public class Credentials {
	
	private String consumerkey, consumersecret;
	private AccessToken accessToken;

	public Credentials(int i) throws Exception {
		readValues((Element) getUserList().item(i));
	}

	public Credentials(String name) throws Exception {
		NodeList list = getUserList();
		
		for (int i = 0; i < list.getLength(); i++) {
			String tmp = list.item(i).getAttributes().item(0).getNodeValue();
			if(tmp.equals(name)){
				readValues((Element) list.item(i));
				break;
			}
		}
	}
	
	public String getConsumerkey() {
		return consumerkey;
	}

	public String getConsumersecret() {
		return consumersecret;
	}

	public AccessToken getAccessToken() {
		return accessToken;
	}

	private NodeList getUserList() throws Exception {
		File file = new File("login.xml");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(file);
		doc.getDocumentElement().normalize();
		return doc.getElementsByTagName("user");
	}
	
	private void readValues(Element e){
		Element subElement = (Element)e.getElementsByTagName("consumerkey").item(0);
		consumerkey = subElement.getChildNodes().item(0).getNodeValue();
		
		subElement = (Element)e.getElementsByTagName("consumersecret").item(0);
		consumersecret = subElement.getChildNodes().item(0).getNodeValue();
		
		subElement = (Element)e.getElementsByTagName("accesstoken").item(0);
		String tmp1 = subElement.getChildNodes().item(0).getNodeValue();
		subElement = (Element)e.getElementsByTagName("accesstokensecret").item(0);
		String tmp2 = subElement.getChildNodes().item(0).getNodeValue();
		accessToken = new AccessToken(tmp1, tmp2);
	}
}