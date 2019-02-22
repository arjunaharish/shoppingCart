package generateEmployeeTables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "EMP",
   uniqueConstraints = { @UniqueConstraint(columnNames = { "EMP_ID" }) })
public class EmployeeTables {
	private int id;    
	private String name;    
	private float salary;    
	private String designation;
	
	

  @Id
  @Column(name = "ID")
	public int getId() {
		return id;
	}
  @Column(name = "ID", length = 20, nullable = false)
	public void setId(int id) {
		this.id = id;
	}
  @Column(name = "NAME", length = 50, nullable = false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "SALARY", length = 50, nullable = true)
	public float getSalary() {
		return salary;
	}
	public void setSalary(float salary) {
		this.salary = salary;
	}
	@Column(name = "DESIGNATION", length = 50, nullable = true)
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
}
