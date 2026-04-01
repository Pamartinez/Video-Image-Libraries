package com.samsung.android.sum.core.evaluate;

import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class EvalNone implements Evaluator {
    public int compareTo(Evaluator evaluator) {
        return 0;
    }

    public <V> boolean evaluate(V v) {
        return true;
    }

    public <V> V getValue() {
        throw new UnsupportedOperationException("EvalNone doesn't support this!!!");
    }

    public Class<?> getValueType() {
        return null;
    }

    public Stream<Evaluator> stream() {
        return Stream.of(this);
    }

    public Evaluator and(Evaluator evaluator) {
        return evaluator;
    }

    public Evaluator or(Evaluator evaluator) {
        return this;
    }
}
