package com;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Classes")
public class Classes {
	
	
	public Classes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Classes(String classNo, String student1, String student2, String student3, String student4, String student5) {
		super();
		this.classNo = classNo;
		this.student1 = student1;
		this.student2 = student2;
		this.student3 = student3;
		this.student4 = student4;
		this.student5 = student5;
	}

	@Id
	@Column(name="classNo")
	private String classNo;
	
	@Column(name="Student_1")
	private String student1;
	
	@Column(name="Student_2")
	private String student2;
	
	@Column(name="Student_3")
	private String student3;
	
	@Column(name="Student_4")
	private String student4;
	
	@Column(name="Student_5")
	private String student5;

	public String getClassNo() {
		return classNo;
	}

	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}

	public String getStudent1() {
		return student1;
	}

	public void setStudent1(String student1) {
		this.student1 = student1;
	}

	public String getStudent2() {
		return student2;
	}

	public void setStudent2(String student2) {
		this.student2 = student2;
	}

	public String getStudent3() {
		return student3;
	}

	public void setStudent3(String student3) {
		this.student3 = student3;
	}

	public String getStudent4() {
		return student4;
	}

	public void setStudent4(String student4) {
		this.student4 = student4;
	}

	public String getStudent5() {
		return student5;
	}

	public void setStudent5(String student5) {
		this.student5 = student5;
	}

	@Override
	public String toString() {
		return "Classes [classNo=" + classNo + ", student1=" + student1 + ", student2=" + student2 + ", student3="
				+ student3 + ", student4=" + student4 + ", student5=" + student5 + "]";
	}

	
	
}
