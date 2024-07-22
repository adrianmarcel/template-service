package br.com.devtools.service.template.core.usecase.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class TemplateRequest {

  @JsonIgnore private UUID id;

  @NotBlank private String name;
}
