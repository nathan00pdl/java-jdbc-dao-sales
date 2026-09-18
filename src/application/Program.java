package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import dao.SellerDAO;
import dao.daoFactory;
import entities.Department;
import entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);

		System.out.println("====TEST 1: Department====");
		Department dep1 = new Department(1, "department 1");
		System.out.println(dep1);
		
		System.out.println();
		
		System.out.println("====TEST 2: Seller====");
		Seller seller1 = new Seller(20, "joao", "joao@gmail.com", new Date(), 300.00, dep1);
		System.out.println(seller1);
		
		System.out.println();
		
		System.out.println("====TEST 3: Seller findById====");
		SellerDAO sellerDao = daoFactory.createSellerDAO();
		Seller seller2 = sellerDao.findById(3);
		System.out.println(seller2);  // prints the 'Alex Grey' seller
		
		System.out.println();
		
		System.out.println("====TEST 4: Seller findByDepartment====");
		Department dep2 = new Department(1, null);  
		SellerDAO sellerDao2 = daoFactory.createSellerDAO();
		List<Seller> list = sellerDao2.findByDepartement(dep2);
		for(Seller obj : list) {
			System.out.println(obj);  // prints the sellers of department 1
		}
		
		System.out.println();
		
		System.out.println("====TEST 5: Seller findAll====");
		SellerDAO sellerDao3 = daoFactory.createSellerDAO();
		list = sellerDao3.findAll();
		for(Seller obj : list) {
			System.out.println(obj);
		}
		
		System.out.println();
		
		System.out.println("====TEST 6: Seller INSERT====");
		Seller seller3 = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.00, dep2);
		sellerDao.insert(seller3);
		System.out.println("New seller inserted!");
		System.out.println("New Id: " + seller3.getId());  // the id comes from Statement.RETURN_GENERATED_KEYS
	
		System.out.println();
		
		System.out.println("====TEST 7: Seller UPDATE====");
		Seller seller4 = sellerDao.findById(1);
		seller4.setName("Martha Waine");
		sellerDao.update(seller4);
		System.out.println("Update completed!");
		
		System.out.println();
		
		System.out.println("====TEST 8: Seller DELETE====");
		System.out.println("Enter 'id' for delete test: ");
		int id = sc.nextInt();
		sellerDao.deleteById(id);
		System.out.println("Delete completed!");
		
		sc.close();
	}
}
