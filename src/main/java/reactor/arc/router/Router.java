package reactor.arc.router;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class Router {

  @Autowired
  private Handler handler;

  @Bean
  public RouterFunction<ServerResponse> routerFunction() {
    return RouterFunctions.route()
        .path("/v1/reactor/arc", builder -> builder
            .GET("/m1/{version}", handler::list))
        .build();
  }
}
