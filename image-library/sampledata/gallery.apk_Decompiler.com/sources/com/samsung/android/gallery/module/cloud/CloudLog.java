package com.samsung.android.gallery.module.cloud;

import A4.M;
import Ab.b;
import Sd.o;
import android.content.Context;
import android.util.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CloudLog {
    private static volatile boolean sInitialized = false;

    private static void init(Context context) {
        if (!sInitialized) {
            sInitialized = true;
            if (context == null) {
                try {
                    Log.w("[SCG-SDK][0.0.0019][SCGalleryLog]", "init: applicationContext is null");
                } catch (Throwable th) {
                    Log.e("[SCG-SDK][0.0.0019][SCGalleryLog]", "init: exception occurred", th);
                }
            } else {
                Context applicationContext = context.getApplicationContext();
                if (applicationContext == null) {
                    Log.w("[SCG-SDK][0.0.0019][SCGalleryLog]", "init: getApplicationContext() returned null, initialization failed. no-op mode");
                    return;
                }
                o.p(applicationContext);
                Log.i("[SCG-SDK][0.0.0019][SCGalleryLog]", "init: initialized successfully");
            }
        }
    }

    /* access modifiers changed from: private */
    public static void lambda$sendLog$0(Context context, String str, int i2) {
        init(context);
        o.v(i2, -123456, str);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$sendLog$1(Context context, String str, int i2, int i7) {
        init(context);
        o.v(i2, i7, str);
    }

    public static void sendLog(Context context, String str, int i2) {
        ThreadUtil.runOnBgThread(new b((Object) context, (Object) str, i2, 26));
    }

    public static void sendLog(Context context, String str, int i2, int i7) {
        ThreadUtil.runOnBgThread(new M((Object) context, (Object) str, i2, i7, 4));
    }
}
