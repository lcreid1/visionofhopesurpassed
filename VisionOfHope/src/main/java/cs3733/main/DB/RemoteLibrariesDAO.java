package cs3733.main.DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cs3733.main.model.RemoteLib;
import cs3733.main.model.VideoSegment;

public class RemoteLibrariesDAO {

	java.sql.Connection conn;

	public RemoteLibrariesDAO() {
		try {
			conn = DatabaseUtil.connect();
		} catch (Exception e) {
			conn = null;
		}
	}

	/**
	 * returns a list of all the remote libraries
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<RemoteLib> getAllRemoteLibraries() throws Exception {
		try {
			List<RemoteLib> remotes = new ArrayList<RemoteLib>();

			PreparedStatement ps = conn.prepareStatement("SELECT distinct remoteURL, name FROM innodb.remoteLibraries");
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				RemoteLib rl = generateRemoteLibrary(resultSet);
				remotes.add(rl);
			}
			resultSet.close();
			ps.close();

			for (RemoteLib remote : remotes) {
				ps = conn.prepareStatement(
						"SELECT * FROM innodb.remoteLibraries where remoteURL = '" + remote.getUrl() + "'");
				resultSet = ps.executeQuery();

				while (resultSet.next()) {
					VideoSegment vs = generateVideoSegment(resultSet);
					remote.addVideo(vs);
				}
				resultSet.close();
				ps.close();
			}

			return remotes;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Failed in getting videos: " + e.getMessage());
		}

	}

	/**
	 * creates Remote Library from one row of the database
	 * 
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 */
	private RemoteLib generateRemoteLibrary(ResultSet resultSet) throws SQLException {
		String name = "";
		String URL = "";

		name = resultSet.getString("name");
		URL = resultSet.getString("remoteURL");

		return new RemoteLib(name, URL);
	}

	/**
	 * returns video segment from one row
	 * 
	 * @param resultSet
	 * @return
	 * @throws Exception
	 */
	private VideoSegment generateVideoSegment(ResultSet resultSet) throws Exception {
		String URL = "";
		String title = "";
		String character = "";

		title = resultSet.getString("videoTitle");
		character = resultSet.getString("videoCharacter");
		URL = resultSet.getString("videoURL");
		return new VideoSegment(title, character, URL);
	}

	public boolean addRemoteLib(String name, String url) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteRemoteLib(String name, String url) {
		// TODO Auto-generated method stub
		return false;
	}
}
