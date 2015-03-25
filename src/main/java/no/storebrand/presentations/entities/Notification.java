package no.storebrand.presentations.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

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

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public String errorMessage() {
        return errors.stream()
                .map(error -> error.message)
                .collect(joining(", ", "Errors: [", "]"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification that = (Notification) o;
        return Objects.equals(errors, that.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errors);
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
