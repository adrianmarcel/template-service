package br.com.devtools.service.template.gateway.domain.template;

import br.com.devtools.service.template.core.usecase.model.TemplateFilterRequest;
import br.com.devtools.service.template.gateway.domain.template.model.TemplateGatewayResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TemplateSearchAllGateway {

  Page<TemplateGatewayResponse> execute(TemplateFilterRequest filterRequest, Pageable pageable);
}
