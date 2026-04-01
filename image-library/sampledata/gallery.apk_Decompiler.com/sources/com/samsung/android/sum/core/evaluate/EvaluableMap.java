package com.samsung.android.sum.core.evaluate;

import com.samsung.android.sum.core.channel.h;
import java.util.Map;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class EvaluableMap<T> implements Evaluator {
    private final Map<Evaluator, T> data;

    public EvaluableMap(Map<Evaluator, T> map) {
        this.data = map;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$get$1(Evaluator evaluator) {
        return this.data.get(evaluator);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$getOr$3(Evaluator evaluator) {
        return this.data.get(evaluator);
    }

    public Evaluator and(Evaluator evaluator) {
        return null;
    }

    public int compareTo(Evaluator evaluator) {
        return 0;
    }

    public <V> boolean evaluate(V v) {
        return false;
    }

    public <V> T get(V v) {
        return this.data.keySet().stream().filter(new a(2, v)).findFirst().map(new b(this, 0)).orElseThrow(new h(0));
    }

    public <V> T getOr(V v, T t) {
        return this.data.keySet().stream().filter(new a(3, v)).findFirst().map(new b(this, 1)).orElse(t);
    }

    public <V> V getValue() {
        throw new UnsupportedOperationException("EvaluableMap doesn't support this!!!");
    }

    public Class<?> getValueType() {
        return null;
    }

    public Evaluator or(Evaluator evaluator) {
        return null;
    }

    public Stream<Evaluator> stream() {
        return null;
    }
}
