package ma.lndroid.tp.inheritance.table.per.concrete.dto;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.Table;

@Entity
@Table(name="CON_EMP")
@AttributeOverrides({
	@AttributeOverride(name="id", column=@Column(name="PK_SEQ")),
	@AttributeOverride(name="name", column=@Column(name="NAME"))
})
public class Contract_Employee extends Employee {

	@Column(name="PAY_PER_HOUR")
	private Float payPerHour;

	@Column(name="CONTRAT_PERIOD")
	private String contractPeriod;

	public Contract_Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Float getPayPerHour() {
		return payPerHour;
	}

	public void setPayPerHour(Float payPerHour) {
		this.payPerHour = payPerHour;
	}

	public String getContractPeriod() {
		return contractPeriod;
	}

	public void setContractPeriod(String contractPeriod) {
		this.contractPeriod = contractPeriod;
	}

}
