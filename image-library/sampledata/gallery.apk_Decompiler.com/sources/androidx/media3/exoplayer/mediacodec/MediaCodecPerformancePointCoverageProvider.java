package androidx.media3.exoplayer.mediacodec;

import android.media.MediaCodecInfo;
import android.os.Build;
import androidx.media3.common.Format;
import androidx.media3.exoplayer.mediacodec.MediaCodecUtil;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import com.samsung.android.sdk.sgpl.pip.core.Encode;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class MediaCodecPerformancePointCoverageProvider {
    /* access modifiers changed from: private */
    public static Boolean shouldIgnorePerformancePoints;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Api29 {
        public static int areResolutionAndFrameRateCovered(MediaCodecInfo.VideoCapabilities videoCapabilities, int i2, int i7, double d) {
            List<MediaCodecInfo.VideoCapabilities.PerformancePoint> supportedPerformancePoints = videoCapabilities.getSupportedPerformancePoints();
            if (supportedPerformancePoints == null || supportedPerformancePoints.isEmpty()) {
                return 0;
            }
            int evaluatePerformancePointCoverage = evaluatePerformancePointCoverage(supportedPerformancePoints, new MediaCodecInfo.VideoCapabilities.PerformancePoint(i2, i7, (int) d));
            if (evaluatePerformancePointCoverage == 1 && MediaCodecPerformancePointCoverageProvider.shouldIgnorePerformancePoints == null) {
                Boolean unused = MediaCodecPerformancePointCoverageProvider.shouldIgnorePerformancePoints = Boolean.valueOf(shouldIgnorePerformancePoints());
                if (MediaCodecPerformancePointCoverageProvider.shouldIgnorePerformancePoints.booleanValue()) {
                    return 0;
                }
            }
            return evaluatePerformancePointCoverage;
        }

        private static int evaluateH264RequiredSupport(boolean z) {
            MediaCodecInfo.VideoCapabilities videoCapabilities;
            List<MediaCodecInfo.VideoCapabilities.PerformancePoint> supportedPerformancePoints;
            try {
                Format build = new Format.Builder().setSampleMimeType(Encode.CodecsMime.VIDEO_CODEC_H264).build();
                if (build.sampleMimeType != null) {
                    List<MediaCodecInfo> decoderInfosSoftMatch = MediaCodecUtil.getDecoderInfosSoftMatch(MediaCodecSelector.DEFAULT, build, z, false);
                    for (int i2 = 0; i2 < decoderInfosSoftMatch.size(); i2++) {
                        if (decoderInfosSoftMatch.get(i2).capabilities != null && (videoCapabilities = decoderInfosSoftMatch.get(i2).capabilities.getVideoCapabilities()) != null && (supportedPerformancePoints = videoCapabilities.getSupportedPerformancePoints()) != null && !supportedPerformancePoints.isEmpty()) {
                            return evaluatePerformancePointCoverage(supportedPerformancePoints, new MediaCodecInfo.VideoCapabilities.PerformancePoint(MediaDefs.Meta.XMP.XMP_MIX_RESERVED_SIZE, 720, 60));
                        }
                    }
                }
            } catch (MediaCodecUtil.DecoderQueryException unused) {
            }
            return 0;
        }

        private static int evaluatePerformancePointCoverage(List<MediaCodecInfo.VideoCapabilities.PerformancePoint> list, MediaCodecInfo.VideoCapabilities.PerformancePoint performancePoint) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                if (list.get(i2).covers(performancePoint)) {
                    return 2;
                }
            }
            return 1;
        }

        private static boolean shouldIgnorePerformancePoints() {
            if (Build.VERSION.SDK_INT >= 35) {
                return false;
            }
            int evaluateH264RequiredSupport = evaluateH264RequiredSupport(false);
            int evaluateH264RequiredSupport2 = evaluateH264RequiredSupport(true);
            if (evaluateH264RequiredSupport == 0) {
                return true;
            }
            if (evaluateH264RequiredSupport2 == 0) {
                if (evaluateH264RequiredSupport != 2) {
                    return true;
                }
                return false;
            } else if (evaluateH264RequiredSupport == 2 && evaluateH264RequiredSupport2 == 2) {
                return false;
            } else {
                return true;
            }
        }
    }

    public static int areResolutionAndFrameRateCovered(MediaCodecInfo.VideoCapabilities videoCapabilities, int i2, int i7, double d) {
        Boolean bool = shouldIgnorePerformancePoints;
        if (bool == null || !bool.booleanValue()) {
            return Api29.areResolutionAndFrameRateCovered(videoCapabilities, i2, i7, d);
        }
        return 0;
    }
}
