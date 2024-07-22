package br.com.devtools.service.template.fixtures.template;

import br.com.devtools.service.template.core.usecase.model.TemplateResponse;
import java.time.OffsetDateTime;
import java.util.UUID;

public class TemplateResponseFixture {

  public static TemplateResponse validResponse() {
    return TemplateResponse.builder()
        .id(UUID.fromString("1cc73839-9b34-4158-9f93-789dc63a1cb2"))
        .name("Template 1")
        .createdAt(OffsetDateTime.now())
        .build();
  }

  public static TemplateResponse validUpdatedResponse() {
    return TemplateResponse.builder()
        .id(UUID.fromString("1cc73839-9b34-4158-9f93-789dc63a1cb2"))
        .name("Template 2")
        .createdAt(OffsetDateTime.now())
        .build();
  }
}
