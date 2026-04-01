package com.samsung.android.gallery.widget.utils;

import android.content.Context;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.SeApiCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PinnedEdgeProperty {
    private static final PinnedEdgeProperty sInstance = new PinnedEdgeProperty();
    private final Object LOCK;
    private final boolean SUPPORT;
    private volatile Boolean mLeftPinnedEdge;

    public PinnedEdgeProperty() {
        boolean z;
        if (!SdkConfig.match(SdkConfig.SEM.R_MR5) || !SeApiCompat.getFloatingFeatureBoolean("SEC_FLOATING_FEATURE_FRAMEWORK_SUPPORT_FOLDABLE_TYPE_FOLD")) {
            z = false;
        } else {
            z = true;
        }
        this.SUPPORT = z;
        this.LOCK = new Object();
    }

    public static PinnedEdgeProperty getInstance() {
        return sInstance;
    }

    public int getWidth(Context context) {
        if (this.SUPPORT) {
            return SeApiCompat.getPinnedEdgeWidth(context);
        }
        return 0;
    }

    public boolean isLeftPinnedEdge(Context context) {
        boolean booleanValue;
        if (!this.SUPPORT) {
            return false;
        }
        synchronized (this.LOCK) {
            try {
                if (this.mLeftPinnedEdge == null) {
                    this.mLeftPinnedEdge = Boolean.valueOf(SeApiCompat.isLeftPinnedEdge(context));
                }
                booleanValue = this.mLeftPinnedEdge.booleanValue();
            } catch (Throwable th) {
                throw th;
            }
        }
        return booleanValue;
    }

    public void recycle() {
        if (this.SUPPORT) {
            synchronized (this.LOCK) {
                this.mLeftPinnedEdge = null;
            }
        }
    }
}
