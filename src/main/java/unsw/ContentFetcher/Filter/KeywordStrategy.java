package unsw.ContentFetcher.Filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import static unsw.ContentFetcher.Constant.Constant.*;
/**
 * Rules:
 * 1. text always starts with Mr./Mrs./Ms./Dr. (defined in constant)
 * 
 * @author zxg
 *
 */
public class KeywordStrategy extends FilterStrategy {
	protected HashSet<String> keyNames = new HashSet<String>();
	protected HashSet<String> keysFromConstant = new HashSet<String>();
	protected HashSet<String> connectingWords = new HashSet<String>();
	
	public KeywordStrategy() {
		this.keysFromConstant = new HashSet<String>(Arrays.asList(KEYS));
	}
	
	/**
	 * Connection word
	 */
	protected void createConnectionWordsList() {
		this.connectingWords.add("of");
		//this.connectingWords.add("at");
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
					resultList.add(words[i]);	
				}
				
			}
		}
		
		return resultList.toArray(new String[0]);
	}
	
	/**
	 * Check if a word ends with a specific char
	 * 
	 * @param String word
	 * @param char c
	 * @return boolean
	 */
	protected boolean wordEndWithChar(String word, char c) {
		int length = word.length();
		if (word.charAt(length - 1) == c) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Get starting of biography which is always a prefix and a name
	 * these will be used as filters
	 * 
	 * @param text
	 */
	protected void retrieveKeyNames(String text) {
		String[] words = text.split(" ");
		int i = 0;
		
		for (i = 0; i < words.length; i++) {
			if (this.containsCapitalLetters(words[i])) {
				this.keyNames.add(words[i]);
			} else {
				break;
			}
		}
	
	}
	
	protected boolean containsCapitalLetters(String word) {
		boolean flag = false;
		int i = 0;
		for (i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (Character.isUpperCase(c)) {
				flag = true;
				break;
			}
		}
		
		return flag;
	}

}
