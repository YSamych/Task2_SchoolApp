package foxminded.ua.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "school_group")
public class Group {

	@Id
	@Column(name = "group_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer groupId;
	
	@Column(name = "group_name")
	private String groupName;
	
	@Transient
	private Integer amountOfStudents;
	
	@OneToMany(mappedBy = "group")
	private List<Student> students;
	
	public Group() {}
	
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Integer getAmountOfStudents() {
		return amountOfStudents;
	}
	public void setAmountOfStudents(int amountOfStudents) {
		this.amountOfStudents = amountOfStudents;
	}
	
	
	
}
