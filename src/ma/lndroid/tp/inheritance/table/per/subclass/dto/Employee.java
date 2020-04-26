package ma.lndroid.tp.inheritance.table.per.subclass.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="EMPLOYEE")
@Inheritance(strategy=InheritanceType.JOINED)
public class Employee {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="PK_SEQ")
	private Integer id;

	@Column(name="NAME")
	private String name;

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
