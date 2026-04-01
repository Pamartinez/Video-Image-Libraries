package androidx.media3.transformer;

import F2.U;
import android.util.SparseArray;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.container.NalUnitUtil;
import androidx.media3.effect.DebugTraceUtil;
import androidx.media3.muxer.BufferInfo;
import androidx.media3.muxer.Muxer;
import androidx.media3.muxer.MuxerException;
import com.samsung.android.sdk.sgpl.pip.core.Encode;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class MuxerWrapper {
    private static final long MAX_TRACK_WRITE_AHEAD_US = Util.msToUs(500);
    private volatile int additionalRotationDegrees;
    private final Format appendVideoFormat;
    private final boolean dropSamplesBeforeFirstVideoSample;
    private long firstVideoPresentationTimeUs;
    private boolean isEnded;
    private boolean isReady;
    private final Listener listener;
    private long maxEndedTrackTimeUs;
    private long minEndedTrackTimeUs;
    private long minTrackTimeUs;
    private boolean muxedPartialAudio;
    private boolean muxedPartialVideo;
    private Muxer muxer;
    private final Muxer.Factory muxerFactory;
    private int muxerMode;
    private final String outputPath;
    private int previousTrackType;
    private volatile int trackCount;
    private final SparseArray<TrackInfo> trackTypeToInfo;
    private final boolean writeNegativeTimestampsToEditList;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class AppendTrackFormatException extends Exception {
        public AppendTrackFormatException(String str) {
            super(str);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Listener {
        void onEnded(long j2, long j3);

        void onSampleWrittenOrDropped();

        void onTrackEnded(int i2, Format format, int i7, int i8);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class TrackInfo {
        public long bytesWritten;
        public final Format format;
        public int sampleCount;
        public long startTimeUs;
        public long timeUs;
        public final int trackId;

        public TrackInfo(Format format2, int i2) {
            this.format = format2;
            this.trackId = i2;
        }

        public int getAverageBitrate() {
            long j2 = this.timeUs;
            if (j2 <= 0) {
                return -2147483647;
            }
            long j3 = this.bytesWritten;
            if (j3 <= 0) {
                return -2147483647;
            }
            long j8 = this.startTimeUs;
            if (j2 == j8) {
                return -2147483647;
            }
            return (int) Util.scaleLargeTimestamp(j3, 8000000, j2 - j8);
        }
    }

    public MuxerWrapper(String str, Muxer.Factory factory, Listener listener2, int i2, boolean z, Format format, boolean z3) {
        boolean z7;
        this.outputPath = str;
        this.muxerFactory = factory;
        this.listener = listener2;
        boolean z9 = false;
        if (i2 == 0 || i2 == 1) {
            z7 = true;
        } else {
            z7 = false;
        }
        Assertions.checkArgument(z7);
        this.muxerMode = i2;
        this.dropSamplesBeforeFirstVideoSample = z;
        this.writeNegativeTimestampsToEditList = z3;
        if ((i2 == 0 && format == null) || (i2 == 1 && format != null)) {
            z9 = true;
        }
        Assertions.checkArgument(z9, "appendVideoFormat must be present if and only if muxerMode is MUXER_MODE_MUX_PARTIAL.");
        this.appendVideoFormat = format;
        this.trackTypeToInfo = new SparseArray<>();
        this.previousTrackType = -2;
        this.firstVideoPresentationTimeUs = -9223372036854775807L;
        this.minEndedTrackTimeUs = Long.MAX_VALUE;
    }

    private boolean canWriteSample(int i2, long j2) {
        if ((this.dropSamplesBeforeFirstVideoSample && i2 != 2 && Util.contains(this.trackTypeToInfo, 2) && this.firstVideoPresentationTimeUs == -9223372036854775807L) || !this.isReady) {
            return false;
        }
        if (this.trackTypeToInfo.size() == 1) {
            return true;
        }
        long j3 = MAX_TRACK_WRITE_AHEAD_US;
        if (j2 - this.trackTypeToInfo.get(i2).timeUs > j3 && MimeTypes.getTrackType(((TrackInfo) Assertions.checkNotNull(getTrackInfoWithMinTimeUs(this.trackTypeToInfo))).format.sampleMimeType) == i2) {
            return true;
        }
        if (i2 != this.previousTrackType) {
            this.minTrackTimeUs = ((TrackInfo) Assertions.checkNotNull(getTrackInfoWithMinTimeUs(this.trackTypeToInfo))).timeUs;
        }
        if (j2 - this.minTrackTimeUs <= j3) {
            return true;
        }
        return false;
    }

    private void ensureMuxerInitialized() {
        if (this.muxer == null) {
            this.muxer = this.muxerFactory.create(this.outputPath);
        }
    }

    private long getCurrentOutputSizeBytes() {
        long length = new File(this.outputPath).length();
        if (length > 0) {
            return length;
        }
        return -1;
    }

    public static List<byte[]> getMostCompatibleInitializationData(Format format, Format format2) {
        if (format.initializationDataEquals(format2)) {
            return format.initializationData;
        }
        if (!Objects.equals(format2.sampleMimeType, Encode.CodecsMime.VIDEO_CODEC_H264) || !Objects.equals(format.sampleMimeType, Encode.CodecsMime.VIDEO_CODEC_H264) || format2.initializationData.size() != 2 || format.initializationData.size() != 2 || !Arrays.equals(format2.initializationData.get(1), format.initializationData.get(1))) {
            return null;
        }
        int i2 = 0;
        byte[] bArr = format2.initializationData.get(0);
        byte[] bArr2 = format.initializationData.get(0);
        int length = NalUnitUtil.NAL_START_CODE.length + 3;
        if (length >= bArr.length || bArr.length != bArr2.length) {
            return null;
        }
        for (int i7 = 0; i7 < bArr.length; i7++) {
            if (i7 != length && bArr[i7] != bArr2[i7]) {
                return null;
            }
        }
        while (true) {
            byte[] bArr3 = NalUnitUtil.NAL_START_CODE;
            if (i2 < bArr3.length) {
                if (bArr[i2] != bArr3[i2]) {
                    return null;
                }
                i2++;
            } else if ((bArr[bArr3.length] & 31) != 7 || bArr[bArr3.length + 1] == 0) {
                return null;
            } else {
                if (bArr2[length] >= bArr[length]) {
                    return format.initializationData;
                }
                return format2.initializationData;
            }
        }
    }

    private static TrackInfo getTrackInfoWithMinTimeUs(SparseArray<TrackInfo> sparseArray) {
        if (sparseArray.size() == 0) {
            return null;
        }
        TrackInfo valueAt = sparseArray.valueAt(0);
        for (int i2 = 1; i2 < sparseArray.size(); i2++) {
            TrackInfo valueAt2 = sparseArray.valueAt(i2);
            if (valueAt2.timeUs < valueAt.timeUs) {
                valueAt = valueAt2;
            }
        }
        return valueAt;
    }

    public void addTrackFormat(Format format) {
        boolean z;
        boolean z3;
        boolean z7;
        String str = format.sampleMimeType;
        int trackType = MimeTypes.getTrackType(str);
        if (trackType == 1 || trackType == 2) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkArgument(z, "Unsupported track format: " + str);
        if (trackType == 2) {
            format = format.buildUpon().setRotationDegrees((format.rotationDegrees + this.additionalRotationDegrees) % 360).build();
            if (this.muxerMode == 1) {
                List<byte[]> mostCompatibleInitializationData = getMostCompatibleInitializationData(format, (Format) Assertions.checkNotNull(this.appendVideoFormat));
                if (mostCompatibleInitializationData != null) {
                    format = format.buildUpon().setInitializationData(mostCompatibleInitializationData).build();
                } else {
                    throw new AppendTrackFormatException("Switching to MUXER_MODE_APPEND will fail.");
                }
            }
        }
        if (this.muxerMode != 2) {
            int i2 = this.trackCount;
            if (i2 > 0) {
                z3 = true;
            } else {
                z3 = false;
            }
            Assertions.checkState(z3, "The track count should be set before the formats are added.");
            if (this.trackTypeToInfo.size() < i2) {
                z7 = true;
            } else {
                z7 = false;
            }
            Assertions.checkState(z7, "All track formats have already been added.");
            Assertions.checkState(!Util.contains(this.trackTypeToInfo, trackType), "There is already a track of type " + trackType);
            ensureMuxerInitialized();
            this.trackTypeToInfo.put(trackType, new TrackInfo(format, this.muxer.addTrack(format)));
            DebugTraceUtil.logEvent("Muxer", "InputFormat", -9223372036854775807L, "%s:%s", Util.getTrackTypeString(trackType), format);
            if (format.metadata != null) {
                for (int i7 = 0; i7 < format.metadata.length(); i7++) {
                    this.muxer.addMetadataEntry(format.metadata.get(i7));
                }
            }
            if (this.trackTypeToInfo.size() == i2) {
                this.isReady = true;
            }
        } else if (trackType == 2) {
            Assertions.checkState(Util.contains(this.trackTypeToInfo, 2));
            Format format2 = this.trackTypeToInfo.get(2).format;
            if (!Objects.equals(format2.sampleMimeType, format.sampleMimeType)) {
                throw new AppendTrackFormatException("Video format mismatch - sampleMimeType: " + format2.sampleMimeType + " != " + format.sampleMimeType);
            } else if (format2.width != format.width) {
                throw new AppendTrackFormatException("Video format mismatch - width: " + format2.width + " != " + format.width);
            } else if (format2.height != format.height) {
                throw new AppendTrackFormatException("Video format mismatch - height: " + format2.height + " != " + format.height);
            } else if (format2.rotationDegrees != format.rotationDegrees) {
                throw new AppendTrackFormatException("Video format mismatch - rotationDegrees: " + format2.rotationDegrees + " != " + format.rotationDegrees);
            } else if (!format.initializationDataEquals((Format) Assertions.checkNotNull(this.appendVideoFormat))) {
                throw new AppendTrackFormatException("The initialization data of the newly added track format doesn't match appendVideoFormat.");
            }
        } else if (trackType == 1) {
            Assertions.checkState(Util.contains(this.trackTypeToInfo, 1));
            Format format3 = this.trackTypeToInfo.get(1).format;
            if (!Objects.equals(format3.sampleMimeType, format.sampleMimeType)) {
                throw new AppendTrackFormatException("Audio format mismatch - sampleMimeType: " + format3.sampleMimeType + " != " + format.sampleMimeType);
            } else if (format3.channelCount != format.channelCount) {
                throw new AppendTrackFormatException("Audio format mismatch - channelCount: " + format3.channelCount + " != " + format.channelCount);
            } else if (format3.sampleRate != format.sampleRate) {
                throw new AppendTrackFormatException("Audio format mismatch - sampleRate: " + format3.sampleRate + " != " + format.sampleRate);
            } else if (!format3.initializationDataEquals(format)) {
                throw new AppendTrackFormatException("Audio format mismatch - initializationData.");
            }
        }
    }

    public void changeToAppendMode() {
        boolean z = true;
        if (this.muxerMode != 1) {
            z = false;
        }
        Assertions.checkState(z);
        this.muxerMode = 2;
    }

    public void endTrack(int i2) {
        if (this.isReady && Util.contains(this.trackTypeToInfo, i2)) {
            TrackInfo trackInfo = this.trackTypeToInfo.get(i2);
            this.minEndedTrackTimeUs = Math.max(0, Math.min(this.minEndedTrackTimeUs, trackInfo.startTimeUs));
            this.maxEndedTrackTimeUs = Math.max(this.maxEndedTrackTimeUs, trackInfo.timeUs);
            this.listener.onTrackEnded(i2, trackInfo.format, trackInfo.getAverageBitrate(), trackInfo.sampleCount);
            DebugTraceUtil.logEvent("Muxer", "InputEnded", trackInfo.timeUs, "%s", Util.getTrackTypeString(i2));
            if (this.muxerMode != 1) {
                this.trackTypeToInfo.delete(i2);
                if (this.trackTypeToInfo.size() == 0) {
                    this.isEnded = true;
                    DebugTraceUtil.logEvent("Muxer", "OutputEnded", this.maxEndedTrackTimeUs);
                }
            } else if (i2 == 2) {
                this.muxedPartialVideo = true;
            } else if (i2 == 1) {
                this.muxedPartialAudio = true;
            }
            long usToMs = Util.usToMs(this.maxEndedTrackTimeUs - this.minEndedTrackTimeUs);
            if (this.muxerMode == 1 && this.muxedPartialVideo && (this.muxedPartialAudio || this.trackCount == 1)) {
                this.listener.onEnded(usToMs, getCurrentOutputSizeBytes());
            } else if (this.isEnded) {
                this.listener.onEnded(usToMs, getCurrentOutputSizeBytes());
            }
        }
    }

    public void finishWritingAndMaybeRelease(int i2) {
        if (i2 != 0 || this.muxerMode != 1) {
            this.isReady = false;
            Muxer muxer2 = this.muxer;
            if (muxer2 != null) {
                try {
                    muxer2.close();
                } catch (MuxerException e) {
                    if (i2 != 1 || !((String) Assertions.checkNotNull(e.getMessage())).equals("Failed to stop the MediaMuxer")) {
                        throw e;
                    }
                }
            }
        }
    }

    public U getSupportedSampleMimeTypes(int i2) {
        return this.muxerFactory.getSupportedSampleMimeTypes(i2);
    }

    public boolean isEnded() {
        if (!this.isEnded) {
            if (this.muxerMode != 1 || !this.muxedPartialVideo) {
                return false;
            }
            if (this.muxedPartialAudio || this.trackCount == 1) {
                return true;
            }
            return false;
        }
        return true;
    }

    public void setAdditionalRotationDegrees(int i2) {
        boolean z;
        if (this.trackTypeToInfo.size() == 0 || this.additionalRotationDegrees == i2) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkState(z, "The additional rotation cannot be changed after adding track formats.");
        this.additionalRotationDegrees = i2;
    }

    public void setTrackCount(int i2) {
        boolean z;
        if (this.muxerMode != 2) {
            if (this.trackTypeToInfo.size() == 0) {
                z = true;
            } else {
                z = false;
            }
            Assertions.checkState(z, "The track count cannot be changed after adding track formats.");
            this.trackCount = i2;
        }
    }

    public boolean supportsSampleMimeType(String str) {
        return getSupportedSampleMimeTypes(MimeTypes.getTrackType(str)).contains(str);
    }

    public boolean writeSample(int i2, ByteBuffer byteBuffer, boolean z, long j2) {
        long j3;
        long j8;
        Assertions.checkArgument(Util.contains(this.trackTypeToInfo, i2));
        TrackInfo trackInfo = this.trackTypeToInfo.get(i2);
        boolean canWriteSample = canWriteSample(i2, j2);
        long j10 = j2;
        DebugTraceUtil.logEvent("Muxer", "CanWriteSample", j10, "%s:%s", Util.getTrackTypeString(i2), Boolean.valueOf(canWriteSample));
        if (i2 == 2) {
            if (this.firstVideoPresentationTimeUs == -9223372036854775807L) {
                this.firstVideoPresentationTimeUs = j10;
            }
        } else if (i2 == 1 && this.dropSamplesBeforeFirstVideoSample && Util.contains(this.trackTypeToInfo, 2)) {
            long j11 = this.firstVideoPresentationTimeUs;
            if (j11 != -9223372036854775807L && j10 < j11) {
                this.listener.onSampleWrittenOrDropped();
                return true;
            }
        }
        boolean z3 = false;
        if (!canWriteSample) {
            return false;
        }
        if (trackInfo.sampleCount == 0) {
            if (i2 != 2 || !Util.contains(this.trackTypeToInfo, 1) || this.dropSamplesBeforeFirstVideoSample || (this.writeNegativeTimestampsToEditList && j10 <= 0)) {
                j8 = j10;
            } else {
                if (this.firstVideoPresentationTimeUs != -9223372036854775807L) {
                    z3 = true;
                }
                Assertions.checkState(z3);
                Log.w("MuxerWrapper", "Applying workarounds for edit list: shifting only the first video timestamp to zero.");
                j8 = 0;
            }
            trackInfo.startTimeUs = j8;
            j3 = j8;
        } else {
            j3 = j10;
        }
        trackInfo.sampleCount++;
        trackInfo.bytesWritten += (long) byteBuffer.remaining();
        trackInfo.timeUs = Math.max(trackInfo.timeUs, j3);
        this.listener.onSampleWrittenOrDropped();
        Assertions.checkStateNotNull(this.muxer);
        this.muxer.writeSampleData(trackInfo.trackId, byteBuffer, new BufferInfo(j3, byteBuffer.remaining(), z ? 1 : 0));
        DebugTraceUtil.logEvent("Muxer", "AcceptedInput", j3, "%s", Util.getTrackTypeString(i2));
        this.previousTrackType = i2;
        return true;
    }
}
