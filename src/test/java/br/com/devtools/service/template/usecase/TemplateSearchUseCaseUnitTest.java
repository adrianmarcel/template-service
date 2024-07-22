package br.com.devtools.service.template.usecase;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import br.com.devtools.service.template.core.usecase.TemplateSearchUseCase;
import br.com.devtools.service.template.core.usecase.TemplateSearchUseCaseImpl;
import br.com.devtools.service.template.core.usecase.model.TemplateFilterRequest;
import br.com.devtools.service.template.fixtures.template.TemplateFilterRequestFixture;
import br.com.devtools.service.template.fixtures.template.TemplateGatewayResponseFixture;
import br.com.devtools.service.template.gateway.domain.template.TemplateSearchAllGateway;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@TestInstance(PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class TemplateSearchUseCaseUnitTest {

  private TemplateSearchUseCase useCase;

  @Mock private TemplateSearchAllGateway searchAllGateway;

  @BeforeEach
  public void setUp() {
    useCase = new TemplateSearchUseCaseImpl(searchAllGateway);
  }

  @Test
  public void testSearchAllTemplatesWithSuccessWhenReturnIsPageEmpty() {
    when(searchAllGateway.execute(any(TemplateFilterRequest.class), any(Pageable.class)))
        .thenReturn(Page.empty());

    var result =
        useCase.execute(TemplateFilterRequestFixture.validFilterRequest(), Pageable.unpaged());

    assertTrue(result.getContent().isEmpty());
  }

  @Test
  public void testSearchAllTemplatesWithSuccess() {
    var responseGateway =
        new PageImpl<>(List.of(TemplateGatewayResponseFixture.validTemplateGatewayResponse()));

    when(searchAllGateway.execute(any(TemplateFilterRequest.class), any(Pageable.class)))
        .thenReturn(responseGateway);

    var result =
        useCase.execute(TemplateFilterRequestFixture.validFilterRequest(), Pageable.unpaged());

    assertFalse(result.getContent().isEmpty());
    assertEquals(
        UUID.fromString("b32c7c77-37ab-4839-b6fd-e3c79aa16f89"),
        result.getContent().get(0).getId());
    assertEquals("Template 1", result.getContent().get(0).getName());
  }
}
