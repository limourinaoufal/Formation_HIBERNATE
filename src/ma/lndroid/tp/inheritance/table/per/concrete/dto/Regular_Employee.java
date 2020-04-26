package ma.lndroid.tp.inheritance.table.per.concrete.dto;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="REG_EMP")
@AttributeOverrides({
	@AttributeOverride(name="id", column=@Column(name="PK_SEQ")),
	@AttributeOverride(name="name", column=@Column(name="NAME"))
})
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
