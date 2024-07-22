package br.com.devtools.service.template.usecase;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import br.com.devtools.service.template.core.domain.shared.exception.BusinessException;
import br.com.devtools.service.template.core.domain.shared.exception.ExceptionCode;
import br.com.devtools.service.template.core.usecase.TemplateDeleteUseCase;
import br.com.devtools.service.template.core.usecase.TemplateDeleteUseCaseImpl;
import br.com.devtools.service.template.fixtures.template.TemplateGatewayResponseFixture;
import br.com.devtools.service.template.gateway.domain.template.TemplateDeleteGateway;
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
public class TemplateDeleteUseCaseUnitTest {

  private TemplateDeleteUseCase useCase;

  @Mock private TemplateDeleteGateway deleteGateway;
  @Mock private TemplateSearchOneGateway searchOneGateway;

  @BeforeEach
  public void setUp() {
    useCase = new TemplateDeleteUseCaseImpl(deleteGateway, searchOneGateway);
  }

  @Test
  public void testTemplateDeleteWithErrorWhenTemplateNotFound() {
    when(searchOneGateway.execute(any(UUID.class))).thenReturn(Optional.empty());

    var be =
        assertThrows(
            BusinessException.class,
            () -> useCase.execute(UUID.fromString("a0ae2eeb-4fae-45e3-8a2d-40464d97a857")));

    assertEquals(ExceptionCode.TEMPLATE_NOT_FOUND.getMessage(), be.getMessage());
    assertEquals(ExceptionCode.TEMPLATE_NOT_FOUND.getCode().toString(), be.getCode());
  }

  @Test
  public void testTemplateDeleteWithSuccess() {
    when(searchOneGateway.execute(any(UUID.class)))
        .thenReturn(Optional.of(TemplateGatewayResponseFixture.validTemplateGatewayResponse()));

    assertDoesNotThrow(
        () -> useCase.execute(UUID.fromString("b32c7c77-37ab-4839-b6fd-e3c79aa16f89")));
  }
}
