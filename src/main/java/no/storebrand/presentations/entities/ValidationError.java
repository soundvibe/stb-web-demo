package no.storebrand.presentations.entities;

/**
 * @author OZY on 2015.03.25
 */

public class ValidationError {

    private final String value;

    public ValidationError(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
