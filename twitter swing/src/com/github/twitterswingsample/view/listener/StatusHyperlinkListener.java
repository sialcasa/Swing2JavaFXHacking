package com.github.twitterswingsample.view.listener;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import com.github.twitterswingsample.view.ErrorMessageFrame;

public class StatusHyperlinkListener implements HyperlinkListener {

	public void hyperlinkUpdate(HyperlinkEvent e) {
		if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
			if(Desktop.isDesktopSupported()) {
			    try {
					Desktop.getDesktop().browse(e.getURL().toURI());
				} catch (IOException | URISyntaxException e1) {
					new ErrorMessageFrame("Unable to open Link");
				}
			}
			else {
				new ErrorMessageFrame("Functionality not supported");
			}
		}
	}
}