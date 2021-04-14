package com;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Teacher")
public class Teacher {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="EmpNo")
	private int EmpNo;
	
	@Column(name="Teachers")
	private String teachers;

	
	public Teacher() {
	}

	public Teacher(String teachers) {
		this.teachers = teachers;
	}

	public int getEmpNo() {
		return EmpNo;
	}

	public void setEmpNo(int empNo) {
		EmpNo = empNo;
	}

	public String getTeachers() {
		return teachers;
	}

	public void setTeachers(String teachers) {
		this.teachers = teachers;
	}

	@Override
	public String toString() {
		return "Teacher [EmpNo=" + EmpNo + ", teachers=" + teachers + "]";
	}
	
	
	

}
