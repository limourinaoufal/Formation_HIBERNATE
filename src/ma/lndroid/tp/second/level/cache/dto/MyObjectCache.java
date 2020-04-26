package ma.lndroid.tp.second.level.cache.dto;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name = "My_Object")
@NamedQueries({
	@NamedQuery(name="AnnQueryGetByIdetById" , query="from MyObjectCache m where m.id= :myId" )
})
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)

public class MyObjectCache {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "NAME")
	private String name;

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

	@Override
	public String toString() {
		return "MyObject [id=" + id + ", name=" + name + "]";
	}

	
	
	

}
