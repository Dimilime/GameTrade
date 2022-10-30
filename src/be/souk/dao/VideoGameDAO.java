package be.souk.dao;

import java.sql.*;
import java.util.ArrayList;

import be.souk.models.VideoGame;

public class VideoGameDAO extends DAO<VideoGame> {

	public VideoGameDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(VideoGame obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(VideoGame obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(VideoGame obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public VideoGame find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<VideoGame> findAll() {
		String req = "select * from VideoGame";
		ArrayList<VideoGame> videoGames = new ArrayList<>();
		try (Statement stmt = connect.createStatement())
		{
			try (ResultSet res = stmt.executeQuery(req))
			{
				while(res.next()) {
					VideoGame vg = new VideoGame(res.getInt("idVideoGame"), res.getString("name"), res.getString("console"), res.getInt("creditCost"), null);
					videoGames.add(vg);
				}
				return videoGames;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

}
