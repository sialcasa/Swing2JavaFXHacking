package com.github.twitterswingsample.view.listener.statusbased;

import java.awt.event.MouseListener;

import twitter4j.Status;

public abstract class StatusMouseListener implements MouseListener, Cloneable {

	private Status status;
	
	public StatusMouseListener() {}

	public StatusMouseListener(Status status) {
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