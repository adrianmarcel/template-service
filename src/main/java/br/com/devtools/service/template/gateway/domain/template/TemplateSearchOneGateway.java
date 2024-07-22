package br.com.devtools.service.template.gateway.domain.template;

import br.com.devtools.service.template.gateway.domain.template.model.TemplateGatewayResponse;
import java.util.Optional;
import java.util.UUID;

public interface TemplateSearchOneGateway {

  Optional<TemplateGatewayResponse> execute(UUID id);
}
