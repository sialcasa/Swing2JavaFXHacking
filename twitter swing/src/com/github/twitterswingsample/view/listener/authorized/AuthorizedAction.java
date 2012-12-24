package com.github.twitterswingsample.view.listener.authorized;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;

import com.github.twitterswingsample.model.Credentials;
import com.github.twitterswingsample.view.listener.MultithreadingListener;

public abstract class AuthorizedAction extends MultithreadingListener{
	
	private Twitter twitter;

	public AuthorizedAction(Twitter twitter) {
		this.twitter = twitter;
	}
	
	protected Twitter getTwitter() {
		return twitter;
	}
}