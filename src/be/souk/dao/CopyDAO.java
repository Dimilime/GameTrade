package be.souk.dao;

import java.sql.*;
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
		String req =  "Insert into copy (idVideoGame, owner, isAvailable) values (?,?,?); ";
		
		try (PreparedStatement stmt = connect.prepareStatement(req))
		{
			int cpt =1;
			stmt.setInt(cpt++, copy.getVideoGame().getIdVideoGame());
			stmt.setInt(cpt++, copy.getOwner().getIdUser());
			stmt.setBoolean(cpt++, copy.isAvailable());
			stmt.executeUpdate();
			
			return true;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Copy copy) {
		String req = "DELETE FROM copy"
				+ "	  WHERE idCopy=?";
		
		try(PreparedStatement stmt = connect.prepareStatement(req)) {
			
			stmt.setInt(1, copy.getIdCopy());
			return stmt.executeUpdate() > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Copy copy) {
		String req = "UPDATE Copy"
				+ "	  SET isAvailable = ? WHERE idCopy=?";
		
		try(PreparedStatement stmt = connect.prepareStatement(req)) {
			stmt.setBoolean(1, copy.isAvailable());
			stmt.setInt(2, copy.getIdCopy());
			
			return stmt.executeUpdate() > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Copy find(int id) {
		String req = "Select * from copy where idCopy=?;";
		Copy copy=null;
		PlayerDAO playerDAO = new PlayerDAO(connect);
		VideoGameDAO videoGameDAO = new VideoGameDAO(connect);
		
		try(PreparedStatement stmt = connect.prepareStatement(req)) {
			
			stmt.setInt(1, id);
			try(ResultSet res = stmt.executeQuery()) {
				if(res.next()) {
					VideoGame vg= videoGameDAO.find(res.getInt(2)) ;
					Player p = playerDAO.find(res.getInt(3));
					boolean isAvailable = res.getBoolean(4);
					copy = new Copy(id, vg, p, isAvailable);
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
		PlayerDAO playerDAO = new PlayerDAO(connect);
		VideoGameDAO videoGameDAO = new VideoGameDAO(connect);
		try (Statement stmt = connect.createStatement())
		{
			try (ResultSet res = stmt.executeQuery(req))
			{
				copies = new ArrayList<>();
				while(res.next()) {
					VideoGame vg = videoGameDAO.find(res.getInt("idVideoGame"));
					Player owner = playerDAO.find(res.getInt("owner"));
					Copy c = new Copy(res.getInt("idCopy"), vg , owner,res.getBoolean("isAvailable"));
					copies.add(c);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return copies;
	}
	
	
	
//	public boolean isAvailable(Copy copy) {
//		
//		String req = "SELECT Copy.idCopy, Loan.onGoing "
//				+ "FROM Copy INNER JOIN Loan ON Copy.idCopy = Loan.idCopy "
//				+ "WHERE Copy.idCopy=?;";
//		
//		
//		boolean isAvailable = true, ongoing;
//		
//		try(PreparedStatement stmt = connect.prepareStatement(req)) {
//			
//			stmt.setInt(1, copy.getIdCopy());
//			try(ResultSet res = stmt.executeQuery()) {
//				
//				if(res.next())
//				{
//					ongoing = res.getBoolean(2);
//					if(ongoing)
//						isAvailable = false;
//					else
//						isAvailable = true;
//				}
//			}
//		} catch (SQLException e) {
//			System.out.println("Error copy not found!");
//		}
//		
//		return isAvailable;
//	}

}
