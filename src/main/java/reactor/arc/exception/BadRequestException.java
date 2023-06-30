package reactor.arc.exception;

public class BadRequestException extends BaseException {

  private final Object[] parameters;

  public BadRequestException(Object... parameters) {
    super(parameters);
    this.parameters = parameters;
  }

  public Object[] getParameters() {
    return this.parameters.clone();
  }
}
