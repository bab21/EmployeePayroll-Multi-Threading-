package com.capgemini.employeepayrollservice;
import java.util.*;

import com.capgemini.employeepayrollservice.model.EmployeePayrollData;

import java.time.LocalDate;

public class EmployeePayrollService {
	public enum IOService{CONSOLE_IO,DB_IO,REST_IO,FILE_IO}
	private  List<EmployeePayrollData> employeePayrollList;
	private EmployeePayrollDBService employeePayrollDBService;
	
	public EmployeePayrollService(List<EmployeePayrollData> employeePayrollList) {
		this();
		this.employeePayrollList=employeePayrollList;
	}
	public EmployeePayrollService() {
		employeePayrollDBService=EmployeePayrollDBService.getInstance();
	}
	
	public void readEmployeePayrollData(Scanner consoleInputReader) {
		System.out.println("Enter Employee ID: ");
		int id=consoleInputReader.nextInt();
		System.out.println("Enter Employee Name: ");
		String name=consoleInputReader.next();
		System.out.println("Enter Employee Salary: ");
		double salary=consoleInputReader.nextDouble();
		employeePayrollList.add(new EmployeePayrollData(id,name,salary));
		
	}
	public void writeEmployeePayrollData(IOService ioService) {
		if(ioService.equals(IOService.CONSOLE_IO))
		System.out.println("Employee Payroll Data"+employeePayrollList);
		else if(ioService.equals(IOService.FILE_IO))
			new EmployeePayrollFileIOService().writeData(employeePayrollList);
		
	}
	public List<EmployeePayrollData> readEmployeePayrollData(IOService ioService) throws EmployeePayrollException {
		
		if(ioService.equals(IOService.FILE_IO))
			this.employeePayrollList=new EmployeePayrollFileIOService().readData();
		if(ioService.equals(IOService.DB_IO))
			this.employeePayrollList=employeePayrollDBService.readData();
		return employeePayrollList;
	}
	public void deleteEmployee(String name) throws EmployeePayrollException {
		employeePayrollDBService.deleteEmployeeFromPayroll(name);
	}
	
	public void printData(IOService ioService) {
		if(ioService.equals(IOService.FILE_IO))
		  new EmployeePayrollFileIOService().printData();
	}
	public int countNumberOfEmployees(IOService ioService) {
		if(ioService==IOService.FILE_IO)
		return (int) new EmployeePayrollFileIOService().countEntries();
		else return this.employeePayrollList.size();
	}
	public void updateEmployeeSalary(String name,double salary) throws EmployeePayrollException {
		int result=employeePayrollDBService.updateEmployeeData(name,salary);
		if(result==0)return;
		EmployeePayrollData employeePayrollData=this.getEmployeePayrollData(name);
		if(employeePayrollData!=null) employeePayrollData.salary=salary;
	}
	private EmployeePayrollData getEmployeePayrollData(String name) {
		
		return this.employeePayrollList.stream()
				.filter(employeePayrollDataItem->employeePayrollDataItem.name.equals(name))
				.findFirst()
				.orElse(null);
	}
	public boolean checkEmployeePayrollInSyncWithDB(String name) throws EmployeePayrollException {
		System.out.println("in"+name);
		List<EmployeePayrollData> employeePayrollDataList=employeePayrollDBService.getEmployeePayrollData(name);
		System.out.println("returned "+employeePayrollDataList);
		return employeePayrollDataList.get(0).equals(getEmployeePayrollData(name));
	}
	
	public List<EmployeePayrollData> getEmployeeWithDateRange(LocalDate startDate,LocalDate endDate) throws EmployeePayrollException{
		List<EmployeePayrollData> employeePayrollDataList=employeePayrollDBService.getEmployeePayrollDataFortDateRange(startDate,endDate);
		return employeePayrollDataList;
	}
	
	public Map<String, Double> getEmployeeSalarySumGroupWithGender() throws EmployeePayrollException{
		return employeePayrollDBService.getEmployeeSalarySumGroupWithGender();
	}
	public void addEmployeeToPayroll(String name, double salary, LocalDate startDate, String gender, int company_id,int[] departments) throws EmployeePayrollException {
		employeePayrollList.add(employeePayrollDBService.addEmployeeToPayroll(name,salary,startDate,gender,company_id,departments));
	}
	
	public void addEmployeeToPayroll(String name, double salary, LocalDate startDate,char gender) throws EmployeePayrollException {
		int[] arr= {1};
		employeePayrollList.add(employeePayrollDBService.addEmployeeToPayroll(name,salary,startDate,Character.toString(gender),1,arr));
	}
	public void addEmployeeToPayroll(List<EmployeePayrollData> employeePayrollDataList) throws EmployeePayrollException {
		employeePayrollDataList.forEach(employeePayrollData ->{
			System.out.println("Employee Being Added"+employeePayrollData.name);
			try {
				this.addEmployeeToPayroll(employeePayrollData.name,employeePayrollData.salary,employeePayrollData.start,employeePayrollData.gender);
			} catch (EmployeePayrollException e) {
				e.printStackTrace();
			}
			System.out.println("Employee Added"+employeePayrollData.name);
			
		});
		System.out.print(this.employeePayrollList);
		
	}
}
