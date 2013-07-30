package com.github.twitterswingsample.view;

import java.awt.Font;

import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;

public class TwitterTheme extends DefaultMetalTheme {
	
	private FontUIResource mainFont = new FontUIResource("Arial", Font.BOLD, 13);
	
	public ColorUIResource getAcceleratorForeground() {
		return super.getAcceleratorForeground();
	}
	
	public ColorUIResource getAcceleratorSelectedForeground() {
		return super.getAcceleratorSelectedForeground();
	}
	
	public ColorUIResource getControl() {
		return super.getControl();
	}
	
	public ColorUIResource getControlDarkShadow() {
		return super.getControlDarkShadow();
	}
	
	public ColorUIResource getControlDisabled() {
		return super.getControlDisabled();
	}
	
	public ColorUIResource getControlHighlight() {
		return super.getControlHighlight();
	}
	
	public ColorUIResource getControlInfo() {
		return super.getControlInfo();
	}
	
	public ColorUIResource getControlShadow() {
		return super.getControlShadow();
	}
	
	public ColorUIResource getControlTextColor() {
		return super.getControlTextColor();
	}
	
	public FontUIResource getControlTextFont() {
		return mainFont;
	}
	
	public ColorUIResource getDesktopColor() {
		return super.getDesktopColor();
	}
	
	public ColorUIResource getFocusColor() {
		return super.getFocusColor();
	}
	
	public ColorUIResource getHighlightedTextColor() {
		return super.getHighlightedTextColor();
	}
	
	public ColorUIResource getInactiveControlTextColor() {
		return super.getInactiveControlTextColor();
	}
	
	public ColorUIResource getInactiveSystemTextColor() {
		return super.getInactiveSystemTextColor();
	}
	
	public ColorUIResource getMenuBackground() {
		return new ColorUIResource(200, 200, 255);
	}
	
	public ColorUIResource getMenuDisabledForeground() {
		return new ColorUIResource(super.getMenuDisabledForeground().darker());
	}
	
	public ColorUIResource getMenuForeground() {
		return new ColorUIResource(super.getMenuForeground());
	}
	
	public ColorUIResource getMenuSelectedBackground() {
		return new ColorUIResource(getMenuBackground().darker());
	}
	
	public ColorUIResource getMenuSelectedForeground() {
		return new ColorUIResource(super.getMenuSelectedForeground().darker());
	}
	
	public FontUIResource getMenuTextFont() {
		return mainFont;
	}
	
	public String getName() {
		return super.getName();
	}
	
	protected ColorUIResource getPrimary1() {
		return super.getPrimary1();
	}
	
	protected ColorUIResource getPrimary2() {
		return super.getPrimary2();
	}
	
	protected ColorUIResource getPrimary3() {
		return new ColorUIResource(180, 180, 255);
	}
	
	public ColorUIResource getPrimaryControl() {
		return super.getPrimaryControl();
	}
	
	public ColorUIResource getPrimaryControlDarkShadow() {
		return super.getPrimaryControlDarkShadow();
	}
	
	public ColorUIResource getPrimaryControlHighlight() {
		return super.getPrimaryControlHighlight();
	}
	
	public ColorUIResource getPrimaryControlInfo() {
		return super.getPrimaryControlInfo();
	}
	
	public ColorUIResource getPrimaryControlShadow() {
		return super.getPrimaryControlShadow();
	}
	
	protected ColorUIResource getSecondary1() {
		return super.getSecondary1();
	}
	
	protected ColorUIResource getSecondary2() {
		return super.getSecondary2();
	}
	
	protected ColorUIResource getSecondary3() {
		return new ColorUIResource(210, 210, 255);
	}
	
	public ColorUIResource getSeparatorBackground() {
		return super.getSeparatorBackground();
	}
	
	public ColorUIResource getSeparatorForeground() {
		return super.getSeparatorForeground();
	}
	
	public FontUIResource getSubTextFont() {
		return mainFont;
	}
	
	public ColorUIResource getSystemTextColor() {
		return super.getSystemTextColor();
	}
	
	public FontUIResource getSystemTextFont() {
		return mainFont;
	}
	
	public ColorUIResource getTextHighlightColor() {
		return super.getTextHighlightColor();
	}
	
	public ColorUIResource getUserTextColor() {
		return super.getUserTextColor();
	}
	
	public FontUIResource getUserTextFont() {
		return mainFont;
	}
	
	public ColorUIResource getWindowBackground() {
		return new ColorUIResource(super.getWindowBackground().brighter());
	}
	
	public ColorUIResource getWindowTitleBackground() {
		return new ColorUIResource(super.getWindowTitleBackground().brighter());
	}
	
	public FontUIResource getWindowTitleFont() {
		return mainFont;
	}
	
	public ColorUIResource getWindowTitleForeground() {
		return new ColorUIResource(super.getWindowTitleForeground().brighter());
	}
	
	public ColorUIResource getWindowTitleInactiveBackground() {
		return new ColorUIResource(super.getWindowTitleInactiveBackground().brighter());
	}
	
	public ColorUIResource getWindowTitleInactiveForeground() {
		return new ColorUIResource(super.getWindowTitleInactiveForeground().brighter());
	}
	
	protected ColorUIResource getBlack() {
		return super.getBlack();
	}
	
	protected ColorUIResource getWhite() {
		return super.getWhite();
	}
}