package com.ferraborghini.controller;

import com.ferraborghini.error.ServiceError;
import com.ferraborghini.response.structure.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/v1")
public class TestController {

    private final Logger logger = LoggerFactory.getLogger(TestController.class);

    @RequestMapping(value = "/hello", consumes = {"application/json"}, method = RequestMethod.POST)
    public ResponseEntity<?> test(
            @RequestBody @Valid User user)
    {
        Map<String,Object> othersParams = user.getOthers();
        if (othersParams.size() > 0){
            return ServiceError.JSON_FORMAT_ERROR.response("unknown field "+ othersParams.keySet());
        }
        logger.info("user name: {}, user sex: {}, user age: {}", user.getName(), user.getSex(), user.getAge());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
