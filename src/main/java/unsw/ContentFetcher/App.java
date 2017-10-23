package unsw.ContentFetcher;

import unsw.ContentFetcher.Service.ContentFetcherService;

public class App 
{
    public static void main( String[] args )
    {
        ContentFetcherService service = new ContentFetcherService();
        service.getCareerAndQualifications();
    }
}
