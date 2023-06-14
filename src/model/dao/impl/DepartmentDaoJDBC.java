package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {

	private Connection conn;

	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Department department) {
		PreparedStatement pstm = null;
		try {
			pstm = conn.prepareStatement("INSERT INTO department (Name) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
			pstm.setString(1, department.getName());

			int linhasAfetadas = pstm.executeUpdate();
			if (linhasAfetadas > 0) {
				ResultSet rs = pstm.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					department.setId(id);
				}
				DB.closeResultSet(rs);
			} else {
				throw new DbException("Erro inesperado! Nenhuma linha foi alterada");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(pstm);
		}
	}

	@Override
	public void update(Department department) {
		PreparedStatement pstm = null;
		try {
			pstm = conn.prepareStatement("UPDATE department SET Name = ? WHERE id = ?");
			pstm.setString(1, department.getName());
			pstm.setInt(2, department.getId());
			pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(pstm);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement pstm = null;
		try {
			pstm = conn.prepareStatement("DELETE FROM department WHERE id = ?");
			pstm.setInt(1, id);			
			int linhasAfetadas = pstm.executeUpdate();
			if(linhasAfetadas == 0) {
				throw new DbException("Erro: Id " + id + " não existe");
			}
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(pstm);
		}
	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			pstm = conn.prepareStatement("SELECT * from department WHERE id = ?");
			pstm.setInt(1, id);
			rs = pstm.executeQuery();
			if (rs.next()) {
				Department dep = new Department();
				dep.setId(rs.getInt("Id"));
				dep.setName(rs.getString("Name"));
				return dep;
			}
			throw new DbException("Erro! id " + id + " não encontrado");
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(pstm);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Department> findAll() {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Department> list = new ArrayList<>();
		
		try {
			pstm = conn.prepareStatement("SELECT * FROM department");
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				Department dep = new Department();
				dep.setId(rs.getInt("Id"));
				dep.setName(rs.getString("Name"));
				list.add(dep);
			}
			return list;
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(pstm);
			DB.closeResultSet(rs);
		}
		
	}

}
