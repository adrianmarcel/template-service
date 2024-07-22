package br.com.devtools.service.template;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "br.com.devtools")
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
