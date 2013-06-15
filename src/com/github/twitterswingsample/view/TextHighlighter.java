package com.github.twitterswingsample.view;

public class TextHighlighter {

	public static String highlightAll(String text) {
		if(text != null && text.length() > 0){
			String terms[] = text.split(" ");
			terms = highlightTerms(terms, "#", "<font style='font-style:italic; color:#0000ff'><strong>", "</strong></font>", true);
			terms = highlightTerms(terms, "@", "<font style='font-style:italic; color:#0000ff'><strong>", "</strong></font>", true);
			terms = highlightTerms(terms, "http://", "<font style='color:#0000ff'><a href=\"", "\" target=\"_blank\"><strong>link</strong></a></font>", false);
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

	private static String[] highlightTerms(String[] terms, String marker, String prefix, String suffix, boolean omitPunctuation) {
		for (int i = 0; i < terms.length; i++) {
			if(terms[i].startsWith(marker)){
				if(omitPunctuation){
					String term = "";
					String punctuation = "";
					for (int j = 1; j < terms[i].length(); j++) {
						if(Character.isLetterOrDigit(terms[i].charAt(j)) || terms[i].charAt(j) == '_'){
							continue;
						}
						else{
							term = terms[i].substring(0, j);
							punctuation = terms[i].substring(j);
							break;
						}
					}
					terms[i] = prefix + term + suffix + punctuation;
				}
				else{
					terms[i] = prefix + terms[i] + suffix;
				}
			}
		}
		return terms;
	}
}