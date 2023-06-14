package application;

import java.util.Date;

import db.Seller;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;

public class Program {

	public static void main(String[] args) {
		
		Department department = new Department(1, "books");
		Seller seller = new Seller(21, "Bob", "bob@email.com", new Date(), 3000.0, department);
		System.out.println(seller);
		
		SellerDao sellerDao = DaoFactory.createSllerDao();

	}

}
