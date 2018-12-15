package com.ferraborghini.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ferraborghini.response.structure.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserResponse {

    @JsonProperty("user")
    User user;

}
