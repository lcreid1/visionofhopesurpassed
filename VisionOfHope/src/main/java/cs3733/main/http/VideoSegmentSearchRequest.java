package cs3733.main.http;

public class VideoSegmentSearchRequest {
	final String character;
	final String string;
	
	public String getCharacter() {return character; }
	public String getString() {return string; }
	public String getSearch() { 
		String response;
		response = character + " , " + string;
		return response;
	}
	
	public VideoSegmentSearchRequest (String ch, String st) {
		this.character = ch;
		this.string = st;
	}

}
