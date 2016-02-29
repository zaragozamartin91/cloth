package hibernateexamples.mkyong.manytomany.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Meeting {
	private Integer id;
	private String subject;
	private Date date;
	private Set<Employee> employees;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, unique = true)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(nullable = false)
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	// el atributo mappedBy es
	// " Required unless the relationship is unidirectional"
	@ManyToMany(mappedBy = "meetings")
	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	public Meeting(String subject, Date date, Set<Employee> employees) {
		super();
		this.subject = subject;
		this.date = date;
		this.employees = employees;
	}

	public Meeting(String subject, Date date) {
		super();
		this.subject = subject;
		this.date = date;
	}

	public Meeting() {
		super();
	}
}
