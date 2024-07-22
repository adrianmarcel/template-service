package br.com.devtools.service.template.gateway.domain.template.model;

import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.*;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TemplateGatewayResponse {

  private UUID id;
  private String name;
  private OffsetDateTime createdAt;

  public static TemplateGatewayResponse mapper(Object object) {
    var result = TemplateGatewayResponse.builder().build();
    BeanUtils.copyProperties(object, result);
    return result;
  }
}
