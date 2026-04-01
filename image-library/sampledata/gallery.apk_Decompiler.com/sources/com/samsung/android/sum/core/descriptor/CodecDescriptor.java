package com.samsung.android.sum.core.descriptor;

import Ad.C0720a;
import android.media.MediaFormat;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Pair;
import android.view.Surface;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.filter.DecoderFilter;
import com.samsung.android.sum.core.filter.EncoderFilter;
import com.samsung.android.sum.core.types.MediaType;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CodecDescriptor extends MFDescriptorBase {
    public static final Parcelable.Creator<CodecDescriptor> CREATOR = new Parcelable.Creator<CodecDescriptor>() {
        public CodecDescriptor createFromParcel(Parcel parcel) {
            return new CodecDescriptor(parcel);
        }

        public CodecDescriptor[] newArray(int i2) {
            return new CodecDescriptor[i2];
        }
    };
    private static final String TAG = Def.tagOf((Class<?>) CodecDescriptor.class);
    private int align;
    private int bitrate;
    private float cropRectRatio = 0.0f;
    private final Pair<Integer, Integer> dimension;
    private MediaFormat mediaFormat;
    private final MediaType mediaType;
    private final String mimeType;
    private float scale;
    private Surface surface;

    /* renamed from: com.samsung.android.sum.core.descriptor.CodecDescriptor$2  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$sum$core$types$MediaType;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.samsung.android.sum.core.types.MediaType[] r0 = com.samsung.android.sum.core.types.MediaType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$sum$core$types$MediaType = r0
                com.samsung.android.sum.core.types.MediaType r1 = com.samsung.android.sum.core.types.MediaType.COMPRESSED_AUDIO     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$types$MediaType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.sum.core.types.MediaType r1 = com.samsung.android.sum.core.types.MediaType.COMPRESSED_VIDEO     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$types$MediaType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.android.sum.core.types.MediaType r1 = com.samsung.android.sum.core.types.MediaType.RAW_AUDIO     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$types$MediaType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.samsung.android.sum.core.types.MediaType r1 = com.samsung.android.sum.core.types.MediaType.RAW_VIDEO     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sum.core.descriptor.CodecDescriptor.AnonymousClass2.<clinit>():void");
        }
    }

    public CodecDescriptor(MFDescriptorBuilder mFDescriptorBuilder) {
        super(mFDescriptorBuilder);
        MediaType mediaType2 = mFDescriptorBuilder.mediaType;
        Objects.requireNonNull(mediaType2);
        this.mediaType = mediaType2;
        int i2 = AnonymousClass2.$SwitchMap$com$samsung$android$sum$core$types$MediaType[mediaType2.ordinal()];
        if (i2 == 1 || i2 == 2) {
            setFilterType(DecoderFilter.class);
        } else if (i2 == 3 || i2 == 4) {
            setFilterType(EncoderFilter.class);
        } else {
            throw new IllegalArgumentException("invalid media-type(" + mediaType2 + ") for codec");
        }
        this.mimeType = (String) Optional.ofNullable(mFDescriptorBuilder.mimeType).orElse("n/a");
        this.mediaFormat = mFDescriptorBuilder.androidMediaFormat;
        this.surface = mFDescriptorBuilder.surface;
        this.dimension = (Pair) Optional.ofNullable(mFDescriptorBuilder.dimension).orElseGet(new C0720a(13));
        this.scale = mFDescriptorBuilder.scale;
        this.cropRectRatio = mFDescriptorBuilder.cropRectRatio;
        this.align = mFDescriptorBuilder.align;
        this.bitrate = mFDescriptorBuilder.bitrate;
        setFilterOption(mFDescriptorBuilder.filterOption);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Pair lambda$new$0() {
        return new Pair(0, 0);
    }

    public boolean containsExtra(String str) {
        return this.extra.containsKey(str);
    }

    public int getAlign() {
        return this.align;
    }

    public int getBitrate() {
        return this.bitrate;
    }

    public float getCropRectRatio() {
        return this.cropRectRatio;
    }

    public MediaFormat getMediaFormat() {
        return this.mediaFormat;
    }

    public MediaType getMediaType() {
        return this.mediaType;
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public Pair<Integer, Integer> getRectSize() {
        return this.dimension;
    }

    public float getScale() {
        return this.scale;
    }

    public Surface getSurface() {
        return this.surface;
    }

    public boolean isRunInstant() {
        return this.filterOption.isRunInstant();
    }

    public void setMediaFormat(MediaFormat mediaFormat2) {
        this.mediaFormat = mediaFormat2;
    }

    public String toString() {
        return "media=" + this.mediaType + " : " + super.toString();
    }

    public void writeToParcel(Parcel parcel, int i2) {
        int i7;
        super.writeToParcel(parcel, i2);
        parcel.writeSerializable(this.mediaType);
        parcel.writeString(this.mimeType);
        if (this.surface == null) {
            i7 = 0;
        } else {
            i7 = 1;
        }
        parcel.writeInt(i7);
        Surface surface2 = this.surface;
        if (surface2 != null) {
            parcel.writeParcelable(surface2, i2);
        }
        parcel.writeMap(this.extra);
        parcel.writeInt(((Integer) this.dimension.first).intValue());
        parcel.writeInt(((Integer) this.dimension.second).intValue());
        parcel.writeFloat(this.scale);
        parcel.writeFloat(this.cropRectRatio);
        parcel.writeInt(this.bitrate);
        parcel.writeInt(this.align);
    }

    public CodecDescriptor(Parcel parcel) {
        super(parcel);
        this.mediaType = (MediaType) parcel.readSerializable(MediaType.class.getClassLoader(), MediaType.class);
        this.mimeType = parcel.readString();
        if (parcel.readInt() != 0) {
            this.surface = (Surface) parcel.readParcelable(Surface.class.getClassLoader(), Surface.class);
        }
        HashMap hashMap = new HashMap();
        this.extra = hashMap;
        parcel.readMap(hashMap, HashMap.class.getClassLoader(), String.class, Object.class);
        this.dimension = new Pair<>(Integer.valueOf(parcel.readInt()), Integer.valueOf(parcel.readInt()));
        this.scale = parcel.readFloat();
        this.cropRectRatio = parcel.readFloat();
        this.bitrate = parcel.readInt();
        this.align = parcel.readInt();
    }
}
