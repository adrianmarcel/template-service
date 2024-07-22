package br.com.devtools.service.template.core.usecase;

import br.com.devtools.service.template.core.domain.shared.exception.BusinessException;
import br.com.devtools.service.template.core.domain.shared.exception.ExceptionCode;
import br.com.devtools.service.template.core.usecase.model.TemplateRequest;
import br.com.devtools.service.template.core.usecase.model.TemplateResponse;
import br.com.devtools.service.template.gateway.domain.template.TemplateUpdateGateway;
import br.com.devtools.service.template.gateway.domain.template.model.TemplateGatewayRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TemplateUpdateUseCaseImpl implements TemplateUpdateUseCase {

  private final TemplateUpdateGateway updateGateway;

  @Override
  public TemplateResponse execute(TemplateRequest request) {
    return updateGateway
        .execute(TemplateGatewayRequest.mapper(request))
        .map(TemplateResponse::mapper)
        .orElseThrow(() -> new BusinessException(ExceptionCode.TEMPLATE_NOT_FOUND));
  }
}
