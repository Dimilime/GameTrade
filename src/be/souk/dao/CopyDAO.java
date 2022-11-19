package be.souk.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import be.souk.models.Admin;
import be.souk.models.Copy;
import be.souk.models.Player;
import be.souk.models.User;
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Copy obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Copy find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Copy> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ArrayList<Copy> getVideoGameCopies(VideoGame vg){

		ArrayList<Copy> copies = null;
		String req = "SELECT Copy.idCopy, Copy.idVideoGame, Copy.idUser"
				+ "FROM Copy"
				+ "WHERE (([Copy].[idVideoGame]=?));";
		
		try (PreparedStatement stmt = connect.prepareStatement(req))
		{
			stmt.setInt(1, vg.getIdVideoGame());
			try (ResultSet res = stmt.executeQuery())
			{
				while(res.next()) {
					Player player = Player.getPlayer(res.getInt(3));
					Copy copy = new Copy(res.getInt(1), vg, player);
					copies = new ArrayList<>();
					copies.add(copy);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return copies;
	}
	
	public boolean isAvailable(Copy copy) {
		
		String req = "SELECT Copy.idCopy, Loan.onGoing"
				+ "FROM Copy INNER JOIN Loan ON Copy.idCopy = Loan.idCopy"
				+ "WHERE (([Loan].[idCopy]=?));";
		
		
		boolean isAvailable = false, ongoing;
		
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
