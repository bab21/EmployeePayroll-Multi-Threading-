package com.capgemini.employeepayrollservice;

import static org.junit.Assert.*;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

import org.junit.Test;

import com.capgemini.employeepayrollservice.EmployeePayrollService.IOService;
import com.capgemini.employeepayrollservice.model.EmployeePayrollData;

public class EmployeePayrollServiceTest {
	
	//UC1 Multithreading..
	@Test
	public void given6Employee_WhenAddedDatabase_ShouldMatchEntries() throws EmployeePayrollException {
		EmployeePayrollData[] arrayEmps={
			new EmployeePayrollData(91,"Ranii",100000.0,LocalDate.now(),'F'),
			new EmployeePayrollData(92,"Ruchii",200000.0,LocalDate.now(),'F'),
			new EmployeePayrollData(93,"Induu",700000.0,LocalDate.now(),'F'),
			new EmployeePayrollData(94,"Surajj",900000.0,LocalDate.now(),'M'),
			new EmployeePayrollData(95,"Prabhatt",800000.0,LocalDate.now(),'M'),
			new EmployeePayrollData(96,"Yeshh",500000.0,LocalDate.now(),'M'),
			
		};
		EmployeePayrollService employeePayrollService=new EmployeePayrollService();
		employeePayrollService.readEmployeePayrollData(IOService.DB_IO);
		Instant start=Instant.now();
		
		employeePayrollService.addEmployeeToPayroll(Arrays.asList(arrayEmps));
		Instant end=Instant.now();
		System.out.println("Duration without thread   ........    "+Duration.between(start, end));
		assertEquals(15,employeePayrollService.countNumberOfEmployees(IOService.REST_IO));
	}
//	//UC2 Database..
//	@Test
//	public void givenEmployeePayrollDB_WhenRetrieved_ShouldMatchEmployeeCount() throws EmployeePayrollException {
//		EmployeePayrollService employeePayrollService=new EmployeePayrollService();
//		List<EmployeePayrollData> employeePayrollDataList=employeePayrollService.readEmployeePayrollData(IOService.DB_IO);
//		System.out.println("data"+employeePayrollDataList);
//		assertEquals(3,employeePayrollDataList.size());
//	}
//	//UC3 Database...
//	@Test
//	public void givenNewSalaryForEmployee_WhenUpdated_ShouldSyncWithDB() throws EmployeePayrollException {
//		EmployeePayrollService employeePayrollService=new EmployeePayrollService();
//		List<EmployeePayrollData> employeePayrollDataList=employeePayrollService.readEmployeePayrollData(IOService.DB_IO);
//		employeePayrollService.updateEmployeeSalary("Terisa",300000.00);
//		boolean result=employeePayrollService.checkEmployeePayrollInSyncWithDB("Terisa");
//		assertTrue(result);
//		
//	}
//	
//	//UC5....
//	@Test
//	public void givenDateRange_WhenEmployeeDataRetrieved_ShouldMatch() throws EmployeePayrollException {
//		EmployeePayrollService employeePayrollService=new EmployeePayrollService();
//		LocalDate startDate=LocalDate.of(2018, 01, 01);
//		LocalDate endDate=LocalDate.now();
//		List<EmployeePayrollData> employeePayrollDataList=employeePayrollService.getEmployeeWithDateRange(startDate,endDate);
//		assertEquals(3,employeePayrollDataList.size());
//	}
//	//UC6...
//	@Test
//	public void givenEmployeeData_WhenSumSalaryWithGender_ShouldMatch() throws EmployeePayrollException {
//		EmployeePayrollService employeePayrollService=new EmployeePayrollService();
//		employeePayrollService.readEmployeePayrollData(IOService.DB_IO);
//		Map<String,Double> salarySumByGender=employeePayrollService.getEmployeeSalarySumGroupWithGender();
//		assertTrue(salarySumByGender.get("F").equals(600000.0) && salarySumByGender.get("M").equals(100000.0));
//	}
	
	//UC7..
//	@Test
//	public void givenNewEmployee_WhenAdded_ShouldSyncWithDB() throws EmployeePayrollException{
//		EmployeePayrollService employeePayrollService=new EmployeePayrollService();
//		employeePayrollService.readEmployeePayrollData(IOService.DB_IO);
//		int[] departments= {1,2,3};
//		employeePayrollService.addEmployeeToPayroll("Alisha",500000.00,LocalDate.now(),"M",2,departments);
//		boolean result=employeePayrollService.checkEmployeePayrollInSyncWithDB("Alisha");
//		assertTrue(result);
//	}
	//UC12...
//	@Test
//	public void givenEmployee_WhenDeleted_ShoudldSyncWithDB() throws EmployeePayrollException {
//		EmployeePayrollService employeePayrollService=new EmployeePayrollService();
//		List<EmployeePayrollData> employeePayrollDataList=employeePayrollService.readEmployeePayrollData(IOService.DB_IO);
//		System.out.print(employeePayrollDataList);
//		
//		int sizeBeforeDeletion=employeePayrollDataList.size();
//		employeePayrollService.deleteEmployee("Bill");
//		
//		employeePayrollDataList=employeePayrollService.readEmployeePayrollData(IOService.DB_IO);
//		System.out.print(employeePayrollDataList);
//		
//		int sizeAfterDeletion=employeePayrollDataList.size();
//		assertTrue(sizeBeforeDeletion==sizeAfterDeletion+1);
//		
//	}

}