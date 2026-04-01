package com.samsung.android.sum.core.descriptor;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sum.core.Def;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DecoratePluginDescriptor extends PluginDescriptor {
    public static final Parcelable.Creator<DecoratePluginDescriptor> CREATOR = new Parcelable.Creator<DecoratePluginDescriptor>() {
        public DecoratePluginDescriptor createFromParcel(Parcel parcel) {
            return new DecoratePluginDescriptor(parcel);
        }

        public DecoratePluginDescriptor[] newArray(int i2) {
            return new DecoratePluginDescriptor[i2];
        }
    };
    private static final String TAG = Def.tagOf((Class<?>) DecoratePluginDescriptor.class);
    private MFDescriptor successorDescriptor;

    public DecoratePluginDescriptor(MFDescriptorBuilder mFDescriptorBuilder) {
        super(mFDescriptorBuilder);
        String str = mFDescriptorBuilder.pluginClassName;
        if (str != null) {
            setPluginClassName(str);
        }
        Class<?> cls = mFDescriptorBuilder.pluginClass;
        if (cls != null) {
            setPluginClass(cls);
        }
        MFDescriptor mFDescriptor = mFDescriptorBuilder.successorDescriptor;
        if (mFDescriptor != null) {
            this.successorDescriptor = mFDescriptor;
        }
        this.operatorName = mFDescriptorBuilder.operatorName;
    }

    public MFDescriptor getSuccessorDescriptor() {
        return this.successorDescriptor;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeParcelable(this.successorDescriptor, i2);
    }

    public DecoratePluginDescriptor(Parcel parcel) {
        super(parcel);
        this.successorDescriptor = (MFDescriptor) parcel.readParcelable(MFDescriptor.class.getClassLoader(), MFDescriptor.class);
    }
}
