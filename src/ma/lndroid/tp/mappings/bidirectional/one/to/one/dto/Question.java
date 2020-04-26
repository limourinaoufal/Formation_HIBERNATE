package ma.lndroid.tp.mappings.bidirectional.one.to.one.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "QUESTIONS")
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PK_SEQ")
	@PrimaryKeyJoinColumn
	private Integer id;

	@Column(name = "Q_NAME")
	private String qname;

	@OneToOne(targetEntity=Answer.class, cascade = CascadeType.ALL, mappedBy="question")
	//@JoinColumn(name="FK_A")
	private Answer answer;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getQname() {
		return qname;
	}

	public void setQname(String qname) {
		this.qname = qname;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", qname=" + qname + ", answer=" + answer
				+ "]";
	}
	
	

}
