package com.samsung.android.motionphoto.utils.v2;

import L1.d;
import Tf.a;
import android.graphics.RectF;
import android.util.Log;
import androidx.core.util.Pair;
import ba.C0582a;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import com.samsung.android.motionphoto.utils.v2.MediaFile;
import com.samsung.android.sum.core.SLog;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.Map;
import java.util.function.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.f;
import me.i;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000¦\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010%\n\u0002\u0010$\n\u0002\b\u0006\n\u0002\u0010\u0012\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0019\u0018\u0000 g2\u00020\u0001:\u0001gB\u0011\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005B\u0011\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0004\u0010\bB\u0011\b\u0016\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u0004\u0010\u000bJ\u000f\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0013\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0016\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0019\u001a\u00020\u0018H\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ\u000f\u0010\u001c\u001a\u00020\u001bH\u0016¢\u0006\u0004\b\u001c\u0010\u001dJ\u000f\u0010\u001e\u001a\u00020\u001bH\u0016¢\u0006\u0004\b\u001e\u0010\u001dJ\u000f\u0010 \u001a\u00020\u001fH\u0016¢\u0006\u0004\b \u0010!J\u000f\u0010#\u001a\u00020\"H\u0016¢\u0006\u0004\b#\u0010$J\u000f\u0010%\u001a\u00020\"H\u0016¢\u0006\u0004\b%\u0010$J\u000f\u0010&\u001a\u00020\"H\u0016¢\u0006\u0004\b&\u0010$J\u000f\u0010(\u001a\u00020'H\u0016¢\u0006\u0004\b(\u0010)J\u000f\u0010*\u001a\u00020'H\u0016¢\u0006\u0004\b*\u0010)J\u0017\u0010-\u001a\n ,*\u0004\u0018\u00010+0+H\u0016¢\u0006\u0004\b-\u0010.J\u000f\u0010/\u001a\u00020'H\u0016¢\u0006\u0004\b/\u0010)J\u000f\u00100\u001a\u00020\u001fH\u0016¢\u0006\u0004\b0\u0010!J\u000f\u00101\u001a\u00020\"H\u0016¢\u0006\u0004\b1\u0010$JO\u00104\u001aB\u0012\f\u0012\n ,*\u0004\u0018\u00010\u001f0\u001f\u0012\f\u0012\n ,*\u0004\u0018\u00010'0' ,* \u0012\f\u0012\n ,*\u0004\u0018\u00010\u001f0\u001f\u0012\f\u0012\n ,*\u0004\u0018\u00010'0'\u0018\u00010302H\u0016¢\u0006\u0004\b4\u00105J\u000f\u00106\u001a\u00020\"H\u0016¢\u0006\u0004\b6\u0010$J\u000f\u00107\u001a\u00020'H\u0016¢\u0006\u0004\b7\u0010)J\u000f\u00108\u001a\u00020'H\u0016¢\u0006\u0004\b8\u0010)J\u000f\u00109\u001a\u00020'H\u0016¢\u0006\u0004\b9\u0010)J\u000f\u0010;\u001a\u00020:H\u0016¢\u0006\u0004\b;\u0010<J\u0017\u0010;\u001a\u00020'2\u0006\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b;\u0010=J\u0017\u0010;\u001a\u00020'2\u0006\u0010>\u001a\u00020\u0006H\u0016¢\u0006\u0004\b;\u0010?J\u000f\u0010@\u001a\u00020\"H\u0016¢\u0006\u0004\b@\u0010$J\u000f\u0010A\u001a\u00020'H\u0016¢\u0006\u0004\bA\u0010)J\u000f\u0010B\u001a\u00020'H\u0016¢\u0006\u0004\bB\u0010)J\u0011\u0010C\u001a\u0004\u0018\u00010+H\u0016¢\u0006\u0004\bC\u0010.J\u000f\u0010D\u001a\u00020'H\u0016¢\u0006\u0004\bD\u0010)J\u0011\u0010F\u001a\u0004\u0018\u00010EH\u0016¢\u0006\u0004\bF\u0010GJ-\u0010I\u001a \u0012\f\u0012\n ,*\u0004\u0018\u00010'0'\u0012\f\u0012\n ,*\u0004\u0018\u00010\"0\"\u0018\u00010HH\u0002¢\u0006\u0004\bI\u0010JJ\u001d\u0010K\u001a\u0010\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020\"\u0018\u00010HH\u0002¢\u0006\u0004\bK\u0010JJ\u001d\u0010P\u001a\u00020O2\f\u0010N\u001a\b\u0012\u0004\u0012\u00020M0LH\u0002¢\u0006\u0004\bP\u0010QR\u001a\u0010\u0003\u001a\u00020\u00028\u0000X\u0004¢\u0006\f\n\u0004\b\u0003\u0010R\u001a\u0004\bS\u0010TR\u001b\u0010X\u001a\u00020\u000f8BX\u0002¢\u0006\f\n\u0004\bU\u0010V\u001a\u0004\bW\u0010\u0011R\u001b\u0010[\u001a\u00020\u00128BX\u0002¢\u0006\f\n\u0004\bY\u0010V\u001a\u0004\bZ\u0010\u0014R\u001b\u0010^\u001a\u00020\f8BX\u0002¢\u0006\f\n\u0004\b\\\u0010V\u001a\u0004\b]\u0010\u000eR\u001b\u0010a\u001a\u00020\u00158BX\u0002¢\u0006\f\n\u0004\b_\u0010V\u001a\u0004\b`\u0010\u0017R\u001b\u0010d\u001a\u00020\u00158BX\u0002¢\u0006\f\n\u0004\bb\u0010V\u001a\u0004\bc\u0010\u0017R\u0016\u0010e\u001a\u00020\u001b8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\be\u0010f¨\u0006h"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/MotionPhotoInfoImpl;", "Lcom/samsung/android/motionphoto/utils/v2/MotionPhotoInfo;", "Lcom/samsung/android/motionphoto/utils/v2/MediaFile;", "mediaFile", "<init>", "(Lcom/samsung/android/motionphoto/utils/v2/MediaFile;)V", "Ljava/io/FileDescriptor;", "fd", "(Ljava/io/FileDescriptor;)V", "Ljava/io/File;", "file", "(Ljava/io/File;)V", "Lcom/samsung/android/motionphoto/utils/v2/XMPInfo;", "getXMPInfo", "()Lcom/samsung/android/motionphoto/utils/v2/XMPInfo;", "Lcom/samsung/android/motionphoto/utils/v2/SEFInfo;", "getSEFInfo", "()Lcom/samsung/android/motionphoto/utils/v2/SEFInfo;", "Lcom/samsung/android/motionphoto/utils/v2/ExifInfo;", "getExifInfo", "()Lcom/samsung/android/motionphoto/utils/v2/ExifInfo;", "Lcom/samsung/android/motionphoto/utils/v2/VideoInfo;", "getVideoInfo", "()Lcom/samsung/android/motionphoto/utils/v2/VideoInfo;", "", "isValid", "()Z", "Lcom/samsung/android/motionphoto/utils/v2/MotionPhotoFormat;", "getSEFMotionPhotoVersion", "()Lcom/samsung/android/motionphoto/utils/v2/MotionPhotoFormat;", "getXMPMotionPhotoVersion", "Lcom/samsung/android/motionphoto/utils/v2/MimeType;", "getImageMimeType", "()Lcom/samsung/android/motionphoto/utils/v2/MimeType;", "", "getImageWidth", "()I", "getImageHeight", "getImageRotation", "", "getFileSize", "()J", "getImageSize", "", "kotlin.jvm.PlatformType", "getDateTimeTaken", "()Ljava/lang/String;", "getCaptureTimestampUs", "getVideoMimeType", "getNumTracksOfVideo", "", "", "getTrackDurationsOfVideo", "()Ljava/util/Map;", "getVideoBitrate", "getVideoDurationUs", "getVideoPosition", "getVideoSize", "", "getVideo", "()[B", "(Ljava/io/File;)J", "fileDescriptor", "(Ljava/io/FileDescriptor;)J", "getAutoPlayVideoBitrate", "getAutoPlayVideoPosition", "getAutoPlayVideoSize", "getExtraInfo", "getImageVideoPadding", "Landroid/graphics/RectF;", "getCropRect", "()Landroid/graphics/RectF;", "Lme/i;", "getVideoInfoFromSEF", "()Lme/i;", "getVideoInfoFromXMP", "", "Lcom/samsung/android/motionphoto/utils/v2/SEFDataInfo;", "sefDataInfos", "Lme/x;", "parseExtraOfMotionPhotoFromSEF", "(Ljava/util/List;)V", "Lcom/samsung/android/motionphoto/utils/v2/MediaFile;", "getMediaFile$motionphoto_utils_v2_0_17_release", "()Lcom/samsung/android/motionphoto/utils/v2/MediaFile;", "sefInfo$delegate", "Lme/f;", "getSefInfo", "sefInfo", "exifIno$delegate", "getExifIno", "exifIno", "xmpInfo$delegate", "getXmpInfo", "xmpInfo", "_videoInfo$delegate", "get_videoInfo", "_videoInfo", "autoPlayVideoInfo$delegate", "getAutoPlayVideoInfo", "autoPlayVideoInfo", "sefMPVersion", "Lcom/samsung/android/motionphoto/utils/v2/MotionPhotoFormat;", "Companion", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MotionPhotoInfoImpl implements MotionPhotoInfo {
    public static final Companion Companion = new Companion((e) null);
    private static final int MOTION_PHOTO_MIN_VIDEO_SIZE = 125000;
    private static final String TAG;
    private final f _videoInfo$delegate;
    private final f autoPlayVideoInfo$delegate;
    private final f exifIno$delegate;
    private final MediaFile mediaFile;
    private final f sefInfo$delegate;
    private MotionPhotoFormat sefMPVersion;
    private final f xmpInfo$delegate;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/MotionPhotoInfoImpl$Companion;", "", "<init>", "()V", "TAG", "", "MOTION_PHOTO_MIN_VIDEO_SIZE", "", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        static {
            /*
                com.samsung.android.motionphoto.utils.v2.MimeType[] r0 = com.samsung.android.motionphoto.utils.v2.MimeType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.samsung.android.motionphoto.utils.v2.MimeType r1 = com.samsung.android.motionphoto.utils.v2.MimeType.IMAGE_JPEG     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.samsung.android.motionphoto.utils.v2.MimeType r1 = com.samsung.android.motionphoto.utils.v2.MimeType.IMAGE_HEIC     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.motionphoto.utils.v2.MotionPhotoInfoImpl.WhenMappings.<clinit>():void");
        }
    }

    static {
        String tagOf = SLog.tagOf(MotionPhotoInfoImpl.class);
        j.d(tagOf, "tagOf(...)");
        TAG = tagOf;
    }

    public MotionPhotoInfoImpl(MediaFile mediaFile2) {
        j.e(mediaFile2, "mediaFile");
        this.mediaFile = mediaFile2;
        this.sefInfo$delegate = d.q(new j(this, 0));
        this.exifIno$delegate = d.q(new j(this, 1));
        this.xmpInfo$delegate = d.q(new j(this, 2));
        this._videoInfo$delegate = d.q(new j(this, 3));
        this.autoPlayVideoInfo$delegate = d.q(new j(this, 4));
        this.sefMPVersion = MotionPhotoFormat.NONE;
    }

    /* access modifiers changed from: private */
    public static final VideoInfoImpl _videoInfo_delegate$lambda$3(MotionPhotoInfoImpl motionPhotoInfoImpl) {
        i iVar;
        Pair<Long, Integer> dataPositionLength = motionPhotoInfoImpl.getSefInfo().getDataPositionLength(2608);
        if (dataPositionLength != null) {
            iVar = new i(dataPositionLength.first, dataPositionLength.second);
        } else {
            iVar = new i(0L, 0);
        }
        Long l = (Long) iVar.d;
        MediaFile mediaFile2 = motionPhotoInfoImpl.mediaFile;
        j.b(l);
        return new VideoInfoImpl(mediaFile2, l.longValue(), (long) ((Integer) iVar.e).intValue());
    }

    /* access modifiers changed from: private */
    public static final VideoInfo autoPlayVideoInfo_delegate$lambda$5(MotionPhotoInfoImpl motionPhotoInfoImpl) {
        Pair<Long, Integer> dataPositionLength = motionPhotoInfoImpl.getSefInfo().getDataPositionLength(2611);
        if (dataPositionLength == null) {
            return new MotionPhotoInfoImpl$autoPlayVideoInfo$2$2();
        }
        Long l = (Long) dataPositionLength.first;
        MediaFile mediaFile2 = motionPhotoInfoImpl.mediaFile;
        j.b(l);
        return new VideoInfoImpl(mediaFile2, l.longValue(), (long) ((Integer) dataPositionLength.second).intValue());
    }

    /* access modifiers changed from: private */
    public static final ExifInfoImpl exifIno_delegate$lambda$1(MotionPhotoInfoImpl motionPhotoInfoImpl) {
        return new ExifInfoImpl(motionPhotoInfoImpl.mediaFile);
    }

    private final VideoInfo getAutoPlayVideoInfo() {
        return (VideoInfo) this.autoPlayVideoInfo$delegate.getValue();
    }

    private final ExifInfo getExifIno() {
        return (ExifInfo) this.exifIno$delegate.getValue();
    }

    private final SEFInfo getSefInfo() {
        return (SEFInfo) this.sefInfo$delegate.getValue();
    }

    private final i getVideoInfoFromSEF() {
        Pair<Long, Integer> dataPositionLength = getSefInfo().getDataPositionLength(2608);
        if (dataPositionLength != null) {
            return new i(dataPositionLength.first, dataPositionLength.second);
        }
        return null;
    }

    private final i getVideoInfoFromXMP() {
        String itemField = getXmpInfo().getItemField(MediaDefs.Meta.XMP.XMP_KEY_MOTION_PHOTO, MediaDefs.Meta.XMP.XMP_KEY_LENGTH);
        if (itemField == null) {
            return null;
        }
        long parseLong = Long.parseLong(itemField);
        return new i(Long.valueOf(this.mediaFile.size() - parseLong), Integer.valueOf((int) parseLong));
    }

    private final XMPInfo getXmpInfo() {
        return (XMPInfo) this.xmpInfo$delegate.getValue();
    }

    private final VideoInfo get_videoInfo() {
        return (VideoInfo) this._videoInfo$delegate.getValue();
    }

    /* access modifiers changed from: private */
    public static final boolean isValid$lambda$9$lambda$8(Long l, FileChannel fileChannel) {
        j.e(fileChannel, "it");
        j.b(l);
        fileChannel.position(l.longValue());
        MediaFile.Companion companion = MediaFile.Companion;
        boolean isMP4Video = companion.isMP4Video(fileChannel);
        fileChannel.position(l.longValue());
        boolean isMOVVideo = companion.isMOVVideo(fileChannel);
        if (isMP4Video || isMOVVideo) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0044, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0045, code lost:
        B1.a.g(r3, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0048, code lost:
        throw r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void parseExtraOfMotionPhotoFromSEF(java.util.List<com.samsung.android.motionphoto.utils.v2.SEFDataInfo> r8) {
        /*
            r7 = this;
            java.lang.Iterable r8 = (java.lang.Iterable) r8
            java.util.Iterator r0 = r8.iterator()
        L_0x0006:
            boolean r1 = r0.hasNext()
            r2 = 0
            if (r1 == 0) goto L_0x001d
            java.lang.Object r1 = r0.next()
            r3 = r1
            com.samsung.android.motionphoto.utils.v2.SEFDataInfo r3 = (com.samsung.android.motionphoto.utils.v2.SEFDataInfo) r3
            int r3 = r3.getType()
            r4 = 2609(0xa31, float:3.656E-42)
            if (r3 != r4) goto L_0x0006
            goto L_0x001e
        L_0x001d:
            r1 = r2
        L_0x001e:
            com.samsung.android.motionphoto.utils.v2.SEFDataInfo r1 = (com.samsung.android.motionphoto.utils.v2.SEFDataInfo) r1
            r0 = 4
            if (r1 == 0) goto L_0x0049
            com.samsung.android.motionphoto.utils.v2.MediaFile r3 = r7.mediaFile
            java.io.FileInputStream r3 = r3.newInputFileStream()
            java.nio.channels.FileChannel r4 = r3.getChannel()     // Catch:{ all -> 0x0042 }
            long r5 = r1.getDataPosition()     // Catch:{ all -> 0x0042 }
            r4.position(r5)     // Catch:{ all -> 0x0042 }
            java.lang.String r1 = com.samsung.android.motionphoto.utils.v2.CommonsKt.readAsString((java.io.InputStream) r3, (int) r0)     // Catch:{ all -> 0x0042 }
            com.samsung.android.motionphoto.utils.v2.MotionPhotoFormat r1 = com.samsung.android.motionphoto.utils.v2.MotionPhotoFormat.of((java.lang.String) r1)     // Catch:{ all -> 0x0042 }
            r7.sefMPVersion = r1     // Catch:{ all -> 0x0042 }
            B1.a.g(r3, r2)
            goto L_0x0049
        L_0x0042:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x0044 }
        L_0x0044:
            r8 = move-exception
            B1.a.g(r3, r7)
            throw r8
        L_0x0049:
            java.util.Iterator r8 = r8.iterator()
        L_0x004d:
            boolean r1 = r8.hasNext()
            if (r1 == 0) goto L_0x0063
            java.lang.Object r1 = r8.next()
            r3 = r1
            com.samsung.android.motionphoto.utils.v2.SEFDataInfo r3 = (com.samsung.android.motionphoto.utils.v2.SEFDataInfo) r3
            int r3 = r3.getType()
            r4 = 2608(0xa30, float:3.655E-42)
            if (r3 != r4) goto L_0x004d
            goto L_0x0064
        L_0x0063:
            r1 = r2
        L_0x0064:
            com.samsung.android.motionphoto.utils.v2.SEFDataInfo r1 = (com.samsung.android.motionphoto.utils.v2.SEFDataInfo) r1
            if (r1 == 0) goto L_0x00ca
            com.samsung.android.motionphoto.utils.v2.MediaFile r8 = r7.mediaFile
            java.io.FileInputStream r8 = r8.newInputFileStream()
            java.nio.channels.FileChannel r3 = r8.getChannel()     // Catch:{ all -> 0x0095 }
            long r4 = r1.getDataPosition()     // Catch:{ all -> 0x0095 }
            r3.position(r4)     // Catch:{ all -> 0x0095 }
            java.lang.String r0 = com.samsung.android.motionphoto.utils.v2.CommonsKt.readAsString((java.io.InputStream) r8, (int) r0)     // Catch:{ all -> 0x0095 }
            com.samsung.android.motionphoto.utils.v2.MotionPhotoFormat r3 = r7.sefMPVersion     // Catch:{ all -> 0x0095 }
            com.samsung.android.motionphoto.utils.v2.MotionPhotoFormat r4 = com.samsung.android.motionphoto.utils.v2.MotionPhotoFormat.NONE     // Catch:{ all -> 0x0095 }
            if (r3 != r4) goto L_0x0097
            com.samsung.android.motionphoto.utils.v2.MotionPhotoFormat r3 = com.samsung.android.motionphoto.utils.v2.MotionPhotoFormat.V2     // Catch:{ all -> 0x0095 }
            java.lang.String r4 = r3.getVersion()     // Catch:{ all -> 0x0095 }
            boolean r0 = kotlin.jvm.internal.j.a(r0, r4)     // Catch:{ all -> 0x0095 }
            if (r0 == 0) goto L_0x0090
            goto L_0x0092
        L_0x0090:
            com.samsung.android.motionphoto.utils.v2.MotionPhotoFormat r3 = com.samsung.android.motionphoto.utils.v2.MotionPhotoFormat.V1     // Catch:{ all -> 0x0095 }
        L_0x0092:
            r7.sefMPVersion = r3     // Catch:{ all -> 0x0095 }
            goto L_0x0097
        L_0x0095:
            r7 = move-exception
            goto L_0x00c4
        L_0x0097:
            com.samsung.android.motionphoto.utils.v2.MediaFile r0 = r7.mediaFile     // Catch:{ all -> 0x0095 }
            com.samsung.android.motionphoto.utils.v2.MimeType r0 = r0.getMimeType()     // Catch:{ all -> 0x0095 }
            com.samsung.android.motionphoto.utils.v2.MimeType r3 = com.samsung.android.motionphoto.utils.v2.MimeType.IMAGE_HEIC     // Catch:{ all -> 0x0095 }
            if (r0 != r3) goto L_0x00c0
            com.samsung.android.motionphoto.utils.v2.MotionPhotoFormat r7 = r7.sefMPVersion     // Catch:{ all -> 0x0095 }
            com.samsung.android.motionphoto.utils.v2.MotionPhotoFormat r0 = com.samsung.android.motionphoto.utils.v2.MotionPhotoFormat.V1     // Catch:{ all -> 0x0095 }
            if (r7 == r0) goto L_0x00c0
            r7 = 2
            r0 = 1
            java.nio.IntBuffer r3 = com.samsung.android.motionphoto.utils.v2.CommonsKt.readAsIntBuffer$default((java.io.InputStream) r8, (int) r0, (java.nio.ByteOrder) r2, (int) r7, (java.lang.Object) r2)     // Catch:{ all -> 0x0095 }
            int r3 = r3.get()     // Catch:{ all -> 0x0095 }
            long r3 = (long) r3     // Catch:{ all -> 0x0095 }
            r1.setDataPosition(r3)     // Catch:{ all -> 0x0095 }
            java.nio.IntBuffer r7 = com.samsung.android.motionphoto.utils.v2.CommonsKt.readAsIntBuffer$default((java.io.InputStream) r8, (int) r0, (java.nio.ByteOrder) r2, (int) r7, (java.lang.Object) r2)     // Catch:{ all -> 0x0095 }
            int r7 = r7.get()     // Catch:{ all -> 0x0095 }
            r1.setDataPayload(r7)     // Catch:{ all -> 0x0095 }
        L_0x00c0:
            B1.a.g(r8, r2)
            return
        L_0x00c4:
            throw r7     // Catch:{ all -> 0x00c5 }
        L_0x00c5:
            r0 = move-exception
            B1.a.g(r8, r7)
            throw r0
        L_0x00ca:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.motionphoto.utils.v2.MotionPhotoInfoImpl.parseExtraOfMotionPhotoFromSEF(java.util.List):void");
    }

    /* access modifiers changed from: private */
    public static final SEFInfoImpl sefInfo_delegate$lambda$0(MotionPhotoInfoImpl motionPhotoInfoImpl) {
        return new SEFInfoImpl(motionPhotoInfoImpl.mediaFile, new C0582a(14, motionPhotoInfoImpl));
    }

    /* access modifiers changed from: private */
    public static final XMPInfoImpl xmpInfo_delegate$lambda$2(MotionPhotoInfoImpl motionPhotoInfoImpl) {
        return new XMPInfoImpl(motionPhotoInfoImpl.mediaFile, motionPhotoInfoImpl.getExifIno().getXMP());
    }

    public int getAutoPlayVideoBitrate() {
        return getAutoPlayVideoInfo().getBitrate();
    }

    public long getAutoPlayVideoPosition() {
        Long l;
        Pair<Long, Integer> dataPositionLength = getSefInfo().getDataPositionLength(2611);
        if (dataPositionLength == null || (l = (Long) dataPositionLength.first) == null) {
            return 0;
        }
        return l.longValue();
    }

    public long getAutoPlayVideoSize() {
        Integer num;
        Pair<Long, Integer> dataPositionLength = getSefInfo().getDataPositionLength(2611);
        if (dataPositionLength == null || (num = (Integer) dataPositionLength.second) == null) {
            return 0;
        }
        return (long) num.intValue();
    }

    public long getCaptureTimestampUs() {
        String property = getXmpInfo().getProperty(MediaDefs.Meta.XMP.XMP_GOOGLE_CAMERA_NS, "MotionPhotoPresentationTimestampUs");
        if (property != null) {
            return Long.parseLong(property);
        }
        return 0;
    }

    public RectF getCropRect() {
        byte[] data = new SEFInfoImpl(this.mediaFile, new Consumer[0]).getData(2610);
        if (data != null) {
            JSONObject jSONObject = new JSONObject(new String(data, a.f3815a));
            JSONObject jSONObject2 = jSONObject.getJSONObject("crop-rect");
            int i2 = jSONObject2.getInt("left");
            int i7 = jSONObject2.getInt("top");
            int i8 = jSONObject2.getInt("right");
            int i10 = jSONObject2.getInt("bottom");
            JSONObject jSONObject3 = jSONObject.getJSONObject("video-info");
            int i11 = jSONObject3.getInt("org-width");
            int i12 = jSONObject3.getInt("org-height");
            String str = TAG;
            StringBuilder h5 = A.a.h(i2, i7, "rect:\n                | left/top - right/bottom : ", "/", " - ");
            N2.j.x(h5, i8, "/", i10, ",\n                | w/h : ");
            h5.append(i11);
            h5.append("/");
            h5.append(i12);
            h5.append("\n            ");
            Log.d(str, CommonsKt.trimToOneLine(h5.toString()));
            if ((i8 == 0 && i10 == 0) || i11 == 0 || i12 == 0) {
                Log.w(str, "crop info is not valid, rect:" + jSONObject2);
            } else if (i2 < 0 || i7 < 0 || i8 < 0 || i10 < 0) {
                Log.w(str, "negative coordinates detected in crop rect: " + jSONObject2);
                return null;
            } else if (i2 > i8 || i7 > i10) {
                StringBuilder h6 = A.a.h(i2, i8, "invalid crop rect dimensions: left=", ", right=", ", top=");
                h6.append(i7);
                h6.append(", bottom=");
                h6.append(i10);
                Log.w(str, h6.toString());
                return null;
            } else if (i8 > i11 || i10 > i12) {
                StringBuilder h8 = A.a.h(i8, i11, "crop rect exceeds video bounds: right=", " vs ", ", bottom=");
                h8.append(i10);
                h8.append(" vs ");
                h8.append(i12);
                Log.w(str, h8.toString());
                return null;
            } else {
                float f = (float) i11;
                float f5 = ((float) i2) / f;
                float f8 = (float) i12;
                float f10 = ((float) i7) / f8;
                float f11 = ((float) i8) / f;
                float f12 = ((float) i10) / f8;
                Log.d(str, CommonsKt.trimToOneLine("rect:\n                | left/top - right/bottom : " + f5 + "/" + f10 + " - " + f11 + "/" + f12 + ",\n            "));
                return new RectF(f5, f10, f11, f12);
            }
        }
        return null;
    }

    public String getDateTimeTaken() {
        return getExifIno().getDateTimeTaken();
    }

    public ExifInfo getExifInfo() {
        return getExifIno();
    }

    public String getExtraInfo() {
        byte[] data = getSefInfo().getData("MotionPhoto_Info");
        if (data != null) {
            return new String(data, a.f3815a);
        }
        return null;
    }

    public long getFileSize() {
        return this.mediaFile.size();
    }

    public int getImageHeight() {
        return getExifIno().getHeight();
    }

    public MimeType getImageMimeType() {
        return this.mediaFile.getMimeType();
    }

    public int getImageRotation() {
        return getExifIno().getRotation();
    }

    public long getImageSize() {
        SEFInfo sefInfo = getSefInfo();
        j.c(sefInfo, "null cannot be cast to non-null type com.samsung.android.motionphoto.utils.v2.SEFInfoImpl");
        return ((SEFInfoImpl) sefInfo).getImageSize$motionphoto_utils_v2_0_17_release();
    }

    public long getImageVideoPadding() {
        int i2 = WhenMappings.$EnumSwitchMapping$0[getImageMimeType().ordinal()];
        if (i2 == 1) {
            return getVideoPosition() - getImageSize();
        }
        if (i2 != 2) {
            return 0;
        }
        return 8;
    }

    public int getImageWidth() {
        return getExifIno().getWidth();
    }

    public final MediaFile getMediaFile$motionphoto_utils_v2_0_17_release() {
        return this.mediaFile;
    }

    public int getNumTracksOfVideo() {
        return getVideoInfo().numberOfTracks();
    }

    public SEFInfo getSEFInfo() {
        return getSefInfo();
    }

    public MotionPhotoFormat getSEFMotionPhotoVersion() {
        getSefInfo().getVersion();
        return this.sefMPVersion;
    }

    public Map<MimeType, Long> getTrackDurationsOfVideo() {
        return getVideoInfo().getTrackDurations();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0039, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003a, code lost:
        B1.a.g(r4, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003d, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] getVideo() {
        /*
            r4 = this;
            com.samsung.android.motionphoto.utils.v2.SEFInfo r0 = r4.getSefInfo()     // Catch:{ Exception -> 0x000b }
            r1 = 2608(0xa30, float:3.655E-42)
            byte[] r4 = r0.getData((int) r1)     // Catch:{ Exception -> 0x000b }
            return r4
        L_0x000b:
            me.i r0 = r4.getVideoInfoFromXMP()
            kotlin.jvm.internal.j.b(r0)
            java.lang.Object r1 = r0.d
            java.lang.Number r1 = (java.lang.Number) r1
            long r1 = r1.longValue()
            java.lang.Object r0 = r0.e
            java.lang.Number r0 = (java.lang.Number) r0
            int r0 = r0.intValue()
            com.samsung.android.motionphoto.utils.v2.MediaFile r4 = r4.mediaFile
            java.io.FileInputStream r4 = r4.newInputFileStream()
            java.nio.channels.FileChannel r3 = r4.getChannel()     // Catch:{ all -> 0x0037 }
            r3.position(r1)     // Catch:{ all -> 0x0037 }
            byte[] r0 = r4.readNBytes(r0)     // Catch:{ all -> 0x0037 }
            r4.close()
            return r0
        L_0x0037:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0039 }
        L_0x0039:
            r1 = move-exception
            B1.a.g(r4, r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.motionphoto.utils.v2.MotionPhotoInfoImpl.getVideo():byte[]");
    }

    public int getVideoBitrate() {
        return getVideoInfo().getBitrate();
    }

    public long getVideoDurationUs() {
        return getVideoInfo().getVideoDurationMs() * 1000;
    }

    public VideoInfo getVideoInfo() {
        return get_videoInfo();
    }

    public MimeType getVideoMimeType() {
        MimeType mimeType = getVideoInfo().getMimeType();
        if (mimeType == null) {
            return MimeType.NONE;
        }
        return mimeType;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0053, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0054, code lost:
        B1.a.g(r0, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0057, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long getVideoPosition() {
        /*
            r6 = this;
            me.i r0 = r6.getVideoInfoFromSEF()
            r1 = 0
            r2 = 0
            if (r0 != 0) goto L_0x001c
            me.i r0 = r6.getVideoInfoFromXMP()
            if (r0 != 0) goto L_0x001c
            me.i r0 = new me.i
            java.lang.Long r4 = java.lang.Long.valueOf(r2)
            java.lang.Integer r5 = java.lang.Integer.valueOf(r1)
            r0.<init>(r4, r5)
        L_0x001c:
            java.lang.Object r4 = r0.d
            java.lang.Long r4 = (java.lang.Long) r4
            java.lang.Object r0 = r0.e
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r0 = r0.intValue()
            r5 = 125000(0x1e848, float:1.75162E-40)
            if (r0 > r5) goto L_0x0059
            com.samsung.android.motionphoto.utils.v2.SEFInfo r6 = r6.getSefInfo()
            r0 = 2608(0xa30, float:3.655E-42)
            byte[] r6 = r6.getData((int) r0)
            if (r6 == 0) goto L_0x0058
            java.io.ByteArrayInputStream r0 = new java.io.ByteArrayInputStream
            r0.<init>(r6)
            r6 = 4
            com.samsung.android.motionphoto.utils.v2.CommonsKt.readAsString((java.io.InputStream) r0, (int) r6)     // Catch:{ all -> 0x0051 }
            r6 = 2
            r2 = 1
            r3 = 0
            java.nio.IntBuffer r6 = com.samsung.android.motionphoto.utils.v2.CommonsKt.readAsIntBuffer$default((java.io.InputStream) r0, (int) r2, (java.nio.ByteOrder) r3, (int) r6, (java.lang.Object) r3)     // Catch:{ all -> 0x0051 }
            long r1 = com.samsung.android.motionphoto.utils.v2.CommonsKt.getLong$default(r6, r1, r2, r3)     // Catch:{ all -> 0x0051 }
            r0.close()
            return r1
        L_0x0051:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x0053 }
        L_0x0053:
            r1 = move-exception
            B1.a.g(r0, r6)
            throw r1
        L_0x0058:
            return r2
        L_0x0059:
            kotlin.jvm.internal.j.b(r4)
            long r0 = r4.longValue()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.motionphoto.utils.v2.MotionPhotoInfoImpl.getVideoPosition():long");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004f, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0050, code lost:
        B1.a.g(r0, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0053, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long getVideoSize() {
        /*
            r5 = this;
            me.i r0 = r5.getVideoInfoFromSEF()
            r1 = 0
            if (r0 != 0) goto L_0x001c
            me.i r0 = r5.getVideoInfoFromXMP()
            if (r0 != 0) goto L_0x001c
            me.i r0 = new me.i
            java.lang.Long r3 = java.lang.Long.valueOf(r1)
            r4 = 0
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r0.<init>(r3, r4)
        L_0x001c:
            java.lang.Object r0 = r0.e
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r3 = r0.intValue()
            r4 = 125000(0x1e848, float:1.75162E-40)
            if (r3 > r4) goto L_0x0055
            com.samsung.android.motionphoto.utils.v2.SEFInfo r5 = r5.getSefInfo()
            r0 = 2608(0xa30, float:3.655E-42)
            byte[] r5 = r5.getData((int) r0)
            if (r5 == 0) goto L_0x0054
            java.io.ByteArrayInputStream r0 = new java.io.ByteArrayInputStream
            r0.<init>(r5)
            r5 = 4
            com.samsung.android.motionphoto.utils.v2.CommonsKt.readAsString((java.io.InputStream) r0, (int) r5)     // Catch:{ all -> 0x004d }
            r5 = 0
            r1 = 2
            java.nio.IntBuffer r5 = com.samsung.android.motionphoto.utils.v2.CommonsKt.readAsIntBuffer$default((java.io.InputStream) r0, (int) r1, (java.nio.ByteOrder) r5, (int) r1, (java.lang.Object) r5)     // Catch:{ all -> 0x004d }
            r1 = 1
            long r1 = com.samsung.android.motionphoto.utils.v2.CommonsKt.getLong(r5, r1)     // Catch:{ all -> 0x004d }
            r0.close()
            return r1
        L_0x004d:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x004f }
        L_0x004f:
            r1 = move-exception
            B1.a.g(r0, r5)
            throw r1
        L_0x0054:
            return r1
        L_0x0055:
            int r5 = r0.intValue()
            long r0 = (long) r5
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.motionphoto.utils.v2.MotionPhotoInfoImpl.getVideoSize():long");
    }

    public XMPInfo getXMPInfo() {
        return getXmpInfo();
    }

    public MotionPhotoFormat getXMPMotionPhotoVersion() {
        MotionPhotoFormat of2;
        String property = getXmpInfo().getProperty(MediaDefs.Meta.XMP.XMP_GOOGLE_CAMERA_NS, MediaDefs.Meta.XMP.XMP_MOTION_PHOTO_FORMAT_VERSION);
        if (property == null || (of2 = MotionPhotoFormat.of(Integer.parseInt(property))) == null) {
            return MotionPhotoFormat.NONE;
        }
        return of2;
    }

    public boolean isValid() {
        Pair<Long, Integer> dataPositionLength = getSefInfo().getDataPositionLength(2608);
        if (dataPositionLength != null) {
            return ((Boolean) this.mediaFile.useInputFileChannel(new Ad.f(5, (Object) (Long) dataPositionLength.first))).booleanValue();
        }
        return false;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public MotionPhotoInfoImpl(FileDescriptor fileDescriptor) {
        this(new MediaFile(fileDescriptor));
        j.e(fileDescriptor, "fd");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public MotionPhotoInfoImpl(File file) {
        this(new MediaFile(file));
        j.e(file, "file");
    }

    /* JADX INFO: finally extract failed */
    public long getVideo(File file) {
        Throwable th;
        Throwable th2;
        j.e(file, "file");
        i videoInfoFromSEF = getVideoInfoFromSEF();
        if (videoInfoFromSEF == null) {
            videoInfoFromSEF = getVideoInfoFromXMP();
            j.b(videoInfoFromSEF);
        }
        Long l = (Long) videoInfoFromSEF.d;
        Integer num = (Integer) videoInfoFromSEF.e;
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            FileInputStream newInputFileStream = this.mediaFile.newInputFileStream();
            try {
                FileChannel channel = newInputFileStream.getChannel();
                j.d(channel, "getChannel(...)");
                j.b(l);
                FileChannel channel2 = fileOutputStream.getChannel();
                j.d(channel2, "getChannel(...)");
                CommonsKt.transferAllTo(channel, l.longValue(), (long) num.intValue(), channel2);
                B1.a.g(newInputFileStream, (Throwable) null);
                fileOutputStream.close();
                return (long) num.intValue();
            } catch (Throwable th3) {
                B1.a.g(newInputFileStream, th2);
                throw th3;
            }
        } catch (Throwable th4) {
            Throwable th5 = th4;
            B1.a.g(fileOutputStream, th);
            throw th5;
        }
    }

    /* JADX INFO: finally extract failed */
    public long getVideo(FileDescriptor fileDescriptor) {
        Throwable th;
        Throwable th2;
        j.e(fileDescriptor, "fileDescriptor");
        i videoInfoFromSEF = getVideoInfoFromSEF();
        if (videoInfoFromSEF == null) {
            videoInfoFromSEF = getVideoInfoFromXMP();
            j.b(videoInfoFromSEF);
        }
        Long l = (Long) videoInfoFromSEF.d;
        Integer num = (Integer) videoInfoFromSEF.e;
        FileOutputStream fileOutputStream = new FileOutputStream(fileDescriptor);
        try {
            FileInputStream newInputFileStream = this.mediaFile.newInputFileStream();
            try {
                FileChannel channel = newInputFileStream.getChannel();
                j.d(channel, "getChannel(...)");
                j.b(l);
                FileChannel channel2 = fileOutputStream.getChannel();
                j.d(channel2, "getChannel(...)");
                CommonsKt.transferAllTo(channel, l.longValue(), (long) num.intValue(), channel2);
                B1.a.g(newInputFileStream, (Throwable) null);
                fileOutputStream.close();
                return (long) num.intValue();
            } catch (Throwable th3) {
                B1.a.g(newInputFileStream, th2);
                throw th3;
            }
        } catch (Throwable th4) {
            Throwable th5 = th4;
            B1.a.g(fileOutputStream, th);
            throw th5;
        }
    }
}
