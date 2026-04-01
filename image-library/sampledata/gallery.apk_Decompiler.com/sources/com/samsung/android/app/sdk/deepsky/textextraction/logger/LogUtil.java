package com.samsung.android.app.sdk.deepsky.textextraction.logger;

import A.a;
import android.graphics.Bitmap;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\n\u0010\u0004\u001a\u00020\u0005*\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/logger/LogUtil;", "", "<init>", "()V", "logString", "", "Landroid/graphics/Bitmap;", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class LogUtil {
    public static final LogUtil INSTANCE = new LogUtil();

    private LogUtil() {
    }

    public final String logString(Bitmap bitmap) {
        j.e(bitmap, "<this>");
        return a.d(bitmap.getWidth(), bitmap.getHeight(), "bitmap=(", "x", ")");
    }
}
