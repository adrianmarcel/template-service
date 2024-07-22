package br.com.devtools.service.template.database;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

import br.com.devtools.service.template.gateway.database.template.TemplateDeleteDBGatewayImpl;
import br.com.devtools.service.template.gateway.database.template.repository.TemplateRepository;
import br.com.devtools.service.template.gateway.domain.template.TemplateDeleteGateway;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@TestInstance(PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class TemplateDeleteGatewayUnitTest {

  private TemplateDeleteGateway gateway;

  @Mock private TemplateRepository repository;

  @BeforeEach
  public void setUp() {
    gateway = new TemplateDeleteDBGatewayImpl(repository);
  }

  @Test
  public void testTemplateDeleteWithSuccess() {
    doNothing().when(repository).deleteById(any(UUID.class));

    assertDoesNotThrow(
        () -> gateway.execute(UUID.fromString("b32c7c77-37ab-4839-b6fd-e3c79aa16f89")));
  }
}
