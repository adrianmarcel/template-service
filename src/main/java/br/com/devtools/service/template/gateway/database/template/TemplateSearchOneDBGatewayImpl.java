package br.com.devtools.service.template.gateway.database.template;

import br.com.devtools.service.template.gateway.database.template.repository.TemplateRepository;
import br.com.devtools.service.template.gateway.domain.template.TemplateSearchOneGateway;
import br.com.devtools.service.template.gateway.domain.template.model.TemplateGatewayResponse;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TemplateSearchOneDBGatewayImpl implements TemplateSearchOneGateway {

  private final TemplateRepository repository;

  @Override
  public Optional<TemplateGatewayResponse> execute(UUID id) {
    return repository.findById(id).map(TemplateGatewayResponse::mapper);
  }
}
