package ma.lndroid.tp.inheritance.table.per.subclass.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="REG_EMP")
@PrimaryKeyJoinColumn(name="FK_EMP")
public class Regular_Employee extends Employee {

	@Column(name="SALARY")
	private Float salary;

	@Column(name="BONUS")
	private Integer bonus;

	public Regular_Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Float getSalary() {
		return salary;
	}

	public void setSalary(Float salary) {
		this.salary = salary;
	}

	public Integer getBonus() {
		return bonus;
	}

	public void setBonus(Integer bonus) {
		this.bonus = bonus;
	}

}
