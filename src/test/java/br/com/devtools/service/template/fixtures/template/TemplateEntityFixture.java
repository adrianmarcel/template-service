package br.com.devtools.service.template.fixtures.template;

import br.com.devtools.service.template.gateway.database.template.model.TemplateEntity;
import java.time.OffsetDateTime;
import java.util.UUID;

public class TemplateEntityFixture {

  public static TemplateEntity validEntity() {
    return TemplateEntity.builder()
        .id(UUID.fromString("b32c7c77-37ab-4839-b6fd-e3c79aa16f89"))
        .name("Template 1")
        .createdAt(OffsetDateTime.now())
        .build();
  }

  public static TemplateEntity validUpdatedEntity() {
    return TemplateEntity.builder()
        .id(UUID.fromString("b32c7c77-37ab-4839-b6fd-e3c79aa16f89"))
        .name("Template 2")
        .createdAt(OffsetDateTime.now())
        .build();
  }
}
