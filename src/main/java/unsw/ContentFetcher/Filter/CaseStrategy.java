package unsw.ContentFetcher.Filter;

import java.util.ArrayList;

/**
 * Rules:
 * 1. start position of a sentence does NOT count
 * 2. consecutive capital letter words should be count as a whole word
 * 3. University of Sydney
 * 
 * @author zxg
 *
 */
public class CaseStrategy extends FilterStrategy {
	public String[] fetchContent(String text) {
		ArrayList<String> keywords = new ArrayList<String>();
		return null;
	}

}
