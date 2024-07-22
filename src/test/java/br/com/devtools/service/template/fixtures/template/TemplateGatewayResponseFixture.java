package br.com.devtools.service.template.fixtures.template;

import br.com.devtools.service.template.gateway.domain.template.model.TemplateGatewayResponse;
import java.time.OffsetDateTime;
import java.util.UUID;

public class TemplateGatewayResponseFixture {

  public static TemplateGatewayResponse validTemplateGatewayResponse() {
    return TemplateGatewayResponse.builder()
        .id(UUID.fromString("b32c7c77-37ab-4839-b6fd-e3c79aa16f89"))
        .name("Template 1")
        .createdAt(OffsetDateTime.now())
        .build();
  }

  public static TemplateGatewayResponse validTemplateUpdatedGatewayResponse() {
    return TemplateGatewayResponse.builder()
        .id(UUID.fromString("b32c7c77-37ab-4839-b6fd-e3c79aa16f89"))
        .name("Template 2")
        .createdAt(OffsetDateTime.now())
        .build();
  }
}
