package reactor.arc.error.handler;

import java.util.Arrays;
import java.util.Collections;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.arc.dto.MessageError;
import reactor.arc.error.exception.InternalServerErrorException;
import reactor.arc.error.exception.BadRequestException;
import reactor.core.publisher.Mono;

public class WebErrorHandler {

    public static Mono<ServerResponse> handleBadRequestException(BadRequestException e) {
        return ServerResponse.status(HttpStatus.BAD_REQUEST)
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(MessageError.builder()
                .code("ERR-002")
                .description("Internal Server Error")
                .errors(Collections.singletonList(MessageError.Error.builder()
                    .message(e.getParameters()[0].toString())
                    .build()))
                .build()));
    }

    public static Mono<ServerResponse> handleInternalServerErrorException(
        InternalServerErrorException e) {
        return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(MessageError.builder()
                .code("ERR-001")
                .description("Internal Server Error")
                .errors(Collections.singletonList(MessageError.Error.builder()
                    .message(Arrays.toString(e.getParameters()))
                    .build()))
                .build()));
    }

    public static Mono<ServerResponse> handleUnknownException(Throwable e) {
        return Mono.error(new RuntimeException("Erro desconhecido"));
    }

    public static Mono<ServerResponse> handleNotFoundException(NotFoundException e) {
        return ServerResponse.status(HttpStatus.NOT_FOUND)
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(MessageError.builder()
                .code("ERR-003")
                .description("Not Found Error")
                .errors(Collections.singletonList(MessageError.Error.builder()
                    .message(e.getMessage())
                    .build()))
                .build()));
    }
}
