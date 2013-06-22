package com.github.twitterswingsample.view.listener.authorized.statusbased;

import java.awt.event.MouseListener;

import com.github.twitterswingsample.view.listener.authorized.AuthorizedAction;

import twitter4j.Status;
import twitter4j.Twitter;

public abstract class StatusMouseListener extends AuthorizedAction implements MouseListener, Cloneable {

	private Status status;
	
	public StatusMouseListener(Twitter twitter) {
		super(twitter);
	}

	public StatusMouseListener(Twitter twitter, Status status) {
		this(twitter);
		this.status = status;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}