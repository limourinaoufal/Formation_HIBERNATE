package ma.lndroid.tp.draft.recursive.inheritance.dto;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="RECURSIVE")
@Embeddable
public class RevertClass {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="NAME")
	private String Name;
	
	@OneToOne
	@JoinColumn(name="FK_REV")
	private RevertClass pereRevClass;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	

	public RevertClass getPereRevClass() {
		return pereRevClass;
	}

	public void setPereRevClass(RevertClass pereRevClass) {
		this.pereRevClass = pereRevClass;
	}

	public RevertClass() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
