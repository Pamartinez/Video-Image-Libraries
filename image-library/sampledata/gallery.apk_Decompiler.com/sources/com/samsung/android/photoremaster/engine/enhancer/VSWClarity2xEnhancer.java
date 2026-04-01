package com.samsung.android.photoremaster.engine.enhancer;

import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import com.samsung.android.photoremaster.engine.nativeInterface.VSWEngineNativeInterface;
import com.samsung.android.photoremaster.sdk.enhancer.EnhanceResult;
import com.samsung.android.photoremaster.sdk.enhancer.IEnhancer;
import com.samsung.android.photoremaster.sdk.ip.BgrBuffer;
import com.samsung.android.photoremaster.sdk.ip.IRequestCancelledChecker;
import com.samsung.android.photoremaster.sdk.ip.PrSize;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import t1.C0280e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VSWClarity2xEnhancer implements IEnhancer {
    private static final String TAG = "Remaster-VSWClarity2xEnhancer";

    public static boolean enhance(byte[] bArr, byte[] bArr2, int i2, int i7, int i8) {
        String str = TAG;
        Log.d(str, "enhance - E");
        VSWEngineNativeInterface.printLibraryVersion();
        boolean runClarityEnhancer = VSWEngineNativeInterface.runClarityEnhancer(bArr, bArr2, i2, i7, i8);
        C0280e.g("result : ", str, str, "enhance - X", runClarityEnhancer);
        return runClarityEnhancer;
    }

    public static boolean enhanceClarity(byte[] bArr, byte[] bArr2, int i2, int i7) {
        return enhance(bArr, bArr2, i2, i7, 1);
    }

    public List<String> getEstimators(Context context) {
        return List.of(VSWBlurEnhancer.class.getName());
    }

    public PrSize getOutputSizeFrom(Context context, PrSize prSize) {
        return new PrSize(prSize.getWidth() * 2, prSize.getHeight() * 2);
    }

    public void stop() {
        VSWEngineNativeInterface.stopEnhancer();
    }

    public EnhanceResult enhance(Context context, BgrBuffer bgrBuffer, BgrBuffer bgrBuffer2, double d, List<Rect> list, String str, IRequestCancelledChecker iRequestCancelledChecker) {
        JSONObject jSONObject;
        byte[] data = bgrBuffer.getData();
        byte[] data2 = bgrBuffer2.getData();
        PrSize size = bgrBuffer.getSize();
        int width = bgrBuffer2.getSize().getWidth() / size.getWidth();
        if (str != null && str.length() > 0) {
            try {
                jSONObject = new JSONObject(str).getJSONObject(VSWClarity2xEnhancer.class.getName()).getJSONObject("estimatedDataJson");
            } catch (JSONException e) {
                String str2 = TAG;
                Log.e(str2, "Error while parsing extraJsonData: " + e);
                jSONObject = new JSONObject();
            }
            String str3 = TAG;
            Log.d(str3, "estimatedDataJson: " + jSONObject);
        }
        if (iRequestCancelledChecker == null || !iRequestCancelledChecker.isRequestCancelled()) {
            return new EnhanceResult(enhance(data, data2, size.getWidth(), size.getHeight(), width));
        }
        Log.i(TAG, "Request is cancelled. Enhancement failed");
        return null;
    }

    public void releaseResources() {
    }
}
