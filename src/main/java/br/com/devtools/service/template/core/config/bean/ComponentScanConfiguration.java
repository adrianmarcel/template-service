package br.com.devtools.service.template.core.config.bean;

import br.com.devtools.service.template.core.domain.shared.exception.TemplateExceptionHandler;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
  basePackages = "br.com.devtools",
  excludeFilters =
      @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        value = TemplateExceptionHandler.class
      )
)
public class ComponentScanConfiguration {}
