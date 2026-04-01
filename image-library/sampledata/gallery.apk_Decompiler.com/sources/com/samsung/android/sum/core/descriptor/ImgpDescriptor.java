package com.samsung.android.sum.core.descriptor;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.format.MediaFormat;
import com.samsung.android.sum.core.plugin.ImgpPlugin;
import com.samsung.android.sum.core.types.ImgpType;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ImgpDescriptor extends PluginDescriptor {
    public static final Parcelable.Creator<ImgpDescriptor> CREATOR = new Parcelable.Creator<ImgpDescriptor>() {
        public ImgpDescriptor createFromParcel(Parcel parcel) {
            return new ImgpDescriptor(parcel);
        }

        public ImgpDescriptor[] newArray(int i2) {
            return new ImgpDescriptor[i2];
        }
    };
    private static final String TAG = Def.tagOf((Class<?>) ImgpDescriptor.class);
    private Enum<?> imgpType;
    private final String imgpTypeName;

    public ImgpDescriptor(MFDescriptorBuilder mFDescriptorBuilder) {
        super(mFDescriptorBuilder);
        String str = mFDescriptorBuilder.pluginClassName;
        if (str != null) {
            setPluginClassName(str);
            if (mFDescriptorBuilder.imgpPluginType == null) {
                mFDescriptorBuilder.imgpPluginType = ImgpPlugin.Type.CUSTOM;
            }
        }
        Class<?> cls = mFDescriptorBuilder.pluginClass;
        if (cls != null) {
            setPluginClass(cls);
            if (mFDescriptorBuilder.imgpPluginType == null) {
                mFDescriptorBuilder.imgpPluginType = ImgpPlugin.Type.CUSTOM;
            }
        }
        MediaFormat mediaFormat = mFDescriptorBuilder.targetFormat;
        if (mediaFormat != null) {
            setTargetFormat(mediaFormat.toMutableFormat());
        }
        setPluginId((ImgpPlugin.Type) Optional.ofNullable(mFDescriptorBuilder.imgpPluginType).orElse(ImgpPlugin.Type.ANY));
        this.imgpType = (Enum) Optional.ofNullable(mFDescriptorBuilder.imgpType).orElse(ImgpType.ANY);
        this.imgpTypeName = mFDescriptorBuilder.imgpTypeName;
    }

    public <T extends Enum<?>> T getImgpType() {
        return this.imgpType;
    }

    public String getImgpTypeName() {
        return this.imgpTypeName;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeSerializable(this.imgpType);
        parcel.writeString(this.imgpTypeName);
    }

    public ImgpDescriptor(Parcel parcel) {
        super(parcel);
        this.imgpType = (Enum) parcel.readSerializable();
        this.imgpTypeName = parcel.readString();
    }
}
