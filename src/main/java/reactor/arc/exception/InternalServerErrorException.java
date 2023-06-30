package reactor.arc.exception;

public class InternalServerErrorException extends BaseException {

  private final Object[] parameters;

  public InternalServerErrorException(Object... parameters) {
    super(parameters);
    this.parameters = parameters;
  }

  public Object[] getParameters() {
    return this.parameters.clone();
  }
}
