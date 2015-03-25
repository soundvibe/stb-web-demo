package no.storebrand.presentations.usecases;

import no.storebrand.presentations.entities.Notification;
import no.storebrand.presentations.entities.UserName;
import no.storebrand.presentations.entities.ValidationError;
import no.storebrand.presentations.functional.Result;

import java.util.Optional;

import static no.storebrand.presentations.functional.Result.failure;
import static no.storebrand.presentations.functional.Result.success;

/**
 * @author OZY on 2015.03.25
 */

public final class UserNameValidator {

    public static Result<ValidationError,UserName> validate(String candidate) {
        return Optional.ofNullable(candidate)
                .filter(username -> !username.isEmpty())
                .map(String::trim)
                .map(username -> username.replace(" ", ""))
                .map(String::toUpperCase)
                .map(UserName::new)
                .<Result<ValidationError,UserName>>map(userName -> success(userName))
                .orElseGet(() -> failure(new ValidationError("Invalid user name")));
    }

    // http://martinfowler.com/articles/replaceThrowWithNotification.html
    public static Notification validateUserName(String candidate) {
        Notification notification = new Notification();

        //TODO implement validation

        return notification;
    }
}
