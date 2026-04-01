package com.samsung.android.sum.core.descriptor;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.gallery.module.dynamicview.a;
import com.samsung.android.sum.core.filter.MediaParserFilter;
import com.samsung.android.sum.core.types.MediaType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaParserDescriptor extends MFDescriptorBase {
    public static final Parcelable.Creator<MediaParserDescriptor> CREATOR = new Parcelable.Creator<MediaParserDescriptor>() {
        public MediaParserDescriptor createFromParcel(Parcel parcel) {
            return new MediaParserDescriptor(parcel);
        }

        public MediaParserDescriptor[] newArray(int i2) {
            return new MediaParserDescriptor[i2];
        }
    };
    private long maxDurationUs;
    private List<MediaType> mediaTypeList;

    public MediaParserDescriptor(MediaType... mediaTypeArr) {
        this.mediaTypeList = (List) Arrays.stream(mediaTypeArr.length == 0 ? new MediaType[]{MediaType.COMPRESSED_AUDIO, MediaType.COMPRESSED_VIDEO} : mediaTypeArr).collect(Collectors.toList());
        this.extra = new HashMap();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$countToMuxerConfigure$0(MediaType mediaType) {
        if (mediaType.isAudio() || mediaType.isVideo()) {
            return true;
        }
        return false;
    }

    public boolean containsExtra(String str) {
        return this.extra.containsKey(str);
    }

    public int countToMuxerConfigure() {
        return (int) this.mediaTypeList.stream().filter(new a(7)).count();
    }

    public int countToParse() {
        return this.mediaTypeList.size();
    }

    public String getFilterId() {
        return MediaParserFilter.class.getName();
    }

    public long getMaxDurationUs() {
        return this.maxDurationUs;
    }

    public boolean isToParse(MediaType mediaType) {
        return this.mediaTypeList.contains(mediaType);
    }

    public void setMaxDurationUs(long j2) {
        this.maxDurationUs = j2;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeLong(this.maxDurationUs);
        parcel.writeList(this.mediaTypeList);
        parcel.writeMap(this.extra);
    }

    public MediaParserDescriptor(MFDescriptorBuilder mFDescriptorBuilder) {
        super(mFDescriptorBuilder);
        List<MediaType> list = mFDescriptorBuilder.mediaTypes;
        this.mediaTypeList = list;
        if (list == null) {
            this.mediaTypeList = new ArrayList();
        }
        if (this.mediaTypeList.isEmpty()) {
            this.mediaTypeList.add(MediaType.COMPRESSED_AUDIO);
            this.mediaTypeList.add(MediaType.COMPRESSED_VIDEO);
        }
    }

    public MediaParserDescriptor(Parcel parcel) {
        super(parcel);
        this.maxDurationUs = parcel.readLong();
        ArrayList arrayList = new ArrayList();
        this.mediaTypeList = arrayList;
        parcel.readList(arrayList, MediaType.class.getClassLoader(), MediaType.class);
        HashMap hashMap = new HashMap();
        this.extra = hashMap;
        parcel.readMap(hashMap, HashMap.class.getClassLoader(), String.class, Object.class);
    }
}
