package no.storebrand.presentations.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author OZY on 2015.03.24
 */

public class Notification {

    private final List<Error> errors = new ArrayList<>();

    public void addError(String message) {
        errors.add(new Error(message, Optional.empty()));
    }

    public void addError(String message, Exception cause) {
        errors.add(new Error(message, Optional.of(cause)));
    }

    private static class Error {
        private final String message;
        private final Optional<Exception> cause;

        private Error(String message, Optional<Exception> cause) {
            this.message = message;
            this.cause = cause;
        }
    }
}
