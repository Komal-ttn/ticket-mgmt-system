package com.example.supportticket.exception;

import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(NotFoundException.class) @ResponseStatus(HttpStatus.NOT_FOUND)
    ProblemDetail notFound(NotFoundException e){return problem(HttpStatus.NOT_FOUND,"Not found",e.getMessage());}
    @ExceptionHandler(InvalidTransitionException.class) @ResponseStatus(HttpStatus.CONFLICT)
    ProblemDetail conflict(InvalidTransitionException e){return problem(HttpStatus.CONFLICT,"Invalid state transition",e.getMessage());}
    @ExceptionHandler(MethodArgumentNotValidException.class) @ResponseStatus(HttpStatus.BAD_REQUEST)
    ProblemDetail validation(MethodArgumentNotValidException e){
        var p=problem(HttpStatus.BAD_REQUEST,"Validation failed","One or more fields are invalid.");
        p.setProperty("errors",e.getBindingResult().getFieldErrors().stream().collect(Collectors.toMap(x->x.getField(),x->x.getDefaultMessage(),(a,b)->a)));
        return p;
    }
    private ProblemDetail problem(HttpStatus s,String title,String detail){var p=ProblemDetail.forStatusAndDetail(s,detail);p.setTitle(title);return p;}
}
