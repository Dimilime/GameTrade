package be.souk.dao;

import java.sql.Connection;

import be.souk.connection.GameTradeConnection;
import be.souk.models.*;

public class DAOFactory extends AbstractDAOFactory {
	
	protected static final Connection conn = GameTradeConnection.getInstance();

	@Override
	public DAO<Admin> getAdminDAO() {
		return new AdminDAO(conn);
	}

	@Override
	public DAO<Booking> getBookingDAO() {
		return new BookingDAO(conn);
	}

	@Override
	public DAO<Player> getPlayerDAO() {
		return new PlayerDAO(conn);
	}

	@Override
	public DAO<Copy> getCopyDAO() {
		return new CopyDAO(conn);
	}

	@Override
	public DAO<Loan> getLoanDAO() {
		return new LoanDAO(conn);
	}

	@Override
	public DAO<VideoGame> getVideoGameDAO() {
		return new VideoGameDAO(conn);
	}

	@Override
	public DAO<User> getUserDAO() {
		return new UserDAO(conn);
	}

	@Override
	public DAO<CreditCostHistory> getCreditCostHistoryDAO() {
		return new CreditCostHistoryDAO(conn);
	}
	
	

}
