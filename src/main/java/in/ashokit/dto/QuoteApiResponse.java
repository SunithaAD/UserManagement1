package in.ashokit.dto;

import java.util.List;

public class QuoteApiResponse {
	
	//private List<QuoteDto> quotes;both are valid u can take list or array
	private QuoteDto[] quotes;

	public QuoteDto[] getQuotes() {
		return quotes;
	}

	public void setQuotes(QuoteDto[] quotes) {
		this.quotes = quotes;
	}
	
	

}
