package reactor.arc.exception;

public class BaseException extends RuntimeException {

  private final Object[] parameters;

  public BaseException(final Object... parameters) {
    super();
    this.parameters = parameters;
  }

  public Object[] getParameters() {
    return this.parameters.clone();
  }
}
