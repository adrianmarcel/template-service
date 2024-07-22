package br.com.devtools.service.template.database;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import br.com.devtools.service.template.fixtures.template.TemplateEntityFixture;
import br.com.devtools.service.template.fixtures.template.TemplateGatewayRequestFixture;
import br.com.devtools.service.template.gateway.database.template.TemplateCreateDBGatewayImpl;
import br.com.devtools.service.template.gateway.database.template.model.TemplateEntity;
import br.com.devtools.service.template.gateway.database.template.repository.TemplateRepository;
import br.com.devtools.service.template.gateway.domain.template.TemplateCreateGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@TestInstance(PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class TemplateCreateGatewayUnitTest {

  private TemplateCreateGateway gateway;

  @Mock private TemplateRepository repository;

  @BeforeEach
  public void setUp() {
    gateway = new TemplateCreateDBGatewayImpl(repository);
  }

  @Test
  public void testTemplateCreateWithSuccess() {
    when(repository.save(any(TemplateEntity.class)))
        .thenReturn(TemplateEntityFixture.validEntity());

    assertDoesNotThrow(
        () -> {
          var result = gateway.execute(TemplateGatewayRequestFixture.validGatewayRequest());

          assertNotNull(result);
          assertEquals("Template 1", result.getName());
        });
  }
}
