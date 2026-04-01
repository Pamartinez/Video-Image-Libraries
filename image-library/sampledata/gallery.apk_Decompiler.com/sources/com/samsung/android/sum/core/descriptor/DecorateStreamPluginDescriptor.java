package com.samsung.android.sum.core.descriptor;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.buffer.u;
import com.samsung.android.sum.core.types.MediaType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DecorateStreamPluginDescriptor extends PluginDescriptor {
    public static final Parcelable.Creator<DecorateStreamPluginDescriptor> CREATOR = new Parcelable.Creator<DecorateStreamPluginDescriptor>() {
        public DecorateStreamPluginDescriptor createFromParcel(Parcel parcel) {
            return new DecorateStreamPluginDescriptor(parcel);
        }

        public DecorateStreamPluginDescriptor[] newArray(int i2) {
            return new DecorateStreamPluginDescriptor[i2];
        }
    };
    private static final String TAG = Def.tagOf((Class<?>) DecorateStreamPluginDescriptor.class);
    private Map<String, Object> extra;
    private List<MediaType> mediaTypeList;
    private final String mimeType;
    private MFDescriptor successorDescriptor;

    public DecorateStreamPluginDescriptor(MFDescriptorBuilder mFDescriptorBuilder) {
        super(mFDescriptorBuilder);
        List<MediaType> list = mFDescriptorBuilder.mediaTypes;
        this.mediaTypeList = list;
        if (list == null) {
            this.mediaTypeList = new ArrayList();
        }
        if (this.mediaTypeList.isEmpty()) {
            MediaType mediaType = mFDescriptorBuilder.mediaType;
            if (mediaType != null) {
                this.mediaTypeList.add(mediaType);
            } else {
                SLog.w(TAG, "missing media-type in DecorateStreamPluginDescriptor");
                this.mediaTypeList.add(MediaType.NONE);
            }
        }
        this.mimeType = (String) Optional.ofNullable(mFDescriptorBuilder.mimeType).orElse("n/a");
        this.extra = (Map) Optional.ofNullable(mFDescriptorBuilder.extra).orElseGet(new u(0));
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
    }

    public <T> T getExtra(String str) {
        return this.extra.get(str);
    }

    public List<MediaType> getMediaTypeList() {
        return this.mediaTypeList;
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public MFDescriptor getSuccessorDescriptor() {
        return this.successorDescriptor;
    }

    public <T> void setExtra(String str, T t) {
        this.extra.put(str, t);
    }

    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeList(this.mediaTypeList);
        parcel.writeString(this.mimeType);
        parcel.writeMap(this.extra);
        parcel.writeParcelable(this.successorDescriptor, i2);
    }

    public DecorateStreamPluginDescriptor(Parcel parcel) {
        super(parcel);
        ArrayList arrayList = new ArrayList();
        this.mediaTypeList = arrayList;
        parcel.readList(arrayList, MediaType.class.getClassLoader(), MediaType.class);
        this.mimeType = parcel.readString();
        parcel.readMap(this.extra, HashMap.class.getClassLoader(), String.class, Object.class);
        this.successorDescriptor = (MFDescriptor) parcel.readParcelable(MFDescriptor.class.getClassLoader(), MFDescriptor.class);
    }
}
