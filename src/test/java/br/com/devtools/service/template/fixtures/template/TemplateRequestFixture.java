package br.com.devtools.service.template.fixtures.template;

import br.com.devtools.service.template.core.usecase.model.TemplateRequest;
import java.util.UUID;

public class TemplateRequestFixture {

  public static TemplateRequest validRequest() {
    return TemplateRequest.builder().name("Template 1").build();
  }

  public static TemplateRequest validUpdateRequest() {
    return TemplateRequest.builder()
        .id(UUID.fromString("1cc73839-9b34-4158-9f93-789dc63a1cb2"))
        .name("Template 2")
        .build();
  }

  public static TemplateRequest invalidRequestWithoutName() {
    return TemplateRequest.builder().build();
  }
}
