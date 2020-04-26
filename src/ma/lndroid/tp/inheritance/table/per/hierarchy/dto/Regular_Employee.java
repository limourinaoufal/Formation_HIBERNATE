package ma.lndroid.tp.inheritance.table.per.hierarchy.dto;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="REG_EMP")
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
