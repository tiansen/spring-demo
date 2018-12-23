package com.ferraborghini.response.structure;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ferraborghini.validation.validate.ValidatorUserName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    @ValidatorUserName
    @JsonProperty("name")
    String name;

    @JsonProperty("sex")
    String sex;

    @JsonProperty("age")
    int age;

    @Setter
    @JsonAnySetter
    @Getter
    @JsonIgnore
    Map<String, Object> others = new HashMap<>();


}
