package com.samsung.android.sum.core.descriptor;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sum.core.Def;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StaplePluginDescriptor extends PluginDescriptor {
    public static final Parcelable.Creator<StaplePluginDescriptor> CREATOR = new Parcelable.Creator<StaplePluginDescriptor>() {
        public StaplePluginDescriptor createFromParcel(Parcel parcel) {
            return new StaplePluginDescriptor(parcel);
        }

        public StaplePluginDescriptor[] newArray(int i2) {
            return new StaplePluginDescriptor[i2];
        }
    };
    private static final String TAG = Def.tagOf((Class<?>) StaplePluginDescriptor.class);

    public StaplePluginDescriptor(MFDescriptorBuilder mFDescriptorBuilder) {
        super(mFDescriptorBuilder);
        String str = mFDescriptorBuilder.pluginClassName;
        if (str != null) {
            setPluginClassName(str);
        }
        Class<?> cls = mFDescriptorBuilder.pluginClass;
        if (cls != null) {
            setPluginClass(cls);
        }
    }

    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
    }

    public StaplePluginDescriptor(Parcel parcel) {
        super(parcel);
    }
}
