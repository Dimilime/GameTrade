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
		return false;
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
