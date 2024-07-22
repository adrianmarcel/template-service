package br.com.devtools.service.template.usecase;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import br.com.devtools.service.template.core.domain.shared.exception.BusinessException;
import br.com.devtools.service.template.core.domain.shared.exception.ExceptionCode;
import br.com.devtools.service.template.core.usecase.TemplateUpdateUseCase;
import br.com.devtools.service.template.core.usecase.TemplateUpdateUseCaseImpl;
import br.com.devtools.service.template.fixtures.template.TemplateGatewayResponseFixture;
import br.com.devtools.service.template.fixtures.template.TemplateRequestFixture;
import br.com.devtools.service.template.gateway.domain.template.TemplateUpdateGateway;
import br.com.devtools.service.template.gateway.domain.template.model.TemplateGatewayRequest;
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
public class TemplateUpdateUseCaseUnitTest {

  private TemplateUpdateUseCase useCase;

  @Mock private TemplateUpdateGateway updateGateway;

  @BeforeEach
  public void setUp() {
    useCase = new TemplateUpdateUseCaseImpl(updateGateway);
  }

  @Test
  public void testUpdateTemplateWithErrorWhenTemplateNotFound() {
    when(updateGateway.execute(any(TemplateGatewayRequest.class))).thenReturn(Optional.empty());

    var be =
        assertThrows(
            BusinessException.class,
            () -> useCase.execute(TemplateRequestFixture.validUpdateRequest()));

    assertEquals(ExceptionCode.TEMPLATE_NOT_FOUND.getMessage(), be.getMessage());
    assertEquals(ExceptionCode.TEMPLATE_NOT_FOUND.getCode().toString(), be.getCode());
  }

  @Test
  public void testUpdateTemplateWithSuccess() {
    when(updateGateway.execute(any(TemplateGatewayRequest.class)))
        .thenReturn(
            Optional.of(TemplateGatewayResponseFixture.validTemplateUpdatedGatewayResponse()));

    assertDoesNotThrow(
        () -> {
          var result = useCase.execute(TemplateRequestFixture.validUpdateRequest());

          assertNotNull(result);
          assertEquals(UUID.fromString("b32c7c77-37ab-4839-b6fd-e3c79aa16f89"), result.getId());
          assertEquals("Template 2", result.getName());
        });
  }
}
