package com.virtualminds.test.backend.utils.error_managment.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAlreadyExistsException extends RuntimeException {
    private String message ;
}

