package com.samsung.android.gallery.support.library.v11.system;

import android.content.Context;
import com.samsung.android.gallery.support.library.abstraction.DvfsManagerCompat;
import com.samsung.android.gallery.support.library.v0.system.SemBoosterCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SemBoosterCompat120 extends SemBoosterCompat {
    private static volatile SemBoosterCompat sInstance;

    public SemBoosterCompat120(Context context) {
        super(context);
    }

    public static SemBoosterCompat getInstance(Context context) {
        SemBoosterCompat semBoosterCompat;
        SemBoosterCompat semBoosterCompat2 = sInstance;
        if (semBoosterCompat2 != null) {
            return semBoosterCompat2;
        }
        synchronized (SemBoosterCompat120.class) {
            try {
                if (sInstance == null) {
                    semBoosterCompat = new SemBoosterCompat120(context);
                    sInstance = semBoosterCompat;
                } else {
                    semBoosterCompat = sInstance;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return semBoosterCompat;
    }

    public DvfsManagerCompat createScrollBoost() {
        return new SemDvfsManagerCompat120(getContext());
    }

    public DvfsManagerCompat createTouchBooster() {
        return new SemDvfsManagerCompat120(getContext());
    }

    public DvfsManagerCompat createTouchTailBooster() {
        return new SemDvfsManagerCompat120(getContext());
    }

    public void acquireFull() {
    }

    public void acquireGpu(int i2) {
    }
}
