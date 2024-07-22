package br.com.devtools.service.template.entrypoint.rest;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import br.com.devtools.service.template.lang.test.AbstractIT;
import br.com.devtools.service.template.lang.test.helpers.FileHelper;
import io.restassured.http.ContentType;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

public class TemplateControllerIT extends AbstractIT {

  @Test
  @Sql(
    scripts = {"classpath:db/clear-tables.sql"},
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
  )
  public void testCreateTemplateWithErrorWhenInvalidPath() throws JSONException {
    var response =
        given()
            .contentType(ContentType.JSON)
            .contentType(MediaType.APPLICATION_JSON.toString())
            .accept(MediaType.APPLICATION_JSON.toString())
            .when()
            .post("/devtools/v1/templates")
            .prettyPeek()
            .then()
            .statusCode(HttpStatus.NOT_FOUND.value())
            .extract()
            .asString();

    assertThat(response).isNotEmpty();

    FileHelper.validateResponse("data/assert/404-create-template-path-not-found.json", response);
  }

  @Test
  @Sql(
    scripts = {"classpath:db/clear-tables.sql"},
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
  )
  public void testCreateTemplateWithErrorWhenInvalidBodyWithoutName() throws JSONException {
    var response =
        given()
            .contentType(ContentType.JSON)
            .contentType(MediaType.APPLICATION_JSON.toString())
            .accept(MediaType.APPLICATION_JSON.toString())
            .when()
            .body(
                FileHelper.getStringFromFile(
                    "data/mock/create-template-invalid-request-without-name.json"))
            .post("/devtools/v1/template")
            .prettyPeek()
            .then()
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .extract()
            .asString();

    assertThat(response).isNotEmpty();

    FileHelper.validateResponse(
        "data/assert/400-invalid-create-template-without-name.json", response);
  }

  @Test
  @Sql(
    scripts = {"classpath:db/clear-tables.sql"},
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
  )
  public void testCreateTemplateWithSuccess() throws JSONException {
    var response =
        given()
            .contentType(ContentType.JSON)
            .contentType(MediaType.APPLICATION_JSON.toString())
            .accept(MediaType.APPLICATION_JSON.toString())
            .when()
            .body(FileHelper.getStringFromFile("data/mock/create-template-valid-request.json"))
            .post("/devtools/v1/template")
            .prettyPeek()
            .then()
            .statusCode(HttpStatus.CREATED.value())
            .extract()
            .asString();

    assertThat(response).isNotEmpty();

    FileHelper.validateResponse("data/assert/201-create-template-with-success.json", response);
  }

  @Test
  @Sql(
    scripts = {"classpath:db/clear-tables.sql"},
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
  )
  public void testReadAllTemplatesWithSuccessWhenReturnIsEmpty() throws JSONException {
    var response =
        given()
            .contentType(ContentType.JSON)
            .contentType(MediaType.APPLICATION_JSON.toString())
            .accept(MediaType.APPLICATION_JSON.toString())
            .when()
            .get("/devtools/v1/template")
            .prettyPeek()
            .then()
            .statusCode(HttpStatus.OK.value())
            .extract()
            .asString();

    assertThat(response).isNotEmpty();

    FileHelper.validateResponse("data/assert/200-search-all-templates-is-empty.json", response);
  }

  @Test
  @Sql(
    scripts = {"classpath:db/clear-tables.sql", "classpath:db/templates/insert-templates.sql"},
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
  )
  public void testReadAllTemplatesWithSuccess() throws JSONException {
    var response =
        given()
            .contentType(ContentType.JSON)
            .contentType(MediaType.APPLICATION_JSON.toString())
            .accept(MediaType.APPLICATION_JSON.toString())
            .when()
            .get("/devtools/v1/template")
            .prettyPeek()
            .then()
            .statusCode(HttpStatus.OK.value())
            .extract()
            .asString();

    assertThat(response).isNotEmpty();

    FileHelper.validateResponse("data/assert/200-search-all-templates-is-not-empty.json", response);
  }

  @Test
  @Sql(
    scripts = {"classpath:db/clear-tables.sql"},
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
  )
  public void testReadOneTemplateWithErrorWhenTemplateNotFound() throws JSONException {
    var response =
        given()
            .contentType(ContentType.JSON)
            .contentType(MediaType.APPLICATION_JSON.toString())
            .accept(MediaType.APPLICATION_JSON.toString())
            .when()
            .get("/devtools/v1/template/16b41496-2acb-4af9-ac37-155f2de7bed7")
            .prettyPeek()
            .then()
            .statusCode(HttpStatus.NOT_FOUND.value())
            .extract()
            .asString();

    assertThat(response).isNotEmpty();

    FileHelper.validateResponse("data/assert/404-template-not-found.json", response);
  }

  @Test
  @Sql(
    scripts = {"classpath:db/clear-tables.sql", "classpath:db/templates/insert-templates.sql"},
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
  )
  public void testReadOneTemplateWithSuccess() throws JSONException {
    var response =
        given()
            .contentType(ContentType.JSON)
            .contentType(MediaType.APPLICATION_JSON.toString())
            .accept(MediaType.APPLICATION_JSON.toString())
            .when()
            .get("/devtools/v1/template/dc44f3fd-4f4e-4bcc-a0a6-cab7a355d217")
            .prettyPeek()
            .then()
            .statusCode(HttpStatus.OK.value())
            .extract()
            .asString();

    assertThat(response).isNotEmpty();

    FileHelper.validateResponse("data/assert/200-search-one-template-with-success.json", response);
  }

  @Test
  @Sql(
    scripts = {"classpath:db/clear-tables.sql"},
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
  )
  public void testUpdateTemplateWithErrorWhenTemplateNotFound() throws JSONException {
    var response =
        given()
            .contentType(ContentType.JSON)
            .contentType(MediaType.APPLICATION_JSON.toString())
            .accept(MediaType.APPLICATION_JSON.toString())
            .when()
            .body(FileHelper.getStringFromFile("data/mock/update-template-valid-request.json"))
            .put("/devtools/v1/template/16b41496-2acb-4af9-ac37-155f2de7bed7")
            .prettyPeek()
            .then()
            .statusCode(HttpStatus.NOT_FOUND.value())
            .extract()
            .asString();

    assertThat(response).isNotEmpty();

    FileHelper.validateResponse("data/assert/404-template-not-found.json", response);
  }

  @Test
  @Sql(
    scripts = {"classpath:db/clear-tables.sql", "classpath:db/templates/insert-templates.sql"},
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
  )
  public void testUpdateTemplateWithSuccess() throws JSONException {
    var response =
        given()
            .contentType(ContentType.JSON)
            .contentType(MediaType.APPLICATION_JSON.toString())
            .accept(MediaType.APPLICATION_JSON.toString())
            .when()
            .body(FileHelper.getStringFromFile("data/mock/update-template-valid-request.json"))
            .put("/devtools/v1/template/dc44f3fd-4f4e-4bcc-a0a6-cab7a355d217")
            .prettyPeek()
            .then()
            .statusCode(HttpStatus.OK.value())
            .extract()
            .asString();

    assertThat(response).isNotEmpty();

    FileHelper.validateResponse("data/assert/200-template-updated-with-success.json", response);
  }

  @Test
  @Sql(
    scripts = {"classpath:db/clear-tables.sql"},
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
  )
  public void testDeleteTemplateWithErrorWhenTemplateNotFound() throws JSONException {
    var response =
        given()
            .contentType(ContentType.JSON)
            .contentType(MediaType.APPLICATION_JSON.toString())
            .accept(MediaType.APPLICATION_JSON.toString())
            .when()
            .delete("/devtools/v1/template/16b41496-2acb-4af9-ac37-155f2de7bed7")
            .prettyPeek()
            .then()
            .statusCode(HttpStatus.NOT_FOUND.value())
            .extract()
            .asString();

    assertThat(response).isNotEmpty();

    FileHelper.validateResponse("data/assert/404-template-not-found.json", response);
  }

  @Test
  @Sql(
    scripts = {"classpath:db/clear-tables.sql", "classpath:db/templates/insert-templates.sql"},
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
  )
  public void testDeleteTemplateWithSuccess() {
    given()
        .contentType(ContentType.JSON)
        .contentType(MediaType.APPLICATION_JSON.toString())
        .accept(MediaType.APPLICATION_JSON.toString())
        .when()
        .delete("/devtools/v1/template/dc44f3fd-4f4e-4bcc-a0a6-cab7a355d217")
        .prettyPeek()
        .then()
        .statusCode(HttpStatus.NO_CONTENT.value())
        .extract()
        .asString();
  }
}
