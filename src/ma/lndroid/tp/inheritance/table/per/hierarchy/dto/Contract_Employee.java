package ma.lndroid.tp.inheritance.table.per.hierarchy.dto;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;

@Entity
@DiscriminatorValue(value="CONT_EMP")
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
