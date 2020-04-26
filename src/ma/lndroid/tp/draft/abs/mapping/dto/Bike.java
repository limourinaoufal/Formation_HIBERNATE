package ma.lndroid.tp.draft.abs.mapping.dto;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@DiscriminatorValue(value="E_BIKE")
public class Bike extends Vehicule{

	@Column(name="TYPE_AIR")
	private String typeAir;

	public String getTypeAir() {
		return typeAir;
	}

	public void setTypeAir(String typeAir) {
		this.typeAir = typeAir;
	}


	
	

}
