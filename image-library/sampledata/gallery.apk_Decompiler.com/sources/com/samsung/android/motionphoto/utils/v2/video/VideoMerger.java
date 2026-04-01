package com.samsung.android.motionphoto.utils.v2.video;

import Ae.b;
import Tf.v;
import android.media.MediaCodec;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import com.samsung.android.motionphoto.utils.v2.CommonsKt;
import com.samsung.android.motionphoto.utils.v2.MPFile;
import com.samsung.android.motionphoto.utils.v2.MimeType;
import com.samsung.android.sum.core.SLog;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.i;
import ne.C1192j;
import ne.C1194l;

@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\t\u0018\u0000 82\u00060\u0001j\u0002`\u0002:\u00018B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0015\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\n\u0010\u000bJ\u001d\u0010\u0010\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0010\u0010\u0011J\u0015\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u0012¢\u0006\u0004\b\u0014\u0010\u0015J\r\u0010\u0016\u001a\u00020\t¢\u0006\u0004\b\u0016\u0010\u0017J\r\u0010\u0018\u001a\u00020\t¢\u0006\u0004\b\u0018\u0010\u0017J\u000f\u0010\u0019\u001a\u00020\tH\u0016¢\u0006\u0004\b\u0019\u0010\u0017JO\u0010%\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\u001a2$\u0010\"\u001a \u0012\u0004\u0012\u00020\u001d\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020!0\u001f0\u001e0\u001c2\u0012\u0010$\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001d0#\"\u00020\u001d¢\u0006\u0004\b%\u0010&J+\u0010%\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\u001a2\u0012\u0010$\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001d0#\"\u00020\u001dH\u0007¢\u0006\u0004\b%\u0010'J\u0015\u0010*\u001a\u00020\t2\u0006\u0010)\u001a\u00020(¢\u0006\u0004\b*\u0010+R\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010,R,\u0010.\u001a\u001a\u0012\u0004\u0012\u00020\u001d\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00120\u001f0-8\u0002X\u0004¢\u0006\u0006\n\u0004\b.\u0010/R\"\u00101\u001a\u0002008\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b1\u00102\u001a\u0004\b3\u00104\"\u0004\b5\u00106R\u0018\u0010)\u001a\u0004\u0018\u00010(8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b)\u00107¨\u00069"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/video/VideoMerger;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "Landroid/media/MediaMuxer;", "muxer", "<init>", "(Landroid/media/MediaMuxer;)V", "Lcom/samsung/android/motionphoto/utils/v2/video/VideoTransferringTask;", "task", "Lme/x;", "process", "(Lcom/samsung/android/motionphoto/utils/v2/video/VideoTransferringTask;)V", "Lcom/samsung/android/motionphoto/utils/v2/MimeType;", "codecType", "Landroid/media/MediaFormat;", "format", "addTrack", "(Lcom/samsung/android/motionphoto/utils/v2/MimeType;Landroid/media/MediaFormat;)V", "", "orientation", "setOrientationHint", "(I)V", "start", "()V", "stop", "close", "Lcom/samsung/android/motionphoto/utils/v2/MPFile;", "mpFile", "", "", "", "Lme/i;", "Ljava/nio/ByteBuffer;", "Landroid/media/MediaCodec$BufferInfo;", "samples", "", "targetMimes", "writeTrack", "(Lcom/samsung/android/motionphoto/utils/v2/MPFile;Ljava/util/Map;[Ljava/lang/String;)V", "(Lcom/samsung/android/motionphoto/utils/v2/MPFile;[Ljava/lang/String;)V", "Lcom/samsung/android/motionphoto/utils/v2/video/EventListener;", "eventListener", "setOnEventListener", "(Lcom/samsung/android/motionphoto/utils/v2/video/EventListener;)V", "Landroid/media/MediaMuxer;", "", "trackInfos", "Ljava/util/Map;", "", "lastTrackTimestampUs", "J", "getLastTrackTimestampUs$motionphoto_utils_v2_0_17_release", "()J", "setLastTrackTimestampUs$motionphoto_utils_v2_0_17_release", "(J)V", "Lcom/samsung/android/motionphoto/utils/v2/video/EventListener;", "Companion", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VideoMerger implements AutoCloseable {
    public static final Companion Companion = new Companion((e) null);
    private static final String TAG;
    private EventListener eventListener;
    private long lastTrackTimestampUs;
    private final MediaMuxer muxer;
    private final Map<String, i> trackInfos = new LinkedHashMap();

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/video/VideoMerger$Companion;", "", "<init>", "()V", "TAG", "", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    static {
        String tagOf = SLog.tagOf(VideoMerger.class);
        j.d(tagOf, "tagOf(...)");
        TAG = tagOf;
    }

    public VideoMerger(MediaMuxer mediaMuxer) {
        j.e(mediaMuxer, "muxer");
        this.muxer = mediaMuxer;
    }

    public final void addTrack(MimeType mimeType, MediaFormat mediaFormat) {
        j.e(mimeType, "codecType");
        j.e(mediaFormat, "format");
        this.trackInfos.put(mimeType.getValue(), new i(Integer.valueOf(this.muxer.addTrack(mediaFormat)), Integer.valueOf(mediaFormat.getInteger("max-input-size"))));
    }

    public void close() {
        this.muxer.release();
    }

    public final long getLastTrackTimestampUs$motionphoto_utils_v2_0_17_release() {
        return this.lastTrackTimestampUs;
    }

    public final void process(VideoTransferringTask videoTransferringTask) {
        j.e(videoTransferringTask, "task");
        long currentTimeMillis = System.currentTimeMillis();
        if (videoTransferringTask.isFromTranscoding()) {
            writeTrack(videoTransferringTask.getMpFile(), videoTransferringTask.getAllTranscodedSamples(), "audio", "video");
        } else {
            writeTrack(videoTransferringTask.getMpFile(), "audio", "video");
        }
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        String str = TAG;
        String path = videoTransferringTask.path();
        SLog.i(str, "merging is done[" + currentTimeMillis2 + " ms]: " + path);
    }

    public final void setLastTrackTimestampUs$motionphoto_utils_v2_0_17_release(long j2) {
        this.lastTrackTimestampUs = j2;
    }

    public final void setOnEventListener(EventListener eventListener2) {
        j.e(eventListener2, "eventListener");
        this.eventListener = eventListener2;
    }

    public final void setOrientationHint(int i2) {
        this.muxer.setOrientationHint(i2);
    }

    public final void start() {
        this.muxer.start();
    }

    public final void stop() {
        this.muxer.stop();
    }

    public final void writeTrack(MPFile mPFile, Map<String, ? extends List<? extends i>> map, String... strArr) {
        Long l;
        Long l8;
        MPFile mPFile2 = mPFile;
        String[] strArr2 = strArr;
        j.e(mPFile2, "mpFile");
        Map<String, ? extends List<? extends i>> map2 = map;
        j.e(map2, "samples");
        j.e(strArr2, "targetMimes");
        SLog.d(TAG, "writeVideo from transcoded samples");
        Iterator it = map2.values().iterator();
        if (!it.hasNext()) {
            l = null;
        } else {
            l = Long.valueOf(((MediaCodec.BufferInfo) ((i) C1194l.T0((List) it.next())).e).presentationTimeUs);
            while (it.hasNext()) {
                Long valueOf = Long.valueOf(((MediaCodec.BufferInfo) ((i) C1194l.T0((List) it.next())).e).presentationTimeUs);
                if (l.compareTo(valueOf) > 0) {
                    l = valueOf;
                }
            }
        }
        long longValue = l != null ? l.longValue() : 0;
        for (Map.Entry next : map2.entrySet()) {
            String str = (String) next.getKey();
            List list = (List) next.getValue();
            int length = strArr2.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    SLog.w(TAG, N2.j.d("target mime types are [", C1192j.s0(strArr, (String) null, (String) null, (String) null, (b) null, 63), "], but current is ", str, ", skip this track"));
                    break;
                } else if (v.t0(str, strArr2[i2])) {
                    i iVar = this.trackInfos.get(str);
                    j.b(iVar);
                    int intValue = ((Number) iVar.d).intValue();
                    Iterator it2 = list.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        }
                        i iVar2 = (i) it2.next();
                        ByteBuffer byteBuffer = (ByteBuffer) iVar2.d;
                        MediaCodec.BufferInfo bufferInfo = (MediaCodec.BufferInfo) iVar2.e;
                        if (CommonsKt.containFlag(bufferInfo, 2)) {
                            SLog.i(TAG, "skip codec-config");
                        } else {
                            long j2 = bufferInfo.presentationTimeUs;
                            if (j2 <= longValue) {
                                Iterator it3 = it2;
                                bufferInfo.presentationTimeUs = j2 + this.lastTrackTimestampUs;
                                Iterator it4 = it3;
                                SLog.d(TAG, CommonsKt.trimToOneLine("[" + intValue + "][" + str + "] write sample:\n                            | info=" + CommonsKt.asString(bufferInfo) + ",\n                            | lastTrackTimestampUs=" + this.lastTrackTimestampUs + " us \n                        "));
                                this.muxer.writeSampleData(intValue, byteBuffer, bufferInfo);
                                byteBuffer.rewind();
                                EventListener eventListener2 = this.eventListener;
                                if (eventListener2 != null) {
                                    eventListener2.onEachFrameTransferred(mPFile2, str, bufferInfo);
                                }
                                String[] strArr3 = strArr;
                                it2 = it4;
                            } else {
                                String str2 = TAG;
                                long videoDurationUs = mPFile2.getMpInfo().getVideoDurationUs() + this.lastTrackTimestampUs;
                                Iterator it5 = map2.values().iterator();
                                if (!it5.hasNext()) {
                                    l8 = null;
                                } else {
                                    l8 = Long.valueOf(((MediaCodec.BufferInfo) ((i) C1194l.T0((List) it5.next())).e).presentationTimeUs);
                                    while (it5.hasNext()) {
                                        Long valueOf2 = Long.valueOf(((MediaCodec.BufferInfo) ((i) C1194l.T0((List) it5.next())).e).presentationTimeUs);
                                        if (l8.compareTo(valueOf2) < 0) {
                                            Map<String, ? extends List<? extends i>> map3 = map;
                                            l8 = valueOf2;
                                        } else {
                                            Map<String, ? extends List<? extends i>> map4 = map;
                                        }
                                    }
                                }
                                SLog.i(str2, CommonsKt.trimToOneLine("[" + str + "]reached EOS:\n                            | sampleTimestampUs=" + j2 + ", \n                            | min-track-durationUs=" + longValue + ",\n                            | last-ts from duration=" + videoDurationUs + " us, \n                            | max-track-duration=" + l8 + "\n                        "));
                            }
                        }
                    }
                } else {
                    i2++;
                    map2 = map;
                    strArr2 = strArr;
                }
            }
            map2 = map;
            strArr2 = strArr;
        }
        this.lastTrackTimestampUs += longValue;
        EventListener eventListener3 = this.eventListener;
        if (eventListener3 != null) {
            eventListener3.onTransferComplete(mPFile2);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00e8, code lost:
        r4 = r0.trackInfos.get(r3);
        kotlin.jvm.internal.j.b(r4);
        r4 = r4;
        r5 = ((java.lang.Number) r4.d).intValue();
        r4 = ((java.lang.Number) r4.e).intValue();
        com.samsung.android.sum.core.SLog.d(TAG, "mimeType=" + r3 + ", muxTrackIndex=" + r5 + ", maxInputSize=" + r4);
        r9.selectTrack(r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x012c, code lost:
        r6 = java.nio.ByteBuffer.allocateDirect(r4);
        r10 = r9.readSampleData(r6, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0137, code lost:
        if (r10 < 0) goto L_0x0141;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x013f, code lost:
        if (r9.getSampleTime() < r12) goto L_0x014a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0141, code lost:
        r19 = r8;
        r20 = r9;
        r9 = r14;
        r17 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x014a, code lost:
        r10 = new android.media.MediaCodec.BufferInfo();
        r10.size = r6.limit();
        r10.offset = 0;
        r10.flags = r9.getSampleFlags();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0162, code lost:
        r19 = r8;
        r20 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:?, code lost:
        r10.presentationTimeUs = r9.getSampleTime() + r0.lastTrackTimestampUs;
        r9 = r14;
        r17 = r15;
        r18 = r4;
        com.samsung.android.sum.core.SLog.d(TAG, com.samsung.android.motionphoto.utils.v2.CommonsKt.trimToOneLine("[" + r5 + "][" + r3 + "] write sample:\n                        | info=" + com.samsung.android.motionphoto.utils.v2.CommonsKt.asString(r10) + ", \n                        | lastMotionTimestampUs=" + r0.lastTrackTimestampUs + " us \n                    "));
        r0.muxer.writeSampleData(r5, r6, r10);
        r6.rewind();
        r2 = r0.eventListener;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x01b6, code lost:
        if (r2 == null) goto L_0x01c0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x01b8, code lost:
        r2.onEachFrameTransferred(r1, r3, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x01c0, code lost:
        r20.advance();
        r2 = r23;
        r14 = r9;
        r15 = r17;
        r4 = r18;
        r8 = r19;
        r9 = r20;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x01d4, code lost:
        com.samsung.android.sum.core.SLog.i(TAG, com.samsung.android.motionphoto.utils.v2.CommonsKt.trimToOneLine("[" + r3 + "]reached EOS:\n                            | sampleTimestampUs=" + r20.getSampleTime() + ", \n                            | minLastTimestampUs=" + r12 + ",\n                            | readBytes=" + r10 + "\n                        "));
        r10 = r17;
        r8 = r20;
        r8.unselectTrack(r10);
        r16 = 0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void writeTrack(com.samsung.android.motionphoto.utils.v2.MPFile r22, java.lang.String... r23) {
        /*
            r21 = this;
            r0 = r21
            r1 = r22
            r2 = r23
            java.lang.String r3 = "mpFile"
            kotlin.jvm.internal.j.e(r1, r3)
            java.lang.String r3 = "targetMimes"
            kotlin.jvm.internal.j.e(r2, r3)
            java.lang.String r3 = TAG
            long r4 = r1.getDistinctDurationMs()
            long r6 = r0.lastTrackTimestampUs
            java.lang.String r8 = r1.path()
            java.lang.String r9 = "writeVideo: dur="
            java.lang.String r10 = ", lastTrackTimestampUs="
            java.lang.StringBuilder r4 = N2.j.j(r4, r9, r10)
            r4.append(r6)
            java.lang.String r5 = ", path="
            r4.append(r5)
            r4.append(r8)
            java.lang.String r4 = r4.toString()
            com.samsung.android.sum.core.SLog.d((java.lang.String) r3, (java.lang.String) r4)
            java.io.FileInputStream r8 = r1.newInputFileStream()
            android.media.MediaExtractor r9 = new android.media.MediaExtractor     // Catch:{ all -> 0x01d0 }
            r9.<init>()     // Catch:{ all -> 0x01d0 }
            java.io.FileDescriptor r10 = r8.getFD()     // Catch:{ all -> 0x01d0 }
            com.samsung.android.motionphoto.utils.v2.MotionPhotoInfoImpl r3 = r1.getMpInfo()     // Catch:{ all -> 0x01d0 }
            long r11 = r3.getVideoPosition()     // Catch:{ all -> 0x01d0 }
            com.samsung.android.motionphoto.utils.v2.MotionPhotoInfoImpl r3 = r1.getMpInfo()     // Catch:{ all -> 0x01d0 }
            long r13 = r3.getVideoSize()     // Catch:{ all -> 0x01d0 }
            r9.setDataSource(r10, r11, r13)     // Catch:{ all -> 0x01d0 }
            int r3 = r9.getTrackCount()     // Catch:{ all -> 0x01d0 }
            r10 = 0
            Ge.e r3 = B1.a.Z(r10, r3)     // Catch:{ all -> 0x01d0 }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x01d0 }
            r4 = r3
            Ge.d r4 = (Ge.d) r4     // Catch:{ all -> 0x01d0 }
            boolean r4 = r4.f     // Catch:{ all -> 0x01d0 }
            java.lang.String r11 = "mime"
            if (r4 != 0) goto L_0x0070
            r3 = 0
            goto L_0x00ba
        L_0x0070:
            r4 = r3
            ne.y r4 = (ne.y) r4     // Catch:{ all -> 0x01d0 }
            int r4 = r4.nextInt()     // Catch:{ all -> 0x01d0 }
            android.media.MediaFormat r4 = r9.getTrackFormat(r4)     // Catch:{ all -> 0x01d0 }
            java.lang.String r4 = r4.getString(r11)     // Catch:{ all -> 0x01d0 }
            kotlin.jvm.internal.j.b(r4)     // Catch:{ all -> 0x01d0 }
            long r4 = r1.getDistinctLastTimestampUs(r4)     // Catch:{ all -> 0x01d0 }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x01d0 }
        L_0x008a:
            r5 = r3
            Ge.d r5 = (Ge.d) r5     // Catch:{ all -> 0x01d0 }
            boolean r5 = r5.f     // Catch:{ all -> 0x01d0 }
            if (r5 == 0) goto L_0x00b9
            r5 = r3
            ne.y r5 = (ne.y) r5     // Catch:{ all -> 0x00b3 }
            int r5 = r5.nextInt()     // Catch:{ all -> 0x00b3 }
            android.media.MediaFormat r5 = r9.getTrackFormat(r5)     // Catch:{ all -> 0x00b3 }
            java.lang.String r5 = r5.getString(r11)     // Catch:{ all -> 0x00b3 }
            kotlin.jvm.internal.j.b(r5)     // Catch:{ all -> 0x00b3 }
            long r5 = r1.getDistinctLastTimestampUs(r5)     // Catch:{ all -> 0x00b3 }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x00b3 }
            int r6 = r4.compareTo(r5)     // Catch:{ all -> 0x00b3 }
            if (r6 <= 0) goto L_0x008a
            r4 = r5
            goto L_0x008a
        L_0x00b3:
            r0 = move-exception
            r1 = r0
            r19 = r8
            goto L_0x0281
        L_0x00b9:
            r3 = r4
        L_0x00ba:
            if (r3 == 0) goto L_0x00c2
            long r3 = r3.longValue()     // Catch:{ all -> 0x00b3 }
        L_0x00c0:
            r12 = r3
            goto L_0x00c5
        L_0x00c2:
            r3 = 0
            goto L_0x00c0
        L_0x00c5:
            int r14 = r9.getTrackCount()     // Catch:{ all -> 0x01d0 }
            r15 = r10
        L_0x00ca:
            if (r15 >= r14) goto L_0x026b
            android.media.MediaFormat r3 = r9.getTrackFormat(r15)     // Catch:{ all -> 0x01d0 }
            java.lang.String r4 = "getTrackFormat(...)"
            kotlin.jvm.internal.j.d(r3, r4)     // Catch:{ all -> 0x01d0 }
            java.lang.String r3 = r3.getString(r11)     // Catch:{ all -> 0x01d0 }
            kotlin.jvm.internal.j.b(r3)     // Catch:{ all -> 0x01d0 }
            int r4 = r2.length     // Catch:{ all -> 0x01d0 }
            r5 = r10
        L_0x00de:
            if (r5 >= r4) goto L_0x0226
            r6 = r2[r5]     // Catch:{ all -> 0x01d0 }
            boolean r6 = Tf.v.t0(r3, r6)     // Catch:{ all -> 0x01d0 }
            if (r6 == 0) goto L_0x0217
            java.util.Map<java.lang.String, me.i> r4 = r0.trackInfos     // Catch:{ all -> 0x01d0 }
            java.lang.Object r4 = r4.get(r3)     // Catch:{ all -> 0x01d0 }
            kotlin.jvm.internal.j.b(r4)     // Catch:{ all -> 0x01d0 }
            me.i r4 = (me.i) r4     // Catch:{ all -> 0x01d0 }
            java.lang.Object r5 = r4.d     // Catch:{ all -> 0x01d0 }
            java.lang.Number r5 = (java.lang.Number) r5     // Catch:{ all -> 0x01d0 }
            int r5 = r5.intValue()     // Catch:{ all -> 0x01d0 }
            java.lang.Object r4 = r4.e     // Catch:{ all -> 0x01d0 }
            java.lang.Number r4 = (java.lang.Number) r4     // Catch:{ all -> 0x01d0 }
            int r4 = r4.intValue()     // Catch:{ all -> 0x01d0 }
            java.lang.String r6 = TAG     // Catch:{ all -> 0x01d0 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x01d0 }
            r7.<init>()     // Catch:{ all -> 0x01d0 }
            java.lang.String r10 = "mimeType="
            r7.append(r10)     // Catch:{ all -> 0x01d0 }
            r7.append(r3)     // Catch:{ all -> 0x01d0 }
            java.lang.String r10 = ", muxTrackIndex="
            r7.append(r10)     // Catch:{ all -> 0x01d0 }
            r7.append(r5)     // Catch:{ all -> 0x01d0 }
            java.lang.String r10 = ", maxInputSize="
            r7.append(r10)     // Catch:{ all -> 0x01d0 }
            r7.append(r4)     // Catch:{ all -> 0x01d0 }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x01d0 }
            com.samsung.android.sum.core.SLog.d((java.lang.String) r6, (java.lang.String) r7)     // Catch:{ all -> 0x01d0 }
            r9.selectTrack(r15)     // Catch:{ all -> 0x01d0 }
        L_0x012c:
            java.nio.ByteBuffer r6 = java.nio.ByteBuffer.allocateDirect(r4)     // Catch:{ all -> 0x01d0 }
            r7 = 0
            int r10 = r9.readSampleData(r6, r7)     // Catch:{ all -> 0x01d0 }
            java.lang.String r7 = "["
            if (r10 < 0) goto L_0x0141
            long r17 = r9.getSampleTime()     // Catch:{ all -> 0x01d0 }
            int r17 = (r17 > r12 ? 1 : (r17 == r12 ? 0 : -1))
            if (r17 < 0) goto L_0x014a
        L_0x0141:
            r19 = r8
            r20 = r9
            r9 = r14
            r17 = r15
            goto L_0x01d4
        L_0x014a:
            android.media.MediaCodec$BufferInfo r10 = new android.media.MediaCodec$BufferInfo     // Catch:{ all -> 0x01d0 }
            r10.<init>()     // Catch:{ all -> 0x01d0 }
            int r2 = r6.limit()     // Catch:{ all -> 0x01d0 }
            r10.size = r2     // Catch:{ all -> 0x01d0 }
            r2 = 0
            r10.offset = r2     // Catch:{ all -> 0x01d0 }
            int r2 = r9.getSampleFlags()     // Catch:{ all -> 0x01d0 }
            r10.flags = r2     // Catch:{ all -> 0x01d0 }
            long r17 = r9.getSampleTime()     // Catch:{ all -> 0x01d0 }
            r19 = r8
            r20 = r9
            long r8 = r0.lastTrackTimestampUs     // Catch:{ all -> 0x01bc }
            long r8 = r17 + r8
            r10.presentationTimeUs = r8     // Catch:{ all -> 0x01bc }
            java.lang.String r2 = TAG     // Catch:{ all -> 0x01bc }
            java.lang.String r8 = com.samsung.android.motionphoto.utils.v2.CommonsKt.asString(r10)     // Catch:{ all -> 0x01bc }
            r9 = r14
            r17 = r15
            long r14 = r0.lastTrackTimestampUs     // Catch:{ all -> 0x01bc }
            r18 = r4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x01bc }
            r4.<init>()     // Catch:{ all -> 0x01bc }
            r4.append(r7)     // Catch:{ all -> 0x01bc }
            r4.append(r5)     // Catch:{ all -> 0x01bc }
            java.lang.String r7 = "]["
            r4.append(r7)     // Catch:{ all -> 0x01bc }
            r4.append(r3)     // Catch:{ all -> 0x01bc }
            java.lang.String r7 = "] write sample:\n                        | info="
            r4.append(r7)     // Catch:{ all -> 0x01bc }
            r4.append(r8)     // Catch:{ all -> 0x01bc }
            java.lang.String r7 = ", \n                        | lastMotionTimestampUs="
            r4.append(r7)     // Catch:{ all -> 0x01bc }
            r4.append(r14)     // Catch:{ all -> 0x01bc }
            java.lang.String r7 = " us \n                    "
            r4.append(r7)     // Catch:{ all -> 0x01bc }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x01bc }
            java.lang.String r4 = com.samsung.android.motionphoto.utils.v2.CommonsKt.trimToOneLine(r4)     // Catch:{ all -> 0x01bc }
            com.samsung.android.sum.core.SLog.d((java.lang.String) r2, (java.lang.String) r4)     // Catch:{ all -> 0x01bc }
            android.media.MediaMuxer r2 = r0.muxer     // Catch:{ all -> 0x01bc }
            r2.writeSampleData(r5, r6, r10)     // Catch:{ all -> 0x01bc }
            r6.rewind()     // Catch:{ all -> 0x01bc }
            com.samsung.android.motionphoto.utils.v2.video.EventListener r2 = r0.eventListener     // Catch:{ all -> 0x01bc }
            if (r2 == 0) goto L_0x01c0
            r2.onEachFrameTransferred(r1, r3, r10)     // Catch:{ all -> 0x01bc }
            goto L_0x01c0
        L_0x01bc:
            r0 = move-exception
        L_0x01bd:
            r1 = r0
            goto L_0x0281
        L_0x01c0:
            r20.advance()     // Catch:{ all -> 0x01bc }
            r2 = r23
            r14 = r9
            r15 = r17
            r4 = r18
            r8 = r19
            r9 = r20
            goto L_0x012c
        L_0x01d0:
            r0 = move-exception
            r19 = r8
            goto L_0x01bd
        L_0x01d4:
            java.lang.String r2 = TAG     // Catch:{ all -> 0x01bc }
            long r4 = r20.getSampleTime()     // Catch:{ all -> 0x01bc }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x01bc }
            r6.<init>()     // Catch:{ all -> 0x01bc }
            r6.append(r7)     // Catch:{ all -> 0x01bc }
            r6.append(r3)     // Catch:{ all -> 0x01bc }
            java.lang.String r3 = "]reached EOS:\n                            | sampleTimestampUs="
            r6.append(r3)     // Catch:{ all -> 0x01bc }
            r6.append(r4)     // Catch:{ all -> 0x01bc }
            java.lang.String r3 = ", \n                            | minLastTimestampUs="
            r6.append(r3)     // Catch:{ all -> 0x01bc }
            r6.append(r12)     // Catch:{ all -> 0x01bc }
            java.lang.String r3 = ",\n                            | readBytes="
            r6.append(r3)     // Catch:{ all -> 0x01bc }
            r6.append(r10)     // Catch:{ all -> 0x01bc }
            java.lang.String r3 = "\n                        "
            r6.append(r3)     // Catch:{ all -> 0x01bc }
            java.lang.String r3 = r6.toString()     // Catch:{ all -> 0x01bc }
            java.lang.String r3 = com.samsung.android.motionphoto.utils.v2.CommonsKt.trimToOneLine(r3)     // Catch:{ all -> 0x01bc }
            com.samsung.android.sum.core.SLog.i((java.lang.String) r2, (java.lang.String) r3)     // Catch:{ all -> 0x01bc }
            r10 = r17
            r8 = r20
            r8.unselectTrack(r10)     // Catch:{ all -> 0x01bc }
            r16 = 0
            goto L_0x025f
        L_0x0217:
            r19 = r8
            r8 = r9
            r9 = r14
            r10 = r15
            int r5 = r5 + 1
            r2 = r23
            r10 = 0
            r9 = r8
            r8 = r19
            goto L_0x00de
        L_0x0226:
            r19 = r8
            r8 = r9
            r9 = r14
            r10 = r15
            java.lang.String r14 = TAG     // Catch:{ all -> 0x01bc }
            r6 = 0
            r7 = 63
            r2 = r3
            r3 = 0
            r4 = 0
            r5 = 0
            r15 = r2
            r16 = 0
            r2 = r23
            java.lang.String r3 = ne.C1192j.s0(r2, r3, r4, r5, r6, r7)     // Catch:{ all -> 0x01bc }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x01bc }
            r2.<init>()     // Catch:{ all -> 0x01bc }
            java.lang.String r4 = "target mime types are ["
            r2.append(r4)     // Catch:{ all -> 0x01bc }
            r2.append(r3)     // Catch:{ all -> 0x01bc }
            java.lang.String r3 = "], but current is "
            r2.append(r3)     // Catch:{ all -> 0x01bc }
            r2.append(r15)     // Catch:{ all -> 0x01bc }
            java.lang.String r3 = ", skip this track"
            r2.append(r3)     // Catch:{ all -> 0x01bc }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x01bc }
            com.samsung.android.sum.core.SLog.w((java.lang.String) r14, (java.lang.String) r2)     // Catch:{ all -> 0x01bc }
        L_0x025f:
            int r15 = r10 + 1
            r2 = r23
            r14 = r9
            r10 = r16
            r9 = r8
            r8 = r19
            goto L_0x00ca
        L_0x026b:
            r19 = r8
            r8 = r9
            r8.release()     // Catch:{ all -> 0x01bc }
            long r2 = r0.lastTrackTimestampUs     // Catch:{ all -> 0x01bc }
            long r2 = r2 + r12
            r0.lastTrackTimestampUs = r2     // Catch:{ all -> 0x01bc }
            r19.close()
            com.samsung.android.motionphoto.utils.v2.video.EventListener r0 = r0.eventListener
            if (r0 == 0) goto L_0x0280
            r0.onTransferComplete(r1)
        L_0x0280:
            return
        L_0x0281:
            throw r1     // Catch:{ all -> 0x0282 }
        L_0x0282:
            r0 = move-exception
            r2 = r19
            B1.a.g(r2, r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.motionphoto.utils.v2.video.VideoMerger.writeTrack(com.samsung.android.motionphoto.utils.v2.MPFile, java.lang.String[]):void");
    }
}
