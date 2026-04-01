package com.samsung.android.sdk.moneta.common;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tR\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u0004\n\u0002\u0010\u0006¨\u0006\n"}, d2 = {"Lcom/samsung/android/sdk/moneta/common/Util;", "", "<init>", "()V", "currentVersion", "", "Ljava/lang/Long;", "getVersion", "context", "Landroid/content/Context;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Util {
    public static final Util INSTANCE = new Util();
    private static Long currentVersion;

    private Util() {
    }

    public final /* synthetic */ long getVersion(Context context) {
        j.e(context, "context");
        Long l = currentVersion;
        if (l != null) {
            return l.longValue();
        }
        try {
            long longVersionCode = context.getApplicationContext().getPackageManager().getPackageInfo(Constants.SMART_SUGGESTIONS_PACKAGE_NAME, 0).getLongVersionCode();
            currentVersion = Long.valueOf(longVersionCode);
            if (longVersionCode >= Constants.PACKAGE_VERSION_ONEUI80) {
                return longVersionCode;
            }
            Logger logger = Logger.INSTANCE;
            logger.e$pde_sdk_1_0_40_release(Constants.TAG, "Not supporting SmartSuggestions version " + longVersionCode);
            return longVersionCode;
        } catch (Exception unused) {
            Logger.INSTANCE.e$pde_sdk_1_0_40_release(Constants.TAG, "SmartSuggestions is not installed");
            currentVersion = 0L;
            return 0;
        }
    }
}
