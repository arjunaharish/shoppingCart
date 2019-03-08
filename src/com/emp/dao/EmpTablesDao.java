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
	public String update(EmployeeTables p){    
		p.setName("harry");
		p.setId(1);
		p.setSalary(2.356f);
		p.setName("barry");
		p.setId(1);
		p.setSalary(2.356f);
		String sql="update Emp set name='"+p.getName()+"', salary="+p.getSalary()+",designation='"+p.getDesignation()+"' where id="+p.getId()+"";    
	    return sql;    
	}    
	public int delete(int id){    
	    String sql="delete from Emp where id="+id+"";    
	    return template.update(sql);    
	}    
	public EmployeeTables getEmpById(int id){    
	    String sql="select * from Emp where id=?";    
	    return template.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper<EmployeeTables>(EmployeeTables.class));
	}    
	
	/*remove the comment to run the implementation*/
	/*
	  public List<EmployeeTables> getEmployees() { return
	  template.query("select * from Emp",new RowMapper<EmployeeTables>(){ public
	  EmployeeTables mapRow(ResultSet rs, int row) throws SQLException {
	  EmployeeTables e=new EmployeeTables(); e.setId(rs.getInt(1));
	  e.setName(rs.getString(2)); e.setSalary(rs.getFloat(3));
	  e.setDesignation(rs.getString(4)); return e; }
	  
	  }); }
	 */
	
	/*added some default hard coded entry*/
	 public EmployeeTables getEmployees() {
              
			  EmployeeTables e=new EmployeeTables(0, null, 0, null); 
			  e.setId(1);
			  e.setName("harry"); 
			  e.setSalary(1000);
			  e.setDesignation("usa");
			return e;
			   
			  }
			  
			  


	
	
	
	
	/*remove the comment to run the implementation*/
	/*
	 * public Emp getEmpByName(String userName,String password){ String
	 * sql="select FIRST_NAME,PASSWORD from users where FIRST_NAME=? and password=?"
	 * ; return template.queryForObject(sql, new Object[]{userName,password},new
	 * BeanPropertyRowMapper<Emp>(Emp.class)); }
	 */
	 
	/*added some default hard coded entry*/
	public String getEmpByName(String userName,String password){    
		userName="harry";
		password="geocode";
		String sql="select FIRST_NAME,PASSWORD from users where FIRST_NAME=? and password=?";
	    return sql;
	}    
	
}