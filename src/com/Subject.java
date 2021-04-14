package com;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Subject")
public class Subject {
		
		@Id
		@GeneratedValue(strategy=GenerationType.SEQUENCE)
		@Column(name="SerialNo")
		private int serialNo;
		
		@Column(name="Subjects")
		private String subjects;
		
		public Subject() {
		}

		public Subject(String subjects) {
			this.subjects = subjects;
		}

		@Override
		public String toString() {
			return "Subject [serialNo=" + serialNo + ", subjects=" + subjects + "]";
		}

		public int getSerialNo() {
			return serialNo;
		}

		public void setSerialNo(int serialNo) {
			this.serialNo = serialNo;
		}

		public String getSubjects() {
			return subjects;
		}

		public void setSubjects(String subjects) {
			this.subjects = subjects;
		}
		
		
		

}

