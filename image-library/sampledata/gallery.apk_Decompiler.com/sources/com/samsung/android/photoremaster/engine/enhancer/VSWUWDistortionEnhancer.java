package com.samsung.android.photoremaster.engine.enhancer;

import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import com.samsung.android.photoremaster.engine.estimator.VSWUWDistortionEstimator;
import com.samsung.android.photoremaster.engine.nativeInterface.VSWEngineNativeInterface;
import com.samsung.android.photoremaster.sdk.enhancer.EnhanceResult;
import com.samsung.android.photoremaster.sdk.enhancer.IEnhancer;
import com.samsung.android.photoremaster.sdk.ip.BgrBuffer;
import com.samsung.android.photoremaster.sdk.ip.IRequestCancelledChecker;
import com.samsung.android.photoremaster.sdk.ip.PrSize;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VSWUWDistortionEnhancer implements IEnhancer {
    private static final String TAG = "Remaster-VSWUWDistortionEnhancer";

    public static boolean enhance(byte[] bArr, byte[] bArr2, int i2, int i7, String str, boolean[] zArr) {
        boolean z;
        String str2 = TAG;
        Log.d(str2, "enhance - E");
        VSWEngineNativeInterface.printLibraryVersion();
        byte[] makeByteArray = VSWEngineNativeInterface.makeByteArray(153600);
        if (VSWUWDistortionEstimator.estimate(bArr, zArr, makeByteArray, i2, i7)) {
            z = VSWEngineNativeInterface.runDistortionEnhancer(bArr, bArr2, str, zArr, makeByteArray, i2, i7);
            Log.d(str2, "result : " + z);
        } else {
            Log.d(str2, "result : skip-enhance, no distortion");
            z = false;
        }
        Log.d(str2, "enhance - X");
        VSWEngineNativeInterface.releaseByteArray(makeByteArray);
        return z;
    }

    public List<String> getEstimators(Context context) {
        return List.of(VSWUWDistortionEnhancer.class.getName());
    }

    public void stop() {
        VSWEngineNativeInterface.stopEnhancer();
    }

    public EnhanceResult enhance(Context context, BgrBuffer bgrBuffer, BgrBuffer bgrBuffer2, double d, List<Rect> list, String str, IRequestCancelledChecker iRequestCancelledChecker) {
        JSONObject jSONObject;
        byte[] data = bgrBuffer.getData();
        byte[] data2 = bgrBuffer2.getData();
        PrSize size = bgrBuffer.getSize();
        bgrBuffer2.getSize();
        if (str != null && str.length() > 0) {
            try {
                jSONObject = new JSONObject(str).getJSONObject(VSWUWDistortionEnhancer.class.getName()).getJSONObject("estimatedDataJson");
            } catch (JSONException e) {
                String str2 = TAG;
                Log.e(str2, "Error while parsing extraJsonData: " + e);
                jSONObject = new JSONObject();
            }
            String str3 = TAG;
            Log.d(str3, "estimatedDataJson: " + jSONObject);
        }
        if (iRequestCancelledChecker == null || !iRequestCancelledChecker.isRequestCancelled()) {
            boolean[] checkPrecondition = VSWUWDistortionEstimator.checkPrecondition(bgrBuffer.getFilePath());
            PrSize prSize = size;
            return new EnhanceResult(enhance(data, data2, prSize.getWidth(), prSize.getHeight(), VSWUWDistortionEstimator.getSEFParam(bgrBuffer.getFilePath()), checkPrecondition));
        }
        Log.i(TAG, "Request is cancelled. Enhancement failed");
        return null;
    }

    public void releaseResources() {
    }

    @Deprecated
    public static boolean enhance(byte[] bArr, byte[] bArr2, String str, int i2, int i7) {
        boolean z;
        String str2 = TAG;
        Log.d(str2, "enhance - E");
        VSWEngineNativeInterface.printLibraryVersion();
        byte[] makeByteArray = VSWEngineNativeInterface.makeByteArray(153600);
        if (VSWUWDistortionEstimator.estimate(bArr, makeByteArray, i2, i7)) {
            z = VSWEngineNativeInterface.runUWDistortionEnhancer(bArr, bArr2, str, makeByteArray, i2, i7);
            Log.d(str2, "result : " + z);
        } else {
            Log.d(str2, "result : skip-enhance, no distortion");
            z = false;
        }
        Log.d(str2, "enhance - X");
        VSWEngineNativeInterface.releaseByteArray(makeByteArray);
        return z;
    }

    @Deprecated
    public static boolean enhance(byte[] bArr, byte[] bArr2, String str, byte[] bArr3, int i2, int i7) {
        boolean z;
        String str2 = TAG;
        Log.d(str2, "enhance - E");
        VSWEngineNativeInterface.printLibraryVersion();
        if (VSWUWDistortionEstimator.estimate(bArr, bArr3, i2, i7)) {
            z = VSWEngineNativeInterface.runUWDistortionEnhancer(bArr, bArr2, str, bArr3, i2, i7);
            Log.d(str2, "result : " + z);
        } else {
            Log.d(str2, "result : skip-enhance, no distortion");
            z = false;
        }
        Log.d(str2, "enhance - X");
        VSWEngineNativeInterface.releaseByteArray(bArr3);
        return z;
    }

    public PrSize getOutputSizeFrom(Context context, PrSize prSize) {
        return prSize;
    }
}
