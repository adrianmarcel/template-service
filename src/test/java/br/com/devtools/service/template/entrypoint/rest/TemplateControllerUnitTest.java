package br.com.devtools.service.template.entrypoint.rest;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.devtools.service.template.core.domain.shared.exception.BusinessException;
import br.com.devtools.service.template.core.domain.shared.exception.ExceptionCode;
import br.com.devtools.service.template.core.domain.shared.exception.TemplateExceptionHandler;
import br.com.devtools.service.template.core.usecase.TemplateCreateUseCase;
import br.com.devtools.service.template.core.usecase.TemplateDeleteUseCase;
import br.com.devtools.service.template.core.usecase.TemplateSearchUseCase;
import br.com.devtools.service.template.core.usecase.TemplateUpdateUseCase;
import br.com.devtools.service.template.core.usecase.model.TemplateFilterRequest;
import br.com.devtools.service.template.core.usecase.model.TemplateRequest;
import br.com.devtools.service.template.fixtures.template.TemplateFilterRequestFixture;
import br.com.devtools.service.template.fixtures.template.TemplateRequestFixture;
import br.com.devtools.service.template.fixtures.template.TemplateResponseFixture;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@TestInstance(PER_CLASS)
@WebMvcTest(TemplateController.class)
@Import(TemplateExceptionHandler.class)
public class TemplateControllerUnitTest {

  @Autowired private WebApplicationContext context;

  @Autowired @MockBean private TemplateCreateUseCase createUseCase;
  @Autowired @MockBean private TemplateDeleteUseCase deleteUseCase;
  @Autowired @MockBean private TemplateUpdateUseCase updateUseCase;
  @Autowired @MockBean private TemplateSearchUseCase searchUseCase;

  private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
  }

  @Test
  public void testCreateTemplateWithErrorWhenInvalidPath() throws Exception {
    mockMvc
        .perform(
            post("/devtools/v1/templates")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(TemplateRequestFixture.validRequest()))
                .characterEncoding("UTF-8"))
        .andExpect(status().isNotFound());
  }

  @Test
  public void testCreateTemplateWithErrorWhenInvalidBodyWithoutName() throws Exception {
    mockMvc
        .perform(
            post("/devtools/v1/template")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    objectMapper.writeValueAsString(
                        TemplateRequestFixture.invalidRequestWithoutName()))
                .characterEncoding("UTF-8"))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.code").value(HttpStatus.BAD_REQUEST.value()))
        .andExpect(jsonPath("$.message").value(HttpStatus.BAD_REQUEST.getReasonPhrase()))
        .andExpect(jsonPath("$.errors[0].name").value("must not be blank"));
  }

  @Test
  public void testCreateTemplateWithSuccess() throws Exception {
    when(createUseCase.execute(any(TemplateRequest.class)))
        .thenReturn(TemplateResponseFixture.validResponse());

    mockMvc
        .perform(
            post("/devtools/v1/template")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(TemplateRequestFixture.validRequest()))
                .characterEncoding("UTF-8"))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value("1cc73839-9b34-4158-9f93-789dc63a1cb2"))
        .andExpect(jsonPath("$.name").value("Template 1"));
  }

  @Test
  public void testReadAllTemplatesWithSuccessWhenReturnIsEmpty() throws Exception {
    when(searchUseCase.execute(
            TemplateFilterRequestFixture.invalidFilterRequest(), Pageable.unpaged()))
        .thenReturn(new PageImpl<>(Collections.emptyList()));

    mockMvc
        .perform(
            get("/devtools/v1/template")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
        .andExpect(status().isOk());
  }

  @Test
  public void testReadAllTemplatesWithSuccess() throws Exception {
    when(searchUseCase.execute(
            TemplateFilterRequestFixture.validFilterRequest(), Pageable.unpaged()))
        .thenReturn(
            new PageImpl<>(Collections.singletonList(TemplateResponseFixture.validResponse())));

    mockMvc
        .perform(
            get("/devtools/v1/template")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
        .andExpect(status().isOk());
  }

  @Test
  public void testReadOneTemplatesWithErrorWhenTemplateNotFound() throws Exception {
    var exception = new BusinessException(ExceptionCode.TEMPLATE_NOT_FOUND);
    when(searchUseCase.execute(any(TemplateFilterRequest.class), any(Pageable.class)))
        .thenThrow(exception);

    mockMvc
        .perform(
            get("/devtools/v1/template/{id}", "7025c696-92de-4ee8-9c28-bd11de8fc4ea")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.errors[0].code").value(exception.getCode()))
        .andExpect(jsonPath("$.errors[0].message").value(exception.getMessage()))
        .andExpect(jsonPath("$.code").value(exception.getHttpStatusCode().value()))
        .andExpect(jsonPath("$.message").value(exception.getHttpStatusCode().getReasonPhrase()));
  }

  @Test
  public void testReadOneTemplateWithSuccess() throws Exception {
    when(searchUseCase.execute(
            TemplateFilterRequestFixture.validFilterRequest(), Pageable.unpaged()))
        .thenReturn(
            new PageImpl<>(Collections.singletonList(TemplateResponseFixture.validResponse())));

    mockMvc
        .perform(
            get("/devtools/v1/template/{id}", "1cc73839-9b34-4158-9f93-789dc63a1cb2")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value("1cc73839-9b34-4158-9f93-789dc63a1cb2"))
        .andExpect(jsonPath("$.name").value("Template 1"));
  }

  @Test
  public void testUpdateTemplateWithErrorWhenTemplateNotFound() throws Exception {
    var exception = new BusinessException(ExceptionCode.TEMPLATE_NOT_FOUND);
    when(updateUseCase.execute(any(TemplateRequest.class))).thenThrow(exception);

    mockMvc
        .perform(
            put("/devtools/v1/template/{id}", "7025c696-92de-4ee8-9c28-bd11de8fc4ea")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    objectMapper.writeValueAsString(TemplateRequestFixture.validUpdateRequest()))
                .characterEncoding("UTF-8"))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.errors[0].code").value(exception.getCode()))
        .andExpect(jsonPath("$.errors[0].message").value(exception.getMessage()))
        .andExpect(jsonPath("$.code").value(exception.getHttpStatusCode().value()))
        .andExpect(jsonPath("$.message").value(exception.getHttpStatusCode().getReasonPhrase()));
  }

  @Test
  public void testUpdateTemplateWithSuccess() throws Exception {
    when(updateUseCase.execute(any(TemplateRequest.class)))
        .thenReturn(TemplateResponseFixture.validUpdatedResponse());

    mockMvc
        .perform(
            put("/devtools/v1/template/{id}", "1cc73839-9b34-4158-9f93-789dc63a1cb2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    objectMapper.writeValueAsString(TemplateRequestFixture.validUpdateRequest()))
                .characterEncoding("UTF-8"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value("1cc73839-9b34-4158-9f93-789dc63a1cb2"))
        .andExpect(jsonPath("$.name").value("Template 2"));
  }

  @Test
  public void testDeleteTemplateWithErrorWhenTemplateNotFound() throws Exception {
    var exception = new BusinessException(ExceptionCode.TEMPLATE_NOT_FOUND);
    doThrow(exception).when(deleteUseCase).execute(any(UUID.class));

    mockMvc
        .perform(
            delete("/devtools/v1/template/{id}", "19e384f0-81cf-4058-98ca-be8a535736bc")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.errors[0].code").value(exception.getCode()))
        .andExpect(jsonPath("$.errors[0].message").value(exception.getMessage()))
        .andExpect(jsonPath("$.code").value(exception.getHttpStatusCode().value()))
        .andExpect(jsonPath("$.message").value(exception.getHttpStatusCode().getReasonPhrase()));
  }

  @Test
  public void testDeleteTemplateWithSuccess() throws Exception {
    doNothing().when(deleteUseCase).execute(any(UUID.class));

    mockMvc
        .perform(
            delete("/devtools/v1/template/{id}", "1cc73839-9b34-4158-9f93-789dc63a1cb2")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
        .andExpect(status().isNoContent());
  }
}
