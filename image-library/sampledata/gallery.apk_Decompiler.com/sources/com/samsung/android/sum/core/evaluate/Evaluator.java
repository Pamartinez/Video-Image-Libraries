package com.samsung.android.sum.core.evaluate;

import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface Evaluator extends Comparable<Evaluator> {
    static <T extends Comparable<T>> Evaluator eq(T t) {
        return new Equal(t);
    }

    static <T extends Comparable<T>> Evaluator ge(T t) {
        return new GreaterEqual(t);
    }

    static <T extends Comparable<T>> Evaluator gt(T t) {
        return new GreaterThan(t);
    }

    static <T extends Comparable<T>> Evaluator le(T t) {
        return new LessEqual(t);
    }

    static <T extends Comparable<T>> Evaluator lt(T t) {
        return new LessThan(t);
    }

    static <T extends Comparable<T>> Evaluator ne(T t) {
        return new NotEqual(t);
    }

    Evaluator and(Evaluator evaluator);

    <V> boolean evaluate(V v);

    <V> V getValue();

    Class<?> getValueType();

    Evaluator or(Evaluator evaluator);

    Stream<Evaluator> stream();
}
