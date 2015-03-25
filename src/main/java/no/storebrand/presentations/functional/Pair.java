package no.storebrand.presentations.functional;

import java.io.Serializable;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.Optional.ofNullable;

/**
 * @author OZY on 2014.12.16
 */

public class Pair<T1, T2> implements Serializable {

    public final T1 val1;
    public final T2 val2;

    public Pair(final T1 val1, final T2 val2) {
        this.val1 = val1;
        this.val2 = val2;
    }

    public static <T1, T2> Pair<T1, T2> of(final T1 val1, final T2 val2) {
        return new Pair<>(val1, val2);
    }

    public Optional<T1> maybe1() {
        return ofNullable(val1);
    }

    public Optional<T2> maybe2() {
        return ofNullable(val2);
    }

    public <R> R map(Function<Pair<T1, T2>, R> mapper) {
        return mapper.apply(this);
    }

    public <T3> Triplet<T1, T2, T3> add(final T3 val3) {
        return new Triplet<>(this.val1, this.val2, val3);
    }

    public Pair<T1,T2> update1(final T1 arg1) {
        return new Pair<>(arg1, this.val2);
    }

    public Pair<T1,T2> update2(final T2 arg2) {
        return new Pair<>(this.val1, arg2);
    }

    public Stream<Object> stream() {
        return Stream.of(this.val1, this.val2);
    }

    public Stream<Object> parallelStream() {
        return stream().parallel();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return !(val1 != null ? !val1.equals(pair.val1) : pair.val1 != null) &&
               !(val2 != null ? !val2.equals(pair.val2) : pair.val2 != null);

    }

    @Override
    public int hashCode() {
        int result = val1 != null ? val1.hashCode() : 0;
        result = 31 * result + (val2 != null ? val2.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "val1=" + val1 +
                ", val2=" + val2 +
                '}';
    }

}
