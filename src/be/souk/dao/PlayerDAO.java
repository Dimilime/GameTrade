package be.souk.dao;

import java.sql.*;
import java.util.*;
import java.util.Date;

import be.souk.models.Player;

public class PlayerDAO extends DAO<Player>  {

	public PlayerDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Player player) {
		String req1 =  "Insert into user (userName,password) values (?,?); ";
		String reqId = "Select @@IDENTITY ";
		String req2 =  "INSERT INTO Player ( idUser, pseudo, dateOfBirth, registrationDate, credit ) Values (?,?,?,?,?);" ;
		
		try (PreparedStatement stmt = connect.prepareStatement(req1))
		{
			int cpt =1, userId=0;
			stmt.setString(cpt++, player.getUserName());
			stmt.setString(cpt++, player.getPassword());
			stmt.execute();
			
			try (ResultSet res = connect.createStatement().executeQuery(reqId)) {
				if(res.next())
					userId= res.getInt(1);
				System.out.println(userId);
			} 
			
			try(PreparedStatement stmt2 = connect.prepareStatement(req2))
			{
				cpt =1;
				stmt2.setInt(cpt++, userId);
				stmt2.setString(cpt++, player.getPseudo());
				stmt2.setTimestamp(cpt++, new Timestamp(player.getDateOfBirth().getTime()));
				stmt2.setTimestamp(cpt++, new Timestamp (player.getRegistrationDate().getTime()));
				stmt2.setInt(cpt++, player.getCredit());
				stmt2.execute();
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Player player) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Player player) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Player find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Player> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
