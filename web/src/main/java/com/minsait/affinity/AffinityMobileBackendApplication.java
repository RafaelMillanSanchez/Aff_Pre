package com.minsait.affinity;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

@SpringBootApplication
@Configuration
@ComponentScan("com.minsait.affinity")
@EnableJpaRepositories(basePackages = "com.minsait.affinity.repo")
@Import({ SecurityConfig.class })
public class AffinityMobileBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(AffinityMobileBackendApplication.class, args);
		//Proximo.setup();
	}

	@Bean
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	public Module hb5ParameterNamesModule() {
		return new Hibernate5Module();
	}

}
