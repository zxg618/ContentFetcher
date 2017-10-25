package unsw.ContentFetcher.Filter;

import java.util.Arrays;
import java.util.HashSet;
import static unsw.ContentFetcher.Constant.Constant.*;

public class FilterStrategy {
	protected HashSet<String> keyNames = null;
	protected HashSet<String> keysFromConstant = null;
	protected HashSet<String> connectingWords = null;
	
	public FilterStrategy() {
		this.keysFromConstant = new HashSet<String>(Arrays.asList(KEYS));
		this.connectingWords = new HashSet<String>(Arrays.asList(
			     CONNECTING_KEYS
			));
	}
	
	public String[] fetchContent(String text) {
		return null;
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
		this.keyNames = new HashSet<String>();
		
		for (i = 0; i < words.length; i++) {
			if (this.containsCapitalLetters(words[i])) {
				this.keyNames.add(words[i]);
			} else {
				break;
			}
		}
	
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
