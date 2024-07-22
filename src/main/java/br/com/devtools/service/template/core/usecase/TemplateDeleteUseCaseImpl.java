package br.com.devtools.service.template.core.usecase;

import br.com.devtools.service.template.core.domain.shared.exception.BusinessException;
import br.com.devtools.service.template.core.domain.shared.exception.ExceptionCode;
import br.com.devtools.service.template.gateway.domain.template.TemplateDeleteGateway;
import br.com.devtools.service.template.gateway.domain.template.TemplateSearchOneGateway;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TemplateDeleteUseCaseImpl implements TemplateDeleteUseCase {

  private final TemplateDeleteGateway deleteGateway;
  private final TemplateSearchOneGateway searchOneGateway;

  @Override
  public void execute(UUID id) {
    searchOneGateway
        .execute(id)
        .ifPresentOrElse(
            template -> deleteGateway.execute(template.getId()),
            () -> {
              throw new BusinessException(ExceptionCode.TEMPLATE_NOT_FOUND);
            });
  }
}
