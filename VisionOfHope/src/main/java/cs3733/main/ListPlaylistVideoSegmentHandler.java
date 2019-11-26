package cs3733.main;

import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import cs3733.main.DB.PlaylistsDAO;
import cs3733.main.http.ListPlaylistVideoSegmentsRequest;
import cs3733.main.http.ListPlaylistVideoSegmentsResponse;
import cs3733.main.model.Playlist;
import cs3733.main.model.VideoSegment;


public class ListPlaylistVideoSegmentHandler implements RequestHandler<ListPlaylistVideoSegmentsRequest,ListPlaylistVideoSegmentsResponse>{

	public LambdaLogger logger;

	/** 
	 * find playlist video segments in RDS
	 * @throws Exception 
	 */
	List<VideoSegment> getPlaylistVideoSegments(String playlistName) throws Exception {
		logger.log("in getPlaylistVideoSegments");
		PlaylistsDAO dao = new PlaylistsDAO();
		
		// check if playlist exists
		Playlist playlist = dao.getPlaylist(playlistName);
		if(playlist != null) {
			return playlist.getPlaylistVideos();
		}
		else {
			return null;
		}
	}
	
	@Override
	public ListPlaylistVideoSegmentsResponse handleRequest(ListPlaylistVideoSegmentsRequest input, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to list all playlist video segments");

		ListPlaylistVideoSegmentsResponse response;
		try {
			// get all video segments associated with a playlist in the database
			List<VideoSegment> list = getPlaylistVideoSegments(input.getPlaylistName());
			response = new ListPlaylistVideoSegmentsResponse(list, 200);
		} catch (Exception e) {
			response = new ListPlaylistVideoSegmentsResponse(403, e.getMessage());
		}		
		return response;
	}

}
