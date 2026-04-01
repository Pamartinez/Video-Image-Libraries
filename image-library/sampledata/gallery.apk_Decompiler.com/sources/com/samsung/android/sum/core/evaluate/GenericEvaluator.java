package com.samsung.android.sum.core.evaluate;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sum.core.Def;
import java.lang.Comparable;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class GenericEvaluator<T extends Comparable<T>> implements Evaluator, Parcelable {
    private static final String TAG = Def.tagOf((Class<?>) GenericEvaluator.class);
    T value;

    public GenericEvaluator(T t) {
        this.value = t;
    }

    public Evaluator and(Evaluator evaluator) {
        if (evaluator instanceof AndEvaluatorGroup) {
            return ((AndEvaluatorGroup) evaluator).add(this);
        }
        return new AndEvaluatorGroup(this, evaluator);
    }

    public int describeContents() {
        return 0;
    }

    public Class<?> getValueType() {
        return this.value.getClass();
    }

    public Evaluator or(Evaluator evaluator) {
        if (evaluator instanceof OrEvaluatorGroup) {
            return ((OrEvaluatorGroup) evaluator).add(this);
        }
        return new OrEvaluatorGroup(this, evaluator);
    }

    public Stream<Evaluator> stream() {
        return Stream.of(this);
    }

    public String toString() {
        return getClass().getSimpleName() + " : " + this.value;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.value.getClass().getName());
        parcel.writeValue(this.value);
    }

    public int compareTo(Evaluator evaluator) {
        if (evaluator instanceof GenericEvaluator) {
            return getValue().compareTo((Comparable) evaluator.getValue());
        }
        EvaluatorGroup evaluatorGroup = (EvaluatorGroup) evaluator;
        evaluatorGroup.sort();
        return getValue().compareTo(evaluatorGroup.front());
    }

    public T getValue() {
        return this.value;
    }

    public GenericEvaluator(Parcel parcel) {
        try {
            this.value = (Comparable) parcel.readValue(Class.forName(parcel.readString()).getClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            this.value = null;
            throw new IllegalArgumentException();
        }
    }

    public <V> V getValue(Class<V> cls) {
        if (!(this.value instanceof Number)) {
            throw new UnsupportedOperationException("not implemented except number type");
        } else if (Float.class.isAssignableFrom(cls)) {
            return cls.cast(Float.valueOf(((Number) this.value).floatValue()));
        } else {
            if (Integer.class.isAssignableFrom(cls)) {
                return cls.cast(Integer.valueOf(((Number) this.value).intValue()));
            }
            if (Long.class.isAssignableFrom(cls)) {
                return cls.cast(Long.valueOf(((Number) this.value).longValue()));
            }
            if (Double.class.isAssignableFrom(cls)) {
                return cls.cast(Double.valueOf(((Number) this.value).doubleValue()));
            }
            if (Byte.class.isAssignableFrom(cls)) {
                return cls.cast(Byte.valueOf(((Number) this.value).byteValue()));
            }
            if (Short.class.isAssignableFrom(cls)) {
                return cls.cast(Short.valueOf(((Number) this.value).shortValue()));
            }
            throw new IllegalStateException("unknown number type");
        }
    }
}
