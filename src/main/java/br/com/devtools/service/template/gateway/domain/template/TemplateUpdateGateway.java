package br.com.devtools.service.template.gateway.domain.template;

import br.com.devtools.service.template.gateway.domain.template.model.TemplateGatewayRequest;
import br.com.devtools.service.template.gateway.domain.template.model.TemplateGatewayResponse;
import java.util.Optional;

public interface TemplateUpdateGateway {

  Optional<TemplateGatewayResponse> execute(TemplateGatewayRequest request);
}
