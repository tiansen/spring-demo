package com.ferraborghini.error;

import com.sun.javafx.binding.StringFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public enum ServiceError {
    SUCCESS("success", "TS.200"),
    SERVICE_ERROR("service inner error", "TS.500"),
    JSON_FORMAT_ERROR("json format error, %s", "TS.400");

    String errorMsg;

    String errCode;

    ServiceError(String errorMsg, String errCode){
        this.errCode = errCode;

        this.errorMsg = errorMsg;
    }

    public ResponseEntity<?> response(String message){
        if (SERVICE_ERROR == this){
            ErrorEntity response = responseEntity(message);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }else if(SUCCESS == this){
            ErrorEntity response = responseEntity(message);
            return ResponseEntity.ok(response);
        }else{
            ErrorEntity response = responseEntity(message);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    public ErrorEntity responseEntity(String message){
        ErrorEntity response = new ErrorEntity();
        if (SERVICE_ERROR == this){
            response.setErrCode(errCode);
            response.setErrMsg(errorMsg);

        }else if(SUCCESS == this){
            response.setResult("success");
        }else{
            response.setErrCode(errCode);
            response.setErrMsg(StringFormatter.format(errorMsg, message).getValue());
        }
        return response;
    }



}
