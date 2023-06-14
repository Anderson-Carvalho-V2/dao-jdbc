package application;

import db.Seller;
import model.dao.DaoFactory;
import model.dao.SellerDao;

public class Program {

	public static void main(String[] args) {
		
		SellerDao sellerDao = DaoFactory.createSllerDao();
		
		Seller seller = sellerDao.findById(3);
		
		System.out.println(seller);

	}

}
