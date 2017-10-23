package unsw.ContentFetcher.Filter;

public class Fetcher {
	protected FilterStrategy strategy = null;

	public FilterStrategy getStrategy() {
		return strategy;
	}

	public void setStrategy(FilterStrategy strategy) {
		this.strategy = strategy;
	}
	
	public String[] fetchCareerAndQualification(String biography) {
		return this.strategy.fetchContent(biography);
	}
}
