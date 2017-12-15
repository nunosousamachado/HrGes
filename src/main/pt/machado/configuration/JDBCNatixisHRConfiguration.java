package main.pt.machado.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.jdbc.pool.PoolProperties;

public class JDBCNatixisHRConfiguration {

	private Properties properties;

	public JDBCNatixisHRConfiguration() throws IOException {
		
		this.properties = this.properties();
	}
	
	public Properties properties() throws IOException {

        try(FileInputStream inputStream = new FileInputStream("src/resources/application.properties")){

            Properties properties = new Properties();
            properties.load(inputStream);
            return properties;
        }
    }

    public DataSource dataSource() {

        PoolProperties pool = new PoolProperties();

        pool.setUrl(this.properties.getProperty("database.url"));
        pool.setUsername(this.properties.getProperty("database.user"));
        pool.setPassword(this.properties.getProperty("database.password"));
        pool.setDriverClassName(this.properties.getProperty("database.driver"));
        pool.setInitialSize(10);
        pool.setMaxActive(100);
        pool.setTestWhileIdle(false);
        pool.setTestOnBorrow(true);
        pool.setTestOnReturn(false);
        pool.setValidationQuery("SELECT 1");
        pool.setMaxWait(10000);
        pool.setValidationInterval(30000);

        org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
        dataSource.setPoolProperties(pool);

        return dataSource;
    }

}
