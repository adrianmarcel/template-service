package br.com.devtools.service.template.gateway.database.template;

import br.com.devtools.service.template.core.usecase.model.TemplateFilterRequest;
import br.com.devtools.service.template.gateway.database.template.repository.TemplateRepository;
import br.com.devtools.service.template.gateway.database.template.specification.TemplateSpecification;
import br.com.devtools.service.template.gateway.domain.template.TemplateSearchAllGateway;
import br.com.devtools.service.template.gateway.domain.template.model.TemplateGatewayResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TemplateSearchAllDBGatewayImpl implements TemplateSearchAllGateway {

  private final TemplateRepository repository;

  @Override
  public Page<TemplateGatewayResponse> execute(
      TemplateFilterRequest filterRequest, Pageable pageable) {
    return repository
        .findAll(TemplateSpecification.build(filterRequest), pageable)
        .map(TemplateGatewayResponse::mapper);
  }
}
