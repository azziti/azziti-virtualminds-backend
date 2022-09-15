package com.virtualminds.test.backend.utils.error_managment;

import com.virtualminds.test.backend.utils.error_managment.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.time.LocalDateTime;


@ControllerAdvice()
public class ErrorHandler {

    // when a caisse does not exist
    @ExceptionHandler({NoSuchCaisseExistsException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity handleResourseNotFound(RuntimeException ex){

        return new ResponseEntity(
                new ErrorResponse(
                        HttpStatus.NOT_FOUND.value(),
                        ex.getMessage() ,
                        LocalDateTime.now() ,
                        null
                ) ,
                HttpStatus.NOT_FOUND);
    }

    //username already used
    @ExceptionHandler({PasswordMismatchException.class , UserAlreadyExistsException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity handleConflitError(RuntimeException ex){

            return new ResponseEntity(
                    new ErrorResponse(
                            HttpStatus.CONFLICT.value(),
                            ex.getMessage() ,
                            LocalDateTime.now() ,
                            null
                    ) ,
                    HttpStatus.CONFLICT
            );
    }

    // when a csv provided file is empty
    @ExceptionHandler({CSVFileEmptyException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity handleEmptyCSVError(CSVFileEmptyException ex){

        return new ResponseEntity(
                new ErrorResponse(
                        HttpStatus.CONFLICT.value(),
                        ex.getMessage() ,
                        LocalDateTime.now() ,
                        null
                ) ,
                HttpStatus.CONFLICT
        );
    }

    // when a provided file is not a csv
    @ExceptionHandler({NotCSVFileException.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity handleNotCSVError(NotCSVFileException ex){

        return new ResponseEntity(
                new ErrorResponse(
                        HttpStatus.UNPROCESSABLE_ENTITY.value(),
                        ex.getMessage() ,
                        LocalDateTime.now() ,
                        null
                ) ,
                HttpStatus.UNPROCESSABLE_ENTITY
        );
    }

    //when a file is larger than 2mb
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public ResponseEntity handleMaxSizeError(MaxUploadSizeExceededException ex) {
        return new ResponseEntity(
                new ErrorResponse(
                        HttpStatus.EXPECTATION_FAILED.value(),
                        "File too large ! Max file size is 10 mb" ,
                        LocalDateTime.now() ,
                        null
                ) ,
                HttpStatus.EXPECTATION_FAILED
        );
    }



    @ExceptionHandler({MethodArgumentNotValidException.class })
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex
    ) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Validation error. Check 'errors' field for details.",
                LocalDateTime.now() ,
                null
        );

        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errorResponse.addValidationError(fieldError.getField(),
                    fieldError.getDefaultMessage());
        }
        return ResponseEntity.unprocessableEntity().body(errorResponse);
    }


    
}

