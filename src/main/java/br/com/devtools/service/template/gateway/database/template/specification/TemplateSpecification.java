package br.com.devtools.service.template.gateway.database.template.specification;

import br.com.devtools.service.template.core.usecase.model.TemplateFilterRequest;
import br.com.devtools.service.template.gateway.database.template.model.TemplateEntity;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.data.jpa.domain.Specification;

public final class TemplateSpecification {

  private TemplateSpecification() {}

  public static Specification<TemplateEntity> build(TemplateFilterRequest filterRequest) {
    return (((root, query, criteriaBuilder) -> {
      List<Predicate> predicates = new ArrayList<>();

      if (Objects.nonNull(filterRequest.getId())) {
        predicates.add(criteriaBuilder.equal(root.get("id"), filterRequest.getId()));
      }

      if (Objects.nonNull(filterRequest.getName())) {
        predicates.add(criteriaBuilder.equal(root.get("name"), filterRequest.getName()));
      }

      return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }));
  }
}
