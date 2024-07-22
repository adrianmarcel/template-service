package br.com.devtools.service.template.gateway.domain.template.model;

import java.util.UUID;
import lombok.*;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class TemplateGatewayRequest {

  private UUID id;
  private String name;

  public static TemplateGatewayRequest mapper(Object object) {
    var result = TemplateGatewayRequest.builder().build();
    BeanUtils.copyProperties(object, result);
    return result;
  }
}
