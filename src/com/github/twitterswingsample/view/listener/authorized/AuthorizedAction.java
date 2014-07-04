package com.github.twitterswingsample.view.listener.authorized;

import twitter4j.Twitter;
import com.github.twitterswingsample.view.listener.MultithreadingListener;

/**
 * Abstract ActionListener for server requests that need to be authorized
 * 
 * @author sourcefranke
 */
public abstract class AuthorizedAction extends MultithreadingListener{
	
	private Twitter twitter;

	public AuthorizedAction(Twitter twitter) {
		this.twitter = twitter;
	}
	
	protected Twitter getTwitter() {
		return twitter;
	}
	
	public void setTwitter(Twitter twitter) {
		this.twitter = twitter;
	}
}