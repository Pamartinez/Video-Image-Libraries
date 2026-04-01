package com.samsung.android.sum.core.evaluate;

import c0.C0086a;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class EvaluatorGroup implements Evaluator {
    private List<Evaluator> evaluators;
    private volatile boolean sorted = false;

    public EvaluatorGroup(Evaluator... evaluatorArr) {
        this.evaluators = Arrays.asList(evaluatorArr);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$toString$0(Evaluator evaluator) {
        return "[" + evaluator + "]";
    }

    public EvaluatorGroup add(Evaluator evaluator) {
        this.evaluators.add(evaluator);
        this.sorted = false;
        return this;
    }

    public Evaluator and(Evaluator evaluator) {
        if (this instanceof AndEvaluatorGroup) {
            return add(evaluator);
        }
        if (evaluator instanceof AndEvaluatorGroup) {
            return ((AndEvaluatorGroup) evaluator).add(this);
        }
        return new AndEvaluatorGroup(this, evaluator);
    }

    public <T extends Comparable> T back() {
        sort();
        Evaluator evaluator = (Evaluator) C0086a.f(1, this.evaluators);
        if (evaluator instanceof GenericEvaluator) {
            return ((GenericEvaluator) evaluator).getValue();
        }
        return ((EvaluatorGroup) evaluator).back();
    }

    public <T extends Comparable> T front() {
        sort();
        Evaluator evaluator = this.evaluators.get(0);
        if (evaluator instanceof GenericEvaluator) {
            return ((GenericEvaluator) evaluator).getValue();
        }
        return ((EvaluatorGroup) evaluator).front();
    }

    public List<Evaluator> getEvaluators() {
        return this.evaluators;
    }

    public <V> V getValue() {
        throw new UnsupportedOperationException("EvaluatorGroup doesn't support this!!!");
    }

    public Class<?> getValueType() {
        return (Class) this.evaluators.stream().findFirst().map(new c(1)).orElse((Object) null);
    }

    public Evaluator or(Evaluator evaluator) {
        if (this instanceof OrEvaluatorGroup) {
            return add(evaluator);
        }
        if (evaluator instanceof OrEvaluatorGroup) {
            return ((OrEvaluatorGroup) evaluator).add(this);
        }
        return new OrEvaluatorGroup(this, evaluator);
    }

    public EvaluatorGroup remove(Evaluator evaluator) {
        this.evaluators.remove(evaluator);
        return this;
    }

    public void sort() {
        if (!this.sorted) {
            this.evaluators = (List) stream().sorted().collect(Collectors.toList());
            this.sorted = true;
        }
    }

    public Stream<Evaluator> stream() {
        return this.evaluators.stream();
    }

    public String toString() {
        String str;
        if (this instanceof OrEvaluatorGroup) {
            str = " or ";
        } else {
            str = " and ";
        }
        return (String) this.evaluators.stream().map(new c(0)).collect(Collectors.joining(str));
    }

    public int compareTo(Evaluator evaluator) {
        if (evaluator instanceof GenericEvaluator) {
            return front().compareTo(((GenericEvaluator) evaluator).getValue());
        }
        EvaluatorGroup evaluatorGroup = (EvaluatorGroup) evaluator;
        evaluatorGroup.sort();
        return front().compareTo(evaluatorGroup.front());
    }
}
