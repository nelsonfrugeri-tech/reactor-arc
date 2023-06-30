package reactor.arc.error.handler;

import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.arc.error.exception.InternalServerErrorException;
import reactor.arc.error.exception.BadRequestException;
import reactor.arc.error.exception.BaseException;
import reactor.core.publisher.Mono;

@Configuration
public class GlobalErrorHandler extends AbstractErrorWebExceptionHandler {

    public GlobalErrorHandler(DefaultErrorAttributes globalErrorAttributes,
        ApplicationContext applicationContext,
        ServerCodecConfigurer serverCodecConfigurer) {

        super(globalErrorAttributes, new WebProperties.Resources(), applicationContext);
        super.setMessageWriters(serverCodecConfigurer.getWriters());
        super.setMessageReaders(serverCodecConfigurer.getReaders());
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }

    private Mono<ServerResponse> renderErrorResponse(ServerRequest request) {
        return Mono.just(request)
            .map(this::getError)
            .cast(BaseException.class)
            .flatMap(Mono::<ServerResponse>error)
            .onErrorResume(BadRequestException.class, WebErrorHandler::handleBadRequestException)
            .onErrorResume(InternalServerErrorException.class, WebErrorHandler::handleInternalServerErrorException)
            .onErrorResume(Throwable.class, WebErrorHandler::handleUnknownException);
    }
}
