package unsw.ContentFetcher.Entity;

public class Record {
	protected int sourceId = 0;
	protected String fullName = "";
	protected String primaryCompany = "";
	protected String biography = "";
	
	public Record(int sourceId, String fullName, String primaryCompany, String biography) {
		this.sourceId = sourceId;
		this.fullName = fullName;
		this.primaryCompany = primaryCompany;
		this.biography = biography;
	}
		
	public int getSourceId() {
		return sourceId;
	}

	public void setSourceId(int sourceId) {
		this.sourceId = sourceId;
	}

	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPrimaryCompany() {
		return primaryCompany;
	}
	public void setPrimaryCompany(String primaryCompany) {
		this.primaryCompany = primaryCompany;
	}
	public String getBiography() {
		return biography;
	}
	public void setBiography(String biography) {
		this.biography = biography;
	}
	
	public boolean isValid() {
		boolean result = true;
		
		if (this.fullName.length() < 1 || this.primaryCompany.length() < 1 || this.biography.length() < 1) {
			result = false;
		}
		
		return result;
	}
	
}
