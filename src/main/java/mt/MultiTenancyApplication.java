package mt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class,
    LiquibaseAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class})
@EnableJpaRepositories(basePackages = "mt.model")
public class MultiTenancyApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(MultiTenancyApplication.class, args);
    }
}
