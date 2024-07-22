package br.com.devtools.service.template.core.domain.shared.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionCode {
  TEMPLATE_NOT_FOUND(1000, "Template not found.", HttpStatus.NOT_FOUND);

  private final Integer code;
  private final String message;
  private final HttpStatus httpStatus;

  ExceptionCode(Integer code, String message, HttpStatus httpStatus) {
    this.code = code;
    this.message = message;
    this.httpStatus = httpStatus;
  }
}
