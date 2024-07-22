package br.com.devtools.service.template.gateway.domain.template;

import br.com.devtools.service.template.gateway.domain.template.model.TemplateGatewayRequest;
import br.com.devtools.service.template.gateway.domain.template.model.TemplateGatewayResponse;

public interface TemplateCreateGateway {

  TemplateGatewayResponse execute(TemplateGatewayRequest request);
}
