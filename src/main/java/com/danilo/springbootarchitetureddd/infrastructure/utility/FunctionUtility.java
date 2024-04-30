package com.danilo.springbootarchitetureddd.infrastructure.utility;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FunctionUtility {

    public static void validateNumber(String number) {
        String regex = "\\d{4} \\d{4} \\d{4} \\d{4}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(number);

        if (!matcher.find()) {
            String msg = "Number invalid.";
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,msg);
        }
    }

    public static void validateExpirationDate(LocalDate expirationDate) {
        if (expirationDate.isBefore(LocalDate.now())) {
            String msg = "Expiration date cannot be before than the current date.";
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,msg);
        }
    }

}
