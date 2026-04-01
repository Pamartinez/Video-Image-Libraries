package com.samsung.android.gallery.support.library.v11.system;

import android.content.Context;
import com.samsung.android.gallery.support.library.abstraction.DvfsManagerCompat;
import com.samsung.android.os.SemDvfsManager;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SemDvfsManagerCompat120 implements DvfsManagerCompat {
    private final SemDvfsManager mSemDvfsManager;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum BoostType {
        NOP(-1);
        
        final int mHintType;

        private BoostType(int i2) {
            this.mHintType = i2;
        }
    }

    public SemDvfsManagerCompat120(Context context) {
        this(context, BoostType.NOP);
    }

    private SemDvfsManager createSemDvfsManager(Context context, BoostType boostType) {
        if (boostType == BoostType.NOP) {
            return null;
        }
        SemDvfsManager createInstance = SemDvfsManager.createInstance(context, boostType.name());
        if (createInstance != null && createInstance.checkHintSupported(boostType.mHintType)) {
            createInstance.setHint(boostType.mHintType);
        }
        return createInstance;
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

    public SemDvfsManagerCompat120(Context context, BoostType boostType) {
        this.mSemDvfsManager = createSemDvfsManager(context, boostType);
    }

    public void acquire(int i2) {
        SemDvfsManager semDvfsManager = this.mSemDvfsManager;
        if (semDvfsManager != null) {
            semDvfsManager.acquire(i2);
        }
    }
}
