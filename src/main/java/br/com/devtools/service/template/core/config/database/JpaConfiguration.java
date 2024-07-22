package br.com.devtools.service.template.core.config.database;

import br.com.devtools.service.template.gateway.database.BaseRepositoryImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
  repositoryBaseClass = BaseRepositoryImpl.class,
  basePackages = "br.com.devtools"
)
public class JpaConfiguration {}
