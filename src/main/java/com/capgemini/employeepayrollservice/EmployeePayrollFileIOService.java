package com.capgemini.employeepayrollservice;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.capgemini.employeepayrollservice.model.EmployeePayrollData;

import java.nio.file.Files;
import java.nio.file.Path;

public class EmployeePayrollFileIOService {
	public static String PAYROLL_FILE_NAME="payroll-file.txt";
	
	public void writeData(List<EmployeePayrollData> employeePayrollList) {
		StringBuffer empBuffer=new StringBuffer();
		employeePayrollList.forEach(employee->{
			String employeeDataString=employee.toString().concat("\n");
			empBuffer.append(employeeDataString);
		});
		try {
			Files.write(Paths.get(PAYROLL_FILE_NAME), empBuffer.toString().getBytes());
		}
		catch(IOException e) {
			
		}
	}
	
	public void printData() {
		try {
			Files.lines(new File("payroll-file.txt").toPath()).forEach(System.out::println);
		}
		catch(IOException e) {
			System.out.println("Error in reading file");
		}
	}
	public List<EmployeePayrollData> readData(){
		List<EmployeePayrollData> employeePayrollDataList=new ArrayList<EmployeePayrollData>();
		try {
			employeePayrollDataList=Files.lines(new File("payroll-file.txt").toPath()).map(line->convertToObject(line)).collect(Collectors.toList());
		}
		catch(IOException e) {
			System.out.println("File not found");
		}
		return employeePayrollDataList;
	}
	public EmployeePayrollData convertToObject(String line) {
		String[] words=line.split(",");
		EmployeePayrollData employeePayrollData=new EmployeePayrollData(Integer.parseInt(words[0]),words[1],Double.parseDouble(words[2]));
		return employeePayrollData;
	}
	
	public long countEntries() {
		long entries=0;
		try {
			entries=Files.lines(new File("payroll-file.txt").toPath()).count();
		}
		catch(IOException e) {
			System.out.println("Error here");
		}
		return entries;
	}

}
