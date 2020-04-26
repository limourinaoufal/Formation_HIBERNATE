package ma.lndroid.tp.inheritance.table.per.subclass.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="CON_EMP")
@PrimaryKeyJoinColumn(name="FK_EMP")
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
