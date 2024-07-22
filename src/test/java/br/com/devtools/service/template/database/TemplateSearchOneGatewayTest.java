package br.com.devtools.service.template.database;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import br.com.devtools.service.template.fixtures.template.TemplateEntityFixture;
import br.com.devtools.service.template.gateway.database.template.TemplateSearchOneDBGatewayImpl;
import br.com.devtools.service.template.gateway.database.template.repository.TemplateRepository;
import br.com.devtools.service.template.gateway.domain.template.TemplateSearchOneGateway;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@TestInstance(PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class TemplateSearchOneGatewayTest {

  private TemplateSearchOneGateway gateway;

  @Mock private TemplateRepository repository;

  @BeforeEach
  public void setUp() {
    gateway = new TemplateSearchOneDBGatewayImpl(repository);
  }

  @Test
  public void testTemplateSearchOneWithSuccess() {
    when(repository.findById(any(UUID.class)))
        .thenReturn(Optional.of(TemplateEntityFixture.validEntity()));

    assertDoesNotThrow(
        () -> {
          var result = gateway.execute(UUID.fromString("b32c7c77-37ab-4839-b6fd-e3c79aa16f89"));

          assertTrue(result.isPresent());
          assertEquals(
              UUID.fromString("b32c7c77-37ab-4839-b6fd-e3c79aa16f89"), result.get().getId());
          assertEquals("Template 1", result.get().getName());
        });
  }
}
