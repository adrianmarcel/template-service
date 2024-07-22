package br.com.devtools.service.template.lang.test;

import br.com.devtools.service.template.Application;
import br.com.devtools.service.template.lang.test.extension.IntegrationTestExtension;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("integration-test")
@ExtendWith(IntegrationTestExtension.class)
@SpringBootTest(
  classes = Application.class,
  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public abstract class AbstractIT {

  @LocalServerPort protected int port;

  @BeforeEach
  public void setupEach() {
    RestAssured.port = this.port;
  }
}
