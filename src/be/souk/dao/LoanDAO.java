package be.souk.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import be.souk.models.Copy;
import be.souk.models.Loan;
import be.souk.models.Player;

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
		String req = "UPDATE loan"
				+ "	  SET onGoing = ? WHERE idLoan=?";
		
		try(PreparedStatement stmt = connect.prepareStatement(req)) {
			stmt.setBoolean(1, loan.isOngoing());
			stmt.setInt(2, loan.getIdLoan());
			
			return stmt.executeUpdate() > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Loan find(int id) {
		return null;
	}

	@Override
	public ArrayList<Loan> findAll() {
		String req = "select * from Loan ";
		ArrayList<Loan> loans =null;
		try (Statement stmt = connect.createStatement())
		{
			try (ResultSet res = stmt.executeQuery(req))
			{
				loans = new ArrayList<>();
				while(res.next()) {
					int idLoan = res.getInt("idLoan");
					LocalDate startDate = res.getDate("startDate").toLocalDate();
					LocalDate endDate = res.getDate("endDate").toLocalDate();
					boolean onGoing = res.getBoolean("onGoing");
					Player borrower = Player.getPlayer(res.getInt("borrower"));
					Player lender = Player.getPlayer(res.getInt("lender"));
					Copy copy = Copy.getCopy(res.getInt("idCopy"));
					Loan loan = new Loan(idLoan, startDate, endDate, onGoing, borrower, lender, copy);
					loans.add(loan);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loans;
	}

}
