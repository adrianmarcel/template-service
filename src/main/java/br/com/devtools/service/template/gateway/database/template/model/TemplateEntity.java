package br.com.devtools.service.template.gateway.database.template.model;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.BeanUtils;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "templates")
public class TemplateEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column private String name;

  @CreationTimestamp
  @Column(updatable = false)
  private OffsetDateTime createdAt;

  public static TemplateEntity mapper(Object object) {
    var result = TemplateEntity.builder().build();
    BeanUtils.copyProperties(object, result);

    return result;
  }
}
