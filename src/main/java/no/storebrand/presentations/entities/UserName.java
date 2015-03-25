package no.storebrand.presentations.entities;

/**
 * @author OZY on 2015.03.25
 */

public class UserName {

    private final String value;

    public UserName(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserName userName = (UserName) o;
        return !(value != null ? !value.equals(userName.value) : userName.value != null);
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
