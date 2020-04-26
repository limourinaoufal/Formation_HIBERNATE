package ma.lndroid.tp.mappings.many.to.one.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ANSWERS")
public class Answer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PK_SEQ")
	private Integer id;

	@Column(name = "ANSWER_NAME")
	private String answerName;

	@Column(name = "POSTED_BY")
	private String postedBy;

	// private List<Question> questions;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAnswerName() {
		return answerName;
	}

	public void setAnswerName(String answerName) {
		this.answerName = answerName;
	}

	public String getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}

	// public List<Question> getQuestions() {
	// return questions;
	// }
	//
	// public void setQuestions(List<Question> questions) {
	// this.questions = questions;
	// }

	public Answer() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Answer [id=" + id + ", answerName=" + answerName
				+ ", postedBy=" + postedBy + "]";
	}

}
