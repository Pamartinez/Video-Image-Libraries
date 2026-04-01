package com.samsung.android.gallery.support.library.v0.system;

import android.content.Context;
import com.samsung.android.gallery.support.library.abstraction.DvfsManagerCompat;
import com.samsung.android.gallery.support.library.utils.Reflector;
import com.samsung.android.os.SemDvfsManager;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SemDvfsManagerCompat implements DvfsManagerCompat {
    private final SemDvfsManager mSemDvfsManager;

    public SemDvfsManagerCompat(Context context, String str, int i2, long j2) {
        this.mSemDvfsManager = createSemDvfsManager(context, str, i2, j2);
    }

    private SemDvfsManager createSemDvfsManager(Context context, String str, int i2, long j2) {
        try {
            return (SemDvfsManager) Reflector.invoke(Reflector.getMethod(SemDvfsManager.class, "createInstance", Context.class, String.class, Integer.TYPE), (Object) null, context, str, Integer.valueOf(i2));
        } catch (Error | Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void acquire() {
        SemDvfsManager semDvfsManager = this.mSemDvfsManager;
        if (semDvfsManager != null) {
            semDvfsManager.acquire();
        }
    }

    public void release() {
        SemDvfsManager semDvfsManager = this.mSemDvfsManager;
        if (semDvfsManager != null) {
            semDvfsManager.release();
        }
    }

    public void acquire(int i2) {
        SemDvfsManager semDvfsManager = this.mSemDvfsManager;
        if (semDvfsManager != null) {
            semDvfsManager.acquire(i2);
        }
    }
}
