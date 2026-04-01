package com.samsung.android.motionphoto.utils.v2;

import L1.d;
import android.media.MediaFormat;
import com.samsung.android.sum.core.SLog;
import com.samsung.scsp.media.file.FileApiContract;
import java.io.File;
import java.io.FileDescriptor;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.f;
import ne.z;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u001c\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\u0018\u0000 T2\u00020\u0001:\u0001TB9\b\u0002\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\t\u0010\nB\u0011\b\u0016\u0012\u0006\u0010\u000b\u001a\u00020\u0002¢\u0006\u0004\b\t\u0010\fB\u0011\b\u0016\u0012\u0006\u0010\r\u001a\u00020\u0004¢\u0006\u0004\b\t\u0010\u000eB!\b\u0016\u0012\u0006\u0010\u000b\u001a\u00020\u0002\u0012\u0006\u0010\u000f\u001a\u00020\u0006\u0012\u0006\u0010\u0010\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\u0011B!\b\u0016\u0012\u0006\u0010\r\u001a\u00020\u0004\u0012\u0006\u0010\u000f\u001a\u00020\u0006\u0012\u0006\u0010\u0010\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\u0012J\r\u0010\u0014\u001a\u00020\u0013¢\u0006\u0004\b\u0014\u0010\u0015J\u0015\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u0015\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0016¢\u0006\u0004\b\u001a\u0010\u0019J\r\u0010\u001b\u001a\u00020\u0013¢\u0006\u0004\b\u001b\u0010\u0015J\u000f\u0010\u001c\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u001c\u0010\u001dJ\u001d\u0010#\u001a\u00020\"2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010!\u001a\u00020 ¢\u0006\u0004\b#\u0010$J\u0019\u0010&\u001a\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020 0%¢\u0006\u0004\b&\u0010'J\u000f\u0010(\u001a\u0004\u0018\u00010 ¢\u0006\u0004\b(\u0010)J\r\u0010*\u001a\u00020 ¢\u0006\u0004\b*\u0010)J\r\u0010+\u001a\u00020\u001e¢\u0006\u0004\b+\u0010,J\r\u0010-\u001a\u00020\u001e¢\u0006\u0004\b-\u0010,J\r\u0010.\u001a\u00020\u0013¢\u0006\u0004\b.\u0010\u0015J\r\u0010/\u001a\u00020\u0013¢\u0006\u0004\b/\u0010\u0015R\u0016\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u00100R\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u00101R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0007\u00102R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00068\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\b\u00102R'\u00106\u001a\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u00060%8FX\u0002¢\u0006\f\n\u0004\b3\u00104\u001a\u0004\b5\u0010'R\"\u00107\u001a\u00020\u00068\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b7\u00108\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<R\"\u0010=\u001a\u00020\u00138\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b=\u0010>\u001a\u0004\b?\u0010\u0015\"\u0004\b@\u0010AR \u0010C\u001a\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020 0B8\u0002X\u0004¢\u0006\u0006\n\u0004\bC\u0010DR\u001b\u0010I\u001a\u00020E8FX\u0002¢\u0006\f\n\u0004\bF\u00104\u001a\u0004\bG\u0010HR\u001b\u0010\u0010\u001a\u00020\u00068FX\u0002¢\u0006\f\n\u0004\bJ\u00104\u001a\u0004\bK\u0010:R\u001b\u0010\u000f\u001a\u00020\u00068FX\u0002¢\u0006\f\n\u0004\bL\u00104\u001a\u0004\bM\u0010:R\u001b\u0010P\u001a\u00020\u00068FX\u0002¢\u0006\f\n\u0004\bN\u00104\u001a\u0004\bO\u0010:R\u001b\u0010S\u001a\u00020\u00068FX\u0002¢\u0006\f\n\u0004\bQ\u00104\u001a\u0004\bR\u0010:¨\u0006U"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/MPFile;", "Lcom/samsung/android/motionphoto/utils/v2/MediaFile;", "Ljava/io/FileDescriptor;", "_fd", "Ljava/io/File;", "_file", "", "_dateTimeMs", "_durationMs", "<init>", "(Ljava/io/FileDescriptor;Ljava/io/File;Ljava/lang/Long;Ljava/lang/Long;)V", "fd", "(Ljava/io/FileDescriptor;)V", "file", "(Ljava/io/File;)V", "dateTimeMs", "durationMs", "(Ljava/io/FileDescriptor;JJ)V", "(Ljava/io/File;JJ)V", "", "videoFrameCount", "()I", "", "mimeType", "getDistinctLastTimestampUs", "(Ljava/lang/String;)J", "getVideoFirstFrameTimestampUs", "distinctFrameCount", "toString", "()Ljava/lang/String;", "Lcom/samsung/android/motionphoto/utils/v2/MimeType;", "codecType", "Landroid/media/MediaFormat;", "trackFormat", "Lme/x;", "addCodecFormat", "(Lcom/samsung/android/motionphoto/utils/v2/MimeType;Landroid/media/MediaFormat;)V", "", "getCodecFormats", "()Ljava/util/Map;", "getAudioCodecFormat", "()Landroid/media/MediaFormat;", "getVideoCodecFormat", "getAudioCodecType", "()Lcom/samsung/android/motionphoto/utils/v2/MimeType;", "getVideoCodecType", "getVideoWidth", "getVideoHeight", "Ljava/io/FileDescriptor;", "Ljava/io/File;", "Ljava/lang/Long;", "sampleCount$delegate", "Lme/f;", "getSampleCount", "sampleCount", "distinctDurationMs", "J", "getDistinctDurationMs", "()J", "setDistinctDurationMs", "(J)V", "rotation", "I", "getRotation", "setRotation", "(I)V", "", "codecFormats", "Ljava/util/Map;", "Lcom/samsung/android/motionphoto/utils/v2/MotionPhotoInfoImpl;", "mpInfo$delegate", "getMpInfo", "()Lcom/samsung/android/motionphoto/utils/v2/MotionPhotoInfoImpl;", "mpInfo", "durationMs$delegate", "getDurationMs", "dateTimeMs$delegate", "getDateTimeMs", "startTimeMs$delegate", "getStartTimeMs", "startTimeMs", "endTimeMs$delegate", "getEndTimeMs", "endTimeMs", "Companion", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MPFile extends MediaFile {
    public static final Companion Companion = new Companion((e) null);
    private static final String TAG;
    private Long _dateTimeMs;
    private Long _durationMs;
    private final FileDescriptor _fd;
    private final File _file;
    private final Map<MimeType, MediaFormat> codecFormats;
    private final f dateTimeMs$delegate;
    private long distinctDurationMs;
    private final f durationMs$delegate;
    private final f endTimeMs$delegate;
    private final f mpInfo$delegate;
    private int rotation;
    private final f sampleCount$delegate;
    private final f startTimeMs$delegate;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/MPFile$Companion;", "", "<init>", "()V", "TAG", "", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    static {
        String tagOf = SLog.tagOf(MPFile.class);
        j.d(tagOf, "tagOf(...)");
        TAG = tagOf;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ MPFile(FileDescriptor fileDescriptor, File file, Long l, Long l8, int i2, e eVar) {
        this((i2 & 1) != 0 ? null : fileDescriptor, (i2 & 2) != 0 ? null : file, (i2 & 4) != 0 ? null : l, (i2 & 8) != 0 ? null : l8);
    }

    /* access modifiers changed from: private */
    public static final long dateTimeMs_delegate$lambda$9(MPFile mPFile) {
        Long l = mPFile._dateTimeMs;
        if (l != null) {
            return l.longValue();
        }
        String dateTimeTaken = mPFile.getMpInfo().getDateTimeTaken();
        String str = TAG;
        String path = mPFile.path();
        SLog.d(str, "path=" + path + ", dateTimeTaken=" + dateTimeTaken);
        return LocalDateTime.parse(dateTimeTaken, DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm:ss")).toEpochSecond(ZoneOffset.UTC) * ((long) 1000);
    }

    /* access modifiers changed from: private */
    public static final long durationMs_delegate$lambda$7(MPFile mPFile) {
        Long l = mPFile._durationMs;
        if (l != null) {
            return l.longValue();
        }
        Map<MimeType, Long> trackDurationsOfVideo = mPFile.getMpInfo().getTrackDurationsOfVideo();
        j.b(trackDurationsOfVideo);
        if (!trackDurationsOfVideo.isEmpty()) {
            Iterator<Map.Entry<MimeType, Long>> it = trackDurationsOfVideo.entrySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (((MimeType) it.next().getKey()).isVideo()) {
                    if (!trackDurationsOfVideo.isEmpty()) {
                        for (Map.Entry<MimeType, Long> key : trackDurationsOfVideo.entrySet()) {
                            if (((MimeType) key.getKey()).isAudio()) {
                                Iterable values = trackDurationsOfVideo.values();
                                j.e(values, "<this>");
                                Iterator it2 = values.iterator();
                                if (it2.hasNext()) {
                                    Comparable comparable = (Comparable) it2.next();
                                    while (it2.hasNext()) {
                                        Comparable comparable2 = (Comparable) it2.next();
                                        if (comparable.compareTo(comparable2) < 0) {
                                            comparable = comparable2;
                                        }
                                    }
                                    return ((Number) comparable).longValue() / 1000;
                                }
                                throw new NoSuchElementException();
                            }
                        }
                    }
                }
            }
        }
        return mPFile.getMpInfo().getVideoDurationUs() / 1000;
    }

    /* access modifiers changed from: private */
    public static final long endTimeMs_delegate$lambda$11(MPFile mPFile) {
        return (mPFile.getDurationMs() + mPFile.getDateTimeMs()) - Math.min(mPFile.getMpInfo().getCaptureTimestampUs() / ((long) 1000), mPFile.getDurationMs());
    }

    /* access modifiers changed from: private */
    public static final MotionPhotoInfoImpl mpInfo_delegate$lambda$3(MPFile mPFile) {
        return new MotionPhotoInfoImpl((MediaFile) mPFile);
    }

    /* access modifiers changed from: private */
    public static final Map sampleCount_delegate$lambda$0(MPFile mPFile) {
        return mPFile.getMpInfo().getVideoInfo().getSampleCount();
    }

    /* access modifiers changed from: private */
    public static final long startTimeMs_delegate$lambda$10(MPFile mPFile) {
        return mPFile.getEndTimeMs() - mPFile.getDurationMs();
    }

    public final void addCodecFormat(MimeType mimeType, MediaFormat mediaFormat) {
        j.e(mimeType, "codecType");
        j.e(mediaFormat, "trackFormat");
        this.codecFormats.put(mimeType, mediaFormat);
    }

    public final int distinctFrameCount() {
        int videoFrameCount = videoFrameCount();
        long durationMs = getDurationMs();
        long j2 = this.distinctDurationMs;
        if (durationMs == 0) {
            return 0;
        }
        return (int) ((((long) videoFrameCount) * j2) / durationMs);
    }

    public final MediaFormat getAudioCodecFormat() {
        return this.codecFormats.get(getAudioCodecType());
    }

    public final MimeType getAudioCodecType() {
        T t;
        Iterator<T> it = this.codecFormats.keySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                t = null;
                break;
            }
            t = it.next();
            if (((MimeType) t).isAudio()) {
                break;
            }
        }
        MimeType mimeType = (MimeType) t;
        if (mimeType == null) {
            return MimeType.NONE;
        }
        return mimeType;
    }

    public final Map<MimeType, MediaFormat> getCodecFormats() {
        return z.f0(this.codecFormats);
    }

    public final long getDateTimeMs() {
        return ((Number) this.dateTimeMs$delegate.getValue()).longValue();
    }

    public final long getDistinctDurationMs() {
        return this.distinctDurationMs;
    }

    public final long getDistinctLastTimestampUs(String str) {
        j.e(str, FileApiContract.Parameter.MIME_TYPE);
        Map<MimeType, Long> trackFirstTimestampUs = getMpInfo().getVideoInfo().getTrackFirstTimestampUs();
        String str2 = TAG;
        long j2 = this.distinctDurationMs;
        long durationMs = getDurationMs();
        SLog.d(str2, "trackFirstTimestampUs=" + trackFirstTimestampUs + ", distinctDurationMs=" + j2 + ", durationMs=" + durationMs);
        Long l = trackFirstTimestampUs.get(MimeType.Companion.of(str));
        if (l == null) {
            return 0;
        }
        long longValue = l.longValue();
        long j3 = this.distinctDurationMs;
        if (j3 == 0) {
            j3 = getDurationMs();
        }
        return (j3 * ((long) 1000)) + longValue;
    }

    public final long getDurationMs() {
        return ((Number) this.durationMs$delegate.getValue()).longValue();
    }

    public final long getEndTimeMs() {
        return ((Number) this.endTimeMs$delegate.getValue()).longValue();
    }

    public final MotionPhotoInfoImpl getMpInfo() {
        return (MotionPhotoInfoImpl) this.mpInfo$delegate.getValue();
    }

    public final int getRotation() {
        return this.rotation;
    }

    public final Map<MimeType, Long> getSampleCount() {
        Object value = this.sampleCount$delegate.getValue();
        j.d(value, "getValue(...)");
        return (Map) value;
    }

    public final long getStartTimeMs() {
        return ((Number) this.startTimeMs$delegate.getValue()).longValue();
    }

    public final MediaFormat getVideoCodecFormat() {
        MediaFormat mediaFormat = this.codecFormats.get(getVideoCodecType());
        j.b(mediaFormat);
        return mediaFormat;
    }

    public final MimeType getVideoCodecType() {
        T t;
        Iterator<T> it = this.codecFormats.keySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                t = null;
                break;
            }
            t = it.next();
            if (((MimeType) t).isVideo()) {
                break;
            }
        }
        MimeType mimeType = (MimeType) t;
        if (mimeType == null) {
            return MimeType.NONE;
        }
        return mimeType;
    }

    public final long getVideoFirstFrameTimestampUs(String str) {
        j.e(str, FileApiContract.Parameter.MIME_TYPE);
        Long l = getMpInfo().getVideoInfo().getTrackFirstTimestampUs().get(MimeType.Companion.of(str));
        if (l != null) {
            return l.longValue();
        }
        return 0;
    }

    public final int getVideoHeight() {
        MediaFormat mediaFormat = this.codecFormats.get(getVideoCodecType());
        if (mediaFormat != null) {
            return mediaFormat.getInteger("height");
        }
        return 0;
    }

    public final int getVideoWidth() {
        MediaFormat mediaFormat = this.codecFormats.get(getVideoCodecType());
        if (mediaFormat != null) {
            return mediaFormat.getInteger("width");
        }
        return 0;
    }

    public final void setDistinctDurationMs(long j2) {
        this.distinctDurationMs = j2;
    }

    public final void setRotation(int i2) {
        this.rotation = i2;
    }

    public String toString() {
        String path = path();
        Long l = this._dateTimeMs;
        Long l8 = this._durationMs;
        long startTimeMs = getStartTimeMs();
        long endTimeMs = getEndTimeMs();
        long j2 = this.distinctDurationMs;
        return CommonsKt.trimToOneLine("{ path=" + path + ",\n            | dateTimeMs=" + l + ", durationMs=" + l8 + ",\n            | startTimeMs=" + startTimeMs + ", endTimeMs=" + endTimeMs + ",\n            | distinctDurationMs=" + j2 + " ms }\n        ");
    }

    public final int videoFrameCount() {
        return getMpInfo().getVideoInfo().getVideoFrameCount();
    }

    private MPFile(FileDescriptor fileDescriptor, File file, Long l, Long l8) {
        super(fileDescriptor, file);
        this._fd = fileDescriptor;
        this._file = file;
        this._dateTimeMs = l;
        this._durationMs = l8;
        this.sampleCount$delegate = d.q(new a(0, this));
        this.codecFormats = new LinkedHashMap();
        this.mpInfo$delegate = d.q(new a(1, this));
        this.durationMs$delegate = d.q(new a(2, this));
        this.dateTimeMs$delegate = d.q(new a(3, this));
        this.startTimeMs$delegate = d.q(new a(4, this));
        this.endTimeMs$delegate = d.q(new a(5, this));
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public MPFile(FileDescriptor fileDescriptor) {
        this(fileDescriptor, (File) null, (Long) null, (Long) null, 14, (e) null);
        j.e(fileDescriptor, "fd");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public MPFile(File file) {
        this((FileDescriptor) null, file, (Long) null, (Long) null, 13, (e) null);
        j.e(file, "file");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public MPFile(FileDescriptor fileDescriptor, long j2, long j3) {
        this(fileDescriptor, (File) null, Long.valueOf(j2), Long.valueOf(j3), 2, (e) null);
        j.e(fileDescriptor, "fd");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public MPFile(File file, long j2, long j3) {
        this((FileDescriptor) null, file, Long.valueOf(j2), Long.valueOf(j3), 1, (e) null);
        j.e(file, "file");
    }
}
