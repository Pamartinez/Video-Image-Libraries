package com.samsung.android.gallery.module.bgm.updater;

import A4.L;
import F8.b;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.samsung.android.gallery.support.utils.AndroidCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.MobileApplicationBundleWrapper;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BgmDownloadReceiver extends BroadcastReceiver {
    private static final BgmDownloadReceiver sInstance = new BgmDownloadReceiver();
    protected final ArrayList<DownloadListener> mCallBacks = new ArrayList<>();

    public static BgmDownloadReceiver getInstance() {
        return sInstance;
    }

    private void notifyDownloadAll(boolean z) {
        Log.d("BgmDownloadReceiver", "download all", Boolean.valueOf(z));
        synchronized (this.mCallBacks) {
            this.mCallBacks.forEach(new L(z, 13));
        }
    }

    private void register() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.sec.android.app.ve.vebgm.downloadSingleBgm");
        intentFilter.addAction("com.sec.android.app.ve.vebgm.downloadAllBgms");
        AndroidCompat.registerReceiver(AppResources.getAppContext(), this, intentFilter);
    }

    private void unregister() {
        AndroidCompat.unregisterReceiver(AppResources.getAppContext(), this);
    }

    public void notifySingleDownloaded(boolean z, String str, String str2, Object obj) {
        ArrayList arrayList;
        int i2;
        if (z) {
            arrayList = (ArrayList) obj;
        } else {
            arrayList = null;
        }
        Boolean valueOf = Boolean.valueOf(z);
        if (arrayList != null) {
            i2 = arrayList.size();
        } else {
            i2 = -1;
        }
        Log.d("BgmDownloadReceiver", "download Single", valueOf, str, str2, Integer.valueOf(i2));
        synchronized (this.mCallBacks) {
            this.mCallBacks.forEach(new b(z, str2, arrayList, 1));
        }
    }

    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            Log.e("BgmDownloadReceiver", "no intent");
            return;
        }
        Log.d("BgmDownloadReceiver", "onReceive", Logger.getEncodedString(intent.getAction()));
        boolean z = true;
        if ("com.sec.android.app.ve.vebgm.downloadSingleBgm".equals(intent.getAction())) {
            if (intent.getIntExtra("status", 0) != 1) {
                z = false;
            }
            notifySingleDownloaded(z, intent.getStringExtra(MobileApplicationBundleWrapper.BUNDLE_KEY_PACKAGE_ID), intent.getStringExtra("bgmName"), intent.getSerializableExtra("uris"));
        } else if ("com.sec.android.app.ve.vebgm.downloadAllBgms".equals(intent.getAction())) {
            if (intent.getIntExtra("status", 0) != 1) {
                z = false;
            }
            notifyDownloadAll(z);
        }
    }

    public void registerCallBack(DownloadListener downloadListener) {
        synchronized (this.mCallBacks) {
            try {
                if (!this.mCallBacks.contains(downloadListener)) {
                    if (this.mCallBacks.isEmpty()) {
                        register();
                    }
                    this.mCallBacks.add(downloadListener);
                    Log.d("BgmDownloadReceiver", "registerCallback", Integer.valueOf(this.mCallBacks.size()));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void unregisterCallBack(DownloadListener downloadListener) {
        synchronized (this.mCallBacks) {
            try {
                if (this.mCallBacks.contains(downloadListener)) {
                    this.mCallBacks.remove(downloadListener);
                    if (this.mCallBacks.isEmpty()) {
                        unregister();
                    }
                    Log.d("BgmDownloadReceiver", "unregisterCallback", Integer.valueOf(this.mCallBacks.size()));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
