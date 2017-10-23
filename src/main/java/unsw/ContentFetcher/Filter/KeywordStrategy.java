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
		this.retrieveKeyNamePrefixes(text);
		String[] words = text.split(" ");
		ArrayList<String> resultList = new ArrayList<String>();
		int i = 0;
		
		for (i = 0; i < words.length; i++) {
			if (this.containsCapitalLetters(words[i]) && !this.keyNames.contains(words[i])) {
				resultList.add(words[i]);
			}
		}
		
		return resultList.toArray(new String[0]);
	}
	
	protected void retrieveKeyNamePrefixes(String text) {
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
