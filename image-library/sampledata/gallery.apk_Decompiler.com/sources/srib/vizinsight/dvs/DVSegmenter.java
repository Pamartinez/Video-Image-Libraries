package srib.vizinsight.dvs;

import android.util.Log;
import com.samsung.android.feature.SemFloatingFeature;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.concurrent.CountDownLatch;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DVSegmenter {
    private static final String TAG = "DVSegmenter";
    public static CountDownLatch cancellationFinished = null;
    public static boolean needTOchangeModel = false;
    private static DVS segmenter = null;
    protected static final String versionString = "VideoClipperInterface_v2.0_Beta13.1";

    public static void abortSegmenter() {
        DVS dvs = segmenter;
        if (dvs != null) {
            if (dvs.DVSTaskStatus()) {
                Log.d(TAG, "Cancelling task.");
                segmenter.DVSAbort();
                try {
                    cancellationFinished.await();
                } catch (InterruptedException e) {
                    Log.d(TAG, e.getMessage());
                }
                Log.d(TAG, "Done cancelling task.");
            }
            releaseSegmenter();
        }
    }

    private static String getConfigMode() {
        SemFloatingFeature instance = SemFloatingFeature.getInstance();
        if (instance == null) {
            Log.e(TAG, "SemFloatingFeature null");
            return "";
        }
        String string = instance.getString("SEC_FLOATING_FEATURE_VIDEO_CONFIG_VIDEO_CLIPPING_MODE");
        Log.d(TAG, "Model type from floating feature SEC_FLOATING_FEATURE_VIDEO_CONFIG_VIDEO_CLIPPING_MODE: " + string);
        if (string.contains(GlobalPostProcInternalPPInterface.SPLIT_REGEX)) {
            return string.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX)[1];
        }
        return "";
    }

    public static DVS getSegmenter(DVSConfig dVSConfig) {
        DVSConfig dVSConfig2 = dVSConfig;
        if (segmenter == null) {
            Log.d(TAG, "Need to change the Model");
            segmenter = new DVS();
            Log.d(TAG, TAG);
            Log.d(TAG, "odModelPath : " + dVSConfig2.odModelPath);
            Log.d(TAG, "segmenterModelPath : " + dVSConfig2.segmenterModelPath);
            Log.d(TAG, "refinerModelPath : " + dVSConfig2.refinerModelPath);
            Log.d(TAG, "detectThreshold : " + dVSConfig2.detectThreshold + " qualityThreshold : " + dVSConfig2.qualityThreshold + " segmentThreshold : " + dVSConfig2.segmentThreshold + "maxPass : " + dVSConfig2.maxPass);
            Log.d("DVS", "Initialize Video Clipper Interface version : VideoClipperInterface_v2.0_Beta13.1");
            String configMode = getConfigMode();
            StringBuilder sb2 = new StringBuilder("configMode : ");
            sb2.append(configMode);
            Log.d("DVS", sb2.toString());
            if (configMode.equals("unifiedclipper")) {
                segmenter.DVSInit(dVSConfig2.getSegmenterModelPath(), dVSConfig2.getOdModelPath(), dVSConfig2.getRefinerModelPath(), dVSConfig2.getDetectThreshold(), dVSConfig2.getQualityThreshold(), dVSConfig2.getSegmentThreshold(), dVSConfig2.getMaxPass());
            } else {
                segmenter.DVSInit(dVSConfig2.getSegmenterModelPath(), dVSConfig2.getOdModelPath(), dVSConfig2.getDetectThreshold(), dVSConfig2.getQualityThreshold(), dVSConfig2.getSegmentThreshold(), dVSConfig2.getMaxPass());
            }
            if (dVSConfig2.isPamir.booleanValue()) {
                segmenter.isPamir = Boolean.TRUE;
            }
            cancellationFinished = new CountDownLatch(1);
        }
        return segmenter;
    }

    public static boolean getSegmenterState() {
        boolean z;
        if (segmenter != null) {
            z = true;
        } else {
            z = false;
        }
        Log.d(TAG, "getSegmenterState - " + z);
        return z;
    }

    public static void releaseSegmenter() {
        DVS dvs = segmenter;
        if (dvs != null) {
            dvs.release();
            segmenter = null;
        }
    }

    public static void releaseUnifiedSegmenter() {
        if (segmenter != null) {
            segmenter = null;
        }
    }
}
