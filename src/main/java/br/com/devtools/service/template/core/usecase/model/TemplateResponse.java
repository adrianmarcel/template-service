package br.com.devtools.service.template.core.usecase.model;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;
import lombok.*;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.BeanUtils;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)
public class TemplateResponse {

  private UUID id;
  private String name;
  private OffsetDateTime createdAt;

  @SneakyThrows
  public static TemplateResponse mapper(Object object) {
    var result = TemplateResponse.builder().build();
    BeanUtils.copyProperties(object, result);

    var name = PropertyUtils.getNestedProperty(object, "name");

    if (Objects.nonNull(name)) {
      result.setName(name.toString());
    }

    return result;
  }
}
