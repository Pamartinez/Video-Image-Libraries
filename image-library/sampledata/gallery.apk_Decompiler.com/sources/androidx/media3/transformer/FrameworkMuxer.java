package androidx.media3.transformer;

import F2.C0040v;
import F2.G;
import F2.N;
import F2.U;
import F2.y0;
import N2.j;
import android.media.MediaCodec;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.os.Build;
import android.util.Pair;
import android.util.SparseArray;
import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.CodecSpecificDataUtil;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.MediaFormatUtil;
import androidx.media3.common.util.Util;
import androidx.media3.container.Mp4LocationData;
import androidx.media3.muxer.BufferInfo;
import androidx.media3.muxer.Muxer;
import androidx.media3.muxer.MuxerException;
import com.samsung.android.sdk.sgpl.pip.core.Encode;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class FrameworkMuxer implements Muxer {
    public static final String MUXER_NAME = ("android.media:" + Build.VERSION.SDK_INT);
    /* access modifiers changed from: private */
    public static final U SUPPORTED_AUDIO_SAMPLE_MIME_TYPES;
    /* access modifiers changed from: private */
    public static final U SUPPORTED_VIDEO_SAMPLE_MIME_TYPES = getSupportedVideoSampleMimeTypes();
    private boolean isReleased;
    private boolean isStarted;
    private final MediaMuxer mediaMuxer;
    private final SparseArray<Long> trackIdToLastPresentationTimeUs;
    private final SparseArray<Long> trackIdToPresentationTimeOffsetUs;
    private final long videoDurationUs;
    private int videoTrackId;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Factory implements Muxer.Factory {
        private long videoDurationUs = -9223372036854775807L;

        public U getSupportedSampleMimeTypes(int i2) {
            if (i2 == 2) {
                return FrameworkMuxer.SUPPORTED_VIDEO_SAMPLE_MIME_TYPES;
            }
            if (i2 == 1) {
                return FrameworkMuxer.SUPPORTED_AUDIO_SAMPLE_MIME_TYPES;
            }
            G g = U.e;
            return y0.f278h;
        }

        public FrameworkMuxer create(String str) {
            try {
                return new FrameworkMuxer(new MediaMuxer(str, 0), this.videoDurationUs);
            } catch (IOException e) {
                throw new MuxerException("Error creating muxer", e);
            }
        }
    }

    static {
        Object[] objArr = {Encode.CodecsMime.AUDIO_CODEC_AAC, Encode.CodecsMime.AUDIO_CODEC_AMR, "audio/amr-wb"};
        C0040v.a(3, objArr);
        SUPPORTED_AUDIO_SAMPLE_MIME_TYPES = U.w(3, objArr);
    }

    private static int getDvLevel(Format format) {
        boolean z;
        if (format.codecs != null) {
            return ((Integer) ((Pair) Assertions.checkNotNull(CodecSpecificDataUtil.getCodecProfileAndLevel(format))).second).intValue();
        }
        int max = Integer.max(format.width, format.height);
        if (max <= 7680) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkState(z);
        float f = ((float) (format.width * format.height)) * format.frameRate;
        if (max <= 1280) {
            if (f <= 2.21184E7f) {
                return 1;
            }
            return 2;
        } else if (max <= 1920 && f <= 4.97664E7f) {
            return 4;
        } else {
            if (max <= 2560 && f <= 6.2208E7f) {
                return 8;
            }
            if (max <= 3840) {
                if (f <= 1.24416E8f) {
                    return 16;
                }
                if (f <= 1.990656E8f) {
                    return 32;
                }
                if (f <= 2.48832E8f) {
                    return 64;
                }
                if (f <= 3.981312E8f) {
                    return 128;
                }
                if (f <= 4.97664E8f) {
                    return 256;
                }
                return 512;
            } else if (max > 7680) {
                return -1;
            } else {
                if (f <= 9.95328E8f) {
                    return 1024;
                }
                return 2048;
            }
        }
    }

    private static int getDvProfile() {
        return 256;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [F2.N, F2.Q] */
    private static U getSupportedVideoSampleMimeTypes() {
        ? n = new N(4);
        String[] strArr = {Encode.CodecsMime.VIDEO_CODEC_H264, "video/3gpp", Encode.ContentType.VIDEO_MP4V_ES};
        C0040v.a(3, strArr);
        n.d(3);
        System.arraycopy(strArr, 0, n.f247a, n.b, 3);
        n.b += 3;
        int i2 = Build.VERSION.SDK_INT;
        n.a("video/hevc");
        if (i2 >= 33) {
            n.a("video/dolby-vision");
        }
        if (i2 >= 34) {
            n.a("video/av01");
        }
        if (i2 >= 36) {
            n.a("video/apv");
        }
        return n.f();
    }

    private void startMuxer() {
        try {
            this.mediaMuxer.start();
            this.isStarted = true;
        } catch (RuntimeException e) {
            throw new MuxerException("Failed to start the muxer", e);
        }
    }

    private static void stopMuxer(MediaMuxer mediaMuxer2) {
        mediaMuxer2.stop();
    }

    public void addMetadataEntry(Metadata.Entry entry) {
        if (entry instanceof Mp4LocationData) {
            Mp4LocationData mp4LocationData = (Mp4LocationData) entry;
            this.mediaMuxer.setLocation(mp4LocationData.latitude, mp4LocationData.longitude);
        }
    }

    public int addTrack(Format format) {
        MediaFormat mediaFormat;
        String str = (String) Assertions.checkNotNull(format.sampleMimeType);
        boolean isVideo = MimeTypes.isVideo(str);
        if (isVideo) {
            mediaFormat = MediaFormat.createVideoFormat(str, format.width, format.height);
            MediaFormatUtil.maybeSetColorInfo(mediaFormat, format.colorInfo);
            if (str.equals("video/dolby-vision") && Build.VERSION.SDK_INT >= 33) {
                mediaFormat.setInteger("profile", getDvProfile());
                mediaFormat.setInteger("level", getDvLevel(format));
            }
            try {
                this.mediaMuxer.setOrientationHint(format.rotationDegrees);
            } catch (RuntimeException e) {
                throw new MuxerException("Failed to set orientation hint with rotationDegrees=" + format.rotationDegrees, e);
            }
        } else {
            mediaFormat = MediaFormat.createAudioFormat(str, format.sampleRate, format.channelCount);
            MediaFormatUtil.maybeSetString(mediaFormat, "language", format.language);
        }
        MediaFormatUtil.setCsdBuffers(mediaFormat, format.initializationData);
        try {
            int addTrack = this.mediaMuxer.addTrack(mediaFormat);
            if (isVideo) {
                this.videoTrackId = addTrack;
            }
            return addTrack;
        } catch (RuntimeException e7) {
            throw new MuxerException("Failed to add track with format=" + format, e7);
        }
    }

    public void close() {
        if (!this.isReleased) {
            if (!this.isStarted) {
                startMuxer();
            }
            long j2 = this.videoDurationUs;
            if (!(j2 == -9223372036854775807L || this.videoTrackId == -1)) {
                writeSampleData(this.videoTrackId, ByteBuffer.allocateDirect(0), new BufferInfo(j2, 0, 4));
            }
            this.isStarted = false;
            try {
                stopMuxer(this.mediaMuxer);
                this.mediaMuxer.release();
                this.isReleased = true;
            } catch (RuntimeException e) {
                throw new MuxerException("Failed to stop the MediaMuxer", e);
            } catch (Throwable th) {
                this.mediaMuxer.release();
                this.isReleased = true;
                throw th;
            }
        }
    }

    public void writeSampleData(int i2, ByteBuffer byteBuffer, BufferInfo bufferInfo) {
        long j2;
        int i7 = i2;
        BufferInfo bufferInfo2 = bufferInfo;
        long j3 = bufferInfo2.presentationTimeUs;
        long j8 = this.videoDurationUs;
        if (j8 == -9223372036854775807L || i7 != this.videoTrackId || j3 <= j8) {
            if (!this.isStarted) {
                startMuxer();
            }
            long longValue = this.trackIdToPresentationTimeOffsetUs.get(i7, 0L).longValue();
            long j10 = j3 + longValue;
            if (Util.contains(this.trackIdToLastPresentationTimeUs, i7)) {
                j2 = this.trackIdToLastPresentationTimeUs.get(i7).longValue();
            } else {
                j2 = 0;
            }
            StringBuilder j11 = j.j(j10, "Samples not in presentation order (", " < ");
            j11.append(j2);
            j11.append(") unsupported on this API version");
            boolean z = true;
            Assertions.checkState(true, j11.toString());
            this.trackIdToLastPresentationTimeUs.put(i7, Long.valueOf(j10));
            if (longValue != 0 && j10 < 0) {
                z = false;
            }
            Locale locale = Locale.US;
            StringBuilder j12 = j.j(j10 - longValue, "Sample presentation time (", ") < first sample presentation time (");
            j12.append(-longValue);
            j12.append("). Ensure the first sample has the smallest timestamp when using the negative PTS workaround.");
            Assertions.checkState(z, j12.toString());
            MediaCodec.BufferInfo bufferInfo3 = new MediaCodec.BufferInfo();
            bufferInfo3.set(byteBuffer.position(), bufferInfo2.size, j10, TransformerUtil.getMediaCodecFlags(bufferInfo2.flags));
            try {
                this.mediaMuxer.writeSampleData(i7, byteBuffer, bufferInfo3);
            } catch (RuntimeException e) {
                StringBuilder j13 = j.j(j10, "Failed to write sample for presentationTimeUs=", ", size=");
                j13.append(bufferInfo2.size);
                throw new MuxerException(j13.toString(), e);
            }
        } else {
            Locale locale2 = Locale.US;
            long j14 = this.videoDurationUs;
            StringBuilder j15 = j.j(j3, "Skipped sample with presentation time (", ") > video duration (");
            j15.append(j14);
            j15.append(")");
            Log.w("FrameworkMuxer", j15.toString());
        }
    }

    private FrameworkMuxer(MediaMuxer mediaMuxer2, long j2) {
        this.mediaMuxer = mediaMuxer2;
        this.videoDurationUs = j2;
        this.trackIdToLastPresentationTimeUs = new SparseArray<>();
        this.trackIdToPresentationTimeOffsetUs = new SparseArray<>();
        this.videoTrackId = -1;
    }
}
