package com.samsung.android.motionphoto.utils.v2.io;

import B1.a;
import L1.d;
import Sf.q;
import com.samsung.android.motionphoto.utils.v2.CommonsKt;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import com.samsung.android.motionphoto.utils.v2.MediaFile;
import com.samsung.android.motionphoto.utils.v2.MotionPhotoInfoImpl;
import com.samsung.android.sum.core.SLog;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.ByteOrder;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.f;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J1\u0010\r\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\bH\u0016¢\u0006\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0011R\u001b\u0010\u0017\u001a\u00020\u00128BX\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u0016\u0010\u0018\u001a\u00020\b8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u0016\u0010\u001a\u001a\u00020\b8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u001a\u0010\u0019¨\u0006\u001c"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/io/MP4MetaReader;", "Lcom/samsung/android/motionphoto/utils/v2/io/VideoMetaReader;", "Lcom/samsung/android/motionphoto/utils/v2/MediaFile;", "mediaFile", "<init>", "(Lcom/samsung/android/motionphoto/utils/v2/MediaFile;)V", "Ljava/io/FileInputStream;", "fis", "", "startOffset", "endOffset", "", "targetType", "findBoxOffset", "(Ljava/io/FileInputStream;JJLjava/lang/String;)Ljava/lang/Long;", "readSampleCount", "()J", "Lcom/samsung/android/motionphoto/utils/v2/MediaFile;", "Lcom/samsung/android/motionphoto/utils/v2/MotionPhotoInfoImpl;", "mpInfo$delegate", "Lme/f;", "getMpInfo", "()Lcom/samsung/android/motionphoto/utils/v2/MotionPhotoInfoImpl;", "mpInfo", "trakBoxSize", "J", "trakOffset", "Companion", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MP4MetaReader implements VideoMetaReader {
    public static final Companion Companion = new Companion((e) null);
    private static final String TAG;
    private final MediaFile mediaFile;
    private final f mpInfo$delegate = d.q(new q(9, this));
    private long trakBoxSize;
    private long trakOffset;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/io/MP4MetaReader$Companion;", "", "<init>", "()V", "TAG", "", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    static {
        String tagOf = SLog.tagOf(MP4MetaReader.class);
        j.d(tagOf, "tagOf(...)");
        TAG = tagOf;
    }

    public MP4MetaReader(MediaFile mediaFile2) {
        Throwable th;
        long j2;
        j.e(mediaFile2, "mediaFile");
        this.mediaFile = mediaFile2;
        FileInputStream newInputFileStream = mediaFile2.newInputFileStream();
        try {
            if (mediaFile2.getMimeType().isVideo()) {
                j2 = 0;
            } else if (!mediaFile2.getMimeType().isImage() || !getMpInfo().isValid()) {
                throw new UnsupportedOperationException();
            } else {
                j2 = getMpInfo().getVideoPosition();
            }
            newInputFileStream.getChannel().position(0);
            Long findBoxOffset = findBoxOffset(newInputFileStream, j2, newInputFileStream.getChannel().size(), MediaDefs.Video.MP4.MP4_MOOV_BOX);
            if (findBoxOffset != null) {
                Long findBoxOffset2 = findBoxOffset(newInputFileStream, ((long) 8) + findBoxOffset.longValue(), newInputFileStream.getChannel().size(), "trak");
                if (findBoxOffset2 != null) {
                    long longValue = findBoxOffset2.longValue();
                    newInputFileStream.getChannel().position(longValue);
                    this.trakOffset = longValue;
                    long long$default = CommonsKt.getLong$default(CommonsKt.readAsIntBuffer$default((InputStream) newInputFileStream, 1, (ByteOrder) null, 2, (Object) null), 0, 1, (Object) null);
                    this.trakBoxSize = long$default;
                    SLog.i(TAG, "find trakBoxSize=" + long$default + ", trakOffset=" + this.trakOffset);
                }
            }
            newInputFileStream.close();
        } catch (Throwable th2) {
            Throwable th3 = th2;
            a.g(newInputFileStream, th);
            throw th3;
        }
    }

    private final Long findBoxOffset(FileInputStream fileInputStream, long j2, long j3, String str) {
        FileInputStream fileInputStream2 = fileInputStream;
        long j8 = j2;
        long j10 = j3;
        String str2 = str;
        String str3 = TAG;
        StringBuilder j11 = N2.j.j(j8, "findBoxOffset: startOffset=", ", endOffset=");
        j11.append(j10);
        j11.append(", targetType=");
        j11.append(str2);
        SLog.i(str3, j11.toString());
        long j12 = j8;
        while (j12 < j10) {
            fileInputStream2.getChannel().position(j12);
            long long$default = CommonsKt.getLong$default(CommonsKt.readAsIntBuffer$default((InputStream) fileInputStream2, 1, (ByteOrder) null, 2, (Object) null), 0, 1, (Object) null);
            String str4 = TAG;
            SLog.i(str4, "boxSize=" + long$default);
            if (long$default != 0) {
                String readAsString = CommonsKt.readAsString((InputStream) fileInputStream2, 4);
                SLog.i(str4, "type=" + readAsString);
                if (long$default == 1) {
                    long$default = CommonsKt.readAsLongBuffer$default((InputStream) fileInputStream2, 1, (ByteOrder) null, 2, (Object) null).get();
                }
                if (j.a(readAsString, str2)) {
                    SLog.i(str4, "found type: " + str2 + " at " + j12);
                    return Long.valueOf(j12);
                }
                j12 += long$default;
            }
        }
        String str5 = TAG;
        SLog.i(str5, "not found type: " + str2 + " from " + j8 + " to " + j10);
        return null;
    }

    private final MotionPhotoInfoImpl getMpInfo() {
        return (MotionPhotoInfoImpl) this.mpInfo$delegate.getValue();
    }

    /* access modifiers changed from: private */
    public static final MotionPhotoInfoImpl mpInfo_delegate$lambda$0(MP4MetaReader mP4MetaReader) {
        return new MotionPhotoInfoImpl(mP4MetaReader.mediaFile);
    }

    public long readSampleCount() {
        Throwable th;
        long j2;
        String str = TAG;
        String path = this.mediaFile.path();
        SLog.i(str, "readSampleCount: " + path);
        FileInputStream newInputFileStream = this.mediaFile.newInputFileStream();
        try {
            long j3 = this.trakOffset;
            long j8 = (long) 8;
            Long findBoxOffset = findBoxOffset(newInputFileStream, j3 + j8, this.trakBoxSize + j3, "stsz");
            if (findBoxOffset != null) {
                long longValue = findBoxOffset.longValue();
                SLog.i(str, "stszOffset=" + longValue);
                newInputFileStream.getChannel().position(longValue);
                long j10 = (long) 4;
                newInputFileStream.getChannel().position(longValue + j8 + j10 + j10);
                CommonsKt.getLong$default(CommonsKt.readAsIntBuffer$default((InputStream) newInputFileStream, 1, (ByteOrder) null, 2, (Object) null), 0, 1, (Object) null);
                j2 = findBoxOffset.longValue();
            } else {
                j2 = 0;
            }
            a.g(newInputFileStream, (Throwable) null);
            return j2;
        } catch (Throwable th2) {
            a.g(newInputFileStream, th);
            throw th2;
        }
    }
}
