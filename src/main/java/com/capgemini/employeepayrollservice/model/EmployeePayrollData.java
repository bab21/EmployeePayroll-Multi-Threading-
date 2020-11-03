package com.capgemini.employeepayrollservice.model;

import java.time.LocalDate;
import java.util.List;

public class EmployeePayrollData {
	public int id;
	public String name;
	public double salary;
	public LocalDate start;
	public char gender;
	public String company;
	public List<String> departmentList;
	
	public EmployeePayrollData(int id,String name,double salary) {
		this.id=id;
		this.name=name;
		this.salary=salary;
	}
	public EmployeePayrollData(int id,String name,double salary,LocalDate start,char gender) {
		this(id, name, salary);
		this.start=start;
		this.gender=gender;
	}
	public EmployeePayrollData(int id,String name,double salary,LocalDate start,char gender,String company,List<String> departmentList) {
		this(id,name,salary,start,gender);
		this.company=company;
		this.departmentList=departmentList;
	}
	public String toString() {
		return id+","+name+","+salary+","+start+","+gender+","+company+","+departmentList;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this==o) return true;
		if(o==null|| this.getClass()!=o.getClass()) return false;
		EmployeePayrollData that=(EmployeePayrollData)o;
		
		return id==that.id &&
				Double.compare(that.salary, salary)==0 &&
				name.equals(that.name) &&
				that.gender==gender &&
				start.compareTo(that.start)==0;
	}

}