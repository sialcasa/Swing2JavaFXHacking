package com.github.twitterswingsample.view.listener;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;

import com.github.twitterswingsample.model.Credentials;

public abstract class AuthorizedAction {
	
	private Twitter twitter;

	public AuthorizedAction(Credentials creds) {
		super();
		twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(creds.getConsumerkey(), creds.getConsumersecret());
		twitter.setOAuthAccessToken(creds.getAccessToken());
	}
	
	protected Twitter getTwitter() {
		return twitter;
	}
}