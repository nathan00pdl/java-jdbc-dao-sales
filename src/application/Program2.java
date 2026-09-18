package application;

import java.util.List;
import java.util.Scanner;

import dao.DepartmentDAO;
import dao.daoFactory;
import entities.Department;

public class Program2 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);


		System.out.println("==== TEST 1: findById ====");
		DepartmentDAO departmentDao = daoFactory.createDepartmentDAO();
		Department dep1 = departmentDao.findById(1);
		System.out.println(dep1);  // prints the 'Computers' department
		
		System.out.println();

		System.out.println("==== TEST 2: findAll ====");
		List<Department> list = departmentDao.findAll();
		for (Department d : list) {
			System.out.println(d);
		}

		System.out.println();
		
		System.out.println("==== TEST 3: INSERT ====");
		Department dep2 = new Department(null, "Music");
		departmentDao.insert(dep2);
		System.out.println("New department inserted!");
		
		System.out.println();

		System.out.println("==== TEST 4: UPDATE ====");
		Department dep3 = departmentDao.findById(1);
		dep3.setName("Food");  // renames 'Computers' to 'Food'
		departmentDao.update(dep3);
		System.out.println("Update completed");

		System.out.println();
		
		System.out.println("==== TEST 5: DELETE ====");
		System.out.print("Enter id for delete test: ");
		int id = sc.nextInt();
		departmentDao.deleteById(id);
		System.out.println("Delete completed");

		sc.close();
	}
}