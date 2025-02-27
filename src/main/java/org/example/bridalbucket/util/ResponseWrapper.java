package org.example.bridalbucket.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseWrapper<P, E> {

    private String status;
    private P payload;
    private E errors;

    private ResponseWrapper(String status, P payload, E errors) {
        this.status = status;
        this.payload = payload;
        this.errors = errors;
    }

    /*
     *   these static methods are to create response wrapper with EITHER status & payload OR status & errors.
     */
    public static <P, E> ResponseWrapper<P, E> successResponseWithPayload(String status, P payload){
        Objects.requireNonNull(payload, "Payload can not be null.");
        return new ResponseWrapper<>(status, payload, null);
    }

    public static <P, E> ResponseWrapper<P, E> failedResponseWithErrors(String status, E errors){
        Objects.requireNonNull(errors, "Error list can not be null.");
        return new ResponseWrapper<>(status, null, errors);
    }

}
