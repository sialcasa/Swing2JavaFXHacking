package com.github.twitterswingsample.view.listener;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import com.github.twitterswingsample.view.frames.ErrorMessageFrame;
import com.github.twitterswingsample.view.frames.PopupFrame;

public class StatusHyperlinkListener implements HyperlinkListener {
	
	private PopupFrame pf;
	
	public StatusHyperlinkListener() {
		pf = new PopupFrame();
	}

	public void hyperlinkUpdate(HyperlinkEvent e) {
		if (e.getEventType() == HyperlinkEvent.EventType.ENTERED){
			if(!pf.isTextSet()){
				pf.setText(e.getURL().toExternalForm());
			}
			pf.arrange();
		}
		else if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
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
		else if(e.getEventType() == HyperlinkEvent.EventType.EXITED){
			pf.setVisible(false);
		}
	}
}