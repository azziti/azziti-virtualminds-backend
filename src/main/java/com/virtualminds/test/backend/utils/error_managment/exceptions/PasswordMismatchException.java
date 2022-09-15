package com.virtualminds.test.backend.utils.error_managment.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordMismatchException extends RuntimeException {
    private String message ;
}