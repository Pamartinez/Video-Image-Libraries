package androidx.media3.transformer;

import F2.G;
import F2.U;
import F2.y0;
import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.container.NalUnitUtil;
import androidx.media3.extractor.metadata.mp4.SlowMotionData;
import androidx.media3.extractor.metadata.mp4.SmtaMetadataEntry;
import com.samsung.android.sdk.sgpl.pip.core.Encode;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class SefSlowMotionFlattener {
    private static final int NAL_START_CODE_LENGTH = NalUnitUtil.NAL_START_CODE.length;
    private final float captureFrameRate;
    private SegmentInfo currentSegmentInfo;
    private long frameTimeDeltaUs;
    private final int inputMaxLayer;
    private long lastSamplePresentationTimeUs = -9223372036854775807L;
    private final String mimeType;
    private SegmentInfo nextSegmentInfo;
    private final int normalSpeedMaxLayer;
    private final byte[] scratch = new byte[NAL_START_CODE_LENGTH];
    private final Iterator<SlowMotionData.Segment> segmentIterator;
    private final SlowMotionData slowMotionData;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class MetadataInfo {
        public float captureFrameRate = -3.4028235E38f;
        public int inputMaxLayer = -1;
        public int normalSpeedMaxLayer = -1;
        public SlowMotionData slowMotionData;
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class SegmentInfo {
        public final long endTimeUs;
        public final int maxLayer;
        public final int speedDivisor;
        public final long startTimeUs;

        public SegmentInfo(SlowMotionData.Segment segment, int i2, int i7) {
            this.startTimeUs = Util.msToUs(segment.startTimeMs);
            this.endTimeUs = Util.msToUs(segment.endTimeMs);
            int i8 = segment.speedDivisor;
            this.speedDivisor = i8;
            this.maxLayer = getSlowMotionMaxLayer(i8, i2, i7);
        }

        private static int getSlowMotionMaxLayer(int i2, int i7, int i8) {
            int i10 = i2;
            while (true) {
                if (i10 <= 0) {
                    break;
                }
                boolean z = true;
                if ((i10 & 1) == 1) {
                    if ((i10 >> 1) != 0) {
                        z = false;
                    }
                    Assertions.checkState(z, "Invalid speed divisor: " + i2);
                } else {
                    i8++;
                    i10 >>= 1;
                }
            }
            return Math.min(i8, i7);
        }
    }

    public SefSlowMotionFlattener(Format format) {
        List list;
        SegmentInfo segmentInfo;
        boolean z;
        MetadataInfo metadataInfo = getMetadataInfo(format.metadata);
        SlowMotionData slowMotionData2 = metadataInfo.slowMotionData;
        this.slowMotionData = slowMotionData2;
        String str = (String) Assertions.checkNotNull(format.sampleMimeType);
        this.mimeType = str;
        if (slowMotionData2 != null) {
            if (str.equals(Encode.CodecsMime.VIDEO_CODEC_H264) || str.equals("video/hevc")) {
                z = true;
            } else {
                z = false;
            }
            Assertions.checkArgument(z, "Unsupported MIME type for SEF slow motion video track: ".concat(str));
        }
        if (slowMotionData2 != null) {
            list = slowMotionData2.segments;
        } else {
            G g = U.e;
            list = y0.f278h;
        }
        Iterator<SlowMotionData.Segment> it = list.iterator();
        this.segmentIterator = it;
        this.captureFrameRate = metadataInfo.captureFrameRate;
        int i2 = metadataInfo.inputMaxLayer;
        this.inputMaxLayer = i2;
        int i7 = metadataInfo.normalSpeedMaxLayer;
        this.normalSpeedMaxLayer = i7;
        if (it.hasNext()) {
            segmentInfo = new SegmentInfo(it.next(), i2, i7);
        } else {
            segmentInfo = null;
        }
        this.nextSegmentInfo = segmentInfo;
    }

    private void enterNextSegment() {
        SegmentInfo segmentInfo;
        if (this.currentSegmentInfo != null) {
            leaveCurrentSegment();
        }
        this.currentSegmentInfo = this.nextSegmentInfo;
        if (this.segmentIterator.hasNext()) {
            segmentInfo = new SegmentInfo(this.segmentIterator.next(), this.inputMaxLayer, this.normalSpeedMaxLayer);
        } else {
            segmentInfo = null;
        }
        this.nextSegmentInfo = segmentInfo;
    }

    private static MetadataInfo getMetadataInfo(Metadata metadata) {
        boolean z;
        boolean z3;
        boolean z7;
        MetadataInfo metadataInfo = new MetadataInfo();
        if (metadata != null) {
            boolean z9 = false;
            for (int i2 = 0; i2 < metadata.length(); i2++) {
                Metadata.Entry entry = metadata.get(i2);
                if (entry instanceof SmtaMetadataEntry) {
                    SmtaMetadataEntry smtaMetadataEntry = (SmtaMetadataEntry) entry;
                    metadataInfo.captureFrameRate = smtaMetadataEntry.captureFrameRate;
                    metadataInfo.inputMaxLayer = smtaMetadataEntry.svcTemporalLayerCount - 1;
                } else if (entry instanceof SlowMotionData) {
                    metadataInfo.slowMotionData = (SlowMotionData) entry;
                }
            }
            if (metadataInfo.slowMotionData != null) {
                if (metadataInfo.inputMaxLayer != -1) {
                    z = true;
                } else {
                    z = false;
                }
                Assertions.checkState(z, "SVC temporal layer count not found.");
                if (metadataInfo.captureFrameRate != -3.4028235E38f) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                Assertions.checkState(z3, "Capture frame rate not found.");
                float f = metadataInfo.captureFrameRate;
                if (f % 1.0f == 0.0f && f % 30.0f == 0.0f) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                Assertions.checkState(z7, "Invalid capture frame rate: " + metadataInfo.captureFrameRate);
                int i7 = ((int) metadataInfo.captureFrameRate) / 30;
                for (int i8 = metadataInfo.inputMaxLayer; i8 >= 0; i8--) {
                    if ((i7 & 1) == 1) {
                        if ((i7 >> 1) == 0) {
                            z9 = true;
                        }
                        Assertions.checkState(z9, "Could not compute normal speed max SVC layer for capture frame rate  " + metadataInfo.captureFrameRate);
                        metadataInfo.normalSpeedMaxLayer = i8;
                        return metadataInfo;
                    }
                    i7 >>= 1;
                }
            }
        }
        return metadataInfo;
    }

    private void leaveCurrentSegment() {
        long j2 = this.frameTimeDeltaUs;
        SegmentInfo segmentInfo = this.currentSegmentInfo;
        this.frameTimeDeltaUs = ((segmentInfo.endTimeUs - segmentInfo.startTimeUs) * ((long) (segmentInfo.speedDivisor - 1))) + j2;
        this.currentSegmentInfo = null;
    }

    private boolean shouldKeepFrameForOutputValidity(int i2, long j2) {
        int i7;
        SegmentInfo segmentInfo = this.nextSegmentInfo;
        if (segmentInfo != null && i2 < (i7 = segmentInfo.maxLayer)) {
            long j3 = ((segmentInfo.startTimeUs - j2) * 30) / 1000000;
            float f = ((float) (-(1 << (this.inputMaxLayer - i7)))) + 0.45f;
            int i8 = 1;
            while (i8 < this.nextSegmentInfo.maxLayer && ((float) j3) < ((float) (1 << (this.inputMaxLayer - i8))) + f) {
                if (i2 <= i8) {
                    return true;
                }
                i8++;
            }
        }
        return false;
    }

    public boolean dropOrTransformSample(ByteBuffer byteBuffer, long j2) {
        int i2;
        boolean z;
        boolean z3;
        if (this.slowMotionData == null) {
            this.lastSamplePresentationTimeUs = j2;
            return false;
        }
        int position = byteBuffer.position();
        byteBuffer.position(NAL_START_CODE_LENGTH + position);
        byteBuffer.get(this.scratch, 0, 4);
        if (this.mimeType.equals(Encode.CodecsMime.VIDEO_CODEC_H264)) {
            byte[] bArr = this.scratch;
            byte b = bArr[0] & 31;
            if (((bArr[1] & 255) >> 7) == 1) {
                z = true;
            } else {
                z = false;
            }
            if (b != 14 || !z) {
                z3 = false;
            } else {
                z3 = true;
            }
            Assertions.checkState(z3, "Missing SVC extension prefix NAL unit.");
            i2 = (this.scratch[3] & 255) >> 5;
        } else if (this.mimeType.equals("video/hevc")) {
            i2 = (this.scratch[1] & 7) - 1;
        } else {
            throw new IllegalStateException();
        }
        boolean processCurrentFrame = processCurrentFrame(i2, j2);
        this.lastSamplePresentationTimeUs = getCurrentFrameOutputTimeUs(j2);
        if (!processCurrentFrame) {
            return true;
        }
        byteBuffer.position(position);
        return false;
    }

    public long getCurrentFrameOutputTimeUs(long j2) {
        long j3 = this.frameTimeDeltaUs + j2;
        SegmentInfo segmentInfo = this.currentSegmentInfo;
        if (segmentInfo != null) {
            j3 += (j2 - segmentInfo.startTimeUs) * ((long) (segmentInfo.speedDivisor - 1));
        }
        return (long) Math.round(((float) (j3 * 30)) / this.captureFrameRate);
    }

    public long getSamplePresentationTimeUs() {
        boolean z;
        if (this.lastSamplePresentationTimeUs != -9223372036854775807L) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkState(z);
        return this.lastSamplePresentationTimeUs;
    }

    /* JADX WARNING: Removed duplicated region for block: B:8:0x0016  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x001a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean processCurrentFrame(int r4, long r5) {
        /*
            r3 = this;
        L_0x0000:
            androidx.media3.transformer.SefSlowMotionFlattener$SegmentInfo r0 = r3.nextSegmentInfo
            if (r0 == 0) goto L_0x000e
            long r1 = r0.endTimeUs
            int r1 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r1 < 0) goto L_0x000e
            r3.enterNextSegment()
            goto L_0x0000
        L_0x000e:
            if (r0 == 0) goto L_0x001a
            long r0 = r0.startTimeUs
            int r0 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
            if (r0 < 0) goto L_0x001a
            r3.enterNextSegment()
            goto L_0x0027
        L_0x001a:
            androidx.media3.transformer.SefSlowMotionFlattener$SegmentInfo r0 = r3.currentSegmentInfo
            if (r0 == 0) goto L_0x0027
            long r0 = r0.endTimeUs
            int r0 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
            if (r0 < 0) goto L_0x0027
            r3.leaveCurrentSegment()
        L_0x0027:
            androidx.media3.transformer.SefSlowMotionFlattener$SegmentInfo r0 = r3.currentSegmentInfo
            if (r0 == 0) goto L_0x002e
            int r0 = r0.maxLayer
            goto L_0x0030
        L_0x002e:
            int r0 = r3.normalSpeedMaxLayer
        L_0x0030:
            if (r4 <= r0) goto L_0x003b
            boolean r3 = r3.shouldKeepFrameForOutputValidity(r4, r5)
            if (r3 == 0) goto L_0x0039
            goto L_0x003b
        L_0x0039:
            r3 = 0
            return r3
        L_0x003b:
            r3 = 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.transformer.SefSlowMotionFlattener.processCurrentFrame(int, long):boolean");
    }
}
