package com.samsung.android.gallery.module.suggested;

import A.a;
import android.content.Context;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SuggestedManager {
    private static volatile SuggestedManager sInstance;

    private SuggestedManager() {
    }

    private int checkCleanOutBadgeCount(Context context) {
        if (getCleanOutBadge() && PreferenceCache.CleanOutBadgeCount.getInt() > 0) {
            return 1;
        }
        return 0;
    }

    public static SuggestedManager getInstance() {
        if (sInstance == null) {
            synchronized (SuggestedManager.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new SuggestedManager();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }

    private boolean isNewCleanOutUpdated(Context context) {
        int cleanOutBadgeCount = getCleanOutBadgeCount();
        int loadCleanOutBadgeCount = loadCleanOutBadgeCount(context);
        Log.d("SuggestedManager", a.d(cleanOutBadgeCount, loadCleanOutBadgeCount, "isNewCleanOutUpdated {", GlobalPostProcInternalPPInterface.SPLIT_REGEX, "}"));
        if (cleanOutBadgeCount == loadCleanOutBadgeCount) {
            return false;
        }
        if ((cleanOutBadgeCount >= 10 || loadCleanOutBadgeCount < 10) && (cleanOutBadgeCount < 10 || loadCleanOutBadgeCount >= 10)) {
            return false;
        }
        setCleanOutBadgeCount(loadCleanOutBadgeCount);
        setCleanOutBadge(true);
        return true;
    }

    public int getAllEventBadgeCount(Context context) {
        if (getEventBadgeCount() > 0 || checkCleanOutBadgeCount(context) > 0) {
            return 1;
        }
        return 0;
    }

    public boolean getCleanOutBadge() {
        return PreferenceCache.CleanOutBadge.getBoolean();
    }

    public int getCleanOutBadgeCount() {
        return PreferenceCache.CleanOutBadgeCount.getInt();
    }

    public int getEventBadgeCount() {
        if (PreferenceCache.EventBadgeCount.getInt() > 0) {
            return 1;
        }
        return 0;
    }

    public boolean isNewSuggestionUpdated(Context context) {
        if (!Features.isEnabled(Features.USE_CMH)) {
            return false;
        }
        return isNewCleanOutUpdated(context);
    }

    public int loadCleanOutBadgeCount(Context context) {
        return SuggestedHelper.getInstance().getCleanOutCount(context);
    }

    public void setCleanOutBadge(boolean z) {
        PreferenceCache.CleanOutBadge.setBoolean(z);
    }

    public void setCleanOutBadgeCount(int i2) {
        PreferenceCache.CleanOutBadgeCount.setInt(i2);
    }
}
