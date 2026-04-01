package com.samsung.android.sum.core.descriptor;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sum.core.filter.DecorateFilter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DecorateDescriptor extends MFDescriptorBase {
    public static final Parcelable.Creator<DecorateDescriptor> CREATOR = new Parcelable.Creator<DecorateDescriptor>() {
        public DecorateDescriptor createFromParcel(Parcel parcel) {
            return new DecorateDescriptor(parcel);
        }

        public DecorateDescriptor[] newArray(int i2) {
            return new DecorateDescriptor[i2];
        }
    };
    private final MFDescriptor successor;

    public DecorateDescriptor(MFDescriptor mFDescriptor) {
        this.successor = mFDescriptor;
    }

    public Class<?> getFilterType() {
        return DecorateFilter.class;
    }

    public MFDescriptor getSuccessor() {
        return this.successor;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeParcelable(this.successor, i2);
    }

    public DecorateDescriptor(MFDescriptorBuilder mFDescriptorBuilder) {
        super(mFDescriptorBuilder);
        this.successor = mFDescriptorBuilder.successorDescriptor;
    }

    public DecorateDescriptor(Parcel parcel) {
        super(parcel);
        this.successor = (MFDescriptor) parcel.readParcelable(MFDescriptor.class.getClassLoader(), MFDescriptor.class);
    }
}
