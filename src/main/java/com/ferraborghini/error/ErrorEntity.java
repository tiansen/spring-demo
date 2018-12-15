package com.ferraborghini.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorEntity {
    @JsonProperty("err_code")
    String errCode;

    @JsonProperty("err_msg")
    String errMsg;

    @JsonProperty("result")
    String result;
}
