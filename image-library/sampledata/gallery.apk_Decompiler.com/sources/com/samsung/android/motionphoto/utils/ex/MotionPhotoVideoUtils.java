package com.samsung.android.motionphoto.utils.ex;

import A8.C0545b;
import Ad.C0720a;
import N2.j;
import android.graphics.RectF;
import android.media.ExifInterface;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.media.MediaMetadataRetriever;
import android.util.Log;
import c4.C0431a;
import com.adobe.internal.xmp.XMPException;
import com.adobe.internal.xmp.XMPMeta;
import com.adobe.internal.xmp.XMPMetaFactory;
import com.adobe.internal.xmp.options.SerializeOptions;
import com.adobe.internal.xmp.properties.XMPProperty;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.media.SemExtendedFormat;
import com.samsung.android.motionphoto.utils.ex.MotionPhotoParser;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import com.samsung.android.sdk.cover.ScoverState;
import i.C0212a;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MotionPhotoVideoUtils {
    private static final long EFFECT_MIN_TIME = 500000;
    private static final long MIN_DIFF_TIME_IMAGE_AND_VIDEO = 100000;
    private static final long MOTION_PHOTO_MIN_DURATION = 1000000;
    private static final String TAG = "MotionPhotoVideoUtilsEx";

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class AutoPlayVideoInfo {
        public long videoLength;
        public long videoOffset;

        public AutoPlayVideoInfo(long j2, long j3) {
            this.videoOffset = j2;
            this.videoLength = j3;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum EffectType {
        BOMERANG,
        SLOWMO
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ItemInfo {
        public long duration;
        public long exifDateTimeOriginalMs;
        public File file;

        public ItemInfo(File file2, long j2, long j3) {
            this.file = file2;
            this.exifDateTimeOriginalMs = j2;
            this.duration = j3;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class OrderedItemList implements Comparator<OrderedItemList> {
        long creationTimeMs;
        long duration;
        File file;
        long fileStartTimeMs;

        public OrderedItemList() {
        }

        public OrderedItemList(File file2, long j2, long j3, long j8) {
            this.file = file2;
            this.fileStartTimeMs = j2;
            this.creationTimeMs = j3;
            this.duration = j8;
        }

        public int compare(OrderedItemList orderedItemList, OrderedItemList orderedItemList2) {
            return (int) (orderedItemList.fileStartTimeMs - orderedItemList2.fileStartTimeMs);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PlaybackInfo {
        public long endPosition;
        public float speed;
        public long startPosition;

        public PlaybackInfo(long j2, long j3, float f) {
            this.startPosition = j2;
            this.endPosition = j3;
            this.speed = f;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SEFInfo {
        Integer sefDataType;
        String sefKey;

        public SEFInfo(String str, Integer num) {
            this.sefKey = str;
            this.sefDataType = num;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SectionInfo {
        public long endPosition;
        public File file;
        public long startPosition;

        public SectionInfo(File file2, long j2, long j3) {
            this.file = file2;
            this.startPosition = j2;
            this.endPosition = j3;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class VideoInfo {
        /* access modifiers changed from: private */
        public final int isMotionPhotoV2;
        private final long videoLength;
        private final long videoOffset;

        public VideoInfo(long j2, long j3, int i2) {
            this.videoOffset = j2;
            this.videoLength = j3;
            this.isMotionPhotoV2 = i2;
        }

        public final long getVideoLength() {
            return this.videoLength;
        }

        public final long getVideoOffset() {
            return this.videoOffset;
        }

        public final int isMotionPhotoV2() {
            return this.isMotionPhotoV2;
        }

        public String toString() {
            StringBuilder sb2 = new StringBuilder("VideoInfo(videoOffset=");
            sb2.append(this.videoOffset);
            sb2.append(", videoLength=");
            return C0212a.o(sb2, this.videoLength, ")");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class XMPInformation {
        public long offset;
        public long size;
        public byte[] xmpbuffer;

        public XMPInformation(long j2, long j3, byte[] bArr) {
            this.offset = j2;
            this.size = j3;
            this.xmpbuffer = bArr;
        }
    }

    public MotionPhotoVideoUtils() {
        Log.d(TAG, "MotionPhotoVideoUtils[2.0.17]");
    }

    public static AutoPlayVideoInfo getAutoPlayVideoDataPosition(File file) {
        MotionPhotoParser motionPhotoParser = new MotionPhotoParser(file);
        MotionPhotoParser.DataPosition64 parseAutoPlaySefTail = motionPhotoParser.parseAutoPlaySefTail(isJpeg(file.toString()));
        if (parseAutoPlaySefTail == null) {
            Log.w(TAG, "Fail to get autoplay info");
            motionPhotoParser.close();
            return null;
        }
        Log.d(TAG, "AutoPlay Video info - offset:" + parseAutoPlaySefTail.getOffset() + " length:" + parseAutoPlaySefTail.getLength());
        motionPhotoParser.close();
        return new AutoPlayVideoInfo(parseAutoPlaySefTail.getOffset(), parseAutoPlaySefTail.getLength());
    }

    public static long getCaptureTimeStampUs(FileDescriptor fileDescriptor) {
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(fileDescriptor);
            fileInputStream.getChannel().position(0);
            long captureTimestampUs = getCaptureTimestampUs(new ExifInterface(fileInputStream));
            fileInputStream.close();
            return captureTimestampUs;
        } catch (Exception e) {
            Log.i(TAG, "fail to get capture timestamp from xmp: " + e);
            return 0;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private static long getCaptureTimestampUs(ExifInterface exifInterface) {
        byte[] attributeBytes = exifInterface.getAttributeBytes("Xmp");
        if (attributeBytes != null) {
            XMPProperty property = XMPMetaFactory.parseFromBuffer(attributeBytes).getProperty(MediaDefs.Meta.XMP.XMP_GOOGLE_CAMERA_NS, "MotionPhotoPresentationTimestampUs");
            if (property == null) {
                return 0;
            }
            String value = property.getValue();
            Log.d(TAG, "presentation timestamp =" + Long.parseLong(value));
            return Long.parseLong(value);
        }
        Log.i(TAG, "no xmp given");
        return 0;
    }

    public static RectF getImageRectIfCropped(File file) {
        RectF rectF;
        File file2 = file;
        try {
            if (!SemExtendedFormat.hasData(file2, "MotionPhoto_Info")) {
                return null;
            }
            JSONObject jSONObject = new JSONObject(new String(SemExtendedFormat.getData(file2, "MotionPhoto_Info")));
            JSONObject jSONObject2 = jSONObject.getJSONObject("crop-rect");
            int i2 = jSONObject2.getInt("left");
            int i7 = jSONObject2.getInt("top");
            int i8 = jSONObject2.getInt("right");
            int i10 = jSONObject2.getInt("bottom");
            JSONObject jSONObject3 = jSONObject.getJSONObject("video-info");
            int i11 = jSONObject3.getInt("originalW");
            rectF = null;
            try {
                int i12 = jSONObject3.getInt("originalH");
                Log.d(TAG, "get rect:left(" + i2 + "),top(" + i7 + "), right(" + i8 + "),bottom(" + i10 + "), w(" + i11 + "),h(" + i12 + ")");
                if (!((i8 == 0 && i10 == 0) || i11 == 0)) {
                    if (i12 != 0) {
                        float f = (float) i11;
                        float f5 = ((float) i2) / f;
                        float f8 = (float) i12;
                        float f10 = ((float) i7) / f8;
                        float f11 = ((float) i8) / f;
                        float f12 = ((float) i10) / f8;
                        Log.d(TAG, "success to get final rect:left(" + f5 + "),top(" + f10 + "), right(" + f11 + "),bottom(" + f12 + ")");
                        return new RectF(f5, f10, f11, f12);
                    }
                }
                Log.w(TAG, "crop info is not valid, rect:" + jSONObject2.toString());
                return null;
            } catch (Exception unused) {
                Log.w(TAG, "Fail to get crop rect");
                return rectF;
            }
        } catch (Exception unused2) {
            rectF = null;
            Log.w(TAG, "Fail to get crop rect");
            return rectF;
        }
    }

    public static String getMPVersion(File file) {
        if (!SemExtendedFormat.hasData(file, "MotionPhoto_Data")) {
            Log.w(TAG, "we only support for motion photo");
            return null;
        } else if (SemExtendedFormat.hasData(file, "MotionPhoto_Version")) {
            return new String(SemExtendedFormat.getData(file, "MotionPhoto_Version"));
        } else {
            Log.w(TAG, "cannot find specific version");
            return null;
        }
    }

    public static List<PlaybackInfo> getPlaybackInfo(File file, EffectType effectType) {
        long j2;
        long j3;
        long j8;
        EffectType effectType2 = effectType;
        ArrayList arrayList = new ArrayList();
        Long l = motionPhotoFileInfo(file).get("mp-video-duration");
        if (l == null) {
            Log.w(TAG, "Fail to get duration, Not supported");
            return null;
        }
        long xMPTimeStamp = getXMPTimeStamp(file);
        long j10 = 0;
        if (xMPTimeStamp == 0) {
            Log.w(TAG, "There is no presentation Timstamp in xmp");
            xMPTimeStamp = l.longValue();
        }
        if (l.longValue() < xMPTimeStamp) {
            Log.w(TAG, "image Timestamp[" + xMPTimeStamp + "] is bigger than video duration[" + l + "]");
            xMPTimeStamp = l.longValue();
        }
        long j11 = xMPTimeStamp;
        Log.i(TAG, "imageTimestamp = " + j11 + ", duration=" + l);
        if (effectType2 == EffectType.BOMERANG) {
            if (l.longValue() <= MOTION_PHOTO_MIN_DURATION) {
                j8 = l.longValue();
            } else if (l.longValue() - j11 >= EFFECT_MIN_TIME) {
                j10 = Math.max(0, j11 - EFFECT_MIN_TIME);
                j8 = j11 + EFFECT_MIN_TIME;
            } else {
                if (l.longValue() - j11 < EFFECT_MIN_TIME) {
                    long longValue = l.longValue();
                    j3 = longValue - MOTION_PHOTO_MIN_DURATION;
                    j2 = longValue;
                } else {
                    j3 = 0;
                    j2 = 0;
                }
                arrayList.add(new PlaybackInfo(j3, j2, 2.0f));
                arrayList.add(new PlaybackInfo(j2, j3, 2.0f));
                return arrayList;
            }
            j2 = j8;
            j3 = j10;
            arrayList.add(new PlaybackInfo(j3, j2, 2.0f));
            arrayList.add(new PlaybackInfo(j2, j3, 2.0f));
            return arrayList;
        } else if (effectType2 != EffectType.SLOWMO) {
            Log.w(TAG, "not supported");
            return arrayList;
        } else if (l.longValue() < MOTION_PHOTO_MIN_DURATION) {
            arrayList.add(new PlaybackInfo(0, l.longValue() / 2, 2.0f));
            arrayList.add(new PlaybackInfo((l.longValue() / 2) + 1, l.longValue(), 0.25f));
            return arrayList;
        } else if (j11 <= EFFECT_MIN_TIME) {
            arrayList.add(new PlaybackInfo(0, j11, 0.25f));
            arrayList.add(new PlaybackInfo(j11 + 1, l.longValue(), 2.0f));
            return arrayList;
        } else {
            arrayList.add(new PlaybackInfo(0, j11 - EFFECT_MIN_TIME, 2.0f));
            if (l.longValue() - j11 <= MIN_DIFF_TIME_IMAGE_AND_VIDEO) {
                arrayList.add(new PlaybackInfo(j11 - 499999, l.longValue(), 0.25f));
                return arrayList;
            }
            arrayList.add(new PlaybackInfo(j11 - 499999, j11, 0.25f));
            arrayList.add(new PlaybackInfo(j11 + 1, l.longValue(), 2.0f));
            return arrayList;
        }
    }

    public static VideoInfo getSEFDataPosition(File file) {
        MotionPhotoParser motionPhotoParser = new MotionPhotoParser(file);
        MotionPhotoParser.DataPosition64 parseJpegSefTail = isJpeg(file.toString()) ? motionPhotoParser.parseJpegSefTail() : motionPhotoParser.parseHeicSefTail();
        if (parseJpegSefTail == null) {
            Log.w(TAG, "Fail to get sef info");
            motionPhotoParser.close();
            return null;
        }
        Log.d(TAG, "Video info - offset:" + parseJpegSefTail.getOffset() + " length:" + parseJpegSefTail.getLength());
        motionPhotoParser.close();
        return new VideoInfo(parseJpegSefTail.getOffset(), parseJpegSefTail.getLength(), parseJpegSefTail.isMPV2());
    }

    public static Map<String, Long> getTrackDuration(long j2, long j3, File file) {
        FileInputStream fileInputStream;
        Throwable th;
        HashMap hashMap = new HashMap();
        try {
            fileInputStream = new FileInputStream(file);
            MediaExtractor mediaExtractor = new MediaExtractor();
            mediaExtractor.setDataSource(fileInputStream.getFD(), j2, j3);
            int trackCount = mediaExtractor.getTrackCount();
            for (int i2 = 0; i2 < trackCount; i2++) {
                MediaFormat trackFormat = mediaExtractor.getTrackFormat(i2);
                String string = trackFormat.getString(MediaDefs.Image.HEIF.HEIF_MIME_BOX);
                if (string != null) {
                    if (string.startsWith("video")) {
                        hashMap.put("mp-video-duration", Long.valueOf(trackFormat.getLong("durationUs")));
                    } else {
                        hashMap.put("mp-audio-duration", Long.valueOf(trackFormat.getLong("durationUs")));
                    }
                }
            }
            fileInputStream.close();
            return hashMap;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    public static VideoInfo getVideoDataPosition(FileDescriptor fileDescriptor) {
        Throwable th;
        long j2;
        long j3;
        boolean isJpeg = isJpeg(fileDescriptor);
        VideoInfo sEFDataPosition = getSEFDataPosition(fileDescriptor);
        long j8 = 0;
        if (sEFDataPosition == null || sEFDataPosition.getVideoLength() <= 0) {
            if (isJpeg) {
                long[] videoDataPositionFromXMP = getVideoDataPositionFromXMP(fileDescriptor);
                if (videoDataPositionFromXMP != null) {
                    j8 = videoDataPositionFromXMP[0];
                    j3 = videoDataPositionFromXMP[1];
                } else {
                    j3 = 0;
                }
                j2 = j3;
            } else {
                FileInputStream fileInputStream = new FileInputStream(fileDescriptor);
                try {
                    fileInputStream.getChannel().position(0);
                    j8 = HEIFParser.getVideoOffset(fileInputStream);
                    long size = fileInputStream.getChannel().size() - j8;
                    fileInputStream.close();
                    j2 = size;
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            long j10 = j8;
            StringBuilder j11 = j.j(j10, "Success to get it from XMP, off=", " len=");
            j11.append(j2);
            Log.d(TAG, j11.toString());
            return new VideoInfo(j10, j2, 0);
        }
        long videoOffset = sEFDataPosition.getVideoOffset();
        long videoLength = sEFDataPosition.getVideoLength();
        StringBuilder j12 = j.j(videoOffset, "Success to get video info from SEF, off=", " len=");
        j12.append(videoLength);
        Log.d(TAG, j12.toString());
        return new VideoInfo(videoOffset, videoLength, sEFDataPosition.isMotionPhotoV2());
        throw th;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0087, code lost:
        android.util.Log.d(TAG, "found motionPhoto_item");
        r7 = r4.getStructField(com.samsung.android.motionphoto.utils.v2.MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, "Container:Directory[" + r9 + "]/Container:Item", com.samsung.android.motionphoto.utils.v2.MediaDefs.Meta.XMP.XMP_GOOGLE_ITEM_NS, "Item:Length");
        r1 = r4.getStructField(com.samsung.android.motionphoto.utils.v2.MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, "Container:Directory[" + r9 + "]/Container:Item", com.samsung.android.motionphoto.utils.v2.MediaDefs.Meta.XMP.XMP_GOOGLE_ITEM_NS, "Item:Padding");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00b8, code lost:
        if (r7 == null) goto L_0x0125;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00ba, code lost:
        r2 = new long[2];
        r3 = (long) java.lang.Integer.parseInt(java.lang.String.valueOf(r7.getValue()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00ca, code lost:
        if (r1 == null) goto L_0x00da;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00cc, code lost:
        r6 = (long) java.lang.Integer.parseInt(java.lang.String.valueOf(r1.getValue()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00da, code lost:
        r6 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00dc, code lost:
        r1 = new java.io.FileOutputStream(r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        r15 = r1.getChannel();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00e9, code lost:
        if (r3 <= com.samsung.android.sdk.mobileservice.common.ErrorCodeConvertor.ERROR_NOT_ALLOWED_CALLER) goto L_0x00f8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00eb, code lost:
        r2[0] = r15.size() - r3;
        r2[1] = r3 - r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00fb, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long[] getVideoDataPositionFromXMP(java.io.FileDescriptor r15) {
        /*
            java.lang.String r0 = "MotionPhotoVideoUtilsEx"
            java.lang.String r1 = "http://ns.google.com/photos/1.0/container/item/"
            java.lang.String r2 = "]/Container:Item"
            java.lang.String r3 = "Container:Directory["
            com.adobe.internal.xmp.XMPMeta r4 = getXmpMeta(r15)     // Catch:{ XMPException -> 0x0018, IOException -> 0x0109 }
            r5 = 0
            java.lang.String r6 = "http://ns.google.com/photos/1.0/container/"
            if (r4 == 0) goto L_0x001b
            java.lang.String r7 = "Container:Directory"
            int r7 = r4.countArrayItems(r6, r7)     // Catch:{ XMPException -> 0x0018, IOException -> 0x0109 }
            goto L_0x001c
        L_0x0018:
            r15 = move-exception
            goto L_0x0110
        L_0x001b:
            r7 = r5
        L_0x001c:
            r8 = 1
            r9 = r8
        L_0x001e:
            if (r9 > r7) goto L_0x0125
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ XMPException -> 0x0018, IOException -> 0x0109 }
            r10.<init>()     // Catch:{ XMPException -> 0x0018, IOException -> 0x0109 }
            r10.append(r3)     // Catch:{ XMPException -> 0x0018, IOException -> 0x0109 }
            r10.append(r9)     // Catch:{ XMPException -> 0x0018, IOException -> 0x0109 }
            r10.append(r2)     // Catch:{ XMPException -> 0x0018, IOException -> 0x0109 }
            java.lang.String r10 = r10.toString()     // Catch:{ XMPException -> 0x0018, IOException -> 0x0109 }
            java.lang.String r11 = "Item:Semantic"
            com.adobe.internal.xmp.properties.XMPProperty r10 = r4.getStructField(r6, r10, r1, r11)     // Catch:{ XMPException -> 0x0018, IOException -> 0x0109 }
            java.lang.String r11 = "Item:Padding"
            java.lang.String r12 = "Item:Length"
            if (r10 == 0) goto L_0x0079
            java.lang.String r13 = "Primary"
            java.lang.String r14 = r10.getValue()     // Catch:{ XMPException -> 0x0018, IOException -> 0x0109 }
            boolean r13 = r13.equals(r14)     // Catch:{ XMPException -> 0x0018, IOException -> 0x0109 }
            if (r13 == 0) goto L_0x0079
            java.lang.String r13 = "found primary_item"
            android.util.Log.d(r0, r13)     // Catch:{ XMPException -> 0x0018, IOException -> 0x0109 }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ XMPException -> 0x0018, IOException -> 0x0109 }
            r13.<init>()     // Catch:{ XMPException -> 0x0018, IOException -> 0x0109 }
            r13.append(r3)     // Catch:{ XMPException -> 0x0018, IOException -> 0x0109 }
            r13.append(r9)     // Catch:{ XMPException -> 0x0018, IOException -> 0x0109 }
            r13.append(r2)     // Catch:{ XMPException -> 0x0018, IOException -> 0x0109 }
            java.lang.String r13 = r13.toString()     // Catch:{ XMPException -> 0x0018, IOException -> 0x0109 }
            r4.getStructField(r6, r13, r1, r12)     // Catch:{ XMPException -> 0x0018, IOException -> 0x0109 }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ XMPException -> 0x0018, IOException -> 0x0109 }
            r13.<init>()     // Catch:{ XMPException -> 0x0018, IOException -> 0x0109 }
            r13.append(r3)     // Catch:{ XMPException -> 0x0018, IOException -> 0x0109 }
            r13.append(r9)     // Catch:{ XMPException -> 0x0018, IOException -> 0x0109 }
            r13.append(r2)     // Catch:{ XMPException -> 0x0018, IOException -> 0x0109 }
            java.lang.String r13 = r13.toString()     // Catch:{ XMPException -> 0x0018, IOException -> 0x0109 }
            r4.getStructField(r6, r13, r1, r11)     // Catch:{ XMPException -> 0x0018, IOException -> 0x0109 }
        L_0x0079:
            if (r10 == 0) goto L_0x0105
            java.lang.String r13 = "MotionPhoto"
            java.lang.String r10 = r10.getValue()     // Catch:{ XMPException -> 0x0018, IOException -> 0x0109 }
            boolean r10 = r13.equals(r10)     // Catch:{ XMPException -> 0x0018, IOException -> 0x0109 }
            if (r10 == 0) goto L_0x0105
            java.lang.String r7 = "found motionPhoto_item"
            android.util.Log.d(r0, r7)     // Catch:{ XMPException -> 0x0018, IOException -> 0x0109 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ XMPException -> 0x0018, IOException -> 0x0109 }
            r7.<init>()     // Catch:{ XMPException -> 0x0018, IOException -> 0x0109 }
            r7.append(r3)     // Catch:{ XMPException -> 0x0018, IOException -> 0x0109 }
            r7.append(r9)     // Catch:{ XMPException -> 0x0018, IOException -> 0x0109 }
            r7.append(r2)     // Catch:{ XMPException -> 0x0018, IOException -> 0x0109 }
            java.lang.String r7 = r7.toString()     // Catch:{ XMPException -> 0x0018, IOException -> 0x0109 }
            com.adobe.internal.xmp.properties.XMPProperty r7 = r4.getStructField(r6, r7, r1, r12)     // Catch:{ XMPException -> 0x0018, IOException -> 0x0109 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ XMPException -> 0x0018, IOException -> 0x0109 }
            r10.<init>()     // Catch:{ XMPException -> 0x0018, IOException -> 0x0109 }
            r10.append(r3)     // Catch:{ XMPException -> 0x0018, IOException -> 0x0109 }
            r10.append(r9)     // Catch:{ XMPException -> 0x0018, IOException -> 0x0109 }
            r10.append(r2)     // Catch:{ XMPException -> 0x0018, IOException -> 0x0109 }
            java.lang.String r2 = r10.toString()     // Catch:{ XMPException -> 0x0018, IOException -> 0x0109 }
            com.adobe.internal.xmp.properties.XMPProperty r1 = r4.getStructField(r6, r2, r1, r11)     // Catch:{ XMPException -> 0x0018, IOException -> 0x0109 }
            if (r7 == 0) goto L_0x0125
            r2 = 2
            long[] r2 = new long[r2]     // Catch:{ XMPException -> 0x0018, IOException -> 0x0109 }
            java.lang.String r3 = r7.getValue()     // Catch:{ XMPException -> 0x0018, IOException -> 0x0109 }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ XMPException -> 0x0018, IOException -> 0x0109 }
            int r3 = java.lang.Integer.parseInt(r3)     // Catch:{ XMPException -> 0x0018, IOException -> 0x0109 }
            long r3 = (long) r3     // Catch:{ XMPException -> 0x0018, IOException -> 0x0109 }
            if (r1 == 0) goto L_0x00da
            java.lang.String r1 = r1.getValue()     // Catch:{ XMPException -> 0x0018, IOException -> 0x0109 }
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch:{ XMPException -> 0x0018, IOException -> 0x0109 }
            int r1 = java.lang.Integer.parseInt(r1)     // Catch:{ XMPException -> 0x0018, IOException -> 0x0109 }
            long r6 = (long) r1     // Catch:{ XMPException -> 0x0018, IOException -> 0x0109 }
            goto L_0x00dc
        L_0x00da:
            r6 = 0
        L_0x00dc:
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ XMPException -> 0x0018, IOException -> 0x0109 }
            r1.<init>(r15)     // Catch:{ XMPException -> 0x0018, IOException -> 0x0109 }
            java.nio.channels.FileChannel r15 = r1.getChannel()     // Catch:{ all -> 0x00f6 }
            r9 = 1024(0x400, double:5.06E-321)
            int r9 = (r3 > r9 ? 1 : (r3 == r9 ? 0 : -1))
            if (r9 <= 0) goto L_0x00f8
            long r9 = r15.size()     // Catch:{ all -> 0x00f6 }
            long r9 = r9 - r3
            r2[r5] = r9     // Catch:{ all -> 0x00f6 }
            long r3 = r3 - r6
            r2[r8] = r3     // Catch:{ all -> 0x00f6 }
            goto L_0x00f8
        L_0x00f6:
            r15 = move-exception
            goto L_0x00fc
        L_0x00f8:
            r1.close()     // Catch:{ XMPException -> 0x0018, IOException -> 0x0109 }
            return r2
        L_0x00fc:
            r1.close()     // Catch:{ all -> 0x0100 }
            goto L_0x0104
        L_0x0100:
            r1 = move-exception
            r15.addSuppressed(r1)     // Catch:{ XMPException -> 0x0018, IOException -> 0x0109 }
        L_0x0104:
            throw r15     // Catch:{ XMPException -> 0x0018, IOException -> 0x0109 }
        L_0x0105:
            int r9 = r9 + 1
            goto L_0x001e
        L_0x0109:
            r15 = move-exception
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            r0.<init>(r15)
            throw r0
        L_0x0110:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "getVideoDataPositionFromXMP failed. e="
            r1.<init>(r2)
            java.lang.String r15 = r15.getMessage()
            r1.append(r15)
            java.lang.String r15 = r1.toString()
            android.util.Log.e(r0, r15)
        L_0x0125:
            java.lang.String r15 = "Video position not found"
            android.util.Log.w(r0, r15)
            r15 = 0
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.motionphoto.utils.ex.MotionPhotoVideoUtils.getVideoDataPositionFromXMP(java.io.FileDescriptor):long[]");
    }

    public static long getXMPTimeStamp(File file) {
        try {
            return getCaptureTimestampUs(new ExifInterface(file));
        } catch (Exception e) {
            Log.i(TAG, "fail to get capture timestamp from xmp: " + e);
            return 0;
        }
    }

    private static XMPInformation getXmpInfo(FileDescriptor fileDescriptor) {
        FileInputStream fileInputStream = new FileInputStream(fileDescriptor);
        try {
            if (isJpeg(fileDescriptor)) {
                Log.d(TAG, "//JPEG//");
                try {
                    XMPInformation coverImageXMPOffsetAndSize = new JPEGParser().getCoverImageXMPOffsetAndSize(fileInputStream);
                    if (coverImageXMPOffsetAndSize != null) {
                        Log.d(TAG, "[JPEG]XMP " + coverImageXMPOffsetAndSize.offset + ArcCommonLog.TAG_COMMA + coverImageXMPOffsetAndSize.size);
                        fileInputStream.close();
                        return coverImageXMPOffsetAndSize;
                    }
                    Log.d(TAG, "Fail to get xmp information");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Log.d(TAG, "//HEIF//");
                HEIFParser hEIFParser = new HEIFParser();
                try {
                    fileInputStream.getChannel().position(0);
                    XMPInformation coverImageXMPOffsetAndSize2 = hEIFParser.getCoverImageXMPOffsetAndSize(fileInputStream);
                    if (coverImageXMPOffsetAndSize2 != null) {
                        Log.d(TAG, "[HEIF]XMP " + coverImageXMPOffsetAndSize2.offset + ArcCommonLog.TAG_COMMA + coverImageXMPOffsetAndSize2.size);
                        fileInputStream.close();
                        return coverImageXMPOffsetAndSize2;
                    }
                    Log.d(TAG, "Fail to get xmp information");
                } catch (Exception e7) {
                    e7.printStackTrace();
                }
            }
            fileInputStream.close();
            return null;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public static XMPMeta getXmpMeta(FileDescriptor fileDescriptor) {
        byte[] bArr;
        try {
            XMPInformation xmpInfo = getXmpInfo(fileDescriptor);
            if (xmpInfo != null) {
                if (xmpInfo.size > 0) {
                    if (isJpeg(fileDescriptor)) {
                        int i2 = (int) ((xmpInfo.size - 4) - ((long) 29));
                        bArr = new byte[i2];
                        System.arraycopy(xmpInfo.xmpbuffer, 33, bArr, 0, i2);
                    } else {
                        int i7 = (int) xmpInfo.size;
                        bArr = new byte[i7];
                        System.arraycopy(xmpInfo.xmpbuffer, 0, bArr, 0, i7);
                    }
                    try {
                        XMPMeta parseFromBuffer = XMPMetaFactory.parseFromBuffer(bArr);
                        if (parseFromBuffer == null) {
                            Log.w(TAG, "xmp meta is null");
                            return null;
                        } else if (parseFromBuffer.getProperty(MediaDefs.Meta.XMP.XMP_GOOGLE_CAMERA_NS, MediaDefs.Meta.XMP.XMP_KEY_MOTION_PHOTO) == null) {
                            return null;
                        } else {
                            Log.i(TAG, "Success to find motionphoto");
                            return parseFromBuffer;
                        }
                    } catch (XMPException unused) {
                        Log.e(TAG, "XMP parse error");
                        return null;
                    }
                }
            }
            Log.w(TAG, "Fail to parser xmp");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isJpeg(FileDescriptor fileDescriptor) {
        FileInputStream fileInputStream = new FileInputStream(fileDescriptor);
        boolean z = false;
        try {
            fileInputStream.getChannel().position(0);
            byte[] bArr = new byte[2];
            fileInputStream.read(bArr, 0, 2);
            if ((bArr[0] & 255) == 255 && (bArr[1] & 255) == 216) {
                z = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            try {
                fileInputStream.close();
            } catch (IOException e7) {
                e7.printStackTrace();
            }
            throw th;
        }
        try {
            fileInputStream.close();
        } catch (IOException e8) {
            e8.printStackTrace();
        }
        return z;
    }

    public static Boolean isSupportedMotionPhoto(File file) {
        FileInputStream fileInputStream;
        VideoInfo videoDataPosition = getVideoDataPosition(file);
        if (videoDataPosition != null && videoDataPosition.getVideoLength() > 0) {
            try {
                fileInputStream = new FileInputStream(file);
                byte[] bArr = new byte[4];
                fileInputStream.getChannel().position(videoDataPosition.getVideoOffset() + 4);
                if (fileInputStream.read(bArr, 0, 4) <= 0 || !new String(bArr, StandardCharsets.UTF_8).equals(MediaDefs.Image.HEIF.HEIF_FTYP_BOX)) {
                    fileInputStream.close();
                } else {
                    Log.i(TAG, "Found motion photo header");
                    Boolean bool = Boolean.TRUE;
                    fileInputStream.close();
                    return bool;
                }
            } catch (Exception unused) {
                Log.w(TAG, "Fail to found motion photo header");
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        return Boolean.FALSE;
        throw th;
    }

    public static void modifyXmp(FileDescriptor fileDescriptor, long j2) {
        byte[] bArr;
        FileOutputStream fileOutputStream;
        Throwable th;
        try {
            boolean isJpeg = isJpeg(fileDescriptor);
            XMPInformation xmpInfo = getXmpInfo(fileDescriptor);
            if (xmpInfo != null) {
                long j3 = xmpInfo.size;
                if (j3 > 0) {
                    if (isJpeg) {
                        int i2 = (int) ((j3 - 4) - ((long) 29));
                        bArr = new byte[i2];
                        System.arraycopy(xmpInfo.xmpbuffer, 33, bArr, 0, i2);
                    } else {
                        int i7 = (int) j3;
                        bArr = new byte[i7];
                        System.arraycopy(xmpInfo.xmpbuffer, 0, bArr, 0, i7);
                    }
                    try {
                        XMPMeta parseFromBuffer = XMPMetaFactory.parseFromBuffer(bArr);
                        if (parseFromBuffer == null) {
                            Log.w(TAG, "xmp meta is null");
                            return;
                        } else if (parseFromBuffer.getProperty(MediaDefs.Meta.XMP.XMP_GOOGLE_CAMERA_NS, MediaDefs.Meta.XMP.XMP_KEY_MOTION_PHOTO) != null) {
                            Log.i(TAG, "Success to find motionphoto");
                            int countArrayItems = parseFromBuffer.countArrayItems(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, "Container:Directory");
                            int i8 = 1;
                            while (i8 <= countArrayItems) {
                                XMPProperty structField = parseFromBuffer.getStructField(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, "Container:Directory[" + i8 + "]/Container:Item", MediaDefs.Meta.XMP.XMP_GOOGLE_ITEM_NS, "Item:Semantic");
                                if (structField == null || !MediaDefs.Meta.XMP.XMP_KEY_MOTION_PHOTO.equals(structField.getValue())) {
                                    FileDescriptor fileDescriptor2 = fileDescriptor;
                                    i8++;
                                } else {
                                    Log.d(TAG, "found motionPhoto_item");
                                    XMPProperty structField2 = parseFromBuffer.getStructField(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, "Container:Directory[" + i8 + "]/Container:Item", MediaDefs.Meta.XMP.XMP_GOOGLE_ITEM_NS, "Item:Length");
                                    if (structField2 != null) {
                                        parseFromBuffer.setStructField(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, "Container:Directory[" + i8 + "]/Container:Item", MediaDefs.Meta.XMP.XMP_GOOGLE_ITEM_NS, "Item:Length", String.valueOf((int) (((long) Integer.parseInt(String.valueOf(structField2.getValue()))) + j2)));
                                        ByteBuffer allocate = ByteBuffer.allocate((int) xmpInfo.size);
                                        if (isJpeg) {
                                            int i10 = ((int) xmpInfo.size) - 2;
                                            allocate.put((byte) -1);
                                            allocate.put((byte) -31);
                                            allocate.put((byte) ((i10 >> 8) & ScoverState.TYPE_NFC_SMART_COVER));
                                            allocate.put((byte) (i10 & ScoverState.TYPE_NFC_SMART_COVER));
                                            allocate.put(MediaDefs.Meta.XMP.XMP_SIGNATURE.getBytes());
                                        }
                                        allocate.put(XMPMetaFactory.serializeToBuffer(parseFromBuffer, new SerializeOptions(80)));
                                        while (allocate.hasRemaining()) {
                                            allocate.put((byte) 32);
                                        }
                                        allocate.rewind();
                                        fileOutputStream = new FileOutputStream(fileDescriptor);
                                        FileChannel channel = fileOutputStream.getChannel();
                                        channel.position(xmpInfo.offset);
                                        channel.write(allocate);
                                        fileOutputStream.close();
                                        return;
                                    }
                                    return;
                                }
                            }
                            return;
                        } else {
                            return;
                        }
                    } catch (XMPException unused) {
                        Log.e(TAG, "XMP parse error");
                        return;
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
            }
            Log.w(TAG, "Fail to parser xmp");
            return;
            throw th;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Long> motionPhotoFileInfo(File file) {
        FileInputStream fileInputStream;
        Throwable th;
        MediaMetadataRetriever mediaMetadataRetriever;
        Throwable th2;
        HashMap hashMap = new HashMap();
        VideoInfo videoDataPosition = getVideoDataPosition(file);
        if (videoDataPosition != null && videoDataPosition.getVideoLength() > 0) {
            long videoOffset = videoDataPosition.getVideoOffset();
            long videoLength = videoDataPosition.getVideoLength();
            hashMap.put("mp-offset", Long.valueOf(videoOffset));
            hashMap.put("mp-length", Long.valueOf(videoLength));
            hashMap.put("mp-version", Long.valueOf((long) videoDataPosition.isMotionPhotoV2));
            try {
                fileInputStream = new FileInputStream(file);
                MediaExtractor mediaExtractor = new MediaExtractor();
                mediaExtractor.setDataSource(fileInputStream.getFD(), videoOffset, videoLength);
                int trackCount = mediaExtractor.getTrackCount();
                for (int i2 = 0; i2 < trackCount; i2++) {
                    MediaFormat trackFormat = mediaExtractor.getTrackFormat(i2);
                    String string = trackFormat.getString(MediaDefs.Image.HEIF.HEIF_MIME_BOX);
                    if (string != null) {
                        if (string.startsWith("video")) {
                            hashMap.put("mp-video-duration", Long.valueOf(trackFormat.getLong("durationUs")));
                        } else {
                            hashMap.put("mp-audio-duration", Long.valueOf(trackFormat.getLong("durationUs")));
                        }
                    }
                }
                try {
                    mediaMetadataRetriever = new MediaMetadataRetriever();
                    mediaMetadataRetriever.setDataSource(fileInputStream.getFD(), videoOffset, videoLength);
                    hashMap.put("mp-file-duration", Long.valueOf(Long.parseLong(mediaMetadataRetriever.extractMetadata(9)) * 1000));
                    hashMap.put("mp-video-width", Long.valueOf(Long.parseLong(mediaMetadataRetriever.extractMetadata(18))));
                    hashMap.put("mp-video-height", Long.valueOf(Long.parseLong(mediaMetadataRetriever.extractMetadata(19))));
                    hashMap.put("mp-video-rotation", Long.valueOf(Long.parseLong(mediaMetadataRetriever.extractMetadata(24))));
                    mediaMetadataRetriever.close();
                } catch (Exception unused) {
                    Log.w(TAG, "Fail ");
                } catch (Throwable th3) {
                    th2.addSuppressed(th3);
                }
                fileInputStream.close();
            } catch (Exception unused2) {
                Log.w(TAG, "Fail to get duration");
            } catch (Throwable th4) {
                th.addSuppressed(th4);
            }
            try {
                ExifInterface exifInterface = new ExifInterface(file);
                hashMap.put("mp-image-width", Long.valueOf(Long.parseLong(exifInterface.getAttribute("ImageWidth"))));
                hashMap.put("mp-image-height", Long.valueOf(Long.parseLong(exifInterface.getAttribute("ImageLength"))));
                hashMap.put("mp-image-rotation", Long.valueOf(Long.parseLong(exifInterface.getAttribute("Orientation"))));
            } catch (Exception unused3) {
                Log.w(TAG, "Fail to get exif from motionphoto");
            }
        }
        return hashMap;
        throw th2;
        throw th;
    }

    private TreeSet<OrderedItemList> sortItemList(LinkedHashSet<ItemInfo> linkedHashSet) {
        MediaMetadataRetriever mediaMetadataRetriever;
        Throwable th;
        MotionPhotoVideoUtils motionPhotoVideoUtils = this;
        TreeSet<OrderedItemList> treeSet = new TreeSet<>(new OrderedItemList());
        Iterator<ItemInfo> it = linkedHashSet.iterator();
        MediaMetadataRetriever mediaMetadataRetriever2 = new MediaMetadataRetriever();
        while (it.hasNext()) {
            try {
                ItemInfo next = it.next();
                long a7 = new ExifInterface(next.file).getDateTimeOriginal();
                VideoInfo videoDataPosition = getVideoDataPosition(next.file);
                if (videoDataPosition != null) {
                    FileInputStream fileInputStream = new FileInputStream(next.file);
                    try {
                        mediaMetadataRetriever2.setDataSource(fileInputStream.getFD(), videoDataPosition.getVideoOffset(), videoDataPosition.getVideoLength());
                        mediaMetadataRetriever = mediaMetadataRetriever2;
                        try {
                            long parseLong = Long.parseLong(mediaMetadataRetriever.extractMetadata(9));
                            long xMPTimeStamp = getXMPTimeStamp(next.file) / 1000;
                            if (200 + xMPTimeStamp < parseLong) {
                                a7 += parseLong - xMPTimeStamp;
                            }
                            long j2 = a7;
                            long j3 = j2 - parseLong;
                            treeSet.add(new OrderedItemList(next.file, j3, j2, parseLong));
                            Log.d(TAG, "file:" + next.file.toString() + ", startTime=" + j3 + ", endTime=" + j2 + ", duration=" + parseLong + ", presentationTimestampMs=" + xMPTimeStamp);
                        } catch (Throwable th2) {
                            th = th2;
                            th = th;
                            fileInputStream.close();
                            throw th;
                        }
                        try {
                            fileInputStream.close();
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        mediaMetadataRetriever = mediaMetadataRetriever2;
                        th = th;
                        fileInputStream.close();
                        throw th;
                    }
                } else {
                    mediaMetadataRetriever = mediaMetadataRetriever2;
                }
                motionPhotoVideoUtils = this;
                mediaMetadataRetriever2 = mediaMetadataRetriever;
            } catch (Throwable th5) {
                th = th5;
                mediaMetadataRetriever = mediaMetadataRetriever2;
                Throwable th6 = th;
                try {
                    mediaMetadataRetriever.close();
                } catch (Throwable th7) {
                    th6.addSuppressed(th7);
                }
                throw th6;
            }
        }
        mediaMetadataRetriever2.close();
        return treeSet;
    }

    /* access modifiers changed from: private */
    public Map<SEFInfo, byte[]> sortSEFDataMap(Map<SEFInfo, byte[]> map) {
        return (Map) map.entrySet().stream().sorted(Comparator.comparingInt(new C0545b(22))).collect(Collectors.toMap(new C0431a(19), new C0431a(20), new a(0), new C0720a(10)));
    }

    /* JADX WARNING: Removed duplicated region for block: B:47:0x00a7 A[Catch:{ Exception -> 0x00ab }] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00b3 A[Catch:{ Exception -> 0x00ab }] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00bc A[Catch:{ Exception -> 0x00ab }] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00c5 A[Catch:{ Exception -> 0x00ab }] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00ce A[Catch:{ Exception -> 0x00ab }] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00e7 A[Catch:{ Exception -> 0x00ab }] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x00f5 A[Catch:{ Exception -> 0x00ab }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean deleteVideo(java.io.File r21) {
        /*
            r20 = this;
            r1 = r21
            java.lang.String r2 = "Long_Exposure_Effect_Info"
            java.lang.String r3 = "MotionPhoto_AutoPlay"
            java.lang.String r4 = "MotionPhoto_Info"
            java.lang.String r5 = "MotionPhoto_Version"
            java.lang.String r6 = "MotionPhoto_Data"
            java.lang.String r0 = "deleteVideo"
            java.lang.String r7 = "MotionPhotoVideoUtilsEx"
            android.util.Log.d(r7, r0)
            r8 = 0
            boolean r0 = com.samsung.android.media.SemExtendedFormat.hasData(r1, r6)     // Catch:{ Exception -> 0x0021 }
            if (r0 != 0) goto L_0x0026
            java.lang.String r0 = "we only support deleting for motion photo"
            android.util.Log.w(r7, r0)     // Catch:{ Exception -> 0x0021 }
            return r8
        L_0x0021:
            r0 = move-exception
            r20 = r8
            goto L_0x0107
        L_0x0026:
            com.samsung.android.motionphoto.utils.ex.MotionPhotoVideoUtils$VideoInfo r0 = getVideoDataPosition((java.io.File) r1)     // Catch:{ Exception -> 0x0021 }
            if (r0 == 0) goto L_0x0104
            long r9 = r0.getVideoLength()     // Catch:{ Exception -> 0x0021 }
            r11 = 0
            int r9 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r9 <= 0) goto L_0x0104
            long r9 = r0.getVideoOffset()     // Catch:{ Exception -> 0x0021 }
            long r11 = r0.getVideoLength()     // Catch:{ Exception -> 0x0021 }
            int r13 = r0.isMotionPhotoV2()     // Catch:{ Exception -> 0x0021 }
            r14 = 3
            java.lang.String r15 = "rw"
            if (r13 == r14) goto L_0x0058
            int r0 = r0.isMotionPhotoV2()     // Catch:{ Exception -> 0x0021 }
            r13 = 4
            if (r0 != r13) goto L_0x0050
            goto L_0x0058
        L_0x0050:
            java.lang.String r0 = "Not MotionPhotoV2"
            android.util.Log.d(r7, r0)     // Catch:{ Exception -> 0x0021 }
            r20 = r8
            goto L_0x00a1
        L_0x0058:
            java.io.RandomAccessFile r13 = new java.io.RandomAccessFile     // Catch:{ Exception -> 0x009b }
            r13.<init>(r1, r15)     // Catch:{ Exception -> 0x009b }
            long r16 = r1.length()     // Catch:{ all -> 0x008e }
            long r11 = r11 + r9
            r20 = r8
            r18 = r9
            long r8 = r16 - r11
            int r0 = (int) r8
            java.nio.ByteBuffer r0 = java.nio.ByteBuffer.allocate(r0)     // Catch:{ all -> 0x008b }
            java.nio.channels.FileChannel r10 = r13.getChannel()     // Catch:{ all -> 0x008b }
            r10.read(r0, r11)     // Catch:{ all -> 0x008b }
            r0.flip()     // Catch:{ all -> 0x008b }
            r11 = 8
            long r11 = r18 - r11
            r10.write(r0, r11)     // Catch:{ all -> 0x008b }
            long r11 = r11 + r8
            r10.truncate(r11)     // Catch:{ all -> 0x008b }
            r10.close()     // Catch:{ all -> 0x008b }
            r13.close()     // Catch:{ Exception -> 0x0089 }
            goto L_0x00a1
        L_0x0089:
            r0 = move-exception
            goto L_0x009e
        L_0x008b:
            r0 = move-exception
        L_0x008c:
            r8 = r0
            goto L_0x0092
        L_0x008e:
            r0 = move-exception
            r20 = r8
            goto L_0x008c
        L_0x0092:
            r13.close()     // Catch:{ all -> 0x0096 }
            goto L_0x009a
        L_0x0096:
            r0 = move-exception
            r8.addSuppressed(r0)     // Catch:{ Exception -> 0x0089 }
        L_0x009a:
            throw r8     // Catch:{ Exception -> 0x0089 }
        L_0x009b:
            r0 = move-exception
            r20 = r8
        L_0x009e:
            r0.printStackTrace()     // Catch:{ Exception -> 0x00ab }
        L_0x00a1:
            boolean r0 = com.samsung.android.media.SemExtendedFormat.hasData(r1, r6)     // Catch:{ Exception -> 0x00ab }
            if (r0 == 0) goto L_0x00ad
            com.samsung.android.media.SemExtendedFormat.deleteData(r1, r6)     // Catch:{ Exception -> 0x00ab }
            goto L_0x00ad
        L_0x00ab:
            r0 = move-exception
            goto L_0x0107
        L_0x00ad:
            boolean r0 = com.samsung.android.media.SemExtendedFormat.hasData(r1, r5)     // Catch:{ Exception -> 0x00ab }
            if (r0 == 0) goto L_0x00b6
            com.samsung.android.media.SemExtendedFormat.deleteData(r1, r5)     // Catch:{ Exception -> 0x00ab }
        L_0x00b6:
            boolean r0 = com.samsung.android.media.SemExtendedFormat.hasData(r1, r4)     // Catch:{ Exception -> 0x00ab }
            if (r0 == 0) goto L_0x00bf
            com.samsung.android.media.SemExtendedFormat.deleteData(r1, r4)     // Catch:{ Exception -> 0x00ab }
        L_0x00bf:
            boolean r0 = com.samsung.android.media.SemExtendedFormat.hasData(r1, r3)     // Catch:{ Exception -> 0x00ab }
            if (r0 == 0) goto L_0x00c8
            com.samsung.android.media.SemExtendedFormat.deleteData(r1, r3)     // Catch:{ Exception -> 0x00ab }
        L_0x00c8:
            boolean r0 = com.samsung.android.media.SemExtendedFormat.hasData(r1, r2)     // Catch:{ Exception -> 0x00ab }
            if (r0 == 0) goto L_0x00d1
            com.samsung.android.media.SemExtendedFormat.deleteData(r1, r2)     // Catch:{ Exception -> 0x00ab }
        L_0x00d1:
            java.io.RandomAccessFile r0 = new java.io.RandomAccessFile     // Catch:{ Exception -> 0x00ab }
            r0.<init>(r1, r15)     // Catch:{ Exception -> 0x00ab }
            android.media.ExifInterface r1 = new android.media.ExifInterface     // Catch:{ Exception -> 0x00ab }
            java.io.FileDescriptor r2 = r0.getFD()     // Catch:{ Exception -> 0x00ab }
            r1.<init>(r2)     // Catch:{ Exception -> 0x00ab }
            java.lang.String r2 = "Xmp"
            byte[] r1 = r1.getAttributeBytes(r2)     // Catch:{ Exception -> 0x00ab }
            if (r1 == 0) goto L_0x00f5
            com.samsung.android.motionphoto.utils.ex.XMPParser$Companion r1 = com.samsung.android.motionphoto.utils.ex.XMPParser.Companion     // Catch:{ Exception -> 0x00ab }
            java.io.FileDescriptor r2 = r0.getFD()     // Catch:{ Exception -> 0x00ab }
            com.samsung.android.motionphoto.utils.ex.XMPParser r1 = r1.create(r2)     // Catch:{ Exception -> 0x00ab }
            r1.removeXmp()     // Catch:{ Exception -> 0x00ab }
            goto L_0x00fa
        L_0x00f5:
            java.lang.String r1 = "There is no xmp"
            android.util.Log.d(r7, r1)     // Catch:{ Exception -> 0x00ab }
        L_0x00fa:
            r0.close()     // Catch:{ Exception -> 0x00ab }
            java.lang.String r0 = "Success to deleteVideo"
            android.util.Log.d(r7, r0)     // Catch:{ Exception -> 0x00ab }
            r0 = 1
            return r0
        L_0x0104:
            r20 = r8
            goto L_0x010a
        L_0x0107:
            r0.printStackTrace()
        L_0x010a:
            java.lang.String r0 = "Fail to deleteVideo"
            android.util.Log.i(r7, r0)
            return r20
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.motionphoto.utils.ex.MotionPhotoVideoUtils.deleteVideo(java.io.File):boolean");
    }

    public LinkedHashSet<SectionInfo> getOverlappingSection(LinkedHashSet<ItemInfo> linkedHashSet) {
        long j2;
        long j3;
        long j8;
        Log.d(TAG, "getOverlappingSection");
        long nanoTime = System.nanoTime();
        LinkedHashSet<SectionInfo> linkedHashSet2 = new LinkedHashSet<>();
        Iterator<ItemInfo> it = linkedHashSet.iterator();
        long j10 = 0;
        int i2 = 0;
        ItemInfo itemInfo = null;
        long j11 = 0;
        long j12 = 0;
        while (it.hasNext()) {
            ItemInfo next = it.next();
            VideoInfo videoDataPosition = getVideoDataPosition(next.file);
            if (videoDataPosition == null || videoDataPosition.getVideoLength() <= j10) {
                j2 = j10;
            } else {
                j2 = j10;
                Map<String, Long> trackDuration = getTrackDuration(videoDataPosition.getVideoOffset(), videoDataPosition.getVideoLength(), next.file);
                Long l = trackDuration.get("mp-video-duration");
                Long l8 = trackDuration.get("mp-audio-duration");
                if (l == null || l8 == null) {
                    j11 = next.duration;
                } else {
                    j11 = Math.min(l.longValue(), l8.longValue()) / 1000;
                }
            }
            long j13 = j11;
            long j14 = next.exifDateTimeOriginalMs;
            long xMPTimeStamp = getXMPTimeStamp(next.file) / 1000;
            if (xMPTimeStamp > j13) {
                xMPTimeStamp = j13;
            }
            if (200 + xMPTimeStamp < j13) {
                j3 = (j13 - xMPTimeStamp) + j14;
            } else {
                j3 = j14;
            }
            Log.d(TAG, "exifDateTimeOriginal=" + j14);
            Log.d(TAG, "[file:" + next.file.toString() + ", endTime=" + j3 + ", duration=" + next + "]");
            if (itemInfo == null) {
                j8 = j13;
                linkedHashSet2.add(new SectionInfo(next.file, 0, j13));
                Log.d(TAG, "startPos = 0, endPos = " + j8);
                i2++;
                j10 = j2;
                j12 = j3;
                itemInfo = next;
            } else {
                j8 = j13;
                long j15 = j3 - j12;
                int i7 = (j15 > j2 ? 1 : (j15 == j2 ? 0 : -1));
                if (i7 > 0) {
                    if (j3 - j8 < j12) {
                        long j16 = j8 - j15;
                        linkedHashSet2.add(new SectionInfo(next.file, j16, j8));
                        Log.d(TAG, "startPos =" + j16 + ", endPos = " + j8);
                    } else {
                        linkedHashSet2.add(new SectionInfo(next.file, 0, j8));
                        Log.d(TAG, "startPos = 0, endPos = " + j8);
                    }
                    i2++;
                    j12 = j3;
                    itemInfo = next;
                } else if (i7 <= 0) {
                    Log.d(TAG, "current MP range is overlapped a lot, skip this item to add list");
                }
                j10 = j2;
            }
            j11 = j8;
        }
        long nanoTime2 = System.nanoTime();
        Log.d(TAG, "executing time :" + ((nanoTime2 - nanoTime) / MOTION_PHOTO_MIN_DURATION) + "ms, total image :" + i2);
        return linkedHashSet2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:48:0x01cb, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x01e2, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:?, code lost:
        r1.addSuppressed(r0);
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:46:0x01c6, B:58:0x01de] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean replaceVideo(java.io.FileDescriptor r38, java.io.FileDescriptor r39, java.util.Map<com.samsung.android.motionphoto.utils.ex.MotionPhotoVideoUtils.SEFInfo, byte[]> r40) {
        /*
            r37 = this;
            r0 = r38
            java.util.Optional r1 = java.util.Optional.ofNullable(r40)
            com.samsung.android.motionphoto.utils.ex.b r2 = new com.samsung.android.motionphoto.utils.ex.b
            r3 = 0
            r4 = r37
            r2.<init>(r3, r4)
            java.util.Optional r1 = r1.map(r2)
            r2 = 0
            java.lang.Object r1 = r1.orElse(r2)
            java.util.Map r1 = (java.util.Map) r1
            boolean r2 = isJpeg((java.io.FileDescriptor) r0)
            com.samsung.android.motionphoto.utils.ex.MotionPhotoVideoUtils$VideoInfo r4 = getVideoDataPosition((java.io.FileDescriptor) r0)
            long r5 = r4.getVideoLength()
            r7 = 0
            int r5 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r5 <= 0) goto L_0x03de
            long r7 = r4.getVideoOffset()
            long r4 = r4.getVideoLength()
            java.lang.String r9 = "offset:"
            java.lang.String r10 = "length:"
            java.lang.StringBuilder r9 = N2.j.j(r7, r9, r10)
            r9.append(r4)
            java.lang.String r9 = r9.toString()
            java.lang.String r10 = "MotionPhotoVideoUtilsEx"
            android.util.Log.d(r10, r9)
            java.io.FileInputStream r9 = new java.io.FileInputStream
            r11 = r39
            r9.<init>(r11)
            java.nio.channels.FileChannel r11 = r9.getChannel()     // Catch:{ all -> 0x01e7 }
            long r11 = r11.size()     // Catch:{ all -> 0x01e7 }
            int r13 = (int) r11     // Catch:{ all -> 0x01e7 }
            byte[] r13 = new byte[r13]     // Catch:{ all -> 0x01e7 }
            r9.read(r13)     // Catch:{ all -> 0x01e7 }
            r16 = 2
            r40 = r3
            java.lang.String r3 = "add new sef data: "
            r37 = 3
            java.lang.String r15 = "] is not the same with sefTailSize["
            r17 = 1
            java.lang.String r6 = "] is not the same with videoSize["
            r18 = 24
            r19 = 16
            java.lang.String r14 = "]"
            r20 = 8
            r21 = r2
            java.lang.String r2 = "writtenByte["
            if (r21 == 0) goto L_0x01eb
            r21 = r4
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ all -> 0x01e7 }
            r4.<init>(r0)     // Catch:{ all -> 0x01e7 }
            java.nio.channels.FileChannel r5 = r4.getChannel()     // Catch:{ all -> 0x01d8 }
            long r23 = r5.size()     // Catch:{ all -> 0x01d8 }
            r25 = r3
            r26 = r4
            long r3 = r7 + r21
            r28 = r9
            r27 = r10
            long r9 = r23 - r3
            r23 = r13
            int r13 = (int) r9
            java.nio.ByteBuffer r13 = java.nio.ByteBuffer.allocate(r13)     // Catch:{ all -> 0x01b1 }
            r5.read(r13, r3)     // Catch:{ all -> 0x01b1 }
            r13.flip()     // Catch:{ all -> 0x01b1 }
            long r3 = r11 - r21
            r24 = r5
            java.io.FileOutputStream r5 = new java.io.FileOutputStream     // Catch:{ all -> 0x01b1 }
            r5.<init>(r0)     // Catch:{ all -> 0x01b1 }
            r21 = r5
            java.nio.channels.FileChannel r5 = r21.getChannel()     // Catch:{ all -> 0x00e1 }
            r5.truncate(r7)     // Catch:{ all -> 0x00e1 }
            r29 = r1
            java.nio.ByteBuffer r1 = java.nio.ByteBuffer.wrap(r23)     // Catch:{ all -> 0x00e1 }
            int r1 = r5.write(r1, r7)     // Catch:{ all -> 0x00e1 }
            r30 = r7
            long r7 = (long) r1     // Catch:{ all -> 0x00e1 }
            int r22 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1))
            if (r22 == 0) goto L_0x00e5
            r22 = r7
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x00e1 }
            r7.<init>(r2)     // Catch:{ all -> 0x00e1 }
            r7.append(r1)     // Catch:{ all -> 0x00e1 }
            r7.append(r6)     // Catch:{ all -> 0x00e1 }
            r7.append(r11)     // Catch:{ all -> 0x00e1 }
            r7.append(r14)     // Catch:{ all -> 0x00e1 }
            java.lang.String r1 = r7.toString()     // Catch:{ all -> 0x00e1 }
            r7 = r27
            android.util.Log.w(r7, r1)     // Catch:{ all -> 0x00e1 }
            goto L_0x00e9
        L_0x00e1:
            r0 = move-exception
            r1 = r0
            goto L_0x01cf
        L_0x00e5:
            r22 = r7
            r7 = r27
        L_0x00e9:
            long r11 = r30 + r22
            int r1 = r5.write(r13, r11)     // Catch:{ all -> 0x00e1 }
            long r11 = (long) r1     // Catch:{ all -> 0x00e1 }
            int r6 = (r11 > r9 ? 1 : (r11 == r9 ? 0 : -1))
            if (r6 == 0) goto L_0x010c
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x00e1 }
            r6.<init>(r2)     // Catch:{ all -> 0x00e1 }
            r6.append(r1)     // Catch:{ all -> 0x00e1 }
            r6.append(r15)     // Catch:{ all -> 0x00e1 }
            r6.append(r9)     // Catch:{ all -> 0x00e1 }
            r6.append(r14)     // Catch:{ all -> 0x00e1 }
            java.lang.String r1 = r6.toString()     // Catch:{ all -> 0x00e1 }
            android.util.Log.w(r7, r1)     // Catch:{ all -> 0x00e1 }
        L_0x010c:
            com.samsung.android.motionphoto.utils.ex.MotionPhotoParser r1 = new com.samsung.android.motionphoto.utils.ex.MotionPhotoParser     // Catch:{ all -> 0x00e1 }
            r1.<init>((java.io.FileDescriptor) r0)     // Catch:{ all -> 0x00e1 }
            r1.readJpegSefTail(r3)     // Catch:{ all -> 0x00e1 }
            java.util.concurrent.LinkedBlockingQueue r2 = r1.getSefTailDataQueue()     // Catch:{ all -> 0x00e1 }
        L_0x0118:
            boolean r6 = r2.isEmpty()     // Catch:{ all -> 0x00e1 }
            if (r6 != 0) goto L_0x0150
            java.lang.Object r6 = r2.poll()     // Catch:{ all -> 0x00e1 }
            com.samsung.android.motionphoto.utils.ex.MotionPhotoParser$SEFTailData r6 = (com.samsung.android.motionphoto.utils.ex.MotionPhotoParser.SEFTailData) r6     // Catch:{ all -> 0x00e1 }
            java.util.Objects.requireNonNull(r6)     // Catch:{ all -> 0x00e1 }
            long r8 = r6.getValue()     // Catch:{ all -> 0x00e1 }
            int r10 = (int) r8     // Catch:{ all -> 0x00e1 }
            byte r10 = (byte) r10     // Catch:{ all -> 0x00e1 }
            long r11 = r8 >> r20
            int r11 = (int) r11     // Catch:{ all -> 0x00e1 }
            byte r11 = (byte) r11     // Catch:{ all -> 0x00e1 }
            long r12 = r8 >> r19
            int r12 = (int) r12     // Catch:{ all -> 0x00e1 }
            byte r12 = (byte) r12     // Catch:{ all -> 0x00e1 }
            long r8 = r8 >> r18
            int r8 = (int) r8     // Catch:{ all -> 0x00e1 }
            byte r8 = (byte) r8     // Catch:{ all -> 0x00e1 }
            r9 = 4
            byte[] r13 = new byte[r9]     // Catch:{ all -> 0x00e1 }
            r13[r40] = r10     // Catch:{ all -> 0x00e1 }
            r13[r17] = r11     // Catch:{ all -> 0x00e1 }
            r13[r16] = r12     // Catch:{ all -> 0x00e1 }
            r13[r37] = r8     // Catch:{ all -> 0x00e1 }
            java.nio.ByteBuffer r8 = java.nio.ByteBuffer.wrap(r13)     // Catch:{ all -> 0x00e1 }
            long r9 = r6.getOffset()     // Catch:{ all -> 0x00e1 }
            r5.write(r8, r9)     // Catch:{ all -> 0x00e1 }
            goto L_0x0118
        L_0x0150:
            r5.close()     // Catch:{ all -> 0x00e1 }
            r1.close()     // Catch:{ all -> 0x00e1 }
            r21.close()     // Catch:{ all -> 0x01b1 }
            if (r29 == 0) goto L_0x01c0
            java.util.Set r1 = r29.keySet()     // Catch:{ all -> 0x01b1 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x01b1 }
            r2 = r40
            r5 = r2
        L_0x0166:
            boolean r6 = r1.hasNext()     // Catch:{ all -> 0x01b1 }
            if (r6 == 0) goto L_0x01bd
            java.lang.Object r6 = r1.next()     // Catch:{ all -> 0x01b1 }
            com.samsung.android.motionphoto.utils.ex.MotionPhotoVideoUtils$SEFInfo r6 = (com.samsung.android.motionphoto.utils.ex.MotionPhotoVideoUtils.SEFInfo) r6     // Catch:{ all -> 0x01b1 }
            r8 = r29
            java.lang.Object r9 = r8.get(r6)     // Catch:{ all -> 0x01b1 }
            byte[] r9 = (byte[]) r9     // Catch:{ all -> 0x01b1 }
            if (r9 == 0) goto L_0x01b4
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x01b1 }
            r10.<init>()     // Catch:{ all -> 0x01b1 }
            r13 = r25
            r10.append(r13)     // Catch:{ all -> 0x01b1 }
            java.lang.Integer r11 = r6.sefDataType     // Catch:{ all -> 0x01b1 }
            r10.append(r11)     // Catch:{ all -> 0x01b1 }
            java.lang.String r10 = r10.toString()     // Catch:{ all -> 0x01b1 }
            android.util.Log.d(r7, r10)     // Catch:{ all -> 0x01b1 }
            android.os.ParcelFileDescriptor r10 = android.os.ParcelFileDescriptor.dup(r0)     // Catch:{ all -> 0x01b1 }
            java.lang.String r11 = r6.sefKey     // Catch:{ all -> 0x01b1 }
            java.lang.Integer r12 = r6.sefDataType     // Catch:{ all -> 0x01b1 }
            int r12 = r12.intValue()     // Catch:{ all -> 0x01b1 }
            r14 = r17
            com.samsung.android.media.SemExtendedFormat.addData(r10, r11, r9, r12, r14)     // Catch:{ all -> 0x01b1 }
            int r2 = r2 + 12
            int r5 = r5 + 8
            int r9 = r9.length     // Catch:{ all -> 0x01b1 }
            int r5 = r5 + r9
            java.lang.String r6 = r6.sefKey     // Catch:{ all -> 0x01b1 }
            int r6 = r6.length()     // Catch:{ all -> 0x01b1 }
            int r5 = r5 + r6
            goto L_0x01b6
        L_0x01b1:
            r0 = move-exception
        L_0x01b2:
            r1 = r0
            goto L_0x01de
        L_0x01b4:
            r13 = r25
        L_0x01b6:
            r29 = r8
            r25 = r13
            r17 = 1
            goto L_0x0166
        L_0x01bd:
            int r2 = r2 + r5
            long r1 = (long) r2     // Catch:{ all -> 0x01b1 }
            long r3 = r3 + r1
        L_0x01c0:
            modifyXmp(r0, r3)     // Catch:{ all -> 0x01b1 }
            r24.close()     // Catch:{ all -> 0x01b1 }
            r26.close()     // Catch:{ all -> 0x01cb }
            goto L_0x03b8
        L_0x01cb:
            r0 = move-exception
        L_0x01cc:
            r1 = r0
            goto L_0x03d5
        L_0x01cf:
            r21.close()     // Catch:{ all -> 0x01d3 }
            goto L_0x01d7
        L_0x01d3:
            r0 = move-exception
            r1.addSuppressed(r0)     // Catch:{ all -> 0x01b1 }
        L_0x01d7:
            throw r1     // Catch:{ all -> 0x01b1 }
        L_0x01d8:
            r0 = move-exception
            r26 = r4
            r28 = r9
            goto L_0x01b2
        L_0x01de:
            r26.close()     // Catch:{ all -> 0x01e2 }
            goto L_0x01e6
        L_0x01e2:
            r0 = move-exception
            r1.addSuppressed(r0)     // Catch:{ all -> 0x01cb }
        L_0x01e6:
            throw r1     // Catch:{ all -> 0x01cb }
        L_0x01e7:
            r0 = move-exception
            r28 = r9
            goto L_0x01cc
        L_0x01eb:
            r21 = r4
            r30 = r7
            r28 = r9
            r7 = r10
            r23 = r13
            r8 = r1
            r13 = r3
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ all -> 0x01cb }
            r1.<init>(r0)     // Catch:{ all -> 0x01cb }
            java.nio.channels.FileChannel r3 = r1.getChannel()     // Catch:{ all -> 0x03c7 }
            long r4 = r11 - r21
            long r9 = r3.size()     // Catch:{ all -> 0x03c7 }
            r24 = 8
            r26 = r4
            long r4 = r30 - r24
            long r24 = r21 + r24
            r32 = r9
            long r9 = r24 + r26
            r29 = r7
            r34 = r8
            long r7 = r4 + r24
            r25 = r13
            r24 = r14
            long r13 = r32 - r7
            r32 = r1
            int r1 = (int) r13
            java.nio.ByteBuffer r1 = java.nio.ByteBuffer.allocate(r1)     // Catch:{ all -> 0x02f2 }
            r3.read(r1, r7)     // Catch:{ all -> 0x02f2 }
            r1.flip()     // Catch:{ all -> 0x02f2 }
            java.io.FileOutputStream r7 = new java.io.FileOutputStream     // Catch:{ all -> 0x02f2 }
            r7.<init>(r0)     // Catch:{ all -> 0x02f2 }
            java.nio.channels.FileChannel r8 = r7.getChannel()     // Catch:{ all -> 0x02a4 }
            r8.truncate(r4)     // Catch:{ all -> 0x02a4 }
            long r4 = r9 >> r18
            int r4 = (int) r4     // Catch:{ all -> 0x02a4 }
            byte r4 = (byte) r4     // Catch:{ all -> 0x02a4 }
            r5 = r3
            r33 = r4
            long r3 = r9 >> r19
            int r3 = (int) r3     // Catch:{ all -> 0x02a4 }
            byte r3 = (byte) r3     // Catch:{ all -> 0x02a4 }
            r35 = r3
            long r3 = r9 >> r20
            int r3 = (int) r3     // Catch:{ all -> 0x02a4 }
            byte r3 = (byte) r3     // Catch:{ all -> 0x02a4 }
            int r4 = (int) r9     // Catch:{ all -> 0x02a4 }
            byte r4 = (byte) r4     // Catch:{ all -> 0x02a4 }
            r9 = 4
            byte[] r10 = new byte[r9]     // Catch:{ all -> 0x02a4 }
            r10[r40] = r33     // Catch:{ all -> 0x02a4 }
            r17 = 1
            r10[r17] = r35     // Catch:{ all -> 0x02a4 }
            r10[r16] = r3     // Catch:{ all -> 0x02a4 }
            r10[r37] = r4     // Catch:{ all -> 0x02a4 }
            long r3 = r8.size()     // Catch:{ all -> 0x02a4 }
            java.nio.ByteBuffer r9 = java.nio.ByteBuffer.wrap(r10)     // Catch:{ all -> 0x02a4 }
            r8.write(r9, r3)     // Catch:{ all -> 0x02a4 }
            java.lang.String r9 = "mpvd"
            java.nio.charset.Charset r10 = java.nio.charset.StandardCharsets.UTF_8     // Catch:{ all -> 0x02a4 }
            byte[] r9 = r9.getBytes(r10)     // Catch:{ all -> 0x02a4 }
            java.nio.ByteBuffer r9 = java.nio.ByteBuffer.wrap(r9)     // Catch:{ all -> 0x02a4 }
            r35 = 4
            long r3 = r3 + r35
            r8.write(r9, r3)     // Catch:{ all -> 0x02a4 }
            java.nio.ByteBuffer r3 = java.nio.ByteBuffer.wrap(r23)     // Catch:{ all -> 0x02a4 }
            r9 = r30
            int r3 = r8.write(r3, r9)     // Catch:{ all -> 0x02a4 }
            r23 = r5
            long r4 = (long) r3     // Catch:{ all -> 0x02a4 }
            int r30 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1))
            if (r30 == 0) goto L_0x02a8
            r30 = r4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x02a4 }
            r4.<init>(r2)     // Catch:{ all -> 0x02a4 }
            r4.append(r3)     // Catch:{ all -> 0x02a4 }
            r4.append(r6)     // Catch:{ all -> 0x02a4 }
            r4.append(r11)     // Catch:{ all -> 0x02a4 }
            r3 = r24
            r4.append(r3)     // Catch:{ all -> 0x02a4 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x02a4 }
            r5 = r29
            android.util.Log.w(r5, r4)     // Catch:{ all -> 0x02a4 }
            goto L_0x02ae
        L_0x02a4:
            r0 = move-exception
            r1 = r0
            goto L_0x03be
        L_0x02a8:
            r30 = r4
            r3 = r24
            r5 = r29
        L_0x02ae:
            long r11 = r9 + r30
            int r1 = r8.write(r1, r11)     // Catch:{ all -> 0x02a4 }
            long r11 = (long) r1     // Catch:{ all -> 0x02a4 }
            int r4 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r4 == 0) goto L_0x02d1
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x02a4 }
            r4.<init>(r2)     // Catch:{ all -> 0x02a4 }
            r4.append(r1)     // Catch:{ all -> 0x02a4 }
            r4.append(r15)     // Catch:{ all -> 0x02a4 }
            r4.append(r13)     // Catch:{ all -> 0x02a4 }
            r4.append(r3)     // Catch:{ all -> 0x02a4 }
            java.lang.String r1 = r4.toString()     // Catch:{ all -> 0x02a4 }
            android.util.Log.w(r5, r1)     // Catch:{ all -> 0x02a4 }
        L_0x02d1:
            com.samsung.android.motionphoto.utils.ex.MotionPhotoParser r1 = new com.samsung.android.motionphoto.utils.ex.MotionPhotoParser     // Catch:{ all -> 0x02a4 }
            r1.<init>((java.io.FileDescriptor) r0)     // Catch:{ all -> 0x02a4 }
            boolean r2 = isJpeg((java.io.FileDescriptor) r0)     // Catch:{ all -> 0x02a4 }
            com.samsung.android.motionphoto.utils.ex.MotionPhotoParser$DataPosition64 r2 = r1.parseSEFTail(r2)     // Catch:{ all -> 0x02a4 }
            if (r2 != 0) goto L_0x02f6
            java.lang.String r0 = "Fail to get sef info"
            android.util.Log.w(r5, r0)     // Catch:{ all -> 0x02a4 }
            r1.close()     // Catch:{ all -> 0x02a4 }
            r7.close()     // Catch:{ all -> 0x02f2 }
            r32.close()     // Catch:{ all -> 0x01cb }
            r28.close()
            return r40
        L_0x02f2:
            r0 = move-exception
        L_0x02f3:
            r1 = r0
            goto L_0x03cc
        L_0x02f6:
            r3 = 4
            byte[] r3 = new byte[r3]     // Catch:{ all -> 0x02a4 }
            long r11 = r9 >> r18
            int r4 = (int) r11     // Catch:{ all -> 0x02a4 }
            byte r4 = (byte) r4     // Catch:{ all -> 0x02a4 }
            r3[r40] = r4     // Catch:{ all -> 0x02a4 }
            long r11 = r9 >> r19
            int r4 = (int) r11     // Catch:{ all -> 0x02a4 }
            byte r4 = (byte) r4     // Catch:{ all -> 0x02a4 }
            r17 = 1
            r3[r17] = r4     // Catch:{ all -> 0x02a4 }
            long r11 = r9 >> r20
            int r4 = (int) r11     // Catch:{ all -> 0x02a4 }
            byte r4 = (byte) r4     // Catch:{ all -> 0x02a4 }
            r3[r16] = r4     // Catch:{ all -> 0x02a4 }
            int r4 = (int) r9     // Catch:{ all -> 0x02a4 }
            byte r4 = (byte) r4     // Catch:{ all -> 0x02a4 }
            r3[r37] = r4     // Catch:{ all -> 0x02a4 }
            java.nio.ByteBuffer r4 = java.nio.ByteBuffer.wrap(r3)     // Catch:{ all -> 0x02a4 }
            long r9 = r2.getOffsetPos()     // Catch:{ all -> 0x02a4 }
            r8.write(r4, r9)     // Catch:{ all -> 0x02a4 }
            long r9 = r21 + r26
            long r11 = r9 >> r18
            int r4 = (int) r11     // Catch:{ all -> 0x02a4 }
            byte r4 = (byte) r4     // Catch:{ all -> 0x02a4 }
            r3[r40] = r4     // Catch:{ all -> 0x02a4 }
            long r11 = r9 >> r19
            int r4 = (int) r11     // Catch:{ all -> 0x02a4 }
            byte r4 = (byte) r4     // Catch:{ all -> 0x02a4 }
            r17 = 1
            r3[r17] = r4     // Catch:{ all -> 0x02a4 }
            long r11 = r9 >> r20
            int r4 = (int) r11     // Catch:{ all -> 0x02a4 }
            byte r4 = (byte) r4     // Catch:{ all -> 0x02a4 }
            r3[r16] = r4     // Catch:{ all -> 0x02a4 }
            int r4 = (int) r9     // Catch:{ all -> 0x02a4 }
            byte r4 = (byte) r4     // Catch:{ all -> 0x02a4 }
            r3[r37] = r4     // Catch:{ all -> 0x02a4 }
            java.nio.ByteBuffer r3 = java.nio.ByteBuffer.wrap(r3)     // Catch:{ all -> 0x02a4 }
            long r9 = r2.getLengthPos()     // Catch:{ all -> 0x02a4 }
            r8.write(r3, r9)     // Catch:{ all -> 0x02a4 }
            r8.close()     // Catch:{ all -> 0x02a4 }
            r1.close()     // Catch:{ all -> 0x02a4 }
            r7.close()     // Catch:{ all -> 0x02f2 }
            if (r34 == 0) goto L_0x03ad
            java.util.Set r1 = r34.keySet()     // Catch:{ all -> 0x02f2 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x02f2 }
            r2 = r40
            r3 = r2
        L_0x0357:
            boolean r4 = r1.hasNext()     // Catch:{ all -> 0x02f2 }
            if (r4 == 0) goto L_0x03a8
            java.lang.Object r4 = r1.next()     // Catch:{ all -> 0x02f2 }
            com.samsung.android.motionphoto.utils.ex.MotionPhotoVideoUtils$SEFInfo r4 = (com.samsung.android.motionphoto.utils.ex.MotionPhotoVideoUtils.SEFInfo) r4     // Catch:{ all -> 0x02f2 }
            r8 = r34
            java.lang.Object r6 = r8.get(r4)     // Catch:{ all -> 0x02f2 }
            byte[] r6 = (byte[]) r6     // Catch:{ all -> 0x02f2 }
            if (r6 == 0) goto L_0x03a1
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x02f2 }
            r7.<init>()     // Catch:{ all -> 0x02f2 }
            r13 = r25
            r7.append(r13)     // Catch:{ all -> 0x02f2 }
            java.lang.Integer r9 = r4.sefDataType     // Catch:{ all -> 0x02f2 }
            r7.append(r9)     // Catch:{ all -> 0x02f2 }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x02f2 }
            android.util.Log.d(r5, r7)     // Catch:{ all -> 0x02f2 }
            android.os.ParcelFileDescriptor r7 = android.os.ParcelFileDescriptor.dup(r0)     // Catch:{ all -> 0x02f2 }
            java.lang.String r9 = r4.sefKey     // Catch:{ all -> 0x02f2 }
            java.lang.Integer r10 = r4.sefDataType     // Catch:{ all -> 0x02f2 }
            int r10 = r10.intValue()     // Catch:{ all -> 0x02f2 }
            r14 = 1
            com.samsung.android.media.SemExtendedFormat.addData(r7, r9, r6, r10, r14)     // Catch:{ all -> 0x02f2 }
            int r3 = r3 + 12
            int r2 = r2 + 8
            int r6 = r6.length     // Catch:{ all -> 0x02f2 }
            int r2 = r2 + r6
            java.lang.String r4 = r4.sefKey     // Catch:{ all -> 0x02f2 }
            int r4 = r4.length()     // Catch:{ all -> 0x02f2 }
            int r2 = r2 + r4
            goto L_0x03a3
        L_0x03a1:
            r13 = r25
        L_0x03a3:
            r34 = r8
            r25 = r13
            goto L_0x0357
        L_0x03a8:
            int r3 = r3 + r2
            long r1 = (long) r3     // Catch:{ all -> 0x02f2 }
            long r4 = r26 + r1
            goto L_0x03af
        L_0x03ad:
            r4 = r26
        L_0x03af:
            modifyXmp(r0, r4)     // Catch:{ all -> 0x02f2 }
            r23.close()     // Catch:{ all -> 0x02f2 }
            r32.close()     // Catch:{ all -> 0x01cb }
        L_0x03b8:
            r28.close()
            r17 = 1
            return r17
        L_0x03be:
            r7.close()     // Catch:{ all -> 0x03c2 }
            goto L_0x03c6
        L_0x03c2:
            r0 = move-exception
            r1.addSuppressed(r0)     // Catch:{ all -> 0x02f2 }
        L_0x03c6:
            throw r1     // Catch:{ all -> 0x02f2 }
        L_0x03c7:
            r0 = move-exception
            r32 = r1
            goto L_0x02f3
        L_0x03cc:
            r32.close()     // Catch:{ all -> 0x03d0 }
            goto L_0x03d4
        L_0x03d0:
            r0 = move-exception
            r1.addSuppressed(r0)     // Catch:{ all -> 0x01cb }
        L_0x03d4:
            throw r1     // Catch:{ all -> 0x01cb }
        L_0x03d5:
            r28.close()     // Catch:{ all -> 0x03d9 }
            goto L_0x03dd
        L_0x03d9:
            r0 = move-exception
            r1.addSuppressed(r0)
        L_0x03dd:
            throw r1
        L_0x03de:
            r17 = 1
            return r17
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.motionphoto.utils.ex.MotionPhotoVideoUtils.replaceVideo(java.io.FileDescriptor, java.io.FileDescriptor, java.util.Map):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x0078 A[SYNTHETIC, Splitter:B:39:0x0078] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0083 A[Catch:{ Exception -> 0x005e }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean saveVideo(java.io.File r10, java.io.File r11) {
        /*
            r9 = this;
            java.lang.String r9 = "saveVideo"
            java.lang.String r0 = "MotionPhotoVideoUtilsEx"
            android.util.Log.d(r0, r9)
            r9 = 0
            com.samsung.android.motionphoto.utils.ex.MotionPhotoVideoUtils$VideoInfo r1 = getVideoDataPosition((java.io.File) r10)     // Catch:{ Exception -> 0x005e }
            if (r1 == 0) goto L_0x008a
            long r2 = r1.getVideoLength()     // Catch:{ Exception -> 0x005e }
            r4 = 0
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 <= 0) goto L_0x008a
            long r2 = r1.getVideoOffset()     // Catch:{ Exception -> 0x005e }
            long r4 = r1.getVideoLength()     // Catch:{ Exception -> 0x005e }
            r1 = 1
            r6 = 0
            java.io.FileInputStream r7 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0072 }
            r7.<init>(r10)     // Catch:{ IOException -> 0x0072 }
            java.nio.channels.FileChannel r8 = r7.getChannel()     // Catch:{ all -> 0x0066 }
            r8.position(r2)     // Catch:{ all -> 0x0066 }
            int r2 = (int) r4     // Catch:{ all -> 0x0066 }
            byte[] r3 = new byte[r2]     // Catch:{ all -> 0x0066 }
            int r2 = r7.read(r3, r9, r2)     // Catch:{ all -> 0x0066 }
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch:{ all -> 0x0066 }
            r4.<init>(r11)     // Catch:{ all -> 0x0066 }
            r4.write(r3, r9, r2)     // Catch:{ all -> 0x0054 }
            java.nio.channels.FileChannel r3 = r4.getChannel()     // Catch:{ all -> 0x0054 }
            long r5 = (long) r2     // Catch:{ all -> 0x0054 }
            r3.truncate(r5)     // Catch:{ all -> 0x0054 }
            java.lang.String r2 = "Long_Exposure_Effect_Info"
            byte[] r10 = com.samsung.android.media.SemExtendedFormat.getData(r10, r2)     // Catch:{ all -> 0x0054 }
            if (r10 == 0) goto L_0x0057
            r3 = 3409(0xd51, float:4.777E-42)
            com.samsung.android.media.SemExtendedFormat.addData(r11, r2, r10, r3, r1)     // Catch:{ all -> 0x0054 }
            goto L_0x0057
        L_0x0054:
            r10 = move-exception
            r6 = r4
            goto L_0x0067
        L_0x0057:
            r7.close()     // Catch:{ IOException -> 0x0063, all -> 0x0060 }
            r4.close()     // Catch:{ Exception -> 0x005e }
            goto L_0x007b
        L_0x005e:
            r10 = move-exception
            goto L_0x0087
        L_0x0060:
            r10 = move-exception
            r6 = r4
            goto L_0x0081
        L_0x0063:
            r10 = move-exception
            r6 = r4
            goto L_0x0073
        L_0x0066:
            r10 = move-exception
        L_0x0067:
            r7.close()     // Catch:{ all -> 0x006b }
            goto L_0x006f
        L_0x006b:
            r11 = move-exception
            r10.addSuppressed(r11)     // Catch:{ IOException -> 0x0072 }
        L_0x006f:
            throw r10     // Catch:{ IOException -> 0x0072 }
        L_0x0070:
            r10 = move-exception
            goto L_0x0081
        L_0x0072:
            r10 = move-exception
        L_0x0073:
            r10.printStackTrace()     // Catch:{ all -> 0x0070 }
            if (r6 == 0) goto L_0x007b
            r6.close()     // Catch:{ Exception -> 0x005e }
        L_0x007b:
            java.lang.String r10 = "Success to saveVideo"
            android.util.Log.d(r0, r10)     // Catch:{ Exception -> 0x005e }
            return r1
        L_0x0081:
            if (r6 == 0) goto L_0x0086
            r6.close()     // Catch:{ Exception -> 0x005e }
        L_0x0086:
            throw r10     // Catch:{ Exception -> 0x005e }
        L_0x0087:
            r10.printStackTrace()
        L_0x008a:
            java.lang.String r10 = "Fail to saveVideo"
            android.util.Log.d(r0, r10)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.motionphoto.utils.ex.MotionPhotoVideoUtils.saveVideo(java.io.File, java.io.File):boolean");
    }

    public static VideoInfo getSEFDataPosition(FileDescriptor fileDescriptor) {
        MotionPhotoParser motionPhotoParser = new MotionPhotoParser(fileDescriptor);
        MotionPhotoParser.DataPosition64 parseJpegSefTail = isJpeg(fileDescriptor) ? motionPhotoParser.parseJpegSefTail() : motionPhotoParser.parseHeicSefTail();
        if (parseJpegSefTail == null) {
            Log.w(TAG, "Fail to get sef info");
            motionPhotoParser.close();
            return null;
        }
        Log.d(TAG, "Video info - offset:" + parseJpegSefTail.getOffset() + " length:" + parseJpegSefTail.getLength());
        motionPhotoParser.close();
        return new VideoInfo(parseJpegSefTail.getOffset(), parseJpegSefTail.getLength(), parseJpegSefTail.isMPV2());
    }

    public static boolean isJpeg(String str) {
        String lowerCase = str.toLowerCase(Locale.getDefault());
        return lowerCase.endsWith(".jpg") || lowerCase.endsWith(".jpeg");
    }

    public static VideoInfo getVideoDataPosition(File file) {
        RandomAccessFile randomAccessFile;
        Throwable th;
        FileInputStream fileInputStream;
        Throwable th2;
        long j2;
        long j3;
        boolean isJpeg = isJpeg(file.toString());
        VideoInfo sEFDataPosition = getSEFDataPosition(file);
        long j8 = 0;
        if (sEFDataPosition == null || sEFDataPosition.getVideoLength() <= 0) {
            try {
                randomAccessFile = new RandomAccessFile(file, "rw");
                FileDescriptor fd2 = randomAccessFile.getFD();
                if (isJpeg) {
                    long[] videoDataPositionFromXMP = getVideoDataPositionFromXMP(fd2);
                    if (videoDataPositionFromXMP != null) {
                        j8 = videoDataPositionFromXMP[0];
                        j3 = videoDataPositionFromXMP[1];
                    } else {
                        j3 = 0;
                    }
                    j2 = j3;
                } else {
                    fileInputStream = new FileInputStream(fd2);
                    fileInputStream.getChannel().position(0);
                    j8 = HEIFParser.getVideoOffset(fileInputStream);
                    long size = fileInputStream.getChannel().size() - j8;
                    fileInputStream.close();
                    j2 = size;
                }
                long j10 = j8;
                randomAccessFile.close();
                StringBuilder j11 = j.j(j10, "Success to get it from XMP, off=", " len=");
                j11.append(j2);
                Log.d(TAG, j11.toString());
                return new VideoInfo(j10, j2, 0);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            } catch (Throwable th3) {
                th.addSuppressed(th3);
            }
        } else {
            long videoOffset = sEFDataPosition.getVideoOffset();
            long videoLength = sEFDataPosition.getVideoLength();
            StringBuilder j12 = j.j(videoOffset, "Success to get video info from SEF, off=", " len=");
            j12.append(videoLength);
            Log.d(TAG, j12.toString());
            return new VideoInfo(videoOffset, videoLength, sEFDataPosition.isMotionPhotoV2());
        }
        throw th2;
        throw th;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ byte[] lambda$sortSEFDataMap$1(byte[] bArr, byte[] bArr2) {
        return bArr;
    }

    /* JADX WARNING: Removed duplicated region for block: B:48:0x00c3 A[Catch:{ Exception -> 0x006e }] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00cc A[Catch:{ Exception -> 0x006e }] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00d5 A[Catch:{ Exception -> 0x006e }] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00de A[Catch:{ Exception -> 0x006e }] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00e7 A[Catch:{ Exception -> 0x006e }] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0100 A[Catch:{ Exception -> 0x006e }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean deleteVideo(java.io.File r21, java.io.File r22) {
        /*
            r20 = this;
            r1 = r22
            java.lang.String r2 = "Long_Exposure_Effect_Info"
            java.lang.String r3 = "MotionPhoto_AutoPlay"
            java.lang.String r4 = "MotionPhoto_Info"
            java.lang.String r5 = "MotionPhoto_Version"
            java.lang.String r6 = "MotionPhoto_Data"
            java.lang.String r0 = "deleteVideo"
            java.lang.String r7 = "MotionPhotoVideoUtilsEx"
            android.util.Log.i(r7, r0)
            r8 = 0
            r0 = r21
            boolean r9 = com.samsung.android.media.SemExtendedFormat.hasData(r0, r6)     // Catch:{ Exception -> 0x0023 }
            if (r9 != 0) goto L_0x0028
            java.lang.String r0 = "we only support deleting for motion photo"
            android.util.Log.w(r7, r0)     // Catch:{ Exception -> 0x0023 }
            return r8
        L_0x0023:
            r0 = move-exception
            r20 = r8
            goto L_0x0119
        L_0x0028:
            java.nio.file.Path r9 = r0.toPath()     // Catch:{ Exception -> 0x0023 }
            java.nio.file.Path r10 = r1.toPath()     // Catch:{ Exception -> 0x0023 }
            r11 = 1
            java.nio.file.CopyOption[] r12 = new java.nio.file.CopyOption[r11]     // Catch:{ Exception -> 0x0023 }
            java.nio.file.StandardCopyOption r13 = java.nio.file.StandardCopyOption.REPLACE_EXISTING     // Catch:{ Exception -> 0x0023 }
            r12[r8] = r13     // Catch:{ Exception -> 0x0023 }
            java.nio.file.Files.copy(r9, r10, r12)     // Catch:{ Exception -> 0x0023 }
            com.samsung.android.motionphoto.utils.ex.MotionPhotoVideoUtils$VideoInfo r0 = getVideoDataPosition((java.io.File) r0)     // Catch:{ Exception -> 0x0023 }
            if (r0 == 0) goto L_0x0116
            long r9 = r0.getVideoLength()     // Catch:{ Exception -> 0x0023 }
            r12 = 0
            int r9 = (r9 > r12 ? 1 : (r9 == r12 ? 0 : -1))
            if (r9 <= 0) goto L_0x0116
            long r9 = r0.getVideoOffset()     // Catch:{ Exception -> 0x0023 }
            long r12 = r0.getVideoLength()     // Catch:{ Exception -> 0x0023 }
            int r14 = r0.isMotionPhotoV2()     // Catch:{ Exception -> 0x0023 }
            r15 = 3
            r20 = r8
            java.lang.String r8 = "rw"
            if (r14 == r15) goto L_0x0071
            int r0 = r0.isMotionPhotoV2()     // Catch:{ Exception -> 0x006e }
            r14 = 4
            if (r0 != r14) goto L_0x0066
            goto L_0x0071
        L_0x0066:
            java.lang.String r0 = "Not MotionPhotoV2"
            android.util.Log.d(r7, r0)     // Catch:{ Exception -> 0x006e }
            r17 = r11
            goto L_0x00bd
        L_0x006e:
            r0 = move-exception
            goto L_0x0119
        L_0x0071:
            java.io.RandomAccessFile r14 = new java.io.RandomAccessFile     // Catch:{ Exception -> 0x00b7 }
            r14.<init>(r1, r8)     // Catch:{ Exception -> 0x00b7 }
            long r15 = r1.length()     // Catch:{ all -> 0x00aa }
            long r12 = r12 + r9
            r17 = r11
            r18 = r12
            long r11 = r15 - r18
            int r0 = (int) r11
            java.nio.ByteBuffer r0 = java.nio.ByteBuffer.allocate(r0)     // Catch:{ all -> 0x00a7 }
            java.nio.channels.FileChannel r13 = r14.getChannel()     // Catch:{ all -> 0x00a7 }
            r15 = r9
            r9 = r18
            r13.read(r0, r9)     // Catch:{ all -> 0x00a7 }
            r0.flip()     // Catch:{ all -> 0x00a7 }
            r9 = 8
            long r9 = r15 - r9
            r13.write(r0, r9)     // Catch:{ all -> 0x00a7 }
            long r9 = r9 + r11
            r13.truncate(r9)     // Catch:{ all -> 0x00a7 }
            r13.close()     // Catch:{ all -> 0x00a7 }
            r14.close()     // Catch:{ Exception -> 0x00a5 }
            goto L_0x00bd
        L_0x00a5:
            r0 = move-exception
            goto L_0x00ba
        L_0x00a7:
            r0 = move-exception
        L_0x00a8:
            r9 = r0
            goto L_0x00ae
        L_0x00aa:
            r0 = move-exception
            r17 = r11
            goto L_0x00a8
        L_0x00ae:
            r14.close()     // Catch:{ all -> 0x00b2 }
            goto L_0x00b6
        L_0x00b2:
            r0 = move-exception
            r9.addSuppressed(r0)     // Catch:{ Exception -> 0x00a5 }
        L_0x00b6:
            throw r9     // Catch:{ Exception -> 0x00a5 }
        L_0x00b7:
            r0 = move-exception
            r17 = r11
        L_0x00ba:
            r0.printStackTrace()     // Catch:{ Exception -> 0x006e }
        L_0x00bd:
            boolean r0 = com.samsung.android.media.SemExtendedFormat.hasData(r1, r6)     // Catch:{ Exception -> 0x006e }
            if (r0 == 0) goto L_0x00c6
            com.samsung.android.media.SemExtendedFormat.deleteData(r1, r6)     // Catch:{ Exception -> 0x006e }
        L_0x00c6:
            boolean r0 = com.samsung.android.media.SemExtendedFormat.hasData(r1, r5)     // Catch:{ Exception -> 0x006e }
            if (r0 == 0) goto L_0x00cf
            com.samsung.android.media.SemExtendedFormat.deleteData(r1, r5)     // Catch:{ Exception -> 0x006e }
        L_0x00cf:
            boolean r0 = com.samsung.android.media.SemExtendedFormat.hasData(r1, r4)     // Catch:{ Exception -> 0x006e }
            if (r0 == 0) goto L_0x00d8
            com.samsung.android.media.SemExtendedFormat.deleteData(r1, r4)     // Catch:{ Exception -> 0x006e }
        L_0x00d8:
            boolean r0 = com.samsung.android.media.SemExtendedFormat.hasData(r1, r3)     // Catch:{ Exception -> 0x006e }
            if (r0 == 0) goto L_0x00e1
            com.samsung.android.media.SemExtendedFormat.deleteData(r1, r3)     // Catch:{ Exception -> 0x006e }
        L_0x00e1:
            boolean r0 = com.samsung.android.media.SemExtendedFormat.hasData(r1, r2)     // Catch:{ Exception -> 0x006e }
            if (r0 == 0) goto L_0x00ea
            com.samsung.android.media.SemExtendedFormat.deleteData(r1, r2)     // Catch:{ Exception -> 0x006e }
        L_0x00ea:
            java.io.RandomAccessFile r0 = new java.io.RandomAccessFile     // Catch:{ Exception -> 0x006e }
            r0.<init>(r1, r8)     // Catch:{ Exception -> 0x006e }
            android.media.ExifInterface r1 = new android.media.ExifInterface     // Catch:{ Exception -> 0x006e }
            java.io.FileDescriptor r2 = r0.getFD()     // Catch:{ Exception -> 0x006e }
            r1.<init>(r2)     // Catch:{ Exception -> 0x006e }
            java.lang.String r2 = "Xmp"
            byte[] r1 = r1.getAttributeBytes(r2)     // Catch:{ Exception -> 0x006e }
            if (r1 == 0) goto L_0x010d
            com.samsung.android.motionphoto.utils.ex.XMPParser$Companion r1 = com.samsung.android.motionphoto.utils.ex.XMPParser.Companion     // Catch:{ Exception -> 0x006e }
            java.io.FileDescriptor r2 = r0.getFD()     // Catch:{ Exception -> 0x006e }
            com.samsung.android.motionphoto.utils.ex.XMPParser r1 = r1.create(r2)     // Catch:{ Exception -> 0x006e }
            r1.removeXmp()     // Catch:{ Exception -> 0x006e }
        L_0x010d:
            r0.close()     // Catch:{ Exception -> 0x006e }
            java.lang.String r0 = "Success to deleteVideo"
            android.util.Log.d(r7, r0)     // Catch:{ Exception -> 0x006e }
            return r17
        L_0x0116:
            r20 = r8
            goto L_0x011c
        L_0x0119:
            r0.printStackTrace()
        L_0x011c:
            java.lang.String r0 = "Fail to deleteVideo"
            android.util.Log.d(r7, r0)
            return r20
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.motionphoto.utils.ex.MotionPhotoVideoUtils.deleteVideo(java.io.File, java.io.File):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0146, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0160, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:?, code lost:
        r1.addSuppressed(r0);
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:32:0x0141, B:46:0x015c] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean replaceVideo(java.io.FileDescriptor r39, java.io.FileDescriptor r40) {
        /*
            r38 = this;
            r0 = r39
            boolean r1 = isJpeg((java.io.FileDescriptor) r0)
            com.samsung.android.motionphoto.utils.ex.MotionPhotoVideoUtils$VideoInfo r2 = getVideoDataPosition((java.io.FileDescriptor) r0)
            long r3 = r2.getVideoLength()
            r5 = 0
            int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r3 <= 0) goto L_0x02eb
            long r5 = r2.getVideoOffset()
            long r2 = r2.getVideoLength()
            java.lang.String r7 = "offset:"
            java.lang.String r8 = "length:"
            java.lang.StringBuilder r7 = N2.j.j(r5, r7, r8)
            r7.append(r2)
            java.lang.String r7 = r7.toString()
            java.lang.String r8 = "MotionPhotoVideoUtilsEx"
            android.util.Log.d(r8, r7)
            java.io.FileInputStream r7 = new java.io.FileInputStream
            r9 = r40
            r7.<init>(r9)
            java.nio.channels.FileChannel r9 = r7.getChannel()     // Catch:{ all -> 0x0165 }
            long r9 = r9.size()     // Catch:{ all -> 0x0165 }
            int r11 = (int) r9     // Catch:{ all -> 0x0165 }
            byte[] r11 = new byte[r11]     // Catch:{ all -> 0x0165 }
            r7.read(r11)     // Catch:{ all -> 0x0165 }
            java.lang.String r15 = "] is not the same with sefTailSize["
            r38 = 1
            java.lang.String r4 = "] is not the same with videoSize["
            r16 = 0
            r17 = 24
            r18 = 16
            r19 = 8
            r40 = 3
            java.lang.String r13 = "]"
            r20 = 2
            java.lang.String r14 = "writtenByte["
            if (r1 == 0) goto L_0x0169
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ all -> 0x0165 }
            r1.<init>(r0)     // Catch:{ all -> 0x0165 }
            java.nio.channels.FileChannel r12 = r1.getChannel()     // Catch:{ all -> 0x0156 }
            long r21 = r12.size()     // Catch:{ all -> 0x0156 }
            r23 = r2
            r3 = r1
            long r1 = r5 + r23
            r26 = r7
            r25 = r8
            long r7 = r21 - r1
            r21 = r3
            int r3 = (int) r7
            java.nio.ByteBuffer r3 = java.nio.ByteBuffer.allocate(r3)     // Catch:{ all -> 0x014a }
            r12.read(r3, r1)     // Catch:{ all -> 0x014a }
            r3.flip()     // Catch:{ all -> 0x014a }
            long r1 = r9 - r23
            r22 = r11
            java.io.FileOutputStream r11 = new java.io.FileOutputStream     // Catch:{ all -> 0x014a }
            r11.<init>(r0)     // Catch:{ all -> 0x014a }
            r23 = r11
            java.nio.channels.FileChannel r11 = r23.getChannel()     // Catch:{ all -> 0x00c3 }
            r11.truncate(r5)     // Catch:{ all -> 0x00c3 }
            r27 = r12
            java.nio.ByteBuffer r12 = java.nio.ByteBuffer.wrap(r22)     // Catch:{ all -> 0x00c3 }
            int r12 = r11.write(r12, r5)     // Catch:{ all -> 0x00c3 }
            r28 = r5
            long r5 = (long) r12     // Catch:{ all -> 0x00c3 }
            int r22 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r22 == 0) goto L_0x00c7
            r30 = r5
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x00c3 }
            r5.<init>(r14)     // Catch:{ all -> 0x00c3 }
            r5.append(r12)     // Catch:{ all -> 0x00c3 }
            r5.append(r4)     // Catch:{ all -> 0x00c3 }
            r5.append(r9)     // Catch:{ all -> 0x00c3 }
            r5.append(r13)     // Catch:{ all -> 0x00c3 }
            java.lang.String r4 = r5.toString()     // Catch:{ all -> 0x00c3 }
            r5 = r25
            android.util.Log.w(r5, r4)     // Catch:{ all -> 0x00c3 }
            goto L_0x00cb
        L_0x00c3:
            r0 = move-exception
            r1 = r0
            goto L_0x014d
        L_0x00c7:
            r30 = r5
            r5 = r25
        L_0x00cb:
            long r9 = r28 + r30
            int r3 = r11.write(r3, r9)     // Catch:{ all -> 0x00c3 }
            long r9 = (long) r3     // Catch:{ all -> 0x00c3 }
            int r4 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r4 == 0) goto L_0x00ee
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x00c3 }
            r4.<init>(r14)     // Catch:{ all -> 0x00c3 }
            r4.append(r3)     // Catch:{ all -> 0x00c3 }
            r4.append(r15)     // Catch:{ all -> 0x00c3 }
            r4.append(r7)     // Catch:{ all -> 0x00c3 }
            r4.append(r13)     // Catch:{ all -> 0x00c3 }
            java.lang.String r3 = r4.toString()     // Catch:{ all -> 0x00c3 }
            android.util.Log.w(r5, r3)     // Catch:{ all -> 0x00c3 }
        L_0x00ee:
            com.samsung.android.motionphoto.utils.ex.MotionPhotoParser r3 = new com.samsung.android.motionphoto.utils.ex.MotionPhotoParser     // Catch:{ all -> 0x00c3 }
            r3.<init>((java.io.FileDescriptor) r0)     // Catch:{ all -> 0x00c3 }
            r3.readJpegSefTail(r1)     // Catch:{ all -> 0x00c3 }
            java.util.concurrent.LinkedBlockingQueue r4 = r3.getSefTailDataQueue()     // Catch:{ all -> 0x00c3 }
        L_0x00fa:
            boolean r5 = r4.isEmpty()     // Catch:{ all -> 0x00c3 }
            if (r5 != 0) goto L_0x0132
            java.lang.Object r5 = r4.poll()     // Catch:{ all -> 0x00c3 }
            com.samsung.android.motionphoto.utils.ex.MotionPhotoParser$SEFTailData r5 = (com.samsung.android.motionphoto.utils.ex.MotionPhotoParser.SEFTailData) r5     // Catch:{ all -> 0x00c3 }
            java.util.Objects.requireNonNull(r5)     // Catch:{ all -> 0x00c3 }
            long r6 = r5.getValue()     // Catch:{ all -> 0x00c3 }
            int r8 = (int) r6     // Catch:{ all -> 0x00c3 }
            byte r8 = (byte) r8     // Catch:{ all -> 0x00c3 }
            long r9 = r6 >> r19
            int r9 = (int) r9     // Catch:{ all -> 0x00c3 }
            byte r9 = (byte) r9     // Catch:{ all -> 0x00c3 }
            long r12 = r6 >> r18
            int r10 = (int) r12     // Catch:{ all -> 0x00c3 }
            byte r10 = (byte) r10     // Catch:{ all -> 0x00c3 }
            long r6 = r6 >> r17
            int r6 = (int) r6     // Catch:{ all -> 0x00c3 }
            byte r6 = (byte) r6     // Catch:{ all -> 0x00c3 }
            r7 = 4
            byte[] r12 = new byte[r7]     // Catch:{ all -> 0x00c3 }
            r12[r16] = r8     // Catch:{ all -> 0x00c3 }
            r12[r38] = r9     // Catch:{ all -> 0x00c3 }
            r12[r20] = r10     // Catch:{ all -> 0x00c3 }
            r12[r40] = r6     // Catch:{ all -> 0x00c3 }
            java.nio.ByteBuffer r6 = java.nio.ByteBuffer.wrap(r12)     // Catch:{ all -> 0x00c3 }
            long r7 = r5.getOffset()     // Catch:{ all -> 0x00c3 }
            r11.write(r6, r7)     // Catch:{ all -> 0x00c3 }
            goto L_0x00fa
        L_0x0132:
            r11.close()     // Catch:{ all -> 0x00c3 }
            r3.close()     // Catch:{ all -> 0x00c3 }
            r23.close()     // Catch:{ all -> 0x014a }
            modifyXmp(r0, r1)     // Catch:{ all -> 0x014a }
            r27.close()     // Catch:{ all -> 0x014a }
            r21.close()     // Catch:{ all -> 0x0146 }
            goto L_0x02c3
        L_0x0146:
            r0 = move-exception
        L_0x0147:
            r1 = r0
            goto L_0x02e2
        L_0x014a:
            r0 = move-exception
        L_0x014b:
            r1 = r0
            goto L_0x015c
        L_0x014d:
            r23.close()     // Catch:{ all -> 0x0151 }
            goto L_0x0155
        L_0x0151:
            r0 = move-exception
            r1.addSuppressed(r0)     // Catch:{ all -> 0x014a }
        L_0x0155:
            throw r1     // Catch:{ all -> 0x014a }
        L_0x0156:
            r0 = move-exception
            r21 = r1
            r26 = r7
            goto L_0x014b
        L_0x015c:
            r21.close()     // Catch:{ all -> 0x0160 }
            goto L_0x0164
        L_0x0160:
            r0 = move-exception
            r1.addSuppressed(r0)     // Catch:{ all -> 0x0146 }
        L_0x0164:
            throw r1     // Catch:{ all -> 0x0146 }
        L_0x0165:
            r0 = move-exception
            r26 = r7
            goto L_0x0147
        L_0x0169:
            r23 = r2
            r28 = r5
            r26 = r7
            r5 = r8
            r22 = r11
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ all -> 0x0146 }
            r1.<init>(r0)     // Catch:{ all -> 0x0146 }
            java.nio.channels.FileChannel r2 = r1.getChannel()     // Catch:{ all -> 0x02d5 }
            long r6 = r9 - r23
            long r11 = r2.size()     // Catch:{ all -> 0x02d5 }
            r30 = 8
            r32 = r11
            long r11 = r28 - r30
            long r30 = r23 + r30
            r34 = r6
            long r6 = r30 + r34
            r36 = r9
            long r8 = r11 + r30
            r10 = r4
            long r3 = r32 - r8
            r21 = r1
            int r1 = (int) r3
            java.nio.ByteBuffer r1 = java.nio.ByteBuffer.allocate(r1)     // Catch:{ all -> 0x0265 }
            r2.read(r1, r8)     // Catch:{ all -> 0x0265 }
            r1.flip()     // Catch:{ all -> 0x0265 }
            java.io.FileOutputStream r8 = new java.io.FileOutputStream     // Catch:{ all -> 0x0265 }
            r8.<init>(r0)     // Catch:{ all -> 0x0265 }
            java.nio.channels.FileChannel r9 = r8.getChannel()     // Catch:{ all -> 0x02c7 }
            r9.truncate(r11)     // Catch:{ all -> 0x02c7 }
            long r11 = r6 >> r17
            int r11 = (int) r11     // Catch:{ all -> 0x02c7 }
            byte r11 = (byte) r11     // Catch:{ all -> 0x02c7 }
            r12 = r10
            r25 = r11
            long r10 = r6 >> r18
            int r10 = (int) r10     // Catch:{ all -> 0x02c7 }
            byte r10 = (byte) r10     // Catch:{ all -> 0x02c7 }
            r27 = r10
            long r10 = r6 >> r19
            int r10 = (int) r10     // Catch:{ all -> 0x02c7 }
            byte r10 = (byte) r10     // Catch:{ all -> 0x02c7 }
            int r6 = (int) r6     // Catch:{ all -> 0x02c7 }
            byte r6 = (byte) r6     // Catch:{ all -> 0x02c7 }
            r7 = 4
            byte[] r11 = new byte[r7]     // Catch:{ all -> 0x02c7 }
            r11[r16] = r25     // Catch:{ all -> 0x02c7 }
            r11[r38] = r27     // Catch:{ all -> 0x02c7 }
            r11[r20] = r10     // Catch:{ all -> 0x02c7 }
            r11[r40] = r6     // Catch:{ all -> 0x02c7 }
            long r6 = r9.size()     // Catch:{ all -> 0x02c7 }
            java.nio.ByteBuffer r10 = java.nio.ByteBuffer.wrap(r11)     // Catch:{ all -> 0x02c7 }
            r9.write(r10, r6)     // Catch:{ all -> 0x02c7 }
            java.lang.String r10 = "mpvd"
            java.nio.charset.Charset r11 = java.nio.charset.StandardCharsets.UTF_8     // Catch:{ all -> 0x02c7 }
            byte[] r10 = r10.getBytes(r11)     // Catch:{ all -> 0x02c7 }
            java.nio.ByteBuffer r10 = java.nio.ByteBuffer.wrap(r10)     // Catch:{ all -> 0x02c7 }
            r30 = 4
            long r6 = r6 + r30
            r9.write(r10, r6)     // Catch:{ all -> 0x02c7 }
            java.nio.ByteBuffer r6 = java.nio.ByteBuffer.wrap(r22)     // Catch:{ all -> 0x02c7 }
            r10 = r28
            int r6 = r9.write(r6, r10)     // Catch:{ all -> 0x02c7 }
            r22 = r8
            long r7 = (long) r6
            int r25 = (r7 > r36 ? 1 : (r7 == r36 ? 0 : -1))
            if (r25 == 0) goto L_0x021d
            r25 = r2
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0219 }
            r2.<init>(r14)     // Catch:{ all -> 0x0219 }
            r2.append(r6)     // Catch:{ all -> 0x0219 }
            r2.append(r12)     // Catch:{ all -> 0x0219 }
            r27 = r7
            r6 = r36
            r2.append(r6)     // Catch:{ all -> 0x0219 }
            r2.append(r13)     // Catch:{ all -> 0x0219 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0219 }
            android.util.Log.w(r5, r2)     // Catch:{ all -> 0x0219 }
            goto L_0x0221
        L_0x0219:
            r0 = move-exception
        L_0x021a:
            r1 = r0
            goto L_0x02cc
        L_0x021d:
            r25 = r2
            r27 = r7
        L_0x0221:
            long r6 = r10 + r27
            int r1 = r9.write(r1, r6)     // Catch:{ all -> 0x0219 }
            long r6 = (long) r1     // Catch:{ all -> 0x0219 }
            int r2 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r2 == 0) goto L_0x0244
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0219 }
            r2.<init>(r14)     // Catch:{ all -> 0x0219 }
            r2.append(r1)     // Catch:{ all -> 0x0219 }
            r2.append(r15)     // Catch:{ all -> 0x0219 }
            r2.append(r3)     // Catch:{ all -> 0x0219 }
            r2.append(r13)     // Catch:{ all -> 0x0219 }
            java.lang.String r1 = r2.toString()     // Catch:{ all -> 0x0219 }
            android.util.Log.w(r5, r1)     // Catch:{ all -> 0x0219 }
        L_0x0244:
            com.samsung.android.motionphoto.utils.ex.MotionPhotoParser r1 = new com.samsung.android.motionphoto.utils.ex.MotionPhotoParser     // Catch:{ all -> 0x0219 }
            r1.<init>((java.io.FileDescriptor) r0)     // Catch:{ all -> 0x0219 }
            boolean r2 = isJpeg((java.io.FileDescriptor) r0)     // Catch:{ all -> 0x0219 }
            com.samsung.android.motionphoto.utils.ex.MotionPhotoParser$DataPosition64 r2 = r1.parseSEFTail(r2)     // Catch:{ all -> 0x0219 }
            if (r2 != 0) goto L_0x0268
            java.lang.String r0 = "Fail to get sef info"
            android.util.Log.w(r5, r0)     // Catch:{ all -> 0x0219 }
            r1.close()     // Catch:{ all -> 0x0219 }
            r22.close()     // Catch:{ all -> 0x0265 }
            r21.close()     // Catch:{ all -> 0x0146 }
            r26.close()
            return r16
        L_0x0265:
            r0 = move-exception
        L_0x0266:
            r1 = r0
            goto L_0x02d9
        L_0x0268:
            r7 = 4
            byte[] r3 = new byte[r7]     // Catch:{ all -> 0x0219 }
            long r4 = r10 >> r17
            int r4 = (int) r4     // Catch:{ all -> 0x0219 }
            byte r4 = (byte) r4     // Catch:{ all -> 0x0219 }
            r3[r16] = r4     // Catch:{ all -> 0x0219 }
            long r4 = r10 >> r18
            int r4 = (int) r4     // Catch:{ all -> 0x0219 }
            byte r4 = (byte) r4     // Catch:{ all -> 0x0219 }
            r3[r38] = r4     // Catch:{ all -> 0x0219 }
            long r4 = r10 >> r19
            int r4 = (int) r4     // Catch:{ all -> 0x0219 }
            byte r4 = (byte) r4     // Catch:{ all -> 0x0219 }
            r3[r20] = r4     // Catch:{ all -> 0x0219 }
            int r4 = (int) r10     // Catch:{ all -> 0x0219 }
            byte r4 = (byte) r4     // Catch:{ all -> 0x0219 }
            r3[r40] = r4     // Catch:{ all -> 0x0219 }
            java.nio.ByteBuffer r4 = java.nio.ByteBuffer.wrap(r3)     // Catch:{ all -> 0x0219 }
            long r5 = r2.getOffsetPos()     // Catch:{ all -> 0x0219 }
            r9.write(r4, r5)     // Catch:{ all -> 0x0219 }
            long r4 = r23 + r34
            long r6 = r4 >> r17
            int r6 = (int) r6     // Catch:{ all -> 0x0219 }
            byte r6 = (byte) r6     // Catch:{ all -> 0x0219 }
            r3[r16] = r6     // Catch:{ all -> 0x0219 }
            long r6 = r4 >> r18
            int r6 = (int) r6     // Catch:{ all -> 0x0219 }
            byte r6 = (byte) r6     // Catch:{ all -> 0x0219 }
            r3[r38] = r6     // Catch:{ all -> 0x0219 }
            long r6 = r4 >> r19
            int r6 = (int) r6     // Catch:{ all -> 0x0219 }
            byte r6 = (byte) r6     // Catch:{ all -> 0x0219 }
            r3[r20] = r6     // Catch:{ all -> 0x0219 }
            int r4 = (int) r4     // Catch:{ all -> 0x0219 }
            byte r4 = (byte) r4     // Catch:{ all -> 0x0219 }
            r3[r40] = r4     // Catch:{ all -> 0x0219 }
            java.nio.ByteBuffer r3 = java.nio.ByteBuffer.wrap(r3)     // Catch:{ all -> 0x0219 }
            long r4 = r2.getLengthPos()     // Catch:{ all -> 0x0219 }
            r9.write(r3, r4)     // Catch:{ all -> 0x0219 }
            r9.close()     // Catch:{ all -> 0x0219 }
            r1.close()     // Catch:{ all -> 0x0219 }
            r22.close()     // Catch:{ all -> 0x0265 }
            r9 = r34
            modifyXmp(r0, r9)     // Catch:{ all -> 0x0265 }
            r25.close()     // Catch:{ all -> 0x0265 }
            r21.close()     // Catch:{ all -> 0x0146 }
        L_0x02c3:
            r26.close()
            goto L_0x02ed
        L_0x02c7:
            r0 = move-exception
            r22 = r8
            goto L_0x021a
        L_0x02cc:
            r22.close()     // Catch:{ all -> 0x02d0 }
            goto L_0x02d4
        L_0x02d0:
            r0 = move-exception
            r1.addSuppressed(r0)     // Catch:{ all -> 0x0265 }
        L_0x02d4:
            throw r1     // Catch:{ all -> 0x0265 }
        L_0x02d5:
            r0 = move-exception
            r21 = r1
            goto L_0x0266
        L_0x02d9:
            r21.close()     // Catch:{ all -> 0x02dd }
            goto L_0x02e1
        L_0x02dd:
            r0 = move-exception
            r1.addSuppressed(r0)     // Catch:{ all -> 0x0146 }
        L_0x02e1:
            throw r1     // Catch:{ all -> 0x0146 }
        L_0x02e2:
            r26.close()     // Catch:{ all -> 0x02e6 }
            goto L_0x02ea
        L_0x02e6:
            r0 = move-exception
            r1.addSuppressed(r0)
        L_0x02ea:
            throw r1
        L_0x02eb:
            r38 = 1
        L_0x02ed:
            return r38
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.motionphoto.utils.ex.MotionPhotoVideoUtils.replaceVideo(java.io.FileDescriptor, java.io.FileDescriptor):boolean");
    }
}
