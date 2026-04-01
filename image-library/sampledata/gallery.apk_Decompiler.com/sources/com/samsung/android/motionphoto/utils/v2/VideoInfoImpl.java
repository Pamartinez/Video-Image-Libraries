package com.samsung.android.motionphoto.utils.v2;

import A.a;
import He.C0748d;
import L1.d;
import android.graphics.Bitmap;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import com.samsung.android.motionphoto.utils.v2.MimeType;
import com.samsung.android.motionphoto.utils.v2.io.VideoMetaReader;
import com.samsung.android.sum.core.SLog;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.v;
import kotlin.jvm.internal.w;
import me.f;
import me.i;
import ne.C1196n;
import ne.y;
import ne.z;

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010$\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010%\n\u0002\b\u0006\u0018\u0000 82\u00020\u0001:\u00039:8B%\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bB\u0011\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0007\u0010\tJC\u0010\u0012\u001a\u00028\u0000\"\b\b\u0000\u0010\u000b*\u00020\n2\u0006\u0010\r\u001a\u00020\f2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000e2\u0012\u0010\u0011\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0\u0010\"\u00020\nH\u0002¢\u0006\u0004\b\u0012\u0010\u0013J;\u0010\u0016\u001a\u00020\u00152\u0016\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000e0\u00142\u0012\u0010\u0011\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0\u0010\"\u00020\nH\u0002¢\u0006\u0004\b\u0016\u0010\u0017J;\u0010\u0018\u001a\u00020\u00152\u0016\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000e0\u00142\u0012\u0010\u0011\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0\u0010\"\u00020\nH\u0002¢\u0006\u0004\b\u0018\u0010\u0017J;\u0010\u0019\u001a\u00020\u00152\u0016\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000e0\u00142\u0012\u0010\u0011\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0\u0010\"\u00020\nH\u0002¢\u0006\u0004\b\u0019\u0010\u0017J\u000f\u0010\u001b\u001a\u00020\u001aH\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001d\u001a\u00020\fH\u0016¢\u0006\u0004\b\u001d\u0010\u001eJ\u000f\u0010\u001f\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u001f\u0010 J\u000f\u0010!\u001a\u00020\fH\u0016¢\u0006\u0004\b!\u0010\u001eJ\u000f\u0010\"\u001a\u00020\fH\u0016¢\u0006\u0004\b\"\u0010\u001eJ\u000f\u0010#\u001a\u00020\fH\u0016¢\u0006\u0004\b#\u0010\u001eJ\u000f\u0010$\u001a\u00020\fH\u0016¢\u0006\u0004\b$\u0010\u001eJ\u001b\u0010&\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u00040%H\u0016¢\u0006\u0004\b&\u0010'J\u001b\u0010(\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u00040%H\u0016¢\u0006\u0004\b(\u0010'J\u000f\u0010)\u001a\u00020\fH\u0016¢\u0006\u0004\b)\u0010\u001eJ\u001b\u0010*\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u00040%H\u0016¢\u0006\u0004\b*\u0010'J\u001b\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u00040%H\u0016¢\u0006\u0004\b+\u0010'J\u000f\u0010-\u001a\u00020,H\u0016¢\u0006\u0004\b-\u0010.J\u001f\u00101\u001a\u00020,2\u0006\u0010/\u001a\u00020\u00042\u0006\u00100\u001a\u00020\fH\u0016¢\u0006\u0004\b1\u00102R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u00103R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u00104R\u0014\u0010\u0006\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0006\u00104R \u00106\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\n058\u0002X\u0004¢\u0006\u0006\n\u0004\b6\u00107¨\u0006;"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/VideoInfoImpl;", "Lcom/samsung/android/motionphoto/utils/v2/VideoInfo;", "Lcom/samsung/android/motionphoto/utils/v2/MediaFile;", "mediaFile", "", "offset", "length", "<init>", "(Lcom/samsung/android/motionphoto/utils/v2/MediaFile;JJ)V", "(Lcom/samsung/android/motionphoto/utils/v2/MediaFile;)V", "", "T", "", "key", "LHe/d;", "clazz", "", "args", "get", "(ILHe/d;[Ljava/lang/Object;)Ljava/lang/Object;", "Lme/i;", "Lme/x;", "parseByRetriever", "(Lme/i;[Ljava/lang/Object;)V", "parseByExtractor", "parseByMetaReader", "Lcom/samsung/android/motionphoto/utils/v2/MimeType;", "getMimeType", "()Lcom/samsung/android/motionphoto/utils/v2/MimeType;", "numberOfTracks", "()I", "getVideoDurationMs", "()J", "getWidth", "getHeight", "getRotation", "getBitrate", "", "getTrackDurations", "()Ljava/util/Map;", "getSampleCount", "getVideoFrameCount", "getTrackFirstTimestampUs", "getTrackLastTimestampUs", "Landroid/graphics/Bitmap;", "getThumbnailImage", "()Landroid/graphics/Bitmap;", "timeUs", "option", "getFrameAt", "(JI)Landroid/graphics/Bitmap;", "Lcom/samsung/android/motionphoto/utils/v2/MediaFile;", "J", "", "data", "Ljava/util/Map;", "Companion", "TrackInfo", "ExtraTrackInfo", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VideoInfoImpl implements VideoInfo {
    public static final Companion Companion = new Companion((e) null);
    private static final int METADATA_KEY_EXTRA_TRACK_INFO = 1001;
    private static final int METADATA_KEY_FRAME = 1003;
    private static final int METADATA_KEY_THUMBNAIL = 1002;
    private static final int METADATA_KEY_TRACK_INFO = 1000;
    private static final String TAG;
    private final Map<Integer, Object> data;
    private final long length;
    private final MediaFile mediaFile;
    private final long offset;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/VideoInfoImpl$Companion;", "", "<init>", "()V", "TAG", "", "METADATA_KEY_TRACK_INFO", "", "METADATA_KEY_EXTRA_TRACK_INFO", "METADATA_KEY_THUMBNAIL", "METADATA_KEY_FRAME", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/VideoInfoImpl$ExtraTrackInfo;", "", "sampleCount", "", "<init>", "(J)V", "getSampleCount", "()J", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ExtraTrackInfo {
        private final long sampleCount;

        public ExtraTrackInfo(long j2) {
            this.sampleCount = j2;
        }

        public static /* synthetic */ ExtraTrackInfo copy$default(ExtraTrackInfo extraTrackInfo, long j2, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                j2 = extraTrackInfo.sampleCount;
            }
            return extraTrackInfo.copy(j2);
        }

        public final long component1() {
            return this.sampleCount;
        }

        public final ExtraTrackInfo copy(long j2) {
            return new ExtraTrackInfo(j2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if ((obj instanceof ExtraTrackInfo) && this.sampleCount == ((ExtraTrackInfo) obj).sampleCount) {
                return true;
            }
            return false;
        }

        public final long getSampleCount() {
            return this.sampleCount;
        }

        public int hashCode() {
            return Long.hashCode(this.sampleCount);
        }

        public String toString() {
            return a.e(this.sampleCount, "ExtraTrackInfo(sampleCount=", ")");
        }
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\f\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\n\u0010\u000bJ$\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0004HÆ\u0001¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000f\u001a\u00020\u000eHÖ\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0012\u001a\u00020\u0011HÖ\u0001¢\u0006\u0004\b\u0012\u0010\u0013J\u001a\u0010\u0016\u001a\u00020\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0018\u001a\u0004\b\u0019\u0010\tR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u001a\u001a\u0004\b\u001b\u0010\u000bR\u001b\u0010!\u001a\u00020\u001c8FX\u0002¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u001b\u0010$\u001a\u00020\u00028FX\u0002¢\u0006\f\n\u0004\b\"\u0010\u001e\u001a\u0004\b#\u0010\tR\u001b\u0010'\u001a\u00020\u00028FX\u0002¢\u0006\f\n\u0004\b%\u0010\u001e\u001a\u0004\b&\u0010\t¨\u0006("}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/VideoInfoImpl$TrackInfo;", "", "", "firstTimestampUs", "Landroid/media/MediaFormat;", "format", "<init>", "(JLandroid/media/MediaFormat;)V", "component1", "()J", "component2", "()Landroid/media/MediaFormat;", "copy", "(JLandroid/media/MediaFormat;)Lcom/samsung/android/motionphoto/utils/v2/VideoInfoImpl$TrackInfo;", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "J", "getFirstTimestampUs", "Landroid/media/MediaFormat;", "getFormat", "Lcom/samsung/android/motionphoto/utils/v2/MimeType;", "mimeType$delegate", "Lme/f;", "getMimeType", "()Lcom/samsung/android/motionphoto/utils/v2/MimeType;", "mimeType", "durationUs$delegate", "getDurationUs", "durationUs", "bitrate$delegate", "getBitrate", "bitrate", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class TrackInfo {
        private final f bitrate$delegate = d.q(new o(this, 2));
        private final f durationUs$delegate = d.q(new o(this, 1));
        private final long firstTimestampUs;
        private final MediaFormat format;
        private final f mimeType$delegate = d.q(new o(this, 0));

        public TrackInfo(long j2, MediaFormat mediaFormat) {
            j.e(mediaFormat, "format");
            this.firstTimestampUs = j2;
            this.format = mediaFormat;
        }

        /* access modifiers changed from: private */
        public static final long bitrate_delegate$lambda$2(TrackInfo trackInfo) {
            return trackInfo.format.getLong("bitrate");
        }

        public static /* synthetic */ TrackInfo copy$default(TrackInfo trackInfo, long j2, MediaFormat mediaFormat, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                j2 = trackInfo.firstTimestampUs;
            }
            if ((i2 & 2) != 0) {
                mediaFormat = trackInfo.format;
            }
            return trackInfo.copy(j2, mediaFormat);
        }

        /* access modifiers changed from: private */
        public static final long durationUs_delegate$lambda$1(TrackInfo trackInfo) {
            return trackInfo.format.getLong("durationUs");
        }

        /* access modifiers changed from: private */
        public static final MimeType mimeType_delegate$lambda$0(TrackInfo trackInfo) {
            MimeType.Companion companion = MimeType.Companion;
            String string = trackInfo.format.getString(MediaDefs.Image.HEIF.HEIF_MIME_BOX);
            if (string == null) {
                string = "";
            }
            return companion.of(string);
        }

        public final long component1() {
            return this.firstTimestampUs;
        }

        public final MediaFormat component2() {
            return this.format;
        }

        public final TrackInfo copy(long j2, MediaFormat mediaFormat) {
            j.e(mediaFormat, "format");
            return new TrackInfo(j2, mediaFormat);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof TrackInfo)) {
                return false;
            }
            TrackInfo trackInfo = (TrackInfo) obj;
            if (this.firstTimestampUs == trackInfo.firstTimestampUs && j.a(this.format, trackInfo.format)) {
                return true;
            }
            return false;
        }

        public final long getBitrate() {
            return ((Number) this.bitrate$delegate.getValue()).longValue();
        }

        public final long getDurationUs() {
            return ((Number) this.durationUs$delegate.getValue()).longValue();
        }

        public final long getFirstTimestampUs() {
            return this.firstTimestampUs;
        }

        public final MediaFormat getFormat() {
            return this.format;
        }

        public final MimeType getMimeType() {
            return (MimeType) this.mimeType$delegate.getValue();
        }

        public int hashCode() {
            return this.format.hashCode() + (Long.hashCode(this.firstTimestampUs) * 31);
        }

        public String toString() {
            long j2 = this.firstTimestampUs;
            MediaFormat mediaFormat = this.format;
            return "TrackInfo(firstTimestampUs=" + j2 + ", format=" + mediaFormat + ")";
        }
    }

    static {
        String tagOf = SLog.tagOf(VideoInfoImpl.class);
        j.d(tagOf, "tagOf(...)");
        TAG = tagOf;
    }

    public VideoInfoImpl(MediaFile mediaFile2, long j2, long j3) {
        j.e(mediaFile2, "mediaFile");
        this.mediaFile = mediaFile2;
        this.offset = j2;
        this.length = j3;
        this.data = new LinkedHashMap();
    }

    private final <T> T get(int i2, C0748d dVar, Object... objArr) {
        if (!this.data.containsKey(Integer.valueOf(i2))) {
            parseByRetriever(new i(Integer.valueOf(i2), dVar), Arrays.copyOf(objArr, objArr.length));
        }
        if (!this.data.containsKey(Integer.valueOf(i2))) {
            parseByExtractor(new i(Integer.valueOf(i2), dVar), Arrays.copyOf(objArr, objArr.length));
        }
        if (!this.data.containsKey(Integer.valueOf(i2))) {
            parseByMetaReader(new i(Integer.valueOf(i2), dVar), Arrays.copyOf(objArr, objArr.length));
        }
        T t = this.data.get(Integer.valueOf(i2));
        j.c(t, "null cannot be cast to non-null type T of com.samsung.android.motionphoto.utils.v2.VideoInfoImpl.get");
        return t;
    }

    private final void parseByExtractor(i iVar, Object... objArr) {
        Throwable th;
        LinkedHashMap linkedHashMap;
        Set<Integer> keySet = this.data.keySet();
        Object obj = iVar.d;
        Object obj2 = iVar.d;
        if (!keySet.contains(obj)) {
            FileInputStream newInputFileStream = this.mediaFile.newInputFileStream();
            try {
                MediaExtractor mediaExtractor = new MediaExtractor();
                if (this.offset != 0) {
                    mediaExtractor.setDataSource(newInputFileStream.getFD(), this.offset, this.length);
                } else {
                    mediaExtractor.setDataSource(newInputFileStream.getFD());
                }
                if (((Number) obj2).intValue() == 1000) {
                    Ge.e Z = B1.a.Z(0, mediaExtractor.getTrackCount());
                    int Z4 = z.Z(C1196n.w0(Z, 10));
                    if (Z4 < 16) {
                        Z4 = 16;
                    }
                    linkedHashMap = new LinkedHashMap(Z4);
                    Iterator it = Z.iterator();
                    while (it.hasNext()) {
                        int nextInt = ((y) it).nextInt();
                        mediaExtractor.selectTrack(nextInt);
                        long sampleTime = mediaExtractor.getSampleTime();
                        MediaFormat trackFormat = mediaExtractor.getTrackFormat(nextInt);
                        j.d(trackFormat, "getTrackFormat(...)");
                        TrackInfo trackInfo = new TrackInfo(sampleTime, trackFormat);
                        MimeType mimeType = trackInfo.getMimeType();
                        mediaExtractor.unselectTrack(nextInt);
                        linkedHashMap.put(mimeType, trackInfo);
                    }
                } else {
                    linkedHashMap = null;
                }
                if (linkedHashMap != null) {
                    this.data.put(obj2, linkedHashMap);
                }
                B1.a.g(newInputFileStream, (Throwable) null);
            } catch (Throwable th2) {
                Throwable th3 = th2;
                B1.a.g(newInputFileStream, th);
                throw th3;
            }
        }
        SLog.d(TAG, "parseByExtractor: " + iVar);
    }

    private final void parseByMetaReader(i iVar, Object... objArr) {
        Map map;
        Set<Integer> keySet = this.data.keySet();
        Object obj = iVar.d;
        if (!keySet.contains(obj)) {
            VideoMetaReader of2 = VideoMetaReader.Companion.of(this.mediaFile);
            if (((Number) obj).intValue() == 1001) {
                SLog.i(TAG, "parse METADATA_KEY_EXTRA_TRACK_INFO");
                Object obj2 = get(1000, v.f4727a.b(Map.class), new Object[0]);
                j.c(obj2, "null cannot be cast to non-null type kotlin.collections.Map<com.samsung.android.motionphoto.utils.v2.MimeType, com.samsung.android.motionphoto.utils.v2.VideoInfoImpl.TrackInfo>");
                Map map2 = (Map) obj2;
                ArrayList arrayList = new ArrayList(map2.size());
                for (Map.Entry entry : map2.entrySet()) {
                    TrackInfo trackInfo = (TrackInfo) entry.getValue();
                    arrayList.add(new i((MimeType) entry.getKey(), new ExtraTrackInfo(of2.readSampleCount())));
                }
                map = z.e0(arrayList);
            } else {
                map = null;
            }
            if (map != null) {
                this.data.put(obj, map);
            }
        }
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Code restructure failed: missing block: B:45:?, code lost:
        B1.a.g(r2, (java.lang.Throwable) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x015b, code lost:
        He.F.u(r6, (java.lang.Throwable) null);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void parseByRetriever(me.i r13, java.lang.Object... r14) {
        /*
            r12 = this;
            r0 = 12
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r1 = 1000(0x3e8, float:1.401E-42)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.Integer[] r1 = new java.lang.Integer[]{r1}
            java.lang.Object r2 = r13.d
            boolean r1 = ne.C1192j.d0(r1, r2)
            if (r1 != 0) goto L_0x0183
            java.util.Map<java.lang.Integer, java.lang.Object> r1 = r12.data
            java.util.Set r1 = r1.keySet()
            java.lang.Object r2 = r13.d
            boolean r1 = r1.contains(r2)
            if (r1 == 0) goto L_0x0028
            goto L_0x0183
        L_0x0028:
            me.i[] r1 = new me.i[]{r13}
            java.util.ArrayList r1 = ne.C1195m.s0(r1)
            java.util.Map<java.lang.Integer, java.lang.Object> r2 = r12.data
            java.util.Set r2 = r2.keySet()
            boolean r2 = r2.contains(r0)
            java.lang.Class r3 = java.lang.Integer.TYPE
            java.lang.Class r4 = java.lang.Long.TYPE
            java.lang.Class<java.lang.String> r5 = java.lang.String.class
            if (r2 != 0) goto L_0x0090
            kotlin.jvm.internal.w r2 = kotlin.jvm.internal.v.f4727a
            He.d r6 = r2.b(r5)
            me.i r7 = new me.i
            r7.<init>(r0, r6)
            r0 = 9
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            He.d r6 = r2.b(r4)
            me.i r8 = new me.i
            r8.<init>(r0, r6)
            r0 = 18
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            He.d r6 = r2.b(r3)
            me.i r9 = new me.i
            r9.<init>(r0, r6)
            r0 = 19
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            He.d r6 = r2.b(r3)
            me.i r10 = new me.i
            r10.<init>(r0, r6)
            r0 = 24
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            He.d r2 = r2.b(r3)
            me.i r6 = new me.i
            r6.<init>(r0, r2)
            me.i[] r0 = new me.i[]{r7, r8, r9, r10, r6}
            ne.C1200r.B0(r1, r0)
        L_0x0090:
            boolean r0 = r1.isEmpty()
            if (r0 != 0) goto L_0x0170
            android.media.MediaMetadataRetriever r6 = new android.media.MediaMetadataRetriever
            r6.<init>()
            com.samsung.android.motionphoto.utils.v2.MediaFile r0 = r12.mediaFile     // Catch:{ all -> 0x015f }
            java.io.FileInputStream r2 = r0.newInputFileStream()     // Catch:{ all -> 0x015f }
            long r7 = r12.offset     // Catch:{ all -> 0x00b5 }
            r9 = 0
            int r0 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r0 == 0) goto L_0x00b9
            java.io.FileDescriptor r7 = r2.getFD()     // Catch:{ all -> 0x00b5 }
            long r8 = r12.offset     // Catch:{ all -> 0x00b5 }
            long r10 = r12.length     // Catch:{ all -> 0x00b5 }
            r6.setDataSource(r7, r8, r10)     // Catch:{ all -> 0x00b5 }
            goto L_0x00c0
        L_0x00b5:
            r0 = move-exception
            r12 = r0
            goto L_0x0162
        L_0x00b9:
            java.io.FileDescriptor r0 = r2.getFD()     // Catch:{ all -> 0x00b5 }
            r6.setDataSource(r0)     // Catch:{ all -> 0x00b5 }
        L_0x00c0:
            java.util.Iterator r0 = r1.iterator()     // Catch:{ all -> 0x00b5 }
        L_0x00c4:
            boolean r1 = r0.hasNext()     // Catch:{ all -> 0x00b5 }
            r7 = 0
            if (r1 == 0) goto L_0x0158
            java.lang.Object r1 = r0.next()     // Catch:{ all -> 0x00b5 }
            me.i r1 = (me.i) r1     // Catch:{ all -> 0x00b5 }
            java.lang.Object r8 = r1.d     // Catch:{ all -> 0x00b5 }
            java.lang.Number r8 = (java.lang.Number) r8     // Catch:{ all -> 0x00b5 }
            int r8 = r8.intValue()     // Catch:{ all -> 0x00b5 }
            java.lang.Object r1 = r1.e     // Catch:{ all -> 0x00b5 }
            He.d r1 = (He.C0748d) r1     // Catch:{ all -> 0x00b5 }
            r9 = 1002(0x3ea, float:1.404E-42)
            if (r8 == r9) goto L_0x0147
            r9 = 1003(0x3eb, float:1.406E-42)
            if (r8 == r9) goto L_0x0126
            java.lang.String r9 = r6.extractMetadata(r8)     // Catch:{ all -> 0x00b5 }
            if (r9 == 0) goto L_0x014b
            kotlin.jvm.internal.w r7 = kotlin.jvm.internal.v.f4727a     // Catch:{ all -> 0x00b5 }
            He.d r10 = r7.b(r3)     // Catch:{ all -> 0x00b5 }
            boolean r10 = kotlin.jvm.internal.j.a(r1, r10)     // Catch:{ all -> 0x00b5 }
            if (r10 == 0) goto L_0x0101
            int r1 = java.lang.Integer.parseInt(r9)     // Catch:{ all -> 0x00b5 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x00b5 }
        L_0x00ff:
            r7 = r1
            goto L_0x014b
        L_0x0101:
            He.d r10 = r7.b(r4)     // Catch:{ all -> 0x00b5 }
            boolean r10 = kotlin.jvm.internal.j.a(r1, r10)     // Catch:{ all -> 0x00b5 }
            if (r10 == 0) goto L_0x0114
            long r9 = java.lang.Long.parseLong(r9)     // Catch:{ all -> 0x00b5 }
            java.lang.Long r1 = java.lang.Long.valueOf(r9)     // Catch:{ all -> 0x00b5 }
            goto L_0x00ff
        L_0x0114:
            He.d r7 = r7.b(r5)     // Catch:{ all -> 0x00b5 }
            boolean r1 = kotlin.jvm.internal.j.a(r1, r7)     // Catch:{ all -> 0x00b5 }
            if (r1 == 0) goto L_0x0120
            r7 = r9
            goto L_0x014b
        L_0x0120:
            java.lang.UnsupportedOperationException r12 = new java.lang.UnsupportedOperationException     // Catch:{ all -> 0x00b5 }
            r12.<init>()     // Catch:{ all -> 0x00b5 }
            throw r12     // Catch:{ all -> 0x00b5 }
        L_0x0126:
            r1 = 0
            r1 = r14[r1]     // Catch:{ all -> 0x00b5 }
            java.lang.String r7 = "null cannot be cast to non-null type kotlin.Long"
            kotlin.jvm.internal.j.c(r1, r7)     // Catch:{ all -> 0x00b5 }
            java.lang.Long r1 = (java.lang.Long) r1     // Catch:{ all -> 0x00b5 }
            long r9 = r1.longValue()     // Catch:{ all -> 0x00b5 }
            r1 = 1
            r1 = r14[r1]     // Catch:{ all -> 0x00b5 }
            java.lang.String r7 = "null cannot be cast to non-null type kotlin.Int"
            kotlin.jvm.internal.j.c(r1, r7)     // Catch:{ all -> 0x00b5 }
            java.lang.Integer r1 = (java.lang.Integer) r1     // Catch:{ all -> 0x00b5 }
            int r1 = r1.intValue()     // Catch:{ all -> 0x00b5 }
            android.graphics.Bitmap r7 = r6.getFrameAtTime(r9, r1)     // Catch:{ all -> 0x00b5 }
            goto L_0x014b
        L_0x0147:
            android.graphics.Bitmap r7 = r6.getFrameAtTime()     // Catch:{ all -> 0x00b5 }
        L_0x014b:
            if (r7 == 0) goto L_0x00c4
            java.lang.Integer r1 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x00b5 }
            java.util.Map<java.lang.Integer, java.lang.Object> r8 = r12.data     // Catch:{ all -> 0x00b5 }
            r8.put(r1, r7)     // Catch:{ all -> 0x00b5 }
            goto L_0x00c4
        L_0x0158:
            B1.a.g(r2, r7)     // Catch:{ all -> 0x015f }
            He.F.u(r6, r7)
            goto L_0x0170
        L_0x015f:
            r0 = move-exception
            r12 = r0
            goto L_0x0169
        L_0x0162:
            throw r12     // Catch:{ all -> 0x0163 }
        L_0x0163:
            r0 = move-exception
            r13 = r0
            B1.a.g(r2, r12)     // Catch:{ all -> 0x015f }
            throw r13     // Catch:{ all -> 0x015f }
        L_0x0169:
            throw r12     // Catch:{ all -> 0x016a }
        L_0x016a:
            r0 = move-exception
            r13 = r0
            He.F.u(r6, r12)
            throw r13
        L_0x0170:
            java.lang.String r12 = TAG
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            java.lang.String r0 = "parseByRetriever: "
            r14.<init>(r0)
            r14.append(r13)
            java.lang.String r13 = r14.toString()
            com.samsung.android.sum.core.SLog.d((java.lang.String) r12, (java.lang.String) r13)
        L_0x0183:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.motionphoto.utils.v2.VideoInfoImpl.parseByRetriever(me.i, java.lang.Object[]):void");
    }

    public int getBitrate() {
        C0748d b = v.f4727a.b(Map.class);
        int i2 = 0;
        for (Object next : ((Map) get(1000, b, new Object[0])).values()) {
            j.c(next, "null cannot be cast to non-null type com.samsung.android.motionphoto.utils.v2.VideoInfoImpl.TrackInfo");
            i2 += (int) ((TrackInfo) next).getBitrate();
        }
        return i2;
    }

    public Bitmap getFrameAt(long j2, int i2) {
        return (Bitmap) get(1003, v.f4727a.b(Bitmap.class), Long.valueOf(j2), Integer.valueOf(i2));
    }

    public int getHeight() {
        return ((Number) get(19, v.f4727a.b(Integer.TYPE), new Object[0])).intValue();
    }

    public MimeType getMimeType() {
        w wVar = v.f4727a;
        return MimeType.Companion.of((String) get(12, wVar.b(String.class), new Object[0]));
    }

    public int getRotation() {
        return ((Number) get(24, v.f4727a.b(Integer.TYPE), new Object[0])).intValue();
    }

    public Map<MimeType, Long> getSampleCount() {
        Map map = (Map) get(1001, v.f4727a.b(Map.class), new Object[0]);
        LinkedHashMap linkedHashMap = new LinkedHashMap(z.Z(map.size()));
        for (Map.Entry entry : map.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            j.c(value, "null cannot be cast to non-null type com.samsung.android.motionphoto.utils.v2.VideoInfoImpl.ExtraTrackInfo");
            linkedHashMap.put(key, Long.valueOf(((ExtraTrackInfo) value).getSampleCount()));
        }
        return linkedHashMap;
    }

    public Bitmap getThumbnailImage() {
        return (Bitmap) get(1002, v.f4727a.b(Bitmap.class), new Object[0]);
    }

    public Map<MimeType, Long> getTrackDurations() {
        Map map = (Map) get(1000, v.f4727a.b(Map.class), new Object[0]);
        LinkedHashMap linkedHashMap = new LinkedHashMap(z.Z(map.size()));
        for (Map.Entry entry : map.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            j.c(value, "null cannot be cast to non-null type com.samsung.android.motionphoto.utils.v2.VideoInfoImpl.TrackInfo");
            linkedHashMap.put(key, Long.valueOf(((TrackInfo) value).getDurationUs()));
        }
        return linkedHashMap;
    }

    public Map<MimeType, Long> getTrackFirstTimestampUs() {
        Map map = (Map) get(1000, v.f4727a.b(Map.class), new Object[0]);
        LinkedHashMap linkedHashMap = new LinkedHashMap(z.Z(map.size()));
        for (Map.Entry entry : map.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            j.c(value, "null cannot be cast to non-null type com.samsung.android.motionphoto.utils.v2.VideoInfoImpl.TrackInfo");
            linkedHashMap.put(key, Long.valueOf(((TrackInfo) value).getFirstTimestampUs()));
        }
        return linkedHashMap;
    }

    public Map<MimeType, Long> getTrackLastTimestampUs() {
        Map map = (Map) get(1000, v.f4727a.b(Map.class), new Object[0]);
        LinkedHashMap linkedHashMap = new LinkedHashMap(z.Z(map.size()));
        for (Map.Entry entry : map.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            j.c(value, "null cannot be cast to non-null type com.samsung.android.motionphoto.utils.v2.VideoInfoImpl.TrackInfo");
            long firstTimestampUs = ((TrackInfo) value).getFirstTimestampUs();
            Object value2 = entry.getValue();
            j.c(value2, "null cannot be cast to non-null type com.samsung.android.motionphoto.utils.v2.VideoInfoImpl.TrackInfo");
            linkedHashMap.put(key, Long.valueOf(((TrackInfo) value2).getDurationUs() + firstTimestampUs));
        }
        return linkedHashMap;
    }

    public long getVideoDurationMs() {
        return ((Number) get(9, v.f4727a.b(Long.TYPE), new Object[0])).longValue();
    }

    public int getVideoFrameCount() {
        return ((Number) get(32, v.f4727a.b(Integer.TYPE), new Object[0])).intValue();
    }

    public int getWidth() {
        return ((Number) get(18, v.f4727a.b(Integer.TYPE), new Object[0])).intValue();
    }

    public int numberOfTracks() {
        return ((Map) get(1000, v.f4727a.b(Map.class), new Object[0])).size();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ VideoInfoImpl(MediaFile mediaFile2, long j2, long j3, int i2, e eVar) {
        this(mediaFile2, (i2 & 2) != 0 ? 0 : j2, (i2 & 4) != 0 ? 0 : j3);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public VideoInfoImpl(MediaFile mediaFile2) {
        this(mediaFile2, 0, 0);
        j.e(mediaFile2, "mediaFile");
    }
}
