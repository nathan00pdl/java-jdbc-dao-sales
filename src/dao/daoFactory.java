package dao;

import db.DB;
import impl.DepartmentDAOjdbc;
import impl.SellerDAOjdbc;

public class daoFactory {
	
	public static SellerDAO createSellerDAO() {
		return new SellerDAOjdbc(DB.getConnection()); 
	}

	public static DepartmentDAO createDepartmentDAO() {
		return new DepartmentDAOjdbc(DB.getConnection());
	}
}
