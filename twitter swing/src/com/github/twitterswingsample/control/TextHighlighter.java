package com.github.twitterswingsample.control;

public class TextHighlighter {

	public static String highlightAll(String text) {
		return highlightLink(
				highlightHashtag(
						highlightTwitterUserMention(text)));
	}

	public static String highlightHashtag(String text) {
		return highlightPartOfText(text, "#", "<font color='#0000ff'><strong>", "</strong></font>");
	}

	public static String highlightLink(String text) {
		if(text != null && text.length() > 0){
			String terms[] = text.split(" ");
			for (int i = 0; i < terms.length; i++) {
				if(terms[i].startsWith("http://")){
					terms[i] = "<font color='#0000ff'><a href=\"" + terms[i] + "\" target=\"_blank\"><strong>" + terms[i] + "</strong></a></font>";
				}
			}
			
			String result = terms[0];
			for (int i = 1; i < terms.length; i++) {
				result += " " + terms[i];
			}
		
			return result;
		}
		else {
			return "";
		}
	}

	public static String highlightTwitterUserMention(String text) {
		return highlightPartOfText(text, "@", "<font color='#0000ff'><strong>", "</strong></font>");
	}

	private static String highlightPartOfText(String text, String marker, String highlightingPrefix, String highlightingSuffix) {
		if(text != null && text.length() > 0){
			String terms[] = text.split(" ");
			for (int i = 0; i < terms.length; i++) {
				if(terms[i].startsWith(marker)){
					terms[i] = highlightingPrefix + terms[i] + highlightingSuffix;
				}
			}
			
			String result = terms[0];
			for (int i = 1; i < terms.length; i++) {
				result += " " + terms[i];
			}
		
			return result;
		}
		else {
			return "";
		}
	}
}