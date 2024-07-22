package br.com.devtools.service.template.core.usecase;

import br.com.devtools.service.template.core.usecase.model.TemplateFilterRequest;
import br.com.devtools.service.template.core.usecase.model.TemplateResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TemplateSearchUseCase {

  Page<TemplateResponse> execute(@Valid TemplateFilterRequest request, Pageable pageable);
}
