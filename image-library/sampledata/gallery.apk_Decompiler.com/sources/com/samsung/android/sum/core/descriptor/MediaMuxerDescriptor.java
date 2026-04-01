package com.samsung.android.sum.core.descriptor;

import Ad.C0720a;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sum.core.filter.MediaMuxerFilter;
import com.samsung.android.sum.core.types.MediaType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaMuxerDescriptor extends MFDescriptorBase {
    public static final Parcelable.Creator<MediaMuxerDescriptor> CREATOR = new Parcelable.Creator<MediaMuxerDescriptor>() {
        public MediaMuxerDescriptor createFromParcel(Parcel parcel) {
            return new MediaMuxerDescriptor(parcel);
        }

        public MediaMuxerDescriptor[] newArray(int i2) {
            return new MediaMuxerDescriptor[i2];
        }
    };
    private final int fileFormat;
    private List<MediaType> mediaTypes;
    private final Set<MediaType> mediaTypesToNotifyEvent;

    public MediaMuxerDescriptor(int i2) {
        this.mediaTypesToNotifyEvent = new HashSet();
        this.fileFormat = i2;
    }

    public boolean containsExtra(String str) {
        return this.extra.containsKey(str);
    }

    public int getFileFormat() {
        return this.fileFormat;
    }

    public String getFilterId() {
        return MediaMuxerFilter.class.getName();
    }

    public List<MediaType> getMediaTypes() {
        return this.mediaTypes;
    }

    public boolean isMediaTypeToNotifyEvent(MediaType mediaType) {
        return this.mediaTypesToNotifyEvent.contains(mediaType.rank());
    }

    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeInt(this.fileFormat);
        parcel.writeList(this.mediaTypes);
        parcel.writeMap(this.extra);
    }

    public MediaMuxerDescriptor(MFDescriptorBuilder mFDescriptorBuilder) {
        super(mFDescriptorBuilder);
        HashSet hashSet = new HashSet();
        this.mediaTypesToNotifyEvent = hashSet;
        this.fileFormat = mFDescriptorBuilder.fileFormat;
        this.mediaTypes = (List) Optional.ofNullable(mFDescriptorBuilder.mediaTypes).orElseGet(new C0720a(1));
        hashSet.addAll(mFDescriptorBuilder.mediaTypesToNotifyEvent);
        MediaType mediaType = mFDescriptorBuilder.mediaType;
        if (mediaType != null && !this.mediaTypes.contains(mediaType)) {
            this.mediaTypes.add(mFDescriptorBuilder.mediaType);
        }
        if (this.mediaTypes.isEmpty()) {
            this.mediaTypes.add(MediaType.RAW_VIDEO);
            this.mediaTypes.add(MediaType.RAW_AUDIO);
        }
    }

    public MediaMuxerDescriptor(Parcel parcel) {
        super(parcel);
        this.mediaTypesToNotifyEvent = new HashSet();
        this.fileFormat = parcel.readInt();
        ArrayList arrayList = new ArrayList();
        this.mediaTypes = arrayList;
        parcel.readList(arrayList, MediaType.class.getClassLoader(), MediaType.class);
        HashMap hashMap = new HashMap();
        this.extra = hashMap;
        parcel.readMap(hashMap, HashMap.class.getClassLoader(), String.class, Object.class);
    }
}
