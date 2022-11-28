package be.souk.dao;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

import be.souk.models.Player;

public class PlayerDAO extends DAO<Player>  {

	public PlayerDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Player player) {
		String req1 =  "Insert into user (userName,password) values (?,?); ";
		String reqId = "Select @@IDENTITY ";
		String req2 =  "INSERT INTO Player ( idUser, pseudo, dateOfBirth, registrationDate, credit, lastSeen ) Values (?,?,?,?,?,?);" ;
		
		try (PreparedStatement stmt = connect.prepareStatement(req1))
		{
			int cpt =1, userId=0;
			stmt.setString(cpt++, player.getUserName());
			stmt.setString(cpt++, player.getPassword());
			stmt.executeUpdate();
			
			try (ResultSet res = connect.createStatement().executeQuery(reqId)) {
				if(res.next())
					userId= res.getInt(1);
			} 
			
			try(PreparedStatement stmt2 = connect.prepareStatement(req2))
			{
				cpt =1;
				stmt2.setInt(cpt++, userId);
				stmt2.setString(cpt++, player.getPseudo());
				stmt2.setDate(cpt++, Date.valueOf(player.getDateOfBirth()));
				stmt2.setDate(cpt++, Date.valueOf(player.getRegistrationDate()));
				stmt2.setInt(cpt++, player.getCredit());
				stmt2.setDate(cpt++, Date.valueOf(player.getLastSeen()) );
				
				return stmt2.executeUpdate()>0;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Player player) {
		return false;
	}

	@Override
	public boolean update(Player player) {
		
		String req = "UPDATE player"
				+ "	  SET credit = ?, lastSeen = ? WHERE idUser=?";
		
		try(PreparedStatement stmt = connect.prepareStatement(req)) {
			stmt.setInt(1, player.getCredit());
			stmt.setDate(2, Date.valueOf(player.getLastSeen()));
			stmt.setInt(3, player.getIdUser());
			
			return stmt.executeUpdate() > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Player find(int id) {
		
		String req = "Select idUser, username, pseudo,dateOfBirth, registrationDate, credit, lastSeen"
				+ " from player p inner join user u on p.idUser=u.idUser where idUser=?;";
		Player player=null;
		
		try(PreparedStatement stmt = connect.prepareStatement(req)) {
			
			stmt.setInt(1, id);
			try(ResultSet res = stmt.executeQuery()) {
				if(res.next()) {
					int idUser = res.getInt(1);
					String name= res.getString(2);
					String pseudo = res.getString(3);
					LocalDate dob = res.getDate(4).toLocalDate();
					LocalDate registrationDate = res.getDate(5).toLocalDate();
					int credit = res.getInt(6);
					LocalDate lastSeen = res.getDate(7).toLocalDate();
					player = new Player(idUser,name,null,pseudo,dob,registrationDate,credit);
					player.setLastSeen(lastSeen);
				}		
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return player;
	}

	@Override
	public ArrayList<Player> findAll() {
		return null;
	}
	
	
	

}
