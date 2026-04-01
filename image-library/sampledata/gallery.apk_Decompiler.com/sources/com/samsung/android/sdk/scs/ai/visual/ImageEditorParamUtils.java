package com.samsung.android.sdk.scs.ai.visual;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import c0.C0086a;
import com.samsung.android.sdk.scs.ai.gateway.AiServiceExecutorFactory;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.utils.Log;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ImageEditorParamUtils {
    private static final EnumMap<AiServiceExecutorFactory.ServiceType, Map<Integer, String>> FEATURE_MAP;
    private static final String TAG = "ImageEditorParamUtils";

    static {
        EnumMap<AiServiceExecutorFactory.ServiceType, Map<Integer, String>> enumMap = new EnumMap<>(AiServiceExecutorFactory.ServiceType.class);
        FEATURE_MAP = enumMap;
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        hashMap.put(0, Feature.FEATURE_VISUAL_PORTRAIT);
        hashMap.put(1, Feature.FEATURE_VISUAL_SKETCH_TO_IMAGE);
        hashMap.put(2, Feature.FEATURE_VISUAL_SKETCH_GUIDED_EDITING);
        hashMap.put(6, Feature.FEATURE_VISUAL_GEN_STICKER);
        hashMap.put(7, Feature.FEATURE_VISUAL_IMAGE_CONVERSION);
        hashMap.put(8, Feature.FEATURE_VISUAL_PET_PORTRAIT);
        hashMap.put(9, Feature.FEATURE_VISUAL_RESTYLING);
        hashMap.put(12, Feature.FEATURE_VISUAL_DESIGN_IMAGE);
        hashMap.put(13, Feature.FEATURE_VISUAL_DESIGN_STICKER);
        hashMap2.put(4, Feature.FEATURE_VISUAL_WALLPAPER);
        hashMap2.put(0, Feature.FEATURE_VISUAL_PORTRAIT_ON_DEVICE);
        hashMap2.put(1, Feature.FEATURE_VISUAL_SKETCH_TO_IMAGE_ON_DEVICE);
        hashMap2.put(2, Feature.FEATURE_VISUAL_SKETCH_GUIDED_EDITING_ON_DEVICE);
        hashMap2.put(3, Feature.FEATURE_VISUAL_GEN_EDIT_ON_DEVICE);
        hashMap2.put(5, Feature.FEATURE_VISUAL_PORTRAIT_RELIGHT_ON_DEVICE);
        hashMap2.put(7, Feature.FEATURE_VISUAL_IMAGE_CONVERSION_ON_DEVICE);
        hashMap2.put(11, Feature.FEATURE_VISUAL_AI_ERASER_ON_DEVICE);
        hashMap2.put(10, Feature.FEATURE_VISUAL_HARMONIZATION_ON_DEVICE);
        hashMap2.put(8, Feature.FEATURE_VISUAL_PET_PORTRAIT_ON_DEVICE);
        enumMap.put(AiServiceExecutorFactory.ServiceType.CLOUD_CORE, hashMap);
        enumMap.put(AiServiceExecutorFactory.ServiceType.AI_CORE, hashMap2);
    }

    private ImageEditorParamUtils() {
    }

    private static Bitmap asSharedCompat(Bitmap bitmap) {
        if (!bitmap.isMutable()) {
            return bitmap;
        }
        Bitmap.Config config = bitmap.getConfig();
        if (config == null) {
            config = Bitmap.Config.ARGB_8888;
        }
        return bitmap.copy(config, false);
    }

    public static String getFeatureName(int i2, AiServiceExecutorFactory.ServiceType serviceType) {
        return getFeatureName(i2, serviceType, (Bundle) null);
    }

    public static void toBundle(Bundle bundle, VisualAppInfo visualAppInfo) {
        bundle.putString("api-key", visualAppInfo.getApiKey());
        bundle.putString("package-signing-key", visualAppInfo.getSigningKey());
        bundle.putString("ssp-app-id", visualAppInfo.getAppId());
        bundle.putString("package-name", visualAppInfo.getPackageName());
        bundle.putString("ssp-access-token", visualAppInfo.getAccessToken());
        bundle.putString("ssp-user-id", visualAppInfo.getUserId());
        bundle.putString("ssp-server-url", visualAppInfo.getServerUrl());
        bundle.putString("ssp-account-type", visualAppInfo.getAccountType());
    }

    public static void transformBitmapsInBundle(Bundle bundle) {
        Bitmap bitmap;
        for (String next : bundle.keySet()) {
            Object obj = bundle.get(next);
            if (obj instanceof Bitmap) {
                Bitmap bitmap2 = (Bitmap) obj;
                try {
                    if (Build.VERSION.SDK_INT >= 31) {
                        bitmap = bitmap2.asShared();
                    } else {
                        bitmap = asSharedCompat(bitmap2);
                    }
                    bundle.putParcelable(next, bitmap);
                } catch (Exception e) {
                    Log.e(TAG, "Failed to create shared Bitmap", e);
                }
            }
        }
    }

    public static String getFeatureName(int i2, AiServiceExecutorFactory.ServiceType serviceType, Bundle bundle) {
        Map map = FEATURE_MAP.get(serviceType);
        Objects.requireNonNull(map);
        String str = (String) map.getOrDefault(Integer.valueOf(i2), (Object) null);
        if (str != null) {
            return (!Feature.FEATURE_VISUAL_SKETCH_GUIDED_EDITING.equals(str) || bundle == null || !bundle.containsKey("alphaRectList")) ? str : Feature.FEATURE_SKETCH_GUIDED_EDITING_CROPPING_RECT;
        }
        throw new IllegalStateException(C0086a.i(i2, "Unexpected value: "));
    }
}
