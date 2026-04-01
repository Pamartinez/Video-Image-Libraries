package com.samsung.android.sum.core.evaluate;

import android.os.Parcel;
import android.os.Parcelable;
import java.lang.Comparable;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class NotEqual<T extends Comparable<T>> extends GenericEvaluator<T> {
    public static final Parcelable.Creator<NotEqual<?>> CREATOR = new Parcelable.Creator<NotEqual<?>>() {
        public NotEqual<?> createFromParcel(Parcel parcel) {
            return new NotEqual<>(parcel);
        }

        public NotEqual<?>[] newArray(int i2) {
            return new NotEqual[i2];
        }
    };

    public NotEqual(T t) {
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
        if (getValue() != v) {
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

    public NotEqual(Parcel parcel) {
        super(parcel);
    }
}
