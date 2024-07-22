package br.com.devtools.service.template.fixtures.template;

import br.com.devtools.service.template.core.usecase.model.TemplateFilterRequest;

public class TemplateFilterRequestFixture {

  public static TemplateFilterRequest validFilterRequest() {
    return TemplateFilterRequest.builder().name("Template 1").build();
  }

  public static TemplateFilterRequest invalidFilterRequest() {
    return TemplateFilterRequest.builder().name("Template 30").build();
  }
}
