package br.com.devtools.service.template.fixtures.template;

import br.com.devtools.service.template.gateway.domain.template.model.TemplateGatewayRequest;
import java.util.UUID;

public class TemplateGatewayRequestFixture {

  public static TemplateGatewayRequest validGatewayRequest() {
    return TemplateGatewayRequest.builder().name("Template 1").build();
  }

  public static TemplateGatewayRequest validUpdateGatewayRequest() {
    return TemplateGatewayRequest.builder()
        .id(UUID.fromString("b32c7c77-37ab-4839-b6fd-e3c79aa16f89"))
        .name("Template 2")
        .build();
  }
}
