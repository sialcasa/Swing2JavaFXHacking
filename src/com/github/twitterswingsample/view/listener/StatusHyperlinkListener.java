package com.github.twitterswingsample.view.listener;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import com.github.twitterswingsample.view.frames.ErrorMessageFrame;
import com.github.twitterswingsample.view.panels.ShortInfoPanel;

public class StatusHyperlinkListener implements HyperlinkListener {

	public void hyperlinkUpdate(HyperlinkEvent e) {
		if (e.getEventType() == HyperlinkEvent.EventType.ENTERED){
			ShortInfoPanel.getInstance().setInfo(e.getURL().toExternalForm());
		}
		else if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
			if(Desktop.isDesktopSupported()) {
			    try {
					Desktop.getDesktop().browse(e.getURL().toURI());
				} catch (IOException e1) {
					new ErrorMessageFrame("Unable to open Link").setVisible(true);
				} catch (URISyntaxException e1) {
					new ErrorMessageFrame("Faulty URL").setVisible(true);
				}
			}
			else {
				new ErrorMessageFrame("Functionality not supported").setVisible(true);
			}
		}
		else if(e.getEventType() == HyperlinkEvent.EventType.EXITED){
			ShortInfoPanel.getInstance().clearInfo();
		}
	}
}