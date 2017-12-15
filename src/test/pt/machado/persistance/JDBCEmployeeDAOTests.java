package test.pt.machado.persistance;

import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import main.pt.machado.configuration.JDBCNatixisHRConfiguration;
import main.pt.machado.persistance.EmployeeDTO;
import main.pt.machado.persistance.IEmployeeDao;
import main.pt.machado.persistance.JDBCEMployeeDAO;


public class JDBCEmployeeDAOTests {
	
	IEmployeeDao employeeDao;
	
	@Before
	public void setup() throws Exception {
		
		JDBCNatixisHRConfiguration configuration = new JDBCNatixisHRConfiguration();
		
		DataSource dataSource = configuration.dataSource();
		
		this.employeeDao = new JDBCEMployeeDAO(dataSource);
		
	}
	 
	@After
	public void tearDown() throws Exception {
		
		try {
			this.employeeDao.deleteEmployeeByBI("55533377");	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void should_get_an_employee_When_given_an_BI() throws Exception {
		
		String expectedBI = "77763598";
		String nome = "Rui Carvalho";
		String dataNascimento = "12/05/1985";
		String morada = "Rua das Fontainhas, 73 4000-289 Porto";
		String categoria = "Senior";
		String funcao = "Java Devoloper";
		
		EmployeeDTO employee = this.employeeDao.getEmployeeByBI("77763598");
		
		Assert.assertEquals(expectedBI, employee.getBi());
		Assert.assertEquals(nome, employee.getNome());
		Assert.assertEquals(dataNascimento, employee.getDataNascimento());
		Assert.assertEquals(morada, employee.getMorada());
		Assert.assertEquals(categoria, employee.getCategoria());
		Assert.assertEquals(funcao, employee.getFuncao());
		
	}
	
	@Test
	public void should_insert_employee_When_given_an_employee() throws Exception {
		
		EmployeeDTO employee = new EmployeeDTO("55533377", "John Doe", "23/02/1995", "Rua de British Street, 243 4000-001 Porto", "Senior", "Databse Admin");

		this.employeeDao.insert(employee);
		
		this.employeeDao.getEmployeeByBI("55533377");
		
		Assert.assertEquals("55533377", employee.getBi());
		Assert.assertEquals("John Doe", employee.getNome());
		Assert.assertEquals("23/02/1995", employee.getDataNascimento());
		Assert.assertEquals("Rua de British Street, 243 4000-001 Porto", employee.getMorada());
		Assert.assertEquals("Senior", employee.getCategoria());
		Assert.assertEquals("Databse Admin", employee.getFuncao());
		
	}
	
	@Test
    public void should_update_employee_When_given_an_employee() throws Exception {

		EmployeeDTO employee = new EmployeeDTO("55533377", "John Doe", "23/02/1995", "Rua de British Street, 243 4000-001 Porto", "Senior", "Databse Admin");

		this.employeeDao.insert(employee);
		
		employee.setDataNascimento("25/12/1995");
		
		this.employeeDao.update(employee);
		
		this.employeeDao.getEmployeeByBI("55533377");
		
		Assert.assertEquals("55533377", employee.getBi());
		Assert.assertEquals("25/12/1995", employee.getDataNascimento());
		
	}
	
	@Test
    public void should_delete_employee_When_given_an_BI() throws Exception {
		
		EmployeeDTO employee = new EmployeeDTO("55533377", "John Doe", "23/02/1995", "Rua de British Street, 243 4000-001 Porto", "Senior", "Databse Admin");

		this.employeeDao.insert(employee);

		EmployeeDTO emp = this.employeeDao.getEmployeeByBI("55533377");
		Assert.assertNotNull(emp);
		
		this.employeeDao.deleteEmployeeByBI("55533377");
		
		try {
            this.employeeDao.getEmployeeByBI("55533377");
            Assert.fail();
        } catch (Exception ex) {
            Assert.assertTrue(true);
        }
		
	}
	
	@Test(expected = Exception.class)
    public void should_throw_exception_When_employee_BI_does_not_exist() throws Exception {

        this.employeeDao.deleteEmployeeByBI("55544455");
    }
	
	@Test
    public void should_return_employees_list_from_funcionarios_table() throws Exception {

        List<EmployeeDTO> employeeDTOList = this.employeeDao.getAll();

        Assert.assertEquals(5, employeeDTOList.size());

    }
		
}
