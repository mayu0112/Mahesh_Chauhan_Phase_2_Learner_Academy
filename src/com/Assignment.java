package com;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Assignment")
public class Assignment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="EntryNo")
	private int EntryNo;
	
	@Column(name="classNo")
	private String classNo;
	
	@Column(name="Subject_1")
	private String subject1;
	
	@Column(name="Teacher_1")
	private String teacher1;
	
	public Assignment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Assignment(String classNo, String subject1, String teacher1) {
		super();
		this.classNo = classNo;
		this.subject1 = subject1;
		this.teacher1 = teacher1;
	}

	public String getClassNo() {
		return classNo;
	}

	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}

	public String getSubject1() {
		return subject1;
	}

	public void setSubject1(String subject1) {
		this.subject1 = subject1;
	}

	public String getTeacher1() {
		return teacher1;
	}

	public void setTeacher1(String teacher1) {
		this.teacher1 = teacher1;
	}


	@Override
	public String toString() {
		return "Assignment [classNo=" + classNo + ", subject1=" + subject1 + ", teacher1=" + teacher1 + "]";
	}
	
}
