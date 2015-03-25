package no.storebrand.presentations.functional;

import java.io.Serializable;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.Optional.ofNullable;

/**
 * @author OZY on 2014.12.16
 */

public class Triplet<T1, T2, T3> implements Serializable {

    public final T1 val1;
    public final T2 val2;
    public final T3 val3;

    public Triplet(final T1 val1, final T2 val2, final T3 val3) {
        this.val1 = val1;
        this.val2 = val2;
        this.val3 = val3;
    }

    public static <T1, T2, T3> Triplet<T1, T2, T3> of(final T1 val1, final T2 val2, final T3 val3) {
        return new Triplet<>(val1, val2, val3);
    }

    public Optional<T1> maybe1() {
        return ofNullable(val1);
    }

    public Optional<T2> maybe2() {
        return ofNullable(val2);
    }

    public Optional<T3> maybe3() {
        return ofNullable(val3);
    }

    public <R> R map(Function<Triplet<T1, T2, T3>, R> mapper) {
        return mapper.apply(this);
    }

    public Triplet<T1, T2, T3> update1(final T1 arg1) {
        return new Triplet<>(arg1, this.val2, this.val3);
    }

    public Triplet<T1, T2, T3> update2(final T2 arg2) {
        return new Triplet<>(this.val1, arg2, this.val3);
    }

    public Triplet<T1, T2, T3> update3(final T3 arg3) {
        return new Triplet<>(this.val1, this.val2, arg3);
    }

    public Stream<Object> stream() {
        return Stream.of(this.val1, this.val2, this.val3);
    }

    public Stream<Object> parallelStream() {
        return stream().parallel();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triplet triplet = (Triplet) o;
        return !(val1 != null ? !val1.equals(triplet.val1) : triplet.val1 != null) &&
               !(val2 != null ? !val2.equals(triplet.val2) : triplet.val2 != null) &&
               !(val3 != null ? !val3.equals(triplet.val3) : triplet.val3 != null);
    }

    @Override
    public int hashCode() {
        int result = val1 != null ? val1.hashCode() : 0;
        result = 31 * result + (val2 != null ? val2.hashCode() : 0);
        result = 31 * result + (val3 != null ? val3.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Triplet{" +
                "val1=" + val1 +
                ", val2=" + val2 +
                ", val3=" + val3 +
                '}';
    }
}
