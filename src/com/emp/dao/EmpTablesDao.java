package com.emp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.emp.beans.Emp;
import com.emp.beans.LoginBean;

import generateEmployeeTables.EmployeeTables;

public class EmpTablesDao {
	JdbcTemplate template;  

	
	public void setTemplate(JdbcTemplate template) {    
	    this.template = template;    
	}    
	public int save(EmployeeTables p){    
	    String sql="insert into Emp(name,salary,designation) values('"+p.getName()+"',"+p.getSalary()+",'"+p.getDesignation()+"')";    
	    return template.update(sql);    
	}    
	public int update(EmployeeTables p){    
	    String sql="update Emp set name='"+p.getName()+"', salary="+p.getSalary()+",designation='"+p.getDesignation()+"' where id="+p.getId()+"";    
	    return template.update(sql);    
	}    
	public int delete(int id){    
	    String sql="delete from Emp where id="+id+"";    
	    return template.update(sql);    
	}    
	public EmployeeTables getEmpById(int id){    
	    String sql="select * from Emp where id=?";    
	    return template.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper<EmployeeTables>(EmployeeTables.class));
	}    
	public List<EmployeeTables> getEmployees() {
		return template.query("select * from Emp",new RowMapper<EmployeeTables>(){    
	        public EmployeeTables mapRow(ResultSet rs, int row) throws SQLException {    
	        	EmployeeTables e=new EmployeeTables();    
	            e.setId(rs.getInt(1));    
	            e.setName(rs.getString(2));    
	            e.setSalary(rs.getFloat(3));    
	            e.setDesignation(rs.getString(4));    
	            return e;    
	}

});
		}

	public Emp getEmpByName(String userName,String password){    
		String sql="select FIRST_NAME,PASSWORD from users where FIRST_NAME=? and password=?";
	    return template.queryForObject(sql, new Object[]{userName,password},new BeanPropertyRowMapper<Emp>(Emp.class));    
	}    
  
}