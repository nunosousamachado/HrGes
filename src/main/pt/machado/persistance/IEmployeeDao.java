package main.pt.machado.persistance;

import java.util.List;
import java.sql.SQLException;

public interface IEmployeeDao {
	
	EmployeeDTO getEmployeeByBI(String bi) throws SQLException, IllegalArgumentException;
	
	void insert(EmployeeDTO funcionario) throws SQLException;
	
	void update(EmployeeDTO funcionario) throws SQLException;
	
	void deleteEmployeeByBI(String funcionario) throws SQLException, IllegalArgumentException;
	
	List<EmployeeDTO> getAll() throws SQLException;

}
