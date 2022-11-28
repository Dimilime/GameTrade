package be.souk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import be.souk.models.CreditCostHistory;

public class CreditCostHistoryDAO extends DAO<CreditCostHistory> {

	public CreditCostHistoryDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(CreditCostHistory creditCostH) {
		return false;
	}

	@Override
	public boolean delete(CreditCostHistory creditCostH) {
		return false;
	}

	@Override
	public boolean update(CreditCostHistory creditCostH) {
		return false;
	}

	@Override
	public CreditCostHistory find(int id) {
		String req = "Select idCreditCostHistory, creditCost, modificationDate from creditCostHistory where idCreditCostHistory=?;";
		CreditCostHistory creditCostHistory=null;
		
		try(PreparedStatement stmt = connect.prepareStatement(req)) {
			
			stmt.setInt(1, id);
			try(ResultSet res = stmt.executeQuery()) {
				if(res.next()) {
					int idCreditCostHistory = res.getInt(1);
					int creditCost= res.getInt(2);
					LocalDate modificationDate = res.getDate(3).toLocalDate();
					creditCostHistory = new CreditCostHistory(idCreditCostHistory, creditCost, modificationDate);
				}		
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return creditCostHistory;
	}

	@Override
	public ArrayList<CreditCostHistory> findAll() {
		return null;
	}
	
	

}
