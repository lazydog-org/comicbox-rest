package org.lazydog.comicbox.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler.
 *
 * @author rjrjr
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {EntityAlreadyExistsException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleEntityAlreadyExistsException(EntityAlreadyExistsException e) {
        return e.getMessage();
    }
    
    @ExceptionHandler(value = {EntityMalformedException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleEntityMalformedException(EntityMalformedException e) {
        return e.getMessage();
    }
    
    @ExceptionHandler(value = {EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleEntityNotFoundException(EntityNotFoundException e) {
        return e.getMessage();
    }
}
