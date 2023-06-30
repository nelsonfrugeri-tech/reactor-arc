package reactor.arc.router;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = Router.class)
@Import(Handler.class)
public class RouterTest {

  @Autowired
  private WebTestClient webTestClient;

  @Test
  void whenListM1Success() {
    webTestClient.get()
        .uri(uriBuilder -> uriBuilder
            .path("/v1/reactor/arc/m1/{version}")
            .build("v1"))
        .exchange()
        .expectStatus().is5xxServerError();
  }
}
