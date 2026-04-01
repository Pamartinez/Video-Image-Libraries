package androidx.media3.transformer;

import F2.M;
import F2.X;
import F2.x0;
import N2.j;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.util.Clock;
import c0.C0086a;
import com.samsung.android.livetranslation.task.LiveTranslationTask;
import com.samsung.android.sum.solution.filter.UniImgp;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ExportException extends Exception {
    static final M NAME_TO_ERROR_CODE;
    public final CodecInfo codecInfo;
    public final int errorCode;
    public final long timestampMs;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class CodecInfo {
        public final String configurationFormat;
        public final boolean isDecoder;
        public final boolean isVideo;
        public final String name;

        public CodecInfo(String str, boolean z, boolean z3, String str2) {
            this.configurationFormat = str;
            this.isVideo = z;
            this.isDecoder = z3;
            this.name = str2;
        }

        public String toString() {
            String str;
            String str2;
            if (this.isVideo) {
                str = "Video";
            } else {
                str = "Audio";
            }
            if (this.isDecoder) {
                str2 = "Decoder";
            } else {
                str2 = "Encoder";
            }
            StringBuilder k = j.k("CodecInfo{type=", str.concat(str2), ", configurationFormat=");
            k.append(this.configurationFormat);
            k.append(", name=");
            return C0086a.m(k, this.name, '}');
        }
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [F2.X, F2.L] */
    static {
        ? x9 = new X(4);
        x9.d("ERROR_CODE_FAILED_RUNTIME_CHECK", 1001);
        x9.d("ERROR_CODE_IO_UNSPECIFIED", 2000);
        x9.d("ERROR_CODE_IO_NETWORK_CONNECTION_FAILED", 2001);
        x9.d("ERROR_CODE_IO_NETWORK_CONNECTION_TIMEOUT", 2002);
        x9.d("ERROR_CODE_IO_INVALID_HTTP_CONTENT_TYPE", 2003);
        x9.d("ERROR_CODE_IO_BAD_HTTP_STATUS", 2004);
        x9.d("ERROR_CODE_IO_FILE_NOT_FOUND", Integer.valueOf(UniImgp.OPTION_PREFERRED_COLOR_FORMAT));
        x9.d("ERROR_CODE_IO_NO_PERMISSION", 2006);
        x9.d("ERROR_CODE_IO_CLEARTEXT_NOT_PERMITTED", 2007);
        x9.d("ERROR_CODE_IO_READ_POSITION_OUT_OF_RANGE", 2008);
        x9.d("ERROR_CODE_DECODER_INIT_FAILED", 3001);
        x9.d("ERROR_CODE_DECODING_FAILED", 3002);
        x9.d("ERROR_CODE_DECODING_FORMAT_UNSUPPORTED", Integer.valueOf(LiveTranslationTask.ERRORTYPE.ERR_INVALID_PARSING_DATA));
        x9.d("ERROR_CODE_ENCODER_INIT_FAILED", 4001);
        x9.d("ERROR_CODE_ENCODING_FAILED", 4002);
        x9.d("ERROR_CODE_ENCODING_FORMAT_UNSUPPORTED", 4003);
        x9.d("ERROR_CODE_VIDEO_FRAME_PROCESSING_FAILED", 5001);
        x9.d("ERROR_CODE_AUDIO_PROCESSING_FAILED", 6001);
        x9.d("ERROR_CODE_MUXING_FAILED", 7001);
        x9.d("ERROR_CODE_MUXING_TIMEOUT", 7002);
        x9.d("ERROR_CODE_MUXING_APPEND", 7003);
        NAME_TO_ERROR_CODE = x9.a();
    }

    private ExportException(String str, Throwable th, int i2) {
        this(str, th, i2, (CodecInfo) null);
    }

    public static ExportException createForAssetLoader(Throwable th, int i2) {
        return new ExportException("Asset loader error", th, i2);
    }

    public static ExportException createForAudioProcessing(AudioProcessor.UnhandledAudioFormatException unhandledAudioFormatException, String str) {
        StringBuilder k = j.k("Audio error: ", str, ", audioFormat=");
        k.append(unhandledAudioFormatException.inputAudioFormat);
        return new ExportException(k.toString(), unhandledAudioFormatException, 6001);
    }

    public static ExportException createForCodec(Throwable th, int i2, CodecInfo codecInfo2) {
        return new ExportException("Codec exception: " + codecInfo2, th, i2, codecInfo2);
    }

    public static ExportException createForMuxer(Throwable th, int i2) {
        return new ExportException("Muxer error", th, i2);
    }

    public static ExportException createForUnexpected(Exception exc) {
        if (exc instanceof RuntimeException) {
            return new ExportException("Unexpected runtime error", exc, 1001);
        }
        return new ExportException("Unexpected error", exc, 1000);
    }

    public static ExportException createForVideoFrameProcessingException(VideoFrameProcessingException videoFrameProcessingException) {
        return new ExportException("Video frame processing error", videoFrameProcessingException, 5001);
    }

    public static String getErrorCodeName(int i2) {
        Object obj = ((x0) NAME_TO_ERROR_CODE).l.get(Integer.valueOf(i2));
        if (obj == null) {
            obj = "invalid error code";
        }
        return (String) obj;
    }

    private ExportException(String str, Throwable th, int i2, CodecInfo codecInfo2) {
        super(str, th);
        this.errorCode = i2;
        this.timestampMs = Clock.DEFAULT.elapsedRealtime();
        this.codecInfo = codecInfo2;
    }

    public String getErrorCodeName() {
        return getErrorCodeName(this.errorCode);
    }
}
