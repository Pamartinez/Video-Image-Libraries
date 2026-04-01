package com.samsung.android.sdk.moneta;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import com.samsung.android.feature.SemFloatingFeature;
import com.samsung.android.sdk.moneta.common.Constants;
import com.samsung.android.sdk.moneta.common.Logger;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\n\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u0018\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\r0\rX\u0004¢\u0006\u0004\n\u0002\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/samsung/android/sdk/moneta/PdeSdk;", "", "<init>", "()V", "isSupported", "", "context", "Landroid/content/Context;", "featureType", "Lcom/samsung/android/sdk/moneta/FeatureType;", "TAG", "", "URI", "Landroid/net/Uri;", "kotlin.jvm.PlatformType", "Landroid/net/Uri;", "IS_SUPPORTED_FEATURE", "FEATURE_TYPE", "RESULT", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PdeSdk {
    private static final String FEATURE_TYPE = "featureType";
    public static final PdeSdk INSTANCE = new PdeSdk();
    private static final String IS_SUPPORTED_FEATURE = "isSupportedFeature";
    private static final String RESULT = "result";
    private static final String TAG = "PdeSdk";
    private static final Uri URI = Uri.parse("content://com.samsung.android.pde.fusioninference");

    private PdeSdk() {
    }

    public final boolean isSupported(Context context) {
        j.e(context, "context");
        try {
            boolean z = SemFloatingFeature.getInstance().getBoolean(Constants.SEC_FLOATING_FEATURE_FRAMEWORK_SUPPORT_PERSONALIZED_DATA_CORE);
            Logger logger = Logger.INSTANCE;
            logger.i$pde_sdk_1_0_40_release(TAG, "[isSupported] in (PDE): " + z);
            return z;
        } catch (Exception unused) {
            Logger.INSTANCE.e$pde_sdk_1_0_40_release(TAG, "[isSupported] PDE not supported");
            return false;
        }
    }

    public final boolean isSupported(Context context, FeatureType featureType) {
        j.e(context, "context");
        j.e(featureType, FEATURE_TYPE);
        if (!isSupported(context)) {
            return false;
        }
        Bundle bundle = new Bundle();
        bundle.putString(FEATURE_TYPE, featureType.toString());
        try {
            Bundle call = context.getContentResolver().call(URI, IS_SUPPORTED_FEATURE, (String) null, bundle);
            boolean z = call != null ? call.getBoolean("result") : false;
            Logger logger = Logger.INSTANCE;
            logger.i$pde_sdk_1_0_40_release(TAG, "[isSupported] in (" + featureType + "): " + z);
            return z;
        } catch (Exception e) {
            Logger logger2 = Logger.INSTANCE;
            logger2.i$pde_sdk_1_0_40_release(TAG, "Not supported: " + e.getMessage());
            return false;
        }
    }
}
