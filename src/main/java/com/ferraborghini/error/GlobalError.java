package com.ferraborghini.error;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice  // 此处需要加上注解
public class GlobalError {
    private Logger logger = LoggerFactory.getLogger(GlobalError.class);

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public ResponseEntity<?> handleNullPointException(HttpServletRequest request, Exception e) {
        logger.error("got a null point exception {}", e.getMessage());

        return ServiceError.SERVICE_ERROR.response("");
    }


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity<?> jsonHandler(HttpServletRequest request, Exception e) {
        logger.error("got a null point exception {}", e.getMessage());
        return ServiceError.SERVICE_ERROR.response("");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleBindException(MethodArgumentNotValidException ex) {

        FieldError fieldError = ex.getBindingResult().getFieldError();
        logger.info("param error:{}({})", fieldError.getDefaultMessage(), fieldError.getField());
        ObjectError error = ex.getBindingResult().getAllErrors().get(0);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONObject.parseObject(error.getDefaultMessage(), ErrorEntity.class));

    }

}
