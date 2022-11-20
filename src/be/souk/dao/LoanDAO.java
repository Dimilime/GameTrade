package be.souk.dao;

import java.sql.*;
import java.util.ArrayList;

import be.souk.models.Loan;

public class LoanDAO extends DAO<Loan>  {

	public LoanDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Loan loan) {
		String req =  "Insert into loan (startDate, endDate, onGoing, borrower, lender, idCopy ) values (?,?,?,?,?,?); ";
		
		try (PreparedStatement stmt = connect.prepareStatement(req))
		{
			int cpt =1;
			stmt.setDate(cpt++, Date.valueOf(loan.getStartDate()));
			stmt.setDate(cpt++, Date.valueOf(loan.getEndDate()));
			stmt.setBoolean(cpt++, loan.isOngoing());
			stmt.setInt(cpt++, loan.getBorrower().getIdUser());
			stmt.setInt(cpt++, loan.getLender().getIdUser());
			stmt.setInt(cpt++, loan.getCopy().getIdCopy());
			
			return stmt.executeUpdate()>0;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Loan loan) {
		return false;
	}

	@Override
	public boolean update(Loan loan) {
		return false;
	}

	@Override
	public Loan find(int id) {
		return null;
	}

	@Override
	public ArrayList<Loan> findAll() {
		return null;
	}

}
