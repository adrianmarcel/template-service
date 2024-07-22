package br.com.devtools.service.template.core.usecase;

import br.com.devtools.service.template.core.usecase.model.TemplateRequest;
import br.com.devtools.service.template.core.usecase.model.TemplateResponse;
import br.com.devtools.service.template.gateway.domain.template.TemplateCreateGateway;
import br.com.devtools.service.template.gateway.domain.template.model.TemplateGatewayRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TemplateCreateUseCaseImpl implements TemplateCreateUseCase {

  private final TemplateCreateGateway createGateway;

  @Override
  public TemplateResponse execute(TemplateRequest request) {
    return TemplateResponse.mapper(createGateway.execute(TemplateGatewayRequest.mapper(request)));
  }
}
