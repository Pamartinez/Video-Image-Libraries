package com.samsung.android.sum.core.descriptor;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StapleDescriptor extends MFDescriptorBase {
    public static final Parcelable.Creator<StapleDescriptor> CREATOR = new Parcelable.Creator<StapleDescriptor>() {
        public StapleDescriptor createFromParcel(Parcel parcel) {
            return new StapleDescriptor(parcel);
        }

        public StapleDescriptor[] newArray(int i2) {
            return new StapleDescriptor[i2];
        }
    };

    public StapleDescriptor(MFDescriptorBuilder mFDescriptorBuilder) {
        super(mFDescriptorBuilder);
    }

    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
    }

    public StapleDescriptor(Parcel parcel) {
        super(parcel);
    }
}
