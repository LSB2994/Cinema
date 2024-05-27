package org.kshrd.leng_sambath_btb_homeword03.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.time.Instant;
import java.time.LocalDateTime;

@RestControllerAdvice //hand to get json form of handler
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(VenueNotFountException.class)
    ProblemDetail handlerVenueNotFountException(VenueNotFountException exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getMessage());
//        problemDetail.setType(URI.create("http://localhost:8080/api/v1/venue/id/not-found"));
//        problemDetail.setTitle("Not Found");
//        problemDetail.setStatus(404);
//        problemDetail.setInstance(URI.create("/api/v1/venue/id"));
        problemDetail.setProperty("time:", LocalDateTime.now());
        return problemDetail;
    }

    @ExceptionHandler(FieldEmptyException.class)
    ProblemDetail handlerFieldEmptyException(FieldEmptyException ex){
        ProblemDetail problemDetail =ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
//            problemDetail.setType(URI.create("http://localhost:8080/api/v1/venue/bad_request"));
//            problemDetail.setTitle("Field can not empty");
            problemDetail.setProperty("time: ",LocalDateTime.now());
        return problemDetail;
    }

    @ExceptionHandler(NotNull.class)
    ProblemDetail handlerVenueNotNull(NotNull e){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,e.getMessage());
        problemDetail.setType(URI.create("about:blank"));
        problemDetail.setTitle("Bad Request");
        problemDetail.setStatus(HttpStatus.valueOf(400));
        problemDetail.setInstance(URI.create("/api/v1/venue/id"));
        problemDetail.setProperty("time:", Instant.now());
        return problemDetail;
    }
}
