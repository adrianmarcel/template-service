package br.com.devtools.service.template.lang.test.extension;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.junit.jupiter.api.extension.ExtensionContext.Namespace.GLOBAL;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.PostgreSQLContainer;

public class IntegrationTestExtension implements BeforeAllCallback, BeforeEachCallback {

  static final Lock lock = new ReentrantLock();
  private static boolean started = false;

  WireMockClassRule wiremock = null;
  PostgreSQLContainer dbPostgres = null;

  @Override
  public void beforeAll(ExtensionContext context) {
    lock.lock();

    if (!started) {
      started = true;

      wiremock = new WireMockClassRule(wireMockConfig().dynamicPort());
      wiremock.start();
      WireMock.configureFor("localhost", wiremock.port());

      dbPostgres =
          new PostgreSQLContainer("postgres")
              .withDatabaseName("template_service-it-db")
              .withUsername("postgres")
              .withPassword("admin");
      dbPostgres.start();

      System.setProperty("spring.datasource.it.url.container", dbPostgres.getJdbcUrl());
      System.setProperty("spring.datasource.it.username.container", dbPostgres.getUsername());
      System.setProperty("spring.datasource.it.password.container", dbPostgres.getPassword());
      context.getRoot().getStore(GLOBAL).put("INTEGRATION_TEST_dbPostgres", this.dbPostgres);

      context.getRoot().getStore(GLOBAL).put("INTEGRATION_TEST_wiremock", this.wiremock);
    } else {
      this.wiremock =
          (WireMockClassRule) context.getRoot().getStore(GLOBAL).get("INTEGRATION_TEST_wiremock");
      this.dbPostgres =
          (PostgreSQLContainer)
              context.getRoot().getStore(GLOBAL).get("INTEGRATION_TEST_dbPostgres");
    }

    lock.unlock();
  }

  @Override
  public void beforeEach(ExtensionContext context) {
    wiremock.resetAll();
  }
}
