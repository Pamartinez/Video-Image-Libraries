package com.samsung.android.sdk.moneta;

import android.content.Context;
import com.samsung.android.feature.SemFloatingFeature;
import com.samsung.android.sdk.moneta.common.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/PdcSdk;", "", "<init>", "()V", "isPDCaCoreSupported", "", "context", "Landroid/content/Context;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PdcSdk {
    public static final PdcSdk INSTANCE = new PdcSdk();

    private PdcSdk() {
    }

    public final boolean isPDCaCoreSupported(Context context) {
        j.e(context, "context");
        return SemFloatingFeature.getInstance().getBoolean(Constants.SEC_FLOATING_FEATURE_FRAMEWORK_SUPPORT_PERSONALIZED_DATA_CORE);
    }
}
