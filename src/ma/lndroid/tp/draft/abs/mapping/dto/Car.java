package ma.lndroid.tp.draft.abs.mapping.dto;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@DiscriminatorValue(value="E_CAR")
public class Car extends Vehicule {

	@Column(name="TYPE_CRABURANT")
	private String typeCraburant;

	/**
	 * @return the typeCraburant
	 */
	public String getTypeCraburant() {
		return typeCraburant;
	}

	/**
	 * @param typeCraburant the typeCraburant to set
	 */
	public void setTypeCraburant(String typeCraburant) {
		this.typeCraburant = typeCraburant;
	}




}
