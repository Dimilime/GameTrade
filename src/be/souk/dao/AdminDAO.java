package be.souk.dao;

import java.sql.*;
import java.util.ArrayList;

import be.souk.models.Admin;

public class AdminDAO extends DAO<Admin> {

	public AdminDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Admin obj) {
		return false;
	}

	@Override
	public boolean delete(Admin obj) {
		return false;
	}

	@Override
	public boolean update(Admin obj) {
		return false;
	}

	@Override
	public Admin find(int id) {
		return null;
	}

	@Override
	public ArrayList<Admin> findAll() {
		return null;
	}
	
	

}
