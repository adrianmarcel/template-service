package br.com.devtools.service.template.entrypoint.rest;

import static org.springframework.http.ResponseEntity.*;

import br.com.devtools.service.template.core.usecase.TemplateCreateUseCase;
import br.com.devtools.service.template.core.usecase.TemplateDeleteUseCase;
import br.com.devtools.service.template.core.usecase.TemplateSearchUseCase;
import br.com.devtools.service.template.core.usecase.TemplateUpdateUseCase;
import br.com.devtools.service.template.core.usecase.model.TemplateFilterRequest;
import br.com.devtools.service.template.core.usecase.model.TemplateRequest;
import br.com.devtools.service.template.core.usecase.model.TemplateResponse;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(TemplateController.BASE_URI)
public class TemplateController {

  public static final String BASE_URI = "/devtools/v1/template";

  private final TemplateCreateUseCase createUseCase;
  private final TemplateDeleteUseCase deleteUseCase;
  private final TemplateUpdateUseCase updateUseCase;
  private final TemplateSearchUseCase searchUseCase;

  @PostMapping
  public ResponseEntity<TemplateResponse> create(@Valid @RequestBody TemplateRequest request) {
    var response = createUseCase.execute(request);
    return created(URI.create(BASE_URI.concat("/").concat(response.getId().toString())))
        .body(response);
  }

  @GetMapping
  public ResponseEntity<Page<TemplateResponse>> readAll(
      @Valid TemplateFilterRequest filterRequest, Pageable pageable) {
    return ok(searchUseCase.execute(filterRequest, pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<TemplateResponse> readOne(@PathVariable("id") UUID id) {
    var filter = TemplateFilterRequest.builder().id(id).build();
    return ok(searchUseCase.execute(filter, Pageable.unpaged()).getContent().get(0));
  }

  @PutMapping("/{id}")
  public ResponseEntity<TemplateResponse> update(
      @PathVariable("id") UUID id, @Valid @RequestBody TemplateRequest request) {
    request.setId(id);
    return ok(updateUseCase.execute(request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
    deleteUseCase.execute(id);
    return noContent().build();
  }
}
