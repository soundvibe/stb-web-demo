package no.storebrand.presentations.usecases;

import no.storebrand.presentations.entities.UserName;
import no.storebrand.presentations.entities.ValidationError;
import no.storebrand.presentations.functional.Result;

import java.util.Optional;

import static no.storebrand.presentations.functional.Result.failure;
import static no.storebrand.presentations.functional.Result.success;

/**
 * @author OZY on 2015.03.25
 */

public class UserNameValidator {

    public static Result<ValidationError,UserName> validate(String candidate) {
        return Optional.ofNullable(candidate)
                .filter(username -> !username.isEmpty())
                .map(String::trim)
                .map(username -> username.replace(" ", ""))
                .map(String::toUpperCase)
                .<Result<ValidationError,UserName>>map(userName -> success(new UserName(userName)))
                .orElseGet(() -> failure(new ValidationError("Invalid user name")));
    }
}
