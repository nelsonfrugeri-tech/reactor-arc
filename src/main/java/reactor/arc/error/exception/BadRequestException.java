package reactor.arc.error.exception;

public class BadRequestException extends BaseException {

  public BadRequestException(Object... parameters) {
    super(parameters);
  }
}
