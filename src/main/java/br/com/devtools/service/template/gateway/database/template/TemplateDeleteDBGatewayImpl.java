package br.com.devtools.service.template.gateway.database.template;

import br.com.devtools.service.template.gateway.database.template.repository.TemplateRepository;
import br.com.devtools.service.template.gateway.domain.template.TemplateDeleteGateway;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TemplateDeleteDBGatewayImpl implements TemplateDeleteGateway {

  private final TemplateRepository repository;

  @Override
  public void execute(UUID id) {
    repository.deleteById(id);
  }
}
