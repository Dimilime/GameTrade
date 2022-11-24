package be.souk.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import be.souk.models.Copy;
import be.souk.models.Player;
import be.souk.models.VideoGame;

public class CopyDAO extends DAO<Copy> {

	public CopyDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Copy copy) {
		String req =  "Insert into copy (idVideoGame, idUser) values (?,?); ";
		
		try (PreparedStatement stmt = connect.prepareStatement(req))
		{
			int cpt =1;
			stmt.setInt(cpt++, copy.getVideoGame().getIdVideoGame());
			stmt.setInt(cpt++, copy.getOwner().getIdUser());
			stmt.executeUpdate();
			
			return true;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Copy obj) {
		return false;
	}

	@Override
	public boolean update(Copy obj) {
		return false;
	}

	@Override
	public Copy find(int id) {
		String req = "Select * from copy where idCopy=?;";
		Copy copy=null;
		
		try(PreparedStatement stmt = connect.prepareStatement(req)) {
			
			stmt.setInt(1, id);
			try(ResultSet res = stmt.executeQuery()) {
				if(res.next()) {
					int idCopy = res.getInt(1);
					VideoGame vg= VideoGame.getVideoGame(res.getInt(2)) ;
					Player p = Player.getPlayer(res.getInt(3));
					
					copy = new Copy(idCopy, vg, p);
				}		
			}
			
		} catch (SQLException e) {
			System.out.println("Error copy not found!");
		}
		
		return copy;
	}

	@Override
	public ArrayList<Copy> findAll() {
		
		String req = "select * from Copy";
		ArrayList<Copy> copies =null;
		try (Statement stmt = connect.createStatement())
		{
			try (ResultSet res = stmt.executeQuery(req))
			{
				copies = new ArrayList<>();
				while(res.next()) {
					VideoGame vg = VideoGame.getVideoGame(res.getInt("idVideoGame"));
					Player owner = Player.getPlayer(res.getInt("owner"));
					Copy c = new Copy(res.getInt("idCopy"), vg , owner);
					copies.add(c);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return copies;
	}
	
	
	
	public boolean isAvailable(Copy copy) {
		
		String req = "SELECT Copy.idCopy, Loan.onGoing "
				+ "FROM Copy INNER JOIN Loan ON Copy.idCopy = Loan.idCopy "
				+ "WHERE Copy.idCopy=?;";
		
		
		boolean isAvailable = true, ongoing;
		
		try(PreparedStatement stmt = connect.prepareStatement(req)) {
			
			stmt.setInt(1, copy.getIdCopy());
			try(ResultSet res = stmt.executeQuery()) {
				
				if(res.next())
				{
					ongoing = res.getBoolean(2);
					if(ongoing)
						isAvailable = false;
					else
						isAvailable = true;
				}
			}
		} catch (SQLException e) {
			System.out.println("Error copy not found!");
		}
		
		return isAvailable;
	}

}
