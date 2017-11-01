package unsw.ContentFetcher.Filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Rules:
 * 1. text always starts with Mr./Mrs./Ms./Dr. (defined in constant)
 * 2. check upper case words
 *   2.1 starting word of a sentence should be ignored
 *   2.2 connecting word should join previous ones and following ones
 * 
 * @author zxg
 *
 */
public class KeywordStrategy extends FilterStrategy {
	public KeywordStrategy() {
		super();
	}
	
	public String[] fetchContent(String text) {
		this.retrieveKeyNames(text);
		String[] words = text.split(" ");
		ArrayList<String> resultList = new ArrayList<String>();
		int i = 0;
		
		for (i = 0; i < words.length; i++) {
			if (this.containsCapitalLetters(words[i]) && !this.keyNames.contains(words[i])) {
				if (
						i > 1 
						&& this.wordEndWithChar(words[i - 1], '.')
						) {
					//identify if a word is a starting word by check previous word's last char
					//starting word of a sentence is always
				} else {
					/*
					int j = 0;
					StringBuilder tmpWord = new StringBuilder();
					for (j = i; j < words.length; j++) {
						if (
								this.containsCapitalLetters(words[j])
								&& !this.keyNames.contains(words[j])
								&& !this.wordEndWithChar(words[j - 1], ',')
								&& !this.wordEndWithChar(words[j - 1], '.')
								) {
							if (tmpWord.length() > 0) {
								tmpWord.append(" ");
							}
							tmpWord.append(words[j]);
						} else {
							break;
						}
					}
					i = j;
					resultList.add(tmpWord.toString());
					System.out.println("Word: " + tmpWord.toString() + " added and i is: " + i);
					*/
					resultList.add(words[i].replaceAll("[,.]", ""));
				}
				
			} else {
				
				//System.out.println("word is " + words[i]);
				//check connecting word
				if (this.connectingWords.contains(words[i].toLowerCase())) {
					int start = i;
					int end = i;
					int j = 0;
					for (start = i - 1; start > 0; start--) {
						if (
								!this.containsCapitalLetters(words[start])
								|| this.wordEndWithChar(words[start], ',')
								|| this.wordEndWithChar(words[start], '.')
								|| this.wordEndWithChar(words[start], ')')
								) {
							break;
						}
					}
					for (end = i + 1; end < words.length; end++) {
						if (
								(!this.containsCapitalLetters(words[end]) && !words[end].equalsIgnoreCase("of"))
								|| this.wordEndWithChar(words[end - 1], ',')
								|| this.wordEndWithChar(words[end - 1], '.')
								|| this.wordEndWithChar(words[end - 1], ')')
								) {
							break;
						}
					}
					//System.out.println("start is: " + start + " end is: " + end + " i is: " + i);
					if (start == i - 1 || end == i + 1) {
						//
					} else {
						start++;
						String[] wordParts = Arrays.copyOfRange(words, start, end);
						//remove already added word parts
						int size = resultList.size();
						int counter = 0;
						for (j = size - 1; j >= 0; j--) {
							resultList.remove(j);
							counter++;
							if (counter == i - start) {
								break;
							}
						}
						String wholeWord = String.join(" ", wordParts);
						//System.out.println(wholeWord + "#######");
						resultList.add(wholeWord.replaceAll("[,.]", ""));
						i = end;
					}
				}
			}
			
			//added sentence separator
			//System.out.println(words[i] + "#######");
			if (i > 0 && this.wordEndWithChar(words[i - 1], '.') && resultList.size() > 0 && !resultList.get(resultList.size() - 1).equals("\n")) {
				//String word = resultList.get(resultList.size() - 1);
				//resultList.remove(resultList.size() - 1);
				resultList.add("\n");
			}
		}
		
		return resultList.toArray(new String[0]);
	}
	
	protected HashSet<String> removeStringsFromSet(String[] needle, HashSet<String> haystack) {
		int i = 0;
		for (i = 0; i < needle.length; i++) {
			haystack.remove(needle[i]);
		}
		
		return haystack;
	}

}
