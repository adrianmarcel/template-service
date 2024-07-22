package br.com.devtools.service.template.core.usecase;

import br.com.devtools.service.template.core.usecase.model.TemplateFilterRequest;
import br.com.devtools.service.template.core.usecase.model.TemplateResponse;
import br.com.devtools.service.template.gateway.domain.template.TemplateSearchAllGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TemplateSearchUseCaseImpl implements TemplateSearchUseCase {

  private final TemplateSearchAllGateway searchAllGateway;

  @Override
  public Page<TemplateResponse> execute(TemplateFilterRequest filterRequest, Pageable pageable) {
    return searchAllGateway.execute(filterRequest, pageable).map(TemplateResponse::mapper);
  }
}
