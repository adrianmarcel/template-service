package br.com.devtools.service.template.usecase;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import br.com.devtools.service.template.core.usecase.TemplateCreateUseCase;
import br.com.devtools.service.template.core.usecase.TemplateCreateUseCaseImpl;
import br.com.devtools.service.template.fixtures.template.TemplateGatewayResponseFixture;
import br.com.devtools.service.template.fixtures.template.TemplateRequestFixture;
import br.com.devtools.service.template.gateway.domain.template.TemplateCreateGateway;
import br.com.devtools.service.template.gateway.domain.template.model.TemplateGatewayRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@TestInstance(PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class TemplateCreateUseCaseUnitTest {

  private TemplateCreateUseCase useCase;

  @Mock private TemplateCreateGateway createGateway;

  @BeforeEach
  public void setUp() {
    useCase = new TemplateCreateUseCaseImpl(createGateway);
  }

  @Test
  public void testTemplateCreateWithSuccess() {
    when(createGateway.execute(any(TemplateGatewayRequest.class)))
        .thenReturn(TemplateGatewayResponseFixture.validTemplateGatewayResponse());

    assertDoesNotThrow(() -> useCase.execute(TemplateRequestFixture.validRequest()));
  }
}
