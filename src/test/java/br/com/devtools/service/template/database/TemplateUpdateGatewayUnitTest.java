package br.com.devtools.service.template.database;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import br.com.devtools.service.template.fixtures.template.TemplateEntityFixture;
import br.com.devtools.service.template.fixtures.template.TemplateGatewayRequestFixture;
import br.com.devtools.service.template.gateway.database.template.TemplateUpdateDBGatewayImpl;
import br.com.devtools.service.template.gateway.database.template.model.TemplateEntity;
import br.com.devtools.service.template.gateway.database.template.repository.TemplateRepository;
import br.com.devtools.service.template.gateway.domain.template.TemplateUpdateGateway;
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
public class TemplateUpdateGatewayUnitTest {

  private TemplateUpdateGateway gateway;

  @Mock private TemplateRepository repository;

  @BeforeEach
  public void setUp() {
    gateway = new TemplateUpdateDBGatewayImpl(repository);
  }

  @Test
  public void testTemplateUpdateWithSuccess() {
    when(repository.findById(eq(UUID.fromString("b32c7c77-37ab-4839-b6fd-e3c79aa16f89"))))
        .thenReturn(Optional.of(TemplateEntityFixture.validEntity()));

    when(repository.save(any(TemplateEntity.class)))
        .thenReturn(TemplateEntityFixture.validUpdatedEntity());

    assertDoesNotThrow(
        () -> {
          var result = gateway.execute(TemplateGatewayRequestFixture.validUpdateGatewayRequest());

          assertTrue(result.isPresent());
          assertEquals(
              UUID.fromString("b32c7c77-37ab-4839-b6fd-e3c79aa16f89"), result.get().getId());
          assertEquals("Template 2", result.get().getName());
        });
  }
}
