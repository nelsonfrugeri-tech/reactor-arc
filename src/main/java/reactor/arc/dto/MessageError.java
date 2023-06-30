package reactor.arc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class MessageError {

  @JsonProperty
  private String code;

  @JsonProperty
  private String description;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonProperty("errors")
  private List<Error> errors;

  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  @Getter
  public static class Error {

    @JsonProperty("message")
    private String message;
  }

}
