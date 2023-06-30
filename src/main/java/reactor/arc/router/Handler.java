package reactor.arc.router;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.arc.exception.BadRequestException;
import reactor.arc.exception.InternalServerErrorException;
import reactor.core.publisher.Mono;

@Component
public class Handler {

  public Mono<ServerResponse> list(ServerRequest serverRequest) {
    if (serverRequest.pathVariable("version").equals("v1")) {
      return Mono.error(new InternalServerErrorException("Internal Server Error"));
    }
    else if(serverRequest.pathVariable("version").equals("v2")) {
      return Mono.error(new BadRequestException("Bad Request"));
    }

    return ServerResponse.ok().body(Mono.just("Hello World"), String.class);
  }
}
