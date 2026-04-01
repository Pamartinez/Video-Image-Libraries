package com.samsung.android.sum.core.evaluate;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sum.core.Def;
import java.lang.Comparable;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LessThan<T extends Comparable<T>> extends GenericEvaluator<T> {
    public static final Parcelable.Creator<LessThan<?>> CREATOR = new Parcelable.Creator<LessThan<?>>() {
        public LessThan<?> createFromParcel(Parcel parcel) {
            return new LessThan<>(parcel);
        }

        public LessThan<?>[] newArray(int i2) {
            return new LessThan[i2];
        }
    };
    private static final String TAG = Def.tagOf((Class<?>) LessThan.class);

    public LessThan(T t) {
        super(t);
    }

    public /* bridge */ /* synthetic */ Evaluator and(Evaluator evaluator) {
        return super.and(evaluator);
    }

    public /* bridge */ /* synthetic */ int compareTo(Evaluator evaluator) {
        return super.compareTo(evaluator);
    }

    public /* bridge */ /* synthetic */ int describeContents() {
        return super.describeContents();
    }

    public <V> boolean evaluate(V v) {
        if (((Comparable) v).compareTo(getValue(v.getClass())) < 0) {
            return true;
        }
        return false;
    }

    public /* bridge */ /* synthetic */ Comparable getValue() {
        return super.getValue();
    }

    public /* bridge */ /* synthetic */ Class getValueType() {
        return super.getValueType();
    }

    public /* bridge */ /* synthetic */ Evaluator or(Evaluator evaluator) {
        return super.or(evaluator);
    }

    public /* bridge */ /* synthetic */ Stream stream() {
        return super.stream();
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public /* bridge */ /* synthetic */ void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
    }

    public LessThan(Parcel parcel) {
        super(parcel);
    }
}
