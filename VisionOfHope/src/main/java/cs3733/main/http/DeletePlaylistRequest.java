package cs3733.main.http;

public class DeletePlaylistRequest {
	String name;
	
	public String getName() {return name; }
	
	public DeletePlaylistRequest () {
		
	}
	
	public DeletePlaylistRequest (String n) {
		this.name = n;
	}
}
