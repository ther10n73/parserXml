import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * Created by Khartonov Oleg on 28.05.2016.
 */
@Configuration
public class AppConfig {
    public Connection connection;
    public DriverManagerDataSource dataSource = new DriverManagerDataSource();
    private final String URL = "jdbc:postgresql://127.0.0.1:6666/qw";
    private final String user = "postgres";
    private final String password = "";

    @Bean(name = "configFile")
    public String configFile() {
        return System.getProperty("mw.config");
    }

    public DataSource dataSource(){
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(URL);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public MainParserXml main() {
        return new MainParserXml(new DBHelper(dataSource()));
    }

}
