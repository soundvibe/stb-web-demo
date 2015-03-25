package no.storebrand.presentations.functional;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author OZY on 2015.01.27
 */

public class Success<F,T> extends Result<F,T> {

    protected Success(T value) {
        super(value);
        Objects.requireNonNull(value, "Success cannot be null");
    }

    @SuppressWarnings("unchecked")
    @Override
    public <R> Result<F,R> map(Function<? super T, R> mapper) {
        return map(mapper, () -> new Failure<>(null, (F) new RuntimeException("mapped value cannot be null")));
    }

    @Override
    public <R> Result<F, R> map(Function<? super T, R> mapper, Supplier<Failure<F, R>> failureSupplier) {
        R mappedValue = mapper.apply(value);
        return mappedValue != null? success(mappedValue): failureSupplier.get();
    }

    @Override
    public <R> Result<F,R> flatMap(Function<? super T, Result<F,R>> mapper) {
        return mapper.apply(value);
    }

    @Override
    public boolean isSuccess() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get() {
        return value;
    }

}
