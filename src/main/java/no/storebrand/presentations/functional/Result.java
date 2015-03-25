package no.storebrand.presentations.functional;

import java.util.Objects;
import java.util.Optional;
import java.util.function.*;

/**
 * @author OZY on 2015.01.27
 */

/**
 * The Result type represents a computation that may either result in an exception, or return a successfully computed value.
 * It's similar to, but semantically different from the Either type.
 Instances of Result are either an instance of Success[T] or Failure[F].
 For example, Result can be used to perform division on a user-defined input, without the need to do explicit
 exception-handling in all of the places that an exception might occur.
 * @param <T> type of wrapped value
 */
public abstract class Result<F, T> {

    protected final T value;

    protected final F failedValue;

    protected Result(final T value) {
        this.value = value;
        this.failedValue = null;
    }

    protected Result(T value, F failedValue) {
        this.value = value;
        this.failedValue = failedValue;
    }


    /**
     * Maps the given function to the value from this Success or returns this if this is a Failure.
     */
    public abstract <R> Result<F,R> map(Function<? super T, R> mapper);

    public abstract <R> Result<F,R> map(Function<? super T, R> mapper, Supplier<Failure<F,R>> failureSupplier);


    /**
     * Returns the given function applied to the value from this Success or returns this if this is a Failure.
     */
    public abstract <R> Result<F,R> flatMap(Function<? super T, Result<F,R>> mapper);

    public <R> Result<F,R> tryFlatMap(Function<? super T, Result<F,R>> mapper, Function<Throwable,Failure<F,R>> failureSupplier) {
        try {
            return flatMap(mapper);
        } catch (Throwable e) {
            return failureSupplier.apply(e);
        }
    }

    public <R> Result<F,R> tryFlatMap(Function<? super T, Result<F,R>> mapper, BiFunction<Throwable,T,Failure<F,R>> failureSupplier) {
        try {
            return flatMap(mapper);
        } catch (Throwable e) {
            return failureSupplier.apply(e, value);
        }
    }

    public abstract boolean isSuccess();

    public boolean isFailure() {
        return !isSuccess();
    }

    public Result<F, T> ifSuccess(Consumer<T> consumer) {
        if (isSuccess()) {
            consumer.accept(value);
        }
        return this;
    }

    public Result<F,T> ifFailure(Consumer<F> consumer) {
        if (!isSuccess()) {
            consumer.accept(failedValue);
        }
        return this;
    }

    public Result<F,T> ifFailure(BiConsumer<F, T> consumer) {
        if (!isSuccess()) {
            Objects.requireNonNull(value, "Success value cannot be null");
            consumer.accept(failedValue, value);
        }
        return this;
    }

    public <X extends Throwable> void ifFailureThrow(Function<F, X> consumer) throws X {
        if (!isSuccess()) {
           throw consumer.apply(failedValue);
        }
    }

    public abstract <R> R get();

    public T getSuccess() {
        if (!isSuccess()) throw new IllegalStateException("Cannot get Success because it is a Failure");
        return value;
    }

    public F getFailure() {
        if (isSuccess()) throw new IllegalStateException("Cannot get Failure because it is a Success");
        return failedValue;
    }

    /**
     * Applies the given function f if this is a Success.
     */
    public void forEach(Consumer<T> consumer) {
        ifSuccess(consumer);
    }

    /**
     * Returns the value from this Success or the given other argument if this is a Failure.
     */
    public T orElse(T other) {
        return isSuccess() ? value : other;
    }

    /**
     * Returns the value from this Success or the given other argument if this is a Failure.
     */
    public T orElseGet(Supplier<? extends T> other) {
        return isSuccess() ? value : other.get();
    }

    public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
        if (isSuccess()) {
            return value;
        } else {
            throw exceptionSupplier.get();
        }
    }

    /**
     * Returns Empty if this is a Failure or a Some containing the value if this is a Success.
     */
    public Optional<T> toOptional() {
        return isSuccess() ? Optional.of(value) : Optional.empty();
    }

    public static <F,T> Result<F,T> success(T value) {
        return new Success<>(value);
    }

    public static <F,T> Result<F,T> failure(F failedValue) {
        return new Failure<>(null, failedValue);
    }
    public static <F,T> Result<F,T> failure(F failedValue, T value) {
        return new Failure<>(value, failedValue);
    }

    public static <F,T> Result<F,T> _if(T arg, Predicate<T> predicate, Function<T,F> failureFunction) {
        return predicate.test(arg) ? success(arg) : failure(failureFunction.apply(arg));
    }

    public static <F,T> Result<F,T> _if(BooleanSupplier predicate, Supplier<T> successSupplier, Supplier<F> failureSupplier) {
        return predicate.getAsBoolean() ? success(successSupplier.get()) : failure(failureSupplier.get());
    }

    public static <F,T> Result<F,T> _try(Supplier<Result<F,T>> resultSupplier, Function<Throwable, Failure<F,T>> failureSupplier) {
        try {
            return resultSupplier.get();
        } catch (Throwable e) {
            return failureSupplier.apply(e);
        }
    }

    public static <F,T> Result<F,T> _try1(Supplier<Result<F,T>> resultSupplier, Function<Throwable,F> failureSupplier) {
        try {
            return resultSupplier.get();
        } catch (Throwable e) {
            return failure(failureSupplier.apply(e));
        }
    }

    public static <F,T> Result<F,T> _try2(Supplier<Result<F,T>> resultSupplier, Function<Throwable,Pair<F, T>> failureSupplier) {
        try {
            return resultSupplier.get();
        } catch (Throwable e) {
            Pair<F, T> pair = failureSupplier.apply(e);
            return failure(pair.val1, pair.val2);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return !(failedValue != null ? !failedValue.equals(result.failedValue) : result.failedValue != null) &&
                !(value != null ? !value.equals(result.value) : result.value != null);
    }

    @Override
    public int hashCode() {
        int result = value != null ? value.hashCode() : 0;
        result = 23 * result + (failedValue != null ? failedValue.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("%s{value=%s}", isSuccess()? "Success": "Failure", isSuccess() ? value : failedValue);
    }
}
