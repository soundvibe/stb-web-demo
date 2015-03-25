package no.storebrand.presentations.functional;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author OZY on 2015.01.27
 */

public class Failure<F,T> extends Result<F,T> {

    protected Failure(T value, F failedValue) {
        super(value, failedValue);
        Objects.requireNonNull(failedValue, "Failure cannot be null");
    }

    @Override
    public <R> Result<F,R> map(Function<? super T, R> mapper) {
        return new Failure<>(value != null ? mapper.apply(value): null, failedValue);
    }

    @Override
    public <R> Result<F, R> map(Function<? super T, R> mapper, Supplier<Failure<F, R>> failureSupplier) {
        return map(mapper);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <R> Result<F,R> flatMap(Function<? super T, Result<F,R>> mapper) {
        return new Failure<>((R) this.value, failedValue);
    }

    @Override
    public boolean isSuccess() {
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public F get() {
        return failedValue;
    }

    @Override
    public T getSuccess() {
        if (value != null) return value;
        return super.getSuccess();
    }
}
