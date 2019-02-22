package com.emp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

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
/*	public List<EmployeeTables> getUserToValidate() {
		return template.query("select * from users",new RowMapper<EmployeeTables>(){    
	        public EmployeeTables mapRow(ResultSet rs, int row) throws SQLException {    
	        	EmployeeTables e=new EmployeeTables();    
	            e.setId(rs.getInt(1));    
	            e.setName(rs.getString(2));    
	            e.setSalary(rs.getFloat(3));    
	            e.setDesignation(rs.getString(4));    
	            return e;    
	}

});
	}*/

	public int getUserToValidate(String username,String password){    
		
		    String sql="select username,password from users where username="+username+" and "+password +"";
		    return template.update(sql);    
		}
	
	public int getUserToValidatePassword(String password){    
	    
	    String sql="select password from users where password="+password+"";    
	    return template.update(sql);    
	}    
}