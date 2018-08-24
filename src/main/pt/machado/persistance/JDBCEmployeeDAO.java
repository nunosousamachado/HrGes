package main.pt.machado.persistance;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import java.sql.*;

public class JDBCEmployeeDAO implements IEmployeeDao {

	private DataSource dataSource;
	private static final String GET_EMPLOYEE_BY_BI = "SELECT * FROM funcionarios WHERE bi=?";
	private static final String INSERT_EMPLOYEE = "INSERT INTO funcionarios (bi, nome, data_nascimento, morada, categoria, funcao) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_EMPLOYEE = "UPDATE funcionarios SET data_nascimento=? WHERE bi=?";
	private static final String DELETE_EMPLOYEE = "DELETE FROM funcionarios WHERE bi=?";
	private static final String GET_EMPLOYEE_LIST = "SELECT * FROM funcionarios";

	public JDBCEmployeeDAO(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public EmployeeDTO getEmployeeByBI(String bi) throws SQLException, IllegalArgumentException {

		try (Connection connection = this.dataSource.getConnection()) {
			return getEmployeeByBI(bi, connection);
		}
	}

	private EmployeeDTO getEmployeeByBI(String bi, Connection connection) throws SQLException, IllegalArgumentException {

		try (PreparedStatement statement = connection.prepareStatement(GET_EMPLOYEE_BY_BI)) {

			statement.setString(1, bi);

			try (ResultSet set = statement.executeQuery()) {
				
				if (set.next()) {

					String user_bi = set.getString("bi");
					String nome = set.getString("nome");
					String dataNascimento = set.getString("data_nascimento");
					String morada = set.getString("morada");
					String categoria = set.getString("categoria");
					String funcao = set.getString("funcao");

					EmployeeDTO user = new EmployeeDTO(user_bi, nome, dataNascimento, morada, categoria, funcao);

					return user;
				} else {
					throw new IllegalArgumentException("User with BI = " + bi + " does not exist");
				}
			}
		}
	}

	@Override
	public void insert(EmployeeDTO funcionario) throws SQLException {

		try (Connection connection = this.dataSource.getConnection()) {

			insert(funcionario, connection);
		}
	}

	private void insert(EmployeeDTO funcionario, Connection connection) throws SQLException {

		try (PreparedStatement statement = connection.prepareStatement(INSERT_EMPLOYEE)) {

			statement.setString(1, funcionario.getBi());
			statement.setString(2, funcionario.getNome());
			statement.setString(3, funcionario.getDataNascimento());
			statement.setString(4, funcionario.getMorada());
			statement.setString(5, funcionario.getCategoria());
			statement.setString(6, funcionario.getFuncao());
			statement.executeUpdate();
		}
	}

	@Override
	public void update(EmployeeDTO funcionario) throws SQLException {
		
		try(Connection connection = this.dataSource.getConnection()) {

            update(funcionario, connection);
        }
	}
	
	private void update(EmployeeDTO funcionario, Connection connection) throws SQLException {

        try (PreparedStatement statement = connection.prepareStatement(UPDATE_EMPLOYEE)) {

            statement.setString(1, funcionario.getDataNascimento());
            statement.setString(2, funcionario.getBi());
            statement.executeUpdate();
        }
    }
	

	@Override
	public void deleteEmployeeByBI(String bi) throws SQLException, IllegalArgumentException {
		
		try(Connection connection = this.dataSource.getConnection()) {

			deleteEmployeeByBI(bi, connection);
        }
	}
	
	private void deleteEmployeeByBI(String bi, Connection connection) throws SQLException, IllegalArgumentException {
        
		try(PreparedStatement statement = connection.prepareStatement(DELETE_EMPLOYEE)) {

            statement.setString(1, bi);

            int result = statement.executeUpdate();

            if (result == 0) {
                throw new IllegalArgumentException("User with BI = " + bi + " does not exist");
            }
        }
    }
	

	@Override
	public List<EmployeeDTO> getAll() throws SQLException {
		
		try(Connection connection = this.dataSource.getConnection()) {
            
			List<EmployeeDTO> userDTOList = getAll(connection);
            
			return userDTOList;
        }
	}
	
	private List<EmployeeDTO> getAll(Connection connection) throws SQLException {

        try(PreparedStatement statement = connection.prepareStatement(GET_EMPLOYEE_LIST);
            ResultSet set = statement.executeQuery()) {

            List<EmployeeDTO> employeeDTOsDTOList = new ArrayList<>();

            while(set.next()) {

            		String user_bi = set.getString("bi");
				String nome = set.getString("nome");
				String dataNascimento = set.getString("data_nascimento");
				String morada = set.getString("morada");
				String categoria = set.getString("categoria");
				String funcao = set.getString("funcao");

                employeeDTOsDTOList.add(new EmployeeDTO(user_bi, nome, dataNascimento, morada, categoria, funcao));

            }
            return employeeDTOsDTOList;
        }
    }	

}
