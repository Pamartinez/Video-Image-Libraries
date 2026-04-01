package com.samsung.android.gallery.module.receiver;

import A.a;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class SettingObserver {
    protected final String TAG = getClass().getSimpleName();
    private ContentObserver mContentObserver;

    private ContentObserver createContentObserver() {
        return new ContentObserver((Handler) null) {
            public void onChange(boolean z) {
                SettingObserver.this.onDataChanged(z);
            }
        };
    }

    /* access modifiers changed from: private */
    public void onDataChanged(boolean z) {
        Boolean bool = (Boolean) Blackboard.getApplicationInstance().read(getGlobalKey());
        boolean isSettingEnabled = isSettingEnabled();
        if (bool == null || bool.booleanValue() != isSettingEnabled) {
            updateCommonData(z, isSettingEnabled);
            Blackboard.getApplicationInstance().publish(getGlobalKey(), Boolean.valueOf(isSettingEnabled));
        }
        String str = this.TAG;
        Log.majorEvent(str, "onDataChanged {" + isSettingEnabled + GlobalPostProcInternalPPInterface.SPLIT_REGEX + bool + "}");
    }

    public final Context getAppContext() {
        Context appContext = AppResources.getAppContext();
        if (appContext != null) {
            return appContext;
        }
        return Blackboard.getAppContext();
    }

    public abstract String getGlobalKey();

    public Uri getObservingUri() {
        return Settings.System.getUriFor(getUriString());
    }

    public abstract String getUriString();

    public boolean isSettingEnabled() {
        if (SeApiCompat.getSettingsSystemInt(getAppContext(), getUriString(), 0) > 0) {
            return true;
        }
        return false;
    }

    public void registerObserver(Context context) {
        try {
            if (this.mContentObserver == null) {
                this.mContentObserver = createContentObserver();
            }
            context.getContentResolver().registerContentObserver(getObservingUri(), false, this.mContentObserver);
        } catch (Exception e) {
            a.s(e, new StringBuilder("registerObserver failed e="), this.TAG);
            unregisterObserver(context);
        }
    }

    public String toString() {
        return this.TAG;
    }

    public final void unregisterObserver(Context context) {
        try {
            if (this.mContentObserver != null) {
                context.getContentResolver().unregisterContentObserver(this.mContentObserver);
                this.mContentObserver = null;
            }
        } catch (Exception e) {
            a.s(e, new StringBuilder("unregisterObserver failed e="), this.TAG);
        }
    }

    public void updateCommonData(boolean z, boolean z3) {
    }
}
