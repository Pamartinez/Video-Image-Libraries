package com.samsung.android.app.sdk.deepsky.objectcapture;

import android.content.Context;
import com.samsung.android.app.sdk.deepsky.objectcapture.base.ObjectCapture;
import com.samsung.android.app.sdk.deepsky.objectcapture.common.Rune;
import com.samsung.android.app.sdk.deepsky.objectcapture.impl.ArcSoftObjectCaptureImpl;
import com.samsung.android.app.sdk.deepsky.objectcapture.impl.SribObjectCaptureImpl;
import com.samsung.android.app.sdk.deepsky.objectcapture.logger.LibLogger;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0007R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/ObjectCaptureProvider;", "", "<init>", "()V", "TAG", "", "getInstance", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectCapture;", "context", "Landroid/content/Context;", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ObjectCaptureProvider {
    public static final ObjectCaptureProvider INSTANCE = new ObjectCaptureProvider();
    private static final String TAG = "ObjectCaptureProvider";

    private ObjectCaptureProvider() {
    }

    public static final ObjectCapture getInstance(Context context) {
        j.e(context, "context");
        String packageName = context.getPackageName();
        LibLogger.i(TAG, "ObjectCapture initialized at " + packageName);
        if (Rune.INSTANCE.getSUPPORT_SRIB_CLIPPER()) {
            LibLogger.i(TAG, "initialize SribObjectCapture");
            return new SribObjectCaptureImpl(context);
        }
        LibLogger.i(TAG, "initialize ArcSoftObjectCapture");
        return new ArcSoftObjectCaptureImpl(context);
    }
}
