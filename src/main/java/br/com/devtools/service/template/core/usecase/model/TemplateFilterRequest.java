package br.com.devtools.service.template.core.usecase.model;

import java.util.UUID;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TemplateFilterRequest {

  private UUID id;
  private String name;
}
