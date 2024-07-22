package br.com.devtools.service.template.core.usecase;

import br.com.devtools.service.template.core.usecase.model.TemplateRequest;
import br.com.devtools.service.template.core.usecase.model.TemplateResponse;
import jakarta.validation.Valid;

public interface TemplateCreateUseCase {

  TemplateResponse execute(@Valid TemplateRequest request);
}
