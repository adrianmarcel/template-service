package br.com.devtools.service.template.gateway.database.template;

import br.com.devtools.service.template.gateway.database.template.repository.TemplateRepository;
import br.com.devtools.service.template.gateway.domain.template.TemplateUpdateGateway;
import br.com.devtools.service.template.gateway.domain.template.model.TemplateGatewayRequest;
import br.com.devtools.service.template.gateway.domain.template.model.TemplateGatewayResponse;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TemplateUpdateDBGatewayImpl implements TemplateUpdateGateway {

  private final TemplateRepository repository;

  @Override
  public Optional<TemplateGatewayResponse> execute(TemplateGatewayRequest request) {
    return repository
        .findById(request.getId())
        .map(
            template -> {
              template.setName(request.getName());
              return TemplateGatewayResponse.mapper(repository.save(template));
            });
  }
}
