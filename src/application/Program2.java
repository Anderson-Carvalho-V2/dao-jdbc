package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
	
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		System.out.println("== TEST 1: Department insert ==");
		Department dep = new Department(null, "teste");
//		departmentDao.insert(dep);
		System.out.println("Departamento inserido, id: " + dep.getId());
		
		System.out.println("\n== TEST 2: Department findById ==");
		dep = departmentDao.findById(1);
		System.out.println(dep);
		
		System.out.println("\n== TEST 3: Department update ==");
		dep = departmentDao.findById(7);
		dep.setName("teste atualizado");
		departmentDao.update(dep);
		System.out.println("Update concluido");
		
		System.out.println("\n== TEST 4: Department findAll ==");
		List<Department> list = departmentDao.findAll();
		for(Department obj : list){
			System.out.println(obj);			
		}
		
		System.out.println("\n== TEST 5: Department delete ==");
		departmentDao.deleteById(9);
		System.out.println("Delecao concluida");

	}

}
