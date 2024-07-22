package br.com.devtools.service.template.gateway.database.template;

import br.com.devtools.service.template.gateway.database.template.model.TemplateEntity;
import br.com.devtools.service.template.gateway.database.template.repository.TemplateRepository;
import br.com.devtools.service.template.gateway.domain.template.TemplateCreateGateway;
import br.com.devtools.service.template.gateway.domain.template.model.TemplateGatewayRequest;
import br.com.devtools.service.template.gateway.domain.template.model.TemplateGatewayResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TemplateCreateDBGatewayImpl implements TemplateCreateGateway {

  private final TemplateRepository repository;

  @Override
  public TemplateGatewayResponse execute(TemplateGatewayRequest request) {
    return TemplateGatewayResponse.mapper(repository.save(TemplateEntity.mapper(request)));
  }
}
