package com.emp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class EmployeeTablesRowMapper implements RowMapper{
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		EmployeeTablesRowMapper customer = new EmployeeTablesRowMapper();
		customer.setEmpId(rs.getInt("ID"));
		customer.setName(rs.getString("NAME"));
		customer.setDesignation(rs.getInt("Designation"));
		return customer;
	}

	private void setDesignation(int int1) {
		// TODO Auto-generated method stub
		
	}

	private void setName(String string) {
		// TODO Auto-generated method stub
		
	}

	private void setEmpId(int int1) {
		// TODO Auto-generated method stub
		
	}
}
