package br.com.devtools.service.template.database;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import br.com.devtools.service.template.fixtures.template.TemplateEntityFixture;
import br.com.devtools.service.template.fixtures.template.TemplateFilterRequestFixture;
import br.com.devtools.service.template.gateway.database.template.TemplateSearchAllDBGatewayImpl;
import br.com.devtools.service.template.gateway.database.template.repository.TemplateRepository;
import br.com.devtools.service.template.gateway.domain.template.TemplateSearchAllGateway;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

@TestInstance(PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class TemplateSearchAllGatewayUnitTest {

  private TemplateSearchAllGateway gateway;

  @Mock private TemplateRepository repository;

  @BeforeEach
  public void setUp() {
    gateway = new TemplateSearchAllDBGatewayImpl(repository);
  }

  @Test
  public void testTemplateSearchAllWithSuccess() {
    when(repository.findAll(any(Specification.class), any(Pageable.class)))
        .thenReturn(new PageImpl<>(List.of(TemplateEntityFixture.validEntity())));

    assertDoesNotThrow(
        () -> {
          var result =
              gateway.execute(
                  TemplateFilterRequestFixture.validFilterRequest(), Pageable.unpaged());

          assertNotNull(result);
          assertNotNull(result.getContent());
          assertEquals(1, result.getSize());
          assertEquals(
              UUID.fromString("b32c7c77-37ab-4839-b6fd-e3c79aa16f89"),
              result.getContent().get(0).getId());
          assertEquals("Template 1", result.getContent().get(0).getName());
        });
  }
}
