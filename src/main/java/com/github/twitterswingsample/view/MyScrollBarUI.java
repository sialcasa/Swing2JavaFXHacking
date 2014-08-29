package com.github.twitterswingsample.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class MyScrollBarUI extends BasicScrollBarUI {

    private JButton b = new JButton() {

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(0, 0);
        }

    };

    @Override
    protected void paintTrack(Graphics g, JComponent c,
            Rectangle trackBounds) {
    	g.setColor(Color.LIGHT_GRAY);
        g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width,
                trackBounds.height);
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c,
            Rectangle thumbBounds) {
    	g.setColor(Color.LIGHT_GRAY);
        g.fillRect(thumbBounds.x + 2, thumbBounds.y + 2, thumbBounds.width - 3,
                thumbBounds.height - 3);
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return b;
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return b;
    }
}