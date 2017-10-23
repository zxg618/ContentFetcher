package unsw.ContentFetcher.Service;

import unsw.ContentFetcher.Utility.ExcelFileReader;
import static unsw.ContentFetcher.Constant.Constant.*;

import unsw.ContentFetcher.Entity.Record;
import unsw.ContentFetcher.Filter.Fetcher;
import unsw.ContentFetcher.Filter.KeywordStrategy;

public class ContentFetcherService {
	public void getCareerAndQualifications() {
		ExcelFileReader efr = new ExcelFileReader(INPUT_PATH + INPUT_FILE);
		Record[] records = efr.readFileContent();
		Fetcher fetcher = new Fetcher();
		String[] keywords = null;
		int i = 0;
		
		//test area
		String sampleBio = "Ms. Patricia Melcher is a Co-Founder and serves as Managing Partner at EIV Capital. In this role, she leads the strategic direction of the firm and directs its investment programs. Prior to co-founding EIV, Patti founded Allegro Capital Management, Inc. in 1997, an energy private equity firm which not only made equity investments in energy companies but also provided consulting services. During her tenure there, she served as Interim Chief Executive Officer for Petrocom Energy, Ltd. Prior to that, Patti worked with Sandefer Capital Partners helping develop the investment strategy for a $500 million fund. Prior to Sandefer, Patti joined newly formed SCF partners, a private equity fund focused on the oilfield services industry. She has more than 29 years of experience and began her career as an investment banker for Simmons & Company International. Patti is active in the non-profit community. In 1997, she co-founded and then served as President and Board Chair (currently Trustee Emeritus) of The Joy School, a non-profit K-8 school focused on meeting the educational needs of children with learning differences. She currently serves on the boards of Workshop Houston, Da Camera of Houston and is a Trustee of the University of Virginia School of Engineering. She received her BS degree in Systems Engineering from University of Virginia and an MBA from Harvard University.";
		KeywordStrategy kws = new KeywordStrategy();
		fetcher.setStrategy(kws);
		keywords = fetcher.fetchCareerAndQualification(sampleBio);
		System.out.println(String.join(",", keywords));
		//test area ends
		
		for (i = 0; i < records.length; i++) {
			//keywords = fetcher.fetchCareerAndQualification(records[i].getBiography());
		}
	}
}
