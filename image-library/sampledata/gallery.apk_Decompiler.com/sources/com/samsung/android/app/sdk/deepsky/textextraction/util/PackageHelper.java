package com.samsung.android.app.sdk.deepsky.textextraction.util;

import android.content.Context;
import android.content.pm.PackageManager;
import com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/util/PackageHelper;", "", "<init>", "()V", "Landroid/content/Context;", "context", "", "targetPackage", "", "isPackageExists", "(Landroid/content/Context;Ljava/lang/String;)Z", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PackageHelper {
    public static final PackageHelper INSTANCE = new PackageHelper();

    private PackageHelper() {
    }

    public final boolean isPackageExists(Context context, String str) {
        j.e(context, "context");
        j.e(str, "targetPackage");
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null || packageManager.getPackageInfo(str, 128) == null) {
                return false;
            }
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            LibLogger.e("PackageHelper", str.concat(" does not exist"));
            return false;
        }
    }
}
