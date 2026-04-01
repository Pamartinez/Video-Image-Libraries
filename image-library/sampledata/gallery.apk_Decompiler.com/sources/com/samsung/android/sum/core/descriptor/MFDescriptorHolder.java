package com.samsung.android.sum.core.descriptor;

import J5.c;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.motionphoto.utils.ex.b;
import com.samsung.android.sum.core.descriptor.MFDescriptor;
import com.samsung.android.sum.core.functional.PlaceHolder;
import java.util.Optional;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MFDescriptorHolder<T extends MFDescriptor> extends MFDescriptorBase implements PlaceHolder<T> {
    public static final Parcelable.Creator<MFDescriptorHolder> CREATOR = new Parcelable.Creator<MFDescriptorHolder>() {
        public MFDescriptorHolder createFromParcel(Parcel parcel) {
            return new MFDescriptorHolder(parcel);
        }

        public MFDescriptorHolder[] newArray(int i2) {
            return new MFDescriptorHolder[i2];
        }
    };
    T descriptor;
    Function<Object[], MFDescriptor> mfDescriptorProvider;
    Object[] vararg;

    public MFDescriptorHolder(MFDescriptor mFDescriptor, Object... objArr) {
        this.vararg = objArr;
        this.descriptor = mFDescriptor;
        setFilterId(mFDescriptor.getFilterId());
        setFilterType(mFDescriptor.getFilterType());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ MFDescriptor lambda$reset$0(Function function) {
        return (MFDescriptor) function.apply(this.vararg);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ MFDescriptor lambda$reset$1() {
        return (MFDescriptor) Optional.ofNullable(this.mfDescriptorProvider).map(new b(4, this)).orElse((Object) null);
    }

    public Object[] getParameters() {
        return this.vararg;
    }

    public boolean isEmpty() {
        if (this.descriptor == null) {
            return true;
        }
        return false;
    }

    public boolean isNotEmpty() {
        if (this.descriptor != null) {
            return true;
        }
        return false;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeParcelable(this.descriptor, i2);
        parcel.writeArray(this.vararg);
    }

    public void put(T t) {
        this.descriptor = t;
    }

    public T reset() {
        T t = (MFDescriptor) Optional.ofNullable(this.descriptor).orElseGet(new c(16, this));
        this.vararg = null;
        this.descriptor = null;
        this.mfDescriptorProvider = null;
        if (t != null) {
            copyTo((MFDescriptorBase) t);
        }
        return t;
    }

    public MFDescriptorHolder(Function<Object[], MFDescriptor> function, Object... objArr) {
        this.vararg = objArr;
        this.mfDescriptorProvider = function;
    }

    public MFDescriptorHolder(Parcel parcel) {
        super(parcel);
        this.descriptor = (MFDescriptor) parcel.readParcelable(MFDescriptor.class.getClassLoader(), MFDescriptor.class);
        this.vararg = parcel.readArray(Object[].class.getClassLoader(), Object[].class);
    }
}
